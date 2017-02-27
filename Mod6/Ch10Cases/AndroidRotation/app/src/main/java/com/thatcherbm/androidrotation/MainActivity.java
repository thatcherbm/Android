/*
 * Ch10 Cases
 * Android Rotation
 *
 * Ben Thatcher
 * ITEC 2615
 * 12/7/16
 *
 * This program is the Intermediate difficulty case from Ch10.  The app is very simple, it loads to
 * show a picture of an android phone, which rotates in a complete circle 4 times at a rate of 1
 * rotation per 1.5s
 */


package com.thatcherbm.androidrotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgRotate = (ImageView) findViewById(R.id.imgPhone);
        imgRotate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));

    }
}
