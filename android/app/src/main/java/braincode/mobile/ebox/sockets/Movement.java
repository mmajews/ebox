package braincode.mobile.ebox.sockets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Movement {
    OnSingleTapConfirmed("onSingleTapConfirmed"), OnDoubleTap("onDoubleTap"),
    OnDoubleTapEvent("onDoubleTapEvent"), OnDown("onDown"), OnShowPress("onShowPress"),
    OnSingleTapUp("onSIngleTapUp"), OnScroll("onScroll"), OnLongPress("onLongPress"), OnFling("onFling");
    private String description;

    Movement(String description) {
        this.description = description;
    }

    @JsonCreator
    public static Movement fromValue(int typeCode) {
        for (Movement c : Movement.values()) {
            if (c.ordinal() == typeCode) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid Status type code: " + typeCode);
    }

    public String getEventText() {
        return description;
    }

    @Override
    public String toString() {
        return this.getEventText();
    }

    @JsonValue
    public String value() {
        return getEventText();
    }

}

