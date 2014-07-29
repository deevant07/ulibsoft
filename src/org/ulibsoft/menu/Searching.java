package org.ulibsoft.menu;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;
import org.ulibsoft.admin.LibraryShedule1;
import org.ulibsoft.search.defaulters.FineReport;
import org.ulibsoft.search.entitySearch.ArchiveBookTrans;
import org.ulibsoft.search.entitySearch.ArchiveBooksSearch;
import org.ulibsoft.search.entitySearch.ArchiveCdTrans;
import org.ulibsoft.search.entitySearch.ArchiveCdsSearch;
import org.ulibsoft.search.entitySearch.ArchiveMzTrans;
import org.ulibsoft.search.entitySearch.ArchiveMzsSearch;
import org.ulibsoft.search.entitySearch.AvailableBooksSearch;
import org.ulibsoft.search.entitySearch.AvailableCdsSearch;
import org.ulibsoft.search.entitySearch.AvailableMzsSearch;
import org.ulibsoft.search.entitySearch.BookSearch;
import org.ulibsoft.search.entitySearch.CdSearch;
import org.ulibsoft.search.entitySearch.MagezineSearch;
import org.ulibsoft.search.entitySearch.StaffSearch;
import org.ulibsoft.search.entitySearch.StudentSearch;
import org.ulibsoft.search.record.BookRecord;
import org.ulibsoft.search.record.CDRecord;
import org.ulibsoft.search.record.MzRecord;
import org.ulibsoft.search.record.StaffRecord;
import org.ulibsoft.search.record.StudentRecord;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;

