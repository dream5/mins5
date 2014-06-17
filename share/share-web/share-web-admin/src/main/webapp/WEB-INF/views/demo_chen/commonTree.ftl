<#include "head.ftl"><#--模板head部分  -->
<script src="${path}/js/jquery.ztree-2.6.js"></script>
<link rel="stylesheet" href="${path}/css/ztree/demoStyle/demo.css" type="text/css">
<link rel="stylesheet" href="${path}/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<#--引用宏 -->
<#import "treeMacro.ftl" as tree>
<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 -->
<body  style="overflow-x:hidden;overflow-y:hidden ">
    <form method="post" id="frm">
      <table width="100%" align="center"  height="95%">
     	 <tr height="95%">
     	   <!--- 左侧树部分-->
     		 <td width="20%" height="100%" style="border:1px solid #4FB8DA;padding:4px;overflow-x:auto;overflow-y:auto;">
     		 <div class="tabTD-title">目录</div>
     			  <@tree.treeDivMacro treeObjName="${treeObjName!}" rootElement="${rootElement!}" treeClass="${treeClass!}" onClickFuc="${onClickFuc!}"/>
     		 </td>
     	   <!-- 右侧显示项--->
     		 <td width="80%" height="100%">
     		    <div>
				   <#--模板查询项部分-->
				   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabTD-content">
				   <@qryContentMacro qryDataList=qryDataList! rowNum=rowNum!/>
				   </table>
				   <#--模板按钮部分-->
				   <center><@buttonItemMacro params=params! style="margin: 5px 1px 10px 5px; padding: 3px; text-align: center"/><center>
				   </form> 
				   <#--模板的显示div层-->
				   <fieldset style="width: 100%;height: 20%" style="border:1px solid #D3D3D3" align="center">
					<@showDivMacro operatDiv="operatDiv"/>
				   </fieldset>
				</div>   
     		</td>
     	</tr>
     	<tr>
     		<td colspan="4" height="5%" align="center">
     		   <#--底部按钮-->
     		    <@buttonItemMacro params=bottomButtonParams! style=""/>
     		</td>
     	</tr>
     </table>

    <#--引入JS模板 -->
<@commJsMacro qryUrl=qryUrl! qryBtton=qryBtton!/>
</body>