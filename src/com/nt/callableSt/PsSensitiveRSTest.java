package com.nt.callableSt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSensitiveRSTest 
{
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement("SELECT SNO , SNAME ,SADD, AVG FROM STUDENT",
																										ResultSet.TYPE_SCROLL_SENSITIVE,
																										ResultSet.CONCUR_UPDATABLE); 
			ResultSet rs = ps.executeQuery();
			)
		{
			if(rs!=null)
			{
				System.out.println("Records display (top to bottom )");
				int count=0;
				while(rs.next())
				{
					if(count == 0)
					{
						Thread.sleep(15000); 
					}
					rs.refreshRow();
					count++;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
			}//if
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	 
	}
}
