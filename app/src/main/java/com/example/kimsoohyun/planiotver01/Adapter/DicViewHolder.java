package com.example.kimsoohyun.planiotver01.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.example.kimsoohyun.planiotver01.R;

/**
 * Created by kimsoohyun on 2017-09-07.
 */

public class DicViewHolder extends RecyclerView.ViewHolder {
    protected ImageView DicPlantImage;
    protected TextView name;
    protected TextView Explanation;

    public DicViewHolder(View itemView) {
        super(itemView);

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
}
