package com.example.weatherapp.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
TextView t1,t2,t3;
    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean isAccelerometerSensor,itisNotFirstTime=false;
    private float curX,curY,curZ,lastX,lastY,lastZ;
    private float XDiff,YDiff,ZDiff;
    private float shakeThreashold = 5f;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        t1=findViewById(R.id.textView9);
        t2=findViewById(R.id.textView10);
        t3=findViewById(R.id.textView11);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){
          sensor =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccelerometerSensor=true;
        }else {
            t1.setText("Proximity Sensor not available");
            isAccelerometerSensor=false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (isAccelerometerSensor)
           t1.setText("Accelerometer Sensor Value :\n X-axis "+sensorEvent.values[0]+"m/s2 ");
        t2.setText("Y-axis : "+sensorEvent.values[1]+"m/s2");
        t3.setText("z-axis : "+sensorEvent.values[2]+"m/s2");
//        String val = "X-axis : "+sensorEvent.values[0]+"m/s2 \n"+"Y-axis : "+sensorEvent.values[1]+"m/s2 \n"+"Z-axis : "+sensorEvent.values[2]+"m/s2";
//        Intent i = new Intent();  // or // Intent i = getIntent()
//        i.putExtra("TAGs",val);
//        setResult(RESULT_OK , i);
//        finish();
        curX=sensorEvent.values[0];
        curY=sensorEvent.values[1];
        curZ=sensorEvent.values[2];
        if (itisNotFirstTime){
            XDiff= Math.abs(lastX-curX);
            YDiff= Math.abs(lastY-curY);
            ZDiff= Math.abs(lastZ-curZ);
            if((XDiff>shakeThreashold && YDiff>shakeThreashold)||(XDiff>shakeThreashold && ZDiff>shakeThreashold)||(YDiff>shakeThreashold && ZDiff>shakeThreashold)){
               vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            }else {
                vibrator.vibrate(500);
            }
        }
        lastX=curX;lastY=curY;lastZ=curZ;
        itisNotFirstTime=true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isAccelerometerSensor){
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isAccelerometerSensor){
            sensorManager.unregisterListener(this);
        }
    }
}