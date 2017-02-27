package com.thatcherbm.floortiling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double twelvearea = 1;
    double eighteenarea = 1.5 * 1.5;
    String lengthTest;
    double length;
    String widthTest;
    double width;
    int tiles;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RadioButton twelve = (RadioButton) findViewById(R.id.rdBtn12);
        final RadioButton eighteen = (RadioButton) findViewById(R.id.rdBtn18);
        final EditText lengthentered = (EditText) findViewById(R.id.lengthIn);
        final EditText widthentered = (EditText) findViewById(R.id.widthIn);
        final TextView result = (TextView) findViewById(R.id.outputFld);
        Button calculate = (Button) findViewById(R.id.calcBtn);

        calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lengthTest = lengthentered.getText().toString();
                widthTest = widthentered.getText().toString();
                if(lengthTest.isEmpty() || widthTest.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.joke, Toast.LENGTH_LONG).show();
                }else {
                    length = Double.parseDouble(lengthentered.getText().toString());
                    width = Double.parseDouble(widthentered.getText().toString());

                    if(length == 0 || width == 0) {
                        Toast.makeText(MainActivity.this, R.string.joke, Toast.LENGTH_LONG).show();
                    }else {
                        if(twelve.isChecked()) {
                            tiles = (int) Math.ceil(length * width / twelvearea);
                            result.setText("You need " + tiles + " 12in x 12in tile(s)");
                        }
                        if(eighteen.isChecked()) {
                            tiles = (int) Math.ceil(length * width / eighteenarea);
                            result.setText("You need " + tiles + " 18in x 18in tile(s)");
                        }
                    }
                }
            }
        });


    }
}
