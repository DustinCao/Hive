package com.infobird.hive.HiveDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.infobird.utils.HiveHelper;

/** 
 * Hive的JavaApi 
 *  
 * 启动hive的远程服务接口命令行执行：hive --service hiveserver >/dev/null 2>/dev/null & 
 *  hive --service hiveserver -p 10008 若端口占用换个端口
 *  
 *  
 */  

public class HiveBase {

	public static ResultSet executeQuery(String sql, Statement stmt) {
		
		ResultSet res = null;
		
		try {
			res = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return res;
	}
	
	public static int executeUpdate(String sql) {
		int count = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = HiveHelper.getConnection();
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HiveHelper.close(conn, stmt);
		}
		
		return count;
	}
	
	
}
