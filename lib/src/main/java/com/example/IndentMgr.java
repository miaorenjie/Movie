package com.example;

import java.util.ArrayList;

public class IndentMgr {

	public static final String ID="id";
	public static final String TICKET="tickets";
	public static final String MONEY="money";
	public static final String TIME="time";
	
	public static final String FROM="indent";

	public static void add(String tickets,float money,String time,String studio_name,String move_name,String start,String end,String seats,String usrname)
	{
		BridgeNative.send(IndentMgr.class.getName(),"add",tickets,money,time,studio_name,move_name,start,end,seats,usrname);
	}
	
	public static ArrayList<ArrayList> get()
	{
		return (ArrayList<ArrayList>) BridgeNative.send(IndentMgr.class.getName(),"get");
	}

}
