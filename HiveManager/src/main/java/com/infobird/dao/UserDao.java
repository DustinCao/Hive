package com.infobird.dao;

import java.util.List;

import com.infobird.base.BaseDao;
import com.infobird.entity.UserEntity;

public class UserDao extends BaseDao<UserEntity>{

	private String tableName = "hivetest02";
	public List<UserEntity> getUserByName(String name) {
		String sql = "select * from " + tableName +  " where key = ?";
		
		List<UserEntity> users = executeQuery(sql, new Object[]{name}, UserEntity.class);
		
		System.out.println(users);
/*		for (UserEntity userEntity : users) {
			System.out.println(users);
		}*/
		
		return users;
	}
}
