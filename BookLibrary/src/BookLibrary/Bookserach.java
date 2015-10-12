package BookLibrary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bookserach
 */
@WebServlet("/Booksearch")
public class Bookserach extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookserach() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pr=response.getWriter();
		response.setContentType("text/html");
		pr.print("<html>");
		pr.print("<form  method=post action=Booklink> ");
		pr.print("<b>enter the title </b>");
		pr.print("<input type=text name=b1 >");
		pr.print("<input type=submit>");
		pr.print("</form>");
	}

}
