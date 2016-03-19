package braincode.mobile.ebox.sockets;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import braincode.mobile.ebox.gesture.GestureEvent;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketController {

    private Socket socket;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private SocketQueueHandler socketQueueHandler = new SocketQueueHandler();

    public SocketController(URI uri) {
        socket = IO.socket(uri);
    }

    public void connect() {
        socket.connect();
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                socketQueueHandler.emit();
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void onGestureEvent(GestureEvent gestureEvent) {
        socketQueueHandler.add(gestureEvent);
    }

    class SocketQueueHandler {
        private static final int QUEUE_LIMIT = 10;

        private List<GestureEvent> queue = new LinkedList<>();

        public synchronized void add(GestureEvent gestureEvent) {
            if (queue.size() >= QUEUE_LIMIT) {
                queue.remove(0);
                queue.add(gestureEvent);
            } else {
                queue.add(gestureEvent);
            }
        }

        public synchronized void emit() {
            Iterator<GestureEvent> it = queue.iterator();
            while (it.hasNext()) {
                GestureEvent gestureEvent = it.next();
                socket.emit(gestureEvent.getName(), gestureEvent.getData());
                it.remove();
            }
        }
    }


}
