package com.example.MySqlUtil;

import com.example.BridgeNative;

import java.util.ArrayList;

/**
 * Created by user on 2017/5/18.
 */

public class PlayMgr {
    public static final String ID="play_id";
    public static final String TYPE="play_type_id";
    public static final String LANG="play_lang_id";
    public static final String NAME="play_name";
    public static final String INTRO="play_introduction";
    public static final String IMG="play_image";
    public static final String LEN="play_length";
    public static final String PRICE="play_ticket_price";
    public static final String STATUS="play_status";

    public static final String FROM="play";

    public static ArrayList<ArrayList> get()
    {
        return (ArrayList<ArrayList>) BridgeNative.send(PlayMgr.class.getName(),"get",ID);
    }

    public static void add(String name,String intro,int len,Float price)
    {
        BridgeNative.send(PlayMgr.class.getName(),"add",name,intro,len,price);
    }

    public static void delete(String name)
    {
        BridgeNative.send(PlayMgr.class.getName(),"delete",name);
    }


}
