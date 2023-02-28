import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class MyListener implements HttpSessionListener,ServletContextListener
{
	public void sessionCreated(HttpSessionEvent e)
	{
		HttpSession session=e.getSession();
		//session.setMaxInactiveInterval(20);
	}
	public void sessionDestroyed(HttpSessionEvent e)
	{
		System.out.println("SEssion destroying...");
		HttpSession session=e.getSession();
		ServletContext ctx=session.getServletContext();
		String userId=(String)session.getAttribute("userId");
		String pass=(String)session.getAttribute("pass");
		System.out.println(userId+" : "+pass);
		try
		{
			Connection c=(Connection)ctx.getAttribute("con");
			Statement s=c.createStatement();
			s.executeUpdate("update cust set loginStatus='F' where userId='"+userId+"' and pass='"+pass+"'");
		}
		catch(Exception we)
		{
			System.out.println(we);
		}
	}
	public void contextInitialized(ServletContextEvent e)
	{
		try
		{
			ServletContext ctx=e.getServletContext();
			String driver=ctx.getInitParameter("driver");
			String url=ctx.getInitParameter("url");
			String user=ctx.getInitParameter("user");
			String pass1=ctx.getInitParameter("pass");
			Class.forName(driver);
			Connection c=DriverManager.getConnection(url,user,pass1);
			ctx.setAttribute("con",c);
			System.out.println("Context Created..");
		}
		catch(Exception se)
		{
			System.out.println(se);
		}
	}
	public void contextDestroyed(ServletContextEvent e)
	{}
}