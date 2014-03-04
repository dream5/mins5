<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="http://localhost:8080/share-web-admin/a/a1.html">A.a1() - GET</a><br/>
	<form action="http://localhost:8080/share-web-admin/a/a1.html" method="post">
		<input type="submit" value="A.a1() - POST" />
	</form>
	<a href="http://localhost:8080/share-web-admin/a/a2.html">A.a2() - GET</a>
	<form action="http://localhost:8080/share-web-admin/a/a2.html" method="post">
		<input type="submit" value="A.a2() - POST" />
	</form>
	<a href="http://localhost:8080/share-web-admin/a/a3.html?username=admin&password=111111">A.a3() - GET</a>
	<form action="http://localhost:8080/share-web-admin/a/a3.html" method="post">
		<input type="hidden" name="username" value="张三" />
		<input type="hidden" name="password" value="111111" />
		<input type="submit" value="A.a3() - POST" />
	</form>
	<a href="http://localhost:8080/share-web-admin/a/a4.html?username=admin&password=111111">A.a4() - GET</a>
	<form action="http://localhost:8080/share-web-admin/a/a4.html" method="post">
		<input type="hidden" name="username" value="张三" />
		<input type="hidden" name="password" value="111111" />
		<input type="submit" value="A.a4() - POST" />
	</form>
</body>
</html>