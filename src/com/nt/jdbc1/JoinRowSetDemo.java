package com.nt.jdbc1;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class JoinRowSetDemo 
{
	public static void main(String[] args) 
	{
		try(OracleCachedRowSet crs1 = new OracleCachedRowSet();
			OracleCachedRowSet crs2 = new OracleCachedRowSet();
			OracleJoinRowSet ojr = new OracleJoinRowSet())
		{
			crs1.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			crs1.setUsername("system");
			crs1.setPassword("9907829926");
			crs1.setMatchColumn(5);
			crs1.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP ");
			crs1.execute();
			
			
			crs2.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			crs2.setUsername("system");
			crs2.setPassword("9907829926");
			crs2.setMatchColumn(1);
			crs2.setCommand("SELECT DEPTNO,DNAME,LOC FROM DEPT");
			crs2.execute();
			
			
			ojr.addRowSet(crs2);
			ojr.addRowSet(crs1);
			
			//process the JoinRowSet
			
			while(ojr.next())
			{
				System.out.println(ojr.getInt(1)+" "+ojr.getString(2)+" "+ojr.getString(3)+" "+ojr.getString(4)+" "+ojr.getString(5)+" "+ojr.getString(6)+" "+ojr.getString(7));
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
