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
import org.ulibsoft.dao.core.KeyConstraintDAO;
import org.ulibsoft.model.KeyConstraints;
import org.ulibsoft.util.MyDriver;
import org.ulibsoft.util.datatype.DateHelper;

/**
 * @author deevan
 *
 */
public class JdbcKeyConstraintDAOImpl implements KeyConstraintDAO{

	private static final Logger log = Logger.getLogger(JdbcKeyConstraintDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
	
	public JdbcKeyConstraintDAOImpl() {
		try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	/*public KeyConstraints findImagePathById(short id)
    {
   	 String IMAGE_SQL = "SELECT IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID = ? ";
   	 KeyConstraints keyCnstrntModel = new KeyConstraints();
   	 keyCnstrntModel.setId(id);
   	 try
   	 {
   		 pstmt = con.prepareStatement(IMAGE_SQL);
   		 pstmt.setShort(1, keyCnstrntModel.getId());
       	 rs=pstmt.executeQuery();
       	 
            if(rs.next())
            {            	 
           	 keyCnstrntModel.setImagePath(rs.getString(1));
            }
   	 }catch ( SQLException ex)
   	 {
   		 log.error("SQLException while fetching the image path :"+ex.getMessage(),ex);
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
   	 return keyCnstrntModel;    	 
    }*/
	public KeyConstraints findById(short id)
    {
   	 String FETCH_SQL = "SELECT MIN_BOOKS,MAX_BOOKS,BOOK_HOLDING_PERIOD,MIN_CDS,MAX_CDS,CD_HOLDING_PERIOD,MIN_MZS,MAX_MZS,MZ_HOLDING_PERIOD,RENEIVAL_PERIOD_BOOK,NO_OF_RENEIVALS_BOOK,RENEIVAL_PERIOD_CD,NO_OF_RENEIVALS_CD,RENEIVAL_PERIOD_MZ,NO_OF_RENEIVALS_MZ,FINE_BOOK,FINE_CD,FINE_MZ,FROM_HOLD,TO_HOLD FROM KEYCONSTRAINTS WHERE ID = ? ";
   	 KeyConstraints keyCnstrntModel = new KeyConstraints();
   	 keyCnstrntModel.setId(id);
   	 try
   	 {
   		 pstmt = con.prepareStatement(FETCH_SQL);
   		 pstmt.setShort(1, keyCnstrntModel.getId());
       	 rs=pstmt.executeQuery();
       	 
            if(rs.next())
            {            	 
            	keyCnstrntModel.setMinBook(rs.getShort(1));
            	keyCnstrntModel.setMaxBook(rs.getShort(2));
            	keyCnstrntModel.setMaxDaysPerBook(rs.getShort(3));
            	keyCnstrntModel.setMinCD(rs.getShort(4));
            	keyCnstrntModel.setMaxCD(rs.getShort(5));
            	keyCnstrntModel.setMaxDaysPerCD(rs.getShort(6));
            	keyCnstrntModel.setMinMZ(rs.getShort(7));
            	keyCnstrntModel.setMaxMZ(rs.getShort(8));
            	keyCnstrntModel.setMaxDaysPerMZ(rs.getShort(9));
            	keyCnstrntModel.setReneivelPerBook(rs.getShort(10));
            	keyCnstrntModel.setMaxReneivelPerBook(rs.getShort(11));
            	keyCnstrntModel.setReneivelPerCD(rs.getShort(12));
            	keyCnstrntModel.setMaxReneivelPerCD(rs.getShort(13));
            	keyCnstrntModel.setReneivelPerMZ(rs.getShort(14));
            	keyCnstrntModel.setMaxReneivelPerMZ(rs.getShort(15));
            	keyCnstrntModel.setFinePerBook(rs.getInt(16));
            	keyCnstrntModel.setFinePerCD(rs.getInt(17));
            	keyCnstrntModel.setFinePerMZ(rs.getInt(18));
            	keyCnstrntModel.setBeginDate(rs.getDate(19));
            	keyCnstrntModel.setEndDate(rs.getDate(20));
           	 	
            }
   	 }catch ( SQLException ex)
   	 {
   		 log.error("SQLException while fetching the image path :"+ex.getMessage(),ex);
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
   	 return keyCnstrntModel;    	 
    }	
	public int create(KeyConstraints keyCnstrntModel) {
		
		int ret = -1;
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append(" INSERT INTO KEYCONSTRAINTS ( ID, MIN_BOOKS, MAX_BOOKS, BOOK_HOLDING_PERIOD, MIN_CDS, MAX_CDS, ");
		sqlInsert.append(" CD_HOLDING_PERIOD, MIN_MZS, MAX_MZS, MZ_HOLDING_PERIOD, RENEIVAL_PERIOD_BOOK, NO_OF_RENEIVALS_BOOK,  ");
		sqlInsert.append(" RENEIVAL_PERIOD_CD, NO_OF_RENEIVALS_CD, RENEIVAL_PERIOD_MZ, NO_OF_RENEIVALS_MZ, FINE_BOOK, FINE_CD,  ");
		sqlInsert.append(" FINE_MZ, FROM_HOLD, TO_HOLD ");
		sqlInsert.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
		
		
		String SQL_INSERT = sqlInsert.toString();
		try {
			pstmt = con.prepareStatement(SQL_INSERT);
			
			pstmt.setInt(1, keyCnstrntModel.getId());
			pstmt.setInt(2, keyCnstrntModel.getMinBook());
			pstmt.setInt(3, keyCnstrntModel.getMaxBook());
			pstmt.setInt(4, keyCnstrntModel.getMaxDaysPerBook());
			pstmt.setInt(5, keyCnstrntModel.getMinCD());
			pstmt.setInt(6, keyCnstrntModel.getMaxCD());
			pstmt.setInt(7, keyCnstrntModel.getMaxDaysPerCD());
			pstmt.setInt(8, keyCnstrntModel.getMinMZ());
			pstmt.setInt(9, keyCnstrntModel.getMaxMZ());
			pstmt.setInt(10, keyCnstrntModel.getMaxDaysPerMZ());
			pstmt.setInt(11, keyCnstrntModel.getReneivelPerBook());
			pstmt.setInt(12, keyCnstrntModel.getMaxReneivelPerBook());
			pstmt.setInt(13, keyCnstrntModel.getReneivelPerCD());
			pstmt.setInt(14, keyCnstrntModel.getMaxReneivelPerCD());
			pstmt.setInt(15, keyCnstrntModel.getReneivelPerMZ());
			pstmt.setInt(16, keyCnstrntModel.getMaxReneivelPerMZ());
			pstmt.setInt(17, keyCnstrntModel.getFinePerBook());
			pstmt.setInt(18, keyCnstrntModel.getFinePerCD());
			pstmt.setInt(19, keyCnstrntModel.getFinePerMZ());
			pstmt.setDate(20, DateHelper.getSqlDate(keyCnstrntModel.getBeginDate()));
			pstmt.setDate(21, DateHelper.getSqlDate(keyCnstrntModel.getEndDate()));
			
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while inserting keyconstraints record",e);
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
	public int update(KeyConstraints keyCnstrntModel) {
		
		int ret = -1;
		StringBuilder sqlUpdate = new StringBuilder();
		sqlUpdate.append(" UPDATE KEYCONSTRAINTS SET MIN_BOOKS = ?, MAX_BOOKS = ?, BOOK_HOLDING_PERIOD = ?, MIN_CDS = ?, MAX_CDS = ?, ");
		sqlUpdate.append(" CD_HOLDING_PERIOD = ?, MIN_MZS = ?, MAX_MZS = ?, MZ_HOLDING_PERIOD = ?, RENEIVAL_PERIOD_BOOK = ?, NO_OF_RENEIVALS_BOOK = ?,  ");
		sqlUpdate.append(" RENEIVAL_PERIOD_CD = ?, NO_OF_RENEIVALS_CD = ?, RENEIVAL_PERIOD_MZ = ?, NO_OF_RENEIVALS_MZ = ?, FINE_BOOK = ?, FINE_CD = ?,  ");
		sqlUpdate.append(" FINE_MZ = ?, FROM_HOLD = ?, TO_HOLD = ? ");
		sqlUpdate.append(" WHERE ID = ? ");
		
		
		String SQL_UPDATE = sqlUpdate.toString();
		try {
			pstmt = con.prepareStatement(SQL_UPDATE);
			
			
			pstmt.setInt(1, keyCnstrntModel.getMinBook());
			pstmt.setInt(2, keyCnstrntModel.getMaxBook());
			pstmt.setInt(3, keyCnstrntModel.getMaxDaysPerBook());
			pstmt.setInt(4, keyCnstrntModel.getMinCD());
			pstmt.setInt(5, keyCnstrntModel.getMaxCD());
			pstmt.setInt(6, keyCnstrntModel.getMaxDaysPerCD());
			pstmt.setInt(7, keyCnstrntModel.getMinMZ());
			pstmt.setInt(8, keyCnstrntModel.getMaxMZ());
			pstmt.setInt(9, keyCnstrntModel.getMaxDaysPerMZ());
			pstmt.setInt(10, keyCnstrntModel.getReneivelPerBook());
			pstmt.setInt(11, keyCnstrntModel.getMaxReneivelPerBook());
			pstmt.setInt(12, keyCnstrntModel.getReneivelPerCD());
			pstmt.setInt(13, keyCnstrntModel.getMaxReneivelPerCD());
			pstmt.setInt(14, keyCnstrntModel.getReneivelPerMZ());
			pstmt.setInt(15, keyCnstrntModel.getMaxReneivelPerMZ());
			pstmt.setInt(16, keyCnstrntModel.getFinePerBook());
			pstmt.setInt(17, keyCnstrntModel.getFinePerCD());
			pstmt.setInt(18, keyCnstrntModel.getFinePerMZ());
			pstmt.setDate(19, DateHelper.getSqlDate(keyCnstrntModel.getBeginDate()));
			pstmt.setDate(20, DateHelper.getSqlDate(keyCnstrntModel.getEndDate()));
			pstmt.setInt(21, keyCnstrntModel.getId());
			
			ret = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			log.error("SQLException while updating keyconstraints record",e);
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
