package com.dayuan.dao;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.Stock;

@MapperScan
public interface StockMapper {
	Stock selectStock(Map<String, Object> params);
	
	int updateStock(Map<String, Object> params);
}

