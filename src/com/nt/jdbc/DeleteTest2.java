package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest2 
{
	public static void main(String[] args) 
	{
		Scanner sc = null;
		Connection conn = null;
		Statement st = null;
		
		try
		{
			//read the input
			sc=new Scanner(System.in);
			int no = 0;
			String newName=null,newAddrs=null;
			float newAvg = 0.0f;
			
			if(sc!=null)
			{
				System.out.println("Enter Existing student number : ");
				no=sc.nextInt();
				System.out.println("Enter Students new name : ");
				newName = sc.next();
				System.out.println("Enter Students new Address :: ");
				newAddrs = sc.next();
				System.out.println("Enter Students new Avg :: ");
				newAvg = sc.nextFloat();
			}
			
			//convert the input values are required for the SQL Query 
			newName = "'"+newName+"'";
			newAddrs= "'"+newAddrs+"'";
			
			//Load jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
			
			//create the Statement Object 
			if(conn!=null)
			{
				st=conn.createStatement();
			}
			//prepare the 	SQL Query
			  //UPDATE STUDENT1 SET NAME='SURESH' CITY='BHOPAL' AVERAGE ='88.5' WHERE NO=101;
			
			String query = "UPDATE STUDENT1 SET NAME="+newName+", CITY="+newAddrs+", AVERAGE ="+newAvg+" WHERE NO="+no;
			System.out.println(query);
			
			//send and execute SQL QUERY in DB s/w 
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process the result
			if(count==0)
			{
				System.out.println("Problem in Record updation");
			}
			else
			{
				System.out.println("Record update with new values");
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
				if(conn!=null)
					conn.close();
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
