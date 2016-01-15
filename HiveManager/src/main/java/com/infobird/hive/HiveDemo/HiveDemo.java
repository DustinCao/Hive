package com.infobird.hive.HiveDemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveDemo extends HiveBase{

	public static void createTable(String tableName, Statement stmt) throws SQLException {
		String sql = "create table "  + tableName 
				+ " (key string, age int, phone string, province string, city string)  row format delimited fields terminated by '\t'";  
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("createTable:" + res.hashCode());
	}
	
	public static void describeTables(String tableName, Statement stmt) throws SQLException {  
    	
        String sql = "describe " + tableName;  
        System.out.println("Running:" + sql);  
        
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("执行 describe table 运行结果:");  
        
        try {
			while (res.next()) {  
			    System.out.println(res.getString(1) + "\t" + res.getString(2));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
    }
    
	public static void showTables(String tableName, Statement stmt) throws SQLException{
        String sql = "show tables '" + tableName + "'";  
        System.out.println("Running:" + sql);  
        
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("执行 show tables 运行结果:");  
        try {
			if (res.next()) {  
			    System.out.println(res.getString(1));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}
	
	public static void dropTable(String tableName, Statement stmt) throws SQLException {  
    	
        String sql = "drop table " + tableName;  
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("dropTable:" + res.hashCode());   
    } 
    
	public static void countData(String tableName, Statement stmt) throws SQLException {  
        String sql = "select count(1) from " + tableName;  
        System.out.println("Running:" + sql);  
        
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("执行“regular hive query”运行结果:");  
        
        try {
			while (res.next()) {  
			    System.out.println("count ------>" + res.getString(1));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
    }  
  
	public static void selectData(String tableName, Statement stmt) throws SQLException {  
        String sql = "select * from " + tableName;  
        System.out.println("Running:" + sql);  
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("执行 select * query 运行结果:");  
        try {
			while (res.next()) {  
			    System.out.println(res.getString(1) + "\t" + res.getInt(2) + "\t" + res.getString(3) 
			    		+ "\t" + res.getString(4) + "\t" + res.getString(5));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  
    }  
  
	public static void loadData(String tableName, Statement stmt) throws SQLException {  
        String filepath = "/data1/testdata";  
        String sql = "load data local inpath '" + filepath + "' into table "  
                + tableName;  
        System.out.println("Running:" + sql);  
        ResultSet res = stmt.executeQuery(sql);  
        System.out.println("loadData:" + res.hashCode());
    } 
	 

    
    
}
