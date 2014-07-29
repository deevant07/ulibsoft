package org.ulibsoft.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SendOff extends JWindow
  {
     private ImageIcon icon;
     public SendOff()
       {
          setBounds(150,150,500,300);
          setVisible(true);

       }
       public void paint(Graphics g)
         {
           icon = new ImageIcon("images/second.jpg");
           icon.paintIcon(this,g,0,-30);
           g.setColor(Color.pink);
           g.drawString("Thank you for using this software .  .  .  .  .",40,270);
           closeMe();
         }
      private void closeMe()
        {
           try
           {
             Thread.sleep(2500);
             //hide();
             System.exit(0);
           }
         catch(Exception ew)
           {
           }
        }

  }