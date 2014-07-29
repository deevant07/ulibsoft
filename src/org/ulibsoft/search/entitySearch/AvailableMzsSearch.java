package org.ulibsoft.search.entitySearch;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;

import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class AvailableMzsSearch extends JFrame
  {
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

     private Container c;
     private JLabel but0;
     private JButton but1,but2,but3,but4,but5,but6,but7,but16,but17,but18;
     private JButton but8,but9,but10,but11,but12,but13,but14,but15,but19,but20;
     private JPanel cmppn,cmppn1,cmppn2;
     private JTable sttable;

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


     public AvailableMzsSearch()
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
           c.add(cmppn,new AbsoluteConstraints(75+30,65,200,240));

           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(277+30  ,65,380,240));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,410-60-40,780,220+40));

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
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20+20, 180, 27 ) );

           but4 = new JButton("TITLE ");
           but4.setBackground(new Color(0,0,80));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           but4.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but4, new AbsoluteConstraints( 10, 48+20, 180, 27 ) );

           but5 = new JButton("VOLUME");
           but5.setBackground(new Color(0,0,80));
           but5.setBorder(new BevelBorder(0));
           but5.setForeground(Color.cyan);
           but5.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but5, new AbsoluteConstraints( 10, 76+20, 180, 27 ) );

           but7 = new JButton("SOURCE OF SUPPLY");
           but7.setBackground(new Color(0,0,80));
           but7.setForeground(Color.cyan);
           but7.setBorder(new BevelBorder(0));
           but7.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but7, new AbsoluteConstraints( 10, 104+20, 180, 27 ) );

           but12 = new JButton("PERIOD");
           but12.setBackground(new Color(0,0,80));
           but12.setForeground(Color.cyan);
           but12.setBorder(new BevelBorder(0));
           but12.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but12, new AbsoluteConstraints( 10, 132+20, 180, 27 ) );

           but14 = new JButton("REMARKS");
           but14.setBackground(new Color(0,0,80));
           but14.setForeground(Color.cyan);
           but14.setBorder(new BevelBorder(0));
           but14.setFont( new Font( but3.getFont().getFontName(),Font.BOLD,9) );
           cmppn.add ( but14, new AbsoluteConstraints( 10, 160+20, 180, 27 ) );

           inputConstraints();
           setVisible(true);
       }

     private void inputConstraints()
       {

          acess = new JLabel( "ACCESSION  NUMBER" );
          acess.setFont(new Font(acess.getFont().getFontName(),Font.BOLD,11));
          acess.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( acess, new AbsoluteConstraints(20+10, 21+20) );

          acess1 = new JTextField( );
          acess1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          acess1.setForeground ( new Color ( 255, 0, 153 ) );
          acess1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( acess1, new AbsoluteConstraints( 140+10, 18+20, 150, 20 ) );

          bookname = new JLabel( "MAGAZINE TITLE  ==>" );
          bookname.setForeground ( new Color ( 120, 120, 153 ) );
          bookname.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,11));
          cmppn1.add ( bookname, new AbsoluteConstraints( 18+10, 48+22) );

          bookname1 = new JComboBox( );
          bookname1.setEditable(true);
          bookname1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          bookname1.setForeground ( new Color ( 255, 0, 153 ) );
          bookname1.setVisible(false);
          cmppn1.add ( bookname1, new AbsoluteConstraints( 140+10, 46+20, 150, 20 ) );

          title = new JTextField( );
          title.setEditable(true);
          title.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
          title.setForeground ( new Color ( 255, 0, 153 ) );
          title.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( title, new AbsoluteConstraints( 140+10, 46+20, 150, 20 ) );

          edt = new JLabel( "MAGAZINE  VOLUME :" );
          edt.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,11));
          edt.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( edt, new AbsoluteConstraints( 18+10, 98) );

          edt1 = new JTextField( );
          edt1.setEditable(true);
          edt1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0,0,40 ) ));
          edt1.setForeground ( new Color ( 255, 0, 153 ) );
          edt1.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( edt1, new AbsoluteConstraints( 140+10, 94, 150,20 ) );

          aut3f = new JLabel( "PERIOD  OF  MAG . . . :" );
          aut3f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,11));
          aut3f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut3f, new AbsoluteConstraints(20+10, 126 ) );

          aut6 = new JTextField( );
          aut6.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut6.setForeground ( new Color ( 255, 0, 153 ) );
          aut6.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut6, new AbsoluteConstraints( 140+10, 122, 150, 20) );

          aut1f = new JLabel( "SOURCE OF SUPPLY  :" );
          aut1f.setFont(new Font(bookname.getFont().getFontName(),Font.BOLD,11));
          aut1f.setForeground ( new Color ( 120, 120, 153 ) );
          cmppn1.add ( aut1f, new AbsoluteConstraints( 20+10, 153 ) );

          aut1f1 = new JComboBox( );
          aut1f1.setEditable(true);
          aut1f1.setVisible(false);
          aut1f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0, 0, 40) ));
          aut1f1.setForeground ( new Color ( 255, 0, 153 ) );
          cmppn1.add ( aut1f1, new AbsoluteConstraints( 140+10, 150, 150, 20) );

          aut2 = new JTextField( );
          aut2.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          aut2.setForeground ( new Color ( 255, 0, 153 ) );
          aut2.setCaretColor ( new Color ( 0, 204, 102 ) );
          cmppn1.add ( aut2, new AbsoluteConstraints( 140+10, 150, 150, 20) );

          box1 = new JComboBox();
          box1.setFont(new Font(box1.getFont().getFontName(),Font.BOLD,12));
          box1.setForeground( new Color( 255, 0, 153 ));
          box1.setBackground(Color.white);
          box1.addItem("");
          box1.addItem("OR");
          box1.addItem("AND");
          cmppn1.add (box1,new AbsoluteConstraints( 300+10, 19+20, 45 , 18 ) );

          box2 = new JComboBox();
          box2.setForeground( new Color( 255, 0, 153 ));
          box2.setBackground(Color.white);
          box2.addItem("");
          box2.addItem("OR");
          box2.addItem("AND");
          cmppn1.add (box2,new AbsoluteConstraints( 300+10, 17+50, 45 , 18 ) );

          box3 = new JComboBox();
          box3.setForeground( new Color( 255, 0, 153 ));
          box3.setBackground(Color.white);
          box3.addItem("");
          box3.addItem("OR");
          box3.addItem("AND");
          cmppn1.add (box3,new AbsoluteConstraints( 300+10, 39+28+27, 45 , 18 ) );

          box4 = new JComboBox();
          box4.addItem("");
          box4.setForeground( new Color( 255, 0, 153 ));
          box4.setBackground(Color.white);
          box4.addItem("OR");
          box4.addItem("AND");
          cmppn1.add (box4,new AbsoluteConstraints( 300+10, 123, 45 , 18 ) );

          box5 = new JComboBox();
          box5.setForeground( new Color( 255, 0, 153 ));
          box5.addItem("");
          box5.setBackground(Color.white);
          box5.addItem("OR");
          box5.addItem("AND");
          cmppn1.add (box5,new AbsoluteConstraints( 300+10, 150, 45 , 18 ) );

          ins = new JButton( "LOST" ) ;
          ins.setBackground ( Color.cyan  );
          ins.setForeground( Color .black );
          ins.setMnemonic('L');
          ins.setBorder ( new BevelBorder ( 0 ));
          cmppn1.add ( ins, new AbsoluteConstraints( 75-30+3, 210-5,110,23 ) );



          search = new JButton( "SEARCH" ) ;
          search.setBackground ( Color.cyan  );
          search.setForeground( Color .black );
          search.setMnemonic('S');
          search.setBorder ( new BevelBorder ( 0 ));
          cmppn1.add ( search, new AbsoluteConstraints( 187+30+3, 210-5,110,23 ) );

          print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setVisible(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 187+30+3, 210-5,110,23 ) );

          next = new JButton( "NEXT>>>" ) ;
          next.setBackground ( Color.cyan );
          next.setForeground(  Color .black );
          next.setMnemonic('N');
          next.setBorder ( new BevelBorder (0));
          cmppn1.add ( next, new AbsoluteConstraints( 75+3,180,110,23 ) );

          quit = new JButton( "EXIT" ) ;
          quit.setBackground ( Color.cyan );
          quit.setMnemonic('X');
          quit.setForeground( Color .black );
          quit.setBorder ( new BevelBorder (0));
          cmppn1.add ( quit, new AbsoluteConstraints( 187+3,180,110,23 ) );

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

         but12.addActionListener(new ActionListener()
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
                     // JOptionPane.showMessageDialog(null,"sdbcjsd");
                      try
                        {
                          s = con.createStatement();
                           rs = s.executeQuery( "SELECT DISTINCT(SOURCE) FROM MZDETAILS WHERE SOURCE LIKE "+"'"+aut2.getText().toUpperCase()+"%"+"'" );
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
                      TITLE=(String)bookname1.getSelectedItem();
                      if(TITLE==null || TITLE .equals(""))
                        {
                           TITLE = title.getText().toUpperCase();
                           if( TITLE==null || TITLE.equals("") )
                             {
                             }
                           else
                             {
                               if( item2 > 0 )
                                 {
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" "+(String)box2.getSelectedItem();
                                 }
                             }
                        }
                        else
                        {

                           if( item2 > 0 )
                                 {
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" "+(String)box2.getSelectedItem();
                                    //JOptionPane.showMessageDialog(null,constraints+"09876");
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
                      if( edt1.getText()==null || edt1.getText().equals("") ) { }
                      else
                        {
                           vol = edt1.getText().toUpperCase();
                           if( item3 > 0 )
                             {
                                constraints=constraints+" VOLUME = "+"'"+vol+"'"+" "+(String)box3.getSelectedItem();
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
                      if( aut6.getText()==null || aut6.getText().equals("") ) { }
                      else
                        {
                           PERIOD = aut6.getText().toUpperCase();
                           if( item4 > 0 )
                             {
                                constraints=constraints+" PERIOD = "+"'"+PERIOD+"'"+" "+(String)box4.getSelectedItem();
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

                TITLE=(String)bookname1.getSelectedItem();
                      if(TITLE==null || TITLE .equals(""))
                        {
                           TITLE = title.getText().toUpperCase();
                           if( TITLE==null || TITLE.equals("") )
                             {
                             }
                           else
                             {
                               if( item2 > 0 ) {}
                               else
                                 {
                                    constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" ";
                                 }
                             }
                        }
                      else
                        {
                           if( item2 > 0 ){}
                           else
                             {
                                constraints=constraints+" MZNAME = "+"'"+TITLE+"'"+" ";
                                //JOptionPane.showMessageDialog(null,constraints+"09876");
                             }
                        }

                if( edt1.getText()==null || edt1.getText().equals("") ) { }
                      else
                        {
                           vol = edt1.getText().toUpperCase();
                           if( item3 > 0 ) {}
                           else
                             {
                                constraints=constraints+" VOLUME = "+"'"+vol+"'"+" ";
                             }
                        }

                if( aut6.getText()==null || aut6.getText().equals("") ) { }
                      else
                        {
                           PERIOD = aut6.getText().toUpperCase();
                           if( item4 > 0 ) {}
                           else
                             {
                                constraints=constraints+" PERIOD = "+"'"+PERIOD+"'"+" ";
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

                try
                {
                    s=con.createStatement();
                    if(columnsParam != null)
                    {
                            s=con.createStatement();
                            if(constraints.equals(""))
                             {
                               rs=s.executeQuery("SELECT "+columnsParam+" FROM MZDETAILS ");
                               if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY  !  .  .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                            else
                             {
                                rs=s.executeQuery("SELECT "+columnsParam+" FROM MZDETAILS WHERE "+constraints);
                                if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                             s.close();
                     }
                    else
                     {
                            s=con.createStatement();
                            if(constraints.equals(""))
                             {
                               rs=s.executeQuery("SELECT ACCESSNO,MZNAME,MZCODE,VOLUME,SOURCE,SUPPLEMENT,CDS,PCITY,PERIOD,REMARKS,TO_CHAR(RDATE,'DD-MON-YYYY') RECIEVE_DATE,TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE   FROM MZDETAILS ");
                               if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY  !  .  .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                   }
                             }
                            else
                             {
                                JOptionPane.showMessageDialog(null,constraints);
                                rs=s.executeQuery("SELECT ACCESSNO,MZNAME,MZCODE,VOLUME,SOURCE,SUPPLEMENT,CDS,PCITY,PERIOD,REMARKS,TO_CHAR(RDATE,'DD-MON-YYYY') RECIEVE_DATE,TO_CHAR(ADATE,'DD-MON-YYYY') ACESSDATE   FROM MZDETAILS WHERE "+constraints);
                                if(rs.next())
                                   {
                                      getTable(rs);
                                   }
                                   else
                                   {
                                     JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY  !  .  .  .","MAGAZINES DATABASE",JOptionPane.INFORMATION_MESSAGE);
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
                                                       new MessageFormat("AVAILABLE MAGAZINE'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("AVAILABLE MAGAZINE'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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

         ins.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {

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
               // aut1.setText("");
                aut2.setText("");
              //  aut2s1.setSelectedItem("");
               // aut2f1.setSelectedItem("");
               // aut3s1.setSelectedItem("");
                aut6.setText("");
               // recieve1.setText("");
            //    note1.setText("");


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

                item1=0;
                item2=0;
                item3=0;
                item4=0;
                item5=0;

                count1=0;count2=0;
                count3=0;count4=0;
                count5=0;

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
          new AvailableMzsSearch();
       }
  }