package com.dayuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.OrderDetails;

@MapperScan
public interface OrderDetailsMapper {
	/**
	 * 查询一个订单项所有商品
	 * @param oid
	 * @return
	 */
	List<OrderDetails> selectOrderDetails(@Param("oid") Long oid);
	
	int insertOrderDetails(OrderDetails orderDetails );
}
