package com.thatcherbm.ringtones;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup ringtone;
    RadioButton kfcRdo, mentalRdo, manamanaRdo;
    TextView txkfc, txmental, txmanamana;
    Button playBtn;
    MediaPlayer mpkfc, mpmental, mpmanamana;
    int playing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ringtone = (RadioGroup) findViewById(R.id.ringtoneGroup);
        kfcRdo = (RadioButton) findViewById(R.id.kfcBtn);
        mentalRdo = (RadioButton) findViewById(R.id.mentalBtn);
        manamanaRdo = (RadioButton) findViewById(R.id.manamanaBtn);
        txkfc = (TextView) findViewById(R.id.kfcDesc);
        txmental = (TextView) findViewById(R.id.mentalDesc);
        txmanamana = (TextView) findViewById(R.id.manamanaDesc);
        mpkfc = new MediaPlayer();
        mpkfc = MediaPlayer.create(this, R.raw.kfc);
        mpmental = new MediaPlayer();
        mpmental = MediaPlayer.create(this, R.raw.mental_health);
        mpmanamana = new MediaPlayer();
        mpmanamana = MediaPlayer.create(this, R.raw.muppets_manamana);
        playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(play);
        playing = 0;
    }

    Button.OnClickListener play = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            int position = ringtone.getCheckedRadioButtonId();
            switch(playing){
                case 0:
                    playing = 1;
                    playBtn.setText(R.string.pauseTxt);

                    switch(position){
                        case R.id.kfcBtn:
                            mpkfc.start();
                            mentalRdo.setVisibility(View.INVISIBLE);
                            manamanaRdo.setVisibility(View.INVISIBLE);
                            txmental.setVisibility(View.INVISIBLE);
                            txmanamana.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.mentalBtn:
                            mpmental.start();
                            kfcRdo.setVisibility(View.INVISIBLE);
                            manamanaRdo.setVisibility(View.INVISIBLE);
                            txkfc.setVisibility(View.INVISIBLE);
                            txmanamana.setVisibility(View.INVISIBLE);
                            break;
                        case R.id.manamanaBtn:
                            mpmanamana.start();
                            kfcRdo.setVisibility(View.INVISIBLE);
                            mentalRdo.setVisibility(View.INVISIBLE);
                            txkfc.setVisibility(View.INVISIBLE);
                            txmental.setVisibility(View.INVISIBLE);
                            break;
                    }

                    break;
                case 1:
                    playing = 0;
                    playBtn.setText(R.string.playTxt);

                    switch(position){
                        case R.id.kfcBtn:
                            mpkfc.pause();
                            mentalRdo.setVisibility(View.VISIBLE);
                            manamanaRdo.setVisibility(View.VISIBLE);
                            txmental.setVisibility(View.VISIBLE);
                            txmanamana.setVisibility(View.VISIBLE);
                            break;
                        case R.id.mentalBtn:
                            mpmental.pause();
                            kfcRdo.setVisibility(View.VISIBLE);
                            manamanaRdo.setVisibility(View.VISIBLE);
                            txkfc.setVisibility(View.VISIBLE);
                            txmanamana.setVisibility(View.VISIBLE);
                            break;
                        case R.id.manamanaBtn:
                            mpmanamana.pause();
                            kfcRdo.setVisibility(View.VISIBLE);
                            mentalRdo.setVisibility(View.VISIBLE);
                            txkfc.setVisibility(View.VISIBLE);
                            txmental.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
            }
        }
    };
}
