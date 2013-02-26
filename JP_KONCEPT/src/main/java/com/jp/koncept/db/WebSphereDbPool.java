/*
*
* All rights reserved.
*/
package com.jp.koncept.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * WebSphere Database Pool Utility
 * Note: if a connection is obtained using this class then it must be closed as soon as it is no longer required.
 */
public class WebSphereDbPool
{
	private static Logger log = Logger.getLogger(WebSphereDbPool.class.getName());

    private static DataSource datasource = null;

    /**
     * WebSphereDbPool constructor
     */
    public WebSphereDbPool()
    {
        try
        {
            Context ctx = new InitialContext();
            datasource = (DataSource) ctx.lookup("java:comp/env/AS_Datasource_Ref");
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Failed to connect to datasource java:comp/env/AS_Datasource_Ref", ex);
        }
    }

    /**
     * Obtain a connections, always close the connection manually when done with it.
     * @return java.sql.Connection
     * @exception SQLException
     */
    public Connection getConnection() throws SQLException
    {
        return datasource.getConnection();
    }
}
