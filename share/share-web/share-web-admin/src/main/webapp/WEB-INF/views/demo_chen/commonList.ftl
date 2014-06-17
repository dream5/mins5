<#include "head.ftl"><#--模板head部分  -->
<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]><#--引用分页自定义标签 -->
<table cellpadding="0" cellspacing="0" border="0" style="width:100%;height:100%;overflow:hidden">
    <tr style="height:32px">
        <td valign="middle" align="center">
            <@dtt.pagination name=pagingResultName! frmName="${frmName!}" showDivName="${showDivName!}" url="${url!}"/>
        </td>
    </tr>
    <tr>
        <td valign="top">
            <div style="width:100%;height:100%;overflow-Y:auto">
                <table width="99%" align="center" class="table_list">
                    <@resultListShowMacro showListResult=showListResult! pagingResultName=pagingResultName! columnHead=columnHeadName! oprateLinks=oprateLink!/>
                </table>
            </div>
        </td>
    </tr>
</table>
<script language="javascript" type="text/javascript">
$(function(){
$("tr:even").addClass("even");
$("tr:odd").addClass("odd");
});
</script>
