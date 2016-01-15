package com.infobird.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class HiveHelper {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";  
    private static String url = "jdbc:hive://mcmaster:10008/default";  
    private static String user = "";  
    private static String password = "";  
    private static final Logger log = Logger.getLogger("com.infobird.utils");  
    
    public static Connection getConnection() {
        Connection conn = null;  
        
            try {
            	url = PropertiesUtil.getKeyValue("HIVE_URL");
            	user = PropertiesUtil.getKeyValue("HIVE_USER");
            	password = PropertiesUtil.getKeyValue("HIVE_PASSWORD");
            	System.out.println("url:" + url + "user:" + user + "password:" + password);
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				log.debug("[HiveHelper:] [getConnection:] [ClassNotFoundException:] [找不到类]");
				e.printStackTrace();
			} catch (SQLException e) {
				log.debug("[HiveHelper:] [getConnection:] [SQLException:]");
				e.printStackTrace();
			}  

        return conn;
    }
    
    public static void close(Connection conn, Statement stmt) {
    	
    		try {
    			if(conn != null) {
    				conn.close();
    				conn = null;
    			}
    			
    			if(stmt != null) {
    				stmt.close();
    				stmt = null;
    			}
			} catch (SQLException e) {
				log.debug("[HiveHelper:] [close:] [SQLException:]");
				e.printStackTrace();
			}
    }
    
    public static void close(Connection conn, PreparedStatement ps, ResultSet res) {
    	
		try {
			if(res != null) {
				res.close();
				res = null;
			}
			
			if(ps != null) {
				ps.close();
				ps = null;
			}
			

			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			log.debug("[HiveHelper:] [close:] [SQLException:]");
			e.printStackTrace();
		}
}
}
