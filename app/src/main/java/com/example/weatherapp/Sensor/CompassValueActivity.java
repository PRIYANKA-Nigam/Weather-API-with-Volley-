package com.example.weatherapp.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;

public class CompassValueActivity extends AppCompatActivity implements SensorEventListener {
TextView textView;
ImageView imageView;
private SensorManager sensorManager;
private Sensor Asensor,Msensor;
private boolean isSensor;
private float[] accelerometer= new float[3];
private float[] magnetometer = new float[3];
private float[] rotatingMatrix = new float[9];
private float[] orientation =new float[3];
boolean isLargeAccelermeterArray =false;
boolean isLastMagnetometerArrayn=false;
long lastUpdatedTime=0;
float currentDegree = 0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_value);
        textView = findViewById(R.id.textView16);
        imageView=findViewById(R.id.imageView);
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Asensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Msensor= sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor==Asensor){
            System.arraycopy(sensorEvent.values,0,accelerometer,0,sensorEvent.values.length);
            isLargeAccelermeterArray=true;
        }else if (sensorEvent.sensor ==Msensor){
            System.arraycopy(sensorEvent.values,0,magnetometer,0,sensorEvent.values.length);
            isLastMagnetometerArrayn=true;
        }
        if (isLastMagnetometerArrayn && isLargeAccelermeterArray && System.currentTimeMillis()-lastUpdatedTime>250){
            SensorManager.getRotationMatrix(rotatingMatrix,null,accelerometer,magnetometer);
            SensorManager.getOrientation(rotatingMatrix,orientation);
            float azimuthInRadian = orientation[0];
            float azimuthIndegree= (float) Math.toDegrees(azimuthInRadian);
            RotateAnimation rotateAnimation =new RotateAnimation(currentDegree,-azimuthIndegree,
                    Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);
            imageView.startAnimation(rotateAnimation);
            currentDegree = -azimuthIndegree;
            lastUpdatedTime = System.currentTimeMillis();
            int x = (int) azimuthIndegree;
            textView.setText(x+"°");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,Asensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,Msensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,Asensor);
        sensorManager.unregisterListener(this,Msensor);
    }
}