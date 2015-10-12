package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Setcomment
 */
@WebServlet("/Setcomment")
public class Setcomment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Setcomment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init()  {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","root");
		} catch (ClassNotFoundException e1) {
			//e1.printStackTrace();
			System.out.println("Class load exception !!");
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String name=request.getParameter("n1");
		String title=request.getParameter("b1");
		String r=request.getParameter("r1");
		PrintWriter pr=response.getWriter();
		
		try {
			
		Statement st=con.createStatement();
		 st.executeUpdate("update reviewofbook set review='"+r+"' WHERE bookname = '"+title+"' AND username = '"+name+"' ");
		 ResultSet rs=st.executeQuery(" select * from reviewofbook  where bookname = '"+title+"' AND username = '"+name+"' ");
		rs.next();
		response.setContentType("text/html");
		pr.print("<html>");
		pr.print( "<b> <i> your review successfully entered </b> </i>");
		
	pr.print( "<br>  book title--"+rs.getString(1)+"</b>");
	pr.print( "<br> user--"+rs.getString(2)+"</b>");
	pr.print( "<br> your review--"+rs.getString(3)+"</b>");
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
