package org.ulibsoft.search.defaulters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.ulibsoft.constants.SearchFilter;
import org.ulibsoft.constants.SearchFilter.PendingCriteria;
import org.ulibsoft.core.ui.CustomTable;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKTransMemberModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;


public class PendingList extends JFrame
  {
     /**
	 * 
	 */
	private static final long serialVersionUID = 4642897202118486090L;
	private JLabel date,branch,year;
    private JButton pl,can,next;
    
    private JTextField df;
    private JComboBox<String> bh,yr ;
    private CustomTable plist;
    private JPanel pnl,bkpn;
     
     private Container c;   
     private BookTransactionSearchDAO bkTransSrchDao;
     
     private String[] branches = new String[] { "", "CSE", "CSIT", "ECE",
  			"EEE", "MEC", "AE", "AEI", "ARC", "ACM", "BME", "BT", "CBE", "CEE",
  			"CHE", "CIV", "CPE", "CSS", "DT", "ECM", "ECS", "ECSE", "ETM",
  			"EIE", "FPT", "FT", "ICE", "INE", "IPE", "IST", "MCT", "MET",
  			"MIN", "MMC", "MMD", "MMT", "NVA", "PHM", "PTG", "PLG", "SCE",
  			"TEX" };

     public PendingList()
       {
         super( "PendingList" );
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         setVisible(true);
         bkTransSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();
         createComponents();         
         componentListener();
         myadapter7 myap = new myadapter7();
         addWindowListener(myap);
       }

     private void createComponents()
       {
         c = getContentPane();
         c.setLayout(new AbsoluteLayout());
         c.setBackground( new Color(0,0,40));

         bkpn = new JPanel(new AbsoluteLayout());
         bkpn.setBackground(c.getBackground());
         bkpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "FIND", 1, 2, c.getFont(), Color.magenta));
         c.add(bkpn,new AbsoluteConstraints(237,50,314,180));

         date = new JLabel( "RECIEVE-DATE" );
         date.setForeground ( new Color ( 120,120, 153 ) );
         bkpn.add(date,new AbsoluteConstraints(30,30));

         df = new JTextField( );
         df.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));
         df.setForeground (new Color ( 120, 120, 153 ) );
         df.setCaretColor ( new Color ( 0, 204, 102 ) );
         bkpn.add(df,new AbsoluteConstraints(125,25,150,22));

         branch = new JLabel("COURSE");
         branch.setForeground (new Color ( 120,120, 153 ) );
         bkpn.add( branch, new AbsoluteConstraints( 63, 60 ));

         bh = new JComboBox<String>(branches);
         bh.setBackground(c.getBackground());
         bh.setForeground(new Color( 120, 120, 153));
         bh.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
         bkpn.add (bh,new AbsoluteConstraints(125,55,60,22));
        
         year = new JLabel("SEMISTER");
         year.setForeground (new Color ( 120,120, 153 ) );
         bkpn.add(year,new AbsoluteConstraints(53,88));

         yr = new JComboBox<String>();
         yr.addItem ("");
         yr.addItem ("I");
         yr.addItem ("II-1");
         yr.addItem ("II-2");
         yr.addItem ("III-1");
         yr.addItem ("III-2");
         yr.addItem ("IV-1");
         yr.addItem ("IV-2");
         yr.setBackground(c.getBackground());
         yr.setForeground(new Color(120, 120, 153));
         yr.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
         bkpn.add (yr,new AbsoluteConstraints(125,85,60,22));

         pl = new JButton( "Search" );
         pl.setBorder ( new BevelBorder (0));
         pl.setBackground ( Color.cyan );
         pl.setForeground ( Color.magenta );
         pl.setMnemonic('S');
         bkpn.add(pl,new AbsoluteConstraints(13,120,95,22));

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.magenta );
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         bkpn.add(can,new AbsoluteConstraints(185+20,120,95,22));

         next = new JButton("CLEAR");
         next.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         next.setBackground ( Color.cyan );
         next.setForeground ( Color.magenta );
         next.setMnemonic('C');
         next.setBorder(new BevelBorder(0));
         //bkpn.add(next,new AbsoluteConstraints(101+8-40,150,95+80,22));
         bkpn.add(next,new AbsoluteConstraints(101+8,120,95,22));
         


         pnl = new JPanel(new BorderLayout());
         pnl.setBackground(c.getBackground ());
         pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                             "PENDING-LIST", 1, 2, c.getFont(), Color.magenta));
         c.add(pnl,new AbsoluteConstraints(100,250,600,275));

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
       can.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
              {
                 setVisible(false);
              }
         }
       );
       next.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
              {                
                 bh.setSelectedIndex(0);
                 yr.setSelectedIndex(0);
                 df.setText("");               
              }
         }
       );       
       /*df.addKeyListener (new KeyListener()
         {
           public void keyPressed(KeyEvent ke){}
           public void keyReleased(KeyEvent k){}

           public void keyTyped(KeyEvent e)
             {
               if(df.getText ().length ()>=0)
                 {
                    pl.setEnabled (false);
                 }
               if(df.getText ().length ()==0)
                 {
                   //JOptionPane.showMessageDialog (null,""+df.getText ().length ());
                   pl.setEnabled (true);
                 }
               g1.setEnabled (false);
               g2.setEnabled (false);
               bar.setEnabled (false);
             }
         }
       );*/
      /*df.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
            {
		    	  Map<SearchFilter.PendingCriteria, Object> filter = new HashMap<>();
		    	  try {
					filter.put(SearchFilter.PendingCriteria.PNDNG_DATE, DateHelper.parse(df.getText ()));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Invalid Date format","VALIDATION ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
		    	  List<BKTransMemberModel> records = bkTransSrchDao.findPendingBooks(filter);
		    	  populatePendingList(records);
        	  
           }
        }
      );*/

      pl.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
            {
        	  Map<SearchFilter.PendingCriteria, Object> filter= populateCriteria();        	  
        	  List<BKTransMemberModel> records = bkTransSrchDao.findPendingBooks(filter);
        	  populatePendingList(records);
            }	
        }
      );
    }
   private Map<PendingCriteria, Object> populateCriteria() {
		Map<SearchFilter.PendingCriteria, Object> filter = new HashMap<>();
 	  	if ( yr.getSelectedIndex() != 0)
 	  		filter.put(SearchFilter.PendingCriteria.PNDNG_YEAR, yr.getSelectedItem ());
 	  	if ( bh.getSelectedIndex() != 0 )
 	  		filter.put(SearchFilter.PendingCriteria.PNDNG_BRANCH, bh.getSelectedItem ());
 	  try {
 		  if ( df.getText() != null && df.getText().length() > 0)
			filter.put(SearchFilter.PendingCriteria.PNDNG_DATE, DateHelper.parse(df.getText ()));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Invalid Date format","VALIDATION ERROR", JOptionPane.ERROR_MESSAGE);
			return null;
		}
 	  if ( filter.isEmpty() ) 
 		  return null;
 	  
 	  return filter;
 	  
	}
  private class myadapter7 extends WindowAdapter
    {
      public void windowClosing(WindowEvent wt)
        {
    	  dispose();
          setVisible(false);          
        }
    }
  private Vector<String> getDisplayColumns() {
		Vector<String> cols = new Vector<String>();
		cols.add("AD NO");
		cols.add("Name");
		cols.add("Branch");
		cols.add("Year");
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
			rowData.add(row.getYear());
			rowData.add(row.getCode());
			rowData.add(DateHelper.format(row.getReturnDate()));
			rows.add(rowData);
		}

		plist = new CustomTable(getDisplayColumns(),"STUDENT BOOK-DEFAULTERS LIST SEARCH");
		plist.populateData(rows);		
        pnl.add(plist.getPanel(),BorderLayout.CENTER);
        validate();
	}
     public static void main(String a[])
       {
         PendingList pl = new PendingList();
       }
 }
