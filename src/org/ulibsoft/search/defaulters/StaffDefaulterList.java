package org.ulibsoft.search.defaulters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

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

import org.ulibsoft.core.ui.CustomTable;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKTransMemberModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class StaffDefaulterList extends JFrame
  {
     private JButton but1,but2,next,can;
     private JLabel but0;

     private JLabel lid;
     private JButton g2;
     private JTextField cd;
     private CustomTable plist;
     private JPanel pnl,bkpn;
     private Container c;

     private BookTransactionSearchDAO bkTxnSrchDao;

     public StaffDefaulterList()
       {
           super("STAFF  DEFAULTERS LIST OF  BOOKS");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
           bkTxnSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();
           createComponents();          
           componentListener();
        }

      private void createComponents()
        {
            c = getContentPane( ) ;
            c.setBackground( new Color(0,0,40) ) ;
            c.setLayout ( new AbsoluteLayout( ) ) ;

            c = getContentPane();
            c.setLayout( new AbsoluteLayout() );
            c.setBackground( new Color( 0, 0, 40 ) );

            but0 = new JLabel( "        STAFF  DEFAULTERS  LIST" ) ;
            but0.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
            but0.setBackground ( Color.pink );
            but0.setForeground(Color.black);
            c.add ( but0, new AbsoluteConstraints( 260+35, 40, 200, 30 ) );

            but1 = new JButton( "" ) ;
            but1.setBackground ( Color.cyan );
            but1.setForeground(Color.black);
            but1.setEnabled(false);
            but1.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
            c.add ( but1, new AbsoluteConstraints( 260+30+5, 40, 200, 30 ) );

            but2 = new JButton( "" ) ;
            but2.setBackground ( Color.pink );
            but2.setForeground(Color.black);
            but2.setEnabled(false);
            but2.setBorder ( new MatteBorder (1, 1, 1, 1, Color.black ) );
            c.add ( but2, new AbsoluteConstraints( 267+30+5, 47, 200, 30 ) );

            bkpn = new JPanel(new AbsoluteLayout());
            bkpn.setBackground(c.getBackground());
            bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CHOOSE", 1, 2, c.getFont(), Color.magenta));
            c.add(bkpn,new AbsoluteConstraints(237-11,95+15,338,110));

            lid = new JLabel( "PROFESSOR  ID" );
            lid.setForeground ( new Color ( 120,120, 153 ) );
            bkpn.add(lid,new AbsoluteConstraints(30+15,30));

            cd = new JTextField( );
            cd.setBackground(new Color(120,120,153));            
            cd.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));
            cd.setForeground (new Color ( 120, 120, 153 ) );
            bkpn.add(cd,new AbsoluteConstraints(125+15,25,150,22));

            g2 = new JButton("FIND  ");
            g2.setBorder (new BevelBorder ( 0 ));
            g2.setBackground ( Color.cyan );
            g2.setForeground ( Color.magenta );
            g2.setMnemonic('F');
           
            bkpn.add(g2,new AbsoluteConstraints(20-2,60,100,27));

            pnl = new JPanel(new BorderLayout());
            pnl.setBackground(c.getBackground ());
            pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                              "DEFAULTERS-LIST", 1, 2, c.getFont(), Color.magenta));
            c.add(pnl,new AbsoluteConstraints(100,250,600,275));

            next = new JButton( "NEXT>>>" ) ;
            next.setBackground ( Color.cyan);
            next.setForeground(Color.magenta);
            next.setMnemonic('N');
            next.setBorder(new BevelBorder(0));
            bkpn.add ( next, new AbsoluteConstraints( 120, 60, 100, 27 ) );

            can = new JButton( "EXIT" ) ;
            can.setBackground ( Color.cyan);
            can.setForeground( Color.magenta);
            can.setMnemonic('X');
            can.setBorder(new BevelBorder(0));
            bkpn.add ( can, new AbsoluteConstraints( 222, 60, 100, 27 ) );            
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
                  //p.setFrame(Index.pl);
                }
             }
         }
       );       
       g2.addActionListener (new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
        	   //Map<SearchFilter.PendingCriteria, Object> filter = new 
        	   List<BKTransMemberModel> records = bkTxnSrchDao.findStaffPendingBooks(null);
        	   populatePendingList(records);
        	 }
        }
      );

      next.addActionListener( new ActionListener()
      {
        public void actionPerformed( ActionEvent e )
          {
            cd.setText("");
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
   private Vector<String> getDisplayColumns() {
		Vector<String> cols = new Vector<String>();
		cols.add("LID");
		cols.add("Name");
		cols.add("Dept");		
		cols.add("Code");
		cols.add("Return Date");
		return cols;
	}
   private void populatePendingList(List<BKTransMemberModel> records) {
		
		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"PENDING BOOK RECORDS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
		for (BKTransMemberModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getId());
			rowData.add(row.getName());
			rowData.add(row.getDept());			
			rowData.add(row.getCode());
			rowData.add(DateHelper.format(row.getReturnDate()));
			rows.add(rowData);
		}

		plist = new CustomTable(getDisplayColumns(),"STAFF BOOK-DEFAULTERS LIST SEARCH");
		plist.populateData(rows);		
       pnl.add(plist.getPanel(),BorderLayout.CENTER);
       validate();
	}
    public static void main(String a[])
    {
        StaffDefaulterList pl = new StaffDefaulterList();
    }
 }
