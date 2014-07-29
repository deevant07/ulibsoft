/**
 * 
 */
package org.ulibsoft.dao.transaction;

import org.ulibsoft.model.BKTransactionModel;

/**
 * @author deevan
 *
 */
public interface BookTransactionDAO {

	/**
	 * Creates a record in book transaction table for student
	 * @param bkTransMdl
	 * @return
	 */
	public int createStudent(BKTransactionModel bkTransMdl);
	/**
	 * Updates a record in book transaction table for student
	 * @param bkTransMdl
	 * @return
	 */
	public int updateStudent(BKTransactionModel bkTransMdl);
	/**
	 * Deletes a record in book transaction table for student
	 * @param bkTransMdl
	 * @return
	 */
	public int deleteStudent(BKTransactionModel bkTransMdl);
	/**
	 * Creates a record in book transaction table for staff
	 * @param bkTransMdl
	 * @return
	 */
	public int createStaff(BKTransactionModel bkTransMdl);
	/**
	 * Updates a record in book transaction table for staff
	 * @param bkTransMdl
	 * @return
	 */
	public int updateStaff(BKTransactionModel bkTransMdl);
	/**
	 * Deletes a record in book transaction table for staff
	 * @param bkTransMdl
	 * @return
	 */
	public int deleteStaff(BKTransactionModel bkTransMdl);
	
}
