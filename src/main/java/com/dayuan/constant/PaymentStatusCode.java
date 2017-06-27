package com.dayuan.constant;

public enum PaymentStatusCode {
	// 待付款
	PAID(2001),
	// 待发货
	DELIVERED(2002),
	// 待收货
	TRADE_SUCCESS(2003),
	// 待评价
	TRADE_FAIL(2004);
	;

	private int value;

	PaymentStatusCode(int value) {
			this.value = value;
		}

	public int getValue() {
		return value;
	}

}
