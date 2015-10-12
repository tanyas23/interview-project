package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Addreview
 */
@WebServlet("/Addreview")
public class Addreview extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext sc;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addreview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pr=response.getWriter();
		sc=this.getServletContext();
		
		  HttpSession session=request.getSession(false);  
	        //String n=(String)session.getAttribute("uname");  
	        
	        response.setContentType("text/html");
			pr.print("<html>");
			if(session !=null)
			{
				pr.print("session successful");
				response.setContentType("text/html");
				pr.print("<html>");
				pr.print("<form  method=post action=Setcomment> ");
				pr.print("<br><b>Enter review </b>");
				pr.print("<input type=text name=r1 >");
				pr.print("<br><b>Enter bookname </b>");
				pr.print("<input type=text name=b1 >");
				
				pr.print("<br><b>Enter username </b>");
				pr.print("<input type=text name=n1 >");
				pr.print("<button type=submit> show review </button>");
				pr.print("</form>");
				
				
				
			}
			else
			{
				pr.print("session unsuccessful");
				RequestDispatcher rd=sc.getRequestDispatcher("/UserLogin.html");
				rd.include(request,response);
				
			}
			
			
	}

}
