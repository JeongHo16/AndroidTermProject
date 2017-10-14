package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MenuDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        int image = intent.getIntExtra("image", 0);
        String point = intent.getStringExtra("point");

        if(name != null) {
            TextView nameView = (TextView) findViewById(R.id.view_name);
            nameView.setText(name);
        }
        if(price != null){
            TextView priceView = (TextView) findViewById(R.id.view_prise);
            priceView.setText(price);
        }
        if(image != 0){
            ImageView imageView = (ImageView) findViewById(R.id.view_image);
            imageView.setImageResource(image);
        }
        if(point != null){
            TextView pointView = (TextView) findViewById(R.id.view_point);
            pointView.setText(point);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Drawable drawable = getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
            if (drawable != null) {
                drawable.setTint(Color.WHITE);
                actionBar.setHomeAsUpIndicator(drawable);
            }
        }

    }
}
