package org.ulibsoft.core.ui;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;

public class FilePreviewer extends JComponent
    implements PropertyChangeListener
  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon thumbnail = null;
	private static String currentDirPath;

    public FilePreviewer(JFileChooser fc)
    {
      setPreferredSize(new Dimension(100, 50));
      fc.addPropertyChangeListener(this);
      setBorder(new BevelBorder(1));
    }

    public void loadImage(File f)
    {
      if (f == null)
      {
        this.thumbnail = null;
      }
      else
      {
        ImageIcon tmpIcon = new ImageIcon(f.getPath());
        if (tmpIcon.getIconWidth() > 90)
        {
          this.thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, 1));
        }
        else
        {
          this.thumbnail = tmpIcon;
        }
      }
    }

    public void propertyChange(PropertyChangeEvent e)
    {
      String prop = e.getPropertyName();
      if (prop == "SelectedFileChangedProperty")
      {
        if (isShowing())
        {
          loadImage((File)e.getNewValue());
          repaint();
        }
      }
    }

    public void paint(Graphics g)
    {
      super.paint(g);
      if (this.thumbnail != null)
      {
        int x = getWidth() / 2 - this.thumbnail.getIconWidth() / 2;
        int y = getHeight() / 2 - this.thumbnail.getIconHeight() / 2;
        if (y < 0)
        {
          y = 0;
        }
        if (x < 5)
        {
          x = 5;
        }
        this.thumbnail.paintIcon(this, g, x, y);
      }
    }
    public static MyImageIcon loadImage(byte[] byteImage,int width,int height)
	  {    	
    	MyImageIcon icon = new MyImageIcon(byteImage); 
		return icon;	
	  }
    public static MyImageIcon loadImage(int width,int height)
	  {
    	JFileChooser fileChooser = createFileChooser();
        MyImageIcon icon =null;
        ExampleFileFilter filter = new ExampleFileFilter(
          new String[] { "jpg", "gif", "jpeg", "png" }, "example");

        ExampleFileView fileView = new ExampleFileView();		        
        fileChooser.setFileView(fileView);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        fileChooser.setAccessory(new FilePreviewer(fileChooser));

        int result = fileChooser.showSaveDialog(null);

        if (result == 0) {        	
        	icon = new MyImageIcon( scaleImage(fileChooser.getSelectedFile().getPath(),width,height ));	
        	currentDirPath = fileChooser.getSelectedFile().getParent();
        }
		return icon;	
	  }
    public static JFileChooser createFileChooser() {
	    JFileChooser fileChooser = new JFileChooser();
	    File homePath;
	    if ( currentDirPath != null )
	    {
	    	homePath = new File(currentDirPath);
		    if (homePath.exists()) {
		      fileChooser.setCurrentDirectory(homePath);
		    }else
		    {
		    	homePath = new File(System.getProperty("user.home"));
		    	if (homePath.exists()) {
				      fileChooser.setCurrentDirectory(homePath);
				    }
		    }
	    }else
	    {
	    	homePath = new File(System.getProperty("user.home"));		    
	    	if (homePath.exists()) {
			      fileChooser.setCurrentDirectory(homePath);
			    }
	    	return fileChooser;
	    }
	    return fileChooser;
    }
    private static ByteArrayOutputStream scaleImage(String origFileName,int width,int height) {
		
		BufferedImage originalImage;
		try {
				originalImage = ImageIO.read(new File(origFileName));
				int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				
				BufferedImage resizeImageJpg = resizeImage(originalImage, width, height, type);
				String extn = origFileName.substring(origFileName.lastIndexOf("."));
				String format = extn.substring(extn.indexOf('.')+1);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				boolean done = ImageIO.write(resizeImageJpg, format, baos);
				System.out.println(done);				
				return baos;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null; 		
	}
    private static BufferedImage resizeImage(BufferedImage originalImage,int width,int height, int type){
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.dispose();
		return resizedImage;
	}    
  }   