package org.ulibsoft.util;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


/**
 * Thumbnail.java (requires Java 1.2+)
 * Load an image, scale it down and save it as a JPEG file.
 * @author Marco Schmidt
 */
public class Thumbnail {
  
	private String orgFile;
	private String scldFile;
	
	public String getOrgFile() {
		return orgFile;
	}

	public void setOrgFile(String orgFile) {
		this.orgFile = orgFile;
	}

	public String getScldFile() {
		return scldFile;
	}

	public void setScldFile(String scldFile) {
		this.scldFile = scldFile;
	}

	/*public void scaleImage(int thumbWidth,int thumbHeight,int quality) throws Exception {
		  
		
		thumbWidth=ScreenResolution.getWidth(thumbWidth);
		thumbHeight=ScreenResolution.getHeight(thumbHeight);
	    // load image from INFILE
	    Image image = Toolkit.getDefaultToolkit().getImage(orgFile);
	    MediaTracker mediaTracker = new MediaTracker(new Container());
	    mediaTracker.addImage(image, 0);
	    mediaTracker.waitForID(0);
	    // determine thumbnail size from WIDTH and HEIGHT
	    
	    double thumbRatio = (double)thumbWidth / (double)thumbHeight;
	    int imageWidth = image.getWidth(null);
	    int imageHeight = image.getHeight(null);
	    double imageRatio = (double)imageWidth / (double)imageHeight;
	    if (thumbRatio < imageRatio) {
	      thumbHeight = (int)(thumbWidth / imageRatio);
	    } else {
	      thumbWidth = (int)(thumbHeight * imageRatio);
	    }
	    // draw original image to thumbnail image object and
	    // scale it to the new size on-the-fly
	    BufferedImage thumbImage = new BufferedImage(thumbWidth, 
	      thumbHeight, BufferedImage.SCALE_SMOOTH);
	    Graphics2D graphics2D = thumbImage.createGraphics();
	    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
	    // save thumbnail image to OUTFILE
	    BufferedOutputStream out = new BufferedOutputStream(new
	      FileOutputStream(scldFile));
	   // System.out.println();
	    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	    JPEGEncodeParam param = encoder.
	      getDefaultJPEGEncodeParam(thumbImage);
	    
	    quality = Math.max(0, Math.min(quality, 100));
	    param.setQuality((float)quality / 100.0f, false);
	    encoder.setJPEGEncodeParam(param);
	    encoder.encode(thumbImage);
	    out.close(); 
	    System.out.println("Done.");    
	  }*/
	public void scaleImage(int thumbWidth,int thumbHeight,int quality) throws Exception {
	  
	
	thumbWidth=ScreenResolution.getWidth(thumbWidth);
	thumbHeight=ScreenResolution.getHeight(thumbHeight);
    // load image from INFILE
    Image image = Toolkit.getDefaultToolkit().getImage(orgFile);
    MediaTracker mediaTracker = new MediaTracker(new Container());
    mediaTracker.addImage(image, 0);
    mediaTracker.waitForID(0);
    // determine thumbnail size from WIDTH and HEIGHT
    
    double thumbRatio = (double)thumbWidth / (double)thumbHeight;
    int imageWidth = image.getWidth(null);
    int imageHeight = image.getHeight(null);
    double imageRatio = (double)imageWidth / (double)imageHeight;
    if (thumbRatio < imageRatio) {
      thumbHeight = (int)(thumbWidth / imageRatio);
    } else {
      thumbWidth = (int)(thumbHeight * imageRatio);
    }
    // draw original image to thumbnail image object and
    // scale it to the new size on-the-fly
    BufferedImage thumbImage = new BufferedImage(thumbWidth,thumbHeight, BufferedImage.SCALE_SMOOTH);
    Graphics2D graphics2D = thumbImage.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
    graphics2D.dispose();
    
    // save thumbnail image to OUTFILE
   // BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(scldFile));
   // System.out.println();
   // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
   // JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
    
    quality = Math.max(0, Math.min(quality, 100));
   // param.setQuality((float)quality / 100.0f, false);
   // encoder.setJPEGEncodeParam(param);
   // encoder.encode(thumbImage);
   // out.close();
    File scldfile = new File(scldFile);
    ImageIO.write(thumbImage, "jpg", scldfile);
    System.out.println("Done.");    
  }
}
