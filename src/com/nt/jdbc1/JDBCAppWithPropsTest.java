package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCAppWithPropsTest 
{
	public static void main(String[] args) 
	{
		try(InputStream is = new FileInputStream("src/com/nt/commons/jdbc.properties");)
		{
			//load the properties content to java.util.Properties  class obj
			Properties props = null;
			if(is!=null)
			{
				props = new Properties();
				props.load(is);
			}
			
			try(Connection con = DriverManager.getConnection(props.getProperty("postgresql.url"),
															 props.getProperty("postgresql.username"),
															 props.getProperty("postgresql.password"));
					PreparedStatement ps = con.prepareStatement("Select * from product");
					ResultSet rs = ps.executeQuery();
					)
			{
				if(rs!=null)
				{
					while(rs.next())
					{
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
					}//while
				}//if
			}//try
		}//try
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
