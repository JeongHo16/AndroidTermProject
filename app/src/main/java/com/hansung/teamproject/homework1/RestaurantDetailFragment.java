package com.hansung.teamproject.homework1;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetailFragment extends Fragment { //프래그먼트 강의자료 참고 코드

    //int mCurCheckPosition = -1;

    public interface OnMenuSelectedListener {
        public void onMenuSelected(MyItem item);
    }


    public RestaurantDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
    }

    /*@Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", -1);
            if (mCurCheckPosition >= 0) {
                Activity activity = getActivity(); // activity associated with the current fragment
                ((OnMenuSelectedListener)activity).onMenuSelected(mCurCheckPosition);

                *//*ListView lv = (ListView) getView().findViewById(R.id.list_item);
                lv.setSelection(mCurCheckPosition);
                lv.smoothScrollToPosition(mCurCheckPosition);*//*
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }*/
}
