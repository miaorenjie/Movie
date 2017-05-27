package com.example.miaojie.ptest.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.BBS;
import com.example.miaojie.ptest.Fragment.BBSFragment;
import com.example.miaojie.ptest.R;

import java.util.ArrayList;

/**
 * Created by user on 2017/5/24.
 */

public class BBSAdapter extends RecyclerView.Adapter implements Runnable{

    private ArrayList<Message> msgs=new ArrayList<>();
    private LayoutInflater lf;
    private int mw;
    private H h=new H();
    private RecyclerView rv;
    private String mName=null;

    public BBSAdapter(Activity c,RecyclerView rv,String name)
    {
        mName=name;
        this.rv=rv;
        mw=c.getWindowManager().getDefaultDisplay().getWidth();
        new Thread(this).start();
        lf=LayoutInflater.from(c);
    }

    public void add(String content,String name)
    {
        new Thread(){
            @Override
            public void run() {
                BBS.send(BBSFragment.name,content,"1111");
                super.run();
            }
        }.start();

        msgs.add(new Message(content,name));
        notifyItemInserted(msgs.size()-1);
        rv.smoothScrollToPosition(msgs.size()-1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=lf.inflate(R.layout.text_content,null,false);
        return new Holder(v,v.findViewById(R.id.content),v.findViewById(R.id.name));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder h=(Holder)holder;
        h.name.setText(msgs.get(position).name);
        h.content.setText(msgs.get(position).content);

        if (msgs.get(position).name.equals(BBSFragment.name))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                h.v.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                h.v.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    @Override
    public void run() {
        ArrayList<ArrayList> r= BBS.get();

        if (r==null||r.size()==0)
            return ;
        for (int i=0;i<r.size();i++)
        {
            msgs.add(new Message((String)r.get(i).get(1),(String)r.get(i).get(0)));
        }
        h.sendEmptyMessage(0);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        h.sendEmptyMessage(0);
    }

    class H extends Handler
    {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            notifyDataSetChanged();
            rv.smoothScrollToPosition(msgs.size()-1);
        }
    }

    class Message
    {
        boolean isMe=false;
        String content,name;
        int num;

        public Message(String content,String name)
        {
            this.content=content;
            this.name=name;
        }
    }

    class Holder extends RecyclerView.ViewHolder
    {

        public TextView content,name;
        public View v;

        public Holder(View itemView,View content,View name) {
            super(itemView);
            v=itemView;
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            content.getLayoutParams().width=mw/8*5;
            content.setLayoutParams(content.getLayoutParams());


            v.setPadding(10,10,10,10);
            v.setLayoutParams(v.getLayoutParams());
            this.content=(TextView)content;
            this.name=(TextView)name;
        }
    }

}
