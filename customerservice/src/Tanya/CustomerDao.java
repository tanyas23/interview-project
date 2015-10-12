package Tanya;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDao {
	
	public static int validation(String name, String pass){
		
		Connection con=DBConnector.getConnection();
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			pst=con.prepareStatement("select * from libuser where name=?");
			pst.setString(1,name);
			rs=pst.executeQuery();
			rs.next();
			
			if(pass.equals(rs.getString(2))){
				return 1;
				
			}
			else return 0;
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 2;
		}
		
	}
	
	public static void insert(String r, String n){
		Connection con=DBConnector.getConnection();
		try {
			Statement st=con.createStatement();
			st.executeUpdate("update customer set complaint ='"+r+"' where c_name='"+n+"'");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> customerDetails(String d){
		Connection con =DBConnector.getConnection();
		 ArrayList<String> ar=new ArrayList<String>();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs=st.executeQuery("select * from customer where c_name='"+d+"'");
			while(rs.next()){
				
				ar.add(rs.getString(1));
			    }
			return ar;
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ar;
		}
		
	}

}
