package com.lasanga.mobilecomputing.sensorsreader;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

    private Button btnStart;
    private TextView accelX,accelY,accelZ;
    private TextView magnoX,magnoY,magnoZ;
    private TextView gyroX,gyroY,gyroZ;
    private TextView light,proximity;

    private SensorManager sensorManager;
    Sensor accelerometer, magnetometer, gyroscope, lightSensor, proximitySensor;

    boolean updateStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateStarted = false;

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setBackgroundColor(Color.parseColor("#FF669900"));
        btnStart.setTextColor(Color.parseColor("#FFFFFFFF"));

        accelX = (TextView) findViewById(R.id.accelX);
        accelY = (TextView) findViewById(R.id.accelY);
        accelZ = (TextView) findViewById(R.id.accelZ);

        magnoX = (TextView) findViewById(R.id.magnoX);
        magnoY = (TextView) findViewById(R.id.magnoY);
        magnoZ = (TextView) findViewById(R.id.magnoZ);

        gyroX = (TextView) findViewById(R.id.gyroX);
        gyroY = (TextView) findViewById(R.id.gyroY);
        gyroZ = (TextView) findViewById(R.id.gyroZ);


        light = (TextView) findViewById(R.id.light);
        proximity = (TextView) findViewById(R.id.proximity);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateStarted){
                    updateStarted =false;
                    btnStart.setText("Start");
                    btnStart.setBackgroundColor(Color.parseColor("#FF669900"));
                }
                else {
                    updateStarted = true;
                    btnStart.setText("Stop");
                    btnStart.setBackgroundColor(Color.parseColor("#FFCC0000"));
                }
            }
        });


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            accelX.setText("not supported");
            accelY.setText("");
            accelZ.setText("");

        }

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(magnetometer != null) {
            sensorManager.registerListener(MainActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            magnoX.setText("not supported");
            magnoY.setText("");
            magnoZ.setText("");

        }

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscope != null) {
            sensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            gyroX.setText("not supported");
            gyroY.setText("");
            gyroZ.setText("");
        }

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if(lightSensor != null) {
            sensorManager.registerListener(MainActivity.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            light.setText("not supported");
        }

        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(proximitySensor != null) {
            sensorManager.registerListener(MainActivity.this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            proximity.setText("not supported");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (updateStarted) {
            Sensor sensor = event.sensor;
            if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelX.setText("X: " + event.values[0]);
                accelY.setText("Y: " + event.values[1]);
                accelZ.setText("Z: " + event.values[2]);
            }
            else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magnoX.setText("X: " + event.values[0]);
                magnoY.setText("Y: " + event.values[1]);
                magnoZ.setText("Z: " + event.values[2]);
            }
            else if(sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                gyroX.setText("X: " + event.values[0]);
                gyroY.setText("Y: " + event.values[1]);
                gyroZ.setText("Z: " + event.values[2]);
            }
            else if(sensor.getType() == Sensor.TYPE_LIGHT) {
                light.setText(""+event.values[0]);
            }
            else if(sensor.getType() == Sensor.TYPE_PROXIMITY) {
                proximity.setText(""+event.values[0]);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
