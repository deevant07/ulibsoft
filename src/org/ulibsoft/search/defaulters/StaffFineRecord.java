package org.ulibsoft.search.defaulters;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class StaffFineRecord extends JFrame
  {

     private JLabel lid;
     private JTextField no;
     private JButton book,cd,mz,find,next,can;
     private JPanel ltpn,p2;
     private JTable lttable;
     private JButton but1,but2,print;
     private JLabel but0;
     private Container c;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs, rs1,rs2 ;

     private int count=0,count1=0;

     public StaffFineRecord()
       {
           super("Staff Record");
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

         ltpn = new JPanel( new AbsoluteLayout() );
         ltpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "LECTURER DETAILS", 1, 2,c.getFont(), Color.magenta));
         ltpn.setBackground(c.getBackground());
         c.add(ltpn,new AbsoluteConstraints(180,90,420,135));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROJECTION  OF  DATABASE", 2, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(90,215+30,600,300));


           but0 = new JLabel( "         SEARCH  STAFF  RECORD" ) ;
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


         //INITIALIZE STAFF DETAILS


         lid = new JLabel ( "LECTURER  ID . ." );
         lid.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add( lid, new AbsoluteConstraints( 30,30 ) );

         no = new JTextField ( );
         no.setEditable(false);
         //no.setText(Gates.cname.getText().toUpperCase());
         no.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add (no,new AbsoluteConstraints( 130, 28, 150, 20 ) );

         find = new JButton( "FIND" ) ;
         find.setBackground ( Color.cyan );
         find.setForeground( Color.black );
         find.setBorder( new BevelBorder( 0 ));
         ltpn.add ( find, new AbsoluteConstraints( 285,28,100,22) );

         print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setEnabled(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
         // ltpn.add ( print, new AbsoluteConstraints( 285,28,100,22) );


         book = new JButton( "BOOK" ) ;
         book.setEnabled(false);
         book.setBackground ( Color.cyan );
         book.setForeground( Color.black );
         book.setBorder( new BevelBorder( 0 ));
         ltpn.add ( book, new AbsoluteConstraints( 60,60,100,25 ) );

         cd = new JButton( "CD/FLOPPY" ) ;
         cd.setEnabled(false);
         cd.setBackground ( Color.cyan );
         cd.setForeground( Color.black );
         cd.setBorder( new BevelBorder( 0 ));
         ltpn.add ( cd, new AbsoluteConstraints( 162,60,100,25  ) );

         mz = new JButton( "MAGAZINE" ) ;
         mz.setBackground ( Color.cyan );
         mz.setEnabled(false);
         mz.setForeground( Color.black );
         mz.setBorder( new BevelBorder( 0 ));
         ltpn.add ( mz, new AbsoluteConstraints(  264,60,100,25 ) );

         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan);
         next.setForeground(Color.black);
         next.setMnemonic('N');
         next.setBorder(new BevelBorder(0));
         ltpn.add ( next, new AbsoluteConstraints( 110, 90, 100, 25 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         ltpn.add ( can, new AbsoluteConstraints( 212, 90, 100, 25 ) );

         setVisible(true);

     }

     private  void componentListener()
     {
         find.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                 try
                  {
                    s = con.createStatement();
                    rs = s.executeQuery("SELECT LIB.ACESSNO,LIB.BOOKNAME, LIB.AUTHOR1S||LIB.AUTHOR1F, BD.IDATE, BD.RDATE FROM BOOKDETAILS BD,LIBRARY LIB WHERE BD.LID ="+"'"+ no.getText().toUpperCase()+"'"+"AND BD.CODE=LIB.ACESSNO AND  BD.RDATE > (SELECT KEY.TO_HOLD FROM KEYCONSTRAINTS KEY WHERE ID=2)" );
                       while(rs.next() )
                         {
                            count1++;
                            book.setEnabled(true);
                         }

                    rs1 = s.executeQuery("SELECT CD.CDCODE,CD.CDNAME,CD.CDVERSION,BD.IDATE,BD.RDATE FROM BOOKDETAILS BD,CDDETAILS CD WHERE BD.LID ="+"'"+ no.getText().toUpperCase()+"'"+"AND CD.CDCODE=BD.CDCODE AND  BD.RDATE > (SELECT KEY.TO_HOLD FROM KEYCONSTRAINTS KEY WHERE ID=2)" );
                        while( rs1.next() )
                          {
                             count1++;
                             cd.setEnabled(true);
                          }

                    rs2 = s.executeQuery("SELECT MD.ACCESSNO,MD.MZNAME,MD.VOLUME,BD.IDATE,BD.RDATE FROM BOOKDETAILS BD,MZDETAILS MD WHERE BD.LID ="+"'"+ no.getText().toUpperCase()+"'"+"AND MD.ACCESSNO=BD.MZCODE  AND  BD.RDATE > (SELECT KEY.TO_HOLD FROM KEYCONSTRAINTS KEY WHERE ID=2)" );
                        while( rs2.next() )
                          {
                             count1++;
                             mz.setEnabled(true);
                          }
                    if( count1 == 0 )
                      {
                         JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  .");
                      }
                    else
                      {
                      //   find.setVisible(false);
                    //     print.setVisible(true);
                    //     print.setEnabled(false);
                      }
                    s.close();
                  }
                catch(SQLException sqe)
                  {
                    JOptionPane.showMessageDialog (null,sqe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE );
                    sqe.printStackTrace();
                  }
               }
           }
           );
           book.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                   try
                   {
                    s=con.createStatement();
                    rs=s.executeQuery ("SELECT  LIB.ACESSNO ACESSNO,LIB.BOOKNAME BOOKNAME, LIB.AUTHOR1S||LIB.AUTHOR1F AUTHOR,TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM BOOKDETAILS BD,LIBRARY LIB WHERE BD.LID="+"'"+no.getText().toUpperCase()+"'"+"AND VALUE='1'"+"AND BD.CODE=LIB.ACESSNO" );
                    while(rs.next())
                    {
                        getTable(rs);
                    }
                  //  print.setEnabled(true);
                    s.close();
                   }catch(SQLException sqe)
                  {
                    JOptionPane.showMessageDialog (null,sqe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE );
                    sqe.printStackTrace();
                  }

               }
           }
           );
           cd.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                   try
                   {
                    s=con.createStatement();
                    rs=s.executeQuery ("SELECT CD.CDNAME, CD.CDVERSION, CD.CDCODE,TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM BOOKDETAILS BD,CDDETAILS CD WHERE BD.LID="+"'"+no.getText().toUpperCase()+"'"+"AND BD.VALUE='2'"+"AND CD.CDCODE=BD.CDCODE" );
                    while(rs.next())
                    {
                        getTable(rs);
                    }
                   // print.setEnabled(true);
                    s.close();
                   }catch(SQLException sqe)
                  {
                    JOptionPane.showMessageDialog (null,sqe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE );
                    sqe.printStackTrace();
                  }
               }
           }
           );
           mz.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
                  try
                   {
                    s=con.createStatement();
                     rs=s.executeQuery ("SELECT MZ.MZNAME, MZ.VOLUME, MZ.ACCESSNO,TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE , TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM BOOKDETAILS BD,MZDETAILS MZ WHERE BD.LID="+"'"+no.getText().toUpperCase()+"'"+"AND BD.VALUE='3'"+"AND MZ.ACCESSNO=BD.MZCODE" );
                    while(rs.next())
                    {
                        getTable(rs);
                    }
                   // print.setEnabled(true);
                    s.close();
                   }catch(SQLException sqe)
                  {
                    JOptionPane.showMessageDialog (null,sqe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE );
                    sqe.printStackTrace();
                  }
               }
           }
           );

            next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                 no.setText("");
                 print.setVisible(false);
                 find.setVisible(true);
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

      private void getTable( ResultSet rs )
       {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                {
                columnHeads.addElement( rsmd.getColumnName(i) );

                }

                do
                 {
                   rows.addElement( getNextRow( rs,rsmd ) );
                 }while( rs.next() );

                lttable = new JTable(rows,columnHeads);
                lttable.setBackground(Color.pink);
                lttable.setEnabled (false);
                p2.add(lttable,BorderLayout.CENTER);
                JScrollPane spane =new JScrollPane(lttable);
                p2.add(spane,BorderLayout.CENTER);
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
                 case Types.VARCHAR : String tp=rs.getString(i);
                                      currentRow.addElement( tp );
                                      break;

                 default:             String tp1=rs.getString(i);
                                      currentRow.addElement( tp1 );
              }
           }
         return currentRow;
      }

     public static void main( String a[] )
       {
          new StaffFineRecord();
       }
  }
