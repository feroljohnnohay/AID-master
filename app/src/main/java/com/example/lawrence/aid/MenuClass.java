package com.example.lawrence.aid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void hotLines(View view){
        Intent intent = new Intent(this, hLines.class);
        startActivity(intent);
    }

    public void flashLight(View v){
        Intent i = new Intent(this, fLight.class);
        startActivity(i);
    }

    public void Compass(View v){
        Intent i = new Intent(this,compass.class);
        startActivity(i);
    }

    public void siren(View v){
        Intent i = new Intent(this,sirenClass.class);
        startActivity(i);
    }

    public void kits(View v){
        Intent i = new Intent(this,items.class);
        startActivity(i);
    }
}
