/**
 * 
 */
package org.ulibsoft.dao.catalog;

import java.util.List;

import org.ulibsoft.model.BookModel;

/**
 * @author deevan
 *
 */
public interface BookCatalogDAO {

	/**
	 * Creates a book record in Library table
	 * @param bkModel
	 * @return
	 */
	public int create(BookModel bkModel);
	/**
	 * Updates a book record in Library table
	 * @param bkModel
	 * @return
	 */
	public int update(BookModel bkModel);
	/**
	 * Fetch the list of books created or updated in library table for current day 
	 * @return
	 */
	public List<BookModel> findByCurrentDay();
	/**
	 * Fetch the book record by access no from library table
	 * @param accessNo
	 * @return
	 */
	public BookModel findByAccessNo(String accessNo);
	/**
	 * Get the next sequence number for library table
	 * @return
	 */
	public int getNextSequenceNo();
}
