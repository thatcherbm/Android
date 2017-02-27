/*
 * Ch10 Cases
 * Flags of the World app
 *
 * Ben Thatcher
 * ITEC 2615
 * 12/8/16
 *
 * This program is the Challenging difficulty case from Ch10.  The app loads to a screen with an
 * image of a flag and two buttons.  The start flags button makes the image animate through 7
 * different flags indefinitely.  The stop flags button will end the flag animation from the start
 * button and open a new activity that shows the 7th flag and fades out over 10 seconds.  Technically
 * I didn't use the animation code described in the book for the stop flags animation, but I was
 * annoyed that after the flag faded out it reappeared and the only way I could find to make sure
 * that didn't happen was to use another animation function which actually doesn't use the
 * alpha.xml layout at all.  I left the original code in an commented it out.  I also wasted some
 * time trying to figure out how to get the last flag shown from the first animation and make that
 * the flag that fades out at the end, but it was a lot more complicated than I wanted to finish.
 */


package com.thatcherbm.flagsoftheworld;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable flagAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imgFrame = (ImageView)findViewById(R.id.imgFlag);
        imgFrame.setBackgroundResource(R.drawable.animation);
        flagAnimation = (AnimationDrawable)imgFrame.getBackground();
        Button btStart = (Button)findViewById(R.id.btStart);
        Button btStop = (Button)findViewById(R.id.btStop);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagAnimation.start();
            }
        });
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagAnimation.stop();
                startActivity (new Intent(MainActivity.this, Tween.class));
            }
        });
    }
}
