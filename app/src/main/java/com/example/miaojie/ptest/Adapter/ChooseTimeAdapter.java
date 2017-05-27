package com.example.miaojie.ptest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.miaojie.ptest.Activity.BuyTicketChooseTimeActivity;
import com.example.miaojie.ptest.Activity.ChoosSeatActivity;
import com.example.miaojie.ptest.Activity.MainActivity;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.bean.MovieSession;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/20.
 */

public class ChooseTimeAdapter extends RecyclerView.Adapter {
    private Context context;
    private RecyclerViewItemOnClickListener onClickListener;
    private ArrayList<MovieSession>arrayList;
    private String movieName;

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public ChooseTimeAdapter(Context context) {
        this.context = context;
    }

    public ChooseTimeAdapter(Context context, ArrayList<MovieSession> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setOnClickListener(RecyclerViewItemOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.time_item,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        if(onClickListener!=null)
        {

            myHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.OnItemClick(v);
                }
            });
        }
        myHolder.startTime.setText(arrayList.get(position).getStartTime());
        myHolder.endTime.setText(arrayList.get(position).getEndTime());
        myHolder.studioId.setText(arrayList.get(position).getStudioId());
        myHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ChoosSeatActivity.class);
                MainActivity.orderInfo.setStartTime(arrayList.get(position).getStartTime());
                MainActivity.orderInfo.setEndTime(arrayList.get(position).getEndTime());
                intent.putExtra("position",position);
                intent.putExtra("MovieName",movieName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView startTime;
        public TextView endTime;
        public TextView studioId;
        public Button buy;
        public MyHolder(View itemView) {
            super(itemView);
            view=itemView;
            startTime= (TextView) view.findViewById(R.id.time_startTime);
            endTime= (TextView) view.findViewById(R.id.time_endTime);
            studioId= (TextView) view.findViewById(R.id.time_studioId);
            buy= (Button) view.findViewById(R.id.Time_item_buy);
        }
    }
}
