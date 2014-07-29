/**
 * 
 */
package org.ulibsoft.model;

/**
 * @author deevan
 *
 */
public class BKTranStaffModel {

	private BookModel bookModel;
	private StaffModel staffModel;
	private BKTransactionModel bkTransModel;
	
	public BKTranStaffModel() {
		bookModel = new BookModel();
		staffModel = new StaffModel();
		bkTransModel = new BKTransactionModel();
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
	/**
	 * @return the bkTransModel
	 */
	public BKTransactionModel getBkTransModel() {
		return bkTransModel;
	}
	/**
	 * @param bkTransModel the bkTransModel to set
	 */
	public void setBkTransModel(BKTransactionModel bkTransModel) {
		this.bkTransModel = bkTransModel;
	}	
}
