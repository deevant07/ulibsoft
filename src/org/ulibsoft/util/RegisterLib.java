package org.ulibsoft.util;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class RegisterLib extends JFrame {

    private Container c;

    private JPanel progressPanel, viewPanel;

    private JLabel systemCompatibility, dbaSettings, creatingDB, fileTransfer,
            registering, launchLib;

    private JLabel dbaName, dbaPassword;
    private JTextField dbaNameField;
    private JPasswordField dbaPasswordField;
    private JPanel dbaPanel;

    private JLabel dbName, dbPassword;
    private JTextField dbNameField;
    private JPasswordField dbPasswordField;
    private JPanel dbPanel;
    private JButton dbCreat;

    private JLabel univName, univAddress, univEmail;
    private JTextField univNameField, univAddressField, univEmailField;
    private JPanel univPanel;

    private JButton registerSoftware, exitInstallation;

    public RegisterLib() {
        super("REGISTERATION PROCESS...");
        //checkSystemConfiguration();
        setResolution();
        setBounds(ScreenResolution.SCREEN_WIDTH	>> 4,
                ScreenResolution.SCREEN_HEIGHT >> 4,
                ScreenResolution.SCREEN_WIDTH - (ScreenResolution.SCREEN_WIDTH >> 3),
                ScreenResolution.SCREEN_HEIGHT - (ScreenResolution.SCREEN_HEIGHT >> 3));
        createComponents();
        //componentListener();
    }

    private void createComponents() {
        c = getContentPane();
        c.setLayout(new AbsoluteLayout());

        progressPanel = new JPanel();
        progressPanel.setBackground(new Color(0, 0, 40));
        progressPanel.setLayout(new AbsoluteLayout());
        progressPanel.setBorder( new TitledBorder(
                new EtchedBorder( Color.cyan, new Color(0, 0, 40)),
                "INSTALLATION...",
                2,
                2,
                new Font(c.getFont().getFontName(), Font.PLAIN, 10),
                Color.magenta));
        c.add(progressPanel, new AbsoluteConstraints(
                0, 0, (int) getBounds().getWidth() >> 2, 492));

        systemCompatibility = new JLabel("1. System Compatibility");
        systemCompatibility.setForeground (new Color( 120, 120, 153));
        progressPanel.add ( systemCompatibility, new AbsoluteConstraints(15, 30));

        dbaSettings = new JLabel("2. DBA Settings");
        dbaSettings.setForeground ( new Color ( 120, 120, 153 ) );
        progressPanel.add ( dbaSettings, new AbsoluteConstraints( 15, 50) );

        creatingDB = new JLabel("3. DB creation");
        creatingDB.setForeground ( new Color ( 120, 120, 153 ) );
        progressPanel.add ( creatingDB, new AbsoluteConstraints( 15, 70) );

        fileTransfer = new JLabel("4. File Transfer");
        fileTransfer.setForeground ( new Color ( 120, 120, 153 ) );
        progressPanel.add ( fileTransfer, new AbsoluteConstraints( 15, 90) );

        registering = new JLabel("5. Registration");
        registering.setForeground ( new Color ( 120, 120, 153 ) );
        progressPanel.add ( registering, new AbsoluteConstraints( 15, 110) );

        launchLib = new JLabel("6. Launch Product");
        launchLib.setForeground ( new Color ( 120, 120, 153 ) );
        progressPanel.add ( launchLib, new AbsoluteConstraints( 15, 130) );

        viewPanel = new JPanel();
        viewPanel.setBackground(new Color(0, 0, 40));
        viewPanel.setLayout(new AbsoluteLayout());
        viewPanel.setBorder(new TitledBorder (
                new EtchedBorder (Color.cyan, new Color(0, 0, 40)),
                "Progressing ...",
                2,
                2,
                new Font(c.getFont().getFontName(), Font.PLAIN, 12),
                Color.magenta));
        
        c.add(viewPanel, new AbsoluteConstraints(175, 0, 517, 492));

        dbaPanel = new JPanel();
        dbaPanel.setBackground(new Color(0, 0, 40));
        dbaPanel.setLayout(new AbsoluteLayout());
        dbaPanel.setBorder(new TitledBorder (
                new EtchedBorder (Color.cyan, new Color(0, 0, 40)),
                "DBA INFORMATION",
                2,
                2,
                new Font(c.getFont().getFontName(), Font.PLAIN, 12),
                Color.magenta));
        
        viewPanel.add(dbaPanel, new AbsoluteConstraints(75, 50, 370, 100));

        dbaName = new JLabel("DBA USERNAME : ");
        dbaName.setHorizontalAlignment(SwingConstants.RIGHT);
        dbaName.setForeground (new Color( 120, 120, 153));
        dbaPanel.add ( dbaName, new AbsoluteConstraints(15, 30, 150, 20));

        dbaNameField = new JTextField();
        dbaNameField.setForeground(Color.blue);
        dbaNameField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        dbaNameField.setCaretColor ( new Color ( 0, 204, 102 ) );
        dbaPanel.add ( dbaNameField, new AbsoluteConstraints(170, 30, 150, 20));

        dbaPassword = new JLabel("DBA PASSOWRD : ");
        dbaPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        dbaPassword.setForeground (new Color( 120, 120, 153));
        dbaPanel.add ( dbaPassword, new AbsoluteConstraints(15, 55, 150, 20));

        dbaPasswordField = new JPasswordField();
        dbaPasswordField.setForeground(Color.blue);
        dbaPasswordField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        dbaPasswordField.setCaretColor ( new Color ( 0, 204, 102 ) );
        dbaPanel.add ( dbaPasswordField, new AbsoluteConstraints(170, 55, 150, 20));


        dbPanel = new JPanel();
        dbPanel.setBackground(new Color(0, 0, 40));
        dbPanel.setLayout(new AbsoluteLayout());
        dbPanel.setBorder(new TitledBorder (
                new EtchedBorder (Color.cyan, new Color(0, 0, 40)),
                "DATABASE CREATION",
                2,
                2,
                new Font(c.getFont().getFontName(), Font.PLAIN, 12),
                Color.magenta));
        
        viewPanel.add(dbPanel, new AbsoluteConstraints(75, 150, 370, 120));

        dbName = new JLabel("DATABASE NAME : ");
        dbName.setHorizontalAlignment(SwingConstants.RIGHT);
        dbName.setForeground (new Color( 120, 120, 153));
        dbPanel.add ( dbName, new AbsoluteConstraints(15, 30, 150, 20));

        dbNameField = new JTextField();
        dbNameField.setForeground(Color.blue);
        dbNameField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        dbNameField.setCaretColor ( new Color ( 0, 204, 102 ) );
        dbPanel.add ( dbNameField, new AbsoluteConstraints(170, 30, 150, 20));

        dbPassword = new JLabel("DATABASE PASSWORD : ");
        dbPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        dbPassword.setForeground (new Color( 120, 120, 153));
        dbPanel.add ( dbPassword, new AbsoluteConstraints(15, 55, 150, 20));

        dbPasswordField = new JPasswordField();
        dbPasswordField.setForeground(Color.blue);
        dbPasswordField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        dbPasswordField.setCaretColor ( new Color ( 0, 204, 102 ) );
        dbPanel.add ( dbPasswordField, new AbsoluteConstraints(170, 55, 150, 20));

        dbCreat = new JButton("CREATE DATABASE") ;
        dbCreat.setBackground (Color.cyan);
        dbCreat.setForeground(Color.black);
        dbCreat.setBorder(new BevelBorder(0));
        dbCreat.setMnemonic('D');
        dbCreat.setEnabled (false);
        dbPanel.add ( dbCreat, new AbsoluteConstraints(20, 80, 300, 20));


        univPanel = new JPanel();
        univPanel.setBackground(new Color(0, 0, 40));
        univPanel.setLayout(new AbsoluteLayout());
        univPanel.setBorder( new TitledBorder (
                new EtchedBorder (Color.cyan, new Color(0, 0, 40)),
                "REGISTERING SOFTWARE TO...",
                2,
                2,
                new Font(c.getFont().getFontName(), Font.PLAIN, 12),
                Color.magenta));

        viewPanel.add(univPanel, new AbsoluteConstraints(75, 300, 370, 150));

        univName = new JLabel("UNIVERSITY NAME : ");
        univName.setHorizontalAlignment(SwingConstants.RIGHT);
        univName.setForeground (new Color( 120, 120, 153));
        univPanel.add ( univName, new AbsoluteConstraints(15, 30, 150, 20));

        univNameField = new JTextField();
        univNameField.setForeground(Color.blue);
        univNameField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        univNameField.setCaretColor ( new Color ( 0, 204, 102 ) );
        univPanel.add ( univNameField, new AbsoluteConstraints(170, 30, 150, 20));

        univAddress = new JLabel("ADDRESS DETAILS : ");
        univAddress.setHorizontalAlignment(SwingConstants.RIGHT);
        univAddress.setForeground (new Color( 120, 120, 153));
        univPanel.add ( univAddress, new AbsoluteConstraints(15, 55, 150, 20));

        univAddressField = new JTextField();
        univAddressField.setForeground(Color.blue);
        univAddressField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        univAddressField.setCaretColor ( new Color ( 0, 204, 102 ) );
        univPanel.add ( univAddressField, new AbsoluteConstraints(170, 55, 150, 20));

        univEmail = new JLabel("E-MAIL : ");
        univEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        univEmail.setForeground (new Color( 120, 120, 153));
        univPanel.add ( univEmail, new AbsoluteConstraints(15, 80, 150, 20));

        univEmailField = new JTextField();
        univEmailField.setForeground(Color.blue);
        univEmailField.setBorder ( new MatteBorder (1, 1, 1, 1, new Color(0,0,40) ) );
        univEmailField.setCaretColor ( new Color ( 0, 204, 102 ) );
        univPanel.add ( univEmailField, new AbsoluteConstraints(170, 80, 150, 20));

        registerSoftware = new JButton("REGISTER") ;
        registerSoftware.setBackground (Color.cyan);
        registerSoftware.setForeground(Color.black);
        registerSoftware.setBorder(new BevelBorder(0));
        registerSoftware.setMnemonic('R');
        registerSoftware.setEnabled (false);
        univPanel.add ( registerSoftware, new AbsoluteConstraints(20, 105, 150, 20));

        exitInstallation = new JButton("EXIT SETUP") ;
        exitInstallation.setBackground (Color.cyan);
        exitInstallation.setForeground(Color.black);
        exitInstallation.setBorder(new BevelBorder(0));
        exitInstallation.setMnemonic('X');
        exitInstallation.setEnabled (false);
        univPanel.add ( exitInstallation, new AbsoluteConstraints(170, 105, 150, 20));

    }

    private void checkSystemConfiguration() {
        if (System.getProperty("java.runtime.version").startsWith("1.6"))
            setDBAInfo();
        else 
            JOptionPane.showMessageDialog(null,
                    "required Java RunTime Environment 1.6 or higher");
    }

    private void setResolution() {
            ScreenResolution.getResolution();
    }

    private void setDBAInfo() {
        //String dbaName="system",dbaPassword="manager";
        //String dbName="ABC",dbPassword="ABC";
        if (DBARegistration.getDBAInfo(dbaNameField.getText(),
                new String(dbaPasswordField.getPassword())) 
                && DBARegistration.getUserInfo(dbNameField.getText(), 
                new String(dbPasswordField.getPassword()))) {
            
            DBARegistration.importDB(dbNameField.getText(),
                    new String(dbPasswordField.getPassword()));
            
        }
    }

    public static void main(String a[]) {
            new RegisterLib().setVisible(true);
    }
}