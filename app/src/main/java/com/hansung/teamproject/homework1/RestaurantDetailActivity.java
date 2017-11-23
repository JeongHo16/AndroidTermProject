package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity implements RestaurantDetailFragment.OnMenuSelectedListener{

    ImageView imageView;
    TextView textView_title;
    TextView textView_address;
    TextView textView_phone;

    private ResHelper resHelper;
    private MenuHelper menuHelper;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        menuHelper = new MenuHelper(this);
        resHelper = new ResHelper(this);
        Cursor cursor = resHelper.getAllUsersBySQL();

        Intent intent = getIntent();     // 인텐트 넘겨 받기

        title = intent.getStringExtra("plusesName");

        imageView = (ImageView) findViewById(R.id.imageView);
        textView_title = (TextView) findViewById(R.id.title);
        textView_address = (TextView) findViewById(R.id.address);
        textView_phone = (TextView) findViewById(R.id.phonenumber);

        while(cursor.moveToNext()){
            if(cursor.getString(2).equals(title)){
                imageView.setImageURI(Uri.parse(cursor.getString(1)));
                textView_title.setText(cursor.getString(2));
                textView_address.setText(cursor.getString(3));
                textView_phone.setText(cursor.getString(4));
                break;
            }
        }
        viewAllToListView();
    }

    public void onMenuSelected(MyItem item) { //프래그먼트 강의자료 참고코드
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            MenuDetailFragment detailsFragment = new MenuDetailFragment();
            detailsFragment.setSelection(item);
            getSupportFragmentManager().beginTransaction().replace(R.id.menudetails, detailsFragment).commit();
        }else{

        }
    }

    public void viewAllToListView() { //9주차 실습과제 참고코드
        Cursor cursor = menuHelper.getAllUsersByMethod();

        ArrayList<MyItem> data = new ArrayList<MyItem>();
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(textView_title.getText())){
                data.add(new MyItem(cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            }
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_view_lay, data);

        ListView lv = (ListView)findViewById(R.id.list_item);
        lv.setAdapter(customAdapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();

                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);
                if (((MyItem)adapter.getItem(i)).image != null) {
                    String menuimage =((MyItem)adapter.getItem(i)).image;
                    intent.putExtra("menuimage", menuimage);
                }
                if (((MyItem) adapter.getItem(i)).name != null) {
                    String menutitle = ((MyItem) adapter.getItem(i)).name;
                    intent.putExtra("name", menutitle);
                }
                if (((MyItem) adapter.getItem(i)).price != null) {
                    String menuprice = ((MyItem) adapter.getItem(i)).price;
                    intent.putExtra("price", menuprice);
                }
                if (((MyItem) adapter.getItem(i)).point != null) {
                    String menudescription = ((MyItem) adapter.getItem(i)).point;
                    intent.putExtra("description", menudescription);
                }
                intent.putExtra("title",title);
                startActivity(intent);
                onMenuSelected((MyItem) adapter.getItem(i));
            }
        });
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void call(View v){
        TextView textView = (TextView)findViewById(R.id.phonenumber);
        String callNum = textView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+callNum));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //액션바 강의자료 참고코드
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resdetail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//액션바 강의자료 참고코드
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(getApplicationContext(), MenuRegistrationActivity.class);        // 인텐트 선언
                intent.putExtra("plusesName", title);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null)
            return;
        Cursor cursor = resHelper.getAllUsersBySQL();
        String intentData = data.getStringExtra("plusesName");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView_title = (TextView) findViewById(R.id.title);
        TextView textView_address = (TextView) findViewById(R.id.address);
        TextView textView_phone = (TextView) findViewById(R.id.phonenumber);

        while(cursor.moveToNext()){
            if(cursor.getString(2).equals(intentData)){
                imageView.setImageURI(Uri.parse(cursor.getString(1)));
                textView_title.setText(cursor.getString(2));
                textView_address.setText(cursor.getString(3));
                textView_phone.setText(cursor.getString(4));
                break;
            }
        }
    }
}
