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
 * Servlet implementation class Bookslist
 */
@WebServlet("/Bookslist")
public class Bookslist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookslist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title=request.getParameter("bookname");
		PrintWriter pr=response.getWriter();
		
		try {
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from booklist where booktitle like '"+title+"'");
			rs.next();
		pr.println("book name-"+rs.getString(1)+"<br/>book id--"+rs.getString(2)+ "<br/> book datails--"+rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		pr.print("<html>");
		pr.print("<form  method=post action=Addreview> ");
		pr.print("<b>enter your review </b>");
		pr.print("<button type='submit'> post review</button>");
		pr.print("</form>");
	}

}
