package org.ulibsoft.register.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Date;
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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.label.ButtonLabel;
import org.ulibsoft.core.ui.FilePreviewer;
import org.ulibsoft.core.ui.MyImageIcon;
import org.ulibsoft.dao.catalog.StudentDAO;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.login.Login;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.menu.StudentAccess;
import org.ulibsoft.model.StudentModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class StudentDetails extends JFrame
{
	 private static final Logger log = Logger.getLogger(StudentDetails.class.getName()) ;
     private String AD_NO;
     private JLabel adno, name, branch, year, doj, image ;
     private MyImageIcon icon;
     private JTextField  no, n1,d1 ;
     private JComboBox<String> b1,yr;
     private JButton ins, can, next,up,browse ;
     private JTable  sttable ;
     private JScrollPane sp ;
     private JPanel p1, p2;
     
     private Container  c ;
     public  StudentDetails parent;     
     private StudentDAO studentDao;
     private AuthDAO authDao;
    
     //private String PATH ;
     
     
     private ButtonActionListener btnLstnr;
     private String[] branches = new String[] { "", "CSE", "CSIT", "ECE",
 			"EEE", "MEC", "AE", "AEI", "ARC", "ACM", "BME", "BT", "CBE", "CEE",
 			"CHE", "CIV", "CPE", "CSS", "DT", "ECM", "ECS", "ECSE", "ETM",
 			"EIE", "FPT", "FT", "ICE", "INE", "IPE", "IST", "MCT", "MET",
 			"MIN", "MMC", "MMD", "MMT", "NVA", "PHM", "PTG", "PLG", "SCE",
 			"TEX" };

     public StudentDetails()
     {
         super("STUDENTS DETAILS");
         ScreenResolution.getResolution();
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         btnLstnr = new ButtonActionListener();         
         studentDao = DAOFactory.getDAOFactory().getStudentDAO();
         authDao = DAOFactory.getDAOFactory().getAuthDAO();         
         createComponents();
         componentListener();
         parent = this;
     }

     private void createComponents()
       {
         c = getContentPane( ) ;
         c.setBackground( new Color(0,0,40) ) ;
         c.setLayout ( new AbsoluteLayout( ) ) ;

         p1 = new JPanel( new AbsoluteLayout() );
         p1.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "STUDENT--DETAILS", 1, 2, c.getFont(), Color.magenta));
         p1.setBackground(c.getBackground());
         c.add(p1,new AbsoluteConstraints(172,50,456,220));

         //INITIALIZE STUDENT DETAILS

         adno = new JLabel ( "ADMISSION-NO" );
         adno.setForeground ( new Color ( 120, 120, 153 ) );
         adno.setHorizontalAlignment ( SwingConstants.RIGHT  );
         p1.add( adno, new AbsoluteConstraints(19+25 , 21+5) );

         no = new JTextField ( );
         no.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         p1.add (no,new AbsoluteConstraints( 115+10+15, 20+5, 140+10, 20 ) );

         name = new JLabel ( "STUDENT-NAME" );
         name.setForeground ( new Color ( 120, 120, 153 ) );
         name.setHorizontalAlignment ( SwingConstants.RIGHT );
         p1.add ( name, new AbsoluteConstraints( 15+10+15, 51+5 ) );

         n1 = new JTextField ( );
         n1.setEditable(false);
         n1.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
         n1.setForeground ( new Color ( 255, 0, 153 ) );
         n1.setCaretColor ( new Color ( 0, 204, 102 ) );
         p1.add ( n1, new AbsoluteConstraints( 115+10+15, 50+5, 140+10, 20 ) );

         image = new JLabel( );
         image.setBounds(245,20,100,113);
         image.setBorder(new MatteBorder (1, 1, 1, 1, Color.cyan));
         p1.add(image,new AbsoluteConstraints(275+20+15,20+5,100,110));
         
         doj = new JLabel ( "DATE-OF-JOIN" );
         doj.setForeground ( new Color ( 120, 120, 153 ) );
         doj.setHorizontalAlignment ( SwingConstants.RIGHT  );
         p1.add ( doj, new AbsoluteConstraints( 25+10+15, 81+5 ) );

         d1 = new JTextField ( );
         d1.setEditable(false);
         d1.setBorder ( new MatteBorder ( 1, 1, 1, 1,new Color(0,0,40) ) );
         d1.setForeground ( new Color ( 255, 0, 153 ) );
         d1.setCaretColor ( new Color ( 0, 204, 102 ) );
         p1.add ( d1, new AbsoluteConstraints( 115+10+15, 80+5,140+10, 20 ) );

         branch = new JLabel ( "COURSE" );
         branch.setForeground ( new Color ( 120, 120, 153 ) );
         p1.add ( branch, new AbsoluteConstraints( 57+10+15, 111+5 ) );

         b1 = new JComboBox<String> (branches);
         b1.setEnabled(false);
         b1.setEditable(true);
         
        //b1.setBackground( new Color( 0, 0, 40 ));
         b1.setForeground( new Color( 255, 0, 153 ));
         p1.add ( b1, new AbsoluteConstraints( 115+10+15,110+5,140+10,20 ));

         year = new JLabel( "SEMESTER" );
         year.setForeground ( new Color ( 120, 120, 153 ) );
         year.setHorizontalAlignment ( SwingConstants.RIGHT );
         p1.add ( year, new AbsoluteConstraints( 45+10+15,141+5 ) );

         yr = new JComboBox<String>();
         yr.setEnabled(false);
         yr.addItem("");
         yr.addItem ( "I" );
         yr.addItem ( "II-1" );
         yr.addItem ( "II-2" );
         yr.addItem ( "III-1" );
         yr.addItem ( "III-2" );
         yr.addItem( "IV-1" );
         yr.addItem ( "IV-2" );
         //yr.setBackground(new Color(0,0,40));
         yr.setForeground(new Color(255, 0, 153));
         p1.add ( yr, new AbsoluteConstraints( 115+10+15,138+5,140+10,20 ) );

         

         //BUTTON CREATION
         ins = new JButton( ButtonLabel.INSERT ) ;
         ins.setBackground (Color.cyan);
         ins.setForeground(Color.black);
         ins.setBorder(new BevelBorder(0));
         ins.setMnemonic(ButtonLabel.INSERT_MNMNC);
         ins.setEnabled (false);
         p1.add ( ins, new AbsoluteConstraints( 15+1, 180, 105, 27 ) );
         ins.addActionListener(btnLstnr);

         up = new JButton( ButtonLabel.UPDATE ) ;
         up.setBackground (Color.cyan);
         up.setForeground(Color.black);
         up.setBorder(new BevelBorder(0));
         up.setMnemonic(ButtonLabel.UPDATE_MNMNC);
         up.setEnabled (false);
         p1.add ( up, new AbsoluteConstraints( 122+1, 180, 105, 27 ) );
         up.addActionListener(btnLstnr);

         next = new JButton( ButtonLabel.NEXT ) ;
         next.setBackground ( Color.cyan);
         next.setForeground(Color.black);
         next.setMnemonic(ButtonLabel.NEXT_MNMNC);
         next.setBorder(new BevelBorder(0));
         p1.add ( next, new AbsoluteConstraints( 229+1, 180, 105, 27 ) );
         next.addActionListener(btnLstnr);
         
         browse = new JButton( ButtonLabel.BROWSE ) ;
         browse.setMnemonic (ButtonLabel.BROWSE_MNMNC);
         browse.setBackground ( Color.cyan  );
         browse.setForeground( Color.black );
         browse.setBorder ( new BevelBorder(0)  );
         browse.setEnabled (false);
         p1.add ( browse, new AbsoluteConstraints( 295+15,141, 101, 25) );
         browse.addActionListener(btnLstnr);
         
         can = new JButton( ButtonLabel.EXIT ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic(ButtonLabel.EXIT_MNMNC);
         can.setBorder(new BevelBorder(0));
         p1.add ( can, new AbsoluteConstraints( 335+2, 180, 105, 27 ) );
         can.addActionListener(btnLstnr);

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "STUDENT -- DATABASE", 1, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(80,300,630,230));
         setVisible(true);
         populateList();        
     }    
    private void populateRecord(StudentModel rec)
    {
    	no.setText(rec.getId());
    	n1.setText(rec.getName());
        b1.setSelectedItem(rec.getBranch());
        yr.setSelectedItem(rec.getYear());                
        d1.setText(DateHelper.format(rec.getJoinDate()));
        
        byte[] imageByte = rec.getImage();
		if ( imageByte != null )
		{
			icon = new MyImageIcon(rec.getImage());
			image.setIcon(icon);
		}else
			image.setIcon(null);		
        
        browse.setEnabled(true);

        n1.setEditable(true);
        b1.setEnabled(true);
        yr.setEnabled(true);
        d1.setEditable(true);

        up.setEnabled(true);
        n1.setEditable(true);
        d1.setEditable(true);
    }
    private Vector<Object> getDisplayColumns() {
		Vector<Object> cols = new Vector<Object>();
		cols.add("AD NO");
		cols.add("NAME");
		cols.add("BRANCH");
		cols.add("YEAR");
		return cols;
	}

	private void populateList() {
		List<StudentModel> records = studentDao.findAll();

		if (records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
					"STUDENTS DATABASE", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		Vector<Object> rows = new Vector<Object>();
		for (StudentModel row : records) {
			Vector<Object> rowData = new Vector<Object>();
			rowData.add(row.getId());
			rowData.add(row.getName());
			rowData.add(row.getBranch());
			rowData.add(row.getYear());
			rows.add(rowData);
		}

		sttable = new JTable(rows, getDisplayColumns());
		sttable.setBackground(Color.cyan);
		sttable.setEnabled(false);
		p2.add(sttable, BorderLayout.CENTER);
		sp = new JScrollPane(sttable);
		p2.add(sp, BorderLayout.CENTER);
		validate();

	}
   private void componentListener()
   {
      addMouseListener( new MouseAdapter()
      {
        public void mouseClicked(MouseEvent e)
          {
            AD_NO = Login.cname.getText().toUpperCase();
            no.setEditable(false);
            ins.setEnabled(false);
            next.setEnabled(false);
            browse.setEnabled(false);
            up.setEnabled(true);

            StudentModel rec = studentDao.findById(AD_NO);
            if ( rec != null )
            {
            	populateRecord(rec);
            }            
            if (e.isMetaDown())
             {
               PopUpMenu p=new PopUpMenu(e.getComponent(),e.getX(),e.getY());

             }
          }
      }
    );

    if( StudentAccess.access == 1 )
      {
         AD_NO = Login.cname.getText().toUpperCase();
         no.setEditable(false);
         ins.setEnabled(false);
         next.setEnabled(false);
         browse.setEnabled(false);
         up.setEnabled(true);

         StudentModel rec = studentDao.findById(AD_NO);
         if ( rec != null )
         {
        	 populateRecord(rec);
         }             
      }
   
    no.addActionListener(new ActionListener()
       {
         public void actionPerformed(ActionEvent e)
         {
             no.setText(no.getText().toUpperCase())  ;
             
            	  StudentModel rec = studentDao.findById(no.getText());
                  if ( rec != null )
                   {
                	  	populateRecord(rec);
                   }
                  else
                  {
                      int cfirm=JOptionPane.showConfirmDialog(null,"DO  U  WANT  TO  CREATE  NEW  RECORD","NEW RECORD",JOptionPane.YES_NO_OPTION);
                      if(cfirm==0)
                      {
                         n1.setText("");
                         d1.setText("");
                         n1.setEditable(true);
                         b1.setEnabled(true);
                         yr.setEnabled(true);
                         d1.setEditable(true);
                         browse.setEnabled(true);
                      }
                     else
                      {
                         ins.setEnabled(false);
                         up.setEnabled(true);
                      }
                  }                 
         }
       }
       );

       n1.addKeyListener(new KeyAdapter()
       {              
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
					if ( b1.getSelectedItem() == null || b1.getSelectedItem().toString().length() <= 0 )
					{
						JOptionPane.showMessageDialog(null,"Please select one item");
						return;	
					}					

					StudentModel recCreate = new StudentModel();
					recCreate.setId(no.getText().toUpperCase());
					recCreate.setName(n1.getText().toUpperCase());
					recCreate.setBranch(b1.getSelectedItem().toString());
					recCreate.setYear(yr.getSelectedItem().toString());
										
					try {
						Date doj = DateHelper.parse(d1.getText());
						recCreate.setJoinDate(doj);
						recCreate.setYearOfJoin(DateHelper.getYear(doj));
					} catch (ParseException e) {
						JOptionPane
						.showMessageDialog(null,
								"Invalid Date Format, Please enter in dd-Mmm-yyyy format");
						return;
					}
					recCreate.setStatus("TRUE");
					byte[] imageByte = icon.getImageBytes();
					if ( imageByte != null )
						recCreate.setImage(imageByte);
					
					
					studentDao.create(recCreate);

					n1.setEditable(false);
					d1.setEditable(false);
					populateList();
					ins.setEnabled(false);					
					new NewPassWordDialog((Frame) parent, "INPUT  PASSWORD");	
					
				}else if ( ButtonLabel.UPDATE.equals(name))
				{
					if(studentDao.hasBook(no.getText().toUpperCase()) || studentDao.hasCD(no.getText().toUpperCase()) || studentDao.hasMagazine(no.getText().toUpperCase()))
	                  {
	                	  JOptionPane.showMessageDialog(null,"HE / SHE HOLDS RECORDS .  .  .  .","BOOKS / CDS / MAGAZINES ",JOptionPane.ERROR_MESSAGE);
	                	  return;
	                  }
	                  else
	                  {
	                	  PassWordDialog pwd1 = new PassWordDialog( (Frame)parent,"PASSWORD CONFIRMATION" );
	                  }

	                if(StudentAccess.access==1)
	                  {
	                     up.setEnabled(true);
	                  }
					
				} else if (ButtonLabel.NEXT.equals(name)) {
					
					no.setText("");
					n1.setText("");
					no.setEditable(true);
					n1.setEditable(false);
					b1.setSelectedItem("");
					b1.setEnabled(false);
					d1.setEditable(false);
					image.setIcon(null);
					yr.setSelectedItem("");
					yr.setEnabled(false);
					d1.setText("");
					populateList();
					
				} else if ( ButtonLabel.BROWSE.equals(name) )
				{
					icon = FilePreviewer.loadImage(200,213);
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

			sub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

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
					
					StudentModel recUpdate = studentDao.findById(no.getText().toUpperCase());
					if ( recUpdate == null )
					{
						log.warn("Record doesn't exist, update failed");
					}else
					{
						recUpdate.setAnswer(input.getText().toUpperCase());
						recUpdate.setQuestion(String.valueOf(question.getSelectedIndex()));
						recUpdate.setPassword(new String(new1.getPassword()));
						int updt = studentDao.update(recUpdate);
						if ( updt <= 0 )
							log.error("Record update failed");
						else
							log.info("Record updated sucessfully");
					}
						
						
										
					setVisible(false);				
				}
			});

             addWindowListener( adap);
             setBounds(230,200,350,190);
             setVisible(true);

           }
     private class MyAdapter extends WindowAdapter
     {
           public void windowClosing(WindowEvent we)
               {
                int weq=JOptionPane.showConfirmDialog(child,"IF  U  CLICK  OK  UR  RECORD  WILL  BE  DELETED ","WARNING",JOptionPane.OK_CANCEL_OPTION);
                if(weq==0)
                {
                   int del = studentDao.delete(no.getText().toUpperCase());
                   if ( del > 0 )
                	   log.info("Record Deleted Sucessfully ");
                }
                else
                {
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
         public PassWordDialog( Frame parent,String title )
           {
              super( parent,title,true );
              
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
                        
                      if( !authDao.authenticateStudentUser(no.getText().toUpperCase(), new String( pwdfd.getPassword() ) ) )
                      {
                    	  JOptionPane.showMessageDialog(null,"INVALID PASSWORD");
                    	  return;
                      }           
                	  StudentModel recUpdt = studentDao.findById(no.getText().toUpperCase());
                	  if ( recUpdt == null )
                	  {
                		  log.warn("Record doesn't exist, update failed");
                	  }
                	  else
                	  {
                		  recUpdt.setName(n1.getText ().toUpperCase());
                		  recUpdt.setBranch(b1.getSelectedItem()!=null ? b1.getSelectedItem().toString() : null );
                		  recUpdt.setYear( yr.getSelectedItem () != null ? yr.getSelectedItem ().toString() : null );
                		          				 
          				  try {
          					  	Date doj = DateHelper.parse(d1.getText());
          						recUpdt.setJoinDate(doj);
                				recUpdt.setYearOfJoin(DateHelper.getYear(doj));
          				  } catch (ParseException e1) {
          						JOptionPane
          								.showMessageDialog(null,
          										"Invalid Date Format, Please enter in dd-Mmm-yyyy format");
          						return;
          				  }
          				  
          				byte[] image = icon.getImageBytes();
         				  if ( image != null ) 
         					  recUpdt.setImage(image);
          				  
          				  int updt = studentDao.update(recUpdt);
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

   public static void main(String ar[])
     {
         StudentDetails st=new StudentDetails();
     }
}
