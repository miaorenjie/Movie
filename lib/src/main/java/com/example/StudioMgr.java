package com.example;

import java.util.ArrayList;

public class StudioMgr {
	
	public static final String NAME="studio_name";
	public static final String ROW="studio_row_count";
	public static final String COL="studio_col_count";
	public static final String INTRO="studio_introduction";

	public static final String FROM="studio";


	public static void add(String name,int r,int c,String intro)
	{
		BridgeNative.send(StudioMgr.class.getName(),"add",name,r,c,intro);
	}
	
	public static boolean exist(String name)
	{
		return (boolean) BridgeNative.send(StudioMgr.class.getName(),"exist",name);
	}
	
	public static ArrayList<ArrayList> get()
	{
		return (ArrayList<ArrayList>) BridgeNative.send(StudioMgr.class.getName(),"get");
	}

}
