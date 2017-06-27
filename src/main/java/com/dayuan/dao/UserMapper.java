package com.dayuan.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.User;

@MapperScan
public interface UserMapper {
	/**
	 * 根据用户ID查询信息
	 * @param id
	 * @return
	 */
	User selectUserById(Integer id);
	/**
	 * 查询用户信息
	 * @param user
	 * @return
	 */
	User selectUserByParam(User user);
	/**
	 * 通过邮箱注册
	 * @param user
	 * @return
	 */
	int addUserByEmail(User user);
	/**
	 * 通过手机号注册
	 * @param user
	 * @return
	 */
	int addUserByMobile(User user);
	/**
	 * 修改密码或用户名或邮箱或手机号
	 * @param user
	 * @return
	 */
	int updateUser(User user); 
}

