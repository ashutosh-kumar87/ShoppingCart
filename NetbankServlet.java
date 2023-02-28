import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class NetbankServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		String cardno=req.getParameter("cardno");
		String bankname=req.getParameter("bankname");
		String name=req.getParameter("name");
		ServletContext ctx=getServletContext();
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		try
		{
			Connection c=(Connection)ctx.getAttribute("con");
			Statement s=c.createStatement();
			ResultSet rs=s.executeQuery("select * from bank where cardno='"+cardno+"' and name='"+bankname+"'");
			if(rs.next())
			{
				//Out.println("matched");
				out.println("<html<body><h1> Thank You for Buying.Happy Shopping !!!! </h1> </body></html>");
				out.println("<html><body> <h2> Payment Completed !!!! </h2></body></html>");
				res.setHeader("Refresh","4;welcome.html");
			}
			else
			{
				out.println("<html><body><h2> Invalid Details Try Again!!! </h2></body></html>");
				res.setHeader("Refresh","3;netbank.html");
			}
		}
		catch(Exception e)
		{
			out.println(e);
			System.out.println(e);
		}
		out.println("</body></html>");
	}
}