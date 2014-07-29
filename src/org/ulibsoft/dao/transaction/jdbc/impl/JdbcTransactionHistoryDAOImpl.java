/**
 * 
 */
package org.ulibsoft.dao.transaction.jdbc.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.transaction.TransactionHistoryDAO;
import org.ulibsoft.model.BKStudentModel;
import org.ulibsoft.model.TransactionHistoryModel;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcTransactionHistoryDAOImpl implements TransactionHistoryDAO {
	
	
	private static final Logger log = Logger.getLogger(JdbcTransactionHistoryDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public JdbcTransactionHistoryDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}

	@Override
	public int create(TransactionHistoryModel transHstry) {
		
		int ret = -1;
		String SQL_INSERT = " INSERT INTO TRANSACTION_HISTORY (CODE, CDCODE, MZCODE, ADNO ,STATUS, CDATE, LID, VALUE, IDATE, RDATE, FINEAMOUNT,FINE_PAID, FDATE, REASON) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?) ";
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, transHstry.getCode());
			pstmt.setString(2, transHstry.getCdCode());
			pstmt.setString(3, transHstry.getMzCode());
			pstmt.setString(4, transHstry.getSid());
			pstmt.setString(5, transHstry.getStatus());
			if ( transHstry.getcDate() != null )
				pstmt.setDate(6, new java.sql.Date(transHstry.getcDate().getTime()));
			else
				pstmt.setNull(6, Types.DATE);
			
			pstmt.setString(7, transHstry.getLid());
			pstmt.setString(8, transHstry.getValue());
			if ( transHstry.getIssuedDate() != null )
				pstmt.setDate(9, new java.sql.Date(transHstry.getIssuedDate().getTime()));
			else
				pstmt.setNull(9, Types.DATE);
			if ( transHstry.getReturnDate() != null )
				pstmt.setDate(10, new java.sql.Date(transHstry.getReturnDate().getTime()));
			else
				pstmt.setNull(10, Types.DATE);
			
			pstmt.setFloat(11, transHstry.getFineAmount());
			pstmt.setFloat(12, transHstry.getFinePaid());
			if ( transHstry.getFineDate() != null )
				pstmt.setDate(13, new java.sql.Date(transHstry.getFineDate().getTime()));
			else
				pstmt.setNull(13, Types.DATE);
			
			pstmt.setString(14, transHstry.getReason());
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting transaction history record",e);
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
	public int updateBkHistory(TransactionHistoryModel transHstry) {

		
		int ret = -1;
		StringBuilder updateQry = new StringBuilder();
		updateQry.append(" UPDATE TRANSACTION_HISTORY SET STATUS = ?, CDATE = ?, VALUE = ?, RDATE = ?, FINEAMOUNT = ?, ");
		updateQry.append(" FINE_PAID = ?, FDATE = ?, REASON = ? WHERE CODE = ? AND IDATE = ?");
		String SQL_UPDATE = updateQry.toString();
		try {
			
			pstmt = con.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, transHstry.getStatus());
			if ( transHstry.getcDate() != null  )
				pstmt.setDate(2, new Date(transHstry.getcDate().getTime()));
			else
				pstmt.setNull(2, Types.DATE);
							
			pstmt.setString(3, transHstry.getValue());
			if ( transHstry.getReturnDate() != null )
				pstmt.setDate(4, new java.sql.Date(transHstry.getReturnDate().getTime()));
			else
				pstmt.setNull(4, Types.DATE);
			
			pstmt.setFloat(5, transHstry.getFineAmount());
			pstmt.setFloat(6, transHstry.getFinePaid());
			
			if ( transHstry.getFineDate() != null )
				pstmt.setDate(7, new java.sql.Date(transHstry.getFineDate().getTime()));
			else
				pstmt.setNull(7, Types.DATE);
			
			pstmt.setString(8, transHstry.getReason());
			
			pstmt.setString(9, transHstry.getCode());
			
			if ( transHstry.getIssuedDate() != null )
				pstmt.setDate(10, new java.sql.Date(transHstry.getIssuedDate().getTime()));
			else
				pstmt.setNull(10, Types.DATE);
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while updating transaction history record",e);
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
	public TransactionHistoryModel findBkRecord(TransactionHistoryModel srchCrt) {


		StringBuilder query = new StringBuilder();
		query.append("SELECT CODE, ADNO ,STATUS, CDATE, VALUE, IDATE, RDATE, FINEAMOUNT,FINE_PAID, FDATE, REASON ");
		query.append(" FROM TRANSACTION_HISTORY WHERE CODE = ? AND IDATE = ?");
		String FETCH_ID = query.toString();
		TransactionHistoryModel transHstry = null;
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1,srchCrt.getCode());
			pstmt.setDate(2, new Date(srchCrt.getIssuedDate().getTime()));
			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				transHstry = new TransactionHistoryModel();
				transHstry.setCode(rs.getString(1));
				transHstry.setSid(rs.getString(2));				
				transHstry.setStatus(rs.getString(3));
				transHstry.setcDate(rs.getDate(4));
				transHstry.setValue(rs.getString(5));			
				transHstry.setIssuedDate(rs.getDate(6));
				transHstry.setReturnDate(rs.getDate(7));
				transHstry.setFineAmount(rs.getFloat(8));
				transHstry.setFinePaid(rs.getFloat(9));
				transHstry.setFineDate(rs.getDate(10));
				transHstry.setReason(rs.getString(11));
				
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transaction history for book id and issuedate", sqlex);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error(
							"SQLException while closing the Statement "
									+ e.getMessage(), e);
				}
			}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(
							"SQLException while closing the ResultSet "
									+ e.getMessage(), e);
				}
		}
		return transHstry;	
	}

}
