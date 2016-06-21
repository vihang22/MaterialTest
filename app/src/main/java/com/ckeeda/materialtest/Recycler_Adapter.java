package com.ckeeda.materialtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.MyViewHolder> {

    private LayoutInflater inflate;
    List<Information> data  = Collections.emptyList();

    Recycler_Adapter(Context context,List<Information> data){
        inflate = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflate.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information cur = data.get(position);
        holder.recycler_image.setImageResource(cur.iconid);
        Log.d("Title", "onBindViewHolder:"+cur.title);
        holder.recycler_text.setText(cur.title);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView recycler_text;
        ImageView recycler_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            recycler_image = (ImageView) itemView.findViewById(R.id.recycler_image);
            recycler_text = (TextView) itemView.findViewById(R.id.recycler_text);


        }
    }
}
