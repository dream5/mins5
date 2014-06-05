package com.mins5.share.web.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author chenry
 * @since 2014年6月5日
 */
public class WebUtils {

	public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat formatStr = new SimpleDateFormat("yyyyMMdd");

	public static String getParamAndAtribute(HttpServletRequest request, String name) {
		String temp = getParameter(request, name);
		if (temp == null || temp.equals("null") || temp.equals("")) {
			temp = getAtribute(request, name);
		}
		return temp;
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name The name of the parameter you want to get
	 * @return The value of the parameter or a zero-length string if the
	 *         parameter was not found or if the parameter is a zero-length
	 *         string.
	 */
	public static String getAtribute(HttpServletRequest request, String name) {
		String temp = (String) request.getAttribute(name);
		return temp != null ? temp.trim() : "";
	}

	/**
	 * 
	 * 获得指定的参数，如果没有获得则返回默认值。
	 * 
	 * @author yaoq
	 */
	public static Vector getParameters(HttpServletRequest request, String name) {
		Vector temp = new Vector();
		String[] tempArray = request.getParameterValues(name);
		if (tempArray != null && tempArray.length > 0) {
			temp = new Vector(Arrays.asList(tempArray));
		}
		return temp;
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name The name of the parameter you want to get
	 * @return The value of the parameter or a zero-length string if the
	 *         parameter was not found or if the parameter is a zero-length
	 *         string.
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		String temp = request.getParameter(name);
		return temp != null ? temp.trim() : "";
	}

	public static String getParameter(HttpServletRequest request, String name, boolean setAttr) {
		String temp = request.getParameter(name);
		if (setAttr)
			request.setAttribute(name, temp);
		return temp != null ? temp.trim() : "";
	}

	/**
	 * @deprecated
	 * @deprecated 建议使用getParameter(request, name, true)
	 */
	@Deprecated
	public static String getParameter2(HttpServletRequest request, String name) {
		return getParameter(request, name, true);
	}

	/**
	 * Gets a parameter as a boolean.
	 * 
	 * @param request The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean dflt) {
		return getBooleanParameter(request, name, dflt, false);
	}

	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean dflt, boolean setAttr) {
		String temp = request.getParameter(name);
		if (setAttr)
			request.setAttribute(name, temp);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		} else if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return dflt;
		}
	}

	/**
	 * @deprecated
	 * @deprecated 建议使用getBooleanParameter(request, name, dflt, true)
	 */
	@Deprecated
	public static boolean getBooleanParameter2(HttpServletRequest request, String name, boolean dflt) {
		return getBooleanParameter(request, name, dflt, true);
	}

	/**
	 * Gets a parameter as an int.
	 * 
	 * @param request The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name The name of the parameter you want to get
	 * @return The int value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static int getIntParameter(HttpServletRequest request, String name, int dflt) {
		return getIntParameter(request, name, dflt, false);
	}

	public static Integer getIntegerParameter(HttpServletRequest request, String name) {
		String result = WebUtils.getParameter(request, name, false);
		if (result == null || result.equals("")) {
			return null;
		} else {
			return new Integer(result);
		}
	}

	public static int getIntParameter(HttpServletRequest request, String name, int dflt, boolean setAttr) {
		String temp = request.getParameter(name);
		if (setAttr)
			request.setAttribute(name, temp);
		int num = dflt;
		try {
			num = Integer.parseInt(temp);
		} catch (Exception ignored) {
		}
		return num;
	}

	/**
	 * @deprecated
	 * @deprecated 建议使用getIntParameter(request, name, dflt, true)
	 */
	@Deprecated
	public static int getIntParameter2(HttpServletRequest request, String name, int dflt) {
		return getIntParameter(request, name, dflt, true);
	}

	/**
	 * Gets a list of int parameters.
	 * 
	 * @param request The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name The name of the parameter you want to get
	 * @param defaultNum The default value of a parameter, if the parameter
	 *            can't be converted into an int.
	 */
	public static int[] getIntParameters(HttpServletRequest request, String name, int dflt) {
		return getIntParameters(request, name, dflt, false);
	}

	public static int[] getIntParameters(HttpServletRequest request, String name, int dflt, boolean setAttr) {
		String[] paramValues = request.getParameterValues(name);
		if (setAttr)
			request.setAttribute(name, paramValues);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = dflt;
			}
		}
		return values;
	}

	/**
	 * @deprecated
	 * @deprecated 建议使用getIntParameters(request, name, dflt, true)
	 */
	@Deprecated
	public static int[] getIntParameters2(HttpServletRequest request, String name, int dflt) {
		return getIntParameters(request, name, dflt, true);
	}

	/**
	 * Gets a parameter as a long.
	 * 
	 * @param request The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name The name of the parameter you want to get
	 * @return The long value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static long getLongParameter(HttpServletRequest request, String name, long dflt) {
		return getLongParameter(request, name, dflt, false);
	}

	public static long getLongParameter(HttpServletRequest request, String name, long dflt, boolean setAttr) {
		String temp = request.getParameter(name);
		if (setAttr)
			request.setAttribute(name, temp);
		if (temp != null && !temp.equals("")) {
			long num = dflt;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return dflt;
		}
	}

	/**
	 * @deprecated
	 * @deprecated 建议使用getLongParameter(request, name, dflt, true)
	 */
	@Deprecated
	public static long getLongParameter2(HttpServletRequest request, String name, long dflt) {
		return getLongParameter(request, name, dflt, true);
	}

