package braincode.mobile.ebox.sockets;

import braincode.mobile.ebox.gesture.GestureEvent;
import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;

public class SocketController {
    private Socket socket;

    public SocketController(URI uri) {
        socket = IO.socket(uri);
    }

    public void connect() {
        socket.connect();
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void onGestureEvent(GestureEvent gestureEvent) {
        socket.emit(gestureEvent.getName(), gestureEvent.getData());
    }
}
