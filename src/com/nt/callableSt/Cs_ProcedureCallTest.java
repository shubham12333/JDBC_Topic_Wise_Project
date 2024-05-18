package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_ProcedureCallTest
{
	private final static String CALL_PROCEDURE="{call p_sum(?,?,?)}";
	
	public static void main(String[] args)
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			Scanner sc = new Scanner(System.in);
			CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
			)
		{
			int val1=0,val2=0;
			
			if(sc!=null)
			{
			System.out.println("Enter Value 1: ");
			val1=sc.nextInt();
			System.out.println("Enter Values 2: ");
			val2=sc.nextInt();
			}
			
			if(cs!=null)
			{
				cs.registerOutParameter(3, Types.INTEGER);
				
				cs.setInt(1, val1);
				cs.setInt(2, val2);
				
				cs.execute();
				
				int result = cs.getInt(3);
				System.out.println("Sum is  :: "+result);
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
