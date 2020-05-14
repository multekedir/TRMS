package com.revature.utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static com.revature.utility.LoggerSingleton.getLogger;


public class ConnectionUtil {
    //singleton for returning a single database connection
    private static ConnectionUtil cu = null;
    private static Properties prop;

    private ConnectionUtil() {
        prop = new Properties();
        try {
            // using the class loader to get our properties file
            // then we don't have to rely on the file system
            InputStream dbProperties = ConnectionUtil.class.getClassLoader().
                    getResourceAsStream("SQL/database.properties");
            getLogger(ConnectionUtil.class).info("Loaded Database Properties ");
            prop.load(dbProperties);
            getLogger(ConnectionUtil.class).debug("dbProperties: " + prop.entrySet());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionUtil getConnectionUtil() {
        if (cu == null)
            getLogger(ConnectionUtil.class).info("Creating new connection util");
        cu = new ConnectionUtil();
        return cu;
    }


    public Connection getConnection() {
        Connection conn = null;
        try {
            // registering our database driver class
            Class.forName(prop.getProperty("drv"));
            conn = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("usr"),
                    prop.getProperty("psw")
            );
            getLogger(ConnectionUtil.class).info("Created new connection");
        } catch (Exception e) {
            getLogger(ConnectionUtil.class).error("Connection failed");
            e.printStackTrace();
        }
        return conn;
    }
}
