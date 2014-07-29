package org.ulibsoft.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public final class DBARegistration extends JFrame {
  static Connection con;
  static Statement stmt;
  static ResultSet rs;
  private Container cont;

  private JPanel system, user;
  
  private JLabel sysUserLbl,sysPwdLbl,libUserLbl,libPwdLbl;
  private JTextField sysUserText,libUserText;
  private JPasswordField sysPwdText,libPwdText;
  
  private JButton next,cancel,submit;
  
  public DBARegistration() {
	  super("DATABASE REGISTRATION");
	  ScreenResolution.getResolution();
	  setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
      setBounds(getWidth()/4+getWidth()/20, getHeight()/4, getWidth()/3+getWidth()/10, getHeight()/2);
      cont=getContentPane();
      cont.setLayout(new AbsoluteLayout());
      cont.setBackground( new Color( 0, 0, 40 ));      
      
      createComponents();
      componentListener();
}
  private void createComponents()
  {
	  system=new JPanel(new AbsoluteLayout());
      system.setBackground(cont.getBackground ());
      system.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                        "SYSTEM INFORMATION", 1, 2,cont.getFont(), Color.magenta));
      cont.add(system,new AbsoluteConstraints(20,20,300,250));
      
      user=new JPanel(new AbsoluteLayout());
      user.setBackground(cont.getBackground ());
      user.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                        "CREATE USER INFORMATION", 1, 2,cont.getFont(), Color.magenta));
      cont.add(user,new AbsoluteConstraints(20,20,300,250));
      
      user.setVisible(false);
      
      sysUserLbl = new JLabel( "USER" );
      sysUserLbl.setForeground ( new Color ( 120, 120, 153 ) );
      system.add ( sysUserLbl, new AbsoluteConstraints(50, 80) );
      
      sysUserText = new JTextField( );
      sysUserText.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
      sysUserText.setForeground ( new Color ( 255, 0, 153 ) );
      sysUserText.setCaretColor ( new Color ( 0, 204, 102 ) );
      system.add ( sysUserText, new AbsoluteConstraints( 135, 80, 125, 20 ) );
      
      sysPwdLbl = new JLabel( "PASSWORD" );
      sysPwdLbl.setForeground ( new Color ( 120, 120, 153 ) );
      system.add ( sysPwdLbl, new AbsoluteConstraints(50, 105) );
      
      sysPwdText = new JPasswordField();
      sysPwdText.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
      sysPwdText.setForeground ( new Color ( 255, 0, 153 ) );
      sysPwdText.setCaretColor ( new Color ( 0, 204, 102 ) );
      system.add ( sysPwdText, new AbsoluteConstraints( 135, 105, 125, 20 ) );
      
      next=new JButton("NEXT >>");
      next.setBackground(Color.cyan);
      next.setForeground(Color.magenta);
      next.setMnemonic ('E');
      next.setBorder(new BevelBorder(0));
      system.add(next,new AbsoluteConstraints(50,140,100,25));

      cancel=new JButton("CANCEL");
      cancel.setBackground(Color.cyan);
      cancel.setForeground(Color.magenta);
      cancel.setMnemonic ('C');
      cancel.setBorder(new BevelBorder(0));
      system.add(cancel,new AbsoluteConstraints(155,140,100,25)); 
      
      
      submit=new JButton("SUBMIT");
      submit.setBackground(Color.cyan);
      submit.setForeground(Color.magenta);
      submit.setMnemonic ('E');
      submit.setBorder(new BevelBorder(0));
      user.add(submit,new AbsoluteConstraints(50,140,100,25));
      
      setVisible(true);
  }
  
  private void componentListener()
  {	  
	  next.addActionListener(new ActionListener()
	  {
		public void actionPerformed(ActionEvent e) {
			if ( getDBAInfo(sysUserText.getText(),sysPwdText.getText()) )
			{
				user.setVisible(true);
				system.setVisible(false);
				sysUserText.setText("");
				sysPwdText.setText("");
				user.add ( sysUserLbl, new AbsoluteConstraints(50, 80) );
				user.add ( sysUserText, new AbsoluteConstraints( 135, 80, 125, 20 ) );
				user.add ( sysPwdLbl, new AbsoluteConstraints(50, 105) );
				user.add ( sysPwdText, new AbsoluteConstraints( 135, 105, 125, 20 ) );
				user.add(cancel,new AbsoluteConstraints(155,140,100,25)); 
			}
				
		}  
	  }
	  );
	  submit.addActionListener(new ActionListener()
	  {
		public void actionPerformed(ActionEvent e) {
			if ( createUserInfo(sysUserText.getText(),String.valueOf(sysPwdText.getPassword())) == 0)
			   if (importTables(sysUserText.getText(), String.valueOf(sysPwdText.getPassword()), "DB_Files/ora_tables.sql") )
				   if (importTables(sysUserText.getText(), String.valueOf(sysPwdText.getPassword()), "DB_Files/ora_AuditTables.sql") )
					   if (importTriggers("DB_Files/ora_triggers.sql") ){
						   JOptionPane.showMessageDialog(null, "DataBase Setup completed Successfully..");						   
					   }
						   
			
			
		}  
	  }
	  );
	  cancel.addActionListener(new ActionListener()
	  {
		  public void actionPerformed(ActionEvent e) {			
			System.exit(0);
		}
	  }
	  );
  }
  public static boolean getDBAInfo(String dbaName, String dbaPassword) {
     try
     {
       if ( con == null )	 
       con = MyDriver.getSystemConnection(dbaName, dbaPassword);
       //System.out.println(con);
       return !con.isClosed();
     }
     catch(Exception se) {
      se.printStackTrace();
     }
    return false;
  }

  public static boolean getUserInfo(String dbaName, String dbaPassword) {
     try
     {
       if ( con == null )
       con = MyDriver.getSystemConnection(dbaName, dbaPassword);
       //System.out.println(con);
       return !con.isClosed();
     }
     catch(Exception se) {
      se.printStackTrace();
     }
    return false;
  }

 public static int createUserInfo(String dbName, String dbPassword) {
     try
     {
       stmt = con.createStatement();
       rs = stmt.executeQuery("SELECT USERNAME FROM ALL_USERS WHERE USERNAME="+"'"+dbName.toUpperCase()+"'");
       if(rs.next()) {
        return 1;
       }
       else {
	   if (!stmt.execute("CREATE USER "+dbName+" IDENTIFIED BY "+dbPassword))
		  if( !stmt.execute("GRANT CONNECT,RESOURCE,DBA TO "+dbName) )
			  return 0;
		  else
			  return 1;
	   else
		   return 1;
       }
     }
     catch(SQLException se) {
    	 if ( se.getErrorCode() == 1920)
    		 JOptionPane.showMessageDialog(null, "USER ALREADY EXISTS PLEASE CHOOSE A DIFFERENT USER","WARNING",JOptionPane.WARNING_MESSAGE);
      se.printStackTrace();
     }
     finally{
    	 if(rs != null)
    	 {
    		 try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
    	 }
     }
     return 0;
  }

 public static boolean importDB(String dbName, String dbPassword) {
     return true;
 }

 public static boolean importTables(String dbName, String dbPassword,String fName) {
	/* Runtime rt=Runtime.getRuntime();
	 rt.exec("IMP "+dbName+"//"+dbPassword +"FULL=Y FILE="+fName+" ROWS=Y CONSTRAINTS=Y");
	 */	
	 
	 try {		 	
			con=MyDriver.getSystemConnection(dbName, dbPassword);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
	 try {
		BufferedReader bfr=new BufferedReader(new FileReader(new File(fName)));
		String oraTables;		
		String createQuery="";
		while ( (oraTables = bfr.readLine()) != null )
		{
			if ( oraTables.contains(";"))
			{
				createQuery+=oraTables.split(";")[0].trim();
				System.out.println(createQuery);
				runQuery(createQuery);
				createQuery="";
			}else{
				createQuery+=oraTables.trim();
			}			
		}
	} catch (FileNotFoundException e) {		
		e.printStackTrace();
		return false;
	}catch (IOException ie){
		ie.printStackTrace();
		return false;
	}catch (SQLException ie){
		ie.printStackTrace();
		return false;
	}
	return true;
 }
public static void runQuery(String createQuery) throws SQLException{
	
		stmt=con.createStatement();
		stmt.execute(createQuery);
	
}
public static boolean importTriggers(String fName)
{	
	try {
		BufferedReader bfr=new BufferedReader(new FileReader(new File(fName)));
		String oraTrigers;		
		String createQuery="";
		while ( (oraTrigers = bfr.readLine()) != null )
		{
			if ( oraTrigers.startsWith("--"))
				continue;
			if ( oraTrigers.contains("/"))
			{
				createQuery+=" ";
				System.out.println(createQuery);
				runQuery(createQuery);
				createQuery="";
			}else{
				createQuery+=" "+oraTrigers;
			}
			//createQuery+=oraTrigers;
		}
		
	} catch (FileNotFoundException e) {		
		e.printStackTrace();
		return false;
	}catch (IOException ie){
		ie.printStackTrace();
		return false;
	}catch (SQLException ie){
		ie.printStackTrace();
		return false;
	}finally{
		
		
			try {
				if ( stmt != null )
					stmt.close();
				if ( con != null)
					 con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    
	}
	return true;	
}
 public static void main(String[] args) {
	DBARegistration reg=new DBARegistration();	
}
}
