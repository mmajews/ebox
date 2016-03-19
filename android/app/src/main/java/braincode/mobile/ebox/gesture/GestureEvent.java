package braincode.mobile.ebox.gesture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class GestureEvent {

    public abstract String getName();

    @JsonIgnore
    public final Object getData() {
        Object data = null;
        try {
            data = new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return data;
    }
}
