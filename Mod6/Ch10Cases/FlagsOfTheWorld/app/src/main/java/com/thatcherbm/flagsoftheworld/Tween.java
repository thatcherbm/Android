package com.thatcherbm.flagsoftheworld;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
// import android.view.animation.Animation;
// import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by bthatcher on 12/7/2016.
 */

public class Tween extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tween);
        final ImageView imgFade = (ImageView)findViewById(R.id.imgTween);
        // imgFade.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha)).Animation.AnimationListenerAdapter;

        imgFade.animate()
                .alpha(0f)
                .setDuration(10000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imgFade.setVisibility(View.GONE);
                    }
                });
    }
}
