package braincode.mobile.ebox.gesture;

import android.util.Log;
import android.view.MotionEvent;

import braincode.mobile.ebox.gesture.event.FlingGestureEvent;
import braincode.mobile.ebox.gesture.event.MotionGestureEvent;
import braincode.mobile.ebox.gesture.event.ScrollGestureEvent;
import braincode.mobile.ebox.sockets.Movement;
import braincode.mobile.ebox.sockets.SocketController;

import static android.view.GestureDetector.OnDoubleTapListener;
import static android.view.GestureDetector.OnGestureListener;

public class GestureListener implements OnGestureListener, OnDoubleTapListener {

    private static final String TAG = "GestureListener";
    private SocketController socketController;

    public GestureListener(SocketController socketController) {
        this.socketController = socketController;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        //Log.d(TAG, Movement.OnSingleTapConfirmed.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnSingleTapConfirmed, e));
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //Log.d(TAG, Movement.OnDoubleTap.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnDoubleTap, e));
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        //Log.d(TAG, Movement.OnDoubleTapEvent.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnDoubleTapEvent, e));
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //Log.d(TAG, Movement.OnDown.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnDown, e));
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //Log.d(TAG, Movement.OnShowPress.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnShowPress, e));
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //Log.d(TAG, Movement.OnSingleTapUp.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnSingleTapUp, e));
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //Log.d(TAG, Movement.OnScroll.getEventText());
        socketController.onGestureEvent(new ScrollGestureEvent(e1, e2, distanceX, distanceY));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //Log.d(TAG, Movement.OnLongPress.getEventText());
//        socketController.onGestureEvent(new MotionGestureEvent(Movement.OnSingleTapConfirmed, e));
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //Log.d(TAG, Movement.OnFling.getEventText());
//        socketController.onGestureEvent(new FlingGestureEvent(e1, e2, velocityX, velocityY));
        return false;
    }
}
