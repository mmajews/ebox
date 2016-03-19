package braincode.mobile.ebox.gesture.event;

import android.view.MotionEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.sockets.Movement;


public class ScrollGestureEvent extends GestureEvent {

    private MotionEvent motionEvent1;
    private MotionEvent motionEvent2;

    private double distanceX;
    private double distanceY;

    public ScrollGestureEvent(MotionEvent motionEvent1, MotionEvent motionEvent2, double distanceX, double distanceY) {
        this.motionEvent1 = motionEvent1;
        this.motionEvent2 = motionEvent2;
        this.distanceY = distanceY;
        this.distanceX = distanceX;
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

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }

    @Override
    public String getName() {
        return Movement.OnScroll.getEventText();
    }

}
