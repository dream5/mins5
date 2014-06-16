/*    */package com.mins5.ehcache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.mins5.ehcache.entity.VDomain;
import com.mins5.share.common.cache.CacheDataUtil;
import com.mins5.share.common.domain.LabelValueBean;

/**
 * Title: 海南电信代理商佣金-Data Application Products <br>
 * Description: <br>
 * Date: 2012-3-9 <br>
 * Copyright (c) 2012 DATANG CATTSOFT <br>
 * 
 * @author wangq
 */
public class CacheDataUtilManager implements CacheDataUtil {
	public String exChangeDataCacheValue(Serializable obj, String cacheName,
			HttpServletRequest request) {
		return exChangeLabel(obj, cacheName, request);
	}

	public List<LabelValueBean> exChangeDataCacheList(
			Map<String, Serializable> dataCacheMap, String cacheName,
			HttpServletRequest request) {
		List<LabelValueBean> res = exChangeLabelValue(dataCacheMap, cacheName,
				request);

		return res;
	}

	private String exChangeLabel(Serializable obj, String cacheName,
			HttpServletRequest request) {
		String label = "";
		if (StringUtils.isNotBlank(cacheName)) {
			if (cacheName.equals(Constant.CACHE_V_DOMAIN)
					|| cacheName.contains(Constant.CACHE_V_DOMAIN)) {// 字典
				String tagValue = (String) request.getAttribute("tagValue");
				List<VDomain> list = (List<VDomain>) obj;
				if (list != null && list.size() > 0) {
					for (VDomain vDomain : list) {
						String stsId = vDomain.getColumnValue();
						if (stsId.equals(tagValue)) {
							label = vDomain.getColumnDescription();
						}
					}
				}
			}
		}
		return label;
	}

	/**
	 * @param obj
	 * @param cacheName
	 * @param value
	 * @return 转换 LabelValueBean
	 * @author wangq
	 */
	private LabelValueBean exChangeLabelValue(Serializable obj,
			String cacheName, String value, HttpServletRequest request) {
		LabelValueBean lb = new LabelValueBean();
		String label = exChangeLabel(obj, cacheName, request);
		lb.setLabel(label);
		lb.setValue(value);
		return lb;
	}

	private List<LabelValueBean> exChangeLabelValue(Map dataCacheMap,
			String cacheName, HttpServletRequest request) {
		List<LabelValueBean> res = null;
		if (dataCacheMap != null && !dataCacheMap.isEmpty()) {
			res = new ArrayList<LabelValueBean>();
			for (Iterator iter = dataCacheMap.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				String value = entry.getKey().toString();
				Serializable obj = (Serializable) entry.getValue();
				if (StringUtils.isNotBlank(value)
						&& value.equals(Constant.CACHE_V_DOMAIN)) {// V_DOMAIN的数据特殊处理
					List<VDomain> list = (List<VDomain>) obj;
					if (list != null && list.size() > 0) {
						for (VDomain vDomain : list) {
							LabelValueBean lb = new LabelValueBean();
							lb.setLabel(vDomain.getColumnDescription());
							lb.setValue(vDomain.getColumnValue());
							res.add(lb);
						}
					}
				} else {
					res.add(exChangeLabelValue(obj, cacheName, value, request));
				}
			}
		}
		return res;
	}

}
