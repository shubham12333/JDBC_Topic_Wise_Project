package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsertTest 
{
	private final static String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT1 VALUES(?,?,?,?)"; // prepared Statement 
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				//establish the connection 
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				//create PreparedStatement having pre-complied SQ query 
				PreparedStatement ps = con.prepareStatement(STUDENT_INSERT_QUERY);
				){
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter Students count:: ");
				count = sc.nextInt();
			}
			if(sc!=null&&ps!=null)
			{
				for(int i=1;i<=count;i++)
				{
					//read each student details 
					System.out.println("Enter "+i+" student details :: ");
					System.out.println("Enter Student Number :: ");
					int no = sc.nextInt();
					
					System.out.println("Enter Student name :: ");
					String name = sc.next();
					
					System.out.println("Enter Student Address :: ");
					String addrs = sc.next();
					
					System.out.println("Enter Student Avg :: ");
					float avg = sc.nextFloat();
					
					//set each student details to pre-complied SQL Query as query param values(?)
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4,avg);
					
					//execute the Query 
					int result = ps.executeUpdate();
					
					//process the result 
					if(result ==0)
					{
						System.out.println(i+" student record is not inserted");
					}
					else
					{
						System.out.println(i+" student record is inserted");
					}
				}//for
			}//if
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
