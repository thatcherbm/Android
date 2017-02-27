/*
 * Ch8 Cases
 * Wild Ginger Dinner Delivery Tablet App
 *
 * Ben Thatcher
 * ITEC 2615
 * 11/16/16
 *
 * This program is the Challenging difficulty case from Ch8. It allows the user to select a dish
 * to be delivered and select a time between 5-11pm to have the food delivered.  The prompt suggests
 * that the radio button choices aren't necessary but I like them.  The time picker isn't nearly as
 * nice about letting me mess with min/max values (basically I'd just be better of using a number
 * picker) so I'm not doing any fancy stuff and I'm not going to bother with any validation. I ahave
 * however set the thing up to start at 5 every time the button is clicked.
 */

package com.thatcherbm.wildgingerdinnerdeliverytablet;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {
    private RadioGroup food;
    private TextView reservation;
    private String dishname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        food = (RadioGroup) findViewById(R.id.food);
        Button btDate = (Button) findViewById(R.id.btnTime);
        reservation = (TextView) findViewById(R.id.txtReservation);

        btDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, d,
                        5, 0, true);
                timePickerDialog.show();
            }
        });

    }

    Calendar c = Calendar.getInstance();
   TimePickerDialog.OnTimeSetListener d = new TimePickerDialog.OnTimeSetListener(){

       @Override
       public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           int position = food.getCheckedRadioButtonId();
           switch(position) {
               case(R.id.rdZabuton):
                   dishname = getString(R.string.txtZabuton);
                   break;
               case(R.id.rdBaconDog):
                   dishname = getString(R.string.txtBaconDog);
                   break;
           }

           reservation.setText("Your " + dishname + " delivery is set for "
                   + hourOfDay + ":" + minute + " PM");
       }
    };
}
