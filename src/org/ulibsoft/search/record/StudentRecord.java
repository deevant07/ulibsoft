package org.ulibsoft.search.record;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.ulibsoft.core.ui.CustomTable;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.transaction.TransactionHistorySearchDAO;
import org.ulibsoft.model.TransMemberModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class StudentRecord extends JFrame
{
     /**
	 * 
	 */
	private static final long serialVersionUID = -2063759837934677570L;
	private JLabel adno;
     private JTextField no;
     private JButton find,can,next;
     private JButton but1,but2,print1;
     private JLabel but0;
     private JPanel cmppn,p2;
     private CustomTable sttable;
     private Container c;

     private TransactionHistorySearchDAO tranHstrySrchDAO;

     public StudentRecord()
       {
           super("Student Record");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
           tranHstrySrchDAO = DAOFactory.getDAOFactory().getTranHistorySrchDAO();
         createComponents(null);         
         componentListener();
       }
     public StudentRecord(String adno)
     {
         super("Student Record");
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
         tranHstrySrchDAO = DAOFactory.getDAOFactory().getTranHistorySrchDAO();
       createComponents(adno);         
       componentListener();
     }
      private void createComponents(String adnoValue)
      {
         c = getContentPane( ) ;
         c.setBackground( new Color(0,0,40) ) ;
         c.setLayout ( new AbsoluteLayout( ) ) ;

         cmppn = new JPanel( new AbsoluteLayout() );
         cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "STUDENT RECORD", 1, 2, c.getFont(), Color.magenta));
         cmppn.setBackground(c.getBackground());
         c.add(cmppn,new AbsoluteConstraints(180,70+20,420,135));

         p2 = new JPanel( new BorderLayout() );
         p2.setBackground(c.getBackground());
         p2.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "PROJECTION  OF  DATABASE", 2, 2, c.getFont(), Color.magenta));
         c.add(p2,new AbsoluteConstraints(90,215+30,600,300));

           but0 = new JLabel( "      SEARCH  STUDENT  RECORD" ) ;
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


         adno = new JLabel ( "ADMISSION-NO" );
         adno.setForeground ( new Color ( 120, 120, 153 ) );
         adno.setHorizontalAlignment ( SwingConstants.RIGHT  );
         cmppn.add( adno, new AbsoluteConstraints(30, 30 ) );

         no = new JTextField ( );
         if ( adnoValue != null )
        	 {
        	 	no.setText(adnoValue);
        	 	no.setEditable(false);
        	 }
         
         no.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
         no.setForeground ( new Color ( 255, 0, 153 ) );
         no.setCaretColor ( new Color ( 0, 204, 102 ) );
         cmppn.add (no,new AbsoluteConstraints( 130, 28, 150, 20 ) );

         find = new JButton( "FIND" ) ;
         find.setBackground ( Color.cyan );
         find.setForeground( Color.black );
         find.setBorder( new BevelBorder( 0 ));
         cmppn.add ( find, new AbsoluteConstraints( 285,28,100,22 ) );

         print1 = new JButton( "PRINT" ) ;
         print1.setBackground (Color.cyan);
         print1.setForeground(Color.black);
         print1.setEnabled(false);
         print1.setBorder(new BevelBorder(0));
         print1.setMnemonic('P');

         cmppn.add ( print1, new AbsoluteConstraints( 60,60,100,25 ) );
        
         next = new JButton( "NEXT>>>" ) ;
         next.setBackground ( Color.cyan);
         next.setForeground(Color.black);
         next.setMnemonic('N');
         next.setBorder(new BevelBorder(0));
         cmppn.add ( next, new AbsoluteConstraints( 162, 60, 100, 25 ) );

         can = new JButton( "EXIT" ) ;
         can.setBackground ( Color.cyan);
         can.setForeground( Color.black);
         can.setMnemonic('X');
         can.setBorder(new BevelBorder(0));
         cmppn.add ( can, new AbsoluteConstraints( 264, 60, 100, 25 ) );

         setVisible(true);
      }
      private void componentListener()
      {
           no.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
            	   List<TransMemberModel> transMdls = tranHstrySrchDAO.listPerStudent(no.getText().toUpperCase());
            	   if ( transMdls != null && transMdls.size() > 0 )
            	   {
            		   populateList(transMdls);           
            		   print1.setEnabled(true);
            	   }else
            		   JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !  .  .  .");                  
               }
           }
           );

           find.addActionListener(new ActionListener()
           {
               public void actionPerformed(ActionEvent e)
               {
            	   List<TransMemberModel> transMdls = tranHstrySrchDAO.listPerStudent(no.getText().toUpperCase());
            	   if ( transMdls != null && transMdls.size() > 0 )
            	   {
            		   populateList(transMdls);         
            		   print1.setEnabled(true);
            	   }else
            		   JOptionPane.showMessageDialog(null,"NO RECORDS TO DISPLAY !  .  .  .");              
               }
           }
           );

          
          next.addActionListener( new ActionListener()
             {
                public void actionPerformed( ActionEvent e )
                  {
                     no.setText("");
                     print1.setEnabled(false);
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
 		cols.add("Code");
 		cols.add("Name");
 		cols.add("Author/Version");
 		cols.add("Issued Date");
 		cols.add("Return Date");
 		cols.add("Type");
 		return cols;
 	}
     private void populateList(List<TransMemberModel> records) {
    		
     	if (records.isEmpty()) {
 			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
 					"STUDENT TRANSACTION HISTORY NOT AVAILABLE", JOptionPane.INFORMATION_MESSAGE);
 			return;
 		}

 		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
 		for (TransMemberModel row : records) {
 			Vector<Object> rowData = new Vector<Object>();
 			rowData.add(row.getCode());
 			rowData.add(row.getItemName());
 			rowData.add(row.getSpec()); 			
 			rowData.add(DateHelper.format(row.getIssuedDate()));
 			rowData.add(DateHelper.format(row.getReturnDate()));
 			rowData.add(row.getType());
 			rows.add(rowData);
 		}
 		        
        sttable = new CustomTable(getDisplayColumns(),"STUDENT TRANSACTION HISTORY");
		sttable.populateData(rows);		
        p2.add(sttable.getPanel(),BorderLayout.CENTER);
        validate();
 	}
     public static void main( String a[] )
       {
          StudentRecord bk = new StudentRecord();
       }
  }
