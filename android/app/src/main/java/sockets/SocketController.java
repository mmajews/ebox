package sockets;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;
import java.util.List;

class SocketController {
	private Socket socket;

	public SocketController(String url) throws URISyntaxException {
		IO.Options opts = new IO.Options();
		opts.forceNew = true;
		opts.reconnection = false;
		socket = IO.socket(url, opts);
		socket.connect();
	}

	public void performMovement(Movement movement, List<String> arguments) {
		socket.emit(movement.toString(), arguments.toArray());
	}
}
