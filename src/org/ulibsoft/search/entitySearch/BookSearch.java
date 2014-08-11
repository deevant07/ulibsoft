package org.ulibsoft.search.entitySearch;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKTransMemberModel;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class BookSearch extends JFrame
  {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1101302846828614679L;
	private JLabel bn,an,code;
     private JButton next,quit;
     private CustomTable table;
     private JPanel pl,cmppn;
     private Container c;

     private Choice b1,a1;
     private JTextField code1;
     public static BookSearch bsch;
     
     private BookTransactionSearchDAO bkTranSrchDao;

     public BookSearch()
       {
          super("SEARCHING FOR A BOOK");
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
          bkTranSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();
          setVisible(true);          
          createComponents();
          componentListener();
          MyAdapter7 myap = new MyAdapter7();
          addWindowListener(myap);
       }

     private void createComponents()
       {
          c = getContentPane();
          c.setLayout(new AbsoluteLayout());
          c.setBackground(new Color(0, 0, 40));

          cmppn = new JPanel(new AbsoluteLayout());
          cmppn.setBackground(c.getBackground());
          cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "SEARCH-BY-TITLE & AUTHOR", 1, 2,c.getFont() , Color.magenta));
          c.add(cmppn,new AbsoluteConstraints(245,30,310,200));

          an = new JLabel ();
          an.setText ( "AUTHOR- NAME" );
          an.setHorizontalAlignment ( SwingConstants.CENTER );
          an.setForeground (new Color ( 120,120, 153 ) );
          cmppn.add ( an ,new AbsoluteConstraints(32,30));

          a1 = new Choice();
          a1.setForeground ( new Color ( 255, 0, 153 ) );
          a1.addItem("");
          Set<String> items = new HashSet<>();

          items = bkTranSrchDao.findCurrentAuthorList();
          if ( items != null && items.size() > 0 )
          {
        	  for ( String itm : items)
        		  a1.addItem(itm);
          }
          cmppn.add ( a1 ,new AbsoluteConstraints(130,25,150,20));

          bn = new JLabel ();
          bn.setText ( "BOOK-NAME" );
          bn.setForeground (new Color ( 120,120, 153 ) );
          cmppn.add(bn,new AbsoluteConstraints(50,60));

          b1 = new Choice();
          b1.setForeground (new Color ( 255, 0, 153 ) );
          cmppn.add (b1,new AbsoluteConstraints( 130,55,150,20 ));



          code = new JLabel ();
          code.setText ( "CODE" );
          code.setForeground (new Color ( 120,120, 153 ) );
          cmppn.add(code,new AbsoluteConstraints(90,90));

          code1 = new JTextField();
          code1.setForeground ( new Color ( 255, 0, 153 ) );
          code1.setBorder (new MatteBorder (1, 1, 1, 1, new Color(0, 0, 40)));
          cmppn.add ( code1 ,new AbsoluteConstraints(130,87,150,20));


          
          next = new JButton( "NEXT>>>" ) ;
          next.setBackground ( Color.cyan );
          next.setForeground ( Color.black );
          next.setBorder ( new BevelBorder(0) );
          next.setMnemonic('N');
          cmppn.add ( next, new AbsoluteConstraints( 55,120,100,27 ) );

          quit = new JButton( "QUIT" ) ;
          quit.setBackground(Color.cyan );
          quit.setForeground ( Color.black );
          quit.setBorder ( new BevelBorder(0) );
          quit.setMnemonic('Q');
          cmppn.add ( quit, new AbsoluteConstraints( 160,120,100,27 ) );

          c.add( pl=new JPanel(new BorderLayout()),new AbsoluteConstraints(100,270,600,250));
          pl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "TABLE-DISPLAY", 1, 2, c.getFont(),Color.magenta));
          pl.setBackground(c.getBackground());

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
                  }
               }
           }
         );

         a1.addItemListener ( new ItemListener()
          {
            public void itemStateChanged( ItemEvent e )
              {
            	Set<String> items = new HashSet<>();

                items = bkTranSrchDao.findCurrentBookNameList(a1.getSelectedItem());
                if ( items != null && items.size() > 0 )
                {
              	  for ( String itm : items)
              		  b1.addItem(itm);
                }
            	
              }
           }
         );

         b1.addItemListener(new ItemListener()
           {
              public void itemStateChanged(ItemEvent ie)
                {
            	  List<BKTransMemberModel> transMemMdls = bkTranSrchDao.findMembersPerBook(b1.getSelectedItem(), a1.getSelectedItem());
             	 if ( transMemMdls != null && transMemMdls.size() > 0 )
             	 {
             		 populateList(transMemMdls);             		 
             	 }else
             		 JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .");                  
                }
           }
         );
         code1.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e)
             {
            	 List<BKTransMemberModel> transMemMdls = bkTranSrchDao.findMembersPerBook(code1.getText());
            	 if ( transMemMdls != null && transMemMdls.size() > 0 )
            	 {
            		 populateList(transMemMdls);            		 
            	 }else
            		 JOptionPane.showMessageDialog(null,"NO  RECORDS  TO  DISPLAY !  .  .  .");             	 
             }
         }
         );         
         next.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
                   //BookSearch b =new BookSearch();
                   //setVisible(false);
                    b1.removeAll();
                    code1.setText("");           
                }
            }
         );

         quit.addActionListener( new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
               {
                 setVisible(false);
               }
           }
         );

       }
     private Vector<String> getDisplayColumns() {
 		Vector<String> cols = new Vector<>();
 		cols.add("ID");
 		cols.add("Name");
 		cols.add("Dept");
 		cols.add("Year");
 		cols.add("Code");
 		cols.add("Book Name");
 		cols.add("Author");
 		cols.add("Issued Date");
 		cols.add("Return Date");
 		return cols;
 	}
     private void populateList(List<BKTransMemberModel> records) {
 	
     	if (records.isEmpty()) {
 			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY !...",
 					"BOOK(S) NOT AVAILABLE", JOptionPane.INFORMATION_MESSAGE);
 			return;
 		}

 		Vector<Vector<Object>> rows = new Vector<>();
 		for (BKTransMemberModel row : records) {
 			Vector<Object> rowData = new Vector<Object>();
 			rowData.add(row.getId());
 			rowData.add(row.getName());
 			rowData.add(row.getDept());
 			if ( "1".equals(row.getYear()) )
 				rowData.add("");
 			else
 				rowData.add(row.getYear());
 			rowData.add(row.getCode());
 			rowData.add(row.getBookName());
 			rowData.add(row.getAuthorSurname() + " " + row.getAuthorName() );
 			rowData.add(DateHelper.format(row.getIssuedDate()));
 			rowData.add(DateHelper.format(row.getReturnDate()));
 			rows.add(rowData);
 		}
 		
 		table = new CustomTable(getDisplayColumns(),"BOOK TRANSACTION SEARCH");
 		table.populateData(rows);
 		pl.add(table.getPanel(),BorderLayout.CENTER);
        validate();
 	}
     private class MyAdapter7 extends WindowAdapter
       {
         public void windowClosing(WindowEvent wt)
           {
             setVisible(false);
           }
       }

      public static void main(String s[])
      {
       BookSearch b=new BookSearch();
       }
  }