//	public static Date getDateParameter(HttpServletRequest request, String name, Date dflt) {
//		return getDateParameter(request, name, dflt, false);
//	}

//	public static Date getDateParameter(HttpServletRequest request, String name, Date dflt, boolean setAttr) {
//		String temp = request.getParameter(name);
//		if (temp == null || "".equals(temp.trim())) {
//			if (setAttr)
//				request.setAttribute(name, dflt);
//			return dflt;
//		}
//
//		try {
//			Date dt = (Date.valueOf(temp));
//			if (setAttr)
//				request.setAttribute(name, dt);
//			return dt;
//		} catch (Exception e) {
//			throw new SystemException("", "输入项非日期Date类型");
//		}
//	}

	/**
	 * 按照指定的格式生成date，默认为当前日期<br>
	 * yq added <br>
	 * 建议大家都使用util.date,不要使用sql.date。<br>
	 * 好处很多的<br>
	 */
//	public static java.util.Date getUtilDateParameter(HttpServletRequest request, String name, String dateFormat) {
//
//		String temp = request.getParameter(name);
//		if (temp == null || "".equals(temp.trim())) {
//			return null;
//		}
//
//		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
//		try {
//			return sdf.parse(temp);
//		} catch (ParseException e) {
//			throw new SystemException("", "输入项非日期Date类型");
//		}
//
//	}

//	public static Timestamp getTmstmpParameter(HttpServletRequest request, String name, Timestamp dflt) {
//		return getTmstmpParameter(request, name, dflt, false);
//	}

//	public static Timestamp getTmstmpParameter(HttpServletRequest request, String name, Timestamp dflt, boolean setAttr) {
//		String temp = request.getParameter(name);
//		if (temp == null || "".equals(temp.trim())) {
//			if (setAttr)
//				request.setAttribute(name, dflt);
//			return dflt;
//		}
//
//		Timestamp tmstmp;
//		try {
//			tmstmp = Timestamp.valueOf(temp);
//		} catch (Exception e) {
//			try {
//				tmstmp = Timestamp.valueOf(temp + " 00:00:00");
//			} catch (Exception se) {
//				throw new SystemException("", "输入项非日期Timestamp类型");
//			}
//		}
//		if (setAttr)
//			request.setAttribute(name, tmstmp);
//		return tmstmp;
//	}

	/**
	 * Gets a Object List
	 * 
	 * @param request
	 * @param prefix
	 * @param clazz
	 * @return
	 */
//	public static List getBeanList(HttpServletRequest request, String prefix, Class clazz) {
//		return getBeanList(request, prefix, clazz, false);
//	}

	/**
	 * @param setAttr true/false 是/否设置在request的attribute中
	 * @return Gets a Object List
	 */
//	public static List getBeanList(HttpServletRequest request, String prefix, Class clazz, boolean setAttr) {
//		HashMapList mapList = getMapList(request, prefix);
//
//		List objs = new ArrayList(mapList.size());
//		for (int i = 0; i < mapList.size(); i++) {
//			try {
//				Object obj = clazz.newInstance();
//				Map hm = mapList.get(i);
//				// modtp：王立峰专用 2006-10-19
//				if (!"N".equals(hm.get("modtp"))) {
//					//BeanUtils.populate(obj, hm);
//					objs.add(obj);
//				}
//			} catch (Exception e) {
//				logger.error(e);
//			}
//		}
//		if (setAttr)
//			request.setAttribute(prefix, objs);
//
//		return objs;
//	}

	/**
	 * @deprecated 建议使用getBeanList(request, prefix, clazz, true)
	 */
	@Deprecated
//	public static List getBeanList2(HttpServletRequest request, String prefix, Class clazz) {
//		return getBeanList(request, prefix, clazz, true);
//	}
	/**
	 * Gets a Object
	 * 
	 * @param request
	 * @param prefix
	 * @param clazz
	 * @return
	 */
