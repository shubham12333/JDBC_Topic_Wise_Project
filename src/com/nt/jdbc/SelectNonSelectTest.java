package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest 
{
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
				Statement st = con.createStatement();
				){
			//read the inputs
			String query = null;
			if(sc!=null) {
				System.out.println("Enter the SQL Query");
				query = sc.nextLine();
			}//if
			//send and execute the SQL Query in Db s/w 
			if(st!=null) {
				boolean flag = st.execute(query);
				if(flag) {
					System.out.println("SELECT SQL Query is executed");
					//get the ResultSet Object
					try(ResultSet rs = st.getResultSet()){//nested try with resource
						//process the ResultSet 
						if(rs!=null) {
							while(rs.next()) {
								System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
							}//while
						}//if
					}//try2
				}
				else
				{
					System.out.println("Non-Select SQL Query is executed");
					int count = st.getUpdateCount();
					System.out.println("no. of records that are effected :: "+count);
				}//else
			}//if
		}//try1
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
