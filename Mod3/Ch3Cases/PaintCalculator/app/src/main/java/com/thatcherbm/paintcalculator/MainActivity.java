package com.thatcherbm.paintcalculator;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int height;
    int distance;
    double numGallons;
    String paintColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText heightin = (EditText)findViewById(R.id.heightInput);
        final EditText distancein = (EditText)findViewById(R.id.distanceInput);
        final Spinner color = (Spinner)findViewById(R.id.colorInput);
        Button calc = (Button)findViewById(R.id.calcBtn);
        calc.setOnClickListener(new View.OnClickListener() {
            final TextView total = (TextView)findViewById(R.id.output);

            @Override
            public void onClick(View v) {
                height = Integer.parseInt(heightin.getText().toString());
                distance = Integer.parseInt(distancein.getText().toString());
                numGallons = (height * distance) / 250;
                DecimalFormat gallons = new DecimalFormat("###,###");
                paintColor = color.getSelectedItem().toString();
                total.setText(gallons.format(numGallons) + " Gallons of " + paintColor + " paint.");
            }
        });
    }
}
