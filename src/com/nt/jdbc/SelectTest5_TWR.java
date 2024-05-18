package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest5_TWR 
{
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
		
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
			Statement st = con.createStatement();
			
		){
		//read input from enduser
			String desg1=null,desg2=null,desg3=null;
			if(sc!=null)
			{
				System.out.println("Enter desg1 : ");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter desg2 : ");
				desg2=sc.next().toUpperCase();
				System.out.println("Enter desg3 : ");
				desg3 =sc.next().toUpperCase();
			}
			//conert input values as the required for the SQL Query 
			//('CLERK','MANAGER','ANALYST')
			String cond = "('"+desg1+"','"+desg2+"','"+desg3+"')";
			
			//prepare the SQL Query
			  //SELECT EMPNO,ENAME,JOB,SAL,FROM EMP WHERE JOB IN('CLERK','MANAGER','ANALYST');
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN "+cond+" ORDER BY JOB";
			System.out.println(query);
			
			//send and execute the SQL Query in Db s/w
			
			try(ResultSet rs = st.executeQuery(query))
			{
				//process the resultSet obj
				
				if(rs!=null)
				{
					boolean isRSEmpty=true;
					while(rs.next())
					{
						isRSEmpty=false;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}//while
					
					if(isRSEmpty)
					{
						System.out.println("No Records Found");
					}
					else
					{
						System.out.println("Records are found and displayed");
					}
				}//if
			}//try2
			
			}//try1
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
