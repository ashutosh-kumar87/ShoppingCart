 import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class LoginFilter implements Filter
{
	FilterConfig config;
	public void init(FilterConfig config) throws ServletException
	{
		this.config=config;
	}
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException
	{
		String userId=req.getParameter("userId");
		String pass=req.getParameter("pass");
		PrintWriter out=res.getWriter();
		ServletContext ctx=config.getServletContext();
		try
		{
			Connection c=(Connection)ctx.getAttribute("con");
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("select * from cust where userId ='"+userId+"' and pass='"+pass+"'");
			if(rs.next())
			{
				if(rs.getString("loginStatus").equals("F"))
				{
					s.executeUpdate("update cust set loginStatus='T' where userId='"+userId+"' and pass ='"+pass+"'");
					chain.doFilter(req,res);
					HttpServletResponse rss=(HttpServletResponse)res;
					rss.setHeader("Refresh","0;welcome.html");
				}	
				else
				{
					out.println("<html><body><h2>User Already Logged In</h2></body></html>");
					HttpServletResponse rss=(HttpServletResponse)res;
					rss.setHeader("Refresh","4;index.html");
				}
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("/index.html");
				rd.forward(req,res);
			}
		}
		catch(Exception e)
		{ 
			out.println(e);
		}
	}
	public void destroy()
	{
	}
}