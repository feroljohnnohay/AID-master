package com.example.lawrence.aid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class sirenClass extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button btnSiren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siren);
    }



    public void startSiren(View v){
        mediaPlayer = MediaPlayer.create(this, R.raw.siren);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    public void backButtonSi(View v){
        mediaPlayer.stop();
        Intent i = new Intent(this, MenuClass.class);
        startActivity(i);
    }
}
