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
import com.dayuan.constant.ConstantCode;
import com.dayuan.constant.PaymentStatusCode;
import com.dayuan.dao.GoodsInfoMapper;
import com.dayuan.dao.OrderDetailsMapper;
import com.dayuan.dao.OrderMapper;
import com.dayuan.dao.StockMapper;
import com.dayuan.dto.GoodsDTO;
import com.dayuan.dto.OrderDTO;
import com.dayuan.exception.StockExctption;
import com.dayuan.utils.DateUtil;
import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.service.PayService;

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
	@Resource
	private PayService payService;

	// 修改订单支付状态(待付款→已付款)
	public void updateOrder(Long orderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", orderId);
		Order order = orderMapper.selectOrderById(params);
		if (order == null) {

		}
		if (order.getTransactionStatus() == PaymentStatusCode.PAID.getValue()) {
			order.setTransactionStatus(PaymentStatusCode.DELIVERED.getValue());
			orderMapper.updateTransactionStatus(order);
		} else {
			
		}
	}

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
	public Map<String, Object> orderConfirm(OrderDTO orderDTO, Long id) throws Exception {
		// 用户id，数量，商品id
		// 商品id查询库存返回库存表id
		// 库存表id查询库存表添加悲观锁，获得库存数量
		// 更新库存表商品库存
		// 查询单价
		// 生成时间
		// 订单表插入数据，返回主键
		// 订单表插入数据
		// 调用支付系统(金额，订单id，用户id，支付方式)

		int sum = 0;
		Map<String, Object> params = new HashMap<>();
		List<GoodsDTO> goodsDTO = orderDTO.getGoodsList();
		for (GoodsDTO goods : goodsDTO) {
			params.put("gid", goods.getGoodsid());
			// 通过商品id查询库存，判断对象是否为null，null为库存不足
			Stock stock = stockMapper.selectStock(params);
			if (stock == null) {
				throw new StockExctption(ConstantCode.STOCK_LOW);
			}
			params.remove("gid");
			// 通过库存表主键查询添加悲观锁
			params.put("id", stock.getId());
			stock = stockMapper.selectStock(params);
			// 获得商品库存
			Integer stockNum = stock.getNumber();
			// 判断库存
			if (stockNum.intValue() < goods.getNumber().intValue()) {
				throw new StockExctption(ConstantCode.STOCK_LOW);
			}
			// 更新商品库存，（判断影响行数）
			params.put("number", stockNum - goods.getNumber());
			stockMapper.updateStock(params);
			// 查询单价
			GoodsInfo goodsInfo = goodsInfoMapper.selectGoodsInfo((long) goods.getGoodsid().intValue());
			// 总金额
			sum += goodsInfo.getPromotionPrice() * goods.getNumber();
		}
		//查询收货人地址
		
		// 插入订单表
		Order order = new Order();
		order.setSum(sum);
		order.setOrdersTime(DateUtil.getNowDate());
		order.setUid(id);
		orderMapper.insertOrder(order);
		for (GoodsDTO goods : goodsDTO) {
			// 插入订单详情表
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setGid(id);
			orderDetails.setNumber(goods.getNumber());
			orderDetails.setOid(order.getId());
			// 查询单价
			GoodsInfo goodsInfo = goodsInfoMapper.selectGoodsInfo((long) goods.getGoodsid().intValue());
			orderDetails.setPrice(goodsInfo.getPromotionPrice());
			orderDetailsMapper.insertOrderDetails(orderDetails);
		}
		PayOrder po = new PayOrder();
		po.setAmount(String.valueOf(sum));
		po.setBizId(order.getId().toString());
		po.setUserId(id.intValue());
		po.setPayChannel(orderDTO.getPayType());
		Map<String, Object> porder = payService.addPayOrder(po);
		return porder;

	}
}
