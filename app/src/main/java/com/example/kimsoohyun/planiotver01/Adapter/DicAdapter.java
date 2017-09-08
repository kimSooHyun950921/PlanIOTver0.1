package com.example.kimsoohyun.planiotver01.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.example.kimsoohyun.planiotver01.R;

import java.util.ArrayList;


/**
 * Created by kimsoohyun on 2017-09-05.
 */

public class DicAdapter extends BaseAdapter {
    ArrayList<DicItem> dicItemList = new ArrayList<>();
    public DicAdapter(){

    }


    @Override
    public int getCount() {
        return dicItemList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int position = i;
        final Context context = viewGroup.getContext();
        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem,viewGroup,false);}

        ImageView iconImageView = (ImageView)view.findViewById(R.id.dic_image);
        TextView plantName = (TextView) view.findViewById(R.id.name);
        TextView plantExplantion = (TextView)view.findViewById(R.id.content);

        plantName.setText(dicItemList.get(position).getName());
        plantExplantion.setText(dicItemList.get(position).getExplanation());
        return view;

        }



}
