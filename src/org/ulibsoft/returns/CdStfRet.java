package org.ulibsoft.returns;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;
import org.ulibsoft.enroll.CdLibrary2;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class CdStfRet extends JFrame
  {
    private JLabel cdcode, lid, sname, dept, image;
    private JLabel cdname, cdvrsion, idate, rdate ;
    private Icon icon,icon1;
    private JTextField cd, no, sn, dt,  nm, vrs, i1, r1 ;
    private JTable bookdetails, templibrary, bktransaction1 ;
    private JButton ret, can, next ;
    private JPanel p1, p2, p3, srpn ;
    private Container c;
    private CdStfRet staffret;

    private Statement s;
    private Connection con;
    private ResultSet rs, rs1 ;
    private String PATH;

    public CdStfRet()
      {
         super("RETRIEVING CDS/FLOPPYS/CASSETTE");
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         show();

         createComponents();
       try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
         try
         {
             s=con.createStatement();
             getTable1();
             rs=s.executeQuery("SELECT CD.CDCODE,CD.CDNAME,CD.CDVERSION  FROM CDDETAILS CD WHERE CD.CDCODE NOT IN(SELECT CDT.CDCODE FROM CDTRANSACTION CDT WHERE CDT.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT CDT1.CDCODE FROM CDTRANSACTION1 CDT1 WHERE CDT1.CDCODE=CD.CDCODE)");
             while(rs.next())
             {
                 getTable2(rs);
             }

              s.close();
         }catch(SQLException sqlex)
         {
            JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
         }
         try
          {
            s=con.createStatement();
            rs=s.executeQuery("SELECT IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID=2");
            while(rs.next())
            {
             PATH=rs.getString(1);
            }
          }catch(SQLException se)
          {
            JOptionPane.showMessageDialog(null,se.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     se.printStackTrace();
          }
         componentListener();
         myadapter5 myp=new myadapter5();
         addWindowListener(myp);
      }
    private void createComponents()
      {
         c = getContentPane();
         c.setLayout( new AbsoluteLayout() );
         c.setBackground (new Color(0,0,40));

         srpn = new JPanel( new AbsoluteLayout() );
         srpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "RETRIVING CDS", 1, 2, c.getFont(), Color.magenta));
         srpn.setBackground(c.getBackground());
         c.add(srpn,new AbsoluteConstraints(80,20,630,190));

         //INITIALIZE BOOK DETAILS

         cdcode = new JLabel ( "CD  CODE. . . . . " );
         cdcode.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( cdcode, new AbsoluteConstraints ( 25, 21+5 ) );

         cd = new JTextField( );
         cd.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         cd.setForeground ( new Color ( 255, 0, 153 ) );
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( cd, new AbsoluteConstraints ( 120, 20+5, 130, 20 ) );

         cdname = new JLabel("CD  NAME . . . .");
         cdname.setForeground (new Color (120, 120, 153));
         srpn.add (cdname, new AbsoluteConstraints (25, 51+5));

         nm = new JTextField( );
         nm.setEditable(false);
         nm.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         nm.setForeground ( new Color ( 255, 0, 153 ) );
         nm.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( nm, new AbsoluteConstraints ( 120, 50+5, 130, 20 ) );

         cdvrsion = new JLabel( "VERSION . . . . ." );
         cdvrsion.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( cdvrsion, new AbsoluteConstraints ( 25, 81+5 ) );

         vrs = new JTextField( );
         vrs.setEditable( false );
         vrs.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         vrs.setForeground ( new Color ( 255, 0, 153 ) );
         vrs.setCaretColor ( new Color (0, 204, 102 ) );
         srpn.add ( vrs, new AbsoluteConstraints ( 120, 80+5, 130, 20 ) );

         //INITIALIZE STUDENT DETAILS

         lid = new JLabel( "LECTURER ID . . . ." );
         lid.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( lid, new AbsoluteConstraints ( 370, 22+5 ) );

         no = new JTextField( );
         no.setEditable( false );
         no.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( no, new AbsoluteConstraints ( 480, 20+5, 130, 20 ) );

         sname = new JLabel( "LECTURER  NAME" );
         sname.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( sname, new AbsoluteConstraints ( 370, 52+5 ) );

         sn = new JTextField( );
         sn.setEditable( false );
         sn.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         sn.setForeground ( new Color ( 255, 0, 153 ) );
         sn.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( sn, new AbsoluteConstraints ( 480, 50+5, 130, 20 ) );

         image = new JLabel( );
         image.setBounds(260,21,100,110);
         image.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.cyan ) );
         srpn.add( image,new AbsoluteConstraints(260,21+5,100,110));

         icon1=new ImageIcon("empty.jpg");

         dept = new JLabel( "DEPARTMENT . . . " );
         dept.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( dept, new AbsoluteConstraints (370, 82+5 ) );

         dt = new JTextField( );
         dt.setEditable( false );
         dt.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         dt.setForeground ( new Color ( 255, 0, 153 ) );
         dt.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( dt, new AbsoluteConstraints ( 480, 80+5, 130, 20 ) );

         //INITTALIZE DATE DETAILS

         idate = new JLabel( "ISSUE DATE . . " );
         idate.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( idate, new AbsoluteConstraints ( 25, 112+5 ) );

         i1 = new JTextField( );
         i1.setEditable( false );
         i1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         i1.setForeground ( new Color ( 255, 0, 153 ) );
         i1.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( i1, new AbsoluteConstraints ( 120, 110+5 , 130, 20 ) );

         rdate = new JLabel( "RECEIVE  DATE . . " );
         rdate.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( rdate, new AbsoluteConstraints ( 370, 112+5 ) );

         r1 = new JTextField( );
         r1.setEditable( false );
         r1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         r1.setForeground ( new Color ( 255, 0, 153 ) );
         r1.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( r1, new AbsoluteConstraints ( 480, 110+5, 130, 20 ) );

         //BUTTON CREATION

         ret = new JButton( "RECIEVE" );
         ret.setMnemonic ('R');
         ret.setBackground ( Color.cyan );
         ret.setForeground (  Color.black   );
         ret.setBorder ( new BevelBorder(0) );
         ret.setEnabled(false);
         srpn.add ( ret, new AbsoluteConstraints ( 155-10, 150, 110, 27 ) );

         next = new JButton( "NEXT>>>" );
         next.setMnemonic ('N');
         next.setBackground ( Color.cyan );
         next.setForeground ( Color.black   );
         next.setBorder ( new BevelBorder(0) );
         srpn.add ( next, new AbsoluteConstraints ( 267-10, 150, 110, 27 ) );

         can = new JButton( "EXIT" );
         can.setMnemonic ('X');
         can.setBackground ( Color.cyan );
         can.setForeground (  Color.black  );
         can.setBorder ( new BevelBorder(0)  );
         srpn.add ( can, new AbsoluteConstraints ( 379-10 , 150, 110, 27 ) );

         p1 = new JPanel( new BorderLayout() );
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CD  DETAILS", 1, 2,c.getFont(),Color.magenta));
         c.add(p1,new AbsoluteConstraints(15, 220, 480, 110));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT TRANSACTION", 1, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(30,340,730,210));

         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE  CDS", 1, 2, c.getFont(), Color.magenta));
         c.add(p3,new AbsoluteConstraints(510,220,270,110));

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
                   //p.setFrame(Index.cdrt);
                 }
              }
          }
        );

        cd.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                try
                  {
                     ret.setEnabled(true);
                     s = con.createStatement ();
                     rs = s.executeQuery("SELECT STF.LID, STF.LNAME, STF.DEPT, CD.CDNAME, CD.CDVERSION, CD.CDCODE, TO_CHAR(CDT1.IDATE,'DD-MON-YYYY') , TO_CHAR(CDT1.RDATE,'DD-MON-YYYY')  FROM CDTRANSACTION1 CDT1,CDDETAILS CD,STAFF STF WHERE CDT1.LID=STF.LID AND CD.CDCODE=CDT1.CDCODE AND CDT1.CDCODE="+"'"+ cd.getText()+"'" );
                       if( rs.next() )
                        {
                          // SET TRANSACTION DETAILS
                          no.setText( rs.getString(1) ) ;
                          sn.setText( rs.getString(2) ) ;
                          dt.setText( rs.getString(3) ) ;
                          nm.setText( rs.getString(4) ) ;
                          vrs.setText ( rs.getString(5) ) ;
                          i1.setText(rs.getString (7) ) ;
                          r1.setText(rs.getString(8) ) ;

                          PassWordDialog PWD=new PassWordDialog(staffret,"PASSWORD CONFIRMATION");
                          icon = new ImageIcon(PATH+no.getText()+".jpg");
                          image.setIcon (icon);
                        }
                       else
                        JOptionPane.showMessageDialog (null,"CD'S RECORD NOT FOUND !...","CURRENT-TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
                     s.close();
                  }
                catch(SQLException sqlex)
                  {
                     JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
                  }
              }

          }
        );

        ret.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                try
                  {
                     s = con.createStatement();
                     rs = s.executeQuery( "SELECT TO_CHAR( SYSDATE, 'yyyy-mm-dd' ) FROM DUAL" );
                       if( rs.next() )
                        {
                                 //INSERTION
                        /*  int r = s.executeUpdate(" INSERT INTO CDLIBRARY ( CDNAME, CDVERSION, CDCODE) VALUES( "+"'"+nm.getText()+"',"
                                                                                                               +"'"+vrs.getText()+"',"
                                                                                                               +"'"+cd.getText()+"'"+")" );*/
                           con.commit();

                           rs = s.executeQuery( "SELECT CD.CDCODE,CD.CDNAME,CD.CDVERSION  FROM CDDETAILS CD WHERE CD.CDCODE NOT IN(SELECT CDT.CDCODE FROM CDTRANSACTION CDT WHERE CDT.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT CDT1.CDCODE FROM CDTRANSACTION1 CDT1 WHERE CDT1.CDCODE=CD.CDCODE)" );
                             if(rs.next())
                              {
                                getTable2(rs);
                              }
                             else
                              {
                                JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !...","AVAILABLE CDS",JOptionPane.INFORMATION_MESSAGE);
                              }

                                  //DELETION
                             int AS=s.executeUpdate("UPDATE CDTRANSACTION1 SET RDATE=TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE CDCODE="+"'"+cd.getText()+"'"+"AND IDATE="+"'"+i1.getText()+"'");
                             int g=s.executeUpdate("UPDATE BOOKDETAILS  SET RDATE=TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE CDCODE="+"'"+cd.getText()+"'"+"AND IDATE="+"'"+i1.getText()+"'"+"AND LID ="+"'"+no.getText()+"'");
                           int x = s.executeUpdate("DELETE FROM CDTRANSACTION1 WHERE CDCODE ="+"'"+cd.getText()+"'" );
                           con.commit();
                           getTable1();
                           getTable( cd.getText() );

                            rs=s.executeQuery("SELECT ISSUED FROM CDDETAILS WHERE CDCODE="+"'"+cd.getText() +"'");
                               while(rs.next())
                               {
                                 if(rs.getString(1).equals("1"))
                                 {
                                   NewDialog nop=new NewDialog((Frame)staffret,"CONFIRMATION" );
                                 }
                               }
                        }
                     s.close();
                  }
                catch(SQLException sqlex)
                  {
                     JOptionPane.showMessageDialog(null,sqlex.getMessage(),"EXception",JOptionPane.ERROR_MESSAGE);
                     sqlex.printStackTrace();
                  }
              }
          }
        );

        next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                //RESET BOOK VALUES
                  cd.setText("");
                  nm.setText("");
                  vrs.setText("");

                //RESET STAFF VALUES
                  no.setText("");
                  sn.setText("");
                  dt.setText("");
                  image.setIcon (icon1);

                //RESET DATE VALUES
                  i1.setText("");
                  r1.setText("");

                  ret.setEnabled(false);
              }
          }
        );

        can.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                setVisible(false);
              }
          }
        );
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

              getContentPane().add(sub = new JButton("SUBMIT"),new AbsoluteConstraints(85,55,100,22));
              sub.setBackground(Color.cyan);
              sub.setMnemonic('S');
              sub.setBorder(new BevelBorder(0));



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
                                        ret.setEnabled (true);
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

   private void getTable(String j)
     {
        Vector columnHeads = new Vector();
        Vector rows = new Vector();
        try
          {
            rs = s.executeQuery ("SELECT STF.LID, STF.LNAME, STF.DEPT, TO_CHAR(BD.IDATE,'DD-MON-YYYY') IDATE, TO_CHAR(BD.RDATE,'DD-MON-YYYY') RDATE  FROM BOOKDETAILS BD,STAFF STF WHERE BD.CDCODE="+"'"+j+"'"+"AND BD.VALUE='2'"+"AND BD.LID=STF.LID");
              if(rs.next ())
               {
                  ResultSetMetaData rsmd = rs.getMetaData();
                  for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                  columnHeads.addElement( rsmd.getColumnName(i) );

                  do
                   {
                     rows.addElement( getNextRow( rs,rsmd ) );
                   }while( rs.next() );

                  bookdetails = new JTable(rows,columnHeads);
                  bookdetails.setBackground(Color.cyan);
                  bookdetails.setEnabled(false);
                  p1.add(bookdetails,BorderLayout.CENTER);
                  JScrollPane sp3 = new JScrollPane(bookdetails);
                  p1.add(sp3,BorderLayout.CENTER);
                  validate();
               }
              else
               {
                  JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !...","CD DATABASE",JOptionPane.INFORMATION_MESSAGE);
               }
          }
        catch( SQLException sqlex )
          {
             sqlex.printStackTrace();
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
          }
      }

    private void getTable1( )
      {

        Vector columnHeads = new Vector();
        Vector rows = new Vector();
        try
          {
            rs = s.executeQuery ( "SELECT CD.CDNAME,CD.CDVERSION,CD.CDCODE,STF.LID,STF.LNAME,STF.DEPT,TO_CHAR(CDT1.IDATE,'DD-MON-YYYY') IDATE,TO_CHAR(CDT1.RDATE,'DD-MON-YYYY') RDATE FROM CDTRANSACTION1 CDT1,STAFF STF,CDDETAILS CD WHERE CD.CDCODE=CDT1.CDCODE AND CDT1.LID=STF.LID" );
            if( rs.next () )
             {
               ResultSetMetaData rsmd = rs.getMetaData();
               for(int i = 1;i <= rsmd.getColumnCount(); i++ )
               columnHeads.addElement( rsmd.getColumnName(i) );

               do
                {
                  rows.addElement( getNextRow( rs,rsmd ) );
                }while( rs.next() );

               bktransaction1 = new JTable(rows,columnHeads);
               bktransaction1.setBackground(Color.pink);
               bktransaction1.setEnabled(false);
               p2.add(bktransaction1,BorderLayout.CENTER);
               JScrollPane sp1 = new JScrollPane(bktransaction1);
               p2.add(sp1,BorderLayout.CENTER);
               validate();
             }
            else
             {
               JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !...","CURRENT TRANSACTIONS",JOptionPane.INFORMATION_MESSAGE);
             }
          }
        catch( SQLException sqlex )
          {
            sqlex.printStackTrace();
            JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
          }
     }
   private void getTable2(ResultSet rs)
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

            templibrary = new JTable(rows,columnHeads);
            templibrary.setBackground(Color.cyan);
            templibrary.setEnabled(false);
            p3.add(templibrary,BorderLayout.CENTER);
            JScrollPane sp2 = new JScrollPane(templibrary);
            p3.add(sp2,BorderLayout.CENTER);
            validate();

          }
        catch( SQLException sqlex )
          {
             sqlex.printStackTrace();
             JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
         }
     }
   private Vector getNextRow( ResultSet rs,ResultSetMetaData rsmd )
    throws SQLException
     {
       Vector currentRow = new Vector();

       for(int i=1;i <= rsmd.getColumnCount();i++ )
           switch( rsmd.getColumnType(i) )
            {
              case Types.VARCHAR: currentRow.addElement( rs.getString(i));
                                  break;
              default:            currentRow.addElement(rs.getDate(i));
            }
       return currentRow;
     }

   private class myadapter5 extends WindowAdapter
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
            setVisible(false);
            dispose();
         }
     }
