/**
 * 
 */
package org.ulibsoft.model;

import java.util.Date;

/**
 * This model holds the Book details with transaction details and Student/Staff details
 * @author deevan
 * 
 */
public class BKTransMemberModel {

	private String code;
	private String bookName;
	private String authorSurname;
	private String authorName;
	private String id;
	private String name;
	private String dept;
	private Date issuedDate;
	private Date returnDate;
	private int renievalCount;
	private String year;
	
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
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * @return the authorSurname
	 */
	public String getAuthorSurname() {
		return authorSurname;
	}
	/**
	 * @param authorSurname the authorSurname to set
	 */
	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
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
	 * @return the renievalCount
	 */
	public int getRenievalCount() {
		return renievalCount;
	}
	/**
	 * @param renievalCount the renievalCount to set
	 */
	public void setRenievalCount(int renievalCount) {
		this.renievalCount = renievalCount;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	
}
