package braincode.mobile.ebox.sockets;

import android.view.MotionEvent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;

import java.util.Iterator;

import braincode.mobile.ebox.gesture.event.MotionGestureEvent;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageTest {

    @Test
    public void shouldCorrectlyDeserializeToJson() throws Exception {
        long timestamp = 12313l;
        float x = 12.3f;
        float y = 0.2f;
        Movement movement = Movement.OnSingleTapConfirmed;

        MotionEvent motionEvent = mock(MotionEvent.class);
        when(motionEvent.getEventTime()).thenReturn(timestamp);
        when(motionEvent.getX()).thenReturn(x);
        when(motionEvent.getY()).thenReturn(y);

        MotionGestureEvent message = new MotionGestureEvent(movement, motionEvent);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actual = mapper.readTree(message.toJson());

        ObjectNode json = mapper.createObjectNode();
        json.put("movement", movement.getEventText());
        json.put("timestamp", timestamp);
        json.put("x", x);
        json.put("y", y);

        Iterator<String> it = json.fieldNames();

        while (it.hasNext()) {
            String field = it.next();
            assertEquals(json.get(field).toString(), actual.get(field).toString());
        }

    }
}