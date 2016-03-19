package braincode.mobile.ebox.sockets;

import android.app.Activity;

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

    private static final int QUEUE_LIMIT = 10;
    private final Activity activity;

    private Socket socket;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    private List<GestureEvent> queue = new LinkedList<>();

    public SocketController(Activity activity, URI uri) {
        this.activity = activity;
        socket = IO.socket(uri);
    }

    public void connect() {
        socket.connect();
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Iterator<GestureEvent> it = queue.iterator();
                        while (it.hasNext()) {
                            GestureEvent gestureEvent = it.next();
                            socket.emit(gestureEvent.getName(), gestureEvent.getData());
                            it.remove();
                        }
                    }
                });
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void onGestureEvent(GestureEvent gestureEvent) {
        if (queue.size() >= QUEUE_LIMIT) {
            queue.remove(0);
            queue.add(gestureEvent);
        } else {
            queue.add(gestureEvent);
        }
    }
}
