package com.infobird.base;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infobird.utils.HiveHelper;

/** 
 * Hive的JavaApi 
 *  
 * 启动hive的远程服务接口命令行执行：hive --service hiveserver >/dev/null 2>/dev/null & 
 *  hive --service hiveserver -p 10008 若端口占用换个端口
 *  
 *  
 */ 
public class BaseDao <T>{

public List<T> executeQuery(String sql, Object[] args, Class<T> type) {
		
		ResultSet res = null;
		Connection conn = null;
		PreparedStatement ps=null;
		
		List<T> results = new ArrayList<T>();
		
		try {
			conn = HiveHelper.getConnection();
			ps = conn.prepareStatement(sql);
			
			if(args != null && args.length > 0){
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i+1, args[i]);
				}
			}
			
			res =ps.executeQuery();
			int len= res.getMetaData().getColumnCount();
			while(res.next()){
				Object[] columeValues=new Object[len];
				for (int i = 0; i < columeValues.length; i++) {
					columeValues[i]=res.getObject(i+1);
				}

				@SuppressWarnings("unchecked")
				Constructor<T>[] ctrs=(Constructor<T>[]) type.getConstructors();
				
				T object=null;
				
				for (int i = 0; i < ctrs.length; i++) {
					try {
						object= ctrs[i].newInstance(columeValues);
						break;
					} catch (Exception e) {
						continue;
					}
					
				}
				
				if(object!=null) {
					results.add(object);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HiveHelper.close(conn, ps, res);
		}
		
		return results;
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
