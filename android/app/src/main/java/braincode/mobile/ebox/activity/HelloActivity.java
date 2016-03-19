/*
 * Copyright © 2016 and Confidential to Pegasystems Inc. All rights reserved.
 */

package braincode.mobile.ebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.net.URI;

import braincode.mobile.ebox.R;
import braincode.mobile.ebox.gesture.GestureListener;
import braincode.mobile.ebox.sockets.SocketController;


public class HelloActivity extends Activity {

    public static String HTTP_SERVER = "http://10.22.102.197:3000";

    private GestureDetector gestureDetector;
    private SocketController socketController;

    public static DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HTTP_SERVER = (String) getIntent().getExtras().get("IP");
        Log.d("ebox", "will connect to: " + HTTP_SERVER);
        setContentView(R.layout.activity_hello);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

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