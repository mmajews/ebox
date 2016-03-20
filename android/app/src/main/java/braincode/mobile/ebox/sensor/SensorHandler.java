package braincode.mobile.ebox.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import braincode.mobile.ebox.gesture.GestureEvent;
import braincode.mobile.ebox.sockets.SocketController;

public class SensorHandler implements SensorEventListener {

    private SocketController socketController;
    private SensorManager sensorManager;

    public SensorHandler(SensorManager sensorManager, SocketController socketController) {
        this.socketController = socketController;
        this.sensorManager = sensorManager;
    }

    public void onResume() {
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            Log.e("SensorHandler", "Sensor registered");
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.e("SensorHandler", "There is no orientation sensor on device!");
        }
    }

    public void onPause() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        socketController.onGestureEvent(new GestureEvent() {
            public float getOrientation() {
                return event.values[1];
            }

            @Override
            public String getName() {
                return "orientation";
            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
