package com.nt.jdbc;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class SelectTest2 
{
	public static void main(String[] args)
	{
		Scanner sc = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			sc= new Scanner(System.in);
			int dno=0;
			if(sc!=null)
			{
				System.out.println("Enter Departent Number");
				dno=sc.nextInt();
			}// dno=10
			//Load jdbc driver class(Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			//etablish the Connection
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926"
					+ "");
			System.out.println(conn);
			//create the JDBC Statement object 
			if(conn!=null)
			{
				st=conn.createStatement();
			}
			//create the SQL query 
			//SELECT * FROM DEPT WHERE DEPTNO=10
			String query = "SELECT * FROM DEPT WHERE DEPTNO="+dno; 
			System.out.println(query);
			
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet obj
			if(rs!=null)
			{
				if(rs.next())
				{
					System.out.println("The Dept details are :: ");
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
				else
				{
					System.out.println("Department is not found");
				}
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
		}
	}
}
