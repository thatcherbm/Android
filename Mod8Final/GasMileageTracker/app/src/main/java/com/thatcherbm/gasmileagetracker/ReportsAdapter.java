package com.thatcherbm.gasmileagetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bthatcher on 2/15/2017.
 */

public class ReportsAdapter extends ArrayAdapter<HashMap<String, String>> {
    public ReportsAdapter(Context context, ArrayList<HashMap<String, String>> reports) {
        super(context, 0, reports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HashMap<String, String> report = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fillup_line, parent, false);
        }
        // Lookup view for data population
        TextView vId = (TextView) convertView.findViewById(R.id.idFld);
        TextView rDate = (TextView) convertView.findViewById(R.id.dateFld);
        TextView vName = (TextView) convertView.findViewById(R.id.vehicleFld);
        TextView rMiles = (TextView) convertView.findViewById(R.id.milesFld);
        TextView rGallons = (TextView) convertView.findViewById(R.id.gallonsFld);
        TextView rCost = (TextView) convertView.findViewById(R.id.costFld);
        TextView rMileage = (TextView) convertView.findViewById(R.id.mileageFld);
        // Populate the data into the template view using the data object
        vId.setText(report.get(Fillup.KEY_ID));
        rDate.setText(report.get(Fillup.KEY_DATE));
        vName.setText(report.get(Fillup.KEY_VEHICLE_NAME));
        rMiles.setText(report.get(Fillup.KEY_MILES));
        rGallons.setText(report.get(Fillup.KEY_GALLONS));
        rCost.setText(report.get(Fillup.KEY_COST));
        rMileage.setText(report.get(Fillup.KEY_MILEAGE));

        // If the at Position >=1 and previous field date is same hide date
        if(position >= 1){
            HashMap<String, String> prevreport = getItem(position - 1);
            String current = report.get(Fillup.KEY_DATE);
            String prev = prevreport.get(Fillup.KEY_DATE);
            if(current.equals(prev)){
                rDate.setVisibility(View.GONE);
            }
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
