package com.thatcherbm.movingtruck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    int intRdoid, intMiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioGroup truck = (RadioGroup)findViewById(R.id.rdgrpTruck);
        final EditText miles = (EditText)findViewById(R.id.edtxtMiles);
        Button submit = (Button)findViewById(R.id.btcalc);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intRdoid = truck.getCheckedRadioButtonId();
                if(miles.getText().toString().equals("")){
                    intMiles = 0;
                }else {
                    intMiles = Integer.parseInt(miles.getText().toString());
                }
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("id", intRdoid);
                editor.putInt("miles", intMiles);
                editor.commit();
                startActivity(new Intent(MainActivity.this, OutActivity.class));
            }
        });

    }
}
