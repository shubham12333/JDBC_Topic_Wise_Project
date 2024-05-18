package batery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class tables {
    public static void main(String[] args)
    {
     Connection con = null;
     Statement st= null;
     try{
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NTAJ415DB","root","root"); //url daal tu
         st=con.createStatement();
         st.executeUpdate("create table users(name varchar(255),email varchar(255), password varchar(50),securityQuestion varchar(500),answer varchar(200),address varchar(200),status varchar(20))");
         JOptionPane.showMessageDialog(null,"Table Created Successfully");
     }
     catch(Exception e)
     {
         JOptionPane.showMessageDialog(null, e);
        
     }
    
     finally
     { 
        try
        {
            con.close();
            st.close();
        }
        catch(Exception e)
        {
        
        }
     }
    }
}
