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
    Context context;
    ClickListener clickListener;


    Recycler_Adapter(Context context,List<Information> data){
        inflate = LayoutInflater.from(context);
        this.context = context;
        this.data = data;

    }

    void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflate.inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        Information cur = data.get(position);
        holder.recycler_image.setImageResource(cur.iconid);
        holder.recycler_text.setText(cur.title);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void Delete_data(int position){
        Log.i("DELETE",position+"");
        data.remove(position);
        notifyItemRemoved(position);

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView recycler_text;
        ImageView recycler_image;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recycler_image = (ImageView) itemView.findViewById(R.id.recycler_image);
            recycler_text = (TextView) itemView.findViewById(R.id.recycler_text);
            recycler_image.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(context,"You clicked on Position"+getPosition(),Toast.LENGTH_SHORT).show();
            //Delete_data(getPosition());
           // context.startActivity(new Intent(context,SubActivity.class));
            if(clickListener != null){
                clickListener.itemclicked(view,getPosition());

            }
        }


    }
    public interface ClickListener{
       void itemclicked(View view,int position);

    }
}
