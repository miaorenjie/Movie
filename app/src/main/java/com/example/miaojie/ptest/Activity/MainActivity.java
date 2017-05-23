package com.example.miaojie.ptest.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.view.WindowManager;
import android.webkit.WebView;

import com.example.miaojie.ptest.Adapter.ChooseCinemaAdapter;
import com.example.miaojie.ptest.Adapter.ChooseTimeAdapter;
import com.example.miaojie.ptest.Adapter.CinemaAdapter;
import com.example.miaojie.ptest.Adapter.RCadapter;
import com.example.miaojie.ptest.Adapter.VPadapter;
import com.example.miaojie.ptest.Fragment.CinemaFragment;
import com.example.miaojie.ptest.Fragment.MoiveListFragment;
import com.example.miaojie.ptest.Fragment.PersonalFragment;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;
import com.example.miaojie.ptest.Utils.SeatTable;
import com.example.miaojie.ptest.bean.OrderInfo;
import com.example.miaojie.ptest.bean.UserInfo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static OrderInfo orderInfo=new OrderInfo();
    public static UserInfo userInfo;
    public static boolean isLogin=false;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private SeatTable seatTableView;
    private ArrayList<Fragment>fragmentArrayList;
    private ArrayList<Integer>piclist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        setContentView(R.layout.activity_main);
//        ChooseTimeAdapter adapter=new ChooseTimeAdapter(this);
//        recyclerView= (RecyclerView) findViewById(R.id.main_choose_time_recyclerView);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new RecycleViewDivider(this,RecyclerView.HORIZONTAL));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CinemaAdapter adapter=new CinemaAdapter(this);

        adapter.setListener(new RCadapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position=recyclerView.getChildAdapterPosition(view);
                Log.e("sad",position+"");
            }
        });
//
//
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        viewPager= (ViewPager) findViewById(R.id.viewpager);


        fragmentArrayList=new ArrayList<>();
        fragmentArrayList.add(new MoiveListFragment());
        fragmentArrayList.add(new CinemaFragment());
        fragmentArrayList.add(new PersonalFragment());

        ArrayList<String> title=new ArrayList<>();
        title.add("��Ӱ");
        title.add("ӰԺ");
        title.add("������Ϣ");
        piclist=new ArrayList<>();
        piclist.add(R.mipmap.tab_movie_a);
        piclist.add(R.mipmap.cinema);
        piclist.add(R.mipmap.personal);
        VPadapter vPadapter=new VPadapter(getSupportFragmentManager(),fragmentArrayList,title,piclist,this);
        viewPager.setAdapter(vPadapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(vPadapter.getTabView(i));
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        userInfo= (UserInfo) getIntent().getSerializableExtra("userNickName");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
