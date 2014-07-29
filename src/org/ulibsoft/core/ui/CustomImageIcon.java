package org.ulibsoft.core.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.ulibsoft.util.Thumbnail;

public class CustomImageIcon extends ImageIcon {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2732560210443033984L;
	Thumbnail thumb;
	
	byte[] imgData;
	
	public CustomImageIcon(String filename) {
		super(filename);
	}
	public CustomImageIcon(Thumbnail thumb) {
		super(thumb.getScldFile());
		this.thumb=thumb;
		
	}
	public CustomImageIcon(byte[] imageData) {
		super(imageData);
		this.imgData=imageData;
		
	};
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.white);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		if (getImageObserver() == null) {
			g.drawImage(getImage(), c.getWidth() / 2 - getIconWidth() / 2,
					c.getHeight() / 2 - getIconHeight() / 2, c);
		} else {
			g.drawImage(getImage(), c.getWidth() / 2 - getIconWidth() / 2,
					c.getHeight() / 2 - getIconHeight() / 2, getImageObserver());
		}
	}
	public byte[] getImageBytes()
	{
		if ( thumb == null)
			return imgData;
		byte[] finalImg=new byte[1];
		FileInputStream fims=null;
		File tmpFl;
		try {
			tmpFl=new File(thumb.getScldFile());
			long length=tmpFl.length();
			if ( length > Integer.MAX_VALUE )
			{
				System.out.println("File size is too big");
			}
			fims = new FileInputStream(tmpFl);
			int read=0,offset=0;
			finalImg=new byte[(int)length];
			while( offset<length &&(read=fims.read(finalImg, offset, finalImg.length-offset))>=0)
			{
				offset+=read;
			}
			if (offset < finalImg.length) {
		        throw new IOException("Could not completely read file "+thumb.getScldFile());
		    }		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if ( fims != null )
			{
				try {
					fims.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		
		return finalImg;
	}
	
	 

}