package com.sdk.agam.eventtracker;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.UUID;

import static android.support.v4.content.ContextCompat.getSystemService;

public class Helpers {

    private static final String PREF_KEY = "PREF_EVENT_TRACKER";

    /**
     * Get or create (if not created already) a unique identifier for this device
     *
     * @param context
     * @return String deviceUUID, a unique identifier for this device.
     */
    private static String getOrCreateUUIDPreference(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(
                PREF_KEY, Context.MODE_PRIVATE);
        String deviceUUID = sharedPrefs.getString(PREF_KEY, null);

        // Create if null
        if (deviceUUID == null) {
            // We will generate a unique 64 character long uuid.
            deviceUUID = UUID.randomUUID().toString();
            deviceUUID = deviceUUID.replace("-", "").substring(0, 16);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(PREF_KEY, deviceUUID);
            editor.commit();
        }
        return deviceUUID;
    }

    /**
     * Return a unique identifier of the current device.
     * Note: can add more checks to get MAC (wifi) or a Unique telephony number (IMEI, MEID...)
     * Read more:
     * https://developer.android.com/training/articles/user-data-ids#i_abuse_detection_detecting_high_value_stolen_credentials
     *
     * @param context (Context) a context used for getting application specific info.
     * @return String a unique device identifier.
     */
    public static String getUniqueDeviceId(Context context) {

        String deviceId = getOrCreateUUIDPreference(context);
        try {
            // Read more here:
            // https://en.proft.me/2017/06/13/how-get-unique-id-identify-android-devices/
            deviceId = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (SecurityException e) {
            Log.e("EventTracker.Helper",
                    "getUniqueDeviceId: fails without proper permissions");
        } finally {
            return deviceId;
        }
    }

    /**
     * Converts an event message with the requiered info (apiKey + deviceUID) into a JSON payload.
     * @param apiKey - apiKey for EventTracker's SDK
     * @param deviceUID - a unique identifier used for EventTracker's SDK
     * @param em - an EventMessage send to the server.
     * @return a JSONObject used for the HTTP request.
     * @throws Exception
     */
    public static JSONObject convertToHTTPPayload(String apiKey, String deviceUID, EventMessage em) throws Exception {
        try {
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("apiKey", apiKey);
            jsonPayload.put("deviceID", deviceUID);
            jsonPayload.put("action", em.getActionKey());
            jsonPayload.put("data", em.getData());
            return jsonPayload;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new Exception("Could not convert to HTTP payload.");
    }

    /**
     * Returns the current Ip in IPv4 format
     * Taken from: https://stackoverflow.com/questions/11015912/how-do-i-get-ip-address-in-ipv4-format
     * Note: If we want the remote ip address then we can use something like wtfismyip.com/text
     * @return ip String
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    // for getting IPV4 format
                    String ip = inetAddress.getHostAddress().toString();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return ip;
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }

}
