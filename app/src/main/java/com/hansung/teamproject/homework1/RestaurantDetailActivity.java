package com.hansung.teamproject.homework1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.noodle_soup, "손칼국수", "5.000"));
        data.add(new MyItem(R.drawable.bossam_formality, "보쌈 정식", "7.000"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 중", "25.000"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 대", "30.000"));

        final CustomAdapter adapter = new CustomAdapter(data, this, R.layout.custom_view_lay);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setDivider(new ColorDrawable(Color.BLACK));
        listView.setDividerHeight(5);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int imageView = ((MyItem)adapter.getItem(i)).image;
                String name = ((MyItem)adapter.getItem(i)).name;
                String price = ((MyItem)adapter.getItem(i)).price;
                Toast.makeText(RestaurantDetailActivity.this,  name + price, Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}
