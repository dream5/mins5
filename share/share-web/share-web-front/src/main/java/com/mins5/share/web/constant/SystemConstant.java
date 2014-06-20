package com.mins5.share.web.constant;

/**
 * 前台常量定义
 * 
 * @author zhanglin
 * @since 2014年6月20日
 */
public class SystemConstant extends ConfigurableConstants {

	static {
		init("/conf/SystemConstant.properties");
	}

	public final static int BANNER_QUERY_COUNT = SystemConstant.getProperty("banner_query_count", 10);

	public final static int RANDOM_READING_QUERY_COUNT = SystemConstant.getProperty("random_reading_query_count", 10);

	public final static int LABEL_QUERY_COUNT = SystemConstant.getProperty("label_query_count", 10);

	public final static int HOT_RECOMMEND_QUERY_COUNT = SystemConstant.getProperty("hot_recommend_query_count", 10);

	public final static int DETAIL_HOT_RECOMMEND_QUERY_COUNT = SystemConstant.getProperty("detail_hot_recommend_query_count", 10);

	public final static int GUESS_LIKE_QUERY_COUNT = SystemConstant.getProperty("guess_like_query_count", 10);

}
