package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NthHighestSal 
{
	private static String Query = "SELECT SAL FROM (SELECT ROWNUM AS RANK,EMPNO,ENAME,SAL FROM(SELECT * FROM EMP ORDER BY SAL DESC)) WHERE RANK=?";
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				PreparedStatement ps = con.prepareStatement(Query);
				)
		{
			int nth=0;
			if(sc!=null)
			{
				System.out.println("Enter the which number salary you want to display :: ");
				nth=sc.nextInt();
			}//if
			if(ps!=null)
			{
				ps.setInt(1, nth);
			}
			System.out.println("\nThe "+nth+" th highest salry in emp table is ");
			try(ResultSet rs = ps.executeQuery();)
			{
				if(rs!=null)
				{
					if(rs.next())
					{
						System.out.println("=================================================");
						System.out.println(rs.getInt(1));
						System.out.println("---------------------------------------");
					}
					else
					{
						System.out.println("No records Found"); 
					}
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
	}//main
}//class
