package org.ulibsoft.returns;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import org.ulibsoft.enroll.MzLibrary2;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class MzStdRet extends JFrame
  {
    private JLabel code, adno, sname, sbranch, syear, image,fine;
    private Icon icon,icon1;
    private JLabel mzname, volume, idate, rdate ;
    private JTextField cd, ano, sn, sb, sy, mn, volume1, i1, r1,f1 ;
    private JTable bookdetails, templibrary, bktransaction ;
    private JButton ret, pl, can, next, ren,exc, pay ;
    private JPanel rtpn,p1,p2,p3,finepnl;
    private JTextArea fineArea;
    private Container c;
    private static int fineValue,fineValue1;
    private boolean confirm=false;
    private MzStdRet parent;

    private Statement s;
    private Connection con;
    private ResultSet rs, rs1,rs2 ;

    private FileOutputStream fout,fout1;
    private File file;
    private String fp,fp1;
    private static String PATH;
    private int PERIOD,NO_REN,NO_OF_REN,FINE_MZ;
    private String no_of_ren;

    private String  temp;
    private int fineCount = 1;
    private int difference ;

    public MzStdRet()
      {
         super("Retriving Magazines");
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         show();

         createComponents();
         try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
          try
         {
             s=con.createStatement();
             getTable1();
             rs=s.executeQuery(" SELECT MZ.ACCESSNO,MZ.MZNAME,MZ.VOLUME FROM MZDETAILS MZ WHERE MZ.ACCESSNO NOT IN(SELECT MZT.MZCODE FROM MZTRANSACTION MZT WHERE MZT.MZCODE=MZ.ACCESSNO) AND MZ.ACCESSNO NOT IN(SELECT MZT1.MZCODE FROM MZTRANSACTION1 MZT1 WHERE MZT1.MZCODE=MZ.ACCESSNO)");
             while(rs.next())
             {
                 getTable2(rs);
             }

              s.close();
         }catch(SQLException sqlex)
         {
            JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
         }
         try
         {
          s=con.createStatement();
          rs=s.executeQuery("SELECT IMAGE_PATH,RENEIVAL_PERIOD_MZ,NO_OF_RENEIVALS_MZ,FINE_MZ FROM KEYCONSTRAINTS WHERE ID=1");
          while(rs.next())
          {
           PATH=rs.getString(1);
           PERIOD=rs.getInt(2);
           NO_REN=rs.getInt(3);
           FINE_MZ=rs.getInt(4);
          }
         }catch(SQLException se)
         {
          JOptionPane.showMessageDialog(null,se.getMessage());
          se.printStackTrace();
         }
         componentListener();
         myadapter5 myp=new myadapter5();
         addWindowListener(myp);
      }
    private void createComponents()
      {
         c = getContentPane();
         c.setLayout( new AbsoluteLayout() );
         c.setBackground( new Color( 0, 0, 40 ) );

         rtpn = new JPanel( new AbsoluteLayout() );
         rtpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "RETRIVE -- MAGAZINES", 1, 2, c.getFont(), Color.magenta));
         rtpn.setBackground(c.getBackground());
         c.add(rtpn,new AbsoluteConstraints(85,20,630,195));

         finepnl = new JPanel( new BorderLayout() );
         finepnl.setLayout(new AbsoluteLayout());
         finepnl.setBackground(c.getBackground());
         finepnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "FINE -- ACCESS", 1, 2,c.getFont(), Color.magenta));
         finepnl.setVisible(false);
         c.add(finepnl,new AbsoluteConstraints(150, 220, 500, 120));

         //INITIALIZE BOOK DETAILS

         code = new JLabel ( "ACCESS-NO " );
         code.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( code, new AbsoluteConstraints (42, 23 ) );

         cd = new JTextField( );
         cd.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
         cd.setForeground ( new Color ( 255, 0, 153 ) );
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( cd, new AbsoluteConstraints ( 120, 20, 125, 18 ) );

         mzname = new JLabel("TITLE");
         mzname.setForeground (new Color (120, 120, 153));
         rtpn.add (mzname, new AbsoluteConstraints( 79, 50));

         mn = new JTextField( );
         mn.setEditable(false);
         mn.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40)) );
         mn.setForeground ( new Color ( 255, 0, 153 ) );
         mn.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( mn, new AbsoluteConstraints ( 120, 48, 125, 18 ) );

         volume = new JLabel( "VOLUME" );
         volume.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( volume, new AbsoluteConstraints ( 59+2, 77 ) );

         volume1 = new JTextField( );
         volume1.setEditable( false );
         volume1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40)) );
         volume1.setForeground ( new Color ( 255, 0, 153 ) );
         volume1.setCaretColor ( new Color (0, 204, 102 ) );
         rtpn.add ( volume1, new AbsoluteConstraints ( 120, 76, 125, 18 ) );

         //INITIALIZE STUDENT DETAILS

         adno = new JLabel( "ADMISSION-NO" );
         adno.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( adno, new AbsoluteConstraints ( 395, 23 ) );

         ano = new JTextField( );
         ano.setEditable( false );
         ano.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
         ano.setForeground ( new Color ( 255, 0, 153 ) );
         ano.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( ano, new AbsoluteConstraints ( 490, 20, 125, 18 ) );

         image = new JLabel();
         image.setBounds(260,20,100,110);
         image.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.cyan ));
         rtpn.add(image,new AbsoluteConstraints(260,20,100,110));

         icon1=new ImageIcon("empty.jpg");

         sname = new JLabel( "STUDENT-NAME" );
         sname.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( sname, new AbsoluteConstraints ( 390, 50 ) );

         sn = new JTextField( );
         sn.setEditable( false );
         sn.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         sn.setForeground ( new Color ( 255, 0, 153 ) );
         sn.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( sn, new AbsoluteConstraints ( 490, 48, 125, 20 ) );

         sbranch = new JLabel( " COURSE " );
         sbranch.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( sbranch, new AbsoluteConstraints (430,77 ) );

         sb = new JTextField( );
         sb.setEditable( false );
         sb.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40)) );
         sb.setForeground ( new Color ( 255, 0, 153 ) );
         sb.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( sb, new AbsoluteConstraints ( 490, 76, 125, 20 ) );

         syear = new JLabel( "SEMESTER" );
         syear.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( syear, new AbsoluteConstraints ( 419, 103 ) );

         sy = new JTextField( );
         sy.setEditable( false );
         sy.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         sy.setForeground ( new Color ( 255, 0, 153 ) );
         sy.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( sy, new AbsoluteConstraints ( 490, 102, 125, 20 ) );

         //INITTALIZE DATE DETAILS

         idate = new JLabel( "ISSUE-DATE" );
         idate.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( idate, new AbsoluteConstraints ( 40+2, 103 ) );

         i1 = new JTextField( );
         i1.setEditable( false );
         i1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         i1.setForeground ( new Color ( 255, 0, 153 ) );
         i1.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( i1, new AbsoluteConstraints ( 120, 102, 125, 18 ) );

         rdate = new JLabel( "RECEIVE-DATE" );
         rdate.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( rdate, new AbsoluteConstraints ( 25+2, 131 ) );

         r1 = new JTextField( );
         r1.setEditable( false );
         r1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40)) );
         r1.setForeground ( new Color ( 255, 0, 153 ) );
         r1.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( r1, new AbsoluteConstraints ( 120, 130, 125, 18 ) );


         fine = new JLabel( "FINE" );
         fine.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( fine, new AbsoluteConstraints ( 456, 131 ) );

         f1 = new JTextField( );
         f1.setEditable( false );
         f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40)) );
         f1.setForeground ( new Color ( 255, 0, 153 ) );
         f1.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( f1, new AbsoluteConstraints ( 490, 130, 125, 18 ) );
         //BUTTON CREATION

         ret = new JButton( "RECIEVE" );
         ret.setMnemonic ('R');
         ret.setBackground ( Color.cyan );
         ret.setForeground(Color.black);
         ret.setBorder(new BevelBorder(0));
         ret.setEnabled (false);
         rtpn.add ( ret, new AbsoluteConstraints ( 60, 160, 100, 22 ) );

         ren = new JButton( "RENEWAL" );
         ren.setMnemonic ('E');
         ren.setBackground ( Color.cyan );
         ren.setForeground(Color.black);
         ren.setBorder(new BevelBorder(0));
         ren.setEnabled (false);
         rtpn.add ( ren, new AbsoluteConstraints ( 162, 160, 100, 22 ) );

         next = new JButton( "NEXT>>>" );
         next.setMnemonic ('N');
         next.setBackground ( Color.cyan  );
         next.setForeground ( Color.black );
         next.setBorder ( new BevelBorder(0) );
         rtpn.add ( next, new AbsoluteConstraints ( 264, 160, 100, 22 ) );

         pl = new JButton( "PLIST" );
         pl.setMnemonic ('P');
         pl.setBackground ( Color.cyan );
         pl.setForeground ( Color.black );
         pl.setBorder ( new BevelBorder(0) );
         pl.setEnabled(false);
         rtpn.add ( pl, new AbsoluteConstraints ( 366, 160, 100, 22 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         rtpn.add ( can, new AbsoluteConstraints( 468, 160, 100, 22 ) );

          exc = new JButton("EXCUSE");
         exc.setBackground ( Color.cyan);
         exc.setForeground( Color.black);
         exc.setMnemonic('U');
         exc.setBorder(new BevelBorder(0));
         exc.setVisible(false);
         rtpn.add ( exc, new AbsoluteConstraints( 162, 160, 100, 22 ) );

         pay = new JButton("PAY");
         pay.setBackground ( Color.cyan);
         pay.setForeground( Color.black);
         pay.setMnemonic('Y');
         pay.setBorder(new BevelBorder(0));
         pay.setVisible(false);
         rtpn.add ( pay, new AbsoluteConstraints( 60, 160, 100, 22 ) );

         //TEXTAREA CREATION

         fineArea = new JTextArea();
         fineArea.setEditable(true);
         fineArea.setFont(new Font("Monotype Corsiva",Font.ITALIC,18));
         finepnl.add(new JScrollPane(fineArea),new AbsoluteConstraints(0,0,500,120));


         p1 = new JPanel( new BorderLayout() );
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "MAGAZINE -- DATABASE", 1, 2,c.getFont(), Color.magenta));

         c.add(p1,new AbsoluteConstraints(15, 220, 480, 120));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT -- TRANSACTION", 1, 2, c.getFont(), Color.magenta));

         c.add(p2,new AbsoluteConstraints(30,350,730,200));

         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE MAGAZINES", 1, 2,c.getFont(), Color.magenta));

         c.add(p3,new AbsoluteConstraints(510,220,270,120));

         try
             {
                fout=new FileOutputStream("c:\\windows\\report35.html");
             }catch(Exception se)
             {
             }
            fp="<HTML>";
            fp+="<BODY BGCOLOR=#f95360><FONT COLOR="+"CYAN"+" SIZE="+"5"+"><CENTER>FINE RECIPT FORM</CENTER></BODY>";
            fp+="<TABLE ALIGN="+" CENTER "+" BORDER="+"1 "+" WIDTH="+"400 "+" CELLSPACING="+" 0 >";
            fp+="<CAPTION>FINE AMOUNT REPORT</CAPTION>";
            fp+="<BR><TR BGCOLOR="+"#AADD00"+">";
             try
             {
                fout1=new FileOutputStream("c:\\windows\\report36.html");
             }catch(Exception se)
             {
             }

            fp1="<HTML>";
            fp1+="<BODY BGCOLOR=#f95360><FONT COLOR="+"CYAN"+" SIZE="+"5"+"><CENTER>FINE RECIPT FORM</CENTER></BODY>";
            fp1+="<TABLE ALIGN="+" CENTER "+" BORDER="+"1 "+" WIDTH="+"400 "+" CELLSPACING="+" 0 >";
            fp1+="<CAPTION>FINE AMOUNT REPORT</CAPTION>";
            fp1+="<BR><TR BGCOLOR="+"#AADD00"+">";

         setVisible(true);
     }


   private void componentListener()
     {
        cd.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                ret.setEnabled (true);
                ren.setEnabled(true);
                try
                  {
                     s = con.createStatement ();

                     rs = s.executeQuery("SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR, MZ.MZNAME, MZ.VOLUME, MZ.MZCODE, TO_CHAR(MZT.IDATE,'DD-MON-YYYY'), TO_CHAR(MZT.RDATE,'DD-MON-YYYY') FROM MZTRANSACTION MZT, STUDENTDETAILS STD,MZDETAILS MZ WHERE MZT.ADNO=STD.ADNO AND MZ.ACCESSNO=MZT.MZCODE AND MZT.MZCODE="+"'"+ cd.getText()+"'" );
                       if( rs.next() )
                        {
                          // SET TRANSACTION DETAILS
                          ano.setText( rs.getString(1) ) ;
                          sn.setText( rs.getString(2) ) ;
                          sb.setText( rs.getString(3) ) ;
                          sy.setText( rs.getString(4) ) ;
                          mn.setText( rs.getString(5) ) ;
                          volume1.setText ( rs.getString(6) ) ;
                          i1.setText(rs.getString(8)) ;
                          r1.setText(rs.getString(9)) ;
                          icon = new ImageIcon(PATH+ano.getText()+".jpg");
                          image.setIcon (icon);

                          PassWordDialog PWD=new PassWordDialog(parent,"PASSWORD CONFIRMATION");
                          r1.setEditable(true);

                        }
                       else
                        {
                          JOptionPane.showMessageDialog (null,"MAGAZINE'S RECORD NOT FOUND","CURRENT TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                          ret.setEnabled(false);
                          ren.setEnabled(false);
                          pl.setEnabled(false);
                        }


                     s.close();
                  }
                catch(SQLException sqlex)
                  {
                     JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
                  }
              }

          }
        );

        ret.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                try
                  {
                     s = con.createStatement();
                       rs=s.executeQuery("SELECT TO_DATE('"+r1.getText()+"') -  TO_DATE(SYSDATE) FROM DUAL");
                       if( rs.next() )
                        {
                           int d= rs.getInt(1);
                           if(d>=0)
                            {                  //INSERTION

                               /*int r = s.executeUpdate(" INSERT INTO MAGAZINELIBRARY (MZNAME, VOLUME, MZCODE) VALUES ( "+"'"+mn.getText()+"',"
                                                                                                                    +"'"+volume1.getText()+"',"
                                                                                                                    +"'"+cd.getText()+"'"+")" );*/
                               con.commit();

                               rs = s.executeQuery( " SELECT MZ.ACCESSNO,MZ.MZNAME,MZ.VOLUME FROM MZDETAILS MZ WHERE MZ.ACCESSNO NOT IN(SELECT MZT.MZCODE FROM MZTRANSACTION MZT WHERE MZT.MZCODE=MZ.ACCESSNO) AND MZ.ACCESSNO NOT IN(SELECT MZT1.MZCODE FROM MZTRANSACTION1 MZT1 WHERE MZT1.MZCODE=MZ.ACCESSNO)" );
                               if(rs.next())
                                {
                                   getTable2(rs);
                                }
                               else
                                {
                                  JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY","AVAILABLE BOOKS-DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                }
                                                  //DELETION

                               int AS=s.executeUpdate("UPDATE MZTRANSACTION SET RDATE=TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE MZCODE="+"'"+cd.getText()+"'"+"AND IDATE="+"'"+i1.getText()+"'");
                               con.commit();

                               int g=s.executeUpdate("UPDATE BOOKDETAILS SET RDATE=TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE MZCODE="+"'"+cd.getText()+"'"+"AND IDATE="+"'"+i1.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'");
                               con.commit();

                               int x = s.executeUpdate("DELETE FROM MZTRANSACTION WHERE MZCODE ="+"'"+cd.getText()+"'" );
                               con.commit();
                               getTable1();
                               getTable( cd.getText() );
                               ren.setEnabled(false);
                               rs=s.executeQuery("SELECT ISSUED FROM MZDETAILS WHERE ACCESSNO="+"'"+cd.getText() +"'");
                               while(rs.next())
                               {
                                 if(rs.getString(1).equals("1"))
                                 {
                                   NewDialog nop=new NewDialog((Frame)parent,"CONFIRMATION" );
                                 }
                               }
                            }
                           else
                            {
                              JOptionPane.showMessageDialog(null,"ILLEGAL RETRIVING !...","Message",JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                     s.close();
                  }
                catch(SQLException sqlex)
                  {
                     JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
                  }
              }
          }
        );

        ren.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
               {
                 ret.setEnabled(false);
                 pl.setEnabled(false);
                 try
                   {
                      s = con.createStatement();
                     // int w = s.executeUpdate("UPDATE MZTRANSACTION SET RDATE="+"'"+r1.getText()+"'"+"WHERE MZCODE="+"'"+cd.getText()+"'");
                      rs=s.executeQuery("SELECT NO_OF_REN  FROM MZTRANSACTION WHERE MZCODE="+"'"+cd.getText()+"'");
                       while(rs.next())
                       {
                         no_of_ren=rs.getString(1);
                       }

                       if(no_of_ren != null)
                       {    NO_OF_REN=Integer.parseInt(no_of_ren);
                          if(NO_OF_REN < NO_REN)
                          {
                           int w = s.executeUpdate("UPDATE MZTRANSACTION SET RDATE=(SELECT RDATE+"+PERIOD+ " FROM MZTRANSACTION  WHERE MZCODE="+"'"+cd.getText()+"') WHERE MZCODE="+"'"+cd.getText()+"'");
                           int ren=s.executeUpdate("UPDATE MZTRANSACTION SET NO_OF_REN ='"+(NO_OF_REN + 1)+"' WHERE MZCODE="+"'"+cd.getText()+"'");
                           con.commit();
                           getTable1();
                          }
                          else
                          {
                             JOptionPane.showMessageDialog(null,"RENEIVAL TIMES EXCEEDED");
                          }
                      }
                       else
                       {
                           int w = s.executeUpdate("UPDATE MZTRANSACTION SET RDATE=(SELECT RDATE+"+PERIOD+ " FROM MZTRANSACTION  WHERE MZCODE="+"'"+cd.getText()+"') WHERE MZCODE="+"'"+cd.getText()+"'");
                           int ren=s.executeUpdate("UPDATE MZTRANSACTION SET NO_OF_REN ='1' WHERE MZCODE="+"'"+cd.getText()+"'");
                           con.commit();
                           getTable1();

                       }
                       rs=s.executeQuery("SELECT TO_CHAR(RDATE,'DD-MON-YYYY') FROM MZTRANSACTION WHERE MZCODE="+"'"+cd.getText()+"'");
                       rs.next();
                       r1.setText(rs.getString(1));
                      s.close();
                   }
                 catch(SQLException sq)
                   {
                     JOptionPane.showMessageDialog(null,sq.getMessage());
                   }
                 ren.setEnabled(false);
               }
          }
        );

        next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                //RESET BOOK VALUES
                  cd.setText("");
                  mn.setText("");
                  volume1.setText("");
                  f1.setText("");
                  fineArea.setText("");
                  fineCount=1;

                //RESET STUDENT VALUES
                  ano.setText("");
                  sn.setText("");
                  sb.setText("");
                  sy.setText("");
                  image.setIcon (icon1);

                //RESET DATE VALUES
                  i1.setText("");
                  r1.setText("");
                  r1.setEditable(false);

                  pay.setVisible(false);
                  exc.setVisible(false);
                  ret.setVisible(true);
                  ren.setVisible(true);
                  ret.setEnabled (false);
                  ren.setEnabled(false);
                  pl.setEnabled (false);
                  finepnl.setVisible(false);
                  p1.setVisible(true);
                  p3.setVisible(true);
              }
          }
        );

        pl.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                try
                  {
                      finepnl.setVisible(false);
                      p1.setVisible(true);
                      p3.setVisible(true);
                    s = con.createStatement ();
                     rs=s.executeQuery("SELECT TO_DATE('"+r1.getText()+"') -  TO_DATE(SYSDATE) FROM DUAL");
                      if( rs.next() )
                        {
                           int k=rs.getInt(1);
                           if(k<0)
                            {
                                                //INSERTION

                              /* int r = s.executeUpdate(" INSERT INTO MAGAZINELIBRARY (MZNAME, VOLUME, MZCODE) VALUES ( "+"'"+mn.getText()+"',"
                                                                                                                    +"'"+volume1.getText()+"',"
                                                                                                                    +"'"+cd.getText()+"'"+")" );*/
                               con.commit();

                               rs = s.executeQuery( " SELECT MZ.ACCESSNO,MZ.MZNAME,MZ.VOLUME FROM MZDETAILS MZ WHERE MZ.ACCESSNO NOT IN(SELECT MZT.MZCODE FROM MZTRANSACTION MZT WHERE MZT.MZCODE=MZ.ACCESSNO) AND MZ.ACCESSNO NOT IN(SELECT MZT1.MZCODE FROM MZTRANSACTION1 MZT1 WHERE MZT1.MZCODE=MZ.ACCESSNO)" );
                               if(rs.next())
                                {
                                   getTable2(rs);
                                }

                                                //DELETION
                               int AS=s.executeUpdate("UPDATE MZTRANSACTION SET RDATE = TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE MZCODE="+"'"+cd.getText()+"'"+"AND IDATE="+"'"+i1.getText()+"'");
                               con.commit();
                               int g=s.executeUpdate("UPDATE BOOKDETAILS SET RDATE = TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE MZCODE="+"'"+cd.getText()+"'"+"AND IDATE="+"'"+i1.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'");
                               con.commit();
                               int x = s.executeUpdate("DELETE FROM MZTRANSACTION WHERE MZCODE ="+"'"+cd.getText()+"'" );
                               con.commit();
                               getTable1();
                               getTable( cd.getText() );
                               rs=s.executeQuery("SELECT ISSUED FROM MZDETAILS WHERE ACCESSNO="+"'"+cd.getText() +"'");
                               while(rs.next())
                               {
                                 if(rs.getString(1).equals("1"))
                                 {
                                   NewDialog nop=new NewDialog((Frame)parent,"CONFIRMATION" );
                                 }
                               }
                            }
                           else
                            {
                              JOptionPane.showMessageDialog(null,"EMPTY PENDING LIST !...","Message",JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                     s.close();
                  }
                catch(SQLException sqlex)
                  {
                     JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
                  }
                pay.setEnabled(false);
                exc.setEnabled(false);
                pl.setEnabled(false);
              }
          }
        );

         pay.addActionListener(new ActionListener()
          {
              public void actionPerformed(ActionEvent e)
                {
                  int sd=JOptionPane.showConfirmDialog(null,"ARE U SURE HE/SHE HAS PAID ! . . .","CONFIRMATION",JOptionPane.YES_NO_OPTION);
                  if(sd==0)
                   {
                      ConfirmDialog lk=new ConfirmDialog((Frame)parent,"CONFIRMATION");
                   }
                }
           }
        );
        exc.addActionListener(new ActionListener()
          {
              public void actionPerformed(ActionEvent e)
                {
                    ConfirmDialog2 lkp=new ConfirmDialog2((Frame)parent,"CONFIRMATION");

                }

          }
        );

        can.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                setVisible(false);
               }
          }
        );

        addMouseListener( new MouseAdapter()
          {
             public void mouseClicked(MouseEvent e)
               {
                 if (e.isMetaDown())
                   {
                     PopUpMenu p = new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                     //p.setFrame(Index.mzri);
                   }
               }
          }
        );

     }

   private class ConfirmDialog extends JDialog
     {
         private JLabel pass;
         private JPasswordField pwd;
         private JButton print;
         private Container k;
          private final int fine=Integer.parseInt(f1.getText());
         public ConfirmDialog(Frame par,String title)
           {
              // fineValue=Integer.parseInt(f1.getText());
               k=getContentPane();
               getContentPane().setLayout(new AbsoluteLayout());
               getContentPane().setBackground(new Color(120,120,180));
               getContentPane().add(pass=new JLabel("PASSWORD :"),new AbsoluteConstraints(30,22));
               pass.setForeground( Color.white );
               getContentPane().add(pwd=new JPasswordField(),new AbsoluteConstraints(120,20,130,20));
               pwd.setBorder ( new MatteBorder ( 1, 1, 1, 1, getContentPane().getBackground() ));

               print = new JButton( "PRINT_RECIPT " ) ;
               print.setBackground (Color.cyan);
               print.setForeground(Color.black);
               print.setEnabled(false);
               print.setBorder(new BevelBorder(0));
               print.setMnemonic('P');
               getContentPane().add(print,new AbsoluteConstraints(80,50,140,25));

               pwd.addActionListener(new ActionListener()
                 {
                     public void actionPerformed(ActionEvent e2)
                       {
                           if( new String(pwd.getPassword()).equals("SUBMITTED"))
                           {
                               confirm=true;
                               if( confirm == true )
                                {
                                   try
                                     {
                                        s = con.createStatement();
                                        int wq=s.executeUpdate("UPDATE BOOKDETAILS SET FINEAMOUNT="+"'"+f1.getText()+"'"+"WHERE MZCODE="+"'"+cd.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'"+"AND RDATE="+"'"+r1.getText()+"'");
                                        con.commit();
                                        int wqd=s.executeUpdate("UPDATE BOOKDETAILS SET FINE_PAID="+"'"+f1.getText()+"'"+"WHERE MZCODE="+"'"+cd.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'"+"AND RDATE="+"'"+r1.getText()+"'");
                                        f1.setText(String.valueOf(0));
                                        con.commit();
                                        int Qwq=s.executeUpdate("UPDATE BOOKDETAILS SET REASON="+"'"+fineArea.getText()+"'"+"WHERE MZCODE="+"'"+cd.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'"+"AND RDATE="+"'"+r1.getText()+"'");

                                        fineArea.setText("");
                                        con.commit();
                                        pl.setEnabled(true);
                                        s.close();
                                        print.setEnabled(true);
                                        //setVisible(false);
                                     }
                                   catch(SQLException  sqlex)
                                     {
                                        JOptionPane.showMessageDialog(null,sqlex.getMessage());
                                     }
                                 }
                           }
                           else
                           {
                               confirm=false;
                               JOptionPane.showMessageDialog(null,"INVALID PASSWORD !. . . ");

                           }
                       }

                 }
               );
               print.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     fp+="<TH>"+"STUDENT"+"</TH>";
                     fp+="<TH>"+"FINE AMOUNT"+"</TH></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">ADNO : "+ano.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+">RS "+fine+"/- PAID</TD></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">NAME : "+sn.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">COURSE : "+sb.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">YEAR : "+sy.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp+="</TABLE></BODY></HTML>";
                     try
                       {
                          fout.write(fp.getBytes());
                       }catch(Exception se){ }

                     Runtime rt = Runtime.getRuntime();
                     try
                       {
                          rt.exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE c:\\windows\\report35.html");
                       }
                     catch(IOException ex)
                       {
                       }
                     setVisible(false);
                 }
            }
          );
          addWindowListener(new WindowAdapter()
            {
               public void windowClosing(WindowEvent e)
                 {
                    JOptionPane.showMessageDialog(null,"U MUST PRINT RECEIPT  !  .  .  .");
                    new ConfirmDialog((Frame)parent,"CONFIRMATION");
                 }
            }
          );
               setBounds(250, 250, 300, 110);
               setVisible(true);
           }
     }
    private class ConfirmDialog2 extends JDialog
     {

         private JLabel pass,amt;
         private JTextField amt1;
         private JPasswordField pwd;
         private JButton sumit,rest1;
         private Container k;
         private final int fine=Integer.parseInt(f1.getText());
         public ConfirmDialog2(Frame par,String title)
           {
               //fineValue=Integer.parseInt(f1.getText());
               getContentPane().setLayout(new AbsoluteLayout());
               getContentPane().setBackground(new Color(120,120,180));
               getContentPane().add(amt=new JLabel("FINE AMOUNT :"),new AbsoluteConstraints(30,30));
               getContentPane().add(pass=new JLabel("PASSWORD :"),new AbsoluteConstraints(30,60));
               pass.setForeground( Color.white );
               amt.setForeground( Color.white );
               getContentPane().add(amt1=new JTextField(),new AbsoluteConstraints(120,28,130,20));
               getContentPane().add(pwd=new JPasswordField(),new AbsoluteConstraints(120,58,130,20));
               //pass.setVisible(false);
               //pwd.setVisible(false);
               getContentPane().add(sumit=new JButton("SUBMIT"),new AbsoluteConstraints(40,95,100,25));
               sumit.setMnemonic('S');
               sumit.setBackground ( Color.cyan  );
               sumit.setForeground ( Color.black );
               sumit.setBorder ( new BevelBorder(0) );
               getContentPane().add(rest1=new JButton("PRINT"),new AbsoluteConstraints(145,95,100,25));
               rest1.setMnemonic('P');
               rest1.setBackground ( Color.cyan  );
               rest1.setForeground ( Color.black );
               rest1.setBorder ( new BevelBorder(0) );
               rest1.setEnabled(false);

               amt1.setText(f1.getText());
               amt1.addActionListener(new ActionListener()
                 {
                     public void actionPerformed(ActionEvent e)
                       {
                          // pass.setVisible(true);
                          // pwd.setVisible(true);
                       }
                 }
               );
               sumit.addActionListener(new ActionListener()
                 {
                     public void actionPerformed(ActionEvent e2)
                       {
                           if( new String(pwd.getPassword()).equals("SUBMITTED"))
                           {
                               confirm=true;
                               if( confirm == true )
                                {
                                   try
                                     {
                                        s = con.createStatement();
                                        int wq=s.executeUpdate("UPDATE BOOKDETAILS SET FINEAMOUNT="+"'"+f1.getText()+"'"+"WHERE MZCODE="+"'"+cd.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'"+"AND RDATE="+"'"+r1.getText()+"'");
                                        int wqD=s.executeUpdate("UPDATE BOOKDETAILS SET FINE_PAID="+"'"+amt1.getText()+"'"+"WHERE MZCODE="+"'"+cd.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'"+"AND RDATE="+"'"+r1.getText()+"'");
                                        int wq1=s.executeUpdate("UPDATE BOOKDETAILS SET REASON="+"'"+amt1.getText()+"'"+"WHERE MZCODE="+"'"+fineArea.getText()+"'"+"AND ADNO="+"'"+ano.getText()+"'"+"AND RDATE="+"'"+r1.getText()+"'");
                                        f1.setText(amt1.getText());
                                        con.commit();
                                        pl.setEnabled(true);
                                        finepnl.setVisible(false);
                                        pay.setEnabled(false);
                                        exc.setEnabled(false);
                                        s.close();
                                        rest1.setEnabled(true);
                                        //setVisible(false);
                                     }
                                   catch(SQLException  sqlex)
                                     {
                                        JOptionPane.showMessageDialog(null,sqlex.getMessage());
                                     }
                                 }
                           }
                           else
                           {
                               confirm=false;
                               JOptionPane.showMessageDialog(null,"INVALID PASSWORD !. . . ");

                           }
                       }

                 }
               );
               rest1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     fineValue1=Integer.parseInt(f1.getText());

                     fp1+="<TH>"+"STUDENT"+"</TH>";
                     fp1+="<TH>"+"FINE AMOUNT"+"</TH></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">ADNO : "+ano.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+">TOTAL AMOUNT RS "+fine+"/- </TD></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">NAME : "+sn.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+">PAID AMOUNT RS "+fineValue1+"/- </TD></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">COURSE : "+sb.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">YEAR : "+sy.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp1+="</TABLE></BODY></HTML>";
                     try
                       {
                          fout1.write(fp1.getBytes());
                       }catch(Exception se){ }

                     Runtime rt = Runtime.getRuntime();
                     try
                       {
                          rt.exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE c:\\windows\\report36.html");
                       }
                     catch(IOException ex)
                       {
                       }
                     setVisible(false);
                 }
            }
          );
              addWindowListener(new WindowAdapter()
            {
               public void windowClosing(WindowEvent e)
                 {
                    JOptionPane.showMessageDialog(null,"U MUST PRINT RECEIPT  !  .  .  .");
                    new ConfirmDialog2((Frame)parent,"CONFIRMATION");
                 }
            }
          );
               setBounds(250, 250, 300, 170);
               setVisible(true);
           }
     }

  private class PassWordDialog extends JDialog
      {
         private JPasswordField pwdfd;
         private PassWordDialog parent1;
         private JLabel pwdlb;
         private JButton sub,newbtn,sub1;
         private PassWordDialog parent2;
         public PassWordDialog( Frame parent,String title )
           {
              super( parent,title,true );

              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(pwdlb = new JLabel("PASSWORD :"),new AbsoluteConstraints(25,25));
              pwdlb.setForeground(Color.white);

              getContentPane().add(pwdfd = new JPasswordField(),new AbsoluteConstraints(110,23,130,22));
              pwdfd.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add(sub = new JButton("SUBMIT"),new AbsoluteConstraints(85,55,100,22));
              sub.setBackground(Color.cyan);
              sub.setMnemonic('S');
              sub.setBorder(new BevelBorder(0));



              sub.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {
                        try
                          {
                             s = con.createStatement();
                             rs = s.executeQuery("SELECT PASSWORD,ADNO FROM STUDENTDETAILS WHERE ADNO="+"'"+ano.getText()+"'" );
                             if( rs.next() )
                               {
                                  if( new String( pwdfd.getPassword() ).equals(rs.getString(1)) )
                                    {
                                        ret.setEnabled (true);
                                        ren.setEnabled(true);
                                        setVisible( false );
                                        s.close();
                                        calfine();
                                    }
                                  else
                                    {
                                       JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                                    }
                                 con.commit();

                               }

                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage() );
                          }
                    }
                }
              );

             setBounds(250,250,270,120);
              setVisible(true);
           }
           }
