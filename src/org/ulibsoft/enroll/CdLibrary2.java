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

public class CdLibrary2 extends JFrame
  {
    private CdLibrary2 p;
    private int first,second,CONFIRM=-1;
    private JLabel cdcode,cdname,cdtype,cdvrsion,cdsbj,bno,acdt,mno,msg,msg1;
    private JLabel cdrec,cdno,cdss,note;
    private JTextField cd,cd1,vrs,sbj,bno1,cdno1,recdt,note1,acdt1,mno1;
    private JTextField title;
    private JComboBox nm,typ,cdss1;
    private JButton ins,up,next,can,mis;
    private JPanel cdpn,p1,despn;
    private JTable cdtbl;
    private JScrollPane sp;
    private Container c;

     private JLabel adno,name,image,branch,year2;
     private JLabel adno1,name1,branch1,year3;
     private Icon icon1,icon;
     private JLabel adno2,name2,image1,branch2,year4;
     private JLabel adno3,name3,branch3,year5;

    private Connection  con;
    private Statement s ;
    private ResultSet rs, rs1 ;


    private int STAGE=0;
    private int count=0;
    private String TITLE="";
    private String student,staff;
    private String value1,value2,value3,value4,value5,value6,value7,value8;
    private String value9,value10,value11,value12,value13,value14,value15;

    private String PATH;

    public static CdLibrary2  cdlib;
    private static int loop;
    public CdLibrary2()
      {
        super("CD(S)  DETAILS");
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

         cdpn = new JPanel(new AbsoluteLayout());
         cdpn.setBackground(c.getBackground());
         cdpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CD(S) -- DATA", 1, 2, c.getFont(), Color.magenta));
         c.add(cdpn,new AbsoluteConstraints(130,20,540,275));

         p1 = new JPanel(new BorderLayout());
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "TABLE   DISPLAY", 1, 2, c.getFont(), Color.magenta));
         c.add(p1,new AbsoluteConstraints(60,315,670,220));

         cdpn.add(cdcode = new JLabel("ACCESS NUMBER"),new AbsoluteConstraints(25,30));
         cdcode.setForeground(new Color(120,120,153));

         cdpn.add(cd = new JTextField(),new AbsoluteConstraints(135,28,63,20));
         cd.setForeground(new Color(255,0,153));
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         cd.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));

         cdpn.add(cd1 = new JTextField(),new AbsoluteConstraints(135+67,28,63,20));
         cd1.setForeground(new Color(255,0,153));
         cd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         cd1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));

         cdpn.add(cdno = new JLabel("SERIAL -- NUMBER "),new AbsoluteConstraints(280,30));
         cdno.setForeground(new Color(120,120,153));

         cdpn.add(cdno1 = new JTextField(),new AbsoluteConstraints(395,28,130,20));
         cdno1.setForeground(new Color(255,0,153));
         cdno1.setEditable(false);
         cdno1.setCaretColor ( new Color ( 0, 204, 102 ) );
         cdno1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));

         cdpn.add(cdname = new JLabel("CD/FLOPPY TITLE"),new AbsoluteConstraints(25,57));
         cdname.setForeground(new Color(120,120,153));

         cdpn.add(nm = new JComboBox(),new AbsoluteConstraints(135,55,390,20));
         nm.setForeground(new Color(255,0,153));
         nm.setEditable(true);
         nm.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));
         nm.setVisible(false);

         cdpn.add(title = new JTextField(),new AbsoluteConstraints(135,55,390,20));
         title.setForeground(new Color(255,0,153));
         title.setEditable(false);
         title.setCaretColor ( new Color ( 0, 204, 102 ) );
         title.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));

         cdpn.add(cdvrsion = new JLabel("CD VERSION  >>>" ),new AbsoluteConstraints(25,84));
         cdvrsion.setForeground(new Color(120,120,153));

         cdpn.add(vrs = new JTextField(),new AbsoluteConstraints(135,82,130,20));
         vrs.setForeground(new Color(255,0,153));
         vrs.setEditable(false);
         vrs.setCaretColor ( new Color ( 0, 204, 102 ) );
         vrs.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));

         cdpn.add(cdtype = new JLabel("CD  /  FLOPPY TYPE "),new AbsoluteConstraints(280,84));
         cdtype.setForeground(new Color(120,120,153));

         cdpn.add(typ = new JComboBox(),new AbsoluteConstraints(395,82,130,20));
         typ.setForeground(new Color(255,0,153));
         typ.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));


         cdpn.add(bno = new JLabel("BOOK ACCESSNO"),new AbsoluteConstraints(25,112));
         bno.setForeground(new Color(120,120,153));

         cdpn.add(bno1 = new JTextField(),new AbsoluteConstraints(135,110,130,20));
         bno1.setEditable(false);
         bno1.setForeground(new Color(255,0,153));
         bno1.setCaretColor ( new Color ( 0, 204, 102 ) );
         bno1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));

         cdpn.add(mno = new JLabel("MAG . -  ACCESSNO"),new AbsoluteConstraints(280,112));
         mno.setForeground(new Color(120,120,153));

         cdpn.add(mno1 = new JTextField(),new AbsoluteConstraints(395,110,130,20));
         mno1.setForeground(new Color(255,0,153));
         mno1.setEditable(false);
         mno1.setCaretColor ( new Color ( 0, 204, 102 ) );
         mno1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));


         cdpn.add(cdss = new JLabel("STATUS OF CD >>"),new AbsoluteConstraints(25,140));
         cdss.setForeground(new Color(120,120,153));

         cdpn.add(cdss1 = new JComboBox(),new AbsoluteConstraints(135,138,130,20));
         cdss1.setForeground(new Color(255,0,153));

         cdss1.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));

         cdpn.add(cdsbj = new JLabel("<==  SUBJECT  ==>"),new AbsoluteConstraints(280,140));
         cdsbj.setForeground(new Color(120,120,153));

         cdpn.add(sbj = new JTextField(),new AbsoluteConstraints(395,138,130,20));
         sbj.setForeground(new Color(255,0,153));
         sbj.setEditable(false);
         sbj.setCaretColor ( new Color ( 0, 204, 102 ) );
         sbj.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));


         cdpn.add(cdrec = new JLabel("RECIEVING - DATE "),new AbsoluteConstraints(25,170));
         cdrec.setForeground(new Color(120,120,153));

         cdpn.add(recdt = new JTextField(),new AbsoluteConstraints(135,168,130,20));
         recdt.setForeground(new Color(255,0,153));
         recdt.setEditable(false);
         recdt.setCaretColor ( new Color ( 0, 204, 102 ) );
         recdt.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));

         cdpn.add(acdt = new JLabel("ACCESSING - DATE"),new AbsoluteConstraints(280,170));
         acdt.setForeground(new Color(120,120,153));

         cdpn.add(acdt1 = new JTextField(),new AbsoluteConstraints(395,168,130,20));
         acdt1.setForeground(new Color(255,0,153));
         acdt1.setEditable(false);
         acdt1.setCaretColor ( new Color ( 0, 204, 102 ) );
         acdt1.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));

         cdpn.add(note = new JLabel("<==    NOTE    ==>"),new AbsoluteConstraints(25,198));
         note.setForeground(new Color(120,120,153));

         cdpn.add(note1 = new JTextField(),new AbsoluteConstraints(135,196,390,20));
         note1.setForeground(new Color(255,0,153));
         note1.setEditable(false);
         note1.setCaretColor ( new Color ( 0, 204, 102 ) );
         note1.setBorder (new MatteBorder (1, 1, 1, 1, c.getBackground()));


         cdpn.add(ins = new JButton("INSERT"),new AbsoluteConstraints(50-10-5,175+5+50,90,24));
         ins.setBackground(Color.cyan);
         ins.setForeground(Color.black);
         ins.setBorder(new BevelBorder(0));
         ins.setMnemonic('S');

         cdpn.add(up = new JButton("UPDATE"),new AbsoluteConstraints(162-20-10-5,175+5+50,90,24));
         up.setBackground(Color.cyan);
         up.setForeground(Color.black);
         up.setBorder(new BevelBorder(0));
         up.setMnemonic('A');
         up.setEnabled(false);

          mis = new JButton( "LOST" ) ;
          mis.setBackground ( Color.cyan  );
          mis.setForeground( Color .black );
          mis.setMnemonic('L');
          mis.setBorder ( new BevelBorder ( 0 ));
          cdpn.add ( mis, new AbsoluteConstraints( 218,175+5+50,90,24 ) );


         cdpn.add(next = new JButton("NEXT>>>"),new AbsoluteConstraints(274+30+5,175+5+50,90,24));
         next.setBackground(Color.cyan);
         next.setForeground(Color.black);
         next.setBorder(new BevelBorder(0));
         next.setMnemonic('N');

         cdpn.add(can = new JButton("EXIT"),new AbsoluteConstraints(386+10+5,175+5+50,90,24));
         can.setBackground(Color.cyan);
         can.setForeground(Color.black);
         can.setBorder(new BevelBorder(0));
         can.setMnemonic('X');


           c.add(despn=new JPanel(new AbsoluteLayout()),new AbsoluteConstraints(220,400,360,155));
          despn.setBackground(Color.white);
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

         setVisible(true);
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
         cd.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
                 firstStep();
             }
         }
         );
        cd1.addActionListener(new ActionListener()
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
                          rs = s.executeQuery( "SELECT DISTINCT(CDNAME) FROM CDDETAILS WHERE CDNAME LIKE "+"'"+title.getText().toUpperCase()+"%"+"'" );
                          while( rs.next() )
                           {
                              nm.addItem(rs.getObject(1));
                             ++count;
                           }
                          if(count>0)
                           {
                              nm.setVisible(true);
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

         nm.addKeyListener(new KeyListener()
           {
              public void keyTyped(KeyEvent e1){}
              public void keyReleased(KeyEvent e2){}
                {
                }
              public void keyPressed(KeyEvent e3)
                {
                    int xy=e3.getKeyCode();
                    if( (xy>=65&&xy<=90)||(xy>=97&&xy<=122)||(xy==32)||(xy==46)||(xy==16)||(xy==18)||(xy==20)||(xy==17)||(xy==8)||(xy==127)||(xy>=37&&xy<=40) )
                      {
                      }
                    else
                      {
                         JOptionPane.showMessageDialog(null,"PLEASE ENTER THE ALPHABETS ONLY","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
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
                      first = Integer.parseInt(cd.getText());
                      if( cd1.getText()==null||cd1.getText().equals(""))
                        {
                        }
                      else
                        {
                           if( Integer.parseInt(cd1.getText()) >= first )
                              {
                                 second =Integer.parseInt(cd1.getText());
                              }
                        }

                      TITLE=(String)nm.getSelectedItem();
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

                      if( CONFIRM==1)
                        {
                          if(first==loop)
                          {
                          if(first<=second)
                            {
                              for(int Z=first;Z<=second;Z++)
                                {
                                   cd.setText(""+Z);
                                  // JOptionPane.showMessageDialog(null,""+Z);
                                   s =con.createStatement();
                                   int a = s.executeUpdate("INSERT INTO CDDETAILS (CDCODE,CDSERIAL,BKANO,CDVERSION, CDNAME,CDTYPE, SUBJECT,CDSTATUS,NOTE,MZACNO ) VALUES ( "+"'"+cd.getText().toUpperCase()+"',"
                                                                                                                                                   +"'"+cdno1.getText().toUpperCase()+"',"
                                                                                                                                                   +"'"+bno1.getText().toUpperCase()+"',"
                                                                                                                                                   +"'"+vrs.getText().toUpperCase()+"',"
                                                                                                                                                   +"'"+TITLE+"',"
                                                                                                                                                   +"'"+typ.getSelectedItem()+"',"
                                                                                                                                                   +"'"+sbj.getText().toUpperCase()+"',"
                                                                                                                                                   +"'"+cdss1.getSelectedItem()+"',"
                                                                                                                                                   +"'"+note1.getText().toUpperCase()+"',"
                                                                                                                                                   +"'"+mno1.getText().toUpperCase()+"'"
                                                                                                                                                   +")");

                                   con.commit();

                                   int r=s.executeUpdate("UPDATE CDDETAILS SET RDATE=TO_CHAR(TO_DATE('"+recdt.getText().toUpperCase()+"'),'DD-MON-YYYY') WHERE CDCODE='"+cd.getText().toUpperCase()+"'");
                                   con.commit();
                                   int r0=s.executeUpdate("UPDATE CDDETAILS SET ADATE=TO_CHAR(TO_DATE(SYSDATE),'DD-MON-YYYY') WHERE CDCODE='"+cd.getText().toUpperCase()+"'");
                                   con.commit();
                                   int wet=s.executeUpdate("UPDATE CDDETAILS SET ISSUED='0' WHERE CDCODE="+"'"+cd.getText()+"'");
                                   con.commit();

                                   rs=s.executeQuery("SELECT TO_CHAR(ADATE,'DD-MON-YYYY') FROM CDDETAILS WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'");
                                   while(rs.next())
                                     {
                                       acdt1.setText(rs.getString(1));
                                     }
                                  con.commit();
                               }
                             getTable();
                           }
                         else
                           {
                              int cfirm=JOptionPane.showConfirmDialog(null,"U MUST GIVE 2-ND  ACCESSNO  GREATER  THAN 1_ST  ACCESSNO","NEW RECORD",JOptionPane.YES_NO_OPTION);
                              if(cfirm==0)
                                {
                                   cd1.setText(cd1.getText());
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
                     rs = s.executeQuery( " SELECT CD.CDCODE, STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR FROM CDTRANSACTION CDT,STUDENTDETAILS STD,CDDETAILS CD WHERE CD.CDCODE="+"'"+cd.getText().toUpperCase()+"'"+" AND CD.CDCODE=CDT.CDCODE AND STD.ADNO=CDT.ADNO" );
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
                                   int qwe=s.executeUpdate("UPDATE CDDETAILS SET ISSUED='1' WHERE CDCODE="+"'"+cd.getText()+"'");
                               }
                              up.setEnabled(false);
                         }

                           rs = s.executeQuery( " SELECT CD.CDCODE, SF.LID, SF.LNAME,SF.DEPT  FROM CDTRANSACTION1 CDT1,STAFF SF,CDDETAILS CD WHERE CD.CDCODE="+"'"+cd.getText().toUpperCase()+"'"+"AND CD.CDCODE=CDT1.CDCODE AND SF.LID=CDT1.LID" );
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
                                   int we=s.executeUpdate("UPDATE CDDETAILS SET ISSUED='1' WHERE CDCODE="+"'"+cd.getText()+"'");
                                }
                              up.setEnabled(false);
                            }


                      if(STAGE==0)
                        {
                           first = Integer.parseInt(cd1.getText());
                           if( cd1.getText()==null||cd1.getText().equals(""))
                             {
                             }
                           else
                             {
                               if( Integer.parseInt(cd1.getText()) >= first )
                                 {
                                    second =Integer.parseInt(cd1.getText());
                                 }
                             }

                             TITLE=(String)nm.getSelectedItem();
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
                             if( CONFIRM==1)
                               {
                                 if(first<=second)
                                   {
                                     for(int Z=first;Z<=second;Z++)
                                        {
                                           cd.setText(""+Z);
                                           int a = s.executeUpdate("UPDATE CDDETAILS SET CDSERIAL ="+"'"+cdno1.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int f = s.executeUpdate("UPDATE CDDETAILS SET BKANO ="+"'"+bno1.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int c1 = s.executeUpdate("UPDATE CDDETAILS SET CDVERSION ="+"'"+vrs.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int b = s.executeUpdate("UPDATE CDDETAILS SET CDNAME ="+"'"+TITLE+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int d = s.executeUpdate("UPDATE CDDETAILS SET CDTYPE ="+"'"+typ.getSelectedItem()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int e1 = s.executeUpdate("UPDATE CDDETAILS SET SUBJECT ="+"'"+sbj.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int g = s.executeUpdate("UPDATE CDDETAILS SET CDSTATUS  ="+"'"+cdss1.getSelectedItem()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int h = s.executeUpdate("UPDATE CDDETAILS SET RDATE  ="+"'"+recdt.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int i = s.executeUpdate("UPDATE CDDETAILS SET NOTE  ="+"'"+note1.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           int j = s.executeUpdate("UPDATE CDDETAILS SET MZACNO ="+"'"+mno1.getText().toUpperCase()+"'"+"WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'" );
                                           con.commit();
                                           int wet1=s.executeUpdate("UPDATE CDDETAILS SET ISSUED='0' WHERE CDCODE="+"'"+cd.getText()+"'");
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
                        rs=s.executeQuery("SELECT CDCODE FROM CDTRANSACTION  WHERE CDCODE="+"'"+cd.getText()+"'");
                        while(rs.next())
                        {
                            int mis1=s.executeUpdate("UPDATE CDDETAILS SET STATUS='MISSING' WHERE CDCODE="+"'"+cd.getText()+"'");
                            con.commit();
                            int mis2=s.executeUpdate("DELETE FROM CDTRANSACTION WHERE CDCODE="+"'"+cd.getText()+"'");
                            con.commit();
                            JOptionPane.showMessageDialog(null,"STATUS OF" +cd.getText()+" UPDATED TO LOST");
                        }
                        rs=s.executeQuery("SELECT CDCODE FROM CDTRANSACTION1  WHERE CDCODE="+"'"+cd.getText()+"'");
                        while(rs.next())
                        {
                            int mis1=s.executeUpdate("UPDATE CDDETAILS SET STATUS='MISSING' WHERE CDCODE="+"'"+cd.getText()+"'");
                            con.commit();
                            int mis2=s.executeUpdate("DELETE FROM CDTRANSACTION1 WHERE CDCODE="+"'"+cd.getText()+"'");
                            con.commit();
                            JOptionPane.showMessageDialog(null,"STATUS OF" +cd.getText()+" UPDATED TO LOST");
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
                 //RESET CD - DETAILS
                 reset();
                  getTable1();
               }
          }
        );

        can.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {

                setVisible( false );
              }
          }
        );
      }
    private void reset()
      {
           count=0;
                 cd.setText("");
                 sequenceGenerator();
                 ins.setEnabled(false);
                 mis.setEnabled(false);
                 title.setText("");
                 title.setVisible(true);
                 cdno1.setEditable(false);
                 bno1.setEditable(false);
                 vrs.setEditable(false);
                 sbj.setEditable(false);
                 recdt.setEditable(false);
                 note1.setEditable(false);
                 mno1.setEditable(false);
                 title.setEditable(false);

                 nm.removeAllItems();
                 //nm.setSelectedItem("");
                 nm.setVisible(false);

                 vrs.setText("");
                 typ.removeAllItems();
                 typ.setSelectedItem("");
                 sbj.setText("");
                 bno1.setText("");
                 cdss1.removeAllItems();
                 cdno1.setText("");
                 recdt.setText("");
                 note1.setText("");
                 mno1.setText("");
                 acdt1.setText("");


                 count=0;
                 despn.setVisible(false);
                 p1.setVisible(true);
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

               k.add(war = new JLabel("U  MUST  INPUT  CD - NAME  .  .  ."),new AbsoluteConstraints(25,20));
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
         if(cd.getText()==null||cd.getText().equals("")){JOptionPane.showMessageDialog(null,"U MUST 1ST INPUT 1-ST ACESSNO .  .  .");}
           else{
               try
                 {
                  s=con.createStatement();
                  rs=s.executeQuery("SELECT CDSERIAL,BKANO,CDVERSION,CDNAME,CDTYPE,SUBJECT,CDSTATUS,TO_CHAR(RDATE,'DD-MON-YYYY') ,NOTE,MZACNO,TO_CHAR(ADATE,'DD-MON-YYYY') FROM CDDETAILS WHERE CDCODE="+"'"+cd.getText().toUpperCase()+"'");
                  if(rs.next())
                  {

                      despn.setVisible(false);
                      p1.setVisible(true);
                      up.setEnabled(true);
                      mis.setEnabled(true);

                      cdno1.setText(rs.getString(1));
                      bno1.setText(rs.getString(2));
                      vrs.setText(rs.getString(3));

                      value1=rs.getString(4);
                      title.setText(value1);
                      title.setEditable(true);
                      cdno1.setEditable(true);
                      bno1.setEditable(true);
                      vrs.setEditable(true);
                      sbj.setEditable(true);
                      recdt.setEditable(true);
                      note1.setEditable(true);
                      mno1.setEditable(true);
                      //nm.setSelectedItem(value1);

                      value3=rs.getString(5);

                      sbj.setText(rs.getString(6));
                      value2=rs.getString(7);
                      recdt.setText(rs.getString(8));
                      note1.setText(rs.getString(9));
                      mno1.setText(rs.getString(10));
                      acdt1.setText(rs.getString(11));


                      //nm.setEditable(true);

                      typ.removeAllItems();
                      cdss1.removeAllItems();
                      typ.addItem("APPLICATION");
                      typ.addItem("SOFTWARE");
                      typ.addItem("GAME");
                      typ.addItem("UTILITIES");
                      typ.setSelectedItem(value3);
                      cdss1.addItem("GOOD");
                      cdss1.addItem("BROKEN");
                      cdss1.setSelectedItem(value2);


                  }
                  else
                  {
                      int cfirm=JOptionPane.showConfirmDialog(null,"DO U WANT TO INSERT NEW RECORD","NEW RECORD",JOptionPane.YES_NO_OPTION);
                      if(cfirm==0)
                        {
                      cdno1.setEditable(true);
                      bno1.setEditable(true);
                      vrs.setEditable(true);
                      sbj.setEditable(true);
                      recdt.setEditable(true);
                      note1.setEditable(true);
                      mno1.setEditable(true);
                      title.setEditable(true);
                      title.setText("");
                      //nm.setEditable(true);
                      //nm.setSelectedItem("");
                      vrs.setText("");
                      typ.setSelectedItem("");
                      sbj.setText("");
                      bno1.setText("");
                      cdss1.setSelectedItem("");
                      cdno1.setText("");
                      mno1.setText("");
                      recdt.setText("");
                      acdt1.setText("");
                      note1.setText("");

                      typ.removeAllItems();
                      cdss1.removeAllItems();
                      typ.addItem("APPLICATION");
                      typ.addItem("SOFTWARE");
                      typ.addItem("GAME");
                      typ.addItem("UTILITIES");
                      cdss1.addItem("GOOD");
                      cdss1.addItem("BROKEN");

                      ins.setEnabled(true);
                     }
                  }

                 }
                 catch(SQLException se)
                 {
                    JOptionPane.showMessageDialog(null,se.getMessage());
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
              rs = s.executeQuery ( " SELECT CDCODE ACCESSNO,CDNAME NAME,CDVERSION VERSION,BKANO BOOKACCESSNO,MZACNO MAGAZINEACCESSNO,CDSERIAL,CDTYPE,CDSTATUS STATUS,SUBJECT,TO_CHAR(RDATE,'DD-MON-YYYY') RECEIVINGDATE FROM CDDETAILS WHERE ADATE=TO_CHAR(SYSDATE,'DD-MON-YYYY')");// ORDER BY CDCODE " );
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
              rs=s.executeQuery("SELECT COUNT(CDCODE) FROM CDDETAILS");
              while(rs.next())
              {
                loop=rs.getInt(1)+1;
              }
              cd.setText(String.valueOf(loop));
              cd1.setText(String.valueOf(loop));
              s.close();
          }
          catch(SQLException sq)
          {
            JOptionPane.showMessageDialog(null,sq.getMessage());
          }
     }


    public static void main( String a[] )
      {
         CdLibrary2 d5 = new CdLibrary2();
         d5.setVisible(true);
      }
  }
