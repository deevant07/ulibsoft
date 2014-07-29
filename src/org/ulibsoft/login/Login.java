/*
 * Login.java
 *
 * $Id$
 *
 *    Copyright (c) 2010 UlibSoft Technologies Ltd. All rights reserved. Use of
 *    copyright notice does not imply publication.
 *
 *                          CONFIDENTIAL INFORMATION
 *                          ------------------------
 *    This Document contains Confidential Information or Trade Secrets, or both,
 *    which are the property of UlibSoft Technologies Ltd. This document may not
 *    be copied, reproduced, reduced to any electronic medium or machine reada-
 *    ble form or otherwise duplicated and the information herein may not be us-
 *    ed, disseminated or otherwise disclosed except with the prior written con-
 *    sent of UlibSoft Technologies Ltd.
 */

package org.ulibsoft.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;
import org.ulibsoft.menu.Index;
import org.ulibsoft.menu.SendOff;
import org.ulibsoft.menu.StaffAccess;
import org.ulibsoft.menu.StudentAccess;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

/**
 *
 * @author pavan kumar kunchapu
 */

public class Login extends JFrame {
    
     private JLabel college,pwd,t1,t2,t3,t4,t5,t6;
     private JCheckBox box1,box2;
     private JPanel pnl;
     public static JTextField cname;
     public static JPasswordField pd;
     private JButton ok;
     private JButton cancel;
     private Container con;
     private ImageIcon icon;
     private Login p;

     private int stage=-1;
     private Icon i1,i2,i3,i4,i5;
     private String path="images/";

     private Connection  con1 ;
     private Statement s ;
     private ResultSet rs, rs1 ;

     public Login() {
          super("Login" );  
          ScreenResolution.getResolution();
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

          con = getContentPane();
          con.setLayout(new AbsoluteLayout());
          con.setBackground(Color.black);

        try {
            con1 = MyDriver.getConnection();
        }
        catch(Exception e)
         {
            JOptionPane.showMessageDialog(null,e.getMessage());
            System.exit(0);
         }
         createComponents();
          componentListener();
       }

