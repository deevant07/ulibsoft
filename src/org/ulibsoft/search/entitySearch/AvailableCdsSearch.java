package org.ulibsoft.search.entitySearch;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;

import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class AvailableCdsSearch extends JFrame
  {
     private Container c;
     private JLabel but0;
     private JButton but1,but2,but3,but4,but5,but6,but7,but16,but17,but18;
     private JButton but8,but9,but10,but11,but12,but13,but14,but15,but19,but20;
     private JPanel cmppn,cmppn1,cmppn2;
     private JTable sttable;

     private JButton ins,next,quit,search,print;

     private JLabel  bookname,aut1s,aut1f,aut2s,aut2f,aut3s,aut3f;
     private JLabel  acess,pub,edt,isbn,note,recieve,phy,place,year;
     private JLabel  sub1,sub2,sub3;
     private JComboBox bookname1;
     private JComboBox aut1s1,aut1f1,aut2s1,aut2f1,aut3s1,aut3f1;
     private JTextField acess1,edt1,isbn1,year1,sub31;
     private JComboBox pub1,phy1,place1;
     private JComboBox sub11,sub21;
     private JTextField recieve1,note1;
     private JTextField title,supply,aut1,aut2,aut3,aut4,aut5,aut6,place2,pub2;

     private JComboBox box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,box11;
     private JComboBox box12,box13,box14,box15,box16,box17,box18,box19,box20;

     private String columnsParam=null;
     private int count=0;
     private String NOTE,REC,subject,status,ver,mzno,bkno,type,TITLE,number,sno;

     private String constraints="";
     private int item1=0,item2=0,item3=0,item4=0,item5=0,item6=0,item7=0,item8=0;
     private int item9=0,item10=0,item11=0,item12=0,item13=0,item14=0,item15=0,item16=0,item17=0;
     private int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,count9=0;
     private int count10=0,count11=0,count12=0,count13=0,count14=0,count15=0,count16=0,count17=0;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs,rs1 ;

     public AvailableCdsSearch()
       {
           super("INPUT CONSTRAINTS FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
           try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
           createComponents();

           componentListener();
       }

     private void createComponents()
       {
           c = getContentPane( ) ;
           c.setBackground( new Color(0,0,40) ) ;
           c.setLayout ( new AbsoluteLayout( ) ) ;

           cmppn = new JPanel( new AbsoluteLayout() );
           cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " CHOOSE  FIELDS  TO  VIEW", 2, 2, new Font( c.getFont().getFontName(),Font.PLAIN,9), Color.magenta));
           cmppn.setBackground(c.getBackground());
           c.add(cmppn,new AbsoluteConstraints(5,65,150,200));

           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(155,65,595+35,200));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,290,780,270));

           but0 = new JLabel( "    SEARCH  AVAILABLE  RECORDS" ) ;
           but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
           but0.setBackground ( Color.pink );
           but0.setForeground(Color.black);
           c.add ( but0, new AbsoluteConstraints( 260+30, 10, 200, 30 ) );

           but1 = new JButton( "" ) ;
           but1.setBackground ( Color.cyan );
           but1.setForeground(Color.black);
           but1.setEnabled(false);
           but1.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but1, new AbsoluteConstraints( 260+30+0, 10, 200, 30 ) );

           but2 = new JButton( "" ) ;
           but2.setBackground ( Color.pink );
           but2.setForeground(Color.black);
           but2.setEnabled(false);
           but2.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but2, new AbsoluteConstraints( 267+30+0, 17, 200, 30 ) );

           but3 = new JButton("ACCESSION  NUMBER");
           but3.setBackground(new Color(0,0,80));
           but3.setForeground(Color.cyan);
           but3.setBorder(new BevelBorder(0));
           but3.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20, 180-15-35, 23 ) );

           but4 = new JButton("CD / FLOPPY TITLE ");
           but4.setBackground(new Color(0,0,80));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           but4.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but4, new AbsoluteConstraints( 10, 44, 180-15-35, 23 ) );

           but6 = new JButton("CD  VERSION");
           but6.setBackground(new Color(0,0,80));
           but6.setForeground(Color.cyan);
           but6.setBorder(new BevelBorder(0));
           but6.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but6, new AbsoluteConstraints( 10, 68, 180-15-35, 23 ) );

           but7 = new JButton("CD / FLOPPY TYPE");
           but7.setBackground(new Color(0,0,80));
           but7.setForeground(Color.cyan);
           but7.setBorder(new BevelBorder(0));
           but7.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but7, new AbsoluteConstraints( 10, 92, 180-15-35, 23 ) );

           but8 = new JButton("BOOK ACCESSION NO");
           but8.setBackground(new Color(0,0,80));
           but8.setForeground(Color.cyan);
           but8.setBorder(new BevelBorder(0));
           but8.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but8, new AbsoluteConstraints( 10, 116, 180-15-35, 23 ) );

           but9 = new JButton("MAG_ACCESSION_NO");
           but9.setBackground(new Color(0,0,80));
           but9.setForeground(Color.cyan);
           but9.setBorder(new BevelBorder(0));
           but9.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but9, new AbsoluteConstraints( 10, 140, 180-15-35, 23 ) );

           but11 = new JButton("  SUBJECT ");
           but11.setBackground(new Color(0,0,80));
           but11.setForeground(Color.cyan);
           but11.setBorder(new BevelBorder(0));
           but11.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but11, new AbsoluteConstraints( 10, 164, 180-15-35, 23 ) );

           inputConstraints();
           setVisible(true);
       }

     private void inputConstraints()
       {
          bookname = new JLabel( "<== CD -- TITLE ==>" );
          bookname.setForeground ( new Color ( 120, 120, 153 ) );
          bookname.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          cmppn1.add ( bookname, new AbsoluteConstraints( 22, 48+20) );

          bookname1 = new JComboBox( );
          bookname1.setEditable(false);
          bookname1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          bookname1.setForeground ( new Color ( 255, 0, 153 ) );
          bookname1.setVisible(false);
          cmppn1.add ( bookname1, new AbsoluteConstraints( 145-20, 46+20, 445, 20 ) );

          title = new JTextField( );
          title.setEditable(true);
          title.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          title.setForeground ( new Color ( 255, 0, 153 ) );
          title.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( title, new AbsoluteConstraints( 145-20, 46+20, 445, 20 ) );

          aut1f = new JLabel( "CD / FLOPPY  TYPE >>" );
          aut1f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut1f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut1f, new AbsoluteConstraints( 310+5+5, 21+20 ) );

          aut1f1 = new JComboBox( );
          aut1f1.setEditable(false);
          aut1f1.addItem("");
          //aut1f1.setVisible(false);
            try
            {
               s=con.createStatement();
               rs=s.executeQuery("SELECT DISTINCT(CDTYPE) FROM CDDETAILS");
               while(rs.next())
               {
                aut1f1.addItem(rs.getObject(1));
               }
            }catch(SQLException sqlex)
            {
            }
          /* aut1f1.addItem("APPLICATION");
           aut1f1.addItem("SOFTWARE");
           aut1f1.addItem("GAME");
           aut1f1.addItem("UTILITIES");*/
           aut1f1.setBackground(Color.white);
          aut1f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0, 0, 40) ));
          aut1f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut1f1, new AbsoluteConstraints( 440-10+5, 18+20, 135,20) );

         /* aut2 = new JTextField( );
          aut2.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut2.setForeground ( new Color ( 255, 0, 153 ) );
          aut2.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut2, new AbsoluteConstraints( 440-10+5, 18+20, 135,20) );

           */
          acess = new JLabel( "<== ACCESS-NO ==>" );
          acess.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          acess.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( acess, new AbsoluteConstraints(15, 21+20) );

          acess1 = new JTextField( );
          acess1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          acess1.setForeground ( new Color ( 255, 0, 153 ) );
          acess1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( acess1, new AbsoluteConstraints( 145-20, 18+20, 150-15, 20 ) );

          aut1s = new JLabel( "<<  CD  VERSION  >>" );
          aut1s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut1s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut1s, new AbsoluteConstraints(15, 74+20 ) );

          aut1s1 = new JComboBox( );
          aut1s1.setEditable(false);
          aut1s1.setVisible(false);
          aut1s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
          aut1s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut1s1, new AbsoluteConstraints( 145-20, 72+20, 150-15, 20 ) );

          aut1 = new JTextField( );
          aut1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut1.setForeground ( new Color ( 255, 0, 153 ) );
          aut1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut1, new AbsoluteConstraints( 145-20, 72+20, 150-15, 20 ) );


          aut2s = new JLabel( "BOOK  ACCESS  NO " );
          aut2s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut2s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut2s, new AbsoluteConstraints(15,101+20) );

          aut2s1 = new JComboBox( );
          aut2s1.setEditable(false);
          aut2s1.setVisible(false);
          aut2s1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          aut2s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut2s1, new AbsoluteConstraints( 145-20, 99+20, 150-15, 20  ) );

          aut3 = new JTextField( );
          aut3.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut3.setForeground ( new Color ( 255, 0, 153 ) );
          aut3.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut3, new AbsoluteConstraints( 145-20, 99+20, 150-15, 20) );

          aut2f = new JLabel( "MAG_ACCESSION_NO" );
          aut2f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut2f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut2f, new AbsoluteConstraints(310+5+5, 101+20 ) );

          aut2f1 = new JComboBox( );
          aut2f1.setEditable(false);
          aut2f1.setVisible(false);
          aut2f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          aut2f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut2f1, new AbsoluteConstraints( 440-10+5, 99+20, 150-15, 20 ) );

          aut4 = new JTextField( );
          aut4.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut4.setForeground ( new Color ( 255, 0, 153 ) );
          aut4.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut4, new AbsoluteConstraints( 440-10+5, 99+20, 150-15, 20) );

          aut3f = new JLabel( "<<==    SUBJECT   ==>" );
          aut3f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut3f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut3f, new AbsoluteConstraints(310+5+5, 74+20 ) );

          aut3f1 = new JComboBox( );
          aut3f1.setEditable(false);
          aut3f1.setVisible(false);
          aut3f1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut3f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut3f1, new AbsoluteConstraints( 440-10+5, 72+20, 150-15, 20 ) );

          aut6 = new JTextField( );
          aut6.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut6.setForeground ( new Color ( 255, 0, 153 ) );
          aut6.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut6, new AbsoluteConstraints( 440-10+5, 72+20, 150-15, 20) );

          box1 = new JComboBox();
          box1.setFont(new Font(box1.getFont().getFontName(),Font.BOLD,12));
          box1.setForeground( new Color( 255, 0, 153 ));
          box1.setBackground(Color.white);
          box1.addItem("");
          box1.addItem("OR");
          box1.addItem("AND");
          cmppn1.add (box1,new AbsoluteConstraints( 265, 19+20, 45 , 18 ) );

          box2 = new JComboBox();
          box2.setForeground( new Color( 255, 0, 153 ));
          box2.setBackground(Color.white);
          box2.addItem("");
          box2.addItem("OR");
          box2.addItem("AND");
          cmppn1.add (box2,new AbsoluteConstraints( 578, 19+20, 45 , 18 ) );

          box3 = new JComboBox();
          box3.setForeground( new Color( 255, 0, 153 ));
          box3.setBackground(Color.white);
          box3.addItem("");
          box3.addItem("OR");
          box3.addItem("AND");
          cmppn1.add (box3,new AbsoluteConstraints( 578, 19+28+20, 45 , 18 ) );

          box4 = new JComboBox();
          box4.setForeground( new Color( 255, 0, 153 ));
          box4.setBackground(Color.white);
          box4.addItem("");
          box4.addItem("OR");
          box4.addItem("AND");
          cmppn1.add (box4,new AbsoluteConstraints( 265, 47+26+20, 45 , 18 ) );

          box5 = new JComboBox();
          box5.setForeground( new Color( 255, 0, 153 ));
          box5.setBackground(Color.white);
          box5.addItem("");
          box5.addItem("OR");
          box5.addItem("AND");
          cmppn1.add (box5,new AbsoluteConstraints( 578, 47+26+20, 45 , 18 ) );

          box6 = new JComboBox();
          box6.setForeground( new Color( 255, 0, 153 ));
          box6.setBackground(Color.white);
          box6.addItem("");
          box6.addItem("OR");
          box6.addItem("AND");
          cmppn1.add (box6,new AbsoluteConstraints( 265, 71+28+20, 45 , 18 ) );

          box7 = new JComboBox();
          box7.setForeground( new Color( 255, 0, 153 ));
          box7.setBackground(Color.white);
          box7.addItem("");
          box7.addItem("OR");
          box7.addItem("AND");
          cmppn1.add (box7,new AbsoluteConstraints( 578, 71+28+20, 45 , 18 ) );




          ins = new JButton( "LOST" ) ;
          ins.setBackground ( Color.cyan  );
          ins.setForeground( Color .black );
          ins.setMnemonic('L');
          ins.setBorder ( new BevelBorder ( 0 ));
          //cmppn1.add ( ins, new AbsoluteConstraints( 35+56, 320-70-30-10,110,27 ) );

          search = new JButton( "SEARCH" ) ;
          search.setBackground ( Color.cyan  );
          search.setForeground( Color .black );
          search.setMnemonic('S');
          //search.setEnabled(false);
          search.setBorder ( new BevelBorder ( 0 ));
          cmppn1.add ( search, new AbsoluteConstraints( 150, 150,110,27 ) );

          print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setVisible(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 150, 150,110,27 ) );

          next = new JButton( "NEXT>>>" ) ;
          next.setBackground ( Color.cyan );
          next.setForeground(  Color .black );
          next.setMnemonic('N');
          next.setBorder ( new BevelBorder (0));
          cmppn1.add ( next, new AbsoluteConstraints( 371-109,150,110,27 ) );

          quit = new JButton( "EXIT" ) ;
          quit.setBackground ( Color.cyan );
          quit.setMnemonic('X');
          quit.setForeground( Color .black );
          quit.setBorder ( new BevelBorder (0));
          cmppn1.add ( quit, new AbsoluteConstraints( 483-109,150,110,27 ) );

       }


      private void componentListener()
       {
         but3.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               if(columnsParam == null)
                 {
                      columnsParam = "CDCODE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"CDCODE";
                 }
            }
          }
          );
          but4.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "CDNAME";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"CDNAME";
                 }
            }
          }
          );

          but6.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "CDVERSION";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"CDVERSION";
                 }
            }
          }
          );
          but7.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "CDTYPE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"CDTYPE";
                 }
            }
          }
          );

          but8.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "BKANO";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"BKANO";
                 }
            }
          }
          );

          but9.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "MZACNO";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"MZACNO";
                 }
            }
          }
          );

         but11.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "SUBJECT";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"SUBJECT";
                 }
            }
          }
          );

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
                              bookname1.addItem(rs.getObject(1));
                              ++count;
                           }
                          if(count>0)
                          {
                             bookname1.setVisible(true);
                             title.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );

          box1.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count1 < 1 )
                    {
                      item1++;
                      if( acess1.getText()==null || acess1.getText().equals("") ) { }
                      else
                        {
                           number = acess1.getText().toUpperCase();
                           if( item1 > 0 )
                             {
                                constraints=constraints+" CDCODE= "+"'"+number+"'"+" "+(String)box1.getSelectedItem();
                             }
                        }
                      count1++;
                    }
                }
           }
         );

         box2.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count2 < 1 )
                    {
                      item2++;
                      if( aut1f1.getSelectedItem()==null || aut1f1.getSelectedItem().equals("") ) { }
                      else
                        {
                           type = ((String)aut1f1.getSelectedItem()).toUpperCase();
                           if( item2 > 0 )
                             {
                                constraints=constraints+" CDTYPE = "+"'"+type+"'"+" "+(String)box2.getSelectedItem();
                             }
                        }
                      count2++;
                    }
                }
           }
         );

         box3.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count3 < 1 )
                    {
                      item3++;
                      TITLE=(String)bookname1.getSelectedItem();
                      if(TITLE==null || TITLE .equals(""))
                        {
                           TITLE = title.getText().toUpperCase();
                           if( TITLE==null || TITLE.equals("") )
                             {
                             }
                           else
                             {
                               if( item3 > 0 )
                                 {
                                    constraints=constraints+" CDNAME = "+"'"+TITLE+"'"+" "+(String)box3.getSelectedItem();
                                 }
                             }
                        }
                       else
                        {
                           if( item3 > 0 )
                                 {
                                    constraints=constraints+" CDNAME = "+"'"+TITLE+"'"+" "+(String)box3.getSelectedItem();
                                 }
                        }

                      count3++;
                    }
                }
           }
         );

         box4.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count4 < 1 )
                    {
                      item4++;
                      if( aut1.getText()==null || aut1.getText().equals("") ) { }
                      else
                        {
                           ver = aut1.getText().toUpperCase();
                           if( item4 > 0 )
                             {
                                constraints=constraints+" CDVERSION = "+"'"+ver+"'"+" "+(String)box4.getSelectedItem();
                             }
                        }
                      count4++;
                    }
                }
           }
         );

         box5.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count5 < 1 )
                    {
                      item5++;
                      if( aut6.getText()==null || aut6.getText().equals("") ) { }
                      else
                        {
                           subject = aut6.getText().toUpperCase();
                           if( item5 > 0 )
                             {
                                constraints=constraints+" SUBJECT = "+"'"+subject+"'"+" "+(String)box5.getSelectedItem();
                             }
                        }
                      count5++;
                    }
                }
           }
         );

         box6.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count6 < 1 )
                    {
                      item6++;
                      if( aut3.getText()==null || aut3.getText().equals("") ) { }
                      else
                        {
                          bkno = aut3.getText().toUpperCase();
                          if( item6 > 0 )
                             {
                                constraints=constraints+" BKANO = "+"'"+bkno+"'"+" "+(String)box6.getSelectedItem();
                             }
                        }
                      count6++;
                    }
                }
           }
         );
         box7.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count7 < 1 )
                    {
                      item7++;
                      if( aut4.getText()==null || aut4.getText().equals("") ) { }
                      else
                        {
                           mzno = aut4.getText().toUpperCase();
                           if( item7 > 0 )
                             {
                                constraints=constraints+" MZACNO = "+"'"+mzno+"'"+" "+(String)box7.getSelectedItem();
                             }
                        }
                      count7++;
                    }
                }
           }
         );

       search.addActionListener(new ActionListener()
          {
              public void actionPerformed(ActionEvent e)
              {

                  if( acess1.getText()==null || acess1.getText().equals("") ) { }
                      else
                        {
                           number = acess1.getText().toUpperCase();
                           if( item1 > 0 ){}
                           else
                             {
                                constraints=constraints+" CDCODE= "+"'"+number+"'"+" ";
                             }
                        }

                  if( aut1f1.getSelectedItem()==null || aut1f1.getSelectedItem().equals("") ) { }
                      else
                        {
                           type = ((String)aut1f1.getSelectedItem()).toUpperCase();
                           if( item2 > 0 ) {}
                           else
                             {
                                constraints=constraints+" CDTYPE = "+"'"+type+"'"+" ";
                             }
                        }

                  TITLE=(String)bookname1.getSelectedItem();
                      if(TITLE==null || TITLE .equals(""))
                        {
                           TITLE = title.getText().toUpperCase();
                           if( TITLE==null || TITLE.equals("") )
                             {
                             }
                           else
                             {
                               if( item3 > 0 ) {}
                               else
                                 {
                                    constraints=constraints+" CDNAME = "+"'"+TITLE+"'"+" ";
                                 }
                             }
                        }
                       else
                        {
                           if( item3 > 0 ){}
                           else
                                 {
                                    constraints=constraints+" CDNAME = "+"'"+TITLE+"'"+" ";
                                 }
                        }

                  if( aut1.getText()==null || aut1.getText().equals("") ) { }
                      else
                        {
                           ver = aut1.getText().toUpperCase();
                           if( item4 > 0 ) {}
                           else
                             {
                                constraints=constraints+" CDVERSION = "+"'"+ver+"'"+" ";
                             }
                        }

                  if( aut6.getText()==null || aut6.getText().equals("") ) { }
                      else
                        {
                           subject = aut6.getText().toUpperCase();
                           if( item5 > 0 ) {}
                           else
                             {
                                constraints=constraints+" SUBJECT = "+"'"+subject+"'"+" ";
                             }
                        }

                  if( aut3.getText()==null || aut3.getText().equals("") ) { }
                      else
                        {
                          bkno = aut3.getText().toUpperCase();
                          if( item6 > 0 ) {}
                           else
                             {
                                constraints=constraints+" BKANO = "+"'"+bkno+"'"+" ";
                             }
                        }

                  if( aut4.getText()==null || aut4.getText().equals("") ) { }
                      else
                        {
                           mzno = aut4.getText().toUpperCase();
                           if( item7 > 0 ) {}
                           else
                             {
                                constraints=constraints+" MZACNO = "+"'"+mzno+"'"+" ";
                             }
                        }

                     try
                     {
                         if(columnsParam != null )
                         {          //JOptionPane.showMessageDialog(null,"78"+constraints);
                            s=con.createStatement();
                            if(constraints.equals(""))
                             {
                               rs=s.executeQuery("SELECT "+columnsParam+" FROM CDDETAILS ");
                               if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY  !  .  .  .","AVAILABLE  CDS  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                            else
                             {
                                rs=s.executeQuery("SELECT "+columnsParam+" FROM CDDETAILS WHERE "+constraints);
                                if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  .","AVAILABLE  CDS  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                             s.close();
                         }
                         else
                         {

                            s=con.createStatement();
                            if(constraints.equals(""))
                             {  //JOptionPane.showMessageDialog(null,constraints+"jgjg");
                               rs=s.executeQuery("SELECT CDCODE, CDNAME, CDVERSION, CDTYPE, SUBJECT, BKANO, MZACNO  FROM  CDDETAILS ");
                               if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  .","AVAILABLE  CDS  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                                   //JOptionPane.showMessageDialog(null,constraints+"jmhjmhgjg");
                             }
                            else
                             {
                                JOptionPane.showMessageDialog(null,constraints);
                                rs=s.executeQuery("SELECT CDCODE, CDNAME, CDVERSION, CDTYPE, SUBJECT, BKANO, MZACNO FROM CDDETAILS WHERE "+constraints);
                                if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  .","CDS DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                             s.close();
                         }
                        search.setVisible(false);
                                    print.setVisible(true);
                     }
                     catch( SQLException sqlex)
                                {
                                  JOptionPane.showMessageDialog(null,sqlex.getMessage());
                                  sqlex.printStackTrace();
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
                       if(sttable!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= sttable.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("AVAILABLE CD'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("AVAILABLE CD'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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

          next.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               print.setVisible(false);
               search.setVisible(true);

               acess1.setText("");
              // edt1.setText("");
               title.setText("");
               aut1.setText("");
               //aut2.setText("");
               aut3.setText("");
               aut4.setText("");
               aut6.setText("");
               //aut3s1.setSelectedItem("");
              // recieve1.setText("");
              // note1.setText("");
               aut1f1.setSelectedItem("");

               bookname1.setVisible(false);
               title.setVisible(true);

                columnsParam=null;
                constraints="";

                box1.setSelectedItem("");
                //JOptionPane.showMessageDialog(null,"bkbk");
                box2.setSelectedItem("");
                box3.setSelectedItem("");
                box4.setSelectedItem("");
                box5.setSelectedItem("");
                box6.setSelectedItem("");
                box7.setSelectedItem("");

                item1=0;
                item2=0;
                item3=0;
                item4=0;
                item5=0;
                item6=0;
                item7=0;
                count1=0;count2=0;
                count3=0;count4=0;
                count5=0;count6=0;
                count7=0;

            }
          }
          );
          quit.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               setVisible(false);
            }
          }
          );

       }
     private void getTable(ResultSet rs )
      {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();

         //OLD CODE FOR PRINTING HERE ,...FOR NEW CODE WE CAN PRINT WITHOUT WRITING INTO FILE fp ,
         //Here I have taken fp as a BufferStorage to write into html file from there we can print its an easy process to print
         try
           {
             ResultSetMetaData rsmd = rs.getMetaData();
             for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                columnHeads.addElement( rsmd.getColumnName(i) );

             do
              {
                rows.addElement( getNextRow( rs,rsmd ) );
              }while( rs.next() );

             sttable = new JTable(rows,columnHeads);
             sttable.setBackground(Color.pink);
             sttable.setEnabled (false);
             cmppn2.add(sttable,BorderLayout.CENTER);
             JScrollPane spane =new JScrollPane(sttable);
             cmppn2.add(spane,BorderLayout.CENTER);
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
          new AvailableCdsSearch();
       }


  }