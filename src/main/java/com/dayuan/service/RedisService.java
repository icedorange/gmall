package com.dayuan.service;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisService {

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> orderQueue;

	public String getOrder() {
		String key = "dayuanit:pay:order";
		return orderQueue.rightPop(key);
	}
}
