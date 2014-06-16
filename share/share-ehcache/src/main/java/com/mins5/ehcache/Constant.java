package com.mins5.ehcache;

public class Constant {

	public static final String DATA_PROCESS_LOG_RESULT_SUCESS = "S";
	public static final String DATA_PROCESS_LOG_RESULT_SUCESS_SHOW = "成功";
	public static final String DATA_PROCESS_LOG_RESULT_FAIL = "F";
	public static final String DATA_PROCESS_LOG_RESULT_FAIL_SHOW = "失败";
	/* 系统共用常量 */

	public static final String STS_STRING_ACTIVE = "A";// 状态-正常
	public static final String STS_STRING_POSITIVE = "P";// 状态-作废
	public static final String ROOT_TREE_ID = "0";// 树 根目录主键ID

	/* 缓存名称信息 */
	public static final String CACHE_REGION = "table.cache.identity.region";
	public static final String CACHE_BILLING_CYCLE = "table.cache.idvalue.billingcycle";
	public static final String CACHE_SYS_CONFIG = "table.cache.identity.sysconfig";
	public static final String CACHE_V_DOMAIN = "table.cache.idlist.vdomain";
	public static final String CACHE_SYSTEM_INFO = "table.cache.idvalue.systeminfo";
	public static final String CACHE_INDEX_CLASSIFICATION = "table.cache.idvalue.indexclassification";
	public static final String CACHE_DIM_CLASSIFICATION = "table.cache.idvalue.dimclassification";

	/* 数据处理存储过程常量 */
	public static final int SUCCESS = 0;// 测试数据处理存储过程返回结果
	public static final String DATA_INTEGRATION_ID = "DATA_INTEGRATION_ID";// 接口编号
	public static final String PRO_SUCCESS = "0";// 数据处理存储过程返回结果
	public static final String PRO_FAILD = "1";// 数据处理存储过程返回结果

	/* 排列顺序 */
	public static final String ORDER_BY_DESC = "DESC";

	public static String getSgsRegionId() {
		return SGS_REGION_ID;
	}

	public static final String SGS_REGION_ID = "46";

	public static String getCacheIndexClassification() {
		return CACHE_INDEX_CLASSIFICATION;
	}

	public static String getCacheRegion() {
		return CACHE_REGION;
	}

	public static String getCacheBillingCycle() {
		return CACHE_BILLING_CYCLE;
	}

	public static String getCacheSysConfig() {
		return CACHE_SYS_CONFIG;
	}

	public static String getCacheVDomain() {
		return CACHE_V_DOMAIN;
	}

	public static String getCacheSystemInfo() {
		return CACHE_SYSTEM_INFO;
	}

	public static String getOrderByDesc() {
		return ORDER_BY_DESC;
	}

	public static String getOrderByAsc() {
		return ORDER_BY_ASC;
	}

	public static String getMainPage() {
		return MAIN_PAGE;
	}

	public static String getListPage() {
		return LIST_PAGE;
	}

	public static String getCommPage() {
		return COMM_PAGE;
	}

	public static String getCacheDimClassification() {
		return CACHE_DIM_CLASSIFICATION;
	}

	public static final String ORDER_BY_ASC = "ASC";

	/* 公用跳转部分 */
	public static final String MAIN_PAGE = "main";
	public static final String LIST_PAGE = "list";
	public static final String COMM_PAGE = "comm";

}
