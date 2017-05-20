package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.miaojie.ptest.Adapter.ChooseCinemaAdapter;
import com.example.miaojie.ptest.Adapter.RecyclerViewItemOnClickListener;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;
import com.example.miaojie.ptest.bean.CinemaInfo;
import com.example.miaojie.ptest.bean.MovieInfo;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/19.
 */

public class BuyTicketChooseCinemaActivity extends Activity {
    private RecyclerView recyclerView;
    private ArrayList<CinemaInfo>cinemaInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyticket_choose_cinema_activity);
        initCinemaInfo();
        recyclerView= (RecyclerView) findViewById(R.id.main_choose_cinema_recyclerView);
        ChooseCinemaAdapter adapter=new ChooseCinemaAdapter(this,cinemaInfos);
        adapter.setListener(new RecyclerViewItemOnClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position=recyclerView.getChildAdapterPosition(view);
                Intent intent=new Intent(BuyTicketChooseCinemaActivity.this,BuyTicketChooseTimeActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this,RecyclerView.HORIZONTAL));

    }

    private void initCinemaInfo()
    {
        cinemaInfos=new ArrayList<>();

        for(int i=0;i<10;i++)
        {
            CinemaInfo cinemaInfo=new CinemaInfo();
            cinemaInfo.setCinema_Name("asd");
            cinemaInfo.setCinemaAdress("asd");
            cinemaInfo.setRecentlySession("asd");
            cinemaInfos.add(cinemaInfo);
        }


    }
}
