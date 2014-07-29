package org.ulibsoft.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.print.*;
import java.text.MessageFormat;

public class PrintTable extends JFrame
  {
     private JTable table;
     private JButton but;
     private Vector rows,cols,col_names;
     private Container c;
     private JScrollPane sp;
     private Date date;

     public PrintTable()
       {
         super("printing table");
         rows = new Vector();
         cols = new Vector();
         col_names = new Vector();
         date=new Date();

         c=getContentPane();
         c.setLayout(new BorderLayout());

         col_names.addElement("One");
         col_names.addElement("two");
         col_names.addElement("Three");
         col_names.addElement("four");
         col_names.addElement("five");
         col_names.addElement("six");
         col_names.addElement("seven");
         col_names.addElement("eight");
         col_names.addElement("nine");
         col_names.addElement("ten");


         for(int j=0;j<10;j++)
         cols.addElement(j);

         for(int i=0;i<100;++i)
         rows.addElement(cols);

         table = new JTable(rows,col_names);
         sp = new JScrollPane(table);
         c.add(sp,BorderLayout.CENTER);

         but = new JButton("print button");
         c.add(but,BorderLayout.SOUTH);
         but.addActionListener(new ActionListener()
           {
              public void actionPerformed(ActionEvent e)
                {
                  try
                  {
                     boolean b= table.print( JTable.PrintMode.NORMAL,
                                new MessageFormat("PRINTING TABLE STARTED......"+date.getDate()+"/"+date.getMonth()+"/"+(1900+date.getYear())),
                                new MessageFormat("PRINTING TABLE COMPLETED...."+"/"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()),
                                true,
                                null,
                                true
                                );
                     //boolean b=table.print();
                  }
                  catch(Exception e2)
                   {
                     System.out.println(e2.getMessage());
                   }
                  /*table.print( NORMAL,
                               new MessageFormat("PRINTING TABLE STARTED.........."),
                               new MessageFormat("PRINTING TABLE COMPLETED........"),
                               true,
                               null,//new PrintRequestAttributeSet(),
                               true
                             );
                  boolean b =table.print( NORMAL,                        23
                               new MessageFormat("PRINTING TABLE STARTED.........."),
                               new MessageFormat("PRINTING TABLE COMPLETED........")
                              ); */
                }
           }
         );

         setVisible(true);
         JOptionPane.showMessageDialog(null,"Date:"+date);
         setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
       }

     public static void main( String a[] )
       {
          new PrintTable();
       }
  }