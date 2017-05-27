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

import com.example.BBS;
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
        orderAdapter=new OrderAdapter(OrderInfoActivity.this,orderInfos);


        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what==1)
                {
                    orderAdapter=new OrderAdapter(OrderInfoActivity.this,orderInfos);
                    Log.e("OrderInfoActivity",orderInfos.size()+"");
                    recyclerView.setAdapter(orderAdapter);
                    orderAdapter.setButtonOnClick(new OrderAdapter.buttonOnClick() {
                        @Override
                        public void onClick(int position) {
                            Log.e("点击了","position");
                            new Thread()
                            {
                                @Override
                                public void run() {
                                    super.run();
                                    IndentMgr.delete(orderInfos.get(position).getOrderId());
                                    Message message=new Message();
                                    message.what=2;
                                    message.obj=new Integer(position);
                                    handler.sendMessage(message);
                                    Log.e("点击了","delete");
                                }
                            }.start();
                            //此处该有删除数据库


                        }
                    });

                }
                if(msg.what==2){
                    Log.e("收到了","delete"+orderInfos.size()+"--"+((Integer) msg.obj));
                    Integer integer= (Integer) msg.obj;

                    orderInfos.remove(integer.intValue());

                    orderAdapter = new OrderAdapter(OrderInfoActivity.this, orderInfos);
                    orderAdapter.setButtonOnClick(new OrderAdapter.buttonOnClick() {
                        @Override
                        public void onClick(int position) {
                            Log.e("点击了","position");
                            new Thread()
                            {
                                @Override
                                public void run() {
                                    super.run();
                                    IndentMgr.delete(orderInfos.get(position).getOrderId());
                                    Message message=new Message();
                                    message.what=2;
                                    message.obj=new Integer(position);
                                    handler.sendMessage(message);
                                    Log.e("点击了","delete");
                                }
                            }.start();
                            //此处该有删除数据库


                        }
                    });
                    recyclerView.setAdapter(orderAdapter);
                    Log.e("结束","delete"+orderInfos.size());
                }

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
                if(allOrderInfo==null)
                    return;
                Log.e("OrderInfo",allOrderInfo.size()+"");
                for(int i=0;i<allOrderInfo.size();i++)
                {

                    ArrayList<Object>temp=allOrderInfo.get(i);

                    OrderInfo orderInfo=new OrderInfo();
                    orderInfo.setUserName((String) temp.get(9));
                    Log.e("OrderInfo",orderInfo.getUserName()+"--"+MainActivity.userInfo.getUserName());
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
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        }.start();


    }

}
