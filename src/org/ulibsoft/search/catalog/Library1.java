package org.ulibsoft.search.catalog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import javax.swing.text.JTextComponent;
import java.text.MessageFormat;
import java.awt.print.PrinterException;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class Library1 extends JFrame
  {
   private JLabel     publisher,acessno,author,xyz,bname;
   private JTextField title,pub1,aut;
   private JTextField ACESSNO;
   private JComboBox AUTHOR,bname1,PUBLISHER;
   private JButton    Search,Next_Rec,Quit,clear,print,edit;
   private JLabel     output1,output2,output3,output4,output5,output6,output7,output8,output9;
   private String     acn,pub,atr,output;
   private JPanel     bkpn,rtpn;//,otpn;
   private JTextArea otpn, cacheData;
   private Container  c;
   private String out1,out2,out3,out4,out5;

   private FileOutputStream fout;
   private File file;
   private String fp,fp1;


   private int xy,count=0,count1=0,count2=0;
   private static int wq=0;

   private Statement  s;
   private Connection con;
   private ResultSet  rs;
   private String TITLE="",PUB="",AUT="";

   public Library1()
     {
        super("SEARCH BY TYPE");
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        show();

        createComponents();
        //odbcConnection();
         try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
        componentListener();
     }

   private void createComponents()
     {
        c = getContentPane();
        c.setLayout( new AbsoluteLayout() );
        c.setBackground(new Color(0,0,40));

        bkpn = new JPanel(new AbsoluteLayout());
        bkpn.setBackground(c.getBackground());
        bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "SEARCH-BY-TYPE", 1, 2, c.getFont(), Color.magenta));
        c.add(bkpn,new AbsoluteConstraints(235,20,310,230));

        rtpn = new JPanel(new AbsoluteLayout());
        rtpn.setBackground(c.getBackground());
        rtpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "REPORT-GENERATION", 1, 2, c.getFont(), Color.magenta));
        c.add(rtpn,new AbsoluteConstraints(120,265,500,280));

        acessno   = new JLabel("ACCESSNO");
        acessno.setForeground(new Color(120,120,153));
        bkpn.add(acessno,new AbsoluteConstraints( 40,31) );

        ACESSNO   = new JTextField("");
        ACESSNO.setForeground(new Color(255,0,153));
        ACESSNO.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        bkpn.add(ACESSNO,new AbsoluteConstraints(115,30,150,20) );

        bname   = new JLabel("BOOK NAME");
        bname.setForeground(new Color(120,120,153));
        bkpn.add(bname,new AbsoluteConstraints( 30,61) );

        bname1   = new JComboBox();
        bname1.setEditable(true);
        bname1.setVisible(false);
        bname1.setForeground(new Color(255,0,153));
        bname1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        bkpn.add(bname1,new AbsoluteConstraints(115,60,150,20) );

        title = new JTextField( );
        title.setEditable(true);
        title.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
        title.setForeground ( new Color ( 255, 0, 153 ) );
        title.setCaretColor ( new Color ( 0, 204, 102 ) );
        bkpn.add ( title, new AbsoluteConstraints( 115,60,150,20 ) );

        author    = new JLabel("AUTHOR");
        author.setForeground(new Color(120,120,153));
        bkpn.add(author,new AbsoluteConstraints( 55, 91) );

        AUTHOR    = new JComboBox();
        AUTHOR.setEditable(true);
        AUTHOR.setVisible(false);
        AUTHOR.setForeground(new Color(255,0,153));
        AUTHOR.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add(AUTHOR, new AbsoluteConstraints(115,90,150,20) );

        aut = new JTextField( );
        aut.setEditable(true);
        aut.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
        aut.setForeground ( new Color ( 255, 0, 153 ) );
        aut.setCaretColor ( new Color ( 0, 204, 102 ) );
        bkpn.add ( aut, new AbsoluteConstraints( 115,90,150,20) );


        publisher = new JLabel("PUBLISHER");
        publisher.setForeground( new Color( 120, 120, 153 ));
        bkpn.add(publisher,new AbsoluteConstraints( 38,121) );

        PUBLISHER = new JComboBox();
        PUBLISHER.setEditable(true);
        PUBLISHER.setVisible(false);
        PUBLISHER.setForeground( new Color(255,0,153 ));
        PUBLISHER.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        bkpn.add(PUBLISHER,new AbsoluteConstraints(115,120,150,20) );

        pub1 = new JTextField( );
        pub1.setEditable(true);
        pub1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
        pub1.setForeground ( new Color ( 255, 0, 153 ) );
        pub1.setCaretColor ( new Color ( 0, 204, 102 ) );
        bkpn.add ( pub1, new AbsoluteConstraints( 115,120,150,20) );



        Search    = new JButton("SEARCH");
        Search.setBackground(Color.cyan);
        Search.setForeground(Color.magenta);
        Search.setMnemonic('S');
        Search.setBorder ( new BevelBorder (0));
        bkpn.add(Search, new AbsoluteConstraints(30,155,80,27));

        Next_Rec  = new JButton("NEXT-REC");
        Next_Rec.setEnabled(false);
        Next_Rec.setBackground(Color.cyan);
        Next_Rec.setForeground(Color.magenta);
        Next_Rec.setMnemonic('N');
        Next_Rec.setBorder ( new BevelBorder (0));
        bkpn.add(Next_Rec,new AbsoluteConstraints(70,190,80,27));

        print = new JButton( "PRINT" ) ;
        print.setBackground (Color.cyan);
        print.setForeground(Color.magenta);
        print.setBorder(new BevelBorder(0));
        print.setMnemonic('P');
        print.setVisible(false);
        bkpn.add(print,new AbsoluteConstraints(30,155,80,27));

        edit    = new JButton("EDIT");
        edit.setBackground(Color.cyan);
        edit.setForeground(Color.magenta);
        edit.setMnemonic('E');
        edit.setBorder ( new BevelBorder (0));
        bkpn.add(edit, new AbsoluteConstraints(152,190,80,27));


        clear = new JButton("CLEAR");
        clear.setBackground(Color.cyan);
        clear.setForeground(Color.magenta);
        clear.setMnemonic('C');
        clear.setBorder(new BevelBorder(0));
        bkpn.add(clear, new AbsoluteConstraints(116,155,80,27));

        Quit = new JButton("QUIT");
        Quit.setBackground(Color.cyan);
        Quit.setForeground(Color.magenta);
        Quit.setMnemonic('Q');
        Quit.setBorder ( new BevelBorder (0));
        bkpn.add(Quit , new AbsoluteConstraints(203,155,80,27 ));

        otpn = new JTextArea();
        otpn.setLayout(new AbsoluteLayout());
		    otpn.setEditable(false);
		    otpn.setEnabled(false);
        rtpn.add( otpn, new AbsoluteConstraints(20,20,460,250));
        JScrollPane spane =new JScrollPane(otpn, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rtpn.add( spane, new AbsoluteConstraints(20,20,460,250));

		    cacheData = new JTextArea();

        output1 = new JLabel("");
        //otpn.add(output1,new AbsoluteConstraints(100,20));

        output9=new JLabel("");
        //otpn.add (output9,new AbsoluteConstraints(10,35));

        output2 = new JLabel("");
        //otpn.add(output2,new AbsoluteConstraints(110,50));

        output3 = new JLabel("");
        //otpn.add(output3,new AbsoluteConstraints(110,70));

        output4 = new JLabel("");
        //otpn.add(output4,new AbsoluteConstraints(30,90));


        output5 = new JLabel("");
        //otpn.add(output5,new AbsoluteConstraints(110,110));

        output6 = new JLabel("");
        //otpn.add(output6,new AbsoluteConstraints(110,130));

        output7 = new JLabel("");
        //otpn.add(output7,new AbsoluteConstraints(110,150));

        output8 = new JLabel("");
        //otpn.add(output8,new AbsoluteConstraints(110,170));

        /*output1.setForeground(new Color(0,0,100));
        output2.setForeground(new Color(0,0,100));
        output3.setForeground(new Color(0,0,100));
        output4.setForeground(new Color(0,0,100));
        output5.setForeground(new Color(0,0,100));
        output6.setForeground(new Color(0,0,100));
        output7.setForeground(new Color(0,0,100));
        output8.setForeground(new Color(0,0,100));
        output9.setForeground(new Color(0,0,100));*/

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
                    //p.setFrame(Index.lib1);
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
                          //MyDriver.getConnection();
                          s = con.createStatement();
                        rs = s.executeQuery( "SELECT DISTINCT(BOOKNAME) FROM LIBRARY WHERE BOOKNAME LIKE "+"'"+title.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {

                              bname1.addItem(rs.getObject(1));
                              ++count;
                           }
                         if(count>0)
                          {
                             bname1.setVisible(true);
                             title.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,"Title: "+sq.getMessage());
                        }

                    }
            }
          );

          aut.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(AUTHOR1S) FROM LIBRARY WHERE AUTHOR1S LIKE "+"'"+aut.getText().toUpperCase()+"%"+"'");
                          //rs = s.executeQuery( "SELECT DISTINCT(AUTHOR1S),DISTINCT(AUTHOR1F) FROM LIBRARY WHERE AUTHOR1S LIKE "+"'"+aut.getText().toUpperCase()+"%"+"'"+"OR AUTHOR1F LIKE "+"'"+aut.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {

                              AUTHOR.addItem(rs.getObject(1));
                             // AUTHOR.addItem(rs.getObject(2));
                              ++count1;
                           }
                         if(count1>0)
                          {
                             AUTHOR.setVisible(true);
                             aut.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,"Author: "+sq.getMessage());
                        }

                    }
            }
          );

          pub1.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {
                          s = con.createStatement();
                        rs = s.executeQuery( "SELECT DISTINCT(PUBLISHER) FROM LIBRARY WHERE PUBLISHER LIKE "+"'"+pub1.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {

                              PUBLISHER.addItem(rs.getObject(1));
                              ++count2;
                           }
                         if(count2>0)
                          {
                             PUBLISHER.setVisible(true);
                             pub1.setVisible(false);
                          }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,"Publisher: "+ sq.getMessage());
                        }

                    }
            }
          );

          edit.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
 					otpn.setEditable(true);
 					otpn.setEnabled(true);
                 }
            }
          );
          Search.addActionListener( new ActionListener()
         {
           public void  actionPerformed(ActionEvent e)
             {
                 TITLE=(String)bname1.getSelectedItem();
                 if(TITLE==null || TITLE == ""){  TITLE = title.getText().toUpperCase();  }

                  AUT=(String)AUTHOR.getSelectedItem();
                  if(AUT==null || AUT== ""){  AUT = aut.getText().toUpperCase();  }

                   PUB=(String)PUBLISHER.getSelectedItem();
                   if(PUB==null || PUB== ""){  PUB = pub1.getText().toUpperCase();  }

  				 try
                 {

                   s = con.createStatement( );
                   rs = s.executeQuery( "SELECT COUNT(*) FROM LIBRARY" );
                   if(rs.next ())
                     {
                       xy = rs.getInt (1);
                     }

                   rs = s.executeQuery( "SELECT * FROM LIBRARY WHERE ACESSNO ="+"'"+ACESSNO.getText()+"'"+"OR PUBLISHER ="+"'"+PUB+"'"+"OR AUTHOR1S="+"'"+AUT+"'"+"OR AUTHOR1F="+"'"+AUT+"'"+"OR AUTHOR2S="+"'"+AUT+"'"+"OR AUTHOR2F="+"'"+AUT+"'"+"OR AUTHOR3S="+"'"+AUT+"'"+"OR AUTHOR3F ="+"'"+AUT+"'"+"OR BOOKNAME="+"'"+TITLE+"'" );

                   if( rs.next() ) {
					   out1=rs.getString(4);
					   out2=rs.getString(3);
					   out3=rs.getString(17);
					   out4=rs.getString(5);
					   out5=rs.getString(6);
				   //    if(out4==" "||out5==" ")
				   //    {

					   String reUseField16 = rs.getString(16);
					   String reUseField19 = rs.getString(19);

					   output1.setText(out1+" : "+out2+" : - ");
					   output2.setText(rs.getString (2)+" / "+out1+" . "+out2);
					   output3.setText( " . - "+reUseField19+" . - "+rs.getString(13)+" . - "+rs.getString (20)+" , "+reUseField16);
					   output4.setText(rs.getString(1));
					   output5.setText (rs.getString (17));
					   output6.setText (reUseField16);
					   output7.setText (rs.getString (15));
					   output8.setText (out3+" , "+rs.getString(18)+" . . . "+reUseField19+" . ");
					   output9.setText (out3);

					   String offString = new String("\t    ");
					   otpn.setText( offString.substring(0,2) + output1.getText() + "\n\n" +
					   				 offString + output2.getText() + "\n" +
					   				 offString + output3.getText() + "\n" +
					   				 "      "  + output4.getText() + "\n" +
					   				 offString + output5.getText() + "\n" +
					   				 offString + output6.getText() + "\n" +
					   				 offString + output7.getText() + "\n" +
					   				 offString + output8.getText() + "\n" +
					   				 offString + output9.getText()
					   			   );
					   cacheData.setText(otpn.getText());
				   //   }
				   //   else
				  //    {
				   /*    output1.setText(rs.getString (2)+"/"+out2+out1+"[et.al]");
					 //  output2.setText(out1+"."+out2);
					   output2.setText (rs.getString (5)+","+rs.getString(6)+".;"+rs.getString(7)+","+rs.getString(8)+".");
					   output3.setText( ".-"+rs.getString(10)+".-"+rs.getString(15)+".-"+rs.getString (9)+","+rs.getString(16));
					   output4.setText(rs.getString(1));
					   output5.setText (rs.getString (13));
					   output6.setText (rs.getString(12));
					   output7.setText (rs.getString (11));
					   output8.setText (out3+","+rs.getString(18)+"..."+rs.getString(19)+".");
					   output9.setText (out3);


					  } */

					   Next_Rec.setEnabled(true);
					   Search.setVisible(false);
					   print.setEnabled(true);
					   print.setVisible(true);
				   }
				   else {
						JOptionPane.showMessageDialog(null,"Please choose one of Filters");
				   }
                 }
               catch(SQLException e1)
                 {
                   JOptionPane.showMessageDialog(null,"Search: " + e1.getMessage());
                 }

             }
          }
        );

        Next_Rec.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent er)
              {
                if(!Next_Rec.getText().equals("Print All")) {
                try
                  {
                    if(rs.next())
                     {
                       while( wq <= xy ) {
                         out1=rs.getString(4);
                         out2=rs.getString(3);
                         out3=rs.getString(17);
                         out4=rs.getString(5);
                         out5=rs.getString(6);
                        // if(out4==" "||out5==" ")
                        // {
                       // JOptionPane.showMessageDialog(null,"143"+bname1.getSelectedItem());
					   String reUseField16 = rs.getString(16);
					   String reUseField19 = rs.getString(19);

					   output1.setText(out1+" : "+out2+" : - ");
					   output2.setText(rs.getString (2)+" / "+out1+" . "+out2);
					   output3.setText( " . - "+reUseField19+" . - "+rs.getString(13)+" . - "+rs.getString (20)+" , "+reUseField16);
					   output4.setText(rs.getString(1));
					   output5.setText (rs.getString (17));
					   output6.setText (reUseField16);
					   output7.setText (rs.getString (15));
					   output8.setText (out3+" , "+rs.getString(18)+" . . . "+reUseField19+" . ");
					   output9.setText (out3);

					   String offString = new String("\t    ");

					   otpn.setText( offString.substring(0,2) + output1.getText() + "\n\n" +
					   				 offString + output2.getText() + "\n" +
					   				 offString + output3.getText() + "\n" +
					   				 "      "  + output4.getText() + "\n" +
					   				 offString + output5.getText() + "\n" +
					   				 offString + output6.getText() + "\n" +
					   				 offString + output7.getText() + "\n" +
					   				 offString + output8.getText() + "\n" +
					   				 offString + output9.getText()
					   			   );
					   cacheData.append(otpn.getText());
                        //}
                     /*    else
                          {
                            output1.setText(rs.getString (2)+"/"+out2+out1+"[et.al]");
                         //  output2.setText(out1+"."+out2);
                            output2.setText (rs.getString (5)+","+rs.getString(6)+".;"+rs.getString(7)+","+rs.getString(8)+".");
                            output3.setText( ".-"+rs.getString(10)+".-"+rs.getString(15)+".-"+rs.getString (9)+","+rs.getString(16));
                            output4.setText(rs.getString(1));
                            output5.setText (rs.getString (13));
                            output6.setText (rs.getString(12));
                            output7.setText (rs.getString (11));
                            output8.setText (out3+","+rs.getString(18)+"..."+rs.getString(19)+".");
                            output9.setText (out3);
                           }
                              */
                       		wq++;
                       		break;
                         }
                     }
                    else
                     {

                       JOptionPane.showMessageDialog(null,"NO MORE RECORDS...!","BOOKSACCESSING",JOptionPane.INFORMATION_MESSAGE);

                       //Next_Rec.setEnabled (false);
                       //Next_Rec.setVisible(false);
                       print.setEnabled(true);
                       print.setVisible(true);
                       Next_Rec.setText("PRINT ALL");
                       Search.setEnabled (false);
                     }
                  }
                catch(SQLException e)
                  {
						JOptionPane.showMessageDialog(null,"NextRecord: "+e.getMessage());
				  }
			  }
			  else {
				  printData(cacheData);
			  }

              }
          }
        );
        print.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                       printData(otpn);
                 }
            }
          );

        clear.addActionListener(new ActionListener()
        {
         public void actionPerformed(ActionEvent e)
         {
           count=0;
           count1=0;
           count2=0;
           Search.setEnabled (true);
           AUTHOR.removeAllItems();
           AUTHOR.setSelectedItem("");
           bname1.removeAllItems();
           bname1.setSelectedItem("");
           PUBLISHER.removeAllItems();
           PUBLISHER.setSelectedItem ("");
           ACESSNO.setText ("");
           AUTHOR.setVisible(false);
           PUBLISHER.setVisible(false);
           bname1.setVisible(false);
           title.setVisible(true);
           pub1.setVisible(true);
           aut.setVisible(true);

           Next_Rec.setText("NEXT-REC");
           Next_Rec.setVisible(true);
           print.setVisible(false);
           Search.setVisible(true);

           System.gc();

           title.setText("");
           pub1.setText("");
           aut.setText("");

           output1.setText("");
           output2.setText("");
           output3.setText( "");
           output4.setText("");
           output5.setText ("");
           output6.setText ("");
           output7.setText ("");
           output8.setText ("");
           output9.setText ("");


           Next_Rec.setEnabled(false);

         }
        }
        );

       Quit.addActionListener (new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
               try{   con.close(); } catch (SQLException ew) {}
               setVisible(false);
             }
         }
       );
     }
private void printData(JTextArea printRecords) {
   /*try
	 {
		java.util.Date date = new java.util.Date();
		boolean success= printRecords.print( new MessageFormat("BOOKS CATALOG" + date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
									 null
								   );
	 }
   catch(PrinterException pex)
	 {
		JOptionPane.showMessageDialog(null,"printing:  "+pex.getMessage());
	 }*/
}

   public static void main(String args[])
     {
       Library1 rt=new Library1();
     }
  }
