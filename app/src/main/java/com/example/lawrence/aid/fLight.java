package com.example.lawrence.aid;

import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class fLight extends AppCompatActivity {

    //For the changing of UI.....//
    private CameraManager CM_Manager;
    private String CM_Id;
    private ImageButton Switch;
    private Boolean was_LightsOn;
    //..........................//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashlight);

        Switch = (ImageButton) findViewById(R.id.lightOff);
        was_LightsOn = false;

        Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (was_LightsOn){
                        turnOff();
                        was_LightsOn = false;
                    } else{
                        turnOn();
                        was_LightsOn = true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    public void turnOn(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                CM_Manager.setTorchMode(CM_Id, true);
                Switch.setImageResource(R.drawable.on);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void turnOff(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                CM_Manager.setTorchMode(CM_Id, false);
                Switch.setImageResource(R.drawable.off);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
