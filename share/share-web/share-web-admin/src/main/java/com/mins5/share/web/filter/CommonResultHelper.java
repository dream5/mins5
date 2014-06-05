/* 
 * Copyright 2006 AsiaInfo Holdings, Inc
 * All right reserved.
 * Created on Aug 21, 2006
 */
/**
 * 
 */
package com.mins5.share.web.filter;

/**
 * 辅助类
 * 
 * @author plh
 */
public class CommonResultHelper {
	public static void fillCommonResult(CommonResult crt) {
		//需要补充
	}

//	public static CommonResult converter(ProcessException e) {
//		return new CommonResult(-1, e.getErrcode(), e.getMessage(), e);
//	}
//
//	public static CommonResult converter(BusinessException e) {
//		return new CommonResult(-1, e.getErrcode(), e.getMessage(), e);
//	}
//
//	public static CommonResult converter(SystemException e) {
//		return new CommonResult(-1, e.getErrcode(), e.getMessage(), e);
//	}
//
//	public static CommonResult converter(Exception e) {
//		if (e == null) {
//			throw new RuntimeException("系统没有发生异常");
//		} else if (e instanceof ProcessException) {
//			return converter((ProcessException) e);
//		} else if (e instanceof BusinessException) {
//			return converter((BusinessException) e);
//		} else if (e instanceof SystemException) {
//			return converter((SystemException) e);
//		} else {
//			if (e.getMessage() != null)
//				return new CommonResult(-1, "SYS-90987", e.getMessage(), e);
//			else
//				return new CommonResult(-1, "SYS-90987", "系统异常，请与管理员联系！", e);
//		}
//	}
}
