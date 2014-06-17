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

<script type="text/javascript">
	var currentPage = 1;
	var onePageSize = 10;
	$(document).ready(function() {
		$('#dg').datagrid({
			title:"管理员列表",
			rownumbers:true,
			singleSelect:true,
			pagination:true,
			pageList:[10, 20, 30, 40, 50],
			pageSize:10,
			toolbar:'#tb'
		});
		$('#dg').datagrid('getPager').pagination({
			onSelectPage:function(pageNum, pageSize){
				currentPage = pageNum;
				onePageSize = pageSize;
				loadTable();
			}
		});
		loadTable();
	});
	function formatStatus(val,row) {
		if (val == 0) {
			return '<span style="color:red;">未启用</span>';
		} else {
			return '<span style="color:green;">启用</span>';
		}
	}
	function formatOperation(val,row) {
		var operation = '<a href="${path}/admin/toAdminEdit.mins?adminId='+val+'">修改</>';
		operation += '&nbsp;&nbsp;&nbsp;';
		operation += '<a href="#" onclick="deleteConfirm('+val+')">删除</>';
		return operation;
	}
	function loadTable() {
		var userName = $('#userName').val();
		var nickName = $('#nickName').val();
		var createTime = $('#createTime').datebox('getValue');
		var status = $('#status').combobox('getValue');
		if(status == -1) {
			status = null;
		}
		var currentTime = new Date().getTime();
		var queryParams = {
			'userName':userName,
			'nickName':nickName,
			'createTime':createTime,
			'status':status,
			'currentPage':currentPage,
			'onePageSize':onePageSize,
			'currentTime':currentTime
		};
		jQuery.ajax({
		    url: '${path}/admin/searchAdmin.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#dg').datagrid('loadData', msg);
		    }
		});
	};
	function deleteConfirm(adminId){
		$.messager.confirm('删除提示', '您确认删除此管理员吗?', function(r){
			if(!r) {
				return;
			}
			var queryParams = {"adminId":adminId};
			jQuery.ajax({
			    url: '${path}/admin/adminDelete.mins',
			    data: queryParams,
			    type: "POST",
			    dataType: "text",
			    success: function (msg) {
			    	$.messager.alert('删除提示', msg);
			    	loadTable();
			    },
			    error: function() {
			    	$.messager.alert('删除提示', '删除失败！');
			    }
			});
		});
	}
</script>
	<div class="curPosition"><span class="sys-nav"><em>当前位置：管理员管理</em></span></div>
	<div class="content">
	<table id="dg" class="gridHead">
		<thead>
			<tr>
				<th data-options="field:'userName',width:300,align:'center'">管理员名称</th>
				<th data-options="field:'nickName',width:100,align:'center'">管理员昵称</th>
				<!--<th data-options="field:'status',width:100,align:'center'">管理员实际姓名</th>-->
				<th data-options="field:'createTime',width:240,align:'center'">管理员创建时间</th>
				<th data-options="field:'adminId',width:300,align:'center',formatter:formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="${path}/admin/toAdminAdd.mins" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加管理员</a>
		</div>
		<div>
			用户名: <input id="userName" type="text" style="width:150px">
			昵称: <input id="nickName" type="text" style="width:150px">
			创建时间: <input id="createTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			管理员状态: 
			<select id="status" class="easyui-combobox" style="width:60px" data-options="editable:false,panelHeight:'auto'">
				<option value="-1">全部</option>
				<option value="0">生效</option>
				<option value="1">失效</option>
				<option value="2">锁定</option>
			</select>
			<a href="#" onclick="loadTable();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	</div>



