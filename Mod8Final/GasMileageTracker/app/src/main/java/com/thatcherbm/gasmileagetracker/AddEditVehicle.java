package com.thatcherbm.gasmileagetracker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AddEditVehicle extends AppCompatActivity {
    VehicleRepo vRepo;
    String getName;
    TextView vehicleDialogTitle;
    EditText enterName;
    Button addBtn, editBtn, cancelBtn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_vehicle);
        context = this;

        // Initialize form elements
        vehicleDialogTitle = (TextView)findViewById(R.id.vehicleDialogTitle);
        enterName = (EditText)findViewById(R.id.vehicleName);
        addBtn = (Button)findViewById(R.id.addButton);
        editBtn = (Button)findViewById(R.id.editButton);
        cancelBtn = (Button)findViewById(R.id.cancelButton);

        // Get repo and vehicle object for editing
        vRepo = new VehicleRepo(this);
        vRepo.cleanup();

        // Get extra
        getName = getIntent().getExtras().getString("vehicle");

        // Determine if doing a new fillup or editing existing.
        if(getName != null && !getName.isEmpty() && !getName.equals("null")) {
            // EDIT vehicle, add button inactive or set to delete if valid, filled field
            // Check for delete valid
            FillupRepo fRepo = new FillupRepo(context);
            ArrayList<HashMap<String, String>> fillupList;
            fillupList =  fRepo.getFillupListVehicle(getName);
            if(fillupList.size()==0) { // no attached fillups so delete is valid
                addBtn.setText(R.string.delete);
            }else {
                // Do not delete vehicles that have attached fillups
                // Disable add button instead
                addBtn.setEnabled(false);
            }
            vehicleDialogTitle.setText(R.string.vehicledialogtitleedit);
            enterName.setText(getName);
        }else { // new vehicle, edit button inactive, blank fields
            editBtn.setEnabled(false);
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addBtn.getText().equals(getString(R.string.add))) {
                    // Adding Vehicle
                    if (validateVehicle()) {
                        Vehicle newVehicle = new Vehicle();
                        newVehicle.name = enterName.getText().toString();
                        vRepo.insert(newVehicle);
                        finish();
                    }
                }else{
                    // Deleting Vehicle
                    Vehicle oldVehicle = new Vehicle();
                    oldVehicle.name = getName;
                    vRepo.delete(oldVehicle);
                    finish();
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateVehicle()) {
                    Vehicle oldVehicle = new Vehicle();
                    oldVehicle.name = getName;
                    Vehicle editVehicle = new Vehicle();
                    editVehicle.name = enterName.getText().toString();
                    vRepo.update(oldVehicle, editVehicle);
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

    private boolean validateVehicle() {
        boolean isValid = true;
        String name = enterName.getText().toString();
        // Check that the field is not empty
        if(name.isEmpty()){
            Toast.makeText(context,"Please enter a name", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        // Check to see if is existing name
        ArrayList<String> vehicles = vRepo.getVehicleList("All");
        if(vehicles.contains(name)){
            Toast.makeText(context,"Vehicle names must be unique", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }
}
