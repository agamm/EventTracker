package com.sdk.agam.eventtracker;

import org.json.JSONObject;

public class EventMessage {

    private String actionKey;
    private JSONObject data;

    EventMessage(String actionKey, JSONObject data) {
        this.actionKey = actionKey;
        this.data = data;
    }

    public String getActionKey() {
        return actionKey;
    }


    public JSONObject getData() {
        return data;
    }
}
