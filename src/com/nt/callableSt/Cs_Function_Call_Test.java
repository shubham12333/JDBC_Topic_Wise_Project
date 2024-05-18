package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_Function_Call_Test 
{
	private final static  String CALL_FUNCTION_QUERY = "{?= call FX_GET_STUD_DETAILS_BY_SNO(?,?,?)}";
	
	public static void main(String[] args) 
	{
		
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			CallableStatement cs = con.prepareCall(CALL_FUNCTION_QUERY);
			)
		{
			int no=0;
			if(sc!=null)
			{
				System.out.println("Enter Student Number :: ");
				no=sc.nextInt();
			}
			
			if(cs!=null)
			{
				//register RETURN, OUT params with JDBC data types
				cs.registerOutParameter(1,Types.FLOAT);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4,Types.VARCHAR);
				
				//set values to IN params 
				cs.setInt(2, no);
				
				//call the PL/SQL function
				cs.execute();
				//gather Result from OUT, RETURN Paramms
				System.out.println("Student Name :: "+cs.getString(3));
				System.out.println("Student Address :: "+cs.getString(4));
				System.out.println("Student Avg :: "+cs.getFloat(1));
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
	}//main
}//class
