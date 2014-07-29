package org.ulibsoft.register.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
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
import java.text.ParseException;
import java.util.List;
import java.util.Vector;

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
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.constants.label.ButtonLabel;
import org.ulibsoft.core.ui.FilePreviewer;
import org.ulibsoft.core.ui.MyImageIcon;
import org.ulibsoft.dao.catalog.StaffDAO;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.issues.StaffIssue;
import org.ulibsoft.login.Login;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.menu.StaffAccess;
import org.ulibsoft.model.StaffModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class StaffDetails extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -364212964492161310L;
	
	private static final Logger log = Logger.getLogger(StaffDetails.class.getName());
    private String AD_NO;
    private JLabel lid, name, dept, image,jd,tech ;
    private MyImageIcon icon;
    private JTextField  no, n1,jd1 ;
    private JComboBox<String> tech1,d1;
    private JButton ins, can, next,up,browse ;
    private JTable  staff ;
    private JScrollPane sp ;
    private JPanel p1,ltpn;
    private Container  c ;
    private StaffIssue parent;    

    private StaffDAO staffDao;   
    private ButtonActionListener btnActnLstnr;

    public StaffDetails()
    {
         super("STAFF ENTRIES");
         ScreenResolution.getResolution();
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);         
         staffDao = DAOFactory.getDAOFactory().getStaffDAO();
         btnActnLstnr = new ButtonActionListener();
         createComponents();
         
         populateList();
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
         c.add(ltpn,new AbsoluteConstraints(172,30,456,230));

         //INITIALIZE STAFF DETAILS
         

         lid = new JLabel ( "PROFESSOR ID :" );
         lid.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add( lid, new AbsoluteConstraints( 45+2, 21+2+5 ) );

         no = new JTextField ( );
         no.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add (no,new AbsoluteConstraints( 147, 20+5, 140, 20+2 ) );

         name = new JLabel ( "PROFESSOR NAME :" );
         name.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( name, new AbsoluteConstraints( 25 , 51+2+5 ) );

         n1 = new JTextField ( );
         n1.setBorder ( new MatteBorder (1, 1, 1, 1, c.getBackground () ) );
         n1.setForeground ( new Color ( 255, 0, 153 ) );
         n1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( n1, new AbsoluteConstraints( 147, 50+5, 140, 20+2 ) );

         image = new JLabel(  );
         image.setBorder ( new MatteBorder (1, 1, 1, 1, Color.cyan ) );
         image.setBounds(260,21,100,110);
         ltpn.add(image,new AbsoluteConstraints(310,21+5,100,110));

         

         dept = new JLabel ( "DEPARTMENT :" );
         dept.setForeground ( new Color ( 123, 120, 153 ) );
         ltpn.add ( dept, new AbsoluteConstraints( 54, 81+2+5 ) );

         d1 = new JComboBox<String>();
         d1.setEnabled(false);
         d1.setEditable(true);
         d1.addItem("");
         d1.addItem ("COMPUTERS");
         d1.addItem ("COMPUTER/INFO.TECH");
         d1.addItem ("ELECTRONICS");
         d1.addItem ("ELECTRICAL");
         d1.addItem ("MECHANICAL");
         d1.addItem ("MANAGEMENT");
         d1.addItem ("GENERAL");
         d1.addItem ("LANGUAGE");
         for ( String item : staffDao.findDepts() )
         {
        	 d1.addItem(item);
         }
         d1.setForeground ( new Color ( 255, 0, 153 ) );
         ltpn.add ( d1, new AbsoluteConstraints( 147, 80+5, 140, 20+2 ) );



         jd = new JLabel( " JOINING DATE :" );
         jd.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( jd, new AbsoluteConstraints(49 , 111+2+5 ) );

         jd1 = new JTextField( );
         jd1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         jd1.setForeground ( new Color ( 255, 0, 153 ) );
         jd1.setCaretColor ( new Color ( 0, 204, 102 ) );
         ltpn.add ( jd1, new AbsoluteConstraints( 147, 110+5, 140, 20+2) );

         tech = new JLabel( " TEACHING  :" );
         tech.setForeground ( new Color ( 120, 120, 153 ) );
         ltpn.add ( tech, new AbsoluteConstraints(69 , 141+2+5 ) );

         tech1 = new JComboBox<String>();
         tech1.setEnabled(false);
         tech1.setBorder ( new MatteBorder ( 1, 1, 1, 1, new Color(0,0,40) ) );
         tech1.setForeground ( new Color ( 255, 0, 153 ) );
         tech1.setBackground(Color.white);
         tech1.addItem("");
         tech1.addItem("YES");
         tech1.addItem("NO");
         ltpn.add ( tech1, new AbsoluteConstraints( 147, 140+5, 140, 20+2) );

         n1.setEditable( false );
         jd1.setEditable(false);

         //button creation

         ins = new JButton( ButtonLabel.INSERT ) ;
         ins.setMnemonic (ButtonLabel.INSERT_MNMNC);
         ins.setBackground ( Color.cyan  );
         ins.setForeground( Color.black );
         ins.setBorder ( new BevelBorder(0)  );
         ins.setEnabled (false);
         ltpn.add ( ins, new AbsoluteConstraints( 15,140+30+5+5, 105, 27) );
         ins.addActionListener(btnActnLstnr);
         
         up = new JButton( ButtonLabel.UPDATE ) ;
         up.setBackground (Color.cyan);
         up.setForeground(Color.black);
         up.setBorder(new BevelBorder(0));
         up.setMnemonic(ButtonLabel.UPDATE_MNMNC);
         up.setEnabled (false);
         ltpn.add ( up, new AbsoluteConstraints( 122, 140+30+5+5, 105, 27 ) );
         up.addActionListener(btnActnLstnr);

         next = new JButton( ButtonLabel.NEXT ) ;
         next.setBackground ( Color.cyan   );
         next.setForeground(Color.black  );
         next.setMnemonic(ButtonLabel.NEXT_MNMNC);
         next.setBorder ( new BevelBorder (0));
         ltpn.add ( next, new AbsoluteConstraints( 229, 180, 105,  27 ) );
         next.addActionListener(btnActnLstnr);

         browse = new JButton( ButtonLabel.BROWSE ) ;
         browse.setMnemonic (ButtonLabel.BROWSE_MNMNC);
         browse.setBackground ( Color.cyan  );
         browse.setForeground( Color.black );
         browse.setBorder ( new BevelBorder(0)  );
         browse.setEnabled (false);
         ltpn.add ( browse, new AbsoluteConstraints( 310,141, 101, 25) );
         browse.addActionListener(btnActnLstnr);

         can = new JButton( ButtonLabel.EXIT ) ;
         can.setBackground ( Color.cyan  );
         can.setForeground( Color.black );
         can.setMnemonic(ButtonLabel.EXIT_MNMNC );
         can.setBorder ( new BevelBorder(0) );
         ltpn.add ( can, new AbsoluteConstraints( 336, 170+5+5, 105, 27 ) );
         can.addActionListener(btnActnLstnr);
         
         p1 = new JPanel( new BorderLayout() );
         p1.setBackground(c.getBackground());
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROFESSORS DATABASE", 1, 2, c.getFont(), Color.magenta));
         c.add(p1,new AbsoluteConstraints(80, 280, 630, 270));

         setVisible(true);

    }


	private void populateRecord(StaffModel rec) {
		// SET STAFF_MEMBER DETAILS
		no.setText(rec.getId());
		n1.setText(rec.getName());		
		d1.setSelectedItem(rec.getDept());		
        jd1.setText(DateHelper.format(rec.getJoinDate()));		
		tech1.setSelectedItem(rec.getStaffType());
		byte[] imageByte = rec.getImage();
		if ( imageByte != null )
		{
			icon = new MyImageIcon(rec.getImage());
			image.setIcon(icon);
		}else
			image.setIcon(null);
		
		
		n1.setEditable(true);
		jd1.setEditable(true);
		
		browse.setEnabled(true);
		d1.setEnabled(true);
		tech1.setEnabled(true);
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
        if(StaffAccess.access==1)
          {
             AD_NO = Login.cname.getText().toUpperCase();
             no.setEditable(false);
             ins.setEnabled(false);
             next.setEnabled(false);
             browse.setEnabled(false);
             up.setEnabled(true);
             
             StaffModel rec = staffDao.findById(AD_NO);
             if ( rec != null )
             {
            	 populateRecord(rec);
             }             
          }
         no.addActionListener( new ActionListener ()
          {

            public void actionPerformed( ActionEvent e )
              {
                no.setText(no.getText().toUpperCase());
                up.setEnabled (true);
                                 
                StaffModel rec = staffDao.findById(no.getText());
                if ( rec != null )
                 {
              	  	populateRecord(rec);
                 }
                  else                 // IF RECORDS=0
                   {
                     int cfirm=JOptionPane.showConfirmDialog(null,"DO U WANT TO ENTER THE NEW RECORD","NEW RECORD",JOptionPane.YES_NO_OPTION);
                      if(cfirm==0)
                      {
                         n1.setText("");
                         n1.setEditable(true);
                         jd1.setText("");
                         d1.setEnabled(true);
                         d1.setSelectedItem("");
                         jd1.setEditable(true);
                         tech1.setSelectedItem("");
                         tech1.setEnabled(true);
                         browse.setEnabled(true);
                      }
                   }                    
                  }
                
              });

        n1.addKeyListener(new KeyListener()
           {
              public void keyTyped(KeyEvent e1)
                {
                }
              public void keyReleased(KeyEvent e2)
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
    }
    private class ButtonActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			
			Object source = event.getSource();
			if ( source instanceof JButton )
			{
				String name = ((JButton)source).getText();
				if ( ButtonLabel.INSERT.equals(name))
				{
	                up.setEnabled(false);
	                if ( n1.getText() == null || n1.getText().length() <= 0 )
					{
						JOptionPane.showMessageDialog(null,"Please enter alpha characters only");
						return;						
					}
					if ( no.getText() == null || no.getText().length() <= 0 )
					{
						JOptionPane.showMessageDialog(null,"Please enter alpha numeric characters only");
						return;	
					}
					if ( d1.getSelectedItem() == null || d1.getSelectedItem().toString().length() <= 0 )
					{
						JOptionPane.showMessageDialog(null,"Please select one item");
						return;	
					}	
					StaffModel recCreate = new StaffModel();
					recCreate.setId(no.getText().toUpperCase());
					recCreate.setName(n1.getText().toUpperCase());
					recCreate.setDept(d1.getSelectedItem().toString().toUpperCase());	
					try {
						recCreate.setJoinDate(DateHelper.parse(jd1.getText()));
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null,"Invalid Date Format, Please enter in dd-Mon-yyyy format");
						return;
					}
					byte[] imageByte = icon.getImageBytes();
					if ( imageByte != null )
						recCreate.setImage(imageByte);
					
					recCreate.setStaffType(tech1.getSelectedItem().toString());
					recCreate.setStatus("TRUE");
					staffDao.create(recCreate);
					populateList();
					jd1.setEditable(false);
	                ins.setEnabled(false);
	                new NewPassWordDialog((Frame)parent,"INPUT  PASSWORD");
	            }else if ( ButtonLabel.UPDATE.equals(name))
				{
	            	if(staffDao.hasBook(no.getText().toUpperCase()) || staffDao.hasCD(no.getText().toUpperCase()) || staffDao.hasMagazine(no.getText().toUpperCase()))
	                {
	              	  JOptionPane.showMessageDialog(null,"HE / SHE HOLDS RECORDS .  .  .  .","BOOKS / CDS / MAGAZINES ",JOptionPane.ERROR_MESSAGE);
	              	  return;
	                }
	                else
	                {
	              	  PassWordDialog pwd1 = new PassWordDialog( (Frame)parent,"PASSWORD CONFIRMATION" );
	                }
	                

	                  if(StaffAccess.access==1)
	                  {
	                     up.setEnabled(true);
	                  }
	            } else if (ButtonLabel.NEXT.equals(name)) {
	                no.setText("");
	                n1.setEditable(false);
	                n1.setText("");
	                d1.setSelectedItem("");
	                jd1.setEditable(false);
	                jd1.setText("");
	                image.setIcon (null);
	                up.setEnabled(false);
	                ins.setEnabled(false);
	                tech1.setSelectedItem("");
	                populateList();
	             } else if ( ButtonLabel.BROWSE.equals(name) )
				{
					icon =  FilePreviewer.loadImage(200,213);
					image.setIcon(icon);
					ins.setEnabled(true);
				}else if ( ButtonLabel.EXIT.equals(name) )
				{
					setVisible(false);
				}
			}
			
		}
	}
    private class NewPassWordDialog extends JDialog
      {
         private JComboBox<String> question;
         private JLabel oldlb,newlb,pwdLabel;
         private JPasswordField new1,input;
         private JButton sub;
         private Container k;
         private int count=0;
         private NewPassWordDialog child;
         MyAdapter adap=new MyAdapter();
         String[] qtnArry = { "", "WT ' S  UR  PET_NAME  ?" , "WHO ' S  UR  FAVOURITE_PERSON  ?", "WT ' S  UR  FAVOURITE_COLOR  ?", "WT ' S  UR  FAVOURITE_SPORT  ?","WT ' S  UR  FAVOURITE_GAME  ?"};

         public NewPassWordDialog(Frame parent,String title)
           {
              super(parent,title,true);

              k = getContentPane();
              k.setLayout(new AbsoluteLayout());
              k.setBackground(new Color(120,120,180));

              k.add( oldlb  = new JLabel("CHOOSE  ME  :"),new AbsoluteConstraints(25,25));
              oldlb.setForeground( Color.white);

              k.add( question = new JComboBox<String>(qtnArry),new AbsoluteConstraints(115,23,200,20) );
              question.setBackground(k.getBackground());
              question.setForeground( Color.white);              

              k.add( newlb  = new JLabel("INPUT TO ME  :"),new AbsoluteConstraints(25,55));
              newlb.setForeground( Color.white);

              input = new JPasswordField();
              input.setEditable(false);
              input.setBackground(k.getBackground());
              k.add(input,new AbsoluteConstraints(115,52,200,20));
              input.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,0) ) );

              k.add( pwdLabel = new JLabel("PASSWORD   :"),new AbsoluteConstraints(25,85));
              pwdLabel.setForeground( Color.white);

              k.add( new1  = new JPasswordField(),new AbsoluteConstraints(115,83,200,20));
              new1.setEditable(false);
              //new1.setBackground(Color.lightGray);
              new1.setBackground(k.getBackground());
              new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,0) ) );

              k.add(sub = new JButton("*  *  *  *  *  *  *  SUBMITT  *  *  *  *  *  * *"),new AbsoluteConstraints(25,117,290,25));
              sub.setBackground(k.getBackground());
              sub.setEnabled(false);
              sub.setMnemonic('S');

              question.addItemListener(new ItemListener()
                {
                   public void itemStateChanged(ItemEvent e)
                     {
                        if( count<1 )
                          {
                             input.setEditable(true);
                             new1.setEditable(true);
                             input.setBackground(Color.white);
                             new1.setBackground(Color.white);
                             new1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );
                             input.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );
                             sub.setEnabled(true);
                             count++;
                          }
                     }
                }
              );

              sub.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {


   					if (question.getSelectedItem() == null
   							|| question.getSelectedItem().equals("")) {
   						JOptionPane.showMessageDialog(null,
   								"U  MUST  CHOOSE  ONE  QUESTION");
   						return;
   					}
   					if (input.getText().equals("") || input.getText() == null) {
   						JOptionPane.showMessageDialog(null,
   								"U  MUST  GIVE  ONE  ANSWER");
   						return;
   					}
   					String password = new String(new1.getPassword());
   					if (password == null || password.equals("")) {
   						JOptionPane.showMessageDialog(null,
   								"U MUST INPUT PASSWORD  !  .  .  .", "WARNING",
   								JOptionPane.INFORMATION_MESSAGE);
   						return;
   					}					
   					
   					StaffModel recUpdate = staffDao.findById(no.getText().toUpperCase());
   					if ( recUpdate == null )
   					{
   						log.warn("Record doesn't exist, update failed");
   					}else
   					{
   						recUpdate.setAnswer(input.getText().toUpperCase());
   						recUpdate.setQuestion(String.valueOf(question.getSelectedIndex()));
   						recUpdate.setPassword(new String(new1.getPassword()));
   						int updt = staffDao.update(recUpdate);
   						if ( updt <= 0 )
   							log.error("Record update failed");
   						else
   							log.info("Record updated sucessfully");
   					}  										
   					setVisible(false);	                       
                    }
                }
              );

             addWindowListener( adap);
             setBounds(230,200,350,190);
             setVisible(true);

           }
          private class MyAdapter extends WindowAdapter
          {
           public void windowClosing(WindowEvent we)
               {
                int weq=JOptionPane.showConfirmDialog(child,"IF  U  CLICK  OK , UR  RECORD  WILL  BE  DELETED ","WARNING",JOptionPane.OK_CANCEL_OPTION);
                if(weq==0)
                {
                	int del = staffDao.delete(no.getText().toUpperCase());
                    if ( del > 0 )
                 	   log.info("Record Deleted Sucessfully "); 
                }
                else
                {
                 //removeWindowListener(adap);
                 new NewPassWordDialog((Frame)parent,"INPUT  PASSWORD");
                }
               }
           }
      }
    private class PassWordDialog extends JDialog
      {
         private JPasswordField pwdfd;
         private JLabel pwdlb;
         private JButton sub;
         private AuthDAO authSvc;
         
         public PassWordDialog( Frame parent,String title )
           {
              super( parent,title,true );
              authSvc = DAOFactory.getDAOFactory().getAuthDAO();
              getContentPane().setLayout(new AbsoluteLayout());
              getContentPane().setBackground(new Color(120,120,180));

              getContentPane().add(pwdlb = new JLabel("PASSWORD :"),new AbsoluteConstraints(25,25));
              pwdlb.setForeground(Color.white);

              getContentPane().add(pwdfd = new JPasswordField(),new AbsoluteConstraints(110,23,130,22));
              pwdfd.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(120,120,180) ) );

              getContentPane().add(sub = new JButton("SUBMIT"),new AbsoluteConstraints(80,55,100,22));
              sub.setBackground(Color.cyan);
              sub.setMnemonic('S');
              sub.setBorder(new BevelBorder(0));


              sub.addActionListener(new ActionListener()
                {
                   public void actionPerformed( ActionEvent e )
                     {
                	   if( !authSvc.authenticateStaffUser(no.getText().toUpperCase(), new String( pwdfd.getPassword() ) ) )
                       {
                     	  JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                     	  return;
                       } 
                	   StaffModel recUpdt = staffDao.findById(no.getText().toUpperCase());
                	   if ( recUpdt == null )
                	   {
                 		  log.warn("Record doesn't exist, update failed");
                 	   }
                	   else
                	   {

                 		  recUpdt.setName(n1.getText ().toUpperCase());
                 		  recUpdt.setDept(d1.getSelectedItem()!=null ? d1.getSelectedItem().toString() : null );
                 		  recUpdt.setStaffType(tech1.getSelectedItem()!=null ? tech1.getSelectedItem().toString() : null );                 		  
                 		  
           				  try {
							recUpdt.setJoinDate(DateHelper.parse(jd1.getText()));
           				  } catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,"Invalid Date Format, Please enter in dd-Mmm-yyyy format");
           				  }
           				  byte[] image = icon.getImageBytes();
           				  if ( image != null ) 
           					  recUpdt.setImage(image);
           				  int updt = staffDao.update(recUpdt);
           				  if ( updt <= 0 )
           					  log.error("Record update failed");
           				  else
           					  log.info("Record updated sucessfully");
           				  
           				  populateList();
           				  setVisible( false );
           				  up.setEnabled(false);
                 	  
                	   }                       
                    }
                }
              );

             setBounds(250,250,270,120);
             setVisible(true);
           }
      }

  
      private Vector<Object> getDisplayColumns() {
  		Vector<Object> cols = new Vector<Object>();
  		cols.add("ID");
  		cols.add("NAME");
  		cols.add("DEPT");
  		cols.add("TEACHING");
  		cols.add("JOININGDATE");
  		return cols;
  	}
      private void populateList() {
  		List<StaffModel> records = staffDao.findAll();

  		if (records.isEmpty()) {
  			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
  					"STAFF DATABASE", JOptionPane.INFORMATION_MESSAGE);
  			return;
  		}

  		Vector<Object> rows = new Vector<Object>();
  		for (StaffModel row : records) {
  			Vector<Object> rowData = new Vector<Object>();
  			rowData.add(row.getId());
  			rowData.add(row.getName());
  			rowData.add(row.getDept());
  			rowData.add(row.getStaffType());  			
  	        rowData.add(DateHelper.format(row.getJoinDate()));
  	        
  			rows.add(rowData);
  		}
  		
  		staff = new JTable(rows,getDisplayColumns());
        staff.setBackground(Color.cyan);
        staff.setEnabled (false);
        p1.add(staff,BorderLayout.CENTER);
        sp = new JScrollPane( staff);
        p1.add(sp,BorderLayout.CENTER);
        validate();
  	}
    private class Myadapter extends WindowAdapter
      {
        public void windowClosing(WindowEvent wt)
          {
           System.exit(ExitStatus.FAIL);
          }
      }

      public static void main(String a[])
      {
        StaffDetails sd = new StaffDetails();
      }

}
