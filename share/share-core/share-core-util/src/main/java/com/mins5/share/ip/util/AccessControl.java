/**
 * 
 */
package com.mins5.share.ip.util;

import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.log4j.Logger;

/**
 * <p>
 * 多线程监控访问IP
 * </p>
 * 
 * @author zhanglin
 * @since 2014年5月14日
 */
public class AccessControl extends Thread {

	public static Logger access_log = Logger.getLogger("accesslog");
	public static Logger deny_log = Logger.getLogger("denyIplog");

	public static boolean isStart = false;

	/** 短时间监控对应的可访问IP集合 **/
	public static Hashtable<String, String> shortMonitoringAccess = new Hashtable<String, String>();
	/** 长时间监控对应的可访问IP集合 **/
	public static Hashtable<String, String> longMonitoringAccess = new Hashtable<String, String>();
	/** 短时间监控对应的拒绝访问IP集合 **/
	public static Hashtable<String, String> shortMonitoringDeny = new Hashtable<String, String>();
	/** 长时间监控对应的拒绝访问IP集合 **/
	public static Hashtable<String, String> longMonitoringDeny = new Hashtable<String, String>();

	public static long shortMonitoringTime = 1L;
	public static long shortMonitoringMaxTimes = 10L;
	public static long shortMonitoringLockTime = 300L;

	public static long longMonitoringTime = 1800L;
	public static long longMonitoringMaxTimes = 1000L;
	public static long longMonitoringLockTime = 3000L;

	public static long sleepTime = 10L;

	/**
	 * 初始化
	 * 
	 * @param _shortMonitoringTime 短监控时间
	 * @param _shortMonitoringMaxTimes 短监控最大访问次数
	 * @param _shortMonitoringLockTime 短监控锁定时间
	 * @param _longMonitoringTime 长监控时间
	 * @param _longMonitoringMaxTimes 长监控最大访问次数
	 * @param _longMonitoringLockTime 长监控锁定时间
	 */
	public AccessControl(int _shortMonitoringTime, int _shortMonitoringMaxTimes, int _shortMonitoringLockTime, int _longMonitoringTime,
			int _longMonitoringMaxTimes, int _longMonitoringLockTime) {
		shortMonitoringTime = _shortMonitoringTime;
		shortMonitoringMaxTimes = _shortMonitoringMaxTimes;
		shortMonitoringLockTime = _shortMonitoringLockTime;
		longMonitoringTime = _longMonitoringTime;
		longMonitoringMaxTimes = _longMonitoringMaxTimes;
		longMonitoringLockTime = _longMonitoringLockTime;
	}

	@SuppressWarnings("rawtypes")
	public void run() {
		isStart = true;
		new AccessLogProcess().start();
		new UnlockLogProcess().start();
		while (true) {
			for (Enumeration e = shortMonitoringAccess.keys(); e.hasMoreElements();) {
				String ip = (String) e.nextElement();
				int accessTimes = Integer.parseInt((String) shortMonitoringAccess.get(ip));
				access_log.debug(" [" + ip + "] 在 " + shortMonitoringTime + " 秒内，访问 [" + accessTimes + "] 次");
			}
			shortMonitoringAccess.clear();
			try {
				sleep(sleepTime * 1000L);
			} catch (Exception localException) {

			}
		}
	}

	/**
	 * 设置访问IP到监控集合中
	 * 
	 * @param clientIP
	 * @author zhanglin
	 * @since 2014年5月15日
	 */
	public static synchronized boolean setAccessIp(String clientIP) {
		int accessTimesForShort = 0;
		int accessTimesForLong = 0;
		long now = System.currentTimeMillis();
		long shortClientLockTime = now + shortMonitoringLockTime * 1000L;// 短监控将要锁定的时间
		long longClientLockTime = now + longMonitoringLockTime * 1000L;

		long denyLockTime = 0L;

		if (shortMonitoringAccess.get(clientIP) != null) {
			accessTimesForShort = Integer.parseInt((String) shortMonitoringAccess.get(clientIP));
		}
		shortMonitoringAccess.put(clientIP, String.valueOf(accessTimesForShort + 1));

		if (longMonitoringAccess.get(clientIP) != null) {
			accessTimesForLong = Integer.parseInt((String) longMonitoringAccess.get(clientIP));
		}
		longMonitoringAccess.put(clientIP, String.valueOf(accessTimesForLong + 1));

		// 黑名单操作
		if (shortMonitoringDeny.containsKey(clientIP)) {
			denyLockTime = Long.parseLong((String) shortMonitoringDeny.get(clientIP));
		}

		if ((accessTimesForLong > longMonitoringMaxTimes) && (longClientLockTime > denyLockTime)) {
			shortMonitoringDeny.put(clientIP, String.valueOf(longClientLockTime));
			longMonitoringDeny.put(clientIP, String.valueOf(longClientLockTime));
			deny_log.debug(" [" + clientIP + "] 被自动封锁" + longMonitoringLockTime + "秒； 在 " + longMonitoringTime + " 秒内，访问 " + accessTimesForLong
					+ " 次");
		} else if ((accessTimesForShort > shortMonitoringMaxTimes) && (shortClientLockTime > denyLockTime)) {
			shortMonitoringDeny.put(clientIP, String.valueOf(shortClientLockTime));
			deny_log.debug(" [" + clientIP + "] 被自动封锁" + longMonitoringLockTime + "秒； 在 " + shortMonitoringTime + " 秒内，访问 " + accessTimesForShort
					+ " 次");
		}
		return isAccessibleIp(clientIP);
	}

	/**
	 * 是否是可访问IP
	 * 
	 * @param clientIP
	 * @author zhanglin
	 * @since 2014年5月15日
	 */
	public static boolean isAccessibleIp(String clientIP) {
		return !shortMonitoringDeny.containsKey(clientIP);
	}

	/**
	 * 锁定IP
	 * 
	 * @param clientIP 客户端IP
	 * @param second 秒
	 * @author zhanglin
	 * @since 2014年5月15日
	 */
	public static boolean lockIP(String clientIP, long second) {
		long now = System.currentTimeMillis();
		long clientLockTime = now + second * 1000L;
		long denyLockTime = 0L;
		if (shortMonitoringDeny.containsKey(clientIP)) {
			denyLockTime = Long.parseLong((String) shortMonitoringDeny.get(clientIP));
		}
		if (clientLockTime > denyLockTime) {
			shortMonitoringDeny.put(clientIP, String.valueOf(clientLockTime));
			deny_log.debug(" [" + clientIP + "] 人工封锁 [" + second + "]秒");
		}
		return isAccessibleIp(clientIP);
	}

	public static boolean unlockIPByAdmin(String clientIP) {
		shortMonitoringDeny.remove(clientIP);
		deny_log.debug(" [" + clientIP + "] 被人工解锁");
		return isAccessibleIp(clientIP);
	}

	/**
	 * 用户解锁
	 * 
	 * @param clientIP
	 * @author zhanglin
	 * @since 2014年5月15日
	 */
	public static boolean unlockIPByUser(String clientIP) {
		if (shortMonitoringDeny.containsKey(clientIP)) {
			shortMonitoringAccess.remove(clientIP);
			shortMonitoringDeny.remove(clientIP);
		}
		if (longMonitoringDeny.containsKey(clientIP)) {
			longMonitoringAccess.remove(clientIP);
			longMonitoringDeny.remove(clientIP);
		}
		deny_log.debug(" [" + clientIP + "] 输入正确验证码，解锁");
		return isAccessibleIp(clientIP);
	}

}
