package org.ulibsoft.search.entitySearch;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.BookCatalogSearchDAO;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;

public class Copies extends JFrame
  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1032517238021529500L;
	private JLabel bn,an,tnb,anb,inb,xyz,ans;
    private JTextField tn,ano,ino;
    private Choice bn1,an1,an2;
    private JButton next,can;
    private JPanel bkpn;
    private Container c;
    private String s1,s2;
    private ResultSet rs,rs1;
    private Statement s;
    private Connection con;
    private static int count;

    private BookCatalogSearchDAO bkCtlgSrch;
    private BookTransactionSearchDAO bkTxnSrch;
    public Copies()
      {
        super ("Copies for Specimen");
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        show();

       try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
        createComponents();
        bkCtlgSrch = DAOFactory.getDAOFactory().getBkCatlogSearchDAO();
        bkTxnSrch = DAOFactory.getDAOFactory().getBkTranSrchDAO();
        Set<String> itms= bkCtlgSrch.listFirstAuthorSurnames("");
        for ( String itm: itms )
        	an2.addItem(itm);
        
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
                                            "COPIES FOR A BOOK", 1, 2, c.getFont(), Color.magenta));
        c.add(bkpn,new AbsoluteConstraints(190,150,410,265));

        ans = new JLabel("AUTHOR-SURNAME");
        ans.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (ans,new AbsoluteConstraints(78,31));

        an2=new Choice();
        an2.setForeground (new Color ( 255, 0, 153 ) );

        bkpn.add(an2,new AbsoluteConstraints(200,30,175,20));

        an = new JLabel("AUTHOR-REALNAME");
        an.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (an,new AbsoluteConstraints(73,61));

        an1=new Choice();
        an1.setForeground (new Color ( 255, 0, 153 ) );
        bkpn.add(an1,new AbsoluteConstraints(200,60,175,20));

        bn = new JLabel("TITLE");
        bn.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add( bn,new AbsoluteConstraints(157,91) );

        bn1 = new Choice();
        bn1.setForeground (new Color ( 255, 0, 153 ) );

        bkpn.add (bn1,new AbsoluteConstraints(200,90,175,20));


        tnb = new JLabel("TOTAL-NO-OF-COPIES ");
        tnb.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (tnb,new AbsoluteConstraints(63,121));

        tn=new JTextField();
        tn.setEditable(false);
        tn.setForeground (new Color ( 255, 0, 153 ) );
        tn.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add(tn,new AbsoluteConstraints(200,120,175,20));

        anb=new JLabel("AVAILABLE-NO-OF-COPIES ");
        anb.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (anb,new AbsoluteConstraints(37,151)) ;

        ano=new JTextField( );
        ano.setEditable(false);
        ano.setForeground (new Color ( 255, 0, 153 ) );
        ano.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add(ano,new AbsoluteConstraints(200,150,175,20));

        inb=new JLabel("ISSUED-NO-OF-COPIES ");
        inb.setForeground (new Color ( 120, 120, 153 ) );
        bkpn.add (inb,new AbsoluteConstraints(58,181));

        ino=new JTextField( );
        ino.setEditable(false);
        ino.setForeground (new Color ( 255, 0, 153 ) );
        ino.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
        bkpn.add (ino,new AbsoluteConstraints( 200, 180, 175, 20 ) );

        next=new JButton("NEXT>>>");
        next.setBackground (Color.cyan);
        next.setForeground(Color.black);
        next.setMnemonic('N');
        next.setBorder (new BevelBorder (0));
        bkpn.add(next,new AbsoluteConstraints(75,220,130,27));

        can=new JButton("QUIT");
        can.setBackground (Color.cyan);
        can.setForeground(Color.black);
        can.setMnemonic('Q');
        can.setBorder (new BevelBorder(0));
        bkpn.add(can,new AbsoluteConstraints(210,220,130,27));

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
                    //p.setFrame(Index.cp);
                 }
              }
          }
        );

        an2.addItemListener(new ItemListener()
          {
            public void itemStateChanged(ItemEvent e)
              {
                count++;
                an1.removeAll();
                Set<String> itms= bkCtlgSrch.listFirstAuthorNames(an2.getSelectedItem());
                for ( String itm: itms )
                	an1.addItem(itm);                
              }
          }
        );

        an1.addItemListener(new ItemListener()
          {
            public void itemStateChanged(ItemEvent e)
              {
                count++;
                bn1.removeAll();
                Set<String> itms= bkCtlgSrch.listBookNames(an2.getSelectedItem(), an1.getSelectedItem());
                for ( String itm: itms )
                	bn1.addItem(itm);                
              }

          }
        );

        bn1.addItemListener(new ItemListener()
          {
            public void itemStateChanged(ItemEvent e)
              {
                count++;
                try
                  {
                    s = con.createStatement();
                    if(count>=3)
                     {
                    	
                        //TOTAL NUMBER OF COPIES
                    	int count = bkCtlgSrch.countBooks(an2.getSelectedItem(), an1.getSelectedItem(), bn1.getSelectedItem());
                    	tn.setText( String.valueOf(count) );
                    	                    	                    	
                        //AVAILABLE NO OF COPIES
                    	List<BookModel> books = bkTxnSrch.availableBooks(an2.getSelectedItem(), an1.getSelectedItem(), bn1.getSelectedItem());
                    	if ( books != null )
                    		ano.setText( String.valueOf(books.size()) );

                        //ISSUED NUMBER OF COPIES
                        //rs = s.executeQuery("SELECT COUNT(BOOKNAME) FROM BKTRANSACTION  WHERE AUTHOR="+"'"+an2.getSelectedItem() +""+an1.getSelectedItem()+"'"+"AND BOOKNAME="+"'"+bn1.getSelectedItem()+"'" );
                            rs=s.executeQuery("SELECT COUNT(CODE) FROM BKTRANSACTION WHERE CODE IN(SELECT ACESSNO FROM LIBRARY WHERE BOOKNAME ="+"'"+bn1.getSelectedItem()+"'"+"AND AUTHOR1S="+"'"+an2.getSelectedItem()+"'"+"AND AUTHOR1F="+"'"+an1.getSelectedItem() +"'"+")");
                           while( rs.next() )
                             {
                                s1=rs.getString(1);
                             }
                       // rs1 = s.executeQuery("SELECT COUNT(BOOKNAME) FROM BKTRANSACTION1  WHERE AUTHOR="+"'"+an2.getSelectedItem()+"'"+"AND BOOKNAME="+"'"+bn1.getSelectedItem()+"'" );
                           rs1=s.executeQuery("SELECT COUNT(CODE) FROM BKTRANSACTION1 WHERE CODE IN(SELECT ACESSNO FROM LIBRARY WHERE BOOKNAME ="+"'"+bn1.getSelectedItem()+"'"+"AND AUTHOR1S="+"'"+an2.getSelectedItem()+"'"+"AND AUTHOR1F="+"'"+an1.getSelectedItem() +"'"+")");
                            while(rs1.next())
                              {
                                s2=rs1.getString(1);
                              }

                        ino.setText(Integer.toString(Integer.parseInt(s1)+Integer.parseInt(s2)));
                        s.close();
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
                //RESET BOOK VALUES
                 // bn1.setText("");
                //  an1.setText("");
                bn1.removeAll();
                an1.removeAll();
                  tn.setText("");
                  ano.setText("");
                  ino.setText("");
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
        Copies i = new Copies();
      }
  }
