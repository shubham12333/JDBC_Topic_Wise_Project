package com.nt.jdbc1;


import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo 
{
	public static void main(String[] args) 
	{
		try(CachedRowSet crw = new OracleCachedRowSet())
		{
			//set jdbc properties
			crw.setUrl("jbdc:oracle:thin:@localhost:1521:orcl");
			crw.setUsername("system");
			crw.setPassword("9907829926");
			//set query
			crw.setCommand("SELECT * FROM STUDENT");
			//EXECUTE QUERY
			crw.execute();
			//process the results
			while(crw.next())
			{
				System.out.println(crw);
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
