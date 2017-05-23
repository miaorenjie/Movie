package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.miaojie.ptest.R;

/**
 * Created by miaojie on 2017/5/22.
 */

public class OrderInfoActivity extends Activity {
    private TextView textView;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyticket_choose_time_activity);
        textView= (TextView) findViewById(R.id.title);
        textView.setText("订单信息");
        recyclerView= (RecyclerView) findViewById(R.id.main_choose_time_recyclerView);

    }
}
