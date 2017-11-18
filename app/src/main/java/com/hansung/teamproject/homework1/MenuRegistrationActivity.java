package com.hansung.teamproject.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MenuRegistrationActivity extends AppCompatActivity {

    EditText menuTitle;
    EditText menuPrice;
    EditText menuDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registration);

        menuTitle = (EditText)findViewById(R.id.menutitle);
        menuTitle.setText("메뉴 제목 등록");
        menuPrice = (EditText)findViewById(R.id.menuprice);
        menuPrice.setText("메뉴 가격 등록");
        menuDescription = (EditText)findViewById(R.id.menudescription);
        menuDescription.setText("메뉴 설명 등록");

        ImageButton restaurantImage = (ImageButton)findViewById(R.id.restaurantimage);
        restaurantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button menuAdd = (Button)findViewById(R.id.menuadd);
        menuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuSaveToDB();
            }
        });
    }

    private void menuSaveToDB() {

    }


}
