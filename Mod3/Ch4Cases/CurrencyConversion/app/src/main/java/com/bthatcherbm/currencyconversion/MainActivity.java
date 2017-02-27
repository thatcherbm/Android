package com.bthatcherbm.currencyconversion;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double toEuros = 0.908016;
    double toPesos = 18.9252;
    double toCandollars = 1.32607;
    String amtInTest;
    double amtIn;
    double amtOut;
    String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText currencyIn = (EditText) findViewById(R.id.moneyIn);
        final RadioButton toEuro = (RadioButton) findViewById(R.id.rdoEur);
        final RadioButton toPeso = (RadioButton) findViewById(R.id.rdoMex);
        final RadioButton toCan = (RadioButton) findViewById(R.id.rdoCan);
        final TextView result = (TextView) findViewById(R.id.moneyOut);
        Button calculate = (Button) findViewById(R.id.calcBtn);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtInTest = currencyIn.getText().toString();
                if(amtInTest.isEmpty()){
                    Toast.makeText(MainActivity.this, "Currency input Cannot be Empty", Toast.LENGTH_LONG).show();
                }else {
                    amtIn = Double.parseDouble(currencyIn.getText().toString());
                    DecimalFormat cash = new DecimalFormat("#,###,###.##");
                    if(amtIn <= 100000) {
                        if(toEuro.isChecked()) {
                            amtOut = amtIn * toEuros;
                            currency = getString(R.string.radioEur);
                        }
                        if(toPeso.isChecked()) {
                            amtOut = amtIn * toPesos;
                            currency = getString(R.string.radioMex);
                        }
                        if(toCan.isChecked()) {
                            amtOut = amtIn * toCandollars;
                            currency = getString(R.string.radioCan);
                        }

                        result.setText(cash.format(amtOut) + " " + currency);
                    }else {
                        Toast.makeText(MainActivity.this, "Currency input must be less than $100,000", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


    }
}
