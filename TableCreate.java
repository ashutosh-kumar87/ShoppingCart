import java.sql.*;
import java.util.*;
import java.io.*;
public class TableCreate
{
	public static void createTab(String path)
	{
		try
		{
			FileInputStream fn=new FileInputStream(path);
			byte br[]=new byte[fn.available()];
			fn.read(br);
			fn.close();
			String data=new String(br);
			StringTokenizer str=new StringTokenizer(data,"/");
			Connection con=ConnectionProvider.getConn();
			Statement stm=con.createStatement();
			while(str.hasMoreTokens())
			{
				String query=str.nextToken();
				if(query.trim().equals("stop"))
				{
					break;
				}
				stm.executeUpdate(query);
				System.out.println(query);
			}
		}catch(Exception e){}
	}
}