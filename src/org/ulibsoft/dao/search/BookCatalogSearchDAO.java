/**
 * 
 */
package org.ulibsoft.dao.search;

import java.util.Set;

/**
 * @author deevan
 *
 */
public interface BookCatalogSearchDAO {

	/**
	 * Gets the list of book names with search keyword
	 * @param keyword
	 * @return
	 */
	public Set<String> listBookNames(String keyword);
	/**
	 * Gets the list of Author Surname with the search keyword 
	 * @param keyword
	 * @return
	 */
	public Set<String> listAuthorSurnames(String keyword);
	/**
	 * Gets the list of Author Real name with search keyword
	 * @param keyword
	 * @return
	 */
	public Set<String> listAuthorNames(String keyword);
	/**
	 * Gets the list of Publishers with search keyword
	 * @param keyword
	 * @return
	 */
	public Set<String> listPublishers(String keyword);
	/**
	 * Gets the list of places with search keyword
	 * @param keyword
	 * @return
	 */
	public Set<String> listPlaces(String keyword);
	/**
	 * Gets the list of SUBJECT2 from library table with search keyword
	 * @param keyword
	 * @return
	 */
	public Set<String> listStreams(String keyword);
	/**
	 * Gets the list of First Author Surname with the search keyword 
	 * @param keyword
	 * @return
	 */
	public Set<String> listFirstAuthorSurnames(String keyword);
	/**
	 * Gets the list of First Author Real name with search keyword
	 * @param keyword
	 * @return
	 */
	public Set<String> listFirstAuthorNames(String surNamae);
	/**
	 * Gets the list of BookNames name by author surname and realname
	 * @param surname
	 * @param realName
	 * @return
	 */
	public Set<String> listBookNames(String surName, String realName);
	
	/**
	 * Find the count of books by surname, realname and booknames
	 * @param surName
	 * @param realName
	 * @param bookName
	 * @return
	 */
	public int countBooks(String surName, String realName, String bookName);
	
}
