package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddbookServlet
 */
@WebServlet("/AddbookServlet")
public class AddbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

  	ServletContext sc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddbookServlet() {
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
		String bn=request.getParameter("t1");
		String bi=request.getParameter("t2");
		String br=request.getParameter("t3");
		PrintWriter pr=response.getWriter();
		response.setContentType("text/html");
		try {
			Statement st=con.createStatement();
			sc=this.getServletContext();
			 st.executeUpdate(" insert into booklist (booktitle,bookid,bookdetail) values('"+bn+"' ,'"+bi+"','"+br+"')");
			 ResultSet rs=st.executeQuery(" select * from booklist where bookid='"+bi+"'");
			rs.next();
			pr.print("<html>");
			pr.print( "<b>"+rs.getString("booktitle")+"</b>");
		pr.print( "<br><b>"+rs.getString("bookid")+"</b>");
		pr.print( "<br><b>"+rs.getString("bookdetail")+"</b>");
		pr.print(" Added successfully");
		RequestDispatcher rd=sc.getRequestDispatcher("/index.html");
		rd.include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
