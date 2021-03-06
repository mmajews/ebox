/*
 * Copyright © 2016 and Confidential to Pegasystems Inc. All rights reserved.
 */

package braincode.mobile.ebox.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.net.URI;

import braincode.mobile.ebox.R;
import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.gesture.GestureListener;
import braincode.mobile.ebox.sensor.SensorHandler;
import braincode.mobile.ebox.sockets.SocketController;


public class HelloActivity extends Activity {

    public static String HTTP_SERVER = "http://10.22.102.197:3000";
    public static DisplayMetrics metrics;
    private GestureDetector gestureDetector;
    private SocketController socketController;
    private SensorHandler sensorHandler;

    private SurfaceHolder holder;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HTTP_SERVER = "http://" + getIntent().getExtras().get("IP").toString();
        Log.d("ebox", "will connect to: " + HTTP_SERVER);
        setContentView(R.layout.activity_hello);

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // socket.io
        URI uri = URI.create(HTTP_SERVER);
        socketController = new SocketController(this, uri);

        GestureListener gestureListener = new GestureListener(socketController);
        gestureDetector = new GestureDetector(this, gestureListener);
        gestureDetector.setOnDoubleTapListener(gestureListener);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorHandler = new SensorHandler(sensorManager, socketController);

        Log.d("Gesture", "GestureDetector registered");
    }

    @Override
    protected void onResume() {
        super.onResume();
        socketController.connect();
        sensorHandler.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorHandler.onPause();
        socketController.disconnect();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                socketController.onGestureEvent(new GestureEvent() {
                    @Override
                    public String getName() {
                        return "touchDown";
                    }
                });
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                socketController.onGestureEvent(new GestureEvent() {

                    @Override
                    public String getName() {
                        return "touchUp";
                    }
                });
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        socketController.emit("back", "");
//        super.onBackPressed();
    }
}