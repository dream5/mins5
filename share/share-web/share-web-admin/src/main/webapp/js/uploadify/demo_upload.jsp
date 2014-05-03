<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>G12E 文件上传</title>
<link href="/css/common/colorbox.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/js/common/jquery-1.3.2.min.js"></script>
<script language="javascript" src="/js/common/jquery_colorbox.js"></script>
<script language="javascript" src="/js/common/open_mini_window.js"></script>
</head>
<body>
	<input style="width: 130px; height: 16px;" name="img_path" type="text"
		id="img_path" value="" />
	<a class="upload" title=""
		href="/expupload/send.jsp?proVal=CRSIMG_PATH&dom=img_path"><input
		type="button" value="上传图片" />
	</a>
</body>
</html>