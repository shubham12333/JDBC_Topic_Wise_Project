package com.nt.callableSt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ps_UpdateAbleRS_Test 
{
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement("SELECT SNO , SNAME ,CITY , AVG FROM STUDENT"
														,ResultSet.TYPE_SCROLL_INSENSITIVE
														,ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
			)
		{
			if(rs!=null)
			{
				System.out.println("Select Operation ");
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				
					/*rs.moveToInsertRow();
					rs.updateInt(1,111);
					rs.updateString(2,"demo");
					rs.updateString(3,"Vizag");
					rs.updateFloat(4,75);
					
					rs.insertRow();
					System.out.println("Record Inserted");*/
					
					//update Operation
					
					/*rs.absolute(4);
					rs.updateString(3,"delhi");
					rs.updateRow();
					
					rs.absolute(1);
					rs.deleteRow();
					*/
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
