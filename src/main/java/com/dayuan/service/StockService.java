package com.dayuan.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dayuan.bean.Stock;
import com.dayuan.dao.StockMapper;
@Service
public class StockService {
	@Resource
	private StockMapper stockMapper;

	public Stock selectStock(Map<String, Object> params) {
		return stockMapper.selectStock(params);
	}

	public int updateStock(Map<String, Object> params) {
		return stockMapper.updateStock(params);
	}
}
