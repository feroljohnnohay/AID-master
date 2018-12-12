package com.example.lawrence.aid;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class sirenClass extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageButton btnSiren;
    private Boolean is_Playing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siren);

//        mediaPlayer = MediaPlayer.create(this, R.raw.siren);
//        btnSiren.findViewById(R.id.sirenButton);
//        btnSiren.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (is_Playing == false){
//                    mediaPlayer.start();
//                    btnSiren.setImageResource(R.drawable.sirenb);
//                    is_Playing = true;
//                    mediaPlayer.setLooping(true);
//                } else{
//                    mediaPlayer.stop();
//                    btnSiren.setImageResource(R.drawable.siren);
//                    is_Playing = false;
//                }
//            }
//        });
    }



    public void startSiren(View v){

        mediaPlayer = MediaPlayer.create(this, R.raw.siren);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        is_Playing = true;
    }

    public void backButtonSi(View v){
        mediaPlayer.stop();
        Intent i = new Intent(this, MenuClass.class);
        startActivity(i);
    }
}
