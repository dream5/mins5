<#-- 添加 -->
<#assign path=request.contextPath>
<script src="${path}/js/schedule_validator.js"></script>
<#-- 准备数据 -->
<#assign title="添加"/>
<#assign qryDataList=[{"content":"对象类型名称：","name":"actorType.actorTypeName","type":"text","seq":"1","id":"firstTd","required":"true"},
                      {"content":"是否能登录：","name":"actorType.canLogin","type":"selectDomain","seq":"2","tabName":"SCY_ACTOR_TYPE","colName":"CAN_LOGIN","required":"true"},
                      {"content":"权限对象实体：","name":"actorType.entityName","type":"text","seq":"3","required":"true"},
                      {"content":"认证SQL语句：","name":"actorType.authSql","type":"text","seq":"4","id":"authsql","required":"true"}, 
                      {"content":"展示SQL语句：","name":"actorType.showSql","type":"text","seq":"5","required":"true"},
                      {"content":"展示方式：","name":"actorType.showType","type":"selectDomain","seq":"6","tabName":"SCY_ACTOR_TYPE","colName":"SHOW_TYPE","required":"true"}
                     ] 
         rowNum=2  />
         
<#assign buttonParams=[{"content":"确  定","id":"ok","seq":"1"},
                       {"content":"取  消","id":"cancel","seq":"2"}
                     ]/>
                     
<#--  导入添加模板 -->
<#include "/templates/commonAdd.ftl">

<script type="text/javaScript">
$('#authsql').css("width","16%");
$("#firstTd").css("width","15%");
	/*****统一非空验证**********/
	var validatorUtil = new Validator();
	validatorUtil.Required.actorTypeName = new Array("actorType.actorTypeName", "对象类型名称不能为空!");
	validatorUtil.Maxlength.actorTypeName = new Array("actorType.actorTypeName","对象类型名称的最长长度为100",new Function("varName","this.maxlength=100;return this[varName];"));
	validatorUtil.Required.entityName = new Array("actorType.entityName", "权限对象实体不能为空!");
	validatorUtil.Maxlength.entityName = new Array("actorType.entityName","权限对象实体体的最长长度为100",new Function("varName","this.maxlength=100;return this[varName];"));
	validatorUtil.Required.authSql = new Array("actorType.authSql", "认证SQL语句不能为空!");
	validatorUtil.Maxlength.authSql = new Array("actorType.authSql","认证SQL语句的最长长度为500",new Function("varName","this.maxlength=500;return this[varName];"));
	validatorUtil.Required.showSql = new Array("actorType.showSql", "展示SQL语句不能为空!");
	validatorUtil.Maxlength.showSql = new Array("actorType.showSql","展示SQL语句的最长长度为500",new Function("varName","this.maxlength=500;return this[varName];"));
	
	

  $(function(){
     $('#ok').click(function(){
     			if (!validatorUtil.validate()) {
				return;
			}
               var actorTypeName=$("[name='actorType.actorTypeName']").val();
               var entityName=$("[name='actorType.entityName']").val();
               var sts=$("[name='actorType.sts']").val();
               var showType=$("[name='actorType.showType']").val();
               var canLogin=$("[name='actorType.canLogin']").val();
               var authSql=$("[name='actorType.authSql']").val();
               var showSql=$("[name='actorType.showSql']").val();
               if(confirm('确定新增吗？')){
                   $.ajax({ 
		                url : '${path}/security/actorType_doAdd.action',
		                type: 'post',
		                data: {
		                    	 'actorType.actorTypeName':actorTypeName,
		                    	 'entityName':entityName,
		                    	 'sts':sts,
		                    	 'showType':showType,
		                    	 'canLogin':canLogin,
		                    	 'authSql':authSql,
		                    	 'showSql':showSql
	                             },
	                	success: function(data) {
	                	             if(data>0){
	                	               alert('添加成功！');
	                	               window.returnValue = 'y';
	                	               window.close();
	                	              }
		                            }
	                   });
              }
      });
  });
   $(function(){
     $('#cancel').click(function(){
               window.close();
  
      });
  });
</script>


