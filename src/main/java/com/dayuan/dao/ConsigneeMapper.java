package com.dayuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.Consignee;

@MapperScan
public interface ConsigneeMapper {
	
	List<Consignee> selectConsignee(Consignee consignee);
	
	Consignee selectConsigneeById(@Param("id")Long id);
	
	int updateConsignee(Consignee consignee);

	int insertConsignee(Consignee consignee);

	int deleteConsignee(Consignee consignee);
}
