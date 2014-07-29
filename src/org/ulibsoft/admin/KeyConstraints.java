package org.ulibsoft.admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class KeyConstraints extends JFrame
  {
      private File file;

      private JLabel book,cd,mag,std,std1,std2,stf,stf1,stf2;

      private JLabel stdbkmin, stdbkmax, stfbkmin, stfbkmax;
      private JLabel stdcdmin, stdcdmax, stfcdmin, stfcdmax;
      private JLabel stdmzmin, stdmzmax, stfmzmin, stfmzmax;

      private JLabel stdbkpd,stdcdpd,stdmzpd;

      private JLabel stdbkRpd,stdcdRpd,stdmzRpd;
      private JLabel stdbkRcount,stdcdRcount,stdmzRcount;

      private JLabel stfbk_Hld_From,stfbk_Hld_To;
      private JLabel stfbkhold,stfcdhold,stfmzhold;

      private JLabel stdbkFine,stdcdFine,stdmzFine;

      private JLabel imagePath;

      private JTextField stdbkmin2, stdbkmax2, stfbkmin2, stfbkmax2;
      private JTextField stdcdmin2, stdcdmax2, stfcdmin2, stfcdmax2;
      private JTextField stdmzmin2, stdmzmax2, stfmzmin2, stfmzmax2;

      private JTextField stdbkpd2,stdcdpd2,stdmzpd2;

      private JTextField stdbkRpd2,stdcdRpd2,stdmzRpd2;
      private JTextField stdbkRcount2,stdcdRcount2,stdmzRcount2;

      private JTextField stfbk_Hld_From2,stfbk_Hld_To2;
      private JTextField stfbkhold2,stfcdhold2,stfmzhold2;

      private JTextField stdbkFine2,stdcdFine2,stdmzFine2;
      private JTextField imagePath2;
      String result,result1;

      private JButton ins,up,quit,next;

      private Container c;
      private JPanel cmppn,pnl;

      private Connection  con ;
      private Statement s ;
      private ResultSet rs, rs1 ;

      public KeyConstraints()
        {
           super("INPUT CONSTRAINTS FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

           createComponents();
           try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
           try
           {
              s=con.createStatement();
              rs=s.executeQuery("SELECT * FROM KEYCONSTRAINTS WHERE ID=1");
              while(rs.next())
              {
                stdbkmin2.setText(rs.getString(2));
                stdbkmax2.setText(rs.getString(3));
                stdbkpd2.setText(rs.getString(4));
                
                stdcdmin2.setText(rs.getString(5));
                stdcdmax2.setText(rs.getString(6));
                stdcdpd2.setText(rs.getString(7));
                
                stdmzmin2.setText(rs.getString(8));
                stdmzmax2.setText(rs.getString(9));
                stdmzpd2.setText(rs.getString(10));
                
                stdbkRpd2.setText(rs.getString(11));
                stdbkRcount2.setText(rs.getString(12));
                
                stdcdRpd2.setText(rs.getString(13));
                stdcdRcount2.setText(rs.getString(14));
                
                stdmzRpd2.setText(rs.getString(15));
                stdmzRcount2.setText(rs.getString(16));
                
                stdbkFine2.setText(rs.getString(17));
                stdcdFine2.setText(rs.getString(18));
                stdmzFine2.setText(rs.getString(19));
                
                imagePath2.setText(rs.getString(22));

                
                
                

              }
              rs=s.executeQuery("SELECT MIN_BOOKS,MAX_BOOKS,BOOK_HOLDING_PERIOD,MIN_CDS,MAX_CDS,CD_HOLDING_PERIOD,MIN_MZS,"+
               "MAX_MZS,MZ_HOLDING_PERIOD,TO_CHAR(FROM_HOLD,'DD-MON-YYYY'),TO_CHAR(TO_HOLD,'DD-MON-YYYY'),IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID=2");
              while(rs.next())
              {
                stfbkmin2.setText(rs.getString(1));
                stfbkmax2.setText(rs.getString(2));
                stfbkhold2.setText(rs.getString(3));

                stfcdmin2.setText(rs.getString(4));
                stfcdmax2.setText(rs.getString(5));
                stfcdhold2.setText(rs.getString(6));
                
                stfmzmin2.setText(rs.getString(7));
                stfmzmax2.setText(rs.getString(8));
                stfmzhold2.setText(rs.getString(9));

                stfbk_Hld_From2.setText(rs.getString(10));
                stfbk_Hld_To2.setText(rs.getString(11));
                
                imagePath2.setText(rs.getString(12));
                ins.setEnabled(false);
              }
              s.close();

           }
           catch(SQLException sqlex)
           {

            JOptionPane.showMessageDialog(null,"error:"+sqlex.getMessage());
           }
           componentListener();
        }

      private void createComponents()
        {
           c = getContentPane( ) ;
           c.setBackground( new Color(0,0,40) ) ;
           c.setLayout ( new AbsoluteLayout( ) ) ;

           cmppn = new JPanel( new AbsoluteLayout() );
           cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INPUT -- CONSTRAINTS", 1, 2, c.getFont(), Color.magenta));
           cmppn.setBackground(c.getBackground());
           c.add(cmppn,new AbsoluteConstraints(50,60,690,480));

           cmppn.add(std = new JLabel("==>  STUDENT "),new AbsoluteConstraints(50,47));
           std.setForeground(Color.cyan);

           cmppn.add(std1 = new JLabel("==>  STUDENT "),new AbsoluteConstraints(270,47));
           std1.setForeground(Color.cyan);

           cmppn.add(std2 = new JLabel("==>  STUDENT "),new AbsoluteConstraints(490,47));
           std2.setForeground(Color.cyan);

           cmppn.add(stf = new JLabel("==>  PROFESSOR"),new AbsoluteConstraints(50,245));
           stf.setForeground(Color.cyan);

           cmppn.add(stf1 = new JLabel("==>  PROFESSOR"),new AbsoluteConstraints(270,245));
           stf1.setForeground(Color.cyan);

           cmppn.add(stf2 = new JLabel("==>  PROFESSOR"),new AbsoluteConstraints(490,245));
           stf2.setForeground(Color.cyan);

           cmppn.add(book = new JLabel("==>  BOOK"),new AbsoluteConstraints(30,23));
           book.setForeground(Color.green);

           cmppn.add(stdbkmin = new JLabel("MINIMUM   BOOKS  :"),new AbsoluteConstraints(30,90-15));
           stdbkmin.setForeground(new Color(120,120,153));
           cmppn.add(stdbkmin2 = new JTextField(),new AbsoluteConstraints(150,88-15,60,20));
           stdbkmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkmax = new JLabel("MAXIMUM  BOOKS :"),new AbsoluteConstraints(30,120-17));
           stdbkmax.setForeground(new Color(120,120,153));
           cmppn.add(stdbkmax2 = new JTextField(),new AbsoluteConstraints(150,118-17,60,20));
           stdbkmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkpd = new JLabel("> HOLDING  PERIOD"),new AbsoluteConstraints(30,150-19));
           stdbkpd.setForeground(new Color(120,120,153));
           cmppn.add(stdbkpd2 = new JTextField(),new AbsoluteConstraints(150,148-19,60,20));
           stdbkpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkRpd = new JLabel("> RENIVEL PERIOD :"),new AbsoluteConstraints(30,180-21));
           stdbkRpd.setForeground(new Color(120,120,153));
           cmppn.add(stdbkRpd2 = new JTextField(),new AbsoluteConstraints(150,178-21,60,20));
           stdbkRpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkRcount = new JLabel("NO_OF_RENIVELS  :"),new AbsoluteConstraints(30,210-23));
           stdbkRcount.setForeground(new Color(120,120,153));
           cmppn.add(stdbkRcount2 = new JTextField(),new AbsoluteConstraints(150,208-23,60,20));
           stdbkRcount2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkFine = new JLabel("<< FINE PER DAY >>"),new AbsoluteConstraints(30,240-25));
           stdbkFine.setForeground(new Color(120,120,153));
           cmppn.add(stdbkFine2 = new JTextField(),new AbsoluteConstraints(150,238-25,60,20));
           stdbkFine2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(cd = new JLabel("==> FLOPPYS"),new AbsoluteConstraints(250,23));
           cd.setForeground(Color.green);

           cmppn.add(stdcdmin = new JLabel("<< MINIMUM CDS >>"),new AbsoluteConstraints(250,90-15));
           stdcdmin.setForeground(new Color(120,120,153));
           cmppn.add(stdcdmin2 = new JTextField(),new AbsoluteConstraints(250+125,90-2-15,60,20));
           stdcdmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdmax = new JLabel(">>> MAXIMUM CDS :"),new AbsoluteConstraints(250,120-17));
           stdcdmax.setForeground(new Color(120,120,153));
           cmppn.add(stdcdmax2 = new JTextField(),new AbsoluteConstraints(250+125,120-2-17,60,20));
           stdcdmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdpd = new JLabel(">> HOLDING PERIOD"),new AbsoluteConstraints(250,150-19));
           stdcdpd.setForeground(new Color(120,120,153));
           cmppn.add(stdcdpd2 = new JTextField(),new AbsoluteConstraints(250+125,150-2-19,60,20));
           stdcdpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdRpd = new JLabel(">> RENIVEL PERIOD :"),new AbsoluteConstraints(250,180-21));
           stdcdRpd.setForeground(new Color(120,120,153));
           cmppn.add(stdcdRpd2 = new JTextField(),new AbsoluteConstraints(250+125,180-2-21,60,20));
           stdcdRpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdRcount = new JLabel("> NO_OF_RENIVELS :"),new AbsoluteConstraints(250,210-23));
           stdcdRcount.setForeground(new Color(120,120,153));
           cmppn.add(stdcdRcount2 = new JTextField(),new AbsoluteConstraints(250+125,210-2-23,60,20));
           stdcdRcount2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdFine = new JLabel("<<  FINE PER DAY  >>"),new AbsoluteConstraints(250,240-25));
           stdcdFine.setForeground(new Color(120,120,153));
           cmppn.add(stdcdFine2 = new JTextField(),new AbsoluteConstraints(250+125,240-2-25,60,20));
           stdcdFine2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(mag = new JLabel("==>  MAGAZINE"),new AbsoluteConstraints(470,23));
           mag.setForeground(Color.green);

           cmppn.add(stdmzmin = new JLabel("MINIMUM  JOURNALS :"),new AbsoluteConstraints(470,90-15));
           stdmzmin.setForeground(new Color(120,120,153));
           cmppn.add(stdmzmin2 = new JTextField(),new AbsoluteConstraints(470+135,90-2-15,60,20));
           stdmzmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzmax = new JLabel("MAXIMUM  JOURNALS"),new AbsoluteConstraints(470,120-17));
           stdmzmax.setForeground(new Color(120,120,153));
           cmppn.add(stdmzmax2 = new JTextField(),new AbsoluteConstraints(470+135,120-2-17,60,20));
           stdmzmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzpd = new JLabel(">>  HOLDING  PERIOD  :"),new AbsoluteConstraints(470,150-19));
           stdmzpd.setForeground(new Color(120,120,153));
           cmppn.add(stdmzpd2 = new JTextField(),new AbsoluteConstraints(470+135,150-2-19,60,20));
           stdmzpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzRpd = new JLabel("<< RENIVEL PERIOD >>"),new AbsoluteConstraints(470,180-21));
           stdmzRpd.setForeground(new Color(120,120,153));
           cmppn.add(stdmzRpd2 = new JTextField(),new AbsoluteConstraints(470+135,180-2-21,60,20));
           stdmzRpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzRcount = new JLabel(">>  NO_OF_RENIVELS  :"),new AbsoluteConstraints(470,210-23));
           stdmzRcount.setForeground(new Color(120,120,153));
           cmppn.add(stdmzRcount2 = new JTextField(),new AbsoluteConstraints(470+135,210-2-23,60,20));
           stdmzRcount2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzFine = new JLabel("<<  FINE - PER - DAY  >>"),new AbsoluteConstraints(470,240-25));
           stdmzFine.setForeground(new Color(120,120,153));
           cmppn.add(stdmzFine2 = new JTextField(),new AbsoluteConstraints(470+135,240-2-25,60,20));
           stdmzFine2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           //STAFF

           cmppn.add(stfbkmin = new JLabel("MINIMUM   BOOKS  :"),new AbsoluteConstraints(30,300-25));
           stfbkmin.setForeground(new Color(120,120,153));
           cmppn.add(stfbkmin2 = new JTextField(),new AbsoluteConstraints(150,298-25,60,20));
           stfbkmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfbkmax = new JLabel("MAXIMUM  BOOKS :"),new AbsoluteConstraints(30,330-27));
           stfbkmax.setForeground(new Color(120,120,153));
           cmppn.add(stfbkmax2 = new JTextField(),new AbsoluteConstraints(150,328-27,60,20));
           stfbkmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfbkhold = new JLabel("HOLDING PERIOD :"),new AbsoluteConstraints(30,360-29));
           stfbkhold.setForeground(new Color(120,120,153));
           cmppn.add(stfbkhold2 = new JTextField(),new AbsoluteConstraints(150,358-29,60,20));
           stfbkhold2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(stfcdmin = new JLabel("<< MINIMUM CDS >>"),new AbsoluteConstraints(250,300-29));
           stfcdmin.setForeground(new Color(120,120,153));
           cmppn.add(stfcdmin2 = new JTextField(),new AbsoluteConstraints(250+125,300-29-2,60,20));
           stfcdmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfcdmax = new JLabel(">>> MAXIMUM CDS :"),new AbsoluteConstraints(250,330-31));
           stfcdmax.setForeground(new Color(120,120,153));
           cmppn.add(stfcdmax2 = new JTextField(),new AbsoluteConstraints(250+125,330-2-31,60,20));
           stfcdmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfcdhold = new JLabel("   HOLDING PERIOD"),new AbsoluteConstraints(250,360-33));
           stfcdhold.setForeground(new Color(120,120,153));
           cmppn.add(stfcdhold2 = new JTextField(),new AbsoluteConstraints(250+125,358-33,60,20));
           stfcdhold2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(stfmzmin = new JLabel("MINIMUM  JOURNALS :"),new AbsoluteConstraints(470,300-33));
           stfmzmin.setForeground(new Color(120,120,153));
           cmppn.add(stfmzmin2 = new JTextField(),new AbsoluteConstraints(470+135,300-2-33,60,20));
           stfmzmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfmzmax = new JLabel("MAXIMUM  JOURNALS"),new AbsoluteConstraints(470,330-35));
           stfmzmax.setForeground(new Color(120,120,153));
           cmppn.add(stfmzmax2 = new JTextField(),new AbsoluteConstraints(470+135,330-2-35,60,20));
           stfmzmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfmzhold = new JLabel("  HOLDING PERIOD   "),new AbsoluteConstraints(470,360-37));
           stfmzhold.setForeground(new Color(120,120,153));
           cmppn.add(stfmzhold2 = new JTextField(),new AbsoluteConstraints(470+135,358-2-37,60,20));
           stfmzhold2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(stfbk_Hld_From = new JLabel("STAFF  BOOKS / CDS / MAGAZINES /JOURNALS HOLDIG  PERIOD  FROM :"),new AbsoluteConstraints(30,360-9+15));
           stfbk_Hld_From.setForeground(new Color(120,120,153));

           cmppn.add(stfbk_Hld_From2 = new JTextField(),new AbsoluteConstraints(440,358-39+30+15,90,20));
           stfbk_Hld_From2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfbk_Hld_To = new JLabel("TO :"),new AbsoluteConstraints(545,360-39+30+15));
           stfbk_Hld_To.setForeground(new Color(120,120,153));

           cmppn.add(stfbk_Hld_To2 = new JTextField(),new AbsoluteConstraints(575,358-39+30+15,90,20));
           stfbk_Hld_To2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(imagePath = new JLabel("IMAGE  PATH  FOR  STUDENT / PROFESSOR :"),new AbsoluteConstraints(30,390-41+30+15));
           imagePath.setForeground(new Color(120,120,153));

           cmppn.add(imagePath2 = new JTextField(),new AbsoluteConstraints(285,388-41+30+15,380,20));
           imagePath2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           ins = new JButton( "INSERT" ) ;
           ins.setBackground ( Color.cyan  );
           ins.setForeground( Color .black );
           ins.setMnemonic('S');
           ins.setBorder ( new BevelBorder ( 0 ));
           cmppn.add ( ins, new AbsoluteConstraints( 85+40, 380+5+30+15,110,27 ) );

           up = new JButton( "UPDATE" ) ;
           up.setBackground ( Color.cyan  );
           up.setForeground( Color .black );
           up.setMnemonic('A');
           //up.setEnabled(false);
           up.setBorder ( new BevelBorder ( 0 ));
           cmppn.add ( up, new AbsoluteConstraints( 197+40, 380+35+15,110,27 ) );

           next = new JButton( "NEXT>>>" ) ;
           next.setBackground ( Color.cyan );
           next.setForeground(  Color .black );
           next.setMnemonic('N');
           next.setBorder ( new BevelBorder (0));
           cmppn.add ( next, new AbsoluteConstraints( 308+40,380+35+15,110,27 ) );

           quit = new JButton( "EXIT" ) ;
           quit.setBackground ( Color.cyan );
           quit.setMnemonic('X');
           quit.setForeground( Color .black );
           quit.setBorder ( new BevelBorder (0));
           cmppn.add ( quit, new AbsoluteConstraints( 420+40,380+35+15,110,27 ) );

           setVisible(true);
        }


      private void componentListener()
        {

           ins.addActionListener(new ActionListener()
              {
                 public void actionPerformed( ActionEvent e )
                   {


                              file=new File(imagePath2.getText());
                              if(!file.isDirectory())
                                    {
                                            int cfirm=JOptionPane.showConfirmDialog(null,"This Directory does not exists\n Create it?","Confirmation",JOptionPane.YES_NO_OPTION);
                                            if(cfirm==0)
                                            {
                                               boolean create= file.mkdir();
                                               if(create==true)
                                               JOptionPane.showMessageDialog(null,"Created Succesfully");
                                            }
                                    }


                      try
                        {


                           s = con.createStatement();

                           int a = s.executeUpdate("INSERT INTO KEYCONSTRAINTS(ID,MIN_BOOKS,MAX_BOOKS,BOOK_HOLDING_PERIOD,MIN_CDS,MAX_CDS,CD_HOLDING_PERIOD,"+
                                                                              "MIN_MZS,MAX_MZS,MZ_HOLDING_PERIOD,RENEIVAL_PERIOD_BOOK,NO_OF_RENEIVALS_BOOK,"+
                                                                               "RENEIVAL_PERIOD_CD,NO_OF_RENEIVALS_CD,RENEIVAL_PERIOD_MZ,NO_OF_RENEIVALS_MZ,"+
                                                                               "FINE_BOOK,FINE_CD,FINE_MZ,"+
                                                                               "IMAGE_PATH) VALUES(1"+","
                                                                               +stdbkmin2.getText()+","+stdbkmax2.getText()+","+stdbkpd2.getText()+","
                                                                               +stdcdmin2.getText()+","+stdcdmax2.getText()+","+stdcdpd2.getText()+","
                                                                               +stdmzmin2.getText()+","+stdmzmax2.getText()+","+stdmzpd2.getText()+","
                                                                               +stdbkRpd2.getText()+","+stdbkRcount2.getText()+","
                                                                               +stdcdRpd2.getText()+","+stdcdRcount2.getText()+","
                                                                               +stdmzRpd2.getText()+","+stdmzRcount2.getText()+","
                                                                               +stdbkFine2.getText()+","+stdcdFine2.getText()+","+stdmzFine2.getText()+","
                                                                               +"'"+imagePath2.getText()+"/'"+")");


                          con.commit();


                          int b= s.executeUpdate("INSERT INTO KEYCONSTRAINTS(ID,MIN_BOOKS,MAX_BOOKS,BOOK_HOLDING_PERIOD,MIN_CDS,MAX_CDS,CD_HOLDING_PERIOD,"+
                                                                              "MIN_MZS,MAX_MZS,MZ_HOLDING_PERIOD,FROM_HOLD,TO_HOLD,IMAGE_PATH) VALUES(2,"
                                                                               +stfbkmin2.getText()+","+stfbkmax2.getText()+","+stfbkhold2.getText()+","
                                                                               +stfcdmin2.getText()+","+stfcdmax2.getText()+","+stfcdhold2.getText()+","
                                                                               +stfmzmin2.getText()+","+stfmzmax2.getText()+","+stfmzhold2.getText()+","
                                                                               +"TO_CHAR(TO_DATE('"+stfbk_Hld_From2.getText()+"'),'DD-MON-YYYY')"+","+"TO_CHAR(TO_DATE('"+stfbk_Hld_To2.getText()+"'),'DD-MON-YYYY')"+","
                                                                               +"'"+imagePath2.getText()+"/'"+")");

                          con.commit();
                          ins.setEnabled(false);



                        }
                      catch( SQLException sqlex )
                        {
                             if(sqlex.getErrorCode()==936)
                            {
                                JOptionPane.showMessageDialog(null,"PLEASE ENTER ALL CONSTRAINTS");
                            }

                        }
                   }
              }
           );
           up.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
             {
                   file=new File(imagePath2.getText());
                              if(!file.isDirectory())
                                    {
                                            int cfirm=JOptionPane.showConfirmDialog(null,"This Directory does not exists\n Create it?","Confirmation",JOptionPane.YES_NO_OPTION);
                                            if(cfirm==0)
                                            {
                                               boolean create= file.mkdir();
                                               if(create==true)
                                               JOptionPane.showMessageDialog(null,"Created Succesfully");
                                            }
                                    }
                try
                {
                 s=con.createStatement();
                 int a1=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MIN_BOOKS="+stdbkmin2.getText()+" WHERE ID=1");
                 int a2=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MAX_BOOKS="+stdbkmax2.getText()+" WHERE ID=1");
                 int a3=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MIN_CDS="+stdcdmin2.getText()+" WHERE ID=1");
                 int a4=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MAX_CDS="+stdcdmax2.getText()+" WHERE ID=1");
                 int a5=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MIN_MZS="+stdmzmin2.getText()+" WHERE ID=1");
                 int a6=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MAX_MZS="+stdmzmax2.getText()+" WHERE ID=1");
                 int a7=s.executeUpdate("UPDATE KEYCONSTRAINTS SET BOOK_HOLDING_PERIOD="+stdbkpd2.getText()+" WHERE ID=1");
                 int a8=s.executeUpdate("UPDATE KEYCONSTRAINTS SET CD_HOLDING_PERIOD="+stdcdpd2.getText()+" WHERE ID=1");
                 int a9=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MZ_HOLDING_PERIOD="+stdmzpd2.getText()+" WHERE ID=1");
                 int a10=s.executeUpdate("UPDATE KEYCONSTRAINTS SET RENEIVAL_PERIOD_BOOK="+stdbkRpd2.getText()+" WHERE ID=1");
                 int a11=s.executeUpdate("UPDATE KEYCONSTRAINTS SET RENEIVAL_PERIOD_CD="+stdcdRpd2.getText()+" WHERE ID=1");
                 int a12=s.executeUpdate("UPDATE KEYCONSTRAINTS SET RENEIVAL_PERIOD_MZ="+stdmzRpd2.getText()+" WHERE ID=1");
                 int a13=s.executeUpdate("UPDATE KEYCONSTRAINTS SET NO_OF_RENEIVALS_BOOK="+stdbkRcount2.getText()+" WHERE ID=1");
                 int a14=s.executeUpdate("UPDATE KEYCONSTRAINTS SET NO_OF_RENEIVALS_CD="+stdcdRcount2.getText()+" WHERE ID=1");
                 int a15=s.executeUpdate("UPDATE KEYCONSTRAINTS SET NO_OF_RENEIVALS_MZ="+stdmzRcount2.getText()+" WHERE ID=1");
                 int a16=s.executeUpdate("UPDATE KEYCONSTRAINTS SET FINE_BOOK="+stdbkFine2.getText()+" WHERE ID=1");
                 int a17=s.executeUpdate("UPDATE KEYCONSTRAINTS SET FINE_CD="+stdcdFine2.getText()+" WHERE ID=1");
                 int a18=s.executeUpdate("UPDATE KEYCONSTRAINTS SET FINE_MZ="+stdmzFine2.getText()+" WHERE ID=1");
                 int a19=s.executeUpdate("UPDATE KEYCONSTRAINTS SET IMAGE_PATH='"+imagePath2.getText()+"/' WHERE ID=1");

                 con.commit();


                 int b1=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MIN_BOOKS="+stfbkmin2.getText()+" WHERE ID=2");
                 int b2=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MAX_BOOKS="+stfbkmax2.getText()+" WHERE ID=2");
                 int b3=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MIN_CDS="+stfcdmin2.getText()+" WHERE ID=2");
                 int b4=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MAX_CDS="+stfcdmax2.getText()+" WHERE ID=2");
                 int b5=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MIN_MZS="+stfmzmin2.getText()+" WHERE ID=2");
                 int b6=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MAX_MZS="+stfmzmax2.getText()+" WHERE ID=2");


                 int b7=s.executeUpdate("UPDATE KEYCONSTRAINTS SET BOOK_HOLDING_PERIOD="+stfbkhold2.getText()+" WHERE ID=2");
                 int b8=s.executeUpdate("UPDATE KEYCONSTRAINTS SET CD_HOLDING_PERIOD="+stfcdhold2.getText()+" WHERE ID=2");
                 int b9=s.executeUpdate("UPDATE KEYCONSTRAINTS SET MZ_HOLDING_PERIOD="+stfmzhold2.getText()+" WHERE ID=2");
                 int b10=s.executeUpdate("UPDATE KEYCONSTRAINTS SET FROM_HOLD=TO_CHAR(TO_DATE('"+stfbk_Hld_From2.getText()+"'),'DD-MON-YYYY') WHERE ID=2");
                 int b11=s.executeUpdate("UPDATE KEYCONSTRAINTS SET TO_HOLD=TO_CHAR(TO_DATE('"+stfbk_Hld_To2.getText()+"'),'DD-MON-YYYY') WHERE ID=2");
                 int b12=s.executeUpdate("UPDATE KEYCONSTRAINTS SET IMAGE_PATH='"+imagePath2.getText()+"/' WHERE ID=2");
                 con.commit();
                 JOptionPane.showMessageDialog(null,"UPDATED");

                 s.close();
               }
               catch( SQLException sqlex )
                        {
                            if(sqlex.getErrorCode()==936)
                            {
                                JOptionPane.showMessageDialog(null,"PLEASE ENTER ALL CONSTRAINTS");
                            }
                        }

             }
           }
           );


           next.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
             {
                //RESET STUDENT FEILD CONSTRAINTS
                stdbkmin2.setText("");
                stdbkmax2.setText("");
                stdcdmin2.setText("");
                stdcdmax2.setText("");
                stdmzmin2.setText("");
                stdmzmax2.setText("");
                stdbkFine2.setText("");
                stdbkpd2.setText("");
                stdcdFine2.setText("");
                stdcdpd2.setText("");
                stdmzFine2.setText("");
                stdmzpd2.setText("");
                stdbkRcount2.setText("");
                stdcdRcount2.setText("");
                stdmzRcount2.setText("");
                stdbkRpd2.setText("");
                stdcdRpd2.setText("");
                stdmzRpd2.setText("");
                //RESET IMAGE FEILD   CONSTRAINTS
                imagePath2.setText("");
                //RESET STAFF FIELDS CONSTRAINTS
                stfbkmin2.setText("");
                stfbkmax2.setText("");
                stfcdmin2.setText("");
                stfcdmax2.setText("");
                stfmzmin2.setText("");
                stfmzmax2.setText("");
                stfbk_Hld_From2.setText("");
                stfbk_Hld_To2.setText("");
                stfbkhold2.setText("");
                stfcdhold2.setText("");
                stfmzhold2.setText("");
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

      public static void main(String a[])
        {
           new KeyConstraints();
        }

  }


