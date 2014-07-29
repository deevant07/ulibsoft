package org.ulibsoft.clearence;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.border.*;
import java.util.* ;
import javax.swing.text.* ;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;


public class Clear extends JFrame
  {
    private JMenuBar bar;
    private JMenu fileMenu, printMenu, helpMenu, certifyMenu;
    private JMenuItem bkitem,cditem,mzitem;
    private JMenuItem stdItem,stfItem,certifyItem;
    private JMenuItem helpItem,aboutItem,exit;

    private JLabel adno,sn,sb,batch,image ;
    private JLabel adno1,sn2,sb2,batch1,image1;
    private Icon icon,icon1;

    private JButton next, can, book, cd, magezine;
    private JButton next1, can1, book1, cd1, magezine1;

    private JTextField no,sn1,sb1,sbt1;
    private JTextField no1,sn3,sb3,sbt2;

    private JTable plist,plist1,plist2;

    private JScrollPane sp,sp1,sp2;

    private JTextPane certificate ;

    private JPanel panel,pn,pn2,pn3,cmppn;
    private JPanel stfpn;

    private static String path="c:\\MyPictures\\";
    private ResultSet rs,rs1,rs2;
    private Container c;
    private Statement s;
    private Connection con;

    private String t1,status,sdate,dt,t2,sname,branch;
    private int s1,yjoin,count=0,stage=0;
    private double diff;

    public Clear()
      {
         super("CLEARENCE && CERTIFICATION");
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         show();

        try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
         createComponents();
         componentListener();
         MyAdapter7 myap = new MyAdapter7();
         addWindowListener(myap);
      }
    private void createComponents()
      {
        c = getContentPane();
        c.setLayout( new AbsoluteLayout() );
        c.setBackground ( new Color( 0, 0, 40 ) );

        bar = new JMenuBar();
        setJMenuBar( bar );

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        fileMenu.add( certifyMenu = new JMenu("Certify") );
        certifyMenu.add( stdItem = new JMenuItem("Student") );
        certifyMenu.add( stfItem = new JMenuItem("Staff") );
        certifyMenu.setMnemonic('e');
        stdItem.setMnemonic('d');
        stfItem.setMnemonic('f');

        fileMenu.add( helpItem = new JMenuItem("Help") );
        helpItem.setMnemonic('H');

        fileMenu.add(aboutItem = new JMenuItem("About...") );
        aboutItem.setMnemonic('A');

        fileMenu.add(exit = new JMenuItem("Exit") );
        exit.setMnemonic('x');

        fileMenu.setBackground(new Color(120,120,240));
        fileMenu.setBorder(new BevelBorder(0));

        printMenu = new JMenu("Print");
        printMenu.setMnemonic('P');

        printMenu.add( bkitem = new JMenuItem("Book") );
        bkitem.setMnemonic('B');

        printMenu.add( cditem = new JMenuItem("Cd/Floppy") );
        bkitem.setMnemonic('D');

        printMenu.add( mzitem = new JMenuItem("Magezine") );
        bkitem.setMnemonic('M');

        printMenu.add(certifyItem = new JMenuItem("Certificate") );
        certifyItem.setMnemonic('C');

        printMenu.setBackground(new Color(240,120,240));
        printMenu.setBorder(new BevelBorder(0));

        bar.add( fileMenu );
        bar.add( printMenu );

        bar.setBackground(new Color(0,0,40));
        bar.setBorder( new BevelBorder ( 0 ));

        cmppn = new JPanel( new AbsoluteLayout() );
        cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                           "CLEARENCE && CERTIFICATION", 1, 2, c.getFont(), Color.magenta));
        cmppn.setBackground(c.getBackground());
        c.add(cmppn,new AbsoluteConstraints(185,20,430,225));
        //cmppn.setVisible(false);

        adno = new JLabel( "ADMISSION - NUMBER" );
        adno.setForeground ( new Color ( 120, 120, 153 ) );
        cmppn.add( adno, new AbsoluteConstraints( 25, 30 ) );

        no = new JTextField( 15 );
        no.setBorder ( new MatteBorder ( 1, 1, 1, 1, c.getBackground() ) );
        no.setForeground (new Color ( 255, 0, 153 ) );
        cmppn.add(no,new AbsoluteConstraints(160,28,125,20));

        sn = new JLabel ( "<= STUDENT-NAME =>" );
        sn.setForeground ( new Color ( 120, 120, 153 ) );
        sn.setHorizontalAlignment ( SwingConstants.RIGHT );
        cmppn.add ( sn, new AbsoluteConstraints( 25, 60 ) );

        sn1 = new JTextField ( );
        sn1.setEditable(false);
        sn1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        sn1.setForeground ( new Color ( 255, 0, 153 ) );
        sn1.setCaretColor ( new Color ( 0, 204, 102 ) );
        cmppn.add ( sn1, new AbsoluteConstraints( 160, 58, 125, 20 ) );

        sb = new JLabel ( "STUDENT--COURCE>>" );
        sb.setForeground ( new Color ( 120, 120, 153 ) );
        cmppn.add ( sb, new AbsoluteConstraints( 25 , 90 ) );

        sb1 = new JTextField ( );
        sb1.setEditable(false);
        sb1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        sb1.setForeground ( new Color ( 255, 0, 153 ) );
        sb1.setCaretColor ( new Color ( 0, 204, 102 ) );
        cmppn.add ( sb1, new AbsoluteConstraints(160, 90, 125, 20));

        batch = new JLabel ( "ACADEMIC - BATCH>>" );
        batch.setForeground ( new Color ( 120, 120, 153 ) );
        cmppn.add ( batch, new AbsoluteConstraints( 25, 120 ) );

        sbt1 = new JTextField ( );
        sbt1.setEditable(false);
        sbt1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        sbt1.setForeground ( new Color ( 255, 0, 153 ) );
        sbt1.setCaretColor ( new Color ( 0, 204, 102 ) );
        cmppn.add ( sbt1, new AbsoluteConstraints(160, 120, 125, 20));

        //IMAGE
        image = new JLabel( );
        image.setBounds(300,28,100,100);
        image.setBorder(new MatteBorder (1, 1, 1, 1, Color.cyan));
        cmppn.add(image,new AbsoluteConstraints(300,28,100,113));

        icon1=new ImageIcon("empty.jpg");

        //BUTTONB CREATION
        book = new JButton( "BOOK" ) ;
        book.setEnabled(false);
        book.setBackground ( Color.cyan );
        book.setForeground( Color.black );
        book.setBorder( new BevelBorder( 0 ));
        cmppn.add ( book, new AbsoluteConstraints( 60,160,100,22 ) );

        cd = new JButton( "CD/FLOPPY" ) ;
        cd.setEnabled(false);
        cd.setBackground ( Color.cyan );
        cd.setForeground( Color.black );
        cd.setBorder( new BevelBorder( 0 ));
        cmppn.add ( cd, new AbsoluteConstraints( 161,160,100,22 ) );

        magezine = new JButton( "MAGEZINE" ) ;
        magezine.setBackground ( Color.cyan );
        magezine.setEnabled(false);
        magezine.setForeground( Color.black );
        magezine.setBorder( new BevelBorder( 0 ));
        cmppn.add ( magezine, new AbsoluteConstraints( 262,160,100,22 ) );

        next = new JButton( "NEXT>>>" ) ;
        next.setBackground ( Color.cyan );
        next.setForeground( Color.black );
        next.setBorder( new BevelBorder( 0 ));
        cmppn.add ( next, new AbsoluteConstraints( 120,190,100,22 ) );

        can = new JButton( "EXIT" ) ;
        can.setBackground ( Color.cyan);
        can.setForeground( Color.black);
        can.setMnemonic('X');
        can.setBorder(new BevelBorder(0));
        cmppn.add ( can, new AbsoluteConstraints( 222, 190, 100, 22 ) );

        //STAFF'S DETAILS IN A PANEL
        stfpn = new JPanel( new AbsoluteLayout() );
        stfpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                           "CLEARENCE && CERTIFICATION", 1, 2, c.getFont(), Color.magenta));
        stfpn.setBackground(c.getBackground());
        c.add(stfpn,new AbsoluteConstraints(185,20,430,225));
        stfpn.setVisible(false);

        adno1 = new JLabel( "PROFESSOR ' S - ID  :" );
        adno1.setForeground ( new Color ( 120, 120, 153 ) );
        stfpn.add( adno1, new AbsoluteConstraints( 25+5, 30 ) );

        no1 = new JTextField( 15 );
        no1.setBorder ( new MatteBorder ( 1, 1, 1, 1, c.getBackground() ) );
        no1.setForeground (new Color ( 255, 0, 153 ) );
        stfpn.add(no1,new AbsoluteConstraints(160,28,125,20));

        sn2 = new JLabel ( "PROFESSOR NAME >" );
        sn2.setForeground ( new Color ( 120, 120, 153 ) );
        sn2.setHorizontalAlignment ( SwingConstants.RIGHT );
        stfpn.add ( sn2, new AbsoluteConstraints( 25+5, 60 ) );

        sn3 = new JTextField ( );
        sn3.setEditable(false);
        sn3.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        sn3.setForeground ( new Color ( 255, 0, 153 ) );
        sn3.setCaretColor ( new Color ( 0, 204, 102 ) );
        stfpn.add ( sn3, new AbsoluteConstraints( 160, 58, 125, 20 ) );

        sb2 = new JLabel ( "<=  DEPARTMENT  =>" );
        sb2.setForeground ( new Color ( 120, 120, 153 ) );
        stfpn.add ( sb2, new AbsoluteConstraints( 25+5 , 90 ) );

        sb3 = new JTextField ( );
        sb3.setEditable(false);
        sb3.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        sb3.setForeground ( new Color ( 255, 0, 153 ) );
        sb3.setCaretColor ( new Color ( 0, 204, 102 ) );
        stfpn.add ( sb3, new AbsoluteConstraints(160, 90, 125, 20));

        batch1 = new JLabel ( "<= JOBED PERIOD =>" );
        batch1.setForeground ( new Color ( 120, 120, 153 ) );
        stfpn.add ( batch1, new AbsoluteConstraints( 25+5, 120 ) );

        sbt2 = new JTextField ( );
        sbt2.setEditable(false);
        sbt2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        sbt2.setForeground ( new Color ( 255, 0, 153 ) );
        sbt2.setCaretColor ( new Color ( 0, 204, 102 ) );
        stfpn.add ( sbt2, new AbsoluteConstraints(160, 120, 125, 20));

        //ADDING BUTTONS AND IMAGE LABEL TO STAFF'S PANEL

        image1 = new JLabel( );
        image1.setBounds(300,28,100,100);
        image1.setBorder(new MatteBorder (1, 1, 1, 1, Color.cyan));
        stfpn.add(image1,new AbsoluteConstraints(300,28,100,113));


        book1 = new JButton( "BOOK" ) ;
        book1.setEnabled(false);
        book1.setBackground ( Color.cyan );
        book1.setForeground( Color.black );
        book1.setBorder( new BevelBorder( 0 ));
        stfpn.add ( book1, new AbsoluteConstraints( 60,160,100,22 ) );

        cd1 = new JButton( "CD/FLOPPY" ) ;
        cd1.setEnabled(false);
        cd1.setBackground ( Color.cyan );
        cd1.setForeground( Color.black );
        cd1.setBorder( new BevelBorder( 0 ));
        stfpn.add ( cd1, new AbsoluteConstraints( 161,160,100,22 ) );

        magezine1 = new JButton( "MAGEZINE" ) ;
        magezine1.setEnabled(false);
        magezine1.setBackground ( Color.cyan );
        magezine1.setForeground( Color.black );
        magezine1.setBorder( new BevelBorder( 0 ));
        stfpn.add ( magezine1, new AbsoluteConstraints( 262,160,100,22 ) );

        next1 = new JButton( "NEXT>>>" ) ;
        next1.setBackground ( Color.cyan );
        next1.setForeground( Color.black );
        next1.setBorder( new BevelBorder( 0 ));
        stfpn.add ( next1, new AbsoluteConstraints( 120,190,100,22 ) );

        can1 = new JButton( "EXIT" ) ;
        can1.setBackground ( Color.cyan);
        can1.setForeground( Color.black);
        can1.setMnemonic('X');
        can1.setBorder(new BevelBorder(0));
        stfpn.add ( can1, new AbsoluteConstraints( 222, 190, 100, 22 ) );


        certificate = new JTextPane  ( );
        certificate.setEditable (false);
        certificate.setBackground( new Color( 0, 0, 40 ) );
        certificate.setForeground( new Color( 255, 255, 255 ) );
        certificate.setFont( new Font( "Monotype Corsiva", Font.ITALIC, 15 ) );

        pn = new JPanel( new FlowLayout() );
        pn.setLayout(new BorderLayout());
        pn.setBackground( new Color( 0, 0, 40 ) );
        pn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                           "CERTIFICATE", 1, 2, c.getFont(), Color.magenta));
        c.add( pn,new AbsoluteConstraints( 130, 250, 540, 260 ) );

        panel = new JPanel( new FlowLayout() );
        panel.setLayout(new BorderLayout());
        panel.setBackground( new Color( 0, 0, 40 ) );
        panel.setVisible(false);
        panel.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                                             "DUE -- BOOKS", 1, 2, c.getFont(), Color.magenta));
        c.add( panel,new AbsoluteConstraints( 130, 250, 540, 260 ) );

        pn2 = new JPanel( new FlowLayout() );
        pn2.setLayout(new BorderLayout());
        pn2.setBackground( new Color( 0, 0, 40 ) );
        pn2.setVisible(false);
        pn2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                             "DUE -- CDS/FLOPPYS/CASETTAS", 1, 2, c.getFont(), Color.magenta));
        c.add( pn2,new AbsoluteConstraints( 130, 250, 540, 260 ) );

        pn3 = new JPanel( new FlowLayout() );
        pn3.setLayout(new BorderLayout());
        pn3.setBackground( new Color( 0, 0, 40 ) );
        pn3.setVisible(false);
        pn3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                             "DUE -- MAGEZINES", 1, 2, c.getFont(), Color.magenta));
        c.add( pn3,new AbsoluteConstraints( 130, 250, 540, 260 ) );

       // sts=new JLabel("");

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
                    PopUpMenu p = new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                  //  p.setFrame(Index.cr);
                 }
              }
          }
        );

       bkitem.addActionListener(new ActionListener()
         {
            public void actionPerformed( ActionEvent e)
              {
              }
         }
       );

       exit.addActionListener(new ActionListener()
         {
            public void actionPerformed( ActionEvent e)
              {
                setVisible(false);
              }
         }
       );

        no.addActionListener (new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                try
                  {
                    s = con.createStatement();
                    studentDetails();

                    rs = s.executeQuery("SELECT LIB.ACESSNO,LIB.BOOKNAME, LIB.AUTHOR1S, BK.IDATE, BK.RDATE FROM BKTRANSACTION BK,LIBRARY LIB WHERE BK.ADNO ="+"'"+ no.getText().toUpperCase()+"'"+"AND BK.CODE=LIB.ACESSNO" );
                       if(rs.next() )
                         {
                            count++;
                            book.setEnabled(true);
                            getTable(rs);
                         }

                    rs1 = s.executeQuery("SELECT CD.CDCODE,CD.CDNAME,CD.CDVERSION,CT.IDATE,CT.RDATE FROM CDTRANSACTION CT,CDDETAILS CD WHERE CT.ADNO ="+"'"+ no.getText().toUpperCase()+"'"+"AND CD.CDCODE=CT.CDCODE");
                        if( rs1.next() )
                          {
                             count++;
                             cd.setEnabled(true);
                             getTable1(rs1);
                          }

                    rs2 = s.executeQuery("SELECT MD.ACCESSNO,MD.MZNAME,MD.VOLUME,MT.IDATE,MT.RDATE FROM MZTRANSACTION MT,MZDETAILS MD WHERE MT.ADNO ="+"'"+ no.getText().toUpperCase()+"'"+"AND MD.ACCESSNO=MT.MZCODE");
                        if( rs2.next() )
                          {
                             count++;
                             magezine.setEnabled(true);
                             getTable2(rs2);
                          }
                    if( count == 0 )
                      {
                         certify();
                      }
                    s.close();
                  }
                catch(SQLException sqe)
                  {
                    JOptionPane.showMessageDialog (null,sqe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE );
                    sqe.printStackTrace();
                  }
              }
          }
        );
        no1.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                try
                  {
                     staffDetails();

                     s=con.createStatement();
                     rs = s.executeQuery("SELECT LIB.ACESSNO,LIB.BOOKNAME, LIB.AUTHOR1S,LIB.AUTHOR1F,BK1.IDATE, BK1.RDATE FROM BKTRANSACTION1 BK1,LIBRARY LIB WHERE BK1.LID ="+"'"+ no1.getText().toUpperCase()+"'"+"AND BK1.CODE=LIB.ACESSNO" );
                       if(rs.next() )
                         {
                            stage++;
                            book1.setEnabled(true);
                            getTable(rs);
                         }

                     rs1 = s.executeQuery("SELECT CD.CDCODE,CD.CDNAME,CD.CDVERSION,CT.IDATE,CT.RDATE FROM CDTRANSACTION1 CT,CDDETAILS CD WHERE CT.LID ="+"'"+ no1.getText().toUpperCase()+"'"+"AND CT.CDCODE=CD.CDCODE");
                        if( rs1.next() )
                          {
                             stage++;
                             cd1.setEnabled(true);
                             getTable1(rs1);
                          }

                     rs2 = s.executeQuery("SELECT MD.MZCODE,MD.MZNAME,MD.VOLUME,MK1.IDATE,MK1.RDATE FROM MZTRANSACTION1 MK1,MZDETAILS MD WHERE MK1.LID ="+"'"+ no1.getText().toUpperCase()+"'"+"AND MK1.MZCODE=MD.ACCESSNO");
                        if( rs2.next() )
                          {
                             stage++;
                             magezine.setEnabled(true);
                             getTable2(rs2);
                          }

                     if(stage==0)
                      {
                        certify_Staff();
                      }
                    con.commit();
                    s.close();
                  }
                catch(SQLException se)
                  {
                    JOptionPane.showMessageDialog(null,se.getMessage());
                  }
            }
        }
        );

        stdItem.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                stfpn.setVisible(false);
                cmppn.setVisible(true);
              }
          }
        );

        stfItem.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                cmppn.setVisible(false);
                stfpn.setVisible(true);
              }
          }
        );

        book.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                //panel.add(plist,BorderLayout.CENTER);
                //panel.add(sp,BorderLayout.CENTER);
                pn.setVisible(false);
                pn2.setVisible(false);
                pn3.setVisible(false);
                panel.setVisible(true);
              }
          }
        );

        cd.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                //pn2.add(plist1,BorderLayout.CENTER);
                //pn2.add(sp1,BorderLayout.CENTER);
                pn.setVisible(false);
                panel.setVisible(false);
                pn3.setVisible(false);
                pn2.setVisible(true);
              }
          }
        );
        magezine.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                //pn3.add(sp2,BorderLayout.CENTER);
                //pn3.add(plist2,BorderLayout.CENTER);
                pn.setVisible(false);
                panel.setVisible(false);
                pn2.setVisible(false);
                pn3.setVisible(true);
              }
          }
        );
        next.addActionListener (new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                    no.setText("");
                    sn1.setText( "");
                    sb1.setText("");
                    sbt1.setText("");
                    image.setIcon(icon1);

                    book.setEnabled(false);
                    cd.setEnabled(false);
                    magezine.setEnabled(false);

                    certificate.setText("");

                    pn.setVisible(true);
                    panel.setVisible(false);
                    pn2.setVisible(false);
                    pn3.setVisible(false);
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

       book1.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
                //panel.add(plist,BorderLayout.CENTER);
                //panel.add(sp = new JScrollPane(plist));
                pn.setVisible(false);
                pn2.setVisible(false);
                pn3.setVisible(false);
                panel.setVisible(true);
             }
         }
       );

       cd1.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
                //pn2.add(plist1,BorderLayout.CENTER);
                //pn2.add(sp1 = new JScrollPane(plist1));
                pn.setVisible(false);
                panel.setVisible(false);
                pn3.setVisible(false);
                pn2.setVisible(true);
             }
         }
       );
       magezine1.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
                //pn3.add(plist2,BorderLayout.CENTER);
                //pn3.add(sp = new JScrollPane(plist2));
                pn.setVisible(false);
                panel.setVisible(false);
                pn2.setVisible(false);
                pn3.setVisible(true);
            }
         }
       );

       next1.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
                no1.setText("");
                sn3.setText( "");
                sb3.setText("");
                sbt2.setText("");
                image1.setIcon(icon1);

                book1.setEnabled(false);
                cd1.setEnabled(false);
                magezine1.setEnabled(false);

                certificate.setText("");

                pn.setVisible(true);
                panel.setVisible(false);
                pn2.setVisible(false);
                pn3.setVisible(false);
             }
         }
       );
       can1.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
               setVisible(false);
             }
         }
       );
      }

   private void studentDetails()
     {
       try
         {
            rs1 = s.executeQuery(" SELECT SNAME,YEAR,YEAROFJOIN FROM STUDENTDETAILS WHERE ADNO="+"'"+ no.getText().toUpperCase()+"'");
            if( rs1.next() )
              {
                 sn1.setText( rs1.getString(1));
                 sb1.setText(rs1.getString(2));
                 yjoin = Integer.parseInt(""+rs1.getString(3));
                 sbt1.setText(yjoin+" - "+(yjoin+1));

                 icon=new ImageIcon(path+""+no.getText()+".jpg");
                 image.setIcon(icon);
                 cmppn.add(image,new AbsoluteConstraints(300,28,100,113));
              }
             else
              {
                 JOptionPane.showMessageDialog(null,"NO RECORD ABT STUDENT !...","STUDENT -- DETAILS",JOptionPane.INFORMATION_MESSAGE);
              }
         }
       catch(SQLException sq)
         {
            JOptionPane.showMessageDialog(null,sq.getMessage());
         }
     }
   private void staffDetails()
     {
       try
         {
            s=con.createStatement();
            rs1 = s.executeQuery(" SELECT LNAME,DEPT,TO_CHAR(JOININGDATE,'YYYY'),TO_CHAR(SYSDATE,'YYYY') FROM STAFF WHERE LID="+"'"+ no1.getText().toUpperCase()+"'");
            if( rs1.next() )
              {
                 sn3.setText( rs1.getString(1));
                 sb3.setText(rs1.getString(2));
                 //yjoin = Integer.parseInt(""+rs1.getString(3));
                 sbt2.setText(rs1.getString(3)+"-"+rs1.getString(4));

                 icon=new ImageIcon(path+""+no1.getText()+".jpg");
                 image1.setIcon(icon);
                 stfpn.add(image1,new AbsoluteConstraints(300,28,100,113));
              }
             else
              {
                 JOptionPane.showMessageDialog(null,"NO RECORD ABT STAFF !...","STAFF -- DETAILS",JOptionPane.INFORMATION_MESSAGE);
              }
         }
       catch(SQLException sq)
         {
            JOptionPane.showMessageDialog(null,sq.getMessage());
         }
     }

   private void certify()
     {
       try
         {
            rs1 = s.executeQuery ("SELECT ADNO, DATEOFJOIN, STATUS ,SNAME,BRANCH FROM STUDENTDETAILS WHERE ADNO="+"'"+no.getText ().toUpperCase()+"'") ;
            if( rs1.next () )
              {
                sname = rs1.getString(4);
                branch = rs1.getString(5);
                status = rs1.getString (3);

                rs = s.executeQuery("SELECT TO_CHAR(DATEOFJOIN,'dd-mon-yyyy') FROM STUDENTDETAILS");
                   if( rs.next() )
                     {
                       t1 = rs.getString(1);
                       JOptionPane.showMessageDialog (null,t1);
                     }

                rs2 = s.executeQuery ("SELECT TO_CHAR(SYSDATE,'dd-mon-yyyy'),SYSDATE FROM DUAL");
                    if( rs2.next() )
                      {
                        sdate = rs2.getString (1);
                        JOptionPane.showMessageDialog (null,sdate);

                        rs1 = s.executeQuery ( "SELECT SYSDATE - TO_DATE('"+t1+"') FROM DUAL" );
                            if (rs1.next ())
                               {
                                  diff = rs1.getDouble(1);
                                  JOptionPane.showMessageDialog (null," "+diff);
                               }
                        if( status.equalsIgnoreCase("true") && diff>1500)
                          {
                              panel.add ( certificate, BorderLayout.CENTER);

                              JOptionPane.showMessageDialog (null,status +""+ sdate);
                              certificate.setText ( "                                       CERTIFICATION                                                  "+
                                                    "                            This  is  to  certify  that  "+no.getText()+"                                  "+
                                                    "  belongs to the Gates Institute Of Technology during the year"+t1+" to "+ sdate + "                                                                                 "+
                                                    "  has  cleared  all the books  to  library"+"                                                                                                                        "+
                                                    "													                				            "+ "Librarian"+""+
                                                    "                                                                               "
                                                  );
                              status = "FALSE";
                             // JOptionPane.showMessageDialog (null,"STATUS");
                               int uv=JOptionPane.showConfirmDialog((Component)this,"Are u sure to certify","CONFIRMATION",JOptionPane.YES_NO_OPTION);
                              // if(uv==0)
                               {
								   panel.setVisible(true);
								  JOptionPane.showMessageDialog (null,"bafdsfsdf...");
                              //int x = s.executeUpdate("UPDATE STUDENTDETAILS SET STATUS="+"'"+status+"'"+"WHERE ADNO="+"'"+no.getText()+"'");
                             // JOptionPane.showMessageDialog (null,"std.dt...");
                              //int abc = s.executeUpdate("INSERT INTO BOOKDETAILS(ADNO,CDATE) VALUES ("+"'"+no.getText()+"',"

                                                       //                                                            +"'"+sdate+"'"+")");
                              //JOptionPane.showMessageDialog (null,"bk.dt...");
                              //int l = s.executeUpdate("UPDATE BOOKDETAILS SET STATUS="+"'"+status+"'"+"WHERE ADNO="+"'"+no.getText()+"'"+"AND CDATE ="+"'"+sdate+"'" );
                              //JOptionPane.showMessageDialog (null,"sts");
                             /* int y = s.executeUpdate("INSERT INTO CERTIFIEDDATE( ADNO,SNAME,CDATE ) VALUES ( "+"'"+no.getText()+"',"
                                                                                                               +"'"+sname+"',"
                                                                                                               +"'"+sdate+"'"+")");*/
                              //int y=s.executeUpdate("UPDATE STUDENTDETAILS SET CDATE="+"'"+sdate+"'"+"WHERE ADNO="+"'"+no.getText()+"'");
                              //JOptionPane.showMessageDialog (null,"cdt");
                               }
                               //else
                               {
                                   no.setText("");
                                   sn1.setText( "");
                                   sb1.setText("");
                                   sbt1.setText("");
                               }
                          }
                        else
                          {
                              rs = s.executeQuery("SELECT CDATE FROM STUDENTDETAILS WHERE ADNO="+"'"+no.getText()+"'" );
                                 if( rs.next() )
                                   {
                                      JOptionPane.showMessageDialog( null,"This was already certified on  "+ rs.getString(1) );
                                   }
                          }
                      }
              }
            else
              {
                 JOptionPane.showMessageDialog (null,"Illegal Number");
              }
           }
         catch(SQLException sq)
           {
              JOptionPane.showMessageDialog(null,sq.getMessage());
           }
     }

     private void certify_Staff()
     {
       String sname1,join_date,pre_date,dep;
       try
         {
             JOptionPane.showMessageDialog(null,"Certification");
             rs=s.executeQuery("SELECT LNAME,TO_CHAR(JOININGDATE,'DD-MON-YYYY'),TO_CHAR(SYSDATE,'DD-MON-YYYY'),DEPT,STATUS FROM STAFF WHERE LID="+"'"+no1.getText().toUpperCase()+"'");
             if(rs.next())
             {
                 sname1=rs.getString(1);
                 join_date=rs.getString(2);
                 pre_date=rs.getString(3);
                 dep=rs.getString(4);
                 status=rs.getString(5);
                 if(status.equalsIgnoreCase("true"))
                 {
                     pn.add ( certificate, BorderLayout.CENTER);
                    // JOptionPane.showMessageDialog (null,status +""+ pre_date);
                     certificate.setText ( "                                       CERTIFICATION                                                  "+
                                           "                            This  is  to  certify  that  "+no1.getText()+"                                  "+
                                           "  belongs to the Gates Institute Of Technology during the year"+join_date+" to "+ pre_date + "                                                                                 "+
                                           "  has  cleared  all the books/cds/magazines  to  library"+"                                                                                                                        "+
                                           "													                				            "+ "Librarian"+""+
                                           "                                                                               "
                                          );
                     status = "FALSE";
                   //  JOptionPane.showMessageDialog (null,"STATUS");
                     int uv=JOptionPane.showConfirmDialog(null,"Are u sure to certify","CONFIRMATION",JOptionPane.YES_NO_OPTION);
                     if(uv==0)
                     {
						 JOptionPane.showMessageDialog (null,"qqqqqqqqq...");
                     int x = s.executeUpdate("UPDATE STAFF SET STATUS="+"'"+status+"'"+"WHERE LID="+"'"+no1.getText()+"'");
                    // JOptionPane.showMessageDialog (null,"std.dt...");
                     int abc = s.executeUpdate("INSERT INTO BOOKDETAILS(LID,CDATE) VALUES ("+"'"+no1.getText()+"',"
                                                                                                +"'"+pre_date+"'"+")");
                     // JOptionPane.showMessageDialog (null,"bk.dt...");
                      int l = s.executeUpdate("UPDATE BOOKDETAILS SET STATUS="+"'"+status+"'"+"WHERE LID="+"'"+no1.getText()+"'"+"AND CDATE ="+"'"+pre_date+"'" );
                    //  JOptionPane.showMessageDialog (null,"sts");
                     /* int y = s.executeUpdate("INSERT INTO CERTIFIEDDATE( LID,LNAME,CDATE ) VALUES ( "+"'"+no1.getText()+"',"
                                                                                                               +"'"+sname1+"',"
                                                                                                               +"'"+pre_date+"'"+")");*/
                      int y=s.executeUpdate("UPDATE STAFF SET CDATE="+"'"+pre_date +"'"+"WHERE LID="+"'"+no1.getText()+"'");
                     }
                     else
                     {
                         no1.setText("");
                         sn3.setText( "");
                         sb3.setText("");
                         sbt2.setText("");
                         certificate.setText("");

                     }
                     // JOptionPane.showMessageDialog (null,"cdt");
                 }
             }
             else
             {
                 JOptionPane.showMessageDialog(null,"Record does not exist");
             }

           }
         catch(SQLException sq)
           {
              JOptionPane.showMessageDialog(null,sq.getMessage());
           }
     }

    private void getTable(ResultSet rs)
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

            plist = new JTable(rows,columnHeads);
            plist.setEnabled(false);
            sp = new JScrollPane(plist);
            //panel.add(plist,BorderLayout.CENTER);
            panel.add(sp,BorderLayout.CENTER);
            plist.setBackground(Color.cyan);
            validate();
          }
        catch( SQLException sqlex )
          {
            JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
            sqlex.printStackTrace();
          }
      }
    private void getTable1(ResultSet rs)
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

            plist1 = new JTable(rows,columnHeads);
            plist1.setBackground(Color.pink);
            plist1.setEnabled(false);
            sp = new JScrollPane(plist1);
            //pn2.add(plist1,BorderLayout.CENTER);
            pn2.add(sp,BorderLayout.CENTER);

            validate();
          }
        catch( SQLException sqlex )
          {
            JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
            sqlex.printStackTrace();
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

            plist2 = new JTable(rows,columnHeads);
            sp = new JScrollPane(plist2);
            plist2.setEnabled(false);
            //pn3.add(plist2,BorderLayout.CENTER);
            pn3.add(sp,BorderLayout.CENTER);
            plist2.setBackground(Color.yellow);
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
           switch( rsmd.getColumnType(i) )
            {
              case Types.VARCHAR: currentRow.addElement( rs.getString(i));
                                  break;
              default:    currentRow.addElement(rs.getDate(i));
                                  break;
            }
        return currentRow;
      }

     private class MyAdapter7 extends WindowAdapter
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
     public static void  main (String a[])
       {
          Clear cl =new Clear();
       }
  }

