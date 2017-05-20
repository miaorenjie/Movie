package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.SeatTable;

/**
 * Created by miaojie on 2017/3/22.
 */

public class ChoosSeatActivity extends AppCompatActivity {
    private SeatTable seatTableView;
    private Toolbar toolbar;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_seat_activity);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(1000);//设置最多选中

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
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

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

        textView= (TextView) findViewById(R.id.Seat_Movie_Name);
        textView.setText(getIntent().getStringExtra("MovieName"));

//        toolbar.setNavigationOnClickListener();
    }
}
