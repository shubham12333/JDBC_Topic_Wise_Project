package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_ProcedureCalltest1 
{
	private final static String CALL_PROCEDURE = "{ call P_GET_EMP_DETAILS_BY_EMPNO(?,?,?)}"; 
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
			Scanner sc = new Scanner(System.in);
			)
		{
			int no = 0;
			if(sc!=null)
			{
				System.out.println("Enter Employee Number :: ");
				no=sc.nextInt();
			}
			if(cs!=null)
			{
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.FLOAT);
				
				// set values to IN PARAMS
				cs.setInt(1, no);
				//call p/sql execute
				cs.execute();
				
				System.out.println("EMP NAME :: "+cs.getString(2));
				System.out.println("EMP SALARY :: "+cs.getFloat(3));
			}//if
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}//class
}//main