private class NewDialog extends JDialog
     {
        private JLabel msg;
        private JButton yes,no;
        private Container jk;
        public NewDialog(Frame parent,String Title)
        {
         // JOptionPane.showMessageDialog(null,"cdcvdfd");
           jk=getContentPane();
           jk.setLayout(new AbsoluteLayout());
           jk.setBackground(new Color(120,120,180));
           msg=new JLabel("DO  U  WANT  TO  UPDATE  THIS  .  .  .  . .");
           msg.setForeground(Color.white);
           jk.add(msg,new AbsoluteConstraints(20,20));

           yes=new JButton("YES");
           yes.setBorder ( new BevelBorder(0) );
           yes.setBackground(Color.cyan);
           yes.setForeground(Color.magenta);
           yes.setMnemonic('Y');
           jk.add(yes,new AbsoluteConstraints(60-40,60,110,25));

           no=new JButton("NO");
           no.setBorder ( new BevelBorder(0) );
           no.setBackground(Color.cyan);
           no.setForeground(Color.magenta);
           no.setMnemonic('N');
           jk.add(no,new AbsoluteConstraints(172-40,60,110,25));

           yes.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent ae)
             {
                MzLibrary2 cd2=new MzLibrary2();
                setVisible(false);
             }
           }
           );

           no.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent ae)
             {
               setVisible(false);
             }
           }
           );
           setBounds(250,250,270,130);
           setVisible(true);
        }

     }

  private void calfine()
    {
        try
        {
           s=con.createStatement();
           rs1 = s.executeQuery ("SELECT TO_DATE(SYSDATE)- TO_DATE('"+r1.getText()+"') FROM DUAL");
           if( rs1.next() )
                               {
                                 difference = rs1.getInt(1);

                               }
          if( difference > 0 )
               {
                  for( int i =1; i <= difference ; i++ )
                        {
                           rs2 = s.executeQuery( " SELECT TO_CHAR(RDATE+"+i+",'DAY') FROM BKTRANSACTION " );
                               if( rs2.next() )
                                    {
                                      temp = rs2.getString(1);

                                    if( temp.startsWith("S",0) ){}
                                       else
                                         {
                                          fineCount++ ;
                                         }
                                    }
                        }
                              fineCount = fineCount*FINE_MZ ;

                              f1.setText( ""+fineCount );
                              p1.setVisible( false );
                              p3.setVisible( false );
                              finepnl.setVisible( true );
                              ret.setVisible(false);
                              ren.setVisible(false);
                              exc.setVisible(true);
                              //exc.setVisible(true);
                              pay.setVisible(true);
                            }
                          else
                            {
                              f1.setText("0");
                              p1.setVisible(true);
                              p3.setVisible(true);
                              finepnl.setVisible(false);
                            }

         s.close();
        }
        catch(SQLException se)
        {
           JOptionPane.showMessageDialog(null,se.getMessage());
        }


    }

   private void getTable( String j )
     {
        Vector columnHeads = new Vector();
        Vector rows = new Vector();
        try
          {
             rs = s.executeQuery( " SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE  FROM BOOKDETAILS BD,STUDENTDETAILS STD WHERE BD.MZCODE ="+"'"+cd.getText()+"'"+"AND BD.VALUE='3'"+"AND BD.ADNO=STD.ADNO" );
               if( rs.next() )
                {
                   ResultSetMetaData rsmd = rs.getMetaData();
                   for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                   columnHeads.addElement( rsmd.getColumnName(i) );

                   do
                    {
                       rows.addElement( getNextRow( rs,rsmd ) );
                    }while( rs.next() );

                   bookdetails = new JTable(rows,columnHeads);
                   bookdetails.setBackground(Color.cyan);
                   bookdetails.setEnabled (false);
                   p1.add(bookdetails,BorderLayout.CENTER);
                   JScrollPane sp3 = new JScrollPane(bookdetails);
                   p1.add(sp3,BorderLayout.CENTER);
                   validate();
                }
               else
                {
                  JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !...","BOOK-DATABASE",JOptionPane.INFORMATION_MESSAGE);
                }
          }
        catch( SQLException sqlex )
          {
             sqlex.printStackTrace();
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
          }
      }

    private void getTable1( )
      {

        Vector columnHeads = new Vector();
        Vector rows = new Vector();
        try
          {
            rs = s.executeQuery ( "SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.YEAR,MZ.MZNAME,MZ.VOLUME,MZ.ACCESSNO FROM MZTRANSACTION MZT,STUDENTDETAILS STD,MZDETAILS MZ WHERE MZ.ACCESSNO=MZT.MZCODE AND MZT.ADNO=STD.ADNO" );
            if( rs.next () )
             {
               ResultSetMetaData rsmd = rs.getMetaData();
               for(int i = 1;i <= rsmd.getColumnCount(); i++ )
               columnHeads.addElement( rsmd.getColumnName(i) );

               do
                {
                  rows.addElement( getNextRow( rs,rsmd ) );
                }while( rs.next() );

               bktransaction = new JTable(rows,columnHeads);
               bktransaction.setBackground(Color.pink);
               bktransaction.setEnabled (false);
               p2.add(bktransaction,BorderLayout.CENTER);
               JScrollPane sp1 = new JScrollPane(bktransaction);
               p2.add(sp1,BorderLayout.CENTER);
               validate();
             }
            else
             {
               JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !...","CURRENT TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
             }
          }
        catch( SQLException sqlex )
          {
            sqlex.printStackTrace();
            JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
          }
     }
   private void getTable2(ResultSet rs)
     {
        Vector columnHeads = new Vector();
        Vector rows = new Vector();
        try
          {


            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 1;i <= rsmd.getColumnCount(); i++ )
            columnHeads.addElement( rsmd.getColumnName(i) );

            do
             {
               rows.addElement( getNextRow( rs,rsmd ) );
             }while( rs.next() );

            templibrary = new JTable(rows,columnHeads);
            templibrary.setBackground(Color.cyan);
            templibrary.setEnabled (false);
            p3.add(templibrary,BorderLayout.CENTER);
            JScrollPane sp2 = new JScrollPane(templibrary);
            p3.add(sp2,BorderLayout.CENTER);
            validate();

          }
        catch( SQLException sqlex )
          {
             sqlex.printStackTrace();
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
         }
     }
   private Vector getNextRow( ResultSet rs,ResultSetMetaData rsmd )
    throws SQLException
     {
       Vector currentRow = new Vector();

       for(int i=1;i <= rsmd.getColumnCount();i++ )
           switch( rsmd.getColumnType(i) )
            {
              case Types.VARCHAR: currentRow.addElement( rs.getString(i));
                                  break;
              default:            currentRow.addElement(rs.getDate(i));
            }
       return currentRow;
     }

   private class myadapter5 extends WindowAdapter
     {
       public void windowClosing(WindowEvent wt)
         {
           try
            {
              con.close();
            }
          catch( SQLException sqlex )
            {
              JOptionPane.showMessageDialog(null,"UNABLE TO DISCONNECT","Exception",JOptionPane.ERROR_MESSAGE);
              sqlex.printStackTrace();
            }
         }

     }
      public static void main(String a[])
       {
         MzStdRet rt=new MzStdRet( );
       }
  }


