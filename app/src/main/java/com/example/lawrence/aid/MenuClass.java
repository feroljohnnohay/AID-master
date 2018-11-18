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
        Intent i = new Intent(this, hLines.class);
        startActivity(i);
    }

    public void flashLight(View v){
        Intent i = new Intent(this, fLight.class);
        startActivity(i);
    }
}
