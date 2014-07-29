/**
 * 
 */
package org.ulibsoft.model;

/**
 * @author deevan
 *
 */
public class BKTranStudModel {

	private BookModel bookModel;
	private StudentModel studentModel;
	private BKTransactionModel bkTransModel;
	
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
	 * @return the studentModel
	 */
	public StudentModel getStudentModel() {
		return studentModel;
	}

	/**
	 * @param studentModel the studentModel to set
	 */
	public void setStudentModel(StudentModel studentModel) {
		this.studentModel = studentModel;
	}

	public BKTranStudModel() {
		bookModel = new BookModel();
		studentModel = new StudentModel();
		bkTransModel = new BKTransactionModel();
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
