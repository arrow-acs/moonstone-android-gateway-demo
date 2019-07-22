package com.arrow.jmyiotgateway.device.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE;
import static android.hardware.Sensor.TYPE_GYROSCOPE;
import static android.hardware.Sensor.TYPE_GYROSCOPE_UNCALIBRATED;
import static android.hardware.Sensor.TYPE_HEART_RATE;
import static android.hardware.Sensor.TYPE_LIGHT;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED;
import static android.hardware.Sensor.TYPE_PRESSURE;
import static android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY;
import static android.hardware.Sensor.TYPE_STEP_COUNTER;

/**
 * Created by osminin on 6/30/2016.
 */
public class AndroidSensorUtils {

    private static final int[] availableSensorsConstraint = {
            TYPE_ACCELEROMETER,
            TYPE_AMBIENT_TEMPERATURE,
            TYPE_GYROSCOPE,
            TYPE_GYROSCOPE_UNCALIBRATED,
            TYPE_HEART_RATE,
            TYPE_LIGHT,
            TYPE_MAGNETIC_FIELD,
            TYPE_MAGNETIC_FIELD_UNCALIBRATED,
            TYPE_PRESSURE,
            TYPE_RELATIVE_HUMIDITY,
            TYPE_STEP_COUNTER
    };

    public static Integer[] getAvailableSensors(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = getSensorsList(sensorManager);
        Integer[] sensorTypes = new Integer[sensors.size()];
        for (int i = 0; i < sensors.size(); ++i) {
            sensorTypes[i] = sensors.get(i).getType();
        }
        return sensorTypes;
    }

    public static List<Sensor> getSensorsList(SensorManager sensorManager) {
        HashSet<Sensor> result = new HashSet<>();
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            if (sensor.getType() == TYPE_ACCELEROMETER ||
                    sensor.getType() == TYPE_AMBIENT_TEMPERATURE ||
                    sensor.getType() == TYPE_HEART_RATE ||
                    sensor.getType() == TYPE_LIGHT ||
                    sensor.getType() == TYPE_PRESSURE ||
                    sensor.getType() == TYPE_RELATIVE_HUMIDITY ||
                    sensor.getType() == TYPE_STEP_COUNTER) {
                result.add(sensor);
            }
        }
        Sensor sensor = sensorManager.getDefaultSensor(TYPE_GYROSCOPE);
        if (!addSensor(result, sensor)) {
            sensor = sensorManager.getDefaultSensor(TYPE_GYROSCOPE_UNCALIBRATED);
            addSensor(result, sensor);
        }
        sensor = sensorManager.getDefaultSensor(TYPE_MAGNETIC_FIELD);
        if (!addSensor(result, sensor)) {
            sensor = sensorManager.getDefaultSensor(TYPE_MAGNETIC_FIELD_UNCALIBRATED);
            addSensor(result, sensor);
        }
        return new ArrayList<>(result);
    }

    private static boolean addSensor(Set<Sensor> sensors, Sensor sensor) {
        if (sensor != null) {
            return  sensors.add(sensor);
        }
        return false;
    }
}
