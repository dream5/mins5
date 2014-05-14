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

}
