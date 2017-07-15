package com.dayuan.dto;

import java.util.List;

public class OrderDTO {
	private Integer payType;
	private Integer consigneeId;
	private List<GoodsDTO> goodsList;
	private Integer sum;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}

	public Integer getSum() {
		return sum;
	}

	public List<GoodsDTO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsDTO> goodsList) {
		this.goodsList = goodsList;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}
}
