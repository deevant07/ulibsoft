package org.ulibsoft.menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.ulibsoft.login.Login;
import org.ulibsoft.register.ui.StaffDetails;
import org.ulibsoft.reserve.StaffInput;
import org.ulibsoft.search.defaulters.StaffFineRecord;
import org.ulibsoft.search.record.StaffRecord;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;


public class StaffAccess extends JFrame
  {
     private JButton profile,search,input,quit,record,fine;
     private Container c;
     private JPanel cmppn,titPn1,titPn2;
     private JLabel image,img1,img2,img3;
     private ImageIcon icon1,icon2,icon;
     private String path="c:\\MyPictures\\";
     private StaffAccess p;
     public static int access=0;
     public static int SEARCH=0;
     public static int INPUT=0;

     private JLabel k1,k2,k3,k4,k5,k6,k7,k8,k9,k10;

     public StaffAccess()
       {
          super("MAIN   MENU  FORM");
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
          createComponents();
          //odbcConnection();
          componentListener();
       }

     private void createComponents()
       {
          c = getContentPane( ) ;
          c.setBackground( new Color(0,0,50) ) ;
          c.setLayout ( new AbsoluteLayout( ) ) ;

          titPn1 = new JPanel(new AbsoluteLayout());
          titPn1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CHOOSE  ME ", 1, 2, c.getFont(), Color.magenta));
          titPn1.setBackground(c.getBackground());
          c.add(titPn1,new AbsoluteConstraints(90,125,300,300));


          image =new JLabel(" -:  MAIN MENU  :- ");
          image.setBorder(new BevelBorder(0));
          image.setBackground(new Color(0,0,60));
          image.setForeground(Color.pink);
          image.setFont(new Font(c.getFont().getFontName(),Font.BOLD,50));
          c.add(image,new AbsoluteConstraints(170,30));

          titPn1.add(profile = new JButton("PROFILE"),new AbsoluteConstraints(15+35,30+18,200,30));
          profile.setBackground(Color.cyan);
          profile.setMnemonic('P');
          profile.setBorder(new BevelBorder(0));

          titPn1.add(search= new JButton("SEARCH"),new AbsoluteConstraints(15+35,65+18,200,30));
          search.setBackground(Color.cyan);
          search.setMnemonic('S');
          search.setBorder(new BevelBorder(0));

          titPn1.add(input= new JButton("INPUT"),new AbsoluteConstraints(15+35,100+18,200,30));
          input.setBackground(Color.cyan);
          input.setMnemonic('I');
          input.setBorder(new BevelBorder(0));

          titPn1.add(record= new JButton("VIEW  RECORD"),new AbsoluteConstraints(15+35,135+18,200,30));
          record.setBackground(Color.cyan);
          record.setMnemonic('R');
          record.setBorder(new BevelBorder(0));

          titPn1.add(fine= new JButton("DEFAULTED OBJECTIVES"),new AbsoluteConstraints(15+35,170+18,200,30));
          fine.setMnemonic('D');
          fine.setBackground(Color.cyan);
          fine.setBorder(new BevelBorder(0));

          titPn1.add(quit= new JButton("EXIT"),new AbsoluteConstraints(15+35,205+18,200,30));
          quit.setMnemonic('X');
          quit.setBackground(Color.cyan);
          quit.setBorder(new BevelBorder(0));

          cmppn = new JPanel( new AbsoluteLayout() );
          cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "ABOUT  DEVELOPERS", 2, 2, c.getFont(), Color.magenta));
          cmppn.setBackground(c.getBackground());
          c.add(cmppn,new AbsoluteConstraints(410,125,300,300));

          cmppn.add(k1 = new JLabel("This  project  , "),new AbsoluteConstraints(30,30));
          k1.setForeground(Color.white);
          k1.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k2 = new JLabel("Technical  Library   "),new AbsoluteConstraints(60,60));
          k2.setForeground(Color.white);
          k2.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k3 = new JLabel("devoloped  by  .  .  .  .  .  .  .  , "),new AbsoluteConstraints(60,90));
          k3.setForeground(Color.white);
          k3.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k4 = new JLabel("K  .  Pavan  Kumar  ;  CSIT"),new AbsoluteConstraints(100,160));
          k4.setForeground(Color.white);
          k4.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k5 = new JLabel(" & "),new AbsoluteConstraints(190,140));
          k5.setForeground(Color.white);
          k5.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k6 = new JLabel("T  .  Deevan  Kumar  ;  CSE"),new AbsoluteConstraints(100,120));
          k6.setForeground(Color.white);
          k6.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k7 = new JLabel("(  2002  --  2006 )  batch"),new AbsoluteConstraints(100,180));
          k7.setForeground(Color.white);
          k7.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k8= new JLabel("@  contact  us  :"),new AbsoluteConstraints(30,210));
          k8.setForeground(Color.white);
          k8.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k9 = new JLabel("pavan_fantasy85@yahoo.com"),new AbsoluteConstraints(60,270));
          k9.setForeground(Color.white);
          k9.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          cmppn.add(k10 = new JLabel("kumar_deevan2000@yahoo.com"),new AbsoluteConstraints(60,240));
          k10.setForeground(Color.white);
          k10.setFont(new Font(c.getFont().getFontName(),Font.PLAIN,13));

          c.add( img1 = new JLabel(),new AbsoluteConstraints(250,450));
          //img1.setToolTipText("TO EXIT THE MENU  ! .  .  .");
          icon=new ImageIcon(path+"D3.gif");
          img1.setIcon(icon);

          c.add( img2 = new JLabel(),new AbsoluteConstraints(575,470));
          icon1=new ImageIcon(path+"earth1.gif");
          img2.setIcon(icon1);

          c.add( img3 = new JLabel(),new AbsoluteConstraints(180,470));
          icon2=new ImageIcon(path+"earth1.gif");
          img3.setIcon(icon2);

          setVisible(true);
       }
     private void componentListener()
       {
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
         profile.addActionListener(new ActionListener()
           {
              public void actionPerformed(ActionEvent e)
                {
                   access=1;
                   new StaffDetails();
                }
           }
         );

         search.addActionListener(new ActionListener()
           {
              public void actionPerformed( ActionEvent e)
                {
                   SEARCH=1;
                   new Searching();
                }
           }
         );

         input.addActionListener(new ActionListener()
           {
              public void actionPerformed( ActionEvent e)
                {
                   new ObjectiveDialog( (Frame)p,"CHOOSE  ME" );
                }
           }
         );

         record.addActionListener(new ActionListener()
           {
              public void actionPerformed( ActionEvent e)
                {
                   new StaffRecord(Login.cname.getText());
                }
           }
         );

         fine.addActionListener(new ActionListener()
           {
              public void actionPerformed( ActionEvent e)
                {
                   new StaffFineRecord();
                }
           }
         );

         quit.addActionListener(new ActionListener()
           {
              public void actionPerformed( ActionEvent e)
                {
                   int  x=JOptionPane.showConfirmDialog (p,"R U SURE ! . . . . . SIGNOUT ?","CONFORMATION",JOptionPane.YES_NO_OPTION );
                      if(x==0)
                        {
                            new Login();
                            setVisible(false);
                        }
                }
           }
         );
       }

     private class ObjectiveDialog extends JDialog
      {
        private JButton std,stf,exit;

        public ObjectiveDialog(Frame parent,String title)
          {
            super( parent, title, true );

            Container c1= getContentPane();
            c1.setLayout(new AbsoluteLayout());
            c1.setBackground(new Color(120,120,160));

            c1.add(std = new JButton("BOOK"),new AbsoluteConstraints(30,30,150,30));
            std.setMnemonic ('B');
            std.setBackground (Color.orange);
             //std.setBackground(new Color(0,0,100));
             //std.setForeground(Color.white);
            std.setForeground(new Color(0,0,120));
            std.setBorder(new BevelBorder(0));

            c1.add(stf = new JButton("CD / FLOPPY"),new AbsoluteConstraints(30,70,150,30));
            stf.setMnemonic ('C');
            stf.setForeground(new Color(0,0,120));
            stf.setBackground(Color.white);
            stf.setBorder(new BevelBorder(0));

            c1.add (exit=new JButton("MAGAZINE"),new AbsoluteConstraints(30,110,150,30));
            exit.setMnemonic ('M');
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
                    INPUT = 1;
                    //JOptionPane.showMessageDialog(null,"CAN ' T  ACESS  .......?");
                    new StaffInput();
                    setVisible(false);
                  }
                if(e.getSource()==stf)
                  {
                    INPUT = 2;
                    //JOptionPane.showMessageDialog(null,"CAN ' T  ACESS  .......?");
                    new StaffInput();
                    setVisible(false);
                  }
                if(e.getSource ()==exit)
                  {
                    INPUT = 3;
                    //JOptionPane.showMessageDialog(null,"CAN ' T  ACESS  .......?");
                    new StaffInput();
                    setVisible(false);
                  }
              }
          }
       }
     public static void main( String a[] )
       {
          new StaffAccess();
       }
  }