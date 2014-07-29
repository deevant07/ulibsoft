/**
 * 
 */
package org.ulibsoft.dao.core;

/**
 * @author deevan
 *
 */
public interface AuthDAO {

	/**
	 * Authenticate user of type Student
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean authenticateStudentUser(String userName, String password);
	/**
	 * Authenticate user of type Staff
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean authenticateStaffUser(String userName, String password);
}
