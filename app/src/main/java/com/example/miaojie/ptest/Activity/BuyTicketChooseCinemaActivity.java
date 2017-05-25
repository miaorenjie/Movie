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
    private String movieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyticket_choose_cinema_activity);
        movieName=getIntent().getStringExtra("MovieName");
        initCinemaInfo();
        recyclerView= (RecyclerView) findViewById(R.id.main_choose_cinema_recyclerView);
        ChooseCinemaAdapter adapter=new ChooseCinemaAdapter(this,cinemaInfos);
        adapter.setListener(new RecyclerViewItemOnClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position=recyclerView.getChildAdapterPosition(view);
                MainActivity.orderInfo.setCinemaName(cinemaInfos.get(position).getCinema_Name());
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

        CinemaInfo cinemaInfo=new CinemaInfo();
        cinemaInfo.setCinema_Name("��˹����������Ӱ��");
        cinemaInfo.setCinemaAdress("��ϸ��ַ����������������105�ų�������㳡105��A��2¥");
        cinemaInfo.setRecentlySession("10:00");
        cinemaInfos.add(cinemaInfo);

        CinemaInfo cinemaInfo1=new CinemaInfo();
        cinemaInfo1.setCinema_Name("������˹������Ӱ�ǣ�������������ص꣩");
        cinemaInfo1.setCinemaAdress("��ϸ��ַ�����������������븮��һ·����ڶ��ϽǸ�һ��");
        cinemaInfo1.setRecentlySession("14:00");
        cinemaInfos.add(cinemaInfo1);

        CinemaInfo cinemaInfo2=new CinemaInfo();
        cinemaInfo2.setCinema_Name("��ӰJMS����Ӱ����ŵ�");
        cinemaInfo2.setCinemaAdress("�������μ·3333�Ż�������㳡��¥");
        cinemaInfo2.setRecentlySession("16:00");
        cinemaInfos.add(cinemaInfo2);

        CinemaInfo cinemaInfo3=new CinemaInfo();
        cinemaInfo3.setCinema_Name("asd");
        cinemaInfo3.setCinemaAdress("asd");
        cinemaInfo3.setRecentlySession("asd");
        cinemaInfos.add(cinemaInfo3);

//        CinemaInfo cinemaInfo=new CinemaInfo();
//        cinemaInfo.setCinema_Name("asd");
//        cinemaInfo.setCinemaAdress("asd");
//        cinemaInfo.setRecentlySession("asd");
//        cinemaInfos.add(cinemaInfo);

    }
}
