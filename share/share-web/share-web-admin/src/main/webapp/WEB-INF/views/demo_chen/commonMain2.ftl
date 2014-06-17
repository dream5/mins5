<#include "head.ftl"><#--模板head部分  -->
<#--引用宏 -->
<#--模板查询项的titileContent部分
	  <@titleFuncMacro titleContent="${titleContent!} "/>
	  -->
<table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;overflow:hidden">
    <tr style="height:${((qryDataList?size/rowNum + 0.5)?int) * 16}px;overflow:hidden">
        <form method="post" id="frm">
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabTD-content">
                <#-- 模板查询项部分 -->
	            <@qryContentMacro qryDataList=qryDataList! rowNum=rowNum!/>
            </table>
        </td>
        </form>
    </tr>
    <tr style="height:20px;overflow:hidden">
        <td valign="middle" align="center">
            <#-- 模板按钮部分 -->
            <@buttonItemMacro params=buttonParams! style=style!"text-align: center" />
        </td>
    </tr>
    <tr style="overflow:hidden">
        <td valign="top">
            <#--模板的显示div层-->
            <@showDivMacro operatDiv="operatDiv"/>
        </td>
    </tr>
</table>
</body>
</html>