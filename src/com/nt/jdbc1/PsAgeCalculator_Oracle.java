package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Scanner;

public class PsAgeCalculator_Oracle 
{
	private static final String Age_Calculator="SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSON_INFO WHERE PID = ?";
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				PreparedStatement ps = con.prepareStatement(Age_Calculator);
				Scanner sc = new Scanner(System.in);
				)
		{
			int PID=0;
			System.out.println("Enter Customer ID : ");
			PID = sc.nextInt();
			
			//set values to query params
			ps.setInt(1, PID);
			
			try(ResultSet rs =ps.executeQuery())
			{
				//process the result
				if(rs.next())
				{
					float age = rs.getFloat(1);
					System.out.println("The Person Age is : "+age);
				}
				else
				{
					System.out.println("Customer not found");
				}
			}
			
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}//main
}//class
