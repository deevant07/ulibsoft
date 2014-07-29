/**
 * 
 */
package org.ulibsoft.dao.search.transaction.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.search.transaction.TransactionHistorySearchDAO;
import org.ulibsoft.model.TransMemberModel;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcTransactionHistorySearchDAOImpl implements
		TransactionHistorySearchDAO {

	private static final Logger log = Logger.getLogger(JdbcTransactionHistorySearchDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    private static final String WILDCARD_SEARCH_ALL = "%";
    
    public JdbcTransactionHistorySearchDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	public List<TransMemberModel> listPerStudent(String adno)
	{

		List<TransMemberModel> rows = new ArrayList<TransMemberModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT TH.CODE,LIB.BOOKNAME, LIB.AUTHOR1S, LIB.AUTHOR1F, TH.IDATE, TH.RDATE, 'BOOK' ");
		query.append(" FROM TRANSACTION_HISTORY TH, LIBRARY LIB WHERE TH.CODE = LIB.ACESSNO AND  TH.ADNO = ? ");
		query.append("AND TH.VALUE = ? ");
		query.append(" UNION ");
		query.append("SELECT TH.CDCODE,CD.CDNAME, CD.CDVERSION, null , TH.IDATE, TH.RDATE, 'CD' ");
		query.append(" FROM TRANSACTION_HISTORY TH, CDDETAILS CD WHERE TH.CDCODE = CD.CDCODE AND  TH.ADNO = ? ");
		query.append("AND TH.VALUE = ? ");
		query.append(" UNION ");
		query.append("SELECT TH.MZCODE,MZ.MZNAME, MZ.VOLUME, null , TH.IDATE, TH.RDATE, 'MZ' ");
		query.append(" FROM TRANSACTION_HISTORY TH, MZDETAILS MZ WHERE TH.MZCODE = MZ.ACCESSNO AND  TH.ADNO = ? ");
		query.append("AND TH.VALUE = ? ");
				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, adno);
			pstmt.setString(2, "1");
			pstmt.setString(3, adno);
			pstmt.setString(4, "1");
			pstmt.setString(5, adno);
			pstmt.setString(6, "1");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				TransMemberModel tranRcrd = new TransMemberModel();
				tranRcrd.setCode(rs.getString(1));
				tranRcrd.setItemName(rs.getString(2));
				tranRcrd.setType(rs.getString(7));
				if ( "BOOK".equals(tranRcrd.getType()) )
					tranRcrd.setSpec(rs.getString(3) + rs.getString(4));
				else
					tranRcrd.setSpec(rs.getString(3));
				tranRcrd.setIssuedDate(rs.getDate(5));
				tranRcrd.setReturnDate(rs.getDate(6));						
				rows.add(tranRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted records per student id: "+adno, sqlex);
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
		return rows;	
	
	}
	public List<TransMemberModel> listPerStaff(String lid)
	{

		List<TransMemberModel> rows = new ArrayList<TransMemberModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT TH.CODE,LIB.BOOKNAME, LIB.AUTHOR1S, LIB.AUTHOR1F, TH.IDATE, TH.RDATE, 'BOOK' ");
		query.append(" FROM TRANSACTION_HISTORY TH, LIBRARY LIB WHERE TH.CODE = LIB.ACESSNO AND  TH.LID = ? ");
		query.append("AND TH.VALUE = ? ");
		query.append(" UNION ");
		query.append("SELECT TH.CDCODE,CD.CDNAME, CD.CDVERSION, null , TH.IDATE, TH.RDATE, 'CD' ");
		query.append(" FROM TRANSACTION_HISTORY TH, CDDETAILS CD WHERE TH.CDCODE = CD.CDCODE AND  TH.LID = ? ");
		query.append("AND TH.VALUE = ? ");
		query.append(" UNION ");
		query.append("SELECT TH.MZCODE,MZ.MZNAME, MZ.VOLUME, null , TH.IDATE, TH.RDATE, 'MZ' ");
		query.append(" FROM TRANSACTION_HISTORY TH, MZDETAILS MZ WHERE TH.MZCODE = MZ.ACCESSNO AND  TH.LID = ? ");
		query.append("AND TH.VALUE = ? ");
				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, lid);
			pstmt.setString(2, "1");
			pstmt.setString(3, lid);
			pstmt.setString(4, "1");
			pstmt.setString(5, lid);
			pstmt.setString(6, "1");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				TransMemberModel tranRcrd = new TransMemberModel();
				tranRcrd.setCode(rs.getString(1));
				tranRcrd.setItemName(rs.getString(2));
				tranRcrd.setType(rs.getString(7));
				if ( "BOOK".equals(tranRcrd.getType()) )
					tranRcrd.setSpec(rs.getString(3) + rs.getString(4));
				else
					tranRcrd.setSpec(rs.getString(3));
				tranRcrd.setIssuedDate(rs.getDate(5));
				tranRcrd.setReturnDate(rs.getDate(6));						
				rows.add(tranRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted records per student id: "+lid, sqlex);
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
		return rows;	
	
	}
}
