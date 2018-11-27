package com.example.lawrence.aid;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class compass  extends AppCompatActivity implements SensorEventListener {

    ImageView img_compass;
    TextView txt_compass;
    int deg;

    private SensorManager mSensorManager;
    private Sensor mRotateV, mAccelerometer, mMagnetometer;
    float[] rCom = new float[9];
    float[] orient = new float[9];
    private float[] mLMagnetometer = new float[3];
    private float[] mLAccelerometer = new float[3];
    private boolean haveSensor = false, haveSensor2=false;
    private boolean mLAccelerometerSet=false;
    private boolean mLMagnetometerSet=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compass);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        img_compass = (ImageView) findViewById(R.id.imgcompass);

        start();
    }

    public void backButtonCo(View v){
        Intent i = new Intent(this, MenuClass.class);
        startActivity(i);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            SensorManager.getRotationMatrixFromVector(rCom,event.values);
            deg = (int) ((Math.toDegrees(SensorManager.getOrientation(rCom,orient)[0])+360)%360);
        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            System.arraycopy(event.values,0,mLAccelerometer,0,event.values.length);
            mLAccelerometerSet = true;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            System.arraycopy(event.values,0,mLMagnetometer,0,event.values.length);
            mLMagnetometerSet= true;
        }

        if(mLAccelerometerSet && mLMagnetometerSet){
            SensorManager.getRotationMatrix(rCom,null,mLAccelerometer,mLMagnetometer);
            SensorManager.getOrientation(rCom,orient);
            deg = (int) ((Math.toDegrees(SensorManager.getOrientation(rCom,orient)[0])+360)%360);
        }

        deg = Math.round(deg);
        img_compass.setRotation(-deg);

        String where = "No";

        if (deg >= 350 || deg <= 10) where = "N";
        if (deg < 350 && deg > 280) where = "NW";
        if (deg <=280 && deg > 260) where = "W";
        if (deg <= 260 && deg > 190) where = "SW";
        if (deg <= 190 && deg > 170) where = "S";
        if (deg <= 170 && deg > 100) where = "SE";
        if (deg <= 100 && deg > 80) where = "E";
        if (deg <= 80 && deg > 10) where = "NE";

        txt_compass.setText(deg+"° " + where);
        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void start(){
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)==null){
            if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)==null || mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)==null){
                noSensorAlert();
            } else {
                mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

                haveSensor = mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
                haveSensor2 = mSensorManager.registerListener(this,mMagnetometer,SensorManager.SENSOR_DELAY_UI);
            }
        }else{
            mRotateV = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            haveSensor = mSensorManager.registerListener(this,mRotateV,SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void noSensorAlert(){
        Toast.makeText(this,"There is no Sensors in this device", Toast.LENGTH_LONG).show();
        finish();
    }

    public void stop(){
        if (haveSensor && haveSensor2){
            mSensorManager.unregisterListener(this,mAccelerometer);
            mSensorManager.unregisterListener(this,mMagnetometer);
        } else
            if(haveSensor){
                mSensorManager.unregisterListener(this,mRotateV);
            }
    }

    @Override
    protected void onPause(){
        super.onPause();
        stop();
    }

    @Override
    protected void onResume(){
        super.onResume();
        stop();
    }
}

