/*
 * Copyright © 2016 and Confidential to Pegasystems Inc. All rights reserved.
 */

package braincode.mobile.ebox;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URI;


public class HelloActivity extends Activity {

    public static final String HTTP_SERVER = "http://localhost:80";
    private EditText messageText;
    private Socket socket;
    private ArrayAdapter<String> adapter;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        messageText = (EditText) findViewById(R.id.messageText);

        // ListView and Adapter
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, R.id.message);
        ListView messageListView = (ListView) findViewById(R.id.messageListView);
        messageListView.setAdapter(adapter);

        // handler
        handler = new Handler();

        // socket.io
        URI uri = URI.create(HTTP_SERVER);
        socket = IO.socket(uri);

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                socket.emit("chat message", "hi");
            }

        });

        socket.on("chat message", new Emitter.Listener() {
            @Override
            public void call(final Object... objects) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (Object o : objects) {
                            String msg = (String) o;
                            adapter.add(msg);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        socket.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        socket.disconnect();
    }

    public void send(View view) {
        String msg = messageText.getText().toString();
        messageText.setText("");
        socket.emit("chat message", msg);
    }

}