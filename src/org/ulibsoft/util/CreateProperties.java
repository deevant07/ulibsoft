package org.ulibsoft.util;

import java.io.*;
import java.util.Properties;


public class CreateProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeProperties();		
		readProperties();
		System.exit(0);
	}
	public static void readProperties()
	{
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream("DB_Files/dbprops.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("User"+prop.getProperty("user")+"PWD"+prop.getProperty("pwd"));
	}
	public static void writeProperties()
	{
		Properties prop=new Properties();
		FileWriter fout=null;
		
		try {
			fout=new FileWriter("DB_Files/dbprops.properties",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prop.setProperty("user", "GATESLIBRARY");
		prop.setProperty("pwd", "FUTURE");
		
		prop.list(new PrintWriter(fout,true));
		try {
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
