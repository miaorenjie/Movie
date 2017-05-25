package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.miaojie.ptest.Adapter.OrderAdapter;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;

/**
 * Created by miaojie on 2017/5/22.
 */

public class OrderInfoActivity extends Activity {
    private TextView textView;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyticket_choose_time_activity);
        textView= (TextView) findViewById(R.id.title);
        textView.setText("∂©µ•–≈œ¢");
        recyclerView= (RecyclerView) findViewById(R.id.main_choose_time_recyclerView);
        orderAdapter=new OrderAdapter(this);
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this,RecyclerView.HORIZONTAL));
    }
}
