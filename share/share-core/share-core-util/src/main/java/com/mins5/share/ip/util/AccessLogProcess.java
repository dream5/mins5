package com.mins5.share.ip.util;

import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 访问日志进程
 * 
 * @author zhanglin
 * @since 2014年5月15日
 */
public class AccessLogProcess extends Thread {

	private static final Log log = LogFactory.getLog(AccessLogProcess.class);

	@SuppressWarnings("rawtypes")
	public void run() {
		while (true) {
			Enumeration e = AccessControl.longMonitoringAccess.keys();
			while (e.hasMoreElements()) {
				String ip = (String) e.nextElement();
				int accessTimes = Integer.parseInt((String) AccessControl.longMonitoringAccess.get(ip));
				log.debug(" [" + ip + "] 在 " + AccessControl.longMonitoringTime + " 秒内，访问 [" + accessTimes + "] 次");
			}
			AccessControl.longMonitoringAccess.clear();
			try {
				sleep(AccessControl.longMonitoringTime * 1000L);
			} catch (Exception ex) {

			}
		}
	}
}
