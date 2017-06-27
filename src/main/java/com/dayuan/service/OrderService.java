package com.dayuan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dayuan.bean.GoodsInfo;
import com.dayuan.bean.Order;
import com.dayuan.bean.OrderDetails;
import com.dayuan.bean.Stock;
import com.dayuan.dao.GoodsInfoMapper;
import com.dayuan.dao.OrderDetailsMapper;
import com.dayuan.dao.OrderMapper;
import com.dayuan.dao.StockMapper;
import com.dayuan.utils.DateUtil;
import com.dayuan.utils.ParamException;

@Service
@Transactional
public class OrderService {
	@Resource
	private StockMapper stockMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderDetailsMapper orderDetailsMapper;
	@Resource
	private GoodsInfoMapper goodsInfoMapper;

	// 查询所有订单
	public List<Order> selectOrder(Integer transactionStatus, Long uid) {
		Map<String, Object> params = new HashMap<>();
		if (transactionStatus == 2001 || transactionStatus == 2002 || transactionStatus == 2003
				|| transactionStatus == 2004) {
			params.put("transactionStatus", transactionStatus);
		}
		params.put("uid", uid);
		List<Order> order = orderMapper.selectOrder(params);
		return order;
	}

	// 查询某订单详情
	public Order selectOrderById(Long id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		Order order = orderMapper.selectOrderById(params);
		return order;
	}

	// 查询订单项包含多个商品
	public List<OrderDetails> selectOrderDetails(Long oid) {
		List<OrderDetails> orderDetails = orderDetailsMapper.selectOrderDetails(oid);
		return orderDetails;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Long orderConfirm(Long id, Integer number, Order order, Long uid) throws Exception {
		try {
			// 用户id，数量，商品id
			// 商品id查询库存返回库存表id(sql)
			// 库存表id查询库存表添加悲观锁，获得库存数量
			// 查询单价
			// 生成时间
			// 更新库存表商品库存
			// 订单表插入数据，返回主键
			// 订单表插入数据
			Map<String, Object> params = new HashMap<>();
			params.put("gid", id);
			// 通过商品id查询库存，判断对象是否为null，null为库存不足
			Stock stock = stockMapper.selectStock(params);
			if (stock == null) {
				throw new ParamException("库存不足");
			}
			params.remove("gid");
			// 通过库存表主键查询添加悲观锁
			params.put("id", id);
			stock = stockMapper.selectStock(params);
			// 获得商品库存
			Integer stockNum = stock.getNumber();
			// 判断数量,转成基本类型
			if (stockNum.intValue() < number.intValue()) {
				throw new ParamException("库存不足");
			}
			// 更新商品库存，（判断影响行数）
			params.put("number", stockNum - number);
			stockMapper.updateStock(params);
			GoodsInfo goodsInfo = goodsInfoMapper.selectGoodsDetails(id);
			// 插入订单表
			order.setSum(number * goodsInfo.getPromotionPrice());
			order.setOrdersTime(DateUtil.getNowDate());
			order.setUid(uid);
			orderMapper.insertOrder(order);
			
			// 插入订单详情表
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setGid(id);
			orderDetails.setNumber(number);
			orderDetails.setOid(order.getId());
			orderDetails.setPrice(goodsInfo.getPromotionPrice());
			orderDetailsMapper.insertOrderDetails(orderDetails);
			return order.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("网络不稳定，请稍后再试");
		}

	}
}
