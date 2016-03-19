package braincode.mobile.ebox.sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

class Message {
    private Movement movement;
    private double x;
    private double y;
    private Date timeStamp;

    Message(Movement movement, double x, double y, Date timeStamp) {
        this.movement = movement;
        this.x = x;
        this.y = y;
        this.timeStamp = timeStamp;
    }

    String toJson() throws JsonProcessingException {
        return (new ObjectMapper()).writeValueAsString(this);
    }

    public Movement getMovement() {
        return movement;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getTimeStamp() {
        return String.valueOf(timeStamp.getTime());
    }
}

