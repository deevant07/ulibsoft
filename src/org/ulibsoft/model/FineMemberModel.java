/**
 * 
 */
package org.ulibsoft.model;

/**
 * @author deevan
 *
 */
public class FineMemberModel extends TransMemberModel {

	private float fineAmount;
	private float finePaid;
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
	
}
