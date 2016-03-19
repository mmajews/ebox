package braincode.mobile.ebox.sockets;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import java.net.URI;
import java.util.Date;

import braincode.mobile.ebox.gesture.GestureEvent;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketController {

    private final Activity activity;

    private Socket socket;
    private Handler handler = new Handler();

    public SocketController(Activity activity, URI uri) {
        this.activity = activity;
        this.socket = IO.socket(uri);
    }

    public void connect() {
        socket.connect();
    }

    public void disconnect() {
        socket.disconnect();
    }

    public void onGestureEvent(final GestureEvent gestureEvent) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Object data = gestureEvent.getData();
                        Log.d("padEvent", "TIMESTAMP:" + new Date().getTime() + ", data: " + data);
                        socket.emit("padEvent", data);
                    }
                });
            }
        }, 30);
    }
}
