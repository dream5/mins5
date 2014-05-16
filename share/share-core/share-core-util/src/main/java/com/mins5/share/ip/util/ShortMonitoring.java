package com.mins5.share.ip.util;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 短监控线程
 * 
 * @author zhanglin
 * @since 2014年5月15日
 */
public class ShortMonitoring extends Thread {

	private static final Log log = LogFactory.getLog(ShortMonitoring.class);

	private int limitTime = 1;// 限定时间（秒）
	private int maxCount = 10;// 限定时间内访问的最大次数
	private int lockTime = 300;// 锁定时间（秒）

	/** 短时间监控对应的可访问IP集合 **/
	public static Hashtable<String, String> whiteListingForShort = new Hashtable<String, String>();

	public ShortMonitoring(int time, int count, int lock) {
		limitTime = time;
		maxCount = count;
		lockTime = lock;
	}

	public synchronized void monitoringClientIp(String clientIp) {

		int accessCount = 0;
		long now = System.currentTimeMillis();
		if (whiteListingForShort.get(clientIp) != null) {
			accessCount = Integer.parseInt((String) whiteListingForShort.get(clientIp));
		}
		whiteListingForShort.put(clientIp, String.valueOf(accessCount + 1));

	}

}
