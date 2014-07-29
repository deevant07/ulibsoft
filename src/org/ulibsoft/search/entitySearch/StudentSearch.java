package org.ulibsoft.search.entitySearch;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.print.*;
import java.text.MessageFormat;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;
public class StudentSearch extends JFrame
  {
     private Container c;
     private JButton but1,but2,but3,but4,but5,but6,but7;
     private JButton but8,but9,but10,print;
     private JLabel but0;
     private JPanel cmppn,cmppn1,cmppn2;
     private JTable sttable;

     private JLabel adno, name, branch, year, doj, sts, batch, image, to ;
     private Icon icon,icon1;
     private JTextField  no, n1,d1,batch1 ,to1;
     private JComboBox b1,yr,sts1;
     private JButton ins, can, next ;
     private JComboBox box1,box2,box3,box4,box5,box6,box7;

     private String columnsParam = null;
     private String number,sname,sbranch,syear,sjyear,sdoj,ssts,sbatch1,sbatch2;
     private String constraints="";
     private int item1=0,item2=0,item3=0,item4=0,item5=0,item6=0,item7=0,item8=0;
     private int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0;

     private int priority =0;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs, rs1 ;


     public StudentSearch()
       {
           super("STUDENT  DATABASE  SEARCH  FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

           createComponents();
           inputConstraints();
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
                                            " CHOOSE  FIELDS  TO  VIEW", 2, 2, c.getFont(), Color.magenta));
           cmppn.setBackground(c.getBackground());
           c.add(cmppn,new AbsoluteConstraints(5,70,185,245));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,335,780,215));

           but0 = new JLabel( "    SEARCH  STUDENT  DATABASE" ) ;
           but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
           but0.setBackground ( Color.pink );
           but0.setForeground(Color.black);
           c.add ( but0, new AbsoluteConstraints( 260+30, 20, 200, 30 ) );

           but1 = new JButton( "" ) ;
           but1.setBackground ( Color.cyan );
           but1.setForeground(Color.black);
           but1.setEnabled(false);
           but1.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but1, new AbsoluteConstraints( 260+30+0, 20, 200, 30 ) );

           but2 = new JButton( "" ) ;
           but2.setBackground ( Color.pink );
           but2.setForeground(Color.black);
           but2.setEnabled(false);
           but2.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but2, new AbsoluteConstraints( 267+30+0, 27, 200, 30 ) );

           but3 = new JButton("ADMISSION  NUMBER");
           but3.setBackground(new Color(0,0,50));
           but3.setForeground(Color.cyan);
           but3.setBorder(new BevelBorder(0));
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20+10, 180-15, 30 ) );

           but4 = new JButton("STUDENT  NAME");
           but4.setBackground(new Color(0,0,50));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           cmppn.add ( but4, new AbsoluteConstraints( 10, 50+10, 180-15, 30 ) );

           but5 = new JButton("STUDENT  COURSE");
           but5.setBackground(new Color(0,0,50));
           but5.setBorder(new BevelBorder(0));
           but5.setForeground(Color.cyan);
           cmppn.add ( but5, new AbsoluteConstraints( 10, 80+10, 180-15, 30 ) );

           but6 = new JButton("STUDENT  SEMISTER");
           but6.setBackground(new Color(0,0,50));
           but6.setForeground(Color.cyan);
           but6.setBorder(new BevelBorder(0));
           cmppn.add ( but6, new AbsoluteConstraints( 10, 110+10, 180-15, 30 ) );

           but7 = new JButton("STUDENT  BATCH");
           but7.setBackground(new Color(0,0,50));
           but7.setForeground(Color.cyan);
           but7.setBorder(new BevelBorder(0));
           cmppn.add ( but7, new AbsoluteConstraints( 10, 150, 180-15, 30 ) );

           but8 = new JButton("DATE  OF  JOIN");
           but8.setBackground(new Color(0,0,50));
           but8.setForeground(Color.cyan);
           but8.setBorder(new BevelBorder(0));
           cmppn.add ( but8, new AbsoluteConstraints( 10, 170+10, 180-15, 30 ) );

           but9 = new JButton("STUDENT  STATUS");
           but9.setBackground(new Color(0,0,50));
           but9.setForeground(Color.cyan);
           but9.setBorder(new BevelBorder(0));
           cmppn.add ( but9, new AbsoluteConstraints( 10, 200+10, 180-15, 30 ) );


           setVisible(true);
       }


     private void inputConstraints()
       {
           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(190,70,595,245));

         //INPUT CONSTRAINTS FOR STUDENT DETAILS

            adno = new JLabel ( "ADMISSION-NO" );
            adno.setForeground ( new Color ( 120, 120, 153 ) );
            adno.setHorizontalAlignment ( SwingConstants.RIGHT  );
            cmppn1.add( adno, new AbsoluteConstraints(25+10-20 , 21+5+30) );

            no = new JTextField ( );
            no.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
            no.setForeground ( new Color ( 255, 0, 153 ) );
            no.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add (no,new AbsoluteConstraints( 115+6+10-20, 20+5+30, 135, 20 ) );

            box1 = new JComboBox();
            box1.setForeground( new Color( 255, 0, 153 ));
            box1.setBackground(Color.white);
            box1.addItem("");
            box1.addItem("AND");
            box1.addItem("OR");
            cmppn1.add (box1,new AbsoluteConstraints( 250, 56, 45 , 20 ) );

            box2 = new JComboBox();
            box2.setForeground( new Color( 255, 0, 153 ));
            box2.setBackground(Color.white);
            box2.addItem("");
            box2.addItem("AND");
            box2.addItem("OR");
            cmppn1.add (box2,new AbsoluteConstraints( 540, 54, 45 , 20 ) );

            box3 = new JComboBox();
            box3.setForeground( new Color( 255, 0, 153 ));
            box3.setBackground(Color.white);
            box3.addItem("");
            box3.addItem("AND");
            box3.addItem("OR");
            cmppn1.add (box3,new AbsoluteConstraints( 250, 86, 45 , 20 ) );

            box4 = new JComboBox();
            box4.setForeground( new Color( 255, 0, 153 ));
            box4.setBackground(Color.white);
            box4.addItem("");
            box4.addItem("AND");
            box4.addItem("OR");
            cmppn1.add (box4,new AbsoluteConstraints( 540, 84, 45 , 20 ) );

            box5 = new JComboBox();
            box5.setForeground( new Color( 255, 0, 153 ));
            box5.setBackground(Color.white);
            box5.addItem("");
            box5.addItem("AND");
            box5.addItem("OR");
            cmppn1.add (box5,new AbsoluteConstraints( 250, 116, 45 , 20 ) );

            box6 = new JComboBox();
            box6.setForeground( new Color( 255, 0, 153 ));
            box6.setBackground(Color.white);
            box6.addItem("");
            box6.addItem("AND");
            box6.addItem("OR");
            cmppn1.add (box6,new AbsoluteConstraints( 540, 114, 45 , 20 ) );

            box7 = new JComboBox();
            box7.setForeground( new Color( 255, 0, 153 ));
            box7.setBackground(Color.white);
            box7.addItem("");
            box7.addItem("AND");
            box7.addItem("OR");
            cmppn1.add (box7,new AbsoluteConstraints( 540, 144, 45 , 20 ) );

            name = new JLabel ( "STD - NAME" );
            name.setForeground ( new Color ( 120, 120, 153 ) );
            //name.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( name, new AbsoluteConstraints( 319, 21+5+30 ) );

            n1 = new JTextField ( );
            n1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
            n1.setForeground ( new Color ( 255, 0, 153 ) );
            n1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( n1, new AbsoluteConstraints( 395, 20+5+30, 135, 20 ) );

            branch = new JLabel ( "COURSE" );
            branch.setForeground ( new Color ( 120, 120, 153 ) );
            cmppn1.add ( branch, new AbsoluteConstraints( 57+16-20, 51+5+30 ) );

            b1 = new JComboBox ( );
            b1.setBackground(Color.white);
            //b1.setEditable(true);
            b1.addItem("");
            b1.addItem ("CSE");
            b1.addItem ("CSIT");
            b1.addItem ("ECE");
            b1.addItem ("EEE");
            b1.addItem ("MEC");
            b1.addItem ("AE");
            b1.addItem ("AEI");
            b1.addItem ("ARC");
            b1.addItem ("ACM");
            b1.addItem ("BME");
            b1.addItem ("BT");
            b1.addItem ("CBE");
            b1.addItem ("CEE");
            b1.addItem ("CHE");
            b1.addItem ("CIV");
            b1.addItem ("CPE");
            b1.addItem ("CSS");
            b1.addItem ("DT");
            b1.addItem ("ECM");
            b1.addItem ("ECS");
            b1.addItem ("ECSE");
            b1.addItem ("ETM");
            b1.addItem ("EIE");
            b1.addItem ("FPT");
            b1.addItem ("FT");
            b1.addItem ("ICE");
            b1.addItem ("INE");
            b1.addItem ("IPE");
            b1.addItem ("IST");
            b1.addItem ("MCT");
            b1.addItem ("MET");
            b1.addItem ("MIN");
            b1.addItem ("MMC");
            b1.addItem ("MMD");
            b1.addItem ("MMT");
            b1.addItem ("NVA");
            b1.addItem ("PHM");
            b1.addItem ("PTG");
            b1.addItem ("PLG");
            b1.addItem ("SCE");
            b1.addItem ("TEX");
           //b1.setBackground( new Color( 0, 0, 40 ));
            b1.setForeground( new Color( 255, 0, 153 ));
            cmppn1.add ( b1, new AbsoluteConstraints( 115+16-20,50+5+30,135,20 ));

            year = new JLabel( "SEMISTER" );
            year.setForeground ( new Color ( 120, 120, 153 ) );
            year.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( year, new AbsoluteConstraints( 322,51+5+30 ) );

            yr = new JComboBox();
            yr.setBackground(Color.white);
            //yr.setEditable(true);
            yr.addItem("");
            yr.addItem ( "I" );
            yr.addItem ( "II-1" );
            yr.addItem ( "II-2" );
            yr.addItem ( "III-1" );
            yr.addItem ( "III-2" );
            yr.addItem( "IV-1" );
            yr.addItem ( "IV-2" );
           //yr.setBackground(new Color(0,0,40));
            yr.setForeground(new Color(255, 0, 153));
            cmppn1.add ( yr, new AbsoluteConstraints( 395,50+5+30,135,20 ) );

            doj = new JLabel ( "DATE-OF-JOIN" );
            doj.setForeground ( new Color ( 120, 120, 153 ) );
            doj.setHorizontalAlignment ( SwingConstants.RIGHT  );
            cmppn1.add ( doj, new AbsoluteConstraints( 25+16-20, 81+5+30 ) );

            d1 = new JTextField ( );
            d1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            d1.setForeground ( new Color ( 255, 0, 153 ) );
            d1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( d1, new AbsoluteConstraints( 115+16-20, 80+5+30,135, 20 ) );

            sts = new JLabel("STATUS");
            sts.setForeground( new Color( 120, 120, 153 ) );
            sts.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( sts, new AbsoluteConstraints( 338,81+5+30 ) );

            sts1 = new JComboBox();
            sts1.setBackground(Color.white);
            sts1.setForeground ( new Color ( 255, 0, 153 ) );
            sts1.setEditable(false);
            sts1.addItem("");
            sts1.addItem("TRUE");
            sts1.addItem("FALSE");
            cmppn1.add(sts1, new AbsoluteConstraints( 395,80+5+30,135,20 ) );


            batch = new JLabel();
            batch.setForeground( new Color( 120, 120, 153 ) );
            batch.setHorizontalAlignment ( SwingConstants.RIGHT );
            batch.setText("BATCH  FROM :");
            cmppn1.add ( batch, new AbsoluteConstraints( 42-20,111+5+30 ) );

            batch1 = new JTextField ( );
            batch1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            batch1.setForeground ( new Color ( 255, 0, 153 ) );
            batch1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( batch1, new AbsoluteConstraints( 115+16-20, 111+5+29,185, 20 ) );

            to = new JLabel("TO  :");
            to.setForeground( new Color( 120, 120, 153 ) );
            to.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( to, new AbsoluteConstraints( 360,111+5+30 ) );

            to1 = new JTextField ( );
            to1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            to1.setForeground ( new Color ( 255, 0, 153 ) );
            to1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( to1, new AbsoluteConstraints( 395, 111+5+29,135, 20 ) );

           //BUTTON CREATION
            ins = new JButton( "SEARCH" ) ;
            ins.setBackground (Color.cyan);
            ins.setForeground(Color.black);
            ins.setBorder(new BevelBorder(0));
            ins.setMnemonic('S');
            cmppn1.add ( ins, new AbsoluteConstraints( 123+5-40, 150+40, 105, 27 ) );

            print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setEnabled(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 123+5+67, 150+40, 105, 27 ) );

            next = new JButton( "NEXT>>>" ) ;
            next.setBackground ( Color.cyan);
            next.setForeground(Color.black);
            next.setMnemonic('N');
            next.setBorder(new BevelBorder(0));
            cmppn1.add ( next, new AbsoluteConstraints( 229+1+5+67, 150+40, 105, 27 ) );

            can = new JButton( "EXIT" ) ;
            can.setBackground ( Color.cyan);
            can.setForeground( Color.black);
            can.setMnemonic('X');
            can.setBorder(new BevelBorder(0));
            cmppn1.add ( can, new AbsoluteConstraints( 335+2+5+67, 150+40, 105, 27 ) );

            setVisible(true);
     }


     private void componentListener()
       {
          but3.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    if( columnsParam==null )
                      {
                         columnsParam = "ADNO";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+"ADNO";
                      }
                 }
            }
          );
          but4.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    if( columnsParam==null )
                      {
                         columnsParam = "SNAME";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" SNAME";
                      }
                 }
            }
          );
          but5.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     if( columnsParam==null )
                      {
                         columnsParam = "BRANCH";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" BRANCH";
                      }
                 }
            }
          );
          but6.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     if( columnsParam==null )
                      {
                         columnsParam = "YEAR";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" YEAR";
                      }
                 }
            }
          );
          but7.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     if( columnsParam==null )
                      {
                         columnsParam = "TO_CHAR(DATEOFJOIN,'DD-MON-YYYY') DATEOFJOIN";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" TO_CHAR(DATEOFJOIN,'DD-MON-YYYY') DATEOFJOIN ";
                      }
                 }
            }
          );
          but8.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     if( columnsParam==null )
                      {
                         columnsParam = "YEAROFJOIN";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" YEAROFJOIN";
                      }
                 }
            }
          );
          but9.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     if( columnsParam==null )
                      {
                         columnsParam = "STATUS";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" STATUS";
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
                           if( no.getText()==null || no.getText().equals("") ) { }
                         else
                           {
                               number = no.getText().toUpperCase();
                               item1++;
                               if( item1 > 0 )
                                 {
                                     constraints=constraints+" ADNO = "+"'"+number+"'"+" "+(String)box1.getSelectedItem();
                                 }
                              count1++;
                           }
                        }


                   }
             }
          );
          box2.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if(count2 <1)
                        {
                           if( n1.getText()==null || n1.getText().equals("") ) { }
                           else
                           {
                               sname = n1.getText().toUpperCase();
                                item2++;

                               if( item2 > 0 )
                                 {
                                     constraints=constraints+" SNAME = "+"'"+sname+"'"+" "+(String)box2.getSelectedItem();
                                 }
                              count2++;
                           }
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
                           if( b1.getSelectedItem()==null || b1.getSelectedItem().equals("") ) { }
                         else
                           {
                              sbranch=((String)b1.getSelectedItem()).toUpperCase();
                              item3++;

                              if( item3 > 0 )
                                 {
                                     constraints=constraints+" BRANCH = "+"'"+sbranch+"'"+" "+(String)box3.getSelectedItem();
                                 }
                              count3++;
                           }
                       }
                   }
             }
          );
          box4.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if( count4<1)
                        {
                            if( yr.getSelectedItem()== null || yr.getSelectedItem().equals("") ){ }
                         else
                           {
                              syear = ((String)yr.getSelectedItem()).toUpperCase();
                              item4++;

                              if( item4 > 0 )
                                 {
                                     constraints=constraints+" YEAR= "+"'"+syear+"'"+" "+(String)box4.getSelectedItem();
                                 }
                              count4++;
                           }
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
                           if( d1.getText()==null || d1.getText().equals("") ) { }
                           else
                           {
                              sdoj = d1.getText().toUpperCase();
                              item5++;

                              if( item5 > 0 )
                                 {
                                     constraints=constraints+"DATEOFJOIN ="+"TO_CHAR(TO_DATE('"+sdoj+"'),'DD-MON-YYYY')"+" "+(String)box5.getSelectedItem();
                                 }
                              count5++;
                           }
                        }
                   }
             }
          );
          box6.addItemListener(new ItemListener()
             {
                public void itemStateChanged(ItemEvent e)
                   {
                      if( count6 < 1 )
                        {

                         if( sts1.getSelectedItem()==null || sts1.getSelectedItem().equals("") ) { }
                         else
                           {
                              ssts = ((String)sts1.getSelectedItem()).toUpperCase();

                              if( item6 > 0 )
                                 {
                                     constraints=constraints+" STATUS= "+"'"+ssts+"'"+" "+(String)box6.getSelectedItem();
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
                      if( item7>=0 )
                        {
                           to_Year();
                           count7++;
                        }
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
                         //constraints="";
                         s = con.createStatement();

                         if( no.getText()==null || no.getText().equals("") ) { }
                         else
                           {
                               number = no.getText().toUpperCase();

                               if( item1 > 0 )
                                 {
                                 }
                               else
                                 {
                                     constraints=constraints+" ADNO = "+"'"+number+"'";
                                 }
                           }

                         if( n1.getText()==null || n1.getText().equals("") ) { }
                         else
                           {
                               sname = n1.getText().toUpperCase();

                               if( item2 > 0 )
                                 {
                                 }
                               else
                                 {
                                     constraints=constraints+" SNAME = "+"'"+sname+"'";
                                 }
                           }


                         if( b1.getSelectedItem()==null || b1.getSelectedItem().equals("") ) { }
                         else
                           {
                              sbranch=((String)b1.getSelectedItem()).toUpperCase();

                              if( item3 > 0 )
                                 {
                                 }
                               else
                                 {
                                     constraints=constraints+" BRANCH = "+"'"+sbranch+"'";
                                 }
                           }

                         if( yr.getSelectedItem()== null || yr.getSelectedItem().equals("") ){ }
                         else
                           {
                              syear = ((String)yr.getSelectedItem()).toUpperCase();

                              if( item4 > 0 )
                                 {
                                 }
                               else
                                 {
                                     constraints=constraints+" YEAR = "+"'"+syear+"'";
                                 }
                           }

                         if( d1.getText()==null || d1.getText().equals("") ) { }
                         else
                           {
                              sdoj = d1.getText().toUpperCase();

                              if( item5 > 0 )
                                 {
                                 }
                               else
                                 {
                                     constraints=constraints+"DATEOFJOIN ="+"TO_CHAR(TO_DATE('"+sdoj+"'),'DD-MON-YYYY')";
                                 }
                           }


                         if( sts1.getSelectedItem()==null || sts1.getSelectedItem().equals("") ) { }
                         else
                           {
                              ssts = ((String)sts1.getSelectedItem()).toUpperCase();

                              if( item6 > 0 )
                                 {
                                 }
                               else
                                 {
                                     constraints=constraints+" STATUS= "+"'"+ssts+"'";
                                 }
                           }


                         if( batch1.getText() == null || batch1.getText().equals("") ) {}
                         else {   sbatch1 = batch1.getText().toUpperCase();  }

                         if( to1.getText()==null||to1.getText().equals("") )
                           {
                              sbatch2="";
                              if(sbatch2.equals(""))
                                {
                                   if( sbatch1==null || sbatch1.equals("") ){}
                                   else  {  sbatch2=sbatch1; }
                                }
                           }
                         else  {  sbatch2 = to1.getText().toUpperCase();   }


                         if( sbatch1==null||sbatch1.equals("")){}
                         else
                           {
                              if( item7 > 0){}
                              else { constraints=constraints+"YEAROFJOIN BETWEEN ('"+sbatch1+"')AND('"+sbatch2+"')";  }
                           }

                           if(columnsParam!=null)
                            {
                               if(constraints.equals(""))
                                 {
                                    rs = s.executeQuery("SELECT "+columnsParam+" FROM STUDENTDETAILS ");

                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STUDENT  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                               else
                                 {
                                    //JOptionPane.showMessageDialog(null,"  11  "+constraints);
                                    rs = s.executeQuery("SELECT "+columnsParam+" FROM STUDENTDETAILS WHERE "+constraints);

                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STUDENT  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                            }
                          else
                            {
                               //JOptionPane.showMessageDialog(null,constraints);
                               if(constraints.equals(""))
                                 {
                                    rs = s.executeQuery(" SELECT ADNO,SNAME,BRANCH,YEAR,STATUS,TO_CHAR(DATEOFJOIN,'DD-MON-YYYY') DATEOFJOIN,YEAROFJOIN,TO_CHAR(CDATE,'DD-MON-YYYY')  CERTIFIEDDATE  FROM STUDENTDETAILS ");

                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STUDENT  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                               else
                                 {
                                    rs = s.executeQuery("SELECT ADNO,SNAME,BRANCH,YEAR,STATUS,TO_CHAR(DATEOFJOIN,'DD-MON-YYYY') DATEOFJOIN,YEAROFJOIN,TO_CHAR(CDATE,'DD-MON-YYYY')  CERTIFIEDDATE FROM STUDENTDETAILS WHERE "+constraints);

                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STUDENT  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                            }

                      }
                    catch(SQLException sqlex)
                      {
                         JOptionPane.showMessageDialog(null,sqlex.getMessage() );
                      }
                      //ins.setEnabled(false);
                      print.setEnabled(true);

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
                                                       new MessageFormat("STUDENT-RECORD'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT-RECORD'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
                   ins.setEnabled(true);
                   no.setText("");
                   n1.setText("");
                   d1.setText("");
                   batch1.setText("");
                   to1.setText("");
                   b1.setSelectedItem("");
                   sts1.setSelectedItem("");
                   yr.setSelectedItem("");

                   columnsParam=null;
                   constraints="";
                   print.setEnabled(false);

                   box1.setSelectedItem("");
                   box2.setSelectedItem("");
                   box3.setSelectedItem("");
                   box4.setSelectedItem("");
                   box5.setSelectedItem("");
                   box6.setSelectedItem("");
                   box7.setSelectedItem("");

                   item1=0;   count1=0;
                   item2=0;   count2=0;
                   item3=0;   count3=0;
                   item4=0;   count4=0;
                   item5=0;   count5=0;
                   item6=0;   count6=0;
                   item7=0;   count7=0;
                 }
            }
          );
          can.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {

                   setVisible(false);
                 }
            }
          );
       }

     private void to_Year()
       {
         if( batch1.getText() == null || batch1.getText().equals("") ) {}
         else {  sbatch1 = batch1.getText().toUpperCase();  }

         if( to1.getText()==null||to1.getText().equals("") )
           {
              sbatch2="";
              if(sbatch2.equals(""))
                {
                   if( sbatch1==null || sbatch1.equals("") ){}
                   else  {  sbatch2=sbatch1; }
                }
           }
         else {  sbatch2 = to1.getText().toUpperCase();  }

         if( sbatch1==null||sbatch1.equals("")){}
         else
           {
              constraints=constraints+"YEAROFJOIN BETWEEN ('"+sbatch1+"')AND('"+sbatch2+"')"+(String)box7.getSelectedItem();
           }
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
          new StudentSearch();
       }
  }