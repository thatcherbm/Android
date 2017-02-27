package com.thatcherbm.rentafrag2;

/**
 * Created by bthatcher on 10/25/2016.
 */

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Companies, R.layout.my_list_row);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        switch (position) {
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
