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

public class AvailableBooksSearch extends JFrame
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
     private JTextField acess1,edt1,isbn1,year1,sub31,sub22;
     private JComboBox pub1,phy1,place1;
     private JComboBox sub11,sub21;
     private JTextField recieve1,note1;
     private JTextField title,supply,aut1,aut2,aut3,aut4,aut5,aut6,place2,pub2;

     private JComboBox box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,box11;
     private JComboBox box12,box13,box14,box15,box16,box17,box18,box19,box20;

     private String columnsParam=null;
     private int stage1=0,stage2=0,stage3=0;
     private int stage4=0,stage5=0,stage6=0;
     private int count = 0,STAGE=0;
     private int flag1=0,flag2=0,flag3=0;
     private String TITLE="",SUPPLY="",AUT1="",AUT2="",AUT3="",AUT4="",AUT5="",AUT6="";
     private String number,PUB="",ISBN,edition,PLACE="",YEAR,PHY,SUB1,SUB2,SUB3,REC,NOTE;

     private String constraints="";
     private int item1=0,item2=0,item3=0,item4=0,item5=0,item6=0,item7=0,item8=0;
     private int item9=0,item10=0,item11=0,item12=0,item13=0,item14=0,item15=0,item16=0,item17=0;
     private int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,count9=0;
     private int count10=0,count11=0,count12=0,count13=0,count14=0,count15=0,count16=0,count17=0;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs,rs1 ;


     public AvailableBooksSearch()
       {
          super("INPUT CONSTRAINTS FORM");
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

           cmppn = new JPanel( new AbsoluteLayout() );
           cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " CHOOSE  FIELDS  TO  VIEW", 2, 2, new Font( c.getFont().getFontName(),Font.PLAIN,9), Color.magenta));
           cmppn.setBackground(c.getBackground());
           c.add(cmppn,new AbsoluteConstraints(5,65,150,280));

           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(155,65,595+35,280));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,410-60,780,220));

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
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20-5+10, 180-15-35, 18 ) );

           but4 = new JButton("TITLE / EDITION");
           but4.setBackground(new Color(0,0,80));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           but4.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but4, new AbsoluteConstraints( 10, 38-5+10, 180-15-35, 18 ) );

           but5 = new JButton("FIRST  AUTHOR");
           but5.setBackground(new Color(0,0,80));
           but5.setBorder(new BevelBorder(0));
           but5.setForeground(Color.cyan);
           but5.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but5, new AbsoluteConstraints( 10, 57-5+10, 180-15-35, 18 ) );

           but6 = new JButton("SECOND  AUTHOR");
           but6.setBackground(new Color(0,0,80));
           but6.setForeground(Color.cyan);
           but6.setBorder(new BevelBorder(0));
           but6.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but6, new AbsoluteConstraints( 10, 76-5+10, 180-15-35, 18 ) );

           but7 = new JButton("THIRD  AUTHOR");
           but7.setBackground(new Color(0,0,80));
           but7.setForeground(Color.cyan);
           but7.setBorder(new BevelBorder(0));
           but7.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but7, new AbsoluteConstraints( 10, 95-5+10, 180-15-35, 18 ) );

           but8 = new JButton("BOOK  PUBLISHER");
           but8.setBackground(new Color(0,0,80));
           but8.setForeground(Color.cyan);
           but8.setBorder(new BevelBorder(0));
           but8.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but8, new AbsoluteConstraints( 10, 114-5+10, 180-15-35, 18 ) );

           but9 = new JButton("PUBLISHED  PLACE");
           but9.setBackground(new Color(0,0,80));
           but9.setForeground(Color.cyan);
           but9.setBorder(new BevelBorder(0));
           but9.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but9, new AbsoluteConstraints( 10, 133-5+10, 180-15-35, 18 ) );

           but14 = new JButton("DEPARTMENT");
           but14.setBackground(new Color(0,0,80));
           but14.setForeground(Color.cyan);
           but14.setBorder(new BevelBorder(0));
           but14.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but14, new AbsoluteConstraints( 10, 152-5+10, 180-15-35, 18 ) );

           but15 = new JButton("COURSE");
           but15.setBackground(new Color(0,0,80));
           but15.setForeground(Color.cyan);
           but15.setBorder(new BevelBorder(0));
           but15.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but15, new AbsoluteConstraints( 10, 171-5+10, 180-15-35, 18 ) );

           but16 = new JButton("SPECIFICATION");
           but16.setBackground(new Color(0,0,80));
           but16.setForeground(Color.cyan);
           but16.setBorder(new BevelBorder(0));
           but16.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but16, new AbsoluteConstraints( 10, 190-5+10, 180-15-35, 18 ) );

           but17 = new JButton("DESCRIPTION");
           but17.setBackground(new Color(0,0,80));
           but17.setForeground(Color.cyan);
           but17.setBorder(new BevelBorder(0));
           but17.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but17, new AbsoluteConstraints( 10, 209-5+10, 180-15-35, 18 ) );

           but18 = new JButton("NOTE");
           but18.setBackground(new Color(0,0,80));
           but18.setForeground(Color.cyan);
           but18.setBorder(new BevelBorder(0));
           but18.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but18, new AbsoluteConstraints( 10, 228-5+10, 180-15-35, 18 ) );

           but19 = new JButton("ACCESSED  DATE");
           but19.setBackground(new Color(0,0,80));
           but19.setForeground(Color.cyan);
           but19.setBorder(new BevelBorder(0));
           but19.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but19, new AbsoluteConstraints( 10, 247-5+10, 180-15-35, 18 ) );

           inputConstraints();

           setVisible(true);
      }

    private void inputConstraints()
      {
          bookname = new JLabel( "<== BOOK  TITLE ==>" );
          bookname.setForeground ( new Color ( 120, 120, 153 ) );
          bookname.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          cmppn1.add ( bookname, new AbsoluteConstraints( 15, 48+20) );

          bookname1 = new JComboBox( );
          bookname1.setEditable(true);
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

          edt = new JLabel( "<= BOOK  EDITION =>" );
          edt.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          edt.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( edt, new AbsoluteConstraints( 310+5+5, 21+20) );

          edt1 = new JTextField( );
          edt1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0,0,40 ) ));
          edt1.setForeground ( new Color ( 255, 0, 153 ) );
          edt1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( edt1, new AbsoluteConstraints( 440-10+5, 18+20, 135,20 ) );

          acess = new JLabel( "<== ACCESS-NO ==>" );
          acess.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          acess.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( acess, new AbsoluteConstraints(15, 21+20) );

          acess1 = new JTextField( );
          acess1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          acess1.setForeground ( new Color ( 255, 0, 153 ) );
          acess1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( acess1, new AbsoluteConstraints( 145-20, 18+20, 150-15, 20 ) );

          aut1s = new JLabel( "AUTHOR1-SURNAME" );
          aut1s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut1s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut1s, new AbsoluteConstraints(15, 74+20 ) );

          aut1s1 = new JComboBox( );
          aut1s1.setEditable(true);
          aut1s1.setVisible(false);
          aut1s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
          aut1s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut1s1, new AbsoluteConstraints( 145-20, 72+20, 150-15, 20 ) );

          aut1 = new JTextField( );
          aut1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut1.setForeground ( new Color ( 255, 0, 153 ) );
          aut1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut1, new AbsoluteConstraints( 145-20, 72+20, 150-15, 20 ) );

          aut1f = new JLabel( "AUTHOR1-REALNAME" );
          aut1f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut1f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut1f, new AbsoluteConstraints( 310+5+5, 74+20 ) );

          aut1f1 = new JComboBox( );
          aut1f1.setEditable(true);
          aut1f1.setVisible(false);
          aut1f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0, 0, 40) ));
          aut1f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut1f1, new AbsoluteConstraints( 440-10+5, 72+20, 150-15, 20) );

          aut2 = new JTextField( );
          aut2.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut2.setForeground ( new Color ( 255, 0, 153 ) );
          aut2.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut2, new AbsoluteConstraints( 440-10+5, 72+20, 150-15, 20) );

          aut2s = new JLabel( "AUTHOR2-SURNAME" );
          aut2s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut2s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut2s, new AbsoluteConstraints(15,101+20) );

          aut2s1 = new JComboBox( );
          aut2s1.setEditable(true);
          aut2s1.setVisible(false);
          aut2s1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          aut2s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut2s1, new AbsoluteConstraints( 145-20, 99+20, 150-15, 20  ) );

          aut3 = new JTextField( );
          aut3.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut3.setForeground ( new Color ( 255, 0, 153 ) );
          aut3.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut3, new AbsoluteConstraints( 145-20, 99+20, 150-15, 20) );

          aut2f = new JLabel( "AUTHOR2-REALNAME" );
          aut2f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut2f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut2f, new AbsoluteConstraints(310+5+5, 101+20 ) );

          aut2f1 = new JComboBox( );
          aut2f1.setEditable(true);
          aut2f1.setVisible(false);
          aut2f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          aut2f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut2f1, new AbsoluteConstraints( 440-10+5, 99+20, 150-15, 20 ) );

          aut4 = new JTextField( );
          aut4.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut4.setForeground ( new Color ( 255, 0, 153 ) );
          aut4.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut4, new AbsoluteConstraints( 440-10+5, 99+20, 150-15, 20) );

          aut3s = new JLabel( "AUTHOR3-SURNAME" );
          aut3s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut3s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut3s, new AbsoluteConstraints(15, 128+20 ) );

          aut3s1 = new JComboBox( );
          aut3s1.setEditable(true);
          aut3s1.setVisible(false);
          aut3s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut3s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut3s1, new AbsoluteConstraints( 145-20, 126+20, 150-15, 20 ) );

          aut5 = new JTextField( );
          aut5.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut5.setForeground ( new Color ( 255, 0, 153 ) );
          aut5.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut5, new AbsoluteConstraints( 145-20, 126+20, 150-15, 20) );

          aut3f = new JLabel( "AUTHOR3-REALNAME" );
          aut3f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut3f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut3f, new AbsoluteConstraints(310+5+5, 128+20 ) );

          aut3f1 = new JComboBox( );
          aut3f1.setEditable(true);
          aut3f1.setVisible(false);
          aut3f1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut3f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut3f1, new AbsoluteConstraints( 440-10+5, 126+20, 150-15, 20 ) );

          aut6 = new JTextField( );
          aut6.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut6.setForeground ( new Color ( 255, 0, 153 ) );
          aut6.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut6, new AbsoluteConstraints( 440-10+5, 126+20, 150-15, 20) );

          pub = new JLabel( "BOOK-PUBLISHER:" );
          pub.setForeground ( new Color ( 120, 120, 153 ) );
          pub.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          cmppn1.add ( pub, new AbsoluteConstraints( 15, 155+20) );

          pub1 = new JComboBox( );
          pub1.setEditable(true);
          pub1.setVisible(false);
          pub1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          pub1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( pub1, new AbsoluteConstraints( 126, 153+20, 150-15, 20 ) );

          pub2 = new JTextField( );

          pub2.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          pub2.setForeground ( new Color ( 255, 0, 153 ) );
          pub2.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( pub2, new AbsoluteConstraints( 126, 153+20, 150-15, 20 ) );

          sub1 = new JLabel( "<==DEPARTMENT :==>" );
          sub1.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          sub1.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( sub1, new AbsoluteConstraints( 310+5+5, 155+20) );

          sub11 = new JComboBox( );
          sub11.addItem("");
          sub11.addItem("ELECTRONICS");
          sub11.addItem("ELECTRICALS");
          sub11.addItem("COMMUNICATIONS");
          sub11.addItem("COMPUTERS");
          sub11.addItem("MECHANICAL");
          sub11.addItem("LANGUAGE");
          sub11.addItem("PHYSICS");
          sub11.addItem("CHEMISTRY");
          sub11.addItem("OTHERS");
          sub11.setBackground(Color.white);
          sub11.setEditable(false);
          sub11.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          sub11.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( sub11, new AbsoluteConstraints( 440-10+5, 153+20, 150-15, 20 ) );

          sub2 = new JLabel( "<== COURSE ==>" );
          sub2.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          sub2.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( sub2, new AbsoluteConstraints( 15, 182+20) );

          sub21 = new JComboBox( );
          sub21.setVisible(false);
          sub21.setEditable(true);
          sub21.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          sub21.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( sub21, new AbsoluteConstraints(145-20 , 180+20, 150-15, 20 ) );

          sub22 = new JTextField();
          sub22.setEditable(true);
          sub22.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          sub22.setForeground ( new Color ( 255, 0, 153 ) );
          sub22.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( sub22, new AbsoluteConstraints(145-20 , 180+20, 150-15, 20 ) );


          sub3 = new JLabel( "<= SPECIFICATION =>" );
          sub3.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          sub3.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( sub3, new AbsoluteConstraints( 310+5+5,182+20) );

          sub31 = new JTextField( );
          sub31.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          sub31.setForeground ( new Color ( 255, 0, 153 ) );
          sub31.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( sub31, new AbsoluteConstraints( 440-10+5, 180+20, 150-15, 20 ) );

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

          box8 = new JComboBox();
          box8.setForeground( new Color( 255, 0, 153 ));
          box8.setBackground(Color.white);
          box8.addItem("");
          box8.addItem("OR");
          box8.addItem("AND");
          cmppn1.add (box8,new AbsoluteConstraints( 265, 97+26+4+20, 45 , 18 ) );

          box9 = new JComboBox();
          box9.setForeground( new Color( 255, 0, 153 ));
          box9.setBackground(Color.white);
          box9.addItem("");
          box9.addItem("OR");
          box9.addItem("AND");
          cmppn1.add (box9,new AbsoluteConstraints( 578, 97+26+4+20, 45 , 18 ) );

          box10 = new JComboBox();
          box10.setForeground( new Color( 255, 0, 153 ));
          box10.setBackground(Color.white);
          box10.addItem("");
          box10.addItem("OR");
          box10.addItem("AND");
          cmppn1.add (box10,new AbsoluteConstraints( 265, 97+28+28+20, 45 , 18 ) );

          box11 = new JComboBox();
          box11.setForeground( new Color( 255, 0, 153 ));
          box11.setBackground(Color.white);
          box11.addItem("");
          box11.addItem("OR");
          box11.addItem("AND");
          cmppn1.add (box11,new AbsoluteConstraints( 578, 97+28+28+20, 45 , 18 ) );

          box12 = new JComboBox();
          box12.setForeground( new Color( 255, 0, 153 ));
          box12.setBackground(Color.white);
          box12.addItem("");
          box12.addItem("OR");
          box12.addItem("AND");
          cmppn1.add (box12,new AbsoluteConstraints( 265, 97+28+28+28+20, 45 , 18 ) );

          box13 = new JComboBox();
          box13.setForeground( new Color( 255, 0, 153 ));
          box13.setBackground(Color.white);
          box13.addItem("");
          box13.addItem("OR");
          box13.addItem("AND");
          cmppn1.add (box13,new AbsoluteConstraints( 578, 97+28+28+28+20, 45 , 18 ) );



          search = new JButton( "SEARCH" ) ;
          search.setBackground ( Color.cyan  );
          search.setForeground( Color .black );
          search.setMnemonic('S');
          search.setBorder ( new BevelBorder ( 0 ));
          cmppn1.add ( search, new AbsoluteConstraints( 150, 240,110,27 ) );

          print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setVisible(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 150, 240,110,27 ) );

          next = new JButton( "NEXT>>>" ) ;
          next.setBackground ( Color.cyan );
          next.setForeground(  Color .black );
          next.setMnemonic('N');
          next.setBorder ( new BevelBorder (0));
          cmppn1.add ( next, new AbsoluteConstraints( 371-110,240,110,27 ) );

          quit = new JButton( "EXIT" ) ;
          quit.setBackground ( Color.cyan );
          quit.setMnemonic('X');
          quit.setForeground( Color .black );
          quit.setBorder ( new BevelBorder (0));
          cmppn1.add ( quit, new AbsoluteConstraints( 483-110,240,110,27 ) );

      }
      private void componentListener()
      {
           but3.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               if(columnsParam == null)
                 {
                      columnsParam = "LIB.ACESSNO";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.ACESSNO";
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
                      columnsParam = "LIB.BOOKNAME,LIB.EDITION";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.BOOKNAME,LIB.EDITION";
                 }
            }
          }
          );
          but5.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "LIB.AUTHOR1S||LIB.AUTHOR1F AUTHOR1";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.AUTHOR1S||LIB.AUTHOR1F AUTHOR1";
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
                      columnsParam = "LIB.AUTHOR2S||LIB.AUTHOR2F AUTHOR2";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.AUTHOR2S||LIB.AUTHOR2F AUTHOR2";
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
                      columnsParam = "AUTHOR3S||AUTHOR3F AUTHOR3";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.AUTHOR3S||LIB.AUTHOR3F AUTHOR3";
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
                      columnsParam = "LIB.PUBLISHER";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.PUBLISHER";
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
                      columnsParam = "LIB.PLACE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.PLACE";
                 }
            }
          }
          );

          but14.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "LIB.SUBJECT1 DEPARTMENT";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.SUBJECT1 DEPARTMENT";
                 }
            }
          }
          );
          but15.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "LIB.SUBJECT2 COURSE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.SUBJECT2 COURSE";
                 }
            }
          }
          );
          but16.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "LIB.SUBJECT3 SPECIFICATION";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.SUBJECT3 SPECIFICATION";
                 }
            }
          }
          );
          but17.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "LIB.DESCPRTN DESCRIPTION";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.DESCPRTN DESCRIPTION";
                 }
            }
          }
          );
          but18.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "LIB.NOTE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"LIB.NOTE";
                 }
            }
          }
          );
          but19.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "TO_CHAR(LIB.ACESSDATE,'DD-MON-YYYY') ACESSDATE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"TO_CHAR(LIB.ACESSDATE,'DD-MON-YYYY') ACESSDATE";
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


                          rs = s.executeQuery( "SELECT DISTINCT(BOOKNAME) FROM LIBRARY WHERE BOOKNAME LIKE "+"'"+title.getText().toUpperCase()+"%"+"'" );
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
           aut1.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR1S) FROM LIBRARY WHERE AUTHOR1S LIKE "+"'"+aut1.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut1s1.addItem(rs.getObject(1));
                              ++stage1;
                           }

                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,"error"+sq.getMessage());
                        }
                    if(stage1>0)
                          {
                             aut1s1.setVisible(true);
                             aut1.setVisible(false);
                          }
                }
            }
          );

          aut2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR1F) FROM LIBRARY WHERE AUTHOR1F LIKE "+"'"+aut2.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut1f1.addItem(rs.getObject(1));
                              ++stage2;
                           }
                          if(stage2>0)
                          {
                             aut1f1.setVisible(true);
                             aut2.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );

          aut3.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR2S) FROM LIBRARY WHERE AUTHOR2S LIKE "+"'"+aut3.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut2s1.addItem(rs.getObject(1));
                              ++stage3;
                           }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                      if(stage3>0)
                          {
                             aut2s1.setVisible(true);
                             aut3.setVisible(false);
                          }
                }
            }
          );

          aut4.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR2F) FROM LIBRARY WHERE AUTHOR2F LIKE "+"'"+aut4.getText().toUpperCase() +"%"+"'" );
                          while( rs.next() )
                           {
                              aut2f1.addItem(rs.getObject(1));
                              ++stage4;
                           }
                          if(stage4>0)
                          {
                             aut2f1.setVisible(true);
                             aut4.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );

          aut5.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR3S) FROM LIBRARY WHERE AUTHOR3S LIKE "+"'"+aut5.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              aut3s1.addItem(rs.getObject(1));
                              ++stage5;
                           }
                          if(stage5>0)
                          {
                             aut3s1.setVisible(true);
                             aut5.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );

          aut6.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR3F) FROM LIBRARY WHERE AUTHOR3F LIKE "+"'"+aut6.getText().toUpperCase() +"%"+"'" );
                          while( rs.next() )
                           {
                              aut3f1.addItem(rs.getObject(1));
                              ++stage6;
                           }
                          if(stage6>0)
                          {
                             aut3f1.setVisible(true);
                             aut6.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );
           pub2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(PUBLISHER) FROM LIBRARY WHERE PUBLISHER LIKE "+"'"+pub2.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              pub1.addItem(rs.getObject(1));
                              ++flag1;
                           }
                          if(flag1>0)
                          {
                             pub1.setVisible(true);
                             pub2.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }
                }
            }
          );
           sub22.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(SUBJECT2) FROM LIBRARY WHERE SUBJECT2 LIKE "+"'"+sub22.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              sub21.addItem(rs.getObject(1));
                              ++flag3;
                           }
                           if(flag3>0)
                          {
                             sub21.setVisible(true);
                             sub22.setVisible(false);
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
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count1<1)
                        {
                           item1++;
                           if( acess1.getText().toUpperCase()==null || acess1.getText().equals("") ) { }
                           else
                            {
                               number = acess1.getText().toUpperCase();
                               if( item1 > 0 )
                                 {
                                     constraints=constraints+" ACESSNO = "+"'"+number+"'"+" "+(String)box1.getSelectedItem();
                                 }
                             }
                           count1++;
                        }
                   }
             }
          );
          box2.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count2<1)
                        {
                           item2++;
                           if( edt1.getText()==null || edt1.getText().equals("") ) { }
                           else
                             {
                                edition = edt1.getText().toUpperCase();
                                if( item2 > 0 )
                                  {
                                     constraints=constraints+" EDITION = "+"'"+edition+"'"+" "+(String)box2.getSelectedItem();
                                  }
                             }
                           count2++;
                        }
                   }
             }
          );
          box3.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count3<1)
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
                                        constraints=constraints+" BOOKNAME = "+"'"+TITLE+"'"+" "+(String)box3.getSelectedItem();
                                     }
                                 }
                            }
                          else
                            {
                               if( item3 > 0 )
                                {
                                    constraints=constraints+" BOOKNAME = "+"'"+TITLE+"'"+" "+(String)box3.getSelectedItem();
                                }
                            }
                         count3++;
                        }
                   }
             }
          );
          box4.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count4<1)
                        {
                           item4++;
                           AUT1=(String)aut1s1.getSelectedItem();
                           if(AUT1==null || AUT1.equals(""))
                             {
                               AUT1 = aut1.getText().toUpperCase();
                               if(AUT1==null || AUT1.equals(""))
                               {
                               }
                               else
                               {
                                 if( item4 > 0 )
                                  {
                                     constraints=constraints+" AUTHOR1S = "+"'"+AUT1+"'"+" "+(String)box4.getSelectedItem();
                                  }
                               }
                             }
                           else
                             {
                                if( item4 > 0 )
                                  {
                                     constraints=constraints+" AUTHOR1S = "+"'"+AUT1+"'"+" "+(String)box4.getSelectedItem();
                                  }
                             }
                           count4++;
                        }
                   }
             }
          );

          box5.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count5<1)
                        {
                           item5++;
                           AUT2=(String)aut1f1.getSelectedItem();
                           if(AUT2==null || AUT2.equals(""))
                             {
                                 AUT2 = aut2.getText().toUpperCase();
                                 if(AUT2==null || AUT2.equals(""))
                                 {
                                 }
                                 else
                                 {
                                   if( item5 > 0 )
                                    {
                                       constraints=constraints+" AUTHOR1F = "+"'"+AUT2+"'"+" "+(String)box5.getSelectedItem();
                                    }
                                 }
                             }
                           else
                             {
                                if( item5 > 0 )
                                    {
                                       constraints=constraints+" AUTHOR1F = "+"'"+AUT2+"'"+" "+(String)box5.getSelectedItem();
                                    }
                             }

                           count5++;
                        }
                   }
             }
          );
          box6.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count6<1)
                        {
                           item6++;
                           AUT3=(String)aut2s1.getSelectedItem();
                           if( AUT3==null || AUT3.equals("") )
                             {
                                AUT3 = aut3.getText().toUpperCase();
                                if( AUT3==null || AUT3.equals("") )
                                  {
                                  }
                                  else
                                  {
                                  if( item6 > 0 )
                                    {
                                       constraints=constraints+" AUTHOR2S = "+"'"+AUT3+"'"+" "+(String)box6.getSelectedItem();
                                    }
                                  }
                               }
                             else
                               {
                                  if( item6 > 0 )
                                    {
                                       constraints=constraints+" AUTHOR2S = "+"'"+AUT3+"'"+" "+(String)box6.getSelectedItem();
                                    }
                               }
                           count6++;
                        }
                   }
             }
          );
          box7.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count7<1)
                        {
                           item7++;
                           AUT4=(String)aut2f1.getSelectedItem();
                           if( AUT4==null || AUT4.equals("") )
                             {
                                AUT4 = aut4.getText().toUpperCase();
                                if( AUT4==null || AUT4.equals("") )
                                  {
                                  }
                                  else
                                   {
                                      if( item7 > 0 )
                                        {
                                            constraints=constraints+" AUTHOR2F = "+"'"+AUT4+"'"+" "+(String)box7.getSelectedItem();
                                        }
                                    }
                              }
                            else
                              {
                                 if( item7 > 0 )
                                        {
                                            constraints=constraints+" AUTHOR2F = "+"'"+AUT4+"'"+" "+(String)box7.getSelectedItem();
                                        }
                              }
                         count7++;
                        }
                   }
             }
          );

          box8.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count8<1)
                        {
                           item8++;
                           AUT5=(String)aut3s1.getSelectedItem();
                           if(AUT5==null || AUT5.equals(""))
                           {
                            AUT5 = aut5.getText().toUpperCase();
                            if(AUT5==null || AUT5.equals(""))
                            {
                            }
                            else
                            {
                               if( item8 > 0 )
                                 {
                                    constraints=constraints+" AUTHOR3S = "+"'"+AUT5+"'"+" "+(String)box8.getSelectedItem();
                                 }
                            }
                         }
                       else
                         {
                            if( item8 > 0 )
                                 {
                                    constraints=constraints+" AUTHOR3S = "+"'"+AUT5+"'"+" "+(String)box8.getSelectedItem();
                                 }
                         }
                         count8++;
                        }
                   }
             }
          );
          box9.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count9<1)
                        {
                           item9++;
                           AUT6=(String)aut3f1.getSelectedItem();
                           if (AUT6==null || AUT6.equals(""))
                           {
                               AUT6 = aut6.getText().toUpperCase();
                               if (AUT6==null || AUT6.equals(""))
                                {
                                }
                               else
                                {
                                    if( item9 > 0 )
                                    {
                                       constraints=constraints+" AUTHOR3F = "+"'"+AUT6+"'"+" "+(String)box9.getSelectedItem();
                                    }
                                }
                            }
                            else
                            {
                               if( item9 > 0 )
                                    {
                                       constraints=constraints+" AUTHOR3F = "+"'"+AUT6+"'"+" "+(String)box9.getSelectedItem();
                                    }
                            }

                           count9++;
                        }
                   }
             }
          );
          box10.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count10<1)
                        {
                           item10++;
                           PUB=(String)pub1.getSelectedItem();
                           if(PUB==null || PUB.equals(""))
                           {
                            PUB = pub2.getText().toUpperCase();
                            if(PUB==null || PUB.equals(""))
                            {
                            }
                            else
                            {
                            if( item10 > 0 )
                            {
                               constraints=constraints+" PUBLISHER = "+"'"+PUB+"'"+" "+(String)box10.getSelectedItem();
                            }
                           }
                         }
                         else
                         {
                             if( item10 > 0 )
                            {
                               constraints=constraints+" PUBLISHER = "+"'"+PUB+"'"+" "+(String)box10.getSelectedItem();
                            }
                         }
                          count10++;
                        }
                   }
             }
          );
          box11.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count11<1)
                        {
                           item11++;
                           if( sub11.getSelectedItem()== null || sub11.getSelectedItem().equals("") ){ }
                           else
                             {
                                SUB1 = ((String)sub11.getSelectedItem()).toUpperCase();
                                if( item11 > 0 )
                                {
                                   constraints=constraints+" SUBJECT1 = "+"'"+SUB1+"'"+" "+(String)box11.getSelectedItem();
                                }
                             }
                           count11++;
                        }
                   }
             }
          );
          box12.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count12<1)
                        {
                           item12++;
                           SUB2=(String)sub21.getSelectedItem();
                             if(SUB2==null || SUB2.equals(""))
                               {
                                  SUB2 = sub22.getText().toUpperCase();
                                  if(SUB2==null || SUB2.equals(""))
                                  {
                                  }
                                  else
                                  {
                                  if( item12 > 0 )
                                    {
                                       constraints=constraints+" SUBJECT2 = "+"'"+SUB2+"'"+" "+(String)box12.getSelectedItem();
                                    }
                                  }
                               }
                             else
                               {
                                  if( item12 > 0 )
                                    {
                                       constraints=constraints+" SUBJECT2 = "+"'"+SUB2+"'"+" "+(String)box12.getSelectedItem();
                                    }
                               }
                           count12++;
                        }
                   }
             }
          );
          box13.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count13<1)
                        {
                           item13++;
                           if( sub31.getText()==null || sub31.getText().equals("") ) { }
                             else
                               {
                                  SUB3 = sub31.getText().toUpperCase();
                                  if( item13 > 0 )
                                    {
                                       constraints=constraints+" SUBJECT3 = "+"'"+SUB3+"'"+" "+(String)box13.getSelectedItem();
                                    }
                               }

                           count13++;
                        }
                   }
             }
          );

          search.addActionListener(new ActionListener()
             {
                public void actionPerformed(ActionEvent e)
                  {

                  if( acess1.getText().toUpperCase()==null || acess1.getText().equals("") ) { }
                else
                 {
                    number = acess1.getText().toUpperCase();
                    if( item1 > 0 ){}
                    else
                      {
                         constraints=constraints+"ACESSNO = "+"'"+number+"'";
                      }
                 }

                 if( edt1.getText()==null || edt1.getText().equals("") ) { }
                 else
                   {
                      edition = edt1.getText().toUpperCase();
                      if( item2 > 0 ){}
                      else
                        {
                           constraints=constraints+" EDITION = "+"'"+edition+"'";
                           JOptionPane.showMessageDialog(null,constraints);
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
                             constraints=constraints+" BOOKNAME = "+"'"+TITLE+"'";
                             JOptionPane.showMessageDialog(null,constraints);
                         }
                      }
                    }
                   else
                    {
                       if( item3 > 0 ) {}
                         else
                          {
                             constraints=constraints+" BOOKNAME = "+"'"+TITLE+"'";
                             JOptionPane.showMessageDialog(null,constraints);
                         }
                    }
                   AUT1=(String)aut1s1.getSelectedItem();
                   if(AUT1==null || AUT1.equals(""))
                     {
                       AUT1 = aut1.getText().toUpperCase();
                       if(AUT1==null || AUT1.equals(""))
                       {
                       }
                       else
                       {
                         if( item4 > 0 ) {}
                         else
                          {
                             constraints=constraints+" AUTHOR1S = "+"'"+AUT1+"'";
                             JOptionPane.showMessageDialog(null,constraints);
                          }
                       }
                     }
                   else
                     {
                        if( item4 > 0 ) {}
                         else
                          {
                             constraints=constraints+" AUTHOR1S = "+"'"+AUT1+"'";
                             JOptionPane.showMessageDialog(null,constraints);
                          }
                     }

                     AUT2=(String)aut1f1.getSelectedItem();
                     if(AUT2==null || AUT2.equals(""))
                       {
                         AUT2 = aut2.getText().toUpperCase();
                         if(AUT2==null || AUT2.equals(""))
                         {
                         }
                         else
                         {
                           if( item5 > 0 ){}
                           else
                            {
                               constraints=constraints+" AUTHOR1F = "+"'"+AUT2+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                         }
                       }
                     else
                       {
                          if( item5 > 0 ){}
                           else
                            {
                               constraints=constraints+" AUTHOR1F = "+"'"+AUT2+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                       }

                     AUT3=(String)aut2s1.getSelectedItem();
                     if( AUT3==null || AUT3.equals("") )
                       {
                          AUT3 = aut3.getText().toUpperCase();
                          if( AUT3==null || AUT3.equals("") )
                          {
                          }
                          else
                          {
                          if( item6 > 0 ) {}
                          else
                            {
                               constraints=constraints+" AUTHOR2S = "+"'"+AUT3+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                          }
                       }
                     else
                       {
                         if( item6 > 0 ) {}
                          else
                            {
                               constraints=constraints+" AUTHOR2S = "+"'"+AUT3+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                       }

                       AUT4=(String)aut2f1.getSelectedItem();
                       if( AUT4==null || AUT4.equals("") )
                         {
                            AUT4 = aut4.getText().toUpperCase();
                           if( AUT4==null || AUT4.equals("") )
                           {
                           }
                           else
                           {
                            if( item7 > 0 ){}
                            else
                            {
                               constraints=constraints+" AUTHOR2F = "+"'"+AUT4+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                          }
                         }
                       else
                         {
                            if( item7 > 0 ){}
                            else
                            {
                               constraints=constraints+" AUTHOR2F = "+"'"+AUT4+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                         }

                       AUT5=(String)aut3s1.getSelectedItem();
                       if(AUT5==null || AUT5.equals(""))
                         {
                            AUT5 = aut5.getText().toUpperCase();
                            if(AUT5==null || AUT5.equals(""))
                            {
                            }
                            else
                            {
                            if( item8 > 0 ) {}
                            else
                            {
                               constraints=constraints+" AUTHOR3S = "+"'"+AUT5+"'";
                            }
                            }
                         }
                       else
                         {
                           if( item8 > 0 ) {}
                            else
                            {
                               constraints=constraints+" AUTHOR3S = "+"'"+AUT5+"'";
                            }
                         }

                       AUT6=(String)aut3f1.getSelectedItem();
                       if (AUT6==null || AUT6.equals(""))
                         {
                            AUT6 = aut6.getText().toUpperCase();
                            if (AUT6==null || AUT6.equals(""))
                           {
                           }
                           else
                           {
                            if( item9 > 0 ){}
                            else
                            {
                               constraints=constraints+" AUTHOR3F = "+"'"+AUT6+"'";
                            }
                            }
                         }
                       else
                         {
                           if( item9 > 0 ){}
                            else
                            {
                               constraints=constraints+" AUTHOR3F = "+"'"+AUT6+"'";
                            }
                         }

                       PUB=(String)pub1.getSelectedItem();
                       if(PUB==null || PUB.equals(""))
                         {
                            PUB = pub2.getText().toUpperCase();
                            if(PUB==null || PUB.equals(""))
                            {
                            }
                            else
                            {
                            if( item10 > 0 ){}
                            else
                            {
                               constraints=constraints+" PUBLISHER = "+"'"+PUB+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                            }
                         }
                       else
                         {
                            if( item10 > 0 ){}
                            else
                            {
                               constraints=constraints+" PUBLISHER = "+"'"+PUB+"'";
                               JOptionPane.showMessageDialog(null,constraints);
                            }
                         }
                           if( sub11.getSelectedItem()== null || sub11.getSelectedItem().equals("") ){ }
                           else
                             {
                                SUB1 = ((String)sub11.getSelectedItem()).toUpperCase();
                                if( item11 > 0 ){}
                                else
                                {
                                   constraints=constraints+" SUBJECT1 = "+"'"+SUB1+"'";
                                }
                             }

                             SUB2=(String)sub21.getSelectedItem();
                             if(SUB2==null || SUB2.equals(""))
                               {
                                  SUB2 = sub22.getText().toUpperCase();
                                  if(SUB2==null || SUB2.equals(""))
                                  {
                                  }
                                  else
                                  {
                                  if( item12 > 0 ){}
                                  else
                                    {
                                       constraints=constraints+" SUBJECT2 = "+"'"+SUB2+"'";
                                    }
                                  }
                               }
                             else
                               {
                                  if( item12 > 0 ){}
                                  else
                                    {
                                       constraints=constraints+" SUBJECT2 = "+"'"+SUB2+"'";
                                    }
                               }

                             if( sub31.getText()==null || sub31.getText().equals("") ) { }
                             else
                               {
                                  SUB3 = sub31.getText().toUpperCase();
                                  if( item15 > 0 ) {}
                                  else
                                    {
                                       constraints=constraints+" SUBJECT3 = "+"'"+SUB3+"'";
                                    }
                               }


                               try
                                 {
                                     if(columnsParam != null)
                                       {
                                          if(constraints.equals(""))
                                            {
                                               s = con.createStatement();
                                               rs=s.executeQuery( " SELECT "+columnsParam+" FROM LIBRARY LIB WHERE "+
                                                           " LIB.ACESSNO NOT IN (SELECT BK.CODE FROM BKTRANSACTION BK"+
                                                           " WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN "+
                                                           " ( SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )"+
                                                           " AND LIB.STATUS IS NULL " );

                                               if(rs.next())
                                                 {
                                                    getTable(rs);
                                                 }
                                               else
                                                 {
                                                    JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY   !  .  .  . ","BOOKS DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                                 }
                                               s.close();
                                            }
                                          else
                                            {
                                               s = con.createStatement();

                                               rs=s.executeQuery( " SELECT "+columnsParam+" FROM LIBRARY LIB WHERE "+
                                                           " LIB.ACESSNO NOT IN (SELECT BK.CODE FROM BKTRANSACTION BK"+
                                                           " WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN "+
                                                           " ( SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )"+
                                                           " AND LIB.STATUS IS NULL  AND "+constraints );

                                               if(rs.next())
                                                 {
                                                    getTable(rs);
                                                 }
                                               else
                                                 {
                                                    JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  . ","BOOKS DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                                 }
                                               s.close();
                                            }
                                       }
                                     else
                                       {
                                          if( constraints.equals(""))
                                            {
                                              // JOptionPane.showMessageDialog(null,constraints);
                                               s=con.createStatement();



                                               rs=s.executeQuery( " SELECT LIB.ACESSNO,LIB.BOOKNAME,"+
                                                                  " LIB.AUTHOR1S||LIB.AUTHOR1F AUTHOR1,"+
                                                                  " LIB.AUTHOR2S||LIB.AUTHOR2F AUTHOR2,"+
                                                                  " LIB.AUTHOR3S||LIB.AUTHOR3F AUTHOR3,"+
                                                                  " LIB.PUBLISHER,LIB.SUBJECT1 DEPARTMENT,"+
                                                                  " LIB.SUBJECT2 COURSE,LIB.SUBJECT3 SPECIFICATION "+
                                                                  " FROM LIBRARY LIB WHERE LIB.STATUS IS NULL AND "+
                                                                  " LIB.ACESSNO NOT IN (SELECT BK.CODE FROM BKTRANSACTION BK"+
                                                                  " WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN "+
                                                                  " ( SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )");


                                               if(rs.next())
                                                 {
                                                    getTable(rs);
                                                 }
                                               else
                                                 {
                                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .","BOOKS DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                                 }
                                                 s.close();
                                            }
                                          else
                                            {
                                               s = con.createStatement();

                                               rs=s.executeQuery( " SELECT LIB.ACESSNO,LIB.BOOKNAME,"+
                                                                  " LIB.AUTHOR1S || LIB.AUTHOR1F AUTHOR1,"+
                                                                  " LIB.AUTHOR2S || LIB.AUTHOR2F AUTHOR2,"+
                                                                  " LIB.AUTHOR3S || LIB.AUTHOR3F AUTHOR2,"+
                                                                  " LIB.PUBLISHER, LIB.SUBJECT1 DEPARTMENT,"+
                                                                  " LIB.SUBJECT2 COURSE,LIB.SUBJECT3 SPECIFICATION "+
                                                                  " FROM LIBRARY LIB WHERE "+
                                                                  " LIB.ACESSNO NOT IN (SELECT BK.CODE FROM BKTRANSACTION BK"+
                                                                  " WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN "+
                                                                  " ( SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )"+
                                                                  " AND LIB.STATUS IS NULL AND " + constraints );

                                               if(rs.next())
                                                {
                                                   getTable(rs);
                                                 }
                                               else
                                                 {
                                                    JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .","BOOKS DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                                 }
                                             s.close();

                                            }

                                      }
                                    search.setVisible(false);
                                    print.setVisible(true);
                                 }
                               catch(SQLException sqlex)
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
                                                       new MessageFormat("AVAILABLE BOOK'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("AVAILABLE BOOK'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
               print.setEnabled(true);
                acess1.setText("");
                title.setText("");
                edt1.setText("");
                aut1.setText("");
                aut2.setText("");
                aut3.setText("");
                aut4.setText("");
                aut5.setText("");
                aut6.setText("");
                pub2.setText("");
                sub22.setText("");
                sub11.setSelectedItem("");
                sub21.setSelectedItem("");
                sub31.setText("");

                aut1s1.removeAllItems();
                aut1f1.removeAllItems();
                aut2s1.removeAllItems();
                aut2f1.removeAllItems();
                aut3s1.removeAllItems();
                aut3f1.removeAllItems();
                bookname1.removeAllItems();
                pub1.removeAllItems();

                aut1s1.setVisible(false);
                aut1f1.setVisible(false);
                aut2s1.setVisible(false);
                aut2f1.setVisible(false);
                aut3s1.setVisible(false);
                aut3f1.setVisible(false);
                bookname1.setVisible(false);
                pub1.setVisible(false);

                aut1.setVisible(true);
                aut2.setVisible(true);
                aut3.setVisible(true);
                aut4.setVisible(true);
                aut5.setVisible(true);
                aut6.setVisible(true);
                title.setVisible(true);
                pub2.setVisible(true);
                sub22.setVisible(true);

                columnsParam=null;
                constraints="";

                box1.setSelectedItem("");
                box2.setSelectedItem("");
                box3.setSelectedItem("");
                box4.setSelectedItem("");
                box5.setSelectedItem("");
                box6.setSelectedItem("");
                box7.setSelectedItem("");
                box8.setSelectedItem("");
                box9.setSelectedItem("");
                box10.setSelectedItem("");
                box11.setSelectedItem("");
                box12.setSelectedItem("");
                box13.setSelectedItem("");

                item1=0;
                item2=0;
                item3=0;
                item4=0;
                item5=0;
                item6=0;
                item7=0;
                item8=0;
                item9=item10=item11=item12=item13=0;
                count1=0;count2=0;count3=0;count4=0;
                count5=0;count6=0;count7=0;count8=0;count9=0;
                count10=0;count11=0;count12=0;count13=0;

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
          new AvailableBooksSearch();
       }
  }



  /*
  rs=s.executeQuery( " SELECT "+columnsParam+" FROM LIBRARY LIB WHERE "+
                                                           " LIB.ACESSNO NOT IN (SELECT BK.CODE FROM BKTRANSACTION BK
                                                           " WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN "+
                                                           " ( SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )"+
                                                           " AND LIB.STATUS IS NULL " + constraints );
  */