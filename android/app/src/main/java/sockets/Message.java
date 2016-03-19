package sockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

class Message {
    private Movement movement;
    private double x;
    private double y;
    private LocalDateTime timeStamp;

    Message(Movement movement, double x, double y, LocalDateTime timeStamp) {
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
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return fmt.print(timeStamp);
    }
}

