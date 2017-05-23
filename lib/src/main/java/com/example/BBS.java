package com.example;


import java.util.ArrayList;

public class BBS {

	public static final String NAME="name";
	public static final String USR="usrname";
	public static final String PWD="passwd";
	public static final String CONTENT="content";
	public static final String TIME="time";
	public static final String INDEX="num";
	
	public static final String FROMU="bbsuser";
	public static final String FROM="bbs";
	
	public static final int LOGIN_OK=1;
	public static final int USR_EXIST=2;
	public static final int NAME_EXIST=3;
	public static final int NONE=0;
	public static final int REGISTER_OK=4;
	
	public static int register(String usrname,String passwd,String name)
	{
		return (int) BridgeNative.send(BBS.class.getName(),"register",usrname,passwd,name);
	}
	
	public static int exist(String usr,String name)
	{
		return (int)BridgeNative.send(BBS.class.getName(),"exist",usr,name);
	}
	
	public static String login(String usrname,String passwd)
	{
		return (String)BridgeNative.send(BBS.class.getName(),"exist",usrname,passwd);
	}
	
	public static boolean send(String name,String content,String time)
	{
		return (boolean)BridgeNative.send(BBS.class.getName(),"send",name,content,time);
	}
	
	public static ArrayList<ArrayList> get()
	{
		return (ArrayList<ArrayList>) BridgeNative.send(BBS.class.getName(),"get");
	}
	
	public static void delete(int index)
	{
		BridgeNative.send(BBS.class.getName(),"delete",index);
	}
	
}
