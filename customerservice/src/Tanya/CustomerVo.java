package Tanya;

import java.util.ArrayList;

public class CustomerVo {
	String name;
	String password;
	
	public CustomerVo(String n,String p)
	{
		name=n;
		password=p;
	}
	
	public void setName(String n)
	{
		name=n;
	}
	
	public void setPassword(String p)
	{
		password=p;
	}
	
	public int isvalid()
	{
		return CustomerDao.validation(name,password);
	}
	
	public static void complaintSet(String m, String n){
		String s=m;
		String na=n;
		CustomerDao.insert(s,na);
	}
	
	public static ArrayList customerDetails(String d){
		String t=d;
		return CustomerDao.customerDetails(d);
	}
	

}
