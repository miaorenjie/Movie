package com.example.miaojie.ptest.Adapter;

/**
 * Created by miaojie on 2017/3/31.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.miaojie.ptest.bean.StudentInfo;
import com.example.miaojie.ptest.R;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/3/22.
 */

public class StudentAdapter extends RecyclerView.Adapter {
    private ArrayList<StudentInfo> list;
    private Context context;
    private StudentAdapter.OnItemClickListener listener;

    public void setList(ArrayList<StudentInfo> list) {
        this.list = list;
    }

    public StudentAdapter(Context context, ArrayList list)
    {
        this.context=context;
        this.list=list;
    }
    public StudentAdapter(Context context)
    {
        this.context=context;
    }

    public void setListener(StudentAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view= LayoutInflater.from(context).inflate(R.layout.student_item,parent,false);
//        final View view= LayoutInflater.from(context).inflate(R.layout.test_item,parent,false);
        if(listener!=null)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(view);
                }
            });
        }

        return new StudentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        StudentAdapter.MyViewHolder viewHolder= (StudentAdapter.MyViewHolder) holder;
        viewHolder.Name.setText(position+"");
        Log.e("Position",list.size()+"");
        viewHolder.Introduce.setText(list.get(position).getStudentMessage());
        viewHolder.Name.setText(list.get(position).getStudentName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView Name;
        public TextView Introduce;

        public MyViewHolder(View itemView) {
            super(itemView);
//            MovieCover= (ImageView) itemView.findViewById(R.id.Movie_Item_Img);
            Name= (TextView) itemView.findViewById(R.id.Student_Item_Name);
            Introduce= (TextView) itemView.findViewById(R.id.Student_Item_Message);
//            Protagonist= (TextView) itemView.findViewById(R.id.Movie_Item_Protagonist);
//            button_buy= (Button) itemView.findViewById(R.id.Movie_Item_Buy);
        }
    }
    public interface OnItemClickListener
    {
        void OnItemClick(View view);
    }
}
