<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	${path}
	<br/>
	${user.username }
	<br/>
	${user.password }
	<br/>
	${user.gender.getName() }(${user.gender.getCode() })
	
	<br/>
	<#if paramMap.currentTime??>
	${paramMap.currentTime?string("yyyy-MM-dd HH:mm:ss") }
	</#if>
	<br/>
	${paramMap.b1?string }
	<br/>
	${paramMap.b2?string }
	<br/>
	<#if paramMap.b1>
	TRUE
	</#if>
	<#if !paramMap.b2>
	FALSE
	</#if>
	
	<br/>
	<table>
		<thead>
			<td>用户名</td><td>密码</td>
		</thead>
		<#list adminList as admin>
		<tr>
			<td>${admin.username}</td>
			<td>${admin.password}</td>
		</tr>
		</#list>
	</table>

</body>
</html>