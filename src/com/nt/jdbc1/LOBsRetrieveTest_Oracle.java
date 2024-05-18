package com.nt.jdbc1;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

//import org.apache.commons.io.IOUtils;

public class LOBsRetrieveTest_Oracle 
{
	private static final String GET_ACTOR_INFO_BY_ID="SELECT * FROM ACTOR_INFO   WHERE AID=?";
	
	public static void main(String[] args) 
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			PreparedStatement ps = con.prepareStatement(GET_ACTOR_INFO_BY_ID);
				)
		{
			int aid=0;
			if(sc!=null)
			{
				System.out.println("Enter Actor Id : ");
				aid=sc.nextInt();
			}//if
			
			//set values to query params
			if(ps!=null)
			{
				ps.setInt(1, aid);
			}
			
			//execute the query 
			try(ResultSet rs = ps.executeQuery())
			{
				if(rs!=null)
				{
					if(rs.next())
					{
						int id=rs.getInt(1);
						String name = rs.getString(2);
						try( InputStream is =rs.getBinaryStream(3);//read Lobs from ResultSet as streams
							Reader reader = rs.getCharacterStream(4);
								//create empty destination  file using streams
								OutputStream os = new FileOutputStream("retrieve_photo.jpg");
								Writer writer  = new FileWriter("retrieve_profile.txt");
								){
							//copy LOBs collected from Db table to destination files 
							IOUtils.copy(is,os);
							IOUtils.copy(reader,writer);
							System.out.println("actor info : "+id+" "+name+" LOBs are retrieved ");
						}//try3
					}//if
					else
					{
						System.out.println("Actor Not Found");
					}
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
	}
}
