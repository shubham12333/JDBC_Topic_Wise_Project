package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassA 
{
	public static void main(String[] args) 
	{
		
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP")
				)
		{
			 ResultSet rs  = ps.executeQuery();
			 
			 while(rs.next())
			 {
				 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
