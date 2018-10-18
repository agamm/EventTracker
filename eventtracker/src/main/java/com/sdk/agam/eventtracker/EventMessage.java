package com.sdk.agam.eventtracker;

import org.json.JSONObject;

public class EventMessage {

    private String actionKey;
    private JSONObject data;

    /**
     * The constructor for a new EventMessage
     * Used for clarification for events structure.
     * @param actionKey the identifier of the current event (network, location ...)
     * @param data specific information for the current event.
     */
    EventMessage(String actionKey, JSONObject data) {
        this.actionKey = actionKey;
        this.data = data;
    }

    /**
     * Basic getter for actionKey
     * @return String actionKey
     */
    public String getActionKey() {
        return actionKey;
    }

    /**
     * Basic getter for data
     * @return JSONObject data
     */
    public JSONObject getData() {
        return data;
    }
}
