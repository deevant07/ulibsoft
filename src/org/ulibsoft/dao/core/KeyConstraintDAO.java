/**
 * 
 */
package org.ulibsoft.dao.core;

import org.ulibsoft.model.KeyConstraints;

/**
 * @author deevan
 *
 */
public interface KeyConstraintDAO {

	//public KeyConstraints findImagePathById(short id);
	/**
	 * Fetch the KeyContraints by id
	 * @param id
	 * @return
	 */
	public KeyConstraints findById(short id);
}
