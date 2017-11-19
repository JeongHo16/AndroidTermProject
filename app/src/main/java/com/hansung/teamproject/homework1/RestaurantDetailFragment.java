package com.hansung.teamproject.homework1;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetailFragment extends Fragment {

    int mCurCheckPosition = -1;
    private MenuHelper menuHelper;

    public interface OnTitleSelectedListener {
        public void onTitleSelected(int i);
    }

    public RestaurantDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = (View)inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ListView lv = (ListView)rootView.findViewById(R.id.list_item);
        Cursor cursor = menuHelper.getAllUsersByMethod();
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(getContext(),
                        R.layout.custom_view_lay, cursor, new String[]{
                        MenuRegistration.Menu.KEY_IMAGE,
                        MenuRegistration.Menu.KEY_TITLE,
                        MenuRegistration.Menu.KEY_PRICE},
                        new int[]{R.id.Item_image, R.id.Item_name, R.id.Item_price}, 0);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurCheckPosition = i;
                Activity activity = getActivity();
                ((OnTitleSelectedListener)activity).onTitleSelected(i);
            }
        });
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        return rootView;
    }

    /*private void viewAllToListView() { //9주차 실습과제 참고코드
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
    }*/

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", -1);
            if (mCurCheckPosition >= 0) {
                Activity activity = getActivity(); // activity associated with the current fragment
                ((OnTitleSelectedListener)activity).onTitleSelected(mCurCheckPosition);

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

}
