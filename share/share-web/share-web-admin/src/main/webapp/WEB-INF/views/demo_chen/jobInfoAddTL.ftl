<#include "/templates/head.ftl">
<#import "/templates/schedule/commonExpMacro.ftl" as commExp>
<#--模板body部分  -->
<body>
	<form method="post" id="schJobInfoForm">
		<#--调度任务基本信息-->
		<fieldset style="width: 98%" class="Main_fieldset" id="jobInfo">
			<@commExp.legend img=img! imageStyle="{width:20px;height:22px}" title="调度任务基本信息" />
		    <@titleFuncMacro titleContent="${titleContent!}"/>
		     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabTD-content">
				  <@qryContentMacro qryDataList=jobInfo! rowNum=rowNum!/>
			 </table>
		    <input type="hidden" name="scheduleJobInfoMVO.schJobId" value="${scheduleJobInfoMVO.schJobId!}" />
		    <input type="hidden" name="scheduleJobInfoMVO.schJobGrpId" value="${scheduleJobInfoMVO.schJobGrpId!}" />
		</fieldset>
		<#--任务类-->
		<fieldset style="width: 98%" class="Main_fieldset">
			<@commExp.legend img=img! imageStyle="{width:20px;height:22px}" title="任务类信息" />
			<table width="100%" align="center" cellspacing="0" cellpadding="0" class="tabTD-content" >
				<tr>
					<td align="right" style="width:18%">任务类名称：</td>
					<td align="left">
						<input type="text" name="scheduleJobInfoMVO.jobClassName" id="jobClassName" readonly="readonly" style="width: 30%">
						<span class='addIcon' id='showJobClass'/>
						<input type="hidden" name="scheduleJobInfoMVO.jobClassId" id="jobClassId" />
					</td>
				</tr>
			</table>
		</fieldset>
		<div id="jobParamList"></div>
		<#--调度任务依赖信息-->
		<fieldset style="width: 98%" class="Main_fieldset" id="jobRel">
			<@commExp.legend img=img! imageStyle="{width:20px;height:22px}" title="调度任务依赖信息" />
			<table width="100%" align="center" cellspacing="0" cellpadding="0" class="tabTD-content" >
				<@qryContentMacro qryDataList=typeIsJOB! rowNum=rowNum!/>
				<input type="hidden" name="scheduleJobInfoMVO.jobRelVO.relSchJobId" id="relSchJobId" value="${scheduleJobInfoMVO.jobRelVO.relSchJobId!}" />
			</table>
		</fieldset>
		<#--执行时间配置-->
	    <fieldset style="width: 98%" class="Main_fieldset" id="jobTime">
			<@commExp.legend img=img! imageStyle="{width:20px;height:22px}" title="执行时间信息" />
			<table width="100%" align="center" cellspacing="0" cellpadding="0" class="tabTD-content">
				<tr id="firstTr">
					<td width="120" rowspan="4">
						<@s.radio name="scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeCycle" id="exeCycle" list=exeCycleList listKey="key" listValue="value"></@s.radio>
					</td>
					<td width="140" align="right"><div id="timeMsg">开始执行时间：</div></td>
					<td><table width="100%" align="center" class="table1" id="exeTimeTable"></table></td>
				</tr>
				<tr id="secondTr" style="display:none">
					<td width="140" align="right">偏移周期：</td>
					<td>
						<div id="exeCycleOffsetDiv">
							每<input name="scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeCycleOffset" type="text" size="2"
								value="${(scheduleJobInfoMVO.jobTimerVO.scheduleJobTimer.exeCycleOffset)!}">
							<span id="dataType"></span>
						</div>
						<div id="wDiv" style="display:none">
							<@s.checkboxlist value="${(scheduleJobInfoMVO.jobTimerVO.weekStr)!}"
								name="scheduleJobInfoMVO.jobTimerVO.weekStr" list=exeWeekList listKey="key" listValue="value"></@s.checkboxlist>
						</div>
						<div id="dDiv" style="display:none">
							<@s.checkboxlist name="scheduleJobInfoMVO.jobTimerVO.mothDayStr"
								value="scheduleJobInfoMVO.jobTimerVO.mothDayArray" list=exeMothList listKey="key" listValue="value"></@s.checkboxlist>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">节假日(<font color="red">不执行</font>):</td>
					<td colspan="3"><input name="scheduleJobInfoMVO.holidayStr" type="text" value="${(scheduleJobInfoMVO.holidayStr)!}" readonly="readonly" />
					<span class='addIcon' id='showHoliday'/></td>
					<input name="scheduleJobInfoMVO.holidayIdStr" type="hidden" value="${(scheduleJobInfoMVO.holidayIdStr)!}" />
				</tr>
			</table>
		</fieldset>
	    <@buttonItemMacro params=buttonParams! style=style!"margin: 5px 1px 10px 5px; padding: 3px; text-align: center"!/>
	</form>
