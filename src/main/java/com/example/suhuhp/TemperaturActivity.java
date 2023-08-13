package com.example.suhuhp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TemperaturActivity extends AppCompatActivity implements SensorEventListener{
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemperatureSsensoreAvailable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.temperaturetext);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){
            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemperatureSsensoreAvailable = true;
        }else{
            textview.setText("Temperature Sensor is not Availible");
            isTemperatureSsensoreAvailable = false;
        }
    }
    @Override
    public void onSensorChanged (SensorEvent sensorEvent){
        textview.setText(sensorEvent.values[0]+"°C");
    }
    @Override
    public void onAccuracyChanged (Sensor sensor, int i) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(isTemperatureSsensoreAvailable){
            sensorManager.registerListener(this,tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        if (isTemperatureSsensoreAvailable){
            sensorManager.unregisterListener(this);
        }
    }
}


