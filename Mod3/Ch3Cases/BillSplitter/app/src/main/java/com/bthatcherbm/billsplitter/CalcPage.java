package com.bthatcherbm.billsplitter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CalcPage extends AppCompatActivity {
    double bill;
    int numPeople;
    double tip = 1.18;
    double split;
    String quality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_page);
        final EditText billin = (EditText)findViewById(R.id.billText);
        final EditText numPeoplein = (EditText)findViewById(R.id.peopleText);
        final Spinner qualityin = (Spinner)findViewById(R.id.qualitySpin);
        Button c=(Button)findViewById(R.id.calcBtn);

        c.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final TextView total = (TextView)findViewById(R.id.tipTxt);
                bill = Double.parseDouble(billin.getText().toString());
                numPeople = Integer.parseInt(numPeoplein.getText().toString());
                split = bill * tip / numPeople;
                DecimalFormat splitEach = new DecimalFormat("$###,###.##");
                quality = qualityin.getSelectedItem().toString();
                total.setText("Your service was " + quality + ". Your share is " + splitEach.format(split));

            }
        });
    }
}
