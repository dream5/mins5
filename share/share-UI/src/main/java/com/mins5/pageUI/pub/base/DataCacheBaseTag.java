package com.mins5.pageUI.pub.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.mins5.pageUI.entity.BaseTag;
import com.mins5.share.common.cache.CacheManager;
import com.mins5.share.common.domain.LabelValueBean;

public abstract class DataCacheBaseTag extends BaseTag {
	protected String cacheName;
	protected String id;
	protected String tabName;
	protected String colName;
	private static final String CACHE_V_DOMAIN = "table.cache.idlist.vdomain";

	public String getTabName() {
		return this.tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCacheName() {
		return this.cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	protected Serializable getCacheObject(String key) {
		Serializable cacheObj = null;
		try {
			CacheManager cacheManager = (CacheManager) getBean("cacheManager");

			if ((StringUtils.isNotBlank(this.cacheName))
					&& (this.cacheName.contains("table.cache.idlist.vdomain"))) {
				String keys = this.cacheName.replace(
						"table.cache.idlist.vdomain.", "");
				cacheObj = cacheManager.get("table.cache.idlist.vdomain", keys);
				HttpServletRequest request = (HttpServletRequest) this.pageContext
						.getRequest();
				request.setAttribute("tagValue", getTagValue());
			} else {
				cacheObj = cacheManager.get(this.cacheName, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheObj;
	}

	protected Map<String, Serializable> getCacheMap() {
		Map cacheMap = null;
		CacheManager cacheManager = (CacheManager) getBean("cacheManager");
		try {
			if ((StringUtils.isNotBlank(this.cacheName))
					&& (this.cacheName.contains("table.cache.idlist.vdomain"))) {
				String keys = this.cacheName.replace(
						"table.cache.idlist.vdomain.", "");
				Serializable cacheObj = cacheManager.get(
						"table.cache.idlist.vdomain", keys);
				cacheMap = new HashMap();
				cacheMap.put("table.cache.idlist.vdomain", cacheObj);
			} else {
				cacheMap = cacheManager.getCacheMap(this.cacheName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cacheMap;
	}

	protected String fillSelectData(List<LabelValueBean> selectData)
			throws JspException {
		StringBuffer str = new StringBuffer();
		String proVal = getTagValue() == null ? "" : getTagValue().toString();
		if ((selectData != null) && (selectData.size() > 0)) {
			str.append("<select name=\"" + this.name + "\" id=\""
					+ (StringUtils.isNotBlank(this.id) ? this.id : this.name)
					+ "\">");

			for (LabelValueBean labelValueBean : selectData) {
				String label = labelValueBean.getLabel();
				String value = labelValueBean.getValue();
				if (StringUtils.isNotBlank(value)) {
					str.append("<option value=");
					str.append(value);
					if ((StringUtils.isNotBlank(proVal))
							&& (value.equals(proVal))) {
						str.append(" selected ");
					}
					str.append(">");
					str.append(label);
					str.append("</option>");
				}
			}
			str.append("</select>");
		}
		return str.toString();
	}
}