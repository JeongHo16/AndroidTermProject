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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity {

    static String name=null;
    static String price=null;
    static String menuimage=null;
    static String description=null;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);


        if(getResources().getConfiguration().orientation //프래그먼트 실습 참고 코드
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        Intent intent = getIntent(); //리스트뷰로 부터온 인텐트 받기
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        menuimage = intent.getStringExtra("menuimage");
        description = intent.getStringExtra("description");
        title = intent.getStringExtra("title");

        ActionBar actionBar = getSupportActionBar(); //액션바 실습 참고 코드
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Drawable drawable = getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
            if (drawable != null) {
                drawable.setTint(Color.WHITE);
                actionBar.setHomeAsUpIndicator(drawable);
            }
        }

        MyItem myItem = new MyItem(menuimage, name, price, description);
        MenuDetailFragment details = new MenuDetailFragment();//프래그먼트 실습 참고 코드
        details.setSelection(myItem);
        getSupportFragmentManager().beginTransaction().replace(R.id.menudetails, details).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent1 = new Intent(getApplicationContext(), RestaurantDetailActivity.class);
        intent1.putExtra("plusesName", title);
        setResult(RESULT_OK, intent1);
        startActivity(intent1);
    }
}
