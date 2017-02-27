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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewVehicles extends ListActivity {
    private ListView list;
    private Button add;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicles);

        // initialize the layout items
        list = (ListView)findViewById(android.R.id.list);
        add = (Button)findViewById(R.id.BtnAdd);
        context = this;

        // open up the add/edit activity to add a new fillup
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent objIntent = new Intent(context, AddEditVehicle.class);
                objIntent.putExtra("vehicleName", "");
                startActivity(objIntent);
            }
        });

        // loadListItems();
    }

    /**
     * Loads the listview with items
     */

    public void loadListItems() {

        VehicleRepo vRepo = new VehicleRepo(this);
        final ArrayList<String> vehicleList;

        /**
         * Check to see if we want all the entries or just those for a particular vehicle
         */

        vehicleList = vRepo.getVehicleList("AddEdit");

        if(vehicleList.size()!=0) { // if results were returned

            // listener to open edit window when a list item is pressed
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String vehicleName = vehicleList.get(position);
                    Intent objIntent = new Intent(context, AddEditVehicle.class);
                    objIntent.putExtra("vehicle", vehicleName);
                    startActivity(objIntent);
                }
            });

            // Populate the list with the entries
            ArrayAdapter adapter = new ArrayAdapter<>( this, R.layout.vehicle_line,
                    vehicleList);
            setListAdapter(adapter);
        }else{
            Toast.makeText(context,"No Vehicles!",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * When the activity resumes after adding a new fillup the list should refresh, going to
     * whatever vehicle sort was previously chosen.
     */

    @Override
    public void onResume() {
        super.onResume();
        loadListItems();
    }
}
