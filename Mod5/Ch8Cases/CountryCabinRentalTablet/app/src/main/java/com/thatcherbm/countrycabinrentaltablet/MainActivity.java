/*
 * Ch8 Cases
 * Cabin Rental Tablet App
 *
 * Ben Thatcher
 * ITEC 2615
 * 11/15/16
 *
 * This program is the intermediate difficulty case from Ch8. It allows the user to choose a 3-day
 * reservation for one of two styles of cabin.  I've added some additional functionality that isn't
 * specified in the book example.  I've disabled all dates in the datepicker before 2 days from the
 * current date (to be realistic) and moved the setting of the calendar object c to the current
 * date so it is in the onClickListener so that you can go back into the datepicker without closing
 * the app and be able to pick a date before the date you selected last time.
 */


package com.thatcherbm.countrycabinrentaltablet;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {
    private RadioGroup cabin;
    private TextView reservation;
    private String cabinname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cabin = (RadioGroup) findViewById(R.id.cabins);
        Button btDate = (Button) findViewById(R.id.btnDate);
        reservation = (TextView) findViewById(R.id.txtReservation);

        btDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                c.add(Calendar.DATE, 2);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, d,
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });

    }

    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            int position = cabin.getCheckedRadioButtonId();
            switch(position) {
                case(R.id.rdAdirondack):
                    cabinname = getString(R.string.rdAdirondack);
                    break;
                case(R.id.rdAppalachian):
                    cabinname = getString(R.string.rdAppalachian);
                    break;
            }

            reservation.setText("Your reservation for an " + cabinname + " cabin is set for "
                + fmtDate.format(c.getTime()));
        }
    };
}
