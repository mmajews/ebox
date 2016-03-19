/*
 * Copyright © 2016 and Confidential to Pegasystems Inc. All rights reserved.
 */

package braincode.mobile.ebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.net.URI;

import braincode.mobile.ebox.R;
import braincode.mobile.ebox.gesture.GestureListener;
import braincode.mobile.ebox.sockets.SocketController;


public class HelloActivity extends Activity {

    public static final String HTTP_SERVER = "http://10.22.102.197:3000";

    private GestureDetector gestureDetector;

    private SocketController socketController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        // socket.io
        URI uri = URI.create(HTTP_SERVER);
        socketController = new SocketController(uri);

        GestureListener gestureListener = new GestureListener(socketController);
        gestureDetector = new GestureDetector(this, gestureListener);
        gestureDetector.setOnDoubleTapListener(gestureListener);

        Log.d("Gesture", "GestureDetector registered");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        socketController.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        socketController.disconnect();
    }
}