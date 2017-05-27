package com.example.miaojie.ptest.Activity;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.BridgeNative;
import com.example.IndentMgr;
import com.example.miaojie.ptest.R;

import com.example.miaojie.ptest.Utils.SeatTable;
import com.example.miaojie.ptest.bean.OrderInfo;
import com.example.miaojie.ptest.bean.SeatInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by miaojie on 2017/3/22.
 */

public class ChoosSeatActivity extends AppCompatActivity {

    private SeatTable seatTableView;
    private Toolbar toolbar;
    private TextView textView;
    private ArrayList<SeatInfo>seatInfos;
    private ArrayList<OrderInfo>orderInfos;
    private ArrayList<SeatInfo>chooseSeatInfos;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_seat_activity);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(1000);//设置最多选中
        seatInfos=new ArrayList<>();

        chooseSeatInfos=new ArrayList<>();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                for(int i=0;i<orderInfos.size();i++)
                {
                    if(isThisSession(orderInfos.get(i)))
                    {
                        for(int j=0;j<orderInfos.get(i).getSeatInfos().size();j++)
                            seatInfos.add(orderInfos.get(i).getSeatInfos().get(j));
                    }
                }
                seatTableView.invalidate();
            }
        };
        getOrderInfos();
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {

                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                Log.e("座位信息",seatInfos.size()+"");
                for(int i=0;i<seatInfos.size();i++)
                {

                    SeatInfo seatInfo=seatInfos.get(i);
                    Log.e("选择座位",seatInfo.getSeatX()+"--"+row);
                    if(seatInfo.getSeatX()-1==row&&seatInfo.getSeatY()-1==column)
                        return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
               chooseSeatInfos.add(new SeatInfo(row+1,column+1));
            }

            @Override
            public void unCheck(int row, int column) {
                int i;
                for(i=0;i<chooseSeatInfos.size();i++)
                {
                    SeatInfo seatInfo=chooseSeatInfos.get(i);
                    if(seatInfo.getSeatX()-1==row&&seatInfo.getSeatY()-1==column)
                        break;
                }
                chooseSeatInfos.remove(i);
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10,15);
        //toolbar.setTitle(getIntent().getStringExtra("MovieName"));

        toolbar= (Toolbar) findViewById(R.id.seat_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buy_ticket_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.isLogin)
                {
                    Toast.makeText(ChoosSeatActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.orderInfo.setSeatInfos(chooseSeatInfos);
                MainActivity.orderInfo.setTicketNumber(chooseSeatInfos.size()+"");
                Date date=new Date();
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now=simpleDateFormat.format(date);
                MainActivity.orderInfo.setBornTime(now);
                OrderInfo orderInfo= MainActivity.orderInfo;
                Log.e("Mainac",MainActivity.orderInfo.toString());
                String seats="";
                for(int i=0;i<chooseSeatInfos.size();i++)
                {
                    //Log.e("asd",chooseSeatInfos.get(i).getSeatX()+"--"+chooseSeatInfos.get(i).getSeatY());
                    if(chooseSeatInfos.size()-1==i)
                        seats+=chooseSeatInfos.get(i).getSeatX()+" "+chooseSeatInfos.get(i).getSeatY();
                    else
                        seats+=chooseSeatInfos.get(i).getSeatX()+" "+chooseSeatInfos.get(i).getSeatY()+",";

                }
                Log.e("qwe",seats);
                //add(String tickets, float money, String time, String studio_name, String move_name, String start, String end, String seats, String usrname)

                String finalSeats = seats;
                new Thread(){
                    @Override
                    public void run() {
                        super.run();

                            IndentMgr.add(MainActivity.orderInfo.getTicketNumber()+"",
                                    MainActivity.orderInfo.getPrice(),
                                    MainActivity.orderInfo.getBornTime(),
                                    MainActivity.orderInfo.getCinemaName(),
                                    MainActivity.orderInfo.getMovieName(),
                                    MainActivity.orderInfo.getStartTime(),
                                    MainActivity.orderInfo.getEndTime(),
                                    finalSeats,
                                    MainActivity.userInfo.getUserName()
                            );

                    }

                }.start();
                for(int i=0;i<chooseSeatInfos.size();i++)
                    seatInfos.add(chooseSeatInfos.get(i));
                chooseSeatInfos.clear();
                seatTableView.removeAll();
                seatTableView.invalidate();

                Toast.makeText(ChoosSeatActivity.this, "购买成功", Toast.LENGTH_SHORT).show();

            }
        });
        textView= (TextView) findViewById(R.id.Seat_Movie_Name);
        textView.setText(getIntent().getStringExtra("MovieName"));

//        toolbar.setNavigationOnClickListener();
    }

    private boolean isThisSession(OrderInfo orderInfo)
    {
        if(orderInfo.getMovieName().equals(MainActivity.orderInfo.getMovieName())&&
                orderInfo.getCinemaName().equals(MainActivity.orderInfo.getCinemaName())&&
                orderInfo.getStartTime().equals(MainActivity.orderInfo.getStartTime()))
            return true;
        return false;
    }

    private void getOrderInfos()
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                orderInfos=new ArrayList<OrderInfo>();

                Log.e("asd","读了");
                ArrayList<ArrayList>allOrderInfo=IndentMgr.get();
                if(allOrderInfo==null)
                    return;
                Log.e("asd","读了"+(allOrderInfo==null));
                    for(int i=0;i<allOrderInfo.size();i++)
                    {
                        ArrayList<Object>temp=allOrderInfo.get(i);
                        OrderInfo orderInfo=new OrderInfo();
                   //     orderInfo.setOrderId((Integer) temp.get(0));
                        orderInfo.setTicketNumber((String) temp.get(1));
                        orderInfo.setPrice((Float) temp.get(2));
                        orderInfo.setBornTime((String) temp.get(3));
                        orderInfo.setCinemaName((String) temp.get(4));
                        orderInfo.setMovieName((String) temp.get(5));
                        orderInfo.setStartTime((String) temp.get(6));
                        orderInfo.setEndTime((String) temp.get(7));
                        ArrayList<SeatInfo>seatInfos=new ArrayList<>();
                        String seats=(String) temp.get(8);
                        Log.e("座位信息",seats+"---");
                        String[]eachSeat=seats.split(",");
                        for(int j=0;j<eachSeat.length;j++)
                        {
                            String[]oneSeat=eachSeat[j].split(" ");
                            SeatInfo seatInfo=new SeatInfo(Integer.parseInt(oneSeat[0]),Integer.parseInt(oneSeat[1]));
                            seatInfos.add(seatInfo);
                        }
                        orderInfo.setSeatInfos(seatInfos);
                        orderInfo.setUserName((String) temp.get(9));
                        orderInfos.add(orderInfo);
                    }
                    handler.sendMessage(new Message());

            }
        }.start();
    }
}
