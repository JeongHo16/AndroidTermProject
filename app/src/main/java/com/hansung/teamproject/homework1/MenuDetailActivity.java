package com.hansung.teamproject.homework1;

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
import android.widget.Toast;

import java.util.ArrayList;

public class MenuDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(ContextCompat.getDrawable(this, R.drawable.noodle_soup), "손칼국수", "5.000"));
        data.add(new MyItem(ContextCompat.getDrawable(this, R.drawable.bossam_formality), "보쌈 정식", "7.000"));
        data.add(new MyItem(ContextCompat.getDrawable(this, R.drawable.bossam_M), "보쌈 중", "25.000"));
        data.add(new MyItem(ContextCompat.getDrawable(this, R.drawable.bossam_M), "보쌈 대", "30.000"));

        final CustomActivity adapter = new CustomActivity(data, this, R.layout.custom_view_lay);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setDivider(new ColorDrawable(Color.BLACK));
        listView.setDividerHeight(5);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Drawable imageView = ((MyItem)adapter.getItem(i)).image;
                String name = ((MyItem)adapter.getItem(i)).name;
                String price = ((MyItem)adapter.getItem(i)).price;
                Toast.makeText(MenuDetailActivity.this,  name + price, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
