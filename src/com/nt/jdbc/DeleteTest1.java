package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest1 
{
	public static void main(String[] args) 
	{
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try
		{
			//read the inputs
			sc = new Scanner(System.in);
			String add="";
			if(sc!=null)
			{
				System.out.println("Enter Student number :: ");
				add = sc.nextLine();
				
			}
			//Load the JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
			
			//create the Statement Object
			if(con!=null)
			{
				st = con.createStatement();
			}
			//prepare the SQL query
			
			//DELETE FROM STUDENT WHERE CITY=BADAGAON;
			String query = "DELETE FROM STUDENT WHERE CITY = "+"'"+add+"'";
			System.out.println(query);
			
			//send and execute the SQL query in DB
			int count = 0;
			if(st!=null)
			{
				count = st.executeUpdate(query);
			}
			
			//process the result
			if(count==0)
			{
				System.out.println("no rexcords found for deletion");
			}
			else
			{
				System.out.println("Records found and deleted");
			}
			
			
		}//try
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
			//close the JDBC objs
			
			try
			{
				if(st!=null)
					st.close();
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
			try
			{
				if(sc!=null)
					sc.close();
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
		}
	}
	
}
