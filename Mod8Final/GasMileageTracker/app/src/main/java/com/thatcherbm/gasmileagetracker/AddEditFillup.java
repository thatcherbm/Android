package com.thatcherbm.gasmileagetracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.text.TextUtils.isEmpty;

public class AddEditFillup extends AppCompatActivity {
    FillupRepo fRepo;
    int fid;
    TextView fillupDialogTitle;
    Spinner spinner;
    DatePickerDialog datePickerDialog;
    Calendar c;
    SimpleDateFormat df;
    EditText enterMiles, enterGallons, enterCost;
    Button selectDate, addBtn, editBtn, cancelBtn;
    Context context;
    ArrayList<String> vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_fillup);

        // Initialize form elements
        fillupDialogTitle = (TextView)findViewById(R.id.fillupDialogTitle);
        spinner = (Spinner)findViewById(R.id.selectVehicle);
        selectDate = (Button) findViewById(R.id.fillupDate);
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        enterMiles = (EditText)findViewById(R.id.fillupMiles);
        enterGallons = (EditText)findViewById(R.id.fillupGallons);
        enterCost = (EditText)findViewById(R.id.fillupCost);
        addBtn = (Button)findViewById(R.id.addButton);
        editBtn = (Button)findViewById(R.id.editButton);
        cancelBtn = (Button)findViewById(R.id.cancelButton);
        context = this;
        c = Calendar.getInstance();

        // Get repo and fillup object for editing
        fRepo = new FillupRepo(this);
        Fillup fillup;

        // get the list of vehicles for the spinner and validation
        // we do not want the list to have All at the beginning
        VehicleRepo vRepo = new VehicleRepo(this);
        vehicleList = vRepo.getVehicleList("AddEdit");

        // The example I used to learn this gives an error for no results, but I'm technically
        // dealing with that in ViewFillups.
        if(vehicleList.size()!=0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, vehicleList);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

        }else{ // this shouldn't be necessary but I left it.
            Toast.makeText(this,"No Vehicles! Create a Vehicle first.", Toast.LENGTH_SHORT).show();
        }

        fid = getIntent().getExtras().getInt("fillupId");
        // Determine if doing a new fillup or editing existing.
        if(fid == -1){ // NEW fillup, add button active, edit button inactive, blank fields
            editBtn.setEnabled(false);
            // If default vehicle was passed set it in vehicle spinner
            String defVehicle = getIntent().getExtras().getString("defVehicle");
            if(!isEmpty(defVehicle)){
                spinner.setSelection(vehicleList.indexOf(defVehicle));
            }
            // Fill in Date Field with today's date
            Date today = c.getTime();
            selectDate.setText(df.format(today));
        }else { // EDIT fillup, add button changed to delete, edit button active, filled fields
            addBtn.setText(R.string.delete);
            fillupDialogTitle.setText(R.string.fillupdialogtitleedit);
            // Retrieve the fillup data
            fillup = fRepo.getFillupById(fid);
            // set the Vehicle spinner to the vehicle
            spinner.setSelection(vehicleList.indexOf(fillup.vehicle_name));
            // set the date picker to the existing date
            selectDate.setText(fillup.date);
            // set the existing miles
            DecimalFormat fmiles =  new DecimalFormat("#,###.0");
            enterMiles.setText(fmiles.format(fillup.miles));
            // set the existing gallons
            DecimalFormat fgallons = new DecimalFormat("###.000");
            enterGallons.setText(fgallons.format(fillup.gallons));
            // set the existing cost
            NumberFormat fcost = NumberFormat.getCurrencyInstance();
            enterCost.setText(fcost.format(fillup.cost));
        }

        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Date newDate = c.getTime();
                selectDate.setText(df.format(newDate));

            }
        };
        datePickerDialog = new DatePickerDialog(this, d,
                c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addBtn.getText().toString().equals(getString(R.string.add))) { // adding
                    if(validateFillup()) {
                        Fillup newFillup = new Fillup();
                        newFillup.vehicle_name = spinner.getSelectedItem().toString();
                        newFillup.date = selectDate.getText().toString();
                        newFillup.miles = Double.parseDouble(enterMiles.getText().toString());
                        newFillup.gallons = Double.parseDouble(enterGallons.getText().toString());
                        newFillup.cost = Double.parseDouble(enterCost.getText().toString());
                        fRepo.insert(newFillup);
                    }
                } else { // Deleting
                    fRepo.delete(fid);
                }
                finish();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFillup()) {
                    Fillup editFillup = new Fillup();
                    editFillup.id = fid;
                    editFillup.vehicle_name = spinner.getSelectedItem().toString();
                    editFillup.date = selectDate.getText().toString();
                    editFillup.miles = Double.parseDouble(enterMiles.getText().toString());
                    editFillup.gallons = Double.parseDouble(enterGallons.getText().toString());
                    editFillup.cost = Double.parseDouble(enterCost.getText().toString());
                    fRepo.update(editFillup);
                    finish();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private boolean validateFillup() {
        boolean isValid = true;
        // Validate Vehicle (shouldn't be necessary but let's be thorough)
        String name = spinner.getSelectedItem().toString();
        if(name.isEmpty()){
            Toast.makeText(context,"Please Select a Vehicle", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else if(!vehicleList.contains(name)){
            Toast.makeText(context,"Invalid Vehicle", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        // Validate Date (also shouldn't be possible to be empty)
        String date = selectDate.getText().toString();
        if(date.isEmpty()){
            Toast.makeText(context,"Please Enter a Date", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        // Validate miles Check for empty then convert to number (box only allows number) check > 0
        String miles = selectDate.getText().toString();
        if(miles.isEmpty()){
            Toast.makeText(context,"Please Enter Miles", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else{
            Double numMiles = Double.parseDouble(enterMiles.getText().toString());
            if(numMiles <= 0){
                isValid = false;
                Toast.makeText(context,"Miles must be greater than zero", Toast.LENGTH_SHORT).show();
            }
        }
        // Validate Gallons Check for empty then convert to number (box only allows number) check > 0
        String gallons = selectDate.getText().toString();
        if(gallons.isEmpty()){
            Toast.makeText(context,"Please Enter Gallons", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else{
            Double numGallons = Double.parseDouble(enterGallons.getText().toString());
            if(numGallons <= 0){
                isValid = false;
                Toast.makeText(context,"Gallons must be greater than zero", Toast.LENGTH_SHORT).show();
            }
        }
        // Validate Cost Check for empty then convert to number (box only allows number) check > 0
        String cost = selectDate.getText().toString();
        if(cost.isEmpty()){
            Toast.makeText(context,"Please Enter Cost", Toast.LENGTH_SHORT).show();
            isValid = false;
        }else{
            Double numCost = Double.parseDouble(enterCost.getText().toString());
            if(numCost <= 0){
                isValid = false;
                Toast.makeText(context,"Cost must be greater than zero", Toast.LENGTH_SHORT).show();
            }
        }

        return isValid;
    }

}

