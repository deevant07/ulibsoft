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

public class ArchiveCdTrans extends JFrame
  {
     private JLabel but0,sts;
     private JButton but1,print,but2,but3,but4,but5,but6,but7,but16,but17,but18;
     private JButton but8,but9,but10,but11,but12,but13,but14,but15,but19,but20;
     private JLabel adno,lid,bkno,cdno,mzno,idt,rdt,batch,to;
     private JTextField adno1,lid1,bkno1,cdno1,mzno1,idt1,rdt1,batch1,to1;
     private JComboBox box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,sts1;
     private JPanel cmppn,cmppn1,cmppn2;
     private JButton ins, can, next ;
     private Container c;
     private JTable sttable;

     private String columnsParam=null;
     private String constraints="";
     private String number,LID,BKNO,STATUS,IDT,RDT,sbatch1,sbatch2;
     private int item1=0,item2=0,item3=0,item4=0,item5=0,item6=0,item7=0,item8=0;
     private int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs,rs1 ;

     public ArchiveCdTrans()
       {
           super("INPUT CONSTRAINTS FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

           createComponents();
           odbcConnection();
           componentListener();
           MyAdapter7 myap = new MyAdapter7();
           addWindowListener(myap);
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
           c.add(cmppn,new AbsoluteConstraints(5,70,185,200));

           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(190,70,595,200));


           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,295,780,250));

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

           but3 = new JButton("ADMISSION  NUMBER");
           but3.setBackground(new Color(0,0,50));
           but3.setForeground(Color.cyan);
           but3.setBorder(new BevelBorder(0));
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20+10, 180-15, 30 ) );

           but4 = new JButton("PROFFESSOR  ID");
           but4.setBackground(new Color(0,0,50));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           cmppn.add ( but4, new AbsoluteConstraints( 10, 50+10, 180-15, 30 ) );

           but6 = new JButton("CD   ACCESS  NUMBER");
           but6.setBackground(new Color(0,0,50));
           but6.setForeground(Color.cyan);
           but6.setBorder(new BevelBorder(0));
           cmppn.add ( but6, new AbsoluteConstraints( 10, 80+10, 180-15, 30 ) );

           but8 = new JButton("ISSUED  DATE");
           but8.setBackground(new Color(0,0,50));
           but8.setForeground(Color.cyan);
           but8.setBorder(new BevelBorder(0));
           cmppn.add ( but8, new AbsoluteConstraints( 10, 110+10, 180-15, 30 ) );

           but9 = new JButton("RECIVED  DATE");
           but9.setBackground(new Color(0,0,50));
           but9.setForeground(Color.cyan);
           but9.setBorder(new BevelBorder(0));
           cmppn.add ( but9, new AbsoluteConstraints( 10, 140+10, 180-15, 30 ) );

           inputConstraints();
           setVisible(true);
       }
  private void odbcConnection()
       {
         try
           {
              Class.forName(  "sun.jdbc.odbc.JdbcOdbcDriver" );
              con = DriverManager.getConnection( "jdbc:odbc:Deepa","gateslibrary","future" );
              s = con.createStatement();

              /*int j =s.executeUpdate(" CREATE VIEW STUDENT_HISTORY2 AS SELECT  STD.ADNO, STD.SNAME,"+
                                     " STD.BRANCH, CD.CDCODE, CD.CDNAME, CD.CDVERSION, BD.IDATE,"+
                                     " BD.RDATE FROM STUDENTDETAILS STD, CDDETAILS CD, BOOKDETAILS BD"+
                                     " WHERE  STD.ADNO = BD.ADNO AND CD.CDCODE = BD.CDCODE " );
              con.commit();

              int k =s.executeUpdate(" CREATE VIEW STAFF_HISTORY2 AS SELECT  STD.LID, STD.LNAME,"+
                                     " STD.DEPT, CD.CDCODE, CD.CDNAME, CD.CDVERSION, BD.IDATE,"+
                                     " BD.RDATE FROM STAFF STD, CDDETAILS CD, BOOKDETAILS BD"+
                                     " WHERE  STD.LID = BD.LID AND CD.CDCODE = BD.MZCODE " );
              con.commit();
               */
              int x =s.executeUpdate("  CREATE TABLE TEMPTRANS( ADNO VARCHAR2(20),"+
                                                               "SNAME VARCHAR2(100),"+
                                                               "BRANCH VARCHAR2(8),"+
			                                       "LID VARCHAR2(20),"+
                                                               "LNAME VARCHAR2(100),"+
                                                               "DEPT VARCHAR2(40),"+
			                                       "CODE VARCHAR2(10),"+
                                                               "CNAME VARCHAR2(100),"+
                                                               "VERSION VARCHAR2(100),"+
                                                               "IDATE DATE,"+
                                          		       "RDATE DATE"+
                                                              ")"
                                    );

             //JOptionPane.showMessageDialog(null,"created");
             int y = s.executeUpdate("INSERT INTO TEMPTRANS (ADNO,SNAME,BRANCH,CODE,CNAME,VERSION,IDATE,RDATE)  SELECT * FROM STUDENT_HISTORY2");
             con.commit();

             int z = s.executeUpdate("INSERT INTO TEMPTRANS (LID,LNAME,DEPT,CODE,CNAME,VERSION,IDATE,RDATE)  SELECT * FROM STAFF_HISTORY2");
             con.commit();

             s.close();
           }
         catch (ClassNotFoundException confx )
           {
              JOptionPane.showMessageDialog (null,"Failed to load JDBC/ODBC drivers ","ERROR ",JOptionPane.ERROR_MESSAGE );
              confx.printStackTrace();
              System.exit(1);
           }
         catch ( SQLException sqlex )
           {
              JOptionPane.showMessageDialog (null,"Unable to connect "+sqlex.getMessage() ,
                                         "ERROR ",JOptionPane.ERROR_MESSAGE );
               sqlex.printStackTrace();
           }
       }

     private void inputConstraints()
       {

           //INPUT CONSTRAINTS FOR STUDENT DETAILS

             adno = new JLabel ( "ADMISSION-NO" );
            adno.setForeground ( new Color ( 120, 120, 153 ) );
            adno.setHorizontalAlignment ( SwingConstants.RIGHT  );
            cmppn1.add( adno, new AbsoluteConstraints(25+10-20 , 21+5+30-15-10) );

            adno1 = new JTextField ( );
            adno1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
            adno1.setForeground ( new Color ( 255, 0, 153 ) );
            adno1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add (adno1,new AbsoluteConstraints( 115+6+10-20, 20+5+30-15-10, 135, 20 ) );

            box1 = new JComboBox();
            box1.setForeground( new Color( 255, 0, 153 ));
            box1.setBackground(Color.white);
            box1.addItem("");
            box1.addItem("AND");
            box1.addItem("OR");
            cmppn1.add (box1,new AbsoluteConstraints( 250, 56-15-10, 45 , 20 ) );

            box2 = new JComboBox();
            box2.setForeground( new Color( 255, 0, 153 ));
            box2.setBackground(Color.white);
            box2.addItem("");
            box2.addItem("AND");
            box2.addItem("OR");
            cmppn1.add (box2,new AbsoluteConstraints( 540, 54-15-10, 45 , 20 ) );

            box3 = new JComboBox();
            box3.setForeground( new Color( 255, 0, 153 ));
            box3.setBackground(Color.white);
            box3.addItem("");
            box3.addItem("AND");
            box3.addItem("OR");
            cmppn1.add (box3,new AbsoluteConstraints( 250, 86-15-10, 45 , 20 ) );

            box4 = new JComboBox();
            box4.setForeground( new Color( 255, 0, 153 ));
            box4.setBackground(Color.white);
            box4.addItem("");
            box4.addItem("AND");
            box4.addItem("OR");
            cmppn1.add (box4,new AbsoluteConstraints( 540, 84-15-10, 45 , 20 ) );

            box5 = new JComboBox();
            box5.setForeground( new Color( 255, 0, 153 ));
            box5.setBackground(Color.white);
            box5.addItem("");
            box5.addItem("AND");
            box5.addItem("OR");
            cmppn1.add (box5,new AbsoluteConstraints( 250, 116-15-10, 45 , 20 ) );

            box6 = new JComboBox();
            box6.setForeground( new Color( 255, 0, 153 ));
            box6.setBackground(Color.white);
            box6.addItem("");
            box6.addItem("AND");
            box6.addItem("OR");
            cmppn1.add (box6,new AbsoluteConstraints( 540, 114-15-10, 45 , 20 ) );

            box7 = new JComboBox();
            box7.setForeground( new Color( 255, 0, 153 ));
            box7.setBackground(Color.white);
            box7.addItem("");
            box7.addItem("OR");
            box7.addItem("AND");
            cmppn1.add (box7,new AbsoluteConstraints( 540, 116+29-25, 45 , 20 ) );

            lid = new JLabel ( "PROFF -- ID :" );
            lid.setForeground ( new Color ( 120, 120, 153 ) );
            cmppn1.add ( lid, new AbsoluteConstraints( 319, 21+5+30-15-10 ) );

            lid1 = new JTextField ( );
            lid1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
            lid1.setForeground ( new Color ( 255, 0, 153 ) );
            lid1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( lid1, new AbsoluteConstraints( 395, 20+5+30-15-10, 135, 20 ) );

            bkno = new JLabel ( "CD_ACS_NO" );
            bkno.setForeground ( new Color ( 120, 120, 153 ) );
            cmppn1.add ( bkno, new AbsoluteConstraints( 25, 51+5+30-15-10 ) );

            bkno1 = new JTextField ( );
            //bkno1.setBackground(Color.white);
            bkno1.setForeground( new Color( 255, 0, 153 ));
            cmppn1.add ( bkno1, new AbsoluteConstraints( 115+16-20,50+5+30-15-10,135,20 ));


            sts = new JLabel("STATUS");
            sts.setForeground( new Color( 120, 120, 153 ) );
            sts.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( sts, new AbsoluteConstraints( 340,51+5+30-15-10 ) );

            sts1 = new JComboBox();
            sts1.setBackground(Color.white);
            sts1.setEditable(false);
            sts1.addItem("");
            sts1.addItem("TRUE");
            sts1.addItem("FALSE");
            cmppn1.add(sts1, new AbsoluteConstraints( 395,50+5+30-15-10,135,20 ) );


            idt = new JLabel();
            idt.setForeground( new Color( 120, 120, 153 ) );
            idt.setHorizontalAlignment ( SwingConstants.RIGHT );
            idt.setText("ISSUED  DATE :");
            cmppn1.add ( idt, new AbsoluteConstraints( 40-24,81+5+30-15-10 ) );
            //cmppn1.add ( batch, new AbsoluteConstraints( 42,111+5 ) );

            idt1 = new JTextField ( );
            idt1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            idt1.setForeground ( new Color ( 255, 0, 153 ) );
            idt1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( idt1, new AbsoluteConstraints( 115+16-21, 81+5+29-15-10,135, 20 ) );
            //cmppn1.add ( batch1, new AbsoluteConstraints( 265, 110+5,110, 20 ) );

            rdt = new JLabel("RECIV_DATE");
            rdt.setForeground( new Color( 120, 120, 153 ) );
            rdt.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( rdt, new AbsoluteConstraints( 316,81+5+30-15-10 ) );

            rdt1 = new JTextField ( );
            rdt1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            rdt1.setForeground ( new Color ( 255, 0, 153 ) );
            rdt1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( rdt1, new AbsoluteConstraints( 395, 81+5+29-15-10,135, 20 ) );

            batch = new JLabel();
            batch.setForeground( new Color( 120, 120, 153 ) );
            batch.setHorizontalAlignment ( SwingConstants.RIGHT );
            batch.setText("YEAR  FROM :");
            cmppn1.add ( batch, new AbsoluteConstraints( 25,111+5+30-15-10 ) );
            //cmppn1.add ( batch, new AbsoluteConstraints( 42,111+5 ) );

            batch1 = new JTextField ( );
            batch1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            batch1.setForeground ( new Color ( 255, 0, 153 ) );
            batch1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( batch1, new AbsoluteConstraints( 115+16-20, 111+5+29-15-10,135, 20 ) );
            //cmppn1.add ( batch1, new AbsoluteConstraints( 265, 110+5,110, 20 ) );

            to = new JLabel("TO  :");
            to.setForeground( new Color( 120, 120, 153 ) );
            to.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( to, new AbsoluteConstraints( 364,111+5+30-15-10 ) );
            //cmppn1.add ( to, new AbsoluteConstraints( 385,111+5 ) );

            to1 = new JTextField ( );
            to1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            to1.setForeground ( new Color ( 255, 0, 153 ) );
            to1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( to1, new AbsoluteConstraints( 395, 111+5+29-15-10,192, 20 ) );
            //cmppn1.add ( to1, new AbsoluteConstraints( 425, 110+5,120, 20 ) );


           //BUTTON CREATION
            ins = new JButton( "SEARCH" ) ;
            ins.setBackground (Color.cyan);
            ins.setForeground(Color.black);
            ins.setBorder(new BevelBorder(0));
            ins.setMnemonic('S');
            //ins.setEnabled (false);
            cmppn1.add ( ins, new AbsoluteConstraints( 123+20, 150+15-10, 105, 27 ) );

            print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setVisible(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 123+20, 150+15-10, 105, 27 ) );

            next = new JButton( "NEXT>>>" ) ;
            next.setBackground ( Color.cyan);
            next.setForeground(Color.black);
            next.setMnemonic('N');
            next.setBorder(new BevelBorder(0));
            cmppn1.add ( next, new AbsoluteConstraints( 229+21, 150+15-10, 105, 27 ) );

            can = new JButton( "EXIT" ) ;
            can.setBackground ( Color.cyan);
            can.setForeground( Color.black);
            can.setMnemonic('X');
            can.setBorder(new BevelBorder(0));
            cmppn1.add ( can, new AbsoluteConstraints( 335+2+20, 150+15-10, 105, 27 ) );

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
                      columnsParam = " ADNO, SNAME, BRANCH";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" ADNO, SNAME, BRANCH";
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
                      columnsParam = " LID, LNAME, DEPT";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" LID, LNAME, DEPT";
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
                      columnsParam = " CODE, CNAME, CDVERSION ";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" CODE, CNAME, VERSION ";
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
                      columnsParam = " IDATE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" IDATE";
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
                      columnsParam = " RDATE";
                 }
                 else
                 {
                      columnsParam = columnsParam+","+" RDATE";
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
                          if( adno1.getText()==null || adno1.getText().equals("") ) { }
                          else
                            {
                                number = adno1.getText().toUpperCase();

                                if( item1 > 0 )
                                  {
                                     constraints=constraints+" ADNO= "+"'"+number+"'"+" "+(String)box1.getSelectedItem();
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
                          if( lid1.getText()==null || lid1.getText().equals("") ) { }
                          else
                            {
                                LID = lid1.getText().toUpperCase();

                                if( item2 > 0 )
                                   {
                                      constraints=constraints+" LID= "+"'"+LID+"'"+"  "+(String)box2.getSelectedItem();
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
                          if( bkno1.getText()==null || bkno1.getText().equals("") ) { }
                          else
                           {
                              BKNO = bkno1.getText().toUpperCase();

                              if( item3 > 0)
                                {
                                   constraints=constraints+" CODE ="+"'"+BKNO+"'"+"  "+(String)box3.getSelectedItem ();
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
                          if( sts1.getSelectedItem()==null || sts1.getSelectedItem().equals("") ) { }
                          else
                            {
                                STATUS=((String)sts1.getSelectedItem()).toUpperCase();

                                if(item4 >0)
                                  {
                                     constraints = constraints+" STATUS ="+"'"+STATUS+"'"+"  "+(String)box4.getSelectedItem();
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
                          if( idt1.getText()==null || idt1.getText().equals("") ) { }
                          else
                            {
                               IDT = idt1.getText().toUpperCase();

                               if( item5>0 )
                                 {
                                    constraints=constraints+" IDATE ="+"'"+IDT+"'"+" "+(String)box5.getSelectedItem();
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
                          if( rdt1.getText()==null || rdt1.getText().equals("") ) { }
                          else
                            {
                               RDT = rdt1.getText().toUpperCase();

                               if( item6 >0 )
                                 {
                                    constraints=constraints+" RDATE ="+"'"+RDT+"'"+(String)box6.getSelectedItem();
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
                          if( batch1.getText() == null || batch1.getText().equals("") ) {}
                          else {  sbatch1=batch1.getText().toUpperCase();  }

                          if( to1.getText()==null||to1.getText().equals("") )
                            {
                               sbatch2="";
                               if(sbatch2.equals(""))
                                 {
                                    if(sbatch1==null||sbatch1.equals("")){}
                                    else {   sbatch2=sbatch1;  }
                                 }
                            }
                          else { sbatch2 = to1.getText().toUpperCase();  }

                          if( sbatch1==null||sbatch1.equals("")){}
                          else
                            {
                              if( item7 > 0)
                                {
                                   constraints=constraints+"( SELECT TO_CHAR(IDATE,'DD-MON-YYYY') FROM TEMPTRANS WHERE  TO_CHAR( IDATE,'YYYY') BETWEEN ('"+sbatch1+"') AND ('"+sbatch2+"')) OR SELECT TO_CHAR(RDATE,'DD-MON-YYYY') FROM TEMPTRANS WHERE  TO_CHAR( RDATE,'YYYY') BETWEEN ("+"'"+sbatch1+"') AND ('"+sbatch2+"')"+(String)box7.getSelectedItem();
                                }
                            }
                          count7++;
                        }
                   }
             }
          );

          ins.addActionListener(new ActionListener()
          {
               public void actionPerformed(ActionEvent e)
               {
                   //constraints="";

                   if( adno1.getText()==null || adno1.getText().equals("") ) { }
                   else
                     {
                         number = adno1.getText().toUpperCase();

                         if( item1 > 0 ) {}
                         else
                           {
                              constraints=constraints+" ADNO= "+"'"+number+"'";
                           }
                     }

                   if( lid1.getText()==null || lid1.getText().equals("") ) { }
                   else
                     {
                        LID = lid1.getText().toUpperCase();

                        if( item2 > 0 ){}
                        else
                          {
                             constraints=constraints+" LID= "+"'"+LID+"'";
                          }
                     }

                   if( bkno1.getText()==null || bkno1.getText().equals("") ) { }
                   else
                     {
                         BKNO = bkno1.getText().toUpperCase();

                         if( item3 > 0){}
                         else
                           {
                              constraints=constraints+" CODE ="+"'"+BKNO+"'";
                           }
                     }

                   if( sts1.getSelectedItem()==null || sts1.getSelectedItem().equals("") ) { }
                   else
                     {
                         STATUS=((String)sts1.getSelectedItem()).toUpperCase();

                         if(item4 >0) {}
                         else
                           {
                              constraints=constraints+" STATUS ="+"'"+STATUS+"'";
                           }
                     }

                   if( idt1.getText()==null || idt1.getText().equals("") ) { }
                   else
                     {
                        IDT = idt1.getText().toUpperCase();

                        if( item5>0 ){}
                        else
                          {
                             constraints=constraints+" IDATE ="+"'"+IDT+"'";
                          }
                     }

                   if( rdt1.getText()==null || rdt1.getText().equals("") ) { }
                   else
                     {
                        RDT = rdt1.getText().toUpperCase();

                        if( item6 >0 ){}
                        else
                          {
                             constraints=constraints+" RDATE ="+"'"+RDT+"'";
                          }
                     }

                   if( batch1.getText() == null || batch1.getText().equals("") ) {}
                   else
                     {
                        sbatch1=batch1.getText().toUpperCase();
                     }

                   if( to1.getText()==null||to1.getText().equals("") )
                     {
                        sbatch2="";
                        if(sbatch2.equals(""))
                          {
                             if(sbatch1==null||sbatch1.equals("")){}
                             else {   sbatch2=sbatch1;  }
                          }
                     }
                   else { sbatch2 = to1.getText().toUpperCase();  }

                   if( sbatch1==null||sbatch1.equals("")){}
                   else
                     {
                        if( item7 > 0){}
                        else
                          {
                             constraints=constraints+"( SELECT TO_CHAR(IDATE,'DD-MON-YYYY') FROM TEMPTRANS WHERE  TO_CHAR( IDATE,'YYYY') BETWEEN ('"+sbatch1+"') AND ('"+sbatch2+"')) OR SELECT TO_CHAR(RDATE,'DD-MON-YYYY') FROM TEMPTRANS WHERE  TO_CHAR( RDATE,'YYYY') BETWEEN ("+"'"+sbatch1+"') AND ('"+sbatch2+"')";
                          }
                     }

                     try
                       {
                          //JOptionPane.showMessageDialog(null,constraints);
                          s=con.createStatement();

                          if(columnsParam !=null)
                            {
                               if(constraints.equals(""))
                                 {
                                    rs=s.executeQuery("SELECT "+columnsParam+" FROM TEMPTRANS ");
                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                         JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . .  ","ARCHIVE TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                               else
                                 {
                                    rs=s.executeQuery("SELECT "+columnsParam+" FROM TEMPTRANS WHERE "+constraints );
                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                         JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . .  ","ARCHIVE TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                            }
                          else
                            {
                               if(constraints.equals(""))
                                 {
                                    rs=s.executeQuery("SELECT ADNO,SNAME,BRANCH,LID,LNAME,DEPT,CODE ACCESSNO,CNAME,VERSION,TO_CHAR(IDATE,'DD-MON-YYYY') ISSUED_DATE,TO_CHAR(RDATE,'DD-MON-YYYY') RECIEVED_DATE  FROM TEMPTRANS ");
                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                         JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . .  ","ARCHIVE TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                               else
                                 {
                                    rs=s.executeQuery("SELECT ADNO,SNAME,BRANCH,LID,LNAME,DEPT,CODE ACCESSNO,CNAME,VERSION,TO_CHAR(IDATE,'DD-MON-YYYY') ISSUED_DATE,TO_CHAR(RDATE,'DD-MON-YYYY') RECIEVED_DATE  FROM TEMPTRANS  WHERE "+constraints );
                                    if(rs.next())
                                      {
                                         getTable(rs);
                                      }
                                    else
                                      {
                                         JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . .  ","ARCHIVE TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                            }
                       }
                     catch(SQLException sqlex)
                       {
                          JOptionPane.showMessageDialog(null,sqlex.getMessage());
                          sqlex.printStackTrace();
                       }
                     ins.setVisible(false);
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
                                                       new MessageFormat("ARCHIVE CD'S TRANSACTIONS ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("ARCHIVE CD'S TRANSACTIONS ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
            next.addActionListener( new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    ins.setVisible(true);
                    print.setVisible(false);

                    adno1.setText("");
                    lid1.setText("");
                    bkno1.setText("");
                    sts1.setSelectedItem("");
                    idt1.setText("");
                    rdt1.setText("");
                    batch1.setText("");
                    to1.setText("");

                    columnsParam=null;
                    constraints="";

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
                  try
                    {
                       s = con.createStatement();
                       int d = s.executeUpdate("DROP TABLE TEMPTRANS");
                       int d2 = s.executeUpdate("DROP VIEW STUDENT_HISTORY2");
                       int d3 = s.executeUpdate("DROP VIEW STAFF_HISTORY2");
                       //JOptionPane.showMessageDialog(null,"deleted");
                       s.close();
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

       private class MyAdapter7 extends WindowAdapter
       {
         public void windowClosing(WindowEvent wt)
           {
             try
               {
                 s = con.createStatement();
                 int d = s.executeUpdate("DROP TABLE TEMPTRANS");
                 s.close();
                 con.close();
               }
             catch( SQLException sqlex )
               {
                  JOptionPane.showMessageDialog(null,"UNABLE TO DISCONNECT","Exception",JOptionPane.ERROR_MESSAGE);
                  sqlex.printStackTrace();
               }
           }
       }
     public static void main( String a[] )
       {
          new ArchiveCdTrans();
       }
  }