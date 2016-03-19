package braincode.mobile.ebox.gesture.event;

import android.view.MotionEvent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import braincode.mobile.ebox.activity.HelloActivity;
import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.sockets.Movement;

public class MotionGestureEvent extends GestureEvent {
    private MotionEvent motionEvent;
    private Movement movement;

    public MotionGestureEvent(Movement movement, MotionEvent motionEvent) {
        this.movement = movement;
        this.motionEvent = motionEvent;
    }

    public String toJson() throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(this);
    }

    @JsonIgnore
    public Movement getMovement() {
        return movement;
    }

    public float getX() {
        return motionEvent.getX() / HelloActivity.metrics.widthPixels;
    }

    public float getY() {
        return motionEvent.getY() / HelloActivity.metrics.heightPixels;
    }

    public long getTimestamp() {
        return motionEvent.getEventTime();
    }

    @Override
    public String getName() {
        return movement.getEventText();
    }

}

