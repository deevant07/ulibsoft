package org.ulibsoft.util;

public class DriverConnection {

  public DriverConnection() {
  }
  static void driverInfo() {
     try {
     //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
     //Driver drv = DriverManager.getDriver("jdbc:odbc:Hello");
     //DriverManager.registerDriver(drv);
     //System.out.println("b4");
     //Connection con = DriverManager.getConnection("jdbc:odbc:Hello");

         String drivers = null;

        /*try {
	    drivers = (String) java.security.AccessController.doPrivileged(
		new sun.security.action.GetPropertyAction("jdbc.drivers"));
            System.out.println("11"+drivers);
        } catch (Exception ex) {
           System.out.println("1 "+ex.getMessage());
            drivers = null;
        }*/

     System.out.println(""+drivers);
     }
     catch(Exception e) {
         System.out.println(""+e.getMessage());
     }
  }
  public static void main(String a[]) {
    DriverConnection.driverInfo();
     //new  DriverConnection();
  }
}