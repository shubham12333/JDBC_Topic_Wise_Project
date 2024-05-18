package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Date_Time_Insert_Oracle 
{
private final static String INSERT_CUSTOMER_QUERY="INSERT INTO CUSTOMER_INFO VALUES(CNO_SEQ.NEXTVAL,?,?,?,?,?)";
	
	public static void main(String[] args)throws Exception
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER_QUERY);
				Scanner sc = new Scanner(System.in);
				){
			//read input values
			String name=null,sdob=null,stop=null,sorderdt=null;
			float billamt=0.0f;
			if(sc!=null)
			{
				System.out.println("Enter Customer Name :: ");
				name=sc.nextLine();
				System.out.println("Enter Customer Bill Amt :: ");
				billamt = sc.nextFloat();
				System.out.println("Enter DOB(dd-MM-yyyy) :: ");
				sdob=sc.next();
				System.out.println("Enter TOP(hh:mm:ss) :: ");
				stop=sc.next();
				System.out.println("Enter Order Date Time(dd/MM/yyyy hh:mm:ss :: ");
				sc.nextLine();
				sorderdt = sc.next();
				
			}//if
			//convert String DOB to java.sql.Date class obj 
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf1.parse(sdob);//String Date to util Date
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);//util Date to sql date
			
			//convert String TOP(time of purchase) to java.sql.Time obj
			java.sql.Time sqtop = java.sql.Time.valueOf(stop);
			
			//convert String Order date time to java.sql.Timestamp obj
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			java.util.Date uorderdt = sdf1.parse(sorderdt); //String Date to utill Date
			long ms1 = uorderdt.getTime();
			java.sql.Timestamp sqorderdt = new java.sql.Timestamp(ms1);// util Date to sql Timestamp obj
			
			//set values SQL Query params
			
			if(ps!=null)
			{
				ps.setString(1,name);
				ps.setFloat(2, billamt);
				ps.setDate(3, sqdob);
				ps.setTime(4, sqtop);
				ps.setTimestamp(5, sqorderdt);
				
				//execute the SQL Query
				int count = ps.executeUpdate();
				
				if(count==0)
					System.out.println("Record not inserted");
				else
					System.out.println("Recod Inserted");
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
	}//main
}
