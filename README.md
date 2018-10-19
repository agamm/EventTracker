## SDK to track events in an Android Application.
By default tacks network state and activity resume/pause. You can send custom data too.

### Usage
     EventTracker et = new EventTracker(getApplicationContext(), true);
     et.init("apiKeyHere", EventTracker.getDeviceUID(getApplicationContext()));
     et.track("network", new JSONObject().put("Initialization", "OK"));

    You might use https://webhook.site to log the events, as it uses HTTPS.

### Things to do if I had more time:
- Permissions:
    * Try to use more permissions if we have them (fallbacks).
    * For example the unique identifier might need a better identifier and we might have it
    from an already used permission.
        * Side note, if I were to use this to the extreme then I would put the identifier in a
            public place in the device where other apps that might be using me could make use of
            those higher permissions and would essentially get the same identifier :)
- Logs + Debug Mode:
    * Use timber as log provider
- API versions:
    * Check that we are really using the minimum api versions we could possibly use.
- Smarter networking
    * I would firstly move away from JSON to a binary implementation (for more packed data) and
    would probably use
- Cleanup
    * I would add a better way of cleaning myself up, as for now I am not doing shit about it.
    (onDestroy ...)
    * Maybe even add a public destroy method.
- CR:
    * Probably go through the whole code in a more rigorous scan and try to ask a friend to CR it.
- README:
    * Add an Install section + ARR file.
- Tests:
     * Add tests to all the functions.
