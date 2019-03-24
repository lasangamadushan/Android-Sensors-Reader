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

    private SensorManager sensorManager;
    Sensor accelerometer, magnetometer;

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
            accelX.setText("accelerometer not supported");
            accelY.setText("accelerometer not supported");
            accelZ.setText("accelerometer not supported");

        }

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(magnetometer != null) {
            sensorManager.registerListener(MainActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            magnoX.setText("magnetometer not supported");
            magnoY.setText("magnetometer not supported");
            magnoZ.setText("magnetometer not supported");

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
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
