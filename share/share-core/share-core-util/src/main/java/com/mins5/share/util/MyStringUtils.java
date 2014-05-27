package com.mins5.share.util;

/**
 * @author zhoutian
 * @since 2014-2-27
 */
public class MyStringUtils {

	/**
	 * 检查目标字符串是否是null或者空格
	 * 
	 * @param str 目标字符串
	 * @return true or false
	 * @since 2014-2-27
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 去除字符串前后空格
	 * 
	 * @param str 目标字符串
	 * @return 去除空格后的字符串
	 * @since 2014-2-27
	 */
	public static String trimBlank(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 转换空字符串为null，并去掉字符串前后空格
	 * 
	 * @param str 目标字符串
	 * @return null或者"去除空格的字符串"
	 * @since 2014-2-27
	 */
	public static String parseNull(String str) {
		str = trimBlank(str);
		if (str == null || str.length() == 0) {
			return null;
		}
		return str;
	}

	/**
	 * 转换null为""，并去掉字符串前后空格
	 * 
	 * @param str 目标字符串
	 * @return ""或者"去除空格的字符串"
	 * @since 2014-2-27
	 */
	public static String parseEmpty(String str) {
		str = trimBlank(str);
		if (str == null || str.length() == 0) {
			return "";
		}
		return str;
	}
}
