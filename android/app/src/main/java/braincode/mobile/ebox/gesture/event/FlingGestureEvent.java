package braincode.mobile.ebox.gesture.event;

import android.view.MotionEvent;

import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.sockets.Movement;


public class FlingGestureEvent extends GestureEvent {

    private MotionEvent motionEvent1;
    private MotionEvent motionEvent2;

    private double velocityX;
    private double velocityY;

    public FlingGestureEvent(MotionEvent motionEvent1, MotionEvent motionEvent2, double velocityX, double velocityY) {
        this.motionEvent1 = motionEvent1;
        this.motionEvent2 = motionEvent2;
        this.velocityY = velocityY;
        this.velocityX = velocityX;
    }

    public double getX1() {
        return motionEvent1.getX();
    }

    public double getY1() {
        return motionEvent1.getY();
    }

    public double getX2() {
        return motionEvent2.getX();
    }

    public double getY2() {
        return motionEvent2.getY();
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    @Override
    public String getName() {
        return Movement.OnFling.getEventText();
    }
}
