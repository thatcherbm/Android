package com.thatcherbm.gadgetwishlist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GadgetView extends AppCompatActivity {

    String[] itemname ={
            "Ionic Breeze",
            "Iron Man Mouse",
            "Protoss Pylon Charger",
            "LED Lantern",
            "Weeping Angels Lights"
    };

    String[] linkaddress ={
            "https://www.sharperimage.com/si/view/product/Ionic+Comfort+Quadra+Air+Purifier/201114?trail=",
            "http://www.thinkgeek.com/product/jmhi/",
            "http://www.thinkgeek.com/product/1f45/",
            "http://www.thinkgeek.com/product/imrr/",
            "http://www.thinkgeek.com/product/ikvp/",
    };

    Integer[] imgid={
            R.drawable.ionic_breeze,
            R.drawable.iron_man_wired_gaming_mouse_front,
            R.drawable.starcraft_pylon_power_station,
            R.drawable.trinity_led_lantern,
            R.drawable.weeping_angel_string_lights
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget_view);

        final int gadgetValue = getIntent().getExtras().getInt("gadgetChoice");
        TextView textView = (TextView) findViewById(R.id.gadgetName);
        textView.setText(itemname[gadgetValue]);
        ImageView gadgetImage = (ImageView) findViewById(R.id.gadgetImage);
        gadgetImage.setImageResource(imgid[gadgetValue]);

        Button browse = (Button) findViewById(R.id.browseBtn);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkaddress[gadgetValue])));
            }
        });
    }
}
