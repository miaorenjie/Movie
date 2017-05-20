package com.example.miaojie.ptest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miaojie.ptest.bean.CinemaInfo;
import com.example.miaojie.ptest.R;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/3/22.
 */

public class CinemaAdapter extends RecyclerView.Adapter {
    private ArrayList<CinemaInfo> list;
    private Context context;
    private RCadapter.OnItemClickListener listener;
    public CinemaAdapter(Context context,ArrayList list)
    {
        this.context=context;
        this.list=list;
    }
    public CinemaAdapter(Context context)
    {
        this.context=context;
    }

    public void setListener(RCadapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(context).inflate(R.layout.cinema_item,parent,false);
//        final View view= LayoutInflater.from(context).inflate(R.layout.test_item,parent,false);

        return new CinemaAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CinemaAdapter.MyViewHolder viewHolder= (CinemaAdapter.MyViewHolder) holder;
        viewHolder.Name.setText(position+"");
        viewHolder.Introduce.setText(list.get(position).getCinema_Introduce());
        viewHolder.Name.setText(list.get(position).getCinema_Name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Name;
        public TextView Introduce;

        public MyViewHolder(View itemView) {
            super(itemView);
//            MovieCover= (ImageView) itemView.findViewById(R.id.Movie_Item_Img);
            Name= (TextView) itemView.findViewById(R.id.Cinema_Item_Name);
            Introduce= (TextView) itemView.findViewById(R.id.Cinema_Item_Introduce);
//            Protagonist= (TextView) itemView.findViewById(R.id.Movie_Item_Protagonist);
//            button_buy= (Button) itemView.findViewById(R.id.Movie_Item_Buy);
        }
    }
}
