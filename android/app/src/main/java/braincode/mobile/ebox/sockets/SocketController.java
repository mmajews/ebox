package braincode.mobile.ebox.sockets;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.Date;

import braincode.mobile.ebox.R;
import braincode.mobile.ebox.gesture.GestureEvent;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketController {

    private static final int EVENT_GAP = 20;

    private Socket socket;
    private TextView currentSendTextView;

    private long previous;

    public SocketController(Activity activity, URI uri) {
        this.socket = IO.socket(uri);
        currentSendTextView = (TextView) activity.findViewById(R.id.currentSendTextView);

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
            emit("padEvent", gestureEvent.getData());
            Object data = gestureEvent.getData();
            Log.d("padEvent", "TIMESTAMP:" + new Date().getTime() + ", data: " + data);
            try {
                JSONObject json = new JSONObject(gestureEvent.getData().toString()); // Convert text to object
                currentSendTextView.setText(json.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            socket.emit("padEvent", data);
            previous = timestamp;
        }

        if (gestureEvent.getName().equals("touchDown") || gestureEvent.getName().equals("touchUp")
                || gestureEvent.getName().equals(Movement.OnSingleTapConfirmed.getEventText())) {
            emit("padEvent", gestureEvent.getData());
        }
    }

    public void emit(String name, Object data) {
        Log.d(name, "TIMESTAMP:" + new Date().getTime() + ", data: " + data);
        socket.emit(name, data);
    }
}
