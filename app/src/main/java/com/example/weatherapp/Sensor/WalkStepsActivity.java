package com.example.weatherapp.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;

public class WalkStepsActivity extends AppCompatActivity implements SensorEventListener {
TextView textView1,textView2;
SensorManager sensorManager;
Sensor stepSensor,detectSensor;
private boolean isCounterSensorPresent,isDetectorSensor;
int stepCount=0,detect=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_steps);
        textView1 = findViewById(R.id.textView7);
        textView2 = findViewById(R.id.textView8);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //to test the functionality we need the screen awake so,
        sensorManager= (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
                  if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            stepSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
              isCounterSensorPresent=true;
          }else {      textView1.setText("Counter Sensor is not present");
          isCounterSensorPresent=false; }
                  if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null){
                      detectSensor =sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
                      isDetectorSensor=true;
                  }else {
                      textView2.setText("");
                      textView2.setText("Detector sensor is not present");
                      isDetectorSensor=false;
                  }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == stepSensor){
           stepCount= (int) sensorEvent.values[0];
           textView1.setText(String.valueOf(stepCount));
        }else if (sensorEvent.sensor==detectSensor){
            detect= (int) (detect+sensorEvent.values[0]);
            textView2.setText(String.valueOf(detect));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume(); //to register the listener
        isCounterSensorPresent=true;
//        Sensor countSensor =sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this,stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
       if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
           sensorManager.registerListener(this,detectSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this,stepSensor);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!=null)
            sensorManager.unregisterListener(this,detectSensor);
    }
}