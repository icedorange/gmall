package com.dayuan.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.UserCart;

@MapperScan
public interface UserCartMapper {
	
	int insertUserCart(@Param("uid")Long uid);
	
	UserCart selectUserCart(@Param("uid")Long uid);
}

