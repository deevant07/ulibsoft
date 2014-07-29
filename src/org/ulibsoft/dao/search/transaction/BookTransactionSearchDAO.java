package org.ulibsoft.dao.search.transaction;

import java.util.List;
import java.util.Map;

import org.ulibsoft.constants.SearchFilter;
import org.ulibsoft.model.BKStaffModel;
import org.ulibsoft.model.BKStudentModel;
import org.ulibsoft.model.BKTranStaffModel;
import org.ulibsoft.model.BKTranStudModel;
import org.ulibsoft.model.BKTransMemberModel;
import org.ulibsoft.model.BookModel;

public interface BookTransactionSearchDAO {

	/**
	 * Fetches the Student details and book transaction details holding the book by book code
	 * @param accessNo
	 * @return
	 */
	public BKStudentModel findByAccessNoForStudent(String accessNo);
	/**
	 * Fetches the Staff details and book transaction details holding the book by book code
	 * @param accessNo
	 * @return
	 */
	public BKStaffModel findByAccessNoForStaff(String accessNo);
	/**
	 * Checks if the Student holds the book for the book code
	 * @param accessNo
	 * @return
	 */
	public boolean hasStudent(String accessNo);
	/**
	 * Checks if the Staff holds the book for the book code
	 * @param accessNo
	 * @return
	 */
	public boolean hasStaff(String accessNo);
	/**
	 * Fetch the count of books for the given Staff id
	 * @param id
	 * @return
	 */
	public int getCountBooksStaff(String id);
	/**
	 * Fetch the count of books for the given Student id
	 * @param id
	 * @return
	 */
	public int getCountBooks(String id);
	
	/**
	 * Fetch the list of books are available within the librarys
	 * @return
	 */
	public List<BookModel> availableBooks();
	
	/**
	 * Find the list of books transacted by Students
	 * @return
	 */
	public List<BKTranStudModel> findStudentsTransactedBooks();
	/**
	 * Find the list of books transacted by Staff's
	 * @return
	 */
	public List<BKTranStaffModel> findStaffsTransactedBooks();
	
	/**
	 * Fetch the list of students who transacted the particular book
	 * @param bookCode
	 * @return
	 */
	public BKTranStudModel findStudentsTransactedBook(String bookCode);
	/**
	 * Find the list of staff's who have transacted the particular book
	 * @param bookCode
	 * @return
	 */
	public BKTranStaffModel findStaffTransactedBook(String bookCode);
	
	/**
	 * Get the reneival count for a particular book by a student
	 * @param bkCode
	 * @return
	 */
	public int getRenievalCount(String bkCode);
	
	/**
	 * Fetch the list of books a particular student holds
	 * @param id
	 * @return
	 */
	public List<BKTranStudModel> findBooksPerStudent(String id);
	/**
	 * Fetch the list of books a particular staff holds
	 * @param id
	 * @return
	 */
	public List<BKTranStaffModel> findBooksPerStaff(String id);
	
	/**
	 * Find the list of students who had taken the particular book
	 * @param code
	 * @return
	 */
	public List<BKTranStudModel> findStudentsPerBook(String code);
	/**
	 * Fetch the list of staff who had taken the particular book
	 * @param code
	 * @return
	 */
	public List<BKTranStaffModel> findStaffsPerBook(String code);
	
	/**
	 * Fetch the list of staff and student who had taken the particular book
	 * @param code
	 * @return
	 */
	public List<BKTransMemberModel> listPerBook(String code);
	
	/**
	 * Fetch the list of students with pending books
	 * @return
	 */
	public List<BKTransMemberModel> findPendingBooks(Map<SearchFilter.PendingCriteria, Object> filters);
	
	
	
	
}