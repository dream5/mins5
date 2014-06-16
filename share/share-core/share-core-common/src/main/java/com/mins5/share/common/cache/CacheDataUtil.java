package com.mins5.share.common.cache;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mins5.share.common.domain.LabelValueBean;

public interface CacheDataUtil {
	public List<LabelValueBean> exChangeDataCacheList(
			Map<String, Serializable> paramMap, String paramString,
			HttpServletRequest paramHttpServletRequest);

	public String exChangeDataCacheValue(Serializable paramSerializable,
			String paramString, HttpServletRequest paramHttpServletRequest);
}
