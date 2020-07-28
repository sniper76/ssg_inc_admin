package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.shinsegae.admin.entity.User;

@Component
public class UserDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public User read(Map<String,Object> param){
		return sqlSessionTemplate.selectOne("mybatis.user.read",param);
	}
	
	public void update(Map<String,Object> param){
		 sqlSessionTemplate.update("mybatis.user.update",param);
	}
	
}
