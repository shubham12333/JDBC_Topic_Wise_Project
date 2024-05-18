package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_Assignment_10_May 
{
	private static final String AUTH_QUERY= "UPDATE STUDENT SET AVG=AVG+(AVG*0.1) WHERE CITY IN(?,?) OR AVG<90";
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				PreparedStatement ps = con.prepareStatement(AUTH_QUERY);
				){
			//read inputs
			String FIRST_CITY=null , SECOND_CITY=null;
			if(sc!=null)
			{
				System.out.println("Enter FIRST CITY :: ");
				FIRST_CITY = sc.nextLine();
				FIRST_CITY = "'"+FIRST_CITY+"'";
				System.out.println("Enter SECOND CITY ::");
				SECOND_CITY = sc.nextLine();
				SECOND_CITY="'"+SECOND_CITY+"'";
			}
			// set values to query params
			if(ps!=null)
			{
				ps.setString(1,FIRST_CITY);
				ps.setString(2,SECOND_CITY);
			}//if
			//SEND AND EXECUTE THE SQL QUERY IN DB S/W 
			int count = ps.executeUpdate();
			System.out.println("Numbers of record updated are : "+count);
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
