package org.ulibsoft.search.defaulters;

import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class StaffDefaulterList extends JFrame
  {
     private JButton but1,but2,next,can,print;
     private JLabel but0;

     private JLabel lid;
     private JButton g2;
     private JComboBox cd;
     private JTable plist;
     private JPanel pnl,bkpn;
     private JScrollPane sp,sp5;
     private Container c;

     private ResultSet rs;
     private Statement s;
     private Connection con;
     private static int count,stage=0;

     public StaffDefaulterList()
       {
           super("STAFF  DEFAULTERS LIST OF  BOOKS");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

           createComponents();
          try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}

           try
             {
                 s=con.createStatement();
                 cd.removeAllItems();
                 rs=s.executeQuery("SELECT BK1.LID FROM BKTRANSACTION1 BK1,KEYCONSTRAINTS KEY WHERE BK1.RDATE < (SELECT KEY.TO_HOLD FROM KEYCONSTRAINTS KEY WHERE ID=2) AND KEY.TO_HOLD<SYSDATE");
                 while(rs.next())
                   {
                      cd.addItem(rs.getString(1));
                   }
                 }catch(SQLException sqlex)
                 {
                    sqlex.printStackTrace();
                    JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
                 }
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

            but0 = new JLabel( "        STAFF  DEFAULTERS  LIST" ) ;
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

            bkpn = new JPanel(new AbsoluteLayout());
            bkpn.setBackground(c.getBackground());
            bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CHOOSE", 1, 2, c.getFont(), Color.magenta));
            c.add(bkpn,new AbsoluteConstraints(237-11,95+15,338,110));

            lid = new JLabel( "PROFESSOR  ID" );
            lid.setForeground ( new Color ( 120,120, 153 ) );
            bkpn.add(lid,new AbsoluteConstraints(30+15,30));

            cd = new JComboBox( );
            cd.setBackground(new Color(120,120,153));
            cd.addItem("PROFESSOR IDS");
            cd.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));
            cd.setForeground (new Color ( 120, 120, 153 ) );
            bkpn.add(cd,new AbsoluteConstraints(125+15,25,150,22));

            g2 = new JButton("FIND  ");
            g2.setBorder (new BevelBorder ( 0 ));
            g2.setBackground ( Color.cyan );
            g2.setEnabled(false);
            g2.setForeground ( Color.magenta );
            g2.setMnemonic('O');
           // g2.setEnabled (false);
            bkpn.add(g2,new AbsoluteConstraints(20-2,60,100,27));

            pnl = new JPanel(new BorderLayout());
            pnl.setBackground(c.getBackground ());
            pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                              "DEFAULTERS-LIST", 1, 2, c.getFont(), Color.magenta));
            c.add(pnl,new AbsoluteConstraints(100,250,600,275));

            next = new JButton( "NEXT>>>" ) ;
            next.setBackground ( Color.cyan);
            next.setForeground(Color.magenta);
            next.setMnemonic('N');
            next.setBorder(new BevelBorder(0));
            bkpn.add ( next, new AbsoluteConstraints( 120, 60, 100, 27 ) );

            can = new JButton( "EXIT" ) ;
            can.setBackground ( Color.cyan);
            can.setForeground( Color.magenta);
            can.setMnemonic('X');
            can.setBorder(new BevelBorder(0));
            bkpn.add ( can, new AbsoluteConstraints( 222, 60, 100, 27 ) );

            print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
           // print.setEnabled(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            bkpn.add(print,new AbsoluteConstraints(20-2,60,100,27));

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
                  //p.setFrame(Index.pl);
                }
             }
         }
       );

       print.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STAFF BOOK-DEFAULTERS LIST SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STAFF BOOK-DEFAULTERS LIST SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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

       /*cd.addItemListener(new ItemListener()
         {
            public void itemStateChanged(ItemEvent e)
              {
               // if(stage<1)
               //   {
                     g2.setEnabled(true);
                     JOptionPane.showMessageDialog(null,"jkhjk");
                  //   stage=1;
                 // }
              }
         }
       );*/

       cd.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
              {
               // if(stage<1)
               //   {
                     g2.setEnabled(true);
                     print.setVisible(false);
                     g2.setVisible(true);
                    // JOptionPane.showMessageDialog(null,"jkhjk");
                  //   stage=1;
                 // }
              }
         }
       );

       g2.addActionListener (new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
                try
                {
                   s=con.createStatement();
                   rs=s.executeQuery("SELECT LIB.ACESSNO,LIB.BOOKNAME,LIB.AUTHOR1S||LIB.AUTHOR1F AUTHOR,TO_CHAR(BK1.IDATE,'DD-MON-YYYY'),TO_CHAR(BK1.RDATE,'DD-MON-YYYY') FROM LIBRARY LIB,BKTRANSACTION1 BK1 WHERE LIB.ACESSNO IN(SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.LID="+"'"+cd.getSelectedItem()+"'"+") AND BK1.CODE IN(SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.LID='"+cd.getSelectedItem()+"')");
                   while(rs.next())
                   {
                     getTable(rs);
                   }
                   g2.setVisible(false);
                   print.setVisible(true);
                }
                catch(SQLException sqlex)
                {
                   sqlex.printStackTrace();
                   JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
                }

             }
        }
      );

          next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                // cd.removeAllItems();
              }
          }
        );


           can.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                //JOptionPane.showMessageDialog(null,"afg");
                try
              {
                con.close();
              }
            catch( SQLException sqlex )
              {
                JOptionPane.showMessageDialog(null,"UNABLE TO DISCONNECT","Exception",JOptionPane.ERROR_MESSAGE);
                sqlex.printStackTrace();
              }
               // JOptionPane.showMessageDialog(null,"afg");
                setVisible(false);
               }
          }
        );
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
         StaffDefaulterList pl = new StaffDefaulterList();
       }
 }
