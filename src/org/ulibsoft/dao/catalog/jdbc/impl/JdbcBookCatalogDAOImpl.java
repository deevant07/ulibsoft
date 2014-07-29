/**
 * 
 */
package org.ulibsoft.dao.catalog.jdbc.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.catalog.BookCatalogDAO;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcBookCatalogDAOImpl implements BookCatalogDAO {

	private static final Logger log = Logger.getLogger(JdbcBookCatalogDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public JdbcBookCatalogDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
    
	/* (non-Javadoc)
	 * @see org.ulibsoft.services.catalog.BookCatalogService#create(org.ulibsoft.model.BookModel)
	 */
	@Override
	public int create(BookModel bkModel) {
		
		int ret = -1;
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append(" INSERT INTO LIBRARY ( ACESSNO, BOOKNAME, AUTHOR1S,AUTHOR1F,AUTHOR2S,AUTHOR2F,AUTHOR3S, ");
		sqlInsert.append(" AUTHOR3F,AUTHOR4S,AUTHOR4F,AUTHOR5S,AUTHOR5F,PUBLISHER,EDITION, ");
		sqlInsert.append(" ISBN,NOTE,DESCPRTN,PHY_MEDM,PLACE,YEAR,SUBJECT1,SUBJECT2,SUBJECT3,ISSUED,ACESSDATE, ");
		sqlInsert.append(" VOLUME,SERIES,SUPPLIER,PRICE,BILLNO,CALLNO,BILLDATE,WITHDRAW_DATE )");
		sqlInsert.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		
		
		String SQL_INSERT = sqlInsert.toString();
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, bkModel.getAccessNo());
			pstmt.setString(2, bkModel.getBookName());
			pstmt.setString(3, bkModel.getAuthor1Surname());
			pstmt.setString(4, bkModel.getAuthor1Name());
			pstmt.setString(5, bkModel.getAuthor2Surname());
			pstmt.setString(6, bkModel.getAuthor2Name());
			pstmt.setString(7, bkModel.getAuthor3Surname());
			pstmt.setString(8, bkModel.getAuthor3Name());
			pstmt.setString(9, bkModel.getAuthor4Surname());
			pstmt.setString(10, bkModel.getAuthor4Name());
			pstmt.setString(11, bkModel.getAuthor5Surname());
			pstmt.setString(12, bkModel.getAuthor5Name());
			pstmt.setString(13, bkModel.getPublisher());
			pstmt.setString(14, bkModel.getEdition());
			pstmt.setString(15, bkModel.getIsbn());
			pstmt.setString(16, bkModel.getNote());
			pstmt.setString(17, bkModel.getDescription());
			pstmt.setString(18, bkModel.getPhyMedium());
			pstmt.setString(19, bkModel.getPlace());
			pstmt.setString(20, bkModel.getYear());
			pstmt.setString(21, bkModel.getBookDept());
			pstmt.setString(22, bkModel.getBookStream());
			pstmt.setString(23, bkModel.getBookSpec());
			pstmt.setString(24, bkModel.getIssued());			
			pstmt.setDate(25, new Date(bkModel.getAccessDate().getTime()));
			pstmt.setString(26, bkModel.getVolume());
			pstmt.setString(27, bkModel.getSeries());
			pstmt.setString(28, bkModel.getSupplier());
			pstmt.setString(29, bkModel.getPrice());
			pstmt.setString(30, bkModel.getBillNo());
			pstmt.setString(31, bkModel.getCallNo());
			if ( bkModel.getAccessDate() != null )
				pstmt.setDate(32, new Date(bkModel.getAccessDate().getTime()));
			else
				pstmt.setDate(32,null);
			if ( bkModel.getWithdrawDate() != null )
				pstmt.setDate(33, new Date(bkModel.getWithdrawDate().getTime()));
			else
				pstmt.setDate(33,null);			
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting book record",e);
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
	public int update(BookModel bkModel) {
		
		int ret = -1;
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append(" UPDATE LIBRARY SET  BOOKNAME = ? , AUTHOR1S = ? , AUTHOR1F = ? , AUTHOR2S = ? , AUTHOR2F = ? , AUTHOR3S = ?, ");
		sqlInsert.append(" AUTHOR3F = ? ,AUTHOR4S = ? ,AUTHOR4F = ? ,AUTHOR5S = ? ,AUTHOR5F = ? ,PUBLISHER = ? ,EDITION = ?, ");
		sqlInsert.append(" ISBN = ? ,NOTE = ? ,DESCPRTN = ? ,PHY_MEDM = ? ,PLACE = ? ,YEAR = ? ,SUBJECT1 = ?, SUBJECT2 = ? ,SUBJECT3 = ?, ");
		sqlInsert.append(" ISSUED = ? ,ACESSDATE = ? ,VOLUME = ?, SERIES = ?,SUPPLIER = ? ,PRICE = ?,BILLNO = ?,CALLNO = ? ,");
		sqlInsert.append(" BILLDATE = ? ,WITHDRAW_DATE = ? WHERE ACESSNO = ? ");
		
		
		String SQL_INSERT = sqlInsert.toString();
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
						
			pstmt.setString(1, bkModel.getBookName());
			pstmt.setString(2, bkModel.getAuthor1Surname());
			pstmt.setString(3, bkModel.getAuthor1Name());
			pstmt.setString(4, bkModel.getAuthor2Surname());
			pstmt.setString(5, bkModel.getAuthor2Name());
			pstmt.setString(6, bkModel.getAuthor3Surname());
			pstmt.setString(7, bkModel.getAuthor3Name());
			pstmt.setString(8, bkModel.getAuthor4Surname());
			pstmt.setString(9, bkModel.getAuthor4Name());
			pstmt.setString(10, bkModel.getAuthor5Surname());
			pstmt.setString(11, bkModel.getAuthor5Name());
			pstmt.setString(12, bkModel.getPublisher());
			pstmt.setString(13, bkModel.getEdition());
			pstmt.setString(14, bkModel.getIsbn());
			pstmt.setString(15, bkModel.getNote());
			pstmt.setString(16, bkModel.getDescription());
			pstmt.setString(17, bkModel.getPhyMedium());
			pstmt.setString(18, bkModel.getPlace());
			pstmt.setString(19, bkModel.getYear());
			pstmt.setString(20, bkModel.getBookDept());
			pstmt.setString(21, bkModel.getBookStream());
			pstmt.setString(22, bkModel.getBookSpec());
			pstmt.setString(23, bkModel.getIssued());			
			pstmt.setDate(24, new Date(bkModel.getAccessDate().getTime()));
			pstmt.setString(25, bkModel.getVolume());
			pstmt.setString(26, bkModel.getSeries());
			pstmt.setString(27, bkModel.getSupplier());
			pstmt.setString(28, bkModel.getPrice());
			pstmt.setString(29, bkModel.getBillNo());
			pstmt.setString(30, bkModel.getCallNo());
			if ( bkModel.getAccessDate() != null )
				pstmt.setDate(31, new Date(bkModel.getAccessDate().getTime()));
			else
				pstmt.setDate(31,null);
			if ( bkModel.getWithdrawDate() != null )
				pstmt.setDate(32, new Date(bkModel.getWithdrawDate().getTime()));
			else
				pstmt.setDate(32,null);			
			
			pstmt.setString(33, bkModel.getAccessNo());
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while updating book record",e);
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
	public List<BookModel> findByCurrentDay() {
		String FETCH_ALL = "SELECT ACESSNO, BOOKNAME, AUTHOR1S, AUTHOR1F, PUBLISHER, EDITION, PLACE, YEAR  FROM LIBRARY WHERE ACESSDATE = ? ";
		List<BookModel> rows = new ArrayList<BookModel>();
		try {
		
			pstmt = con.prepareStatement(FETCH_ALL);
			pstmt.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BookModel bkRcrd = new BookModel();
				bkRcrd.setAccessNo(rs.getString(1));
				bkRcrd.setBookName(rs.getString(2));
				bkRcrd.setAuthor1Surname(rs.getString(3));
				bkRcrd.setAuthor1Name(rs.getString(4));
				bkRcrd.setPublisher(rs.getString(5));
				bkRcrd.setEdition(rs.getString(6));
				bkRcrd.setPlace(rs.getString(7));
				bkRcrd.setYear(rs.getString(8));
				rows.add(bkRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching book records", sqlex);
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
	@Override
	public BookModel findByAccessNo(String accessNo) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT ACESSNO,BOOKNAME,AUTHOR1S,AUTHOR1F,AUTHOR2S,AUTHOR2F,AUTHOR3S,AUTHOR3F,AUTHOR4S,AUTHOR4F,AUTHOR5S,AUTHOR5F, ");
		query.append("PUBLISHER,EDITION,ISBN,NOTE,DESCPRTN,PHY_MEDM,PLACE,YEAR,SUBJECT1,SUBJECT2,SUBJECT3,ACESSDATE,ISSUED,");
		query.append("STATUS,VOLUME,SERIES,SUPPLIER,PRICE,BILLNO,CALLNO,BILLDATE,WITHDRAW_DATE FROM LIBRARY WHERE ACESSNO = ?");
		String FETCH_ID = query.toString();
		BookModel bkRcrd = null;
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1,accessNo);
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				bkRcrd = new BookModel();
				bkRcrd.setAccessNo(rs.getString(1));
				bkRcrd.setBookName(rs.getString(2));
				bkRcrd.setAuthor1Surname(rs.getString(3));
				bkRcrd.setAuthor1Name(rs.getString(4));
				bkRcrd.setAuthor2Surname(rs.getString(5));
				bkRcrd.setAuthor2Name(rs.getString(6));
				bkRcrd.setAuthor3Surname(rs.getString(7));
				bkRcrd.setAuthor3Name(rs.getString(8));
				bkRcrd.setAuthor4Surname(rs.getString(9));
				bkRcrd.setAuthor4Name(rs.getString(10));
				bkRcrd.setAuthor5Surname(rs.getString(11));
				bkRcrd.setAuthor5Name(rs.getString(12));
				bkRcrd.setPublisher(rs.getString(13));
				bkRcrd.setEdition(rs.getString(14));
				bkRcrd.setIsbn(rs.getString(15));
				bkRcrd.setNote(rs.getString(16));
				bkRcrd.setDescription(rs.getString(17));
				bkRcrd.setPhyMedium(rs.getString(18));				
				bkRcrd.setPlace(rs.getString(19));
				bkRcrd.setYear(rs.getString(20));
				bkRcrd.setBookDept(rs.getString(21));
				bkRcrd.setBookStream(rs.getString(22));
				bkRcrd.setBookSpec(rs.getString(23));
				bkRcrd.setAccessDate(rs.getDate(24));
				bkRcrd.setIssued(rs.getString(25));
				bkRcrd.setStatus(rs.getString(26));
				bkRcrd.setVolume(rs.getString(27));
				bkRcrd.setSeries(rs.getString(28));
				bkRcrd.setSupplier(rs.getString(29));
				bkRcrd.setPrice(rs.getString(30));
				bkRcrd.setBillNo(rs.getString(31));
				bkRcrd.setCallNo(rs.getString(32));
				bkRcrd.setBillDate(rs.getDate(33));
				bkRcrd.setWithdrawDate(rs.getDate(34));
				
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching book record by id", sqlex);
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
		return bkRcrd;	
	}	
	@Override
	public int getNextSequenceNo() {
		String FETCH_SEQUENCE = "SELECT COUNT(ACESSNO) FROM LIBRARY ";
		int sequence=-1;
		try {		
			pstmt = con.prepareStatement(FETCH_SEQUENCE);			
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				sequence = rs.getInt(1) + 1;
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching count book records", sqlex);
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
		return sequence;
	
	}
}
