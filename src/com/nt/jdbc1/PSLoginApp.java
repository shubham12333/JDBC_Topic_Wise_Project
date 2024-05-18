package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSLoginApp 
{
	private final static String AUTH_QUERY = "SELECT COUNT(*) FROM USER_INFO WHERE USERNAME=? AND PASSWORD=?";
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				PreparedStatement ps = con.prepareStatement(AUTH_QUERY);
				){
			//read the inputs 
			String user = null;
			String pass = null;
			if(sc!=null)
			{
				System.out.print("Enter Username :: ");
				user=sc.nextLine();
				System.out.println();
				System.out.print("Enter Password :: ");
				pass=sc.nextLine();
			}
			//set values to the query params 
			if(ps!=null)
			{
				ps.setString(1, user);
				ps.setString(2, pass);
			}
			//send and execute the SQL Query in Db s/w
			try(ResultSet rs = ps.executeQuery())
			{
				//process the ResultSet Obj
				if(rs!=null)
				{
					rs.next();
					int count = rs.getInt(1);
					if(count ==0)
						System.out.println("Invalid Credintials");
					else
						System.out.println("Valid Credintials");
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
