package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Booklink
 */
@WebServlet("/Booklink")
public class Booklink extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	ServletContext sc;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booklink() {
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
String book=request.getParameter("b1");
		
		PrintWriter pr=response.getWriter();
		
		try {
			   
			        
			PreparedStatement pst=con.prepareStatement("select * from booklist where booktitle like '"+book+"'");
			//pst.setString(1, name);
			response.setContentType("text/html");
			pr.print("<html><body>");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				pr.println("<a href='Bookslist?bookname="+book+"'>"+book+"</a>");
			}
			pr.print("</body></html>");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
