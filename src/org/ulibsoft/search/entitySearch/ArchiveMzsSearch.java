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

public class ArchiveMzsSearch extends JFrame
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

     private String columnsParam;
     private int stage2=0,count=0;
     private String NOTE,REC,SUP,SOP,COM,PCITY,PERIOD,mzcode,vol,TITLE,number;

     private String constraints="";
     private int item1=0,item2=0,item3=0,item4=0,item5=0,item6=0,item7=0,item8=0;
     private int item9=0,item10=0,item11=0,item12=0,item13=0,item14=0,item15=0,item16=0,item17=0;
     private int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0,count9=0;
     private int count10=0,count11=0,count12=0,count13=0,count14=0,count15=0,count16=0,count17=0;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs,rs1 ;


     public ArchiveMzsSearch()
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
           c.add(cmppn,new AbsoluteConstraints(5,50,150,360-60-10));

           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(155,50,595+35,360-60-10));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,410-60,780,160+50));

           but0 = new JLabel( "    SEARCH  ARCHIVE  RECORDS" ) ;
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
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20-5, 180-15-35, 18 ) );

           but4 = new JButton("TITLE ");
           but4.setBackground(new Color(0,0,80));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           but4.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but4, new AbsoluteConstraints( 10, 38-5, 180-15-35, 18 ) );

           but5 = new JButton("VOLUME");
           but5.setBackground(new Color(0,0,80));
           but5.setBorder(new BevelBorder(0));
           but5.setForeground(Color.cyan);
           but5.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but5, new AbsoluteConstraints( 10, 57-5, 180-15-35, 18 ) );

           but6 = new JButton("ISSUE NUMBER");
           but6.setBackground(new Color(0,0,80));
           but6.setForeground(Color.cyan);
           but6.setBorder(new BevelBorder(0));
           but6.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but6, new AbsoluteConstraints( 10, 76-5, 180-15-35, 18 ) );

           but7 = new JButton("SOURCE OF SUPPLY");
           but7.setBackground(new Color(0,0,80));
           but7.setForeground(Color.cyan);
           but7.setBorder(new BevelBorder(0));
           but7.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but7, new AbsoluteConstraints( 10, 95-5, 180-15-35, 18 ) );

           but8 = new JButton("NUMBER OF PAGES");
           but8.setBackground(new Color(0,0,80));
           but8.setForeground(Color.cyan);
           but8.setBorder(new BevelBorder(0));
           but8.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but8, new AbsoluteConstraints( 10, 114-5, 180-15-35, 18 ) );

           but9 = new JButton("SUPPLEMENT COPY");
           but9.setBackground(new Color(0,0,80));
           but9.setForeground(Color.cyan);
           but9.setBorder(new BevelBorder(0));
           but9.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but9, new AbsoluteConstraints( 10, 133-5, 180-15-35, 18 ) );

           but10 = new JButton("COMPACT DISC");
           but10.setBackground(new Color(0,0,80));
           but10.setForeground(Color.cyan);
           but10.setBorder(new BevelBorder(0));
           but10.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but10, new AbsoluteConstraints( 10, 152-5, 180-15-35, 18 ) );

           but11 = new JButton("PRICE");
           but11.setBackground(new Color(0,0,80));
           but11.setForeground(Color.cyan);
           but11.setBorder(new BevelBorder(0));
           but11.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but11, new AbsoluteConstraints( 10, 171-5, 180-15-35, 18 ) );

           but12 = new JButton("PERIODICITY");
           but12.setBackground(new Color(0,0,80));
           but12.setForeground(Color.cyan);
           but12.setBorder(new BevelBorder(0));
           but12.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but12, new AbsoluteConstraints( 10, 190-5, 180-15-35, 18 ) );

           but13 = new JButton("PERIOD");
           but13.setBackground(new Color(0,0,80));
           but13.setForeground(Color.cyan);
           but13.setBorder(new BevelBorder(0));
           but13.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but13, new AbsoluteConstraints( 10, 209-5, 180-15-35, 18 ) );

           but14 = new JButton("REMARKS");
           but14.setBackground(new Color(0,0,80));
           but14.setForeground(Color.cyan);
           but14.setBorder(new BevelBorder(0));
           but14.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but14, new AbsoluteConstraints( 10, 228-5, 180-15-35, 18 ) );


           but19 = new JButton("ACCESSED  DATE");
           but19.setBackground(new Color(0,0,80));
           but19.setForeground(Color.cyan);
           but19.setBorder(new BevelBorder(0));
           but19.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but19, new AbsoluteConstraints( 10, 223+19, 180-15-35, 18 ) );

           but20 = new JButton("RECIEVE  DATE");
           but20.setBackground(new Color(0,0,80));
           but20.setForeground(Color.cyan);
           but20.setBorder(new BevelBorder(0));
           but20.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but20, new AbsoluteConstraints( 10, 223+19+19, 180-15-35, 18 ) );

           inputConstraints();
           setVisible(true);
       }

     private void inputConstraints()
       {
          bookname = new JLabel( "<==MAG...  TITLE ==>" );
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

          edt = new JLabel( "<== MZ  VOLUME  ==>" );
          edt.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          edt.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( edt, new AbsoluteConstraints( 310+5+5, 21+20) );

          edt1 = new JTextField( );
          edt1.setEditable(true);
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

          aut1s = new JLabel( "ISSUE    NUMBER" );
          aut1s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut1s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut1s, new AbsoluteConstraints(15, 74+20 ) );

