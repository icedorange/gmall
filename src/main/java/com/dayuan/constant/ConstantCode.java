package com.dayuan.constant;

public enum ConstantCode {
	// 操作失败
	FAIL(0),
	// 操作成功
	SUCCESS(1),
	// 参数为空
	PARAM_EMPTY(1001),
	// 验证码不正确
	CODE_ERROR(1002);
	;

	private int value;

	ConstantCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
