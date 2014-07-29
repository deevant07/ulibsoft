package org.ulibsoft.search.defaulters;

import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.awt.*;

import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;

import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class CdPendingList extends JFrame
  {
     private JLabel date,branch,year;
     private JButton pl,g1,g2,g3,bar,can,next;
     private JButton print1,print2,print3,print4;
     private JTextField df;
     private JComboBox bh,yr ;
     private JTable plist;
     private JPanel pnl,bkpn;
     private JScrollPane sp,sp5;
     private Container c;

     private ResultSet rs;
     private Statement s;
     private Connection con;
     private static int count;

     public CdPendingList()
       {
         super( "CD/FLOPPY/CASETTA PENDINGLIST" );
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         show();

         createComponents();
         try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
         componentListener();
         Myadapter7 myap = new Myadapter7();
         addWindowListener(myap);
       }

     private void createComponents()
       {
         c = getContentPane();
         c.setLayout(new AbsoluteLayout());
         c.setBackground( new Color(0,0,40));

         bkpn = new JPanel(new AbsoluteLayout());
         bkpn.setBackground(c.getBackground());
         bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "FIND", 1, 2, c.getFont(), Color.magenta));
         c.add(bkpn,new AbsoluteConstraints(237,50,314,180));

         date = new JLabel( "RECIEVE-DATE" );
         date.setForeground ( new Color ( 120,120, 153 ) );
         bkpn.add(date,new AbsoluteConstraints(30,30));

         df = new JTextField( );
         df.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));
         df.setForeground (new Color ( 120, 120, 153 ) );
         df.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add(df,new AbsoluteConstraints(125,25,150,22));

         branch = new JLabel("COURSE");
         branch.setForeground (new Color ( 120,120, 153 ) );
         bkpn.add( branch, new AbsoluteConstraints( 63, 60 ));

         bh = new JComboBox();
         bh.addItem ("CSE");
         bh.addItem ("CSIT");
         bh.addItem ("ECE");
         bh.addItem ("EEE");
         bh.addItem ("MEC");
         bh.addItem ("AE");
         bh.addItem ("AEI");
         bh.addItem ("ARC");
         bh.addItem ("ACM");
         bh.addItem ("BME");
         bh.addItem ("BT");
         bh.addItem ("CBE");
         bh.addItem ("CEE");
         bh.addItem ("CHE");
         bh.addItem ("CIV");
         bh.addItem ("CPE");
         bh.addItem ("CSS");
         bh.addItem ("DT");
         bh.addItem ("ECM");
         bh.addItem ("ECS");
         bh.addItem ("ECSE");
         bh.addItem ("ETM");
         bh.addItem ("EIE");
         bh.addItem ("FPT");
         bh.addItem ("FT");
         bh.addItem ("ICE");
         bh.addItem ("INE");
         bh.addItem ("IPE");
         bh.addItem ("IST");
         bh.addItem ("MCT");
         bh.addItem ("MET");
         bh.addItem ("MIN");
         bh.addItem ("MMC");
         bh.addItem ("MMD");
         bh.addItem ("MMT");
         bh.addItem ("NVA");
         bh.addItem ("PHM");
         bh.addItem ("PTG");
         bh.addItem ("PLG");
         bh.addItem ("SCE");
         bh.addItem ("TEX");
         bh.setBackground(c.getBackground());
         bh.setForeground(new Color( 120, 120, 153));
         bh.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
         bkpn.add (bh,new AbsoluteConstraints(125,55,60,22));

         g1 = new JButton("GO >>>");
         g1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         g1.setBackground ( Color.cyan );
         g1.setForeground ( Color.magenta );
         g1.setMnemonic('G');
         g1.setEnabled (false);
         g1.setBorder(new BevelBorder(0));
         bkpn.add(g1,new AbsoluteConstraints(190,55,85,22));

         year = new JLabel("SEMISTER");
         year.setForeground (new Color ( 120,120, 153 ) );
         bkpn.add(year,new AbsoluteConstraints(53,88));

         yr = new JComboBox();
         yr.addItem ("I");
         yr.addItem ("II-1");
         yr.addItem ("II-2");
         yr.addItem ("III-1");
         yr.addItem ("III-2");
         yr.addItem ("IV-1");
         yr.addItem ("IV-2");
         yr.setBackground(c.getBackground());
         yr.setForeground(new Color(120, 120, 153));
         yr.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
         bkpn.add (yr,new AbsoluteConstraints(125,85,60,22));

         g2 = new JButton("GO >>>");
         g2.setBorder (new BevelBorder ( 0 ));
         g2.setBackground ( Color.cyan );
         g2.setForeground ( Color.magenta );
         g2.setMnemonic('O');
         g2.setEnabled (false);
         bkpn.add(g2,new AbsoluteConstraints(190,85,85,22));

         pl = new JButton( "PLIST" );
         pl.setBorder ( new BevelBorder (0));
         pl.setBackground ( Color.cyan );
         pl.setForeground ( Color.magenta );
         pl.setMnemonic('P');
         bkpn.add(pl,new AbsoluteConstraints(13,120,95,22));

         bar = new JButton("BR && YR");
         bar.setBackground (Color.cyan );
         bar.setForeground (Color.magenta );
         bar.setMnemonic('Y');
         bar.setEnabled (false);
         bar.setBorder (new BevelBorder (0));
         bkpn.add(bar,new AbsoluteConstraints(101+8,120,95,22));

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.magenta );
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         bkpn.add(can,new AbsoluteConstraints(185+20,120,95,22));

         print1 = new JButton("PRINT");
         print1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print1.setBackground ( Color.cyan );
         print1.setForeground ( Color.magenta );
         print1.setMnemonic('P');
         print1.setVisible (false);
         print1.setBorder(new BevelBorder(0));
         bkpn.add(print1,new AbsoluteConstraints(190,55,85,22));

         print2 = new JButton("PRINT");
         print2.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print2.setBackground ( Color.cyan );
         print2.setForeground ( Color.magenta );
         print2.setMnemonic('P');
         print2.setVisible (false);
         print2.setBorder(new BevelBorder(0));
         bkpn.add(print2,new AbsoluteConstraints(190,85,85,22));

         print3 = new JButton("PRINT");
         print3.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print3.setBackground ( Color.cyan );
         print3.setForeground ( Color.magenta );
         print3.setMnemonic('P');
         print3.setVisible (false);
         print3.setBorder(new BevelBorder(0));
         bkpn.add(print3,new AbsoluteConstraints(13,120,95,22));

         print4 = new JButton("PRINT");
         print4.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print4.setBackground ( Color.cyan );
         print4.setForeground ( Color.magenta );
         print4.setMnemonic('P');
         print4.setVisible (false);
         print4.setBorder(new BevelBorder(0));
         bkpn.add(print4,new AbsoluteConstraints(101+8,120,95,22));

         next = new JButton("CLEAR");
         next.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         next.setBackground ( Color.cyan );
         next.setForeground ( Color.magenta );
         next.setMnemonic('C');
         next.setBorder(new BevelBorder(0));
         bkpn.add(next,new AbsoluteConstraints(101+8-40,150,95+80,22));

         pnl = new JPanel(new BorderLayout());
         pnl.setBackground(c.getBackground ());
         pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                             "PENDING-LIST", 1, 2, c.getFont(), Color.magenta));
         c.add(pnl,new AbsoluteConstraints(100,250,600,275));

         setVisible(true);
      }


   private void componentListener()
     {
       addMouseListener( new MouseAdapter()
         {
           public void mouseClicked(MouseEvent e)
             {
               if (e.isMetaDown())
                {
                  PopUpMenu p = new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                  //p.setFrame(Index.cdpl);
                }
             }
         }
       );
        next.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
              {
                 print1.setVisible(false);
                 print2.setVisible(false);
                 print3.setVisible(false);
                 print4.setVisible(false);

                 g1.setVisible(true);
                 g2.setVisible(true);
                 bar.setVisible(true);
                 pl.setVisible(true);

                 g1.setEnabled(false);
                 g2.setEnabled(false);
                 bar.setEnabled(false);
                // pl.setEnabled(false);
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

       print1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );
        print2.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH BY BRANCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH BY NRANCH->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );
          print3.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH BY YEAR->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH BY YEAR->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );
          print4.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH BY BRANCH&YEAR->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT CD-DEFAULTERS LIST SEARCH BY BRANCH&YEAR->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );
       df.addKeyListener (new KeyListener()
         {
           public void keyPressed(KeyEvent ke){}
           public void keyReleased(KeyEvent k){}

           public void keyTyped(KeyEvent e)
             {
               if(df.getText ().length ()>=0)
                 {
                    pl.setEnabled (false);
                 }
               if(df.getText ().length ()==0)
                 {
                   pl.setEnabled (true);
                 }
               g1.setEnabled (false);
               g2.setEnabled (false);
               bar.setEnabled (false);
             }
         }
       );

       bh.addItemListener (new ItemListener()
         {
           public void itemStateChanged(ItemEvent e)
             {
               g1.setEnabled (true);
               count++;
               if( count>2 )
               bar.setEnabled (true);
             }
        }
      );

      yr.addItemListener (new ItemListener()
        {
          public void itemStateChanged(ItemEvent e)
            {
              g2.setEnabled (true);
              count++;
              if(count>2)
              bar.setEnabled (true);
            }
        }
      );

      df.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
            {
              try
                {
                   s = con.createStatement ();
                   rs = s.executeQuery("SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR,TO_CHAR(CDT.RDATE,'DD-MON-YYYY') RDATE FROM CDTRANSACTION CDT ,STUDENTDETAILS STD WHERE CDT.RDATE="+"'"+df.getText ()+"'"+"AND STD.ADNO=CDT.ADNO");
                   if(rs.next())
                    {
                      getTable(rs);
                    }
                   else
                   {
                       JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . .","CURRENT TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                   }
                   s.close ();
                }
              catch(SQLException sqe)
                {
                   JOptionPane.showMessageDialog (null,sqe.getMessage (),"ERROR",JOptionPane.ERROR_MESSAGE );
                   sqe.printStackTrace ();
                }
            }
        }
      );

      pl.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
            {
              try
                {
                   s=con.createStatement ();
                   rs=s.executeQuery("SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR,TO_CHAR(CDT.RDATE,'DD-MON-YYYY') RDATE FROM CDTRANSACTION CDT,STUDENTDETAILS STD WHERE CDT.ADNO=STD.ADNO AND CDT.RDATE < ( SELECT SYSDATE FROM DUAL)");
                   if(rs.next())
                    {
                       getTable(rs);
                       pl.setVisible(false);
                       print3.setVisible(true);
                    }
                   else
                    {
                       JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY" );
                    }
                   s.close ();
                }
              catch(SQLException sqe)
                {
                   JOptionPane.showMessageDialog (null,sqe.getMessage (),"ERROR",JOptionPane.ERROR_MESSAGE );
                   sqe.printStackTrace ();
                }
            }
        }
      );

      g1.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
            {
              try
                {
                  s=con.createStatement ();
                  rs=s.executeQuery ("SELECT  STD.ADNO, STD.SNAME, STD.YEAR,TO_CHAR(CDT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(CDT.RDATE,'DD-MON-YYYY') RDATE FROM CDTRANSACTION CDT,STUDENTDETAILS STD WHERE STD.BRANCH="+"'"+bh.getSelectedItem ()+"'"+"AND CDT.RDATE<(SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL) AND CDT.ADNO=STD.ADNO");
                  if(rs.next ())
                   {
                     getTable(rs);
                     g1.setVisible(false);
                     print1.setVisible(true);
                   }
                  else
                   {
                     JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY" );
                   }
                  s.close();
                }
              catch(SQLException e)
                {
                  JOptionPane.showMessageDialog( null,e.getMessage() );
                }
            }

        }
      );

      g2.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
            {
              try
                {
                  s=con.createStatement ();
                  rs=s.executeQuery ("SELECT  STD.ADNO, STD.SNAME, STD.BRANCH,TO_CHAR(CDT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(CDT.RDATE,'DD-MON-YYYY') RDATE FROM CDTRANSACTION CDT,STUDENTDETAILS STD WHERE STD.YEAR="+"'"+yr.getSelectedItem()+"'"+"AND CDT.RDATE<(SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL) AND CDT.ADNO=STD.ADNO");
                    if(rs.next ())
                     {
                       getTable(rs);
                       g2.setVisible(false);
                       print2.setVisible(true);
                     }
                    else
                     {
                       JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY" );
                     }
                  s.close();
                }
              catch(SQLException e)
                {
                  JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        }
      );

      bar.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
            {
              try
                {
                  s=con.createStatement ();
                  rs=s.executeQuery ("SELECT  STD.ADNO, STD.SNAME, TO_CHAR(CDT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(CDT.RDATE,'DD-MON-YYYY') RDATE FROM CDTRANSACTION CDT,STUDENTDETAILS STD WHERE STD.BRANCH="+"'"+bh.getSelectedItem ()+"'" +" AND STD.YEAR="+ "'"+yr.getSelectedItem ()+"'"+"AND CDT.RDATE<(SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL) AND STD.ADNO=CDT.ADNO");
                    if(rs.next ())
                     {
                       getTable(rs);
                       bar.setVisible(false);
                       print4.setVisible(true);
                     }

                    else
                     {
                       JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY" );
                     }
                  s.close();
                }
              catch(SQLException e)
                {
                   JOptionPane.showMessageDialog(null,e.getMessage());
                }
            }
        }
      );
    }
  private class Myadapter7 extends WindowAdapter
    {
      public void windowClosing(WindowEvent wt)
        {
          try
            {
              con.close();
              setVisible(false);
            }
          catch( SQLException sqlex )
            {
              JOptionPane.showMessageDialog(null,"Unable to Disconnect","Exception",JOptionPane.ERROR_MESSAGE);
              sqlex.printStackTrace();
            }
        }
    }

  private void getTable(ResultSet rs )
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

             plist = new JTable(rows,columnHeads);
             plist.setBackground(Color.pink);
             plist.setEnabled (false);
             pnl.add(plist,BorderLayout.CENTER);
             JScrollPane spane =new JScrollPane(plist);
             pnl.add(spane,BorderLayout.CENTER);
             validate();
           }
         catch( SQLException sqlex )
           {
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
             sqlex.printStackTrace();
           }
      }
     private Vector getNextRow( ResultSet rs,ResultSetMetaData rsmd )
     throws SQLException
      {
         Vector currentRow = new Vector();

         for(int i=1;i <= rsmd.getColumnCount();i++ )
         {

             switch( rsmd.getColumnType(i) )
              {
                 case Types.VARCHAR : currentRow.addElement( rs.getString(i) );
                                      break;

                 default:             currentRow.addElement( rs.getString(i) );
              }
           }

         return currentRow;
      }
     public static void main(String a[])
       {
         CdPendingList pl = new CdPendingList();
       }
 }