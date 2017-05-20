package com.example.miaojie.ptest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.bean.CinemaInfo;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/19.
 */

public class ChooseCinemaAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<CinemaInfo>arrayList;
    private RecyclerViewItemOnClickListener listener;

    public void setListener(RecyclerViewItemOnClickListener listener) {
        this.listener = listener;
    }

    public ChooseCinemaAdapter(Context context) {
        this.context = context;
    }

    public ChooseCinemaAdapter(Context context, ArrayList<CinemaInfo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.choose_cinema_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.cinemaName.setText(arrayList.get(position).getCinema_Name());
        myViewHolder.cinemaAdress.setText(arrayList.get(position).getCinemaAdress());
        myViewHolder.cinemaRecentlySession.setText(arrayList.get(position).getRecentlySession());

        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.OnItemClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView cinemaName;
        public TextView cinemaAdress;
        public TextView cinemaRecentlySession;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            cinemaName= (TextView) view.findViewById(R.id.cinema_item_name);
            cinemaAdress= (TextView) view.findViewById(R.id.cinema_item_adress);
            cinemaRecentlySession= (TextView) view.findViewById(R.id.cinema_item_recentlySession);
        }
    }
}
