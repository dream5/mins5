<#include "head.ftl"><#--模板head部分  -->
<script src="${path}/js/jquery.ztree-2.6.js"></script>
<link rel="stylesheet" href="${path}/css/ztree/demoStyle/demo.css" type="text/css">
<link rel="stylesheet" href="${path}/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<#--引用宏 -->
<#import "treeMacro.ftl" as tree>
<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 -->
<table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;overflow:hidden">
    <form method="post" id="frm">
        <tr valign="top">
            <!-- 左侧目录 -->
            <td style="width:20%" style="border:1px solid #4FB8DA;padding:4px;">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%">
                    <tr style="height:20px">
                        <td class="tabTD-title" align="center" valign="middle">目录</td>
                    </tr>
                    <tr>
                        <td valign="top">
                        <@tree.treeDivMacro treeObjName="${treeObjName!}" rootElement="${rootElement!}" treeClass="${treeClass!}" onClickFuc="${onClickFuc!}"/>
                        </td>
                    </tr>
                </table>
            </td>
            <!-- 右侧查询及列表 -->
            <td style="padding:4px;">
                <table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;overflow:hidden">
                    <tr style="height:${((qryDataList?size/rowNum + 0.5)?int) * 16}px;overflow:hidden">
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabTD-content">
                                <#-- 模板查询项部分 -->
	                            <@qryContentMacro qryDataList=qryDataList! rowNum=rowNum!/>
                            </table>
                        </td>
                    </tr>
                    <tr style="height:20px;overflow:hidden">
                        <td valign="middle" align="center">
                            <#-- 模板按钮部分 -->
                            <@buttonItemMacro params=params! style=style!"text-align: center" />
                        </td>
                    </tr>
                    <tr style="overflow:hidden">
                        <td valign="top">
                            <#--模板的显示div层-->
                            <@showDivMacro operatDiv="operatDiv"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <#if bottomButtonParams?exists>
        <tr style="height:30px">
            <td colspan="2" valign="middle" align="center">
                <@buttonItemMacro params=bottomButtonParams! style=""/>
            </td>
        </tr>
    </#if>
    </form>
</table>
<#--引入JS模板 -->
<@commJsMacro qryUrl=qryUrl! qryBtton=qryBtton!/>
</body>
</html>