/**
 * 
 */
package org.ulibsoft.dao.transaction.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.transaction.BookTransactionDAO;
import org.ulibsoft.model.BKTransactionModel;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcBookTransactionDAOImpl implements BookTransactionDAO {

	private static final Logger log = Logger.getLogger(JdbcBookTransactionDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    
    
    public JdbcBookTransactionDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	/* (non-Javadoc)
	 * @see org.ulibsoft.services.catalog.BookTransactionService#createStudent(org.ulibsoft.model.BKTransactionModel)
	 */
	@Override
	public int createStudent(BKTransactionModel bkTransMdl) {
		
		int ret = -1;
		String SQL_INSERT = " INSERT INTO BKTRANSACTION (CODE, ADNO , IDATE, RDATE, NO_OF_REN) VALUES (?, ?, ?, ?, ?) ";
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, bkTransMdl.getCode());
			pstmt.setString(2, bkTransMdl.getId());
			pstmt.setDate(3, new java.sql.Date(bkTransMdl.getIssuedDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(bkTransMdl.getReturnDate().getTime()));			
			pstmt.setInt(5, bkTransMdl.getReneivalCount());
			
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting bktransaction record",e);
			ret = -1;
		}finally{   		 
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}
		
       return ret;
	
	}

	/* (non-Javadoc)
	 * @see org.ulibsoft.services.catalog.BookTransactionService#update(org.ulibsoft.model.BKTransactionModel)
	 */
	@Override
	public int updateStudent(BKTransactionModel bkTransMdl) {
		
		int ret = -1;
		String SQL_UPDATE = " UPDATE BKTRANSACTION  SET ADNO = ? , IDATE = ?, RDATE = ?, NO_OF_REN = ? WHERE CODE = ? ";
		try {
			pstmt = con.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, bkTransMdl.getId());
			pstmt.setDate(2, new java.sql.Date(bkTransMdl.getIssuedDate().getTime()));
			pstmt.setDate(3, new java.sql.Date(bkTransMdl.getReturnDate().getTime()));			
			pstmt.setInt(4, bkTransMdl.getReneivalCount());
			pstmt.setString(5, bkTransMdl.getCode());
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while UPDATING bktransaction record",e);
			ret = -1;
		}finally{   		 
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}
		
       return ret;
	
	}

	/* (non-Javadoc)
	 * @see org.ulibsoft.services.catalog.BookTransactionService#delete(org.ulibsoft.model.BKTransactionModel)
	 */
	@Override
	public int deleteStudent(BKTransactionModel bkTransMdl) {

		int ret = -1;
		String SQL_DELETE = "DELETE FROM BKTRANSACTION WHERE CODE = ?";
		try {
			pstmt = con.prepareStatement(SQL_DELETE);
			
			pstmt.setString(1, bkTransMdl.getCode());
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while deleting bktransaction record",e);
			ret = -1;
		}finally{   		 
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}
		return ret;
	
	}
	@Override
	public int createStaff(BKTransactionModel bkTransMdl) {
		
		int ret = -1;
		String SQL_INSERT = " INSERT INTO BKTRANSACTION1 (CODE, LID , IDATE, RDATE) VALUES (?, ?, ?, ?) ";
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, bkTransMdl.getCode());
			pstmt.setString(2, bkTransMdl.getId());
			pstmt.setDate(3, new java.sql.Date(bkTransMdl.getIssuedDate().getTime()));
			pstmt.setDate(4, new java.sql.Date(bkTransMdl.getReturnDate().getTime()));			
			
			
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting bktransaction1 record",e);
			ret = -1;
		}finally{   		 
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}
		
       return ret;
	}
	@Override
	public int updateStaff(BKTransactionModel bkTransMdl) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteStaff(BKTransactionModel bkTransMdl) {

		int ret = -1;
		String SQL_DELETE = "DELETE FROM BKTRANSACTION1 WHERE CODE = ?";
		try {
			pstmt = con.prepareStatement(SQL_DELETE);
			
			pstmt.setString(1, bkTransMdl.getCode());
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while deleting bktransaction1 record",e);
			ret = -1;
		}finally{   		 
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}
		return ret;
	
	}
	
	

}
