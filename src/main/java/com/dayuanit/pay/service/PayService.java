package com.dayuanit.pay.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.domain.PayType;

@WebService
public interface PayService {
	
	Map<String, Object> addPayOrder(PayOrder payOrder);
	
	List<PayType> listPayType();
	
	String getPayName(int payType);
	
}
