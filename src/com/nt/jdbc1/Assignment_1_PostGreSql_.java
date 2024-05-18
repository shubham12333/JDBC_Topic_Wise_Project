package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Assignment_1_PostGreSql_ 
{
	private static final String QUERY = "INSERT INTO DEMO VALUES(NEXTVAL('d_id_seq'),?,?,?,?,?)";
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJDB415","postgres","9907829926");
			PreparedStatement ps = con.prepareStatement(QUERY);
				)
		{
			String name=null,DOB=null,time=null,picturePath=null,resumePath=null;
			
			if(sc!=null)
			{
				System.out.println("Enter name :: ");
				name=sc.nextLine();
				System.out.println("Enter DOB (yyyy-mm-dd) :: ");
				DOB = sc.nextLine();
				
				System.out.println("Enter TimeStamp (yyyy-mm-dd hh:mi:ss) :: ");
				time = sc.nextLine();
				System.out.println("Paste Picture Path :: ");
				picturePath = sc.nextLine();
				System.out.println("Resume Path :: ");
				resumePath = sc.nextLine();
			}
			InputStream is = new FileInputStream(picturePath);
			Reader reader = new FileReader(resumePath);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date udob = sdf.parse(DOB);
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);
			
			Timestamp ts = Timestamp.valueOf(time);
			//System.out.println(ts);
			
			
			if(ps!=null)
			{
				ps.setString(1,name);
				ps.setDate(2,sqdob);
				ps.setTimestamp(3, ts);
				ps.setBinaryStream(4, is);
				ps.setCharacterStream(5, reader);
				
				int count = ps.executeUpdate();
				
				if(count==0)
					System.out.println("Error data cant be inserted");
				else
					System.out.println("Data Inserted SuccessFully!!!!");
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
