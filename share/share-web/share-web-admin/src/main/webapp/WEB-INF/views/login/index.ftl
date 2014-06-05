<#include "common/common.ftl" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MINS Five</title>
<link href="${path}/js/css/style.css" rel="stylesheet" type="text/css">
<style>body{overflow-y:auto;}</style>
<#include "common/common_js.ftl" />
<script language="javascript">
function window.onload() {
	if( document.location.search.indexOf("monkey")>0 ) {
		loginForm.userName.value = "dev11";
		document.all.random.value = document.getElementById('tmp').innerHTML;
		document.all.password.value = "000000";
		document.all.monkey.value = "Y";
		loginForm.submit();
	}
	loginForm.userName.select();
	loginForm.userName.focus();
	
}

function submitForm(){
			if($.trim($("#userName").val())=='') {
	    		alert("请输入用户名");
	    	}else if($("#password").val()=='') {
	    		alert("请输入密码");
	    	}else if($.trim($("#random").val())=='') {
	    		alert("请输入验证码");
	    	}else {
	    		$("#loginForm").submit();
	    	}
}
</script>
<script type="text/javascript">
	var msg = '${msg}';
	function window.onload() {
		if(msg != null && msg !=''){
			alert(msg.length());
		}
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" scroll="no" >
<form id="loginForm" name="loginForm" method="post" action="${path}/login/login.mins">
<table width="100%" height="100%"  border="0" cellpadding="0" cellspacing="0" background="${path}/images/login_bg.jpg" >
			  <tr>
				<td width="494">&nbsp;</td>
				<td width="260">
				<INPUT type="hidden" name="isRemberMyLoginName" value="true"/><table width="100%"  border="0" cellspacing="0" cellpadding="0">
			</tr>				  
			<tr>
					<td align="right"  style="font-size:9pt">&nbsp;用户名：</td>
					<td width="176" height="30">
					     <input type="text" id="userName" name="userName" maxlength="10" size="20" value="admin" onkeypress="if(event.keyCode == 13){loginForm.querypwd.focus();}" style="BORDER-RIGHT: #999999 1px solid; BORDER-TOP: #999999 1px solid; BORDER-LEFT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid">
					</td>
				  </tr>
				  <tr>
					<td align="right" style="font-size:9pt">&nbsp;密&nbsp;&nbsp;&nbsp;码：</td>
					<td width="176" height="30">
					     <input type="password" id="password"  name="password"  onkeypress="if(event.keyCode == 13){loginForm.random.focus();}" style="BORDER-RIGHT: #999999 1px solid; BORDER-TOP: #999999 1px solid; BORDER-LEFT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid" maxLength="10">
					</td>
				  </tr>
				  <tr>
     				  <td align="right" style="font-size:9pt">&nbsp;随机码：</td>
				  	  <td  width="176" height="30">	
				  	   <input id="random" name="random" onkeypress="if(event.keyCode == 13){submitForm();}"  style="WIDTH:30%;" maxLength="4" size="20"  value="">	
	                   <img src="${path}/randomImage"> 
					  <a href="${path}/images/get_pwd.jsp"><font color="red">忘记密码</font></a>
				  	</td>
				  </tr>
				  <tr>
					<td colspan="2" align="center">	
							<br/>
							<a href="#" onClick="submitForm();" tabindex="3" ><img src="${path}/images/login.gif" border="0" ></a>　
					</td>
				  </tr>
</table>
</form>

</body>
</html>