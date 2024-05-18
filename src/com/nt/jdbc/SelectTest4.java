package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 
{
	public static void main(String[] args) 
	{
		{
			Scanner sc = null;
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
		try
		{
			sc = new Scanner(System.in);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
			//CREATE the JDBC  Statement Object
			
			if(con!=null)
				st=con.createStatement();
			//prepare the SQL query
			
			String query = "SELECT COUNT(*) FROM EMP"; 
			//SEND AND EXECUTE THE SQL QUERY
			
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet obj
			if(rs!=null)
			{
				rs.next();
				//System.out.println("Emp db table records count is :: "+rs.getInt("count(*)")); (or)
				System.out.println("Emp db table records count ::"+rs.getInt(1));
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(st!=null)
				{
					st.close();
				}
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
}
}