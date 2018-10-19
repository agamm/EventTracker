package com.sdk.agam.eventtrackerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sdk.agam.eventtracker.EventTracker;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventTracker et = new EventTracker(getApplicationContext(), true);
        et.init("aaaaaaaaaaaaaaaa", EventTracker.getDeviceUID(getApplicationContext()));
        try {
            et.track("network", new JSONObject().put("Initialization", "OK"));
            sleep(5000);

            // Test the max size of the queue
//            et.track("network", new JSONObject().put("TestQueue", "OK0"));
//            et.track("network", new JSONObject().put("TestQueue", "OK1"));
//            et.track("network", new JSONObject().put("TestQueue", "OK2"));
//            et.track("network", new JSONObject().put("TestQueue", "OK3"));
//            et.track("network", new JSONObject().put("TestQueue", "OK4"));
//            et.track("network", new JSONObject().put("TestQueue", "OK5"));
//            et.track("network", new JSONObject().put("TestQueue", "OK6"));
//            et.track("network", new JSONObject().put("TestQueue", "OK7"));
//            et.track("network", new JSONObject().put("TestQueue", "OK8"));
//            et.track("network", new JSONObject().put("TestQueue", "OK9"));
//            et.track("network", new JSONObject().put("TestQueue", "OK10"));
//            et.track("network", new JSONObject().put("TestQueue", "OK11"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
