/**
 * 
 */
package org.ulibsoft.model;

/**
 * Combination of {@link BookModel} and {@link StudentModel} which holds student and Book details
 * @author deevan
 *
 */
public class BKStudentModel {

	private BookModel bkModel;
	private StudentModel stdModel;
	
	public BKStudentModel() {
		bkModel = new BookModel();
		stdModel = new StudentModel();
	}
	/**
	 * @return the bkModel
	 */
	public BookModel getBkModel() {
		return bkModel;
	}
	/**
	 * @param bkModel the bkModel to set
	 */
	public void setBkModel(BookModel bkModel) {
		this.bkModel = bkModel;
	}
	/**
	 * @return the stdModel
	 */
	public StudentModel getStdModel() {
		return stdModel;
	}
	/**
	 * @param stdModel the stdModel to set
	 */
	public void setStdModel(StudentModel stdModel) {
		this.stdModel = stdModel;
	}
	
}
