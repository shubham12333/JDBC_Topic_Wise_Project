package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class Cs_Procedure_Call_Test_Salary_Range 
{
	private static final String CALL_PROCEDURE = "{call P_GET_EMPS_BY_SALARY_RANGE(?,?,?)";
	
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			Scanner sc = new Scanner(System.in);
			CallableStatement cs = con.prepareCall(CALL_PROCEDURE);
			)
		{
			int startSalary=0,endSalary=0;
			if(sc!=null)
			{
				System.out.println("Enter Start Range :: ");
				startSalary=sc.nextInt();
				System.out.println("Enter End Range :: ");
				endSalary = sc.nextInt();
			}
			
			if(cs!=null)
			{
				cs.registerOutParameter(3,OracleTypes.CURSOR);
				
				cs.setInt(1,startSalary);
				cs.setInt(2,endSalary);
				
				cs.execute();
				
				try(ResultSet rs = (ResultSet)cs.getObject(3))
				{
					if(rs!=null)
					{
						while(rs.next())
						{
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getFloat(5));
						}
					}
					else
					{
						System.out.println("Please increment your emp salaries for this range");
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
	}
}
