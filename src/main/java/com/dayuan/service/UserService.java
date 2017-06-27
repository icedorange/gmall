package com.dayuan.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dayuan.bean.User;
import com.dayuan.dao.UserMapper;

@Service
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;
	
	public User selectUserByParam(User user) {
		return userMapper.selectUserByParam(user);
	}

	public void addUserByMobile(User user) throws Exception {
		try {
			userMapper.addUserByMobile(user);
		} catch (Exception e) {
			logger.error("用户注册失败：" + e.getMessage());
			throw new Exception("网络不稳定，请稍后再试");
		}	
		
	}
	
	public void addUserByEmail(User user) throws Exception {
		try {
			userMapper.addUserByEmail(user);
		} catch (Exception e) {
			logger.error("用户注册失败：" + e.getMessage());
			throw new Exception("网络不稳定，请稍后再试");
		}			
	}

}
