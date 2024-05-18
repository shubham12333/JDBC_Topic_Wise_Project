package batery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class data_Store 
{
	private final static String QUERY = "INSERT INTO USERS1 VALUES(?,?,?,?,?,?)";
	
	public static void main(String[] args) 
	
	{
		try(Scanner sc = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj415db","root","root");
			PreparedStatement ps = con.prepareStatement(QUERY);
			)
		{
			if(sc!=null && ps!=null)
			{
				System.out.println("Enter Name : ");
				String name = sc.nextLine();
				System.out.println("Enter Email : ");
				String email = sc.nextLine();
				System.out.println("Enter Password : ");
				String pass = sc.nextLine();
				System.out.println("Enter Security Question : ");
				String sc_que = sc.nextLine();
				System.out.println("Enter Answer : ");
				String answer = sc.nextLine();
				System.out.println("Enter Address : ");
				String addrss  = sc.nextLine();
				
				//Setting data to PreparedStatement Query 
				
				ps.setString(1,name);
				ps.setString(2, email);
				ps.setString(3, pass);
				ps.setString(4, sc_que);
				ps.setString(5, answer);
				ps.setString(6, addrss);
				
				int result = ps.executeUpdate();
				
				if(result==0)
				{
					System.out.println("Data Not Inserted");
				}
				else
				{
					System.out.println("Data Inserted");
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

