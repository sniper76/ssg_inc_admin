package com.shinsegae.admin.service;

import java.util.Map;

import com.shinsegae.admin.entity.User;

public interface IUserService {

	User get(Map<String, Object> param);

	void update(User user);

}