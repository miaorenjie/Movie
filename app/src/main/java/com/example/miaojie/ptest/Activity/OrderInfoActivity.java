package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.IndentMgr;
import com.example.miaojie.ptest.Adapter.OrderAdapter;
import com.example.miaojie.ptest.Adapter.RecyclerViewItemOnClickListener;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;
import com.example.miaojie.ptest.bean.OrderInfo;
import com.example.miaojie.ptest.bean.SeatInfo;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/22.
 */

public class OrderInfoActivity extends Activity {
    private TextView textView;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private ArrayList<OrderInfo>orderInfos;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyticket_choose_time_activity);
        textView= (TextView) findViewById(R.id.title);
        textView.setText("订单信息");
        recyclerView= (RecyclerView) findViewById(R.id.main_choose_time_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this,RecyclerView.HORIZONTAL));
        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                orderAdapter=new OrderAdapter(OrderInfoActivity.this,orderInfos);
                recyclerView.setAdapter(orderAdapter);
                orderAdapter.setListener(new RecyclerViewItemOnClickListener() {
                    @Override
                    public void OnItemClick(View view) {
                        int position=recyclerView.getChildAdapterPosition(view);
                        view.findViewById(R.id.order_item_delete).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                orderInfos.remove(position);
                                orderAdapter=new OrderAdapter(OrderInfoActivity.this,orderInfos);
                                recyclerView.setAdapter(orderAdapter);
                                //此处该有删除数据库

                            }
                        });
                    }
                });
            }
        };
        getOrderInfos();
    }
    private void getOrderInfos(){
        new Thread()
        {
            @Override
            public void run() {
                orderInfos=new ArrayList<OrderInfo>();

                ArrayList<ArrayList>allOrderInfo=IndentMgr.get();
                for(int i=0;i<allOrderInfo.size();i++)
                {

                    ArrayList<Object>temp=allOrderInfo.get(i);

                    OrderInfo orderInfo=new OrderInfo();
                    orderInfo.setUserName((String) temp.get(9));
                    if(!orderInfo.getUserName().equals(MainActivity.userInfo.getUserName()))
                        continue;
                    orderInfo.setOrderId((Integer) temp.get(0));
                    orderInfo.setTicketNumber((String) temp.get(1));
                    orderInfo.setPrice((Float) temp.get(2));
                    orderInfo.setBornTime((String) temp.get(3));
                    orderInfo.setCinemaName((String) temp.get(4));
                    orderInfo.setMovieName((String) temp.get(5));
                    orderInfo.setStartTime((String) temp.get(6));
                    orderInfo.setEndTime((String) temp.get(7));
                    ArrayList<SeatInfo>seatInfos=new ArrayList<>();
                    String seats=(String) temp.get(8);
                    Log.e("座位信息",seats);
                    String[]eachSeat=seats.split(",");
                    for(int j=0;j<eachSeat.length;j++)
                    {
                        String[]oneSeat=eachSeat[j].split(" ");
                        SeatInfo seatInfo=new SeatInfo(Integer.parseInt(oneSeat[0]),Integer.parseInt(oneSeat[1]));
                        seatInfos.add(seatInfo);
                    }
                    orderInfo.setSeatInfos(seatInfos);
                    orderInfos.add(orderInfo);
                }
                handler.sendMessage(new Message());
            }
        }.start();


    }

}
