package org.ulibsoft.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class LibraryShedule extends JFrame
  {
     private JButton up,exit,inst;
     private JTable plist;
     private JPanel pnl,insert;
     private JScrollPane sp;
     private Container c;
     private Connection con;
     private ResultSet rs;
     private Statement s;
     private LibraryShedule parent;

     private JLabel time,day,year,branch;
     private JComboBox year1,branch1;
     private JTextField time1,day1;
     private JButton up1,inst1,next;

     public LibraryShedule()
       {
          super("Library Shedule");
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
          show();

         try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
          createComponents();
          componentListener();

          try
            {
               s = con.createStatement ();
               rs = s.executeQuery ("select * from shedule");
                 if(rs.next ())
                   {
                      getTable(rs);
                   }
            }
          catch(SQLException e)
            {
               JOptionPane.showMessageDialog (null,e.getMessage ());
            }

          MyAdapter7 myap = new MyAdapter7();
          addWindowListener(myap);
       }

     private  void createComponents()
       {
          c = getContentPane();
          c.setLayout(new AbsoluteLayout());
          c.setBackground (new Color(0,0,40));

          pnl = new JPanel(new BorderLayout());
          pnl.setBackground(c.getBackground ());
          pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "TIME -- TABLE", 1, 2, new Font ("Monotype Corsiva", Font.ITALIC, 15), new Color(0,100,250) ));
          c.add(pnl,new AbsoluteConstraints(100,30,600,200));

          insert = new JPanel(new AbsoluteLayout());
          insert.setBackground(c.getBackground ());
          insert.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INSERT DETAILS", 1, 2, new Font ("Monotype Corsiva", Font.ITALIC, 15), new Color(0,100,250) ));
          c.add(insert,new AbsoluteConstraints(225,300,350,200));

          inst=new JButton("INSERT");
          inst.setMnemonic ('S');
          inst.setToolTipText ("For Inserting shedule");
          inst.setBackground ( Color.cyan );
          inst.setForeground( Color.magenta );
          inst.setBorder ( new BevelBorder ( 0 ));
          c.add ( inst, new AbsoluteConstraints( 217,255,120,27 ) );

          up=new JButton("UPDATE");
          up.setMnemonic ('U');
          up.setToolTipText ("For updating shedule");
          up.setBackground ( Color.cyan );
          up.setForeground( Color.magenta );
          up.setBorder ( new BevelBorder ( 0 ) );
          c.add ( up, new AbsoluteConstraints( 322+17,255,120,27 ) );

          exit=new JButton("EXIT");
          exit.setMnemonic ('X');
          exit.setToolTipText ("Close the Window");
          exit.setBackground ( Color.cyan );
          exit.setForeground( Color.magenta );
          exit.setBorder ( new BevelBorder (0));
          c.add ( exit, new AbsoluteConstraints( 444+17,255,120,27 ) );

          day=new JLabel("NAME - OF - DAY :");
          day.setForeground(new Color(120,120,153));
          day.setHorizontalAlignment (SwingConstants.RIGHT );
          insert.add (day,new AbsoluteConstraints(25+20,31));

          day1 = new JTextField( );
          day1.setFont( new Font( "Monotype Corsiva", Font.ITALIC, 18 ) );
          day1.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.black ) );
          day1.setForeground ( new Color ( 255, 0, 153 ) );
          day1.setCaretColor ( new Color ( 0, 204, 102 ) );
          insert.add ( day1, new AbsoluteConstraints ( 130+20, 20+10, 150, 20 ) );

          time=new JLabel("<==  TIMING  ==>");
          time.setForeground(new Color(120,120,153));
          time.setHorizontalAlignment (SwingConstants.RIGHT );
          insert.add (time,new AbsoluteConstraints(25+20,51+10));

          time1=new JTextField();
          time1.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.black ) );
          time1.setForeground ( new Color ( 255, 0, 153 ) );
          time1.setCaretColor ( new Color ( 0, 204, 102 ) );
          insert.add (time1,new AbsoluteConstraints(130+20,50+10,150,20));

          branch=new JLabel("<=   COURSE   =>");
          branch.setForeground(new Color(120,120,153));
          branch.setHorizontalAlignment ( SwingConstants.RIGHT );
          insert.add (branch,new AbsoluteConstraints(25+20,81+10));

          branch1=new JComboBox();
          branch1.addItem ("AE");
          branch1.addItem ("AEI");
          branch1.addItem ("ARC");
          branch1.addItem ("ACM");
          branch1.addItem ("BME");
          branch1.addItem ("BT");
          branch1.addItem ("CBE");
          branch1.addItem ("CEE");
          branch1.addItem ("CHE");
          branch1.addItem ("CIV");
          branch1.addItem ("CPE");
          branch1.addItem ("CSE");
          branch1.addItem ("CSS");
          branch1.addItem ("CSIT");
          branch1.addItem ("DT");
          branch1.addItem ("ECE");
          branch1.addItem ("ECM");
          branch1.addItem ("ECS");
          branch1.addItem ("ECSE");
          branch1.addItem ("ETM");
          branch1.addItem ("EEE");
          branch1.addItem ("EIE");
          branch1.addItem ("FPT");
          branch1.addItem ("FT");
          branch1.addItem ("ICE");
          branch1.addItem ("INE");
          branch1.addItem ("IPE");
          branch1.addItem ("IST");
          branch1.addItem ("MCT");
          branch1.addItem ("MEC");
          branch1.addItem ("MET");
          branch1.addItem ("MIN");
          branch1.addItem ("MMC");
          branch1.addItem ("MMD");
          branch1.addItem ("MMT");
          branch1.addItem ("NVA");
          branch1.addItem ("PHM");
          branch1.addItem ("PTG");
          branch1.addItem ("PLG");
          branch1.addItem ("SCE");
          branch1.addItem ("TEX");
          branch1.setBackground( new Color( 0, 0, 40 ));
          branch1.setForeground( new Color( 120, 120, 153 ));
          insert.add (branch1,new AbsoluteConstraints(130+20,80+10,150,20));

          year=new JLabel("<= SEMESTER =>");
          year.setForeground(new Color(120,120,153));
          year.setHorizontalAlignment (SwingConstants.RIGHT );
          insert.add (year,new AbsoluteConstraints(25+20,111+10));

          year1=new JComboBox();
          year1.addItem ( "I" );
          year1.addItem ( "II-1" );
          year1.addItem ( "II-2" );
          year1.addItem ( "III-1" );
          year1.addItem ( "III-2" );
          year1.addItem( "IV-1" );
          year1.addItem ( "IV-2" );
          year1.setBackground(new Color(0,0,40));
          year1.setForeground(new Color(120,120,153));
          insert.add (year1,new AbsoluteConstraints(130+20,110+10,150,20));

          inst1=new JButton("SUBMIT");
          inst1.setMnemonic ('B');
          inst1.setVisible(false);
          inst1.setToolTipText ("For Inserting shedule");
          inst1.setBackground ( Color.cyan );
          inst1.setForeground( Color.magenta );
          inst1.setBorder ( new BevelBorder ( 0 ));
          insert.add ( inst1, new AbsoluteConstraints( 52,155,120,27 ) );

          up1=new JButton("SUBMIT");
          up1.setMnemonic ('B');
          up1.setToolTipText ("For updating shedule");
          up1.setBackground ( Color.cyan );
          up1.setForeground( Color.magenta );
          up1.setVisible(false);
          up1.setBorder ( new BevelBorder ( 0 ) );
          insert.add ( up1, new AbsoluteConstraints( 52,155,120,27 ) );

          next = new JButton("RESET");
          next.setVisible(false);
          next.setBorder(new BevelBorder(0));
          next.setToolTipText("Reset all");
          next.setBackground(Color.cyan);
          next.setForeground(Color.magenta);//new Color(0,100,200));
          next.setMnemonic('R');
          insert.add(next,new AbsoluteConstraints(174,155,120,27));
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
                        //p.setFrame(Index.libsh );
                     }
                 }
            }
          );

          inst.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    up1.setVisible(false);
                    inst1.setVisible(true);
                    next.setVisible(true);
                 }
            }
          );
          up.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    next.setVisible(true);
                    inst1.setVisible(false);
                    up1.setVisible(true);
                 }
            }
          );
          inst1.addActionListener (new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    try
                      {
                         s=con.createStatement ();
                         int ins1=s.executeUpdate("INSERT INTO SHEDULE VALUES("+"'"+day1.getText ()+"',"
                                                                               +"'"+time1.getText ()+"',"
                                                                               +"'"+branch1.getSelectedItem()+"',"
                                                                               +"'"+year1.getSelectedItem()+"'"+")" );
                         con.commit ();

                         rs=s.executeQuery("select * from shedule");
                           if(rs.next ())
                             {
                                getTable(rs);
                             }
                        s.close ();
                      }
                    catch (SQLException se)
                      {
                         JOptionPane.showMessageDialog(null,se.getMessage(),"exception",JOptionPane.ERROR_MESSAGE);
                         se.printStackTrace();
                      }
                 }
            }
          );

          up1.addActionListener (new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    try
                      {
                         s=con.createStatement ();
                         int p= s.executeUpdate ("UPDATE SHEDULE SET TIME="+"'"+time1.getText ()+"'"+"WHERE BRANCH="+"'"+branch1.getSelectedItem ()+"'"+"AND YEAR="+"'"+year1.getSelectedItem ()+"'" );
                         int l= s.executeUpdate ("UPDATE SHEDULE SET DAY="+"'"+day1.getText ()+"'"+"WHERE BRANCH="+"'"+branch1.getSelectedItem()+"'"+"AND YEAR="+"'"+year1.getSelectedItem() +"'" );
                         con.commit ();

                         rs=s.executeQuery("select * from shedule");
                            if(rs.next ())
                              {
                                 getTable(rs);
                              }
                         s.close ();
                      }
                    catch (SQLException se)
                      {
                         JOptionPane.showMessageDialog(null,se.getMessage(),"exception",JOptionPane.ERROR_MESSAGE);
                         se.printStackTrace();
                      }
                 }
            }
          );

          next.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    day1.setText("");
                    time1.setText("");
                 }
            }
          );
          exit.addActionListener (new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    setVisible(false);
                 }
            }
          );
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
               setVisible(false);
            }
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
       LibraryShedule ls=new LibraryShedule();
     }

  }
