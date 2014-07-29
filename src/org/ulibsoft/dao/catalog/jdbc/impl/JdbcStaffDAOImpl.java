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
import org.ulibsoft.dao.catalog.StaffDAO;
import org.ulibsoft.model.StaffModel;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcStaffDAOImpl implements StaffDAO {

	private static final Logger log = Logger.getLogger(JdbcStaffDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
	
    public JdbcStaffDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
    
    public int create(StaffModel rec) {
		
		int ret = -1;
		String SQL_INSERT = " INSERT INTO STAFF(LID, LNAME, DEPT, JOININGDATE, TEACHING, STATUS, PASSWORD, QUESTION, ANSWER, CDATE , IMAGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setString(1, rec.getId());
			pstmt.setString(2, rec.getName());
			pstmt.setString(3, rec.getDept());
			if ( rec.getJoinDate() !=null )
				pstmt.setDate(4, new java.sql.Date(rec.getJoinDate().getTime()));
			else
				pstmt.setDate(4, null);
			pstmt.setString(5, rec.getStaffType());			
			pstmt.setString(6, rec.getStatus());
			pstmt.setString(7, rec.getPassword());
			pstmt.setString(8, rec.getQuestion());			
			pstmt.setString(9, rec.getAnswer());
			if ( rec.getcDate() != null )
				pstmt.setDate(10, new java.sql.Date(rec.getcDate().getTime()));
			else
				pstmt.setDate(10, null);
			
			if ( rec.getImage() != null )
				pstmt.setBytes(11, rec.getImage() );
			else
				pstmt.setNull(11, Types.BLOB);
				
			
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting staff record",e);
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
    public int update(StaffModel rec) {
		
		int ret = -1;
		String SQL_UPDATE = " UPDATE STAFF SET LNAME = ?, DEPT = ?, JOININGDATE = ?, TEACHING = ?, STATUS = ?, PASSWORD = ?, QUESTION = ? , ANSWER = ? , CDATE =?,  IMAGE = ? WHERE LID = ? ";
		try {
			pstmt = con.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, rec.getName());
			pstmt.setString(2, rec.getDept());
			if ( rec.getJoinDate() !=null )
				pstmt.setDate(3, new java.sql.Date(rec.getJoinDate().getTime()));
			else
				pstmt.setDate(3, null);
			pstmt.setString(4, rec.getStaffType());			
			pstmt.setString(5, rec.getStatus());
			pstmt.setString(6, rec.getPassword());
			pstmt.setString(7, rec.getQuestion());			
			pstmt.setString(8, rec.getAnswer());
			if ( rec.getcDate() != null )
				pstmt.setDate(9, new java.sql.Date(rec.getcDate().getTime()));
			else
				pstmt.setDate(9, null);		
			if ( rec.getImage() != null )
				pstmt.setBytes(10, rec.getImage());
			else
				pstmt.setNull(10, Types.BLOB);
			
			pstmt.setString(11, rec.getId());
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while updating staff record",e);
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
		String SQL_DELETE = "DELETE FROM STAFF WHERE LID = ?";
		try {
			pstmt = con.prepareStatement(SQL_DELETE);
			
			pstmt.setString(1, id);
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while deleting staff record",e);
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
	 * @see org.ulibsoft.services.core.StaffService#findDepts()
	 */
	@Override
	public List<String> findDepts() {

	   	 String DEPT_LIST_SQL = "SELECT DISTINCT(DEPT) FROM STAFF ";
	   	 List<String> depts = new ArrayList<String>();
	   	 try
	   	 {
	   		 pstmt = con.prepareStatement(DEPT_LIST_SQL);	   		
	       	 rs=pstmt.executeQuery();
	       	 
	            while(rs.next())
	            {            	 
	            	depts.add(rs.getString(1));
	            }
	   	 }catch ( SQLException ex)
	   	 {
	   		 log.error("SQLException while fetching the depts :"+ex.getMessage(),ex);
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
	   	 return depts;    	 
	    
	}
	public List<StaffModel> findAll() {

		String FETCH_ALL = "SELECT LID, LNAME, DEPT, TEACHING, JOININGDATE FROM STAFF";
		List<StaffModel> rows = new ArrayList<StaffModel>();
		try {

			pstmt = con.prepareStatement(FETCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StaffModel staffRcrd = new StaffModel();
				staffRcrd.setId(rs.getString(1));
				staffRcrd.setName(rs.getString(2));
				staffRcrd.setDept(rs.getString(3));
				staffRcrd.setStaffType(rs.getString(4));
				staffRcrd.setJoinDate(rs.getDate(5));
				rows.add(staffRcrd);
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching staff records", sqlex);
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
	public StaffModel findById(String id) {
		
		StaffModel rec = null ;
		
		String FETCH_ID = " SELECT LNAME, DEPT, JOININGDATE, TEACHING, STATUS, PASSWORD, QUESTION, ANSWER, CDATE, IMAGE FROM STAFF WHERE  LID = ? ";
		try {
			pstmt = con.prepareStatement(FETCH_ID);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				rec = new StaffModel();
				rec.setId(id);
				rec.setName(rs.getString(1));
				rec.setDept(rs.getString(2));				
				rec.setJoinDate(rs.getDate(3));
				rec.setStaffType(rs.getString(4));
				rec.setStatus(rs.getString(5));
				rec.setPassword(rs.getString(6));
				rec.setQuestion(rs.getString(7));
				rec.setAnswer(rs.getString(8));
				rec.setcDate(rs.getDate(9));
				rec.setImage(rs.getBytes(10));
				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching staff by id",e);
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
	public boolean isActive(String id) {
		
		boolean active = false ;
		
		String FETCH_ID = " SELECT STATUS FROM STAFF WHERE LID = ? ";
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
			log.error("SQLException while fetching staff by id",e);
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
	@Override
	protected void finalize() throws Throwable {
		con.close();
	}

}
