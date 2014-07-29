package org.ulibsoft.issues;

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


public class CdStdIss extends JFrame
  {
    private JLabel adno, name, branch, year, doj, cdcode, cdname, cdvrsion, idt, rdt, sts, image ;
    private Icon icon, icon1;
    private JTextField  no, n1,  d1, cd, nm, vrs , idt1, rd1,b1,yr ;
    private JButton iss, can, next,next_rec ;
    private JTable  studentdetails, sttable, bktransaction, templibrary ;
    private JScrollPane sp, sp1, sp2, sp3 ;
    private JPanel p1, p2, p3, p4 ,cmppn;
    private Container  c ;
    private CdStdIss parent;
    private String bname,aname1;

    private int MIN_CDS,MAX_CDS,PERIOD,issue,hold;;
    private JComboBox bkno;

    private int temp,count,STAGE=0 ;
    private String PATH,value,constraints=null ;

    private Connection  con ;
    private Statement s ;
    private ResultSet rs, rs1,rs2 ;

    public CdStdIss()
      {
         super("ISSUING CDs/FLOPPYs/CASSETTE");
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

         createComponents();
        try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
         getTable2();
         getTable3();
         try
         {
          s=con.createStatement();
          rs=s.executeQuery("SELECT MIN_CDS,MAX_CDS,CD_HOLDING_PERIOD,IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID=1");
          while(rs.next())
          {
           MIN_CDS=rs.getInt(1);
           MAX_CDS=rs.getInt(2);
           PERIOD=rs.getInt(3);
           PATH=rs.getString(4);
          }
         }catch(SQLException se)
         {
          JOptionPane.showMessageDialog(null,se.getMessage());
          se.printStackTrace();
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
                                            "ISSUE -- CDS", 1, 2, c.getFont(), Color.magenta));
         cmppn.setBackground(c.getBackground());
         c.add(cmppn,new AbsoluteConstraints(100,20,600,200));

         //INITIALIZE STUDENT DETAILS

         adno = new JLabel ( "ADMISSION-NO" );
         adno.setForeground ( new Color ( 120, 120, 153 ) );
         adno.setHorizontalAlignment ( SwingConstants.RIGHT  );
         cmppn.add( adno, new AbsoluteConstraints(19, 21+3 ) );

         no = new JTextField ( );
         no.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add (no,new AbsoluteConstraints( 115, 20+3, 120, 20 ) );

         name = new JLabel ( "STUDENT-NAME" );
         name.setForeground ( new Color ( 120, 120, 153 ) );
         name.setHorizontalAlignment ( SwingConstants.RIGHT );
         cmppn.add ( name, new AbsoluteConstraints( 15, 51) );

         n1 = new JTextField ( );
         n1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
         n1.setForeground ( new Color ( 255, 0, 153 ) );
         n1.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( n1, new AbsoluteConstraints( 115, 50, 120, 20 ) );

         image = new JLabel( );
         image.setBounds(245,20,100,113);
         image.setBorder(new MatteBorder (1, 1, 1, 1, Color.cyan));
         cmppn.add(image,new AbsoluteConstraints(250,20+3,100,110));

         icon1=new ImageIcon("empty.jpg");

         branch = new JLabel ( "COURSE" );
         branch.setForeground ( new Color ( 120, 120, 153 ) );
         cmppn.add ( branch, new AbsoluteConstraints(57, 81-2  ) );

         b1 = new JTextField();
         b1.setEditable(false);
         b1.setForeground( new Color( 255, 0, 153 ));
         b1.setCaretColor ( new Color ( 0, 204, 102 ) );
         b1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         cmppn.add ( b1, new AbsoluteConstraints(115, 80-2,120, 20  ));

         year = new JLabel( "SEMESTER" );
         year.setForeground ( new Color ( 120, 120, 153 ) );
         year.setHorizontalAlignment ( SwingConstants.RIGHT );
         cmppn.add ( year, new AbsoluteConstraints(43, 111-4  ) );

         yr = new JTextField();
         yr.setEditable(false);
         yr.setForeground(new Color(255, 0, 153));
         yr.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         yr.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( yr, new AbsoluteConstraints( 115,110-4,120,20 ) );

         doj = new JLabel ( "DATE-OF-JOIN" );
         doj.setForeground ( new Color ( 120, 120, 153 ) );
         doj.setHorizontalAlignment ( SwingConstants.RIGHT  );
         cmppn.add ( doj, new AbsoluteConstraints(25,141-6  ) );

         d1 = new JTextField ( );
         d1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         d1.setForeground ( new Color ( 255, 0, 153 ) );
         d1.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( d1, new AbsoluteConstraints( 115,140-6,120,20 ) );

         sts = new JLabel("");

         //INITIALIZING BOOKDETAILS

         cdcode = new JLabel( "CD ACCESSNO" );
         cdcode.setForeground ( new Color ( 120, 120, 153 ) );
         cmppn.add ( cdcode, new AbsoluteConstraints( 390-25, 21+2 ) );

         cd = new JTextField( );
         cd.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         cd.setForeground ( new Color ( 255, 0, 153 ) );
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         cd.setEditable(false);
         cmppn.add ( cd, new AbsoluteConstraints( 485-25, 20+2, 120, 20 ) );

         bkno = new JComboBox();
         bkno.addItem("");
         bkno.setBackground(Color.white);
         bkno.setForeground ( new Color ( 255, 0, 153 ) );
         bkno.setVisible(false);
         cmppn.add ( bkno, new AbsoluteConstraints( 485-25, 20+2, 120, 20 ) );

         cdname = new JLabel( "CD  TITLE" );
         cdname.setForeground ( new Color ( 120, 120, 153 ) );
         cmppn.add ( cdname, new AbsoluteConstraints(420-25, 50, 120, 18 ) );

         nm = new JTextField( );
         nm.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         nm.setForeground ( new Color ( 255, 0, 153 ) );
         nm.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( nm, new AbsoluteConstraints( 485-25, 50, 120, 20 ) );

         cdvrsion = new JLabel( "CD VERSION" );
         cdvrsion.setForeground ( new Color ( 120, 120, 153 ) );
         cdvrsion.setHorizontalAlignment ( SwingConstants.CENTER );
         cmppn.add ( cdvrsion, new AbsoluteConstraints( 403-25, 81-2 ) );

         vrs = new JTextField( );
         vrs.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         vrs.setForeground ( new Color ( 255, 0, 153 ) );
         vrs.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( vrs, new AbsoluteConstraints( 485-25, 80-2, 120, 20) );

         //INITIALIZE DATES

         idt = new JLabel( "ISSUE-DATE" );
         idt.setForeground ( new Color ( 120, 120, 153 ) );
         cmppn.add ( idt, new AbsoluteConstraints( 405-25, 111-4 ) );

         idt1 = new JTextField( );
         idt1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         idt1.setForeground ( new Color ( 255, 0, 153 ) );
         idt1.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( idt1, new AbsoluteConstraints( 485-25, 110-4, 120, 20 ) );

         rdt = new JLabel( "RECIEVE-DATE" );
         rdt.setForeground ( new Color ( 120, 120, 153 ) );
         cmppn.add ( rdt, new AbsoluteConstraints( 391-25, 141-6, 150, 18 ) );

         rd1 = new JTextField( );
         rd1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         rd1.setForeground ( new Color ( 255, 0, 153 ) );
         rd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add ( rd1, new AbsoluteConstraints( 485-25, 140-6, 120, 20 ) );

         //BUTTON CREATION

         iss = new JButton( "ISSUE" ) ;
         iss.setBackground (Color.cyan);
         iss.setForeground(Color.black);
         iss.setBorder(new BevelBorder(0));
         iss.setMnemonic('S');
         iss.setEnabled (false);
         cmppn.add ( iss, new AbsoluteConstraints( 130, 170-5, 110, 25 ) );

         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan);
         next.setForeground(Color.black);
         next.setMnemonic('N');
         next.setBorder(new BevelBorder(0));
         cmppn.add ( next, new AbsoluteConstraints( 212+30, 170-5, 110, 25 ) );

         next_rec = new JButton( "NEXT_REC" ) ;
         next_rec.setBackground ( Color.cyan);
         next_rec.setForeground(Color.black);
         next_rec.setMnemonic('N');
         next_rec.setBorder(new BevelBorder(0));
         next_rec.setVisible(false);
         cmppn.add ( next, new AbsoluteConstraints( 212+30, 170-5, 110, 25 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         cmppn.add ( can, new AbsoluteConstraints( 325+30, 170-5, 110, 25 ) );

         //CREATION OF PANELS

         p4 = new JPanel( new BorderLayout() );
         p4.setBackground(c.getBackground());
         p4.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE -- CDS", 1, 2, c.getFont(), Color.magenta));
         c.add(p4,new AbsoluteConstraints(425,215+20,350,130));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "STUDENT -- DATABASE", 1, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(15,215+20,400,130));

         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT -- TRANSACTIONS", 1, 2,c.getFont(),Color.magenta));
         c.add(p3,new AbsoluteConstraints(20,360+10,750,180));


         //n1.setEditable( false );
         //br.setEditable( false );
        // d1.setEditable( false );
         n1.setEditable( false );   //MODIFIED
         b1.setEditable(false);
         yr.setEditable(false);
         d1.setEditable( false );   //MODIFIED
         idt1.setEditable(false);  //MODIFIED
         rd1.setEditable(false);    //MODIFIED
         cd.setEditable(false);     //MODIFIED
         nm.setEditable( false );
         vrs.setEditable( false );


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
                   //p.setFrame(Index.cdsi);
                 }
              }
          }
        );

          //RECIEVING STUDENT DETAILS FROM DATABASE

        no.addActionListener( new ActionListener ()
          {
            public void actionPerformed( ActionEvent e )
              {
                try
                  {
                    no.setText(no.getText().toUpperCase())  ;

                    s = con.createStatement();
                     rs = s.executeQuery ("SELECT COUNT(*) FROM CDTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'" );
                    if(rs.next())
                    {
                      count=rs.getInt(1);
                    }
                    if(count>=MAX_CDS)
                    {
                      JOptionPane.showMessageDialog(null,"MAXIMUM   CDS  EXCEEDED  !  . . . ");
                      no.setText("");
                    }

                   else
                   {

                    rs = s.executeQuery ( "SELECT ADNO, SNAME, BRANCH, YEAR, TO_CHAR(DATEOFJOIN,'DD-MON-YYYY') FROM STUDENTDETAILS WHERE  ADNO ="+"'"+no.getText()+"'" );

                      if ( rs.next() )      //FOR NUMBER OF RECORDS >=1
                       {
                          PassWordDialog pwd1 = new PassWordDialog( (Frame)parent,"PASSWORD CONFIRMATION" );
                       }
                      else                 // IF RECORDS=0
                       {
                       JOptionPane.showMessageDialog(null,"RECORD DOES NOT EXIST !  .  .  .");
                       }
                    con.commit();
                   }
                    s.close();
                  }
                catch( SQLException sqlex )
                  {
                    JOptionPane.showMessageDialog(null,sqlex.getMessage(),"exception",JOptionPane.ERROR_MESSAGE);
                    sqlex.printStackTrace();
                  }
              }
          }
        );
        //CD DETAILS FROM DATABASE

        cd.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent ae )
              {

                try
                  {
                     s = con.createStatement ();

                     //SELECT BOOK DETAILS
                     rs = s.executeQuery( " SELECT CD.CDNAME, CD.CDVERSION FROM CDDETAILS CD WHERE CD.CDCODE ="+"'"+cd.getText ()+"'"+"AND CD.CDCODE NOT IN(SELECT CDT.CDCODE FROM CDTRANSACTION CDT WHERE CDT.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT CDT1.CDCODE FROM CDTRANSACTION1 CDT1 WHERE CDT1.CDCODE=CD.CDCODE) AND CD.STATUS IS NULL  AND CD.CDSTATUS <>'BROKEN'" );
                       if( rs.next() )
                        {
                           //SET BOOKNAME & AUTHOR
                           nm.setText( rs.getString( 1 ) );
                           vrs.setText( rs.getString( 2 ) );
                           inputDates( );
                           iss.setEnabled (true);

                        }
                       else
                        {
                           JOptionPane.showMessageDialog(null,"CD DOESN'T FOUND !... ","AVAILABLE CDS DATABASE",
                                                                           JOptionPane.INFORMATION_MESSAGE );
                        }

                     con.commit();
                     s.close();
                   }
                 catch( SQLException sq )
                   {
                      JOptionPane.showMessageDialog (null,sq.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                      sq.printStackTrace();
                   }
               }
          }
        );
        bkno.addActionListener(new ActionListener()
          {
             public void actionPerformed( ActionEvent e )
               {
                  if(bkno.getSelectedIndex()!=0)
                    {
                       iss.setEnabled(true);
                       cd.setText((String)bkno.getSelectedItem());
                    }
               }
          }
        );
        iss.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                try
                  {
                     s = con.createStatement();
                     rs = s.executeQuery ( "SELECT COUNT(*) FROM CDTRANSACTION WHERE ADNO="+"'"+no.getText ()+"'");
                     if(rs.next ())
                       {
                         count=rs.getInt(1);
                        if( count < MIN_CDS  ) {  acess();  }
                         else
                          {
                           if(count < MAX_CDS)
                           {
                            int ws=JOptionPane.showConfirmDialog (parent, count+" CDS R ISSUED  ! . WILL U GIVE ONE MORE....?","warning",JOptionPane.YES_NO_OPTION  ) ;
                            if(ws==0)
                              {
                                acess();
                              }
                            else
                              {
                                reset();
                              }
                             }
                             else
                             {
                              JOptionPane.showMessageDialog(null,"MAXIMUM  PERIOD EXCEEDED ! . . .");
                              reset();
                             }
                          }
                       }
                     iss.setEnabled (false);
                  }
                catch(SQLException sq)
                  {
                     JOptionPane.showMessageDialog (null,sq.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                     sq.printStackTrace();
                  }
              }
          }
        );

        next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                reset();
              }
          }
        );

        next_rec.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                 bkno.removeAllItems();
                 nm.setText("");
                 vrs.setText("");
                 idt1.setText("");
                 rd1.setText("");
                 cdRecord();
              }
          }
        );

        can.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                setVisible(false);
              }
          }
        );



      }

    private  void reset()
      {
         //RESET STUDENT VALUES
         no.setText("");
         no.setEditable(true);
         n1.setText("");
         n1.setEditable(false);
         b1.setText("");
         image.setIcon (icon1);
         yr.setEditable(false);
         yr.setText("");

         d1.setEditable(false);
         d1.setText("");

         //RESET cd VALUES
         nm.setText("");
         nm.setEditable(false);
         vrs.setText("");
         vrs.setEditable(false);
         cd.setText("");
         cd.setEditable(false);
         cd.setVisible(true);
         bkno.setVisible(false);
         bkno.removeAllItems();

         //RESET DATES
         idt1.setText("");
         idt1.setEditable(false);
         rd1.setText("");
         rd1.setEditable(false);

         iss.setEnabled (false);

      }

    protected void inputDates()
      {
        try
          {
              rd1.setEditable(true);
             //FOR ISSUEDATE & RETRIVE DATE
             rs = s.executeQuery( " SELECT TO_CHAR ( SYSDATE, 'DD-MON-YYYY' ) , TO_CHAR ( SYSDATE+"+PERIOD+", 'DD-MON-YYYY' ) FROM DUAL" );

                if( rs.next() )
                 {
                    idt1.setText( rs.getString( 1 ) );
                    rd1.setText( rs.getString( 2 ) );
                 }
          }
        catch(SQLException sqlex)
          {
             JOptionPane.showMessageDialog (null,sqlex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
             sqlex.printStackTrace();
          }
      }

    private class PassWordDialog extends JDialog
      {
         private JPasswordField pwdfd;
         private PassWordDialog parent1;
         private JLabel pwdlb;
         private JButton sub,newbtn,sub1;
         private PassWordDialog parent2;
         public PassWordDialog( Frame parent,String title )
           {
              super( parent,title,true );

              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(pwdlb = new JLabel("PASSWORD :"),new AbsoluteConstraints(25,25));
              pwdlb.setForeground(Color.white);

              getContentPane().add(pwdfd = new JPasswordField(),new AbsoluteConstraints(110,23,130,22));
              pwdfd.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add(sub = new JButton("SUBMIT"),new AbsoluteConstraints(30,55,100,22));
              sub.setBackground(Color.cyan);
              sub.setMnemonic('S');
              sub.setBorder(new BevelBorder(0));
              getContentPane().add(newbtn = new JButton(" CHANGE "),new AbsoluteConstraints(132,55,100,22));
              newbtn.setBackground(Color.cyan);
              newbtn.setMnemonic('C');
              newbtn.setBorder(new BevelBorder(0));

              newbtn.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        ChangePassWordDialog cpd = new ChangePassWordDialog((Dialog)parent1,"CHANGE PASSORD");
                        setVisible(false);
                     }
                }
              );

              sub.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {
                        try
                          {
                             s = con.createStatement();
                             rs = s.executeQuery("SELECT PASSWORD,ADNO FROM STUDENTDETAILS WHERE ADNO="+"'"+no.getText()+"'" );
                             if( rs.next() )
                               {
                                  if( new String( pwdfd.getPassword() ).equals(rs.getString(1)) )
                                    {
                                       //SET STUDENT DETAILS
                                       studentRecord();
                                       cd.setEditable(true);
                                       cdRecord();
                                       setVisible( false );
                                    }
                                  else
                                    {
                                       JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                                    }
                                 con.commit();
                                 s.close();
                               }

                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage() );
                          }
                    }
                }
              );

             setBounds(250,250,270,120);
              setVisible(true);
           }
      }




    private class ChangePassWordDialog extends JDialog
      {
         private JLabel msg;
         private JRadioButton yes,no2;
         private JPasswordField old,new1;
         private JLabel oldlb,newlb;
         public ChangePassWordDialog(Dialog parent2,String title)
           {
              super(parent,title,true);

              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(msg = new JLabel("R U SURE  !  .  .  .  TO CHANGE UR ' S  PASSWORD  ?"),new AbsoluteConstraints(25,25));
              msg.setForeground(Color.white);

              getContentPane().add( yes = new JRadioButton("YES"),new AbsoluteConstraints(25,50));
              yes.setBackground(getContentPane().getBackground());
              yes.setMnemonic('Y');
              getContentPane().add( no2  = new JRadioButton("NO"),new AbsoluteConstraints(25,75));
              no2.setBackground(getContentPane().getBackground());
              no2.setMnemonic('N');
              getContentPane().add( oldlb  = new JLabel("OLD  PASSWORD  :"),new AbsoluteConstraints(25,105));
              oldlb.setForeground(Color.white);

              getContentPane().add( newlb  = new JLabel("NEW PASSWORD  :"),new AbsoluteConstraints(25,135));
              newlb.setForeground(Color.white);

              getContentPane().add( old  = new JPasswordField(),new AbsoluteConstraints(125+10,105,130,20));
              old.setEnabled(false);
              old.setBackground(Color.lightGray);
              old.setEditable(false);
              old.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add( new1  = new JPasswordField(),new AbsoluteConstraints(125+10,134,130,20));
              new1.setEnabled(false);
              new1.setBackground(Color.lightGray);
              new1.setEditable(false);
              new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              yes.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                         old.setEnabled(true);
                         old.setBackground(Color.white);
                        old.setEditable(true);
                        no2.setEnabled(false);
                     }
                }
              );

              no2.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        new1.setEnabled(false);
                         new1.setEditable(false);
                        yes.setEnabled(false);
                        old.setEnabled(false);
                        old.setEditable(false);
                     }
                }
              );

              old.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        try
                          {
                             s = con.createStatement();
                             rs = s.executeQuery("SELECT PASSWORD FROM STUDENTDETAILS WHERE ADNO ="+"'"+no.getText()+"'"+"AND PASSWORD ="+"'"+new String( old.getPassword())+"'");
                                if( rs.next() )
                                  {
                                    //if( )//rs.getString(2).equals(no.getText()))
                                     // {
                                         new1.setEnabled(true);
                                         new1.setBackground(Color.white);
                                         new1.setEditable(true);
                                    //  }
                                  }
                                else
                                  {
                                     JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                                  }
                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage());
                          }
                     }
                }
              );

              new1.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        try
                          {
                             s = con.createStatement();
                             int h = s.executeUpdate("UPDATE STUDENTDETAILS SET PASSWORD ="+"'"+new String(new1.getPassword())+"'"+"WHERE ADNO = "+"'"+no.getText()+"'" );
                             con.commit();
                             s.close();
                             studentRecord();
                             cd.setEditable(true);
                             setVisible(false);
                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage());
                          }
                     }
                }
              );

             setBounds(220,200,320,190);
              setVisible(true);

           }
      }

    private void studentRecord()
      {
          try
            {  s = con.createStatement();
               rs = s.executeQuery("SELECT ADNO, SNAME, BRANCH, YEAR, TO_CHAR(DATEOFJOIN,'DD-MON-YYYY') FROM STUDENTDETAILS WHERE  ADNO ="+"'"+no.getText()+"'" );
                 if( rs.next() )
                   {
                      n1.setText(rs.getString(2));
                      b1.setText(rs.getString(3));
                      yr.setText(rs.getString(4));
                      d1.setText(rs.getString(5));
                      icon = new ImageIcon(PATH+no.getText()+".jpg");
                      image.setIcon (icon);


                   }
            }
          catch(SQLException sq)
            {
            }
      }
    private void getTable( String j )
      {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {
             rs = s.executeQuery ("SELECT  CD.CDCODE , CD.CDNAME, CD.CDVERSION,  TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE  FROM BOOKDETAILS BD,CDDETAILS CD WHERE BD.ADNO ="+"'"+j+"'"+"AND BD.VALUE='2'"+"AND CD.CDCODE=BD.CDCODE" );
             if (rs.next ())
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
                p2.add(sttable,BorderLayout.CENTER);
                JScrollPane spane =new JScrollPane(sttable);
                p2.add(spane,BorderLayout.CENTER);
                validate();
              }
             else
              {
                JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY !...","STUDENT  DATABASE",JOptionPane.INFORMATION_MESSAGE );
              }
           }
         catch( SQLException sqlex )
           {
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
             sqlex.printStackTrace();
           }
      }



   private void getTable2( )
      {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {
              s = con.createStatement();
              rs = s.executeQuery ( "SELECT STD.ADNO,STD.SNAME,STD.BRANCH,STD.YEAR,CD.CDCODE,CD.CDNAME,CD.CDVERSION ,CDT.IDATE,CDT.RDATE FROM CDTRANSACTION CDT,STUDENTDETAILS STD,CDDETAILS CD WHERE STD.ADNO=CDT.ADNO AND CDT.CDCODE=CD.CDCODE" );
              if( rs.next () )
               {
                  ResultSetMetaData rsmd = rs.getMetaData();
                  for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                  columnHeads.addElement( rsmd.getColumnName(i) );

                  do
                   {
                      rows.addElement( getNextRow( rs,rsmd ) );
                   }while( rs.next() );

                  bktransaction = new JTable( rows, columnHeads );
                  bktransaction.setBackground(Color.cyan);
                  bktransaction.setEnabled (false);
                  p3.add(bktransaction,BorderLayout.CENTER);
                  p3.add(sp1 = new JScrollPane(bktransaction),BorderLayout.CENTER);
                  validate();
               }
              else
               {
                 JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY !...","CURRENT  TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE );
               }
           }
         catch( SQLException sqlex )
           {
              JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
              sqlex.printStackTrace();
          }
      }

    private void getTable3()
      {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {
              s = con.createStatement();
              rs = s.executeQuery ( " SELECT CD.CDCODE,CD.CDNAME,CD.CDVERSION  FROM CDDETAILS CD WHERE CD.CDCODE NOT IN(SELECT CDT.CDCODE FROM CDTRANSACTION CDT WHERE CDT.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT CDT1.CDCODE FROM CDTRANSACTION1 CDT1 WHERE CDT1.CDCODE=CD.CDCODE) AND CD.CDSTATUS <>'BROKEN' " );
              if( rs.next () )
               {
                  ResultSetMetaData rsmd = rs.getMetaData();
                  for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                  columnHeads.addElement( rsmd.getColumnName(i) );

                  do
                   {
                     rows.addElement( getNextRow( rs,rsmd ) );
                   }while( rs.next() );

                  templibrary = new JTable(rows,columnHeads);
                  templibrary.setBackground(Color.pink);
                  templibrary.setEnabled(false);
                  p4.add(templibrary,BorderLayout.CENTER);
                  p4.add(sp2 = new JScrollPane(templibrary),BorderLayout.CENTER);
                  validate();
               }
              else
               {
                 JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !...","AVAILABLE CDS ",JOptionPane.INFORMATION_MESSAGE);
               }
           }
         catch( SQLException sqlex )
           {
              JOptionPane.showMessageDialog(null,sqlex.getMessage (),"Exception",JOptionPane.ERROR_MESSAGE);
              sqlex.printStackTrace();
           }
      }

    private Vector getNextRow( ResultSet rs,ResultSetMetaData rsmd )
     throws SQLException
      {
         Vector currentRow = new Vector();

         for(int i=1;i <= rsmd.getColumnCount();i++ )
             switch( rsmd.getColumnType(i) )
              {
                 case Types.VARCHAR : currentRow.addElement( rs.getString (i) );
                                      break;
                 default:             currentRow.addElement( rs.getDate (i) );
              }
         return currentRow;

      }
    private void cdRecord()
      {
         try
           {
              s = con.createStatement();

              rs = s.executeQuery("SELECT ADNO,CDNAME,VERSION,SUBJECT1 FROM STD_RESERVE WHERE ADNO='"+no.getText().toUpperCase()+"' AND CDNAME IS NOT NULL");
              if( rs.next() )
                {
                   bname = rs.getString(2);
                   if( bname==null||bname.equals("") ){}
                   else{constraints = " CD.CDNAME = '"+bname+"'";}
                   //JOptionPane.showMessageDialog(null,""+bname);
                   aname1 = rs.getString(3);
                   if( aname1==null||aname1.equals("") ){}
                   else{constraints =constraints+" AND CD.VERSION = '"+aname1+"'";}
                   String aname2 = rs.getString(4);
                   if( aname2==null||aname2.equals("") ){}
                   else{constraints =constraints+ " AND CD.SUBJECT = '"+aname2+"'";}

                   //JOptionPane.showMessageDialog(null,"  2  "+constraints);

                   rs = s.executeQuery("SELECT COUNT(CDNAME) FROM STD_RESERVE WHERE ADNO='"+no.getText()+"'");
                   while(rs.next())
                     {
                       hold=rs.getInt(1);
                     }
                   if(hold>1)
                     {
                        next.setVisible(false);
                        next_rec.setVisible(true);
                     }
                   else
                     {
                        next.setVisible(true);
                        next_rec.setVisible(false);
                     }
                   rs = s.executeQuery("SELECT CD.CDCODE FROM CDDETAILS CD WHERE CD.CDCODE NOT IN(SELECT BK.CDCODE FROM CDTRANSACTION BK WHERE BK.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT BK1.CDCODE FROM CDTRANSACTION1 BK1 WHERE BK1.CDCODE=CD.CDCODE )  AND"+constraints);//BOOKNAME='"+bname1+"' AND AUTHOR1S||AUTHOR1F ='"+aname1+"' AND AUTHOR2S||AUTHOR2F ='"+aname2+"AND AUTHOR3S||AUTHOR3F ='"+aname3+"'");
                   while( rs.next() )
                     {
                       value = rs.getString(1);
                       //JOptionPane.showMessageDialog(null,"erweywry"+value);
                       bkno.addItem(value);
                       nm.setText( bname );
                       vrs.setText( aname1 );
                       cd.setVisible(false);
                       bkno.setVisible(true);
                       issue=1;
                     }
                   if(issue==1)
                     {
                        inputDates();
                     }
                }
              s.close();
           }
         catch(SQLException sqlex)
           {
             JOptionPane.showMessageDialog(null,sqlex.getMessage());
           }
      }
    private void acess()
      {
        try
          {
            rs = s.executeQuery("SELECT STATUS FROM STUDENTDETAILS WHERE ADNO="+"'"+no.getText()+"'");
               if( rs.next())
                 {
                    String str = rs.getString(1) ;
                    if(str.equalsIgnoreCase("true"))
                      {
                        //INSERTION FOR BKTRANSACTION
                        int a = s.executeUpdate( "INSERT INTO CDTRANSACTION(  CDCODE, ADNO, IDATE, RDATE ) VALUES ( "+"'"+cd.getText()+"',"
                                                                                                                               +"'"+no.getText()+"',"
                                                                                                                               +"'"+idt1.getText()+"',"
                                                                                                                               +"'"+rd1.getText()+"'"
                                                                                                                              +")" );



                        con.commit();
                        getTable2();

                        int bks = s.executeUpdate( "INSERT INTO BOOKDETAILS( CDCODE, ADNO, IDATE, RDATE,VALUE ) VALUES ( "+"'"+cd.getText()+"',"
                                                                                                                          +"'"+no.getText()+"',"
                                                                                                                          +"'"+idt1.getText()+"',"
                                                                                                                          +"'"+rd1.getText()+"',"
                                                                                                                          +"'"+"2"+"'"
                                                                                                                          +")" );



                       // int bks = s.executeUpdate( "INSERT INTO BOOKDETAILS( BOOKNAME, AUTHOR, CODE, ADNO, SNAME, BRANCH, YEAR, IDATE, RDATE ) SELECT * FROM CDTRANSACTION WHERE CODE="+"'"+bc1.getText()+"'" );
                        con.commit();

                        int c = s.executeUpdate( "UPDATE BOOKDETAILS SET STATUS="+"'TRUE'" +"WHERE ADNO="+"'"+no.getText ()+"'");
                        con.commit();
                        getTable( no.getText() );
                        if(bname==null||bname.equals("")||aname1==null||aname1.equals("")){}
                        else
                        {
                        int k =s.executeUpdate("DELETE FROM STD_RESERVE WHERE ADNO='"+no.getText().toUpperCase()+"' AND CDNAME='"+bname+"' AND VERSION='"+aname1+"'");//"+constraints);
                        con.commit();
                        //JOptionPane.showMessageDialog(null,"deleted");
                        }
                        s.close( );
                     }
                   else
                     {
                        JOptionPane.showMessageDialog(null,"STUDENT'S  STUDYING PERIOD HAS OVERED !...");
                        iss.setEnabled(false);
                     }
                 }
               else
                 {
                   JOptionPane.showMessageDialog( null,"NO RECORDS ABT STUDENT !...","STUDENT  DATABASE",JOptionPane.INFORMATION_MESSAGE );
                 }
          }
        catch(SQLException se)
          {
            JOptionPane.showMessageDialog(null,se.getMessage());
          }
      }

    private class Myadapter extends WindowAdapter
      {
        public void windowClosing(WindowEvent wt)
          {
            try
              {
                con.close();
              }
            catch( SQLException sqlex )
              {
                JOptionPane.showMessageDialog(null,"UNABLE TO DISCONNECT","Exception",JOptionPane.ERROR_MESSAGE);
                sqlex.printStackTrace();
              }
          }
      }


    public static void main(String a[])
      {
        CdStdIss i = new CdStdIss();
      }
  }
