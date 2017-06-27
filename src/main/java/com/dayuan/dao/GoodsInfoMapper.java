package com.dayuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.dayuan.bean.GoodsInfo;

@MapperScan
public interface GoodsInfoMapper {
	/* @Param("product")String product */
	List<GoodsInfo> selectGoods(GoodsInfo goodsInfo);

	GoodsInfo selectGoodsDetails(@Param("id") Long id);
}
