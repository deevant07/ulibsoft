package org.ulibsoft.issues;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.ulibsoft.dao.catalog.StaffDAO;
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.dao.transaction.TransactionHistoryDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKTranStaffModel;
import org.ulibsoft.model.BKTransactionModel;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.model.KeyConstraints;
import org.ulibsoft.model.StaffModel;
import org.ulibsoft.model.TransactionHistoryModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class StaffIssue extends JFrame
  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7866776869731718988L;
	private static final Logger log = Logger.getLogger( StaffIssue.class.getName() );
    private JLabel lid, name, dept, image, bc, bn, an, idt, rdt,jd ;
    private Icon icon;
    private JTextField  no, n1, bc1,dr, bn1, an1, idt1, rd1, jd1 ;
    private JButton iss, can, next ,next_rec;
    private JTable  lttable, bktransaction1, templibrary ;
    private JPanel p2,p3,p4,ltpn;
    private Container  c ;
    private StaffIssue parent;
    private String bname,aname1;

    private int issue,hold;
    private String constraints=null,value;
    private JComboBox<String> bkno;

    private Connection  con ;
    private Statement s ;
    private ResultSet rs;
    
    private KeyConstraintDAO keyCnstrntDao;
    private KeyConstraints keyConstraints;
    private BookTransactionSearchDAO bkTransSrchDao;
    private BookTransactionDAO bkTranDao;
    private BookCatalogDAO bkCatDao; 
    private TransactionHistoryDAO transHstryDao;
    private StaffDAO stfDao;
    public StaffIssue()
      {
         super("Issuing Books");         
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         keyCnstrntDao = DAOFactory.getDAOFactory().getKeyCnstrntDAO();
         bkTransSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();
         bkTranDao = DAOFactory.getDAOFactory().getBKTransDAO();
         stfDao = DAOFactory.getDAOFactory().getStaffDAO();
         bkCatDao = DAOFactory.getDAOFactory().getBookCatalogDAO();
         transHstryDao = DAOFactory.getDAOFactory().getTransHistoryDAO();
         
         keyConstraints = keyCnstrntDao.findById((short)2);    
         createComponents();
         populateStaffsTransactedBookList();
         populateAvailableBookList();
         try
         {
          con=MyDriver.getConnection();
         }catch(Exception e){}
        
         componentListener();
         Myadapter myap = new Myadapter();
         addWindowListener(myap);
      }
    private void createComponents()
      {
         c = getContentPane( ) ;
         c.setBackground( new Color(0,0,40) ) ;
         c.setLayout ( new AbsoluteLayout( ) ) ;

         ltpn = new JPanel( new AbsoluteLayout() );
         ltpn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "LECTURER DETAILS", 1, 2,c.getFont(), Color.magenta));
         ltpn.setBackground(c.getBackground());
         c.add(ltpn,new AbsoluteConstraints(80,20,630,175));

         //INITIALIZE STAFF DETAILS
         
         lid = new JLabel ( "LECTURER  ID . ." );
         lid.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add( lid, new AbsoluteConstraints( 25, 21 ) );

         no = new JTextField ( );
         no.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add (no,new AbsoluteConstraints( 120, 20, 130, 20 ) );

         name = new JLabel ( "NAME . . . . . . . ." );
         name.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( name, new AbsoluteConstraints( 25 , 51 ) );

         n1 = new JTextField ( );
         n1.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         n1.setForeground ( new Color ( 255, 0, 153 ) );
         n1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( n1, new AbsoluteConstraints( 120, 50, 130, 20 ) );

         image = new JLabel(  );
         image.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
         image.setBounds(260,21,100,110);
         ltpn.add(image,new AbsoluteConstraints(260,21,100,110));

         

         dept = new JLabel ( "DEPARTMENT. ." );
         dept.setForeground ( new Color ( 123, 120, 153 ) );
         ltpn.add ( dept, new AbsoluteConstraints( 25, 81 ) );


         dr=new JTextField("");
         dr.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         dr.setForeground ( new Color ( 255, 0, 153 ) );
         dr.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add (dr,new AbsoluteConstraints( 120, 80, 130, 20 ));

         jd = new JLabel( " JOINING  DATE" );
         jd.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( jd, new AbsoluteConstraints(20 , 111 ) );

         jd1 = new JTextField( );
         jd1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         jd1.setForeground ( new Color ( 255, 0, 153 ) );
         jd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( jd1, new AbsoluteConstraints( 120, 110, 130, 20) );

         n1.setEditable( false );
         dr.setEditable( false );
         jd1.setEditable(false);
         //INITIALIZING BOOKDETAILS

         bc = new JLabel( "ACCESS-NO . . .  " );
         bc.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( bc, new AbsoluteConstraints( 385, 21 ) );

         bc1 = new JTextField( );
         bc1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         bc1.setForeground ( new Color ( 255, 0, 153 ) );
         bc1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( bc1, new AbsoluteConstraints( 485, 20, 130, 20 ) );

         bkno = new JComboBox<String>();
         bkno.addItem("");
         bkno.setBackground(Color.white);
         bkno.setForeground ( new Color ( 255, 0, 153 ) );
         bkno.setVisible(false);
         ltpn.add ( bkno, new AbsoluteConstraints( 485, 20, 130, 20 ) );

         bn = new JLabel( "BOOK  NAME  . . " );
         bn.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( bn, new AbsoluteConstraints( 385, 51 ) );

         bn1 = new JTextField( );
         bn1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         bn1.setForeground ( new Color ( 255, 0, 153 ) );
         bn1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( bn1, new AbsoluteConstraints( 485, 50, 130, 20 ) );

         an = new JLabel( "AUTHOR  . . . . . . " );
         an.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( an, new AbsoluteConstraints( 385, 81 ) );

         an1 = new JTextField( );
         an1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         an1.setForeground ( new Color ( 255, 0, 153 ) );
         an1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( an1, new AbsoluteConstraints( 485, 80, 130, 20) );

         //INITiALIZE DATES

         idt = new JLabel( "ISSUE - DATE . . " );
         idt.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( idt, new AbsoluteConstraints( 385, 111 ) );

         idt1 = new JTextField( );
         idt1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         idt1.setForeground ( new Color ( 255, 0, 153 ) );
         idt1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( idt1, new AbsoluteConstraints( 485, 110, 130, 20 ) );

         rdt = new JLabel( "RECIEVE - DATE" );
         rdt.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( rdt, new AbsoluteConstraints( 385, 141 ) );

         rd1 = new JTextField( );
         rd1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         rd1.setForeground ( new Color ( 255, 0, 153 ) );
         rd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( rd1, new AbsoluteConstraints( 485, 140, 130, 20 ) );

         idt1.setEditable(false);
         rd1.setEditable(false);
         bn1.setEditable( false );
         an1.setEditable( false );

         //BUTTON CREATION

         iss = new JButton( "ISSUE" ) ;
         iss.setMnemonic ('I');
         iss.setBackground ( Color.cyan  );
         iss.setForeground( Color.black );
         iss.setBorder ( new BevelBorder(0)  );
         iss.setEnabled (false);
         ltpn.add ( iss, new AbsoluteConstraints( 50-25,140, 110, 25) );

         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan   );
         next.setForeground(Color.black  );
         next.setMnemonic('N');
         next.setBorder ( new BevelBorder (0));
         ltpn.add ( next, new AbsoluteConstraints( 162-25, 140, 110, 25 ) );

         next_rec = new JButton( "NEXT_REC" ) ;
         next_rec.setBackground ( Color.cyan);
         next_rec.setForeground(Color.black);
         next_rec.setMnemonic('N');
         next_rec.setBorder(new BevelBorder(0));
         next_rec.setVisible(false);
         ltpn.add ( next_rec, new AbsoluteConstraints( 162-25, 140, 110, 25 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan  );
         can.setForeground( Color.black );
         can.setMnemonic('X');
         can.setBorder ( new BevelBorder(0) );
         ltpn.add ( can, new AbsoluteConstraints( 274-25, 140, 110, 25 ) );

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROFESSOR  DATABASE", 1, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(15,215,400,140));

         p3 = new JPanel( new BorderLayout() );
         p3.setBackground(c.getBackground());
         p3.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "CURRENT  TRANSACTIONS", 1, 2, c.getFont(), Color.magenta));
         c.add(p3,new AbsoluteConstraints(20,360+10,750,190));


         p4 = new JPanel( new BorderLayout() );
         p4.setBackground(c.getBackground());
         p4.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "AVAILABLE  BOOKS", 1, 2, c.getFont(), Color.magenta));
          c.add(p4,new AbsoluteConstraints(425,215,350,140));

         setVisible(true);
      }

    private void componentListener()
      {
        //SETTING POP-UP-MENU
        addMouseListener( new MouseAdapter()
          {
            public void mouseClicked(MouseEvent e)
              {
                if (e.isMetaDown())
                 {
                   PopUpMenu p=new PopUpMenu(e.getComponent(),e.getX(),e.getY());
                   //p.setFrame(parent);//Index.si);
                 }
              }
          }
        );
          //RECIEVING STAFF DETAILS FROM DATABASE
        no.addActionListener( new ActionListener ()
          {
            public void actionPerformed( ActionEvent e )
              {
                no.setText(no.getText().toUpperCase());
                //up.setEnabled (true);                      
                if( bkTransSrchDao.getCountBooksStaff(no.getText ()) >= keyConstraints.getMaxBook() )
                {
                  JOptionPane.showMessageDialog(null,"MAXIMUM  BOOKS  EXCEEDED");
                  no.setText("");
                }

               else
               {
                  if ( stfDao.isActive( no.getText() ) )      
                   {
                      //SET STAFF_MEMBER DETAILS
                      PassWordDialog pwd1 = new PassWordDialog( (Frame)parent,"PASSWORD CONFIRMATION" );
                   }
                  else                 
                   {
                      JOptionPane.showMessageDialog(null,"RECORD DOES NOT EXIST !  .  .  .");
                   }               
               }
                   
                  
             }
          }
        );

        bc1.addActionListener( new ActionListener()
          {
             public void actionPerformed( ActionEvent ae )
               {
            	 BookModel bkMdl = bkCatDao.findByAccessNo(bc1.getText ());
	               if( bkMdl != null  )
	                {	                   
	                   bn1.setText( bkMdl.getBookName() );
	                   an1.setText( bkMdl.getAuthor1Surname() + " " +bkMdl.getAuthor1Name() );
	                   setDates( );
	                   iss.setEnabled (true);
	                }
	               else
                   {
                      JOptionPane.showMessageDialog(null,"BOOK'S RECORD NOT EXIST","CURRENT TRANSACTIONS",
                                                           JOptionPane.INFORMATION_MESSAGE );
                   }                
               }
          }
        );

        bkno.addActionListener(new ActionListener()
          {
             public void actionPerformed( ActionEvent e )
               {
                  if(bkno.getSelectedIndex()!=0)
                    {
                       iss.setEnabled(true);
                       bc1.setText((String)bkno.getSelectedItem());
                    }
               }
          }
        );

        iss.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {    
            	int count=bkTransSrchDao.getCountBooksStaff(no.getText());
                if( count < keyConstraints.getMinBook()  )
                 {
                	acess();
                 }
                 else
                  {
                     if(count < keyConstraints.getMaxBook() )
                     {
	                       int ws = JOptionPane.showConfirmDialog (parent,count+"BOOKS R ISSUED . R U GIVE ONE MORE . . . ?","warning",JOptionPane.YES_NO_OPTION  ) ;
	                       if(ws==0)
	                       {
	                         acess();
	                       }
	                       else
	                       {
	                    	   reset();
	                       }
                      }
                      else
                      {
                            JOptionPane.showMessageDialog(null,"MAXIMUM  BOOKS  EXCEEDED !  . . .");
                            reset();
                      }
                  }
                  iss.setEnabled (false);                  
              }
          }
        );


        can.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
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
                setVisible(false);
              }
          }
        );

        next.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                reset();
              }
          }
        );
        next_rec.addActionListener( new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
              {
                 bkno.removeAllItems();
                 bc1.setText("");
                 an1.setText("");
                 idt1.setText("");
                 rd1.setText("");
                 bookRecord();
              }
          }
        );

      }
    private void bookRecord()
      {
         try
           {
              s = con.createStatement();

              rs = s.executeQuery("SELECT LID,BOOKNAME,AUTHOR1,AUTHOR2,AUTHOR3 FROM STF_RESERVE WHERE LID='"+no.getText().toUpperCase()+"' AND BOOKNAME IS NOT NULL");
              if( rs.next() )
                {
                   bname = rs.getString(2);
                   if( bname==null||bname.equals("") ){}
                   else{constraints = " LIB.BOOKNAME = '"+bname+"'";}
                   //JOptionPane.showMessageDialog(null,""+bname);
                   aname1 = rs.getString(3);
                   if( aname1==null||aname1.equals("") ){}
                   else{constraints =constraints+" AND LIB.AUTHOR1S||LIB.AUTHOR1F = '"+aname1+"'";}
                   String aname2 = rs.getString(4);
                   if( aname2==null||aname2.equals("") ){}
                   else{constraints =constraints+ " AND LIB.AUTHOR2S||LIB.AUTHOR2F = '"+aname2+"'";}
                   String aname3 = rs.getString(5);
                   if( aname3==null||aname3.equals("") ){}
                   else{constraints = constraints+" AND LIB.AUTHOR3S||LIB.AUTHOR3F = '"+aname3+"'";}
                   //JOptionPane.showMessageDialog(null,"  2  "+constraints);

                   rs = s.executeQuery("SELECT COUNT(BOOKNAME) FROM STF_RESERVE WHERE LID= '"+no.getText().toUpperCase()+"'");
                   while(rs.next())
                     {
                       hold=rs.getInt(1);
                     }
                   if(hold>1)
                     {
                        next.setVisible(false);
                        next_rec.setVisible(true);
                     }
                   else
                     {
                        next.setVisible(true);
                        next_rec.setVisible(false);
                     }
                   rs = s.executeQuery("SELECT LIB.ACESSNO FROM LIBRARY LIB WHERE LIB.ACESSNO NOT IN(SELECT BK.CODE FROM BKTRANSACTION BK WHERE BK.CODE=LIB.ACESSNO) AND LIB.ACESSNO NOT IN(SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO )  AND "+constraints);//BOOKNAME='"+bname1+"' AND AUTHOR1S||AUTHOR1F ='"+aname1+"' AND AUTHOR2S||AUTHOR2F ='"+aname2+"AND AUTHOR3S||AUTHOR3F ='"+aname3+"'");
                   while( rs.next() )
                     {
                       value = rs.getString(1);
                       //JOptionPane.showMessageDialog(null,"erweywry"+value);
                       bkno.addItem(value);
                       bn1.setText( bname );
                       an1.setText( aname1 );
                       bc1.setVisible(false);
                       bkno.setVisible(true);
                       issue=1;
                     }
                   if(issue==1)
                     {
                        setDates();
                     }
                }
              s.close();
           }
         catch(SQLException sqlex)
           {
             JOptionPane.showMessageDialog(null,sqlex.getMessage());
           }
      }

    private void acess()
    {
    	BKTransactionModel bkTransMdl = new BKTransactionModel();
		bkTransMdl.setCode(bc1.getText());
		bkTransMdl.setId(no.getText());
		try
		{
			bkTransMdl.setIssuedDate(DateHelper.parse(idt1.getText()));
    		bkTransMdl.setReturnDate(DateHelper.parse(rd1.getText()));
		}catch(ParseException pe)
		{
			JOptionPane.showMessageDialog(null,"Invalid Date format, Please enter in dd-Mmm-yyyy");
			return;
		}
		bkTranDao.createStaff(bkTransMdl);
		
		TransactionHistoryModel transHstry = new TransactionHistoryModel();
		transHstry.setCode(bc1.getText());
		transHstry.setLid(no.getText());
		transHstry.setValue("1");
		transHstry.setStatus("TRUE");
		try
		{
			transHstry.setIssuedDate(DateHelper.parse(idt1.getText()));
			transHstry.setReturnDate(DateHelper.parse(rd1.getText()));
		}catch(ParseException pe)
		{
			JOptionPane.showMessageDialog(null,"Invalid Date format, Please enter in dd-Mmm-yyyy");
			return;
		}
		if ( transHstryDao.create(transHstry) > 0 )
			log.info("Transaction History record inserted successfully");
		else
			log.warn("Transaction History record insertion failed");
        try
          {        	
            s=con.createStatement ();
            //INSERTION FOR BKTRANSACTION
            populateStaffsTransactedBookList( );                   
            populateBooksPerStaff( no.getText() );
            populateAvailableBookList( );
            if(bname==null||bname.equals("")||aname1==null||aname1.equals("")){}
            else
            {
            int k =s.executeUpdate("DELETE FROM STF_RESERVE WHERE LID='"+no.getText().toUpperCase()+"' AND BOOKNAME='"+bname+"' AND AUTHOR1='"+aname1+"'");//"+constraints);
            con.commit();
            }
            //JOptionPane.showMessageDialog(null,"deleted");
            s.close( );

           }
           catch(SQLException sq)
           {
               JOptionPane.showMessageDialog (null,"ERROR : "+sq.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
               sq.printStackTrace();
           }
    }

    private class PassWordDialog extends JDialog
      {
         private JPasswordField pwdfd;
         private JLabel pwdlb;
         private JButton sub,newbtn;
         public PassWordDialog( Frame parent,String title )
           {
              super( parent,title,true );

              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(pwdlb = new JLabel("PASSWORD :"),new AbsoluteConstraints(25,25));
              pwdlb.setForeground(Color.white);

              getContentPane().add(pwdfd = new JPasswordField(),new AbsoluteConstraints(110,23,130,22));
              pwdfd.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add(sub = new JButton("SUBMIT"),new AbsoluteConstraints(30,55,100,22));
              sub.setBackground(Color.cyan);
              sub.setMnemonic('S');
              sub.setBorder(new BevelBorder(0));
              getContentPane().add(newbtn = new JButton(" CHANGE "),new AbsoluteConstraints(132,55,100,22));
              newbtn.setBackground(Color.cyan);
              newbtn.setMnemonic('C');
              newbtn.setBorder(new BevelBorder(0));

              /*newbtn.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        ChangePassWordDialog cpd = new ChangePassWordDialog((Dialog)parent1,"CHANGE PASSORD");
                        setVisible(false);
                     }
                }
              );*/

              sub.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {
                        try
                          {
                             s = con.createStatement();
                             rs = s.executeQuery("SELECT PASSWORD FROM STAFF WHERE LID="+"'"+no.getText()+"'" );
                             if( rs.next() )
                               {
                                  if( new String( pwdfd.getPassword() ).equals(rs.getString(1)) )
                                    {
                                       //SET STUDENT DETAILS
                                       staffRecord();
                                       bc1.setEditable(true);
                                       bookRecord();
                                       setVisible( false );
                                    }
                                  else
                                    {
                                       JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                                    }
                                 con.commit();
                                 s.close();
                               }

                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage() );
                          }
                    }
                }
              );

             setBounds(250,250,270,120);
              setVisible(true);
           }
      }


    /*private class ChangePassWordDialog extends JDialog
      {
         private JLabel msg;
         private JRadioButton yes,no2;
         private JPasswordField old,new1;
         private JLabel oldlb,newlb;
         public ChangePassWordDialog(Dialog parent2,String title)
           {
              super(parent,title,true);

              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(msg = new JLabel("R U SURE  !  .  .  .  TO CHANGE UR ' S  PASSWORD  ?"),new AbsoluteConstraints(25,25));
              msg.setForeground(Color.white);

              getContentPane().add( yes = new JRadioButton("YES"),new AbsoluteConstraints(25,50));
              yes.setBackground(getContentPane().getBackground());
              yes.setMnemonic('Y');
              getContentPane().add( no2  = new JRadioButton("NO"),new AbsoluteConstraints(25,75));
              no2.setBackground(getContentPane().getBackground());
              no2.setMnemonic('N');
              getContentPane().add( oldlb  = new JLabel("OLD  PASSWORD  :"),new AbsoluteConstraints(25,105));
              oldlb.setForeground(Color.white);

              getContentPane().add( newlb  = new JLabel("NEW PASSWORD  :"),new AbsoluteConstraints(25,135));
              newlb.setForeground(Color.white);

              getContentPane().add( old  = new JPasswordField(),new AbsoluteConstraints(125+10,105,130,20));
              old.setEditable(false);
              old.setBackground(Color.lightGray);
              old.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add( new1  = new JPasswordField(),new AbsoluteConstraints(125+10,134,130,20));
              new1.setEditable(false);
              new1.setBackground(Color.lightGray);
              new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              yes.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        old.setEditable(true);
                        old.setBackground(Color.white);
                        no2.setEnabled(false);
                     }
                }
              );

              no2.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e)
                     {
                        new1.setEditable(false);
                        yes.setEnabled(false);
                        old.setEditable(false);
                     }
                }
              );

              old.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        try
                          {
                             s = con.createStatement();
                             rs = s.executeQuery("SELECT PASSWORD FROM STAFF WHERE LID ="+"'"+no.getText()+"'"+"AND PASSWORD ="+"'"+new String( old.getPassword())+"'");
                                if( rs.next() )
                                  {
                                    //if( )//rs.getString(2).equals(no.getText()))
                                     // {
                                      new1.setBackground(Color.white);
                                         new1.setEditable(true);
                                    //  }
                                  }
                                else
                                  {
                                     JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                                  }
                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage());
                          }
                     }
                }
              );

              new1.addActionListener(new ActionListener()
                {
                   public void actionPerformed(ActionEvent e)
                     {
                        try
                          {
                             s = con.createStatement();
                             rs = s.executeQuery("SELECT PASSWORD FROM STAFF WHERE PASSWORD="+"'"+new String(new1.getPassword())+"'");
                             if( rs.next() )
         		       {
 				  JOptionPane.showMessageDialog(null,"PASSWORD ALREADY EXISTS !  .  .  .");
			          new1.setText("");
                               }
                             else
                               {
                                  int h = s.executeUpdate("UPDATE STAFF SET PASSWORD ="+"'"+new String(new1.getPassword())+"'"+"WHERE LID = "+"'"+no.getText()+"'" );
  				  staffRecord();
                                  setVisible(false);
                               }
                             con.commit();
                             s.close();
                          }
                        catch(SQLException sq)
                          {
                             JOptionPane.showMessageDialog(null,sq.getMessage());
                          }
                     }
                }
              );

             setBounds(220,200,320,190);
              setVisible(true);

           }
      }*/

    private void staffRecord()
      {
    	StaffModel stfMdl = stfDao.findById(no.getText());
    	if ( stfMdl != null )
    	{
    		n1.setText(stfMdl.getName());
            dr.setText(stfMdl.getDept());
            jd1.setText(DateHelper.format(stfMdl.getJoinDate()));       
            
            byte[] imageByte = stfMdl.getImage();
    		if ( imageByte != null )
    		{
    			icon = new MyImageIcon(stfMdl.getImage());
    			image.setIcon(icon);
    		}else
    			image.setIcon(null);           
    	}        
      }
    private void reset()
    {
    	//RESET STAFF VALUES
        no.setText("");
        no.setEditable(true);

        n1.setEditable(false);
        n1.setText("");
        dr.setText("");
        dr.setEditable(false);
        jd1.setText("");
        jd1.setEditable(false);
        image.setIcon (null);

     //RESET BOOK VALUES
        bn1.setText("");
        bn1.setEditable(false);
        an1.setText("");
        an1.setEditable(false);
        bc1.setEditable(false);
        bkno.setVisible(false);
        bkno.removeAllItems();
        bc1.setVisible(true);

     //RESET DATES
        idt1.setText("");
        idt1.setEditable(false);
        rd1.setText("");
        rd1.setEditable(false);

        iss.setEnabled(false);
        constraints=null;        
    }
    protected void setDates()
    {
    	rd1.setEditable(true);    	
    	idt1.setText( DateHelper.getCurrentDateString() );
    	rd1.setText( DateHelper.addDaysToCurrentString(keyConstraints.getMaxDaysPerBook()) );        
    }
    private Vector<Object> getBooksPerStudentColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("Access No");
		cols.add("Book Name");
		cols.add("Author");
		cols.add("Issued Date");
		cols.add("Return Date");		
		return cols;
	}
    private void populateBooksPerStaff(String stfId) {
		List<BKTranStaffModel> records = bkTransSrchDao.findBooksPerStaff(stfId);

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"STAFF BOOK RECORDS", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (BKTranStaffModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getBookModel().getAccessNo());
			rowData.add(row.getBookModel().getBookName());
			rowData.add(row.getBookModel().getAuthor1Surname() + " " + row.getBookModel().getAuthor1Name() );
			rowData.add(DateHelper.format(row.getBkTransModel().getIssuedDate()));
			rowData.add(DateHelper.format(row.getBkTransModel().getReturnDate()));
			
			rows.add(rowData);
		}	
		lttable = new JTable(rows,getBooksPerStudentColumns());
        lttable.setBackground(Color.cyan);
        lttable.setEnabled (false);
        p2.add(lttable,BorderLayout.CENTER);
        JScrollPane spane =new JScrollPane(lttable);
        p2.add(spane,BorderLayout.CENTER);
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
        bktransaction1.setEnabled (false);
        p3.add(bktransaction1,BorderLayout.CENTER);
        p3.add(new JScrollPane(bktransaction1));
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
       p4.add(templibrary,BorderLayout.CENTER);
       p4.add(new JScrollPane(templibrary),BorderLayout.CENTER);
       validate();
   }
    private class Myadapter extends WindowAdapter
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
        StaffIssue i = new StaffIssue();
      }
  }
