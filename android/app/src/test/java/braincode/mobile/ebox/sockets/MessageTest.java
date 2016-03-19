package braincode.mobile.ebox.sockets;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MessageTest {

    @Test
    public void shouldCorrectlyDeserializeToJson() throws Exception {
        Date date = new Date(0);
        Message message = new Message(Movement.OnSingleTapConfirmed, 1.0, 2.0, date);

        String returnedJson = message.toJson();

        assertEquals("{\"movement\":\"onSingleTapConfirmed\",\"x\":1.0,\"y\":2.0,\"timeStamp\":\"0\"}", returnedJson);
    }
}