package com.example.MySqlUtil;


import com.example.IndentMgr;

import java.util.ArrayList;

public class MyClass {

    public static void main(String []sssss)
    {
       IndentMgr.add("aaa",1f,"2222-01-01 00:00:00","xxx","xxx","xxx","xxx","xxx","xxx");
        p(IndentMgr.get());
        //p(Float.valueOf("1.0"));
    }

    public static void p(ArrayList o)
    {
        for (int i=0;i<o.size();i++)
        {
            p(o.get(i));
        }
        if (o.get(0) instanceof ArrayList)
        p("\n");
        else
            p(" ");
    }

    public static void p(Object o)
    {System.out.println(o);}
}
