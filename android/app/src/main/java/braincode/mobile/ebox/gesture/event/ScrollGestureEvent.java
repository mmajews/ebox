package braincode.mobile.ebox.gesture.event;

import android.view.MotionEvent;

import braincode.mobile.ebox.activity.HelloActivity;
import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.sockets.Movement;


public class ScrollGestureEvent extends GestureEvent {

    private MotionEvent motionEvent1;
    private MotionEvent motionEvent2;

    private float distanceX;
    private float distanceY;

    public ScrollGestureEvent(MotionEvent motionEvent1, MotionEvent motionEvent2, float distanceX, float distanceY) {
        this.motionEvent1 = motionEvent1;
        this.motionEvent2 = motionEvent2;
        this.distanceY = distanceY;
        this.distanceX = distanceX;
    }

    public float getX1() {
        return motionEvent1.getX() / HelloActivity.metrics.widthPixels;
    }

    public float getY1() {
        return motionEvent1.getY() / HelloActivity.metrics.heightPixels;
    }

    public float getX2() {
        return motionEvent2.getX() / HelloActivity.metrics.widthPixels;
    }

    public float getY2() {
        return motionEvent2.getY() / HelloActivity.metrics.heightPixels;
    }

    public float getDistanceX() {
        return distanceX / HelloActivity.metrics.widthPixels;
    }

    public float getDistanceY() {
        return distanceY / HelloActivity.metrics.heightPixels;
    }

    public long getTimestamp() {
        return motionEvent1.getEventTime();
    }

    @Override
    public String getName() {
        return Movement.OnScroll.getEventText();
    }

}
