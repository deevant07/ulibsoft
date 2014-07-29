/**
 * 
 */
package org.ulibsoft.dao.catalog;

import java.util.List;

import org.ulibsoft.model.StaffModel;

/**
 * @author deevan
 *
 */
public interface StaffDAO {

	/**
	 * Fetches the list of staff
	 * @return
	 */
	public List<StaffModel> findAll();
	/**
	 * Fetches the list of departments
	 * @return
	 */
	public List<String> findDepts();
	/**
	 * Fetch the staff record by Id
	 * @param id
	 * @return
	 */
	public StaffModel findById(String id);
	/**
	 * Creates the Staff record
	 * @param recCreate
	 * @return
	 */
	public int create(StaffModel recCreate);
	/**
	 * Updates the staff record
	 * @param rec
	 * @return
	 */
	public int update(StaffModel rec);
	/**
	 * Deletes the staff record by id
	 * @param id
	 * @return
	 */
	public int delete(String id);
	/**
	 * Checks if staff has any book by Staff id
	 * @param id
	 * @return
	 */
	public boolean hasBook(String id);
	/**
	 * Checks if staff has any CD by Staff id
	 * @param id
	 * @return
	 */
	public boolean hasCD(String id);
	/**
	 * Checks if staff has any Magazine by Staff id
	 * @param id
	 * @return
	 */
	public boolean hasMagazine(String id);
	
	/**
	 * Returns active status of the Staff
	 * @param id
	 * @return
	 */
	public boolean isActive(String id);
}
