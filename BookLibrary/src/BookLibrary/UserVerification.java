package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class UserVerification
 */
@WebServlet("/UserVerification")
public class UserVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	ServletContext sc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init()
	{
		// TODO Auto-generated method stub
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","root");
			System.out.println("hii1");
		} catch (ClassNotFoundException e1) {
			//e1.printStackTrace();
			System.out.println("Class load exception !!");
		}
		catch(SQLException e2){
			e2.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hii2");
		String name=request.getParameter("var1");
		String pass=request.getParameter("var2");
		PrintWriter pr=response.getWriter();
		
		try {
			    HttpSession session=request.getSession();  
			        session.setAttribute("uname",name);  
			
			PreparedStatement pst=con.prepareStatement("select * from libuser where name=?");
			pst.setString(1, name);
			ResultSet rs=pst.executeQuery();
			rs.next();
			if(pass.equals(rs.getString(2))){
				
				pr.print("Access Granted");
				sc=this.getServletContext();
				RequestDispatcher rd=sc.getRequestDispatcher("/Booksearch");
				rd.forward(request, response);
		
			}
			else
			{
				pr.print("Access Denied");
				sc=this.getServletContext();
				RequestDispatcher rd=sc.getRequestDispatcher("/UserLogin.html");
			//	rd.include(request, response);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
