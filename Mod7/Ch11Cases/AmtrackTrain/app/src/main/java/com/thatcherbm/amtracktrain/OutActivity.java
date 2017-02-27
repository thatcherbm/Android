package com.thatcherbm.amtracktrain;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        TextView result = (TextView) findViewById(R.id.txtResult);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int bHour = sharedPref.getInt("bHour", 0);
        int bMinute = sharedPref.getInt("bMinute", 0);
        int tTime = sharedPref.getInt("tTime", 0);
        bHour += tTime / 60;
        bMinute += tTime % 60;
        String messageOut = "";

        if(bMinute > 59) {
            bMinute -= 60;
            bHour += 1;
        }

        if(bHour > 23){
            messageOut = "Red Eye ";
            while(bHour > 23) {
                bHour -= 24;
            }
        }
        messageOut += "Arrival time  " + bHour + ":" + bMinute;
        result.setText(messageOut);
    }
}
