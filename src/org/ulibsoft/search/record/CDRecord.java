package org.ulibsoft.search.record;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;

import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class CDRecord extends JFrame
  {
      private JLabel code;
      private int count=0;

      private JTextField cd;
      private JButton std,stf,find;
      private JTable bookdetails,bookdetails1;
      private JPanel rtpn,p1;
      private JButton but1,but2,print1,print2;
      private JLabel but0;
      private Container c;
      private JButton next,can;

      private Statement s;
      private Connection con;
      private ResultSet rs, rs1 ;


     public CDRecord()
       {
           super("CD/FLOPPY  RECORD FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

         createComponents();
        try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
         componentListener();
       }

      private void createComponents()
      {
         c = getContentPane( ) ;
         c.setBackground( new Color(0,0,40) ) ;
         c.setLayout ( new AbsoluteLayout( ) ) ;

         c = getContentPane();
         c.setLayout( new AbsoluteLayout() );
         c.setBackground( new Color( 0, 0, 40 ) );

         rtpn = new JPanel( new AbsoluteLayout() );
         rtpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "BOOK DETAILS", 1, 2, c.getFont(), Color.magenta));
         rtpn.setBackground(c.getBackground());
         c.add(rtpn,new AbsoluteConstraints(228,70+20,342,135));

         p1 = new JPanel( new BorderLayout() );
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROJECTION  OF  DATABASE", 2, 2,c.getFont(), Color.magenta));
         c.add(p1,new AbsoluteConstraints(90,215+30,600,300));

         but0 = new JLabel( "         SEARCH  CD  RECORD" ) ;
         but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
         but0.setBackground ( Color.pink );
         but0.setForeground(Color.black);
         c.add ( but0, new AbsoluteConstraints( 260+35, 40, 200, 30 ) );

         but1 = new JButton( "" ) ;
         but1.setBackground ( Color.cyan );
         but1.setForeground(Color.black);
         but1.setEnabled(false);
         but1.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
         c.add ( but1, new AbsoluteConstraints( 260+30+5, 40, 200, 30 ) );

         but2 = new JButton( "" ) ;
         but2.setBackground ( Color.pink );
         but2.setForeground(Color.black);
         but2.setEnabled(false);
         but2.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
         c.add ( but2, new AbsoluteConstraints( 267+30+5, 47, 200, 30 ) );

         code = new JLabel ( "ACCESS  NUMBER :" );
         code.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( code, new AbsoluteConstraints (40,30 ) );

         cd = new JTextField( );
         cd.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
         cd.setForeground ( new Color ( 255, 0, 153 ) );
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( cd, new AbsoluteConstraints ( 155, 28, 150, 20 ) );

         find = new JButton( "FIND" ) ;
         find.setBackground ( Color.cyan );
         find.setForeground( Color.black );
         find.setBorder( new BevelBorder( 0 ));
         rtpn.add ( find, new AbsoluteConstraints( 20,60,100,22 ) );

            print1 = new JButton( "PRINT" ) ;
            print1.setBackground (Color.cyan);
            print1.setForeground(Color.black);
            print1.setVisible(false);
            print1.setBorder(new BevelBorder(0));
            print1.setMnemonic('P');

            print2 = new JButton( "PRINT" ) ;
            print2.setBackground (Color.cyan);
            print2.setForeground(Color.black);
            print2.setVisible(false);
            print2.setBorder(new BevelBorder(0));
            print2.setMnemonic('P');

           rtpn.add ( print1, new AbsoluteConstraints( 60+10,90,100,22 ) );
           rtpn.add ( print2, new AbsoluteConstraints( 162+10,90,100,22  ) );

         std = new JButton( "STUDENT" ) ;
         std.setEnabled(false);
         std.setBackground ( Color.cyan );
         std.setForeground( Color.black );
         std.setBorder( new BevelBorder( 0 ));
         rtpn.add ( std, new AbsoluteConstraints( 60+10,90,100,22 ) );

         stf = new JButton( "STAFF" ) ;
         stf.setEnabled(false);
         stf.setBackground ( Color.cyan );
         stf.setForeground( Color.black );
         stf.setBorder( new BevelBorder( 0 ));
         rtpn.add ( stf, new AbsoluteConstraints( 162+10,90,100,22  ) );

         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan);
         next.setForeground(Color.black);
         next.setMnemonic('N');
         next.setBorder(new BevelBorder(0));
         rtpn.add ( next, new AbsoluteConstraints( 122, 60, 100, 22 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         rtpn.add ( can, new AbsoluteConstraints( 224, 60, 100, 22 ) );

         setVisible(true);
        }

     private void componentListener()
     {
         cd.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 try
                    {
                      s = con.createStatement();
                       rs = s.executeQuery("SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM  STUDENTDETAILS STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND BD.CDCODE IN( SELECT CD.CDCODE FROM CDDETAILS CD WHERE CD.CDCODE ="+"'"+cd.getText()+"')" );
                         if( rs.next () )
                           {
                              std.setEnabled(true);
                              count++;
                           }
                         else
                           {
                              std.setEnabled(false);
                           }
                        rs = s.executeQuery ("SELECT STF.LID, STF.LNAME, STF.DEPT, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM STAFF STF,BOOKDETAILS BD WHERE STF.LID=BD.LID AND BD.CDCODE IN(SELECT CD.CDCODE FROM CDDETAILS CD WHERE CD.CDCODE="+"'"+cd.getText()+"')" );
                         if( rs.next() )
                           {
                              stf.setEnabled(true);
                              count++;
                           }
                         else
                           {
                              stf.setEnabled(false);
                           }
                          if(count<1)
                          {
                            JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .");
                          }
                      s.close();
                    }
                  catch(SQLException sqlex)
                    {
                       JOptionPane.showMessageDialog(null,sqlex.getMessage (),"exception",JOptionPane.ERROR_MESSAGE);
                       sqlex.printStackTrace();
                    }
             }
         }
         );
         find.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 try
                    {
                      s = con.createStatement();
                     rs = s.executeQuery("SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM  STUDENTDETAILS STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND BD.CDCODE IN( SELECT CD.CDCODE FROM CDDETAILS CD WHERE CD.CDCODE ="+"'"+cd.getText()+"')" );
                         if( rs.next () )
                           {
                              std.setEnabled(true);
                              count++;
                           }
                         else
                           {
                              std.setEnabled(false);
                           }
                        rs = s.executeQuery ("SELECT STF.LID, STF.LNAME, STF.DEPT, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM STAFF STF,BOOKDETAILS BD WHERE STF.LID=BD.LID AND BD.CDCODE IN(SELECT CD.CDCODE FROM CDDETAILS CD WHERE CD.CDCODE="+"'"+cd.getText()+"')" );
                         if( rs.next() )
                           {
                              stf.setEnabled(true);
                              count++;
                           }
                         else
                           {
                              stf.setEnabled(false);
                           }
                          if(count<1)
                          {
                            JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .");
                          }
                      s.close();
                    }
                  catch(SQLException sqlex)
                    {
                       JOptionPane.showMessageDialog(null,sqlex.getMessage (),"exception",JOptionPane.ERROR_MESSAGE);
                       sqlex.printStackTrace();
                    }
             }
         }
         );
         std.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
               try
               {
                   s=con.createStatement();
                   rs = s.executeQuery( " SELECT STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE  FROM BOOKDETAILS BD,STUDENTDETAILS STD WHERE BD.CDCODE ="+"'"+cd.getText()+"'" +"AND BD.VALUE='2'"+"AND BD.ADNO=STD.ADNO");
                   while(rs.next())
                   {
                       getTable1(rs);
                   }
                   //find.setVisible(false);
                   print1.setVisible(true);
                   std.setVisible(false);
                   s.close();
               }
               catch(SQLException sqlex)
                    {
                       JOptionPane.showMessageDialog(null,sqlex.getMessage (),"exception",JOptionPane.ERROR_MESSAGE);
                       sqlex.printStackTrace();
                    }
             }
         }
         );
        stf.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                try
               {
                 s=con.createStatement();
                   rs = s.executeQuery ("SELECT STF.LID, STF.LNAME, STF.DEPT, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE  FROM BOOKDETAILS BD,STAFF STF WHERE BD.CDCODE="+"'"+cd.getText()+"'"+"AND BD.VALUE='2'"+"AND BD.LID=STF.LID");
                   while(rs.next())
                   {
                       getTable2(rs);
                   }
                  // find.setVisible(false);
                   print2.setVisible(true);
                   stf.setVisible(false);
                 s.close();
               }
               catch(SQLException sqlex)
                    {
                       JOptionPane.showMessageDialog(null,sqlex.getMessage (),"exception",JOptionPane.ERROR_MESSAGE);
                       sqlex.printStackTrace();
                    }
             }
         }
         );

          next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                 cd.setText("");
                 count=0;
                 print1.setVisible(false);
                 print2.setVisible(false);
                 std.setVisible(true);
                 stf.setVisible(true);
                 std.setEnabled(false);
                 stf.setEnabled(false);
              }
          }
        );

         print1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(bookdetails!=null){
                       java.util.Date date=new java.util.Date();
                       boolean success= bookdetails.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("CD-RECORD'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("CD-RECORD'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
                       if(bookdetails1!=null){
                       java.util.Date date=new java.util.Date();
                       boolean success= bookdetails.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("CD-RECORD'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("CD-RECORD'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
           can.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
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
                setVisible(false);
               }
          }
        );
     }

     private void getTable1(ResultSet rs )
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

             bookdetails = new JTable(rows,columnHeads);
             bookdetails.setBackground(Color.pink);
             bookdetails.setEnabled (false);
             p1.add(bookdetails,BorderLayout.CENTER);
             JScrollPane spane =new JScrollPane(bookdetails);
             p1.add(spane,BorderLayout.CENTER);
             validate();
           }
         catch( SQLException sqlex )
           {
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
             sqlex.printStackTrace();
           }
      }

      private void getTable2(ResultSet rs )
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

             bookdetails1 = new JTable(rows,columnHeads);
             bookdetails1.setBackground(Color.pink);
             bookdetails1.setEnabled (false);
             p1.add(bookdetails1,BorderLayout.CENTER);
             JScrollPane spane =new JScrollPane(bookdetails1);
             p1.add(spane,BorderLayout.CENTER);
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


     public static void main( String a[] )
       {
          CDRecord bk = new CDRecord();
       }
  }
