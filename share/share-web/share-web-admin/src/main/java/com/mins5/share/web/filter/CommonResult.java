/* 
 * Copyright 2006 AsiaInfo Holdings, Inc
 * All right reserved.
 * Created on Aug 21, 2006
 */
package com.mins5.share.web.filter;

import org.springframework.util.StringUtils;

/**
 * 建立日期 : Sep 25, 2006 10:15:48 AM <br>
 * 作者 : plh <br>
 * 模块 : CommonResult是系统的通用返回接口<br>
 * 描述 : isSuccess()判断返回是否成功，如果返回成功，程序进入下一步处理；<br>
 * 失败则应该通过errtp分析错误的原因 (大于0，应用逻辑出错，错误信息为RESULT 小于0, 系统出错，错误信息见RESULT)<br>
 * 修改历史 <br>
 * 序号 日期 修改人 修改原因 <br>
 * 1 061024 yaoq 添加了一个isError，呵呵。<br>
 * 2 061024 yaoq 修改了pushErrorInfo函数的错误。<br>
 */

public class CommonResult {
	/**
	 * 返回编码 若0表示成功，成功返回信息见RESULT 大于0，应用逻辑出错，错误信息为RESULT 小于0, 系统出错，错误信息见RESULT
	 */
	private int errtp = 0;

	/**
	 * 错误编码 编码格式：XX-XSSS-NNNNN
	 */
	private String errcode;

	/**
	 * 错误信息 显示给操作员
	 */
	private String message;

	/**
	 * 错误堆栈信息 Debug信息，只显示给程序员<br/>
	 * detail可能是Exception或者String
	 */
	private Object detail;

	/**
	 * 业务名称
	 */
	private String businame;

	/**
	 * 解决方法
	 */
	private String solution;

	/**
	 * 工作序号 若非工作流，可以为空。
	 */
	private String wfid;

	/**
	 * 网页history回退的数量。
	 */
	private String backPageNum = "-1";

	/** 把tcl串解析出来的东西，可能是VO, MODEL, 或者map */
	private Object parseResult;

	private String hrefUrl = "";//如果有这值，则跳转的时候跳转到该页面

	private String hrefTarget = "_self";//目标框架

	/**
	 * 收银序号 若非工作流，可以为空。若无收银记录，可为空。 <br>
	 * （比较关键，后台若有收银记录，必须要传）。若有收银记录，前台页面跳转到票据打印。
	 */
	private String chgid;

	public CommonResult() {
		message = "";
	}

	public CommonResult(int errtp, String errcode, String message) {
		this.errtp = errtp;
		this.errcode = errcode;
		this.message = message;
	}

	public CommonResult(int errtp, int errcode, String message) {
		this.errtp = errtp;
		this.errcode = String.valueOf(errcode);
		this.message = message;
	}

	public CommonResult(int errtp, String errcode, String message, Object detail) {
		this.errtp = errtp;
		this.errcode = errcode;
		this.message = message;
		this.detail = detail;
	}

	/**
	 * 信息修改
	 * 
	 * @param errtp 错误类型
	 * @param errcode 错误代码
	 * @param message 错误信息
	 */
	public void modiInfo(int errtp, String errcode, String message) {
		this.errtp = errtp;
		this.errcode = errcode;
		this.message = message;
	}

	/**
	 * 信息修改
	 * 
	 * @param errtp 错误类型
	 * @param errcode 错误代码
	 * @param message 错误信息
	 */
	public void modiInfo(int errtp, int errcode, String message) {
		this.errtp = errtp;
		this.errcode = String.valueOf(errcode);
		this.message = message;
	}

	/**
	 * 信息修改
	 * 
	 * @param errtp 错误类型
	 * @param errcode 错误代码
	 * @param message 错误信息
	 * @param detail 详细信息
	 */
	public void modiInfo(int errtp, String errcode, String message, Object detail) {
		this.errtp = errtp;
		this.errcode = errcode;
		this.message = message;
		this.detail = detail;
	}

	/**
	 * 信息修改
	 * 
	 * @param errtp 错误类型
	 * @param errcode 错误代码
	 * @param message 错误信息
	 * @param detail 详细信息
	 */
	public void modiInfo(int errtp, int errcode, String message, Object detail) {
		this.errtp = errtp;
		this.errcode = String.valueOf(errcode);
		this.message = message;
		this.detail = detail;
	}

	/**
	 * 追加错误信息
	 * <p>
	 * 与modiInfo方法不同，modiInfo方法会将errcode和message替换，<br>
	 * 而pushErrorInfo方法是将errcode替换,message=aErrormsg＋message
	 * </p>
	 * 
	 * @param aErrorcode
	 * @param aErrormsg
	 */
	public void pushErrorInfo(String aErrorcode, String aErrormsg) {
		this.errcode = aErrorcode;
		this.message += aErrormsg;
		this.errtp = 1;
	}

	public void pushErrorInfo(String aErrormsg) {
		this.errcode = "100001";
		this.message += aErrormsg;
		this.errtp = 1;
	}

	public void pushSuccessInfo(String aErrormsg) {
		this.errcode = "0";
		this.message += aErrormsg;
		this.errtp = 0;
	}

	/**
	 * @return 成功true/失败false
	 */
	public boolean isSuccess() {
		return errtp == 0;
	}

	public boolean isError() {
		return errtp != 0;
	}

	public String getBusiname() {
		return businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getMessage() {
		return message;
	}

	public String getSimpleMessage() {
		if (StringUtils.hasText(message)) {
			String simpleMessage = message.replaceAll("^.*-.*异常[\\s]*", "");
			simpleMessage = simpleMessage.replaceAll("File.*$", "");
			simpleMessage = simpleMessage.replaceAll("\\[.*\\][\\s]*", "");
			return simpleMessage;
		} else {
			return "";
		}
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrtp() {
		return errtp;
	}

	public void setErrtp(int errtp) {
		this.errtp = errtp;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getBackPageNum() {
		return backPageNum;
	}

	public void setBackPageNum(String backPageNum) {
		this.backPageNum = backPageNum;
	}

	public String getChgid() {
		return chgid;
	}

	public void setChgid(String chgid) {
		this.chgid = chgid;
	}

	public String getWfid() {
		return wfid;
	}

	public void setWfid(String wfid) {
		this.wfid = wfid;
	}

	public Object getParseResult() {
		return parseResult;
	}

	public void setParseResult(Object parseResult) {
		this.parseResult = parseResult;
	}

	public void clean() {
		errcode = "";
		message = "";
		detail = null;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public void setIndex(String appType) {
		if (appType == null)
			appType = "";
		this.setHrefTarget("_top");
//		if ("crm".equals(appType)) {
//			this.setHrefUrl(ConstantsWeb.webUrl + "/manager");
//		} else
//			this.setHrefUrl(ConstantsWeb.webUrl + "");
	}

}
