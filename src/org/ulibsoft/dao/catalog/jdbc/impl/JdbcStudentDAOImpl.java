/**
 * 
 */
package org.ulibsoft.dao.catalog.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.catalog.StudentDAO;
import org.ulibsoft.model.StudentModel;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcStudentDAOImpl implements StudentDAO{

	private static final Logger log = Logger.getLogger(JdbcStudentDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    
	public JdbcStudentDAOImpl() {
		try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	public int create(StudentModel rec) {
		
		int ret = -1;
		String SQL_INSERT = " INSERT INTO STUDENTDETAILS(ADNO, SNAME, BRANCH, YEAR, DATEOFJOIN, YEAROFJOIN, STATUS, IMAGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, rec.getId());
			pstmt.setString(2, rec.getName());
			pstmt.setString(3, rec.getBranch());
			pstmt.setString(4, rec.getYear());
			pstmt.setDate(5, new java.sql.Date(rec.getJoinDate().getTime()));
			pstmt.setString(6, rec.getYearOfJoin());
			pstmt.setString(7, rec.getStatus());
			if ( rec.getImage() != null )
				pstmt.setBytes(8, rec.getImage());
			else
				pstmt.setNull(8, Types.BLOB);
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting student record",e);
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
	public int update(StudentModel rec) {
		
		int ret = -1;
		String SQL_UPDATE = " UPDATE STUDENTDETAILS SET SNAME = ?, BRANCH = ?, YEAR = ?, DATEOFJOIN = ?, YEAROFJOIN = ?, STATUS = ?, CDATE = ?, QUESTION = ? , ANSWER = ? , PASSWORD =?, IMAGE = ? WHERE ADNO = ? ";
		try {
			pstmt = con.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, rec.getName());
			pstmt.setString(2, rec.getBranch());
			pstmt.setString(3, rec.getYear());
			
			if ( rec.getJoinDate() == null )
				pstmt.setDate(4, null);
			else
				pstmt.setDate(4, new java.sql.Date(rec.getJoinDate().getTime()));
			
			pstmt.setString(5, rec.getYearOfJoin());
			pstmt.setString(6, rec.getStatus());
			
			if ( rec.getcDate() == null )
				pstmt.setDate(7, null);
			else
				pstmt.setDate(7, new java.sql.Date(rec.getcDate().getTime()));
			
			pstmt.setString(8, rec.getQuestion());
			pstmt.setString(9, rec.getAnswer());
			pstmt.setString(10, rec.getPassword());
			if ( rec.getImage() != null )
				pstmt.setBytes(11, rec.getImage());
			else
				pstmt.setNull(11, Types.BLOB);
			
			pstmt.setString(12, rec.getId());
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while updating student record",e);
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
	public int delete(String id)
	{
		int ret = -1;
		String SQL_DELETE = "DELETE FROM STUDENTDETAILS WHERE ADNO = ?";
		try {
			pstmt = con.prepareStatement(SQL_DELETE);
			
			pstmt.setString(1, id);
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while deleting student record",e);
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
	public List<StudentModel> findAll() {

		String FETCH_ALL = "SELECT ADNO,SNAME,BRANCH,YEAR FROM STUDENTDETAILS";
		List<StudentModel> rows = new ArrayList<StudentModel>();
		try {

			pstmt = con.prepareStatement(FETCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StudentModel stdRcrd = new StudentModel();
				stdRcrd.setId(rs.getString(1));
				stdRcrd.setName(rs.getString(2));
				stdRcrd.setBranch(rs.getString(3));
				stdRcrd.setYear(rs.getString(4));
				rows.add(stdRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching student records", sqlex);
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
	public boolean exists(String id) {
		
		boolean exist = false ;
		
		String FETCH_ID = " SELECT  ADNO FROM STUDENTDETAILS WHERE ADNO = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				exist =true;
				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching student by id",e);
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
       return exist;
	}
	public StudentModel findById(String id) {
		
		StudentModel rec = null ;
		
		String FETCH_ID = " SELECT  SNAME, BRANCH, YEAR, DATEOFJOIN, YEAROFJOIN, STATUS, CDATE, QUESTION, ANSWER, PASSWORD,IMAGE FROM STUDENTDETAILS WHERE ADNO = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				rec = new StudentModel();
				rec.setId(id);
				rec.setName(rs.getString(1));
				rec.setBranch(rs.getString(2));
				rec.setYear(rs.getString(3));
				rec.setJoinDate(rs.getDate(4));
				rec.setYearOfJoin(rs.getString(5));
				rec.setStatus(rs.getString(6));
				rec.setcDate(rs.getDate(7));
				rec.setQuestion(rs.getString(8));
				rec.setAnswer(rs.getString(9));
				rec.setPassword(rs.getString(10));
				rec.setImage(rs.getBytes(11));
				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching student by id",e);
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
       return rec;
	}
	public boolean isActive(String id) {
		
		boolean active = false ;
		
		String FETCH_ID = " SELECT STATUS FROM STUDENTDETAILS WHERE ADNO = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				if ( "TRUE".equals(rs.getString(1)))
					active =true;
				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching student by id",e);
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
       return active;
	}
	public boolean hasBook(String id) {
		
		boolean isBook = false ;
		
		String FETCH_BKTN_ID = " SELECT ADNO FROM BKTRANSACTION WHERE ADNO= ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				isBook = true;				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching bktransaction by id",e);
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
		
       return isBook;

	}
	public boolean hasCD(String id) {
		
		boolean isCd = false ;
		
		String FETCH_BKTN_ID = " SELECT ADNO FROM CDTRANSACTION WHERE ADNO= ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				isCd = true;				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching cdtransaction by id",e);
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
		
       return isCd;

	}
	public boolean hasMagazine(String id) {
		
		boolean isMag = false ;
		
		String FETCH_BKTN_ID = " SELECT ADNO FROM MZTRANSACTION WHERE ADNO= ? ";
		try {
			pstmt = con.prepareStatement(FETCH_BKTN_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				isMag = true;				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching mztransaction by id",e);
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
		
	   return isMag;
	
	}
}
