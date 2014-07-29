package org.ulibsoft.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.ulibsoft.clearence.Clear;
import org.ulibsoft.enroll.CdLibrary2;
import org.ulibsoft.enroll.MzLibrary2;
import org.ulibsoft.issues.CdStdIss;
import org.ulibsoft.issues.CdStfIss;
import org.ulibsoft.issues.IssueBooks;
import org.ulibsoft.issues.MzStdIss;
import org.ulibsoft.issues.MzStfIss;
import org.ulibsoft.issues.StaffIssue;
import org.ulibsoft.login.Login;
import org.ulibsoft.register.ui.Library2;
import org.ulibsoft.register.ui.StaffDetails;
import org.ulibsoft.register.ui.StudentDetails;
import org.ulibsoft.returns.CdStdRet;
import org.ulibsoft.returns.CdStfRet;
import org.ulibsoft.returns.MzStdRet;
import org.ulibsoft.returns.MzStfRet;
import org.ulibsoft.returns.Retrive;
import org.ulibsoft.returns.StaffRetrive;
import org.ulibsoft.search.catalog.CdLibrary1;
import org.ulibsoft.search.catalog.Library1;
import org.ulibsoft.search.catalog.MzLibrary1;
import org.ulibsoft.search.defaulters.CdPendingList;
import org.ulibsoft.search.defaulters.MzPendingList;
import org.ulibsoft.search.defaulters.PendingList;
import org.ulibsoft.search.entitySearch.BookSearch;
import org.ulibsoft.search.entitySearch.CdCopies;
import org.ulibsoft.search.entitySearch.CdSearch;
import org.ulibsoft.search.entitySearch.Copies;
import org.ulibsoft.search.entitySearch.MagezineSearch;
import org.ulibsoft.search.entitySearch.MzCopies;


