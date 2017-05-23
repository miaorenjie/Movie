package com.example.miaojie.ptest.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_seat_activity);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(1000);//设置最多选中
        seatInfos=new ArrayList<>();
        orderInfos=getOrderInfos();
        chooseSeatInfos=new ArrayList<>();
        for(int i=0;i<orderInfos.size();i++)
        {
            if(isThisSession(orderInfos.get(i)))
            {
                for(int j=0;j<orderInfos.get(i).getSeatInfos().size();i++)
                    seatInfos.add(orderInfos.get(i).getSeatInfos().get(j));
            }
        }
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                for(int i=0;i<seatInfos.size();i++)
                {
                    SeatInfo seatInfo=seatInfos.get(i);
                    if(seatInfo.getSeatY()==row&&seatInfo.getSeatX()==column)
                        return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
               chooseSeatInfos.add(new SeatInfo(column,row));
            }

            @Override
            public void unCheck(int row, int column) {
                int i;
                for(i=0;i<chooseSeatInfos.size();i++)
                {
                    SeatInfo seatInfo=chooseSeatInfos.get(i);
                    if(seatInfo.getSeatY()==row&&seatInfo.getSeatX()==column)
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
                MainActivity.orderInfo.setSeatInfos(chooseSeatInfos);
                MainActivity.orderInfo.setTicketNumber(chooseSeatInfos.size());
                Date date=new Date();
                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now=simpleDateFormat.format(date);
                MainActivity.orderInfo.setBornTime(now);
                OrderInfo orderInfo= MainActivity.orderInfo;
                Log.e("asd",MainActivity.orderInfo.toString());
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
                            try {
                                IndentMgr.add(MainActivity.orderInfo.getTicketNumber()+"",
                                        MainActivity.orderInfo.getPrice(),
                                        MainActivity.orderInfo.getBornTime(),
                                        MainActivity.orderInfo.getCinemaName(),
                                        MainActivity.orderInfo.getMovieName(),
                                        MainActivity.orderInfo.getStartTime(),
                                        MainActivity.orderInfo.getEndTime(),
                                        finalSeats,
                                        "123"
                                );
                            } catch (BridgeNative.DataCrashException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();


            }
        });
        textView= (TextView) findViewById(R.id.Seat_Movie_Name);
//        textView.setText(getIntent().getStringExtra("MovieName"));

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

    private ArrayList<OrderInfo> getOrderInfos()
    {
        return new ArrayList<>();
    }
}
