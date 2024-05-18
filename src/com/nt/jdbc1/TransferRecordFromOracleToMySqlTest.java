package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferRecordFromOracleToMySqlTest 
{
	private static final String MySql_Insert_ProductQuery="INSERT INTO PRODUCT VALUES(?,?,?,?)";
	private static final String Oracle_Select_Query = "SELECT * FROM PRODUCT";
	
	public static void main(String[] args) 
	{
		try(Connection oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				Connection mysqlCon = DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","root");
				Statement st = oraCon.createStatement();
				PreparedStatement ps = mysqlCon.prepareStatement(MySql_Insert_ProductQuery);
				){
		try(ResultSet rs = st.executeQuery(Oracle_Select_Query))
		{
			//process the resultSet and also insert records to mysql Db s/w
			int count = 0;
			if(rs!=null && ps!=null)
			{
				while(rs.next())
				{
					//get each record from Oracle Db Table
					int id = rs.getInt(1);
					String name = rs.getString(2);
					float price = rs.getFloat(3);
					float qty = rs.getFloat(4);
					//set the above values INSERT SQL Query param values to insert the record to mysql db table
					ps.setInt(1, id);ps.setString(2, name); ps.setFloat(3,price); ps.setFloat(4, qty);
					
					//execute Query
					int result = ps.executeUpdate();
					//process the result
					
					if(result == 0)
					{
						System.out.println("record not inserted");
					}
					else
					{
						System.out.println("Record Inserted");
						
					}
					count++;
					
				}//while
				System.out.println(count+" record insertes");
			}//if
		}//try2
		}//try1
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
