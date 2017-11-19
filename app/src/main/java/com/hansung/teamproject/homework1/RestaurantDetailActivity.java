package com.hansung.teamproject.homework1;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {

    ResHelper  resHelper = new ResHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        ArrayList<MyItem> data = new ArrayList<MyItem>();

        Cursor cursor = resHelper.getAllUsersBySQL();

        Intent intent = getIntent();     // 인텐트 넘겨 받기
        String title;

        if(intent.getStringExtra("plusesNames") != null){       // 추가매뉴에서 인텐트 받아옴
            title = intent.getStringExtra("plusesNames");
            Log.i("titles", title + "");
        }
        else{
            title = intent.getStringExtra("plusesName");
            Log.i("title", title + "");
            Log.i("titles", intent.getStringExtra("plusesNames") + "");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView_title = (TextView) findViewById(R.id.title);
        TextView textView_address = (TextView) findViewById(R.id.address);
        TextView textView_phone = (TextView) findViewById(R.id.phonenumber);

        while(cursor.moveToNext()){
            Log.i("getIntent_title", cursor.getString(2));
            if(cursor.getString(2).equals(title)){
                imageView.setImageURI(Uri.parse(cursor.getString(1)));
                textView_title.setText(cursor.getString(2));
                textView_address.setText(cursor.getString(3));
                textView_phone.setText(cursor.getString(4));
                break;
            }
        }

        data.add(new MyItem(R.drawable.noodle_soup, "손칼국수", "5.000", "4.5"));
        data.add(new MyItem(R.drawable.bossam_formality, "보쌈 정식", "7.000", "4.0"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 중", "25.000", "4.1"));
        data.add(new MyItem(R.drawable.bossam_l, "보쌈 대", "30.000", "3.7"));

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
                String point = ((MyItem)adapter.getItem(i)).point;

                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);

                intent.putExtra("name", name.toString());
                intent.putExtra("price", price.toString());
                intent.putExtra("image", imageView);
                intent.putExtra("point", point);
                startActivity(intent);
            }
        });
    }

        public void call(View v){
            TextView textView = (TextView)findViewById(R.id.phonenumber);
            String callNum = textView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+callNum));
            startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resdetail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                TextView textView_title = (TextView) findViewById(R.id.title);
                Intent intent = new Intent(getApplicationContext(), MenuRegistrationActivity.class);        // 인텐트 선언
                intent.putExtra("plusesName", textView_title.getText());
                Log.i("add_btn", textView_title.getText() + "");
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
        Log.i("onActivityResult_output", "start");
        Cursor cursor = resHelper.getAllUsersBySQL();
        String intentData = data.getStringExtra("plusesName");

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView_title = (TextView) findViewById(R.id.title);
        TextView textView_address = (TextView) findViewById(R.id.address);
        TextView textView_phone = (TextView) findViewById(R.id.phonenumber);

        while(cursor.moveToNext()){
            Log.i("onActivityResult", intentData);
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
