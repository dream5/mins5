package com.mins5.share.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author chenry
 * @since 2014年6月10日
 */
public class MD5 {
	/**
	 * MD5转换成16进制字符串需要的基础数据。
	 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 将字节数组转换成16进制字符串。
	 * 
	 * @param b byte[] 字节数组
	 * @return String 16进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将一个字节转换成16进制字符串。
	 * 
	 * @param b byte 字节
	 * @return String 16进制字符串
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 根据MD5算法转换字符串
	 * 
	 * @param oriStr String 要转换的字符串
	 * @throws NoSuchAlgorithmException 函数抛出的错误，表示不支持该种算法。
	 * @return String 转换结果字符串
	 */
	public String encode(String oriStr) {
		String tarStr = null;
		try {
			MessageDigest md = null;
			md = MessageDigest.getInstance("MD5");
			tarStr = byteArrayToHexString(md.digest(oriStr.getBytes()));
		} catch (NoSuchAlgorithmException nsae) {
		}
		return tarStr;
	}

}
