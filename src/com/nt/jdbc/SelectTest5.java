package com.nt.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class SelectTest5 
{
public static void main(String[] args) 
{
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	try
	{
		//Load jdbc driver class (optioanl)
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establish the Connection
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
		
		//CREATE THE JDBC Statement object
		if(conn!=null)
			st=conn.createStatement();
		
		//prepare the SQL query 
		System.out.println("Max Salary is :: ");
		String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
		
		//SEND AND EXECUTE THE SQL QUERY
		if(st!=null)
			rs=st.executeQuery(query);
		
		//process the RESULTSET obj
		if(rs!=null)
		{
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
			}//while
		}//if
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
		//close the jdbc objs
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
			if(st!=null)
				st.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		try
		{
			if(rs!=null)
				rs.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}
}
