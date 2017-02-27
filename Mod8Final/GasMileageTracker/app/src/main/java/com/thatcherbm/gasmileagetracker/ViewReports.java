package com.thatcherbm.gasmileagetracker;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewReports extends ListActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);
        context = this;
        FillupRepo fRepo = new FillupRepo(context);
        ArrayList<HashMap<String, String>> fillupList = new ArrayList<>();
        fillupList = fRepo.getFillupListMonthReport();
        if(fillupList.size()!=0) { // if results were returned
            // Custom ArrayAdapter
            ReportsAdapter rAdapter = new ReportsAdapter(this, fillupList);
            ListView rList = (ListView)findViewById(android.R.id.list);
            rList.setAdapter(rAdapter);
        }else{
            Toast.makeText(context,"No fillups!",Toast.LENGTH_SHORT).show();
        }

        RadioGroup rGroup = (RadioGroup)findViewById(R.id.inputTray);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FillupRepo fRepo = new FillupRepo(context);
                ArrayList<HashMap<String, String>> fillupList;

                switch(checkedId){
                    case R.id.rdoMonth:
                    default:
                        fillupList = fRepo.getFillupListMonthReport();
                        break;
                    case R.id.rdoYear:
                        fillupList = fRepo.getFillupListYearReport();
                        break;
                }

                if(fillupList.size()!=0) { // if results were returned
                    // Custom ArrayAdapter
                    ReportsAdapter rAdapter = new ReportsAdapter(context, fillupList);
                    ListView rList = (ListView)findViewById(android.R.id.list);
                    rList.setAdapter(rAdapter);
                }else{
                    Toast.makeText(context,"No fillups!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
