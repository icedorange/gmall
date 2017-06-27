package com.dayuan.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.Category;

@MapperScan
public interface CategoryMapper {
	/**
	 * 根据pid查询
	 * @param pid
	 * @return
	 */
	List<Category> selectCategoryByPid(Integer pid);
	
}

