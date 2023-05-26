package com.example.weatherapp.Sensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
    private SensorManager sensorManager;
    private List<Sensor> list;
    private Sensor sensor,tempSensor,humidSensor,pressureSensor;
    private float changedValue;
    private boolean isTempSensor,isHumidSensor,isPressureSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        textView = findViewById(R.id.tt);
        textView2 = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView3);
        textView5 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView5);
        textView7 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView7);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        list = sensorManager.getSensorList(Sensor.TYPE_ALL);
        printSensors();
        specificSensors();
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        textView3.setText("Step Detector Sensor - \n " +
                "Power Consumption : " + String.valueOf(sensor.getPower()) + "\n Vendor :" +
                String.valueOf(sensor.getVendor()) + "\n Version :" + String.valueOf(sensor.getVersion()));
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTempSensor = true;
        } else {
            textView5.setText("Temperature Sensor not available");
            isTempSensor = false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humidSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            isHumidSensor = true;
        } else {
            textView6.setText("Humidity Sensor not available");
            isHumidSensor = false;
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            isPressureSensor = true;
        } else {
            textView7.setText("Pressure Sensor not available");
            isPressureSensor = false;
        }
    }
    private void specificSensors() {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)!=null){
            textView2.setText("This device has Magnetometer sensor");
        }else {
            textView2.setText("No Magnetometer sensor found");
        }
    }

    private void printSensors() {
        for (Sensor sensor :list){
            textView.setText(textView.getText()+"\n"+sensor.getName());
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        changedValue = event.values[0];
        textView4.setText("Light Sensor Value : "+String.valueOf(changedValue));
        if (isTempSensor)
            textView5.setText("Temperature Sensor Value : "+event.values[0]+"°C");
        if (isHumidSensor)
            textView6.setText("Humidity Sensor Value : "+event.values[0]+"%");
        if (isPressureSensor)
            textView7.setText("Pressure Sensor Value : "+event.values[0]+"hpa");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL);
        if(isTempSensor){
            sensorManager.registerListener(this,tempSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isHumidSensor){
            sensorManager.registerListener(this,humidSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isPressureSensor){
            sensorManager.registerListener(this,pressureSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        if (isTempSensor){
            sensorManager.unregisterListener(this);
        }
        if (isHumidSensor){
            sensorManager.unregisterListener(this);
        }
        if (isPressureSensor){
            sensorManager.unregisterListener(this);
        }
    }
    public void check(View view) {
        Intent intent =new Intent(this, AccelerometerActivity.class);
        startActivity(intent);
    }

    public void steps(View view) {   startActivity(new Intent(this, WalkStepsActivity.class));
    }

    public void checkGravity(View view) { startActivity(new Intent(this, GravityVibrationActivity.class));
    }

    public void checkProximity(View view) { Intent intent=new Intent(SensorActivity.this, ProximityActivity.class);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String input=data.getStringExtra("TAG");
        textView8.setText(input);

    }

    public void checkCompass(View view) { startActivity(new Intent(SensorActivity.this, CompassValueActivity.class));
    }
}