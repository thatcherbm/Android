package com.thatcherbm.rentacar;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class companyList extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        String[] company={"Alamo", "Dollar", "Hertz", "Enterprise", "Budget", "National"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.my_list_row, company));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position){
            case 0: // Alamo
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.alamo.com/en_US/car-rental/home.html")));
                break;
            case 1: // Dollar
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dollar.com/")));
                break;
            case 2: // Hertz
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hertz.com/rentacar/reservation/")));
                break;
            case 3: // Enterprise
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.enterprise.com/en/home.html")));
                break;
            case 4: // Budget
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.budget.com/budgetWeb/reservation/initializer.ex?action=reset")));
                break;
            case 5: // National
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nationalcar.com/en_US/car-rental/home.html")));
                break;
        }
    }
}
