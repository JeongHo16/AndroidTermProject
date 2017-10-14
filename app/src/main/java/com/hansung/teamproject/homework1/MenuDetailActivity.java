package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        Intent intent = getIntent();
        int image = intent.getIntExtra("item_image", 1);
        String name = intent.getStringExtra("item_name");
        String price = intent.getStringExtra("item_price");

        TextView textname = (TextView)findViewById(R.id.Item_name);
        TextView textprice = (TextView)findViewById(R.id.Item_price);
        ImageView textimage = (ImageView)findViewById(R.id.Item_image);

        textname.setText(name);
        textprice.setText(price);
        textimage.setImageResource(image);
    }
}
