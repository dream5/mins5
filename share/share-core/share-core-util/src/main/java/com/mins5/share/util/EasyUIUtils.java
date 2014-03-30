package com.mins5.share.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasyUI数据格式转换工具类
 * @author zhoutian
 * @since 2014年3月29日
 */
public abstract class EasyUIUtils {

	/**
	 * 转换数据为datagrid需要的格式
	 * @author zhoutian
	 * @since 2014年3月29日
	 * @param total 数据总数
	 * @param list
	 * @return
	 */
	public static <T extends Object> String parseDataGrid(Long total, List<T> list) {
		if(total == null) {
			total = 0L;
		}
		if(list == null) {
			list = Collections.emptyList();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);
		String json = JsonUtils.writeValue(map);
		return json;
	}
}
