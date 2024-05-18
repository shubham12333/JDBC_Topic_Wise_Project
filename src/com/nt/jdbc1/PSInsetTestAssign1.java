package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsetTestAssign1 
{
	private final static String STUDENT_INSERT_QUERY = "INSERT INTO PRODUCT VALUES(?,?,?,?)";
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				//establish the Connection
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				//create PreparedStatement having pre-compliled SQL Query 
				PreparedStatement ps = con.prepareStatement(STUDENT_INSERT_QUERY);
				){
			int count = 0;
			if(sc!=null)
			{
				System.out.println("Enter Products count :: ");
				count = sc.nextInt();
			}
			if(sc!=null && ps!=null)
			{
				for(int i=1;i<=count;++i) {
					//read each student details 
					System.out.println("Enter"+i+" product details :: ");
					System.out.println("Enter Product Number :: ");
					int no = sc.nextInt();
					
					System.out.println("Enter Product Name :: ");
					String name = sc.nextLine();
					
					System.out.println("Enter Product PRICE :: ");
					int price = sc.nextInt();
					
					System.out.println("Enter Product QTY :: ");
					float avg = sc.nextFloat();
					
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setInt(3,price);
					ps.setFloat(4,avg);
					
					//execute the Query
					int result = ps.executeUpdate();
					
					//process the result 
					
					if(result==0)
					{
						System.out.println(i+" PRODUCT record is not inserted ");
					}
					else
					{
						System.out.println(i+" PRODUCT record is inserted");
						System.out.println("------------------------------------");
					}
				}//for
			}//if
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		/*catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}*/
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}//main
}//class
