import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class CarServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		HttpSession session=req.getSession(false);
		if(session!=null)
		{
			String cars[]=req.getParameterValues("car");
			session.setAttribute("cars",cars);
			RequestDispatcher rd=req.getRequestDispatcher("/welcome.html");
		
		    rd.forward(req,res);
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("/index.html");
		
		    rd.forward(req,res);
		}
	}
}