/*          aut1s1 = new JComboBox( );
          aut1s1.setEditable(false);
          aut1s1.setVisible(false);
          aut1s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
          aut1s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut1s1, new AbsoluteConstraints( 145-20, 72+20, 150-15, 20 ) );
  */
          aut1 = new JTextField( );
          aut1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut1.setForeground ( new Color ( 255, 0, 153 ) );
          aut1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut1, new AbsoluteConstraints( 145-20, 72+20, 150-15, 20 ) );

          aut1f = new JLabel( "SOURCE OF SUPPLY" );
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

          aut2s = new JLabel( "SUPPLEMENT COPY" );
          aut2s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut2s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut2s, new AbsoluteConstraints(15,101+20) );

          aut2s1 = new JComboBox( );
          aut2s1.setBackground(Color.white);
          aut2s1.addItem("");
          aut2s1.addItem("YES");
          aut2s1.addItem("NO");
          aut2s1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          aut2s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut2s1, new AbsoluteConstraints( 145-20, 99+20, 150-15, 20  ) );


          aut2f = new JLabel( "< COMPACT DISC >" );
          aut2f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut2f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut2f, new AbsoluteConstraints(310+5+5, 101+20 ) );

          aut2f1 = new JComboBox( );
          aut2f1.setBackground(Color.white);
          aut2f1.addItem("");
          aut2f1.addItem("YES");
          aut2f1.addItem("NO");
          aut2f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          aut2f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut2f1, new AbsoluteConstraints( 440-10+5, 99+20, 150-15, 20 ) );

          aut3s = new JLabel( "< PERIODICITY >" );
          aut3s.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut3s.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut3s, new AbsoluteConstraints(15, 128+20 ) );

          aut3s1 = new JComboBox( );
          aut3s1.setEditable(false);
          aut3s1.addItem("");
          aut3s1.addItem("WEEKLY");
          aut3s1.addItem("MONTHLY");
          aut3s1.setBackground(Color.white);
          aut3s1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut3s1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut3s1, new AbsoluteConstraints( 145-20, 126+20, 150-15, 20 ) );

          aut3f = new JLabel( "<<== PERIOD ==>>" );
          aut3f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          aut3f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut3f, new AbsoluteConstraints(310+5+5, 128+20 ) );

          aut6 = new JTextField( );
          aut6.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut6.setForeground ( new Color ( 255, 0, 153 ) );
          aut6.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut6, new AbsoluteConstraints( 440-10+5, 126+20, 150-15, 20) );


          recieve = new JLabel( "=>  RECIEVED  DATE" );
          recieve.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          recieve.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( recieve, new AbsoluteConstraints( 15, 128+20+26) );

          recieve1 = new JTextField( );
          recieve1.setEditable(true);
          recieve1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          recieve1.setForeground ( new Color ( 255, 0, 153 ) );
          recieve1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( recieve1, new AbsoluteConstraints( 145-20, 126+20+26, 135, 20 ) );

          note = new JLabel( "=> ACCESSED  DATE" );
          note.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,10));
          note.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( note, new AbsoluteConstraints( 310+5+5,128+20+26) );

          note1 = new JTextField( );
          note1.setEditable(true);
          note1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
          note1.setForeground ( new Color ( 255, 0, 153 ) );
          note1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( note1, new AbsoluteConstraints( 440-10+5, 126+20+26, 150-15, 20 ) );

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
          box4.addItem("");
          box4.setForeground( new Color( 255, 0, 153 ));
          box4.setBackground(Color.white);
          box4.addItem("OR");
          box4.addItem("AND");
          cmppn1.add (box4,new AbsoluteConstraints( 265, 47+26+20, 45 , 18 ) );

          box5 = new JComboBox();
          box5.setForeground( new Color( 255, 0, 153 ));
          box5.addItem("");
          box5.setBackground(Color.white);
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

          ins = new JButton( "LOST" ) ;
          ins.setBackground ( Color.cyan  );
          ins.setForeground( Color .black );
          ins.setMnemonic('L');
          ins.setBorder ( new BevelBorder ( 0 ));
          cmppn1.add ( ins, new AbsoluteConstraints( 35+56+50, 320-50-30,110,27 ) );

          search = new JButton( "SEARCH" ) ;
          search.setBackground ( Color.cyan  );
          search.setForeground( Color .black );
          search.setMnemonic('S');
          search.setBorder ( new BevelBorder ( 0 ));
          cmppn1.add ( search, new AbsoluteConstraints( 147+56, 320-80-30,110,27 ) );

          print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setVisible(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 147+56, 320-80-30,110,27 ) );

          next = new JButton( "NEXT>>>" ) ;
          next.setBackground ( Color.cyan );
          next.setForeground(  Color .black );
          next.setMnemonic('N');
          next.setBorder ( new BevelBorder (0));
          cmppn1.add ( next, new AbsoluteConstraints( 371-56,320-80-30,110,27 ) );

          quit = new JButton( "EXIT" ) ;
          quit.setBackground ( Color.cyan );
          quit.setMnemonic('X');
          quit.setForeground( Color .black );
          quit.setBorder ( new BevelBorder (0));
          cmppn1.add ( quit, new AbsoluteConstraints( 483-56-50,320-50-30,110,27 ) );

          setVisible(true);

       }

       private void componentListener()
       {
         but3.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               if(columnsParam == null)
                 {
                      columnsParam = "ACCESSNO";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"ACCESSNO";
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
                      columnsParam = "MZNAME";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"MZNAME";
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
                      columnsParam = "VOLUME";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"VOLUME";
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
                      columnsParam = "MZCODE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"MZCODE";
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
                      columnsParam = "SOURCE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"SOURCE";
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
                      columnsParam = "PAGES";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"PAGES";
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
                      columnsParam = "SUPPLEMENT";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"SUPPLEMENT";
                 }
            }
          }
          );
          but10.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "CDS";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"CDS";
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
                      columnsParam = "PRICE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"PRICE";
                 }
            }
          }
          );
         but12.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "PCITY";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"PCITY";
                 }
            }
          }
          );
          but13.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                if(columnsParam == null)
                 {
                      columnsParam = "PERIOD";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"PERIOD";
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
                      columnsParam = "REMARKS";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+"REMARKS";
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
                      columnsParam = " TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE ";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE ";
                 }

             }
         }
         );
         but20.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 if(columnsParam == null)
                 {
                      columnsParam = " TO_CHAR(RDATE,'DD-MON-YYYY') RECEIVE_DATE ";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" TO_CHAR(RDATE,'DD-MON-YYYY') RECEIVE_DATE ";
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


                          rs = s.executeQuery( "SELECT DISTINCT(MZNAME) FROM MZDETAILS WHERE MZNAME LIKE "+"'"+title.getText().toUpperCase()+"%"+"'" );
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
            aut2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                           rs = s.executeQuery( "SELECT DISTINCT(SOURCE) FROM MZDETAILS WHERE SOURCE LIKE "+"'"+supply.getText().toUpperCase()+"%"+"'" );
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
                                constraints=constraints+" ACCESSNO = "+"'"+number+"'"+" "+(String)box1.getSelectedItem();
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
                      if( edt1.getText()==null || edt1.getText().equals("") ) { }
                      else
                        {
                           vol = edt1.getText().toUpperCase();
                           if( item2 > 0 )
                             {
                                constraints=constraints+" VOLUME = "+"'"+vol+"'"+" "+(String)box2.getSelectedItem();
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
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" "+(String)box3.getSelectedItem();
                                 }
                             }
                        }
                      else
                        {
                           if( item3 > 0 )
                                 {
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" "+(String)box3.getSelectedItem();
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
                           mzcode = aut1.getText().toUpperCase();
                           if( item4 > 0 )
                             {
                                constraints=constraints+" MZCODE = "+"'"+mzcode+"'"+" "+(String)box4.getSelectedItem();
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
                      SOP=(String)aut1f1.getSelectedItem();
                      if(SOP==null || SOP .equals(""))
                        {
                           SOP = aut2.getText().toUpperCase();
                           if(SOP==null || SOP .equals("")) {}
                           else
                             {
                                if( item5 > 0 )
                                 {
                                    constraints=constraints+" SOURCE = "+"'"+SOP+"'"+" "+(String)box5.getSelectedItem();
                                 }
                             }
                        }
                      else
                        {
                            if( item5 > 0 )
                                 {
                                    constraints=constraints+" SOURCE = "+"'"+SOP+"'"+" "+(String)box5.getSelectedItem();
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
                      if( aut2s1.getSelectedItem()==null || aut2s1.getSelectedItem().equals("") ) { }
                      else
                        {
                           SUP=((String)aut2s1.getSelectedItem()).toUpperCase();
                           if( item6 > 0 )
                             {
                                constraints=constraints+" SUPPLEMENT = "+"'"+SUP+"'"+" "+(String)box6.getSelectedItem();
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
                      if( aut2f1.getSelectedItem()==null || aut2f1.getSelectedItem().equals("") ) { }
                      else
                        {
                           COM=((String)aut2f1.getSelectedItem()).toUpperCase();
                           if( item7 > 0 )
                             {
                                constraints=constraints+" CDS = "+"'"+COM+"'"+" "+(String)box7.getSelectedItem();
                             }
                        }
                      count7++;
                    }
                }
           }
         );
         box8.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count8 < 1 )
                    {
                      item8++;
                      if( aut3s1.getSelectedItem()==null || aut3s1.getSelectedItem().equals("") ) { }
                      else
                        {
                           PCITY=((String)aut3s1.getSelectedItem()).toUpperCase();
                           if( item8 > 0 )
                             {
                                constraints=constraints+" PCITY = "+"'"+PCITY+"'"+" "+(String)box8.getSelectedItem();
                             }
                        }
                      count8++;
                    }
                }
           }
         );
         box9.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count9 < 1 )
                    {
                      item9++;
                      if( aut6.getText()==null || aut6.getText().equals("") ) { }
                      else
                        {
                           PERIOD = aut6.getText().toUpperCase();
                           if( item9 > 0 )
                             {
                                constraints=constraints+" PERIOD = "+"'"+PERIOD+"'"+" "+(String)box9.getSelectedItem();
                             }
                        }
                      count9++;
                    }
                }
           }
         );
         box10.addItemListener(new ItemListener()
           {
              public void itemStateChanged( ItemEvent e )
                {
                  if( count10 < 1 )
                    {
                      item10++;
                      if( recieve1.getText()==null || recieve1.getText().equals("") ) { }
                      else
                        {
                           REC = recieve1.getText().toUpperCase();
                           if( item10 > 0 )
                             {
                                constraints=constraints+" RDATE = "+"TO_CHAR(TO_DATE('"+REC+"'),'DD-MON-YYYY')"+" "+(String)box10.getSelectedItem();
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
                        if( note1.getText()==null || note1.getText().equals("") ) { }
                        else
                          {
                             NOTE = note1.getText().toUpperCase();
                             if( item10 > 0 )
                               {
                                  constraints=constraints+" ADATE = "+"TO_CHAR(TO_DATE('"+NOTE+"'),'DD-MON-YYYY')"+" "+(String)box10.getSelectedItem();
                               }
                          }
                        count11++;
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
                           if( item1 > 0 ) {}
                           else
                             {
                                constraints=constraints+" ACCESSNO = "+"'"+number+"'"+" ";
                             }
                        }

                if( edt1.getText()==null || edt1.getText().equals("") ) { }
                      else
                        {
                           vol = edt1.getText().toUpperCase();
                           if( item2 > 0 ) {}
                           else
                             {
                                constraints=constraints+" VOLUME = "+"'"+vol+"'"+" ";
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
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" ";
                                 }
                             }
                        }
                      else
                        {
                           if( item3 > 0 ) {}
                               else
                                 {
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" ";
                                 }
                        }

                if( aut1.getText()==null || aut1.getText().equals("") ) { }
                      else
                        {
                           mzcode = aut1.getText().toUpperCase();
                           if( item4 > 0 ) {}
                           else
                             {
                                constraints=constraints+" MZCODE = "+"'"+mzcode+"'"+" ";
                             }
                        }


                           SOP = (String)aut1f1.getSelectedItem();
                           if(SOP==null || SOP .equals(""))
                             {
                                SOP = aut2.getText().toUpperCase();
                                if(SOP==null || SOP .equals("")) {}
                                else
                                  {
                                     if( item5 > 0 ) {}
                                     else
                                       {
                                          constraints=constraints+" SOURCE = "+"'"+SOP+"'"+" ";
                                          //JOptionPane.showMessageDialog(null,constraints);
                                       }
                                  }
                             }
                           else
                             {
                                if( item5 > 0 ) {}
                                     else
                                       {
                                          constraints=constraints+" SOURCE = "+"'"+SOP+"'"+" ";
                                       }
                             }

                if( aut2s1.getSelectedItem()==null || aut2s1.getSelectedItem().equals("") ) { }
                      else
                        {
                           SUP=((String)aut2s1.getSelectedItem()).toUpperCase();
                           if( item6 > 0 ) {}
                           else
                             {
                                constraints=constraints+" SUPPLEMENT = "+"'"+SUP+"'"+" ";
                             }
                        }


                if( aut2f1.getSelectedItem()==null || aut2f1.getSelectedItem().equals("") ) { }
                      else
                        {
                           COM=((String)aut2f1.getSelectedItem()).toUpperCase();
                           if( item7 > 0 ) {}
                           else
                             {
                                constraints=constraints+" CDS = "+"'"+COM+"'"+" ";
                             }
                        }


                if( aut3s1.getSelectedItem()==null || aut3s1.getSelectedItem().equals("") ) { }
                      else
                        {
                           PCITY=((String)aut3s1.getSelectedItem()).toUpperCase();
                           if( item8 > 0 ) {}
                           else
                             {
                                constraints=constraints+" PCITY = "+"'"+PCITY+"'"+" ";
                             }
                        }


                if( aut6.getText()==null || aut6.getText().equals("") ) { }
                      else
                        {
                           PERIOD = aut6.getText().toUpperCase();
                           if( item9 > 0 ) {}
                           else
                             {
                                constraints=constraints+" PERIOD = "+"'"+PERIOD+"'"+" ";
                             }
                        }


                if( recieve1.getText()==null || recieve1.getText().equals("") ) { }
                      else
                        {
                           REC = recieve1.getText().toUpperCase();
                           if( item10 > 0 ) {}
                           else
                             {
                                constraints=constraints+" RDATE = "+"TO_CHAR(TO_DATE('"+REC+"'),'DD-MON-YYYY')"+" ";
                             }
                        }

                if( note1.getText()==null || note1.getText().equals("") ) { }
                        else
                          {
                             NOTE = note1.getText().toUpperCase();
                             if( item10 > 0 ) {}
                             else
                               {
                                  constraints=constraints+" ADATE = "+"TO_CHAR(TO_DATE('"+NOTE+"'),'DD-MON-YYYY')"+" ";
                               }
                          }


                try
                {
                    s=con.createStatement();
                    if(columnsParam != null)
                    {
                            s=con.createStatement();
                            if(constraints.equals(""))
                             {
                               rs=s.executeQuery("SELECT  "+columnsParam+"  FROM MZDETAILS ");
                               if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY  !  . 12 .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                            else
                             {
                                rs=s.executeQuery("SELECT  "+columnsParam+"  FROM MZDETAILS WHERE "+constraints);
                                if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  . 13 .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                             s.close();
                     }
                    else
                     {
                            s=con.createStatement();
                            if(constraints.equals(""))
                             {

                               rs=s.executeQuery("SELECT ACCESSNO,MZNAME,MZCODE ISSUE_NO,VOLUME,SOURCE,SUPPLEMENT,CDS,PCITY,PERIOD,REMARKS,TO_CHAR(RDATE,'DD-MON-YYYY') RECIEVE_DATE,TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE  FROM MZDETAILS ");
                               //rs1=s.executeQuery("SELECT *  FROM MZDETAILS ");
                               if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY  !  .  14.  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                            else
                             {
                                JOptionPane.showMessageDialog(null,constraints);
                                rs=s.executeQuery("SELECT ACCESSNO,MZNAME,MZCODE ISSUE_NO,VOLUME,SOURCE,SUPPLEMENT,CDS,PCITY,PERIOD,REMARKS,TO_CHAR(RDATE,'DD-MON-YYYY') RECIEVE_DATE,TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE  FROM MZDETAILS WHERE "+constraints);
                                if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  . 15 .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
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

         ins.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
               try
               {
                    s=con.createStatement();
                    rs=s.executeQuery("SELECT ACCESSNO,MZNAME,MZCODE ISSUE_NO,VOLUME,SOURCE,SUPPLEMENT,CDS,PCITY,PERIOD,REMARKS,TO_CHAR(RDATE,'DD-MON-YYYY') RECEIVE_DATE,TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE  FROM MZDETAILS WHERE STATUS IS NOT NULL");
                    if(rs.next())
                    {
                     getTable(rs);
                    }
                    else
                    {
                      JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY  !  .  .  .");
                    }
               }
               catch(SQLException sqlex)
               {
                        JOptionPane.showMessageDialog(null,sqlex.getMessage());
                        sqlex.printStackTrace();
               }
                 search.setVisible(false);
                 print.setVisible(true);
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
                                                       new MessageFormat("ARCHIVE MAGAZINE'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("ARCHIVE MAGAZINE'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
                title.setText("");
                edt1.setText("");
                aut1.setText("");
                aut2.setText("");
                aut2s1.setSelectedItem("");
                aut2f1.setSelectedItem("");
                aut3s1.setSelectedItem("");
                aut6.setText("");
                recieve1.setText("");
                note1.setText("");


                aut1f1.setVisible(false);
                bookname1.setVisible(false);

                title.setVisible(true);
                aut2.setVisible(true);

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

                item1=0;
                item2=0;
                item3=0;
                item4=0;
                item5=0;
                item6=0;
                item7=0;
                item8=0;
                item9=item10=item11=0;
                count1=0;count2=0;
                count3=0;count4=0;
                count5=0;count6=0;
                count7=0;count8=0;
                count9=0;
                count10=0;count11=0;

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
          new ArchiveMzsSearch();
       }
  }