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
	 * Gets the list of 1st Author Surname with the search keyword 
	 * @param keyword
	 * @return
	 */
	public Set<String> listAuthorSurnames(String keyword);
	/**
	 * Gets the list of 1st Author Real name with search keyword
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
}
