package com.thatcherbm.amtracktrain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    NumberPicker boardHour, boardMinute, tripTime;
    int bHour, bMinute, tTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardHour = (NumberPicker)findViewById(R.id.numpickHour);
        boardHour.setMaxValue(23);
        boardMinute = (NumberPicker)findViewById(R.id.numpickMinute);
        boardMinute.setMaxValue(59);
        tripTime = (NumberPicker)findViewById(R.id.numpickTripTime);
        tripTime.setMaxValue(1500);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Button calc = (Button)findViewById(R.id.btcalc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bHour = boardHour.getValue();
                bMinute = boardMinute.getValue();
                tTime = tripTime.getValue();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("bHour", bHour);
                editor.putInt("bMinute", bMinute);
                editor.putInt("tTime", tTime);
                editor.commit();
                startActivity(new Intent(MainActivity.this, OutActivity.class));
            }
        });
    }
}
