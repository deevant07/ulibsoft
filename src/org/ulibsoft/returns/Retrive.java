package org.ulibsoft.returns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.ulibsoft.core.ui.MyImageIcon;
import org.ulibsoft.dao.catalog.BookCatalogDAO;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.dao.transaction.TransactionHistoryDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKTranStudModel;
import org.ulibsoft.model.BKTransactionModel;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.model.KeyConstraints;
import org.ulibsoft.model.StudentModel;
import org.ulibsoft.model.TransactionHistoryModel;
import org.ulibsoft.register.ui.Library2;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;
import org.ulibsoft.util.datatype.NumberUtils;

public class Retrive extends JFrame
  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6365827017823908088L;

	private static final Logger log = Logger.getLogger(Retrive.class.getName());
	
    private JLabel code, adno, sname, sbranch, syear, image, fine;
    private Icon icon,icon1;
    private JLabel bname, aname, idate, rdate ;
    private JTextField cd, ano, sn, sb, sy, bn, an, i1, r1,f1 ;
    private JTable bookdetails, templibrary, bktransaction ;
    private JButton ret, pl, can, next, ren ,finebtn, exc, pay;
    private JPanel rtpn,p1,p2,p3,finepnl;
    private JTextArea fineArea;
    private Container c;

    private FileOutputStream fout,fout1;
    private String fp,fp1;
    private static int fineValue1;

    private boolean confirm=false;

    private static Retrive parent;
   
    private TransactionHistoryDAO transHstryDao;
    private BookCatalogDAO bkCatDao;
    private BookTransactionSearchDAO bkTransSrchDao;
    private BookTransactionDAO bkTranDao;
    private KeyConstraintDAO keyCnstrntDao;
    private KeyConstraints keyConstraints;
    
    public Retrive()
      {
         super("Retriving Books");
         ScreenResolution.getResolution();
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         setVisible( true);
         bkTransSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();
         keyCnstrntDao = DAOFactory.getDAOFactory().getKeyCnstrntDAO();
         bkTranDao = DAOFactory.getDAOFactory().getBKTransDAO();
         bkCatDao = DAOFactory.getDAOFactory().getBookCatalogDAO();
         transHstryDao = DAOFactory.getDAOFactory().getTransHistoryDAO();
         
         keyConstraints = keyCnstrntDao.findById((short)1); 
         createComponents();
         populateAvailableBookList();
         populateStudentsTransactedBookList();              
         componentListener();
         myadapter5 myp=new myadapter5();
         addWindowListener(myp);
      }
    private void createComponents()
      {
         c = getContentPane();
         c.setLayout( new AbsoluteLayout() );
         c.setBackground( new Color( 0, 0, 40 ) );

         rtpn = new JPanel( new AbsoluteLayout() );
         rtpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "RETRIVE -- BOOKS", 1, 2, c.getFont(), Color.magenta));
         rtpn.setBackground(c.getBackground());
         c.add(rtpn,new AbsoluteConstraints(85,20,630,195));

         finepnl = new JPanel( new BorderLayout() );
         finepnl.setLayout(new AbsoluteLayout());
         finepnl.setBackground(c.getBackground());
         finepnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "FINE -- ACCESS", 1, 2,c.getFont(), Color.magenta));
         finepnl.setVisible(false);
         c.add(finepnl,new AbsoluteConstraints(150, 220, 500, 120));

         //INITIALIZE BOOK DETAILS

         code = new JLabel ( "ACCESS-NO " );
         code.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( code, new AbsoluteConstraints (42, 23 ) );

         cd = new JTextField( );
         cd.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color( 0, 0, 40 ) ));
         cd.setForeground ( new Color ( 255, 0, 153 ) );
         cd.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( cd, new AbsoluteConstraints ( 120, 20, 125, 20 ) );

         bname = new JLabel("TITLE");
         bname.setForeground (new Color (120, 120, 153));
         rtpn.add (bname, new AbsoluteConstraints( 79, 50));

         bn = new JTextField( );
         bn.setEditable(false);
         bn.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40)) );
         bn.setForeground ( new Color ( 255, 0, 153 ) );
         bn.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( bn, new AbsoluteConstraints ( 120, 48, 125, 20 ) );

         aname = new JLabel( "AUTHOR-NAME" );
         aname.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( aname, new AbsoluteConstraints ( 25, 77 ) );

         an = new JTextField( );
         an.setEditable( false );
         an.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40)) );
         an.setForeground ( new Color ( 255, 0, 153 ) );
         an.setCaretColor ( new Color (0, 204, 102 ) );
         rtpn.add ( an, new AbsoluteConstraints ( 120, 76, 125, 20 ) );

         //INITIALIZE STUDENT DETAILS

         adno = new JLabel( "ADMISSION-NO" );
         adno.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( adno, new AbsoluteConstraints ( 395, 20 ) );

         ano = new JTextField( );
         ano.setEditable( false );
         ano.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ));
         ano.setForeground ( new Color ( 255, 0, 153 ) );
         ano.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( ano, new AbsoluteConstraints ( 490, 23, 125, 20 ) );

         image = new JLabel();
         image.setBounds(260,20,100,110);
         image.setBorder ( new MatteBorder ( 1, 1, 1, 1, Color.cyan ));
         rtpn.add(image,new AbsoluteConstraints(260,20,100,110));

         icon1=new ImageIcon("empty.jpg");

         sname = new JLabel( "STUDENT-NAME" );
         sname.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( sname, new AbsoluteConstraints ( 390, 51 ) );

         sn = new JTextField( );
         sn.setEditable( false );
         sn.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         sn.setForeground ( new Color ( 255, 0, 153 ) );
         sn.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( sn, new AbsoluteConstraints ( 490, 50,125, 20 ) );

         sbranch = new JLabel( " COURSE" );
         sbranch.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( sbranch, new AbsoluteConstraints (430,77 ) );

         sb = new JTextField( );
         sb.setEditable( false );
         sb.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40)) );
         sb.setForeground ( new Color ( 255, 0, 153 ) );
         sb.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( sb, new AbsoluteConstraints ( 490, 76, 125, 20 ) );

         syear = new JLabel( "SEMESTER" );
         syear.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( syear, new AbsoluteConstraints ( 419, 103 ) );

         sy = new JTextField( );
         sy.setEditable( false );
         sy.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         sy.setForeground ( new Color ( 255, 0, 153 ) );
         sy.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( sy, new AbsoluteConstraints ( 490, 102, 125, 20 ) );

         //INITTALIZE DATE DETAILS

         idate = new JLabel( "ISSUE-DATE" );
         idate.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( idate, new AbsoluteConstraints ( 40, 103 ) );

         i1 = new JTextField( );
         i1.setEditable( false );
         i1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         i1.setForeground ( new Color ( 255, 0, 153 ) );
         i1.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( i1, new AbsoluteConstraints ( 120, 102, 125, 20 ) );

         rdate = new JLabel( "RECEIVE-DATE" );
         rdate.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( rdate, new AbsoluteConstraints ( 25, 131 ) );

         r1 = new JTextField( );
         r1.setEditable( false );
         r1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40)) );
         r1.setForeground ( new Color ( 255, 0, 153 ) );
         r1.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( r1, new AbsoluteConstraints ( 120, 130, 125, 20 ) );


         fine = new JLabel( "FINE" );
         fine.setForeground ( new Color ( 120, 120, 153 ) );
         rtpn.add ( fine, new AbsoluteConstraints ( 456, 131 ) );

         f1 = new JTextField( );
         f1.setEditable( false );
         f1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40)) );
         f1.setForeground ( new Color ( 255, 0, 153 ) );
         f1.setCaretColor ( new Color ( 0, 204, 102 ) );
         rtpn.add ( f1, new AbsoluteConstraints ( 490, 130, 125, 20 ) );

         //BUTTON CREATION

         ret = new JButton( "RECIEVE" );
         ret.setMnemonic ('R');
         ret.setBackground ( Color.cyan );
         ret.setForeground(Color.black);
         ret.setBorder(new BevelBorder(0));
         ret.setEnabled (false);
         rtpn.add ( ret, new AbsoluteConstraints ( 60, 160, 100, 22 ) );

         ren = new JButton( "RENIVAL" );
         ren.setMnemonic ('E');
         ren.setBackground ( Color.cyan );
         ren.setForeground(Color.black);
         ren.setBorder(new BevelBorder(0));
         ren.setEnabled (false);
         rtpn.add ( ren, new AbsoluteConstraints ( 162, 160, 100, 22 ) );

         next = new JButton( "NEXT>>>" );
         next.setMnemonic ('N');
         next.setBackground ( Color.cyan  );
         next.setForeground ( Color.black );
         next.setBorder ( new BevelBorder(0) );
         rtpn.add ( next, new AbsoluteConstraints ( 264, 160, 100, 22 ) );

         pl = new JButton( "PLIST" );
         pl.setMnemonic ('P');
         pl.setBackground ( Color.cyan );
         pl.setForeground ( Color.black );
         pl.setBorder ( new BevelBorder(0) );
         pl.setEnabled(false);
         rtpn.add ( pl, new AbsoluteConstraints ( 366, 160, 100, 22 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         rtpn.add ( can, new AbsoluteConstraints( 468, 160, 100, 22 ) );

         finebtn = new JButton("FINE");
         finebtn.setBackground ( Color.cyan);
         finebtn.setForeground( Color.black);
         finebtn.setMnemonic('E');
         finebtn.setBorder(new BevelBorder(0));
         finebtn.setVisible(false);
         //rtpn.add ( finebtn, new AbsoluteConstraints( 366, 160, 100, 22 ) );

         exc = new JButton("EXCUSE");
         exc.setBackground ( Color.cyan);
         exc.setForeground( Color.black);
         exc.setMnemonic('U');
         exc.setBorder(new BevelBorder(0));
         exc.setVisible(false);
         rtpn.add ( exc, new AbsoluteConstraints( 162, 160, 100, 22 ) );

         pay = new JButton("PAY");
         pay.setBackground ( Color.cyan);
         pay.setForeground( Color.black);
         pay.setMnemonic('Y');
         pay.setBorder(new BevelBorder(0));
         pay.setVisible(false);
         rtpn.add ( pay, new AbsoluteConstraints( 60, 160, 100, 22 ) );

         //TEXTAREA CREATION

         fineArea = new JTextArea();
         fineArea.setEditable(true);
         fineArea.setFont(new Font("Monotype Corsiva",Font.ITALIC,18));
         finepnl.add(new JScrollPane(fineArea),new AbsoluteConstraints(0,0,500,120));

         p1 = new JPanel( new BorderLayout() );
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "BOOK -- DATABASE", 1, 2,c.getFont(), Color.magenta));
         c.add(p1,new AbsoluteConstraints(15, 220, 480, 120));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT -- TRANSACTION", 1, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(30,350,730,200));

         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE BOOKS", 1, 2,c.getFont(), Color.magenta));
         c.add(p3,new AbsoluteConstraints(510,220,270,120));

         try
             {
                fout=new FileOutputStream("c:\\windows\\report37.html");
             }catch(Exception se)
             {
             }
            fp="<HTML>";
            fp+="<BODY BGCOLOR=#f95360><FONT COLOR="+"CYAN"+" SIZE="+"5"+"><CENTER>FINE RECIPT FORM</CENTER></BODY>";
            fp+="<TABLE ALIGN="+" CENTER "+" BORDER="+"1 "+" WIDTH="+"400 "+" CELLSPACING="+" 0 >";
            fp+="<CAPTION>FINE AMOUNT REPORT</CAPTION>";
            fp+="<BR><TR BGCOLOR="+"#AADD00"+">";
             try
             {
                fout1=new FileOutputStream("c:\\windows\\report38.html");
             }catch(Exception se)
             {
             }

            fp1="<HTML>";
            fp1+="<BODY BGCOLOR=#f95360><FONT COLOR="+"CYAN"+" SIZE="+"5"+"><CENTER>FINE RECIPT FORM</CENTER></BODY>";
            fp1+="<TABLE ALIGN="+" CENTER "+" BORDER="+"1 "+" WIDTH="+"400 "+" CELLSPACING="+" 0 >";
            fp1+="<CAPTION>FINE AMOUNT REPORT</CAPTION>";
            fp1+="<BR><TR BGCOLOR="+"#AADD00"+">";

         setVisible(true);
     }



   private void componentListener()
     {
        cd.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
				BKTranStudModel bkTranStdMdl = bkTransSrchDao.findStudentsTransactedBook(cd.getText());
				
				if (bkTranStdMdl != null) {
					// SET TRANSACTION DETAILS
					StudentModel stdMdl = bkTranStdMdl.getStudentModel();
					ano.setText(stdMdl.getId());
					sn.setText(stdMdl.getName());
					sb.setText(stdMdl.getBranch());
					sy.setText(stdMdl.getYear());
					bn.setText(bkTranStdMdl.getBookModel().getBookName());
					an.setText(bkTranStdMdl.getBookModel().getAuthor1Surname()
							+ " "
							+ bkTranStdMdl.getBookModel().getAuthor1Name());
					i1.setText(DateHelper.format(bkTranStdMdl.getBkTransModel()
							.getIssuedDate()));
					r1.setText(DateHelper.format(bkTranStdMdl.getBkTransModel()
							.getReturnDate()));
					
					byte[] imageByte = stdMdl.getImage();
		    		if ( imageByte != null )
		    		{
		    			icon = new MyImageIcon(stdMdl.getImage());
		    			image.setIcon(icon);
		    		}else
		    			image.setIcon(null);	
		    		
					PassWordDialog PWD = new PassWordDialog(parent,
							"PASSWORD CONFIRMATION");
					r1.setEditable(true);
					
								
				}
				else {
					JOptionPane.showMessageDialog(null,
							"BOOK'S RECORD NOT FOUND", "CURRENT TRANSACTIONS",
							JOptionPane.INFORMATION_MESSAGE);
					ret.setEnabled(false);
					ren.setEnabled(false);
					pl.setEnabled(false);
				}                
              }
          }
        );

        ret.addActionListener( new ActionListener()
          {
            public void actionPerformed(ActionEvent e)
              {
                
            	Date rDate = null;
				try {
					rDate = DateHelper.parse(r1.getText());
				} catch (ParseException e1) {
					log.error("Parse Exception on return button click",e1);
				}
            	int diff = DateHelper.compareDates(rDate, DateHelper.getCurrentDate());
                 
                
                if( diff >= 0 )
                {                 	
                	BKTransactionModel transMdl = new BKTransactionModel();
                	transMdl.setCode(cd.getText());
                	bkTranDao.deleteStudent(transMdl);
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
                    populateStudentsTransactedBookList();
                    populateStudentsPerBook( cd.getText() );
                    populateAvailableBookList();
                    BookModel bkMdl = bkCatDao.findByAccessNo(cd.getText());
                    if ( bkMdl != null && "1".equals(bkMdl.getIssued()))
                    {
                    	NewDialog nop=new NewDialog((Frame)parent,"CONFIRMATION" );
                    }                        
                }
               else
                {
                  JOptionPane.showMessageDialog(null,"ILLEGAL RETRIVING !...","Message",JOptionPane.INFORMATION_MESSAGE);
                }                  
              }
          }
        );

        ren.addActionListener(new ActionListener()
          {
             public void actionPerformed(ActionEvent e)
               {
                 ret.setEnabled(false);
                 pl.setEnabled(false);
                 BKTranStudModel bkTranStd = bkTransSrchDao.findStudentsTransactedBook(cd.getText());
                 if(bkTranStd.getBkTransModel().getReneivalCount() < keyConstraints.getMaxReneivelPerBook())
                 {                 
            		 Date newDate = DateHelper.addDaysToDate(bkTranStd.getBkTransModel().getReturnDate(), keyConstraints.getMaxDaysPerBook());
            		 int newReneivalCnt = bkTranStd.getBkTransModel().getReneivalCount() + 1;
            		 bkTranStd.getBkTransModel().setReturnDate(newDate);
            		 bkTranStd.getBkTransModel().setReneivalCount(newReneivalCnt);
            		 if ( bkTranDao.updateStudent(bkTranStd.getBkTransModel()) > 0 )
            		 {
            			 log.info("Book transaction updated successfully");
            			 r1.setText(DateHelper.format(newDate));
            		 }
            		 else
            			 log.warn("Book transaction update failed");
            		 populateStudentsTransactedBookList();
                 }else
                 {
                     JOptionPane.showMessageDialog(null,"RENEIVAL TIMES EXCEEDED");
                 }               
                 ren.setEnabled(false);
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
                  f1.setText("");
                  fineArea.setText("");                  

                //RESET STUDENT VALUES
                  ano.setText("");
                  sn.setText("");
                  sb.setText("");
                  sy.setText("");
                  image.setIcon (icon1);

                //RESET DATE VALUES
                  i1.setText("");
                  r1.setText("");
                  r1.setEditable(false);

                  pay.setVisible(false);
                  exc.setVisible(false);
                  ret.setVisible(true);
                  ren.setVisible(true);
                  ret.setEnabled (false);
                  ren.setEnabled(false);
                  pl.setEnabled (false);
                  finepnl.setVisible(false);
                  p1.setVisible(true);
                  p3.setVisible(true);
              }
          }
        );

		pl.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				finepnl.setVisible(false);
				p1.setVisible(true);
				p3.setVisible(true);					
				Date rDate = null;
				try {
					rDate = DateHelper.parse(r1.getText());
				} catch (ParseException e1) {
					log.error("Parse Exception on return button click", e1);
				}
				int diff = DateHelper.compareDates(rDate,DateHelper.getCurrentDate());

				// rs =
				// s.executeQuery("SELECT TO_DATE('"+r1.getText()+"') -  TO_DATE(SYSDATE) FROM DUAL");
				if (diff < 0) {					
					BKTransactionModel transMdl = new BKTransactionModel();
					transMdl.setCode(cd.getText());
					bkTranDao.deleteStudent(transMdl);

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
                	
                	populateAvailableBookList();
					populateStudentsTransactedBookList();
					populateStudentsPerBook(cd.getText());
					BookModel bkMdl = bkCatDao.findByAccessNo(cd.getText());
					if (bkMdl != null && "1".equals(bkMdl.getIssued())) {
						NewDialog nop = new NewDialog((Frame) parent,
								"CONFIRMATION");
					}
				} else {
					JOptionPane.showMessageDialog(null,	"EMPTY PENDING LIST !...", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}					
				
				pay.setEnabled(false);
				exc.setEnabled(false);
				pl.setEnabled(false);
			}
		});
        pay.addActionListener(new ActionListener()
          {
              public void actionPerformed(ActionEvent e)
                {
                  int sd=JOptionPane.showConfirmDialog(null,"ARE U SURE HE/SHE HAS PAID ! . . .","CONFIRMATION",JOptionPane.YES_NO_OPTION);
                  if(sd==0)
                   {
                      ConfirmDialog lk=new ConfirmDialog((Frame)parent,"CONFIRMATION");
                   }
                }
           }
        );
        exc.addActionListener(new ActionListener()
          {
              public void actionPerformed(ActionEvent e)
                {
                    ConfirmDialog2 lkp=new ConfirmDialog2((Frame)parent,"CONFIRMATION");

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

        addMouseListener( new MouseAdapter()
          {
             public void mouseClicked(MouseEvent e)
               {
                 if (e.isMetaDown())
                   {
                     PopUpMenu p = new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                    // p.setFrame(Index.rt);
                   }
               }
          }
        );
     }

   private class ConfirmDialog extends JDialog
     {
         private JLabel pass;
         private JPasswordField pwd;
         private JButton print;
         private Container k;
         private final int fine=Integer.parseInt(f1.getText());
         public ConfirmDialog(Frame par,String title)
           {

               k=getContentPane();
               getContentPane().setLayout(new AbsoluteLayout());
               getContentPane().setBackground(new Color(120,120,180));
               getContentPane().add(pass=new JLabel("PASSWORD :"),new AbsoluteConstraints(30,22));
               pass.setForeground( Color.white );
               getContentPane().add(pwd=new JPasswordField(),new AbsoluteConstraints(120,20,130,20));
               pwd.setBorder ( new MatteBorder ( 1, 1, 1, 1, getContentPane().getBackground() ));

               print = new JButton( "PRINT_RECIPT " ) ;
               print.setBackground (Color.cyan);
               print.setForeground(Color.black);
               print.setEnabled(false);
               print.setBorder(new BevelBorder(0));
               print.setMnemonic('P');
               getContentPane().add(print,new AbsoluteConstraints(80,50,140,25));

               pwd.addActionListener(new ActionListener()
                 {
                     public void actionPerformed(ActionEvent e2)
                       {
                           if( new String(pwd.getPassword()).equals("SUBMITTED"))
                           {
                               confirm=true;
                               if( confirm == true )
                                {
                            	   
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
	                               		transHstry.setFineAmount(NumberUtils.toFloat(f1.getText()));
	                               		transHstry.setFinePaid(NumberUtils.toFloat(f1.getText()));
	                               		transHstry.setReason(fineArea.getText());
	                               		if ( transHstryDao.updateBkHistory(transHstry) > 0 )
	                               			log.info("Update fine details  to transaction history is successful");
	                               		else
	                               			log.warn("Update fine details to transaction history is failed");
	                               	}else 
	                               		log.warn("Record not found in transaction history table for code: "+cd.getText());                               	                              
	                                        
	                                f1.setText(String.valueOf(0));
	                                fineArea.setText("");                                
	                                pl.setEnabled(true);                                
	                                print.setEnabled(true);
	                                //setVisible(false);                                     
                                 }
                           }
                           else
                           {
                               confirm=false;
                               JOptionPane.showMessageDialog(null,"INVALID PASSWORD !. . . ");
                           }
                       }

                 }
               );
               print.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     fp+="<TH>"+"STUDENT"+"</TH>";
                     fp+="<TH>"+"FINE AMOUNT"+"</TH></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">ADNO : "+ano.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+">RS "+fine+"/- PAID</TD></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">NAME : "+sn.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">COURSE : "+sb.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp+="<TR><TD BGCOLOR="+"#aadd00"+">YEAR : "+sy.getText( )+"</TD>";
                     fp+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp+="</TABLE></BODY></HTML>";
                     try
                       {
                          fout.write(fp.getBytes());
                       }catch(Exception se){ }

                     Runtime rt = Runtime.getRuntime();
                     try
                       {
                          rt.exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE c:\\windows\\report37.html");
                       }
                     catch(IOException ex)
                       {
                       }
                     setVisible(false);
                 }
            }
          );
          addWindowListener(new WindowAdapter()
            {
               public void windowClosing(WindowEvent e)
                 {
                    JOptionPane.showMessageDialog(null,"U MUST PRINT RECEIPT  !  .  .  .");
                    new ConfirmDialog((Frame)parent,"CONFIRMATION");
                 }
            }
          );
               setBounds(250, 250, 300, 110);
               setVisible(true);
           }
     }
    private class ConfirmDialog2 extends JDialog
     {

         private JLabel pass,amt;
         private JTextField amt1;
         private JPasswordField pwd;
         private JButton sumit,rest1;
         private Container k;
         private final int fine=Integer.parseInt(f1.getText());
         public ConfirmDialog2(Frame par,String title)
           {
               //fineValue=Integer.parseInt(f1.getText());
               k=getContentPane();
               getContentPane().setLayout(new AbsoluteLayout());
               getContentPane().setBackground(new Color(120,120,180));
               getContentPane().add(amt=new JLabel("FINE AMOUNT :"),new AbsoluteConstraints(30,30));
               getContentPane().add(pass=new JLabel("PASSWORD :"),new AbsoluteConstraints(30,60));
               pass.setForeground( Color.white );
               amt.setForeground( Color.white );
               getContentPane().add(amt1=new JTextField(),new AbsoluteConstraints(120,28,130,20));
               getContentPane().add(pwd=new JPasswordField(),new AbsoluteConstraints(120,58,130,20));
               //pass.setVisible(false);
               //pwd.setVisible(false);
               getContentPane().add(sumit=new JButton("SUBMIT"),new AbsoluteConstraints(40,95,100,25));
               sumit.setMnemonic('S');
               sumit.setBackground ( Color.cyan  );
               sumit.setForeground ( Color.black );
               sumit.setBorder ( new BevelBorder(0) );
               getContentPane().add(rest1=new JButton("PRINT_RECEIPT"),new AbsoluteConstraints(145,95,100,25));
               rest1.setMnemonic('P');
               rest1.setBackground ( Color.cyan  );
               rest1.setEnabled(false);
               rest1.setForeground ( Color.black );
               rest1.setBorder ( new BevelBorder(0) );

               amt1.setText(f1.getText());
               amt1.addActionListener(new ActionListener()
                 {
                     public void actionPerformed(ActionEvent e)
                       {
                          // pass.setVisible(true);
                          // pwd.setVisible(true);
                       }
                 }
               );
               sumit.addActionListener(new ActionListener()
                 {
                     public void actionPerformed(ActionEvent e2)
                       {
                           if( new String(pwd.getPassword()).equals("SUBMITTED"))
                           {
                               confirm=true;
                               if( confirm == true )
                                {
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
	                               		transHstry.setFineAmount(NumberUtils.toFloat(f1.getText()));
	                               		transHstry.setFinePaid(NumberUtils.toFloat(f1.getText()));
	                               		transHstry.setReason(fineArea.getText());
	                               		if ( transHstryDao.updateBkHistory(transHstry) > 0 )
	                               			log.info("Update reuturn date to transaction history is successful");
	                               		else
	                               			log.warn("Update reuturn date to transaction history is failed");
	                               	}else 
	                               		log.warn("Record not found in transaction history table for code: "+cd.getText()); 
	                               	
	                               	f1.setText(amt1.getText());
                                    fineArea.setText("");
                                    
                                    pl.setEnabled(true);
                                    finepnl.setVisible(false);
                                    p1.setVisible(true);
                                    p3.setVisible(true);
                                    pay.setEnabled(false);
                                    exc.setEnabled(false);                                    
                                    rest1.setEnabled(true);
                                   // setVisible(false);                                   
                                 }
                           }
                           else
                           {
                               confirm=false;
                               JOptionPane.showMessageDialog(null,"INVALID PASSWORD !. . . ");

                           }
                       }

                 }
               );
                rest1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     fineValue1=Integer.parseInt(f1.getText());

                     fp1+="<TH>"+"STUDENT"+"</TH>";
                     fp1+="<TH>"+"FINE AMOUNT"+"</TH></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">ADNO : "+ano.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+">TOTAL AMOUNT RS "+fine+"/- </TD></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">NAME : "+sn.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+">PAID AMOUNT RS "+fineValue1+"/- </TD></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">COURSE : "+sb.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp1+="<TR><TD BGCOLOR="+"#aadd00"+">YEAR : "+sy.getText( )+"</TD>";
                     fp1+="<TD BGCOLOR="+"#aadd00"+"></TD></TR>";
                     fp1+="</TABLE></BODY></HTML>";
                     try
                       {
                          fout1.write(fp1.getBytes());
                       }catch(Exception se){ }

                     Runtime rt = Runtime.getRuntime();
                     try
                       {
                          rt.exec("C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE c:\\windows\\report38.html");
                       }
                     catch(IOException ex)
                       {
                       }
                     setVisible(false);
                 }
            }
          );
          addWindowListener(new WindowAdapter()
            {
               public void windowClosing(WindowEvent e)
                 {
                    JOptionPane.showMessageDialog(null,"U MUST PRINT RECEIPT  !  .  .  .");
                    new ConfirmDialog2((Frame)parent,"CONFIRMATION");
                 }
            }
          );
               setBounds(250, 250, 300, 170);
               setVisible(true);
           }
     }

    private class PassWordDialog extends JDialog
      {
         private JPasswordField pwdfd;
         private PassWordDialog parent1;
         private JLabel pwdlb;
         private JButton sub,newbtn,sub1;
         private PassWordDialog parent2;
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
                	   if ( authDao.authenticateStudentUser(ano.getText(), new String( pwdfd.getPassword() )))
                	   {
                		   ret.setEnabled (true);
                           ren.setEnabled(true);
                           setVisible( false );
                           calfine();
                	   }else
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
    private void calfine()
    {
    	int diff = 0;
    	try {
			 diff = DateHelper.differenceFromCurrent(r1.getText());
		} catch (ParseException e) {			
			e.printStackTrace();
		}    	   	
    	if ( diff > 0 )
    	{
    		int finAmt = diff * keyConstraints.getFinePerBook();
    		f1.setText( String.valueOf(finAmt) );
            p1.setVisible( false );
            p3.setVisible( false );
            finepnl.setVisible( true );
            ret.setVisible(false);
            ren.setVisible(false);
            exc.setVisible(true);
            pay.setVisible(true);
            pay.setEnabled(true);
            exc.setEnabled(true);
    	}
    	else
        {
          f1.setText("0");
          p1.setVisible(true);
          p3.setVisible(true);
          finepnl.setVisible(false);
        }
     }
    private Vector<Object> getStudentsPerBookColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("Ad No");
		cols.add("Name");
		cols.add("Branch");
		cols.add("Year");
		cols.add("Issued Date");
		cols.add("Return Date");		
		return cols;
	}
    private void populateStudentsPerBook(String bookId) {
		List<BKTranStudModel> records = bkTransSrchDao.findStudentsPerBook(bookId);

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"STUDENT BOOK RECORDS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTranStudModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getStudentModel().getId());
			rowData.add(row.getStudentModel().getName());
			rowData.add(row.getStudentModel().getBranch() );
			rowData.add(row.getStudentModel().getYear() );
			rowData.add(DateHelper.format(row.getBkTransModel().getIssuedDate()));
			rowData.add(DateHelper.format(row.getBkTransModel().getReturnDate()));
			
			rows.add(rowData);
		}	
		bookdetails = new JTable(rows,getStudentsPerBookColumns());
        bookdetails.setBackground(Color.cyan);
        bookdetails.setEnabled (false);
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
		cols.add("Student No");
		cols.add("Student Name");
		cols.add("Branch");
		cols.add("Year");
		cols.add("Issued Date");
		cols.add("Return");		
		return cols;
	}
   private void populateStudentsTransactedBookList() {
		List<BKTranStudModel> records = bkTransSrchDao.findStudentsTransactedBooks();

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"CURRENT TRANSACTIONS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTranStudModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getBookModel().getAccessNo());
			rowData.add(row.getBookModel().getBookName());
			rowData.add(row.getBookModel().getAuthor1Surname() + " " + row.getBookModel().getAuthor1Name() );
			rowData.add(row.getStudentModel().getId());
			rowData.add(row.getStudentModel().getName());
			rowData.add(row.getStudentModel().getBranch());
			rowData.add(row.getStudentModel().getYear());
			rowData.add(DateHelper.format(row.getBkTransModel().getIssuedDate()));
			rowData.add(DateHelper.format(row.getBkTransModel().getReturnDate()));
			
			rows.add(rowData);
		}
		bktransaction = new JTable(rows,getTransactedBookDisplayColumns());
        bktransaction.setBackground(Color.pink);
        bktransaction.setEnabled (false);
        p2.add(bktransaction,BorderLayout.CENTER);
        JScrollPane sp1 = new JScrollPane(bktransaction);
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
        templibrary.setEnabled (false);
        p3.add(templibrary,BorderLayout.CENTER);
        JScrollPane sp2 = new JScrollPane(templibrary);
        p3.add(sp2,BorderLayout.CENTER);
        validate();		
    }  
   private class myadapter5 extends WindowAdapter
     {
       public void windowClosing(WindowEvent wt)
         {
           
         }
     }
      public static void main(String a[])
       {
         Retrive rt=new Retrive( );
       }
  }