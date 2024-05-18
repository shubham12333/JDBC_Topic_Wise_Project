package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Assignment_2_PostGreSql_Select 
{
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJDB415","postgres","9907829926");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM DEMO");
				ResultSet rs = ps.executeQuery();
			)
		{
			if(rs!=null)
			{
				if(rs.next())
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getDate(3)+" "+rs.getTimestamp(4)+" "+rs.getBinaryStream(5)+" "+rs.getCharacterStream(6));
					
				}
				else
				{
					System.out.println("record not found");
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
