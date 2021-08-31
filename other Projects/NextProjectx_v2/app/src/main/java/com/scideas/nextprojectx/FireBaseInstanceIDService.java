package com.scideas.nextprojectx;

import android.util.Log;

/**
 * Created by Niraj kumar -scideas - slon- on 19/05/2020.
 */

public class FireBaseInstanceIDService extends FireBaseMessagingService {

    public static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.e( TAG, "onNewToken: "+token  );
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {

    }
}
