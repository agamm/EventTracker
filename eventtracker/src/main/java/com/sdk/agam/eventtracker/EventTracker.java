package com.sdk.agam.eventtracker;

import org.json.JSONObject;
import java.util.concurrent.ArrayBlockingQueue;

public class EventTracker {

    private String apiKey;
    private String deviceUID;
    private static final Integer APIKEY_LENGTH = 16;
    private static final Integer DEVICEUID_LENGTH = 32;
    private ArrayBlockingQueue<EventMessage> eventQueue;
    private static final Integer EVENTQUEUE_SIZE = 10;
    private static final Integer FLUSH_INTERVAL_SECONDS = 10;


    /**
     * Initialize the SDK with the basic tracking identifiers.
     * In addition, check for valid parameter usages.
     * @param apiKey the apiKey used for identifying SDK usage (user, ratelimit etc...)
     * @param deviceUID the specific user unique identifier we will event track
     */
    public void init(String apiKey, String deviceUID) {

        if (apiKey == null || apiKey.length() != APIKEY_LENGTH) {
            throw new IllegalArgumentException("apiKey must be a valid 32 character string");
        }

        if (deviceUID == null || deviceUID.length() != DEVICEUID_LENGTH) {
            throw new IllegalArgumentException("deviceUID must be a valid 32 character string");
        }

        this.apiKey = apiKey;
        this.deviceUID = deviceUID;

        // We are using an ArrayBlockingQueue as it is -
        // more performance aware because of the fixed size.
        // Also this is a thread-safe implementation of a Queue
        // - which we need.
        this.eventQueue = new ArrayBlockingQueue<EventMessage>(EVENTQUEUE_SIZE);


        // Initialize the network background task for flushing the events.

        // Initialize the default network connectivity status event tracking.
    }

    /**
     * Adds a new event for the next batch of events sent to the server.
     * Note: event is used as the same meaning of action (only used because of specification).
     * @param actionKey the identifier of the current event (network, location ...)
     * @param data specific information for the current event.
     */
    public void track(String actionKey, JSONObject data) {
        EventMessage em = new EventMessage(actionKey, data);

        // Try to add a new event to the queue (producer)
        // If offer fails (eg the queue is full, we will remove elements until it works)
        while(!this.eventQueue.offer(em)) {
            this.eventQueue.poll();
        }
    }


}
