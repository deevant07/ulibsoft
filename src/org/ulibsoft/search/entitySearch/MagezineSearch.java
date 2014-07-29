package org.ulibsoft.search.entitySearch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class MagezineSearch extends JFrame
  {
     private JLabel mn,pub,code;
     private JButton std,stf,next ,quit,print1,print2;
     private JTable nstudents1,nstudents;
     private JPanel pl,pnl,cmppn;
     private Container c;
     private Choice b1,a1;
     private JTextField code1;
     private JScrollPane sp;

     private ResultSet rs,rs1;
     private Statement s;
     private Connection con;

     public MagezineSearch()
       {
          super("SEARCHING FOR  MAGAZINES");
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
          show();

        try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
          createComponents();
          componentListener();
          MyAdapter7 myap = new MyAdapter7();
          addWindowListener(myap);
       }

     private void createComponents()
       {

          c = getContentPane();
          c.setLayout(new AbsoluteLayout());
          c.setBackground(new Color(0, 0, 40));

          cmppn = new JPanel(new AbsoluteLayout());
          cmppn.setBackground(c.getBackground());
          cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "SEARCH-BY-TITLE & VOLUME", 1, 2,c.getFont() , Color.magenta));
          c.add(cmppn,new AbsoluteConstraints(245,30,310,200));

          mn = new JLabel ();
          mn.setText ( "MAGEZINE  TITLE" );
          mn.setForeground (new Color ( 120,120, 153 ) );
          cmppn.add(mn,new AbsoluteConstraints(35,30));

          b1 = new Choice ();
          b1.addItem("");
          b1.setForeground (new Color ( 255, 0, 153 ) );
            try
              {
                s = con.createStatement ();
                rs1 = s.executeQuery ( " SELECT DISTINCT( MZ.MZNAME ) FROM MZDETAILS MZ, MZTRANSACTION1 MZT1 WHERE MZ.ACCESSNO=MZT1.MZCODE" );

                while( rs1.next ())
                     {
                        b1.addItem ( rs1.getString (1) );
                     }
                rs = s.executeQuery ( " SELECT DISTINCT(MZ.MZNAME) FROM MZDETAILS MZ,MZTRANSACTION MZT1 WHERE MZ.ACCESSNO=MZT1.MZCODE AND MZ.MZNAME  NOT IN(SELECT MZ.MZNAME FROM MZDETAILS MZ,MZTRANSACTION1 MZ1 WHERE MZ1.MZCODE=MZ.ACCESSNO )" );
                   while( rs.next ())
                     {
                        b1.addItem ( rs.getString (1) );
                    }
                s.close();
                con.commit();
              }
            catch(SQLException sqe)
              {
                JOptionPane.showMessageDialog (null,sqe.getMessage (),"ERROR",JOptionPane.ERROR_MESSAGE );
                sqe.printStackTrace ();
              }
          cmppn.add (b1,new AbsoluteConstraints( 140,25,150,20 ));

          pub = new JLabel ();
          pub.setText ( "MAGEZINE VOLUME" );
          pub.setHorizontalAlignment ( SwingConstants.CENTER );
          pub.setForeground (new Color ( 120,120, 153 ) );
          cmppn.add ( pub ,new AbsoluteConstraints(20,60));

          a1 = new Choice ();
          a1.setForeground ( new Color ( 255, 0, 153 ) );
          a1.addItem("");
          cmppn.add ( a1 ,new AbsoluteConstraints(140,55,150,20));

           code = new JLabel ();
          code.setText ( "CODE" );
          code.setForeground (new Color ( 120,120, 153 ) );
          cmppn.add(code,new AbsoluteConstraints(80,90));

          code1 = new JTextField();
          code1.setForeground ( new Color ( 255, 0, 153 ) );
          code1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));
          cmppn.add ( code1 ,new AbsoluteConstraints(140,87,150,20));

          std = new JButton ( "STUDENT" ) ;
          std.setBackground ( Color.cyan );
          std.setForeground ( Color.black );
          std.setMnemonic('S');
          std.setEnabled(false);
          std.setBorder ( new BevelBorder(0) );
          cmppn.add ( std,new AbsoluteConstraints(34 ,155,120,27));

          stf = new JButton ( "STAFF" ) ;
          stf.setBackground ( Color.cyan );
          stf.setForeground ( Color.black );
          stf.setMnemonic('A');
          stf.setEnabled(false);
          stf.setBorder ( new BevelBorder(0) );
          cmppn.add ( stf,new AbsoluteConstraints(161 ,155,120,27));

          next = new JButton( "NEXT>>>" ) ;
          next.setBackground ( Color.cyan );
          next.setForeground ( Color.black );
          next.setBorder ( new BevelBorder(0) );
          next.setMnemonic('N');
          cmppn.add ( next, new AbsoluteConstraints( 55,120,100,27 ) );

          print1 = new JButton( "PRINT" ) ;
          print1.setBackground (Color.cyan);
          print1.setForeground(Color.black);
          print1.setBorder(new BevelBorder(0));
          print1.setMnemonic('P');
          print1.setVisible(false);
          cmppn.add ( print1,new AbsoluteConstraints(34 ,155,120,27));

          print2 = new JButton( "PRINT" ) ;
          print2.setBackground (Color.cyan);
          print2.setForeground(Color.black);
          print2.setBorder(new BevelBorder(0));
          print2.setMnemonic('P');
          print2.setVisible(false);
          cmppn.add ( print2,new AbsoluteConstraints(161 ,155,120,27));

          quit = new JButton( "QUIT" ) ;
          quit.setBackground(Color.cyan );
          quit.setForeground ( Color.black );
          quit.setBorder ( new BevelBorder(0) );
          quit.setMnemonic('Q');
          cmppn.add ( quit, new AbsoluteConstraints( 160,120,100,27 ) );

          c.add( pl=new JPanel(new BorderLayout()),new AbsoluteConstraints(100,240,600,300));

          pl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "TABLE-DISPLAY", 1, 2, c.getFont(),Color.magenta));
          pl.setBackground(c.getBackground());

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
                    PopUpMenu p=new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                    //p.setFrame(Index.mzsh);
                  }
               }
           }
         );

         b1.addItemListener ( new ItemListener()
          {
            public void itemStateChanged( ItemEvent e )
              {
                try
                  {
                     a1.removeAll ();
                     s = con.createStatement ();

                      a1.addItem("");

                     rs1 = s.executeQuery ( "SELECT DISTINCT (MZ.VOLUME) FROM MZDETAILS MZ ,MZTRANSACTION1 MZT1 WHERE MZ.ACCESSNO=MZT1.MZCODE AND MZ.MZNAME="+"'"+b1.getSelectedItem ()+"'");
                       while( rs1.next () )
                        {
                           a1.addItem ( rs1.getString (1) );
                        }
                     rs=s.executeQuery(" SELECT DISTINCT(MZ.VOLUME) FROM MZDETAILS MZ,MZTRANSACTION MZT1 WHERE MZ.ACCESSNO=MZT1.MZCODE AND MZ.MZNAME="+"'"+b1.getSelectedItem()+"'"+"AND MZ.VOLUME NOT IN(SELECT MZ.VOLUME FROM MZDETAILS MZ,MZTRANSACTION1 MZT1  WHERE MZ.ACCESSNO=MZT1.MZCODE AND MZ.MZNAME="+"'"+b1.getSelectedItem()+"'"+"  )");
                       while( rs.next () )
                         {
                            a1.addItem ( rs.getString (1) );
                         }
                    con.commit();
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

         a1.addItemListener(new ItemListener()
           {
             public void itemStateChanged(ItemEvent e)
               {
                 try
                   {
                     s = con.createStatement();
                     rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.YEAR,TO_CHAR(MZT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT.RDATE,'DD-MON-YYYY') RDATE FROM  STUDENTDETAILS STD,MZTRANSACTION MZT WHERE STD.ADNO=MZT.ADNO AND MZT.MZCODE IN(SELECT MZ.ACCESSNO FROM MZDETAILS MZ WHERE MZ.MZNAME ="+"'"+b1.getSelectedItem ()+"'"+"AND MZ.VOLUME ="+"'"+a1.getSelectedItem ()+"')" );
                       if( rs.next () )
                        {
                          std.setEnabled(true);
                        }
                      else
                       {
                           std.setEnabled(false);
                       }

                     rs = s.executeQuery ("SELECT STF.LID,STF.LNAME,STF.DEPT,TO_CHAR(MZT1.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT1.RDATE,'DD-MON-YYYY') RDATE FROM STAFF STF,MZTRANSACTION1 MZT1 WHERE STF.LID=MZT1.LID AND MZT1.MZCODE IN(SELECT MZ.ACCESSNO FROM MZDETAILS MZ WHERE MZ.MZNAME="+"'"+b1.getSelectedItem ()+"'"+" AND MZ.VOLUME="+"'"+a1.getSelectedItem ()+"')" );

                            if( rs.next() )
                            {
                                stf.setEnabled(true);
                            }
                            else {stf.setEnabled(false);  }


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
         print1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(nstudents!=null){
                       java.util.Date date=new java.util.Date();
                       boolean success= nstudents.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("BOOK-HOLDER SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("BOOK-HOLDER SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
                       if(nstudents!=null){
                       java.util.Date date=new java.util.Date();
                       boolean success= nstudents.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("BOOK-HOLDER SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("BOOK-HOLDER SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
           code1.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
             {
                 try
                 {
                     s=con.createStatement();
                     rs=s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.YEAR,TO_CHAR(MZT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT.RDATE,'DD-MON-YYYY') RDATE FROM STUDENTDETAILS STD,MZTRANSACTION MZT WHERE STD.ADNO=MZT.ADNO AND MZT.MZCODE="+"'"+code1.getText()+"'");
                     if( rs.next () )
                           {
                               getTable1(rs);
                             // std.setEnabled(true);
                           }
                         else
                           {
                                // std.setEnabled(false);
                           }
                      rs = s.executeQuery ("SELECT STF.LID,STF.LNAME,STF.DEPT,TO_CHAR(MZT1.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT1.RDATE,'DD-MON-YYYY') RDATE FROM STAFF STF ,MZTRANSACTION1 MZT1 WHERE STF.LID=MZT1.LID AND MZT1.MZCODE="+"'"+code1.getText()+"'");
                         if( rs.next() )
                           {
                               getTable2(rs);
                             // stf.setEnabled(true);
                           }
                         else
                           {
                             // stf.setEnabled(false);
                           }
                      s.close();

                 }catch(SQLException sqlex)
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
                     s = con.createStatement();
                     //rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.YEAR,MZT.MZCODE,TO_CHAR(MZT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT.RDATE,'DD-MON-YYYY') RDATE FROM  STUDENTDETAILS STD ,MZTRANSACTION MZT WHERE MZT.MZCODE IN(SELECT MZ.ACCESSNO FROM MZDETAILS MZ WHERE MZ.MZNAME ="+"'"+b1.getSelectedItem ()+"'"+"AND MZ.VOLUME ="+"'"+a1.getSelectedItem ()+"')" );
                     rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.YEAR,MZT.MZCODE,TO_CHAR(MZT.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT.RDATE,'DD-MON-YYYY') RDATE FROM  STUDENTDETAILS STD ,MZTRANSACTION MZT,MZDETAILS MZ WHERE STD.ADNO=MZT.ADNO AND MZT.MZCODE = MZ.ACCESSNO AND MZ.MZNAME ="+"'"+b1.getSelectedItem ()+"'"+"AND MZ.VOLUME ="+"'"+a1.getSelectedItem ()+"'" );
                       if( rs.next () )
                        {
                          getTable1(rs );
                        }
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
                     s = con.createStatement();
                    // rs = s.executeQuery ("SELECT STF.LID,STF.LNAME,STF.DEPT,MZT1.MZCODE,TO_CHAR(MZT1.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT1.RDATE,'DD-MON-YYYY') RDATE FROM STAFF STF ,MZTRANSACTION1 MZT1 WHERE STF.LID=MZT1.LID AND MZT1.MZCODE IN(SELECT MZ.ACCESSNO FROM MZDETAILS MZ WHERE MZ.MZNAME="+"'"+b1.getSelectedItem ()+"'"+" AND MZ.VOLUME="+"'"+a1.getSelectedItem ()+"')" );
                     rs = s.executeQuery ("SELECT STF.LID,STF.LNAME,STF.DEPT,MZT1.MZCODE,TO_CHAR(MZT1.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(MZT1.RDATE,'DD-MON-YYYY') RDATE FROM STAFF STF ,MZTRANSACTION1 MZT1,MZDETAILS MZ WHERE STF.LID=MZT1.LID AND MZT1.MZCODE = MZ.ACCESSNO AND MZ.MZNAME="+"'"+b1.getSelectedItem ()+"'"+" AND MZ.VOLUME="+"'"+a1.getSelectedItem ()+"'" );
                     if( rs.next() )
                     {
                         getTable2(rs);
                     }
                     else
                     {
                         JOptionPane.showMessageDialog (null,"NO RECORDS TO DISPLAY","STAFF DATABASE",JOptionPane.INFORMATION_MESSAGE);
                     }
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



         next.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                   //MagezineSearch b =new MagezineSearch();
                    //b1.removeAll();
                    a1.removeAll();
                    code1.setText("");
                    print1.setVisible(false);
                    print2.setVisible(false);
                    std.setVisible(true);
                    stf.setVisible(true);
                    std.setEnabled(false);
                    stf.setEnabled(false);
                  // setVisible(false);
                }
            }
        );

        quit.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
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

             nstudents = new JTable(rows,columnHeads);
             nstudents.setBackground(Color.pink);
             nstudents.setEnabled (false);
             pl.add(nstudents,BorderLayout.CENTER);
             JScrollPane spane =new JScrollPane(nstudents);
             pl.add(spane,BorderLayout.CENTER);
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

             nstudents1 = new JTable(rows,columnHeads);
             nstudents1.setBackground(Color.pink);
             nstudents1.setEnabled (false);
             pl.add(nstudents1,BorderLayout.CENTER);
             JScrollPane spane =new JScrollPane(nstudents1);
             pl.add(spane,BorderLayout.CENTER);
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

     private class MyAdapter7 extends WindowAdapter
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
      public static void main(String s[])
      {
       MagezineSearch b=new MagezineSearch();
       }
  }

