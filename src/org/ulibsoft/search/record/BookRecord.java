package org.ulibsoft.search.record;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
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

import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.search.transaction.TransactionHistorySearchDAO;
import org.ulibsoft.model.BKTransMemberModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class BookRecord extends JFrame
  {
      /**
	 * 
	 */
	private static final long serialVersionUID = -7206007774957562022L;
	private JLabel code;
      private JTextField cd;
      private JButton find;
      private JTable bookdetails;
      private JPanel rtpn,p1;
      private JButton but1,but2,print1;
      private JLabel but0;
      private Container c;
      private JButton next,can;

      private TransactionHistorySearchDAO tranHistDao;
     

     public BookRecord()
       {
           super("BOOK  RECORD  FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
           tranHistDao = DAOFactory.getDAOFactory().getTranHistorySrchDAO();
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

         rtpn = new JPanel( new AbsoluteLayout() );
         rtpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "BOOK DETAILS", 1, 2, c.getFont(), Color.magenta));
         rtpn.setBackground(c.getBackground());
         c.add(rtpn,new AbsoluteConstraints(228,70+20,342,135));

         p1 = new JPanel( new BorderLayout() );
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROJECTION  OF  DATABASE", 2, 2,c.getFont(), Color.magenta));
         c.add(p1,new AbsoluteConstraints(90,215+30,600,300));

           but0 = new JLabel( "         SEARCH  BOOK  RECORD" ) ;
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


           code = new JLabel ( "ACCESS  NUMBER :" );
           code.setForeground ( new Color ( 120, 120, 153 ) );
           rtpn.add ( code, new AbsoluteConstraints (40,30 ) );

           cd = new JTextField( );
           cd.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
           cd.setForeground ( new Color ( 255, 0, 153 ) );
           cd.setCaretColor ( new Color ( 0, 204, 102 ) );
           rtpn.add ( cd, new AbsoluteConstraints ( 155, 28, 150, 20 ) );

           find = new JButton( "FIND" ) ;
           find.setBackground ( Color.cyan );
           find.setForeground( Color.black );
           find.setBorder( new BevelBorder( 0 ));
           rtpn.add ( find, new AbsoluteConstraints( 20,60,100,22 ) );

            print1 = new JButton( "PRINT" ) ;
            print1.setBackground (Color.cyan);
            print1.setForeground(Color.black);            
            print1.setBorder(new BevelBorder(0));
            print1.setMnemonic('P');

            
           rtpn.add ( print1, new AbsoluteConstraints( 60+10,90,100,22 ) );
                      
           next = new JButton( "NEXT>>>" ) ;
           next.setBackground ( Color.cyan);
           next.setForeground(Color.black);
           next.setMnemonic('N');
           next.setBorder(new BevelBorder(0));
           rtpn.add ( next, new AbsoluteConstraints( 122, 60, 100, 22 ) );

           can = new JButton( "EXIT" ) ;
           can.setBackground ( Color.cyan);
           can.setForeground( Color.black);
           can.setMnemonic('X');
           can.setBorder(new BevelBorder(0));
           rtpn.add ( can, new AbsoluteConstraints( 224, 60, 100, 22 ) );



           setVisible(true);
        }

    private void componentListener()
     {
         cd.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
            	 List<BKTransMemberModel> transMemMdls = tranHistDao.listPerBook(cd.getText());
            	 if ( transMemMdls != null && transMemMdls.size() > 0 )
            	 {
            		 populateList(transMemMdls);
            		 print1.setEnabled(true);
            	 }else
            		 JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .");                
             }
         }
         );
         find.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
            	 List<BKTransMemberModel> transMemMdls = tranHistDao.listPerBook(cd.getText());
            	 if ( transMemMdls != null && transMemMdls.size() > 0 )
            	 {
            		 populateList(transMemMdls);
            		 print1.setEnabled(true);
            	 }else
            		 JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .");                
             }
         }
         );
    
          next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                 cd.setText("");
                 print1.setEnabled(false);               
              }
          }
        );

         print1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     try
                     {
                       if(bookdetails!=null){
                       java.util.Date date=new java.util.Date();
                       boolean success= bookdetails.print( JTable.PrintMode.NORMAL,
                                                       new MessageFormat("BOOK-RECORD'S SEARCH ->"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                                       new MessageFormat("BOOK-RECORD'S SEARCH ->"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
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
           can.addActionListener( new ActionListener()
            {
            public void actionPerformed( ActionEvent e )
              {
                //JOptionPane.showMessageDialog(null,"afg");
                setVisible(false);
               }
          }
        );

     }
    private Vector<Object> getDisplayColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("ID");
		cols.add("Name");
		cols.add("Dept");
		cols.add("Year");
		cols.add("Issued Date");
		cols.add("Return Date");
		return cols;
	}
    private void populateList(List<BKTransMemberModel> records) {
	
    	if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"BOOK HISTORY NOT AVAILABLE", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTransMemberModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getId());
			rowData.add(row.getName());
			rowData.add(row.getDept());
			if ( "1".equals(row.getYear()) )
				rowData.add("");
			else
				rowData.add(row.getYear());
			rowData.add(DateHelper.format(row.getIssuedDate()));
			rowData.add(DateHelper.format(row.getReturnDate()));
			rows.add(rowData);
		}
		
		 bookdetails = new JTable(rows,getDisplayColumns());
         bookdetails.setBackground(Color.pink);
         bookdetails.setEnabled (false);
         p1.add(bookdetails,BorderLayout.CENTER);
         JScrollPane spane =new JScrollPane(bookdetails);
         p1.add(spane,BorderLayout.CENTER);
         validate();
	}
   

     public static void main( String a[] )
       {
          BookRecord bk = new BookRecord();
       }
  }
