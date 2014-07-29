/**
 * 
 */
package org.ulibsoft.dao.catalog;

import java.util.List;

import org.ulibsoft.model.StudentModel;

/**
 * @author deevan
 *
 */
public interface StudentDAO {
	
	/**
	 * Deletes the Student record by id
	 * @param id
	 * @return
	 */
	public int delete(String id);
	/**
	 * Updates the Student record
	 * @param rec
	 * @return
	 */
	public int update(StudentModel rec);
	/**
	 * Creates the Student record
	 * @param rec
	 * @return
	 */
	public int create(StudentModel rec);
	/**
	 * Check if Student record exists by id.
	 * @param id
	 * @return
	 */
	public boolean exists(String id);
	/**
	 * Check if Student record is active by id
	 * @param id
	 * @return
	 */
	public boolean isActive(String id);
	/**
	 * Fetch the Student record by id
	 * @param id
	 * @return
	 */
	public StudentModel findById(String id);
	/**
	 * Fetch the list of Student records
	 * @return
	 */
	public List<StudentModel> findAll();
	/**
	 * Find if Student holds atleast one book
	 * @param id
	 * @return
	 */
	public boolean hasBook(String id);
	/**
	 * Find if Student holds atleast one CD
	 * @param id
	 * @return
	 */
	public boolean hasCD(String id);
	/**
	 * Find if Student holds atleast one Magazine
	 * @param id
	 * @return
	 */
	public boolean hasMagazine(String id);
	
}
