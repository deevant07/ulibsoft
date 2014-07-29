package org.ulibsoft.returns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.ulibsoft.core.ui.MyImageIcon;
import org.ulibsoft.dao.catalog.BookCatalogDAO;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.dao.transaction.TransactionHistoryDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKTranStaffModel;
import org.ulibsoft.model.BKTransactionModel;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.model.TransactionHistoryModel;
import org.ulibsoft.register.ui.Library2;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class StaffRetrive extends JFrame
  {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6385468699917029400L;
	
	private static final Logger log = Logger.getLogger(StaffRetrive.class.getName());
	
	private JLabel code, lid, sname, dept, image;
    private JLabel bname, aname, idate, rdate ;
    private Icon icon;
    private JTextField cd, no, sn, dt,  bn, an, i1, r1 ;
    private JTable bookdetails, templibrary, bktransaction1 ;
    private JButton ret, can, next ;
    private JPanel p1,p2,p3,srpn;
    private Container c;
    private StaffRetrive staffret;

    private TransactionHistoryDAO transHstryDao;
    private BookCatalogDAO bkCatDao;
    private BookTransactionSearchDAO bkTransSrchDao;
    private BookTransactionDAO bkTranDao;        
    
    public StaffRetrive()
      {
         super("Retriving Books");
         staffret= this;
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         
         bkTransSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();         
         bkTranDao = DAOFactory.getDAOFactory().getBKTransDAO();
         bkCatDao = DAOFactory.getDAOFactory().getBookCatalogDAO();
         transHstryDao = DAOFactory.getDAOFactory().getTransHistoryDAO();
                  
         setVisible(true);
         createComponents();
         populateAvailableBookList();
         populateStaffsTransactedBookList();     
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
                                            "RETRIVING BOOKS", 1, 2, c.getFont(), Color.magenta));
         srpn.setBackground(c.getBackground());
         c.add(srpn,new AbsoluteConstraints(80,20,630,190));

         //INITIALIZE BOOK DETAILS

         code = new JLabel ( "ACCESS-NO. . ." );
         code.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( code, new AbsoluteConstraints ( 25, 21+5 ) );

         cd = new JTextField( );
         cd.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         cd.setForeground ( new Color ( 255, 0, 153 ) );
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( cd, new AbsoluteConstraints ( 120, 20+5, 130, 20 ) );

         bname = new JLabel("BOOK  NAME . .");
         bname.setForeground (new Color (120, 120, 153));
         srpn.add (bname, new AbsoluteConstraints (25, 51+5));

         bn = new JTextField( );
         bn.setEditable(false);
         bn.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         bn.setForeground ( new Color ( 255, 0, 153 ) );
         bn.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( bn, new AbsoluteConstraints ( 120, 50+5, 130, 20 ) );

         aname = new JLabel( "AUTHOR NAME" );
         aname.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( aname, new AbsoluteConstraints ( 25, 81+5 ) );

         an = new JTextField( );
         an.setEditable( false );
         an.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         an.setForeground ( new Color ( 255, 0, 153 ) );
         an.setCaretColor ( new Color (0, 204, 102 ) );
         srpn.add ( an, new AbsoluteConstraints ( 120, 80+5, 130, 20 ) );

         //INITIALIZE STUDENT DETAILS

         lid = new JLabel( "LECTURER ID . . ." );
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

         dept = new JLabel( "DEPARTMENT . ." );
         dept.setForeground ( new Color ( 120, 120, 153 ) );
         srpn.add ( dept, new AbsoluteConstraints (370, 82+5 ) );

         dt = new JTextField( );
         dt.setEditable( false );
         dt.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         dt.setForeground ( new Color ( 255, 0, 153 ) );
         dt.setCaretColor ( new Color ( 0, 204, 102 ) );
         srpn.add ( dt, new AbsoluteConstraints ( 480, 80+5, 130, 20 ) );

         //INITTALIZE DATE DETAILS

         idate = new JLabel( "ISSUE   DATE . ." );
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
                                            "BOOK  DETAILS", 1, 2,c.getFont(),Color.magenta));
         c.add(p1,new AbsoluteConstraints(15, 220, 480, 110));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT TRANSACTION", 1, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(30,340,730,210));

         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE  BOOKS", 1, 2, c.getFont(), Color.magenta));
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
                   //p.setFrame(staffret);//Index.sr);
                 }
              }
          }
        );

        cd.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
            	BKTranStaffModel bkTranStfMdl = bkTransSrchDao.findStaffTransactedBook(cd.getText());
            	if ( bkTranStfMdl != null )
            	{
            		no.setText(bkTranStfMdl.getStaffModel().getId());
            		sn.setText(bkTranStfMdl.getStaffModel().getName());
            		dt.setText(bkTranStfMdl.getStaffModel().getDept());
            		bn.setText(bkTranStfMdl.getBookModel().getBookName());
            		an.setText(bkTranStfMdl.getBookModel().getAuthor1Surname() + " "+ bkTranStfMdl.getBookModel().getAuthor1Name());
            		i1.setText(DateHelper.format(bkTranStfMdl.getBkTransModel().getIssuedDate()));
            		r1.setText(DateHelper.format(bkTranStfMdl.getBkTransModel().getReturnDate()));
            		
            		byte[] imageByte = bkTranStfMdl.getStaffModel().getImage();
		    		if ( imageByte != null )
		    		{
		    			icon = new MyImageIcon(bkTranStfMdl.getStaffModel().getImage());
		    			image.setIcon(icon);
		    		}else
		    			image.setIcon(null);
		    		
            		PassWordDialog PWD=new PassWordDialog(staffret,"PASSWORD CONFIRMATION");
            		ret.setEnabled(true);
            	}                
              }
          }
        );

        ret.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {           	
            	BKTransactionModel transMdl = new BKTransactionModel();
            	transMdl.setCode(cd.getText());
            	bkTranDao.deleteStaff(transMdl);
            	
            	TransactionHistoryModel inputCriteria = new TransactionHistoryModel();
            	inputCriteria.setCode(cd.getText());
            	try {
					inputCriteria.setIssuedDate(DateHelper.parse(i1.getText()));
				} catch (ParseException e1) {
					log.error("Parse Exception on return button click",e1);
				}
            	
            	TransactionHistoryModel transHstry = transHstryDao.findBkRecord(inputCriteria);
            	if ( transHstry != null )
            	{
            		transHstry.setReturnDate(DateHelper.getCurrentDate());
            		if ( transHstryDao.updateBkHistory(transHstry) > 0 )
            			log.info("Update reuturn date to transaction history is successful");
            		else
            			log.warn("Update reuturn date to transaction history is failed");
            	}else 
            		log.warn("Record not found in transaction history table for code: "+cd.getText());
            	
            	BookModel bkMdl = bkCatDao.findByAccessNo(cd.getText());
                if ( bkMdl != null && "1".equals(bkMdl.getIssued()))
                {
             	   NewDialog nop=new NewDialog((Frame)staffret,"CONFIRMATION" );
                }
                populateAvailableBookList();
                populateStaffsTransactedBookList();
                populatestaffPerBook( cd.getText() );               
              }
          }
        );

        next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                //RESET BOOK VALUES
                  cd.setText("");
                  bn.setText("");
                  an.setText("");

                //RESET STAFF VALUES
                  no.setText("");
                  sn.setText("");
                  dt.setText("");
                  image.setIcon (null);

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
         /**
		 * 
		 */
		private static final long serialVersionUID = -4210877869492907264L;
		private JPasswordField pwdfd;
         private JLabel pwdlb;
         private JButton sub;
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
                	   AuthDAO authDao = DAOFactory.getDAOFactory().getAuthDAO();
                	   if ( authDao.authenticateStaffUser(no.getText(), new String( pwdfd.getPassword() )))
                	   {
                		   ret.setEnabled (true);
                           setVisible( false ); 
                	   }
                	   else
                       {
                          JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                       }                        
                    }
                }
              );
             setBounds(250,250,270,120);
             setVisible(true);
           }
      }

  private class NewDialog extends JDialog
  {
        /**
	 * 
	 */
	private static final long serialVersionUID = 5198981222939178562L;
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
                Library2 cd2=new Library2();
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
  private Vector<Object> getBooksPerStudentColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("Professor ID");
		cols.add("Professor Name");
		cols.add("Department");
		cols.add("Issued Date");
		cols.add("Return Date");		
		return cols;
	}
  	private void populatestaffPerBook(String bkCode) {
		List<BKTranStaffModel> records = bkTransSrchDao.findStaffsPerBook(bkCode);

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"STAFF BOOK RECORDS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTranStaffModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getStaffModel().getId());
			rowData.add(row.getStaffModel().getName());
			rowData.add(row.getStaffModel().getDept());
			rowData.add(DateHelper.format(row.getBkTransModel().getIssuedDate()));
			rowData.add(DateHelper.format(row.getBkTransModel().getReturnDate()));
			
			rows.add(rowData);
		}	
		bookdetails = new JTable(rows,getBooksPerStudentColumns());
        bookdetails.setBackground(Color.cyan);
        bookdetails.setEnabled(false);
        p1.add(bookdetails,BorderLayout.CENTER);
        JScrollPane sp3 = new JScrollPane(bookdetails);
        p1.add(sp3,BorderLayout.CENTER);
        validate();		    
  	}   
    private Vector<Object> getTransactedBookDisplayColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("Access No");
		cols.add("Book Name");
		cols.add("Author");
		cols.add("Professor ID");
		cols.add("Professor Name");
		cols.add("Dept");		
		cols.add("Issued Date");
		cols.add("Return Date");		
		return cols;
	}
    private void populateStaffsTransactedBookList() {
		List<BKTranStaffModel> records = bkTransSrchDao.findStaffsTransactedBooks();

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"CURRENT TRANSACTIONS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTranStaffModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getBookModel().getAccessNo());
			rowData.add(row.getBookModel().getBookName());
			rowData.add(row.getBookModel().getAuthor1Surname() + " " + row.getBookModel().getAuthor1Name() );
			rowData.add(row.getStaffModel().getId());
			rowData.add(row.getStaffModel().getName());
			rowData.add(row.getStaffModel().getDept());			
			rowData.add(DateHelper.format(row.getBkTransModel().getIssuedDate()));
			rowData.add(DateHelper.format(row.getBkTransModel().getReturnDate()));
			
			rows.add(rowData);
		}
		
		bktransaction1 = new JTable( rows, getTransactedBookDisplayColumns() );
		bktransaction1.setBackground(Color.pink);
        bktransaction1.setEnabled(false);
        p2.add(bktransaction1,BorderLayout.CENTER);
        JScrollPane sp1 = new JScrollPane(bktransaction1);
        p2.add(sp1,BorderLayout.CENTER);
        validate();
    }    
    private Vector<Object> getAvailableBookDisplayColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("Access No");
		cols.add("Book Name");
		cols.add("Author");
		return cols;
    }
    private void populateAvailableBookList() {
		List<BookModel> records = bkTransSrchDao.availableBooks();

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"AVAILABLE BOOKS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BookModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getAccessNo());
			rowData.add(row.getBookName());
			rowData.add(row.getAuthor1Surname() + " " + row.getAuthor1Name() );
			
			rows.add(rowData);
		}
		templibrary = new JTable(rows,getAvailableBookDisplayColumns());
		templibrary.setBackground(Color.cyan);
        templibrary.setEnabled(false);
        p3.add(templibrary,BorderLayout.CENTER);
        JScrollPane sp2 = new JScrollPane(templibrary);
        p3.add(sp2,BorderLayout.CENTER);
        validate();		
    }   
  
   private class myadapter5 extends WindowAdapter
     {
       public void windowClosing(WindowEvent wt)
         {           
            setVisible(false);
            dispose();
         }
     }
     public static void main(String a[])
       {
         StaffRetrive rt=new StaffRetrive( );
       }
  }