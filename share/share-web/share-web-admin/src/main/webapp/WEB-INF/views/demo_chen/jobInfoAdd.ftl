<#--任务基础信息部分  -->
<#assign path=request.contextPath>
<script src="${path}/js/schedule_validator.js"></script>
<script src="${path}/js/schedule_util.js"></script>
<#assign jobInfo=[{"content":"调度任务分组名称：","name":"scheduleJobInfoMVO.groupName","type":"text","seq":"1","id":"groupName","cssStyle":"width:68%","img":"<span class='addIcon' id='showTree'/>","readonly":"true"},
                      {"content":"调度任务名称： ","name":"scheduleJobInfoMVO.schJobName","type":"text","required":"true","seq":"2","cssStyle":"width:68%","required":"true","cssStyle":"width:68%"},
                      {"content":"调度任务编码：","name":"scheduleJobInfoMVO.schJobCode","type":"text","seq":"3","required":"true","cssStyle":"width:68%"}, 
                      {"content":"调度类型： ","name":"scheduleJobInfoMVO.scheduleType","seq":"4","type":"selectDomain","tabName":"SCHEDULE_JOB_INFO","colName":"SCHEDULE_TYPE"},
                      {"content":"生效时间： ","name":"scheduleJobInfoMVO.effDate","seq":"5","type":"timeRange","format":"yyyy-MM-dd","id":"effDate","required":"true"},
                      {"content":"失效时间： ","name":"scheduleJobInfoMVO.expDate","type":"timeRange","seq":"6","format":"yyyy-MM-dd","id":"expDate"},
                      {"content":"线程数： ","name":"scheduleJobInfoMVO.threadCount","type":"text","seq":"7","oneTR":"true","required":"true","id","threadCount"},
                      {"content":"调度任务描述： ","name":"scheduleJobInfoMVO.jobDesc","type":"textarea","seq":"8","oneTR":"true"}]
         rowNum=2 />
<#--时间调度OR任务调度 -->
<#--任务调度-->
<#assign typeIsJOB=[{"content":"依赖调度任务名称：","name":"scheduleJobInfoMVO.jobRelVO.relSchJobName","type":"text","seq":"1","cssStyle":"width:68%","img":"<span class='addIcon' id='showSchJob'>","id":"relSchJobName"},
                    {"content":"依赖类型： ","name":"scheduleJobInfoMVO.jobRelVO.relType","type":"selectDomain","tabName":"SCHEDULE_JOB_REL","colName":"REL_TYPE","seq":"2","cssStyle":"width:68%"}]
         rowNum=2 />
<#--节假日情况-->
<#assign JobHolidayList= "scheduleJobInfoMVO.scheduleJobHolidayList"/>
<#--BUTTON-->
<#assign  buttonParams=[{"content":"保&nbsp;&nbsp;存","id":"saveButton","seq":"1"},
						{"content":"取&nbsp;&nbsp;消","id":"closeButton","seq":"2"}]
			  />
<#--集合-->
<#assign  exeMothList= {'1':'1日','2':'2日','3':'3日','4':'4日','5':'5日','6':'6日',
						'7':'7日','8':'8日','9':'9日','10':'10日','11':'11日','12':'12日',
						'13':'13日','14':'14日','15':'15日','16':'16日','17':'17日','18':'18日',
						'19':'19日','20':'20日','21':'21日','22':'22日','23':'23日','24':'24日','25':'25日',
						'26':'26日','27':'27日','28':'28日','29':'29日','30':'30日','31':'31日','0':'最后一天'}
		  exeWeekList= {'1':'星期一','2':'星期二','3':'星期三','4':'星期四','5':'星期五','6':'星期六','7':'星期日'}
		  exeCycleList = {'O':'一次','D':'每天 ','W':'每周','M':'每月','R':'实时'}
			  />
<#--导入模板-->
<#include "/templates/schedule/jobInfoAddTL.ftl">

