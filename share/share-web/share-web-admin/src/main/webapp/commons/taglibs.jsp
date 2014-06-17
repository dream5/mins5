<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:directive.page import="com.cplatform.ad.common.util.Configger"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="my" uri="/tld" %>
<%@ page import= "com.cplatform.ad.common.util.DataUtil" %>
<%
String begintime=DataUtil.lastWeek(7);
String endtime=DataUtil.lastWeek(0);
request.setAttribute("endtime",endtime);
request.setAttribute("begintime",begintime);
request.setAttribute("sysLoginUrl", Configger.getString("system.login.url"));
%>
<c:set var="basepath" value="${pageContext.request.contextPath}"/>
<c:set var="APP_NAME" value="互联网流量营销平台"/>
<c:set var="username" value="${username }"/>
<c:set var="buisRoles" value="${buisRoles}"/>
<c:set var="tab" value="${param.tab==null?'0':param.tab}"/>
<c:set var="member_id" value="${member_id}"></c:set>
<c:set var="loginUser" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.userDetails}"/>
