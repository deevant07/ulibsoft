package org.ulibsoft.search.defaulters;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class FineReport extends JFrame
  {
      private int choice = 0;
      private Container c;
      private JButton but1,but2,but3,but4,but5,but6,but7;
      private JButton but8,but9,but10,print;
      private JLabel but0;
      private JPanel cmppn,cmppn1,cmppn2;
      private JTable bookdetails,sttable;

      private FileOutputStream fout;
      private File file;
      private String fp;
    //private static int fineValue;


      private JComboBox box1, box2, box3, box4, box5, box6, branch1;
      private int item1=0,item2=0,item3=0,item4=0;
      private int count1=0,count2=0,count3=0,count4=0;

      private JPanel tablePanel,componentPanel,componentPanel2,componentPanel3;
      private JTable transactionTable;
      private JLabel date,fm,to,idt,rdt,branch,fine1,fine2;
      private JTextField date1,date2,idt1,rdt1;
      private JButton sh,sh1,sh2,can,clr,sub;
      private String constraints="",number,adno;

      private Connection  con;
      private Statement s ;
      private ResultSet rs, rs1 ;


      public FineReport()
        {
           super(" FINE  REPORT  GENERATION  FORM");
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
                                            " CHOOSE  UR  CHOICE", 2, 2, c.getFont(), Color.magenta));
           cmppn.setBackground(c.getBackground());
           c.add(cmppn,new AbsoluteConstraints(130,70,185,190));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,335,780,215));

           cmppn1 = new JPanel(new AbsoluteLayout());
           c.add( cmppn1 , new AbsoluteConstraints(243,275,300,50) );
           cmppn1.setBackground(new Color(0,0,50));
           cmppn1.setBorder(new BevelBorder(0));

           fine1 = new JLabel("TOTAL  FINE  AMOUNT  :");
           fine1.setForeground(new Color(120,120,155));
           cmppn1.add(fine1,new AbsoluteConstraints(20+20-3,15));

           fine2 = new JLabel(" RS  000 - 00  / --");
           fine2.setForeground(new Color(120,120,155));
           cmppn1.add(fine2,new AbsoluteConstraints(160+20-3,15));

           componentPanel = new JPanel( new AbsoluteLayout() );
           componentPanel.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                             "INPUT  CONSTRAINTS", 1, 2, c.getFont(),new  Color(0,100,250)));
           componentPanel.setBackground( c.getBackground() );
           c.add(componentPanel,new AbsoluteConstraints( 313, 70, 344, 190 ));

           componentPanel2 = new JPanel( new AbsoluteLayout() );
           componentPanel2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                             "INPUT  CONSTRAINTS", 1, 2, c.getFont(),new  Color(0,100,250)));
           componentPanel2.setBackground( c.getBackground() );
           componentPanel2.setVisible(false);
           c.add(componentPanel2,new AbsoluteConstraints( 313, 70, 344, 190 ));

           componentPanel3 = new JPanel( new AbsoluteLayout() );
           componentPanel3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                             "INPUT  CONSTRAINTS", 1, 2, c.getFont(),new  Color(0,100,250)));
           componentPanel3.setBackground( c.getBackground() );
           c.add(componentPanel3,new AbsoluteConstraints( 313, 70, 344, 190 ));
           componentPanel3.setVisible(true);


           but0 = new JLabel( "       FINE  REPORT  GENERATION" ) ;
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

           sh = new JButton("BOOK");
           sh.setMnemonic ('B');
           sh.setBackground(new Color(0,0,55));
           sh.setForeground ( Color.cyan );
           sh.setBorder ( new BevelBorder(0) );
           cmppn.add(sh,new AbsoluteConstraints(10,20,170,25));

           sh1 = new JButton("CD / FLOPPY");
           sh1.setMnemonic ('B');
           sh1.setBackground(new Color(0,0,55));
           sh1.setForeground ( Color.cyan );
           sh1.setBorder ( new BevelBorder(0) );
           cmppn.add(sh1,new AbsoluteConstraints(10,58-12,170,25));

           sh2 = new JButton("MAGAZINE");
           sh2.setMnemonic ('T');
           sh2.setBackground(new Color(0,0,55));
           sh2.setForeground ( Color.cyan );
           sh2.setBorder ( new BevelBorder(0) );
           cmppn.add(sh2,new AbsoluteConstraints(10,86-14,170,25));

           sub = new JButton("***   SEARCH   ***");
           sub.setMnemonic ('T');
           sub.setBackground(new Color(0,0,55));
           sub.setForeground ( Color.cyan );
           sub.setBorder ( new BevelBorder(0) );
           cmppn.add(sub,new AbsoluteConstraints(10,114-16,170,25));

           clr = new JButton("  CLEAR  ");
           clr.setBackground(new Color(0,0,55));
           clr.setForeground ( Color.cyan );
           clr.setBorder ( new BevelBorder(0));
           cmppn.add(clr,new AbsoluteConstraints(10, 142-18, 170, 25));

           can = new JButton( "EXIT" ) ;
           can.setBackground(new Color(0,0,55));
           can.setForeground ( Color.cyan );
           can.setMnemonic('X');
           can.setBorder(new BevelBorder(0));
           cmppn.add ( can, new AbsoluteConstraints( 10, 170-20, 170, 25 ) );

            print = new JButton( "PRINT" ) ;
            print.setBackground (new Color(0,0,55));
           // print.setForeground(Color.black);
            //print.setEnabled(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');

           bookConstraints();


           setVisible(true);
       }



    private void bookConstraints()
      {
           idt = new JLabel("ADMISSION_NO");
           idt.setForeground(new Color(120,120,153));
           componentPanel.add(idt,new AbsoluteConstraints(25-3,31));

           idt1 = new JTextField();
           idt1.setForeground ( new Color ( 255, 0, 153 ) );
           idt1.setCaretColor ( new Color ( 0, 204, 102 ) );
           idt1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
           componentPanel.add(idt1,new AbsoluteConstraints(120,30,150,20));

           rdt = new JLabel("ACCESS_NUM  :");
           rdt.setForeground(new Color(120,120,153));
           componentPanel.add(rdt,new AbsoluteConstraints(25-2,61));

           rdt1 = new JTextField();
           rdt1.setForeground ( new Color ( 255, 0, 153 ) );
           rdt1.setCaretColor ( new Color ( 0, 204, 102 ) );
           rdt1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
           componentPanel.add(rdt1,new AbsoluteConstraints(120,60,150,20));

           date = new JLabel("DATE => ");
           date.setForeground(new Color(120,120,153));
           componentPanel.add(date,new AbsoluteConstraints(25,90,150,20));

           fm = new JLabel(" FROM ");
           fm.setForeground(new Color(120,120,153));
           componentPanel.add(fm,new AbsoluteConstraints(73,90,150,20));

           date1 = new JTextField();
           date1.setForeground ( new Color ( 255, 0, 153 ) );
           date1.setCaretColor ( new Color ( 0, 204, 102 ) );
           date1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
           componentPanel.add(date1,new AbsoluteConstraints(120,89,206,20));

           to = new JLabel("=> TO >>");
           to.setForeground(new Color(120,120,153));
           componentPanel.add(to,new AbsoluteConstraints(58,120));

           date2 = new JTextField();
           date2.setForeground ( new Color ( 255, 0, 153 ) );
           date2.setCaretColor ( new Color ( 0, 204, 102 ) );
           date2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
           componentPanel.add(date2,new AbsoluteConstraints(120,119,150,20));

           branch = new JLabel("==>  COURSE  :");
           branch.setForeground(new Color(120,120,153));
           componentPanel.add(branch,new AbsoluteConstraints(25,151));

           branch1 = new JComboBox();
           branch1.setEditable(false);
           branch1.addItem("");
           branch1.setForeground ( new Color ( 255, 0, 153 ) );
           branch1.setBackground(Color.white);
           componentPanel.add(branch1,new AbsoluteConstraints(120,150,150,20));

           box1 = new JComboBox();
           box1.setForeground( new Color( 255, 0, 153 ));
           box1.setBackground(Color.white);
           box1.addItem("");
           box1.addItem("AND");
           box1.addItem("OR");
           componentPanel.add (box1,new AbsoluteConstraints( 280, 30, 45 , 18 ) );

           box2 = new JComboBox();
           box2.setForeground( new Color( 255, 0, 153 ));
           box2.setBackground(Color.white);
           box2.addItem("");
           box2.addItem("AND");
           box2.addItem("OR");
           componentPanel.add (box2,new AbsoluteConstraints( 280, 60, 45 , 18 ) );

           box3 = new JComboBox();
           box3.setForeground( new Color( 255, 0, 153 ));
           box3.setBackground(Color.white);
           box3.addItem("");
           box3.addItem("AND");
           box3.addItem("OR");
           componentPanel.add (box3,new AbsoluteConstraints( 280, 120, 45 , 18 ) );

           box4 = new JComboBox();
           box4.setForeground( new Color( 255, 0, 153 ));
           box4.setBackground(Color.white);
           box4.addItem("");
           box4.addItem("AND");
           box4.addItem("OR");
           componentPanel.add (box4,new AbsoluteConstraints( 280, 150, 45 , 18 ) );

           setStatus(false);
      }

    private void setStatus(boolean value)
      {
         idt1.setEditable(value);
         rdt1.setEditable(value);
         //date1.setEditable(value);
         //date2.setEditable(value);
         branch1.setEnabled(value);

         box1.setEnabled(value);
         box2.setEnabled(value);
         //box3.setEnabled(value);
         box4.setEnabled(value);
      }

    private void componentListener()
      {

         box1.addItemListener(new ItemListener()
            {
               public void itemStateChanged(ItemEvent e)
                 {
                    if( count1<1 )
                      {
                         item1++;
                         if( idt1.getText()==null || idt1.getText().equals("")){}
                         else
                           {
                              adno=idt1.getText();
                              if( item1>0 )
                                {
                                   constraints=constraints+" BD.ADNO= "+"'"+adno+"'"+" "+(String)box1.getSelectedItem();
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
                    if( count2<1 )
                      {
                         item2++;
                         if( rdt1.getText()==null || rdt1.getText().equals(""))
                         {

                         }
                         else
                           {
                              if( item2>0 )
                                {
                                   number=rdt1.getText();
                                   switch(choice)
                                     {
                                        case 1:  constraints=constraints+" BD.CODE= "+"'"+number+"'"+" "+(String)box2.getSelectedItem();
                                                 break;
                                        case 2:  constraints=constraints+" BD.CDCODE= "+"'"+number+"'"+" "+(String)box2.getSelectedItem();
                                                 break;
                                        case 3:  constraints=constraints+" BD.MZCODE= "+"'"+number+"'"+" "+(String)box2.getSelectedItem();
                                                 break;
                                     }
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
                    if( count3<1 )
                      {
                         item3++;
                         if( date1.getText()==null || date1.getText().equals("")){}
                         else
                           {
                              if( item3>0 )
                                {
                                   if(date2.getText()==null || date2.getText().equals(""))
                                     {
                                        date2.setText("");
                                        if(date2.getText().equals(""))
                                          {
                                              date2.setText(date1.getText());
                                          }
                                         constraints=constraints+" BD.FDATE= TO_CHAR(TO_DATE('"+date1.getText()+"'),'DD-MON-YYYY')"+"" +(String)box3.getSelectedItem();
                                     }
                                   else
                                     {
                                         constraints=constraints+" BD.FDATE BETWEEN (TO_CHAR(TO_DATE('"+date1.getText()+"'),'DD-MON-YYYY')) AND (TO_CHAR(TO_DATE('"+date2.getText()+"'),'DD-MON-YYYY')) " +""+(String)box3.getSelectedItem();
                                     }
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
                    if( count4<1 )
                      {
                         item4++;
                         if( branch1.getSelectedItem()==null || branch1.getSelectedItem().equals("")){}
                         else
                           {
                              if( item4>0 )
                                {
                                   constraints=constraints+" STD.BRANCH = "+"'"+(String)branch1.getSelectedItem()+"'"+(String)box4.getSelectedItem();
                                }
                           }
                         count4++;
                      }
                 }
            }
         );

         sh.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    choice=1;
                    setStatus(true);
                    branchdetails();
                 }
            }
         );

         sh1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    choice=2;
                    setStatus(true);
                    branchdetails();
                 }
            }
         );
         sh2.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    choice=3;
                    setStatus(true);
                    branchdetails();
                 }
            }
         );

         print.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     Runtime rt = Runtime.getRuntime();
                     try
                       {
                          fout.close();
                          rt.exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE c:\\windows\\report4.html");
                       }
                     catch(IOException ex)
                       {
                       }
                 }
            }
          );

         sub.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
                 {
                    switch( choice )
                      {
                          case 1 :
                          case 2 :
                          case 3 : calFine();   break;
                          default: JOptionPane.showMessageDialog(null,"CHOOSE  UR  CHOICE  . . . ?");
                      }
                 }
           }
         );
         clr.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                   idt1.setText("");
                   rdt1.setText("");
                   date1.setText("");
                   date2.setText("");
                   branch1.setSelectedItem("");

                   box1.setSelectedItem("");
                   box2.setSelectedItem("");
                   box3.setSelectedItem("");
                   box4.setSelectedItem("");

                   /*file=new File("c:/windows/report41.html");
                   boolean xw=file.delete();
                   if(xw==true)
                   {
                      JOptionPane.showMessageDialog(null,"Deleted");
                   }   */
                   constraints="";
                   choice=0;
                   setStatus(false);
                   item1=0;   count1=0;
                   item2=0;   count2=0;
                   item3=0;   count3=0;
                   item4=0;   count4=0;

                   fine2.setText(" RS  000 - 00  / --");
                   print.setVisible(false);
                   sh.setVisible(true);
                   sh1.setVisible(true);
                   sh2.setVisible(true);
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
      private void branchdetails()
      {
        try
         {
            s=con.createStatement();
            branch1.removeAllItems();
            branch1.addItem("");
            rs=s.executeQuery("SELECT DISTINCT(BRANCH) FROM STUDENTDETAILS");
            while(rs.next())
            {
              branch1.addItem(rs.getObject(1));
            }
         }catch(SQLException sqlex)
            {
               JOptionPane.showMessageDialog(null,sqlex.getMessage());
               sqlex.printStackTrace();
            }
      }
    private void calFine()
    {

         if(idt1.getText()==null || idt1.getText().equals("")){ }
        else
        {
          adno=idt1.getText();
          if(item1>0){}
          else
          {
          switch(choice)
          {
             case 1:
             case 2:
             case 3: constraints=constraints+" BD.ADNO= "+"'"+adno+"'"+" ";
          }
          }
        }

        if(rdt1.getText()==null || rdt1.getText().equals("")){ }
        else
        {
          number=rdt1.getText();
           if(item2>0){}
          else
          {
          switch(choice)
          {
             case 1: constraints=constraints+" BD.CODE= "+"'"+number+"'"+" ";
                     break;
             case 2: constraints=constraints+" BD.CDCODE= "+"'"+number+"'"+" ";
                     break;
             case 3: constraints=constraints+" BD.MZCODE= "+"'"+number+"'"+" ";
                     break;
          }
          }
        }

        if(date1.getText()==null || date1.getText().equals("")){ }
        else
         {   if(item3>0){}
          else
          {
            constraints=constraints+" BD.FDATE= TO_CHAR(TO_DATE('"+date1.getText()+"'),'DD-MON-YYYY')"+"" ;
            }
         }

        if(date2.getText()==null || date2.getText().equals(""))
          {
              date2.setText("");
              if(date2.getText().equals(""))
                {
                   date2.setText(date1.getText());
                }
          }
        else
          {   if(item3>0){}
          else
          {
             constraints=constraints+" BD.FDATE BETWEEN (TO_CHAR(TO_DATE('"+date1.getText()+"'),'DD-MON-YYYY')) AND (TO_CHAR(TO_DATE('"+date2.getText()+"'),'DD-MON-YYYY')) " +"";
           }
          }

        if(branch1.getSelectedItem()==null || branch1.getSelectedItem().equals("")){ }
          else
          {
              if(item4>0){}
          else
            {
              constraints=constraints+" STD.BRANCH = "+"'"+(String)branch1.getSelectedItem()+"'";
            }
          }

         try
           {
               s=con.createStatement();
               switch(choice)
                 {
                     case 1:
                            if(constraints==null||constraints.equals(""))
                            {
                              rs1 = s.executeQuery("SELECT SUM(BD.FINEAMOUNT) FROM BOOKDETAILS BD,STUDENT_HISTORY STD WHERE STD.ADNO=BD.ADNO ");
                              if( rs1.next() )
                                {
                                    fine2.setText("RS  "+rs1.getString(1)+"  /  --");
                                }

                              rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.ACESSNO,STD.AUTHOR,STD.BOOKNAME,TO_CHAR(STD.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(STD.RDATE,'DD-MON-YYYY') RDATE,BD.FINEAMOUNT,BD.FINE_PAID AMOUNTPAID,BD.REASON FROM STUDENT_HISTORY STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND STD.ACESSNO=BD.CODE AND BD.FINEAMOUNT IS NOT NULL ");
                              if( rs.next() )
                                {

                                    getTable(rs);
                                }
                              else
                                {
                                    JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . . ");
                                }
                            }
                              else
                               {


                                  rs1 = s.executeQuery("SELECT SUM(BD.FINEAMOUNT) FROM BOOKDETAILS BD,STUDENT_HISTORY STD WHERE STD.ADNO=BD.ADNO AND "+constraints);
                              if( rs1.next() )
                                {
                                    fine2.setText("RS  "+rs1.getString(1)+"  /  --");
                                }

                              rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.ACESSNO,STD.AUTHOR,STD.BOOKNAME,TO_CHAR(STD.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(STD.RDATE,'DD-MON-YYYY') RDATE,BD.FINEAMOUNT,BD.FINE_PAID AMOUNTPAID,BD.REASON FROM STUDENT_HISTORY STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND STD.ACESSNO=BD.CODE  AND BD.FINEAMOUNT IS NOT NULL AND "+constraints);
                              if( rs.next() )
                                {
                                    getTable(rs);
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . . ");
                                }
                               }
                              cmppn.add(print,new AbsoluteConstraints(10,20,170,25));
                              sh.setVisible(false);
                              print.setVisible(true);
                              print.setForeground(Color.cyan);
                              break;

                     case 2:
                            if(constraints==null||constraints.equals(""))
                            {
                              rs1 = s.executeQuery("SELECT SUM(BD.FINEAMOUNT) FROM BOOKDETAILS BD,STUDENT_HISTORY2 STD WHERE STD.ADNO=BD.ADNO ");
                               if( rs1.next() )
                                {
                                    fine2.setText("RS  "+rs1.getString(1)+"  /  --");
                                }
                             //JOptionPane.showMessageDialog(null,"cdscsd"+fine2.getText());
                             rs=s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.CDCODE,STD.CDVERSION,STD.CDNAME,TO_CHAR(STD.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(STD.RDATE,'DD-MON-YYYY') RDATE,BD.FINEAMOUNT,BD.FINE_PAID AMOUNTPAID,BD.REASON FROM STUDENT_HISTORY2 STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND STD.CDCODE=BD.CDCODE  AND BD.FINEAMOUNT IS NOT NULL");
                             if( rs.next() )
                               {
                                  getTable(rs);
                               }
                             else
                               {
                                   JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . . ");
                               }
                             }
                             else
                             {
                               rs1 = s.executeQuery("SELECT SUM(BD.FINEAMOUNT) FROM BOOKDETAILS BD,STUDENT_HISTORY STD WHERE STD.ADNO=BD.ADNO AND "+constraints);
                               if( rs1.next() )
                                {
                                    fine2.setText("RS  "+rs1.getString(1)+"  /  --");
                                }
                             rs=s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.CDCODE,STD.CDVERSION,STD.CDNAME,TO_CHAR(STD.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(STD.RDATE,'DD-MON-YYYY') RDATE,BD.FINEAMOUNT,BD.FINE_PAID AMOUNTPAID,BD.REASON FROM STUDENT_HISTORY2 STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND STD.CDCODE=BD.CDCODE  AND BD.FINEAMOUNT IS NOT NULL AND "+constraints);
                             if( rs.next() )
                               {
                                  getTable(rs);
                               }
                             else
                               {
                                   JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . . ");
                               }
                             }
                             cmppn.add(print,new AbsoluteConstraints(10,58-12,170,25));
                             sh1.setVisible(false);
                             print.setVisible(true);
                             print.setForeground(Color.cyan);
                             break;

                     case 3: if(constraints==null||constraints.equals(""))
                             {
                              rs1 = s.executeQuery("SELECT SUM(BD.FINEAMOUNT) FROM BOOKDETAILS BD,STUDENT_HISTORY3 STD WHERE STD.ADNO=BD.ADNO ");
                               if( rs1.next() )
                                {
                                    fine2.setText("RS  "+rs1.getString(1)+"  /  --");
                                }
                             rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.ACCESSNO,STD.VOLUME,STD.MZNAME,TO_CHAR(STD.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(STD.RDATE,'DD-MON-YYYY') RDATE,BD.FINEAMOUNT,BD.FINE_PAID AMOUNTPAID,BD.REASON FROM STUDENT_HISTORY3 STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND STD.ACCESSNO=BD.MZCODE  AND BD.FINEAMOUNT IS NOT NULL");
                             if( rs.next() )
                               {
                                   getTable(rs);
                               }
                             else
                               {
                                   JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . . ");
                               }
                             }
                             else
                              {
                               rs1 = s.executeQuery("SELECT SUM(BD.FINEAMOUNT) FROM BOOKDETAILS BD,STUDENT_HISTORY3 STD WHERE STD.ADNO=BD.ADNO AND "+constraints);
                               if( rs1.next() )
                                {
                                    fine2.setText("RS  "+rs1.getString(1)+"  /  --");
                                }
                             rs = s.executeQuery("SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.ACCESSNO,STD.VOLUME,STD.MZNAME,TO_CHAR(STD.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(STD.RDATE,'DD-MON-YYYY') RDATE,BD.FINEAMOUNT,BD.FINE_PAID AMOUNTPAID,BD.REASON FROM STUDENT_HISTORY3 STD,BOOKDETAILS BD WHERE STD.ADNO=BD.ADNO AND STD.ACCESSNO=BD.MZCODE  AND BD.FINEAMOUNT IS NOT NULL AND "+constraints);
                             if( rs.next() )
                               {
                                   getTable(rs);
                               }
                             else
                               {
                                   JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY ! . . . ");
                               }
                              }
                             cmppn.add(print,new AbsoluteConstraints(10,86-14,170,25));
                             sh2.setVisible(false);
                             print.setVisible(true);
                             print.setForeground(Color.cyan);
                             break;
                 }
            }
          catch(SQLException sqlex)
            {
               JOptionPane.showMessageDialog(null,sqlex.getMessage());
               sqlex.printStackTrace();
            }
      }
    private void getTable(ResultSet rs )
      {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {
                try
                {
                  fout=new FileOutputStream("c:\\windows\\report4.html");
                }catch(Exception se)
                {
                }
              fp="<HTML>";
              fp+="<BODY BGCOLOR=#f95360><CENTER><FONT COLOR="+"CYAN"+" SIZE="+"6"+">FINE REPORT GENERATION</FONT></CENTER>";
              fp+="<TABLE ALIGN="+" CENTER "+" BORDER="+"1 "+" WIDTH="+"700 "+" CELLSPACING="+" 0 >";
              //fp+="<CAPTION>FINE AMOUNT</CAPTION>";
              fp+="<BR><TR BGCOLOR="+"#AADD00"+">";

                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                {
                columnHeads.addElement( rsmd.getColumnName(i) );
                 fp+="<TH>"+rsmd.getColumnName(i)+"</TH>";
                }
                 fp+="</TR><TR>";
                do
                 {
                   rows.addElement( getNextRow( rs,rsmd ) );
                 }while( rs.next() );

                   fp+="</TABLE></BODY></HTML>";

                  try
                   {
                        fout.write(fp.getBytes());
                    }catch(Exception se){ }

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
                 case Types.VARCHAR :     String tp=rs.getString(i);
                                          fp+="<TD BGCOLOR="+"#AADD00"+">"+tp+"</TD>" ;
                                          currentRow.addElement( tp );
                                          break;

                 case Types.LONGVARCHAR : String tp1=rs.getString(i);
                                          fp+="<TD BGCOLOR="+"#AADD00"+">"+tp1+"</TD>" ;
                                          currentRow.addElement( tp1 );
                                          break;
                 default:                 String tp2=rs.getString(i);
                                          fp+="<TD BGCOLOR="+"#AADD00"+">"+tp2+"</TD>" ;
                                          currentRow.addElement( tp2 );
              }
           }
        fp+="</TR>";

         return currentRow;
      }

    public static void main( String a[] )
       {
          new FineReport();
       }
  }