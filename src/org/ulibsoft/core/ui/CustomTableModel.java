/**
 * 
 */
package org.ulibsoft.core.ui;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * @author Administrator
 *
 */
public class CustomTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6485144396067141977L;
	
	public boolean isCellEditable(int row, int column) 
	{
		return false;
	}
	public CustomTableModel(Vector<Vector<Object>> rows,Vector<String> cols) {
		super(rows,cols);
	}
}
