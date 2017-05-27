package com.example.miaojie.ptest.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miaojie.ptest.Adapter.BBSAdapter;
import com.example.miaojie.ptest.R;

/**
 * Created by user on 2017/5/25.
 */

public class BBSFragment extends Fragment {

    RecyclerView rv;
    EditText edit;
    Button send;
    BBSAdapter ada;
    public static String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.bbs_content,null,false);
        rv=(RecyclerView)root.findViewById(R.id.bbs);
        ada=new BBSAdapter(getActivity(),rv,name);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(ada);
        edit=(EditText) root.findViewById(R.id.edit);
        send=(Button)root.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.equals(""))
                {
                    Toast.makeText(getContext(),"Î´µÇÂ¼£¬ÇëÏÈµÇÂ¼",Toast.LENGTH_SHORT).show();
                    return;
                }
                ada.add(edit.getText().toString(),name);
                edit.setText("");
            }
        });
        return root;
    }
}
