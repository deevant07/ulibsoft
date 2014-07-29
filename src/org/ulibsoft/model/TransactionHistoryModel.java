/**
 * 
 */
package org.ulibsoft.model;

import java.util.Date;

/**
 * @author deevan
 *
 */
public class TransactionHistoryModel {

	private String code;
	private String cdCode;
	private String mzCode;
	private String sid;
	private String lid;
	private String status;
	private Date cDate;
	private String value;
	private Date issuedDate;
	private Date returnDate;
	private float fineAmount;
	private float finePaid;
	private Date fineDate;
	private String reason;
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
	 * @return the cdCode
	 */
	public String getCdCode() {
		return cdCode;
	}
	/**
	 * @param cdCode the cdCode to set
	 */
	public void setCdCode(String cdCode) {
		this.cdCode = cdCode;
	}
	/**
	 * @return the mzCode
	 */
	public String getMzCode() {
		return mzCode;
	}
	/**
	 * @param mzCode the mzCode to set
	 */
	public void setMzCode(String mzCode) {
		this.mzCode = mzCode;
	}
	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sid the sid to set
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the lid
	 */
	public String getLid() {
		return lid;
	}
	/**
	 * @param lid the lid to set
	 */
	public void setLid(String lid) {
		this.lid = lid;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the cDate
	 */
	public Date getcDate() {
		return cDate;
	}
	/**
	 * @param cDate the cDate to set
	 */
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return the fineAmount
	 */
	public float getFineAmount() {
		return fineAmount;
	}
	/**
	 * @param fineAmount the fineAmount to set
	 */
	public void setFineAmount(float fineAmount) {
		this.fineAmount = fineAmount;
	}
	/**
	 * @return the finePaid
	 */
	public float getFinePaid() {
		return finePaid;
	}
	/**
	 * @param finePaid the finePaid to set
	 */
	public void setFinePaid(float finePaid) {
		this.finePaid = finePaid;
	}
	/**
	 * @return the fineDate
	 */
	public Date getFineDate() {
		return fineDate;
	}
	/**
	 * @param fineDate the fineDate to set
	 */
	public void setFineDate(Date fineDate) {
		this.fineDate = fineDate;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}
