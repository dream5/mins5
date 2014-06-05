package com.mins5.share.web.filter;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 应用管理器
 * 
 * @author chenry
 * @since 2014年5月28日
 */
public class ApplicationManager {
	private static final Logger logger = Logger.getLogger(ApplicationManager.class);

	//(key-value)=(工号-sessionid)
	private static HashMap usermap = new HashMap(100);

	//(key-value)=(sessionid-工号)
	private static HashMap usermap2 = new HashMap(100);

	/**
	 * 判断工号optorsn是否在线
	 * 
	 * @param optorsn 操作员工号
	 * @return true or false
	 */
	public static boolean isOnline(String optorsn) {
		return usermap.containsKey(optorsn);
	}

	/**
	 * 判断操作员是否在线
	 * 
	 * @param session HttpSession
	 * @return true or false
	 */
	public static boolean isOnline(HttpSession session) {
		return usermap.containsValue(session.getId());
	}

	public static String getOptorsn2(HttpSession session) {
		return (String) usermap2.get(session.getId());
	}

	public static void remove2(HttpSession session) {
		usermap2.remove(session.getId());
	}

	/**
	 * 通过工号获取 Remote Host
	 * 
	 * @param optorsn 工号
	 * @return RemoteHost
	 */
	public static String getRemoteHost(String optorsn) {
//        Authorization auth = AuthorizationFactory.getSimpleAuthorization(optorsn);
//        return auth!=null?auth.getRemoteHost():null;
		return null;
	}

	/**
	 * 添加用户
	 * 
	 * @param optorsn 操作员工号
	 * @param sessionID sessionid
	 */
	public static void put(String optorsn, String sessionID) {
//		Authorization auth = AuthorizationFactory.getSimpleAuthorization(optorsn);
//		PermissionsManager.getInstance(auth).getFunctionPermissions();
		usermap.put(optorsn, sessionID);
		usermap2.put(sessionID, optorsn);
		//ApplicationCounter.increase("Z");
		logger.info("在线用户数：" + size());
	}

	/**
	 * @return 在线用户数
	 */
	public static int size() {
		return usermap.size();
	}

}