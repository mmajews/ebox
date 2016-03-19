package braincode.mobile.ebox.sockets;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;
import java.util.List;

public class SocketController {
    private Socket socket;

    public SocketController(URI uri) {
        socket = IO.socket(uri, createIOOptions());
    }

    public void connect() {
        socket.connect();
    }

    public void disconnect() {
        socket.disconnect();
    }

    private IO.Options createIOOptions() {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;

        return opts;
    }

    public void onGestureEvent(Movement movement, List<String> arguments) {
//        socket.emit(movement.toString(), arguments.toArray());
    }
}
