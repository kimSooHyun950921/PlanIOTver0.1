package com.example.kimsoohyun.planiotver01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Item.MyPlantItem;
import com.example.kimsoohyun.planiotver01.R;

import java.util.ArrayList;

/**
 * Created by kimsoohyun on 2017-08-01.
 */
public class menuAdapter extends BaseAdapter {
    private ArrayList<MyPlantItem> menuList = new ArrayList<>();
    public menuAdapter(){

    }


    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_item_layout, parent, false);}
            ImageView iconImageView = (ImageView) view.findViewById(R.id.myPlantImage);
            TextView plantName = (TextView) view.findViewById(R.id.myPlantName);
            TextView plantDate = (TextView) view.findViewById(R.id.menu_plantDate);
            TextView originalPlantName = (TextView)view.findViewById(R.id.menu_original_plant_name);


        Glide.with(iconImageView.getContext())
                .load(menuList.get(position).getMyPlantImg())
                .into(iconImageView);
            plantName.setText(menuList.get(position).getMyPlantName());
            plantDate.setText(menuList.get(position).getPlantDate());
            originalPlantName.setText(menuList.get(position).getOriginalPlantName());
            return view;



    }
    public void addItem(String icon,String plantName ,String originalName, String date) {
        MyPlantItem item = new MyPlantItem(icon,plantName,originalName,date);
        menuList.add(item);
    }

}
