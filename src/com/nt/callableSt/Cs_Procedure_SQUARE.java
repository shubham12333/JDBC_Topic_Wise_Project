package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Cs_Procedure_SQUARE 
{
	private static final String QUERY ="{ call p_square(?,?)}";
	
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			CallableStatement cs = con.prepareCall(QUERY);
				Scanner sc = new Scanner(System.in);
				)
		{
			int val1=0;
			if(sc!=null)
			{
				System.out.println("Enter Value1 :: ");
				val1=sc.nextInt();
			}
			if(cs!=null)
			{
				cs.registerOutParameter(2,Types.INTEGER);
				
				cs.setInt(1, val1);
				
				cs.execute();
				
				int result = cs.getInt(2);
				
				System.out.println("Square is :: "+result);
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
