package com.nt.jdbc1;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDeo 
{
	public static void main(String[] args) 
	{
		try(WebRowSet wrs = new OracleWebRowSet())
		{
			//set jdbc properties
			wrs.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			wrs.setUsername("system");
			wrs.setPassword("9907829926");
			//set qery
			wrs.setCommand("SELECT * FROM STUDENT ");
			//execute the query
			wrs.execute();
			//process the result
			while(wrs.next())
			{
				System.out.println(wrs.getInt(1)+" "+wrs.getString(2)+" "+wrs.getString(3)+" "+wrs.getFloat(4));
			}//while
			System.out.println("--------------------");
			wrs.writeXml(System.out);
			System.out.println("--------------------");
			Writer writer = new FileWriter("student.xml");
			wrs.writeXml(writer);
			writer.flush();
			writer.close();
			
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
