package braincode.mobile.ebox.sockets;

import java.util.Date;


public class ScrollMessage extends Message {

    private double x1;
    private double y1;
    private double distanceX;
    private double distanceY;

    public ScrollMessage(Movement movement, double x, double y, double x1, double y1, double distanceY, double distanceX, Date timeStamp) {
        super(movement, x, y, timeStamp);
        this.x1 = x1;
        this.y1 = y1;
        this.distanceY = distanceY;
        this.distanceX = distanceX;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }
}
