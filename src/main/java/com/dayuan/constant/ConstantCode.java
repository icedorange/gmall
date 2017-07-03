package com.dayuan.constant;

public enum ConstantCode implements Base {
	FAIL(0, "操作失败"), 
	SUCCESS(1, "操作成功"), 
	PARAM_EMPTY(1001, "参数错误"), 
	CODE_ERROR(1002, "验证码错误"),
	STOCK_LOW(3001, "库存不足");

	private int code;
	private String msg;

	ConstantCode(int code, String msg) {
		this.setCode(code);
		this.setMsg(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String printMsg() {

		return code + "," + msg;
	}

}
