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

public class CustomAdapter extends BaseAdapter{
    private Context mContext;
    private int mResource;
    private ArrayList<MyItem> mData = new ArrayList<MyItem>();

    public CustomAdapter(Context context, int resource, ArrayList<MyItem> data){
        mContext = context;
        mData = data;
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

        ImageView Image = (ImageView) view.findViewById(R.id.Item_image);
        Image.setImageResource(mData.get(i).image);

        TextView Text1 = (TextView) view.findViewById(R.id.Item_name);
        Text1.setText(mData.get(i).name);

        TextView Text2 = (TextView) view.findViewById(R.id.Item_price);
        Text2.setText(mData.get(i).price);

        return view;
    }
}
class MyItem{
    int image;
    String name;
    String price;
    String point;

<<<<<<< HEAD
    MyItem(int image, String name, String price){
        this.image = image;
=======
<<<<<<< HEAD
    public MyItem(int image, String name, String price, String point){
      //  this.image = image;
=======
    public MyItem(int image, String name, String price){
        //this.image = image;
>>>>>>> 3385f2f74eccf98ae009f8e609c5b4d61576d389
>>>>>>> 3442466593f231b122db4c691cfe24e696c5bab0
        this.name = name;
        this.price = price;
        this.point = point;
    }
}
