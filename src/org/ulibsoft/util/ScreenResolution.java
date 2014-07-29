package org.ulibsoft.util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author pavan kumar kunchapu
 */

public final class ScreenResolution {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int defaultWidth=800;
    public static int defaultHeight=600;
    
    static{
    	getResolution();
    }
    
    public static void getResolution() {
        GraphicsConfiguration gc = getGraphicsConfiguration();
        SCREEN_WIDTH = (int) gc.getBounds().getWidth();
        SCREEN_HEIGHT = (int) gc.getBounds().getHeight();
    }

    public static GraphicsConfiguration getGraphicsConfiguration() {
        return GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();
    }
    public static int getWidth(int width)
    {
    	return (int)(width * ScreenResolution.SCREEN_WIDTH / defaultWidth);
    }
    public static int getHeight(int height)
    {
    	return (int)(height * ScreenResolution.SCREEN_HEIGHT / defaultHeight);
    }
}