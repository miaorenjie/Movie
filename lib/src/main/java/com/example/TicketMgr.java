package com.example;

import java.util.ArrayList;

public class TicketMgr {
	
	public static final String ID="ticket_id";
	public static final String SCHED="sched_id";
	public static final String PRICE="ticket_price";
	public static final String STATUS="ticket_status";
	public static final String TIME="ticket_locked_time";
	public static final String ROW="row";
	public static final String COL="col";
	
	public static final String FROM="ticket";
	
	private static final int DEFAULT_STATUS=1;
	public static boolean add(Integer sched,Integer price,String time,Integer row,Integer col)
	{
		return (boolean) BridgeNative.send(TicketMgr.class.getName(),"add",sched,price,time,row,col);
	}
	
	public static boolean exist(int sched,int row,int col)
	{
		return (boolean) BridgeNative.send(TicketMgr.class.getName(),"exist",sched,row,col);
	}
	
	public static void delete(int id)
	{
		BridgeNative.send(TicketMgr.class.getName(),"delete",id);
	}
	
	public static ArrayList<ArrayList> get()
	{
		return (ArrayList<ArrayList>) BridgeNative.send(TicketMgr.class.getName(),"get");
	}

}
