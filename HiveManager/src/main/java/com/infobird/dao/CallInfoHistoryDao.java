package com.infobird.dao;

import java.util.List;

import com.infobird.base.BaseDao;
import com.infobird.entity.CallInfoHistoryEntity;
import com.infobird.utils.PropertiesUtil;


public class CallInfoHistoryDao extends BaseDao<CallInfoHistoryEntity>{

	public List<CallInfoHistoryEntity> getUserByName(String name) {
		
		//String sql = "select * from call_info_history_10000 where ispname_name = ?";
		
		String tableName  = PropertiesUtil.getKeyValue("CALL_INFO_HISTORY_TABLE_NAME");
		String sql = "select * from " + tableName;
		long startTime = System.currentTimeMillis();
		
		//Object[] parameters = (name == null) ? null : new Object[]{name};
		
		List<CallInfoHistoryEntity> callInfos = executeQuery(sql, null, CallInfoHistoryEntity.class);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(callInfos.size() + "cost:" + (endTime - startTime)/1000 + "s");
		
		//System.out.println(users);
		
		//System.out.println(users);
/*		for (UserEntity userEntity : users) {
			System.out.println(users);
		}*/
		
		return callInfos;
	}
}
