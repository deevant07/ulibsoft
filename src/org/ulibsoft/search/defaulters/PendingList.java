package org.ulibsoft.search.defaulters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.ulibsoft.constants.SearchFilter;
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
     private JLabel date,branch,year,xyz;
     private JButton pl,g1,g2,g3,bar,can,next;
     private JButton print1,print2,print3,print4;
     private JTextField df;
     private JComboBox bh,yr ;
     private JTable plist;
     private JPanel pnl,bkpn;
     private JScrollPane sp,sp5;
     private Container c;   
     private static int count;
     
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

         bh = new JComboBox();
         bh.addItem ("CSE");
         bh.addItem ("CSIT");
         bh.addItem ("ECE");
         bh.addItem ("EEE");
         bh.addItem ("MEC");
         bh.addItem ("AE");
         bh.addItem ("AEI");
         bh.addItem ("ARC");
         bh.addItem ("ACM");
         bh.addItem ("BME");
         bh.addItem ("BT");
         bh.addItem ("CBE");
         bh.addItem ("CEE");
         bh.addItem ("CHE");
         bh.addItem ("CIV");
         bh.addItem ("CPE");
         bh.addItem ("CSS");
         bh.addItem ("DT");
         bh.addItem ("ECM");
         bh.addItem ("ECS");
         bh.addItem ("ECSE");
         bh.addItem ("ETM");
         bh.addItem ("EIE");
         bh.addItem ("FPT");
         bh.addItem ("FT");
         bh.addItem ("ICE");
         bh.addItem ("INE");
         bh.addItem ("IPE");
         bh.addItem ("IST");
         bh.addItem ("MCT");
         bh.addItem ("MET");
         bh.addItem ("MIN");
         bh.addItem ("MMC");
         bh.addItem ("MMD");
         bh.addItem ("MMT");
         bh.addItem ("NVA");
         bh.addItem ("PHM");
         bh.addItem ("PTG");
         bh.addItem ("PLG");
         bh.addItem ("SCE");
         bh.addItem ("TEX");
         bh.setBackground(c.getBackground());
         bh.setForeground(new Color( 120, 120, 153));
         bh.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40)));
         bkpn.add (bh,new AbsoluteConstraints(125,55,60,22));

         g1 = new JButton("GO >>>");
         g1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         g1.setBackground ( Color.cyan );
         g1.setForeground ( Color.magenta );
         g1.setMnemonic('G');
         g1.setEnabled (false);
         g1.setBorder(new BevelBorder(0));
         bkpn.add(g1,new AbsoluteConstraints(190,55,85,22));

         year = new JLabel("SEMISTER");
         year.setForeground (new Color ( 120,120, 153 ) );
         bkpn.add(year,new AbsoluteConstraints(53,88));

         yr = new JComboBox();
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

         g2 = new JButton("GO >>>");
         g2.setBorder (new BevelBorder ( 0 ));
         g2.setBackground ( Color.cyan );
         g2.setForeground ( Color.magenta );
         g2.setMnemonic('O');
         g2.setEnabled (false);
         bkpn.add(g2,new AbsoluteConstraints(190,85,85,22));

         pl = new JButton( "PLIST" );
         pl.setBorder ( new BevelBorder (0));
         pl.setBackground ( Color.cyan );
         pl.setForeground ( Color.magenta );
         pl.setMnemonic('P');
         bkpn.add(pl,new AbsoluteConstraints(13,120,95,22));

         bar = new JButton("BR && YR");
         bar.setBackground (Color.cyan );
         bar.setForeground (Color.magenta );
         bar.setMnemonic('Y');
         bar.setEnabled (false);
         bar.setBorder (new BevelBorder (0));
         bkpn.add(bar,new AbsoluteConstraints(101+8,120,95,22));

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.magenta );
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         bkpn.add(can,new AbsoluteConstraints(185+20,120,95,22));

         print1 = new JButton("PRINT");
         print1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print1.setBackground ( Color.cyan );
         print1.setForeground ( Color.magenta );
         print1.setMnemonic('P');
         print1.setVisible (false);
         print1.setBorder(new BevelBorder(0));
         bkpn.add(print1,new AbsoluteConstraints(190,55,85,22));

         print2 = new JButton("PRINT");
         print2.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print2.setBackground ( Color.cyan );
         print2.setForeground ( Color.magenta );
         print2.setMnemonic('P');
         print2.setVisible (false);
         print2.setBorder(new BevelBorder(0));
         bkpn.add(print2,new AbsoluteConstraints(190,85,85,22));

         print3 = new JButton("PRINT");
         print3.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print3.setBackground ( Color.cyan );
         print3.setForeground ( Color.magenta );
         print3.setMnemonic('P');
         print3.setVisible (false);
         print3.setBorder(new BevelBorder(0));
         bkpn.add(print3,new AbsoluteConstraints(13,120,95,22));

         print4 = new JButton("PRINT");
         print4.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         print4.setBackground ( Color.cyan );
         print4.setForeground ( Color.magenta );
         print4.setMnemonic('P');
         print4.setVisible (false);
         print4.setBorder(new BevelBorder(0));
         bkpn.add(print4,new AbsoluteConstraints(101+8,120,95,22));

         next = new JButton("CLEAR");
         next.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ));
         next.setBackground ( Color.cyan );
         next.setForeground ( Color.magenta );
         next.setMnemonic('C');
         next.setBorder(new BevelBorder(0));
         bkpn.add(next,new AbsoluteConstraints(101+8-40,150,95+80,22));


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
                 print1.setVisible(false);
                 print2.setVisible(false);
                 print3.setVisible(false);
                 print4.setVisible(false);

                 g1.setVisible(true);
                 g2.setVisible(true);
                 bar.setVisible(true);
                 pl.setVisible(true);

                 g1.setEnabled(false);
                 g2.setEnabled(false);
                 bar.setEnabled(false);
                 //pl.setEnabled(false);
              }
         }
       );

       print1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );
        print2.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH BY BRANCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH BY NRANCH->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );
          print3.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH BY YEAR->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH BY YEAR->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );

          print4.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(plist!=null) {
                       java.util.Date date=new java.util.Date();
                       boolean success= plist.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH BY BRANCH&YEAR->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("STUDENT BOOK-DEFAULTERS LIST SEARCH BY BRANCH&YEAR->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                                       true,
                                                       null,
                                                       true
                                                      );
                       }
                       else
                         {
                           JOptionPane.showMessageDialog(null,"Plz Choose query to print");
                         }
                     }
                   catch(PrinterException pex)
                     {
                        JOptionPane.showMessageDialog(null,""+pex.getMessage());
                     }
                 }
            }
          );


       df.addKeyListener (new KeyListener()
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
       );

       bh.addItemListener (new ItemListener()
         {
           public void itemStateChanged(ItemEvent e)
             {
               g1.setEnabled (true);
               count++;
               if( count>2 )
               bar.setEnabled (true);
             }
        }
      );

      yr.addItemListener (new ItemListener()
        {
          public void itemStateChanged(ItemEvent e)
            {
              g2.setEnabled (true);
              count++;
              if(count>2)
              bar.setEnabled (true);
            }
        }
      );

      df.addActionListener (new ActionListener()
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
      );

      pl.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
            {
        	  List<BKTransMemberModel> records = bkTransSrchDao.findPendingBooks(null);
        	  populatePendingList(records);              
            }
        }
      );

      g1.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
            {
        	  Map<SearchFilter.PendingCriteria, Object> filter = new HashMap<>();
        	  filter.put(SearchFilter.PendingCriteria.PNDNG_BRANCH, bh.getSelectedItem ());
        	  List<BKTransMemberModel> records = bkTransSrchDao.findPendingBooks(filter);
        	  populatePendingList(records);
            }

        }
      );

      g2.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
            {
        	  Map<SearchFilter.PendingCriteria, Object> filter = new HashMap<>();
        	  filter.put(SearchFilter.PendingCriteria.PNDNG_YEAR, yr.getSelectedItem ());
        	  List<BKTransMemberModel> records = bkTransSrchDao.findPendingBooks(filter);
        	  populatePendingList(records);              
            }
        }
      );

      bar.addActionListener (new ActionListener()
        {
          public void actionPerformed(ActionEvent ae)
            {
        	  Map<SearchFilter.PendingCriteria, Object> filter = new HashMap<>();
        	  filter.put(SearchFilter.PendingCriteria.PNDNG_YEAR, yr.getSelectedItem ());
        	  filter.put(SearchFilter.PendingCriteria.PNDNG_BRANCH, bh.getSelectedItem ());
        	  List<BKTransMemberModel> records = bkTransSrchDao.findPendingBooks(filter);
        	  populatePendingList(records);
            }
        }
      );
    }
  private class myadapter7 extends WindowAdapter
    {
      public void windowClosing(WindowEvent wt)
        {
    	  dispose();
          setVisible(false);          
        }
    }
  private Vector<Object> getDisplayColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("AD NO");
		cols.add("Name");
		cols.add("Branch");
		cols.add("Year");
		cols.add("Return Date");
		return cols;
	}
  private void populatePendingList(List<BKTransMemberModel> records) {
		
		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"PENDING BOOK RECORDS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTransMemberModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getId());
			rowData.add(row.getName());
			rowData.add(row.getDept());
			rowData.add(row.getYear());
			rowData.add(DateHelper.format(row.getReturnDate()));
			rows.add(rowData);
		}

		plist = new JTable(rows,getDisplayColumns());
        plist.setBackground(Color.pink);
        plist.setEnabled (false);
        pnl.add(plist,BorderLayout.CENTER);
        JScrollPane spane =new JScrollPane(plist);
        pnl.add(spane,BorderLayout.CENTER);
        validate();
	}
     public static void main(String a[])
       {
         PendingList pl = new PendingList();
       }
 }