public class PopUpMenu  extends JPopupMenu
  {
      private JPopupMenu pop;

      private JMenu issue,retrive,catalog,search,insertion,features;

      private JMenu bookissue,cdissue,magissue;
      private JMenu bookret,cdret,magret;
      private JMenu bksearch,cdsearch,mzsearch;
      private JMenu pending,printing ;

      private JMenuItem mainmenu;

      private JMenuItem bstdiss,bstfiss,cstdiss,cstfiss,mstdiss,mstfiss;
      private JMenuItem bstdret,bstfret,cstdret,cstfret,mstdret,mstfret;
      private JMenuItem bkcat,cdcat,mzcat;
      private JMenuItem bqsearch,bkprsn,bkarc,bkref;
      private JMenuItem cqsearch,cdprsn,cdarc,cdref;
      private JMenuItem mqsearch,mzprsn,mzarc,mzref;
      private JMenuItem bplist,bcertify;
      private JMenuItem cdplist,cdcertify;
      private JMenuItem mzplist,mzcertify;
      
      private JMenuItem student1,staff1;
      
      private JMenuItem student,staff,book,cd,magazine,transaction,certify;

      //private JFrame fr;

      public PopUpMenu( Component c, int x, int y )
        {
           pop = new JPopupMenu();

           //ISSUE
           issue      = new JMenu("Issue");
           bookissue  = new JMenu("bookIssue");
           cdissue    = new JMenu("CdIssue");
           magissue   = new JMenu("Mag..Issue");

           bstdiss    = new JMenuItem("Student");
           bstfiss    = new JMenuItem("Staff");
           cstdiss    = new JMenuItem("Student");
           cstfiss    = new JMenuItem("Staff");
           mstdiss    = new JMenuItem("Student");
           mstfiss    = new JMenuItem("Staff");

           bookissue.add(bstdiss);
           bookissue.add(bstfiss);
           cdissue.add(cstdiss);
           cdissue.add(cstfiss);
           magissue.add(mstdiss);
           magissue.add(mstfiss);

           issue.add(bookissue);
           issue.add(cdissue);
           issue.add(magissue);

           //RETRIEVE
           retrive    = new JMenu("Retrieve");
           bookret    = new JMenu("BookReturn");
           cdret      = new JMenu("CdReturn");
           magret     = new JMenu("Mag..Return");

           bstdret    = new JMenuItem("Student");
           bstfret    = new JMenuItem("Staff");
           cstdret    = new JMenuItem("Student");
           cstfret    = new JMenuItem("Staff");
           mstdret    = new JMenuItem("Student");
           mstfret    = new JMenuItem("Staff");

           bookret.add(bstdret);
           bookret.add(bstfret);
           cdret.add(cstdret);
           cdret.add(cstfret);
           magret.add(mstdret);
           magret.add(mstfret);

           retrive.add(bookret);
           retrive.add(cdret);
           retrive.add(magret);

           //CATALOG
           catalog    = new JMenu("Catalog");
           bkcat      = new JMenuItem("BookCatalog");
           cdcat      = new JMenuItem("CdCatalog");
           mzcat      = new JMenuItem("Mag..Catalog");

           catalog.add(bkcat);
           catalog.add(cdcat);
           catalog.add(mzcat);

           //SEARCHING
           search     = new JMenu("Search");
           bksearch   = new JMenu("BookSearch");
           cdsearch   = new JMenu("CdSearch");
           mzsearch   = new JMenu("Mag..Search");

           bqsearch   = new JMenuItem("QuickSearch");
           bkprsn     = new JMenuItem("Person");
           bkarc      = new JMenuItem("Archive");
           bkref      = new JMenuItem("Reference");

           bksearch.add(bqsearch);
           bksearch.add(bkprsn);
           bksearch.add(bkarc);
           bksearch.add(bkref);

           cqsearch   = new JMenuItem("QuickSearch");
           cdprsn     = new JMenuItem("Person");
           cdarc      = new JMenuItem("Archive");
           cdref      = new JMenuItem("Reference");

           cdsearch.add(cqsearch);
           cdsearch.add(cdprsn);
           cdsearch.add(cdarc);
           cdsearch.add(cdref);

           mqsearch   = new JMenuItem("QuickSearch");
           mzprsn     = new JMenuItem("Person");
           mzarc      = new JMenuItem("Archive");
           mzref      = new JMenuItem("Reference");

           mzsearch.add(mqsearch);
           mzsearch.add(mzprsn);
           mzsearch.add(mzarc);
           mzsearch.add(mzref);

           search.add(bksearch);
           search.add(cdsearch);
           search.add(mzsearch);
           
           //INSERTION
           insertion   = new JMenu("Insertion");
           student1    = new JMenuItem("Student");
           staff1      = new JMenuItem("Staff");
           
           insertion.add(student1);
           insertion.add(staff1);
           
           

           
           //FEATURES
           features   = new JMenu("Features");
           pending    = new JMenu("Defaulters");
           bplist  = new JMenuItem("Book");
           cdplist  = new JMenuItem("Cd/Floppy");
           mzplist  = new JMenuItem("Magazine");

           pending.add(bplist);
           pending.add(cdplist);
           pending.add(mzplist);

           certify    = new JMenuItem("Certify");

           features.add(pending);         

           //MAIN-MENU
           mainmenu   = new JMenuItem("MainMenu");

           pop.add(issue);
           pop.add(retrive);
           pop.add(catalog);
           pop.add(search);
           pop.add(insertion);
           pop.add(features);
           pop.add(mainmenu);

           MyActionListener listener = new MyActionListener();
           bstdiss.addActionListener(listener);
           bstfiss.addActionListener(listener);
           cstdiss.addActionListener(listener);
           cstfiss.addActionListener(listener);
           mstdiss.addActionListener(listener);
           mstfiss.addActionListener(listener);

           bstdret.addActionListener(listener);
           bstfret.addActionListener(listener);
           cstdret.addActionListener(listener);
           cstfret.addActionListener(listener);
           mstdret.addActionListener(listener);
           mstfret.addActionListener(listener);

           bkcat.addActionListener(listener);
           cdcat.addActionListener(listener);
           mzcat.addActionListener(listener);

           bqsearch.addActionListener(listener);
           bkprsn.addActionListener(listener);
           bkarc.addActionListener(listener);
           bkref.addActionListener(listener);

           cqsearch.addActionListener(listener);
           cdprsn.addActionListener(listener);
           cdarc.addActionListener(listener);
           cdref.addActionListener(listener);

           mqsearch.addActionListener(listener);
           mzprsn.addActionListener(listener);
           mzarc.addActionListener(listener);
           mzref.addActionListener(listener);

           bplist.addActionListener(listener);
           cdplist.addActionListener(listener);
           mzplist.addActionListener(listener);
           
           student1.addActionListener(listener);
           staff1.addActionListener(listener);

           certify.addActionListener(listener);

           mainmenu.addActionListener(listener);

           pop.show (c,x,y);

        }

      private class MyActionListener implements ActionListener
        {
           public void actionPerformed(ActionEvent e)
             {
               //fr.setVisible(false);
               if ( e.getSource() == bstdiss )
                {
                   IssueBooks i=new IssueBooks();
                }
              else if( e.getSource() == bstfiss )
                {
                   StaffIssue si=new StaffIssue();
                }
              else if(e.getSource() == cstdiss )
                {
                   CdStdIss cst = new CdStdIss();
                }
              else if(e.getSource() == cstfiss )
                {
                   CdStfIss csf = new CdStfIss();
                }
              else if(e.getSource() == mstdiss )
                {
                   MzStdIss mzst = new MzStdIss();
                }
              else if( e.getSource()== mstfiss )
                {
                   MzStfIss mzsf = new MzStfIss();
                }
              else if(e.getSource() == bstdret)
                {
                   Retrive ret = new Retrive();
                }

              else if(e.getSource() == bstfret )
                {
                   StaffRetrive stfret = new StaffRetrive();
                }
              else if(e.getSource() == cstdret )
                {
                   CdStdRet cdstret = new CdStdRet();
                }
              else if(e.getSource() == cstfret )
                {
                   CdStfRet cdsfret = new CdStfRet();
                }
              else if(e.getSource() == mstdret )
                {
                   MzStdRet mzstret = new MzStdRet();
                }
              else if(e.getSource() == mstfret )
                {
                   MzStfRet mzstfret = new MzStfRet();
                }
              else if(e.getSource() == bkcat )
                {
                   Library2 lib2 = new Library2();
                }
              else if(e.getSource() == cdcat )
                {
                   CdLibrary2 cdlib2 = new CdLibrary2();
                }
              else if(e.getSource() == mzcat )
                {
                   MzLibrary2 mzlib2 = new MzLibrary2();
                }
              else if(e.getSource() == bqsearch)
                {
                   new Searching();
                }
              else if(e.getSource() == bkprsn )
                {
                   BookSearch bs = new BookSearch();
                }
              else if( e.getSource() == bkarc )
                {
                   Copies cp = new Copies();
                }
              else if( e.getSource() == bkref )
                {
                   Library1 lib1 = new Library1();
                }
              else if( e.getSource() == cqsearch )
                {
                   new Searching();
                }
              else if( e.getSource() == cdprsn )
                {
                   CdSearch cdsr = new CdSearch();
                }
              else if( e.getSource() == cdarc )
                {
                   CdCopies cdcp = new CdCopies();
                }
              else if( e.getSource() == cdref )
                {
                   CdLibrary1 cdlib1 = new CdLibrary1();
                }
              else if( e.getSource() == mqsearch )
                {
                   new Searching();
                }
              else if( e.getSource() == mzprsn )
                {
                   MagezineSearch mz = new MagezineSearch();
                }
              else if( e.getSource() == mzarc )
                {
                   MzCopies mzcp = new MzCopies();
                }
              else if( e.getSource() == mzref )
                {
                   MzLibrary1 mzlib1= new MzLibrary1();
                }
              else if( e.getSource() == bplist )
                {
                   PendingList pl = new PendingList();
                }
              else if( e.getSource() == cdplist )
                {
                   CdPendingList cdp = new CdPendingList();
                }
              else if( e.getSource() == mzplist )
                {
                   MzPendingList mzpl = new MzPendingList();
                }
              else if( e.getSource() == student1 )
                {
                   StudentDetails stdtls= new StudentDetails();
                }
              else if( e.getSource() == staff1 )
                {
                   StaffDetails stfdtls = new StaffDetails();
                }
              else if( e.getSource() == certify )
                {
                   Clear cl = new Clear();
                }
              else if ( e.getSource() == mainmenu )
                {
                   Login gs = new Login ();
                }
             }

        }
       /*public void setFrame(JFrame j)
        {
          fr=j;
        }*/
  }

