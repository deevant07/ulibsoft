/**
 * This POJO Model Object holds the KeyConstraints data retrieved/updated in database.
 */

package org.ulibsoft.model;

import java.util.Date;

public class KeyConstraints {
	
	private short id;
	private short minBook;
	private short maxBook;
	private short maxDaysPerBook;
	private short minCD;
	private short maxCD;
	private short maxDaysPerCD;
	private short minMZ;
	private short maxMZ;
	private short maxDaysPerMZ;
	private short reneivelPerBook;
	private short maxReneivelPerBook;
	private short reneivelPerCD;
	private short maxReneivelPerCD;
	private short reneivelPerMZ;
	private short maxReneivelPerMZ;
	private int finePerBook;
	private int finePerCD;
	private int finePerMZ;
	private Date beginDate;
	private Date endDate;
	private String imagePath;
	/**
	 * @return the id
	 */
	public short getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(short id) {
		this.id = id;
	}
	/**
	 * @return the minBook
	 */
	public short getMinBook() {
		return minBook;
	}
	/**
	 * @param minBook the minBook to set
	 */
	public void setMinBook(short minBook) {
		this.minBook = minBook;
	}
	/**
	 * @return the maxBook
	 */
	public short getMaxBook() {
		return maxBook;
	}
	/**
	 * @param maxBook the maxBook to set
	 */
	public void setMaxBook(short maxBook) {
		this.maxBook = maxBook;
	}
	/**
	 * @return the maxDaysPerBook
	 */
	public short getMaxDaysPerBook() {
		return maxDaysPerBook;
	}
	/**
	 * @param maxDaysPerBook the maxDaysPerBook to set
	 */
	public void setMaxDaysPerBook(short maxDaysPerBook) {
		this.maxDaysPerBook = maxDaysPerBook;
	}
	/**
	 * @return the minCD
	 */
	public short getMinCD() {
		return minCD;
	}
	/**
	 * @param minCD the minCD to set
	 */
	public void setMinCD(short minCD) {
		this.minCD = minCD;
	}
	/**
	 * @return the maxCD
	 */
	public short getMaxCD() {
		return maxCD;
	}
	/**
	 * @param maxCD the maxCD to set
	 */
	public void setMaxCD(short maxCD) {
		this.maxCD = maxCD;
	}
	/**
	 * @return the maxDaysPerCD
	 */
	public short getMaxDaysPerCD() {
		return maxDaysPerCD;
	}
	/**
	 * @param maxDaysPerCD the maxDaysPerCD to set
	 */
	public void setMaxDaysPerCD(short maxDaysPerCD) {
		this.maxDaysPerCD = maxDaysPerCD;
	}
	/**
	 * @return the minMZ
	 */
	public short getMinMZ() {
		return minMZ;
	}
	/**
	 * @param minMZ the minMZ to set
	 */
	public void setMinMZ(short minMZ) {
		this.minMZ = minMZ;
	}
	/**
	 * @return the maxMZ
	 */
	public short getMaxMZ() {
		return maxMZ;
	}
	/**
	 * @param maxMZ the maxMZ to set
	 */
	public void setMaxMZ(short maxMZ) {
		this.maxMZ = maxMZ;
	}
	/**
	 * @return the maxDaysPerMZ
	 */
	public short getMaxDaysPerMZ() {
		return maxDaysPerMZ;
	}
	/**
	 * @param maxDaysPerMZ the maxDaysPerMZ to set
	 */
	public void setMaxDaysPerMZ(short maxDaysPerMZ) {
		this.maxDaysPerMZ = maxDaysPerMZ;
	}
	/**
	 * @return the reneivelPerBook
	 */
	public short getReneivelPerBook() {
		return reneivelPerBook;
	}
	/**
	 * @param reneivelPerBook the reneivelPerBook to set
	 */
	public void setReneivelPerBook(short reneivelPerBook) {
		this.reneivelPerBook = reneivelPerBook;
	}
	/**
	 * @return the maxReneivelPerBook
	 */
	public short getMaxReneivelPerBook() {
		return maxReneivelPerBook;
	}
	/**
	 * @param maxReneivelPerBook the maxReneivelPerBook to set
	 */
	public void setMaxReneivelPerBook(short maxReneivelPerBook) {
		this.maxReneivelPerBook = maxReneivelPerBook;
	}
	/**
	 * @return the reneivelPerCD
	 */
	public short getReneivelPerCD() {
		return reneivelPerCD;
	}
	/**
	 * @param reneivelPerCD the reneivelPerCD to set
	 */
	public void setReneivelPerCD(short reneivelPerCD) {
		this.reneivelPerCD = reneivelPerCD;
	}
	/**
	 * @return the maxReneivelPerCD
	 */
	public short getMaxReneivelPerCD() {
		return maxReneivelPerCD;
	}
	/**
	 * @param maxReneivelPerCD the maxReneivelPerCD to set
	 */
	public void setMaxReneivelPerCD(short maxReneivelPerCD) {
		this.maxReneivelPerCD = maxReneivelPerCD;
	}
	/**
	 * @return the reneivelPerMZ
	 */
	public short getReneivelPerMZ() {
		return reneivelPerMZ;
	}
	/**
	 * @param reneivelPerMZ the reneivelPerMZ to set
	 */
	public void setReneivelPerMZ(short reneivelPerMZ) {
		this.reneivelPerMZ = reneivelPerMZ;
	}
	/**
	 * @return the maxReneivelPerMZ
	 */
	public short getMaxReneivelPerMZ() {
		return maxReneivelPerMZ;
	}
	/**
	 * @param maxReneivelPerMZ the maxReneivelPerMZ to set
	 */
	public void setMaxReneivelPerMZ(short maxReneivelPerMZ) {
		this.maxReneivelPerMZ = maxReneivelPerMZ;
	}
	/**
	 * @return the finePerBook
	 */
	public int getFinePerBook() {
		return finePerBook;
	}
	/**
	 * @param finePerBook the finePerBook to set
	 */
	public void setFinePerBook(int finePerBook) {
		this.finePerBook = finePerBook;
	}
	/**
	 * @return the finePerCD
	 */
	public int getFinePerCD() {
		return finePerCD;
	}
	/**
	 * @param finePerCD the finePerCD to set
	 */
	public void setFinePerCD(int finePerCD) {
		this.finePerCD = finePerCD;
	}
	/**
	 * @return the finePerMZ
	 */
	public int getFinePerMZ() {
		return finePerMZ;
	}
	/**
	 * @param finePerMZ the finePerMZ to set
	 */
	public void setFinePerMZ(int finePerMZ) {
		this.finePerMZ = finePerMZ;
	}
	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
