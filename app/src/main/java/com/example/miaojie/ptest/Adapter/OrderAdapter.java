package com.example.miaojie.ptest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.bean.OrderInfo;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by miaojie on 2017/5/24.
 */

public class OrderAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<OrderInfo>orderInfos;
    private RecyclerViewItemOnClickListener listener;

    public RecyclerViewItemOnClickListener getListener() {
        return listener;
    }

    public void setListener(RecyclerViewItemOnClickListener listener) {
        this.listener = listener;
    }

    public OrderAdapter(Context context) {
        this.context = context;
    }

    public OrderAdapter(Context context,ArrayList<OrderInfo> orderInfos ) {
        this.orderInfos = orderInfos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        if (listener!=null)
            listener.OnItemClick(view);
        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrderHolder orderHolder= (OrderHolder) holder;
        orderHolder.orderId.setText(orderInfos.get(position).getOrderId());
        orderHolder.startTime.setText(orderInfos.get(position).getStartTime());
        orderHolder.cinemaName.setText(orderInfos.get(position).getCinemaName());
        String seats="";
        for(int i=0;i<orderInfos.get(position).getSeatInfos().size();i++)
        {
            seats+=orderInfos.get(position).getSeatInfos().get(i).getSeatX()+
                    "ее"+orderInfos.get(position).getSeatInfos().get(i).getSeatY()+"ап ";
        }
        orderHolder.seats.setText(seats);
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class OrderHolder extends RecyclerView.ViewHolder{
        private TextView orderId;
        private TextView movieName;
        private TextView startTime;
        private TextView cinemaName;
        private TextView seats;
        private Button delete;
        public OrderHolder(View itemView) {
            super(itemView);
            orderId= (TextView) itemView.findViewById(R.id.order_item_id);
            movieName= (TextView) itemView.findViewById(R.id.order_item_name);
            startTime= (TextView) itemView.findViewById(R.id.order_item_startTime);
            cinemaName= (TextView) itemView.findViewById(R.id.order_item_cinemaName);
            seats= (TextView) itemView.findViewById(R.id.order_item_seats);
            delete= (Button) itemView.findViewById(R.id.order_item_delete);
        }
    }
}
