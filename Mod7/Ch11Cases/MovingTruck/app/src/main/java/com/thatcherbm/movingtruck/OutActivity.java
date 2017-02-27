package com.thatcherbm.movingtruck;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class OutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        ImageView image = (ImageView) findViewById(R.id.truckPic);
        TextView result = (TextView) findViewById(R.id.txtResults);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int rdoId = sharedPref.getInt("id", 0);
        int miles = sharedPref.getInt("miles", 0);
        double cost = miles * .99;
        String messageOut;
        NumberFormat cash = NumberFormat.getCurrencyInstance();

        switch (rdoId){
            case R.id.rdbt10:
                image.setImageResource(R.drawable.truck10);
                cost += 19.95;
                break;
            case R.id.rdbt17:
                image.setImageResource(R.drawable.truck17);
                cost += 29.95;
                break;
            case R.id.rdbt26:
                image.setImageResource(R.drawable.truck26);
                cost += 39.95;
                break;
        }
        messageOut = "Your rental will cost " + cash.format(cost);
        result.setText(messageOut);
    }
}
