package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Job_Seeker_Details 
{
	private static final String Query = "INSERT INTO JOB_SEEKER_DETAILS VALUES(SEEKER_ID_SEQ.NEXTVAL,?,?,?,?,?,?)";
	
	public static void main(String[] args)
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement(Query);
				)
		{
			
			String name=null , Address=null ,qualification=null ,photoPath=null,resumePath=null,date=null;
			
			if(sc!=null)
			{
				
				System.out.println(" Name : ");
				name=sc.nextLine();
				System.out.println("Address :: ");
				Address = sc.nextLine();
				System.out.println("Qualification :: ");
				qualification = sc.nextLine();
				System.out.println("Photo Path :: ");
				photoPath = sc.nextLine();
				System.out.println("Resume Path :: ");
				resumePath = sc.nextLine();
				System.out.println("Enter DOB :: ");
				date=sc.next();
				
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date udob =sdf.parse(date);
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			//create stream points to the files
			
			try(InputStream is = new FileInputStream(photoPath);
				Reader reader = new FileReader(resumePath);
					)
			{
				if(ps!=null)
				{
					//set query values to params
					ps.setString(1,name);
					ps.setString(2,Address);
					ps.setString(3,qualification);
					ps.setBinaryStream(4, is);
					ps.setCharacterStream(5, reader);
					ps.setDate(6,sqdob);
					
					//execute the query 
					int count = ps.executeUpdate();
					//process the result 
					if(count==0)
						System.out.println("Record  Not Inserted");
					else
						System.out.println("Record Inseerted");
				}
				
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
