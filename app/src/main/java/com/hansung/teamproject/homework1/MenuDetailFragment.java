package com.hansung.teamproject.homework1;


import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailFragment extends Fragment { // 프래그먼트 참고 코드

    TextView nameView;
    TextView priceView;
    TextView pointView;
    ImageView imageView;

    public MenuDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = (View)inflater.inflate(R.layout.fragment_menu_detail2, container, false);

        if(MenuDetailActivity.name != null) {
            nameView = (TextView) rootView.findViewById(R.id.view_name);
            nameView.setText(MenuDetailActivity.name);
        }
        if(MenuDetailActivity.price != null){
            priceView = (TextView) rootView.findViewById(R.id.view_prise);
            priceView.setText(MenuDetailActivity.price+" 원");
        }
        if(MenuDetailActivity.menuimage != null){
            imageView = (ImageView) rootView.findViewById(R.id.view_image);
            imageView.setImageURI(Uri.parse(MenuDetailActivity.menuimage));
        }
        if(MenuDetailActivity.description != null){
            pointView = (TextView) rootView.findViewById(R.id.view_point);
            pointView.setText("설명: "+MenuDetailActivity.description);
        }

        return rootView;
    }
    public void setSelection(MyItem item){
        MenuDetailActivity.name = item.name;
        MenuDetailActivity.description = item.point;
        MenuDetailActivity.price = item.price;
        MenuDetailActivity.menuimage = item.image;
    }
}
