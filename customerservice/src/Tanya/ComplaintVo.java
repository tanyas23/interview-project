package Tanya;

import java.util.ArrayList;

public class ComplaintVo {
	
	String cmptype;
	String cmpstatus;
	
	public ComplaintVo(String t, String s){
		
		cmptype=t;
		cmpstatus=s;
	
	}
	
	public ComplaintVo(String t){
		
		cmptype=t;
	}
	
	public String toString(){
		return("Complaint type is:" +cmptype+ "Complaint status is :" +cmpstatus);
	}
	
	public void access(String user){
		ComplaintDao.insert(cmptype,user);
		
	}
	
	public static ArrayList<String> status(String n, String c){
		 
		 return ComplaintDao.selectStatus(n, c);
	}
	
	public static ArrayList complaintDetails(String n){
		String t=n;
		return ComplaintDao.complaintDetails(n);
		
	}
	
	

}
