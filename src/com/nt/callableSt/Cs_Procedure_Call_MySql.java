package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Cs_Procedure_Call_MySql 
{
	private static final String Call_Procedure="{call P_GET_PROD_DETAILS_BY_PRICE_RANGE(?,?)}";
	
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","root");
			CallableStatement cs = con.prepareCall(Call_Procedure);
			Scanner sc = new Scanner(System.in);
			)
		{
			float startRange=0,endRange=0;
			if(sc!=null)
			{
				System.out.println("Enter Lowest Range for Product : ");
				startRange=sc.nextFloat();
				System.out.println("Enter Highest Range for Product :: ");
				endRange = sc.nextFloat();
			}
			if(cs!=null)
			{
				cs.setFloat(1,startRange);
				cs.setFloat(2, endRange);
				
				cs.execute();
				
				try(ResultSet rs = cs.getResultSet())
				{
					while(rs.next())
					{
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4));
					}
				}
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
