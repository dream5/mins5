<#include "head.ftl"><#--模板head部分  -->
<script src="${path}/js/jquery.ztree-2.6.js"></script>
<link rel="stylesheet" href="${path}/css/ztree/demoStyle/demo.css" type="text/css">
<link rel="stylesheet" href="${path}/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
<#--引用宏 -->
<#import "treeMacro.ftl" as tree>
<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 -->
<script language="javaScript">
    var currentSelectTreeNode ="";
    clickmethod = ${onClickFuc};
    jevent = "";
    jTreeId = "";
    function ${onClickFuc}(event, treeId, treeNode) {
    <#if stringUtil.isNotBlank(qryUrl)>
        currentSelectTreeNode = treeNode;
        jevent = event;
        jTreeId = treeId;
        $("#datailInfoDiv").load("${qryUrl!}"+"?nId="+treeNode.id+"&time="+new Date().getMilliseconds());
    </#if>
    }
</script>
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
                        <@tree.treeDivMacro treeObjName="${treeObjName!}"  rootElement="${rootElement!}" treeClass="${treeClass!}" onClickFuc="${onClickFuc!}"/>
                        </td>
                    </tr>
                </table>
            </td>
            <!-- 右侧展现部分 -->
            <td style="padding:4px;" valign="top">
                <div id="datailInfoDiv" style="margin-top:3px"/>
            </td>
        </tr>
    </form>
</table>
</body>
</html>