<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String   contextPath  = request.getScheme()+"://"+request.getHeader("host") +request.getRequestURI(); 
	String path = contextPath+request.getContextPath();//配置项目名称时使用
%>
<c:set var="context" value="<%=contextPath%>"></c:set>