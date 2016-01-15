package com.infobird.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infobird.entity.CallInfoHistoryEntity;
import com.infobird.redis.RedisManager;
import com.infobird.utils.PropertiesUtil;

public class TestDao {
	
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		CallInfoHistoryDao callDao = new CallInfoHistoryDao();
		List <CallInfoHistoryEntity> callInfos = callDao.getUserByName(null);
		
		long startTime1 =System.currentTimeMillis();
		System.out.println("查询花费时间：" + (startTime1 - startTime) + "ms");
		
		Map<String, String> maps = new HashMap<String, String>();
		
		for (CallInfoHistoryEntity callInfoHistoryEntity : callInfos) {
			StringBuffer values = new StringBuffer();
			values.append(callInfoHistoryEntity.getCompleteTime());
			values.append(";");
			values.append( callInfoHistoryEntity.getTalkingTime());
			values.append(";");
			values.append( callInfoHistoryEntity.getHuashuId());
			values.append(";");
			values.append( callInfoHistoryEntity.getCity());
			values.append(";");
			values.append( callInfoHistoryEntity.getKeyStamp());
			
/*			String value = callInfoHistoryEntity.getCompleteTime() + ";" + callInfoHistoryEntity.getTalkingTime()
					+ ";" + callInfoHistoryEntity.getHuashuId() + ";" + callInfoHistoryEntity.getAge();*/
			maps.put(callInfoHistoryEntity.getPhoneNo(), values.toString());
		}
		
		long startTime2 = System.currentTimeMillis();
		System.out.println("组装数据花费时间：" + (startTime2 - startTime1) + "ms");
		
		String key = PropertiesUtil.getKeyValue("HASH_TABLE_NAME");
		
		RedisManager.getJedisConnection();
		RedisManager.putHash(key, maps);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("存入redis花费时间:" + (endTime - startTime2) + "ms");
		
		System.out.println("all cost:" + (endTime - startTime) + "ms");
	}
	

	/*public static void main(String[] args) {
		//UserDao udao = new UserDao();
		//udao.getUserByName("Liming");
		
		//CallInfoHistoryDao callDao = new CallInfoHistoryDao();
		//callDao.getUserByName("移动");
		
		long startTime = System.currentTimeMillis();
		
		List<CallInfoHistoryEntity> infos = new ArrayList<CallInfoHistoryEntity>();
		for (int i = 0; i < 10000000; i++ ) {
			long phone = 15600000000l;
			
			CallInfoHistoryEntity entity = new CallInfoHistoryEntity(String.valueOf(phone),
					"沈阳", "1705760", "移动", "2015-03-10 15:37:40.0;2015-04-02 10:25:15.0;2015-04-21 18:22:36.0",
					"4;4;4", "0;1;21", "0;0;0", "0;0;0", "40296;40825;42920", "143;169;148", "0;0;0", "2015-03-11 00:00:00.0",
					"null", "null");
			
			infos.add(entity);
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("cost: " + (endTime - startTime) + "ms");
				
		
		System.out.println("[end:] [size:]" + infos.size());
	}*/
}
