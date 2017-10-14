package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        final CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_view_lay, data);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setDivider(new ColorDrawable(Color.BLACK));
        listView.setDividerHeight(5);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int imageView = ((MyItem)adapter.getItem(i)).image;
                String name = ((MyItem)adapter.getItem(i)).name;
                String price = ((MyItem)adapter.getItem(i)).price;
                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);
                intent.putExtra("item_image", imageView);
                intent.putExtra("item_name", name);
                intent.putExtra("item_price", price);
                //startActivity(intent);
            }
        });
    }

        public void call(View v){
            TextView textView = (TextView)findViewById(R.id.phonenumber);
            String callNum = textView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+callNum));
            startActivity(intent);
        }

}
