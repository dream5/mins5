package com.mins5.share.web.common;

/**
 * 
 * @author chenry
 * @since 2014年6月5日
 */
public final class Constants {
	/**
	 * 正确
	 */
	public static int OK = 0;

	/**
	 * 异常
	 */
	public static int ERROR = -1;

	/** 错误的超级密码 */
	public static int ERROR_SUPER_PWD = -2;

	/** 错误的用户密码校验 */
	public static int ERROR_PWD = -3;

	/** 错误的用户业务校验，即该用户无权做该业务 */
	public static int ERROR_BUSIRULE = -4;

	/**
	 * 营业业务管理
	 */
	public static String AIIP_EXEC_BMS = "aiip_exec_BMS";

	/**
	 * 帐务管理
	 */
	public static String AIIP_EXEC_ACCT = "aiip_exec_ACCT";

	/**
	 * 通用查询(查询三户信息等，不会引起堵塞)
	 */
	public static String AIIP_EXEC_ABS = "aiip_exec_ABS";

	/**
	 * 详单查询
	 */
	public static String AIIP_EXEC_CDR = "aiip_exec_CDR";

	/**
	 * 系统管理（以后转到普元平台，使用较少）
	 */
	public static String AIIP_EXEC_SYS = "aiip_exec_SYS";

	/**
	 * 为联机指令同步接口新开一个SERVER:SYNC
	 */
	public static String AIIP_EXEC_SYNC = "aiip_exec_SYNC";

	/**
	 * 大数据量查询（使用较少,可能会引起堵塞）
	 */
	public static String AIIP_EXEC_INQ = "aiip_exec_INQ";

	// SESSION KEY值
	public static String RANDOM_TOKEN = "com.asiainfo.frame.web.RANDOM";
	public static String LOGIN_TOKEN = "com.asiainfo.frame.web.LOGIN";

	public static String RANDOM_SMS = "com.asiainfo.frame.web.SMS";

	// ERROR KEY值
	public static String ERROR_KEY = "com.asiainfo.frame.web.ERROR";

	// AppStack KEY值
	public static String STACK = "stack";

	// 错误页面跳转name
	public static String STACKJSP = "errpage";

	// 统一报错jsp跳转页面nextPage的名称
	public static String COMMONRESULTJSP = "commonResultjsp";
	// 缴费成功jsp跳转页面nextPage的名称
	public static String DEPOSITOK = "depositOK";

	// 统一报错信息对象名称
	public static String COMMONRESULT = "commonResult";
	// 打印票据对象名称
	public static String DEPOSITSHOUJU = "depositShouju";
	// 工单信息
	public static String APPWFINFO = "wfInfoPrint";

	/** 正确页面跳转name */
	public static String SUCCESS = "success";
	public static String PAGE_SUCCESS = "okpagecommonResult";
	/** 统一模式成功页面 */
	public static String PAGE_OKPAGE_COMMON_RESULT = "okpagecommonResult";
	/** 统一模式提示页面 */
	public static String PAGE_OKPAGE_COMMON_NOTICE = "commonOkNotice";
	/** 统一模式成功页面Pop弹出窗口 */
	public static String PAGE_OKPAGE_COMMON_RESULT_POP = "okpagecommonResultPop";
	/** 统一模式报错页面 */
	public static String PAGE_ERRPAGE_COMMON_RESULT = "errpagecommonResult";
	/** 统一模式报错页面Pop弹出窗口 */
	public static String PAGE_ERRPAGE_COMMON_RESULT1 = "errpagecommonResult1";
	public static String PAGE_ERRPAGE_COMMON_RESULT_POP = "errpagecommonResultPop";
	/** 验证报错但是照常显示用户信息 */
	public static String PAGE_ERRPAGE_COMMON_SHOWUSERINFO = "checkErrorButShowUserInfo";
	/** 验证报错但是照常显示智能网用户信息 */
	public static String PAGE_ERRPAGE_COMMON_SHOWPPSUSERINFO = "checkErrorButShowPPSUserInfo";

	/** 针对具体用户不允许做某业务的报错页面 */
	public static String PAGE_ERROR_BUSIRULE = "errorBusiRule";
	/** 免填单页面 */
	public static String PAGE_WF_INFOPRINT = "wfInfoPrint";
	/** 审批页面 */
	public static String PAGE_WF_SHENPI = "wfInfoShenPi";

	/** 批量任务成功提交显示页面 */
	public static String PAGE_BATJOBOK = "batJobOK";
	/** 批量任务成功免填免填单界面 */
	public static String PAGE_BATJOBMTD = "batJobMTD";

	/** 批量业务上传文件的公共页面 */
	public static String PAGE_BATCH_BUSI_UPFILE = "batchBusiUpFile";

	/* 批量任务传递的VO 对象名称 com.asiainfo.crm.service.batch.BatchJobVO.java */
	public static String BATCHJOBVO = "BATCHJOBVO";

	/** 批量任务 数据上传方式 */
	public static String JOB_DATA_TYPE_FILE_ONE = "0"; //单文件方式
	public static String JOB_DATA_TYPE_HAODUAN = "1"; //号段方式
	public static String JOB_DATA_TYPE_FILE_MUL = "2"; //多文件方式
	public static String JOB_DATA_TYPE_FILE_FIVE = "5"; //后台在处理文件方式

	/** 文件上传保留方式 */
	public static String FILE_TYPE_MID = "MID"; //中间文件， 以后可以删除。
	public static String FILE_TYPE_AR = "AR"; //归档文件， 永久保留

	/** 业务受理成功（含费用票据打印）页面 */
	public static String PAGE_WF_FEEPRINT = "wfFeePrint";
	public static String PAGE_WF_INFOQUERY = "wfInfoQuery";
	public static String PAGE_WF_AUDIT = "wfAudit";

	public static final String BUSIID = "busiId";
	public static final String BUSIID_INFO = "busiIdInfo";
	public static final String ARRTNFLAG = "arrtnFlag";
	public static final String PWD_CHECKYN = "pwdCheckYn";
	public static final String SERVICE_TYPE = "serviceType";
	public static final String SERVICE_NUM = "serviceNum";
	public static final String USER_PASSWD = "userPasswd";
	public static final String BUSI_ID = "busiId";
	public static final String ARRTN_FLAG = "arRtnFlag";
	public static final String USERARINFO = "UserArInfo";
	public static final String OPERATOR_ID = "OperatorId";
	public static final String REGION_ID = "RegionId";
	public static final String CARD_TYPE = "CardType";
	public static final String AUTHORIZATION = "Authorization";
	public static final String CARDFEE = "CardFee";
	public static final String BUSIFEE = "BusiFee";
	public static final String FEEINPUTINFO = "FeeInputinfo";

	/** 文档资料维护使用的 **/
	public static final String FILE_SEP = System.getProperty("file.separator");

	private Constants() {
	}
}