package org.ulibsoft.issues;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class MzStfIss extends JFrame
  {
    private JLabel lid, name, dept, image, mc,mn,vol, idt, rdt,jd,sts ;
    private Icon icon,icon1;
    private JTextField  no, n1, mc1,dr, mn1, vol1, idt1, rd1, jd1 ;
    private JButton iss, can, next,next_rec;
    private JTable  staff, lttable, bktransaction1, templibrary ;
    private JScrollPane sp, sp1, sp2, sp3 ;
    private JPanel p1,p2,p3,p4,ltpn;
    private Container  c ;
    private StaffIssue parent;
    private int count,STAGE=0;
    private String bname,aname1;

    private int MIN_MZS,MAX_MZS,PERIOD,issue,hold;
    private String PATH,constraints=null,value;
    private JComboBox bkno;

    private Connection  con ;
    private Statement s ;
    private ResultSet rs, rs1,rs2 ;

    public MzStfIss()
      {
         super("Issuing Magazines");
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);

         createComponents();
         try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
         try
         {
             s=con.createStatement();
             getTable2();
             getTable3();
             s.close();
         }catch(SQLException se)
         {
           JOptionPane.showMessageDialog(null,se.getMessage(),"exception",JOptionPane.ERROR_MESSAGE);
                    se.printStackTrace();
         }
          try
         {
          s=con.createStatement();
          rs=s.executeQuery("SELECT MIN_MZS,MAX_MZS,MZ_HOLDING_PERIOD,IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID=2");
          while(rs.next())
          {
           MIN_MZS=rs.getInt(1);
           MAX_MZS=rs.getInt(2);
           PERIOD=rs.getInt(3);
           PATH=rs.getString(4);
          }
         }catch(SQLException se)
         {
          JOptionPane.showMessageDialog(null,se.getMessage());
          se.printStackTrace();
         }
         componentListener();
         Myadapter myap = new Myadapter();
         addWindowListener(myap);
      }
    private void createComponents()
      {
         c = getContentPane( ) ;
         c.setBackground( new Color(0,0,40) ) ;
         c.setLayout ( new AbsoluteLayout( ) ) ;

         ltpn = new JPanel( new AbsoluteLayout() );
         ltpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "ISSUE MAGAZINES ", 1, 2,c.getFont(), Color.magenta));
         ltpn.setBackground(c.getBackground());
         c.add(ltpn,new AbsoluteConstraints(80,20,630,175));

         //INITIALIZE STAFF DETAILS

         lid = new JLabel ( "LECTURER  ID . ." );
         lid.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add( lid, new AbsoluteConstraints( 25, 21 ) );

         no = new JTextField ( );
         no.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add (no,new AbsoluteConstraints( 120, 20, 130, 20 ) );

         name = new JLabel ( "NAME . . . . . . . ." );
         name.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( name, new AbsoluteConstraints( 25 , 51 ) );

         n1 = new JTextField ( );
         n1.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         n1.setForeground ( new Color ( 255, 0, 153 ) );
         n1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( n1, new AbsoluteConstraints( 120, 50, 130, 20 ) );

         image = new JLabel(  );
         image.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
         image.setBounds(260,21,100,110);
         ltpn.add(image,new AbsoluteConstraints(260,21,100,110));

         icon1=new ImageIcon("empty.jpg");

         dept = new JLabel ( "DEPARTMENT. ." );
         dept.setForeground ( new Color ( 123, 120, 153 ) );
         ltpn.add ( dept, new AbsoluteConstraints( 25, 81 ) );


         dr=new JTextField("");
         dr.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         dr.setForeground ( new Color ( 255, 0, 153 ) );
         dr.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add (dr,new AbsoluteConstraints( 120, 80, 130, 20 ));

         jd = new JLabel( " JOINING  DATE" );
         jd.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( jd, new AbsoluteConstraints(20 , 111 ) );

         jd1 = new JTextField( );
         jd1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         jd1.setForeground ( new Color ( 255, 0, 153 ) );
         jd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( jd1, new AbsoluteConstraints( 120, 110, 130, 20) );

         n1.setEditable( false );
         dr.setEditable( false );
         jd1.setEditable(false);

         //INITIALIZING BOOKDETAILS

         mc = new JLabel( "ACCESS-NO . . .  " );
         mc.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( mc, new AbsoluteConstraints( 385, 21 ) );

         mc1 = new JTextField( );
         mc1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         mc1.setForeground ( new Color ( 255, 0, 153 ) );
         mc1.setCaretColor ( new Color ( 0, 204, 102 ) );
         mc1.setEditable(false);
         ltpn.add ( mc1, new AbsoluteConstraints( 485, 20, 130, 20 ) );

         bkno = new JComboBox();
         bkno.addItem("");
         bkno.setBackground(Color.white);
         bkno.setForeground ( new Color ( 255, 0, 153 ) );
         bkno.setVisible(false);
         ltpn.add ( bkno, new AbsoluteConstraints( 485, 20, 130, 20 ) );

         mn = new JLabel( "BOOK  NAME  . . " );
         mn.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( mn, new AbsoluteConstraints( 385, 51 ) );

         mn1 = new JTextField( );
         mn1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         mn1.setForeground ( new Color ( 255, 0, 153 ) );
         mn1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( mn1, new AbsoluteConstraints( 485, 50, 130, 20 ) );

         vol = new JLabel( "VOLUME  . . . . . . " );
         vol.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( vol, new AbsoluteConstraints( 385, 81 ) );

         vol1 = new JTextField( );
         vol1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         vol1.setForeground ( new Color ( 255, 0, 153 ) );
         vol1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( vol1, new AbsoluteConstraints( 485, 80, 130, 20) );

         //INITiALIZE DATES

         idt = new JLabel( "ISSUE - DATE . . " );
         idt.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( idt, new AbsoluteConstraints( 385, 111 ) );

         idt1 = new JTextField( );
         idt1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         idt1.setForeground ( new Color ( 255, 0, 153 ) );
         idt1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( idt1, new AbsoluteConstraints( 485, 110, 130, 20 ) );

         rdt = new JLabel( "RECIEVE - DATE" );
         rdt.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( rdt, new AbsoluteConstraints( 385, 141 ) );

         rd1 = new JTextField( );
         rd1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         rd1.setForeground ( new Color ( 255, 0, 153 ) );
         rd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( rd1, new AbsoluteConstraints( 485, 140, 130, 20 ) );

         mn1.setEditable( false );
         vol1.setEditable( false );
         idt1.setEditable(false);
         rd1.setEditable(false);

         //BUTTON CREATION
         sts = new JLabel();
          iss = new JButton( "ISSUE" ) ;
         iss.setMnemonic ('I');
         iss.setBackground ( Color.cyan  );
         iss.setForeground( Color.black );
         iss.setBorder ( new BevelBorder(0)  );
         iss.setEnabled (false);
         ltpn.add ( iss, new AbsoluteConstraints( 50-25,140, 110, 25) );

         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan   );
         next.setForeground(Color.black  );
         next.setMnemonic('N');
         next.setBorder ( new BevelBorder (0));
         ltpn.add ( next, new AbsoluteConstraints( 162-25, 140, 110, 25 ) );

         next_rec = new JButton( "NEXT_REC" ) ;
         next_rec.setBackground ( Color.cyan);
         next_rec.setForeground(Color.black);
         next_rec.setMnemonic('N');
         next_rec.setBorder(new BevelBorder(0));
         next_rec.setVisible(false);
         ltpn.add ( next_rec, new AbsoluteConstraints( 162-25, 140, 110, 25 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan  );
         can.setForeground( Color.black );
         can.setMnemonic('X');
         can.setBorder ( new BevelBorder(0) );
         ltpn.add ( can, new AbsoluteConstraints( 274-25, 140, 110, 25 ) );


         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROFESSOR  DATABASE", 1, 2, c.getFont(), Color.magenta));
          c.add(p2,new AbsoluteConstraints(15,215,400,140));


         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT  TRANSACTIONS", 1, 2, c.getFont(), Color.magenta));
         c.add(p3,new AbsoluteConstraints(20,360+10,750,190));

         p4 = new JPanel( new BorderLayout() );
         p4.setBackground(c.getBackground());
         p4.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE  MAGAZINES", 1, 2, c.getFont(), Color.magenta));
          c.add(p4,new AbsoluteConstraints(425,215,350,140));

         setVisible(true);
      }

    private void componentListener()
      {
        //SETTING POP-UP-MENU
        addMouseListener( new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
              {
                if (e.isMetaDown())
                 {
                   PopUpMenu p=new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                   //p.setFrame(Index.mzst);
                 }
              }
          }
        );

        no.addActionListener( new ActionListener ()
          {

            public void actionPerformed( ActionEvent e )
              {
                no.setText(no.getText().toUpperCase());

                try
                  {
                    s = con.createStatement();
                      rs = s.executeQuery ("SELECT COUNT(*) FROM MZTRANSACTION1 WHERE LID="+"'"+no.getText ()+"'" );
                    if(rs.next())
                    {
                      count=rs.getInt(1);
                    }
                    if(count>=MAX_MZS)
                    {
                      JOptionPane.showMessageDialog(null,"MAXIMUM  MAGAZINES  EXCEEDED");
                      no.setText("");
                    }

                   else
                   {
                    rs = s.executeQuery ( "SELECT LID, LNAME, DEPT,TO_CHAR(JOININGDATE,'DD-MON-YYYY')  FROM STAFF WHERE  LID ="+"'"+no.getText()+"'" );

                      if ( rs.next() )      //FOR NUMBER OF RECORDS >=1
                       {
                          //SET STAFF_MEMBER DETAILS
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

        mc1.addActionListener( new ActionListener()
          {
             public void actionPerformed( ActionEvent ae )
               {
                 try
                   {
                      s = con.createStatement ();

                      //SELECT BOOK DETAILS
                        rs = s.executeQuery( " SELECT MZ.MZNAME,MZ.VOLUME FROM MZDETAILS MZ WHERE MZ.ACCESSNO ="+"'"+mc1.getText ()+"' AND MZ.ACCESSNO NOT IN(SELECT MZT.MZCODE FROM MZTRANSACTION MZT WHERE MZT.MZCODE=MZ.ACCESSNO) AND MZ.ACCESSNO NOT IN(SELECT MZT1.MZCODE FROM MZTRANSACTION1 MZT1 WHERE MZT1.MZCODE=MZ.ACCESSNO) AND MZ.STATUS  IS NULL" );

                        if( rs.next() )
                         {
                            //SET BOOKNAME & AUTHOR
                            mn1.setText( rs.getString( 1 ) );
                            vol1.setText( rs.getString( 2 ) );
                            inputDates( );
                            iss.setEnabled (true);
                         }
                        else
                         {
                            JOptionPane.showMessageDialog(null,"BOOK'S RECORD NOT EXIST","CURRENT TRANSACTIONS",
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
                       mc1.setText((String)bkno.getSelectedItem());
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
                    s=con.createStatement();
                     rs=s.executeQuery ("select count(*) from MZtransaction1 where LID="+"'"+no.getText ()+"'");
                     if(rs.next ())
                       {
                         count = rs.getInt(1);
                       if( count < MIN_MZS  ) {  acess();  }
                         else
                          {
                           if(count < MAX_MZS)
                           {
                            int ws = JOptionPane.showConfirmDialog (parent,count+"MAGAZINES R ISSUED . R U GIVE ONE MORE . . . ?","warning",JOptionPane.YES_NO_OPTION  ) ;
                            if(ws==0)
                              {
                                acess();
                              }
                            else
                              {
                                //RESET STAFF VALUES
                                  no.setText("");
                                  n1.setText("");
                                  dr.setText("");
                                  jd1.setText("");
                                  image.setIcon (icon1);

                                //RESET BOOK VALUES
                                  mn1.setText("");
                                  vol1.setText("");
                                  mc1.setText("");

                                //RESET DATES
                                  idt1.setText("");
                                  rd1.setText("");

                                  iss.setEnabled(false);
                              }
                             }
                             else
                             {
                                 JOptionPane.showMessageDialog(null,"MAXIMUM  PERIOD EXCEEDED ! . . .");
                                 //RESET STAFF VALUES
                                  no.setText("");
                                  n1.setText("");
                                  dr.setText("");
                                  jd1.setText("");
                                  image.setIcon (icon1);

                                //RESET BOOK VALUES
                                  mn1.setText("");
                                  vol1.setText("");
                                  mc1.setText("");

                                //RESET DATES
                                  idt1.setText("");
                                  rd1.setText("");

                                  iss.setEnabled(false);
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


        can.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                setVisible(false);
              }
          }
        );

        next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                //RESET STAFF VALUES
                   no.setText("");
                   n1.setText("");
                   n1.setEditable(false);
                   dr.setEditable(false);
                   dr.setText("");
                   jd1.setText("");
                   jd1.setEditable(false);
                   image.setIcon (icon1);

                //RESET BOOK VALUES
                   mn1.setText("");
                   vol1.setText("");
                   mc1.setText("");
                   mc1.setEditable(false);
                   mc1.setVisible(true);
                   bkno.setVisible(false);
                   bkno.removeAllItems();

                //RESET DATES
                   idt1.setText("");
                   rd1.setText("");
                   idt1.setEditable(false);
                   rd1.setEditable(false);

                   iss.setEnabled(false);
                   constraints=null;
              }
          }
        );
        next_rec.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                 bkno.removeAllItems();
                 mn1.setText("");
                 vol1.setText("");
                 idt1.setText("");
                 rd1.setText("");
                 mzRecord();
              }
          }
        );
      }
    private void mzRecord()
      {
         try
           {
              s = con.createStatement();

              rs = s.executeQuery("SELECT LID,MZNAME,VOLUME,MONTH,PUBLISHER FROM STF_RESERVE WHERE LID='"+no.getText().toUpperCase()+"' AND MZNAME IS NOT NULL");
              if( rs.next() )
                {
                   bname = rs.getString(2);
                   if( bname==null||bname.equals("") ){}
                   else{constraints = " MZ.MZNAME = '"+bname+"'";}
                   //JOptionPane.showMessageDialog(null,""+bname);
                   aname1 = rs.getString(3);
                   if( aname1==null||aname1.equals("") ){}
                   else{constraints =constraints+" AND MZ.VOLUME = '"+aname1+"'";}
                   String aname2 = rs.getString(4);
                   if( aname2==null||aname2.equals("") ){}
                   else{constraints =constraints+ " AND MZ.PCITY = '"+aname2+"'";}
                   String aname3 = rs.getString(5);
                   if( aname3==null||aname3.equals("") ){}
                   else{constraints = constraints+" AND PUBLISHER = '"+aname3+"'";}
                   //JOptionPane.showMessageDialog(null,"  2  "+constraints);

                   rs = s.executeQuery("SELECT COUNT(MZNAME) FROM STF_RESERVE WHERE LID='"+no.getText().toUpperCase()+"'");
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
                   rs = s.executeQuery("SELECT LIB.ACESSNO FROM LIBRARY LIB WHERE LIB.ACESSNO NOT IN(SELECT BK.CODE FROM BKTRANSACTION BK WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN(SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )  AND"+constraints);//BOOKNAME='"+bname1+"' AND AUTHOR1S||AUTHOR1F ='"+aname1+"' AND AUTHOR2S||AUTHOR2F ='"+aname2+"AND AUTHOR3S||AUTHOR3F ='"+aname3+"'");
                   while( rs.next() )
                     {
                       value = rs.getString(1);
                       //JOptionPane.showMessageDialog(null,"erweywry"+value);
                       bkno.addItem(value);
                       mn1.setText( bname );
                       vol1.setText( aname1 );
                       mc1.setVisible(false);
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
            s=con.createStatement ();

            //INSERTION FOR BKTRANSACTION
             rs=s.executeQuery("SELECT STATUS FROM STAFF WHERE LID="+"'"+no.getText()+"'");
             while(rs.next())
             {

                 if(rs.getString(1).equalsIgnoreCase("TRUE"))
                 {

                     int a = s.executeUpdate( "INSERT INTO MZTRANSACTION1( MZCODE, LID,  IDATE, RDATE ) VALUES ( "+"'"+mc1.getText()+"',"
                                                                                                                  +"'"+no.getText()+"',"
                                                                                                                  +"'"+idt1.getText()+"',"
                                                                                                                  +"'"+rd1.getText()+"'"+")" );



                    con.commit( );
                    getTable2( );

                    int b = s.executeUpdate( "INSERT INTO BOOKDETAILS ( MZCODE, LID, VALUE, IDATE, RDATE) VALUES (  "+"'"+mc1.getText()+"',"
                                                                                                                     +"'"+no.getText()+"',"
                                                                                                                     +"'3'"
                                                                                                                     +",'"+idt1.getText()+"',"
                                                                                                                     +"'"+rd1.getText()+"'"+")" );



                    con.commit();
                    getTable( no.getText() );
                    getTable3( );

                    if(bname==null||bname.equals("")||aname1==null||aname1.equals("")){}
                        else
                        {
                        int k =s.executeUpdate("DELETE FROM STF_RESERVE WHERE LID='"+no.getText().toUpperCase()+"' AND MZNAME='"+bname+"' AND VOLUME='"+aname1+"'");//"+constraints);
                        con.commit();
                        //JOptionPane.showMessageDialog(null,"deleted");
                        }


                 }
                 else
                 {
                    JOptionPane.showMessageDialog (null,"TEACHING PERIOD HAS OVERED ! . . .","PROFESSOR'S DATABASE",JOptionPane.INFORMATION_MESSAGE);
                 }
               }
              s.close( );
        }
                  catch(SQLException sq)
                  {
                     JOptionPane.showMessageDialog (null,sq.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                     sq.printStackTrace();
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
                             rs = s.executeQuery("SELECT PASSWORD,LID FROM STAFF WHERE LID="+"'"+no.getText()+"'" );
                             if( rs.next() )
                               {
                                  if( new String( pwdfd.getPassword() ).equals(rs.getString(1)) )
                                    {
                                       //SET STUDENT DETAILS
                                       staffRecord();
                                       mc1.setEditable(true);
                                       mzRecord();
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
              old.setEditable(false);
              old.setBackground(Color.lightGray);
              old.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add( new1  = new JPasswordField(),new AbsoluteConstraints(125+10,134,130,20));
              new1.setEditable(false);
              new1.setBackground(Color.lightGray);
              new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              yes.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        old.setEditable(true);
                        old.setBackground(Color.white);
                        no2.setEnabled(false);
                     }
                }
              );

              no2.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        new1.setEditable(false);
                        yes.setEnabled(false);
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
                             rs = s.executeQuery("SELECT PASSWORD FROM STAFF WHERE LID ="+"'"+no.getText()+"'"+"AND PASSWORD ="+"'"+new String( old.getPassword())+"'");
                                if( rs.next() )
                                  {
                                    //if( )//rs.getString(2).equals(no.getText()))
                                     // {
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
                             rs = s.executeQuery("SELECT PASSWORD FROM STAFF WHERE PASSWORD="+"'"+new String(new1.getPassword())+"'");
                             if( rs.next() )
         		       {
 				  JOptionPane.showMessageDialog(null,"PASSWORD ALREADY EXISTS !  .  .  .");
			          new1.setText("");
                               }
                             else
                               {
                                  int h = s.executeUpdate("UPDATE STAFF SET PASSWORD ="+"'"+new String(new1.getPassword())+"'"+"WHERE LID = "+"'"+no.getText()+"'" );
  				  staffRecord();
                                  setVisible(false);
                               }
                             con.commit();
                             s.close();
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

    private void staffRecord()
      {
         try
           {
               s = con.createStatement();
               rs = s.executeQuery ( "SELECT LID, LNAME, DEPT,TO_CHAR(JOININGDATE,'DD-MON-YYYY')  FROM STAFF WHERE  LID ="+"'"+no.getText()+"'" );
                  if ( rs.next() )
                    {
                       //SET STAFF_MEMBER DETAILS
                       n1.setText(rs.getString(2));
                       n1.setEditable(true);
                       dr.setEditable(true);
                       jd1.setEditable(true);
                       dr.setText(rs.getString(3));
                       jd1.setText(rs.getString(4));
                       icon = new ImageIcon(PATH+no.getText()+".jpg");
                       image.setIcon (icon);
                    }
           }
         catch(SQLException sq)
           {
              JOptionPane.showMessageDialog(null,sq.getMessage());
           }
      }

    private void inputDates()
      {
        try
          {
             //FOR ISSUEDATE & RETRIVE DATE
             rs = s.executeQuery( " SELECT TO_CHAR ( SYSDATE, 'DD-MON-YYYY' ) , TO_CHAR ( SYSDATE+"+PERIOD+", 'DD-MON-YYYY' ) FROM DUAL" );

                if( rs.next() )
                 {
                    idt1.setText( rs.getString( 1 ) );
                    rd1.setText( rs.getString( 2 ) );
                    rd1.setEditable(true);
                    iss.setEnabled(true);

                 }
          }
        catch(SQLException sqlex)
          {
             JOptionPane.showMessageDialog (null,sqlex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
             sqlex.printStackTrace();
          }
      }


    private void getTable( String j )
      {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {

             rs=s.executeQuery ("SELECT MZ.MZNAME, MZ.VOLUME, MZ.ACCESSNO,TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE , TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE FROM BOOKDETAILS BD,MZDETAILS MZ WHERE BD.LID="+"'"+j+"'"+"AND BD.VALUE='3'"+"AND MZ.ACCESSNO=BD.MZCODE" );
             if (rs.next ())
              {
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                columnHeads.addElement( rsmd.getColumnName(i) );

                do
                 {
                   rows.addElement( getNextRow( rs,rsmd ) );
                 }while( rs.next() );

                lttable = new JTable(rows,columnHeads);
                lttable.setBackground(Color.pink);
                lttable.setEnabled (false);
                p2.add(lttable,BorderLayout.CENTER);
                JScrollPane spane =new JScrollPane(lttable);
                p2.add(spane,BorderLayout.CENTER);
                validate();
              }
             else
              {
                JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY !...","LECTURER DATABASE",JOptionPane.INFORMATION_MESSAGE );
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
              rs = s.executeQuery ( "SELECT MZ.MZNAME,MZ.VOLUME,MZ.ACCESSNO,STF.LID,STF.LNAME,STF.DEPT,TO_CHAR(MZT1.IDATE,'DD-MON-YYYY') IDATE,  TO_CHAR(MZT1.RDATE,'DD-MON-YYYY') RDATE FROM MZTRANSACTION1 MZT1,STAFF STF, MZDETAILS MZ WHERE STF.LID=MZT1.LID AND MZ.ACCESSNO=MZT1.MZCODE" );
              if( rs.next () )
               {
                  ResultSetMetaData rsmd = rs.getMetaData();
                  for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                  columnHeads.addElement( rsmd.getColumnName(i) );

                  do
                   {
                      rows.addElement( getNextRow( rs,rsmd ) );
                   }while( rs.next() );

                  bktransaction1 = new JTable( rows, columnHeads );
                  bktransaction1.setBackground(Color.cyan);
                  bktransaction1.setEnabled (false);
                  p3.add(bktransaction1,BorderLayout.CENTER);
                  p3.add(sp1 = new JScrollPane(bktransaction1));
                  validate();
               }
              else
               {
                 JOptionPane.showMessageDialog( null,"NO RECORDS TO DISPLAY !...","CURRENT TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE );
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
              rs = s.executeQuery ( "  SELECT MZ.ACCESSNO,MZ.MZNAME,MZ.VOLUME FROM MZDETAILS MZ WHERE MZ.ACCESSNO NOT IN(SELECT MZT.MZCODE FROM MZTRANSACTION MZT WHERE MZT.MZCODE=MZ.ACCESSNO) AND MZ.ACCESSNO NOT IN(SELECT MZT1.MZCODE FROM MZTRANSACTION1 MZT1 WHERE MZT1.MZCODE=MZ.ACCESSNO)" );
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
                  templibrary.setEnabled (false);
                  p4.add(templibrary,BorderLayout.CENTER);
                  p4.add(sp2 = new JScrollPane(templibrary),BorderLayout.CENTER);
                  validate();
               }
              else
               {
                  JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !..."," AVAILABLE MAGEZINES",JOptionPane.INFORMATION_MESSAGE);
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
        MzStfIss i = new MzStfIss();
      }
  }