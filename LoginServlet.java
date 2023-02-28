import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class LoginServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		String userId=req.getParameter("userId");
		String pass=req.getParameter("pass");
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		try{
		HttpSession session=req.getSession();
		session.setAttribute("userId",userId);
		session.setAttribute("pass",pass);
		}
		catch(Exception e)
		{
			out.println(e);
			System.out.println(e);
		}
	}
}