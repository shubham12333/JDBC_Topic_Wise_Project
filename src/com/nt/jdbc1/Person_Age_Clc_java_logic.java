package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Person_Age_Clc_java_logic 
{
	private static final String GET_DOB_BY_CNO ="SELECT DOB FROM CUSTOMER_INFO WHERE CNO=?";
	
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement(GET_DOB_BY_CNO);
				){
			//read input values
			int no=0;
			if(sc!=null)
			{
				System.out.println("Enter Customer No :: ");
				no=sc.nextInt();
			}
			//set values to query params
			if(ps!=null)
			{
				ps.setInt(1, no);
			}
			//execute the SQL Query and get the ResultSet obj
			try(ResultSet rs = ps.executeQuery())
			{
				if(rs!=null)
				{
					if(rs.next())
					{
						java.util.Date udob = rs.getDate(1);
						java.util.Date sysdate = new Date();
						long ageinMs =sysdate.getTime()-udob.getTime();
						System.out.println("Person Age :: "+(ageinMs/(1000.0f*60.0f*24.0f*365.25f*60.0f)));
					}
					else
					{
						System.out.println("Customer Not Found");
					}
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
}
