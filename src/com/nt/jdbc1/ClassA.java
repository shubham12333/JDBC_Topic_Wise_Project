package com.nt.jdbc1;

import java.util.Calendar;
import java.util.Date;

public class ClassA 
{
	public static void main(String[] args) 
	{
		Date d = new Date();
		int m = d.getMonth();
		System.out.println(m);
		Calendar c ;
		System.out.println(Calendar.getInstance());
	}
}
