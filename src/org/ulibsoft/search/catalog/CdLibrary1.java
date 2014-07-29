package org.ulibsoft.search.catalog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class CdLibrary1 extends JFrame
  {
   private JLabel     publisher,acessno,name;
   private JTextField PUBLISHER,ACESSNO,NAME;
   private JButton    Search,Next_Rec,Quit,clear;
   private JLabel     output1,output2,output3,output4,output5,output6,output7,output8,output9;
   private String    acn,pub,atr,output;
   private JPanel     bkpn,rtpn,otpn;
   private Container  c;
   private String out1,out2,out3,out4,out5;

   private int xy;
   private static int wq=0;

   private Statement  s;
   private Connection con;
   private ResultSet  rs;


   public CdLibrary1()
     {
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        show();

        createComponents();
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
        c.add(bkpn,new AbsoluteConstraints(235,20,310,195));

        rtpn = new JPanel(new AbsoluteLayout());
        rtpn.setBackground(c.getBackground());
        rtpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "REPORT-GENERATION", 1, 2, c.getFont(), Color.magenta));
        c.add(rtpn,new AbsoluteConstraints(120,230,550,300));

        publisher = new JLabel("PUBLISHER");
        publisher.setForeground( new Color( 120, 120, 153 ));
        bkpn.add(publisher,new AbsoluteConstraints( 38,31) );

        PUBLISHER = new JTextField("");
        PUBLISHER.setForeground( new Color(255,0,153 ));
        PUBLISHER.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        bkpn.add(PUBLISHER,new AbsoluteConstraints(115,30,150,20) );

        acessno   = new JLabel("ACCESSNO");
        acessno.setForeground(new Color(120,120,153));
        bkpn.add(acessno,new AbsoluteConstraints( 40,61) );

        ACESSNO   = new JTextField("");
        ACESSNO.setForeground(new Color(255,0,153));
        ACESSNO.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        bkpn.add(ACESSNO,new AbsoluteConstraints(115,60,150,20) );

        name    = new JLabel("TITLE");
        name.setForeground(new Color(120,120,153));
        bkpn.add(name,new AbsoluteConstraints( 55, 91) );

        NAME    = new JTextField("");
        NAME.setForeground(new Color(255,0,153));
        NAME.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add(NAME, new AbsoluteConstraints(115,90,150,20) );

        Search    = new JButton("SEARCH");
        Search.setBackground(Color.cyan);
        Search.setForeground(Color.magenta);
        Search.setMnemonic('S');
        Search.setBorder ( new BevelBorder (0));
        bkpn.add(Search, new AbsoluteConstraints(30,125,80,27));

        Next_Rec  = new JButton("NEXT-REC");
        Next_Rec.setEnabled(false);
        Next_Rec.setBackground(Color.cyan);
        Next_Rec.setForeground(Color.magenta);
        Next_Rec.setMnemonic('N');
        Next_Rec.setBorder ( new BevelBorder (0));
        bkpn.add(Next_Rec,new AbsoluteConstraints(100,160,120,27));

        clear = new JButton("CLEAR");
        clear.setBackground(Color.cyan);
        clear.setForeground(Color.magenta);
        clear.setMnemonic('C');
        clear.setBorder(new BevelBorder(0));
        bkpn.add(clear, new AbsoluteConstraints(116,125,80,27));

        Quit      = new JButton("QUIT");
        Quit.setBackground(Color.cyan);
        Quit.setForeground(Color.magenta);
        Quit.setMnemonic('Q');
        Quit.setBorder ( new BevelBorder (0));
        bkpn.add(Quit , new AbsoluteConstraints(203,125,80,27 ));

        otpn = new JPanel(new AbsoluteLayout());
        rtpn.add( otpn, new AbsoluteConstraints(20,20,510,265)  );
        otpn.setBackground ( Color.white );

        output1 = new JLabel("");
        otpn.add(output1,new AbsoluteConstraints(100,20));

        output9=new JLabel();
        otpn.add (output9,new AbsoluteConstraints(10,35));

        output2 = new JLabel("");
        otpn.add(output2,new AbsoluteConstraints(110,50));

        output3 = new JLabel("");
        otpn.add(output3,new AbsoluteConstraints(110,70));

        output4 = new JLabel("");
        otpn.add(output4,new AbsoluteConstraints(30,90));


        output5 = new JLabel("");
        otpn.add(output5,new AbsoluteConstraints(110,110));

        output6 = new JLabel("");
        otpn.add(output6,new AbsoluteConstraints(110,130));

        output7 = new JLabel("");
        otpn.add(output7,new AbsoluteConstraints(110,150));

        output8 = new JLabel("");
        otpn.add(output8,new AbsoluteConstraints(110,170));


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
                    //p.setFrame(Index.cdlib1);
                  }
               }
           }
         );
       Search.addActionListener( new ActionListener()
         {
           public void  actionPerformed(ActionEvent e)
             {
               try
                 {
                   s = con.createStatement( );
                   rs = s.executeQuery( "SELECT COUNT(*) FROM CDDETAILS" );
                   if(rs.next ())
                     {
                       xy = rs.getInt (1);
                     }
                 }
                 catch(SQLException se)
                 {
                  JOptionPane.showMessageDialog(null,se.getMessage());
                  se.printStackTrace();
                 }

             }
          }
        );

        Next_Rec.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent er)
              {
                try
                  {
                    if(rs.next())
                     {
                       while( wq <= xy )
                         {
                           wq++;
                           break;
                         }
                     }
                    else
                     {
                       JOptionPane.showMessageDialog(null,"No more Records");
                       Next_Rec.setEnabled (false);
                       Search.setEnabled (false);
                     }
                  }
                catch(SQLException e){}
              }
          }
        );

        clear.addActionListener(new ActionListener()
        {
         public void actionPerformed(ActionEvent e)
         {
           Search.setEnabled (true);
           NAME.setText ("");
           PUBLISHER.setText ("");
           ACESSNO.setText ("");
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
   public static void main(String args[])
     {
       CdLibrary1 rt=new CdLibrary1();
     }
  }
