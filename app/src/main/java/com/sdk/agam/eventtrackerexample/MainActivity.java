package com.sdk.agam.eventtrackerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sdk.agam.eventtracker.EventTracker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventTracker et = new EventTracker();
        et.init("aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
