package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Lob_Insertion_Test_Oracle 
{
	private final static String ACTOR_INSERT_QUERY ="INSERT INTO ACTOR_INFO VALUES(AID_SEQ.NEXTVAL,?,?,?)";
	
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","9907829926");
			PreparedStatement ps = con.prepareStatement(ACTOR_INSERT_QUERY);
				){
			//read input values from enduser
			String aname=null,photoPath=null,resumePath=null;
			if(sc!=null)
			{
				System.out.println("Enter Actor Name :: ");
				aname = sc.nextLine();
				System.out.println("Enter Photo Path :: ");
				photoPath = sc.nextLine();
				System.out.println("Enter Resume Path :: ");
				resumePath = sc.nextLine();
			}
			try(InputStream is = new FileInputStream(photoPath);
				Reader reader = new FileReader(resumePath);
					)
			{
				if(ps!=null)
				{
					//set values to Query Params
					ps.setString(1,aname);
					ps.setBinaryStream(2,is);
					ps.setCharacterStream(3,reader);
					//execute the Query
					int count = ps.executeUpdate();
					//process the Result
					if(count==0)
					{
						System.out.println("Record Not Inserted");
					}
					else
					{
						System.out.println("Record Inserted");
					}
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