//	public static Object getBean(HttpServletRequest request, String prefix, Class clazz) {
//		return getBean(request, prefix, clazz, false);
//	}
	/**
	 * @param setAttr true/false 是/否设置在request的attribute中
	 * @return Gets a Object List
	 */
//	public static Object getBean(HttpServletRequest request, String prefix, Class clazz, boolean setAttr) {
//		HashMapList mapList = getMapList(request, prefix);
//
//		Object obj = null;
//		try {
//			if (mapList.size() != 0) {
//				obj = clazz.newInstance();
//				BeanUtils.populate(obj, mapList.get(0));
//			}
//		} catch (Exception e) {
//			logger.error(e);
//		}
//
//		if (setAttr)
//			request.setAttribute(prefix, obj);
//		return obj;
//	}
	/**
	 * @deprecated 建议使用getBean(request, prefix, clazz, true)
	 */
//	@Deprecated
//	public static Object getBean2(HttpServletRequest request, String prefix, Class clazz) {
//		return getBean(request, prefix, clazz, true);
//	}
	/**
	 * 检验随机码
	 * 
	 * @param request HttpServletRequest
	 * @return true or false
	 */
//    public static boolean checkRandom(HttpServletRequest request) {
//        String argRandom = request.getParameter("random");
//
//        HttpSession session = request.getSession();
//        String random = (String) session.getAttribute(Constants.RANDOM_TOKEN);
//        session.removeAttribute(Constants.RANDOM_TOKEN);
//
//        return argRandom.equals(random);
//    }
//
//    /**
//     * 获取下载输出流
//     * 
//     * @param response
//     *            HttpServletResponse
//     * @param fileName
//     *            String
//     * @throws SystemException
//     * @return OutputStream
//     */
//    public static OutputStream getDownloadStream(HttpServletResponse response,
//            String fileName) throws SystemException {
//        OutputStream output = null;
//        try {
//            logger.debug("***********" + fileName);
//            response.setContentType("application/x-msdownload");
//            response.setContentType("Content-Transfer-Encoding: binary");
//            response.setContentType("Content-Length:1024");
//            // String url = URLEncoder.encode(fileName);
//            String url = fileName;
//            response.addHeader("Content-Disposition", "attachment; filename="
//                    + url);
//
//            output = response.getOutputStream();
//        } catch (Exception ex) {
//            logger.error("获取下载输出流失败: " + ex);
//            throw new SystemException("", "", ex);
//        }
//
//        return output;
//    }
//
//    /**
//     * 给vo list 填充属性值
//     * 
//     * @param beanList
//     * @param name
//     * @param value
//     * @throws IllegalAccessException
//     * @throws InvocationTargetException
//     */
//    public static void fillBeanList(List beanList, String name, Object value)
//            throws IllegalAccessException, InvocationTargetException {
//        for (int i = 0; i < beanList.size(); i++) {
//            Object bean = beanList.get(i);
//            BeanUtils.setProperty(bean, name, value);
//        }
//    }
//
//    public static HashMapList getMapList(HttpServletRequest request) {
//        // Iterator of parameter names
//        Enumeration names = request.getParameterNames();
//        HashMapList mapList = new HashMapList();
//
//        while (names.hasMoreElements()) {
//            String name = (String) names.nextElement();
//            String stripped = name;
//            int index;
//            if ((index = stripped.indexOf(".")) != -1) {
//                stripped = stripped.substring(index + 1);
//                mapList.put(stripped, request.getParameterValues(name));
//            }
//        }
//
//        return mapList;
//    }
//
//    private static HashMapList getMapList(HttpServletRequest request,
//            String prefix) {
//        // Iterator of parameter names
//        Enumeration names = request.getParameterNames();
//        HashMapList mapList = new HashMapList();
//
//        if (!prefix.endsWith(".")) {
//            prefix = prefix + ".";
//        }
//        while (names.hasMoreElements()) {
//            String name = (String) names.nextElement();
//            String stripped = name;
//            if (prefix != null && stripped.startsWith(prefix)) {
//                stripped = stripped.substring(prefix.length());
//                mapList.put(stripped, request.getParameterValues(name));
//            }
//        }
//
//        return mapList;
//    }
	public static String parseBafString(Object obj, String wrongstr) {
		String str = "";
		try {
			if (obj == null) {
				return "";
			} else {
				// if ( String.valueOf( obj.getClass() ).indexOf("Timestamp")>=0
				// ) {
				if (obj instanceof Timestamp) {
					Timestamp ts = (Timestamp) obj;
					java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
					str = formatter.format(ts);// new java.util.Date()
					return str;
				}
			}

			str = String.valueOf(obj);
			if (wrongstr != null)
				if (str.equals(wrongstr))
					str = "";
			return str;
		} catch (Exception e) {
			return "";
		}
	}

	public static String parseBafString(int i, String wrongstr) {
		try {
			String str = String.valueOf(i);
			if (wrongstr != null)
				if (str.equals(wrongstr))
					str = "";
			return str;
		} catch (Exception e) {
			return "";
		}
	}

	public static String parseBafString(long i, String wrongstr) {
		try {
			String str = String.valueOf(i);
			if (wrongstr != null)
				if (str.equals(wrongstr))
					str = "";
			return str;
		} catch (Exception e) {
			return "";
		}
	}

	public static String parseBafString(Object obj) {
		return parseBafString(obj, null);
	}

	public static String parseBafString(int i) {
		return parseBafString(i, null);
	}

	public static String parseBafString(long i) {
		return parseBafString(i, null);
	}

	public static long parseLong(String str) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String parseString(Object obj) {
		try {
			String str = String.valueOf(obj);
			if (str == null || str.equals("null")) {
				str = "";
			}
			return str;
		} catch (Exception e) {
			return "";
		}
	}

