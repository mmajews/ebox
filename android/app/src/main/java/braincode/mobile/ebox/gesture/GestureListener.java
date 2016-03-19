package braincode.mobile.ebox.gesture;

import android.util.Log;
import android.view.MotionEvent;
import braincode.mobile.ebox.sockets.Message;
import braincode.mobile.ebox.sockets.Movement;
import braincode.mobile.ebox.sockets.ScrollMessage;
import braincode.mobile.ebox.sockets.SocketController;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.view.GestureDetector.OnDoubleTapListener;
import static android.view.GestureDetector.OnGestureListener;

public class GestureListener implements OnGestureListener, OnDoubleTapListener {

    private static final String TAG = "GestureListener";
    private SocketController socketController;

    public GestureListener(String httpServer) {
        socketController = new SocketController(URI.create(httpServer));
    }

    private void sendEvent(List list) {
        socketController.onGestureEvent("padEvent", list);
    }

    private List<String> createMessage(Movement movement, MotionEvent motionEvent) {
        List<String> list = new ArrayList<>(1);
        try {
            list.add((new Message(movement, motionEvent.getX(), motionEvent.getY(), new Date()).toJson()));
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    private List<String> createScrollMessage(Movement movement, MotionEvent motionEvent, MotionEvent motionEvent1, double distanceX, double distanceY) {
        List<String> list = new ArrayList<>(1);
        try {
            list.add((new ScrollMessage(movement, motionEvent.getX(), motionEvent.getY(), motionEvent1.getX(), motionEvent1.getY(), distanceX, distanceY, new Date()).toJson()));
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        return list;

    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, Movement.OnSingleTapConfirmed.getEventText());
        sendEvent(createMessage(Movement.OnSingleTapConfirmed, e));
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
        sendEvent(createMessage(Movement.OnDoubleTapEvent, e));
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, Movement.OnDown.getEventText());
        sendEvent(createMessage(Movement.OnDown, e));
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, Movement.OnShowPress.getEventText());
        sendEvent(createMessage(Movement.OnShowPress, e));
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, Movement.OnSingleTapUp.getEventText());
        sendEvent(createMessage(Movement.OnSingleTapUp, e));
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, Movement.OnScroll.getEventText());
        ;
        sendEvent(createScrollMessage(Movement.OnScroll, e1, e1, distanceX, distanceY));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        sendEvent(createMessage(Movement.OnLongPress, e));
        Log.d(TAG, Movement.OnLongPress.getEventText());

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, Movement.OnFling.getEventText());
        return false;
    }
}
