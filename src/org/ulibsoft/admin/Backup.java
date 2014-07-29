package org.ulibsoft.admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import org.ulibsoft.util.AbsoluteConstraints;
import org.ulibsoft.util.AbsoluteLayout;
import org.ulibsoft.util.ScreenResolution;

public class Backup extends JFrame {

	private JButton export;
	private JButton recover;
    private JButton exit;
    private Container con;
	private JPanel pnl;
    
	public Backup() {
		super("Backup Manager" );
		ScreenResolution.getResolution();
        setSize(ScreenResolution.SCREEN_WIDTH, ScreenResolution.SCREEN_HEIGHT);
        //setSize(800,600);
        //show();

        con=getContentPane();
        con.setLayout(new AbsoluteLayout());
        con.setBackground(Color.black );
        
        createComponents();
        componentListener();
	}
	private void createComponents()
    {
		pnl = new JPanel(new AbsoluteLayout());
        pnl.setBackground(con.getBackground ());
        pnl.setBorder( new TitledBorder ( new EtchedBorder ( Color.cyan, new Color(0,0,40)),
                                          "LOGIN && LOGOUT", 1, 2,con.getFont(), Color.magenta));
        con.add(pnl,new AbsoluteConstraints(250,100,300,180));
        
        export=new JButton("EXPORT");
        export.setBackground(Color.cyan);
        export.setForeground(Color.magenta);
        export.setMnemonic ('E');
        export.setBorder(new BevelBorder(0));
        pnl.add(export,new AbsoluteConstraints(50,120+25,100,25));
        
        recover=new JButton("IMPORT");
        recover.setBackground(Color.cyan);
        recover.setForeground(Color.magenta);
        recover.setMnemonic ('M');
        recover.setBorder(new BevelBorder(0));
        pnl.add(recover,new AbsoluteConstraints(75,100,100,25));
        
        exit=new JButton("EXIT");
        exit.setBackground(Color.cyan);
        exit.setForeground(Color.magenta);
        exit.setMnemonic ('X');
        exit.setBorder(new BevelBorder(0));
        pnl.add(exit,new AbsoluteConstraints(153,120+25,100,25));

        setVisible(true);
    }
	 private void componentListener()
     {
		 export.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
        	   Runtime rt=Runtime.getRuntime();
        	   try {
				rt.exec("EXP GATESLIBRARY/FUTURE FILE=e:\\test.dmp ROWS=Y CONSTRAINTS=Y");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             }
         }
       );
		 recover.addActionListener(new ActionListener()
         {
           public void actionPerformed(ActionEvent e)
             {
        	   Runtime rt=Runtime.getRuntime();
        	   try {
				rt.exec("IMP GATESLIBRARY/FUTURE FULL=Y FILE=e:\\test.dmp ROWS=Y CONSTRAINTS=Y");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             }
         }
       );

		 exit.addActionListener(new ActionListener()
         {
           public void actionPerformed (ActionEvent e)
             {
              System.exit(0);
             }
         }
       );
     }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Backup();
	}

}
