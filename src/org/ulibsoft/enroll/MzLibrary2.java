package org.ulibsoft.enroll;

import java.sql.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class MzLibrary2 extends JFrame
  {
    private MzLibrary2 p;
    private int first,second;
    private JLabel mzcode,mzname,mzvol,pub,mzsbj,acess,ino,sop,pg,sup;
    private JLabel cds,price,pty,pom,rdate,adate,rem;
    private JTextField cd,nm,vol,pb,sbj,acess1,ino1,pg1;
    private JTextField price1,pty1,pom1,rdate1,adate1,rem1;
    private JTextField title,supply,acess2;
    private String TITLE="",SUPPLY="";
    private JComboBox mzname1,sop1,sup1,cds1;
    private JButton ins,up,next,quit,mis;
    private JPanel bkpn,p1,despn;
    private JTable cdtbl;
    private JScrollPane sp;
    private int count=0,counter=0;
    private static int loop;
    private static int CONFIRM=-1;

    private String PATH;

    private String value1,value2;

    private JLabel msg,msg1;

    private Container c;

     private JLabel adno,name,image,branch,year2;
     private JLabel adno1,name1,branch1,year3;
     private Icon icon1,icon;
     private JLabel adno2,name2,image1,branch2,year4;
     private JLabel adno3,name3,branch3,year5;
     private int STAGE;
     private String student,staff;

    private Connection  con ;
    private Statement s ;
    private ResultSet rs, rs1 ;

    public MzLibrary2()
      {
        super("MZ(S)  DETAILS");
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        show();
        createComponents();
       try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
        sequenceGenerator();
        componentListener();
        try
          {
              s = con.createStatement();
              getTable();
              s.close();
           }
         catch(SQLException sqlex)
           {
             JOptionPane.showMessageDialog(null,sqlex.getMessage());
           }
          try
         {
          s=con.createStatement();
          rs=s.executeQuery("SELECT IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID=1");
          while(rs.next())
          {

           PATH=rs.getString(1);
          }
         }catch(SQLException se)
         {
          JOptionPane.showMessageDialog(null,se.getMessage());
          se.printStackTrace();
         }
      }

    private void createComponents()
    {
         c = getContentPane();
         c.setLayout(new AbsoluteLayout());
         c.setBackground( new Color(0,0,40));

         bkpn = new JPanel(new AbsoluteLayout());
         bkpn.setBackground(c.getBackground());
         bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INSERTION", 1, 2, c.getFont(), Color.magenta));
         c.add(bkpn,new AbsoluteConstraints(90,10,610,290));

         mzvol = new JLabel( "> VOLUME - NUMBER*" );
         mzvol.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( mzvol, new AbsoluteConstraints( 310, 21+5) );

         vol = new JTextField( );
         vol.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0,0,40 ) ));
         vol.setForeground ( new Color ( 255, 0, 153 ) );
         vol.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( vol, new AbsoluteConstraints( 440, 18+5, 150,20 ) );

         acess = new JLabel( "ACCESS NUMBER  >*" );
         acess.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( acess, new AbsoluteConstraints(25, 21+5) );

         acess1 = new JTextField( );
         acess1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
         acess1.setForeground ( new Color ( 255, 0, 153 ) );
         acess1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( acess1, new AbsoluteConstraints( 145, 18+5, 73, 20 ) );

         acess2 = new JTextField( );
          acess2.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
          acess2.setForeground ( new Color ( 255, 0, 153 ) );
          acess2.setCaretColor ( new Color ( 0, 204, 102 ) );
          bkpn.add ( acess2, new AbsoluteConstraints( 145+70+7, 18+5, 73, 20 ) );

         mzname = new JLabel( "MAGAZINE TITLE >>*" );
         mzname.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( mzname, new AbsoluteConstraints( 25, 48+5) );

         title = new JTextField( );
         title.setEditable(true);
         title.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
         title.setForeground ( new Color ( 255, 0, 153 ) );
         title.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( title, new AbsoluteConstraints( 145, 46+5, 445, 20 ) );

         mzname1 = new JComboBox( );
         mzname1.setEditable(true);
         mzname1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
         mzname1.setForeground ( new Color ( 255, 0, 153 ) );
         mzname1.setBackground(Color.white);
         bkpn.add ( mzname1, new AbsoluteConstraints( 145, 46+5, 445, 20 ) );
         mzname1.setVisible(false);

         ino = new JLabel( "ISSUE NUMBER >>>*" );
         ino.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( ino, new AbsoluteConstraints(25, 74+5 ) );

         ino1 = new JTextField( );
         ino1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
         ino1.setForeground ( new Color ( 255, 0, 153 ) );
         ino1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( ino1, new AbsoluteConstraints( 145, 72+5, 150, 20 ) );

         sop = new JLabel( "SOURCE  OF  SUPPLY*" );
         sop.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( sop, new AbsoluteConstraints( 310, 74+5 ) );

         supply = new JTextField( );
         supply.setEditable(true);
         supply.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0, 0, 40) ));
         supply.setForeground ( new Color ( 255, 0, 153 ) );
         bkpn.add ( supply, new AbsoluteConstraints( 440, 72+5, 150, 20) );

         sop1 = new JComboBox( );
         sop1.setEditable(true);
         sop1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color( 0, 0, 40) ));
         sop1.setForeground ( new Color ( 255, 0, 153 ) );
         bkpn.add ( sop1, new AbsoluteConstraints( 440, 72+5, 150, 20) );
         sop1.setVisible(false);

         pg = new JLabel( "NUMBER OF PAGES" );
         pg.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( pg, new AbsoluteConstraints(25,101+5) );

         pg1 = new JTextField( );
         pg1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
         pg1.setForeground ( new Color ( 255, 0, 153 ) );
         pg1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( pg1, new AbsoluteConstraints( 145, 99+5, 150, 20  ) );

         sup = new JLabel( "SUPPLEMENT - COPY" );
         sup.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( sup, new AbsoluteConstraints(310, 101+5 ) );

         sup1 = new JComboBox( );
         sup1.setEnabled(false);
         sup1.setEditable(true);
         sup1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0, 0, 40) ));
         sup1.setForeground ( new Color ( 255, 0, 153 ) );
         bkpn.add ( sup1, new AbsoluteConstraints( 440, 99+5, 150, 20 ) );

         cds = new JLabel( "COMPACT DISC>>>" );
         cds.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( cds, new AbsoluteConstraints(25, 128+5 ) );

         cds1 = new JComboBox( );
         cds1.setEnabled(false);
         cds1.setEditable(true);
         cds1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
         cds1.setForeground ( new Color ( 255, 0, 153 ) );
         bkpn.add ( cds1, new AbsoluteConstraints( 145, 126+5, 150, 20 ) );

         price = new JLabel( "PRICE OF - MAGAZINE " );
         price.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( price, new AbsoluteConstraints(310, 128+5 ) );

         price1 = new JTextField( );
         price1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
         price1.setForeground ( new Color ( 255, 0, 153 ) );
         price1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( price1, new AbsoluteConstraints( 440, 126+5, 150, 20 ) );

         pty = new JLabel( "<=  PERIODICITY  =>" );
         pty.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( pty, new AbsoluteConstraints( 25, 155+5) );

         pty1 = new JTextField( );
         pty1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
         pty1.setForeground ( new Color ( 255, 0, 153 ) );
         pty1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( pty1, new AbsoluteConstraints( 145, 153+5, 150, 20 ) );

         pom = new JLabel( "PERIOD OF MAGAZINE" );
         pom.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( pom, new AbsoluteConstraints( 310, 155+5) );

         pom1 = new JTextField( );
         pom1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
         pom1.setForeground ( new Color ( 255, 0, 153 ) );
         pom1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( pom1, new AbsoluteConstraints( 440, 153+5, 150, 20 ) );

         rdate = new JLabel( "RECEIVING DATE >>" );
         rdate.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( rdate, new AbsoluteConstraints( 25, 182+5) );

         rdate1 = new JTextField( );
         rdate1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
         rdate1.setForeground ( new Color ( 255, 0, 153 ) );
         rdate1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( rdate1, new AbsoluteConstraints(  145, 180+5, 150, 20 ) );

         adate = new JLabel( ">>> ACCESSING DATE" );
         adate.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( adate, new AbsoluteConstraints( 310, 182+5) );

         adate1 = new JTextField( );
         adate1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ));
         adate1.setForeground ( new Color ( 255, 0, 153 ) );
         adate1.setCaretColor ( new Color ( 0, 204, 102 ) );
         adate1.setEditable(false);
         bkpn.add ( adate1, new AbsoluteConstraints( 440, 180+5, 150, 20 ) );

         rem = new JLabel( "<==  REMARKS  ==>" );
         rem.setForeground ( new Color ( 120, 120, 153 ) );
         bkpn.add ( rem, new AbsoluteConstraints(25, 209+5) );

         rem1 = new JTextField( );
         rem1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
         rem1.setForeground ( new Color ( 255, 0, 153 ) );
         rem1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add ( rem1, new AbsoluteConstraints( 145, 207+5, 445, 20 ) );

         ins = new JButton( "INSERT" ) ;
         ins.setBackground ( Color.cyan  );
         ins.setForeground( Color .black );
         ins.setMnemonic('S');
         ins.setBorder ( new BevelBorder ( 0 ));
         bkpn.add ( ins, new AbsoluteConstraints( 85-15, 240+5,90,24 ) );

         up = new JButton( "UPDATE" ) ;
         up.setBackground ( Color.cyan  );
         up.setForeground( Color .black );
         up.setMnemonic('A');
         up.setEnabled(false);
         up.setBorder ( new BevelBorder ( 0 ));
         bkpn.add ( up, new AbsoluteConstraints( 197-35, 240+5,90,24 ) );

         mis = new JButton( "LOST" ) ;
         mis.setBackground ( Color.cyan  );
         mis.setForeground( Color .black );
         mis.setMnemonic('S');
         mis.setBorder ( new BevelBorder ( 0 ));
         bkpn.add ( mis, new AbsoluteConstraints( 253, 240+5,90,24 ) );

         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan );
         next.setForeground(  Color .black );
         next.setMnemonic('N');
         next.setBorder ( new BevelBorder (0));
         bkpn.add ( next, new AbsoluteConstraints( 308+35+1,240+5,90,24 ) );

         quit = new JButton( "EXIT" ) ;
         quit.setBackground ( Color.cyan );
         quit.setMnemonic('X');
         quit.setForeground( Color .black );
         quit.setBorder ( new BevelBorder (0));
         bkpn.add ( quit, new AbsoluteConstraints( 420+15+1,240+5,90,24 ) );

         p1 = new JPanel(new BorderLayout());
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "TABLE   DISPLAY", 1, 2, c.getFont(), Color.magenta));
         c.add(p1,new AbsoluteConstraints(50,330,700,220));

         c.add(despn=new JPanel(new AbsoluteLayout()),new AbsoluteConstraints(220,400,360,155));
          despn.setBackground(Color.white);//c.getBackground());
          //despn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                           //  "ISSUED TO", 1, 2, c.getFont(), Color.magenta));
          despn.setBorder(new BevelBorder(0));
          despn.setVisible(false);

          //MESSAGE
          msg =new JLabel("-:  ISSUED  TO  STUDENT , U  DON ' T  UPDATE  THIS  RECORD  :-");
          msg.setBackground(new Color(0,0,60));
          msg.setForeground(new Color(120,120,160));
          msg.setBorder(new BevelBorder(0));
          msg.setVisible(false);
          c.add(msg,new AbsoluteConstraints(90,380));

          msg1 =new JLabel("-:  ISSUED  TO  PROFESSOR , U  DON ' T  UPDATE  THIS  RECORD  :-");
          c.add(msg1,new AbsoluteConstraints(90,380));
          msg1.setBackground(new Color(0,0,60));
          msg1.setForeground(new Color(120,120,160));
          msg1.setBorder(new BevelBorder(0));
          msg1.setVisible(false);

          // PERSON DETAILS
          adno = new JLabel (  );
          adno.setForeground ( new Color(0,0,100) );
          adno.setHorizontalAlignment ( SwingConstants.RIGHT  );


          adno1 = new JLabel ( );
          adno1.setForeground ( new Color ( 0, 0, 100 ) );
          adno1.setHorizontalAlignment ( SwingConstants.RIGHT  );


          name = new JLabel (  );
          name.setForeground ( new Color(0,0,100) );
          name.setHorizontalAlignment ( SwingConstants.RIGHT );


          name1 = new JLabel (  );
          name1.setForeground ( new Color ( 0, 0, 100 ) );
          name1.setHorizontalAlignment ( SwingConstants.RIGHT );


          image = new JLabel( );
          image.setBounds(245,20,100,113);
          image.setBorder(new MatteBorder (1, 1, 1, 1, new Color(0, 0, 100 )));
          despn.add(image,new AbsoluteConstraints(240,20,100,110));

          icon1=new ImageIcon("empty.jpg");

          branch = new JLabel (  );
          branch.setForeground (new Color(0,0,100) );


          branch1 = new JLabel ( );
          branch1.setForeground ( new Color ( 0, 0, 100  ) );


          year2 = new JLabel( );
          year2.setForeground ( new Color(0,0,100) );
          year2.setHorizontalAlignment ( SwingConstants.RIGHT );


          year3 = new JLabel(  );
          year3.setForeground ( new Color ( 0, 0, 100  ) );
          year3.setHorizontalAlignment ( SwingConstants.RIGHT );


          adno.setText("ADNO  :");
          name.setText("NAME  :");
          branch.setText("COURSE  :");
          year2.setText("SEM  :");

          adno.setVisible(false);
          name.setVisible(false);
          branch.setVisible(false);
          year2.setVisible(false);

          adno1.setVisible(false);
          name1.setVisible(false);
          branch1.setVisible(false);
          year3.setVisible(false);

          //STAFF
          adno2 = new JLabel (  );
          adno2.setForeground ( new Color(0,0,100));
          adno2.setHorizontalAlignment ( SwingConstants.RIGHT  );


          adno3 = new JLabel ( );
          adno3.setForeground (new Color(0,0,100) );
          adno3.setHorizontalAlignment ( SwingConstants.RIGHT  );


          name2 = new JLabel (  );
          name2.setForeground ( new Color(0,0,100));
          name2.setHorizontalAlignment ( SwingConstants.RIGHT );


          name3 = new JLabel (  );
          name3.setForeground (new Color(0,0,100) );
          name3.setHorizontalAlignment ( SwingConstants.RIGHT );


          image1 = new JLabel( );
          image1.setBounds(245,20,100,113);
          image1.setBorder(new MatteBorder (1, 1, 1, 1, Color.cyan));
          despn.add(image1,new AbsoluteConstraints(240,20,100,110));

          icon1=new ImageIcon("empty.jpg");

          branch2 = new JLabel (  );
          branch2.setForeground ( new Color(0,0,100) );


          branch3 = new JLabel ( );
          branch3.setForeground ( new Color(0,0,100) );


          year4 = new JLabel(  );
          year4.setForeground ( new Color(0,0,100));
          year4.setHorizontalAlignment ( SwingConstants.RIGHT );


          year5 = new JLabel(  );
          year5.setForeground ( new Color(0,0,100) );
          year5.setHorizontalAlignment ( SwingConstants.RIGHT );


            adno2.setText("PROFESSOR ID :");
            name2.setText("NAME  :");
            branch2.setText("DEPARTMENT :");
            year4.setText("JOINING DATE :");

            adno2.setVisible(false);
          name2.setVisible(false);
          branch2.setVisible(false);
          year4.setVisible(false);

          adno3.setVisible(false);
          name3.setVisible(false);
          branch3.setVisible(false);
          year5.setVisible(false);

            despn.add( adno, new AbsoluteConstraints(35, 21 ) );
            despn.add( adno1, new AbsoluteConstraints(90, 21 ) );
            despn.add ( name, new AbsoluteConstraints( 35, 51 ) );
            despn.add ( name1, new AbsoluteConstraints( 90, 51 ) );
            despn.add ( branch, new AbsoluteConstraints( 20, 81 ) );
            despn.add ( branch1, new AbsoluteConstraints( 90, 81 ) );
            despn.add ( year2, new AbsoluteConstraints( 43,111 ) );
            despn.add ( year3, new AbsoluteConstraints( 90,111 ) );

            despn.add( adno2, new AbsoluteConstraints(14, 21 ) );
            despn.add( adno3, new AbsoluteConstraints(90+10+10, 21 ) );
            despn.add ( name2, new AbsoluteConstraints( 62, 51 ) );
            despn.add ( name3, new AbsoluteConstraints( 90+10+10, 51 ) );
            despn.add ( branch2, new AbsoluteConstraints( 20, 81 ) );
            despn.add ( branch3, new AbsoluteConstraints( 90+10+10, 81 ) );
            despn.add ( year4, new AbsoluteConstraints( 20,111 ) );
            despn.add ( year5, new AbsoluteConstraints( 90+10+10,111 ) );

                    title.setEditable(false);
                    vol.setEditable(false);
                    ino1.setEditable(false);
                    supply.setEditable(false);
                    pg1.setEditable(false);
                    price1.setEditable(false);
                    pty1.setEditable(false);
                    pom1.setEditable(false);
                    rdate1.setEditable(false);
                    adate1.setEditable(false);
                    rem1.setEditable(false);

                    cds1.setEnabled(false);
                    sup1.setEnabled(false);
                    setVisible(true);
                    //requestFocus();

                    ins.setEnabled(false);
                    mis.setEnabled(false);
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
                    //p.setFrame(Index.mzlib2);
                  }
               }
           }
         );
         msg.addMouseListener(new MouseAdapter()
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
                    msg.setBackground(new Color(0,0,100));
                  }
                public void mouseExited(MouseEvent me)
                  {
                     msg.setBackground(new Color(0,0,60));
                  }
            }
         );
         msg1.addMouseListener(new MouseAdapter()
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
                    msg1.setBackground(new Color(0,0,100));
                  }
                public void mouseExited(MouseEvent me)
                  {
                     msg1.setBackground(new Color(0,0,60));
                  }
            }
         );
         acess1.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
               firstStep();
             }
         }

         );
         acess2.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
               firstStep();
             }
         }
         );

         title.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {

                      try
                        {


                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(MZNAME) FROM MZDETAILS WHERE MZNAME LIKE "+"'"+title.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {

                              mzname1.addItem(rs.getObject(1));
                              ++count;
                           }
                          if(count>0)
                          {
                             mzname1.setVisible(true);
                             title.setVisible(false);
                          }

                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }


                }
            }
          );


          supply.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                     try
                        {
                          s = con.createStatement();
                          rs = s.executeQuery( "SELECT DISTINCT(SOURCE) FROM MZDETAILS WHERE SOURCE LIKE "+"'"+supply.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              sop1.addItem(rs.getObject(1));

                              ++counter;
                           }
                          if( counter > 0)
                           {
                              sop1.setVisible(true);
                              supply.setVisible(false);
                           }
                        }
                      catch(SQLException sq)
                        {
                          JOptionPane.showMessageDialog(null,sq.getMessage());
                        }

                }
            }
          );
        ins.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
               {
                  try
                    {
                      s =con.createStatement();
                      rs1=s.executeQuery("SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') FROM DUAL");
                      while(rs1.next())
                       {
                         adate1.setText(rs1.getString(1));
                       }

                      first = Integer.parseInt(acess1.getText());
                        if( acess2.getText()==null||acess2.getText().equals(""))
                          {
                          }
                        else
                          {

                             if( Integer.parseInt(acess2.getText()) >= first )
                               {
                                  second =Integer.parseInt(acess2.getText());
                               }
                          }

                      TITLE=(String)mzname1.getSelectedItem();
                              if(TITLE==null || TITLE .equals(""))
                                {
                                   TITLE = title.getText().toUpperCase();
                                   if(TITLE==null || TITLE .equals(""))
                                     {
                                        new ConfirmDialog1((Frame)p,"WARNING");
                                        CONFIRM=0;
                                     }
                                   else
                                     {
                                        CONFIRM=1;
                                     }
                                }

                      SUPPLY=(String)sop1.getSelectedItem();
                      if(SUPPLY==null || SUPPLY.equals("")){  SUPPLY = supply.getText().toUpperCase(); }

                      if( CONFIRM==1)
                          {
                            if(first==loop)
                              {
                                if(first<=second)
                                  {
                                    for(int i=first;i<=second;i++)
                                      {
                                         acess1.setText(""+i);
                                         int a = s.executeUpdate("INSERT INTO MZDETAILS (ACCESSNO, MZNAME, VOLUME, MZCODE, SOURCE, PAGES,SUPPLEMENT,CDS,PRICE,PCITY,PERIOD,RDATE,ADATE,REMARKS ) VALUES ( "+"'"+acess1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+TITLE+"',"
                                                                                                                                                                                        +"'"+vol.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+ino1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+SUPPLY+"',"
                                                                                                                                                                                        +"'"+pg1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+sup1.getSelectedItem()+"',"
                                                                                                                                                                                        +"'"+cds1.getSelectedItem()+"',"
                                                                                                                                                                                        +"'"+price1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+pty1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+pom1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+rdate1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+adate1.getText().toUpperCase()+"',"
                                                                                                                                                                                        +"'"+rem1.getText().toUpperCase()+"'"
                                                                                                                                                                                        +")");
                                         con.commit();
                                         int wet=s.executeUpdate("UPDATE MZDETAILS SET ISSUED='0' WHERE ACCESSNO="+"'"+acess1.getText()+"'");
                                         con.commit();
                                      }
                                     getTable();
                                  }
                                 else
                                  {
                                     int cfirm=JOptionPane.showConfirmDialog(null,"U MUST GIVE 2-ND  ACCESSNO  GREATER  THAN 1_ST  ACCESSNO","NEW RECORD",JOptionPane.YES_NO_OPTION);
                                     if(cfirm==0)
                                       {
                                          acess2.setText(acess1.getText());
                                       }
                                  }
                              }
                             else
                              {
                                 JOptionPane.showMessageDialog(null,"U MUST INSERT IN SEQUENTIAL FASHION .  .  .  .  .  .");
                              }
                          }
                        s.close();
                    }
                  catch(SQLException sq)
                    {
                       JOptionPane.showMessageDialog(null,sq.getMessage());
                    }
               }
          }
        );

        up.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
               {
                 try
                   {
                     s =con.createStatement();
                     rs = s.executeQuery( " SELECT MZ.ACCESSNO, STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR FROM MZTRANSACTION MZT,MZDETAILS MZ,STUDENTDETAILS STD  WHERE MZ.ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'"+"AND MZT.MZCODE=MZ.ACCESSNO AND STD.ADNO=MZT.ADNO" );
                    if(rs.next())
                    {
                        STAGE++;
                        msg.setVisible(true);
                        student = rs.getString(2);
                               if( student!= null )
                                {
                                       adno2.setVisible(false);
                                       name2.setVisible(false);
                                       branch2.setVisible(false);
                                       year4.setVisible(false);

                                       adno3.setVisible(false);
                                       name3.setVisible(false);
                                       branch3.setVisible(false);
                                       year5.setVisible(false);

                                   adno.setVisible(true);
                                   name.setVisible(true);
                                   branch.setVisible(true);
                                   year2.setVisible(true);

                                   adno1.setVisible(true);
                                   name1.setVisible(true);
                                   branch1.setVisible(true);
                                   year3.setVisible(true);



                                    adno1.setText(student);
                                   name1.setText(rs.getString(3));
                                   branch1.setText(rs.getString(4));
                                   year3.setText(rs.getString(5));
                                   icon = new ImageIcon(PATH+student+".jpg");
                                   image.setIcon(icon);
                                   p1.setVisible(false);
                                   despn.setVisible(true);
                                     int qwe=s.executeUpdate("UPDATE MZDETAILS SET ISSUED='1' WHERE ACCESSNO="+"'"+acess1.getText()+"'");
                               }
                              up.setEnabled(false);
                         }

                         rs = s.executeQuery( " SELECT MZ.ACCESSNO, SF.LID, SF.LNAME,SF.DEPT  FROM MZTRANSACTION1 MZT1,STAFF SF,MZDETAILS MZ  WHERE MZ.ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'"+"AND MZT1.MZCODE=MZ.ACCESSNO AND SF.LID=MZT1.LID" );
                         if( rs.next() )
                           {
                              STAGE++;
                              msg1.setVisible(true);
                               staff = rs.getString(2);
                               if( staff!= null )
                                {

                                     adno.setVisible(false);
                                     name.setVisible(false);
                                     branch.setVisible(false);
                                     year2.setVisible(false);

                                     adno1.setVisible(false);
                                     name1.setVisible(false);
                                     branch1.setVisible(false);
                                     year3.setVisible(false);

                                   adno2.setVisible(true);
                                   name2.setVisible(true);
                                   branch2.setVisible(true);
                                   year4.setVisible(true);

                                   adno3.setVisible(true);
                                   name3.setVisible(true);
                                   branch3.setVisible(true);
                                   year5.setVisible(true);

                                    adno3.setText(staff);
                                   name3.setText(rs.getString(3));
                                   branch3.setText(rs.getString(4));
                                   rs1=s.executeQuery("SELECT TO_CHAR(JOININGDATE,'DD-MON-YYYY') JDATE FROM STAFF WHERE LID="+"'"+staff+"'");
                                   while(rs1.next())
                                   {
                                       year5.setText(rs1.getString(1));
                                   }
                                   icon = new ImageIcon(PATH+staff+".jpg");
                                   image.setIcon(icon);
                                   p1.setVisible(false);
                                   despn.setVisible(true);
                                    int we=s.executeUpdate("UPDATE MZDETAILS SET ISSUED='1' WHERE ACCESSNO="+"'"+acess1.getText()+"'");
                                }
                              up.setEnabled(false);
                            }

                    if(STAGE==0)
                    {
                      first = Integer.parseInt(acess1.getText());
                        if( acess2.getText()==null||acess2.getText().equals(""))
                          {
                          }
                        else
                          {

                             if( Integer.parseInt(acess2.getText()) >= first )
                               {
                                  second =Integer.parseInt(acess2.getText());
                               }
                          }

                      TITLE=(String)mzname1.getSelectedItem();
                              if(TITLE==null || TITLE .equals(""))
                                {
                                   TITLE = title.getText().toUpperCase();
                                   if(TITLE==null || TITLE .equals(""))
                                     {
                                        new ConfirmDialog1((Frame)p,"WARNING");
                                        CONFIRM=0;
                                     }
                                   else
                                     {
                                        CONFIRM=1;
                                     }
                                }

                     SUPPLY=(String)sop1.getSelectedItem();
                      if(SUPPLY==null || SUPPLY.equals("") ){  SUPPLY = supply.getText().toUpperCase(); }

                     if( CONFIRM==1)
                                {
                                   if(first<=second)
                                     {
                                        for(int Z=first;Z<=second;Z++)
                                          {
                                             acess1.setText(""+Z);
                                             int a = s.executeUpdate("UPDATE MZDETAILS SET MZNAME ="+"'"+TITLE+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int b = s.executeUpdate("UPDATE MZDETAILS SET VOLUME ="+"'"+vol.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int c1=s.executeUpdate("UPDATE MZDETAILS SET MZCODE ="+"'"+ino1.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int d = s.executeUpdate("UPDATE MZDETAILS SET SOURCE ="+"'"+SUPPLY+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int f = s.executeUpdate("UPDATE MZDETAILS SET PAGES ="+"'"+pg1.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int g=s.executeUpdate("UPDATE MZDETAILS SET SUPPLEMENT ="+"'"+sup1.getSelectedItem()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int h=s.executeUpdate("UPDATE MZDETAILS SET CDS ="+"'"+cds1.getSelectedItem()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int i=s.executeUpdate("UPDATE MZDETAILS SET PRICE ="+"'"+price1.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int j=s.executeUpdate("UPDATE MZDETAILS SET PCITY ="+"'"+pty1.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int k=s.executeUpdate("UPDATE MZDETAILS SET PERIOD ="+"'"+pom1.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             int l=s.executeUpdate("UPDATE MZDETAILS SET RDATE =TO_DATE('"+rdate1.getText().toUpperCase()+"') WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             //JOptionPane.showMessageDialog(null,adate1.getText());
                                             int m=s.executeUpdate("UPDATE MZDETAILS SET ADATE =TO_CHAR(SYSDATE,'DD-MON-YYYY') WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                            // JOptionPane.showMessageDialog(null,adate1.getText());
                                             int n=s.executeUpdate("UPDATE MZDETAILS SET REMARKS ="+"'"+rem1.getText().toUpperCase()+"'"+"WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'" );
                                             con.commit();
                                             int wet1=s.executeUpdate("UPDATE MZDETAILS SET ISSUED='0' WHERE ACCESSNO="+"'"+acess1.getText()+"'");
                                             con.commit();
                                             up.setEnabled(false);
                                          }
                                       getTable();
                                    }
                                }
                        }
                     s.close();

                   }
                 catch( SQLException sq )
                   {
                     JOptionPane.showMessageDialog( null,sq.getMessage() );
                   }
               }
          }
        );

         mis.addActionListener(new ActionListener()
          {
               public void actionPerformed(ActionEvent e)
               {
                    try
                    {
                        s=con.createStatement();
                        rs=s.executeQuery("SELECT MZCODE FROM MZTRANSACTION WHERE MZCODE="+"'"+cd.getText()+"'");
                        while(rs.next())
                        {
                            int mis1=s.executeUpdate("UPDATE MZDETAILS SET STATUS='MISSING' WHERE ACCESSNO="+"'"+cd.getText()+"'");
                            con.commit();
                            int mis2=s.executeUpdate("DELETE FROM MZTRANSACTION WHERE MZCODE="+"'"+cd.getText()+"'");
                            con.commit();
                            JOptionPane.showMessageDialog(null,"STATUS OF" +acess1.getText()+" UPDATED TO LOST");
                        }
                        rs=s.executeQuery("SELECT MZCODE FROM MZTRANSACTION1 WHERE MZCODE="+"'"+cd.getText()+"'");
                        while(rs.next())
                        {
                            int mis1=s.executeUpdate("UPDATE MZDETAILS SET STATUS='MISSING' WHERE ACCESSNO="+"'"+cd.getText()+"'");
                            con.commit();
                            int mis2=s.executeUpdate("DELETE FROM MZTRANSACTION1 WHERE MZCODE="+"'"+cd.getText()+"'");
                            con.commit();
                            JOptionPane.showMessageDialog(null,"STATUS OF" +acess1.getText()+" UPDATED TO LOST");
                        }
                    }
                    catch(SQLException sqlex)
                    {
                        JOptionPane.showMessageDialog(null,sqlex.getMessage(),"Exception",JOptionPane.ERROR_MESSAGE);
                        sqlex.printStackTrace();
                    }
               }
          }
          );

        next.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
               {
                  reset();
                  getTable1();
               }
          }
        );

        quit.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                dispose();
                setVisible( false );
              }
          }
        );
      }
    private void reset()
      {
          count=0;
                    counter=0;
                    acess1.setText("");
                    title.setText("");
                    vol.setText("");
                    ino1.setText("");
                    supply.setText("");
                    pg1.setText("");
                    price1.setText ("");
                    pty1.setText ("");
                    pom1.setText ("");
                    rdate1.setText("");
                    adate1.setText ("");
                    rem1.setText ("");

                    title.setEditable(false);
                    vol.setEditable(false);
                    ino1.setEditable(false);
                    supply.setEditable(false);
                    pg1.setEditable(false);
                    price1.setEditable(false);
                    pty1.setEditable(false);
                    pom1.setEditable(false);
                    rdate1.setEditable(false);
                    adate1.setEditable(false);
                    rem1.setEditable(false);

                    sop1.setVisible(false);
                    mzname1.setVisible(false);
                    title.setVisible(true);
                    supply.setVisible(true);

                    sop1.removeAllItems();
                    sup1.removeAllItems();
                    cds1.removeAllItems();
                    mzname1.removeAllItems();

                    sop1.setSelectedItem("");
                    cds1.setSelectedItem ("");
                    mzname1.setSelectedItem("");

                    cds1.setEnabled(false);
                    sup1.setEnabled(false);

                    sequenceGenerator();

                    despn.setVisible(false);
                    p1.setVisible(true);

                    ins.setEnabled(false);
                    mis.setEnabled(false);
      }
    private class ConfirmDialog1 extends JDialog
       {
          private Container k;
          private JLabel war;
          private JButton ok,can;
          public ConfirmDialog1(Frame parent ,String title)
            {
               super(parent,title,true);
               k=getContentPane();
               k.setBackground(new Color(120,120,180));
               k.setLayout(new AbsoluteLayout());

               k.add(war = new JLabel("U  MUST  INPUT  MAGAZINE - NAME  .  .  ."),new AbsoluteConstraints(25,20));
               war.setForeground(Color.white);

               k.add(ok = new JButton("OK"),new AbsoluteConstraints(50,50,80,25));
               ok.setBackground(Color.cyan);
               ok.setBorder(new BevelBorder(0));
               ok.setMnemonic('K');

               k.add(can = new JButton("CANCEL"),new AbsoluteConstraints(131,50,80,25));
               can.setBackground(Color.cyan);
               can.setBorder(new BevelBorder(0));
               can.setMnemonic('C');

               ok.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         reset();
                         CONFIRM=1;
                         setVisible(false);
                      }
                 }
               );
               can.addActionListener(new ActionListener()
                 {
                    public void actionPerformed(ActionEvent e)
                      {
                         CONFIRM=2;
                         setVisible(false);
                      }
                 }
               );

               setBounds(270,200,250,120);
               setVisible(true);
            }
       }
    private void firstStep()
      {
          if(acess1.getText()==null||acess1.getText().equals("")){JOptionPane.showMessageDialog(null,"U MUST 1ST INPUT 1-ST ACESSNO .  .  .");}
           else{
            try
               {
                   despn.setVisible(false);
                       p1.setVisible(true);

                   s=con.createStatement();
                   rs=s.executeQuery("SELECT ACCESSNO, MZNAME, VOLUME, MZCODE, SOURCE, PAGES,SUPPLEMENT,CDS,PRICE,PCITY,PERIOD,TO_CHAR( RDATE ,'DD-MON-YYYY') ,TO_CHAR( ADATE,'DD-MON-YYYY'),REMARKS FROM MZDETAILS WHERE ACCESSNO="+"'"+acess1.getText().toUpperCase()+"'");
                   if(rs.next())
                   {

                    up.setEnabled(true);
                    mis.setEnabled(true);

                    vol.setText( rs.getString(3) );
                    ino1.setText(rs.getString(4));
                    value1=rs.getString(5);
                    pg1.setText(rs.getString(6));
                    sup1.setSelectedItem (rs.getString(7));
                    cds1.setSelectedItem (rs.getString(8));
                    price1.setText (rs.getString(9));
                    pty1.setText (rs.getString(10));
                    pom1.setText (rs.getString(11));
                    rdate1.setText(rs.getString(12));
                    adate1.setText (rs.getString(13));
                    rem1.setText (rs.getString(14));

                    value2=(String)rs.getObject(2);

                    supply.setText(value1);
                    title.setText(value2);
                    supply.setEditable(true);
                    title.setEditable(true);

                    cds1.setEnabled(true);
                    sup1.setEnabled(true);

                    up.setEnabled(true);

                    sup1.removeAllItems();
                    cds1.removeAllItems();
                    sup1.addItem("YES");
                    sup1.addItem("NO");
                    cds1.addItem("YES");
                    cds1.addItem("NO");

                    title.setEditable(true);
                    vol.setEditable(true);
                    ino1.setEditable(true);
                    supply.setEditable(true);
                    pg1.setEditable(true);
                    price1.setEditable(true);
                    pty1.setEditable(true);
                    pom1.setEditable(true);
                    rdate1.setEditable(true);
                    adate1.setEditable(false);
                    rem1.setEditable(true);

                   }
                   else
                   {
                       int cfirm=JOptionPane.showConfirmDialog(null,"DO U WANT TO INSERT NEW RECORD","NEW RECORD",JOptionPane.YES_NO_OPTION);
                       if(cfirm==0)
                         {
                        sup1.removeAllItems();
                        cds1.removeAllItems();
                        title.setText("");
                        supply.setText("");
                        sup1.addItem("YES");
                        sup1.addItem("NO");
                        cds1.addItem("YES");
                        cds1.addItem("NO");
                        title.setEditable(true);
                        vol.setEditable(true);
                        ino1.setEditable(true);
                        supply.setEditable(true);
                        pg1.setEditable(true);
                        price1.setEditable(true);
                        pty1.setEditable(true);
                        pom1.setEditable(true);
                        rdate1.setEditable(true);

                        rem1.setEditable(true);

                        cds1.setEnabled(true);
                        sup1.setEnabled(true);

                        ins.setEnabled(true);
                      }
                   }
               }
               catch(SQLException se)
               {
                   JOptionPane.showMessageDialog(null,se.getMessage());
                   se.printStackTrace();
               }
            }
      }

     private void getTable1()
        {
           try
             {
             s = con.createStatement();
           tableDetails();
           s.close();
           }catch(SQLException sqlex)
            {
            }

        }
      private void getTable()
        {
           tableDetails();
        }
      private void tableDetails()
        {
         Vector columnHeads = new Vector();
         Vector rows = new Vector();
         try
           {
//              JOptionPane
              rs = s.executeQuery ( " SELECT ACCESSNO, MZNAME, VOLUME, MZCODE, SOURCE, PAGES,SUPPLEMENT,CDS,PRICE,PCITY,PERIOD,TO_CHAR( RDATE ,'DD-MON-YYYY') RDATE ,TO_CHAR( ADATE,'DD-MON-YYYY') ADATE,REMARKS FROM MZDETAILS WHERE ADATE= TO_CHAR(SYSDATE,'DD-MON-YYYY') " );
              if( rs.next () )
               {
                  ResultSetMetaData rsmd = rs.getMetaData();
                  for(int i = 1;i <= rsmd.getColumnCount(); i++ )
                  columnHeads.addElement( rsmd.getColumnName(i) );

                  do
                   {
                     rows.addElement( getNextRow( rs,rsmd ) );
                   }while( rs.next() );

                  cdtbl = new JTable(rows,columnHeads);
                  cdtbl.setBackground(Color.cyan);
                  cdtbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                  cdtbl.setEnabled (false);
                  p1.add(cdtbl,BorderLayout.CENTER);
                  sp = new JScrollPane( cdtbl);
                  p1.add(sp,BorderLayout.CENTER);
                  validate();
               }
              else
               {
                 JOptionPane.showMessageDialog(null,"TODAY NO RECORDS R INSERTED ! .  .  .");
               }
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
                 case Types.VARCHAR : currentRow.addElement( rs.getString (i) );
                                      break;
                 default:             currentRow.addElement( rs.getDate (i) );
              }
         return currentRow;

      }

     private void sequenceGenerator()
     {

          try
          {
              s=con.createStatement();
              rs=s.executeQuery("SELECT COUNT(ACCESSNO) FROM MZDETAILS");
              while(rs.next())
              {
                loop=rs.getInt(1)+1;
              }
              acess1.setText(String.valueOf(loop));
              acess2.setText(String.valueOf(loop));
              s.close();
          }
          catch(SQLException sq)
          {
            JOptionPane.showMessageDialog(null,sq.getMessage());
          }
     }
    public static void main( String a[] )
      {
         MzLibrary2 d5 = new MzLibrary2();

      }

  }
