package com.nt.callableSt;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Capabilities_Test 
{
	public static void main(String[] args) 
	{
		try(//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","root");
			)
		{
			DatabaseMetaData dbmd=null;
			
			if(con!=null)
			{
				dbmd = con.getMetaData();
			}
			
			if(dbmd!=null)
			{
				System.out.println(" DB s/w Name :: "+dbmd.getDatabaseProductName());
				System.out.println("DB s/w verison :: "+dbmd.getDatabaseProductVersion());
				System.out.println("DB s/w version :: "+dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion());
				System.out.println("JDBC Driver Name ::  "+dbmd.getDriverName());
				System.out.println("JDBC version :: "+dbmd.getJDBCMajorVersion()+"."+dbmd.getJDBCMinorVersion());
				System.out.println("Max Connection Count :: "+dbmd.getMaxConnections());
				System.out.println("Max DB table name length :: "+dbmd.getMaxTableNameLength());
				System.out.println("Max UserName in Lenght :: "+dbmd.getMaxUserNameLength());
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
