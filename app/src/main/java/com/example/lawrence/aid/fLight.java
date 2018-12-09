package com.example.lawrence.aid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class fLight extends AppCompatActivity {

    //For the changing of UI.....//
    android.hardware.Camera camera;
    android.hardware.Camera.Parameters parameters;
    ImageButton Switch;
    private Boolean has_flash = false;
    private Boolean was_LightsOn = false;
    //..........................//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashlight);

        Switch = (ImageButton) findViewById(R.id.lightOff);
        if(getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
            camera = android.hardware.Camera.open();
            parameters = camera.getParameters();
            has_flash = true;
        }

        Switch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (has_flash){
                    if (was_LightsOn == false){
                        Switch.setImageResource(R.drawable.on);
                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(parameters);
                        camera.startPreview();
                        was_LightsOn = true;
                    } else {
                        Switch.setImageResource(R.drawable.off);
                        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(parameters);
                        camera.stopPreview();
                        was_LightsOn = false;
                    }


                } else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(fLight.this);
                    builder.setTitle("Error...");
                    builder.setMessage("Flashlight is not Available on this Device");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }


    public void backButtonFlashlight(View v){
        Intent i = new Intent(this, MenuClass.class);
        startActivity(i);
    }
}
