package project_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Data_Retreving 
{
	//public static final String Get_Data = "SELECT * FROM LOGIN_DETAILS_FETCH WHERE USER_ID = ?";

	public static void main(String[] args) 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try(Connection conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
				//PreparedStatement ps = conn.prepareStatement("Get_Data");
				Scanner sc = new Scanner(System.in);
				)
			{
			System.out.println("EnterID : ");
			String l_id = sc.nextLine();
			System.out.println("Enter PAssword : ");
			String passwd = sc.nextLine();
			
				Statement s = conn.createStatement();
				
				ResultSet rs = s.executeQuery("SELECT * FROM LOGIN_DETAILS_FETCH WHERE USER_ID = 10");
				
				//ps.setString(1,l_id);
				//ps.setString(2, passwd);
				
				
				//ResultSet rs = ps.executeQuery(Get_Data);
				
				while(rs.next())
				{
					if(l_id.equals(rs.getString(1)) && passwd.equals(rs.getString(2)))
					{
						System.out.println(rs.getString(1)+" "+rs.getString(2));
					}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			

	}
}
