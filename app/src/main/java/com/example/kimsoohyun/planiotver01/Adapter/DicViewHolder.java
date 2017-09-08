package com.example.kimsoohyun.planiotver01.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.DicInfoActivity;
import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.example.kimsoohyun.planiotver01.R;

import java.util.ArrayList;

/**
 * Created by kimsoohyun on 2017-09-07.
 */

public class DicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    protected ImageView DicPlantImage;
    protected TextView name;
    protected TextView Explanation;
    ArrayList<DicItem> item = new ArrayList<DicItem>();
    Context ctx;
    public DicViewHolder(View itemView, Context ctx, ArrayList<DicItem> item)  {

        super(itemView);
        itemView.setOnClickListener(this);
        this.ctx = ctx;
        this.item = item;

        Explanation = (TextView)itemView.findViewById(R.id.content);
        name = (TextView)itemView.findViewById(R.id.name);
        DicPlantImage = (ImageView)itemView.findViewById(R.id.dic_image);
    }

    public void bind(DicItem dicItem) {
        name.setText(dicItem.getName());
        Explanation.setText(dicItem.getExplanation());
        Glide.with(DicPlantImage.getContext())
                .load(dicItem.getDicPlantImage())
                .into(DicPlantImage);


    }

    @Override
    public void onClick(View view) {
        int position = getPosition();
        DicItem item = this.item.get(position);
        Intent intent = new Intent(ctx, DicInfoActivity.class);


        intent.putExtra("DicItem",new DicItem(item.getDicPlantImage(),item.getName(),item.getExplanation(),item.getHumidity(),item.getLight(),item.getTemperature()));

        Log.i("습도",String.valueOf(item.getHumidity()));
        Log.i("빛",String.valueOf(item.getLight()));
        Log.i("온도",String.valueOf(item.getTemperature()));



        this.ctx.startActivity(intent);


    }


}
