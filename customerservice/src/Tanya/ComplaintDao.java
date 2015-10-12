package Tanya;



import Tanya.ComplaintVo;
import Tanya.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComplaintDao {

	public static void insert(String m, String n) {
		
		Connection con=DBConnector.getConnection();
		
		try {
			Statement st=con.createStatement();
			st.execute("insert into complaint(complainttype,complaintstatus,customername) values('"+m+"' , 'unresolved', '"+n+"')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> selectStatus(String m, String c){
		
		Connection con =DBConnector.getConnection();
		ArrayList<String> a=new ArrayList<String>();
		String status=null;
		
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select complaintstatus from complaint where customername=('"+m+"') and complainttype=('"+c+"')");
			while(rs.next())
			{
				a.add(rs.getString(1));
			}
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
		
	public static ArrayList<ComplaintVo> complaintDetails(String z)
	{
		Connection con=DBConnector.getConnection();
		 ArrayList<ComplaintVo> ar=new ArrayList<ComplaintVo>();
		 String status = null;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select complainttype,complaintstatus from complaint where customername=('"+z+"')");
			while(rs.next())
			{
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
				//status = rs.getString(1);
				ar.add(new ComplaintVo(rs.getString(1),rs.getString(2)));
			}
			 return ar;
           } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return ar;
		}
	}
}
		
		
		
		
		
		
	


