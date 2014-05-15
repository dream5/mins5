package com.mins5.share.ip.util;

import java.util.Enumeration;

/**
 * 解锁日志进程
 * 
 * @author zhanglin
 * @since 2014年5月15日
 */
public class UnlockLogProcess extends Thread {

	public void run() {
		while (true) {
			for (Enumeration e = AccessControl.shortMonitoringDeny.keys(); e.hasMoreElements();) {
				String ip = (String) e.nextElement();
				long now = System.currentTimeMillis();
				long denyLockTime = 0L;
				if (AccessControl.shortMonitoringDeny.containsKey(ip)) {
					denyLockTime = Long.parseLong((String) AccessControl.shortMonitoringDeny.get(ip));
				}
				if (now > denyLockTime) {
					AccessControl.shortMonitoringDeny.remove(ip);
					AccessControl.deny_log.debug(" [" + ip + "] 被自动解锁");
				}
			}
			try {
				sleep(AccessControl.sleepTime * 1000L);
			} catch (Exception e) {
			}
		}
	}

}
