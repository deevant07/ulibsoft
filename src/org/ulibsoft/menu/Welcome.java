/*
 * Welcome.java
 *
 * $Id$
 *
 *    Copyright (c) 2010 UlibSoft Technologies Ltd. All rights reserved. Use of
 *    copyright notice does not imply publication.
 *
 *                          CONFIDENTIAL INFORMATION
 *                          ------------------------
 *    This Document contains Confidential Information or Trade Secrets, or both,
 *    which are the property of UlibSoft Technologies Ltd. This document may not
 *    be copied, reproduced, reduced to any electronic medium or machine reada-
 *    ble form or otherwise duplicated and the information herein may not be us-
 *    ed, disseminated or otherwise disclosed except with the prior written con-
 *    sent of UlibSoft Technologies Ltd.
 */

package org.ulibsoft.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JWindow;

import org.ulibsoft.constants.STR;
import org.ulibsoft.login.Login;
import org.ulibsoft.util.ScreenResolution;

/**
 *
 * @author pavan kumar kunchapu
 */

public class Welcome extends JWindow {
    
    static {
        ScreenResolution.getResolution();
    }
    
    private static final int SPLAH_SCRN_WIDTH    = 500;
    private static final int SPLAH_SCRN_HEIGHT   = 375;

    private static final int SPLASH_SCRN_X =
            (ScreenResolution.SCREEN_WIDTH >> 1) - (SPLAH_SCRN_WIDTH >> 1);

    private static final int SPLASH_SCRN_Y =
            (ScreenResolution.SCREEN_HEIGHT >> 1) - (SPLAH_SCRN_HEIGHT >> 1);

    private static final int LOADING_INFO_X =  40;
    private static final int LOADING_INFO_Y = SPLAH_SCRN_HEIGHT - 20;

    private ImageIcon icon;    

    public Welcome() {
        super(ScreenResolution.getGraphicsConfiguration());        
        setBounds(SPLASH_SCRN_X, SPLASH_SCRN_Y, SPLAH_SCRN_WIDTH, SPLAH_SCRN_HEIGHT);
                
        try {
            InputStream is = "".getClass().getResourceAsStream(STR.IMG_SPLASH);
            int imgSize = is.available();            
            byte[] imgData = new byte[imgSize];
            is.read(imgData, 0, imgData.length);
            Image image = Toolkit.getDefaultToolkit().createImage(imgData);
            icon = new ImageIcon(image);

            is = null;
            image = null;
            imgData = null;
        }
        catch (IOException ex) {
            Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(true);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        if (icon == null) {
            repaint();
        }
        icon.paintIcon(this, g, 0, 0);
        g.setColor(Color.pink);
        g.drawString("Loading classes , databases .  .  .  .  .", 
                LOADING_INFO_X, LOADING_INFO_Y);        
    }

    public static void main(String a[]) {
        Welcome obj = new Welcome();

        try {
            Thread.sleep(5000);
            new Login();
            //obj.hide();
            obj.setVisible(false);
            System.exit(0);
        }
        catch(Exception e){

        }
    }
}