<#include "head.ftl"><#--模板head部分  -->
<#--引用宏 -->
<form method="post" name="frm" id="frm">
<#--模板查询项的titileContent部分-->
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabTD-content">
	  <#--模板新增项部分-->
	  <@qryContentMacro qryDataList=qryDataList! rowNum=rowNum!/>
 </table>
	<#--模板按钮部分-->
	  <center><@buttonItemMacro params=buttonParams! style=style!"margin: 5px 1px 10px 5px; padding: 3px; text-align: center"/></center> 
</form>
<#include "footer.ftl"><#--模板head部分  -->
