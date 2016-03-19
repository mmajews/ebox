package braincode.mobile.ebox.sockets;

import android.view.MotionEvent;

import org.junit.Test;

import braincode.mobile.ebox.gesture.event.MotionGestureEvent;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageTest {

    @Test
    public void shouldCorrectlyDeserializeToJson() throws Exception {
        long timestamp = 12313;
        float x = 12.3f;
        float y = 0.2f;

        MotionEvent motionEvent = mock(MotionEvent.class);
        when(motionEvent.getEventTime()).thenReturn(timestamp);
        when(motionEvent.getX()).thenReturn(x);
        when(motionEvent.getY()).thenReturn(y);

        MotionGestureEvent message = new MotionGestureEvent(Movement.OnSingleTapConfirmed, motionEvent);

        String returnedJson = message.toJson();

        assertEquals("{\"movement\":\"onSingleTapConfirmed\",\"x\":1.0,\"y\":2.0,\"timestamp\":\"0\"}", returnedJson);
    }
}