//	public static String getField(String sql) throws DAOException {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList retLst = new ArrayList();
//
//		String restr = "";
//
//		try {
//			connection = DBUtils.getConnection(JNDINames.DS_ZCRM);
//			pstmt = connection.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				restr = rs.getString(1) != null ? rs.getString(1).trim() : "";
//			}
//
//			return restr;
//		} catch (SQLException e) {
//			logger.error("ORA_QUERY", sql, e);
//			throw new DAOException("ORA_QUERY", sql, e);
//		} finally {
//			DBUtils.close(connection, pstmt, rs);
//		}
//	}
//
//	public static String getFields(String sql) throws DAOException {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList retLst = new ArrayList();
//
//		String restr = "";
//
//		try {
//			connection = DBUtils.getConnection(JNDINames.DS_ZCRM);
//			pstmt = connection.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				restr += WebUtils.parseBafString(rs.getString(1)) + ";";
//			}
//
//			return restr;
//		} catch (SQLException e) {
//			logger.error("ORA_QUERY", sql, e);
//			throw new DAOException("ORA_QUERY", sql, e);
//		} finally {
//			DBUtils.close(connection, pstmt, rs);
//		}
//	}

	/**
	 * 获取客户端IP地址( 多级反向代理[Squid]下获取客户端真实IP地址
	 * )[http://www.blogjava.net/Alpha/archive/2006/07/12/57764.html]
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 替换字符串
	 * 
	 * @param from
	 * @param to
	 * @param source
	 * @return
	 */
	public static String str_replace(String from, String to, String source) {
		StringBuffer bf = new StringBuffer("");
		StringTokenizer st = new StringTokenizer(source, from, true);
		while (st.hasMoreTokens()) {
			String tmp = st.nextToken();
			if (tmp.equals(from)) {
				bf.append(to);
			} else {
				bf.append(tmp);
			}
		}
		return bf.toString();
	}

	/**
	 * rql 工具方法
	 * 
	 * @param date
	 * @return
	 */
	public static String parseStr(java.util.Date date) {
		return formatDate.format(date);
	}

	/**
	 * rql 工具方法
	 * 
	 * @param date
	 * @return
	 */
	public static String parseStr(java.util.Date date, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(date);
	}

	/**
	 * rql 工具方法
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date parseDate(String date) {
		try {
			return formatStr.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * rql 工具方法
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date parseDate(String date, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * rql 工具方法 格式化日历控件的日期格式 例子： source : 2012-02-02 返回值 : 20120202
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateStr(String source) {
		if (source != null) {
			String[] strs = source.split("-");
			if (strs.length == 3) {
				return addZore(strs[0], 4) + addZore(strs[1], 2) + addZore(strs[2], 2);
			}
		}
		return "";
	}

	/**
	 * rql 工具方法 格式化日历控件的日期格式的反格式化 例子： source : 20120202 result : 2012-02-02
	 * 
	 * @param date
	 * @return
	 */
	public static String enformatDateStr(String source) {
		StringBuffer result = new StringBuffer("");
		if (source != null) {
			if (source.length() == 8) {
				result.append(source.substring(0, 4));
				result.append("-");
				result.append(source.substring(4, 6));
				result.append("-");
				result.append(source.substring(6));
			}
		}

		return result.toString();
	}

	/**
	 * rql 工具方法 自动补0
	 * 
	 * @param date
	 * @return
	 */
	public static String addZore(String source, int len) {
		if (source == null)
			return null;
		if (source.length() < len) {
			return addZore("0" + source, len);
		} else {
			return source;
		}
	}

	public static void main(String[] args) {
		System.out.println(WebUtils.enformatDateStr("20120202"));
	}
}