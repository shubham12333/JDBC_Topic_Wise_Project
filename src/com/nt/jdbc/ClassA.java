package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClassA 
{
	public static void main(String[] args) 
	{
		Scanner  sc = null;
		Connection con = null;
		Statement st = null;
		
		try
		{
			sc = new Scanner(System.in);
			int no=0;
			if(sc!=null)
			{
				System.out.println("Enter student number :: ");
				no = sc.nextInt();
			}//if
			//Load the JDBC Driver Class
			Class.forName("oracle.jdbc.OracleDriver");
			//establish the Connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			
			//create the Statement Object
			if(con!=null)
			{
				st=con.createStatement();
				//prepare the SQL Query 
						//DELETE from student where sno=101;
				String query = "SELECT * FROM STUDENT WHERE SNO="+no;
				System.out.println(query);
				
				int count = 0;
				if(st!=null)
				{
					count = st.executeUpdate(query);
				}
				
				//process the Result 
				if(count==0)
				{
					System.out.println("No Records Found");
				}
				else
				{
					System.out.println("Record Found and displayed");
				}
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
		
	}
}
