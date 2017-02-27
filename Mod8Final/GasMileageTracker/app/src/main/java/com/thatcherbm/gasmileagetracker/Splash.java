package com.thatcherbm.gasmileagetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);

        ImageView imgRotate = (ImageView)findViewById(R.id.imgSplashWheel);
        imgRotate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));

        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                finish();
                startActivity(new Intent(Splash.this, PageListActivity.class));
            }
        };
        Timer opening = new Timer();
        opening.schedule(task, 2000);



    }

}
