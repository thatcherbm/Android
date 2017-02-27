package com.thatcherbm.gadgetwishlist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    String[] itemname ={
            "Ionic Breeze",
            "Iron Man Mouse",
            "Protoss Pylon Charger",
            "LED Lantern",
            "Weeping Angels Lights"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<>(this, R.layout.my_list_row, itemname));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent newActivity = new Intent(this, GadgetView.class);
        newActivity.putExtra("gadgetChoice", position);
        startActivity(newActivity);

        /**
         switch (position) {

             case 0: // Ionic Breeze
                 Intent newActivity0 = new Intent(this, GadgetView.class);
                 newActivity0.putExtra("gadgetChoice", position);
                 startActivity(newActivity0);
             break;
             case 1: // Iron Man Mouse
                 Intent newActivity1 = new Intent(this, GadgetView.class);
                 newActivity1.putExtra("gadgetChoice", position);
                 startActivity(newActivity1);
             break;
             case 2: // Pylon Charger
                 Intent newActivity2 = new Intent(this, GadgetView.class);
                 newActivity2.putExtra("gadgetChoice", position);
                 startActivity(newActivity2);
             break;
             case 3: // LED Lantern
                 Intent newActivity3 = new Intent(this, GadgetView.class);
                 newActivity3.putExtra("gadgetChoice", position);
                 startActivity(newActivity3);
             break;
             case 4: // Weeping Angels
                 Intent newActivity4 = new Intent(this, GadgetView.class);
                 newActivity4.putExtra("gadgetChoice", position);
                 startActivity(newActivity4);
             break;
         }
         */


    }
}
