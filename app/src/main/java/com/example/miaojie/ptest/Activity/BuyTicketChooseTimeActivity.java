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
                MainActivity.orderInfo.setStartTime(movieSessions.get(position).getStartTime());
                MainActivity.orderInfo.setEndTime(movieSessions.get(position).getEndTime());
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

        MovieSession movieSession=new MovieSession();
        movieSession.setStartTime("10:05");
        movieSession.setEndTime("12:25");
        movieSession.setStudioId("7Ìü");
        movieSessions.add(movieSession);

        MovieSession movieSession1=new MovieSession();
        movieSession1.setStartTime("11:25");
        movieSession1.setEndTime("13:45");
        movieSession1.setStudioId("8Ìü");
        movieSessions.add(movieSession1);

        MovieSession movieSession2=new MovieSession();
        movieSession2.setStartTime("12:35");
        movieSession2.setEndTime("14:55");
        movieSession2.setStudioId("7Ìü");
        movieSessions.add(movieSession2);

        MovieSession movieSession3=new MovieSession();
        movieSession3.setStartTime("13:55");
        movieSession3.setEndTime("16:15");
        movieSession3.setStudioId("8Ìü");
        movieSessions.add(movieSession3);

        MovieSession movieSession4=new MovieSession();
        movieSession4.setStartTime("15:05");
        movieSession4.setEndTime("17:25");
        movieSession4.setStudioId("4Ìü");
        movieSessions.add(movieSession4);

        MovieSession movieSession5=new MovieSession();
        movieSession5.setStartTime("16:25");
        movieSession5.setEndTime("18:45");
        movieSession5.setStudioId("5Ìü");
        movieSessions.add(movieSession5);

        MovieSession movieSession6=new MovieSession();
        movieSession6.setStartTime("17:35");
        movieSession6.setEndTime("19:55");
        movieSession6.setStudioId("4Ìü");
        movieSessions.add(movieSession6);

        MovieSession movieSession7=new MovieSession();
        movieSession7.setStartTime("18:55");
        movieSession7.setEndTime("21:15");
        movieSession7.setStudioId("5Ìü");
        movieSessions.add(movieSession7);

        MovieSession movieSession8=new MovieSession();
        movieSession8.setStartTime("20:10");
        movieSession8.setEndTime("22:30");
        movieSession8.setStudioId("4Ìü");
        movieSessions.add(movieSession8);

        MovieSession movieSession9=new MovieSession();
        movieSession9.setStartTime("21:25");
        movieSession9.setEndTime("23:45");
        movieSession9.setStudioId("5Ìü");
        movieSessions.add(movieSession9);

        MovieSession movieSession10=new MovieSession();
        movieSession10.setStartTime("22:40");
        movieSession10.setEndTime("01:00");
        movieSession10.setStudioId("4Ìü");
        movieSessions.add(movieSession10);
    }
}
