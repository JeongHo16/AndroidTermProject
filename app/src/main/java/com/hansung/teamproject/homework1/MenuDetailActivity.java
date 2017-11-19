package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

        if(getResources().getConfiguration().orientation //프래그먼트 실습 참고 코드
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        MenuDetailFragment details = new MenuDetailFragment();//프래그먼트 실습 참고 코드
        details.setSelection(getIntent().getIntExtra("index", -1));
        getSupportFragmentManager().beginTransaction().replace(R.id.menudetails, details).commit();

        Intent intent = getIntent(); //리스트뷰로 부터온 인텐트 받기
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String menuimage = intent.getStringExtra("menuimage");
        String description = intent.getStringExtra("description");

        if(name != null) {
            TextView nameView = (TextView) findViewById(R.id.view_name);
            nameView.setText(name);
        }
        if(price != null){
            TextView priceView = (TextView) findViewById(R.id.view_prise);
            priceView.setText(price+"원");
        }
        if(menuimage != null){
            ImageView imageView = (ImageView) findViewById(R.id.view_image);
            imageView.setImageURI(Uri.parse(menuimage));
        }
        if(description != null){
            TextView pointView = (TextView) findViewById(R.id.view_point);
            pointView.setText("설명: "+description);
        }

        ActionBar actionBar = getSupportActionBar(); //액션바 실습 참고 코드
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
