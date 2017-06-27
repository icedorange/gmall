package com.dayuan.utils;

import java.security.MessageDigest;

public class MD5Util {
	public static final String MD5(String pwd) {
		char[] md5String = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

		try {
			byte[] e = pwd.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(e);
			byte[] md = mdInst.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;

			for (int i = 0; i < j; ++i) {
				byte byte0 = md[i];
				str[k++] = md5String[byte0 >>> 4 & 15];
				str[k++] = md5String[byte0 & 15];
			}

			return new String(str);
		} catch (Exception arg9) {
			return null;
		}
	}
}
