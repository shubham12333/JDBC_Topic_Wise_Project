package PACK1;

import java.text.SimpleDateFormat;

public class ClassZ 
{
	public static void main(String[] args) throws Exception 
	
	{
		String date = "23-02-2000";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date udob =sdf.parse(date);
		long ms = udob.getTime();
		java.sql.Date sqdob = new java.sql.Date(ms);
		System.out.println(sqdob);
	}
}
