<#assign path=request.contextPath>
<html>
	<head>
<title>${title!"Mins5后台管理"}</title>
        <link rel="icon" href="${path}/favicon.ico" mce_href="${path}/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="${path}/favicon.ico" mce_href="${path}/favicon.ico" type="image/x-icon">
<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 --> 
<#assign security=JspTaglibs["http://shiro.apache.org/tags"]> <#--引用shiro标签 --> 
<script src="${path}/webframe/page/main/js/jquery-1.7.2.js"></script>
<#include "common_js.ftl"><#--模板js部分  -->
</head>
<body>
