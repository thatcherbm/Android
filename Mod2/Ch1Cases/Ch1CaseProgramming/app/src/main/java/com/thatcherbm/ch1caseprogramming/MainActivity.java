package com.thatcherbm.ch1caseprogramming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        final LinearLayout versions = (LinearLayout) findViewById(R.id.androidVersions);
        final LinearLayout card = (LinearLayout) findViewById(R.id.businessCard);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    versions.setVisibility(View.INVISIBLE);
                    card.setVisibility(View.VISIBLE);
                } else {
                    versions.setVisibility(View.VISIBLE);
                    card.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
