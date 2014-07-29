/**
 * 
 */
package org.ulibsoft.util.db;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.ulibsoft.util.MyDriver;

/**
 * @author Administrator
 *
 */
public class BlobUtil {

	public static byte[] toByteArray(Blob img) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try {
	      return toByteArrayImpl(img, baos);
	    } catch (Exception e) {
	    }
	    return null;
	}
	public static byte[] toByteArrayImpl(Blob fromImageBlob, 
		      ByteArrayOutputStream baos) throws SQLException, IOException {
		    byte buf[] = new byte[4000];
		    int dataSize;
		    InputStream is = fromImageBlob.getBinaryStream(); 

		    try {
		      while((dataSize = is.read(buf)) != -1) {
		        baos.write(buf, 0, dataSize);
		      }    
		    } finally {
		      if(is != null) {
		        is.close();
		      }
		    }
		    return baos.toByteArray();
	}
	public static Blob createBlob(byte[] image) {
		
		Blob blob=null;
		
		try {
				blob = MyDriver.getConnection().createBlob();
				blob.setBytes(0, image);
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blob;
	}
	
}
