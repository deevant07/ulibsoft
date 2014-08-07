/**
 * 
 */
package org.ulibsoft.core.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.ulibsoft.util.datatype.DateHelper;

/**
 * @author Administrator
 *
 */
public class CustomTable {

	
	private CustomTableModel tm;
	private Vector<String> dispCols;
	private JTable table;
	private JPanel panel;
	private String printHeader;
	
	public CustomTable(Vector<String> cols,String printHeader) {
		super();
		this.dispCols=cols;		
		table = new JTable();
		table.setEnabled (true);
		table.setBackground(Color.cyan);   
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setDragEnabled(false);
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		this.printHeader = printHeader;
		
        
		panel = new JPanel(new BorderLayout());
		JButton print = new JButton("Print");
		panel.add(print, BorderLayout.SOUTH);
       // pnl.setBackground(c.getBackground ());
       // panel.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
        //                                    "PENDING-LIST", 1, 2, c.getFont(), Color.magenta));
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				print();
				
			}
		});
		JScrollPane spane =new JScrollPane(table);
		panel.add(spane,BorderLayout.CENTER);     
        
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public void populateData(Vector<Vector<Object>> rows)
	{
		if ( rows == null)
			return;
		
		/*Vector<Vector<Object>> rows=new Vector<Vector<Object>>();
		for ( Object rec: records)
		{
			if ( rec instanceof GenericBean )
			{
				GenericBean bean=(GenericBean)rec;
				Vector<Object> rowData=new Vector<Object>();
				for ( int i=0;i<colHeaders.length;i++)
				{
					if ( colTypes.get(colHeaders[i]) instanceof CustomComboBox )
					{
						Object val=((CustomComboBox)colTypes.get(colHeaders[i])).getItemAt((Integer)bean.get(colHeaders[i]));
						rowData.add(val);
					}						
					else
						rowData.add(bean.get(colHeaders[i]));
				}
				rows.add(rowData);
			}		
		}*/
		tm=new CustomTableModel(rows, dispCols);
		table.setModel(tm);
		table.validate();
		   
	}
	public void print()
	{
		
		try
        {
          if(table!=null) {
        	  if ( printHeader == null ) printHeader = "";
          table.print( JTable.PrintMode.NORMAL,
                                          new MessageFormat(printHeader+" "+DateHelper.getCurrentDateString()),
                                          new MessageFormat(printHeader+" "+DateHelper.getCurrentTimeString()),
                                          true,
                                          null,
                                          true
                                         );
          }
          else
            {
              JOptionPane.showMessageDialog(null,"Empty Table");
            }
        }
      catch(PrinterException pex)
        {
           JOptionPane.showMessageDialog(null,""+pex.getMessage());
        }
	}
}
