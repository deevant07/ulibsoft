package org.ulibsoft.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;


public class LibraryShedule1 extends JFrame
  {
        private JButton up,exit,inst;
     private JTable plist;

     private JPanel pnl,insert;

     private JScrollPane sp;
     private Container c;


     private LibraryShedule parent;
     private JButton but1,but2;
      private JLabel but0;
     private JLabel time,day,year,branch;
     private JComboBox year1,branch1;
     private JTextField time1,day1;
     private JButton up1,inst1,next;

     private Statement s;
      private Connection con;
      private ResultSet rs, rs1 ;

     public LibraryShedule1()
       {
           super("Student Record");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

         createComponents();
        try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}

         try
            {
               s = con.createStatement ();
               rs = s.executeQuery ("select * from shedule");
                 if(rs.next ())
                   {
                      getTable(rs);
                   }
                 else
                 {
                      JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ");
                 }
            }
          catch(SQLException e)
            {
               JOptionPane.showMessageDialog (null,e.getMessage ());
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



           but0 = new JLabel( "             LIBRARY  TIME  TABLE" ) ;
           but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
           but0.setBackground ( Color.pink );
           but0.setForeground(Color.black);
           c.add ( but0, new AbsoluteConstraints( 260+35, 40+35, 200, 30 ) );

           but1 = new JButton( "" ) ;
           but1.setBackground ( Color.cyan );
           but1.setForeground(Color.black);
           but1.setEnabled(false);
           but1.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but1, new AbsoluteConstraints( 260+30+5, 40+35, 200, 30 ) );

           but2 = new JButton( "" ) ;
           but2.setBackground ( Color.pink );
           but2.setForeground(Color.black);
           but2.setEnabled(false);
           but2.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but2, new AbsoluteConstraints( 267+30+5, 47+35, 200, 30 ) );

          pnl = new JPanel(new BorderLayout());
          pnl.setBackground(c.getBackground ());
          pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "TIME -- TABLE", 1, 2, c.getFont(), new Color(0,100,250) ));
          c.add(pnl,new AbsoluteConstraints(100,90+35,600,250));

          exit=new JButton("EXIT");
          exit.setMnemonic ('X');
          exit.setToolTipText ("Close the Window");
          exit.setBackground ( Color.cyan );
          exit.setForeground( Color.magenta );
          exit.setBorder ( new BevelBorder (0));
          c.add ( exit, new AbsoluteConstraints( 340,370+35,120,27 ) );
          setVisible(true);
        }


      private void componentListener()
        {
              exit.addActionListener (new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    setVisible(false);
                 }
            }
          );
        }

        private void getTable(ResultSet rs)
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
                plist.setBackground(Color.cyan);
                plist.setEnabled(false);
                pnl.add(plist,BorderLayout.CENTER);
                pnl.add(sp = new JScrollPane(plist),BorderLayout.CENTER);
                validate();
             }
           catch( SQLException sqlex )
             {
                JOptionPane.showMessageDialog(null,sqlex.getMessage());
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

           default:    currentRow.addElement(rs.getDate(i));
                               break;
         }
        return currentRow;
     }




   public static void main(String a[])
     {
       LibraryShedule1 ls=new LibraryShedule1();
     }


     }