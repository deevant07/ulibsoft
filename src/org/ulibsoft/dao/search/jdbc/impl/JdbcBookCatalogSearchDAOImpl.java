/**
 * 
 */
package org.ulibsoft.dao.search.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ulibsoft.constants.ExitStatus;
import org.ulibsoft.dao.search.BookCatalogSearchDAO;
import org.ulibsoft.util.MyDriver;

/**
 * @author deevan
 *
 */
public class JdbcBookCatalogSearchDAOImpl implements BookCatalogSearchDAO {

	private static final Logger log = Logger.getLogger(JdbcBookCatalogSearchDAOImpl.class.getName());
	private Connection  con ;     
    private PreparedStatement pstmt;
    private ResultSet rs;
    private static final String WILDCARD_SEARCH_ALL = "%";
    
	public JdbcBookCatalogSearchDAOImpl() {
		try
        {
         con = MyDriver.getConnection();
        }catch(Exception e){
       	 log.error("Failed while getting DB Connection",e);
       	 System.exit(ExitStatus.FAIL);
        }
	}
	/* (non-Javadoc)
	 * @see org.ulibsoft.services.search.BookSearchService#listBookNames(java.lang.String)
	 */
	@Override
	public Set<String> listBookNames(String keyword) {
		
		String FETCH_BNAMES = "SELECT DISTINCT(BOOKNAME) FROM LIBRARY WHERE BOOKNAME LIKE ?";
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_BNAMES);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching booknames ", sqlex);
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
	public Set<String> listAuthorSurnames(String keyword)
	{
		String FETCH_AUT1S = "SELECT DISTINCT(AUTHOR1S) FROM LIBRARY WHERE AUTHOR1S LIKE ?";
		String FETCH_AUT2S = "SELECT DISTINCT(AUTHOR2S) FROM LIBRARY WHERE AUTHOR2S LIKE ?";
		String FETCH_AUT3S = "SELECT DISTINCT(AUTHOR3S) FROM LIBRARY WHERE AUTHOR3S LIKE ?";
		String FETCH_AUT4S = "SELECT DISTINCT(AUTHOR4S) FROM LIBRARY WHERE AUTHOR4S LIKE ?";
		String FETCH_AUT5S = "SELECT DISTINCT(AUTHOR5S) FROM LIBRARY WHERE AUTHOR5S LIKE ?";
		
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_AUT1S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			
			pstmt = con.prepareStatement(FETCH_AUT2S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			pstmt = con.prepareStatement(FETCH_AUT3S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			pstmt = con.prepareStatement(FETCH_AUT4S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			pstmt = con.prepareStatement(FETCH_AUT5S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching author surnames ", sqlex);
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
	public Set<String> listAuthorNames(String keyword)
	{
		String FETCH_AUT1S = "SELECT DISTINCT(AUTHOR1F) FROM LIBRARY WHERE AUTHOR1F LIKE ?";
		String FETCH_AUT2S = "SELECT DISTINCT(AUTHOR2F) FROM LIBRARY WHERE AUTHOR2F LIKE ?";
		String FETCH_AUT3S = "SELECT DISTINCT(AUTHOR3F) FROM LIBRARY WHERE AUTHOR3F LIKE ?";
		String FETCH_AUT4S = "SELECT DISTINCT(AUTHOR4F) FROM LIBRARY WHERE AUTHOR4F LIKE ?";
		String FETCH_AUT5S = "SELECT DISTINCT(AUTHOR5F) FROM LIBRARY WHERE AUTHOR5F LIKE ?";
		
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_AUT1S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			
			pstmt = con.prepareStatement(FETCH_AUT2S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			pstmt = con.prepareStatement(FETCH_AUT3S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			pstmt = con.prepareStatement(FETCH_AUT4S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			pstmt = con.prepareStatement(FETCH_AUT5S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
			
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching author realnames ", sqlex);
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
	public Set<String> listPublishers(String keyword) {
		
		String FETCH_PUBLSHR = "SELECT DISTINCT(PUBLISHER) FROM LIBRARY WHERE PUBLISHER LIKE ?";
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_PUBLSHR);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching publishers ", sqlex);
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
	public Set<String> listPlaces(String keyword) {
		
		String FETCH_PLACE = "SELECT DISTINCT(PLACE) FROM LIBRARY WHERE PLACE LIKE ?";
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_PLACE);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching publishers ", sqlex);
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
	public Set<String> listStreams(String keyword) {
		
		String FETCH_PLACE = "SELECT DISTINCT(SUBJECT2) FROM LIBRARY WHERE SUBJECT2 LIKE ?";
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_PLACE);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching streams ", sqlex);
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
	public Set<String> listFirstAuthorSurnames(String keyword)
	{
		String FETCH_AUT1S = "SELECT DISTINCT(AUTHOR1S) FROM LIBRARY WHERE AUTHOR1S LIKE ?";
		
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_AUT1S);
			pstmt.setString(1, keyword + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}			
						
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching author surnames ", sqlex);
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
	public Set<String> listFirstAuthorNames(String surName)
	{
		String FETCH_AUT1S = "SELECT DISTINCT(AUTHOR1F) FROM LIBRARY WHERE AUTHOR1S LIKE ?";
		
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_AUT1S);
			pstmt.setString(1, surName + WILDCARD_SEARCH_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}			
					
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching author realnames ", sqlex);
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
	public Set<String> listBookNames(String surName, String realName)
	{
		String FETCH_BKNM = "SELECT DISTINCT(BOOKNAME) FROM LIBRARY WHERE AUTHOR1S LIKE ?  AND AUTHOR1F LIKE ?";
		
		Set<String> rows = new HashSet<String>();
		try {

			pstmt = con.prepareStatement(FETCH_BKNM);
			pstmt.setString(1, surName );
			pstmt.setString(2, realName );
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				rows.add(rs.getString(1));
			}			
					
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching bookname for author surname: "+surName +" realname: "+realName, sqlex);
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
	public int countBooks(String surName, String realName, String bookName)
	{
		String FETCH_BKNM = "SELECT COUNT(*) FROM LIBRARY WHERE AUTHOR1S LIKE ?  AND AUTHOR1F LIKE ? AND BOOKNAME LIKE ?";
		
		int ret = -1;
		try {

			pstmt = con.prepareStatement(FETCH_BKNM);
			pstmt.setString(1, surName );
			pstmt.setString(2, realName );
			pstmt.setString(3, bookName);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				ret = rs.getInt(1);
			}			
					
		} catch (SQLException sqlex) {
			log.error("SQLException while fetching count of bookname for author surname: "+surName +" realname: "+realName +" bookname: "+bookName , sqlex);
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
		return ret;
	}

}
