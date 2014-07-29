/**
 * 
 */
package org.ulibsoft.model;

import java.util.Date;

/**
 * This is replica of BKTRANSACTION table which holds the list of book transactions for student
 * @author deevan
 *
 */
public class BKTransactionModel {

	private String code;
	private String id;
	private Date issuedDate;
	private Date returnDate;
	private int reneivalCount;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the issuedDate
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}
	/**
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	/**
	 * @return the reneivalCount
	 */
	public int getReneivalCount() {
		return reneivalCount;
	}
	/**
	 * @param reneivalCount the reneivalCount to set
	 */
	public void setReneivalCount(int reneivalCount) {
		this.reneivalCount = reneivalCount;
	}
	
}
