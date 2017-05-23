package com.example.miaojie.ptest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miaojie.ptest.Activity.MainActivity;
import com.example.miaojie.ptest.R;

/**
 * Created by miaojie on 2017/3/23.
 */

public class PersonalFragment extends Fragment{
    private View view;
    private TextView userNickName;
    private ListMenuItemView userOrder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_personal,container,false);
        userNickName= (TextView) view.findViewById(R.id.me_layout_name_tips);
        userOrder= (ListMenuItemView) view.findViewById(R.id.me_layout_my_infomation);
        userOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.isLogin)
                {
                    Toast.makeText(getContext(),"未登录，请先登录",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(MainActivity.userInfo!=null)
          userNickName.setText(MainActivity.userInfo.getUserNickName());

    }
}
