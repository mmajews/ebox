/*
 * Copyright © 2016 and Confidential to Pegasystems Inc. All rights reserved.
 */

package braincode.mobile.ebox.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import java.net.URI;

import braincode.mobile.ebox.R;
import braincode.mobile.ebox.gesture.GestureListener;


public class HelloActivity extends Activity {

    public static final String HTTP_SERVER = "http://localhost:80";

    private GestureDetector gestureDetector;
    private GestureListener gestureListener;

    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        // socket.io
        URI uri = URI.create(HTTP_SERVER);

        gestureListener = new GestureListener();

        gestureDetector = new GestureDetector(this, gestureListener);
        gestureDetector.setOnDoubleTapListener(gestureListener);

        Log.d("Gesture", "GestureDetecter registered");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);

        handleVelocity(event);
        return super.onTouchEvent(event);
    }

    private void handleVelocity(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the handleVelocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    // Reset the handleVelocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                // When you want to determine the handleVelocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the handleVelocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000);
                // Log handleVelocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
                Log.d("Hello", "X velocity: " +
                        VelocityTrackerCompat.getXVelocity(mVelocityTracker, pointerId));
                Log.d("Hello", "Y velocity: " +
                        VelocityTrackerCompat.getYVelocity(mVelocityTracker, pointerId));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker.recycle();
                break;
        }
    }

}