/**
 * This POJO Model Object holds the StudentDetails data retrieved/updated in database.
 */
package org.ulibsoft.model;

import java.util.Date;

/**
 * @author Deevan
 *
 */
public class StudentModel {

	private String id;
	private String name;
	private String branch;
	private String year;
	private Date joinDate;
	private String yearOfJoin;
	private String status;
	private Date cDate;
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	/**
	 * @return the yearOfJoin
	 */
	public String getYearOfJoin() {
		return yearOfJoin;
	}
	/**
	 * @param yearOfJoin the yearOfJoin to set
	 */
	public void setYearOfJoin(String yearOfJoin) {
		this.yearOfJoin = yearOfJoin;
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
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
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
		}else if (field.equals("branch"))
		{
			return getBranch();
		}else if (field.equals("year"))
		{
			return getYear();
		}else if ( field.equals("joinDate"))
		{
			return getJoinDate();
		}
		
		return ret;
	}
}
