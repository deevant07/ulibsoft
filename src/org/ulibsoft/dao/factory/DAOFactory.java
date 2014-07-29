/**
 * 
 */
package org.ulibsoft.dao.factory;

import org.ulibsoft.dao.catalog.BookCatalogDAO;
import org.ulibsoft.dao.catalog.StaffDAO;
import org.ulibsoft.dao.catalog.StudentDAO;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.dao.search.BookCatalogSearchDAO;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.search.transaction.TransactionHistorySearchDAO;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.dao.transaction.TransactionHistoryDAO;

/**
 * @author deevan
 *
 */
public abstract class DAOFactory {

	public static final int JDBC = 1;
	static DAOFactory instance;
	public static DAOFactory getDAOFactory()
	{
		if ( instance == null )
				instance = new JdbcDAOFactory();
		
		return instance;
	}
	public abstract BookCatalogDAO getBookCatalogDAO();
	public abstract BookTransactionDAO getBKTransDAO();
	public abstract StaffDAO getStaffDAO();
	public abstract StudentDAO getStudentDAO();
	public abstract AuthDAO getAuthDAO();
	public abstract KeyConstraintDAO getKeyCnstrntDAO();
	public abstract BookCatalogSearchDAO getBkCatlogSearchDAO();
	public abstract BookTransactionSearchDAO getBkTranSrchDAO();
	public abstract TransactionHistoryDAO getTransHistoryDAO();
	public abstract TransactionHistorySearchDAO getTranHistorySrchDAO();
}
