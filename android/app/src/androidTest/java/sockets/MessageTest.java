package sockets;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageTest {

    @Test
    public void shouldCorrectlyDeserializeToJson() throws Exception {
        LocalDateTime localDateTime = new LocalDateTime(0);
        Message message = new Message(Movement.OnSingleTapConfirmed, 1.0, 2.0, localDateTime);

        String returnedJson = message.toJson();

        assertEquals("{\"movement\":\"onSingleTapConfirmed\",\"x\":1.0,\"y\":2.0,\"timeStamp\":\"1970-01-01 01:00:00\"}", returnedJson);
    }
}