/**
 * 
 */
package com.mins5.share.ip.util;

import java.util.Hashtable;
import java.util.logging.Logger;

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

	public static long time3 = 10L;

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

}
