package com.hansung.teamproject.homework1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Junho on 2017-10-14.
 */

public class CustomActivity extends BaseAdapter{
    ArrayList<MyItem> mData;
    Context mContext;
    int mResource;

    public CustomActivity(ArrayList<MyItem> data, Context context, int resource){
        mData = data;
        mContext = context;
        mResource = resource;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mResource, viewGroup, false);
        }

        ImageView Image = view.findViewById(R.id.Item_image);
        TextView Text1 = view.findViewById(R.id.Item_name);
        TextView Text2 = view.findViewById(R.id.Item_price);

        Image.setImageDrawable(mData.get(i).image);
        Text1.setText(mData.get(i).name);
        Text2.setText(mData.get(i).price);

        return view;
    }
}
class MyItem{
    Drawable image;
    String name;
    String price;
    public MyItem(Drawable image, String name, String price){
        this.image = image;
        this.name = name;
        this.price = price;
    }
}
