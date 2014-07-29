package org.ulibsoft.search.entitySearch;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;

import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class CdCopies extends JFrame
  {
    private JLabel cn,vrs,tnb,anb,inb;
    private JTextField tn,ano,ino;
    private Choice cn1,vrs1;
    private JButton next,can;
    private JPanel bkpn;
    private Container c;
    private String s1,s2;
    private ResultSet rs,rs1;
    private Statement s;
    private Connection con;
    private static int count;

    public CdCopies()
      {
        super ("COPIES OF SPECIMEN");
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        show();

        try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
        createComponents();
        componentListener();
        MyAdapter4 myap = new MyAdapter4();
        addWindowListener(myap);
      }

    private void createComponents()
      {
        c = getContentPane();
        c.setLayout(new AbsoluteLayout());
        c.setBackground( new Color(0, 0, 40 ));

        bkpn = new JPanel(new AbsoluteLayout());
        bkpn.setBackground(c.getBackground());
        bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "COPIES OF A CD", 1, 2, c.getFont(), Color.magenta));
        c.add(bkpn,new AbsoluteConstraints(190,150,410,235));

        cn = new JLabel("TITLE");
        cn.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add( cn,new AbsoluteConstraints(157,31) );

        cn1 = new Choice();
        cn1.setForeground (new Color ( 255, 0, 153 ) );
           try
             {
                s = con.createStatement();
                rs = s.executeQuery("SELECT DISTINCT(CDNAME) FROM CDDETAILS ");
                   while(rs.next ())
                     {

                        cn1.addItem(rs.getString(1));
                     }
                s.close();

             }
           catch(SQLException se)
             {
                JOptionPane.showMessageDialog (null,se.getMessage ());
                se.printStackTrace();
             }

        bkpn.add (cn1,new AbsoluteConstraints(200,30,175,20));



        vrs = new JLabel("VERSION");
        vrs.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (vrs,new AbsoluteConstraints(138,61));

        vrs1=new Choice();
        vrs1.setForeground (new Color ( 255, 0, 153 ) );
        bkpn.add(vrs1,new AbsoluteConstraints(200,60,175,20));

        tnb = new JLabel("TOTAL-NO-OF-COPIES ");
        tnb.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (tnb,new AbsoluteConstraints(63,91));

        tn=new JTextField();
        tn.setEditable(false);
        tn.setForeground (new Color ( 255, 0, 153 ) );
        tn.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add(tn,new AbsoluteConstraints(200,90,175,20));

        anb=new JLabel("AVAILABLE-NO-OF-COPIES ");
        anb.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (anb,new AbsoluteConstraints(37,121)) ;

        ano=new JTextField( );
        ano.setEditable(false);
        ano.setForeground (new Color ( 255, 0, 153 ) );
        ano.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add(ano,new AbsoluteConstraints(200,120,175,20));

        inb=new JLabel("ISSUED-NO-OF-COPIES ");
        inb.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (inb,new AbsoluteConstraints(58,151));

        ino=new JTextField( );
        ino.setEditable(false);
        ino.setForeground (new Color ( 255, 0, 153 ) );
        ino.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add (ino,new AbsoluteConstraints( 200, 150, 175, 20 ) );

        next=new JButton("NEXT>>>");
        next.setBackground (Color.cyan);
        next.setForeground(Color.black);
        next.setMnemonic('N');
        next.setBorder (new BevelBorder (0));
        bkpn.add(next,new AbsoluteConstraints(75,190,130,27));

        can=new JButton("QUIT");
        can.setBackground (Color.cyan);
        can.setForeground(Color.black);
        can.setMnemonic('Q');
        can.setBorder (new BevelBorder(0));
        bkpn.add(can,new AbsoluteConstraints(210,190,130,27));

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
                 }
              }
          }
        );

        cn1.addItemListener(new ItemListener()
          {
            public void itemStateChanged(ItemEvent e)
              {

                vrs.removeAll();
                try
                  {
                    s = con.createStatement();
                    rs = s.executeQuery("SELECT DISTINCT(CDVERSION) FROM CDDETAILS WHERE CDNAME="+"'"+cn1.getSelectedItem()+"'"+"ORDER BY CDVERSION");
                       while( rs.next () )
                         {
                           vrs1.addItem (rs.getString(1));
                         }
                    s.close();
                  }
                catch(SQLException se)
                  {
                    JOptionPane.showMessageDialog (null,se.getMessage ());
                    se.printStackTrace();
                  }
              }
          }
        );


        vrs1.addItemListener(new ItemListener()
          {
            public void itemStateChanged(ItemEvent e)
              {

                try
                  {
                    s = con.createStatement();


                        //TOTAL NUMBER OF COPIES
                        rs = s.executeQuery("SELECT COUNT(CDNAME) FROM CDDETAILS WHERE  CDNAME="+"'"+cn1.getSelectedItem()+"'"+"AND  CDVERSION="+"'"+vrs1.getSelectedItem()+"'");
                           if( rs.next ())
                             {
                               tn.setText( rs.getString(1) );
                             }

                        //AVAILABLE NO OF COPIES
                        rs = s.executeQuery("SELECT COUNT(CD.CDNAME) FROM CDDETAILS CD WHERE CD.CDCODE NOT IN(SELECT CDT.CDCODE FROM CDTRANSACTION CDT WHERE CDT.CDCODE=CD.CDCODE) AND CD.CDCODE NOT IN(SELECT CDT1.CDCODE FROM CDTRANSACTION1 CDT1 WHERE CDT1.CDCODE=CD.CDCODE) AND CD.CDNAME="+"'"+cn1.getSelectedItem()+"'"+"AND CD.CDVERSION="+"'"+vrs1.getSelectedItem()+"'" );
                           if( rs.next() )
                             {
                               ano.setText( rs.getString(1) );
                             }

                        //ISSUED NUMBER OF COPIES
                        rs = s.executeQuery("SELECT COUNT(CD.CDNAME) FROM CDDETAILS CD,CDTRANSACTION CDT WHERE CDT.CDCODE=CD.CDCODE AND CD.CDNAME="+"'"+cn1.getSelectedItem() +"'"+"AND CD.CDVERSION="+"'"+vrs1.getSelectedItem()+"'" );
                           if( rs.next() )
                             {
                                s1=rs.getString(1);
                             }
                        rs1 = s.executeQuery("SELECT COUNT(CD.CDNAME) FROM CDDETAILS CD, CDTRANSACTION1 CDT1 WHERE CDT1.CDCODE=CD.CDCODE AND CD.CDNAME="+"'"+cn1.getSelectedItem()+"'"+"AND CD.CDVERSION="+"'"+vrs1.getSelectedItem()+"'" );
                            if(rs1.next())
                              {
                                s2=rs1.getString(1);
                              }

                        ino.setText(Integer.toString(Integer.parseInt(s1)+Integer.parseInt(s2)));
                        s.close();
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
                //RESET BOOK VALUES
                 // bn1.setText("");
                //  an1.setText("");
                  tn.setText("");
                  ano.setText("");
                  ino.setText("");
                  vrs1.removeAll();
              }
          }
        );

        can.addActionListener(new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                setVisible(false);
              }
          }
        );
      }
    private class MyAdapter4 extends WindowAdapter
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
        CdCopies i = new CdCopies();
      }
  }
