/**
 * 
 */
package org.ulibsoft.dao.search.transaction.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.constants.SearchFilter;
import org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO;
import org.ulibsoft.model.BKStaffModel;
import org.ulibsoft.model.BKStudentModel;
import org.ulibsoft.model.BKTranStaffModel;
import org.ulibsoft.model.BKTranStudModel;
import org.ulibsoft.model.BKTransMemberModel;
import org.ulibsoft.model.BookModel;
import org.ulibsoft.model.TransMemberModel;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.datatype.DateHelper;

/**
 * @author deevan
 *
 */
public class JdbcBookTransactionSearchDAOImpl implements
		BookTransactionSearchDAO {

	private static final Logger log = Logger.getLogger(JdbcBookTransactionSearchDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    private static final String WILDCARD_SEARCH_ALL = "%";
    
    public JdbcBookTransactionSearchDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	/* (non-Javadoc)
	 * @see org.ulibsoft.services.search.transaction.BookTransactionSearchService#findByAccessNoForStudent(java.lang.String)
	 */
	@Override
	public BKStudentModel findByAccessNoForStudent(String accessNo) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT LIB.ACESSNO, STD.ADNO, STD.SNAME, STD.BRANCH, STD.YEAR, STD.IMAGE FROM BKTRANSACTION BK,LIBRARY LIB, ");
		query.append("STUDENTDETAILS STD  WHERE LIB.ACESSNO= ? AND LIB.ACESSNO = BK.CODE AND STD.ADNO = BK.ADNO");
		String FETCH_ID = query.toString();
		BKStudentModel bkStdRcrd = null;
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1,accessNo);
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				bkStdRcrd = new BKStudentModel();
				bkStdRcrd.getBkModel().setAccessNo(rs.getString(1));
				bkStdRcrd.getStdModel().setId(rs.getString(2));				
				bkStdRcrd.getStdModel().setName(rs.getString(3));
				bkStdRcrd.getStdModel().setBranch(rs.getString(4));
				bkStdRcrd.getStdModel().setYear(rs.getString(5));
				bkStdRcrd.getStdModel().setImage(rs.getBytes(6));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching book record for student by id", sqlex);
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
		return bkStdRcrd;
	
	
	}
	/*
	 * (non-Javadoc)
	 * @see org.ulibsoft.dao.search.transaction.BookTransactionSearchDAO#findByAccessNoForStaff(java.lang.String)
	 */
	public BKStaffModel findByAccessNoForStaff(String accessNo) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT LIB.ACESSNO, SF.LID, SF.LNAME, SF.DEPT, SF.JOININGDATE, SF.IMAGE  FROM BKTRANSACTION1 BK1, STAFF SF, LIBRARY LIB ");
		query.append(" WHERE LIB.ACESSNO= ?  AND LIB.ACESSNO=BK1.CODE AND SF.LID=BK1.LID ");
		String FETCH_ID = query.toString();
		BKStaffModel bkStfRcrd = null;
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1,accessNo);
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				bkStfRcrd = new BKStaffModel();
				bkStfRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkStfRcrd.getStaffModel().setId(rs.getString(2));				
				bkStfRcrd.getStaffModel().setName(rs.getString(3));
				bkStfRcrd.getStaffModel().setDept(rs.getString(4));
				bkStfRcrd.getStaffModel().setJoinDate(rs.getDate(5));
				bkStfRcrd.getStaffModel().setImage(rs.getBytes(6));
				
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching book record for staff by id", sqlex);
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
		return bkStfRcrd;	
	}
	public boolean hasStudent(String accessNo)
	{		
		boolean isStudent = false ;
		
		String FETCH_BKTN_CODE = " SELECT CODE FROM BKTRANSACTION WHERE CODE = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN_CODE);
			pstmt.setString(1, accessNo);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				isStudent = true;				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching bktransaction by accessNo :"+accessNo,e);
		}finally{
   		 if ( rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the ResultSet "+e.getMessage(),e);
				}
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}		
       return isStudent;	
	}
	public boolean hasStaff(String accessNo)
	{		
		boolean isStaff = false ;
		
		String FETCH_BKTN1_CODE = " SELECT CODE FROM BKTRANSACTION1 WHERE CODE = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN1_CODE);
			pstmt.setString(1, accessNo);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				isStaff = true;				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching bktransaction1 by accessNo :"+accessNo,e);
		}finally{
   		 if ( rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the ResultSet "+e.getMessage(),e);
				}
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}		
       return isStaff;	
	}
	@Override
	public int getCountBooks(String id) {
		
		int count = -1 ;
		
		String FETCH_BKTN_COUNT = " SELECT COUNT(1) FROM BKTRANSACTION WHERE ADNO = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN_COUNT);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				count = rs.getInt(1);				
			}
		} catch (SQLException e) {
			log.error("SQLException while count bktransaction by id :"+id,e);
		}finally{
   		 if ( rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the ResultSet "+e.getMessage(),e);
				}
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}		
       return count;	
	
	}
	@Override
	public int getCountBooksStaff(String id) {
		
		int count = -1 ;
		
		String FETCH_BKTN_COUNT = " SELECT COUNT(1) FROM BKTRANSACTION1 WHERE LID = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN_COUNT);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				count = rs.getInt(1);				
			}
		} catch (SQLException e) {
			log.error("SQLException while count bktransaction1 by id :"+id,e);
		}finally{
   		 if ( rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the ResultSet "+e.getMessage(),e);
				}
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}		
       return count;	
	
	}
	public List<BookModel> availableBooks()
	{

		String FETCH_AVAILABLE = "SELECT LIB.ACESSNO ACESSNO,LIB.BOOKNAME BOOKNAME,LIB.AUTHOR1S,LIB.AUTHOR1F AUTHOR FROM LIBRARY LIB  " +
				" WHERE LIB.ACESSNO NOT IN(SELECT BK.CODE FROM BKTRANSACTION BK WHERE BK.CODE=LIB.ACESSNO) " +
				" AND LIB.ACESSNO NOT IN(SELECT BK1.CODE FROM BKTRANSACTION1 BK1 WHERE BK1.CODE=LIB.ACESSNO ) AND LIB.STATUS IS NULL ";
		List<BookModel> rows = new ArrayList<BookModel>();
		try {
		
			pstmt = con.prepareStatement(FETCH_AVAILABLE);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BookModel bkRcrd = new BookModel();
				bkRcrd.setAccessNo(rs.getString(1));
				bkRcrd.setBookName(rs.getString(2));
				bkRcrd.setAuthor1Surname(rs.getString(3));
				bkRcrd.setAuthor1Name(rs.getString(4));
				
				rows.add(bkRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching avialble book records", sqlex);
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
	public List<BKTranStudModel> findStudentsTransactedBooks() {

		List<BKTranStudModel> rows = new ArrayList<BKTranStudModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT LIB.ACESSNO , LIB.BOOKNAME ,LIB.AUTHOR1S, LIB.AUTHOR1F ,SD.ADNO ,SD.SNAME , ");
		query.append(" SD.BRANCH ,SD.YEAR ,BK.IDATE, BK.RDATE FROM BKTRANSACTION BK, ");
		query.append("LIBRARY LIB,STUDENTDETAILS SD WHERE SD.ADNO=BK.ADNO AND BK.CODE=LIB.ACESSNO ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTranStudModel bkTranStdRcrd = new BKTranStudModel();
				bkTranStdRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkTranStdRcrd.getBookModel().setBookName(rs.getString(2));
				bkTranStdRcrd.getBookModel().setAuthor1Surname(rs.getString(3));
				bkTranStdRcrd.getBookModel().setAuthor1Name(rs.getString(4));
				bkTranStdRcrd.getStudentModel().setId(rs.getString(5));				
				bkTranStdRcrd.getStudentModel().setName(rs.getString(6));
				bkTranStdRcrd.getStudentModel().setBranch(rs.getString(7));
				bkTranStdRcrd.getStudentModel().setYear(rs.getString(8));
				bkTranStdRcrd.getBkTransModel().setIssuedDate(rs.getDate(9));
				bkTranStdRcrd.getBkTransModel().setReturnDate(rs.getDate(10));
				
				rows.add(bkTranStdRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted book records for students ", sqlex);
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
	public List<BKTranStaffModel> findStaffsTransactedBooks() {

		List<BKTranStaffModel> rows = new ArrayList<BKTranStaffModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT LIB.ACESSNO , LIB.BOOKNAME ,LIB.AUTHOR1S, LIB.AUTHOR1F ,SF.LID ,SF.LNAME , ");
		query.append(" SF.DEPT ,BK1.IDATE, BK1.RDATE FROM BKTRANSACTION1 BK1, ");
		query.append("LIBRARY LIB,STAFF SF WHERE SF.LID=BK1.LID AND BK1.CODE=LIB.ACESSNO ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTranStaffModel bkTranStfRcrd = new BKTranStaffModel();
				bkTranStfRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkTranStfRcrd.getBookModel().setBookName(rs.getString(2));
				bkTranStfRcrd.getBookModel().setAuthor1Surname(rs.getString(3));
				bkTranStfRcrd.getBookModel().setAuthor1Name(rs.getString(4));
				bkTranStfRcrd.getStaffModel().setId(rs.getString(5));				
				bkTranStfRcrd.getStaffModel().setName(rs.getString(6));
				bkTranStfRcrd.getStaffModel().setDept(rs.getString(7));
				bkTranStfRcrd.getBkTransModel().setIssuedDate(rs.getDate(8));
				bkTranStfRcrd.getBkTransModel().setReturnDate(rs.getDate(9));
				
				rows.add(bkTranStfRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted book records for students ", sqlex);
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
	public BKTranStudModel findStudentsTransactedBook(String bookCode) {

		BKTranStudModel bkTranStdRcrd = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT LIB.ACESSNO , LIB.BOOKNAME ,LIB.AUTHOR1S, LIB.AUTHOR1F ,SD.ADNO ,SD.SNAME , ");
		query.append(" SD.BRANCH ,SD.YEAR, SD.IMAGE ,BK.CODE, BK.IDATE, BK.RDATE, BK.NO_OF_REN, BK.ADNO FROM BKTRANSACTION BK, ");
		query.append("LIBRARY LIB,STUDENTDETAILS SD WHERE SD.ADNO=BK.ADNO AND BK.CODE= ? ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, bookCode);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				bkTranStdRcrd = new BKTranStudModel();
				bkTranStdRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkTranStdRcrd.getBookModel().setBookName(rs.getString(2));
				bkTranStdRcrd.getBookModel().setAuthor1Surname(rs.getString(3));
				bkTranStdRcrd.getBookModel().setAuthor1Name(rs.getString(4));
				bkTranStdRcrd.getStudentModel().setId(rs.getString(5));				
				bkTranStdRcrd.getStudentModel().setName(rs.getString(6));
				bkTranStdRcrd.getStudentModel().setBranch(rs.getString(7));
				bkTranStdRcrd.getStudentModel().setYear(rs.getString(8));				
				bkTranStdRcrd.getStudentModel().setImage(rs.getBytes(9));
				bkTranStdRcrd.getBkTransModel().setCode(rs.getString(10));
				bkTranStdRcrd.getBkTransModel().setIssuedDate(rs.getDate(11));
				bkTranStdRcrd.getBkTransModel().setReturnDate(rs.getDate(12));
				bkTranStdRcrd.getBkTransModel().setReneivalCount(rs.getInt(13));
				bkTranStdRcrd.getBkTransModel().setId(rs.getString(14));			
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted book record for students ", sqlex);
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
		return bkTranStdRcrd;	
	}
	public BKTranStaffModel findStaffTransactedBook(String bookCode) {

		BKTranStaffModel bkTranStfRcrd = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT LIB.ACESSNO , LIB.BOOKNAME ,LIB.AUTHOR1S, LIB.AUTHOR1F ,SF.LID ,SF.LNAME , ");
		query.append(" SF.DEPT, SF.IMAGE ,BK1.CODE, BK1.IDATE, BK1.RDATE, BK1.LID FROM BKTRANSACTION1 BK1, ");
		query.append("LIBRARY LIB,STAFF SF WHERE SF.LID=BK1.LID AND BK1.CODE= ? ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, bookCode);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				bkTranStfRcrd = new BKTranStaffModel();
				bkTranStfRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkTranStfRcrd.getBookModel().setBookName(rs.getString(2));
				bkTranStfRcrd.getBookModel().setAuthor1Surname(rs.getString(3));
				bkTranStfRcrd.getBookModel().setAuthor1Name(rs.getString(4));
				bkTranStfRcrd.getStaffModel().setId(rs.getString(5));				
				bkTranStfRcrd.getStaffModel().setName(rs.getString(6));
				bkTranStfRcrd.getStaffModel().setDept(rs.getString(7));								
				bkTranStfRcrd.getStaffModel().setImage(rs.getBytes(8));
				bkTranStfRcrd.getBkTransModel().setCode(rs.getString(9));
				bkTranStfRcrd.getBkTransModel().setIssuedDate(rs.getDate(10));
				bkTranStfRcrd.getBkTransModel().setReturnDate(rs.getDate(11));				
				bkTranStfRcrd.getBkTransModel().setId(rs.getString(12));			
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted book record for staff ", sqlex);
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
		return bkTranStfRcrd;	
	}
	public int getRenievalCount(String bkCode)
	{
		// doubtful, didnot understand why this is on bookcode rather than adno
		int count = -1 ;
		
		String FETCH_REN_COUNT = " SELECT NO_OF_REN FROM BKTRANSACTION WHERE CODE = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_REN_COUNT);
			pstmt.setString(1, bkCode);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				count = rs.getInt(1);				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching renieval count bktransaction by code :"+bkCode,e);
		}finally{
   		 if ( rs != null )
				try {
					rs.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the ResultSet "+e.getMessage(),e);
				}
   		 if (pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					log.error("SQLException while closing the Statement "+e.getMessage(),e);
				}
   	 	}		
       return count;	
	}
	public List<BKTranStudModel> findBooksPerStudent(String id)
	{

		List<BKTranStudModel> rows = new ArrayList<BKTranStudModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT  LIB.ACESSNO, LIB.BOOKNAME, LIB.AUTHOR1S, LIB.AUTHOR1F, TH.IDATE, TH.RDATE ");
		query.append(" FROM TRANSACTION_HISTORY TH, LIBRARY LIB WHERE TH.ADNO = ? AND TH.CODE=LIB.ACESSNO ");
		query.append("AND TH.VALUE = ? ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, id);
			pstmt.setString(2, "1");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTranStudModel bkTranStdRcrd = new BKTranStudModel();
				bkTranStdRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkTranStdRcrd.getBookModel().setBookName(rs.getString(2));
				bkTranStdRcrd.getBookModel().setAuthor1Surname(rs.getString(3));
				bkTranStdRcrd.getBookModel().setAuthor1Name(rs.getString(4));				
				bkTranStdRcrd.getBkTransModel().setIssuedDate(rs.getDate(5));
				bkTranStdRcrd.getBkTransModel().setReturnDate(rs.getDate(6));
				
				rows.add(bkTranStdRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted book records per student id: "+id, sqlex);
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
	public List<BKTranStaffModel> findBooksPerStaff(String id)
	{

		List<BKTranStaffModel> rows = new ArrayList<BKTranStaffModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT  LIB.ACESSNO, LIB.BOOKNAME, LIB.AUTHOR1S, LIB.AUTHOR1F, TH.IDATE, TH.RDATE ");
		query.append(" FROM TRANSACTION_HISTORY TH, LIBRARY LIB WHERE TH.LID = ? AND TH.CODE=LIB.ACESSNO ");
		query.append("AND TH.VALUE = ? ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, id);
			pstmt.setString(2, "1");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTranStaffModel bkTranStfRcrd = new BKTranStaffModel();
				bkTranStfRcrd.getBookModel().setAccessNo(rs.getString(1));
				bkTranStfRcrd.getBookModel().setBookName(rs.getString(2));
				bkTranStfRcrd.getBookModel().setAuthor1Surname(rs.getString(3));
				bkTranStfRcrd.getBookModel().setAuthor1Name(rs.getString(4));				
				bkTranStfRcrd.getBkTransModel().setIssuedDate(rs.getDate(5));
				bkTranStfRcrd.getBkTransModel().setReturnDate(rs.getDate(6));
				
				rows.add(bkTranStfRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted book records per staff id: "+id, sqlex);
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
	public List<BKTranStudModel> findStudentsPerBook(String code)
	{

		List<BKTranStudModel> rows = new ArrayList<BKTranStudModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT SD.ADNO, SD.SNAME, SD.BRANCH, SD.YEAR, TH.IDATE, TH.RDATE ");
		query.append(" FROM TRANSACTION_HISTORY TH, STUDENTDETAILS SD WHERE TH.CODE = ? AND TH.ADNO=SD.ADNO ");
		query.append("AND TH.VALUE = ? ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, code);
			pstmt.setString(2, "1");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTranStudModel bkTranStdRcrd = new BKTranStudModel();
				bkTranStdRcrd.getStudentModel().setId(rs.getString(1));
				bkTranStdRcrd.getStudentModel().setName(rs.getString(2));
				bkTranStdRcrd.getStudentModel().setBranch(rs.getString(3));
				bkTranStdRcrd.getStudentModel().setYear(rs.getString(4));				
				bkTranStdRcrd.getBkTransModel().setIssuedDate(rs.getDate(5));
				bkTranStdRcrd.getBkTransModel().setReturnDate(rs.getDate(6));
				
				rows.add(bkTranStdRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted student records per book id: "+code, sqlex);
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
	public List<BKTranStaffModel> findStaffsPerBook(String code)
	{

		List<BKTranStaffModel> rows = new ArrayList<BKTranStaffModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT SF.LID, SF.LNAME, SF.DEPT, TH.IDATE, TH.RDATE ");
		query.append(" FROM TRANSACTION_HISTORY TH, STAFF SF WHERE TH.CODE = ? AND TH.LID=SF.LID ");
		query.append("AND TH.VALUE = ? ");
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, code);
			pstmt.setString(2, "1");
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTranStaffModel bkTranStfRcrd = new BKTranStaffModel();
				bkTranStfRcrd.getStaffModel().setId(rs.getString(1));
				bkTranStfRcrd.getStaffModel().setName(rs.getString(2));
				bkTranStfRcrd.getStaffModel().setDept(rs.getString(3));								
				bkTranStfRcrd.getBkTransModel().setIssuedDate(rs.getDate(4));
				bkTranStfRcrd.getBkTransModel().setReturnDate(rs.getDate(5));
				
				rows.add(bkTranStfRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted staff records per book id: "+code, sqlex);
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
	@Override
	public List<BKTransMemberModel> findStaffPendingBooks(Map<SearchFilter.PendingCriteria, Object> filters) {

		List<BKTransMemberModel> rows = new ArrayList<BKTransMemberModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT SF.LID, SF.LNAME, SF.DEPT, BK.CODE, BK.IDATE, BK.RDATE ");
		query.append(" FROM BKTRANSACTION1 BK, STAFF SF, KEYCONSTRAINTS KEY WHERE KEY.ID = 2 AND BK.LID = SF.LID ");
		if ( filters != null )
		{
			if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				query.append(" AND  ( BK.RDATE <=  ? AND ( BK.RDATE <= KEY.TO_HOLD OR BK.RDATE <= ?)");
			if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_ID))
				query.append(" AND  SF.LID =  ? ");
			if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_BRANCH))
				query.append(" AND SF.DEPT LIKE ? ");			
			if ( !filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				query.append(" AND ( BK.RDATE <=  ? OR BK.RDATE <= KEY.TO_HOLD ) ");
		}else
			query.append(" AND  BK.RDATE <  ? ");
			
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			int cnt = 1;
			if ( filters != null )
			{
				if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				{
					Object val = filters.get(SearchFilter.PendingCriteria.PNDNG_DATE);
					if ( val != null && val instanceof Date)
					pstmt.setDate(cnt, new java.sql.Date(((Date)val).getTime()));
					cnt++;
					pstmt.setDate(cnt, new java.sql.Date(DateHelper.getCurrentDate().getTime()));
					cnt++;
				}
				if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_ID))
				{
					pstmt.setString(cnt, filters.get(SearchFilter.PendingCriteria.PNDNG_ID).toString());
					cnt++;
				}	
				if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_BRANCH))
				{
					pstmt.setString(cnt, filters.get(SearchFilter.PendingCriteria.PNDNG_BRANCH).toString());
					cnt++;
				}				
				if ( !filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				{
					pstmt.setDate(cnt, new java.sql.Date(DateHelper.getCurrentDate().getTime()));
					cnt++;
				}
				
			}else
				pstmt.setDate(1, new java.sql.Date(DateHelper.getCurrentDate().getTime()));
						
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTransMemberModel bkTranRcrd = new BKTransMemberModel();
				bkTranRcrd.setId(rs.getString(1));
				bkTranRcrd.setName(rs.getString(2));
				bkTranRcrd.setDept(rs.getString(3));				
				bkTranRcrd.setCode(rs.getString(4));
				bkTranRcrd.setIssuedDate(rs.getDate(5));
				bkTranRcrd.setReturnDate(rs.getDate(6));
				rows.add(bkTranRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching pending staff records: ", sqlex);
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
	public List<BKTransMemberModel> findPendingBooks(Map<SearchFilter.PendingCriteria, Object> filters) {

		List<BKTransMemberModel> rows = new ArrayList<BKTransMemberModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT ST.ADNO, ST.SNAME, ST.BRANCH, ST.YEAR,BK.CODE, BK.IDATE, BK.RDATE ");
		query.append(" FROM BKTRANSACTION BK, STUDENTDETAILS ST WHERE BK.ADNO = ST.ADNO ");
		if ( filters != null )
		{
			if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				query.append(" AND  BK.RDATE <=  ? ");
			if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_BRANCH))
				query.append(" AND ST.BRANCH LIKE ? ");
			if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_YEAR))
				query.append(" AND ST.YEAR LIKE ? ");
			if ( !filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				query.append(" AND  BK.RDATE <  ? ");
		}else
			query.append(" AND  BK.RDATE <  ? ");
			
		
		
				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			int cnt = 1;
			if ( filters != null )
			{
				if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				{
					Object val = filters.get(SearchFilter.PendingCriteria.PNDNG_DATE);
					if ( val != null && val instanceof Date)
					pstmt.setDate(cnt, new java.sql.Date(((Date)val).getTime()));
					cnt++;
				}
					
				if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_BRANCH))
				{
					pstmt.setString(cnt, filters.get(SearchFilter.PendingCriteria.PNDNG_BRANCH).toString());
					cnt++;
				}
				if ( filters.containsKey(SearchFilter.PendingCriteria.PNDNG_YEAR))
				{
					pstmt.setString(cnt, filters.get(SearchFilter.PendingCriteria.PNDNG_YEAR).toString());
					cnt++;
				}
				if ( !filters.containsKey(SearchFilter.PendingCriteria.PNDNG_DATE))
				{
					pstmt.setDate(cnt, new java.sql.Date(DateHelper.getCurrentDate().getTime()));
					cnt++;
				}
				
			}else
				pstmt.setDate(1, new java.sql.Date(DateHelper.getCurrentDate().getTime()));
						
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTransMemberModel bkTranRcrd = new BKTransMemberModel();
				bkTranRcrd.setId(rs.getString(1));
				bkTranRcrd.setName(rs.getString(2));
				bkTranRcrd.setDept(rs.getString(3));
				bkTranRcrd.setYear(rs.getString(4));
				bkTranRcrd.setCode(rs.getString(5));
				bkTranRcrd.setIssuedDate(rs.getDate(6));
				bkTranRcrd.setReturnDate(rs.getDate(7));
				rows.add(bkTranRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching pending student records: ", sqlex);
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
	public List<BKTransMemberModel> findMembersPerBook(String code)
	{

		List<BKTransMemberModel> rows = new ArrayList<BKTransMemberModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT BK.ADNO, BK.CODE, BK.IDATE, BK.RDATE, ST.SNAME, ST.BRANCH, ST.YEAR, LIB.AUTHOR1S, LIB.AUTHOR1F, ");
		query.append(" LIB.BOOKNAME FROM BKTRANSACTION BK, STUDENTDETAILS ST,LIBRARY LIB WHERE BK.CODE = ? ");
		query.append(" AND  BK.ADNO = ST.ADNO AND BK.CODE = LIB.ACESSNO ");
		query.append(" UNION ");
		query.append("SELECT BK1.LID, BK1.CODE, BK1.IDATE, BK1.RDATE, SF.LNAME, SF.DEPT, '1', LIB.AUTHOR1S, LIB.AUTHOR1F,  ");
		query.append(" LIB.BOOKNAME FROM BKTRANSACTION1 BK1, STAFF SF, LIBRARY LIB WHERE BK1.CODE = ? ");
		query.append("AND BK1.LID = SF.LID AND BK1.CODE = LIB.ACESSNO ");

				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, code);
			pstmt.setString(2, code);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTransMemberModel bkTranRcrd = new BKTransMemberModel();
				bkTranRcrd.setId(rs.getString(1));
				bkTranRcrd.setCode(rs.getString(2));
				bkTranRcrd.setIssuedDate(rs.getDate(3));
				bkTranRcrd.setReturnDate(rs.getDate(4));
				bkTranRcrd.setName(rs.getString(5));
				bkTranRcrd.setDept(rs.getString(6));
				bkTranRcrd.setYear(rs.getString(7));
				bkTranRcrd.setAuthorSurname(rs.getString(8));
				bkTranRcrd.setAuthorName(rs.getString(9));
				bkTranRcrd.setBookName(rs.getString(10));
				rows.add(bkTranRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted student and staff records per book id: "+code, sqlex);
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
	public Set<String> findCurrentAuthorList()
	{
		Set<String> rows = new HashSet<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT( LIB.AUTHOR1S||LIB.AUTHOR1F ) FROM LIBRARY LIB,BKTRANSACTION1 BK1 WHERE LIB.ACESSNO=BK1.CODE ");		
		query.append(" UNION ");
		query.append("SELECT DISTINCT( LIB.AUTHOR1S||LIB.AUTHOR1F ) FROM LIBRARY LIB,BKTRANSACTION BK WHERE LIB.ACESSNO=BK.CODE  ");
		
				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching Authors list: ", sqlex);
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
	public Set<String> findCurrentBookNameList(String authorName)
	{
		Set<String> rows = new HashSet<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT( LIB.BOOKNAME ) FROM LIBRARY LIB,BKTRANSACTION1 BK1 WHERE LIB.ACESSNO=BK1.CODE AND LIB.AUTHOR1S||LIB.AUTHOR1F LIKE ? ");		
		query.append(" UNION ");
		query.append("SELECT DISTINCT( LIB.BOOKNAME ) FROM LIBRARY LIB,BKTRANSACTION BK WHERE LIB.ACESSNO=BK.CODE  AND LIB.AUTHOR1S||LIB.AUTHOR1F LIKE ? ");

				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);			
			
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorName);
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching Booknames list: ", sqlex);
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
	public List<BKTransMemberModel> findMembersPerBook(String bookName, String authorName)
	{

		List<BKTransMemberModel> rows = new ArrayList<BKTransMemberModel>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT BK.ADNO, BK.CODE, BK.IDATE, BK.RDATE, ST.SNAME, ST.BRANCH, ST.YEAR, LIB.AUTHOR1S, LIB.AUTHOR1F, ");
		query.append(" LIB.BOOKNAME FROM BKTRANSACTION BK, STUDENTDETAILS ST,LIBRARY LIB WHERE LIB.BOOKNAME LIKE ? AND LIB.AUTHOR1S||LIB.AUTHOR1F LIKE ? ");
		query.append(" AND  BK.ADNO = ST.ADNO AND BK.CODE = LIB.ACESSNO ");
		query.append(" UNION ");
		query.append("SELECT BK1.LID, BK1.CODE, BK1.IDATE, BK1.RDATE, SF.LNAME, SF.DEPT, '1', LIB.AUTHOR1S, LIB.AUTHOR1F,  ");
		query.append(" LIB.BOOKNAME FROM BKTRANSACTION1 BK1, STAFF SF, LIBRARY LIB WHERE LIB.BOOKNAME LIKE ? AND LIB.AUTHOR1S||LIB.AUTHOR1F LIKE ? ");
		query.append("AND BK1.LID = SF.LID AND BK1.CODE = LIB.ACESSNO ");

				
		String FETCH_ID = query.toString();
		
		try {
		
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, bookName);
			pstmt.setString(2, authorName);
			pstmt.setString(3, bookName);
			pstmt.setString(4, authorName);

			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				BKTransMemberModel bkTranRcrd = new BKTransMemberModel();
				bkTranRcrd.setId(rs.getString(1));
				bkTranRcrd.setCode(rs.getString(2));
				bkTranRcrd.setIssuedDate(rs.getDate(3));
				bkTranRcrd.setReturnDate(rs.getDate(4));
				bkTranRcrd.setName(rs.getString(5));
				bkTranRcrd.setDept(rs.getString(6));
				bkTranRcrd.setYear(rs.getString(7));
				bkTranRcrd.setAuthorSurname(rs.getString(8));
				bkTranRcrd.setAuthorName(rs.getString(9));
				bkTranRcrd.setBookName(rs.getString(10));
				rows.add(bkTranRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching transacted student and staff records per book name: "+bookName +" author :"+authorName, sqlex);
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
