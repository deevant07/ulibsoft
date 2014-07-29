/**
 * 
 */
package org.ulibsoft.util.datatype;

/**
 * @author deevan
 *
 */
public class NumberUtils {

	public static float toFloat(String sValue)
	{
		float fValue = 0.0F;
		try
		{
			fValue = Float.parseFloat(sValue);
		}catch (NumberFormatException ne)
		{			
		}
		
		return fValue;
	}
}
