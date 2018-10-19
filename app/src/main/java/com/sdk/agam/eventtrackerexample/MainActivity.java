package com.sdk.agam.eventtrackerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdk.agam.eventtracker.EventTracker;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventTracker et = new EventTracker();
        et.init("aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        try {
            et.track("network", new JSONObject().put("Initialization", "OK"));
            sleep(5000);
            et.track("network", new JSONObject().put("Initialization2", "OK2"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
