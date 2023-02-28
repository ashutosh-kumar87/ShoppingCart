 import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;
public class RegisterFilter implements Filter
{
	FilterConfig config;
	public void init(FilterConfig config) throws ServletException
	{
		this.config=config;
	}
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException,ServletException
	{
		PrintWriter out=res.getWriter();
		String userId=req.getParameter("userId");
		String pass=req.getParameter("pass");
		String repass=req.getParameter("repass");
		if(pass.equals(repass))
		{
			ServletContext ctx=config.getServletContext();
			try
			{
				Connection c=(Connection)ctx.getAttribute("con");
				Statement s=c.createStatement();
				ResultSet rs=s.executeQuery(" select * from cust where userId='"+userId+"' and pass='"+pass+"'");
				if(rs.next())
				{
					chain.doFilter(req,res);
				}
				else
				{
					int x=s.executeUpdate("insert into cust values('"+userId+"','"+pass+"','F')");
					out.println("<html><body><br><h1> Registered Successfully !!! Login Now</h1>");
					out.println("<br><h2> Login page Realoding ...</h2>.</body></html>");
					HttpServletResponse rss=(HttpServletResponse)res;
					rss.setHeader("Refresh","4;index.html");
					//RequestDispatcher rd=req.getRequestDispatcher("/registered");
					//rd.forward(req,res);
				}
			}
			catch(Exception o)
			{
				out.println(o);
			}
		} 
		else
		{
			out.println("<html><body><br><h1> Password and ReEntered Password did not match!!! Try Again </h1>");
			out.println("<br><h2> Registration page Realoding ... </h2>.</body></html>");
			HttpServletResponse rss=(HttpServletResponse)res;
			rss.setHeader("Refresh","4;register.html");
		}
	}
	public void destroy()
	{
	}
}