public class Searching extends JFrame
  {
     private JLabel but0,time1,time2;
     private JButton but1,but2,but3,but4,but5,but6,but17,but18,but19,but20,but21,but22,but23;
     private JButton but7,but8,but9,but10,but11,but12,but13,but14,but15,but16;

     private JPanel cmppn;
     private Container c;

     private JLabel img,img1,img2,img3,img4,img5,img6;
     private ImageIcon icon,icon1,icon2,icon3,icon4,icon5;

     private String path="images/";

     public Searching()
       {
           super("INPUT CONSTRAINTS FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

           createComponents();
           //odbcConnection();

           if(StudentAccess.SEARCH==1||StaffAccess.SEARCH==1)
             {
                but20.setForeground(Color.magenta);
                but12.setForeground(Color.magenta);
                but15.setForeground(Color.magenta);
                but16.setForeground(Color.magenta);
                componentListener2();
             }
           else
             {
                componentListener1();
             }
       }

    private void createComponents()
       {
           c = getContentPane( ) ;
           c.setBackground( new Color(0,0,40) ) ;
           c.setLayout ( new AbsoluteLayout( ) ) ;

           img = new JLabel( );
           icon=new ImageIcon(path+"earth1.gif");
           img.setIcon(icon);
           c.add(img,new AbsoluteConstraints(380,280));

           img1 = new JLabel( );
           icon1=new ImageIcon(path+"earth1.gif");
           img1.setIcon(icon1);
           c.add(img1,new AbsoluteConstraints(325,280));

           img2 = new JLabel( );
           icon2=new ImageIcon(path+"earth1.gif");
           img2.setIcon(icon2);
           c.add(img2,new AbsoluteConstraints(435,280));

           img3 = new JLabel( );
           icon3=new ImageIcon(path+"earth1.gif");
           img3.setIcon(icon3);
           c.add(img3,new AbsoluteConstraints(380,225));

           img4 = new JLabel( );
           icon4=new ImageIcon(path+"earth1.gif");
           img4.setIcon(icon4);
           c.add(img4,new AbsoluteConstraints(380,335));

           but0 = new JLabel( "              SEARCH  DATABASE" ) ;
           but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
           but0.setBackground ( Color.pink );
           but0.setForeground(Color.black);
           c.add ( but0, new AbsoluteConstraints( 260+30+5, 30, 200, 30 ) );

           but12 = new JButton( "" ) ;
           but12.setBackground ( Color.cyan );
           but12.setForeground(Color.black);
           but12.setEnabled(false);
           but12.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but12, new AbsoluteConstraints( 260+30+5, 30, 200, 30 ) );

           but23 = new JButton( "" ) ;
           but23.setBackground ( Color.pink );
           but23.setForeground(Color.black);
           but23.setEnabled(false);
           but23.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
           c.add ( but23, new AbsoluteConstraints( 267+30+5, 37, 200, 30 ) );


           but1 = new JButton( "STUDENTS  DATABASE" ) ;
           but1.setBackground ( Color.cyan );
           but1.setForeground(Color.black);
           //but1.setMnemonic('N');
           but1.setBorder(new BevelBorder(0));
           c.add ( but1, new AbsoluteConstraints( 50+140, 85, 200, 30 ) );

           but3 = new JButton( "ARCHIVE  BOOKS" ) ;
           but3.setBackground ( Color.cyan );
           but3.setForeground(Color.black);
           //but3.setMnemonic('N');
           but3.setBorder(new BevelBorder(0));
           c.add ( but3, new AbsoluteConstraints( 50+70, 125, 200, 30 ) );

           but5 = new JButton( "ARCHIVE  MAGAZINES" ) ;
           but5.setBackground ( Color.cyan );
           but5.setForeground(Color.black);
           //but5.setMnemonic('N');
           but5.setBorder(new BevelBorder(0));
           c.add ( but5, new AbsoluteConstraints( 50+50, 165, 200, 30 ) );

           but6 = new JButton( "CURRENT  CD  TRANSACTIONS" ) ;
           but6.setBackground ( Color.cyan );
           but6.setForeground(Color.black);
           //but6.setMnemonic('N');
           but6.setBorder(new BevelBorder(0));
           c.add ( but6, new AbsoluteConstraints( 50+30, 205, 200, 30 ) );

           but13 = new JButton( "STUDENT  RECORD" ) ;
           but13.setBackground ( Color.cyan );
           but13.setForeground(Color.black);
           //but13.setMnemonic('N');
           but13.setBorder(new BevelBorder(0));
           c.add ( but13, new AbsoluteConstraints( 50+10, 245, 200, 30 ) );

           but9 = new JButton( "BOOK  RECORD" ) ;
           but9.setBackground ( Color.cyan );
           but9.setForeground(Color.black);
           //but9.setMnemonic('N');
           but9.setBorder(new BevelBorder(0));
           c.add ( but9, new AbsoluteConstraints( 50-10, 285, 200, 30 ) );

           but11 = new JButton( "MAGAZINE / JOURNAL  RECORD" ) ;
           but11.setBackground ( Color.cyan );
           but11.setForeground(Color.black);
           //but11.setMnemonic('N');
           but11.setBorder(new BevelBorder(0));
           c.add ( but11, new AbsoluteConstraints( 50+10, 325, 200, 30 ) );

           but15 = new JButton( "AVAILABLE  CDS / FLOPPYS " ) ;
           but15.setBackground ( Color.cyan );
           but15.setForeground(Color.black);
           //but15.setMnemonic('N');
           but15.setBorder(new BevelBorder(0));
           c.add ( but15, new AbsoluteConstraints( 50+30, 365, 200, 30 ) );

           but17 = new JButton( "ARCHIVE BOOK TRANSACTIONS" ) ;
           but17.setBackground ( Color.cyan );
           but17.setForeground(Color.black);
           //but17.setMnemonic('N');
           but17.setBorder(new BevelBorder(0));
           c.add ( but17, new AbsoluteConstraints( 50+50, 405, 200, 30 ) );

           but19 = new JButton( "ARCHIVE MAGAZINE TRANSACTIONS" ) ;
           but19.setBackground ( Color.cyan );
           but19.setForeground(Color.black);
           //but19.setMnemonic('N');
           but19.setBorder(new BevelBorder(0));
           c.add ( but19, new AbsoluteConstraints( 50+70, 445, 200, 30 ) );

           but21 = new JButton( "FINE  REPORT" ) ;
           but21.setBackground ( Color.cyan );
           but21.setForeground(Color.black);
           //but21.setMnemonic('N');
           but21.setBorder(new BevelBorder(0));
           c.add ( but21, new AbsoluteConstraints( 50+140, 485, 200, 30 ) );

           but2 = new JButton( "STAFF  DATABASE" ) ;
           but2.setBackground ( Color.cyan );
           but2.setForeground(Color.black);
           //but2.setMnemonic('N');
           but2.setBorder(new BevelBorder(0));
           c.add ( but2, new AbsoluteConstraints( 555-140, 85, 200, 30 ) );

           but4 = new JButton( "ARCHIVE  CDS / FLOPPYS" ) ;
           but4.setBackground ( Color.cyan );
           but4.setForeground(Color.black);
           //but4.setMnemonic('N');
           but4.setBorder(new BevelBorder(0));
           c.add ( but4, new AbsoluteConstraints( 555-70, 125, 200, 30 ) );

           but10 = new JButton( "CURRENT BOOK TRANSACTIONS" ) ;
           but10.setBackground ( Color.cyan );
           but10.setForeground(Color.black);
           //but10.setMnemonic('N');
           but10.setBorder(new BevelBorder(0));
           c.add ( but10, new AbsoluteConstraints( 555-50, 165, 200, 30 ) );

           but7 = new JButton( "CURRENT  MAGAZINE  TRANSACTIONS" ) ;
           but7.setBackground ( Color.cyan );
           but7.setForeground(Color.black);
          // but7.setMnemonic('N');
           but7.setBorder(new BevelBorder(0));
           c.add ( but7, new AbsoluteConstraints( 555-30, 205, 200, 30 ) );

           but8 = new JButton( "STAFF  RECORD" ) ;
           but8.setBackground ( Color.cyan );
           but8.setForeground(Color.black);
           //but8.setMnemonic('N');
           but8.setBorder(new BevelBorder(0));
           c.add ( but8, new AbsoluteConstraints( 555-10, 245, 200, 30 ) );

           but14 = new JButton( "CD / FLOPPY  RECORD" ) ;
           but14.setBackground ( Color.cyan );
           but14.setForeground(Color.black);
           //but14.setMnemonic('N');
           but14.setBorder(new BevelBorder(0));
           c.add ( but14, new AbsoluteConstraints( 555+10, 285, 200, 30 ) );

           but12 = new JButton( "AVAILABLE  BOOKS" ) ;
           but12.setBackground ( Color.cyan );
           but12.setForeground(Color.black);
           //but12.setMnemonic('N');
           but12.setBorder(new BevelBorder(0));
           c.add ( but12, new AbsoluteConstraints( 555-10, 325, 200, 30 ) );

           but16 = new JButton( "AVAILABLE MAGAZINES / JOURNALS" ) ;
           but16.setBackground ( Color.cyan );
           but16.setForeground(Color.black);
           //but16.setMnemonic('N');
           but16.setBorder(new BevelBorder(0));
           c.add ( but16, new AbsoluteConstraints( 555-30, 365, 200, 30 ) );

           but18 = new JButton( "ARCHIVE CD TRANSACTIONS" ) ;
           but18.setBackground ( Color.cyan );
           but18.setForeground(Color.black);
           //but18.setMnemonic('N');
           but18.setBorder(new BevelBorder(0));
           c.add ( but18, new AbsoluteConstraints( 555-50, 405, 200, 30 ) );

           but20 = new JButton( "LIBRARY SHEDULE" ) ;
           but20.setBackground ( Color.cyan );
           but20.setForeground(Color.black);
           //but20.setMnemonic('H');
           but20.setBorder(new BevelBorder(0));
           c.add ( but20, new AbsoluteConstraints( 555-70, 445, 200, 30 ) );

           but22 = new JButton( "EXIT" ) ;
           but22.setBackground ( Color.cyan );
           but22.setForeground(Color.black);
           //but22.setMnemonic('X');
           but22.setBorder(new BevelBorder(0));
           c.add ( but22, new AbsoluteConstraints( 555-140, 485, 200, 30 ) );

           setVisible(true);

       }


    private void componentListener1()
      {
         but1.addActionListener(new ActionListener()
           {
              public void actionPerformed(ActionEvent e)
                {
                   new StudentSearch();
                }
            }
         );

         but2.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                 new StaffSearch();
              }
          }
          );

          but3.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
              {
                 new ArchiveBooksSearch();
              }
          }
          );
           but4.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                 new ArchiveCdsSearch();
            }
          }
          );
           but5.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new ArchiveMzsSearch();
            }
          }
          );
           but6.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new CdSearch();
            }
          }
          );
           but7.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new MagezineSearch();
            }
          }
          );
           but8.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              new StaffRecord();
            }
          }
          );
           but9.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new BookRecord();
            }
          }
          );
           but10.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                new BookSearch();
            }
          }
          );
           but11.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new MzRecord();
            }
          }
          );
           but12.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              new AvailableBooksSearch();
            }
          }
          );
           but13.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new StudentRecord();
            }
          }
          );
           but14.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new CDRecord();
            }
          }
          );
           but15.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new AvailableCdsSearch();
            }
          }
          );
           but16.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new AvailableMzsSearch();
            }
          }
          );
           but17.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
             new ArchiveBookTrans();
            }
          }
          );
           but18.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
             new ArchiveCdTrans();
            }
          }
          );
           but19.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
             new ArchiveMzTrans();
            }
          }
          );
           but20.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                new LibraryShedule1();
            }
          }
          );
           but21.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new FineReport();
            }
          }
          );


          but22.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    setVisible(false);
                 }
            }
          );
      }
    private void componentListener2()
      {
         but1.addActionListener(new ActionListener()
           {
              public void actionPerformed(ActionEvent e)
                {
                   message();
                }
            }
         );

         but2.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                 message();
              }
          }
          );

          but3.addActionListener(new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
              {
                 message();
              }
          }
          );
           but4.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                 message();
            }
          }
          );
           but5.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but6.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but7.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but8.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              message();
            }
          }
          );
           but9.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but10.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                message();
            }
          }
          );
           but11.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but12.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
              new AvailableBooksSearch();
            }
          }
          );
           but13.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but14.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but15.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new AvailableCdsSearch();
            }
          }
          );
           but16.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               new AvailableMzsSearch();
            }
          }
          );
           but17.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                message();
            }
          }
          );
           but18.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                message();
            }
          }
          );
           but19.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );
           but20.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
                new LibraryShedule1();
            }
          }
          );
           but21.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
            {
               message();
            }
          }
          );


          but22.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    setVisible(false);
                 }
            }
          );
      }
    private void message()
       {
          JOptionPane.showMessageDialog(null,"U  HAVE  NO  RIGHTS  TO  ACCESS  !  .   .   .");
       }
    public static void main(String arg[])
       {
           new Searching();
       }

  }