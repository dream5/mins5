<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.cplatform.security.uesrdetails.CustomUser"%>
<%@page import="com.cplatform.security.domain.UserResource"%>
<%@page	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="java.util.Collection"%>
<%@page import="org.springframework.security.cas.authentication.CasAuthenticationToken"%>
<%@page import="java.util.*"%>
<%@page import="com.cplatform.ad.common.util.DataUtil"%>
<%response.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta content="冲浪联盟流量合作平台" name="description"/>
<meta content="手机冲浪,手机广告平台,手机冲浪广告,移动广告,sms广告,mms广告,web广告,网站广告,移动应用广告,手机应用广告,无线广告,手机软件广告,手机游戏广告,无线营销,精准投放,android手机软件广告,android应用广告,ios手机软件广告,iphone手机广告,全平台广告" name="keywords"/>
<title>冲浪联盟流量合作平台</title>
<link href="${basepath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${basepath}/css/system.css" rel="stylesheet" type="text/css" />
<link href="${basepath}/css/goodcal.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${basepath}/scripts/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="${basepath}/scripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basepath}/scripts/Calendar.js"></script>
<script type="text/javascript" src="${basepath}/scripts/jquery.boxy.js"></script>
<script type="text/javascript" src="${basepath}/scripts/scroll.js"></script>
<script type="text/javascript" src="${basepath}/scripts/treemenu.js"></script>
<script type="text/javascript" src="${basepath}/scripts/divselect.js" ></script>
<script type="text/javascript" src="${basepath}/scripts/GoodCalendar.js" ></script>
<script type="text/javascript" src="${basepath}/scripts/jquery.query-2.1.7.js" ></script>
<script type="text/javascript" src="${basepath}/scripts/system.js"></script>
<script language="javascript" type="text/javascript">
var pageType="";
var selCol=false;
var selectWidth;//定义下拉列表宽度
var basepath='${basepath}';
$(document).ready(function (){
    if('${msg}'!=null && '${msg}' != ''){
       alert('${msg}');
    }
}) ;
//
<%
try{
	request.setAttribute("requestPath", Configger.getString("stat.request.path")) ;
	CasAuthenticationToken authentication = (CasAuthenticationToken) request.getUserPrincipal();
	String temp_lastLoginTime=(String)authentication.getAssertion().getPrincipal().getAttributes().get("lastLoginTime");
	request.setAttribute("lastLoginTime", DataUtil.toDateTime(temp_lastLoginTime)) ;
	String userid = (String) authentication.getAssertion().getPrincipal().getAttributes().get("id");
	/**获取引导语数据*/
	if(temp_lastLoginTime==null){
		temp_lastLoginTime="";
	}
	Object obj=com.cplatform.httpclient.adplatform.RemoteCallUtils.getUserGuides(userid+"@"+temp_lastLoginTime, Configger.getString("guide.path"));
	List<com.cplatform.ad.domain.GuideInfo> showMsgList= new ArrayList<com.cplatform.ad.domain.GuideInfo>();
	if(obj instanceof List){
		showMsgList = (List<com.cplatform.ad.domain.GuideInfo>) obj;
	}else if(obj instanceof String){
	}
	net.sf.json.JSONArray jsobjcet = net.sf.json.JSONArray.fromObject(showMsgList);
	request.setAttribute("showMsgList", jsobjcet.toString()) ;
}catch(Exception e){e.printStackTrace();}
%>
</script>
</head>
<body>
 <div id="top">
	<div class="hd">
		<div class="contact fl hide">
			<div class="tel fl"><span></span>010-67126565 - 6098</div>
			<div class="email fl"><span></span><a href="mailto:ads@c-platform.com">ads@c-platform.com</a></div>
			<div class="on-line fl">
				<span class="fl" >在线客服：</span>
				<span class="qq" ><a href="#"></a></span>
				<span class="feixin" ><a href="#"></a></span>
			</div>
		</div>
		<div class="login fr">
			<div class="txt" >欢迎您,${username}&nbsp;&nbsp;<a href="<%=Configger.getString("union")%>/reg/toEdit" title="个人设置">个人设置</a> | <a href="${basepath}/cookieLogout" title="退出">退出</a>
			<span id="toEdit" style="position:absolute;"></span>
			</div>
		</div>
	</div>
</div><!--top end-->
<div id="head">
	<div class="hd">
		<div class="logo fl"><a href="${basepath}/index/about">冲浪联盟流量合作平台</a></div>
		<div class="nav fr">
			<ul>
				<c:forEach
					items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.userDetails.resources}"
					var="m">
					<!-- 前台用户路径为报表应用的路径 -->
					<c:set value="${m}" var="resource" scope="request" />
					<li <c:if test="${pageId  eq m.moduleCode }">class="on"</c:if> >
						<a id="${m.moduleCode}" href="${basepath}${m.url}" title="${m.moduleName}" class="nav">
							<span>${m.moduleName}</span>
						</a>
						<span id="a_${m.moduleCode}" style="position:absolute;"></span>
					</li>
				</c:forEach>


			</ul>
		</div>
	</div>
</div>
<div id="info">
	<div class="hd">
		<div class="info-txt">上次登录时间：<fmt:formatDate value="${lastLoginTime}" pattern="yyyy年MM月dd日 HH:mm:ss EEEE"/></div>
	</div>
</div>
<!--head end-->