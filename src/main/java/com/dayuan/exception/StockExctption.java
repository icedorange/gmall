package com.dayuan.exception;

import com.dayuan.constant.ConstantCode;

/**
 * 库存异常
 * 
 * @author elk
 *
 */
public class StockExctption extends Exception {

	private static final long serialVersionUID = 1L;

	private ConstantCode constantCode;

	public StockExctption() {
		super();
	}

	public StockExctption(ConstantCode constantCode) {
		this.constantCode = constantCode;
	}

	public StockExctption(ConstantCode constantCode, String message) {
		super(message);
		this.constantCode = constantCode;
	}

	public ConstantCode getConstantCode() {
		return constantCode;
	}

}
