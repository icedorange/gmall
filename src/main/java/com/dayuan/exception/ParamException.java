package com.dayuan.exception;

import com.dayuan.constant.ConstantCode;

/**
 * 参数异常
 * 
 * @author elk
 *
 */
public class ParamException extends Exception {

	private static final long serialVersionUID = 1L;
	private ConstantCode constantCode;

	public ParamException() {
		super();
	}

	public ParamException(String message) {
		super(message);
	}

	public ParamException(ConstantCode constantCode) {
		this.constantCode = constantCode;
	}

	public ParamException(ConstantCode constantCode, String message) {
		super(message);
		this.constantCode = constantCode;
	}

	public ConstantCode getConstantCode() {
		return constantCode;
	}
}
