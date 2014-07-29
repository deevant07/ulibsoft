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
   	 String FETCH_SQL = "SELECT MIN_BOOKS,MAX_BOOKS,BOOK_HOLDING_PERIOD,MIN_CDS,MAX_CDS,CD_HOLDING_PERIOD,MIN_MZS,MAX_MZS,MZ_HOLDING_PERIOD,RENEIVAL_PERIOD_BOOK,NO_OF_RENEIVALS_BOOK,RENEIVAL_PERIOD_CD,NO_OF_RENEIVALS_CD,RENEIVAL_PERIOD_MZ,NO_OF_RENEIVALS_MZ,FINE_BOOK,FINE_CD,FINE_MZ,FROM_HOLD,TO_HOLD,IMAGE_PATH FROM KEYCONSTRAINTS WHERE ID = ? ";
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
           	 	keyCnstrntModel.setImagePath(rs.getString(21));
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
	
}