<#--自定义JS-->
<script language="javascript" type="text/javascript">
	$('#groupName').css('width','20%');
	$('#relSchJobName').css('width','20%');
	$('#relSchJobName').next().css('width','30%');
	$('#closeButton').bind('click',function(){
		self.close();
	});

	/*************************************************************/
 	/*************************************************************/
	/**********************非空校验*******************/
	/*****统一非空验证**********/
	var validatorUtil = new Validator();
	validatorUtil.Required.groupName = new Array("scheduleJobInfoMVO.groupName", "调度任务分组名称不能为空!");
	validatorUtil.Required.schJobName = new Array("scheduleJobInfoMVO.schJobName", "调度任务名称不能为空!");
	validatorUtil.Required.schJobCode = new Array("scheduleJobInfoMVO.schJobCode", "调度任务编码不能为空!");
	validatorUtil.Required.scheduleType = new Array("scheduleJobInfoMVO.scheduleType", "请选择调度类型!");
	validatorUtil.Required.effDate = new Array("scheduleJobInfoMVO.effDate", "生效时间不能为空!");
	validatorUtil.Required.threadCount = new Array("scheduleJobInfoMVO.threadCount", "线程数不能为空!");
	validatorUtil.Required.jobClassName = new Array("scheduleJobInfoMVO.jobClassName", "任务类名称不能为空!");
	validatorUtil.Maxlength.jobDescLength = new Array("scheduleJobInfoMVO.jobDesc","调度任务描述的最长长度为100",new Function("varName","this.maxlength=100;return this[varName];"));
	
	var IsMultiThread = "";//是否支持多线程(选择任务类的时候)
	$(function() {
		$("#saveButton").bind("click",function() {
			if (!validatorUtil.validate()) {
				return;
			}
			//alert("统一非空验证通过");
			//生效时间不能大于失效时间
			var effDate = $("input[name='scheduleJobInfoMVO.effDate']").val();
			var expDate = $("input[name='scheduleJobInfoMVO.expDate']").val();
			if($.trim(expDate) != ''){
				var effArray = effDate.split("-");
				var expArray = expDate.split("-");
				var eff = new Date(effArray[0],effArray[1],effArray[2]);
				var exp = new Date(expArray[0],expArray[1],expArray[2]);
				if(eff > exp){
					alert("生效时间不能大于失效时间");
					return;
				}
			}
			//alert("时间校验通过");
			//如果支持多线程
			var threadCount = $("input[name='scheduleJobInfoMVO.threadCount']").val();
			if(IsMultiThread == '' || IsMultiThread == '是'){
				if(!isNaN(threadCount)&&(threadCount<1)){
					 alert("线程数的最小值是1");
					 return;
				}else if(isNaN(threadCount)&&threadCount.indexOf(".")>-1){
				     alert("线程数必须是>=1正整数");
				     return;
				}
			}else{
				if(threadCount!=1){
					alert("线程数只能为1");
					return;
				}
			}
			//alert("线程数校验通过");
			/******根据任务类参数是否为空来判断参数值*******/
			var msg = '';
			$('#jobParamTable tr:gt(0)').each(function(index){
				var isEmptyNode = $(this).find("input[name$='isEmpty']");
				var isEmpty = isEmptyNode.val();
				if(isEmpty == 'N'){
					var val = $(this).find("[name$='paramValue']").val();
					if($.trim(val) == ''){
						msg = msg+'\n参数列表的第'+(index+1)+'行参数值不能为空！';
					}
				}
			});
			if($.trim(msg) != ''){
				alert(msg);
				return;
			}
			/******根据调度类型来分别验证*******/
			var scheduleType = $("select[name='scheduleJobInfoMVO.scheduleType']").val();
			//如果调度类型是：任务调度    依赖调度任务名称,和依赖类型都不能为空
			if(scheduleType=='J'){
				var relSchJobName = $("input[name='scheduleJobInfoMVO.jobRelVO.relSchJobName']").val();
				var relType=$("select[name='scheduleJobInfoMVO.jobRelVO.relType']").val();
				if($.trim(relSchJobName) != ''){
					if($.trim(relSchJobName) == ''){
						alert("依赖类型不能为空!");
						return;
					}
				}
			
			//如果调度类型是：时间调度
			}else if(scheduleType=='T'){
				//执行时间类型
				var isSel = checkIsSel("scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeCycle","请选择执行时间类型!");
				if(!isSel){
					return;
				}
				//开始执行时间是否为空
				var exeTime = $("#exeTime").val();
				if($.trim(exeTime) == ''){
					alert("开始执行时间不能为空!");
					return;
				}
				
				var exeCycle = $("[name='scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeCycle']:checked").val();
				if(exeCycle != 'O'){
					var exeCycleOffset = $("input[name='scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeCycleOffset']").val();
					
					if($.trim(exeCycleOffset) == ''){
						alert("时间偏移不可以为空！");
						return;
					}
					if(isNaN(exeCycleOffset) || exeCycleOffset<0){
						alert("时间偏移必须是数字且要大于零！")
						return;
					}
					if(exeCycle == 'W'){
						var weekStr = '';
						$("input[name='scheduleJobInfoMVO.jobTimerVO.weekStr']:checked").each(function(){
							weekStr = weekStr+','+$(this).val();
						});
						weekStr =weekStr.substring(1);
						if($.trim(weekStr) == ''){
							alert("请选择星期!");
							return;
						}
					}else if(exeCycle == 'M'){
						var mothDayStr = '';
						$("input[name='scheduleJobInfoMVO.jobTimerVO.mothDayStr']:checked").each(function(){
							mothDayStr = mothDayStr+','+$(this).val();
						});
						mothDayStr =mothDayStr.substring(1);
						if($.trim(mothDayStr) == ''){
							alert("请选择月日期!");
							return;
						}
					}
				}
			}
			var url = "${path}/sch/jobInfo_doAdd.action";
			$.post(url, $("#schJobInfoForm").formSerialize(), function(resultData){
				alert("新增任务信息成功！");
				window.close();
				window.returnValue = 'OK';
			});
		});
		
		//初始化页面
		selectCycle();
		selectType();
		
	});	
							
 	/******选择周期*****/
	 function selectCycle() {
		var exeTimeTable = $("#exeTimeTable");
		exeTimeTable.empty();
		cycleId=$("input[id^=exeCycle]:checked").val();
		if(cycleId==''||cycleId==undefined){
			return;
		}
		var fullDateTr = "<tr><td><input type=\"text\" id=\"exeTime\" name=\"scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeTime\" class=\"Wdate\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'sysdate'})\" value=\"${(scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeTime)!}\"/><span class=\"required\"></span></td></tr>";
	    var timeTr="<tr><td><input type=\"text\" id=\"exeTime\" name=\"scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeTime\" class=\"Wdate\" readonly=\"readonly\" onfocus=\"WdatePicker({dateFmt:'HH:mm:ss'})\" value=\"${(scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeTime)!}\"/><span class=\"required\"></span></td></tr>";
	    if(cycleId=="O"){
	       $('#firstTr').show();
	       $('#secondTr').hide();
	  	   $("#timeMsg").html("开始执行时间：");
	       exeTimeTable.empty();
	       exeTimeTable.append(fullDateTr);
		   $("#exeTime").val("");
	    }
	   if(cycleId=="D"){//每天
	     $('#firstTr').show();
	     $('#secondTr').show();
	   	 $("#timeMsg").html("开始执行时间：");
	   	 exeTimeTable.empty();
	     exeTimeTable.append(timeTr);
	     $("#dataType").html("天");
	     $("#wDiv").hide();
	     $("#dDiv").hide();
	 	 $("#exeTime").val("");
	  }
	  if(cycleId=="W"){//每周
	  	 $('#firstTr').show();
	     $('#secondTr').show();
	  	 $("#timeMsg").html("开始执行时间：");
	  	 exeTimeTable.empty();
	     exeTimeTable.append(timeTr);
	     $("#dataType").html("周");
	     $("#wDiv").show();
	     $("#dDiv").hide();
	     $("#exeTime").val("");
	  }
	  if(cycleId=="M"){//每月
	  	 $('#firstTr').show();
	     $('#secondTr').show();
	     $("#timeMsg").html("开始执行时间：");
	     exeTimeTable.empty();
	     exeTimeTable.append(timeTr);
	     $("#dataType").html("月");
	     $("#wDiv").hide();
	     $("#dDiv").show();
		 $("#exeTime").val("");
	  }
			  
	  if(cycleId=="R"){//实时
	 	 $('#firstTr').show();
	     $('#secondTr').show();
	     $("#timeMsg").html("开始执行时间：");
	     exeTimeTable.empty();
	     exeTimeTable.append(timeTr);
	     $("#dataType").html("秒");
	     $("#wDiv").hide();
	     $("#dDiv").hide();
	 	 $("#exeTime").val("");
	  }
   }
   $("input[id^=exeCycle]").bind('click',selectCycle);
   /******选择类型*****/
	 function selectType() {
		var scheduleType = $("select[name='scheduleJobInfoMVO.scheduleType']").val();
		if(scheduleType=='T'){
			$("#jobTime").show();
			$("#jobRel").hide();
		}	 
		if(scheduleType=='J'){
			$("#jobRel").show();
			$("#jobTime").hide();
		}
	}
	 $("select[name='scheduleJobInfoMVO.scheduleType']").change(selectType);
 	/******选择任务分组名称*****/
 	function showTree(){
 		 var url = "${path}/sch/MonitorManage_tree.action";
         var winParm = "dialogHeight: 250px; dialogWidth: 200px; center: yes; help: no;status:yes;title:no;scroll:no;resizable:yes";
         var retVal = window.showModalDialog(url,window,winParm);
	     if(retVal==''||retVal==undefined){
	       return;
	     }
	     var ret=eval('('+retVal+')');
	     if($.trim(ret.name) != '')
	     $("input[name='scheduleJobInfoMVO.groupName']").attr('value',ret.name);
	     if($.trim(ret.id) != '')
	     $("input[name='scheduleJobInfoMVO.schJobGrpId']").attr('value',ret.id);
 	}
 	$('#showTree').bind('click',showTree);
 	/******展示任务类参数*****/
 	function showJobParamList(jobClassId){
 		var url = '${path}/sch/jobClass_selectParamList.action?jobClassId='+jobClassId;
 		$("#jobParamList").load(url);
 	}
 	/******选择任务类*****/
 	function showJobClass(){
 		var url="${path}/sch/jobClass_selectMain.action";
 		var winParm = "dialogHeight: 500px; dialogWidth: 1200px; center: yes; help: no;status:yes;title:no;scroll:no;resizable:yes";
        var retVal = window.showModalDialog(url,window,winParm);
        if(retVal == undefined){
        	return;
        }
        var data = eval('('+retVal+')');
	    if($.trim(data.jobClassId) != ''){
	    	$("#jobClassId").val(data.jobClassId);
	    	$("#jobClassName").val(data.jobClassName);
	   		IsMultiThread = data.multiThread;//是OR否
	   		showJobParamList(data.jobClassId);
	    }
 	}
 	$('#showJobClass').bind('click',showJobClass);
 	
 	/******选择依赖任务*****/
 	function showSchJob(){
 		var url="${path}/sch/jobInfo_selectMain.action";
 		var winParm = "dialogHeight: 500px; dialogWidth: 1200px; center: yes; help: no;status:yes;title:no;scroll:no;resizable:yes";
        var retVal = window.showModalDialog(url,window,winParm);
        if(retVal == undefined || retVal==''){
        	return;
        }
        var data = eval('('+retVal+')');
        if($.trim(data.schJobId) != '')
        $("#relSchJobId").val(data.schJobId);
        if($.trim(data.schJobName) != '')
	    $("input[name='scheduleJobInfoMVO.jobRelVO.relSchJobName']").val(data.schJobName);
 	}
 	$('#showSchJob').bind('click',showSchJob);
 	
 	/******选择节假日*****/
 	function showHoliday(){
 		var url="${path}/sch/HolidayManage_mainOut.action";
 		var winParm = "dialogHeight: 500px; dialogWidth: 1200px; center: yes; help: no;status:yes;title:no;scroll:no;resizable:yes";
        var retVal = window.showModalDialog(url,window,winParm);
        if(retVal == undefined){
        	return;
        }
        var data = eval('('+retVal+')');
        if($.trim(data.holidayIdStr) != '')
        $("input[name='scheduleJobInfoMVO.holidayIdStr']").val(data.holidayIdStr);
        if($.trim(data.holidayStr) != '')
	    $("input[name='scheduleJobInfoMVO.holidayStr']").val(data.holidayStr);
 	}
 	$('#showHoliday').bind('click',showHoliday);
 	/*******************************************************/
</script>