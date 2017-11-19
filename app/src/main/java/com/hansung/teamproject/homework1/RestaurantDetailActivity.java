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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView_title;
    TextView textView_address;
    TextView textView_phone;

    private ResHelper resHelper;
    private MenuHelper menuHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        menuHelper = new MenuHelper(this);

        resHelper = new ResHelper(this);
        Cursor cursor = resHelper.getAllUsersBySQL();

        Intent intent = getIntent();     // 인텐트 넘겨 받기
        String title;

        if(intent.getStringExtra("plusesNames") != null){       // 맛집등록에서 인텐트 받아옴
            title = intent.getStringExtra("plusesNames");
            Log.i("titles", title + "");
        }
        else{
            title = intent.getStringExtra("plusesName");
            Log.i("title", title + "");
            Log.i("titles", intent.getStringExtra("plusesNames") + "");
        }

        imageView = (ImageView) findViewById(R.id.imageView);
        textView_title = (TextView) findViewById(R.id.title);
        textView_address = (TextView) findViewById(R.id.address);
        textView_phone = (TextView) findViewById(R.id.phonenumber);

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

        viewAllToListView();
    }

    private void viewAllToListView() { //9주차 실습과제 참고코드
        Cursor cursor = menuHelper.getAllUsersByMethod();

        android.widget.SimpleCursorAdapter adapter =
                new android.widget.SimpleCursorAdapter(getApplicationContext(),
                        R.layout.custom_view_lay, cursor, new String[]{
                        MenuRegistration.Menu.KEY_IMAGE,
                        MenuRegistration.Menu.KEY_TITLE,
                        MenuRegistration.Menu.KEY_PRICE},
                        new int[]{R.id.Item_image, R.id.Item_name, R.id.Item_price}, 0);

        ListView lv = (ListView)findViewById(R.id.list_item);
        lv.setAdapter(adapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter adapter = adapterView.getAdapter();

                String menuimage = ((Cursor)adapter.getItem(i)).getString(2);
                String menutitle = ((Cursor)adapter.getItem(i)).getString(3);
                String menuprice = ((Cursor)adapter.getItem(i)).getString(4);
                String menudescription = ((Cursor)adapter.getItem(i)).getString(5);

                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);

                intent.putExtra("menuimage", menuimage);
                intent.putExtra("name", menutitle);
                intent.putExtra("price", menuprice);
                intent.putExtra("description", menudescription);
                startActivity(intent);
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
