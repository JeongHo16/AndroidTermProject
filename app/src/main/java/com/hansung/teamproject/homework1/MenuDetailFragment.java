package com.hansung.teamproject.homework1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailFragment extends Fragment { // 프래그먼트 참고 코드

    int index;

    public MenuDetailFragment() {
        // Required empty public constructor
    }

    public void setSelection(int i) { index = i; }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_detail2, container, false);
    }

}