package com.example.miaojie.ptest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.miaojie.ptest.Activity.PersonalInfoActivity;
import com.example.miaojie.ptest.Adapter.CinemaAdapter;
import com.example.miaojie.ptest.Adapter.StudentAdapter;
import com.example.miaojie.ptest.bean.CinemaInfo;
import com.example.miaojie.ptest.bean.StudentInfo;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.Utils.RecycleViewDivider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by miaojie on 2017/3/22.
 */

public class CinemaFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private ArrayList<CinemaInfo>list;
    private String AllInfoUrl="https://join.xiyoumobile.com/api/all?callback=jQuery31105390785517085479_1490953604487&mobile=xiyou3gfz155**%2F%2F&_=1490953604509";
    private HashMap<String,StudentInfo>Android;
    private HashMap<String,StudentInfo>iOS;
    private HashMap<String,StudentInfo>Web;

    private ArrayList<StudentInfo>AndroidList=new ArrayList<>();
    private ArrayList<StudentInfo>iOSList=new ArrayList<>();
    private ArrayList<StudentInfo>WebList=new ArrayList<>();
    private ArrayList<StudentInfo>SearchList=new ArrayList<>();
    private Handler handler;
    private Button bt_Android,bt_iOS,bt_Web;

    private SearchView searchView;

    private StudentAdapter adapter;

    //当前选中组的信息：
    private final int FLAG_ANDROID=1;
    private final int FLAG_IOS=2;
    private final int FLAG_WEB=3;
    private int choose_flag=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cinema_fragment,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.Cinema_List);
        bt_Android= (Button) view.findViewById(R.id.bt_Android);
        bt_iOS= (Button) view.findViewById(R.id.bt_iOS);
        bt_Web= (Button) view.findViewById(R.id.bt_Web);
        searchView= (SearchView) view.findViewById(R.id.Student_SearchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.e("asd",newText);
//                SearchList.clear();
//                switch (choose_flag)
//                {
//                    case FLAG_ANDROID:
//                        Log.e("asd","FLAG_ANDROID");
//                        for(int i=0;i<AndroidList.size();i++)
//                        {
//                            if (AndroidList.get(i).getStudentName().contains(newText)||
//                                    AndroidList.get(i).getStudentNum().contains(newText)||
//                                    AndroidList.get(i).getStudentState().contains(newText)||
//                                    AndroidList.get(i).getStudentSex().contains(newText))
//                            {
//                                SearchList.add(AndroidList.get(i));
//                                Log.e("asd","setList"+SearchList.size());
//                            }
//
//
//                        }
//                        if (SearchList.size()>0||newText.length()>0)
//                        {
//                            Log.e("asd","setList"+SearchList.size());
//                            adapter.setList(SearchList);
//                            recyclerView.setAdapter(adapter);
//                        }
//                        if(newText.length()==0)
//                        {
//                            adapter.setList(AndroidList);
//                            recyclerView.setAdapter(adapter);
//                        }
//
//                        break;
//                    case FLAG_IOS:
//                        for(int i=0;i<iOSList.size();i++)
//                        {
//                            if (iOSList.get(i).getStudentName().contains(newText)||
//                                    iOSList.get(i).getStudentNum().contains(newText)||
//                                    iOSList.get(i).getStudentState().contains(newText)||iOSList.get(i).getStudentSex().contains(newText))
//                                SearchList.add(iOSList.get(i));
//
//                        }
//                        if (SearchList.size()>0||newText.length()>0)
//                        {
//                            adapter.setList(SearchList);
//                            recyclerView.setAdapter(adapter);
//                        }
//                        if(newText.length()==0)
//                        {
//                            adapter.setList(iOSList);
//                            recyclerView.setAdapter(adapter);
//                        }
//                        break;
//                    case FLAG_WEB:
//                        for(int i=0;i<WebList.size();i++)
//                        {
//                            if (WebList.get(i).getStudentName().contains(newText)||
//                                    WebList.get(i).getStudentNum().contains(newText)||
//                                    WebList.get(i).getStudentState().contains(newText)||
//                                    WebList.get(i).getStudentSex().contains(newText))
//                                SearchList.add(WebList.get(i));
//
//                        }
//                        if (SearchList.size()>0||newText.length()>0)
//                        {
//                            adapter.setList(SearchList);
//                            recyclerView.setAdapter(adapter);
//                        }
//                        if(newText.length()==0)
//                        {
//                            adapter.setList(WebList);
//                            recyclerView.setAdapter(adapter);
//                        }
//                        break;
//
//                }
//
//
//                return false;
//            }
//        });
        bt_Android.setOnClickListener(this);
        bt_iOS.setOnClickListener(this);
        bt_Web.setOnClickListener(this);



        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                Log.e("Android","人数"+AndroidList.size());
                bt_Android.setText("Android "+AndroidList.size());
                bt_iOS.setText("iOS "+iOSList.size());
                bt_Web.setText("Web "+WebList.size());
                adapter=new StudentAdapter(getContext(),AndroidList);
                recyclerView.setAdapter(adapter);
                adapter.setListener(new StudentAdapter.OnItemClickListener() {

                        @Override
                        public void OnItemClick(View view) {
                            int position=recyclerView.getChildAdapterPosition(view);
                            Bundle bundle=new Bundle();
                            if(searchView.getQuery().length()>0)
                                bundle.putSerializable("DetailedStudent",SearchList.get(position));
                            else
                                switch (choose_flag)
                                {
                                    case FLAG_ANDROID:
                                        bundle.putSerializable("DetailedStudent",AndroidList.get(position));
                                        break;
                                    case FLAG_IOS:
                                        bundle.putSerializable("DetailedStudent",iOSList.get(position));
                                        break;
                                    case FLAG_WEB:
                                        bundle.putSerializable("DetailedStudent",WebList.get(position));
                                        break;
                                }
                            Intent intent=new Intent(getContext(), PersonalInfoActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            Log.e("点击了","213");
                    }
                    });
            }
        };
        list=new ArrayList<>();
        Android=new HashMap<>();
        iOS=new HashMap<>();
        Web=new HashMap<>();

