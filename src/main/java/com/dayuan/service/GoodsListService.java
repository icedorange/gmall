package com.dayuan.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dayuan.bean.GoodsInfo;
import com.dayuan.dao.GoodsInfoMapper;

@Service
public class GoodsListService {
	@Resource
	private GoodsInfoMapper goodsInfoMapper;

	public List<GoodsInfo> selectGoods(GoodsInfo goodsInfo) {
		List<GoodsInfo> goods = goodsInfoMapper.selectGoods(goodsInfo);
		return goods;
	}

	public GoodsInfo selectGoodsDetails(Long id) {
		return goodsInfoMapper.selectGoodsDetails(id);
	}
}
