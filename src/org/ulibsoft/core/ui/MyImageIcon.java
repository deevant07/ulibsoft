package org.ulibsoft.core.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.ByteArrayOutputStream;

import javax.swing.ImageIcon;

public class MyImageIcon extends ImageIcon
  {
    /**
	 * 
	 */
	private ByteArrayOutputStream byteStream;
	private static final long serialVersionUID = 1L;
	public MyImageIcon(String filename)
    {
      super(filename);
    }
	public MyImageIcon(byte[] byteImage)
    {
      super(byteImage);
    }
	public MyImageIcon(ByteArrayOutputStream baos)
	{
		super(baos.toByteArray());
		byteStream = baos;
	}
    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
      g.setColor(Color.white);
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
      if (getImageObserver() == null) {
        g.drawImage(
          getImage(), 
          c.getWidth() / 2 - getIconWidth() / 2, 
          c.getHeight() / 2 - getIconHeight() / 2, 
          c);
      }
      else
        g.drawImage(
          getImage(), 
          c.getWidth() / 2 - getIconWidth() / 2, 
          c.getHeight() / 2 - getIconHeight() / 2, 
          getImageObserver());
    }
    public byte[] getImageBytes()
    {
    	if ( byteStream != null )
    		return byteStream.toByteArray();
    	return null;
    }
    
  }