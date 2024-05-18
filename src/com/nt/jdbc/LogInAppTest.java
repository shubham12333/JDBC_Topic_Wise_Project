package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LogInAppTest 
{
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				Statement st = con.createStatement();
				){
			String user = null;
			String pass = null;
			//read the input values
			if(sc!=null) {
				System.out.println("Enter Username :: ");
				user=sc.next().toLowerCase();
				System.out.println("Enter Password :: ");
				pass=sc.next().toLowerCase();
			}
			//convert the input values as the required for the SQL Query 
			user ="'"+user+"'";
			pass ="'"+pass+"'";
			
			//prepare the SQL Query
			//SELECT COUNT(*) FROM USERINFO WHERE USERNAME='RAJA' AND PASSWORD='123';
			
			String query = "SELECT COUNT(*) FROM USERINFO WHERE USERNAME="+user+" AND PASSWORD="+pass;
			System.out.println(query);
			
			//send and execute the SQL Query
			
			try(ResultSet rs = st.executeQuery(query))
			{
				//process the result object 
				if(rs!=null) {
					rs.next();
					int count = rs.getInt(1);
					if(count==0)
					{
						
						System.out.println("Invalid Credentials");
					}
					else
					{
						
						System.out.println("Valid Credentials");
					}
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
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
