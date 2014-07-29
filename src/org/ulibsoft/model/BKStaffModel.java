/**
 * 
 */
package org.ulibsoft.model;

/**
 * Combination of {@link BookModel} and {@link StaffModel} which can hold staff details and book details.
 * @author deevan
 */
public class BKStaffModel {

	private BookModel bookModel;
	private StaffModel staffModel;
	
	public BKStaffModel() {
		bookModel = new BookModel();
		staffModel = new StaffModel();
	}
	/**
	 * @return the bookModel
	 */
	public BookModel getBookModel() {
		return bookModel;
	}
	/**
	 * @param bookModel the bookModel to set
	 */
	public void setBookModel(BookModel bookModel) {
		this.bookModel = bookModel;
	}
	/**
	 * @return the staffModel
	 */
	public StaffModel getStaffModel() {
		return staffModel;
	}
	/**
	 * @param staffModel the staffModel to set
	 */
	public void setStaffModel(StaffModel staffModel) {
		this.staffModel = staffModel;
	}
	
	
}
