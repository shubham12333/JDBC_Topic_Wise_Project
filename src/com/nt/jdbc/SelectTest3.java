package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) 
	{
		Scanner sc = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try
		{
			sc= new Scanner(System.in);
			int empno = 0;
			if(sc!=null)
			{
				System.out.print("Enter EMP NO. For Details :: ");
				empno = sc.nextInt();
			}
			//Load jdbc driver class (Optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection 
			
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
			
			//CREATE THE JDBC STATEMENT OBJECT
			
			if(conn!=null)
			{
				st=conn.createStatement();
			}
			//create the SQL query 
			//SELECT * FROM EMP WHERE EMPN0=empno;
			
			String query = "SELECT * FROM EMP WHERE EMPNO = "+empno;
			System.out.println(query);
			
			//process the ResultSet obj
			if(st!=null)
			{
				rs=st.executeQuery(query);
			}
			
			if(rs!=null)
			{
				if(rs.next())
				{
					System.out.println("The Employee details are here ");
					System.out.println();
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getDate(5)+" "+rs.getInt(6)+" "+rs.getInt(7)+" "+rs.getInt(8)+" ");
				}
				else
				{
					System.out.println("Employee not found");
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
