package braincode.mobile.ebox.sockets;

import android.util.Log;

import java.net.URI;
import java.util.Date;

import braincode.mobile.ebox.gesture.GestureEvent;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketController {

    private static final int EVENT_GAP = 20;

    private Socket socket;

    private long previous;

    public SocketController(URI uri) {
        this.socket = IO.socket(uri);
    }

    public void connect() {
        socket.connect();
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void onGestureEvent(final GestureEvent gestureEvent) {
        long timestamp = new Date().getTime();
        if (timestamp - previous > EVENT_GAP) {
            Object data = gestureEvent.getData();
            Log.d("padEvent", "TIMESTAMP:" + new Date().getTime() + ", data: " + data);
            socket.emit("padEvent", data);
            previous = timestamp;
        }
    }
}
