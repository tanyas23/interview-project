package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class Deleteservlet
 */
@WebServlet("/Deleteservlet")
public class Deleteservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	ServletContext sc;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deleteservlet() {
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
		sc=this.getServletContext();
		String bi=request.getParameter("t4");
		PrintWriter pr=response.getWriter();
		try {
			Statement st=con.createStatement();
			 st.executeUpdate(" delete from booklist where bookid='"+bi+"'");
			response.setContentType("text/html");
			pr.print("<html>");
			
		pr.print(" Deleted successfully");
		RequestDispatcher rd=sc.getRequestDispatcher("/index.html");
		rd.include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	

}
