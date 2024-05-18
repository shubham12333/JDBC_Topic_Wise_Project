package txMgmt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class ConnPoolTest
{
	private final static String GET_STUDENTS_QUERY = "SELECT * FROM STUDENT ";
	public static void main(String[] args) 
	{
		OracleConnectionPoolDataSource ds = null;
		
		try
		{
			//create DataSource Object
			ds = new OracleConnectionPoolDataSource();
			//set jdbc properties to it
			
			ds.setDriverType("thin");
			ds.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ds.setUser("system");
			ds.setPassword("9907829926");
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//get pooled jdbc con object
		
		try(Connection con = ds.getConnection();
		
			PreparedStatement ps = con.prepareStatement(GET_STUDENTS_QUERY);
			ResultSet rs = ps.executeQuery();)
		{
			if(rs!=null)
			{
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
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
		
		
		
	}//main
}//class
