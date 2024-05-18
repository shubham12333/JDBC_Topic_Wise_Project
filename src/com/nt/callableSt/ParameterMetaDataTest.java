package com.nt.callableSt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest 
{
	private static final String INSERT_QUERY = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement(INSERT_QUERY);
			)
		{
			ParameterMetaData pmd = null;
			if(ps!=null)
			{
				pmd=ps.getParameterMetaData();
				
			}
			if(pmd!=null)
			{
				int paramsCount = pmd.getParameterCount();
				for(int i =1;i<=paramsCount;++i)
				{
					System.out.println("Parameter Index : "+i);
					System.out.println("Param Mode : "+pmd.getParameterMode(i));
					System.out.println("Is param Signed : "+pmd.isSigned(i));
					System.out.println("Param Scale : "+pmd.getScale(i));
					System.out.println("param Precision : "+pmd.getPrecision(i));
					System.out.println();
					System.out.println("*******************************************");
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
