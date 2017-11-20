package com.hansung.teamproject.homework1;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailFragment extends Fragment { // 프래그먼트 참고 코드

    //int index;

    public MenuDetailFragment() {
        // Required empty public constructor
    }

    //public void setSelection(int i) { index = i; }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = (View)inflater.inflate(R.layout.fragment_menu_detail2, container, false);


            /*TextView nameView = (TextView) rootView.findViewById(R.id.view_name);
            nameView.setText(MenuDetailActivity.name);
            TextView priceView = (TextView) rootView.findViewById(R.id.view_prise);
            priceView.setText(MenuDetailActivity.price+"원");
            ImageView imageView = (ImageView) rootView.findViewById(R.id.view_image);
            imageView.setImageURI(Uri.parse(MenuDetailActivity.menuimage));
            TextView pointView = (TextView) rootView.findViewById(R.id.view_point);
            pointView.setText("설명: "+MenuDetailActivity.description);*/

        if(MenuDetailActivity.name != null) {
            TextView nameView = (TextView) rootView.findViewById(R.id.view_name);
            nameView.setText(MenuDetailActivity.name);
        }
        if(MenuDetailActivity.price != null){
            TextView priceView = (TextView) rootView.findViewById(R.id.view_prise);
            priceView.setText(MenuDetailActivity.price+"원");
        }
        if(MenuDetailActivity.menuimage != null){
            ImageView imageView = (ImageView) rootView.findViewById(R.id.view_image);
            imageView.setImageURI(Uri.parse(MenuDetailActivity.menuimage));
        }
        if(MenuDetailActivity.description != null){
            TextView pointView = (TextView) rootView.findViewById(R.id.view_point);
            pointView.setText("설명: "+MenuDetailActivity.description);
        }

        return rootView;
    }

}
