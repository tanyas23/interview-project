package Tanya;

import Tanya.CustomerVo;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Controller() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc;
		sc = this.getServletContext();
		PrintWriter pr=response.getWriter();
		String c=request.getParameter("check");
		String n=request.getParameter("var1");
		String p=request.getParameter("var2");
		CustomerVo cv=new CustomerVo(n,p);
		
		if(c.equals("login"))
		{
			int flag=cv.isvalid();
			if(flag==1)
			{
				pr.println("Logged in Successfully");
				HttpSession session=request.getSession();
				session.setAttribute("uname", n);
				RequestDispatcher rd=sc.getRequestDispatcher("/Options.jsp");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd=sc.getRequestDispatcher("/LoginUser.jsp");
				rd.forward(request, response);
			}
			
		}
		
		if(c.equals("new complaint"))
		{
			String cm=request.getParameter("cmp");
			HttpSession session=request.getSession(false);
			String r=(String) session.getAttribute("uname");
			ComplaintVo cvo=new ComplaintVo(cm);
			cvo.access(r);
			pr.println("complaint successfully registered");
			CustomerVo.complaintSet(cm,r);
		}
		
		if(c.equals("complaint status"))
		{
			HttpSession session=request.getSession(false);
			String r=(String) session.getAttribute("uname");
			String cm=request.getParameter("type");
			ArrayList<String> a= ComplaintVo.status(r,cm);
	        for(int i=0;i<a.size();i++)
	        	pr.println("complaint status is="+a.get(i));
		}
		
		if(c.equals("all complaint"))
		{
			String cn=request.getParameter("cn");
			ArrayList b= ComplaintVo.complaintDetails(cn);
			for(int i=0;i<b.size();i++) {
				
	        	pr.println(b.get(i));
				pr.println("<br/>");
			}
	        
		}
		
		if(c.equals("customer details")){
			HttpSession session=request.getSession(false);
			String r=(String)session.getAttribute("uname");
		    ArrayList<String> ar=CustomerVo.customerDetails(r);
		    for(int i=0;i<ar.size();i++){
		    pr.println("User "+r+ "has registered following Complaints :" +ar.get(i));
		    }
			
		}
		
	}

}
