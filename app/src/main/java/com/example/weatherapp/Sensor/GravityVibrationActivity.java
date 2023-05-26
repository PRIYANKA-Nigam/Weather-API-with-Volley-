package com.example.weatherapp.Sensor;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;

public class GravityVibrationActivity extends AppCompatActivity implements SensorEventListener {
TextView t1,t2,t3;
SensorManager sensorManager;
Sensor vibrateSensor;
boolean isGravitySensor;
private AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_vibration);
        t1=findViewById(R.id.textView14);
        t2=findViewById(R.id.textView13);
        t3=findViewById(R.id.textView12);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!=null){
            vibrateSensor =sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            isGravitySensor=true;
        }else {
            t1.setText("Gravity Sensor not available");
            isGravitySensor=false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        t1.setText(sensorEvent.values[0]+"m/s2");
        t2.setText(sensorEvent.values[1]+"m/s2");
        t3.setText(sensorEvent.values[2]+"m/s2");
        if (sensorEvent.values[2]<-9.7)
        {
            getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }else {
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isGravitySensor){
            sensorManager.registerListener(this,vibrateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isGravitySensor){
            sensorManager.unregisterListener(this,vibrateSensor);
        }

    }
}