package org.ulibsoft.admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.dao.factory.DAOFactory;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;
import org.ulibsoft.util.datatype.DateHelper;

public class KeyConstraints extends JFrame
  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -783744640301732024L;
	private static final Logger log = Logger.getLogger(KeyConstraints.class.getName()) ;
      private JLabel book,cd,mag,std,std1,std2,stf,stf1,stf2;

      private JLabel stdbkmin, stdbkmax, stfbkmin, stfbkmax;
      private JLabel stdcdmin, stdcdmax, stfcdmin, stfcdmax;
      private JLabel stdmzmin, stdmzmax, stfmzmin, stfmzmax;

      private JLabel stdbkpd,stdcdpd,stdmzpd;

      private JLabel stdbkRpd,stdcdRpd,stdmzRpd;
      private JLabel stdbkRcount,stdcdRcount,stdmzRcount;

      private JLabel stfbk_Hld_From,stfbk_Hld_To;
      private JLabel stfbkhold,stfcdhold,stfmzhold;

      private JLabel stdbkFine,stdcdFine,stdmzFine;

      private JLabel imagePath;

      private JTextField stdbkmin2, stdbkmax2, stfbkmin2, stfbkmax2;
      private JTextField stdcdmin2, stdcdmax2, stfcdmin2, stfcdmax2;
      private JTextField stdmzmin2, stdmzmax2, stfmzmin2, stfmzmax2;

      private JTextField stdbkpd2,stdcdpd2,stdmzpd2;

      private JTextField stdbkRpd2,stdcdRpd2,stdmzRpd2;
      private JTextField stdbkRcount2,stdcdRcount2,stdmzRcount2;

      private JTextField stfbk_Hld_From2,stfbk_Hld_To2;
      private JTextField stfbkhold2,stfcdhold2,stfmzhold2;

      private JTextField stdbkFine2,stdcdFine2,stdmzFine2;
      private JTextField imagePath2;
      private JButton ins,up,quit,next;

      private Container c;
      private JPanel cmppn;

      private KeyConstraintDAO keyCnstDao;

      public KeyConstraints()
        {
           super("INPUT CONSTRAINTS FORM");
           setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
           keyCnstDao = DAOFactory.getDAOFactory().getKeyCnstrntDAO();
           createComponents();
           
           org.ulibsoft.model.KeyConstraints stdKeyCnst = keyCnstDao.findById((short)1);
           populateStdConstraints(stdKeyCnst);
           
           org.ulibsoft.model.KeyConstraints stfKeyCnst = keyCnstDao.findById((short)2);
           populateStfConstraints(stfKeyCnst);
           componentListener();
        }
      public void populateStdConstraints(org.ulibsoft.model.KeyConstraints keyCnst)
      {
    	  stdbkmin2.setText(String.valueOf(keyCnst.getMinBook()));
          stdbkmax2.setText(String.valueOf(keyCnst.getMaxBook()));
          stdbkpd2.setText(String.valueOf(keyCnst.getMaxDaysPerBook()));
          
          stdcdmin2.setText(String.valueOf(keyCnst.getMinCD()));
          stdcdmax2.setText(String.valueOf(keyCnst.getMaxCD()));
          stdcdpd2.setText(String.valueOf(keyCnst.getMaxDaysPerCD()));
          
          stdmzmin2.setText(String.valueOf(keyCnst.getMinMZ()));
          stdmzmax2.setText(String.valueOf(keyCnst.getMaxMZ()));
          stdmzpd2.setText(String.valueOf(keyCnst.getMaxDaysPerMZ()));
          
          stdbkRpd2.setText(String.valueOf(keyCnst.getReneivelPerBook()));
          stdbkRcount2.setText(String.valueOf(keyCnst.getMaxReneivelPerBook()));
          
          stdcdRpd2.setText(String.valueOf(keyCnst.getReneivelPerCD()));
          stdcdRcount2.setText(String.valueOf(keyCnst.getMaxReneivelPerCD()));
          
          stdmzRpd2.setText(String.valueOf(keyCnst.getReneivelPerMZ()));
          stdmzRcount2.setText(String.valueOf(keyCnst.getReneivelPerMZ()));
          
          stdbkFine2.setText(String.valueOf(keyCnst.getFinePerBook()));
          stdcdFine2.setText(String.valueOf(keyCnst.getFinePerCD()));
          stdmzFine2.setText(String.valueOf(keyCnst.getFinePerMZ()));
      }
      public void populateStfConstraints(org.ulibsoft.model.KeyConstraints keyCnst)
      {
    	  stfbkmin2.setText(String.valueOf(keyCnst.getMinBook()));
          stfbkmax2.setText(String.valueOf(keyCnst.getMaxBook()));
          stfbkhold2.setText(String.valueOf(keyCnst.getMaxDaysPerBook()));
          
          stfcdmin2.setText(String.valueOf(keyCnst.getMinCD()));
          stfcdmax2.setText(String.valueOf(keyCnst.getMaxCD()));
          stfcdhold2.setText(String.valueOf(keyCnst.getMaxDaysPerCD()));
         
          stfmzmin2.setText(String.valueOf(keyCnst.getMinMZ()));
          stfmzmax2.setText(String.valueOf(keyCnst.getMaxMZ()));
          stfmzhold2.setText(String.valueOf(keyCnst.getMaxDaysPerMZ()));
          
          stfbk_Hld_From2.setText(DateHelper.format(keyCnst.getBeginDate()));
          stfbk_Hld_To2.setText(DateHelper.format(keyCnst.getEndDate()));
      }
      private void createComponents()
        {
           c = getContentPane( ) ;
           c.setBackground( new Color(0,0,40) ) ;
           c.setLayout ( new AbsoluteLayout( ) ) ;

           cmppn = new JPanel( new AbsoluteLayout() );
           cmppn.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                            "INPUT -- CONSTRAINTS", 1, 2, c.getFont(), Color.magenta));
           cmppn.setBackground(c.getBackground());
           c.add(cmppn,new AbsoluteConstraints(50,60,690,480));

           cmppn.add(std = new JLabel("==>  STUDENT "),new AbsoluteConstraints(50,47));
           std.setForeground(Color.cyan);

           cmppn.add(std1 = new JLabel("==>  STUDENT "),new AbsoluteConstraints(270,47));
           std1.setForeground(Color.cyan);

           cmppn.add(std2 = new JLabel("==>  STUDENT "),new AbsoluteConstraints(490,47));
           std2.setForeground(Color.cyan);

           cmppn.add(stf = new JLabel("==>  PROFESSOR"),new AbsoluteConstraints(50,245));
           stf.setForeground(Color.cyan);

           cmppn.add(stf1 = new JLabel("==>  PROFESSOR"),new AbsoluteConstraints(270,245));
           stf1.setForeground(Color.cyan);

           cmppn.add(stf2 = new JLabel("==>  PROFESSOR"),new AbsoluteConstraints(490,245));
           stf2.setForeground(Color.cyan);

           cmppn.add(book = new JLabel("==>  BOOK"),new AbsoluteConstraints(30,23));
           book.setForeground(Color.green);

           cmppn.add(stdbkmin = new JLabel("MINIMUM   BOOKS  :"),new AbsoluteConstraints(30,90-15));
           stdbkmin.setForeground(new Color(120,120,153));
           cmppn.add(stdbkmin2 = new JTextField(),new AbsoluteConstraints(150,88-15,60,20));
           stdbkmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkmax = new JLabel("MAXIMUM  BOOKS :"),new AbsoluteConstraints(30,120-17));
           stdbkmax.setForeground(new Color(120,120,153));
           cmppn.add(stdbkmax2 = new JTextField(),new AbsoluteConstraints(150,118-17,60,20));
           stdbkmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkpd = new JLabel("> HOLDING  PERIOD"),new AbsoluteConstraints(30,150-19));
           stdbkpd.setForeground(new Color(120,120,153));
           cmppn.add(stdbkpd2 = new JTextField(),new AbsoluteConstraints(150,148-19,60,20));
           stdbkpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkRpd = new JLabel("> RENIVEL PERIOD :"),new AbsoluteConstraints(30,180-21));
           stdbkRpd.setForeground(new Color(120,120,153));
           cmppn.add(stdbkRpd2 = new JTextField(),new AbsoluteConstraints(150,178-21,60,20));
           stdbkRpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkRcount = new JLabel("NO_OF_RENIVELS  :"),new AbsoluteConstraints(30,210-23));
           stdbkRcount.setForeground(new Color(120,120,153));
           cmppn.add(stdbkRcount2 = new JTextField(),new AbsoluteConstraints(150,208-23,60,20));
           stdbkRcount2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdbkFine = new JLabel("<< FINE PER DAY >>"),new AbsoluteConstraints(30,240-25));
           stdbkFine.setForeground(new Color(120,120,153));
           cmppn.add(stdbkFine2 = new JTextField(),new AbsoluteConstraints(150,238-25,60,20));
           stdbkFine2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(cd = new JLabel("==> FLOPPYS"),new AbsoluteConstraints(250,23));
           cd.setForeground(Color.green);

           cmppn.add(stdcdmin = new JLabel("<< MINIMUM CDS >>"),new AbsoluteConstraints(250,90-15));
           stdcdmin.setForeground(new Color(120,120,153));
           cmppn.add(stdcdmin2 = new JTextField(),new AbsoluteConstraints(250+125,90-2-15,60,20));
           stdcdmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdmax = new JLabel(">>> MAXIMUM CDS :"),new AbsoluteConstraints(250,120-17));
           stdcdmax.setForeground(new Color(120,120,153));
           cmppn.add(stdcdmax2 = new JTextField(),new AbsoluteConstraints(250+125,120-2-17,60,20));
           stdcdmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdpd = new JLabel(">> HOLDING PERIOD"),new AbsoluteConstraints(250,150-19));
           stdcdpd.setForeground(new Color(120,120,153));
           cmppn.add(stdcdpd2 = new JTextField(),new AbsoluteConstraints(250+125,150-2-19,60,20));
           stdcdpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdRpd = new JLabel(">> RENIVEL PERIOD :"),new AbsoluteConstraints(250,180-21));
           stdcdRpd.setForeground(new Color(120,120,153));
           cmppn.add(stdcdRpd2 = new JTextField(),new AbsoluteConstraints(250+125,180-2-21,60,20));
           stdcdRpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdRcount = new JLabel("> NO_OF_RENIVELS :"),new AbsoluteConstraints(250,210-23));
           stdcdRcount.setForeground(new Color(120,120,153));
           cmppn.add(stdcdRcount2 = new JTextField(),new AbsoluteConstraints(250+125,210-2-23,60,20));
           stdcdRcount2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdcdFine = new JLabel("<<  FINE PER DAY  >>"),new AbsoluteConstraints(250,240-25));
           stdcdFine.setForeground(new Color(120,120,153));
           cmppn.add(stdcdFine2 = new JTextField(),new AbsoluteConstraints(250+125,240-2-25,60,20));
           stdcdFine2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(mag = new JLabel("==>  MAGAZINE"),new AbsoluteConstraints(470,23));
           mag.setForeground(Color.green);

           cmppn.add(stdmzmin = new JLabel("MINIMUM  JOURNALS :"),new AbsoluteConstraints(470,90-15));
           stdmzmin.setForeground(new Color(120,120,153));
           cmppn.add(stdmzmin2 = new JTextField(),new AbsoluteConstraints(470+135,90-2-15,60,20));
           stdmzmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzmax = new JLabel("MAXIMUM  JOURNALS"),new AbsoluteConstraints(470,120-17));
           stdmzmax.setForeground(new Color(120,120,153));
           cmppn.add(stdmzmax2 = new JTextField(),new AbsoluteConstraints(470+135,120-2-17,60,20));
           stdmzmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzpd = new JLabel(">>  HOLDING  PERIOD  :"),new AbsoluteConstraints(470,150-19));
           stdmzpd.setForeground(new Color(120,120,153));
           cmppn.add(stdmzpd2 = new JTextField(),new AbsoluteConstraints(470+135,150-2-19,60,20));
           stdmzpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzRpd = new JLabel("<< RENIVEL PERIOD >>"),new AbsoluteConstraints(470,180-21));
           stdmzRpd.setForeground(new Color(120,120,153));
           cmppn.add(stdmzRpd2 = new JTextField(),new AbsoluteConstraints(470+135,180-2-21,60,20));
           stdmzRpd2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzRcount = new JLabel(">>  NO_OF_RENIVELS  :"),new AbsoluteConstraints(470,210-23));
           stdmzRcount.setForeground(new Color(120,120,153));
           cmppn.add(stdmzRcount2 = new JTextField(),new AbsoluteConstraints(470+135,210-2-23,60,20));
           stdmzRcount2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stdmzFine = new JLabel("<<  FINE - PER - DAY  >>"),new AbsoluteConstraints(470,240-25));
           stdmzFine.setForeground(new Color(120,120,153));
           cmppn.add(stdmzFine2 = new JTextField(),new AbsoluteConstraints(470+135,240-2-25,60,20));
           stdmzFine2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           //STAFF

           cmppn.add(stfbkmin = new JLabel("MINIMUM   BOOKS  :"),new AbsoluteConstraints(30,300-25));
           stfbkmin.setForeground(new Color(120,120,153));
           cmppn.add(stfbkmin2 = new JTextField(),new AbsoluteConstraints(150,298-25,60,20));
           stfbkmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfbkmax = new JLabel("MAXIMUM  BOOKS :"),new AbsoluteConstraints(30,330-27));
           stfbkmax.setForeground(new Color(120,120,153));
           cmppn.add(stfbkmax2 = new JTextField(),new AbsoluteConstraints(150,328-27,60,20));
           stfbkmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfbkhold = new JLabel("HOLDING PERIOD :"),new AbsoluteConstraints(30,360-29));
           stfbkhold.setForeground(new Color(120,120,153));
           cmppn.add(stfbkhold2 = new JTextField(),new AbsoluteConstraints(150,358-29,60,20));
           stfbkhold2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(stfcdmin = new JLabel("<< MINIMUM CDS >>"),new AbsoluteConstraints(250,300-29));
           stfcdmin.setForeground(new Color(120,120,153));
           cmppn.add(stfcdmin2 = new JTextField(),new AbsoluteConstraints(250+125,300-29-2,60,20));
           stfcdmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfcdmax = new JLabel(">>> MAXIMUM CDS :"),new AbsoluteConstraints(250,330-31));
           stfcdmax.setForeground(new Color(120,120,153));
           cmppn.add(stfcdmax2 = new JTextField(),new AbsoluteConstraints(250+125,330-2-31,60,20));
           stfcdmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfcdhold = new JLabel("   HOLDING PERIOD"),new AbsoluteConstraints(250,360-33));
           stfcdhold.setForeground(new Color(120,120,153));
           cmppn.add(stfcdhold2 = new JTextField(),new AbsoluteConstraints(250+125,358-33,60,20));
           stfcdhold2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(stfmzmin = new JLabel("MINIMUM  JOURNALS :"),new AbsoluteConstraints(470,300-33));
           stfmzmin.setForeground(new Color(120,120,153));
           cmppn.add(stfmzmin2 = new JTextField(),new AbsoluteConstraints(470+135,300-2-33,60,20));
           stfmzmin2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfmzmax = new JLabel("MAXIMUM  JOURNALS"),new AbsoluteConstraints(470,330-35));
           stfmzmax.setForeground(new Color(120,120,153));
           cmppn.add(stfmzmax2 = new JTextField(),new AbsoluteConstraints(470+135,330-2-35,60,20));
           stfmzmax2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfmzhold = new JLabel("  HOLDING PERIOD   "),new AbsoluteConstraints(470,360-37));
           stfmzhold.setForeground(new Color(120,120,153));
           cmppn.add(stfmzhold2 = new JTextField(),new AbsoluteConstraints(470+135,358-2-37,60,20));
           stfmzhold2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );


           cmppn.add(stfbk_Hld_From = new JLabel("STAFF  BOOKS / CDS / MAGAZINES /JOURNALS HOLDIG  PERIOD  FROM :"),new AbsoluteConstraints(30,360-9+15));
           stfbk_Hld_From.setForeground(new Color(120,120,153));

           cmppn.add(stfbk_Hld_From2 = new JTextField(),new AbsoluteConstraints(440,358-39+30+15,90,20));
           stfbk_Hld_From2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(stfbk_Hld_To = new JLabel("TO :"),new AbsoluteConstraints(545,360-39+30+15));
           stfbk_Hld_To.setForeground(new Color(120,120,153));

           cmppn.add(stfbk_Hld_To2 = new JTextField(),new AbsoluteConstraints(575,358-39+30+15,90,20));
           stfbk_Hld_To2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           cmppn.add(imagePath = new JLabel("IMAGE  PATH  FOR  STUDENT / PROFESSOR :"),new AbsoluteConstraints(30,390-41+30+15));
           imagePath.setForeground(new Color(120,120,153));

           cmppn.add(imagePath2 = new JTextField(),new AbsoluteConstraints(285,388-41+30+15,380,20));
           imagePath2.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );

           ins = new JButton( "INSERT" ) ;
           ins.setBackground ( Color.cyan  );
           ins.setForeground( Color .black );
           ins.setMnemonic('S');
           ins.setBorder ( new BevelBorder ( 0 ));
           cmppn.add ( ins, new AbsoluteConstraints( 85+40, 380+5+30+15,110,27 ) );

           up = new JButton( "UPDATE" ) ;
           up.setBackground ( Color.cyan  );
           up.setForeground( Color .black );
           up.setMnemonic('A');
           //up.setEnabled(false);
           up.setBorder ( new BevelBorder ( 0 ));
           cmppn.add ( up, new AbsoluteConstraints( 197+40, 380+35+15,110,27 ) );

           next = new JButton( "NEXT>>>" ) ;
           next.setBackground ( Color.cyan );
           next.setForeground(  Color .black );
           next.setMnemonic('N');
           next.setBorder ( new BevelBorder (0));
           cmppn.add ( next, new AbsoluteConstraints( 308+40,380+35+15,110,27 ) );

           quit = new JButton( "EXIT" ) ;
           quit.setBackground ( Color.cyan );
           quit.setMnemonic('X');
           quit.setForeground( Color .black );
           quit.setBorder ( new BevelBorder (0));
           cmppn.add ( quit, new AbsoluteConstraints( 420+40,380+35+15,110,27 ) );

           setVisible(true);
        }


      private void componentListener()
        {

           ins.addActionListener(new ActionListener()
              {
                 public void actionPerformed( ActionEvent e )
                   {
                	 org.ulibsoft.model.KeyConstraints keyCnst = new org.ulibsoft.model.KeyConstraints();
                	 
                	 keyCnst.setId((short)1);
                	 if ( stdbkmin2.getText() != null )
                		 keyCnst.setMinBook((short)Integer.parseInt(stdbkmin2.getText()));
                	 if ( stdbkmax2.getText() != null )
                		 keyCnst.setMaxBook((short)Integer.parseInt(stdbkmax2.getText()));
                	 if ( stdbkpd2.getText() != null )
                		 keyCnst.setMaxDaysPerBook((short)Integer.parseInt(stdbkpd2.getText()));
                	 if ( stdcdmin2.getText() != null )
                		 keyCnst.setMinCD((short)Integer.parseInt(stdcdmin2.getText()));
                	 if ( stdcdmax2.getText() != null )
                		 keyCnst.setMaxCD((short)Integer.parseInt(stdcdmax2.getText()));
                	 if ( stdcdpd2.getText() != null )
                		 keyCnst.setMaxDaysPerCD((short)Integer.parseInt(stdcdpd2.getText()));
                	 if ( stdmzmin2.getText() != null )
                		 keyCnst.setMinMZ((short)Integer.parseInt(stdmzmin2.getText()));
                	 if ( stdmzmax2.getText() != null )
                		 keyCnst.setMaxMZ((short)Integer.parseInt(stdmzmax2.getText()));
                	 if ( stdmzpd2.getText() != null )
                		 keyCnst.setMaxDaysPerMZ((short)Integer.parseInt(stdmzpd2.getText()));
                	 if ( stdbkRpd2.getText() != null )
                		 keyCnst.setReneivelPerBook((short)Integer.parseInt(stdbkRpd2.getText()));
                	 if ( stdbkRcount2.getText() != null )
                		 keyCnst.setMaxReneivelPerBook((short)Integer.parseInt(stdbkRcount2.getText()));
                	 if ( stdcdRpd2.getText() != null )
                		 keyCnst.setReneivelPerBook((short)Integer.parseInt(stdcdRpd2.getText()));
                	 if ( stdcdRcount2.getText() != null )
                		 keyCnst.setMaxReneivelPerBook((short)Integer.parseInt(stdcdRcount2.getText()));
                	 if ( stdbkFine2.getText() != null )
                		 keyCnst.setFinePerBook((short)Integer.parseInt(stdbkFine2.getText()));
                	 if ( stdcdFine2.getText() != null )
                		 keyCnst.setFinePerBook((short)Integer.parseInt(stdcdFine2.getText()));
                	 if ( stdmzFine2.getText() != null )
                		 keyCnst.setFinePerBook((short)Integer.parseInt(stdmzFine2.getText()));
                	 
                	 int ret = keyCnstDao.create(keyCnst);
                	 if ( ret <= 0 )
                	 {
                		 log.warn("Key Constraints creation failed");
                		 JOptionPane.showMessageDialog(null,"CREATE FAILED");
                	 }
                	 else
                	 {
                		 log.info("Key Constraints created successfully");
                		 JOptionPane.showMessageDialog(null,"CREATED");
                	 }
                	 
                	 org.ulibsoft.model.KeyConstraints stfCnst = new org.ulibsoft.model.KeyConstraints();
                	 stfCnst.setId((short)2);
                	 if ( stfbkmin2.getText() != null )
                		 stfCnst.setMinBook((short)Integer.parseInt(stfbkmin2.getText()));
                	 if ( stfbkmax2.getText() != null )
                		 stfCnst.setMaxBook((short)Integer.parseInt(stfbkmax2.getText()));
                	 if ( stfbkhold2.getText() != null )
                		 stfCnst.setMaxDaysPerBook((short)Integer.parseInt(stfbkhold2.getText()));
                	 if ( stfcdmin2.getText() != null )
                		 stfCnst.setMinCD((short)Integer.parseInt(stfcdmin2.getText()));
                	 if ( stfcdmax2.getText() != null )
                		 stfCnst.setMaxCD((short)Integer.parseInt(stfcdmax2.getText()));
                	 if ( stfcdhold2.getText() != null )
                		 stfCnst.setMaxDaysPerCD((short)Integer.parseInt(stfcdhold2.getText()));
                	 if ( stfmzmin2.getText() != null )
                		 stfCnst.setMinMZ((short)Integer.parseInt(stfmzmin2.getText()));
                	 if ( stfmzmax2.getText() != null )
                		 stfCnst.setMaxMZ((short)Integer.parseInt(stfmzmax2.getText()));
                	 if ( stfmzhold2.getText() != null )
                		 stfCnst.setMaxDaysPerMZ((short)Integer.parseInt(stfmzhold2.getText()));
                	 try {
						stfCnst.setBeginDate(DateHelper.parse(stfbk_Hld_From2.getText()));
						stfCnst.setEndDate(DateHelper.parse(stfbk_Hld_To2.getText()));
                	 } catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Invalid date format, expected (dd-Mon-yyyy)");						
                	 }
                	 int ret1 = keyCnstDao.create(stfCnst);
                	 if ( ret1 <= 0 )
                	 {
                		 log.warn("Key Constraintsfor staff creation failed");
                		 JOptionPane.showMessageDialog(null,"CREATE FAILED");
                	 }
                	 else
                	 {
                		 log.info("Key Constraints created successfully");
                		 JOptionPane.showMessageDialog(null,"CREATED");
                	 }
                	 
                }
              }
           );
           up.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
             {
            	 org.ulibsoft.model.KeyConstraints keyCnst = new org.ulibsoft.model.KeyConstraints();
            	 
            	 keyCnst.setId((short)1);
            	 if ( stdbkmin2.getText() != null )
            		 keyCnst.setMinBook((short)Integer.parseInt(stdbkmin2.getText()));
            	 if ( stdbkmax2.getText() != null )
            		 keyCnst.setMaxBook((short)Integer.parseInt(stdbkmax2.getText()));
            	 if ( stdbkpd2.getText() != null )
            		 keyCnst.setMaxDaysPerBook((short)Integer.parseInt(stdbkpd2.getText()));
            	 if ( stdcdmin2.getText() != null )
            		 keyCnst.setMinCD((short)Integer.parseInt(stdcdmin2.getText()));
            	 if ( stdcdmax2.getText() != null )
            		 keyCnst.setMaxCD((short)Integer.parseInt(stdcdmax2.getText()));
            	 if ( stdcdpd2.getText() != null )
            		 keyCnst.setMaxDaysPerCD((short)Integer.parseInt(stdcdpd2.getText()));
            	 if ( stdmzmin2.getText() != null )
            		 keyCnst.setMinMZ((short)Integer.parseInt(stdmzmin2.getText()));
            	 if ( stdmzmax2.getText() != null )
            		 keyCnst.setMaxMZ((short)Integer.parseInt(stdmzmax2.getText()));
            	 if ( stdmzpd2.getText() != null )
            		 keyCnst.setMaxDaysPerMZ((short)Integer.parseInt(stdmzpd2.getText()));
            	 if ( stdbkRpd2.getText() != null )
            		 keyCnst.setReneivelPerBook((short)Integer.parseInt(stdbkRpd2.getText()));
            	 if ( stdbkRcount2.getText() != null )
            		 keyCnst.setMaxReneivelPerBook((short)Integer.parseInt(stdbkRcount2.getText()));
            	 if ( stdcdRpd2.getText() != null )
            		 keyCnst.setReneivelPerBook((short)Integer.parseInt(stdcdRpd2.getText()));
            	 if ( stdcdRcount2.getText() != null )
            		 keyCnst.setMaxReneivelPerBook((short)Integer.parseInt(stdcdRcount2.getText()));
            	 if ( stdbkFine2.getText() != null )
            		 keyCnst.setFinePerBook((short)Integer.parseInt(stdbkFine2.getText()));
            	 if ( stdcdFine2.getText() != null )
            		 keyCnst.setFinePerBook((short)Integer.parseInt(stdcdFine2.getText()));
            	 if ( stdmzFine2.getText() != null )
            		 keyCnst.setFinePerBook((short)Integer.parseInt(stdmzFine2.getText()));
            	 
            	 int ret = keyCnstDao.update(keyCnst);
            	 if ( ret <= 0 )
            	 {
            		 log.warn("Key Constraintsfor staff updated failed");
            		 JOptionPane.showMessageDialog(null,"UPDATE FAILED");
            	 }
            	 else
            	 {
            		 log.info("Key Constraints updated successfully");
            		 JOptionPane.showMessageDialog(null,"UPDATED");
            	 }
            	 
            	 org.ulibsoft.model.KeyConstraints stfCnst = new org.ulibsoft.model.KeyConstraints();
            	 stfCnst.setId((short)2);
            	 if ( stfbkmin2.getText() != null )
            		 stfCnst.setMinBook((short)Integer.parseInt(stfbkmin2.getText()));
            	 if ( stfbkmax2.getText() != null )
            		 stfCnst.setMaxBook((short)Integer.parseInt(stfbkmax2.getText()));
            	 if ( stfbkhold2.getText() != null )
            		 stfCnst.setMaxDaysPerBook((short)Integer.parseInt(stfbkhold2.getText()));
            	 if ( stfcdmin2.getText() != null )
            		 stfCnst.setMinCD((short)Integer.parseInt(stfcdmin2.getText()));
            	 if ( stfcdmax2.getText() != null )
            		 stfCnst.setMaxCD((short)Integer.parseInt(stfcdmax2.getText()));
            	 if ( stfcdhold2.getText() != null )
            		 stfCnst.setMaxDaysPerCD((short)Integer.parseInt(stfcdhold2.getText()));
            	 if ( stfmzmin2.getText() != null )
            		 stfCnst.setMinMZ((short)Integer.parseInt(stfmzmin2.getText()));
            	 if ( stfmzmax2.getText() != null )
            		 stfCnst.setMaxMZ((short)Integer.parseInt(stfmzmax2.getText()));
            	 if ( stfmzhold2.getText() != null )
            		 stfCnst.setMaxDaysPerMZ((short)Integer.parseInt(stfmzhold2.getText()));
            	 try {
					stfCnst.setBeginDate(DateHelper.parse(stfbk_Hld_From2.getText()));
					stfCnst.setEndDate(DateHelper.parse(stfbk_Hld_To2.getText()));
            	 } catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Invalid date format, expected (dd-Mon-yyyy)");						
            	 }
            	 int ret1 = keyCnstDao.update(stfCnst);
            	 if ( ret1 <= 0 )
            	 {
            		 log.warn("Key Constraintsfor staff updated failed");
            		 JOptionPane.showMessageDialog(null,"UPDATE FAILED");
            	 }
            	 else
            	 {
            		 log.info("Key Constraints updated successfully");
            		 JOptionPane.showMessageDialog(null,"UPDATED");
            	 }
             }
           }
           );


           next.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
             {
                //RESET STUDENT FEILD CONSTRAINTS
                stdbkmin2.setText("");
                stdbkmax2.setText("");
                stdcdmin2.setText("");
                stdcdmax2.setText("");
                stdmzmin2.setText("");
                stdmzmax2.setText("");
                stdbkFine2.setText("");
                stdbkpd2.setText("");
                stdcdFine2.setText("");
                stdcdpd2.setText("");
                stdmzFine2.setText("");
                stdmzpd2.setText("");
                stdbkRcount2.setText("");
                stdcdRcount2.setText("");
                stdmzRcount2.setText("");
                stdbkRpd2.setText("");
                stdcdRpd2.setText("");
                stdmzRpd2.setText("");
                //RESET IMAGE FEILD   CONSTRAINTS
                imagePath2.setText("");
                //RESET STAFF FIELDS CONSTRAINTS
                stfbkmin2.setText("");
                stfbkmax2.setText("");
                stfcdmin2.setText("");
                stfcdmax2.setText("");
                stfmzmin2.setText("");
                stfmzmax2.setText("");
                stfbk_Hld_From2.setText("");
                stfbk_Hld_To2.setText("");
                stfbkhold2.setText("");
                stfcdhold2.setText("");
                stfmzhold2.setText("");
                
                org.ulibsoft.model.KeyConstraints stdKeyCnst = keyCnstDao.findById((short)1);
                populateStdConstraints(stdKeyCnst);
                
                org.ulibsoft.model.KeyConstraints stfKeyCnst = keyCnstDao.findById((short)2);
                populateStfConstraints(stfKeyCnst);
             }
           }
           );
           quit.addActionListener(new ActionListener()
           {
             public void actionPerformed(ActionEvent e)
             {
               setVisible(false);
             }
           }
           );
        }

      public static void main(String a[])
        {
           new KeyConstraints();
        }

  }


