package com.nt.jdbc1;

import java.text.SimpleDateFormat;

public class DateConversionTest 
{
	public static void main(String[] args) throws Exception
	{
		String d1="10-12-2020";//dd-MM-yyyy
		//Convert String date calue to java.text.SimpleDateFormat class object
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1 = sdf.parse(d1);
		System.out.println("util Date :: "+ud1);
		
		//converting java.util.Date class object to java.sql.Date class object
		
		long ms = ud1.getTime();//get Date in MilliSeconds w.r.t. jan 1st 1970 00:00 hours
		java.sql.Date sqd1 = new java.sql.Date(ms);
		System.out.println("sql Date is :: "+sqd1);
		
		//if String date value pattern is "yyyy-MM-dd" then it can directly converted directly to 
		//java.sql.Date class obj without converting into java.util.Date class obj 
		
		String s2="1990-10-20";//yyyy-MM-dd
		
		java.sql.Date sqd2 = java.sql.Date.valueOf(s2);
		System.out.println(sqd2);
		
		System.out.println("================================================================================");
		String t1= "20:10:20";//hh:mm:ss
		//converting String time values to java.sql.Time object
		
		java.sql.Time sqt = java.sql.Time.valueOf(t1);
		System.out.println("sql time :: "+sqt);
		
		//converting String Date and time to java.sql.Timestamp obj 
		
		String dt1="10-20-1990 12:12:34"; //dd-MM-yyyy hh:mm:ss
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date ud2 = sdf2.parse(dt1);
		long ms2 = ud2.getTime();
		java.sql.Timestamp sqts = new java.sql.Timestamp(ms2);
		System.out.println(sqts);
		
	}
}
