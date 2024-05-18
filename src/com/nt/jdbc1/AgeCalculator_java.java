package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class AgeCalculator_java 
{
	private static final String query="SELECT DOB FROM PERSON_INFO WHERE PID=?";
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				PreparedStatement ps = con.prepareStatement(query);
				Scanner sc = new Scanner(System.in);
				)
		{
			int pid=0;
			if(sc!=null)
			{
			System.out.print("Enter Person ID : ");
			pid=sc.nextInt();
			}
			ps.setInt(1,pid);
			
			//process the Result set 
			try(ResultSet rs = ps.executeQuery())
			{
				//process the ResultSet
				if(rs.next())
				{
					java.sql.Date date = rs.getDate(1);
					//System.out.println(date);
					int year = date.getYear();//DOB YEAR 
					System.out.println("DOB IS : "+date);
					Date date1 = new Date(); 
					//System.out.println(date1);
					int year1 = date1.getYear();//current year 
					//System.out.println(1900+year1);
					
					System.out.println("Your Age is : "+(year1-year));
					//System.out.println(Calendar.getInstance().get(Calendar.YEAR)); //current year by Calendar Class
					
				}
				else
				{
					System.out.println("Not Found!!!!");
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
	}
}
