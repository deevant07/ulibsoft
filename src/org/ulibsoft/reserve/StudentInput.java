package org.ulibsoft.reserve;

import java.io.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.*;
import org.ulibsoft.login.Login;
import org.ulibsoft.menu.StaffAccess;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.menu.StudentAccess;

public class StudentInput extends JFrame
  {
     private StudentInput p;
     private String day;
     int count11,count22;
     private static int CONFIRM=-1;

     private JLabel but0,msg1,msg2,msg3,msg4,msg5,msg6;
     private JLabel adno, name, aut1,aut2,aut3 ;
     private JTextField  no, n1,aut11,aut22,aut33 ;
     private JTextField  title,version,sop,subject;
     private JTextField  title2,month,volume,pub;
     private JButton ins, can, next,but23,but12 ;
     private JPanel p1, p2;
     private JComboBox bookname1,aut1s1,aut2s1,aut3s1,nm,mzname1,sop1;

     private String dr,NAME,MSG1,MSG2,MSG3,MSG4,MSG5,MSG6,MSG7;
     private Container  c ;
     private String constraints=null,constraints1;
     private String AUT1="",AUT3="",AUT5="",TITLE="";
     private  int count=0,stage1=0,stage3=0,stage5=0,counter=0,count1=0,count2=0;
     private int count100,count111,MIN_BOOKS,MAX_BOOKS,MAX_CDS,MIN_CDS,MAX_MZS,MIN_MZS;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs, rs1 ;

     public StudentInput() {
          super("INPUT  OBJECTIVE  DETAILS");
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
           //DELETION PENDING FOR BOOKS OF STUDENTS
          try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
          createComponents();
          try
            {
               s=con.createStatement();
               if(org.ulibsoft.menu.StudentAccess.INPUT==1)
               {
                 rs=s.executeQuery("SELECT MIN_BOOKS,MAX_BOOKS FROM KEYCONSTRAINTS WHERE ID=1");
                 while(rs.next())
                 {
                    MIN_BOOKS=rs.getInt(1);
                    MAX_BOOKS=rs.getInt(2);
                    JOptionPane.showMessageDialog(null,"max"+MAX_BOOKS);
                 }
               rs = s.executeQuery ("SELECT COUNT(*) FROM BKTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                     if(rs.next ())
                       {
                          count100 = rs.getInt(1);
                          if(count100 >=MAX_BOOKS)
                            {
                                   JOptionPane.showMessageDialog(null,""+count100);
                                   JOptionPane.showMessageDialog(null,"U  HOLD  MAX  BOOKS ,U  CAN ' T  INPUT  ANOTHER  !");
                                   n1.setEditable(false);
                                   aut11.setEditable(false);
                                   aut22.setEditable(false);
                                   aut33.setEditable(false);
                                   ins.setEnabled(false);
                                   next.setEnabled(false);
                             }

                       }
               rs = s.executeQuery("SELECT COUNT(BOOKNAME) FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'");
               if( rs.next() )
                 {
                    count111=rs.getInt(1);
                    if( count111 < MIN_BOOKS  )
                      {
                      }
                    else
                      {
                         if(count111 < MAX_BOOKS)
                                     {
                                        int ws = JOptionPane.showConfirmDialog (p, count111+" BOOKS R RESERVED . WILL U RESERVE ONE MORE....?","WARNING",JOptionPane.YES_NO_OPTION  ) ;
                                        if( ws==0 ) {    }
                                        else {  setVisible(false);  }
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"U HAVE BEEN RESERVED MAX BOOKS ,U CAN ' T  RESERVE  ANOTHER  !");
                                        n1.setEditable(false);
                                        aut11.setEditable(false);
                                        aut22.setEditable(false);
                                        aut33.setEditable(false);
                                        ins.setEnabled(false);
                                        next.setEnabled(false);
                                     }
                      }
                 }
                }
               //CD
               if(StudentAccess.INPUT==2)
               {
               rs=s.executeQuery("SELECT MIN_CDS,MAX_CDS FROM KEYCONSTRAINTS WHERE ID=1");
               while(rs.next())
                 {
                    MIN_CDS=rs.getInt(1);
                    MAX_CDS=rs.getInt(2);
                 }
               rs = s.executeQuery ("SELECT COUNT(*) FROM CDTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                     if(rs.next ())
                       {
                          count100 = rs.getInt(1);
                          if(count100 >=MAX_CDS)
                            {
                                   JOptionPane.showMessageDialog(null,""+count100);
                                   JOptionPane.showMessageDialog(null,"U  HOLD  MAX  CDS ,U  CAN ' T  INPUT  ANOTHER  !");
                                   title.setEditable(false);
                                   version.setEditable(false);
                                   subject.setEditable(false);
                                   ins.setEnabled(false);
                                   next.setEnabled(false);
                             }

                       }
               rs = s.executeQuery("SELECT COUNT(CDNAME) FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'");
               if( rs.next() )
                 {
                    count111=rs.getInt(1);
                    if( count111 < MIN_CDS  )
                      {
                      }
                    else
                      {
                         if(count111 < MAX_CDS)
                                     {
                                        int ws = JOptionPane.showConfirmDialog (p, count111+" CDS R RESERVED . WILL U RESERVE ONE MORE....?","WARNING",JOptionPane.YES_NO_OPTION  ) ;
                                        if( ws==0 ) {    }
                                        else {  setVisible(false);  }
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"U HAVE BEEN RESERVED MAX CDS ,U CAN ' T  RESERVE  ANOTHER  !");
                                        title.setEditable(false);
                                        version.setEditable(false);
                                        subject.setEditable(false);
                                        ins.setEnabled(false);
                                        next.setEnabled(false);
                                     }
                      }
                 }

                }
                 //MAGAZINE
               if(StudentAccess.INPUT==3)
               {
               rs=s.executeQuery("SELECT MIN_MZS,MAX_MZS FROM KEYCONSTRAINTS WHERE ID=1");
               while(rs.next())
                 {
                    MIN_MZS=rs.getInt(1);
                    MAX_MZS=rs.getInt(2);
                 }
               rs = s.executeQuery ("SELECT COUNT(*) FROM MZTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                     if(rs.next ())
                       {
                          count100 = rs.getInt(1);
                          if(count100 >=MAX_MZS)
                            {
                                   JOptionPane.showMessageDialog(null,""+count100);
                                   JOptionPane.showMessageDialog(null,"U  HOLD  MAX  MZS ,U  CAN ' T  INPUT  ANOTHER  !");
                                   title2.setEditable(false);
                                   volume.setEditable(false);
                                   month.setEditable(false);
                                   pub.setEditable(false);
                                   ins.setEnabled(false);
                                   next.setEnabled(false);
                             }

                       }
               rs = s.executeQuery("SELECT COUNT(MZNAME) FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'");
               if( rs.next() )
                 {
                    count111=rs.getInt(1);
                    if( count111 < MIN_MZS  )
                      {
                      }
                    else
                      {
                         if(count111 < MAX_MZS)
                                     {
                                        int ws = JOptionPane.showConfirmDialog (p, count111+" MAGAZINES R RESERVED . WILL U RESERVE ONE MORE....?","WARNING",JOptionPane.YES_NO_OPTION  ) ;
                                        if( ws==0 ) {    }
                                        else {  setVisible(false);  }
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"U HAVE BEEN RESERVED MAX MAGAZINES ,U CAN ' T  RESERVE  ANOTHER  !");
                                        title2.setEditable(false);
                                        volume.setEditable(false);
                                        month.setEditable(false);
                                        pub.setEditable(false);
                                        ins.setEnabled(false);
                                        next.setEnabled(false);
                                     }
                      }
                 }
                }
             }
            catch(SQLException se)
              {
                  JOptionPane.showMessageDialog(null,se.getMessage());
                  se.printStackTrace();
              }

          if(StudentAccess.INPUT==1)
          componentListener1();
          if(StudentAccess.INPUT==2)
          componentListener2();
          if(StudentAccess.INPUT==3)
          componentListener3();

       }

     private void createComponents()
       {
           c = getContentPane( ) ;
           c.setBackground( new Color(0,0,40) ) ;
           c.setLayout ( new AbsoluteLayout( ) ) ;

           p1 = new JPanel( new AbsoluteLayout() );
           p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INPUT  BOOK  DETAILS", 1, 2, c.getFont(), Color.magenta));
           p1.setBackground(c.getBackground());
           c.add(p1,new AbsoluteConstraints(200-5,80,395,230));

           but0 = new JLabel( "              INPUT  BOOK  DETAILS" ) ;
           but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
           but0.setBackground ( Color.pink );
           but0.setForeground(Color.black);
           c.add ( but0, new AbsoluteConstraints( 260+30+5, 30, 200, 30 ) );

           but12 = new JButton( "" ) ;
           but12.setBackground ( Color.cyan );
           but12.setForeground(Color.black);
           but12.setEnabled(false);
           but12.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but12, new AbsoluteConstraints( 260+30+5, 30, 200, 30 ) );

           but23 = new JButton( "" ) ;
           but23.setBackground ( Color.pink );
           but23.setForeground(Color.black);
           but23.setEnabled(false);
           but23.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but23, new AbsoluteConstraints( 267+30+5, 37, 200, 30 ) );

           p1.add(adno = new JLabel("ADMISSION  NUMBER :"),new AbsoluteConstraints(30,30));
           adno.setForeground(new Color(120,120,155));

           p1.add(no = new JTextField(),new AbsoluteConstraints(165,28,200,20));
           no.setText(Login.cname.getText().toUpperCase());
           no.setEditable(false);
           no.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
           no.setForeground ( new Color ( 255, 0, 153 ) );
           no.setCaretColor ( new Color ( 0, 204, 102 ) );

           p1.add(name = new JLabel("<==  BOOK  TITLE  ==>"),new AbsoluteConstraints(30,60));
           name.setForeground(new Color(120,120,155));

           p1.add(aut1 = new JLabel("<=  FIRST  AUTHOR  =>"),new AbsoluteConstraints(30,90));
           aut1.setForeground(new Color(120,120,155));

           p1.add(aut2 = new JLabel("=> SECOND AUTHOR  :"),new AbsoluteConstraints(30,120));
           aut2.setForeground(new Color(120,120,155));

           p1.add(aut3 = new JLabel("<=  THIRD AUTHOR  =>"),new AbsoluteConstraints(30,150));
           aut3.setForeground(new Color(120,120,155));

           if(StudentAccess.INPUT==1)
             {
                p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INPUT  BOOK  DETAILS", 1, 2, c.getFont(), Color.magenta));
                n1 = new JTextField ( );
                n1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                n1.setForeground ( new Color ( 255, 0, 153 ) );
                n1.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( n1, new AbsoluteConstraints( 165, 58, 200, 20 ) );


                bookname1 = new JComboBox( );
                bookname1.setEditable(true);
                bookname1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
                bookname1.setForeground ( new Color ( 255, 0, 153 ) );
                bookname1.setVisible(false);
                p1.add ( bookname1, new AbsoluteConstraints( 165, 58, 200, 20) );

                aut11 = new JTextField ( );
                aut11.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                aut11.setForeground ( new Color ( 255, 0, 153 ) );
                aut11.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( aut11, new AbsoluteConstraints( 165, 88, 200, 20 ) );

                aut1s1 = new JComboBox( );
                aut1s1.setEditable(true);
                aut1s1.setVisible(false);
                aut1s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
                aut1s1.setForeground ( new Color ( 255, 0, 153 ) );
                p1.add ( aut1s1, new AbsoluteConstraints( 165, 88, 200, 20 ) );

                aut22 = new JTextField ( );
                aut22.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                aut22.setForeground ( new Color ( 255, 0, 153 ) );
                aut22.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( aut22, new AbsoluteConstraints( 165, 118, 200, 20 ) );

                aut2s1 = new JComboBox( );
                aut2s1.setEditable(true);
                aut2s1.setVisible(false);
                aut2s1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
                aut2s1.setForeground ( new Color ( 255, 0, 153 ) );
                p1.add ( aut2s1, new AbsoluteConstraints( 165, 118, 200, 20   ) );

                aut33 = new JTextField ( );
                aut33.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                aut33.setForeground ( new Color ( 255, 0, 153 ) );
                aut33.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( aut33, new AbsoluteConstraints( 165, 148, 200, 20 ) );

                aut3s1 = new JComboBox( );
                aut3s1.setEditable(true);
                aut3s1.setVisible(false);
                aut3s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
                aut3s1.setForeground ( new Color ( 255, 0, 153 ) );
                p1.add ( aut3s1, new AbsoluteConstraints( 165, 148, 200, 20 ) );

             }

           if(StudentAccess.INPUT==2)
             {
                p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INPUT  CD / FLOPPY  DETAILS", 1, 2, c.getFont(), Color.magenta));

                name.setText("CD / FLOPPY  TITLE  =>");
                aut1.setText("<==  CD  VERSION  ==>");
                aut2.setText("==>  SUBJECT  OF  CD :");
                aut3.setText("SOURCE  OF  SUPPLY  :");

                title = new JTextField ( );
                title.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                title.setForeground ( new Color ( 255, 0, 153 ) );
                title.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( title, new AbsoluteConstraints( 165, 58, 200, 20 ) );

                 p1.add(nm = new JComboBox(),new AbsoluteConstraints(165, 58, 200, 20));
                 nm.setForeground(new Color(255,0,153));
                 nm.setEditable(true);
                 nm.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));
                 nm.setVisible(false);

                version = new JTextField ( );
                version.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                version.setForeground ( new Color ( 255, 0, 153 ) );
                version.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( version, new AbsoluteConstraints( 165, 88, 200, 20 ) );

                subject = new JTextField ( );
                subject.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                subject.setForeground ( new Color ( 255, 0, 153 ) );
                subject.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( subject, new AbsoluteConstraints( 165, 118, 200, 20 ) );

                sop = new JTextField ( );
                sop.setEditable(false);
                sop.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                sop.setForeground ( new Color ( 255, 0, 153 ) );
                sop.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( sop, new AbsoluteConstraints( 165, 148, 200, 20 ) );

             }
           if(StudentAccess.INPUT==3)
             {
                p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INPUT  MAGAZINE  DETAILS", 1, 2, c.getFont(), Color.magenta));

                name.setText("==> MAGAZINE  TITLE :");
                aut1.setText("MAGAZINE   VOLUME  :");
                aut2.setText("MAGAZINE  MONTH  :");
                aut3.setText("SOURCE  OF  SUPPLY :");

                title2 = new JTextField ( );
                title2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                title2.setForeground ( new Color ( 255, 0, 153 ) );
                title2.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( title2, new AbsoluteConstraints( 165, 58, 200, 20 ) );

                mzname1 = new JComboBox( );
                mzname1.setEditable(true);
                mzname1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
                mzname1.setForeground ( new Color ( 255, 0, 153 ) );
                mzname1.setBackground(Color.white);
                p1.add ( mzname1, new AbsoluteConstraints( 165, 58, 200, 20 ) );
                mzname1.setVisible(false);

                volume = new JTextField ( );
                volume.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                volume.setForeground ( new Color ( 255, 0, 153 ) );
                volume.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( volume, new AbsoluteConstraints( 165, 88, 200, 20 ) );

                month = new JTextField ( );
                month.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                month.setForeground ( new Color ( 255, 0, 153 ) );
                month.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( month, new AbsoluteConstraints( 165, 118, 200, 20 ) );

                pub = new JTextField ( );
                pub.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
                pub.setForeground ( new Color ( 255, 0, 153 ) );
                pub.setCaretColor ( new Color ( 0, 204, 102 ) );
                p1.add ( pub, new AbsoluteConstraints( 165, 148, 200, 20 ) );

             }

           ins = new JButton( "SUBMITT" ) ;
           ins.setBackground (Color.cyan);
           ins.setForeground(Color.black);
           ins.setBorder(new BevelBorder(0));
           ins.setMnemonic('S');
           //ins.setEnabled (false);
           p1.add ( ins, new AbsoluteConstraints( 15+1+20+2, 180, 105, 27 ) );

           next = new JButton( "NEXT>>>" ) ;
           next.setBackground ( Color.cyan);
           next.setForeground(Color.black);
           next.setMnemonic('N');
           next.setBorder(new BevelBorder(0));
           p1.add ( next, new AbsoluteConstraints( 122+20+2, 180, 105, 27 ) );

           can = new JButton( "EXIT" ) ;
           can.setBackground ( Color.cyan);
           can.setForeground( Color.black);
           can.setMnemonic('X');
           can.setBorder(new BevelBorder(0));
           p1.add ( can, new AbsoluteConstraints( 230-1+20+2, 180, 105, 27 ) );

           p2 = new JPanel(new AbsoluteLayout());
           p2.setBackground(new Color(255,255,255));
           c.add(p2,new AbsoluteConstraints(140,340,500,210));

           dr ="Dear  ,";
           p2.add(msg1 = new JLabel(""),new AbsoluteConstraints(30+50,30));
           msg1.setForeground(new Color(0,0,100));

           try
             {
                s=con.createStatement();
                rs = s.executeQuery("SELECT SNAME FROM STUDENTDETAILS WHERE ADNO="+"'"+Login.cname.getText().toUpperCase()+"'");
                if( rs.next() )
                  {
                     MSG1=rs.getString(1)+"  !  .  .  .";
                  }
                  s.close();
             }
           catch(SQLException sqlex)
             {
                JOptionPane.showMessageDialog(null,sqlex.getMessage());
             }

           p2.add(msg2 = new JLabel(""),new AbsoluteConstraints(50+50,60));
           msg2.setForeground(new Color(0,0,100));

           MSG2="You  will  get  your  ";
           MSG3="  with  in two  days  .";
           p2.add(msg3 = new JLabel(""),new AbsoluteConstraints(50+50,90));
           msg3.setForeground(new Color(0,0,100));

           MSG4="That  is  before  ";
           MSG5="  .";
           p2.add(msg4 = new JLabel(""),new AbsoluteConstraints(50+50,120));
           msg4.setForeground(new Color(0,0,100));

           MSG6="Bye  .  .  .  .  .  .  .  .  .  .  .";
           p2.add(msg5 = new JLabel(""),new AbsoluteConstraints(50+50,150));
           msg5.setForeground(new Color(0,0,100));

           MSG7="GIT  Library  .";
           p2.add(msg6 = new JLabel(""),new AbsoluteConstraints(250+50,180));
           msg6.setForeground(new Color(0,0,100));

           setVisible(true);
       }

       private void componentListener1()
       {
           n1.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(BOOKNAME) FROM LIBRARY WHERE BOOKNAME LIKE "+"'"+n1.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              bookname1.addItem(rs.getObject(1));
                              ++count;
                           }
                          if( count > 0 )
                          {
                             bookname1.setVisible(true);
                             n1.setVisible(false);
                          }
                          s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );
           aut11.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR1S||AUTHOR1F) FROM LIBRARY WHERE AUTHOR1S LIKE "+"'"+aut11.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut1s1.addItem(rs.getObject(1));
                              ++stage1;
                           }
                           s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,"error"+sq.getMessage());
                        }
                      if(stage1>0)
                          {
                             aut1s1.setVisible(true);
                             aut11.setVisible(false);
                          }
                }
            }
          );
            aut22.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR2S||AUTHOR2F) FROM LIBRARY WHERE AUTHOR2S LIKE "+"'"+aut22.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut2s1.addItem(rs.getObject(1));
                              ++stage3;
                           }
                          s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                      if(stage3>0)
                          {
                             aut2s1.setVisible(true);
                             aut22.setVisible(false);
                          }

                }
            }
          );
            aut33.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR3S||AUTHOR3F) FROM LIBRARY WHERE AUTHOR3S LIKE "+"'"+aut33.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut3s1.addItem(rs.getObject(1));
                              ++stage5;
                           }
                          if(stage5>0)
                          {
                             aut3s1.setVisible(true);
                             aut33.setVisible(false);
                          }
                         s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                   }
            }
          );

        ins.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
             {

                if(n1.getText().equals("")||n1.getText()==null)
                  {
                      new ConfirmDialog1((Frame)p,"WARNING");
                  }
                else
                  {
                     TITLE=(String)bookname1.getSelectedItem();
                     if(TITLE==null || TITLE .equals(""))
                       {
                          CONFIRM=0;
                          TITLE = n1.getText().toUpperCase();
                          constraints=" LIB.BOOKNAME = '"+TITLE+"'";
                          constraints1=" BOOKNAME = '"+TITLE+"'";
                       }
                     else
                       {
                          CONFIRM=0;
                          constraints=" LIB.BOOKNAME = '"+TITLE+"'";
                          constraints1=" BOOKNAME = '"+TITLE+"'";
                       }
                  }

                if(aut11.getText().equals("")||aut11.getText()==null)
                  {
                     new ConfirmDialog2((Frame)p,"WARNING");
                  }
                else
                  {
                      AUT1=(String)aut1s1.getSelectedItem();
                      if(AUT1==null || AUT1.equals(""))
                        {
                           CONFIRM=0;
                           AUT1 = aut11.getText().toUpperCase();
                           constraints=constraints+" AND LIB.AUTHOR1S||LIB.AUTHOR1F ='"+AUT1+"'";
                           constraints1=constraints1+" AND AUTHOR1 ='"+AUT1+"'";
                        }
                      else
                       {
                          CONFIRM=0;
                          constraints=constraints+" AND LIB.AUTHOR1S||LIB.AUTHOR1F ='"+AUT1+"'";
                          constraints1=constraints1+" AND AUTHOR1 ='"+AUT1+"'";
                       }
                  }
                if(aut22.getText().equals("")||aut22.getText()==null)
                  {
                  }
                else
                  {
                     AUT3=(String)aut2s1.getSelectedItem();
                     if(AUT3==null || AUT3.equals(""))
                       {
                           AUT3 = aut22.getText().toUpperCase();
                           constraints=constraints+" AND LIB.AUTHOR2S||LIB.AUTHOR2F ='"+AUT3+"'";
                           constraints1=constraints1+" AND AUTHOR2 ='"+AUT3+"'";
                       }
                     else
                       {
                          constraints=constraints+" AND LIB.AUTHOR2S||LIB.AUTHOR2F ='"+AUT3+"'";
                          constraints1=constraints1+" AND AUTHOR2 ='"+AUT3+"'";
                       }
                  }
                if(aut22.getText().equals("")||aut22.getText()==null)
                  {
                  }
                else
                  {
                      AUT5=(String)aut3s1.getSelectedItem();
                      if(AUT5==null || AUT5.equals(""))
                        {
                           AUT5 = aut33.getText().toUpperCase();
                           constraints=constraints+" AND LIB.AUTHOR3S||LIB.AUTHOR3F ='"+AUT5+"'";
                           constraints1=constraints1+" AND AUTHOR3 ='"+AUT5+"'";
                        }
                      else
                       {
                          constraints=constraints+" AND LIB.AUTHOR3S||LIB.AUTHOR3F ='"+AUT5+"'";
                          constraints1=constraints1+" AND AUTHOR3 ='"+AUT5+"'";
                       }
                  }
                try
                  {
                    s=con.createStatement();
                    rs = s.executeQuery ("SELECT COUNT(*) FROM BKTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                    if(rs.next())
                      {
                        count=rs.getInt(1);
                      }
                    if(count>=MAX_BOOKS)
                      {
                        JOptionPane.showMessageDialog(null,"MAXIMUM  BOOKS  EXCEEDED !  .  .  ."+"MAX"+MAX_BOOKS+"COUNT"+count);
                        s.close();
                      }
                    else
                      {
                        s.close();
                        if(CONFIRM==0)
                          {
                             try
                               {
                                  s=con.createStatement();
                                  rs = s.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY'),TO_CHAR(SYSDATE,'HH:MM:SS') FROM  DUAL ");
                                  if( rs.next() )
                                    {
                                       String date=rs.getString(1);
                                       String time=rs.getString(2);

                                       rs = s.executeQuery("SELECT COUNT(*) FROM STD_RESERVE WHERE "+constraints1);
                                       while( rs.next() )
                                         {
                                             count11=rs.getInt(1);
                                             JOptionPane.showMessageDialog(null,""+count11);
                                         }

                                       rs = s.executeQuery("SELECT COUNT(LIB.ACESSNO) FROM LIBRARY LIB WHERE LIB.ACESSNO NOT IN(SELECT BK.CODE FROM BKTRANSACTION BK WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN(SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO ) AND "+constraints);
                                       while( rs.next() )
                                         {
                                            count22=rs.getInt(1);
                                            JOptionPane.showMessageDialog(null,""+count22);
                                         }
                                       if( count11 <= count22 )
                                         {
                                            rs = s.executeQuery("SELECT LIB.ACESSNO FROM LIBRARY LIB WHERE "+constraints);
                                            if(rs.next())
                                              {
                                                rs =s.executeQuery("SELECT ADNO FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'"+"AND "+constraints1);
                                                if( rs.next() )
                                                  {
                                                     JOptionPane.showMessageDialog(null,"U R ALREDY RESERVED THIS BOOK  !  .  .  .");
                                                  }
                                                else
                                                  {
                                                      int wq=s.executeUpdate("INSERT INTO STD_RESERVE (ADNO,BOOKNAME,AUTHOR1,AUTHOR2,AUTHOR3,IDATE,TIME) VALUES("+"'"+no.getText()+"',"
                                                                                                                                                                 +"'"+TITLE+"',"
                                                                                                                                                                 +"'"+AUT1+"',"
                                                                                                                                                                 +"'"+AUT3+"',"
                                                                                                                                                                 +"'"+AUT5+"',"
                                                                                                                                                                 +"'"+date+"',"
                                                                                                                                                                 +"'"+time+"'"+")");


                                                      con.commit();
                                                      msg1.setText(dr);
                                                      msg2.setText(MSG1);
                                                      msg3.setText(MSG2+"  books  "+MSG3);

                                                      rs = s.executeQuery("SELECT TO_CHAR(SYSDATE+2,'DAY') FROM DUAL");
                                                        if( rs.next() )
                                                          {
                                                             day = rs.getString(1).toLowerCase();

                                                             if( day.equalsIgnoreCase("SATURDAY") )
                                                               {
                                                                  day = "Monday";
                                                               }
                                                             if( day.equalsIgnoreCase("SUNDAY") )
                                                               {
                                                                  day = "Tuesday";
                                                               }

                                                             msg4.setText("That  is  on  "+day+MSG5);
                                                          }

                                                          msg5.setText(MSG6);
                                                          msg6.setText(MSG7);
                                                  }
                                              }
                                            else
                                              {
                                                JOptionPane.showMessageDialog(null,"BOOK RECORD DOES NOT EXIST  !  .  .  .");
                                              }

                                         }
                                       else if( count11 > count22)
                                         {
                                             msg1.setText(dr);
                                             msg2.setText(MSG1);
                                             msg3.setText("Sorry , This  book  has  been  issued  to  some  one  !  .");

                                             rs = s.executeQuery("SELECT MIN(TO_DATE(BK.RDATE)) FROM BKTRANSACTION BK,LIBRARY LIB WHERE BK.CODE=LIB.ACESSNO AND "+constraints );
                                             if( rs.next() )
                                               {
                                                  msg4.setText("If  u  still  need , u  will  come  on  "+""+rs.getDate(1));
                                               }
                                             msg5.setText(MSG6);
                                             msg6.setText(MSG7);
                                         }
                                       else
                                         {
                                            JOptionPane.showMessageDialog(null,"BOOK RECORD DOES NOT EXIST  !  .  .  . ");
                                         }
                                    }
                                  s.close();
                               }
                             catch(SQLException sq)
                               {
                                  JOptionPane.showMessageDialog(null,sq.getMessage());
                               }
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

          next.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    n1.setText("");
                    aut11.setText("");
                    aut22.setText("");
                    aut33.setText("");
                    n1.setVisible(true);
                    aut11.setVisible(true);
                    aut22.setVisible(true);
                    aut33.setVisible(true);
                    constraints=null;

                    bookname1.removeAllItems();
                    aut1s1.removeAllItems();
                    aut2s1.removeAllItems();
                    aut3s1.removeAllItems();

                    bookname1.setVisible(false);
                    aut1s1.setVisible(false);
                    aut2s1.setVisible(false);
                    aut3s1.setVisible(false);
                    count11=0;
                    count22=0;
                    count100=0;
                    count111=0;
                    constraints=null;
                    constraints1=null;

                    try
                      {
                         s=con.createStatement();
                         rs = s.executeQuery("SELECT COUNT(BOOKNAME) FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'");
                         if( rs.next() )
                           {
                              count111=rs.getInt(1);
                              if( count111 < MIN_BOOKS  ) {  }
                              else
                                {
                                   if(count111 < MAX_BOOKS)
                                     {
                                        int ws = JOptionPane.showConfirmDialog (p, count111+" BOOKS R RESERVED . WILL U RESERVE ONE MORE....?","WARNING",JOptionPane.YES_NO_OPTION  ) ;
                                        if( ws==0 ) {    }
                                        else {  setVisible(false);  }
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"U HAVE BEEN RESERVED MAX BOOKS ,U CAN ' T  RESERVE  ANOTHER  !");
                                        n1.setEditable(false);
                                        aut11.setEditable(false);
                                        aut22.setEditable(false);
                                        aut33.setEditable(false);
                                        ins.setEnabled(false);
                                        next.setEnabled(false);
                                     }
                                }
                           }
                      }
                    catch(SQLException se)
                      {
                         JOptionPane.showMessageDialog(null,se.getMessage());
                         se.printStackTrace();
                      }
                 }
            }
          );
          can.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
             {
               setVisible(false);
             }
          }
          );
       }
     private void componentListener2()
       {
          title.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(CDNAME) FROM CDDETAILS WHERE CDNAME LIKE "+"'"+title.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              nm.addItem(rs.getObject(1));
                             ++count2;
                           }
                          if(count2>0)
                           {
                              nm.setVisible(true);
                              title.setVisible(false);
                           }
                          s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );

          ins.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
             {

                if(title.getText().equals("")||title.getText()==null)
                  {
                      new ConfirmDialog3((Frame)p,"WARNING");
                  }
                else
                  {
                     TITLE=(String)nm.getSelectedItem();
                     if(TITLE==null || TITLE .equals(""))
                       {
                          CONFIRM=0;
                          TITLE = title.getText().toUpperCase();
                          constraints=" CD.CDNAME = '"+TITLE+"'";
                          constraints1=" CDNAME = '"+TITLE+"'";
                       }
                     else
                       {
                          CONFIRM=0;
                          constraints=" CD.CDNAME = '"+TITLE+"'";
                          constraints1=" CDNAME = '"+TITLE+"'";
                       }
                  }

                if(version.getText().equals("")||version.getText()==null)
                  {
                     new ConfirmDialog4((Frame)p,"WARNING");
                  }
                else
                  {
                      AUT1=(String)version.getText();
                      CONFIRM=0;
                      constraints=constraints+" AND CD.CDVERSION ='"+AUT1+"'";
                      constraints1=constraints1+" AND VERSION ='"+AUT1+"'";
                  }
                if(subject.getText().equals("")||subject.getText()==null)
                  {
                  }
                else
                  {
                     AUT3=subject.getText();
                     constraints=constraints+" AND CD.SUBJECT ='"+AUT3+"'";
                     constraints1=constraints1+" AND SUBJECT1 ='"+AUT3+"'";

                  }
                try
                  {
                    s=con.createStatement();
                    rs = s.executeQuery ("SELECT COUNT(*) FROM CDTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                    if(rs.next())
                      {
                        count=rs.getInt(1);
                      }
                    if(count>=MAX_CDS)
                      {
                        JOptionPane.showMessageDialog(null,"MAXIMUM  CDS  EXCEEDED !  .  .  .");
                        s.close();
                      }
                    else
                      {
                        s.close();
                        if(CONFIRM==0)
                          {
                             try
                               {
                                  s=con.createStatement();
                                  rs = s.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY'),TO_CHAR(SYSDATE,'HH:MM:SS') FROM  DUAL ");
                                  if( rs.next() )
                                    {
                                       String date=rs.getString(1);
                                       String time=rs.getString(2);
                                       JOptionPane.showMessageDialog(null,constraints1);
                                       rs = s.executeQuery("SELECT COUNT(*) FROM STD_RESERVE WHERE "+constraints1);
                                       if( rs.next() )
                                         {
                                             count11=rs.getInt(1);
                                             JOptionPane.showMessageDialog(null,""+count11);
                                         }

                                       rs = s.executeQuery("SELECT COUNT(CD.CDCODE) FROM CDDETAILS CD  WHERE CD.CDCODE NOT IN(SELECT BK.CDCODE FROM CDTRANSACTION BK WHERE BK.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT BK1.CDCODE FROM CDTRANSACTION1 BK1 WHERE BK1.CDCODE=CD.CDCODE ) AND"+constraints);
                                       if( rs.next() )
                                         {
                                            count22=rs.getInt(1);
                                            JOptionPane.showMessageDialog(null,""+count22);
                                         }
                                       if( count11 <= count22 )
                                         {
                                            rs = s.executeQuery("SELECT CD.CDCODE FROM CDDETAILS CD WHERE "+constraints);
                                            if(rs.next())
                                              {
                                                rs =s.executeQuery("SELECT ADNO FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'"+"AND "+constraints1);
                                                if( rs.next() )
                                                  {
                                                     JOptionPane.showMessageDialog(null,"U R ALREDY RESERVED THIS CD  !  .  .  .");
                                                  }
                                                else
                                                  {
                                                      int wq=s.executeUpdate("INSERT INTO STD_RESERVE (ADNO,CDNAME,VERSION,SUBJECT1,IDATE,TIME) VALUES("+"'"+no.getText()+"',"
                                                                                                                                                       +"'"+TITLE+"',"
                                                                                                                                                       +"'"+AUT1+"',"
                                                                                                                                                       +"'"+AUT3+"',"
                                                                                                                                                       +"'"+date+"',"
                                                                                                                                                       +"'"+time+"'"+")");


                                                      con.commit();

                                                      msg1.setText(dr);
                                                      msg2.setText(MSG1);
                                                      msg3.setText(MSG2+"  CDs  "+MSG3);

                                                    rs = s.executeQuery("SELECT TO_CHAR(SYSDATE+2,'DAY') FROM DUAL");
                                                    if( rs.next() )
                                                      {
                                                         day = rs.getString(1).toLowerCase();

                                                         if( day.equalsIgnoreCase("SATURDAY") )
                                                           {
                                                               day = "Monday";
                                                           }
                                                         if( day.equalsIgnoreCase("SUNDAY") )
                                                           {
                                                               day = "Tuesday";
                                                           }

                                                         msg4.setText("That  is  on  "+day+MSG5);
                                                       }

                                                      msg5.setText(MSG6);
                                                      msg6.setText(MSG7);
                                                  }
                                              }
                                            else
                                              {
                                                JOptionPane.showMessageDialog(null,"CD RECORD DOES NOT EXIST  !  .  .  .");
                                              }

                                         }
                                       else if( count11 > count22)
                                         {
                                             msg1.setText(dr);
                                             msg2.setText(MSG1);
                                             msg3.setText("Sorry , This  cd  has  been  issued  to  some  one  !  .");

                                             rs = s.executeQuery("SELECT MIN(TO_DATE(BK.RDATE)) FROM CDTRANSACTION BK,CDDETAILS CD WHERE BK.CDCODE=CD.CDCODE AND "+constraints );
                                             if( rs.next() )
                                               {
                                                  msg4.setText("If  u  still  need , u  will  come  on  "+""+rs.getDate(1));
                                               }
                                             msg5.setText(MSG6);
                                             msg6.setText(MSG7);
                                         }
                                       else
                                         {
                                            JOptionPane.showMessageDialog(null,"CD RECORD DOES NOT EXIST  !  .  .  . ");
                                         }
                                    }
                                  s.close();
                               }
                             catch(SQLException sq)
                               {
                                  JOptionPane.showMessageDialog(null,sq.getMessage());
                               }
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

          next.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    title.setText("");
                    version.setText("");
                    subject.setText("");

                    title.setVisible(true);
                    version.setVisible(true);
                    subject.setVisible(true);

                    constraints=null;

                    nm.removeAllItems();

                    nm.setVisible(false);

                    count11=0;
                    count22=0;
                    count100=0;
                    count111=0;
                    constraints=null;
                    constraints1=null;

                    try
                      {
                         s=con.createStatement();
                         rs = s.executeQuery("SELECT COUNT(CDNAME) FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'");
                         if( rs.next() )
                           {
                              count111=rs.getInt(1);
                              if( count111 < MIN_CDS  ) {  }
                              else
                                {
                                   if(count111 < MAX_CDS)
                                     {
                                        int ws = JOptionPane.showConfirmDialog (p, count111+" CDS R RESERVED . WILL U RESERVE ONE MORE....?","WARNING",JOptionPane.YES_NO_OPTION  ) ;
                                        if( ws==0 ) {    }
                                        else {  setVisible(false);  }
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"U HAVE BEEN RESERVED MAX CDS ,U CAN ' T  RESERVE  ANOTHER  !");
                                        title.setEditable(false);
                                        version.setEditable(false);
                                        subject.setEditable(false);
                                        ins.setEnabled(false);
                                        next.setEnabled(false);
                                     }
                                }
                           }
                      }
                    catch(SQLException se)
                      {
                         JOptionPane.showMessageDialog(null,se.getMessage());
                         se.printStackTrace();
                      }
                 }
            }
          );
          can.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
             {
               setVisible(false);
             }
          }
          );
       }
     private void componentListener3()
       {
          title2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {

                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(MZNAME) FROM MZDETAILS WHERE MZNAME LIKE "+"'"+title2.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {

                              mzname1.addItem(rs.getObject(1));
                              ++count1;
                           }
                          if(count1>0)
                          {
                             mzname1.setVisible(true);
                             title2.setVisible(false);
                          }
                           s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }

                }
            }
          );

          pub.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                     try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(SOURCE) FROM MZDETAILS WHERE SOURCE LIKE "+"'"+pub.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              sop1.addItem(rs.getObject(1));

                              ++counter;
                           }
                          if( counter > 0)
                           {
                              sop1.setVisible(true);
                              pub.setVisible(false);
                           }
                          s.close();
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );

        ins.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
             {

                if(title2.getText().equals("")||title2.getText()==null)
                  {
                      new ConfirmDialog5((Frame)p,"WARNING");
                  }
                else
                  {
                     TITLE=(String)mzname1.getSelectedItem();
                     if(TITLE==null || TITLE .equals(""))
                       {
                          CONFIRM=0;
                          TITLE = title2.getText().toUpperCase();
                          constraints=" MZ.MZNAME = '"+TITLE+"'";
                          constraints1=" MZNAME = '"+TITLE+"'";
                       }
                     else
                       {
                          CONFIRM=0;
                          constraints=" MZ.MZNAME = '"+TITLE+"'";
                          constraints1=" MZNAME = '"+TITLE+"'";
                       }
                  }

                if(volume.getText().equals("")||volume.getText()==null)
                  {
                     new ConfirmDialog6((Frame)p,"WARNING");
                  }
                else
                  {
                      AUT1=volume.getText();
                      CONFIRM=0;
                      constraints=constraints+" AND MZ.VOLUME ='"+AUT1+"'";
                      constraints1=constraints1+" AND VOLUME ='"+AUT1+"'";

                  }
                if(month.getText().equals("")||month.getText()==null)
                  {
                  }
                else
                  {
                     AUT3=month.getText();
                     constraints=constraints+" AND MZ.PCITY ='"+AUT3+"'";
                     constraints1=constraints1+" AND MONTH ='"+AUT3+"'";
                  }

                if(pub.getText().equals("")||pub.getText()==null)
                  {
                  }
                else
                  {
                      AUT5=(String)sop1.getSelectedItem();
                      if(AUT5==null || AUT5.equals(""))
                        {
                           AUT5 = pub.getText().toUpperCase();
                           constraints=constraints+" AND MZ.PUBLISHER ='"+AUT5+"'";
                           constraints1=constraints1+" AND PUBLISHER ='"+AUT5+"'";
                        }
                      else
                       {
                          constraints=constraints+" AND MZ.PUBLISHER ='"+AUT5+"'";
                          constraints1=constraints1+" AND PUBLISHER ='"+AUT5+"'";
                       }
                  }
                try
                  {
                    s=con.createStatement();
                    rs = s.executeQuery ("SELECT COUNT(*) FROM MZTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                    if(rs.next())
                      {
                        count=rs.getInt(1);
                      }
                    if(count>=MAX_MZS)
                      {
                        JOptionPane.showMessageDialog(null,"MAXIMUM  MAGAZINES  EXCEEDED !  .  .  .");
                        s.close();
                      }
                    else
                      {
                        s.close();
                        if(CONFIRM==0)
                          {
                             try
                               {
                                  s=con.createStatement();
                                  rs = s.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY'),TO_CHAR(SYSDATE,'HH:MM:SS') FROM  DUAL ");
                                  if( rs.next() )
                                    {
                                       String date=rs.getString(1);
                                       String time=rs.getString(2);

                                       rs = s.executeQuery("SELECT COUNT(*) FROM STD_RESERVE WHERE "+constraints1);
                                       if( rs.next() )
                                         {
                                             count11=rs.getInt(1);
                                             JOptionPane.showMessageDialog(null,""+count11);
                                         }

                                       rs = s.executeQuery("SELECT COUNT(MZ.ACCESSNO) FROM MZDETAILS MZ WHERE MZ.ACCESSNO NOT IN(SELECT BK.MZCODE FROM MZTRANSACTION BK WHERE BK.MZCODE=MZ.ACCESSNO) AND MZ.ACCESSNO NOT IN(SELECT BK1.MZCODE FROM MZTRANSACTION1 BK1 WHERE BK1.MZCODE=MZ.ACCESSNO ) AND"+constraints);
                                       if( rs.next() )
                                         {
                                            count22=rs.getInt(1);
                                            JOptionPane.showMessageDialog(null,""+count22);
                                         }
                                       if( count11 <= count22 )
                                         {
                                            rs = s.executeQuery("SELECT MZ.ACCESSNO FROM MZDETAILS MZ WHERE "+constraints);
                                            if(rs.next())
                                              {
                                                 rs =s.executeQuery("SELECT ADNO FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'"+"AND "+constraints1);
                                                if( rs.next() )
                                                  {
                                                     JOptionPane.showMessageDialog(null,"U R ALREDY RESERVED THIS MAGAZINE  !  .  .  .");
                                                  }
                                                else
                                                  {
                                                      int wq=s.executeUpdate("INSERT INTO STD_RESERVE (ADNO,MZNAME,VOLUME,MONTH,PUBLISHER,IDATE,TIME) VALUES("+"'"+no.getText()+"',"
                                                                                                                                                             +"'"+TITLE+"',"
                                                                                                                                                             +"'"+AUT1+"',"
                                                                                                                                                             +"'"+AUT3+"',"
                                                                                                                                                             +"'"+AUT5+"',"
                                                                                                                                                             +"'"+date+"',"
                                                                                                                                                             +"'"+time+"'"+")");


                                                      con.commit();
                                                      msg1.setText(dr);
                                                      msg2.setText(MSG1);
                                                      msg3.setText(MSG2+"  magazines  "+MSG3);

                                                      rs = s.executeQuery("SELECT TO_CHAR(SYSDATE+2,'DAY') FROM DUAL");
                                                      if( rs.next() )
                                                        {
                                                          day = rs.getString(1).toLowerCase();

                                                          if( day.equalsIgnoreCase("SATURDAY") )
                                                            {
                                                               day = "Monday";
                                                            }
                                                          if( day.equalsIgnoreCase("SUNDAY") )
                                                            {
                                                              day = "Tuesday";
                                                            }

                                                          msg4.setText("That  is  on  "+day+MSG5);
                                                        }

                                                     msg5.setText(MSG6);
                                                     msg6.setText(MSG7);
                                                  }
                                              }
                                            else
                                              {
                                                 JOptionPane.showMessageDialog(null,"MAGAZINE RECORD DOES NOT EXIST  !  .  .  .");
                                              }
                                         }
                                       else if( count11 > count22)
                                         {
                                             msg1.setText(dr);
                                             msg2.setText(MSG1);
                                             msg3.setText("Sorry , This  magazine  has  been  issued  to  some  one  !  .");

                                             rs = s.executeQuery("SELECT MIN(TO_DATE(BK.RDATE)) FROM MZTRANSACTION BK,MZDETAILS MZ WHERE BK.MZCODE=MZ.ACCESSNO AND "+constraints );
                                             if( rs.next() )
                                               {
                                                  msg4.setText("If  u  still  need , u  will  come  on  "+""+rs.getDate(1));
                                               }
                                             msg5.setText(MSG6);
                                             msg6.setText(MSG7);
                                         }
                                       else
                                         {
                                            JOptionPane.showMessageDialog(null,"MAGAZINE RECORD DOES NOT EXIST  !  .  .  . ");
                                         }
                                    }
                                  s.close();
                               }
                             catch(SQLException sq)
                               {
                                  JOptionPane.showMessageDialog(null,sq.getMessage());
                               }
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

          next.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    title2.setText("");
                    volume.setText("");
                    month.setText("");
                    pub.setText("");
                    title2.setVisible(true);
                    volume.setVisible(true);
                    month.setVisible(true);
                    pub.setVisible(true);
                    constraints=null;

                    mzname1.removeAllItems();
                    sop1.removeAllItems();

                    mzname1.setVisible(false);
                    sop1.setVisible(false);
                    count11=0;
                    count22=0;
                    count100=0;
                    count111=0;
                    constraints=null;
                    constraints1=null;

                    try
                      {
                         s=con.createStatement();
                         rs = s.executeQuery("SELECT COUNT(MZNAME) FROM STD_RESERVE WHERE ADNO='"+Login.cname.getText().toUpperCase()+"'");
                         if( rs.next() )
                           {
                              count111=rs.getInt(1);
                              if( count111 < MIN_MZS  ) {  }
                              else
                                {
                                   if(count111 < MAX_MZS)
                                     {
                                        int ws = JOptionPane.showConfirmDialog (p, count111+" MAGAZINES R RESERVED . WILL U RESERVE ONE MORE....?","WARNING",JOptionPane.YES_NO_OPTION  ) ;
                                        if( ws==0 ) {    }
                                        else {  setVisible(false);  }
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"U HAVE BEEN RESERVED MAX MAGAZINES ,U CAN ' T  RESERVE  ANOTHER  !");
                                        title2.setEditable(false);
                                        volume.setEditable(false);
                                        month.setEditable(false);
                                        pub.setEditable(false);
                                        ins.setEnabled(false);
                                        next.setEnabled(false);
                                     }
                                }
                           }
                      }
                    catch(SQLException se)
                      {
                         JOptionPane.showMessageDialog(null,se.getMessage());
                         se.printStackTrace();
                      }
                 }
            }
          );
          can.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
             {
               setVisible(false);
             }
          }
          );
       }

     private class ConfirmDialog1 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog1(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  BOOK - NAME  .  .  ."),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         n1.setText("");
                         n1.setVisible(true);
                         bookname1.removeAllItems();
                         bookname1.setVisible(false);

                         aut11.setText("");
                         aut11.setVisible(true);
                         aut1s1.removeAllItems();
                         aut1s1.setVisible(false);

                         aut22.setText("");
                         aut22.setVisible(true);
                         aut2s1.removeAllItems();
                         aut2s1.setVisible(false);

                         aut33.setText("");
                         aut33.setVisible(true);
                         aut3s1.removeAllItems();
                         aut3s1.setVisible(false);

                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         //new ConfirmDialog1((Frame)p,"WARNING");
                         CONFIRM=2;
                         setVisible(false);
                      }
                 }
               );
               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
     private class ConfirmDialog2 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog2(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  FIRST_AUTHOR  .  .  .  ! "),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         n1.setText("");
                         n1.setVisible(true);
                         bookname1.removeAllItems();
                         bookname1.setVisible(false);

                         aut11.setText("");
                         aut11.setVisible(true);
                         aut1s1.removeAllItems();
                         aut1s1.setVisible(false);

                         aut22.setText("");
                         aut22.setVisible(true);
                         aut2s1.removeAllItems();
                         aut2s1.setVisible(false);

                         aut33.setText("");
                         aut33.setVisible(true);
                         aut3s1.removeAllItems();
                         aut3s1.setVisible(false);

                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         //new ConfirmDialog1((Frame)p,"WARNING");
                         CONFIRM=2;
                         setVisible(false);

                      }
                 }
               );
               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
     private class ConfirmDialog3 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog3(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  CD - NAME  .  .  ."),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         n1.setText("");
                         n1.setVisible(true);
                         bookname1.removeAllItems();
                         bookname1.setVisible(false);

                         aut11.setText("");
                         aut11.setVisible(true);
                         aut1s1.removeAllItems();
                         aut1s1.setVisible(false);

                         aut22.setText("");
                         aut22.setVisible(true);
                         aut2s1.removeAllItems();
                         aut2s1.setVisible(false);

                         aut33.setText("");
                         aut33.setVisible(true);
                         aut3s1.removeAllItems();
                         aut3s1.setVisible(false);

                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         //new ConfirmDialog1((Frame)p,"WARNING");
                         CONFIRM=2;
                         setVisible(false);
                      }
                 }
               );
               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
     private class ConfirmDialog4 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog4(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  CD VERSION  .  .  ."),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         n1.setText("");
                         n1.setVisible(true);
                         bookname1.removeAllItems();
                         bookname1.setVisible(false);

                         aut11.setText("");
                         aut11.setVisible(true);
                         aut1s1.removeAllItems();
                         aut1s1.setVisible(false);

                         aut22.setText("");
                         aut22.setVisible(true);
                         aut2s1.removeAllItems();
                         aut2s1.setVisible(false);

                         aut33.setText("");
                         aut33.setVisible(true);
                         aut3s1.removeAllItems();
                         aut3s1.setVisible(false);

                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         //new ConfirmDialog1((Frame)p,"WARNING");
                         CONFIRM=2;
                         setVisible(false);
                      }
                 }
               );
               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
    private class ConfirmDialog5 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog5(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  MAGAZINE - NAME  .  .  ."),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         n1.setText("");
                         n1.setVisible(true);
                         bookname1.removeAllItems();
                         bookname1.setVisible(false);

                         aut11.setText("");
                         aut11.setVisible(true);
                         aut1s1.removeAllItems();
                         aut1s1.setVisible(false);

                         aut22.setText("");
                         aut22.setVisible(true);
                         aut2s1.removeAllItems();
                         aut2s1.setVisible(false);

                         aut33.setText("");
                         aut33.setVisible(true);
                         aut3s1.removeAllItems();
                         aut3s1.setVisible(false);

                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         //new ConfirmDialog1((Frame)p,"WARNING");
                         CONFIRM=2;
                         setVisible(false);
                      }
                 }
               );
               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
     private class ConfirmDialog6 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog6(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  VOLUME  .  .  ."),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         n1.setText("");
                         n1.setVisible(true);
                         bookname1.removeAllItems();
                         bookname1.setVisible(false);

                         aut11.setText("");
                         aut11.setVisible(true);
                         aut1s1.removeAllItems();
                         aut1s1.setVisible(false);

                         aut22.setText("");
                         aut22.setVisible(true);
                         aut2s1.removeAllItems();
                         aut2s1.setVisible(false);

                         aut33.setText("");
                         aut33.setVisible(true);
                         aut3s1.removeAllItems();
                         aut3s1.setVisible(false);

                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         //new ConfirmDialog1((Frame)p,"WARNING");
                         CONFIRM=2;
                         setVisible(false);
                      }
                 }
               );
               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
     public static void main(String a[])
       {
          new StudentInput();
       }
  }