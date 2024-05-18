package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.processing.AbstractProcessor;

public class PropertiesFileTest {

	public static void main(String[] args) 
	{
		try(InputStream is = new FileInputStream("src/com/nt/commons/Info.properties");)
		{
			Properties props = new Properties();
			props.load(is);
			
			System.out.println("Properties Class obj : "+props);
			System.out.println("per.name key value :: "+props.getProperty("per.name"));
			System.out.println("per.age key value : "+props.getProperty("per.age"));
			System.out.println("per.addrs key value :: "+props.getProperty("per.addrs"));
			System.out.println("per.qlfy key value :: "+props.getProperty("per.qlfy"));
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
