package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest_Mysql 
{
	public static void main(String[] args) 
	{
		try(Connection conn = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB","root","root");
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM PRODUCT");
				){
			//PROCESS THE RESULT
			if(rs!=null)
			{
				boolean rsEmpty = true;
				while(rs.next())
				{
					rsEmpty=false;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
				}//while
				if(rsEmpty)
				{
					System.out.println("No records found");
				}
				else
				{
					System.out.println("Records found and displayed");
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
