package org.ulibsoft.search.entitySearch;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
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

public class StaffSearch extends JFrame
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
     private JComboBox box1,box2,box3,box4,box5,box6;

     private String columnsParam = null;
     private String number,sname,sbranch,syear,sjyear,sdoj,ssts,sbatch1,sbatch2;
     private String constraints="";
     private int item1=0,item2=0,item3=0,item4=0,item5=0,item6=0,item7=0,item8=0;
     private int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0;

     private Connection  con ;
     private Statement s ;
     private ResultSet rs, rs1 ;

     public StaffSearch()
       {
           super("INPUT CONSTRAINTS FORM");
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
           c.add(cmppn,new AbsoluteConstraints(120,70,185,200));

           cmppn2 = new JPanel( new BorderLayout() );
           cmppn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " PROJECTION  OF  DATABASE ", 2, 2, c.getFont(), Color.magenta));
           cmppn2.setBackground(c.getBackground());
           c.add(cmppn2,new AbsoluteConstraints(5,300,780,250));

           but0 = new JLabel( "    SEARCH  STAFF  DATABASE" ) ;
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

           but3 = new JButton("PROFFESSOR ID");
           but3.setBackground(new Color(0,0,50));
           but3.setForeground(Color.cyan);
           but3.setBorder(new BevelBorder(0));
           cmppn.add ( but3, new AbsoluteConstraints( 10, 20+10, 180-15, 30 ) );

           but4 = new JButton("PROFFESSOR NAME");
           but4.setBackground(new Color(0,0,50));
           but4.setForeground(Color.cyan);
           but4.setBorder(new BevelBorder(0));
           cmppn.add ( but4, new AbsoluteConstraints( 10, 50+10, 180-15, 30 ) );

           but5 = new JButton("DEPARTMENT ");
           but5.setBackground(new Color(0,0,50));
           but5.setBorder(new BevelBorder(0));
           but5.setForeground(Color.cyan);
           cmppn.add ( but5, new AbsoluteConstraints( 10, 80+10, 180-15, 30 ) );


           but8 = new JButton("JOINED  DATE");
           but8.setBackground(new Color(0,0,50));
           but8.setForeground(Color.cyan);
           but8.setBorder(new BevelBorder(0));
           cmppn.add ( but8, new AbsoluteConstraints( 10, 110+10, 180-15, 30 ) );

           but9 = new JButton("PROFFESSOR STATUS");
           but9.setBackground(new Color(0,0,50));
           but9.setForeground(Color.cyan);
           but9.setBorder(new BevelBorder(0));
           cmppn.add ( but9, new AbsoluteConstraints( 10, 140+10, 180-15, 30 ) );




           setVisible(true);
       }
        private void inputConstraints()
        {
           cmppn1 = new JPanel( new AbsoluteLayout() );
           cmppn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " HAVING  CONSTRAINTS", 2, 2, c.getFont(), Color.magenta));
           cmppn1.setBackground(c.getBackground());
           c.add(cmppn1,new AbsoluteConstraints(290+20,70,375,200));

         //INPUT CONSTRAINTS FOR STUDENT DETAILS

            adno = new JLabel ( "PROFFESSOR  ID :" );
            adno.setForeground ( new Color ( 120, 120, 153 ) );
            adno.setHorizontalAlignment ( SwingConstants.RIGHT  );
            cmppn1.add( adno, new AbsoluteConstraints(25+20-2 , 21) );

            no = new JTextField ( );
            no.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
            no.setForeground ( new Color ( 255, 0, 153 ) );
            no.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add (no,new AbsoluteConstraints( 115+6+10+25, 20, 135, 20 ) );

            box1 = new JComboBox();
            box1.setForeground( new Color( 255, 0, 153 ));
            box1.setBackground(Color.white);
            box1.addItem("");
            box1.addItem("AND");
            box1.addItem("OR");
            cmppn1.add (box1,new AbsoluteConstraints( 250+15+30+10, 21, 45 , 18 ) );

            box2 = new JComboBox();
            box2.setForeground( new Color( 255, 0, 153 ));
            box2.setBackground(Color.white);
            box2.addItem("");
            box2.addItem("AND");
            box2.addItem("OR");
            cmppn1.add (box2,new AbsoluteConstraints( 250+15+30+10, 47, 45 , 18 ) );

            box3 = new JComboBox();
            box3.setForeground( new Color( 255, 0, 153 ));
            box3.setBackground(Color.white);
            box3.addItem("");
            box3.addItem("AND");
            box3.addItem("OR");
            cmppn1.add (box3,new AbsoluteConstraints( 250+15+30+10, 73, 45 , 18 ) );

            box4 = new JComboBox();
            box4.setForeground( new Color( 255, 0, 153 ));
            box4.setBackground(Color.white);
            box4.addItem("");
            box4.addItem("AND");
            box4.addItem("OR");
            cmppn1.add (box4,new AbsoluteConstraints( 250+15+30+10, 100, 45 , 18 ) );

            box5 = new JComboBox();
            box5.setForeground( new Color( 255, 0, 153 ));
            box5.setBackground(Color.white);
            box5.addItem("");
            box5.addItem("AND");
            box5.addItem("OR");
            cmppn1.add (box5,new AbsoluteConstraints( 250+15+30+10, 128, 45 , 18 ) );

            box6 = new JComboBox();
            box6.setForeground( new Color( 255, 0, 153 ));
            box6.setBackground(Color.white);
            box6.addItem("");
            box6.addItem("AND");
            box6.addItem("OR");
            //cmppn1.add (box6,new AbsoluteConstraints( 540+15+10, 114, 45 , 20 ) );

            name = new JLabel ( "PROFFESSOR NAME :" );
            name.setForeground ( new Color ( 120, 120, 153 ) );
            cmppn1.add ( name, new AbsoluteConstraints( 25, 21+30-4 ) );

            n1 = new JTextField ( );
            n1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
            n1.setForeground ( new Color ( 255, 0, 153 ) );
            n1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( n1, new AbsoluteConstraints( 115+16+25, 20+30-4, 135, 20 ) );

            branch = new JLabel ( "DEPARTMENT :" );
            branch.setForeground ( new Color ( 120, 120, 153 ) );
            cmppn1.add ( branch, new AbsoluteConstraints( 40+20, 51+23 ) );

            b1 = new JComboBox ( );
            b1.setBackground(Color.white);
            b1.addItem("");
            b1.addItem ("COMPUTERS");
            b1.addItem ("COMPUTER/INFO.TECH");
            b1.addItem ("ELECTRONICS");
            b1.addItem ("ELECTRICAL");
            b1.addItem ("MECHANICAL");
            b1.addItem ("MANAGEMENT");
            b1.addItem ("GENERAL");
            b1.addItem ("LANGUAGE");
            b1.setForeground( new Color( 255, 0, 153 ));
            cmppn1.add ( b1, new AbsoluteConstraints( 115+16+25,50+23,135,20 ));

            doj = new JLabel ( "JOINING  DATE :" );
            doj.setForeground ( new Color ( 120, 120, 153 ) );
            doj.setHorizontalAlignment ( SwingConstants.RIGHT  );
            cmppn1.add ( doj, new AbsoluteConstraints( 25+32, 81+22 ) );

            d1 = new JTextField ( );
            d1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
            d1.setForeground ( new Color ( 255, 0, 153 ) );
            d1.setCaretColor ( new Color ( 0, 204, 102 ) );
            cmppn1.add ( d1, new AbsoluteConstraints( 115+16+25, 80+20,135, 20 ) );

            sts = new JLabel("STATUS :");
            sts.setForeground( new Color( 120, 120, 153 ) );
            sts.setHorizontalAlignment ( SwingConstants.RIGHT );
            cmppn1.add ( sts, new AbsoluteConstraints( 93,129 ) );

            sts1 = new JComboBox();
            sts1.setBackground(Color.white);
           // sts1.setEditable(true);
            sts1.addItem("");
            sts1.addItem("TRUE");
            sts1.addItem("FALSE");
            cmppn1.add(sts1, new AbsoluteConstraints( 115+16+25,127,135,20 ) );

           //BUTTON CREATION
            ins = new JButton( "SEARCH" ) ;
            ins.setBackground (Color.cyan);
            ins.setForeground(Color.black);
            ins.setBorder(new BevelBorder(0));
            ins.setMnemonic('S');
            //ins.setEnabled (false);
            cmppn1.add ( ins, new AbsoluteConstraints( 26, 160 ,81, 23 ) );

            print = new JButton( "PRINT" ) ;
            print.setBackground (Color.cyan);
            print.setForeground(Color.black);
            print.setEnabled(false);
            print.setBorder(new BevelBorder(0));
            print.setMnemonic('P');
            cmppn1.add ( print, new AbsoluteConstraints( 109, 160, 81, 23 ) );

            next = new JButton( "NEXT>>>" ) ;
            next.setBackground ( Color.cyan);
            next.setForeground(Color.black);
            next.setMnemonic('N');
            next.setBorder(new BevelBorder(0));
            cmppn1.add ( next, new AbsoluteConstraints( 137-6+60,160, 81, 23 ) );

            can = new JButton( "EXIT" ) ;
            can.setBackground ( Color.cyan);
            can.setForeground( Color.black);
            can.setMnemonic('X');
            can.setBorder(new BevelBorder(0));
            cmppn1.add ( can, new AbsoluteConstraints( 244-5+34,160, 81, 23 ) );

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
                         columnsParam = "LID";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+"LID";
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
                         columnsParam = "LNAME";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" LNAME";
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
                         columnsParam = "DEPT";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+" DEPT";
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
                         columnsParam = " TO_CHAR(JOININGDATE,'DD-MON-YYYY') JOININGDATE";
                      }
                     else
                      {
                         columnsParam = columnsParam+","+"  TO_CHAR(JOININGDATE,'DD-MON-YYYY') JOININGDATE";
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
                      if( count1 < 0 )
                      {
                      if( no.getText()==null || no.getText().equals("") ) { }
                         else
                           {
                              number = no.getText().toUpperCase();
                              item1++;
                              if( item1 > 0 )
                                 {
                                     constraints=constraints+" LID = "+"'"+number+"'"+" "+(String)box1.getSelectedItem();
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
                      if( count2 < 1)
                        {
                          item2++;
                          if( n1.getText()==null || n1.getText().equals("") ) { }
                          else
                           {
                              sname = n1.getText().toUpperCase();

                              if( item2 > 0 )
                                 {
                                     constraints=constraints+" LNAME = "+"'"+sname+"'"+" "+(String)box2.getSelectedItem();
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
                      if(count3 < 1)
                        {
                           item3++;
                           if( b1.getSelectedItem()==null || b1.getSelectedItem().equals("") ) { }
                          else
                            {
                              sbranch=((String)b1.getSelectedItem()).toUpperCase();

                              if( item3 > 0 )
                                 {
                                     constraints=constraints+" DEPT = "+"'"+sbranch+"'"+" "+(String)box3.getSelectedItem();
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
                      if(count4 < 1)
                        {
                           item4++;
                           if( d1.getText()==null || d1.getText().equals("") ) { }
                           else
                             {
                               sdoj = d1.getText().toUpperCase();

                               if( item4 > 0 )
                                 {
                                     constraints=constraints+" JOININGDATE ="+"TO_CHAR(TO_DATE('"+sdoj+"'),'DD-MON-YYYY')"+" "+(String)box4.getSelectedItem();
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
                      if(count5 < 1)
                        {
                           item5++;
                           if( sts1.getSelectedItem()==null || sts1.getSelectedItem().equals("") ) { }
                           else
                            {
                              ssts = ((String)sts1.getSelectedItem()).toUpperCase();

                              if( item5 > 0 )
                                 {
                                     constraints=constraints+" STATUS= "+"'"+ssts+"'"+" "+(String)box5.getSelectedItem();
                                 }
                            }
                           count5++;
                        }
                   }
             }
          );

          ins.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    try
                      {  constraints="";
                         s = con.createStatement();

                         if( no.getText()==null || no.getText().equals("") ) { }
                         else
                           {
                              number = no.getText().toUpperCase();

                              if( item1 > 0 ){}
                              else
                                {
                                    constraints=constraints+" LID = "+"'"+number+"'";
                                }
                           }

                         if( n1.getText()==null || n1.getText().equals("") ) { }
                         else
                           {
                              sname = n1.getText().toUpperCase();

                              if( item2 > 0 ){}
                              else
                                 {
                                     constraints=constraints+" LNAME = "+"'"+sname+"'";
                                 }
                           }

                         if( b1.getSelectedItem()==null || b1.getSelectedItem().equals("") ) { }
                         else
                           {
                              sbranch=((String)b1.getSelectedItem()).toUpperCase();

                              if( item3 > 0 ){}
                              else
                                 {
                                     constraints=constraints+" DEPT = "+"'"+sbranch+"'";
                                 }
                           }

                         if( d1.getText()==null || d1.getText().equals("") ) { }
                         else
                           {
                             sdoj = d1.getText().toUpperCase();

                             if( item4 > 0 ){}
                             else
                               {
                                  constraints=constraints+" JOININGDATE ="+"TO_CHAR(TO_DATE('"+sdoj+"'),'DD-MON-YYYY')";
                               }
                           }

                         if( sts1.getSelectedItem()==null || sts1.getSelectedItem().equals("") ) { }
                         else
                           {
                              ssts = ((String)sts1.getSelectedItem()).toUpperCase();

                              if( item5 > 0 ){}
                              else
                                {
                                   constraints=constraints+" STATUS= "+"'"+ssts+"'";
                                }
                           }

                           if( columnsParam!=null )
                             {
                                if( constraints.equals("") )
                                 {
                                   rs = s.executeQuery("SELECT "+columnsParam+" FROM STAFF ");

                                   if(rs.next())
                                     {
                                        getTable(rs);
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STAFF  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                     }
                                 }
                               else
                                 {
                                    rs = s.executeQuery("SELECT "+columnsParam+" FROM STAFF WHERE "+constraints);
                                    if(rs.next())
                                      {
                                        getTable(rs);
                                      }
                                    else
                                      {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STAFF  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }

                             }
                           else
                             {
                               if( constraints.equals(""))
                                 {
                                   rs = s.executeQuery("SELECT LID,LNAME,DEPT, TO_CHAR(JOININGDATE,'DD-MON-YYYY') JOININGDATE,STATUS,TEACHING FROM STAFF" );

                                   if(rs.next())
                                     {
                                        getTable(rs);
                                     }
                                   else
                                     {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STAFF  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                     }
                                 }
                               else
                                 {
                                    rs = s.executeQuery("SELECT * FROM STAFF WHERE "+constraints );
                                    if(rs.next())
                                      {
                                        getTable(rs);
                                      }
                                    else
                                      {
                                        JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY ! . . .","STAFF  DATABASE",JOptionPane.INFORMATION_MESSAGE);
                                      }
                                 }
                             }
                      }
                    catch(SQLException sqlex)
                      {
                         JOptionPane.showMessageDialog(null,sqlex.getMessage() );
                      }
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
                   print.setEnabled(false);
                   no.setText("");
                   n1.setText("");
                   b1.setSelectedItem("");
                   d1.setText("");
                   sts1.setSelectedItem("");

                   columnsParam=null;
                   constraints="";

                   box1.setSelectedItem("");
                   box2.setSelectedItem("");
                   box3.setSelectedItem("");
                   box4.setSelectedItem("");
                   box5.setSelectedItem("");
                   box6.setSelectedItem("");
                   //box7.setSelectedItem("");

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


     public static void main(String a[] )
       {
          new StaffSearch();
       }
  }