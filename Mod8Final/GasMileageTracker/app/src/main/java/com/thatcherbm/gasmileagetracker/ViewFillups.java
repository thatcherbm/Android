package com.thatcherbm.gasmileagetracker;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewFillups extends ListActivity {

    private Spinner spinner;
    private ListView list;
    private Button add;
    private int hasVehicles;
    Context context;
    ArrayList<HashMap<String, String>> fillupList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_fillups);

        // initialize the layout items
        spinner = (Spinner)findViewById(R.id.spnVehicles);
        add = (Button)findViewById(R.id.BtnAdd);
        list = (ListView)findViewById(android.R.id.list);
        context = this;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int fillupId = Integer.parseInt(fillupList.get(position).get(Fillup.KEY_ID));
                Intent objIntent = new Intent(context, AddEditFillup.class);
                objIntent.putExtra("fillupId", fillupId);
                startActivity(objIntent);
            }
        });


        // get the list of vehicle for the spinner
        VehicleRepo vRepo = new VehicleRepo(this);
        final ArrayList<String> vehicleList =  vRepo.getVehicleList("");

        // The example I used to learn this gives and error for no results, but for my code
        // to work I need to always add in the 'ALL' option which is easier to do in the
        // getVehicleList method rather than tying to add it to the top of the list here.
        // however I do want to prevent a user from accessing the new fillup activity if they do
        // not already have some vehicles.

        hasVehicles = 0;
        if(vehicleList.size()>= 1) {
            if(vehicleList.size() > 1){
                hasVehicles = 1;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, vehicleList);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                /**
                 * Refreshes the list limiting entries to a particlar sort value being either
                 * all the items or only those applying to a specific vehicle.
                 * @param parent
                 * @param view
                 * @param position
                 * @param id
                 */
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selected = parent.getItemAtPosition(position).toString();
                    loadListItems(selected);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // sometimes you need nothing here
                }
            });

        }else{
            Toast.makeText(context,"No Vehicles!", Toast.LENGTH_SHORT).show();
        }

        // loadListItems("All");

        // open up the add/edit activity to add a new fillup
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasVehicles == 1) {
                    Intent objIntent = new Intent(context,AddEditFillup.class);
                    objIntent.putExtra("fillupId", -1);
                    String spin = spinner.getSelectedItem().toString();
                    if(!(spin.equals("ALL"))){
                        objIntent.putExtra("defVehicle", spin);
                    }
                    startActivity(objIntent);
                }else {
                    String message = "Please add a Vehicle First!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadListItems(String limit) {

        FillupRepo fRepo = new FillupRepo(this);

        /**
         * Check to see if we want all the entries or just those for a particular vehicle
         */

        if(limit.equals("All")) {
            fillupList = fRepo.getFillupListAll();

        }else{
            fillupList =  fRepo.getFillupListVehicle(limit);
        }

        if(fillupList.size()!=0) { // if results were returned
            // Populate the list with the entries
            ListAdapter adapter = new SimpleAdapter( this, fillupList, R.layout.fillup_line,
                    new String[] { Fillup.KEY_DATE, Fillup.KEY_ID, Fillup.KEY_VEHICLE_NAME,
                            Fillup.KEY_MILES, Fillup.KEY_GALLONS, Fillup.KEY_COST, Fillup.KEY_MILEAGE},
                    new int[] {R.id.dateFld, R.id.idFld, R.id.vehicleFld, R.id.milesFld,
                            R.id.gallonsFld, R.id.costFld, R.id.mileageFld});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this,"No fillups!",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When the activity resumes after adding a new fillup the list should refresh, going to
     * whatever vehicle sort was previously chosen.
     */

    @Override
    public void onResume() {
        super.onResume();
        loadListItems(spinner.getSelectedItem().toString());
    }
}