private class NewDialog extends JDialog
     {
        private JLabel msg;
        private JButton yes,no;
        private Container jk;
        public NewDialog(Frame parent,String Title)
        {
         // JOptionPane.showMessageDialog(null,"cdcvdfd");
           jk=getContentPane();
           jk.setLayout(new AbsoluteLayout());
           jk.setBackground(new Color(120,120,180));
           msg=new JLabel("DO  U  WANT  TO  UPDATE  THIS  .  .  .  . .");
           msg.setForeground(Color.white);
           jk.add(msg,new AbsoluteConstraints(20,20));

           yes=new JButton("YES");
           yes.setBorder ( new BevelBorder(0) );
           yes.setBackground(Color.cyan);
           yes.setForeground(Color.magenta);
           yes.setMnemonic('Y');
           jk.add(yes,new AbsoluteConstraints(60-40,60,110,25));

           no=new JButton("NO");
           no.setBorder ( new BevelBorder(0) );
           no.setBackground(Color.cyan);
           no.setForeground(Color.magenta);
           no.setMnemonic('N');
           jk.add(no,new AbsoluteConstraints(172-40,60,110,25));

           yes.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent ae)
             {
                CdLibrary2 cd2=new CdLibrary2();
                setVisible(false);
             }
           }
           );

           no.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent ae)
             {
               setVisible(false);
             }
           }
           );
           setBounds(250,250,270,130);
           setVisible(true);
        }

     }
     public static void main(String a[])
       {
         CdStfRet rt=new CdStfRet( );
       }
  }


