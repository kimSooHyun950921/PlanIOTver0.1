package com.example.kimsoohyun.planiotver01.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kimsoohyun.planiotver01.Item.DicItem;
import com.example.kimsoohyun.planiotver01.R;

import java.util.ArrayList;

/**
 * Created by kimsoohyun on 2017-09-07.
 */

public class DicAdpaterRecycler extends RecyclerView.Adapter<DicAdpaterRecycler.ViewHolderDic> {

    private ArrayList<DicItem> listDic = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    public DicAdpaterRecycler(ArrayList<DicItem>listDic,Context context){
        this.listDic = listDic;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
   }

   public void setListDic(ArrayList<DicItem> list) {
        this.listDic = list;
        notifyItemRangeChanged(0,list.size());
    }

    static  class ViewHolderDic extends DicViewHolder {

        public ViewHolderDic(View itemView) {
           super(itemView);
        }

    }
    @Override
    public DicAdpaterRecycler.ViewHolderDic onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = layoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        ViewHolderDic viewHolder = new ViewHolderDic(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DicAdpaterRecycler.ViewHolderDic holder, int position) {
        holder.bind(listDic.get(position));
    }

    @Override
    public int getItemCount() {
        return listDic.size();
    }
}
