package com.hansung.teamproject.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MenuRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registration);

        EditText menutitle = (EditText)findViewById(R.id.menutitle);
        menutitle.setText("메뉴 제목 등록");
        EditText menuprice = (EditText)findViewById(R.id.menuprice);
        menuprice.setText("메뉴 가격 등록");
        EditText menudescription = (EditText)findViewById(R.id.menudescription);
        menudescription.setText("메뉴 설명 등록");

        ImageButton restaurantimage = (ImageButton)findViewById(R.id.restaurantimage);
        restaurantimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
