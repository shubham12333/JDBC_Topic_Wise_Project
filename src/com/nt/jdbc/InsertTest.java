package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest 
{
	public static void main(String[] args) 
	{
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try
		{
			//reads the input
			sc = new Scanner(System.in);
			int no =0;
			String name = null , adrss = null;
			float avg = 0.0f;
			
			if(sc!=null)
			{
				
				System.out.println("Enter Student Number :: ");
				no = sc.nextInt();
				System.out.println("Enter Student Name :: ");
				name = sc.next();
				System.out.println("Enter Student Address :: ");
				adrss = sc.next();
				System.out.println("Enter Student Avg :: ");
				avg = sc.nextFloat();
				
			}
			
			//convert the input values are required for the SQL query 
			name = "'"+name+"'";//name = 'arun'
			adrss ="'"+adrss+"'";//addrss = 'indore'
			
			//load the JDBC Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			
			//create the STATEMENT OBJECT
			if(con!=null)
				st=con.createStatement();
			
			//prepare the SQL Query
			//INSERT INTO STUDENT VALUES(1010,'RAJA','HYD',67.8);
			String query = "INSERT INTO STUDENT1 VALUES ("+no+","+name+","+adrss+","+avg+")";
			System.out.println(query);
			
			//send and execute SQL query in Db s/w;
			
			int count=0;
			if(st!=null)
			{
				count=st.executeUpdate(query);
			}
			//process the result 
			if(count==0)
			{
				System.out.println("Problem in record insertion");
			}
			else
			{
				System.out.println("Record inserted");
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
