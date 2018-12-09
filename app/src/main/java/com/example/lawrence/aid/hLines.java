package com.example.lawrence.aid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class hLines extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotline);
    }



    public void backButton(View v){
        Intent i = new Intent(this, MenuClass.class);
        startActivity(i);
    }
}
