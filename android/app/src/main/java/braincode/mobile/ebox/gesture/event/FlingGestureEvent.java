package braincode.mobile.ebox.gesture.event;

import android.view.MotionEvent;

import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.sockets.Movement;


public class FlingGestureEvent extends GestureEvent {

    private MotionEvent motionEvent1;
    private MotionEvent motionEvent2;

    private float velocityX;
    private float velocityY;

    public FlingGestureEvent(MotionEvent motionEvent1, MotionEvent motionEvent2, float velocityX, float velocityY) {
        this.motionEvent1 = motionEvent1;
        this.motionEvent2 = motionEvent2;
        this.velocityY = velocityY;
        this.velocityX = velocityX;
    }

    public float getX1() {
        return motionEvent1.getX();
    }

    public float getY1() {
        return motionEvent1.getY();
    }

    public float getX2() {
        return motionEvent2.getX();
    }

    public float getY2() {
        return motionEvent2.getY();
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public long getTimestamp() {
        return motionEvent1.getEventTime();
    }

    @Override
    public String getName() {
        return Movement.OnFling.getEventText();
    }
}
