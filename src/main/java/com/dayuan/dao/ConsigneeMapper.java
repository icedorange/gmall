package com.dayuan.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.Consignee;

@MapperScan
public interface ConsigneeMapper {
	
	List<Consignee> selectConsignee(Consignee consignee);

	int updateConsignee(Consignee consignee);

	int insertConsignee(Consignee consignee);

	int deleteConsignee(Consignee consignee);
}
