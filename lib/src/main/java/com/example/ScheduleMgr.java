package com.example;

import java.util.ArrayList;

public class ScheduleMgr {

	private static final int default_studio=6;
	
	public static final String ID="sched_id";
	public static final String STUDIO="studio_id";
	public static final String PLAY="play_id";
	public static final String TIME="sched_time";
	public static final String PRICE="sched_ticket_price";
	
	public static final String FROM="schedule";

	public static void add(int play,String time,float price)
	{
		add(default_studio, play, time,price);
	}
	
	public static void add(int studio,int play,String time,float price)
	{
		BridgeNative.send(ScheduleMgr.class.getName(),"add",studio,play,time,price);
	}
	
	public static ArrayList<ArrayList> get()
	{
		return (ArrayList<ArrayList>) BridgeNative.send(ScheduleMgr.class.getName(),"get");
	}
	
	public static void delete(int i)
	{
		BridgeNative.send(ScheduleMgr.class.getName(),"delete",i);
	}


}
