package com.dayuan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dayuan.service.OrderService;
import com.dayuan.service.RedisService;

@Component
public class Handler {
	@Resource
	private RedisService redisService;
	@Resource
	private OrderService orderService;

	public void getOrder() {
		for (;;) {
			String order = redisService.getOrder();
			System.out.println(order);
			if (order == null) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (order != null) {
				String[] orderId = order.split("\\$");
				System.out.println(orderId[0]);
				orderService.updateOrder(Long.valueOf(orderId[0]));
			}
		}
	}
}
