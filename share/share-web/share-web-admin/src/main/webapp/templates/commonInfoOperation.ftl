<#--引用宏 -->
<#include "head.ftl"><#--模板head部分  -->
<#assign path=request.contextPath>
<script type="text/javascript">
		basePath="${path}";
		jQuery(function(e) {
			jQuery(".table1 tr").mouseover(function() {
				//如果鼠标移到class为stripe的表格的tr上时，执行函数
					jQuery(this).addClass("over");
				})
	
			jQuery(".table1 tr").mouseout(function() { //给这行添加class值为over，并且当鼠标一出该行时执行函数
						jQuery(this).removeClass("over");
					}) //移除该行的class 
			
			//奇偶变色，添加样式   
			jQuery('.table1 tr').addClass('odd');
			   
	        jQuery('.table1 tr:even').addClass('even');
	        jQuery('.table1 td:odd').css("width","38%");
	        jQuery('.table1 td:odd').css("padding-left","3px");
		});
        </script>
		<style>
			tr.over td {
				background: #FFEEDD; /*鼠标高亮行的背景色*/
			}
		</style>
<#--模板查询项部分-->
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabTD-content">
<@qryContentMacro qryDataList=qryDataList! rowNum=rowNum!/>
</table>
<#--模板按钮部分-->
 <center><@buttonItemMacro params=params! style="margin: 5px 1px 10px 5px; padding: 3px; text-align: center"/></center> 