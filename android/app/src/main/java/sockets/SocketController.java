package sockets;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.util.List;

class SocketController {
	private final static Logger logger = LogManager.getLogger(SocketController.class);
	private Socket socket;

	public SocketController(String url) throws URISyntaxException {
		IO.Options opts = new IO.Options();
		opts.forceNew = true;
		opts.reconnection = false;
		socket = IO.socket(url, opts);
		socket.connect();
		logger.info(String.format("Connecting to socket with url %s", url));
	}

	public void performMovement(Movement movement, List<String> arguments) {
		socket.emit(movement.toString(), arguments.toArray());
	}
}
