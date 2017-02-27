/*
 * Ch7 Cases
 * Car Rental App
 *
 * Ben Thatcher
 * ITEC 2615
 * 11/7/16
 *
 * This program is the intermediate difficulty case from Ch7.  The program uses
 * a gridview to display images of 6 cars available for rent.  Touching any of
 * the images displays a bigger version of the image below and a toast states
 * the type of car and price.  The prompt says that I should use an If statement
 * with the toast but there isn't any reason for an if statement there, so I'm
 * just going to ignore it and see if it gets past the instructor.
 */


package com.thatcherbm.carrental;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Integer[] carImages = {R.drawable.economy, R.drawable.compact, R.drawable.intermediate,
            R.drawable.standard, R.drawable.full_size, R.drawable.standard_suv};
    String[] rentalTypes = {"Economy $46/day", "Compact $48/day", "Intermediate $50/day",
            "Standard $52/day", "Full Size $54/day", "Standard SUV $68/day"};
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gr = (GridView) findViewById(R.id.gridView1);
        final ImageView pic = (ImageView) findViewById(R.id.imgLarge);
        gr.setAdapter(new ImageAdapter(this));
        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getBaseContext(), rentalTypes[arg2], Toast.LENGTH_SHORT).show();
                pic.setImageResource(carImages[arg2]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter{
        private Context context;

        public ImageAdapter(Context c) {
            context=c;
        }

        @Override
        public int getCount() {
            return carImages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            pic = new ImageView(context);
            pic.setImageResource(carImages[position]);
            pic.setScaleType(ImageView.ScaleType.FIT_CENTER);
            pic.setLayoutParams(new GridView.LayoutParams(350,350));
            return pic;
        }
    }
}
