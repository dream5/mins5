package com.mins5.pageUI.tag.datacache;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mins5.pageUI.pub.base.DataCacheOptionBaseTag;
import com.mins5.share.common.cache.CacheDataUtil;
import com.mins5.share.common.domain.LabelValueBean;

public class DataCacheOptionsTag extends DataCacheOptionBaseTag {
	protected List<LabelValueBean> exChangeOptionData() {
		List res = null;
		try {
			Map cacheMap = getCacheMap();
			CacheDataUtil cacheDataUtil = (CacheDataUtil) getBean("cacheDataUtil");
			if ((cacheMap != null) && (!cacheMap.isEmpty())) {
				HttpServletRequest request = (HttpServletRequest) this.pageContext
						.getRequest();
				res = cacheDataUtil.exChangeDataCacheList(cacheMap,
						this.cacheName, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}