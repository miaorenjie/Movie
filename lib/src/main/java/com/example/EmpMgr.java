package com.example;



import java.util.ArrayList;

public class EmpMgr {

	public static final String ID="emp_id";
	public static final String NO="emp_no";
	public static final String NAME="emp_name";
	public static final String TEL="emp_tel_num";
	public static final String ADDR="emp_addr";
	public static final String EMAIL="emp_email";
	
	public static final String FROM="employee";

	public static ArrayList<ArrayList> get()
	{
		return (ArrayList<ArrayList>) BridgeNative.send(EmpMgr.class.getName(),"get");
	}
	
	public static void add(String name)
	{
		BridgeNative.send(EmpMgr.class.getName(),"add",name);
	}
	
	public static void delete(String name)
	{
		BridgeNative.send(EmpMgr.class.getName(),"delete",name);
	}


}
