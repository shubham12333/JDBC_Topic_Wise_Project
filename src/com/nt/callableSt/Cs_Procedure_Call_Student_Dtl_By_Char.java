package com.nt.callableSt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class Cs_Procedure_Call_Student_Dtl_By_Char 
{
	private final static String query="{ call P_GET_EMP_DETAILS_ENAME_CHARS(?,?)}";
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			Scanner sc = new Scanner(System.in);
			CallableStatement cs = con.prepareCall(query);
			)
		{
			//READ INPUTS
			String initChars=null;
			if(sc!=null)
			{
				System.out.println("Enter Intial Character :: ");
				initChars = sc.next();
			}
			if(cs!=null)
			{
				cs.registerOutParameter(2,OracleTypes.CURSOR);
				
				//Set values to IN params
				cs.setString(1, initChars+"%");
				//call Pl/SQL Procedure
				cs.execute();
				
				try(ResultSet rs = (ResultSet)cs.getObject(2))
				{
					if(rs!=null)
					{
						boolean isRSEmpty = false;
						while(rs.next())
						{
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
						}
					}
				}
			}
		}//try
		catch(SQLException se)
		{
			if(se.getErrorCode()==1403)
				System.out.println("EMP Number Not Found");
			else if(se.getErrorCode()==1017)
				System.out.println("Invalid Credintials");
			else
				System.out.println("Some DB Problem");
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}//main
}//class
