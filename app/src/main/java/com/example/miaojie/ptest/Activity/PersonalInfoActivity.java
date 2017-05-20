package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.miaojie.ptest.bean.StudentInfo;
import com.example.miaojie.ptest.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by miaojie on 2017/3/30.
 */

public class PersonalInfoActivity extends Activity implements View.OnClickListener{
    private StudentInfo studentInfo;

    private TextView StudentNum;
    private TextView StudentName;
    private TextView StudentClass;
    private TextView StudentSex;
    private TextView StudentMessage;
    private TextView StudentMail;
    private TextView StudentTel;
    private TextView StudentGroup;
    private TextView StudentState;

    private final String UpdateUrl="https://join.xiyoumobile.com/api/update";

    private Button Student_Pass_One;
    private Button Student_Pass_Two;
    private Button Student_Pass_Three;
    private Button Student_Out;
    private Button Student_Give_Notice;
    private Handler handler;
    private RequestQueue requestQueue;
    private int state;

    private String Mail_Subject;
    private String Mail_Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                StudentMessage.setText((String) msg.obj);
            }
        };
       studentInfo= (StudentInfo) getIntent().getExtras().get("DetailedStudent");

        init();
        setData();
    }

    private void init()
    {
        requestQueue= Volley.newRequestQueue(PersonalInfoActivity.this);
        StudentNum= (TextView) findViewById(R.id.Student_Num);
        StudentName= (TextView) findViewById(R.id.Student_Name);
        StudentClass= (TextView) findViewById(R.id.Student_Class);
        StudentSex= (TextView) findViewById(R.id.Student_Sex);
        StudentMessage= (TextView) findViewById(R.id.Student_Message);
        StudentMail= (TextView) findViewById(R.id.Student_Mail);
        StudentTel= (TextView) findViewById(R.id.Student_Tel);
        StudentState= (TextView) findViewById(R.id.Student_State);

        Student_Pass_One= (Button) findViewById(R.id.Student_Pass_One);
        Student_Pass_Two=(Button) findViewById(R.id.Student_Pass_Two);
        Student_Pass_Three=(Button) findViewById(R.id.Student_Pass_Three);
        Student_Out=(Button) findViewById(R.id.Student_Out);
        Student_Give_Notice=(Button) findViewById(R.id.Student_Give_Notice);


        Student_Pass_One.setOnClickListener(this);
        Student_Pass_Two.setOnClickListener(this);
        Student_Pass_Three.setOnClickListener(this);
        Student_Out.setOnClickListener(this);
        Student_Give_Notice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isclick=false;

        switch (v.getId())
        {
            case R.id.Student_Pass_One:
                isclick=true;
                state=1;
                Log.e("点击了","Student_Pass_One");
                break;
            case R.id.Student_Pass_Two:
                isclick=true;
                state=2;
                Log.e("点击了","Student_Pass_Two");
                break;
            case R.id.Student_Pass_Three:
                state=3;
                isclick=true;
                Log.e("点击了","Student_Pass_Three");
                break;
            case R.id.Student_Out:
                state=0;
                isclick=true;
                Log.e("点击了","Student_Out");
                break;
            case R.id.Student_Give_Notice:
                state=4;
                isclick=true;
                break;
        }
        if(isclick) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(PersonalInfoActivity.this);
            dialog.setTitle("提示").
                    setMessage("确认不是误操作吗！").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //学号（num）、姓名（name）、学号（num）、电话（tel）、留言（message）、邮箱（mail）、方向（group）、状态（state）(规定状态分为undefined、未面试、一面通过、二面通过、三面通过、已录取、已发光)

                    String finalUrl=null;
                    switch (state)
                    {
                        case 1:
                            finalUrl="https://join.xiyoumobile.com/api/update?callback=jQuery31106613778265835957_1490969690028&num="+studentInfo.getStudentNum()+"&newData%5Bstate%5D=%E4%B8%80%E9%9D%A2%E9%80%9A%E8%BF%87&mobile=xiyou3gfz155**%2F%2F&_=1490969690030";
                            break;
                        case 2:
                            finalUrl="https://join.xiyoumobile.com/api/update?callback=jQuery31106613778265835957_1490969690028&num="+studentInfo.getStudentNum()+"&newData%5Bstate%5D=%E4%BA%8C%E9%9D%A2%E9%80%9A%E8%BF%87&mobile=xiyou3gfz155**%2F%2F&_=1490969690031";
                            break;
                        case 3:
                            finalUrl="https://join.xiyoumobile.com/api/update?callback=jQuery31106613778265835957_1490969690028&num="+studentInfo.getStudentNum()+"&newData%5Bstate%5D=%E5%B7%B2%E5%BD%95%E5%8F%96&mobile=xiyou3gfz155**%2F%2F&_=1490969690033";
                            break;
                        case 0:
                            finalUrl="https://join.xiyoumobile.com/api/update?callback=jQuery31106613778265835957_1490969690028&num="+studentInfo.getStudentNum()+"&newData%5Bstate%5D=%E5%B7%B2%E5%8F%91%E5%85%89&mobile=xiyou3gfz155**%2F%2F&_=1490969690034";
                            break;
                        case 4:
                            Mail_Subject="asd";
                            Mail_Text="zxc";
                            finalUrl="https://join.xiyoumobile.com/api/mail?callback=jQuery31106613778265835957_1490969690028&to="+studentInfo.getStudentMail()+"&subject="+Mail_Subject+"&text="+Mail_Text+"&mobile=xiyou3gfz155**%2F%2F&_=1490969690035";
                            break;
                    }
                    Log.e("点击了",finalUrl);

                    StringRequest request=new StringRequest(Request.Method.GET,finalUrl, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String s) {

                            Log.e("Update",s);
                            Message message=new Message();
                            message.obj=s;
                            handler.sendMessage(message);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("Update","失败");
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>map=new HashMap<String, String>();
//                            map.put("num",studentInfo.getStudentNum());
//                            map.put("state",state);
                            map.put("mobile","xiyou3gfz155**//");
                            return map;
                        }


                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            Map postParams= getParams();
                            return postParams != null && postParams.size() > 0?encodeParameters(postParams, this.getPostParamsEncoding()):null;
                        }


                        public byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
                            Log.e("执行了","222");
                            StringBuilder encodedParams = new StringBuilder();

                            try {
                                Iterator var5 = params.entrySet().iterator();

                                while(var5.hasNext()) {
                                    Map.Entry uee = (Map.Entry)var5.next();
                                    encodedParams.append(URLEncoder.encode((String)uee.getKey(), paramsEncoding));
                                    encodedParams.append('=');
                                    encodedParams.append(URLEncoder.encode((String)uee.getValue(), paramsEncoding));
                                    encodedParams.append('&');
                                }
                                Log.e("yuanBody",encodedParams.toString());
                                String result= encodedParams.substring(0,encodedParams.length()-1);
                                Log.e("post Body",result);
                                return result.getBytes(paramsEncoding);
                            } catch (UnsupportedEncodingException var6) {
                                throw new RuntimeException("Encoding not supported: " + paramsEncoding, var6);
                            }
                        }
                    };
                    requestQueue.add(request);
                }
            }).setNegativeButton("不确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        }
    }

    private String getUrl(Map<String,String> params) {
        StringBuilder encodedParams = new StringBuilder(UpdateUrl);


        Iterator var5 = params.entrySet().iterator();
        boolean isfirst=true;
        while(var5.hasNext()) {
            java.util.Map.Entry uee = (java.util.Map.Entry)var5.next();
            if(isfirst)
                encodedParams.append('?');
            encodedParams.append(URLEncoder.encode((String)uee.getKey()));
            encodedParams.append('=');
            encodedParams.append(URLEncoder.encode((String)uee.getValue()));
            encodedParams.append('&');
            isfirst=false;
        }
        Log.e("yuanBody",encodedParams.toString());
        String result= encodedParams.substring(0,encodedParams.length()-1);
        Log.e("post Body",result);
        return result;
    }

    private void setData()
    {
        StudentNum.setText(studentInfo.getStudentNum());
        StudentName.setText(studentInfo.getStudentName());
        StudentClass.setText(studentInfo.getStudentClass());
        StudentSex.setText(studentInfo.getStudentSex());
        StudentMessage.setText(studentInfo.getStudentMessage());
        StudentMail.setText(studentInfo.getStudentMail());
        StudentTel.setText(studentInfo.getStudentTel());
        StudentState.setText(studentInfo.getStudentState());
    }


}
