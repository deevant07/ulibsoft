package org.ulibsoft.register.ui;

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
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.lang3.math.NumberUtils;
import org.ulibsoft.core.ui.MyImageIcon;
import org.ulibsoft.dao.catalog.BookCatalogDAO;
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.dao.search.BookCatalogSearchDAO;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.menu.PopUpMenu;
import org.ulibsoft.model.BKStaffModel;
import org.ulibsoft.model.BKStudentModel;
import org.ulibsoft.model.BKTransactionModel;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.model.KeyConstraints;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class Library2 extends JFrame
  {
     private int first,second;

     private JLabel  bookname,aut1s,aut1f,aut2s,aut2f,aut3s,aut3f,aut4s,aut4f,aut5f,aut5s;
     private JLabel  volume,series,src_pub,bill_no,price,call_no,with_draw,rec_dt;
     private JLabel  acess,pub,edt,isbn,note,des,phy,place,year;
     private JLabel  sub1,sub2,sub3,xyz;

     private JLabel adno,name,image,branch,year2;
     private JLabel adno1,name1,branch1,year3;
     private Icon   icon1,icon;
     private JLabel adno2,name2,image1,branch2,year4;
     private JLabel adno3,name3,branch3,year5;

     private JLabel msg,msg1;

     private JComboBox<String> bookname1;
     private JComboBox<String> aut1s1,aut1f1,aut2s1,aut2f1,aut3s1,aut3f1,aut4s1,aut4f1,aut5s1,aut5f1;
     private JTextField volume2,series2,src_pub2,bill_no2,price2,call_no2,with_draw2,rec_dt2;
     private JTextField acess1,acess2,edt1,isbn1,year1,sub31,sub22;
     private JComboBox<String> pub1,phy1,place1;
     private JComboBox<String> sub11,sub21;
     private JTextField des1,note1;
     private JTextField title,aut1,aut2,aut3,aut4,aut5,aut6,aut7,aut8,aut9,aut10,place2,pub2;
     private String TITLE="",AUT1="",AUT2="",AUT3="",AUT4="",AUT5="",AUT6="",AUT7="",AUT8="",AUT9="",AUT10="";
     private String PLACE="",PUB="",SUB2="",PATH;

     private JButton ins,next,quit,up,mis;
     private JTable lib;
     private JPanel pnl,bkpn,despn;
     private Container  c ;     
     private String student,staff;
     private static int loop;
     
     private BookCatalogSearchDAO bkSrchDao;
     private BookCatalogDAO bkCatlogDao;
     private BookTransactionSearchDAO bkTranSrchDao;
     private BookTransactionDAO bkTransDao;
     
     public Library2()
       {
          super( "BOOKS ACCESSING" );
          ScreenResolution.getResolution();
          setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
          setVisible(true);
          bkSrchDao = DAOFactory.getDAOFactory().getBkCatlogSearchDAO();
          bkCatlogDao = DAOFactory.getDAOFactory().getBookCatalogDAO();
          bkTranSrchDao = DAOFactory.getDAOFactory().getBkTranSrchDAO();          
          bkTransDao = DAOFactory.getDAOFactory().getBKTransDAO();
          
          createComponents();
          sequenceGenerator();
          componentListener();
          populateList();                   
       }

	private void createComponents() {
		c = getContentPane();
		c.setBackground(new Color(0, 0, 40));
		c.setLayout(new AbsoluteLayout());

		// INITIALIZING BOOKDETAILS

		bkpn = new JPanel(new AbsoluteLayout());
		bkpn.setBackground(c.getBackground());
		JScrollPane spn = new JScrollPane(bkpn);
		spn.setBackground(new Color(0, 0, 40));
		spn.setBorder(new TitledBorder(new EtchedBorder(Color.cyan, new Color(
				0, 0, 40)), "INSERTION", 1, 2, c.getFont(), Color.magenta));
		c.add(spn, new AbsoluteConstraints(90, 30, 630, 327 + 7));

		acess = new JLabel("<==  ACCESS-NO  ==>");
		acess.setForeground(new Color(120, 120, 153));
		bkpn.add(acess, new AbsoluteConstraints(15, 21 - 18 + 4));

		acess1 = new JTextField();
		acess1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		acess1.setForeground(new Color(255, 0, 153));
		acess1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(acess1, new AbsoluteConstraints(145, 18 - 18 + 4, 73, 20));

		acess2 = new JTextField();
		acess2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		acess2.setForeground(new Color(255, 0, 153));
		acess2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(acess2, new AbsoluteConstraints(145 + 70 + 7, 18 + 4 - 18, 73,
				20));

		edt = new JLabel("<= BOOK  EDITION =>");
		edt.setForeground(new Color(120, 120, 153));
		bkpn.add(edt, new AbsoluteConstraints(310, 21 - 18 + 4));

		edt1 = new JTextField();
		edt1.setEditable(false);
		edt1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		edt1.setForeground(new Color(255, 0, 153));
		edt1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(edt1, new AbsoluteConstraints(440, 18 - 18 + 4, 150, 20));

		bookname = new JLabel("<== BOOK  TITLE ==>");
		bookname.setForeground(new Color(120, 120, 153));
		bkpn.add(bookname, new AbsoluteConstraints(15, 48 - 18 + 4));

		bookname1 = new JComboBox<String>();
		bookname1.setEditable(false);
		bookname1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		bookname1.setForeground(new Color(255, 0, 153));
		bookname1.setVisible(false);
		bkpn.add(bookname1, new AbsoluteConstraints(145, 46 + 4 - 18, 445, 20));

		title = new JTextField();
		title.setEditable(true);
		title.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		title.setForeground(new Color(255, 0, 153));
		title.setCaretColor(new Color(0, 204, 102));
		bkpn.add(title, new AbsoluteConstraints(145, 46 + 4 - 18, 445, 20));

		volume = new JLabel("<===  VOLUME : ===>");
		volume.setForeground(new Color(120, 120, 153));
		bkpn.add(volume, new AbsoluteConstraints(15, 59 + 4));

		volume2 = new JTextField();
		volume2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		volume2.setForeground(new Color(255, 0, 153));
		volume2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(volume2, new AbsoluteConstraints(145, 57 + 4, 150, 20));

		series = new JLabel("==>  BOOK  SERIES  : : ");
		series.setForeground(new Color(120, 120, 153));
		bkpn.add(series, new AbsoluteConstraints(310, 59 + 4));

		series2 = new JTextField();
		series2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		series2.setForeground(new Color(255, 0, 153));
		series2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(series2, new AbsoluteConstraints(440, 57 + 4, 150, 20));

		aut1s = new JLabel("AUTHOR1-SURNAME");
		aut1s.setForeground(new Color(120, 120, 153));
		bkpn.add(aut1s, new AbsoluteConstraints(15, 74 + 17));

		aut1s1 = new JComboBox<String>();
		aut1s1.setEditable(false);
		aut1s1.setVisible(false);
		aut1s1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut1s1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut1s1, new AbsoluteConstraints(145, 72 + 17, 150, 20));

		aut1 = new JTextField();
		aut1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut1.setForeground(new Color(255, 0, 153));
		aut1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut1, new AbsoluteConstraints(145, 72 + 17, 150, 20));

		aut1f = new JLabel("AUTHOR1-REALNAME");
		aut1f.setForeground(new Color(120, 120, 153));
		bkpn.add(aut1f, new AbsoluteConstraints(310, 74 + 17));

		aut1f1 = new JComboBox<String>();
		aut1f1.setEditable(false);
		aut1f1.setVisible(false);
		aut1f1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut1f1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut1f1, new AbsoluteConstraints(440, 72 + 17, 150, 20));

		aut2 = new JTextField();
		aut2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut2.setForeground(new Color(255, 0, 153));
		aut2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut2, new AbsoluteConstraints(440, 72 + 17, 150, 20));

		aut2s = new JLabel("AUTHOR2-SURNAME");
		aut2s.setForeground(new Color(120, 120, 153));
		bkpn.add(aut2s, new AbsoluteConstraints(15, 101 + 17));

		aut2s1 = new JComboBox<String>();
		aut2s1.setEditable(false);
		aut2s1.setVisible(false);
		aut2s1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut2s1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut2s1, new AbsoluteConstraints(145, 99 + 17, 150, 20));

		aut3 = new JTextField();
		aut3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut3.setForeground(new Color(255, 0, 153));
		aut3.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut3, new AbsoluteConstraints(145, 99 + 17, 150, 20));

		aut2f = new JLabel("AUTHOR2-REALNAME");
		aut2f.setForeground(new Color(120, 120, 153));
		bkpn.add(aut2f, new AbsoluteConstraints(310, 101 + 17));

		aut2f1 = new JComboBox<String>();
		aut2f1.setEditable(false);
		aut2f1.setVisible(false);
		aut2f1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut2f1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut2f1, new AbsoluteConstraints(440, 99 + 17, 150, 20));

		aut4 = new JTextField();
		aut4.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut4.setForeground(new Color(255, 0, 153));
		aut4.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut4, new AbsoluteConstraints(440, 99 + 17, 150, 20));

		aut3s = new JLabel("AUTHOR3-SURNAME");
		aut3s.setForeground(new Color(120, 120, 153));
		bkpn.add(aut3s, new AbsoluteConstraints(15, 128 + 17));

		aut3s1 = new JComboBox<String>();
		aut3s1.setEditable(false);
		aut3s1.setVisible(false);
		aut3s1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut3s1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut3s1, new AbsoluteConstraints(145, 126 + 17, 150, 20));

		aut5 = new JTextField();
		aut5.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut5.setForeground(new Color(255, 0, 153));
		aut5.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut5, new AbsoluteConstraints(145, 126 + 17, 150, 20));

		aut3f = new JLabel("AUTHOR3-REALNAME");
		aut3f.setForeground(new Color(120, 120, 153));
		bkpn.add(aut3f, new AbsoluteConstraints(310, 128 + 17));

		aut3f1 = new JComboBox<String>();
		aut3f1.setEditable(false);
		aut3f1.setVisible(false);
		aut3f1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut3f1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut3f1, new AbsoluteConstraints(440, 126 + 17, 150, 20));

		aut6 = new JTextField();
		aut6.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut6.setForeground(new Color(255, 0, 153));
		aut6.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut6, new AbsoluteConstraints(440, 126 + 17, 150, 20));

		aut4s = new JLabel("AUTHOR4-SURNAME");
		aut4s.setForeground(new Color(120, 120, 153));
		bkpn.add(aut4s, new AbsoluteConstraints(15, 128 + 17 + 27));

		aut4s1 = new JComboBox<String>();
		aut4s1.setEditable(false);
		aut4s1.setVisible(false);
		aut4s1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut4s1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut4s1, new AbsoluteConstraints(145, 126 + 17 + 27, 150, 20));

		aut7 = new JTextField();
		aut7.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut7.setForeground(new Color(255, 0, 153));
		aut7.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut7, new AbsoluteConstraints(145, 126 + 17 + 27, 150, 20));

		aut4f = new JLabel("AUTHOR4-REALNAME");
		aut4f.setForeground(new Color(120, 120, 153));
		bkpn.add(aut4f, new AbsoluteConstraints(310, 128 + 17 + 27));

		aut4f1 = new JComboBox<String>();
		aut4f1.setEditable(false);
		aut4f1.setVisible(false);
		aut4f1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut4f1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut4f1, new AbsoluteConstraints(440, 126 + 17 + 27, 150, 20));

		aut8 = new JTextField();
		aut8.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut8.setForeground(new Color(255, 0, 153));
		aut8.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut8, new AbsoluteConstraints(440, 126 + 17 + 27, 150, 20));

		aut5s = new JLabel("AUTHOR5-SURNAME");
		aut5s.setForeground(new Color(120, 120, 153));
		bkpn.add(aut5s, new AbsoluteConstraints(15, 128 + 17 + 27 + 27));

		aut5s1 = new JComboBox<String>();
		aut5s1.setEditable(false);
		aut5s1.setVisible(false);
		aut5s1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut5s1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut5s1, new AbsoluteConstraints(145, 126 + 17 + 27 + 27, 150,
				20));

		aut9 = new JTextField();
		aut9.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut9.setForeground(new Color(255, 0, 153));
		aut9.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut9,
				new AbsoluteConstraints(145, 126 + 17 + 27 + 27, 150, 20));

		aut5f = new JLabel("AUTHOR5-REALNAME");
		aut5f.setForeground(new Color(120, 120, 153));
		bkpn.add(aut5f, new AbsoluteConstraints(310, 128 + 17 + 27 + 27));

		aut5f1 = new JComboBox<String>();
		aut5f1.setEditable(false);
		aut5f1.setVisible(false);
		aut5f1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut5f1.setForeground(new Color(255, 0, 153));
		bkpn.add(aut5f1, new AbsoluteConstraints(440, 126 + 17 + 27 + 27, 150,
				20));

		aut10 = new JTextField();
		aut10.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		aut10.setForeground(new Color(255, 0, 153));
		aut10.setCaretColor(new Color(0, 204, 102));
		bkpn.add(aut10, new AbsoluteConstraints(440, 126 + 17 + 27 + 27, 150,
				20));

		isbn = new JLabel("<== BOOK  ISBN ==>");
		isbn.setForeground(new Color(120, 120, 153));
		bkpn.add(isbn, new AbsoluteConstraints(15, 155 + 17 + 27 + 27));

		isbn1 = new JTextField();
		isbn1.setEditable(false);
		isbn1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		isbn1.setForeground(new Color(255, 0, 153));
		isbn1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(isbn1, new AbsoluteConstraints(145, 153 + 17 + 27 + 27, 150,
				20));

		pub = new JLabel("=>  BOOK-PUBLISHER");
		pub.setForeground(new Color(120, 120, 153));
		bkpn.add(pub, new AbsoluteConstraints(310, 155 + 17 + 27 + 27));

		pub1 = new JComboBox<String>();
		pub1.setEditable(false);
		pub1.setVisible(false);
		pub1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		pub1.setForeground(new Color(255, 0, 153));
		bkpn.add(pub1,
				new AbsoluteConstraints(440, 153 + 17 + 27 + 27, 150, 20));

		pub2 = new JTextField();

		pub2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		pub2.setForeground(new Color(255, 0, 153));
		pub2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(pub2,
				new AbsoluteConstraints(440, 153 + 17 + 27 + 27, 150, 20));

		place = new JLabel("PUBLISHED -- PLACE");
		place.setForeground(new Color(120, 120, 153));
		bkpn.add(place, new AbsoluteConstraints(15, 182 + 17 + 27 + 27));

		place1 = new JComboBox<String>();
		place1.setEditable(false);
		place1.setVisible(false);
		place1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		place1.setForeground(new Color(255, 0, 153));
		bkpn.add(place1, new AbsoluteConstraints(145, 180 + 17 + 27 + 27, 150,
				20));

		place2 = new JTextField();
		place2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		place2.setForeground(new Color(255, 0, 153));
		bkpn.add(place2, new AbsoluteConstraints(145, 180 + 17 + 27 + 27, 150,
				20));

		year = new JLabel(">>> PUBLISHED YEAR");
		year.setForeground(new Color(120, 120, 153));
		bkpn.add(year, new AbsoluteConstraints(310, 182 + 17 + 27 + 27));

		year1 = new JTextField();
		year1.setEditable(false);
		year1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		year1.setForeground(new Color(255, 0, 153));
		year1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(year1, new AbsoluteConstraints(440, 180 + 17 + 27 + 27, 150,
				20));

		phy = new JLabel("PHYSICAL -- MEDIUM");
		phy.setForeground(new Color(120, 120, 153));
		bkpn.add(phy, new AbsoluteConstraints(15, 209 + 17 + 27 + 27));

		phy1 = new JComboBox<String>();
		phy1.setEditable(false);
		phy1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		phy1.setForeground(new Color(255, 0, 153));
		bkpn.add(phy1,
				new AbsoluteConstraints(145, 207 + 17 + 27 + 27, 150, 20));

		sub1 = new JLabel("<==DEPARTMENT==>");
		sub1.setForeground(new Color(120, 120, 153));
		bkpn.add(sub1, new AbsoluteConstraints(310, 209 + 17 + 27 + 27));

		sub11 = new JComboBox<String>();
		sub11.setEditable(false);
		sub11.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		sub11.setForeground(new Color(255, 0, 153));
		bkpn.add(sub11, new AbsoluteConstraints(440, 207 + 17 + 27 + 27, 150,
				20));

		sub2 = new JLabel("<==  :  COURSE  :  ==>");
		sub2.setForeground(new Color(120, 120, 153));
		bkpn.add(sub2, new AbsoluteConstraints(15, 236 + 17 + 27 + 27));

		sub21 = new JComboBox<String>();
		sub21.setEditable(true);
		sub21.setVisible(false);
		sub21.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		sub21.setForeground(new Color(255, 0, 153));
		bkpn.add(sub21, new AbsoluteConstraints(145, 234 + 17 + 27 + 27, 150,
				20));

		sub22 = new JTextField();
		sub22.setEditable(false);
		sub22.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		sub22.setForeground(new Color(255, 0, 153));
		sub22.setCaretColor(new Color(0, 204, 102));
		bkpn.add(sub22, new AbsoluteConstraints(145, 234 + 17 + 27 + 27, 150,
				20));

		sub3 = new JLabel("<= SPECIFICATION =>");
		sub3.setForeground(new Color(120, 120, 153));
		bkpn.add(sub3, new AbsoluteConstraints(310, 236 + 17 + 27 + 27));

		sub31 = new JTextField();
		sub31.setEditable(false);
		sub31.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		sub31.setForeground(new Color(255, 0, 153));
		sub31.setCaretColor(new Color(0, 204, 102));
		bkpn.add(sub31, new AbsoluteConstraints(440, 234 + 17 + 27 + 27, 150,
				20));

		src_pub = new JLabel("SOURCE  OF  SUPPLY :");
		src_pub.setForeground(new Color(120, 120, 153));
		bkpn.add(src_pub, new AbsoluteConstraints(15, 263 + 17 + 27 + 27));

		src_pub2 = new JTextField();
		src_pub2.setEditable(false);
		src_pub2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		src_pub2.setForeground(new Color(255, 0, 153));
		src_pub2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(src_pub2, new AbsoluteConstraints(145, 261 + 17 + 27 + 27,
				150, 20));

		price = new JLabel("==>  PRICE  OF  BOOK ");
		price.setForeground(new Color(120, 120, 153));
		bkpn.add(price, new AbsoluteConstraints(310, 263 + 17 + 27 + 27));

		price2 = new JTextField();
		price2.setEditable(false);
		price2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		price2.setForeground(new Color(255, 0, 153));
		price2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(price2, new AbsoluteConstraints(440, 261 + 17 + 27 + 27, 150,
				20));

		bill_no = new JLabel("==>  BILL  NUMBER : :");
		bill_no.setForeground(new Color(120, 120, 153));
		bkpn.add(bill_no, new AbsoluteConstraints(15, 263 + 17 + 54 + 27));

		bill_no2 = new JTextField();
		bill_no2.setEditable(false);
		bill_no2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		bill_no2.setForeground(new Color(255, 0, 153));
		bill_no2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(bill_no2, new AbsoluteConstraints(145, 261 + 17 + 54 + 27,
				150, 20));

		call_no = new JLabel("==>  CALL  NUMBER : :");
		call_no.setForeground(new Color(120, 120, 153));
		bkpn.add(call_no, new AbsoluteConstraints(310, 263 + 17 + 54 + 27));

		call_no2 = new JTextField();
		call_no2.setEditable(false);
		call_no2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		call_no2.setForeground(new Color(255, 0, 153));
		call_no2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(call_no2, new AbsoluteConstraints(440, 261 + 17 + 54 + 27,
				150, 20));

		with_draw = new JLabel("DATE OF WITHDRAW");
		with_draw.setForeground(new Color(120, 120, 153));
		bkpn.add(with_draw, new AbsoluteConstraints(15, 263 + 17 + 54 + 54));

		with_draw2 = new JTextField();
		with_draw2.setEditable(false);
		with_draw2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		with_draw2.setForeground(new Color(255, 0, 153));
		with_draw2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(with_draw2, new AbsoluteConstraints(145, 261 + 17 + 54 + 54,
				150, 20));

		rec_dt = new JLabel("==>  BILL      DATE :");
		rec_dt.setForeground(new Color(120, 120, 153));
		bkpn.add(rec_dt, new AbsoluteConstraints(310, 263 + 17 + 54 + 54));

		rec_dt2 = new JTextField();
		rec_dt2.setEditable(false);
		rec_dt2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		rec_dt2.setForeground(new Color(255, 0, 153));
		rec_dt2.setCaretColor(new Color(0, 204, 102));
		bkpn.add(rec_dt2, new AbsoluteConstraints(440, 261 + 17 + 54 + 54, 150,
				20));

		des = new JLabel("====>  DESCRIPTION");
		des.setForeground(new Color(120, 120, 153));
		bkpn.add(des, new AbsoluteConstraints(15, 263 + 17 + 27 + 54 + 54));

		des1 = new JTextField();
		des1.setEditable(false);
		des1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		des1.setForeground(new Color(255, 0, 153));
		des1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(des1, new AbsoluteConstraints(145, 261 + 17 + 27 + 54 + 54,
				445, 20));

		note = new JLabel(" <====  NOTE  ====>");
		note.setForeground(new Color(120, 120, 153));
		bkpn.add(note, new AbsoluteConstraints(15, 291 + 17 + 27 + 54 + 54));

		note1 = new JTextField();
		note1.setEditable(false);
		note1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 40)));
		note1.setForeground(new Color(255, 0, 153));
		note1.setCaretColor(new Color(0, 204, 102));
		bkpn.add(note1, new AbsoluteConstraints(145, 289 + 17 + 27 + 54 + 54,
				445, 20));

		ins = new JButton("INSERT");
		ins.setBackground(Color.cyan);
		ins.setForeground(Color.black);
		ins.setMnemonic('S');
		ins.setBorder(new BevelBorder(0));
		bkpn.add(ins, new AbsoluteConstraints(25, 320 + 17 + 27 + 54 + 54, 110,
				27));

		up = new JButton("UPDATE");
		up.setBackground(Color.cyan);
		up.setForeground(Color.black);
		up.setMnemonic('A');
		up.setEnabled(false);
		up.setBorder(new BevelBorder(0));
		bkpn.add(up, new AbsoluteConstraints(137, 320 + 17 + 27 + 54 + 54, 110,
				27));

		mis = new JButton("LOST");
		mis.setBackground(Color.cyan);
		mis.setForeground(Color.black);
		mis.setMnemonic('L');
		mis.setBorder(new BevelBorder(0));
		bkpn.add(mis, new AbsoluteConstraints(249, 320 + 17 + 27 + 54 + 54,
				110, 27));

		next = new JButton("NEXT>>>");
		next.setBackground(Color.cyan);
		next.setForeground(Color.black);
		next.setMnemonic('N');
		next.setBorder(new BevelBorder(0));
		bkpn.add(next, new AbsoluteConstraints(361, 320 + 17 + 27 + 54 + 54,
				110, 27));

		quit = new JButton("EXIT");
		quit.setBackground(Color.cyan);
		quit.setMnemonic('X');
		quit.setForeground(Color.black);
		quit.setBorder(new BevelBorder(0));
		bkpn.add(quit, new AbsoluteConstraints(473, 320 + 17 + 54 + 27 + 54,
				110, 27));

		c.add(pnl = new JPanel(new BorderLayout()), new AbsoluteConstraints(10,
				380, 770, 175));
		pnl.setBackground(c.getBackground());
		pnl.setBorder(new TitledBorder(new EtchedBorder(Color.cyan, new Color(
				0, 0, 40)), "INSERTEDBOOKS-DISPLAY", 1, 2, c.getFont(),
				Color.magenta));
		c.add(despn = new JPanel(new AbsoluteLayout()),
				new AbsoluteConstraints(220, 390, 360, 155));
		despn.setBackground(Color.white);// c.getBackground());
		// despn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan,
		// new Color(0,0,40)),
		// "ISSUED TO", 1, 2, c.getFont(), Color.magenta));
		despn.setBorder(new BevelBorder(0));
		despn.setVisible(false);

		// MESSAGE
		msg = new JLabel(
				"-:  ISSUED  TO  STUDENT , U  DON ' T  UPDATE  THIS  RECORD  :-");
		msg.setBackground(new Color(0, 0, 60));
		msg.setForeground(new Color(120, 120, 160));
		msg.setBorder(new BevelBorder(0));
		msg.setVisible(false);
		c.add(msg, new AbsoluteConstraints(90, 360));

		msg1 = new JLabel(
				"-:  ISSUED  TO  PROFESSOR , U  DON ' T  UPDATE  THIS  RECORD  :-");
		c.add(msg1, new AbsoluteConstraints(90, 360));
		msg1.setBackground(new Color(0, 0, 60));
		msg1.setForeground(new Color(120, 120, 160));
		msg1.setBorder(new BevelBorder(0));
		msg1.setVisible(false);

		// PERSON DETAILS
		adno = new JLabel();
		adno.setForeground(new Color(0, 0, 100));
		adno.setHorizontalAlignment(SwingConstants.RIGHT);

		adno1 = new JLabel();
		adno1.setForeground(new Color(0, 0, 100));
		adno1.setHorizontalAlignment(SwingConstants.RIGHT);

		name = new JLabel();
		name.setForeground(new Color(0, 0, 100));
		name.setHorizontalAlignment(SwingConstants.RIGHT);

		name1 = new JLabel();
		name1.setForeground(new Color(0, 0, 100));
		name1.setHorizontalAlignment(SwingConstants.RIGHT);

		image = new JLabel();
		image.setBounds(245, 20, 100, 113);
		image.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 100)));
		despn.add(image, new AbsoluteConstraints(240, 20, 100, 110));

		icon1 = new ImageIcon("empty.jpg");

		branch = new JLabel();
		branch.setForeground(new Color(0, 0, 100));

		branch1 = new JLabel();
		branch1.setForeground(new Color(0, 0, 100));

		year2 = new JLabel();
		year2.setForeground(new Color(0, 0, 100));
		year2.setHorizontalAlignment(SwingConstants.RIGHT);

		year3 = new JLabel();
		year3.setForeground(new Color(0, 0, 100));
		year3.setHorizontalAlignment(SwingConstants.RIGHT);

		adno.setText("ADNO  :");
		name.setText("NAME  :");
		branch.setText("COURSE  :");
		year2.setText("SEM  :");

		adno.setVisible(false);
		name.setVisible(false);
		branch.setVisible(false);
		year2.setVisible(false);

		adno1.setVisible(false);
		name1.setVisible(false);
		branch1.setVisible(false);
		year3.setVisible(false);

		// STAFF
		adno2 = new JLabel();
		adno2.setForeground(new Color(0, 0, 100));
		adno2.setHorizontalAlignment(SwingConstants.RIGHT);

		adno3 = new JLabel();
		adno3.setForeground(new Color(0, 0, 100));
		adno3.setHorizontalAlignment(SwingConstants.RIGHT);

		name2 = new JLabel();
		name2.setForeground(new Color(0, 0, 100));
		name2.setHorizontalAlignment(SwingConstants.RIGHT);

		name3 = new JLabel();
		name3.setForeground(new Color(0, 0, 100));
		name3.setHorizontalAlignment(SwingConstants.RIGHT);

		image1 = new JLabel();
		image1.setBounds(245, 20, 100, 113);
		image1.setBorder(new MatteBorder(1, 1, 1, 1, Color.cyan));
		despn.add(image1, new AbsoluteConstraints(240, 20, 100, 110));

		icon1 = new ImageIcon("empty.jpg");

		branch2 = new JLabel();
		branch2.setForeground(new Color(0, 0, 100));

		branch3 = new JLabel();
		branch3.setForeground(new Color(0, 0, 100));

		year4 = new JLabel();
		year4.setForeground(new Color(0, 0, 100));
		year4.setHorizontalAlignment(SwingConstants.RIGHT);

		year5 = new JLabel();
		year5.setForeground(new Color(0, 0, 100));
		year5.setHorizontalAlignment(SwingConstants.RIGHT);

		adno2.setText("PROFESSOR ID :");
		name2.setText("NAME  :");
		branch2.setText("DEPARTMENT :");
		year4.setText("JOINING DATE :");

		adno2.setVisible(false);
		name2.setVisible(false);
		branch2.setVisible(false);
		year4.setVisible(false);

		adno3.setVisible(false);
		name3.setVisible(false);
		branch3.setVisible(false);
		year5.setVisible(false);

		despn.add(adno, new AbsoluteConstraints(35, 21));
		despn.add(adno1, new AbsoluteConstraints(90, 21));
		despn.add(name, new AbsoluteConstraints(35, 51));
		despn.add(name1, new AbsoluteConstraints(90, 51));
		despn.add(branch, new AbsoluteConstraints(20, 81));
		despn.add(branch1, new AbsoluteConstraints(90, 81));
		despn.add(year2, new AbsoluteConstraints(43, 111));
		despn.add(year3, new AbsoluteConstraints(90, 111));

		despn.add(adno2, new AbsoluteConstraints(14, 21));
		despn.add(adno3, new AbsoluteConstraints(90 + 10 + 10, 21));
		despn.add(name2, new AbsoluteConstraints(62, 51));
		despn.add(name3, new AbsoluteConstraints(90 + 10 + 10, 51));
		despn.add(branch2, new AbsoluteConstraints(20, 81));
		despn.add(branch3, new AbsoluteConstraints(90 + 10 + 10, 81));
		despn.add(year4, new AbsoluteConstraints(20, 111));
		despn.add(year5, new AbsoluteConstraints(90 + 10 + 10, 111));

		disableFields();
		setVisible(true);

		ins.setEnabled(false);
		mis.setEnabled(false);
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
          msg.addMouseListener(new MouseAdapter()
            {                
                public void mouseEntered(MouseEvent me)
                  {
                    msg.setBackground(new Color(0,0,100));
                  }
                public void mouseExited(MouseEvent me)
                  {
                     msg.setBackground(new Color(0,0,60));
                  }
            }
         );
         msg1.addMouseListener(new MouseAdapter()
            {                
                public void mouseEntered(MouseEvent me)
                  {
                    msg1.setBackground(new Color(0,0,100));
                  }
                public void mouseExited(MouseEvent me)
                  {
                     msg1.setBackground(new Color(0,0,60));
                  }
            }
         );
          acess1.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                   firstStep();
                 }
            }
          );
          acess2.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                   firstStep();
                 }
            }
          );
          title.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> bNames = bkSrchDao.listBookNames(title.getText().toUpperCase());
            	  if ( !bNames.isEmpty() )
            	  {
            		  bookname1.setVisible(true);
                      title.setVisible(false);
                      for ( String bName : bNames )
                	  {
                		  bookname1.addItem(bName);
                	  }  
            	  }
            	                      
                }
            }
          );
          aut1.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorSurnames(aut1.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut1s1.setVisible(true);
                      aut1.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut1s1.addItem(aName);
                	  } 
            	  } 
                }
            }
          );

          aut2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorNames(aut2.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut1f1.setVisible(true);
                      aut2.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut1f1.addItem(aName);
                	  } 
            	  }                     
                }
            }
          );

          aut3.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorSurnames(aut3.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut2s1.setVisible(true);
                      aut3.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut2s1.addItem(aName);
                	  } 
            	  }                      
                }
            }
          );

          aut4.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorNames(aut4.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut2f1.setVisible(true);
                      aut4.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut2f1.addItem(aName);
                	  } 
            	  }                   
                }
            }
          );

          aut5.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorSurnames(aut5.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut3s1.setVisible(true);
                      aut5.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut3s1.addItem(aName);
                	  } 
            	  }                   
                }
            }
          );

          aut6.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorNames(aut6.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut3f1.setVisible(true);
                      aut6.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut3f1.addItem(aName);
                	  } 
            	  }                     
                }
            }
          );
           aut7.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorSurnames(aut7.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut4s1.setVisible(true);
                      aut7.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut4s1.addItem(aName);
                	  } 
            	  }                   
                }
            }
          );

          aut8.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorNames(aut8.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut4f1.setVisible(true);
                      aut8.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut4f1.addItem(aName);
                	  } 
            	  }                 
                }
            }
          );
           aut9.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorSurnames(aut9.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut5s1.setVisible(true);
                      aut9.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut5s1.addItem(aName);
                	  } 
            	  }                     
                }
            }
          );

          aut10.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> authorNames = bkSrchDao.listAuthorNames(aut10.getText().toUpperCase());
            	  if ( !authorNames.isEmpty() )
            	  {
            		  aut5f1.setVisible(true);
                      aut10.setVisible(false);
                      for ( String aName : authorNames )
                	  {
                    	  aut5f1.addItem(aName);
                	  } 
            	  }                  
                }
            }
          );

          pub2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> publshrNames = bkSrchDao.listAuthorNames(pub2.getText().toUpperCase());
            	  if ( !publshrNames.isEmpty() )
            	  {
            		  pub1.setVisible(true);
                      pub2.setVisible(false);
                      for ( String pubName : publshrNames )
                	  {
                    	  pub1.addItem(pubName);
                	  } 
            	  }                       
                }
            }
          );

          place2.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> places = bkSrchDao.listAuthorNames(place2.getText().toUpperCase());
            	  if ( !places.isEmpty() )
            	  {
            		  place1.setVisible(true);
                      place2.setVisible(false);
                      for ( String place : places )
                	  {
                    	  place1.addItem(place);
                	  } 
            	  }                      
                }
            }
          );
           sub22.addActionListener(new ActionListener()
            {
              public void actionPerformed(ActionEvent e)
                {
            	  Set<String> streams = bkSrchDao.listStreams(sub22.getText().toUpperCase());
            	  if ( !streams.isEmpty() )
            	  {
            		  sub21.setVisible(true);
                      sub22.setVisible(false);
                      for ( String place : streams )
                	  {
                    	  sub21.addItem(place);
                	  } 
            	  }                      
                }
            }
          );

		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BKStaffModel bkstfmodel = null;
				BKStudentModel model = bkTranSrchDao.findByAccessNoForStudent(acess1.getText().toUpperCase());
				if (model != null) {
					msg.setVisible(true);
					student = model.getStdModel().getId();
					if (student != null) {
						adno2.setVisible(false);
						name2.setVisible(false);
						branch2.setVisible(false);
						year4.setVisible(false);

						adno3.setVisible(false);
						name3.setVisible(false);
						branch3.setVisible(false);
						year5.setVisible(false);

						adno.setVisible(true);
						name.setVisible(true);
						branch.setVisible(true);
						year2.setVisible(true);

						adno1.setVisible(true);
						name1.setVisible(true);
						branch1.setVisible(true);
						year3.setVisible(true);

						adno1.setText(student);
						name1.setText(model.getStdModel().getName());
						branch1.setText(model.getStdModel().getBranch());
						year3.setText(model.getStdModel().getYear());

						byte[] byteImg = model.getStdModel().getImage();
						if ( byteImg != null )
						{
							icon = new MyImageIcon(byteImg);
							image.setIcon(icon);
						}else
							image.setIcon(null);
						
						pnl.setVisible(false);
						despn.setVisible(true);
						
						BookModel bkModel = bkCatlogDao.findByAccessNo(acess1.getText());						
						if (bkModel != null) {
							bkModel.setIssued("1");
							bkCatlogDao.update(bkModel);
						}
					}
					up.setEnabled(false);
				} else {
					bkstfmodel = bkTranSrchDao.findByAccessNoForStaff(acess1
							.getText().toUpperCase());
					if (bkstfmodel != null) {
						msg1.setVisible(true);
						staff = bkstfmodel.getStaffModel().getId();
						if (staff != null) {
							adno.setVisible(false);
							name.setVisible(false);
							branch.setVisible(false);
							year2.setVisible(false);

							adno1.setVisible(false);
							name1.setVisible(false);
							branch1.setVisible(false);
							year3.setVisible(false);

							adno2.setVisible(true);
							name2.setVisible(true);
							branch2.setVisible(true);
							year4.setVisible(true);

							adno3.setVisible(true);
							name3.setVisible(true);
							branch3.setVisible(true);
							year5.setVisible(true);

							adno3.setText(staff);
							name3.setText(bkstfmodel.getStaffModel().getName());
							branch3.setText(bkstfmodel.getStaffModel()
									.getDept());
							year5.setText(DateHelper.format(bkstfmodel.getStaffModel().getJoinDate()));
							
							byte[] byteImg= bkstfmodel.getStaffModel().getImage();
							if ( byteImg != null )
							{
								icon = new MyImageIcon(byteImg);
								image.setIcon(icon);
							}else
							{
								image.setIcon(null);
							}
							
							pnl.setVisible(false);
							despn.setVisible(true);
							BookModel bkModel = bkCatlogDao
									.findByAccessNo(acess1.getText());
							if (bkModel != null) {
								bkModel.setIssued("1");
								bkCatlogDao.update(bkModel);
							}
						}
						up.setEnabled(false);
					}
				}

				int first = -1;
				int second = -1;
				if (acess1.getText() != null) {
					if (!NumberUtils.isNumber(acess1.getText())) {
						JOptionPane.showMessageDialog(null,"Please enter numbers for Access Field");
						return;
					} else
						first = Integer.parseInt(acess1.getText());
				}
				if (acess2.getText() != null) {
					if (!NumberUtils.isNumber(acess2.getText())) {
						JOptionPane.showMessageDialog(null,"Please enter numbers for Access Field");
						return;
					} else
						second = Integer.parseInt(acess2.getText());
				}

				if (first > loop && second < first ) {
					JOptionPane
							.showMessageDialog(null,"U MUST INSERT IN SEQUENTIAL FASHION .  .  .  .  .  .");
					return;
				}

				if (model == null && bkstfmodel == null) {

					TITLE = (String) bookname1.getSelectedItem();
					if (TITLE == null || TITLE.length() == 0) {
						TITLE = title.getText().toUpperCase();
						if (TITLE == null || TITLE.length() == 0) {
							JOptionPane.showMessageDialog(null,
									"Book Name is Mandatory!!!",
									"FIELD VALIDATION",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					AUT1 = (String) aut1s1.getSelectedItem();
					if (AUT1 == null || AUT1.equals("")) {
						AUT1 = aut1.getText().toUpperCase();
						if (AUT1 == null || AUT1.equals("")) {
							JOptionPane.showMessageDialog(null,
									"First Author is Mandatory!!!",
									"FIELD VALIDATION",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					AUT2 = (String) aut1f1.getSelectedItem();
					if (AUT2 == null || AUT2.equals("")) {
						AUT2 = aut2.getText().toUpperCase();
						if (AUT2 == null || AUT2.equals("")) {
							JOptionPane.showMessageDialog(null,
									"First Author is Mandatory!!!",
									"FIELD VALIDATION",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}

					AUT3 = (String) aut2s1.getSelectedItem();
					if (AUT3 == null || AUT3.equals("")) {
						AUT3 = aut3.getText().toUpperCase();
					}

					AUT4 = (String) aut2f1.getSelectedItem();
					if (AUT4 == null || AUT4.equals("")) {
						AUT4 = aut4.getText().toUpperCase();
					}

					AUT5 = (String) aut3s1.getSelectedItem();
					if (AUT5 == null || AUT5.equals("")) {
						AUT5 = aut5.getText().toUpperCase();
					}

					AUT6 = (String) aut3f1.getSelectedItem();
					if (AUT6 == null || AUT6.equals("")) {
						AUT6 = aut6.getText().toUpperCase();
					}

					AUT7 = (String) aut4s1.getSelectedItem();
					if (AUT7 == null || AUT7.equals("")) {
						AUT7 = aut7.getText().toUpperCase();
					}

					AUT8 = (String) aut4f1.getSelectedItem();
					if (AUT8 == null || AUT8.equals("")) {
						AUT8 = aut8.getText().toUpperCase();
					}

					AUT9 = (String) aut5s1.getSelectedItem();
					if (AUT9 == null || AUT9.equals("")) {
						AUT9 = aut9.getText().toUpperCase();
					}

					AUT10 = (String) aut5f1.getSelectedItem();
					if (AUT10 == null || AUT10.equals("")) {
						AUT10 = aut10.getText().toUpperCase();
					}

					PUB = (String) pub1.getSelectedItem();
					if (PUB == null || PUB.equals("")) {
						PUB = pub2.getText().toUpperCase();
					}

					SUB2 = (String) sub21.getSelectedItem();
					if (SUB2 == null || SUB2.equals("")) {
						SUB2 = sub22.getText().toUpperCase();
					}

					PLACE = (String) place1.getSelectedItem();
					if (PLACE == null || PLACE.equals("")) {
						PLACE = place2.getText().toUpperCase();
					}

					if (first <= second) {
						for (int Z = first; Z <= second; Z++) {
							acess1.setText("" + Z);
							BookModel bkRcrd = new BookModel();
							bkRcrd.setAccessNo(String.valueOf(Z));
							bkRcrd.setBookName(TITLE);
							bkRcrd.setAuthor1Surname(AUT1);
							bkRcrd.setAuthor1Name(AUT2);
							bkRcrd.setAuthor2Surname(AUT3);
							bkRcrd.setAuthor2Name(AUT4);
							bkRcrd.setAuthor3Surname(AUT5);
							bkRcrd.setAuthor3Name(AUT6);
							bkRcrd.setAuthor4Surname(AUT7);
							bkRcrd.setAuthor4Name(AUT8);
							bkRcrd.setAuthor5Surname(AUT9);
							bkRcrd.setAuthor5Name(AUT10);
							bkRcrd.setPublisher(PUB);
							bkRcrd.setEdition(edt1.getText().toUpperCase());
							bkRcrd.setIsbn(isbn1.getText().toUpperCase());
							bkRcrd.setNote(note1.getText().toUpperCase());
							bkRcrd.setDescription(des1.getText().toUpperCase());
							bkRcrd.setPhyMedium(phy1.getSelectedItem().toString());
							bkRcrd.setPlace(PLACE);
							bkRcrd.setYear(year1.getText().toUpperCase());
							bkRcrd.setBookDept(sub11.getSelectedItem().toString());
							bkRcrd.setBookStream(SUB2);
							bkRcrd.setBookSpec(sub31.getText().toUpperCase());
							bkRcrd.setVolume(volume2.getText().toUpperCase());
							bkRcrd.setSeries(series2.getText().toUpperCase());
							bkRcrd.setSupplier(src_pub2.getText().toUpperCase());
							bkRcrd.setPrice(price2.getText().toUpperCase());
							bkRcrd.setBillNo(bill_no2.getText().toUpperCase());
							bkRcrd.setCallNo(call_no2.getText().toUpperCase());
							try {
								bkRcrd.setBillDate(DateHelper.parse(rec_dt2
										.getText()));
								bkRcrd.setWithdrawDate(DateHelper
										.parse(with_draw2.getText()));
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(null,
												"Invalid Date format, Please enter in dd-Mon-yyyy format",
												"FIELD VALIDATION",
												JOptionPane.WARNING_MESSAGE);
								return;
							}
							bkRcrd.setAccessDate(Calendar.getInstance()
									.getTime());
							bkRcrd.setIssued("0");
							bkCatlogDao.update(bkRcrd);
							up.setEnabled(false);
						}
						populateList();
					}

				}
			}
		});


          ins.addActionListener( new ActionListener()
             {
               public void actionPerformed(ActionEvent e)
                 {                  
                	   
            	   TITLE=(String)bookname1.getSelectedItem();
                   if( TITLE==null || TITLE.length() == 0 )
                     {
                	   TITLE = title.getText().toUpperCase();
                	   if ( TITLE==null || TITLE.length() == 0 )
                	   {
                		   JOptionPane.showMessageDialog(null, "Book Name is Mandatory!!!", "FIELD VALIDATION",JOptionPane.WARNING_MESSAGE);
                		   return;
                	   }
                     }
                   AUT1=(String)aut1s1.getSelectedItem();
                   if(AUT1==null || AUT1.equals(""))
                     {
                        AUT1 = aut1.getText().toUpperCase();
                        if(AUT1==null || AUT1.equals(""))
                          {
                        	JOptionPane.showMessageDialog(null, "First Author is Mandatory!!!", "FIELD VALIDATION",JOptionPane.WARNING_MESSAGE);
                 		   	return;
                          }                           
                     }
                   AUT2=(String)aut1f1.getSelectedItem();
                   if(AUT2==null || AUT2.equals(""))
                   {
                      AUT2 = aut2.getText().toUpperCase();
                      if(AUT2==null || AUT2.equals(""))
                        {
                    	  JOptionPane.showMessageDialog(null, "First Author is Mandatory!!!", "FIELD VALIDATION",JOptionPane.WARNING_MESSAGE);
               		   	  return;
                        }                          
                   }
                   AUT3=(String)aut2s1.getSelectedItem();
                   if(AUT3==null || AUT3.equals("")){  AUT3 = aut3.getText().toUpperCase();  }

                   AUT4=(String)aut2f1.getSelectedItem();
                   if(AUT4==null || AUT4.equals("")){  AUT4 = aut4.getText().toUpperCase();  }

                   AUT5=(String)aut3s1.getSelectedItem();
                   if(AUT5==null || AUT5.equals("")){  AUT5 = aut5.getText().toUpperCase();  }

                   AUT6=(String)aut3f1.getSelectedItem();
                   if(AUT6==null || AUT6.equals("")){  AUT6 = aut6.getText().toUpperCase();  }

                   AUT7=(String)aut4s1.getSelectedItem();
                   if(AUT7==null || AUT7.equals("")){  AUT7 = aut7.getText().toUpperCase();  }

                   AUT8=(String)aut4f1.getSelectedItem();
                   if(AUT8==null || AUT8.equals("")){  AUT8 = aut8.getText().toUpperCase();  }

                   AUT9=(String)aut5s1.getSelectedItem();
                   if(AUT9==null || AUT9.equals("")){  AUT9 = aut9.getText().toUpperCase();  }

                   AUT10=(String)aut5f1.getSelectedItem();
                   if(AUT10==null || AUT10.equals("")){  AUT10 = aut10.getText().toUpperCase();  }

                   PUB=(String)pub1.getSelectedItem();
                   if(PUB==null || PUB.equals("")){  PUB = pub2.getText().toUpperCase();  }

                   PLACE=(String)place1.getSelectedItem();
                   if(PLACE==null || PLACE.equals("")){  PLACE = place2.getText().toUpperCase();  }

                   SUB2=(String)sub21.getSelectedItem();
                   if(SUB2==null || SUB2.equals("")){  SUB2 = sub22.getText().toUpperCase();  }
                        
                    int first = -1;
                    int second = -1;
                    if ( acess1.getText() != null )
                    {
                    	if (!NumberUtils.isNumber(acess1.getText()))
                    	{
                    		JOptionPane.showMessageDialog(null, "Please enter numbers for Access Field");
                    		return;
                    	}else 
                    		first = Integer.parseInt(acess1.getText());
                    }
                    if ( acess2.getText() != null )
                    {
                    	if (!NumberUtils.isNumber(acess2.getText()))
                    	{
                    		JOptionPane.showMessageDialog(null, "Please enter numbers for Access Field");
                    		return;
                    	}else
                    		second = Integer.parseInt(acess2.getText());
                    }
                    
                    if ( first != loop)
                    {
                    	JOptionPane.showMessageDialog(null,"U MUST INSERT IN SEQUENTIAL FASHION .  .  .  .  .  .");
                    	return;
                    }
                    if(first<=second)
                    {
                    	for( int i = first; i <= second ; i++)
                        {
                        	BookModel bkRcrd = new BookModel();
                            bkRcrd.setAccessNo(String.valueOf(i));
                            bkRcrd.setBookName(TITLE);
                            bkRcrd.setAuthor1Surname(AUT1);
                            bkRcrd.setAuthor1Name(AUT2);
                            bkRcrd.setAuthor2Surname(AUT3);
                            bkRcrd.setAuthor2Name(AUT4);
                            bkRcrd.setAuthor3Surname(AUT5);
                            bkRcrd.setAuthor3Name(AUT6);
                            bkRcrd.setAuthor4Surname(AUT7);
                            bkRcrd.setAuthor4Name(AUT8);
                            bkRcrd.setAuthor5Surname(AUT9);
                            bkRcrd.setAuthor5Name(AUT10);
                            bkRcrd.setPublisher(PUB);
                            bkRcrd.setEdition(edt1.getText().toUpperCase());
                            bkRcrd.setIsbn(isbn1.getText().toUpperCase());
                            bkRcrd.setNote(note1.getText().toUpperCase());
                            bkRcrd.setDescription(des1.getText().toUpperCase());
                            bkRcrd.setPhyMedium(phy1.getSelectedItem().toString());
                            bkRcrd.setPlace(PLACE);
                            bkRcrd.setYear(year1.getText().toUpperCase());
                            bkRcrd.setBookDept(sub11.getSelectedItem().toString());
                            bkRcrd.setBookStream(SUB2);
                            bkRcrd.setBookSpec(sub31.getText().toUpperCase());
                            bkRcrd.setVolume(volume2.getText().toUpperCase());
                            bkRcrd.setSeries(series2.getText().toUpperCase());
                            bkRcrd.setSupplier(src_pub2.getText().toUpperCase());
                            bkRcrd.setPrice(price2.getText().toUpperCase());
                            bkRcrd.setBillNo(bill_no2.getText().toUpperCase());
                            bkRcrd.setCallNo(call_no2.getText().toUpperCase());                            
                            try {
								bkRcrd.setBillDate(DateHelper.parse(rec_dt2.getText()));
								bkRcrd.setWithdrawDate(DateHelper.parse(with_draw2.getText()));
							} catch (ParseException e1) {
								JOptionPane.showMessageDialog(null, "Invalid Date format, Please enter in dd-Mon-yyyy format","FIELD VALIDATION",JOptionPane.WARNING_MESSAGE);
								return;
							}
                            bkRcrd.setAccessDate(Calendar.getInstance().getTime());
                            bkRcrd.setIssued("0");
                            bkCatlogDao.create(bkRcrd);
                        }
                    }else
                    {
                    	int cfirm=JOptionPane.showConfirmDialog(null,"U MUST GIVE 2-ND  ACCESSNO  GREATER  THAN 1_ST  ACCESSNO","NEW RECORD",JOptionPane.YES_NO_OPTION);
                        if(cfirm==0)
                          {
                            acess2.setText(acess1.getText());
                          }
                    }                        
                    populateList();
                 }
             }
          );

          mis.addActionListener(new ActionListener()
          {
               public void actionPerformed(ActionEvent e)
               {                    
	                if(bkTranSrchDao.hasStudent(acess1.getText()))
	                {
	                	BookModel bkModel = bkCatlogDao.findByAccessNo(acess1.getText());
	                	bkModel.setStatus("MISSING");
	                	bkCatlogDao.update(bkModel);                            
	                	BKTransactionModel bkTransMdl = new BKTransactionModel();                        	
	                	bkTransMdl.setCode(acess1.getText());
	                	bkTransDao.deleteStudent(bkTransMdl);
	                    JOptionPane.showMessageDialog(null,"STATUS OF" +acess1.getText()+" UPDATED TO LOST");
	                }                       
	                else if ( bkTranSrchDao.hasStaff(acess1.getText()))
	                {
	                	BookModel bkModel = bkCatlogDao.findByAccessNo(acess1.getText());
	                	bkModel.setStatus("MISSING");
	                	bkCatlogDao.update(bkModel);
	                    
	                	BKTransactionModel bkTransMdl = new BKTransactionModel();                        	
	                	bkTransMdl.setCode(acess1.getText());
	                	bkTransDao.deleteStaff(bkTransMdl);
	                    JOptionPane.showMessageDialog(null,"STATUS OF" +acess1.getText()+" UPDATED TO LOST");
	                }         
               }
          }
          );

          next.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                     reset();
                     populateList();
                 }
            }
          );

          quit.addActionListener( new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
                 {
                    //PRINT THE DATA BEFORE QUIT
                    setVisible(false);
                 }
            }
          );

          addWindowListener( new WindowAdapter()
            {
               public void windowClosing( WindowEvent e)
                 {
                    
                 }
            }
          );
       }
     private void firstStep()
       {
           if(acess1.getText()==null||acess1.getText().equals("")){
        	   JOptionPane.showMessageDialog(null,"U MUST 1ST INPUT 1-ST ACESSNO .  .  .");
           }
           else{
           
                         enableFields();
                         resetCombos();
                         despn.setVisible(false);
                         pnl.setVisible(true);
                         BookModel bkModel = bkCatlogDao.findByAccessNo(acess1.getText());
                         
                         /*String query="ACESSNO,BOOKNAME,AUTHOR1S,AUTHOR1F,AUTHOR2S,AUTHOR2F,AUTHOR3S,AUTHOR3F,AUTHOR4S,AUTHOR4F,AUTHOR5S,AUTHOR5F,"+
                                       "PUBLISHER,EDITION,ISBN,NOTE,DESCPRTN,PHY_MEDM,PLACE,YEAR,SUBJECT1,SUBJECT2,SUBJECT3,ACESSDATE,ISSUED,"+
                                       " STATUS,VOLUME,SERIES,SUPPLIER,PRICE,BILLNO,CALLNO,TO_CHAR(BILLDATE,'DD-MON-YYYY'),TO_CHAR(WITHDRAW_DATE,'DD-MON-YYYY')";
                         s = con.createStatement();
                         rs = s.executeQuery("SELECT "+query+" FROM LIBRARY WHERE ACESSNO="+"'"+acess1.getText().toUpperCase()+"'");*/
                           if( bkModel != null )
                             {
                        	   	title.setText(bkModel.getBookName());
                                aut1.setText(bkModel.getAuthor1Surname());
                                aut2.setText(bkModel.getAuthor1Name());
                                aut3.setText(bkModel.getAuthor2Surname());
                                aut4.setText(bkModel.getAuthor2Name());
                                aut5.setText(bkModel.getAuthor3Surname());
                                aut6.setText(bkModel.getAuthor3Name());
                                aut7.setText(bkModel.getAuthor4Surname());
                                aut8.setText(bkModel.getAuthor4Name());
                                aut9.setText(bkModel.getAuthor5Surname());
                                aut10.setText(bkModel.getAuthor5Name());
                                pub2.setText (bkModel.getPublisher());                                
                                edt1.setText (bkModel.getEdition());
                                isbn1.setText(bkModel.getIsbn());
                                note1.setText (bkModel.getNote());
                                des1.setText (bkModel.getDescription());                                
                                phy1.setSelectedItem (bkModel.getPhyMedium());
                                place2.setText (bkModel.getPlace());
                                volume2.setText(bkModel.getVolume());
                                series2.setText(bkModel.getSeries());
                                src_pub2.setText(bkModel.getSupplier());
                                price2.setText(bkModel.getPrice());
                                bill_no2.setText(bkModel.getBillNo());
                                call_no2.setText(bkModel.getCallNo());                                
                                rec_dt2.setText(DateHelper.format(bkModel.getBillDate()));
                                with_draw2.setText(DateHelper.format(bkModel.getWithdrawDate()));                                
                                sub11.setSelectedItem(bkModel.getBookDept());
                                sub22.setText(bkModel.getBookStream());
                                sub31.setText(bkModel.getBookSpec());
                                year1.setText(bkModel.getYear());
                                
                                up.setEnabled(true);
                                //ins.setEnabled(false);
                                mis.setEnabled(true);

                                enableCombos();
                                addComboItems();
                             }
                           else
                             {
                                int cfirm=JOptionPane.showConfirmDialog(null,"DO U WANT TO INSERT NEW RECORD","NEW RECORD",JOptionPane.YES_NO_OPTION);
                                if(cfirm==0)
                                {
                                    resetFields();
                                    resetCombos();
                                    enableFields();
                                    enableCombos();
                                    addComboItems();
                                    ins.setEnabled(true);
                                    mis.setEnabled(false);
                                 }
                                 else
                                 {
                                   reset();
                                 }
                             }
                     
             }
       }
     private void disableFields()
       {
          edt1.setEditable(false);
          title.setEditable(false);
          volume2.setEditable(false);
          series2.setEditable(false);
          price2.setEditable(false);
          src_pub2.setEditable(false);
          bill_no2.setEditable(false);
          call_no2.setEditable(false);
          with_draw2.setEditable(false);
          rec_dt2.setEditable(false);

          aut1.setEditable(false);
          aut2.setEditable(false);
          aut3.setEditable(false);
          aut4.setEditable(false);
          aut5.setEditable(false);
          aut6.setEditable(false);
          aut7.setEditable(false);
          aut8.setEditable(false);
          aut9.setEditable(false);
          aut10.setEditable(false);
          isbn1.setEditable(false);
          note1.setEditable(false);
          des1.setEditable(false);
          pub2.setEditable(false);
          phy1.setEditable(false);
          place2.setEditable(false);
          year1.setEditable(false);
          sub11.setEditable(false);
          sub21.setEditable(false);
          sub31.setEditable(false);
          sub22.setEditable(false);
     }

     private void enableFields()
       {
          edt1.setEditable(true);
          title.setEditable(true);
          aut1.setEditable(true);
          aut2.setEditable(true);
          aut3.setEditable(true);
          aut4.setEditable(true);
          aut5.setEditable(true);
          aut6.setEditable(true);
          aut7.setEditable(true);
          aut8.setEditable(true);
          aut9.setEditable(true);
          aut10.setEditable(true);
          isbn1.setEditable(true);
          note1.setEditable(true);
          des1.setEditable(true);
          pub2.setEditable(true);
          phy1.setEditable(true);
          place2.setEditable(true);
          year1.setEditable(true);
          sub11.setEditable(true);
          sub22.setEditable(true);

          sub31.setEditable(true);
            volume2.setEditable(true);
            series2.setEditable(true);
            price2.setEditable(true);
            bill_no2.setEditable(true);
            src_pub2.setEditable(true);
            call_no2.setEditable(true);
            rec_dt2.setEditable(true);
            with_draw2.setEditable(true);

     }

     private void resetFields()
       {
            title.setText("");
            aut1.setText("");
            aut2.setText("");
            aut3.setText("");
            aut4.setText("");
            aut5.setText("");
            aut6.setText("");
            aut7.setText("");
            aut8.setText("");
            aut9.setText("");
            aut10.setText("");
            pub2.setText("");
            place2.setText("");
            edt1.setText("");
            isbn1.setText("");
            note1.setText("");
            des1.setText("");
            year1.setText("");
            sub22.setText("");
            sub31.setText("");
            volume2.setText("");
            series2.setText("");
            price2.setText("");
            bill_no2.setText("");
            src_pub2.setText("");
            call_no2.setText("");
            rec_dt2.setText("");
            with_draw2.setText("");
       }
     private void resetCombos()
       {
            aut1s1.removeAllItems();
            aut1f1.removeAllItems();
            aut2s1.removeAllItems();
            aut2f1.removeAllItems();
            aut3s1.removeAllItems();
            aut3f1.removeAllItems();
            aut4s1.removeAllItems();
            aut4f1.removeAllItems();
            aut5s1.removeAllItems();
            aut5f1.removeAllItems();
            bookname1.removeAllItems();
            pub1.removeAllItems();
            place1.removeAllItems();
            sub11.removeAllItems();
            sub21.removeAllItems();
            phy1.removeAllItems();
       }

     private void enableCombos()
       {
            bookname1.setEditable(true);
            aut1s1.setEditable(true);
            aut1f1.setEditable(true);
            aut2s1.setEditable(true);
            aut2f1.setEditable(true);
            aut3s1.setEditable(true);
            aut3f1.setEditable(true);
            aut4s1.setEditable(true);
            aut4f1.setEditable(true);
            aut5s1.setEditable(true);
            aut5f1.setEditable(true);
            isbn1.setEditable(true);
            note1.setEditable(true);
            des1.setEditable(true);
            pub1.setEditable(true);
            phy1.setEditable(true);
            place1.setEditable(true);
            year1.setEditable(true);
            sub11.setEditable(true);
            sub21.setEditable(true);
       }
     private void addComboItems()
       {
           phy1.addItem("");
           phy1.addItem("PAPER");
           phy1.addItem("THESIS");
           sub11.addItem("");
           sub11.addItem("ELECTRONICS");
           sub11.addItem("ELECTRICALS");
           sub11.addItem("COMMUNICATIONS");
           sub11.addItem("COMPUTERS");
           sub11.addItem("MECHANICAL");
           sub11.addItem("LANGUAGE");
           sub11.addItem("PHYSICS");
           sub11.addItem("CHEMISTRY");
           sub11.addItem("OTHERS");
           sub21.addItem("");
           sub21.addItem("APPLICATIONS");
           sub21.addItem("DESIGNING");
       }

    

     private void reset()
       {
           disableFields();
           resetCombos();
           resetFields();

           bookname1.setVisible(false);
           aut1s1.setVisible(false);
           aut1f1.setVisible(false);
           aut2s1.setVisible(false);
           aut2f1.setVisible(false);
           aut3s1.setVisible(false);
           aut3f1.setVisible(false);
           aut4s1.setVisible(false);
           aut4f1.setVisible(false);
           aut5s1.setVisible(false);
           aut5f1.setVisible(false);

           pub1.setVisible(false);
           place1.setVisible(false);
           sub21.setVisible(false);

           title.setVisible(true);
           aut1.setVisible(true);
           aut2.setVisible(true);
           aut3.setVisible(true);
           aut4.setVisible(true);
           aut5.setVisible(true);
           aut6.setVisible(true);
           aut7.setVisible(true);
           aut8.setVisible(true);
           aut9.setVisible(true);
           aut10.setVisible(true);
           pub2.setVisible(true);
           place2.setVisible(true);
           sub22.setVisible(true);

           acess1.setText ("");

           sequenceGenerator();

           msg.setVisible(false);
           msg1.setVisible(false);

           despn.setVisible(false);
           pnl.setVisible(true);

       }     
      private Vector<Object> getDisplayColumns() {
  		Vector<Object> cols = new Vector<Object>();
  		cols.add("Access No");
  		cols.add("Book Name");
  		cols.add("Author");
  		cols.add("Publisher");
  		cols.add("Edition");
  		cols.add("Place");
  		cols.add("Year");
  		return cols;
  	}

  	private void populateList() {
  		List<BookModel> records = bkCatlogDao.findByCurrentDay();

  		if (records.isEmpty()) {
  			JOptionPane.showMessageDialog(null, "NO RECORDS TO DISPLAY FOR TODAY !...",
  					"BOOKS DATABASE", JOptionPane.INFORMATION_MESSAGE);
  			return;
  		}

  		Vector<Object> rows = new Vector<Object>();
  		for (BookModel row : records) {
  			Vector<Object> rowData = new Vector<Object>();
  			rowData.add(row.getAccessNo());
  			rowData.add(row.getBookName());
  			rowData.add(row.getAuthor1Surname() + " " + row.getAuthor1Name());
  			rowData.add(row.getPublisher());
  			rowData.add(row.getEdition());
  			rowData.add(row.getPlace());
  			rowData.add(row.getYear());
  			rows.add(rowData);
  		}
  		
  		lib = new JTable(rows,getDisplayColumns());
        lib.setBackground(Color.cyan);
        lib.setEnabled(false);
        pnl.add(lib,BorderLayout.CENTER);
        JScrollPane sp1 = new JScrollPane(lib);
        pnl.add(sp1,BorderLayout.CENTER);
        validate();
  	}     
     private void sequenceGenerator()
     {
    	 loop = bkCatlogDao.getNextSequenceNo();
    	 acess1.setText(String.valueOf(loop));
         acess2.setText(String.valueOf(loop));         
     }

     public static void main(String a[])
       {
          Library2 l=new Library2();
       }
  }
