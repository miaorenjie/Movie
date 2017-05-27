package com.example.miaojie.ptest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miaojie.ptest.Activity.BuyTicketChooseCinemaActivity;
import com.example.miaojie.ptest.Activity.ChoosSeatActivity;
import com.example.miaojie.ptest.Activity.MainActivity;
import com.example.miaojie.ptest.Activity.WebViewActivity;
import com.example.miaojie.ptest.bean.MovieInfo;
import com.example.miaojie.ptest.R;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/3/17.
 */

public class RCadapter extends RecyclerView.Adapter {
    private ArrayList<MovieInfo>list;
    private Context context;
    private OnItemClickListener listener;
    private View HeadView;
    private int TYPE_NORMAL=1;
    private int TYPE_HEADER=2;
    private int mPosition;

    public void setList(ArrayList<MovieInfo> list) {
        this.list = list;
    }

    public RCadapter(Context context, ArrayList list)
    {
        this.context=context;
        this.list=list;
    }
    public RCadapter(Context context)
    {
        this.context=context;
    }

    public void setHeadView(View headView) {
        HeadView = headView;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(HeadView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType==TYPE_HEADER&&HeadView!=null)
           return new MyViewHolder(HeadView);
        final View view= LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
//        final View view= LayoutInflater.from(context).inflate(R.layout.test_item,parent,false);
        if(listener!=null)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(view);
                }
            });
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==TYPE_HEADER)
            return;
        if(HeadView!=null)
            mPosition=position-1;
        Log.e("asd",position+"");
        Log.e("asd","asd");
        MyViewHolder viewHolder= (MyViewHolder) holder;
        viewHolder.MovieCover.setImageResource(list.get(mPosition).CoverId);
        viewHolder.Director.setText(list.get(mPosition).getDirector());
        viewHolder.Protagonist.setText(list.get(mPosition).Protagonist);
        viewHolder.Name.setText(list.get(mPosition).getName());
        viewHolder.button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BuyTicketChooseCinemaActivity.class);
                MainActivity.orderInfo.setMovieName(list.get(mPosition).getName());
                MainActivity.orderInfo.setPrice(list.get(mPosition).getPrice());
                intent.putExtra("MovieName",list.get(mPosition).getName());
                context.startActivity(intent);
            }
        });
        viewHolder.MovieCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WebViewActivity.class);
                intent.putExtra("url",list.get(mPosition).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView MovieCover;
        public TextView Name;
        public TextView Director;
        public TextView Protagonist;
        public Button button_buy;
        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView==HeadView)
                return;
            MovieCover= (ImageView) itemView.findViewById(R.id.Movie_Item_Img);
            Name= (TextView) itemView.findViewById(R.id.Movie_Item_Name);
            Director= (TextView) itemView.findViewById(R.id.Movie_Item_Director);
            Protagonist= (TextView) itemView.findViewById(R.id.Movie_Item_Protagonist);
            button_buy= (Button) itemView.findViewById(R.id.Movie_Item_Buy);
        }
    }
    public interface OnItemClickListener
    {
        void OnItemClick(View view);
    }


}
