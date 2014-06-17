<%@ page contentType="text/html;charset=UTF-8"%>
<!--<jsp:directive.page import="com.cplatform.ad.common.util.Configger"/>-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="my" uri="http://www.mins5.com/tags" %>
<%
//request.setAttribute("sysLoginUrl", Configger.getString("system.login.url"));
%>
<c:set var="basepath" value="${pageContext.request.contextPath}"/>
<c:set var="username" value="${username }"/>
