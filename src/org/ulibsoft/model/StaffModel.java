/**
 * This is POJO Model Object for holding the StaffDetails data retrieved/update in database. 
 */
package org.ulibsoft.model;

import java.util.Date;

/**
 * @author Deevan
 *
 */

public class StaffModel {

	
	private String id;
	private String name;
	private String dept;
	private String staffType;
	private Date joinDate;
	private Date cDate;
	private String status;
	private String question;
	private String answer;
	private String password;
	private byte[] image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getImage() {
		if( image!=null)
			return image;
		else
			return null;
	}
	public void setImage(byte[] img) {
		this.image=img;
	}
	
	
	public Object get(String field)
	{
		Object ret=null;
		
		if( field.equals("id"))
		{
			return getId();
		}else if(field.equals("name"))
		{
			return getName();
		}else if (field.equals("dept"))
		{
			return getDept();
		}else if ( field.equals("joinDate"))
		{
			return getJoinDate();
		}
		
		return ret;
	}
}
