package org.ulibsoft.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JOptionPane;

public final class LoadProperties {
	static Properties props;
	static String fname = "DB_Files/dbprops.properties";
	static FileReader fread;
	static FileWriter fwrite;
	
	static{
		props=new Properties();
	}

	/**
	 * @author Deevan Kumar
	 * @param key
	 * @param value
	 * Writes the properties to the file.
	 */
	public static void writeProperties(String key, String value)
	{	
		try {
			fwrite=new FileWriter(fname,true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		props.setProperty(key,value);		
		props.list(new PrintWriter(fwrite,true));
		try {
			fwrite.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
		/** 
	    * Reads the properties file to load database server and user information.
	    * @return Properties 
	    * @exception IOException
	    * @author Deevan Kumar.<br>  
	    */
	public static Properties readProperties()
	{
		if ( props != null){
			try {
				props.load(new FileInputStream("DB_Files/dbprops.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return props;		
	}
}