     private void createComponents()
       {
         // if(stage<0)
          // {
    	 if ( ScreenResolution.SCREEN_WIDTH == 1024 && ScreenResolution.SCREEN_HEIGHT == 768)
          i1=new ImageIcon(path+"git_1024_768.gif");
    	 else
    		 i1=new ImageIcon(path+"git.gif");
    	 
          t1=new JLabel(i1);
          con.add (t1,new AbsoluteConstraints(0,0));
          
         if ( ScreenResolution.SCREEN_WIDTH == 1024 && ScreenResolution.SCREEN_HEIGHT == 768)
          i2=new ImageIcon(path+"leftimage_1024_768.gif");
         else
        	 i2=new ImageIcon(path+"leftimage.gif"); 
         
          t2=new JLabel(i2);
          con.add (t2,new AbsoluteConstraints(14,186));
          
          if ( ScreenResolution.SCREEN_WIDTH == 1024 && ScreenResolution.SCREEN_HEIGHT == 768)
        	  i4=new ImageIcon(path+"rightimage_1024_768.gif");
          else
        	  i4=new ImageIcon(path+"rightimage.gif");
          t4=new JLabel(i4);
          con.add (t4,new AbsoluteConstraints(630,186));

          i5=new ImageIcon(path+"GATE6.gif");
          t5=new JLabel(i5);
          con.add (t5,new AbsoluteConstraints(290,186));

            // stage=0;
           ///}

          pnl = new JPanel(new AbsoluteLayout());
          pnl.setBackground(con.getBackground ());
          pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "LOGIN && LOGOUT", 1, 2,con.getFont(), Color.magenta));
          con.add(pnl,new AbsoluteConstraints(250,385,300,180));

          college = new JLabel ("USER  NAME  :");
          college.setForeground(Color.cyan);
          pnl.add(college,new AbsoluteConstraints(46-15,30) );

          cname = new JTextField (  );
          cname.setForeground( Color.blue);
          cname.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.black ) );
          cname.setCaretColor ( new Color ( 0, 204, 102 ) );
          pnl.add(cname,new AbsoluteConstraints(130-10,27,150,20));

          pwd=new JLabel("PASSWORD :");
          pwd.setForeground(Color.cyan);
          pnl.add(pwd,new AbsoluteConstraints(50-15,60));

          pd=new JPasswordField( );
          pd.setForeground(Color.blue);
          pd.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.black ) );
          pd.setCaretColor ( new Color ( 0, 204, 102 ) );
          pnl.add(pd,new AbsoluteConstraints(130-10,57,150,20));

          ok=new JButton("LOGIN");
          ok.setBackground(Color.cyan);
          ok.setForeground(Color.magenta);
          ok.setMnemonic ('L');
          ok.setBorder(new BevelBorder(0));
          pnl.add(ok,new AbsoluteConstraints(50,120+25,100,25));

          cancel=new JButton("LOGOUT");
          cancel.setBackground(Color.cyan);
          cancel.setForeground(Color.magenta);
          cancel.setMnemonic ('O');
          cancel.setBorder(new BevelBorder(0));
          pnl.add(cancel,new AbsoluteConstraints(153,120+25,100,25));

          box1 = new JCheckBox();
          box1.setText("PASSWORD  FORGOTTEN");
          box1.setBackground(Color.black);
          box1.setForeground(Color.cyan);
          pnl.add(box1,new AbsoluteConstraints(70,90));

          box2 = new JCheckBox();
          box2.setText("CHANGE  PASSWORD");
          box2.setBackground(Color.black);
          box2.setForeground(Color.cyan);
          pnl.add(box2,new AbsoluteConstraints(80,115));

          setVisible( true );
       }

     private void componentListener()
       {

         box1.addItemListener(new ItemListener()
           {
              public void itemStateChanged(ItemEvent ie)
                {
                    if( ie.getStateChange() == ItemEvent.SELECTED )
                      {
                         box1.setForeground(Color.magenta);
                         new ForGottenPwd((Frame)p,"PASSWORD  FORGOTTEN");
                      }
                    if(  ie.getStateChange() == ItemEvent.DESELECTED )
                      {
                         box1.setForeground(Color.cyan);
                        // new ChangePassWordDialog((Frame)p,"CHANGE  PASSWORD");
                      }
                }
           }
         );

         box2.addItemListener(new ItemListener()
           {
              public void itemStateChanged(ItemEvent ie)
                {
                   if( ie.getStateChange() == ItemEvent.SELECTED )
                      {
                         box2.setForeground(Color.magenta);
                         new ChangePassWordDialog((Frame)p,"CHANGE  PASSWORD");
                      }
                   if(  ie.getStateChange() == ItemEvent.DESELECTED )
                      {
                         box2.setForeground(Color.cyan);
                      }
                }
           }
         );
         pd.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                  login();
                }
            }
          );
         ok.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                  login();
                }
            }
          );

          cancel.addActionListener(new ActionListener()
            {
              public void actionPerformed (ActionEvent e)
                {
                 new SendOff();
                  setVisible(false);
                }
            }
          );
       }
     
    private void login()
      {
         if(cname.getText().equalsIgnoreCase("Gates"))
                    {
                      if(new String(pd.getPassword()).equals("LIBRARY"))
                        {
                          StudentAccess.SEARCH=0;
                          StaffAccess.SEARCH=0;
                          Index cn= new Index();
                          setVisible(false);
                        }
                      else
                        {
                          JOptionPane.showMessageDialog(null,"INVALID  PASSWORD  !  .  .  .","LOGIN",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                  else
                    {
                      try
                        {
                           s = con1.createStatement();
                           rs = s.executeQuery("SELECT ADNO,PASSWORD FROM STUDENTDETAILS WHERE ADNO= '"+cname.getText().toUpperCase()+"' AND PASSWORD='"+new String(pd.getPassword())+"'");
                           if( rs.next() )
                             {
                                new StudentAccess();
                                setVisible(false);
                             }
                           else
                             {
                                rs = s.executeQuery("SELECT LID,PASSWORD FROM STAFF WHERE LID= '"+cname.getText().toUpperCase()+"' AND PASSWORD='"+new String(pd.getPassword())+"'");
                                if( rs.next() )
                                  {
                                     new StaffAccess();
                                     setVisible(false);
                                  }
                                else
                                  {
                                     JOptionPane.showMessageDialog(null,"INVALID  LOGIN  !  .  .  .");
                                  }
                             }
                        }
                      catch(SQLException sqlex)
                        {
                           JOptionPane.showMessageDialog(null,sqlex.getMessage());
                        }
                    }
      }
    
    private class ForGottenPwd extends JDialog
      {
         private JLabel msg;
         private JRadioButton yes,no2;
         private JComboBox question;
         private JLabel oldlb,newlb,pwdLabel;
         private JPasswordField new1,input;
         private JButton sub,can;
         private Container k;
         private int count=0;

         public ForGottenPwd(Frame parent,String title)
           {
              super(parent,title,true);

              k = getContentPane();
              k.setLayout(new AbsoluteLayout());
              k.setBackground(new Color(0,0,50));//new Color(120,120,180));

              k.add(msg = new JLabel("R  U  FORGOTTEN  PASSWORD  ?  .  .  ."),new AbsoluteConstraints(25,25));
              msg.setForeground(new Color(120,120,153));

              k.add( yes = new JRadioButton("YES"),new AbsoluteConstraints(25,50));
              yes.setBackground(getContentPane().getBackground());
              yes.setMnemonic('Y');
              yes.setForeground(new Color(120,120,153));

              k.add( no2  = new JRadioButton("NO"),new AbsoluteConstraints(75,50));
              no2.setBackground(getContentPane().getBackground());
              no2.setMnemonic('N');
              no2.setForeground(new Color(120,120,153));

              k.add( oldlb  = new JLabel("CHOOSE  ME  :"),new AbsoluteConstraints(25,85));
              oldlb.setForeground(new Color(120,120,153));

              k.add( question = new JComboBox(),new AbsoluteConstraints(115,83,200,20) );
              question.setBackground(k.getBackground());
              question.setForeground(new Color(120,120,153));
              question.setEnabled(false);
              question.addItem("");
              question.addItem("WT ' S  UR  PET_NAME  ?");
              question.addItem("WHO ' S  UR  FAVOURITE_PERSON  ?");
              question.addItem("WT ' S  UR  FAVOURITE_COLOR  ?");
              question.addItem("WT ' S  UR  FAVOURITE_SPORT  ?");
              question.addItem("WT ' S  UR  FAVOURITE_GAME  ?");

              k.add( newlb  = new JLabel("   HINT"),new AbsoluteConstraints(25,115));
              newlb.setForeground(new Color(120,120,153));

              input = new JPasswordField();
              input.setEditable(false);
              k.add(input,new AbsoluteConstraints(115,112,200,20));
              input.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,50) ) );

              k.add( pwdLabel = new JLabel("PASSWORD   :"),new AbsoluteConstraints(25,145));
              pwdLabel.setForeground(new Color(120,120,153));

              k.add( new1  = new JPasswordField(),new AbsoluteConstraints(115,143,200,20));
              new1.setEditable(false);
              new1.setBackground(Color.lightGray);
              new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              k.add(sub = new JButton("SUBMITT"),new AbsoluteConstraints(78-10,177,100,25));
              sub.setBackground(Color.cyan);
              sub.setEnabled(false);
              sub.setMnemonic('S');
              sub.setBorder(new BevelBorder(0));

              k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(176-7,177,100,25));
              can.setBackground(Color.cyan);
              can.setMnemonic('C');
              can.setBorder(new BevelBorder(0));

              yes.addItemListener(new ItemListener()
                {
                   public void itemStateChanged( ItemEvent e)                      {

                        no2.setEnabled(false);
                        question.setEnabled(true);
                     }
                }
              );

              no2.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        yes.setEnabled(false);
                        question.setEnabled(false);
                     }
                }
              );
              question.addItemListener(new ItemListener()
                {
                   public void itemStateChanged(ItemEvent e)
                     {
                        if(count<1)
                          {
                             input.setEditable(true);
                             new1.setEditable(true);
                             new1.setBackground(Color.white);
                             sub.setEnabled(true);
                             count++;
                          }
                     }
                }
              );
              can.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {
                       setVisible(false);
                     }
                }
              );

              sub.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {
                        if(question.getSelectedItem()==null||question.getSelectedItem().equals(""))
                          {
                             JOptionPane.showMessageDialog(null,"U  MUST  CHOOSE  ONE  QUESTION");
                          }
                        else
                          {
                             if( new String(input.getPassword()).equals("")||new String(input.getPassword())==null ){ JOptionPane.showMessageDialog(null,"U  MUST  GIVE  ONE  ANSWER"); }
                             else
                               {
                                  String password=new String(new1.getPassword());
                                  if( password==null||password.equals("") )
                                    {
                                       JOptionPane.showMessageDialog(null,"U MUST INPUT PASSWORD  !  .  .  .","WARNING",JOptionPane.INFORMATION_MESSAGE);
                                    }
                                  else
                                    {
                                  try
                                    {

                                        s = con1.createStatement();
                                       rs = s.executeQuery("SELECT QUESTION, ANSWER FROM STUDENTDETAILS WHERE QUESTION= '"+String.valueOf(question.getSelectedIndex())+"' AND ANSWER= '"+new String(input.getPassword())+"'AND ADNO='"+cname.getText().toUpperCase()+"'");
                                       if( rs.next() )
                                         {


                                            int v = s.executeUpdate("UPDATE STUDENTDETAILS SET PASSWORD='"+new String(new1.getPassword())+"' WHERE ADNO='"+cname.getText().toUpperCase()+"'");
                                            pd.setText(String.valueOf(new1.getPassword()));
                                            setVisible(false);
                                         }
                                       else
                                         {
                                             rs.close();

                                            rs1 = s.executeQuery("SELECT QUESTION,ANSWER FROM STAFF WHERE QUESTION= '"+String.valueOf(question.getSelectedIndex())+"' AND ANSWER= '"+new String(input.getPassword())+"' AND LID='"+cname.getText().toUpperCase()+"'");
                                            if( rs1.next() )
                                              {

                                                 int v = s.executeUpdate("UPDATE STAFF SET PASSWORD='"+new String(new1.getPassword())+"'WHERE LID='"+cname.getText().toUpperCase()+"'");
                                                 pd.setText(String.valueOf(new1.getPassword()));
                                                 setVisible(false);
                                              }
                                            else
                                              {
                                                 JOptionPane.showMessageDialog(null,"INVALID  LOGIN  NAME  !  .  .  .");
                                              }
                                         }
                                    }
                                  catch(SQLException sq)
                                    {
                                    }
                                    }
                               }
                          }
                     }
                }
              );

             setBounds(230,200,350,240);
              setVisible(true);

           }
      }

      private class ChangePassWordDialog extends JDialog
      {
         private JLabel msg;
         private JRadioButton yes,no2;
         private JPasswordField old,new1;
         private JLabel oldlb,newlb;
         public ChangePassWordDialog(Frame parent2,String title)
           {
              super( parent2, title, true );

              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(msg = new JLabel("R U SURE  !  .  .  .  TO CHANGE UR ' S  PASSWORD  ?"),new AbsoluteConstraints(25,25));
              msg.setForeground(Color.white);

              getContentPane().add( yes = new JRadioButton("YES"),new AbsoluteConstraints(25,50));
              yes.setBackground(getContentPane().getBackground());
              yes.setMnemonic('Y');
              getContentPane().add( no2  = new JRadioButton("NO"),new AbsoluteConstraints(25,75));
              no2.setBackground(getContentPane().getBackground());
              no2.setMnemonic('N');
              getContentPane().add( oldlb  = new JLabel("OLD  PASSWORD  :"),new AbsoluteConstraints(25,105));
              oldlb.setForeground(Color.white);

              getContentPane().add( newlb  = new JLabel("NEW PASSWORD  :"),new AbsoluteConstraints(25,135));
              newlb.setForeground(Color.white);

              getContentPane().add( old  = new JPasswordField(),new AbsoluteConstraints(125+10,105,130,20));
              old.setEditable(false);
              old.setBackground(Color.lightGray);
              old.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add( new1  = new JPasswordField(),new AbsoluteConstraints(125+10,134,130,20));
              new1.setEditable(false);
              new1.setBackground(Color.lightGray);
              new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              yes.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        old.setEditable(true);
                        old.setBackground(Color.white);
                        no2.setEnabled(false);
                     }
                }
              );

              no2.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        new1.setEditable(false);
                        yes.setEnabled(false);
                        old.setEditable(false);
                     }
                }
              );

              old.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        try
                          {
                             s = con1.createStatement();
                             if(cname.getText().equals("")||cname.getText()==null){}
                             else
                               {
                                  rs = s.executeQuery("SELECT PASSWORD FROM STUDENTDETAILS WHERE ADNO ="+"'"+cname.getText().toUpperCase()+"'"+"AND PASSWORD ="+"'"+new String( old.getPassword())+"'");
                                     if( rs.next() )
                                       {
                                           new1.setBackground(Color.white);
                                           new1.setEditable(true);
                                       }
                                     else
                                       {
                                          JOptionPane.showMessageDialog(null,"INVALID  PASSWORD  !  .  .  .");
                                       }
                               }
                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage());
                          }
                     }
                }
              );

              new1.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        try
                          {
                             s = con1.createStatement();
                             int h = s.executeUpdate("UPDATE STUDENTDETAILS SET PASSWORD ="+"'"+new String(new1.getPassword())+"'"+"WHERE ADNO = "+"'"+cname.getText().toUpperCase()+"'" );
                             con1.commit();
                             pd.setText(new String(new1.getPassword()));
                             s.close();
                             setVisible(false);
                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage());
                          }
                     }
                }
              );
              setBounds(220,200,320,190);
              setVisible(true);

           }
      }

     private class myadapter extends WindowAdapter
       {
         public void windowClosing(WindowEvent e)
           {
             System.exit( 0);
           }
       }
     public static void main(String ar[])
       {
         new Login();
       }

 }





