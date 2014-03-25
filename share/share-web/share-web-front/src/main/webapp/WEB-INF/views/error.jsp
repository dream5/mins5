<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="description" content="对不起，出错了！" />
<meta name="keywords" content="" />
<meta name="robots" content="all" />
<style>
	body {TEXT-ALIGN: center;}
	.error{
		margin-top:200px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
<script>
	
	function refresh(){
		setTimeout("turnIndex()",5000);
	}
	
	function turnIndex(){
		window.location.href='/index/goToIndex.mins';
	}
	
</script>
</head>
<body onload="refresh();">
	<div class="error">
		<img src="${context}/images/404.gif" title="404" alt="404"/><span style="font-size:200px;">404</span>
		<p>
			抱歉，您访问的页面不存在！页面将在5秒后自动跳转到首页，或点击链接返回<a href="/index/goToIndex.mins">首页</a>
		</p>
	</div>
</body>
</html>