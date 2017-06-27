package com.dayuan.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.Order;

@MapperScan
public interface OrderMapper {

	List<Order> selectOrder(Map<String, Object> params);

	Order selectOrderById(Map<String, Object> params);
	
	Long insertOrder(Order order);
}
