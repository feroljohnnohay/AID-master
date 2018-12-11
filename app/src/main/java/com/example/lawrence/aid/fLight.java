package com.example.lawrence.aid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class fLight extends AppCompatActivity {

    //For the changing of UI.....//

    ImageButton Switch;
    private Boolean has_flash = false;
    private Boolean was_LightsOn = false;
    //..........................//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashlight);

        Switch = (ImageButton) findViewById(R.id.lightOff);
        has_flash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


        Switch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (has_flash){
                    if (was_LightsOn == false){
                        Switch.setImageResource(R.drawable.on);
                        flashLightOn();
                        was_LightsOn = true;
                    } else {
                        Switch.setImageResource(R.drawable.off);
                        flashLightOff();
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

    private void flashLightOn() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
        } catch (CameraAccessException e) {
        }
    }

    private void flashLightOff() {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e) {
        }
    }

    public void backButtonFlashlight(View v){
        Intent i = new Intent(this, MenuClass.class);
        startActivity(i);
    }
}
