package braincode.mobile.ebox.sockets;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URI;
import java.util.List;

public class SocketController {
    private Socket socket;

    public SocketController(URI uri) {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;
        socket = IO.socket(uri, opts);
        socket.connect();
    }

    public void performMovement(Movement movement, List<String> arguments) {
        socket.emit(movement.toString(), arguments.toArray());
    }
}
