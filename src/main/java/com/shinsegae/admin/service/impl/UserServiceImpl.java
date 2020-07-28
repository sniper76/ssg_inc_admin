package com.shinsegae.admin.service.impl;


import com.shinsegae.admin.dao.UserDao;
import com.shinsegae.admin.entity.User;
import com.shinsegae.admin.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User get(Map<String, Object> param) {
		return userDao.read(param);
	}

	@Override
	public void update(User user) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userPassword", user.getUserPassword());
		param.put("newPassword1", user.getNewPassword1());
		userDao.update(param);
		
	}

	
}