//        init();

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
//int method, String url, Listener<String> listener, ErrorListener errorListener)
        StringRequest request=new StringRequest(Request.Method.GET, AllInfoUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                s=s.substring(s.indexOf("{"),s.length()-2);
                Log.e("最后",s.charAt(s.length()-1)+"");
                solveResponse(s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("请求失败","111");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("mobile","xiyou3gfz155**//");
                Log.e("执行了","111");
                return params;
            }

            private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
                Log.e("执行了","222");
                StringBuilder encodedParams = new StringBuilder();

                try {
                    Iterator var5 = params.entrySet().iterator();

                    while(var5.hasNext()) {
                        java.util.Map.Entry uee = (java.util.Map.Entry)var5.next();
                        encodedParams.append(URLEncoder.encode((String)uee.getKey(), paramsEncoding));
                        encodedParams.append('=');
                        encodedParams.append(URLEncoder.encode((String)uee.getValue(), paramsEncoding));
                        encodedParams.append('&');
                    }
                   String result= encodedParams.substring(0,encodedParams.length()-2);
                    Log.e("post Body",result);
                    return result.getBytes(paramsEncoding);
                } catch (UnsupportedEncodingException var6) {
                    throw new RuntimeException("Encoding not supported: " + paramsEncoding, var6);
                }
            }
        };
        requestQueue.add(request);
        CinemaAdapter adapter=new CinemaAdapter(getContext(),list);
       //recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecycleViewDivider(getContext(),RecyclerView.HORIZONTAL));

        return view;
    }

    private void solveResponse(String s)
    {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject student=jsonArray.getJSONObject(i);


                if(student.getString("group").equals("Web"))
                {
                    StudentInfo info=new StudentInfo();
                    info.setStudentName(student.getString("name"));
                    info.setStudentNum(student.getString("num"));
                    if(student.has("sex"))
                            info.setStudentSex(student.getString("sex"));
                    if(student.has("class"))
                            info.setStudentClass(student.getString("class"));
                    if(student.has("state"))
                        info.setStudentState(student.getString("state"));
                    info.setStudentTel(student.getString("tel"));
                    info.setStudentGroup(student.getString("group"));
                    info.setStudentMessage(student.getString("message"));
                    info.setStudentMail(student.getString("mail"));

                    Web.put(info.getStudentNum(),info);
                }

                if(student.getString("group").equals("Android"))
                {
                    StudentInfo info=new StudentInfo();
                    info.setStudentName(student.getString("name"));
                    info.setStudentNum(student.getString("num"));
                    if(student.has("sex"))
                        info.setStudentSex(student.getString("sex"));
                    if(student.has("class"))
                        info.setStudentClass(student.getString("class"));
                    if(student.has("state"))
                        info.setStudentState(student.getString("state"));
                    info.setStudentTel(student.getString("tel"));
                    info.setStudentGroup(student.getString("group"));
                    info.setStudentMessage(student.getString("message"));
                    info.setStudentMail(student.getString("mail"));
                    Android.put(info.getStudentNum(),info);
                }

                if(student.getString("group").equals("iOS"))
                {
                    StudentInfo info=new StudentInfo();
                    info.setStudentName(student.getString("name"));
                    info.setStudentNum(student.getString("num"));
                    if(student.has("sex"))
                        info.setStudentSex(student.getString("sex"));
                    if(student.has("class"))
                        info.setStudentClass(student.getString("class"));
                    if(student.has("state"))
                        info.setStudentState(student.getString("state"));
                    info.setStudentTel(student.getString("tel"));
                    info.setStudentGroup(student.getString("group"));
                    info.setStudentMessage(student.getString("message"));
                    info.setStudentMail(student.getString("mail"));

                    iOS.put(info.getStudentNum(),info);
                }
            }

            Object[] aaa=  Android.values().toArray();
            for(int i=0;i<aaa.length;i++)
            {
                AndroidList.add((StudentInfo) aaa[i]);
            }

            Object[] bbb=  iOS.values().toArray();
            for(int i=0;i<bbb.length;i++)
            {
                iOSList.add((StudentInfo) bbb[i]);
            }
            Object[] ccc=  Web.values().toArray();
            for(int i=0;i<ccc.length;i++)
            {
                WebList.add((StudentInfo) ccc[i]);
            }

            Log.e("Android","人数"+AndroidList.get(0).getStudentName());
            Log.e("iOS","人数"+iOS.size());
            Log.e("Web","人数"+Web.size());
            handler.sendMessage(new Message());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void init()
    {
        CinemaInfo cinemaInfo=new CinemaInfo();
        cinemaInfo.setCinema_Name("奥斯卡长安国际影城");
        cinemaInfo.setCinema_Introduce("详细地址：长安区西长安街105号长安中央广场105号A座2楼");

        list.add(cinemaInfo);


        CinemaInfo cinemaInfo2=new CinemaInfo();
        cinemaInfo2.setCinema_Name("西安奥斯卡国际影城（长安城南新天地店）");
        cinemaInfo2.setCinema_Introduce("详细地址：长安区西长安街与府东一路交叉口东南角负一层");

        list.add(cinemaInfo2);

        CinemaInfo cinemaInfo3=new CinemaInfo();
        cinemaInfo3.setCinema_Name("中影JMS国际影城灞桥店");
        cinemaInfo3.setCinema_Introduce("灞桥区纺渭路3333号华东购物广场三楼");

        list.add(cinemaInfo3);

        CinemaInfo cinemaInfo4=new CinemaInfo();
        cinemaInfo4.setCinema_Name("奥斯卡长安国际影城");
        cinemaInfo4.setCinema_Introduce("详细地址：长安区西长安街105号长安中央广场105号A座2楼");

        list.add(cinemaInfo4);


        CinemaInfo cinemaInfo5=new CinemaInfo();
        cinemaInfo5.setCinema_Name("西安奥斯卡国际影城（长安城南新天地店）");
        cinemaInfo5.setCinema_Introduce("详细地址：长安区西长安街与府东一路交叉口东南角负一层");

        list.add(cinemaInfo5);

        CinemaInfo cinemaInfo6=new CinemaInfo();
        cinemaInfo6.setCinema_Name("中影JMS国际影城灞桥店");
        cinemaInfo6.setCinema_Introduce("灞桥区纺渭路3333号华东购物广场三楼");

        list.add(cinemaInfo6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_Android:
                adapter.setList(AndroidList);
//                adapter=new StudentAdapter(getContext(),AndroidList);
                recyclerView.setAdapter(adapter);
                choose_flag=FLAG_ANDROID;
                searchView.clearFocus();
                searchView.setQuery("",false);
                break;
            case R.id.bt_iOS:
                adapter.setList(iOSList);
//                adapter=new StudentAdapter(getContext(),iOSList);
                recyclerView.setAdapter(adapter);
                choose_flag=FLAG_IOS;
                searchView.clearFocus();
                searchView.setQuery("",false);
                break;
            case R.id.bt_Web:
                adapter.setList(WebList);
//                adapter=new StudentAdapter(getContext(),WebList);
                recyclerView.setAdapter(adapter);
                choose_flag=FLAG_WEB;
                searchView.clearFocus();
                searchView.setQuery("",false);
                break;
        }
    }
}
