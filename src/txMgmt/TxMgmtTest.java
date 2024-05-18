package txMgmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TxMgmtTest {

	private final static String GET_BALANCE_BY_ACNO = "SELECT BALANCE FROM JDBC_ACCOUNT_INFO WHERE ACNO=?";
	public static void main(String[] args)
	{	
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","9907829926");
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement(GET_BALANCE_BY_ACNO);
			Scanner sc = new Scanner(System.in);
			)
		{
			long srcNo=0,destNo=0;
			float amount = 0.0f;
			
			if(sc!=null)
			{
				System.out.println("Enter source Account Number :: ");
				srcNo = sc.nextLong();
				System.out.println("Enter Destination Account Number :: ");
				destNo = sc.nextLong();
				System.out.println("Enter Amount to Transfer :: ");
				amount = sc.nextFloat();
			}
			
			if(ps!=null)
			{
				ps.setLong(1, srcNo);
			}
			try(ResultSet rs = ps.executeQuery())
			{
				float balance = 0.0f;
				if(rs.next())
				{
					balance = rs.getFloat(1);
					if(amount>balance)
					{
						System.out.println("Insufficient funds in the source account (Tx aborted)");
						return;
					}
				}
				else
				{
					System.out.println("Source Account not Found");
				}
			}
			
			//begin Tx
			
			if(con!=null)
			{
				con.setAutoCommit(false);
			}
			
			if(st!=null)
			{
				//add quries to the batch for withdraw Option
				
				st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE = BALANCE-"+amount+" WHERE ACNO = "+srcNo);
				//for Deposit operation
				
				st.addBatch("UPDATE JDBC_ACCOUNT_INFO SET BALANCE = BALANCE+"+amount+" WHERE ACNO = "+destNo);
				//execute the batch query
				
				int result[] = st.executeBatch();
				
				if(result!=null)
				{
					boolean taskFlag = true;
					
					for(int i=0;i<result.length;i++)
					{
						if(result[i]==0)
						{
							taskFlag = false;
							break;
						}
					}
					
					if(taskFlag)
					{
						con.commit();
						System.out.println("Transaction Commited (Money Transferred)");
					}
					else
					{
						con.rollback();
						System.out.println("Transaction not Committed (rolledback) (Money Not Transferred)");
					}
				}//if
			}//if
		}//try
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
