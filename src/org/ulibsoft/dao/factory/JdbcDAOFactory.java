package org.ulibsoft.dao.factory;

import org.ulibsoft.dao.catalog.BookCatalogDAO;
import org.ulibsoft.dao.catalog.StaffDAO;
import org.ulibsoft.dao.catalog.StudentDAO;
import org.ulibsoft.dao.catalog.jdbc.impl.JdbcBookCatalogDAOImpl;
import org.ulibsoft.dao.catalog.jdbc.impl.JdbcStaffDAOImpl;
import org.ulibsoft.dao.catalog.jdbc.impl.JdbcStudentDAOImpl;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.dao.core.jdbc.impl.JdbcAuthDAOImpl;
import org.ulibsoft.dao.core.jdbc.impl.JdbcKeyConstraintDAOImpl;
import org.ulibsoft.dao.search.BookCatalogSearchDAO;
import org.ulibsoft.dao.search.jdbc.impl.JdbcBookCatalogSearchDAOImpl;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.dao.search.transaction.TransactionHistorySearchDAO;
import org.ulibsoft.dao.search.transaction.jdbc.impl.JdbcBookTransactionSearchDAOImpl;
import org.ulibsoft.dao.search.transaction.jdbc.impl.JdbcTransactionHistorySearchDAOImpl;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.dao.transaction.TransactionHistoryDAO;
import org.ulibsoft.dao.transaction.jdbc.impl.JdbcBookTransactionDAOImpl;
import org.ulibsoft.dao.transaction.jdbc.impl.JdbcTransactionHistoryDAOImpl;

public class JdbcDAOFactory extends DAOFactory {

	private BookCatalogDAO bkCatDao;
	private BookTransactionDAO bkTranDao;
	private StaffDAO staffDao;
	private StudentDAO stdDao;
	private AuthDAO authDao;
	private KeyConstraintDAO keyCnstrntDao;
	private BookCatalogSearchDAO bkCatlogSrchDao;
	private BookTransactionSearchDAO bkTranSrchDao;
	private TransactionHistoryDAO transHistoryDao;
	private TransactionHistorySearchDAO transHistorySrchDao;
	
	@Override
	public BookCatalogDAO getBookCatalogDAO() {
		if ( bkCatDao == null )
			bkCatDao = new JdbcBookCatalogDAOImpl();
		return bkCatDao;
	}

	@Override
	public BookTransactionDAO getBKTransDAO() {
		if ( bkTranDao == null )
			bkTranDao = new JdbcBookTransactionDAOImpl();
		
		return bkTranDao;
	}

	@Override
	public StaffDAO getStaffDAO() {
		if ( staffDao == null )
			staffDao = new JdbcStaffDAOImpl();
		return staffDao;
	}

	@Override
	public StudentDAO getStudentDAO() {
		if ( stdDao == null )
			stdDao = new JdbcStudentDAOImpl();
		return stdDao;
	}

	@Override
	public AuthDAO getAuthDAO() {
		if ( authDao == null )
			authDao = new JdbcAuthDAOImpl();
		return authDao;
	}

	@Override
	public KeyConstraintDAO getKeyCnstrntDAO() {
		if ( keyCnstrntDao == null )
			keyCnstrntDao = new JdbcKeyConstraintDAOImpl();
		return keyCnstrntDao;
	}

	@Override
	public BookCatalogSearchDAO getBkCatlogSearchDAO() {
		if ( bkCatlogSrchDao == null )
			bkCatlogSrchDao = new JdbcBookCatalogSearchDAOImpl();
		return bkCatlogSrchDao;
	}

	@Override
	public BookTransactionSearchDAO getBkTranSrchDAO() {
		if ( bkTranSrchDao == null )
			bkTranSrchDao = new JdbcBookTransactionSearchDAOImpl();
		return bkTranSrchDao;
	}
	@Override
	public TransactionHistoryDAO getTransHistoryDAO() {
		if ( transHistoryDao == null )
			transHistoryDao = new JdbcTransactionHistoryDAOImpl();
		return transHistoryDao;
	}
	@Override
	public TransactionHistorySearchDAO getTranHistorySrchDAO() {
		if ( transHistorySrchDao == null )
			transHistorySrchDao = new JdbcTransactionHistorySearchDAOImpl();
		return transHistorySrchDao;
	}
}
