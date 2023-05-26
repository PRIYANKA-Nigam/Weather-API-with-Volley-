package com.example.weatherapp.Sensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isProximitySensor;
    private String changedValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!=null){
         sensor =sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            isProximitySensor=true;
        }else {
           Toast.makeText(getApplicationContext(),"Proximity Sensor not Present", Toast.LENGTH_SHORT).show();
            isProximitySensor=false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (isProximitySensor)
         changedValue=sensorEvent.values[0]+"%";
        Intent i = new Intent();  // or // Intent i = getIntent()
        i.putExtra("TAG",changedValue);
        setResult(RESULT_OK , i);
        finish();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isProximitySensor){
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isProximitySensor){
            sensorManager.unregisterListener(this);
        }
    }
}