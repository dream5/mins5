<table class="headerTb" cellspacing="0" cellpadding="0"  border="0">
	<tbody>
		<tr>
		  <td width="15px"><img src="${path}/images/frame/new_019.jpg" border="0"></td>
		  <td width="100%" background="${path}/images/frame/new_020.jpg" height="20"></td>
		  <td width="15px"><img src="${path}/images/frame/new_021.jpg"></td>
		</tr>
	</tbody>
</table>
<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg">
		<div class="cs-north-logo">Mins5 后台管理系统</div>
		<ul class="ui-skin-nav">	
		<#if loginUserName?exists>
        	<!--<li><a href="<%=personSettingPath%>" title="设置">修改密码</a></li>-->
            <li>欢迎您:${loginUserName}!<a href="${path}/login/logout" title="退出">[退出]</a></li>
        <#else>  
        	<li><a href="##" title="登录">登录</a></li>
      	</#if>
			<li class="li-skinitem" title="gray"><span class="gray" rel="gray"></span></li>
			<li class="li-skinitem" title="default"><span class="default" rel="default"></span></li>
			<li class="li-skinitem" title="bootstrap"><span class="bootstrap" rel="bootstrap"></span></li>
			<li class="li-skinitem" title="black"><span class="black" rel="black"></span></li>
			<li class="li-skinitem" title="metro"><span class="metro" rel="metro"></span></li>
		</ul>
		</div>
	</div>