/**
 * 
 */
package com.mins5.share.web.common;

/**
 * 建立日期 : Sep 18, 2006 2:33:34 PM <br>
 * 作者 : 姚强（yq）<br>
 * 模块 : <br>
 * 描述 : 跟baf有关的常量都放到这里<br>
 * 修改历史 <br>
 * 序号 日期 修改人 修改原因 <br>
 * 1 <br>
 * 2 <br>
 */
public final class ServiceConstants {
	
    public static String CHANNEL_BMS = "A2";
    /** 门户报错页面 */
    public static String errorPage = "/userdoor/inc/pub/errpagecommonResult.jsp";//
    /** crm报错页面 */
    public static String errorPageCrm = "/manager/error.jsp";//
    /** 分页展示每页多少条记录 */
    public static int rowCount=10;
    /** 批量产品定购退定扫描时间间隔(毫秒) */
    public static long secondBat=10000;
    /** 扫描变更表，变更用户资料 扫描时间间隔(毫秒 */
    public static long secondUserINfo=10000;
    /** 内存刷新sys_web_qry_key等表的变更 扫描时间间隔(毫秒 */
    public static long secondMemory=10000;
    public static long productOffNotify=10000; 
    
}