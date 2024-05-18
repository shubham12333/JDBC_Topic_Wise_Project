package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PostGreSQLInsertTest 
{
	private static final String INSERT_PRODUCT_QUERY = "INSERT INTO "+"PRODUCT"+" VALUES(NEXTVAL('PID_SEQ'),?,?,?)";
	
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				//Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:/NTAJDB415","postgres","9907829926");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJDB415","postgres","9907829926");
			PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT_QUERY);
			)
		{
			//read value from the enduser
			String name=null;
			double price =0.0,qty = 0.0;
			
			if(sc!=null)
			{
			System.out.println("Enter Product Name :: ");
			name=sc.next();
			System.out.println("Enter Product Price :: ");
			price = sc.nextDouble();
			System.out.println("Enter Product Qunatity :: ");
			qty = sc.nextDouble();
			}
			//set values to query params
			
			if(ps!=null)
			{
				ps.setString(1, name);
				ps.setDouble(2, price);
				ps.setDouble(3, qty);
				
				int count = ps.executeUpdate();
				
				if(count==0)
					System.out.println("Record Not Inserted");
				else
					System.out.println("Record Inserted");
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
