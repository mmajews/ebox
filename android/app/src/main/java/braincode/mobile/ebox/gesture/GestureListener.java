package braincode.mobile.ebox.gesture;

import android.util.Log;
import android.view.MotionEvent;
import braincode.mobile.ebox.sockets.Movement;
import braincode.mobile.ebox.sockets.SocketController;

import java.net.URI;
import java.util.ArrayList;

import static android.view.GestureDetector.OnDoubleTapListener;
import static android.view.GestureDetector.OnGestureListener;

public class GestureListener implements OnGestureListener, OnDoubleTapListener {

    private static final String TAG = "GestureListener";
    private SocketController socketController;

    public GestureListener(String httpServer) {
        socketController = new SocketController(URI.create(httpServer));
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, Movement.OnSingleTapConfirmed.getEventText());
        socketController.performMovement(Movement.OnSingleTapConfirmed, new ArrayList<String>());
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, Movement.OnDoubleTap.getEventText());
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG, Movement.OnDoubleTapEvent.getEventText());
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, Movement.OnDown.getEventText());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, Movement.OnShowPress.getEventText());

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, Movement.OnSingleTapUp.getEventText());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, Movement.OnScroll.getEventText());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, Movement.OnLongPress.getEventText());

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, Movement.OnFling.getEventText());
        return false;
    }
}
