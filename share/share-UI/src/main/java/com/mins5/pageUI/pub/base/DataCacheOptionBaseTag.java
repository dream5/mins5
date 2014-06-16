package com.mins5.pageUI.pub.base;

import java.util.List;

import com.mins5.share.common.domain.LabelValueBean;

public abstract class DataCacheOptionBaseTag extends DataCacheBaseTag {
	protected abstract List<LabelValueBean> exChangeOptionData();

	public void doTag(Object value) throws Exception {
		try {
			List data = exChangeOptionData();
			write(fillSelectData(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}