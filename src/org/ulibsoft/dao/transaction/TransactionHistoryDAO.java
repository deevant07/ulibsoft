/**
 * 
 */
package org.ulibsoft.dao.transaction;

import org.ulibsoft.model.TransactionHistoryModel;

/**
 * @author deevan
 *
 */
public interface TransactionHistoryDAO {

	/**
	 * Create a record in transaction history which maintains every list of transaction
	 * @param transHstry
	 * @return
	 */
	public int create(TransactionHistoryModel transHstry);
	/**
	 * Updates a record in transaction history
	 * @param transHstry
	 * @return
	 */
	public int updateBkHistory(TransactionHistoryModel transHstry);
	/**
	 * Fetches the transaction history for a book based on the search criteria
	 * @param srchCrt
	 * @return
	 */
	public TransactionHistoryModel findBkRecord(TransactionHistoryModel srchCrt);
	
}
