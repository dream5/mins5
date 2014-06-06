<#include "common/common.ftl" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MINS Five</title>
<link href="${path}/js/css/style.css" rel="stylesheet" type="text/css">
<style>body{overflow-y:auto;}</style>
<#include "common/common_js.ftl" />
<script>
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
		$('.contain').css("display","none");
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})
</script>
<script language="javascript">
function onload() {
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
	function onload() {
		if(msg != null && msg !=''){
			alert(msg);
		}
	}
</script>
</head>
<body>
<div class="contain">
	 <div class="box">
		<h1>站长工具</h1>
		<div class="link">
			<table>
				<tr>
					<td><a href="http://seo.chinaz.com/" target="_blank">SEO综合查询</a></td>
					<td><a href="http://tool.chinaz.com/webscan/" target="_blank">网站安全检查</a></td>
					<td><a href="http://link.chinaz.com/" target="_blank">友情链接检查</a></td>
					<td><a href="http://alexa.chinaz.com/" target="_blank">alexa查询</a></td>
				</tr>
				<tr>
					<td><a href="http://pr.chinaz.com/" target="_blank">PR查询</a></td>
					<td><a href="http://mytool.chinaz.com/baidusort.aspx" target="_blank">百度权重查询</a></td>
					<td><a href="http://tool.chinaz.com/baidu/words.aspx" target="_blank">关键字查询</a></td>
					<td><a href="http://tool.chinaz.com/baidu/" target="_blank">百度收录查询</a></td>
				</tr>
				<tr>
					<td><a href="http://tool.chinaz.com/KeyWords/default.aspx" target="_blank">关键词排名查询</a></td>
					<td><a href="http://tool.chinaz.com/Tools/MetaCheck.aspx?url= target="_blank">网页META信息检测工具</a></td>
					<td><a href="http://tool.chinaz.com/Links/Default.aspx" target="_blank">死链接检测/全站PR查询</a></td>
					<td><a href="http://tool.chinaz.com/Seos/Sites.aspx" target="_blank">搜索引擎收录/反链查询</a></td>
				</tr>
				<tr>		
					<td><a href="http://ip.chinaz.com/" target="_blank">IP查询</a></td>
					<td><a href="http://tool.chinaz.com/Tools/Density.aspx" target="_blank">网页关键词密度检测工具</a></td>
					<td><a href="http://seo.chinaz.com/" target="_blank">SEO综合查询</a></td>
					<td><a href="http://seo.chinaz.com/" target="_blank">SEO综合查询</a></td>
				</tr>
				
				
			</table>
		
		</div>
		<div class="theme-buy">
			<a class="btn btn-primary btn-large theme-login" href="javascript:;">点我登录</a>
		</div>
	 </div>

</div>


<div class="theme-popover">
     <div class="theme-poptit">
          <a href="javascript:;" title="关闭" class="close">×</a>
          <h3>MINS FIVE-后台管理系统</h3>
     </div>
     <div class="theme-popbod dform">
           <form class="theme-signin" id="loginform" name="loginForm" method="post" action="${path}/login/login.mins">
                <ol>
                     <li><strong>用户名：</strong><input class="ipt" type="text" id="userName" name="userName" maxlength="10" size="20" value="admin" onkeypress="if(event.keyCode == 13){loginForm.querypwd.focus();}"></li>
                     <li><strong>密码：</strong><input class="ipt" type="password" id="password"  name="password"  onkeypress="if(event.keyCode == 13){loginForm.random.focus();}"></li>
                     <li><strong>随机码：</strong> <input id="random" class="ipt" name="random" onkeypress="if(event.keyCode == 13){submitForm();}" style="width:30%;" maxLength="4" size="20"  value="">	
	                   <img src="${path}/randomImage"> 
	                 </li>
                     <li><input class="btn btn-primary" type="submit" name="submit" value=" 登 录 " onClick="submitForm();"/></li>
                </ol>
           </form>
     </div>
</div>
<div class="theme-popover-mask"></div>
</body>
</html>