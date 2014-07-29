/**
 * 
 */
package org.ulibsoft.dao.core.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.core.AuthDAO;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcAuthDAOImpl implements AuthDAO {

	private static final Logger log = Logger.getLogger(JdbcAuthDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public JdbcAuthDAOImpl() {
    	try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	/* (non-Javadoc)
	 * @see org.ulibsoft.services.core.AuthService#authenticateStudentUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authenticateStudentUser(String userName, String password) {
		String dbPwd = getStudentPassword(userName);
		if ( password.equals(dbPwd) )
			return true;
		else
			return false;
	}
	@Override
	public boolean authenticateStaffUser(String userName, String password) {
		String dbPwd = getStaffPassword(userName);
		if ( password.equals(dbPwd) )
			return true;
		else
			return false;
	}
	private String getStudentPassword(String id)
	{
		
		String password = null ;
		
		String SQL_AUTH = "SELECT PASSWORD FROM STUDENTDETAILS WHERE ADNO= ? ";
		try {
			pstmt = con.prepareStatement(SQL_AUTH);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				password = rs.getString(1);				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching password by id",e);
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
		
       return password;	
	}
	private String getStaffPassword(String id)
	{
		
		String password = null ;
		
		String SQL_AUTH = "SELECT PASSWORD FROM STAFF WHERE LID= ? ";
		try {
			pstmt = con.prepareStatement(SQL_AUTH);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if ( rs.next() )
			{
				password = rs.getString(1);				
			}
		} catch (SQLException e) {
			log.error("SQLException while fetching password by id",e);
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
		
       return password;	
	}
}
