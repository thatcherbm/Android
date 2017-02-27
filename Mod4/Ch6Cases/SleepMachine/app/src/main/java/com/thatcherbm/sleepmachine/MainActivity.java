package com.thatcherbm.sleepmachine;

import android.media.MediaPlayer;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btWaves, btFan;
    MediaPlayer mpWaves, mpFan;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btWaves = (Button) findViewById(R.id.wavesBtn);
        btFan = (Button) findViewById(R.id.fanBtn);
        btWaves.setOnClickListener(bWaves);
        btFan.setOnClickListener(bFan);
        mpWaves = new MediaPlayer();
        mpWaves = MediaPlayer.create(this, R.raw.waves);
        mpFan = new MediaPlayer();
        mpFan = MediaPlayer.create(this, R.raw.fan);
        playing = 0;
    }

    Button.OnClickListener bWaves = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(playing){
                case 0:
                    mpWaves.start();
                    playing = 1;
                    btWaves.setText(R.string.pauseWaves);
                    btFan.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpWaves.pause();
                    playing = 0;
                    btWaves.setText(R.string.playWaves);
                    btFan.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    Button.OnClickListener bFan = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch(playing){
                case 0:
                    mpFan.start();
                    playing = 1;
                    btFan.setText(R.string.pauseFan);
                    btWaves.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpFan.pause();
                    playing = 0;
                    btFan.setText(R.string.playFan);
                    btWaves.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}
