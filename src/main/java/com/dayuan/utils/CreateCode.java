package com.dayuan.utils;
/**
 * 生成六位随机数，作为验证码
 * @author elk
 *
 */
public class CreateCode {
	public static int code() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}
}
