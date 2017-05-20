package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.miaojie.ptest.Adapter.ChooseCinemaAdapter;
import com.example.miaojie.ptest.Adapter.ChooseTimeAdapter;
import com.example.miaojie.ptest.Adapter.RecyclerViewItemOnClickListener;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;
import com.example.miaojie.ptest.bean.CinemaInfo;
import com.example.miaojie.ptest.bean.MovieSession;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/20.
 */

public class BuyTicketChooseTimeActivity extends Activity {
    private RecyclerView recyclerView;
    private ArrayList<MovieSession>movieSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyticket_choose_time_activity);
        initCinemaInfo();
        recyclerView= (RecyclerView) findViewById(R.id.main_choose_time_recyclerView);
        ChooseTimeAdapter adapter=new ChooseTimeAdapter(this,movieSessions);
        adapter.setOnClickListener(new RecyclerViewItemOnClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position=recyclerView.getChildAdapterPosition(view);
                Intent intent=new Intent(BuyTicketChooseTimeActivity.this,ChoosSeatActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        Log.e("asd","asd");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecycleViewDivider(this,RecyclerView.HORIZONTAL));
    }
    private void initCinemaInfo()
    {
        movieSessions=new ArrayList<>();

        for(int i=0;i<10;i++)
        {
            MovieSession movieSession=new MovieSession();
            movieSession.setStartTime("1111");
            movieSession.setEndTime("2222");
            movieSession.setStudioId("123");
            movieSessions.add(movieSession);
        }


    }
}
