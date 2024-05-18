package com.nt.jdbc1;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetExaple 
{
	public static void main(String[] args) 
	{
		try(JdbcRowSet jrowset = new OracleJDBCRowSet())
		{
			//set jdbc properties 
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			jrowset.setUsername("system");
			jrowset.setPassword("9907829926");
			//set query
			jrowset.setCommand("SELECT * FROM STUDENT ");
			//EXECUTE QUERY
			jrowset.execute();
			//process the results
			while(jrowset.next())
			{
				System.out.println(jrowset.getInt(1)+" "+jrowset.getString(2)+" "+jrowset.getString(3)+" "+jrowset.getInt(4));
			}//while
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
