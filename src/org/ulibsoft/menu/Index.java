package org.ulibsoft.menu;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

import org.ulibsoft.search.entitySearch.Copies;
import javax.swing.*;
import org.ulibsoft.admin.KeyConstraints;
import org.ulibsoft.admin.LibraryShedule;
import org.ulibsoft.clearence.Clear;
import org.ulibsoft.enroll.CdLibrary2;
import org.ulibsoft.register.ui.Library2;
import org.ulibsoft.enroll.MzLibrary2;
import org.ulibsoft.register.ui.StaffDetails;
import org.ulibsoft.register.ui.StudentDetails;
import org.ulibsoft.issues.CdStdIss;
import org.ulibsoft.issues.CdStfIss;
import org.ulibsoft.issues.IssueBooks;
import org.ulibsoft.issues.MzStdIss;
import org.ulibsoft.issues.MzStfIss;
import org.ulibsoft.issues.StaffIssue;
import org.ulibsoft.login.Login;
import org.ulibsoft.returns.CdStdRet;
import org.ulibsoft.returns.CdStfRet;
import org.ulibsoft.returns.MzStdRet;
import org.ulibsoft.returns.MzStfRet;
import org.ulibsoft.returns.Retrive;
import org.ulibsoft.returns.StaffRetrive;
import org.ulibsoft.search.catalog.CdLibrary1;
import org.ulibsoft.search.catalog.Library1;
import org.ulibsoft.search.catalog.MzLibrary1;
import org.ulibsoft.search.defaulters.CdPendingList;
import org.ulibsoft.search.defaulters.CdStfDefaulterList;
import org.ulibsoft.search.defaulters.MzPendingList;
import org.ulibsoft.search.defaulters.MzStfDefaulterList;
import org.ulibsoft.search.defaulters.PendingList;
import org.ulibsoft.search.defaulters.StaffDefaulterList;
import org.ulibsoft.search.entitySearch.BookSearch;
import org.ulibsoft.search.entitySearch.CdCopies;
import org.ulibsoft.search.entitySearch.CdSearch;
import org.ulibsoft.search.entitySearch.MagezineSearch;
import org.ulibsoft.search.entitySearch.MzCopies;

