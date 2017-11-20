package com.hansung.teamproject.homework1;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetailFragment extends Fragment { //프래그먼트 강의자료 참고 코드

    int mCurCheckPosition;
    MyItem myItem;

    public interface OnMenuSelectedListener {
        public void onMenuSelected(MyItem myItem, int i);
    }


    public RestaurantDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("생성상태", "RestaurantDetailFragment");
        View rootView = (View)inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.list_item);
        listView.setAdapter(new ArrayAdapter<MyItem>(getActivity(), android.R.layout.simple_list_item_activated_1));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurCheckPosition = position;
                Activity activity = getActivity();
                if(activity == null)
                    activity = new Activity();
                myItem = (MyItem)parent.getItemAtPosition(position);
                ((OnMenuSelectedListener)activity).onMenuSelected(myItem, position);
            }
        });
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        return rootView;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", -1);
            if (mCurCheckPosition >= 0) {
                Activity activity = getActivity(); // activity associated with the current fragment
                ((OnMenuSelectedListener)activity).onMenuSelected(myItem, mCurCheckPosition);

                ListView lv = (ListView) getView().findViewById(R.id.list_item);
                lv.setSelection(mCurCheckPosition);
                lv.smoothScrollToPosition(mCurCheckPosition);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("생성상태", "RestaurantDetailFragment" + " stop");
    }
}
