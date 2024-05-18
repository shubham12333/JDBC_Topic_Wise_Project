package com.nt.callableSt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest1 
{
	public static void main(String[] args) 
	{
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PRODUCT");
			){
			
			ResultSetMetaData rsmd = null;
			if(rs!=null)
			{
				rsmd=rs.getMetaData();
			}
			
			if(rsmd!=null)
			{
				int colCount = rsmd.getColumnCount();
				
				for(int i=1;i<=colCount;++i)
				{
					System.out.println("Column Index : "+i);
					System.out.println("Column Name : "+rsmd.getColumnName(i));
					System.out.println("Column Data Type Name : "+rsmd.getColumnTypeName(i));
					System.out.println("Cloumn Scale : "+rsmd.getScale(i));
					System.out.println("Cloumn precisiom : "+rsmd.getPrecision(i));
					System.out.println("Is Cloumn signed : "+rsmd.isSigned(i));
					System.out.println("Is Column Autoincrement : "+rsmd.isAutoIncrement(i));
					System.out.println("Is Column Nullable : "+rsmd.isNullable(i));
					System.out.println("Is Column Currency : "+rsmd.isCurrency(i));
					System.out.println("Is Column SearchAble : "+rsmd.isSearchable(i));
					System.out.println("Column Display Size  : "+rsmd.getColumnDisplaySize(i));
					System.out.println("Is Column Writeable : "+rsmd.isWritable(i));
					System.out.println("**************************************************");
				}
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
