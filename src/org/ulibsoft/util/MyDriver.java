/*
 * MyDriver.java
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

package org.ulibsoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author  Deevan & Pavan
 */
public final class MyDriver {
    
    private static Connection con;
    private static Properties dbprops;

    private static String dbDriver;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    
    public static Connection getConnection() throws Exception {
        if (con != null && !con.isClosed()) {
            return con;
        }   
        if (dbprops == null) {
            loadDBProperties();
        }

        Class.forName(dbDriver);
        con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        con.setAutoCommit(false);

        return con;
    }

    private static void loadDBProperties() {
        dbprops = LoadProperties.readProperties();
        dbDriver = dbprops.getProperty("driver");
        dbUrl = dbprops.getProperty("url");
        dbUser = dbprops.getProperty("user");
        dbPassword = dbprops.getProperty("password");

        //Class.forName("oracle.jdbc.driver.OracleDriver");
        //con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:DEEVAN","GATESLIBRARY","FUTURE");
    }
    
   public static Connection getSystemConnection(String user, String pass)
           throws Exception {
        if (con != null && !con.isClosed()) {
            return con;
        }
        if (dbprops == null) {
            loadDBProperties();
        }

        Class.forName(dbDriver);
        con = DriverManager.getConnection(dbUrl, user, pass);

        return con;
   }
}