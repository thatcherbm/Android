/*
 * Ch7 Cases
 * Personal Photos App
 *
 * Ben Thatcher
 * ITEC 2615
 * 11/8/16
 *
 * This program is the challenging difficulty case from Ch7.
 * The program uses a gridview to display 8 personl photos.
 * Touching any of the images displays a bigger version of the
 * image below and a toast states the name of the person in the
 * photo. The hardest part of this project was getting my photos
 * small enough to avoid an out of memory error.
 */

package com.thatcherbm.personalphoto;

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
    Integer[] gridImages = {R.drawable.beardvember, R.drawable.dallin_family, R.drawable.houston_jesse,
            R.drawable.houston_rudy, R.drawable.kimball_charissa, R.drawable.laney_molly_zeek,
            R.drawable.trevor_family, R.drawable.zeek};
    String[] names = {"Ben", "Dallin and Family", "Houston",
            "Houston and Rudy", "Kimball and Charissa", "Laney, Molly, and Zeek",
            "Trevor and Family", "Zeek"};
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gr = (GridView) findViewById(R.id.gridView1);
        final ImageView pic = (ImageView)findViewById(R.id.imgLarge);
        gr.setAdapter(new ImageAdapter(this));
        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), names[position], Toast.LENGTH_SHORT).show();
                pic.setImageResource(gridImages[position]);
            }
        });

    }

    public class ImageAdapter extends BaseAdapter{
        private Context context;
        public ImageAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return gridImages.length;
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
            pic.setImageResource(gridImages[position]);
            pic.setScaleType(ImageView.ScaleType.FIT_CENTER);
            pic.setLayoutParams(new GridView.LayoutParams(220,220));
            return pic;
        }
    }
}