public class Index extends JFrame
  {
    private Index p;
    private JLabel image,img1,img2,img3;
    private Icon icon,icon1,icon2;
    private String path="images/";
    public static int SEARCH=0;

    private JButton book, cd, MAGAZINE, features, insertion, quit;
    private JButton issue1, retrive1,pending1,access1,search1;
    private JButton issue2, retrive2,pending2,access2,search2;
    private JButton issue3, retrive3,pending3,access3,search3;
    private JPanel cmppn, panel1,panel2,panel3,panel4;
    private Container c;

    public static IssueBooks     is;
    public static StaffIssue     si;
    public static Retrive        rt;
    public static StaffRetrive   sr;
    public static Library2       lib2;
    public static Library1       lib1;
    public static LibraryShedule libsh;
    public static BookSearch     bs;
    public static Clear          cr;
    public static Copies         cp;
    public static PendingList    pl;
   // public static SubmitQuery    sq1;

    public static CdStdIss       cdsi;
    public static CdStfIss       cdst;
    public static CdStdRet       cdri;
    public static CdStfRet       cdrt;
    public static CdLibrary2     cdlib2;
    public static CdLibrary1     cdlib1;
    public static CdSearch       cdsh;
    public static CdCopies       cdcp;
    public static CdPendingList  cdpl;
    //public static SubmitQuery    sq2;

    public static MzStdIss       mzsi;
    public static MzStfIss       mzst;
    public static MzStdRet       mzri;
    public static MzStfRet       mzrt;
    public static MzLibrary2     mzlib2;
    public static MzLibrary1     mzlib1;
    public static MagezineSearch  mzsh;

    public static MzCopies       mzcp;
    public static MzPendingList  mzpl;
    //public static SubmitQuery    sq3;

    public static Login          gs;

    public Index()
      {
        super("MAIN MENU");

        System.out.println("ScreenResolution" + ScreenResolution.SCREEN_WIDTH+ " " +ScreenResolution.SCREEN_HEIGHT);
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        //setSize(800,600);
        show();

        createComponents();
        componentListener();
      }
    private void createComponents()
      {
         c=getContentPane();
         c.setLayout(new AbsoluteLayout());
         c.setBackground(new Color(0,0,50));

         image =new JLabel(" -:  MAIN MENU  :- ");
          image.setBorder(new BevelBorder(0));
          image.setBackground(new Color(0,0,60));
          image.setForeground(Color.pink);
          image.setFont(new Font(c.getFont().getFontName(),Font.BOLD,50));
          c.add(image,new AbsoluteConstraints(120+50+90,30));

         c.add( img1 = new JLabel(),new AbsoluteConstraints(250+40,450));
         img1.setToolTipText("TO EXIT THE MENU  ! .  .  .");
         icon=new ImageIcon(path+"D3.gif");
         img1.setIcon(icon);

         c.add( img2 = new JLabel(),new AbsoluteConstraints(575,470));
         icon1=new ImageIcon(path+"earth1.gif");
         img2.setIcon(icon1);

         c.add( img3 = new JLabel(),new AbsoluteConstraints(180,470));
         icon2=new ImageIcon(path+"earth1.gif");
         img3.setIcon(icon2);

         cmppn = new JPanel( new AbsoluteLayout() );
         cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            " OBJECTIVES ", 1, 2, c.getFont(), Color.magenta));
         cmppn.setBackground(c.getBackground());
         c.add(cmppn,new AbsoluteConstraints(90,125,300,300));

         panel1 = new JPanel( new AbsoluteLayout() );
         panel1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "BOOKS", 1, 2, c.getFont(), Color.magenta));
         panel1.setBackground(c.getBackground());
         c.add(panel1,new AbsoluteConstraints(410,125,300,300));
         //panel1.setVisible(false);

         panel2 = new JPanel( new AbsoluteLayout() );
         panel2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CD/FLOPPY/CASSETTE", 1, 2, c.getFont(), Color.magenta));
         panel2.setBackground(c.getBackground());
         c.add( panel2,new AbsoluteConstraints(410,125,300,300));
         panel2.setVisible(false);

         panel3 = new JPanel( new AbsoluteLayout() );
         panel3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "MAGAZINES/JOURNALS", 1, 2, c.getFont(), Color.magenta));
         panel3.setBackground(c.getBackground());
         c.add(panel3,new AbsoluteConstraints(410,125,300,300));
         panel3.setVisible(false);

         panel4 = new JPanel( new AbsoluteLayout() );
         panel4.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PRINTING", 1, 2, c.getFont(), Color.magenta));
         panel4.setBackground(c.getBackground());
         c.add(panel4,new AbsoluteConstraints(410,125,300,300));
         panel4.setVisible(false);

         cmppn.add( book = new JButton("BOOKS"), new AbsoluteConstraints(50,55,200,30) );
         book.setBackground (Color.cyan);
         book.setForeground(Color.black);
         book.setBorder(new BevelBorder(0));
         book.setMnemonic('B');

         cmppn.add( cd = new JButton("CD/FLOPPY/CASSETTE"), new AbsoluteConstraints(50,95,200,30) );
         cd.setBackground (Color.cyan);
         cd.setForeground(Color.black);
         cd.setBorder(new BevelBorder(0));
         cd.setMnemonic('C');

         cmppn.add( MAGAZINE = new JButton("MAGAZINE/JOURNAL"), new AbsoluteConstraints(50,135,200,30) );
         MAGAZINE.setBackground (Color.cyan);
         MAGAZINE.setForeground(Color.black);
         MAGAZINE.setBorder(new BevelBorder(0));
         MAGAZINE.setMnemonic('M');

         cmppn.add( insertion = new JButton("INSERTION"), new AbsoluteConstraints(50,175,200,30) );
         insertion.setBackground (Color.cyan);
         insertion.setForeground(Color.black);
         insertion.setBorder(new BevelBorder(0));
         insertion.setMnemonic('N');

         cmppn.add( features = new JButton("FEATURES"), new AbsoluteConstraints(50,215,200,30) );
         features.setBackground (Color.cyan);
         features.setForeground(Color.black);
         features.setBorder(new BevelBorder(0));
         features.setMnemonic('E');

         /*cmppn.add( quit = new JButton("QUIT"),new AbsoluteConstraints(50,215,200,30));
         quit.setBackground (Color.cyan);
         quit.setForeground(Color.black);
         quit.setBorder(new BevelBorder(0));
         quit.setMnemonic('Q');
          */

         //BOOKS TRANSACTIONS

         panel1.add(issue1 = new JButton("ISSUE-BOOKS"),new AbsoluteConstraints(50,55,200,30));
         issue1.setBackground (Color.cyan);
         issue1.setForeground(Color.black);
         issue1.setBorder(new BevelBorder(0));
         issue1.setMnemonic('I');

         panel1.add(retrive1 = new JButton("BOOKS-RETURN"),new AbsoluteConstraints(50,95,200,30));
         retrive1.setBackground (Color.cyan);
         retrive1.setForeground(Color.black);
         retrive1.setBorder(new BevelBorder(0));
         retrive1.setMnemonic('R');

         panel1.add(access1 = new JButton("BOOKS-CATALOG"),new AbsoluteConstraints(50,135,200,30));
         access1.setBackground (Color.cyan);
         access1.setForeground(Color.black);
         access1.setBorder(new BevelBorder(0));
         access1.setMnemonic('A');

         panel1.add(search1 = new JButton("BOOK-SEARCH"),new AbsoluteConstraints(50,175,200,30));
         search1.setBackground (Color.cyan);
         search1.setForeground(Color.black);
         search1.setBorder(new BevelBorder(0));
         search1.setMnemonic('B');

         panel1.add(pending1 = new JButton("DEFAULTERS"),new AbsoluteConstraints(50,215,200,30));
         pending1.setBackground (Color.cyan);
         pending1.setForeground(Color.black);
         pending1.setBorder(new BevelBorder(0));
         pending1.setMnemonic('U');

         //CD/FLOPPY/CASSETTE TRANSACTIONS

         panel2.add(issue2 = new JButton("ISSUE-CDS"),new AbsoluteConstraints(50,55,200,30));
         issue2.setBackground (Color.cyan);
         issue2.setForeground(Color.black);
         issue2.setBorder(new BevelBorder(0));
         issue2.setMnemonic('S');

         panel2.add(retrive2 = new JButton("CDS-RETURN"),new AbsoluteConstraints(50,95,200,30));
         retrive2.setBackground (Color.cyan);
         retrive2.setForeground(Color.black);
         retrive2.setBorder(new BevelBorder(0));
         retrive2.setMnemonic('E');

         panel2.add(access2 = new JButton("CDS-CATALOG"),new AbsoluteConstraints(50,135,200,30));
         access2.setBackground (Color.cyan);
         access2.setForeground(Color.black);
         access2.setBorder(new BevelBorder(0));
         access2.setMnemonic('A');

         panel2.add(search2 = new JButton("CD-SEARCH"),new AbsoluteConstraints(50,175,200,30));
         search2.setBackground (Color.cyan);
         search2.setForeground(Color.black);
         search2.setBorder(new BevelBorder(0));
         search2.setMnemonic('H');

         panel2.add(pending2 = new JButton("DEFAULTERS"),new AbsoluteConstraints(50,215,200,30));
         pending2.setBackground (Color.cyan);
         pending2.setForeground(Color.black);
         pending2.setBorder(new BevelBorder(0));
         pending2.setMnemonic('F');

         //MAGAZINE TRANSACTIONS
         panel3.add(issue3 = new JButton("MAGAZINES-ISSUE"),new AbsoluteConstraints(50,55,200,30));
         issue3.setBackground (Color.cyan);
         issue3.setForeground(Color.black);
         issue3.setBorder(new BevelBorder(0));
         issue3.setMnemonic('M');

         panel3.add(retrive3 = new JButton("MAGAZINES-RETURN"),new AbsoluteConstraints(50,95,200,30));
         retrive3.setBackground (Color.cyan);
         retrive3.setForeground(Color.black);
         retrive3.setBorder(new BevelBorder(0));
         retrive3.setMnemonic('G');

         panel3.add(access3 = new JButton("MAGAZINE-CATALOG"),new AbsoluteConstraints(50,135,200,30));
         access3.setBackground (Color.cyan);
         access3.setForeground(Color.black);
         access3.setBorder(new BevelBorder(0));
         access3.setMnemonic('L');

         panel3.add(search3 = new JButton("MAGAZINE-SEARCH"),new AbsoluteConstraints(50,175,200,30));
         search3.setBackground (Color.cyan);
         search3.setForeground(Color.black);
         search3.setBorder(new BevelBorder(0));
         search3.setMnemonic('Z');

         panel3.add(pending3 = new JButton("DEFAULTERS"),new AbsoluteConstraints(50,215,200,30));
         pending3.setBackground (Color.cyan);
         pending3.setForeground(Color.black);
         pending3.setBorder(new BevelBorder(0));
         pending3.setMnemonic('T');

         setVisible(true);
      }
    private void componentListener()
      {
         book.addActionListener(new ActionListener()
           {
             public void actionPerformed( ActionEvent e )
               {
                 panel2.setVisible(false);
                 panel3.setVisible(false);
                 panel4.setVisible(false);
                 panel1.setVisible(true);
               }
           }
         );
         cd.addActionListener(new ActionListener()
           {
             public void actionPerformed( ActionEvent e )
               {
                 panel4.setVisible(false);
                 panel3.setVisible(false);
                 panel1.setVisible(false);
                 panel2.setVisible(true);
               }
           }
         );
         MAGAZINE.addActionListener(new ActionListener()
           {
             public void actionPerformed( ActionEvent e )
               {
                 panel1.setVisible(false);
                 panel2.setVisible(false);
                 panel4.setVisible(false);
                 panel3.setVisible(true);
               }
           }
         );
         insertion.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                InsertionDialog ft=new InsertionDialog((Frame)p,"INSERTION");
               }
           }
         );

         features.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                FeatureDialog ft=new FeatureDialog((Frame)p,"FEATURES");
               }
           }

           );

         img1.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                  {
                      int  x=JOptionPane.showConfirmDialog (p,"R U SURE ! . . . . . LOGOUT ?","CONFORMATION",JOptionPane.YES_NO_OPTION );
                      if(x==0)
                        {
                            Login g=new Login();
                            setVisible(false);
                        }
                  }
                public void mousePressed(MouseEvent me)
                  {
                  }
                public void mouseReleased(MouseEvent me)
                  {
                  }
                public void mouseEntered(MouseEvent me)
                  {
                  }
                public void mouseExited(MouseEvent me)
                  {
                  }
            }
         );

         image.addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent me)
                  {
                  }
                public void mousePressed(MouseEvent me)
                  {
                  }
                public void mouseReleased(MouseEvent me)
                  {
                  }
                public void mouseEntered(MouseEvent me)
                  {
                    image.setBackground(new Color(0,0,100));
                  }
                public void mouseExited(MouseEvent me)
                  {
                     image.setBackground(new Color(0,0,60));
                  }
            }
         );
         //BOOK COMP...LISTENER
         issue1.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  IssueDialog1 id = new IssueDialog1( (Frame)p,"ISSUE-BOOKS");
               }
           }
         );

         retrive1.addActionListener (new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 RetriveDialog1 rd=new RetriveDialog1((Frame)p,"RETRIVE BOOKS");
               }
           }
         );

         access1.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 CatalogDialog1 cd=new CatalogDialog1((Frame)p,"BOOKS ACCESSING");
               }
           }
         );

         search1.addActionListener (new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 //SearchDialog1 cd=new SearchDialog1((Frame)p,"SEARCHING BOOKS");
                 new Copies();
               }
           }
         );

         pending1.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  DefaultersListDialog1 dfl = new DefaultersListDialog1((Frame)p,"DEFAULTERS LIST");
               }
           }
         );

         //CD COMP..LISTENER
         issue2.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  IssueDialog2 id = new IssueDialog2( (Frame)p,"ISSUE-CDS");
               }
           }
         );

         retrive2.addActionListener (new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 RetriveDialog2 rd=new RetriveDialog2((Frame)p,"RETRIVE CDS");
               }
           }
         );

         access2.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 CatalogDialog2 cd=new CatalogDialog2((Frame)p,"CDS ACCESSING");
               }
           }
         );

         search2.addActionListener (new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 //SearchDialog2 cd=new SearchDialog2((Frame)p,"SEARCHING CDS");
                 new CdCopies();
               }
           }
         );

         pending2.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 DefaultersListDialog2 dfl2 = new DefaultersListDialog2((Frame)p,"DEFAULTERS LIST");
               }
           }
         );

         //MAGAZINE COMP...LISTENER
         issue3.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  IssueDialog3 id = new IssueDialog3( (Frame)p,"ISSUE-MAGAZINES");
               }
           }
         );

         retrive3.addActionListener (new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  RetriveDialog3 rd=new RetriveDialog3((Frame)p,"RETRIVE MAGAZINES");
               }
           }
         );

         access3.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  CatalogDialog3 cd=new CatalogDialog3((Frame)p,"MAGAZINES ACCESSING");
               }
           }
         );

         search3.addActionListener (new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  //SearchDialog3 cd=new SearchDialog3((Frame)p,"SEARCHING MAGAZINES");
                  new MzCopies();
               }
           }
         );

         pending3.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                  DefaultersListDialog3 dfl3 = new DefaultersListDialog3((Frame)p,"DEFAULTERS LIST");
               }
           }
         );
      }

   private class InsertionDialog extends JDialog
      {
        private JButton std,stf,exit;

        public InsertionDialog(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            //stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    StudentDetails sd = new StudentDetails();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    StaffDetails sf = new StaffDetails();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }

   private class FeatureDialog extends JDialog
      {
        private JButton std,stf,exit,cer;

        public FeatureDialog(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(Color.pink);

            c1.add(std = new JButton("SEARCHING"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('R');
            std.setBackground (new Color(0,0,100));
            std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("CONSTRAINTS"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            stf.setBackground (new Color(0,0,100));
            stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (cer=new JButton("CERTIFY"),new AbsoluteConstraints(30,110,150,30));
            cer.setMnemonic ('X');
            cer.setBackground (new Color(0,0,100));
            cer.setForeground(Color.white);
            cer.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,150,150,30));
            exit.setMnemonic ('X');
            exit.setBackground (new Color(0,0,100));
            exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            cer.addActionListener(handler);
            exit.addActionListener (handler);

            setBounds(300,200,220,240);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                     //panel1.setVisible(false);
                     //panel2.setVisible(false);
                     //panel3.setVisible(false);
                     //panel4.setVisible(true);
                     SEARCH=1;
                     new Searching();
                     setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    new KeyConstraints();
                    setVisible(false);
                  }
                if(e.getSource()==cer)
                  {
                    Clear clr = new Clear();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }

    private class IssueDialog1 extends JDialog
      {
        private JButton std,stf,exit;

        public IssueDialog1(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            std.setBackground (Color.orange);
             //std.setBackground(new Color(0,0,100));
             //std.setForeground(Color.white);
            std.setForeground(new Color(0,0,120));
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            stf.setForeground(new Color(0,0,120));
           stf.setBackground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground(new Color(0,0,100));
            exit.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            //exit.setForeground(Color.black);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    is = new IssueBooks();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    si = new StaffIssue();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }

    private class RetriveDialog1 extends JDialog
      {
        private JButton std,stf,exit;

        public RetriveDialog1(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');

            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic('T');
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');

            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    rt = new Retrive();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    sr = new StaffRetrive();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }
    private class CatalogDialog1 extends JDialog
      {
        public CatalogDialog1( Frame parent, String title )
          {
             super(parent,title,true);
             setBounds(300,200,190,200);

             Container jk = getContentPane();
             jk.setLayout (new AbsoluteLayout());
             JPanel buttonpane=new JPanel();
             buttonpane.setBackground (Color.pink);
             buttonpane.setLayout (new AbsoluteLayout());
             jk.add(buttonpane,new AbsoluteConstraints(0,0,190,200 ));

             JButton std = new JButton("NEW-BOOKS") ;
             JButton stf = new JButton("REFERENCE");
             JButton lse=  new JButton("SHEDULE");
             JButton exit=new JButton("EXIT");

             std.setMnemonic ('N');
             stf.setMnemonic ('R');
             lse.setMnemonic ('S');
             exit.setMnemonic ('X');

             std.setBackground (new Color(0,0,100));
             std.setForeground(Color.white);
             std.setBorder(new BevelBorder(0));

             stf.setBackground (new Color(0,0,100));
             stf.setForeground(Color.white);
             stf.setBorder(new BevelBorder(0));

             lse.setBackground (new Color(0,0,100));
             lse.setForeground(Color.white);
             lse.setBorder(new BevelBorder(0));

             exit.setBackground (new Color(0,0,100));
             exit.setForeground(Color.white);
             exit.setBorder(new BevelBorder(0));

             buttonpane.add(std,new AbsoluteConstraints(20,25,150,27));
             buttonpane.add(stf,new AbsoluteConstraints(20,65,150,27));
             buttonpane.add(lse,new AbsoluteConstraints(20,105,150,27));
             buttonpane.add (exit,new AbsoluteConstraints(20,145,150,27));

             lse.addActionListener (new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                     libsh = new LibraryShedule();
                     setVisible( false );
                   }
               }
             );

             std.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                     lib2=new Library2();
                     setVisible(false);
                   }
               }
             );

             stf.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                     lib1=new Library1();
                     setVisible(false);
                   }
               }
             );

             exit.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                     setVisible(false);
                   }
               }
             );

             pack ();
             jk.setVisible(true);
             setVisible (true);

          }
      }


      // CD DIALOGS
    private class IssueDialog2 extends JDialog
      {
        private JButton std,stf,exit;

        public IssueDialog2(Frame parent,String title)
          {
            super( parent, title, true );

            Container c2= getContentPane();
            c2.setLayout(new AbsoluteLayout());
            c2.setBackground(new Color(120,120,160));
           //c2.setBackground(c.getBackground());

            c2.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            std.setBackground(Color.orange);
            std.setForeground(new Color(0,0,120));
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c2.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            //stf.setBackground (new Color(0,0,100));
            stf.setBackground(Color.white);
            stf.setForeground(new Color(0,0,120));
            stf.setBorder(new BevelBorder(0));

            c2.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            exit.setBackground(new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    cdsi = new CdStdIss();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    cdst = new CdStfIss();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }

    private class RetriveDialog2 extends JDialog
      {
        private JButton std,stf,exit;

        public RetriveDialog2(Frame parent,String title)
          {
            super( parent, title, true );

            Container c2= getContentPane();
            c2.setLayout(new AbsoluteLayout());
            c2.setBackground(new Color(120,120,160));

            c2.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c2.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            //stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));
            stf.setMnemonic('T');

            c2.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            exit.setBackground (new Color(0,0,100));
            exit.setForeground(Color.pink);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                     cdri= new CdStdRet();
                     setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                     cdrt = new CdStfRet();
                     setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                     setVisible(false);
                  }
              }
          }
      }
    private class CatalogDialog2 extends JDialog
      {
        private JPanel jk;
        public CatalogDialog2( Frame parent, String title )
          {
             super(parent,title,true);

             Container c1 = getContentPane();
             c1.setBackground (new Color(120,120,160) );
             c1.setLayout(new AbsoluteLayout());
             c1.add(jk = new JPanel(),new AbsoluteConstraints(0,0,210,170));
             jk.setLayout (new AbsoluteLayout());
             jk.setBackground (new Color(120,120,160));
             jk.setLayout (new AbsoluteLayout());

             JButton std = new JButton("NEW-CDS") ;
             JButton stf = new JButton("REFERENCE");
             JButton exit=new JButton("EXIT");

             std.setMnemonic ('N');
             stf.setMnemonic ('R');
             exit.setMnemonic ('X');

             //std.setBackground (new Color(0,0,100));
             //std.setForeground(Color.white);
             std.setBorder(new BevelBorder(0));

             //stf.setBackground (new Color(0,0,100));
             //stf.setForeground(Color.white);
             stf.setBorder(new BevelBorder(0));

             //exit.setBackground (new Color(0,0,100));
             //exit.setForeground(Color.white);
             exit.setBorder(new BevelBorder(0));

             std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

             jk.add(std,new AbsoluteConstraints(30,30,150,30));
             jk.add(stf,new AbsoluteConstraints(30,70,150,30));
             jk.add (exit,new AbsoluteConstraints(30,110,150,30));
             setBounds(300,200,210,170);

             std.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                      cdlib2=new CdLibrary2();
                      setVisible(false);
                   }
               }
             );

             stf.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                      cdlib1=new CdLibrary1();
                      setVisible(false);
                   }
               }
             );

             exit.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                     setVisible(false);
                   }
               }
             );

             pack ();
             jk.setVisible(true);
             setVisible (true);

          }
      }


          //MAGAZINE DIALOGS
    private class IssueDialog3 extends JDialog
      {
        private JButton std,stf,exit;

        public IssueDialog3(Frame parent,String title)
          {
            super( parent, title, true );

            Container c3= getContentPane();
            c3.setLayout(new AbsoluteLayout());
            c3.setBackground(new Color(120,120,160));

            c3.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c3.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            //stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c3.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    mzsi = new MzStdIss();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                     mzst = new MzStfIss();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }

    private class RetriveDialog3 extends JDialog
      {
        private JButton std,stf,exit;

        public RetriveDialog3(Frame parent,String title)
          {
            super( parent, title, true );

            Container c3= getContentPane();
            c3.setLayout(new AbsoluteLayout());
            c3.setBackground(new Color(120,120,160));

            c3.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c3.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            //stf.setBorder(new BevelBorder(0));
            stf.setMnemonic('T');

            c3.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                     mzri = new MzStdRet();
                     setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                     mzrt = new MzStfRet();
                     setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                     setVisible(false);
                  }
              }
          }
      }
    private class CatalogDialog3 extends JDialog
      {
        private JPanel jk;
        public CatalogDialog3( Frame parent, String title )
          {
             super(parent,title,true);
             setBounds(300,200,210,170);

             Container c1 = getContentPane();
             c1.setBackground (new Color(120,120,160) );
             c1.setLayout(new AbsoluteLayout());
             c1.add(jk = new JPanel(),new AbsoluteConstraints(0,0,210,170));
             jk.setLayout (new AbsoluteLayout());
             jk.setBackground (new Color(120,120,160));

             JButton std = new JButton("NEW-MAGAZINES") ;
             JButton stf = new JButton("REFERENCE");
             JButton exit=new JButton("EXIT");

             std.setMnemonic ('N');
             stf.setMnemonic ('R');
             exit.setMnemonic ('X');

             //std.setBackground (new Color(0,0,100));
             //std.setForeground(Color.white);
             std.setBorder(new BevelBorder(0));

             //stf.setBackground (new Color(0,0,100));
             //stf.setForeground(Color.white);
             stf.setBorder(new BevelBorder(0));

             //exit.setBackground (new Color(0,0,100));
             //exit.setForeground(Color.white);
             exit.setBorder(new BevelBorder(0));

             std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

             jk.add(std,new AbsoluteConstraints(30,30,150,27));
             jk.add(stf,new AbsoluteConstraints(30,70,150,27));
             jk.add (exit,new AbsoluteConstraints(30,110,150,27));
             setBounds(300,200,220,200);

             std.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                      mzlib2=new MzLibrary2();
                      setVisible(false);
                   }
               }
             );

             stf.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                      mzlib1=new MzLibrary1();
                      setVisible(false);
                   }
               }
             );

             exit.addActionListener(new ActionListener()
               {
                 public void actionPerformed(ActionEvent e)
                   {
                     setVisible(false);
                   }
               }
             );

             pack ();
             jk.setVisible(true);
             setVisible (true);

          }
      }


    private class DefaultersListDialog1 extends JDialog
      {
        private JButton std,stf,exit;

        public DefaultersListDialog1(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            //stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    new PendingList();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    new StaffDefaulterList();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }
   private class DefaultersListDialog2 extends JDialog
      {
        private JButton std,stf,exit;

        public DefaultersListDialog2(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            //stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    new CdPendingList();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    new CdStfDefaulterList();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }

      private class DefaultersListDialog3 extends JDialog
      {
        private JButton std,stf,exit;

        public DefaultersListDialog3(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("STUDENT"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('S');
            //std.setBackground (new Color(0,0,100));
            //std.setForeground(Color.white);
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("STAFF"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('T');
            //stf.setBackground (new Color(0,0,100));
            //stf.setForeground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("EXIT"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('X');
            //exit.setBackground (new Color(0,0,100));
            //exit.setForeground(Color.white);
            exit.setBorder(new BevelBorder(0));

            Handler handler = new Handler();
            std.addActionListener(handler);
            stf.addActionListener(handler);
            exit.addActionListener (handler);

            std.setBackground (Color.orange);
            std.setForeground(new Color(0,0,120));
            stf.setBackground (Color.white);
            stf.setForeground(new Color(0,0,120));
            exit.setBackground (new Color(0,200,0));
            exit.setForeground(new Color(0,0,120));

            setBounds(300,200,220,200);
            setVisible(true);
          }
        private class Handler implements ActionListener
          {
            public void actionPerformed(ActionEvent e)
              {
                if(e.getSource()==std)
                  {
                    new MzPendingList();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    new MzStfDefaulterList();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    setVisible(false);
                  }
              }
          }
      }


    public static void main(String a[])
      {
        Index in =new Index();
        in.setVisible(true);
      }
  }
