<#macro commJsMacro qryUrl qryBtton>
<script language="javascript" type="text/javascript">
	jQuery(function(){

		jQuery("#${qryBtton!}").click(function(){
		      queryPagination("operatDiv","${path}${qryUrl!}","frm");
		})
		jQuery("#${qryBtton!}").click();
	})
</script>
</#macro>

<#macro domainOption name tabName colName>
 <@dtt.dataCacheOption cacheName="table.cache.idlist.vdomain."+tabName+"."+colName name=name/>
</#macro>

<#macro domainStatus name tabName colName>
 <@dtt.dataCacheStatus cacheName="table.cache.idlist.vdomain."+tabName+"."+colName name=name/>
</#macro>


<#macro titleFuncMacro titleContent>
	             	<div class="curPosition"><span class="sys-nav"><em>当前位置：${titleContent!}</em></span></div>
</#macro>


<#macro commSaveJsMacro saveUrl>
<script language="javascript" type="text/javascript">
	$(function(){
		      
		      $("#saveButton").click(function(){
		      	   document.frm.action="${path}${saveUrl!}";
		      	   document.frm.submit();
		      })

	})
</script>
</#macro>


<#macro commUpdateJsMacro updateUrl>
<script language="javascript" type="text/javascript">
	$(function(){
		      
		      $("#saveButton").click(function(){
		      	   document.frm.action="${path}${updateUrl!}";
		      	   document.frm.submit();
		           //queryPagination("operatDiv","${path}${qryUrl!}","frm");
		      })

	})
</script>
</#macro>


<#macro buttonItemMacro params style>
   <#if params?exists>
     <div style="${style!}">
		<#list params?sort_by("seq") as element>
	          <input type="button" value="${element.content}" id="${element.id}" class="btn6"/>
			  <#if (element.required)??>
			          <span class="required" ></span>
			  </#if>
	    </#list>
	  </div>
   </#if>    
</#macro>


<#macro legendMacro titleContent>
	<legend>
	    <span class="title_text">
	        <img src="../images/icon_1.gif"/>
	        <strong>${titleContent!}</strong>
	    </span>
	</legend>
</#macro>


<#macro showDivMacro operatDiv>
		<center>
			<div id="${operatDiv!}"></div>
		</center>
</#macro>

<#macro qryContentMacro qryDataList rowNum>
			<tr>
			     <#if qryDataList?exists>
			         <#assign x=0>
				      <#list qryDataList?sort_by("seq") as element>
				             <#if element.type !="hidden">
		                        <td width="12%" align="right" id="${element.id!}">
		                           ${element.content!}
		                        </td>
		                      </#if>
	                         
							 <#if (element.oneTR)??>
						        <td colspan=${(rowNum-(qryDataList?size%rowNum))*2+1} id="${element.id!}">
							 <#elseif element.type !="hidden">
							 <td id="${element.id!}">
							 <#else>
							 </#if>
							 
                             <@elementConvert element/>							  
				            </td>
				    
					 <#if (x+1)%rowNum==0>
					    </tr>
					    <tr>
				     </#if>
				     <#if element.type !="hidden">
				     <#assign x=x+1>
				     </#if>

	              </#list>
                </#if>
               </tr>
                
</#macro>


<#macro elementConvert element>
	 <#if element.type == "checkboxlist">
		<@s.checkboxlist name=element.name! disabled=disabled list=element.list! listKey=element.listKey! listValue=element.listValue value=element.value/>
		<#if (element.oneTR)??></tr><tr></#if> 
		<#if (element.required)??><span class="required"/></#if>
	 </#if>
     
	 <#if element.type =="hidden">	          
	      <@s.hidden   name=element.name! style=element.style! rows=element.rows! disabled=element.disabled!/>&nbsp;
	      <#if (element.required)??>
	      	<span class="required" ></span>
	      </#if>
	       <#if (element.oneTR)??>
	    	</tr><tr>
	 	   </#if>
	      
	 </#if>
	 
	 <#if element.type =="text">
	  	<@s.textfield  cssStyle=element.cssStyle! name=element.name! size=element.size! disabled=element.disabled! readonly=element.readonly!/>
	   	<#if (element.img)??>
	   		${element.img}
	   	</#if>
		<#if (element.required)??>
			<span class="required" ></span>
		</#if>
	    <#if (element.oneTR)??>
	    	</tr><tr>
	 	</#if>
	 </#if>

	 <#if element.type =="view">
	    <@s.property  value=element.name! />&nbsp&nbsp&nbsp
	    <#if (element.oneTR)??>
	    	</tr><tr>
	 	</#if>
	 </#if>
	 
    <#if element.type =="domainStatus">
    	<@domainStatus name=element.propertyName! tabName=element.tabName! colName=element.colName!/>
    </#if>
    
    <#if element.type =="dataCacheStatus">
    	<@dtt.dataCacheStatus name=element.name! cacheName=element.cacheName! />
    </#if>
    
	 <#if element.type =="password">
	  	<@s.password   name=element.name! style=element.style! rows=element.rows! disabled=element.disabled!/>
	     <#if (element.required)??>
	     	<span class="required" ></span>
	     </#if>
	 </#if>
	                      
    <#if element.type =="selectDomain">
		<#if element.name?exists>
	    	<@domainOption  name=element.name! tabName=element.tabName! colName=element.colName! />
	    	<#if (element.required)??>
	     		<span class="required" ></span>
	     	</#if>
	     	<#if (element.oneTR)??>
	        	</tr><tr>
	      	</#if>
	    </#if>
	</#if>
	
    <#if element.type =="selectRegion">
     	<#if element.name?exists>
           	<@dtt.dataCacheOption cacheName=element.cacheName! name=element.name!/>
        	<#if (element.required)??>
          		<span class="style2">*</span>
         	</#if>
       		<#if (element.oneTR)??>
             	<#assign y=1>
         	</#if>
     	</#if>
    </#if> 
            
	<#if element.type =="select">
		<#if element.name?exists>
	       <@s.select  name=element.name! disabled=element.disabled! list=element.list! cssStyle=element.cssStyle! />
	        <#if (element.required)??>
	        	<span class="required" ></span>
	        </#if>
	        <#if (element.oneTR)??>
	        	</tr><tr>
	        </#if>
	    </#if>
	</#if>
	 
	<#if element.type =="timeRange">
	  <#if element.name?exists>
		    <#list element.name?split(",") as dateEle>
		          <input type="text" name=${dateEle!} class="Wdate" onfocus="WdatePicker({dateFmt:'${element.format}'})"
		         value='<#if element.value??><@s.property value=element.value/></#if>' >
		            <#if dateEle_index!=element.name?split(",")?size-1>
		                     ----
		            </#if>
		    </#list> 
		    <#if (element.required)??>
		    	<span class="required" ></span>
		    </#if>
		    <#if (element.oneTR)??>
		    	</tr><tr>
		    </#if>   
	  </#if>
	</#if>
	
  
    <#if element.type =="textarea">
		<@s.textarea   name=element.name! style=element.style! rows=element.rows! disabled=element.disabled!/>
	    <#if (element.required)??>
	    	<span class="required" ></span>
	    </#if>
	    <#if (element.oneTR)??>
		    	</tr><tr>
		</#if>
	</#if>
	
	
    <#if element.type =="button">
          <input type="button" value="${element.value}" id="${element.id}" class="btn6"/>
	      <#if (element.required)??>
	      <span class="required" ></span>
	      </#if>
	</#if>
	
	<#if element.type =="date">
	   <@s.date name="${element.name!}" format="${element.format!}" />
	    <#if (element.required)??>
	      <span class="required" ></span>
	    </#if>
   </#if>
	
</#macro>

<#macro resultListShowMacro showListResult pagingResultName oprateLinks columnHead>
	    <#if showListResult?exists>
	      <tr>
		     <#list showListResult as list >
		        <th>${list.title}</th>
		     </#list>
		     <#if columnHead??><#if (columnHead?length>0)><th>${columnHead}</th></#if></#if>
		   </tr>
	    </#if>
     
     <#if pagingResultName??>
       <@s.if test="${pagingResultName}.resultList!=null">
			 <@s.iterator value="${pagingResultName!}.resultList" status="objStat">
	              <tr>
		             <#list showListResult as list >
		             	<#if list.type?exists && list.type == "radio">
		             	   <td width="5%" align="center">
		             	     <input type ="radio" id="${list.name}" name ="${list.name}" value="<@s.property value="${list.name!}"/>"/>
		             	   </td>
		             	<#elseif list.type?exists && list.type == "checkBox">
		             		 <td width="5%" align="center">
		             	       <input type ="checkBox" id="${list.name}" name ="${list.name}" value="<@s.property value="${list.name!}"/>"/>
		             	     </td>
		             	<#else>
				        <td  nowrap="nowrap" align="center">
				 		
				        <#if list.type?exists>
				        	
					        <#if list.type =="domainStatus">
					        	<@domainStatus  name="${list.propertyName!}" tabName="${list.tabName!}" colName="${list.colName!}"/>
					        </#if>
					         
					       
					        <#if list.type =="dataCacheStatus">
					        	<@dtt.dataCacheStatus  name="${list.name!}" cacheName="${list.cacheName}"/>
					        </#if>
					       	
					        <#if list.type =="date">
					        	<@s.date name="${list.name!}" format="${list.format!}" />
					        </#if>
					        
					        <#if list.type =="hidden">
					            <@s.hidden   name="${list.name!}"/>
					        </#if>
					    
					    <#else>
					    
					    	<@s.property value="${list.name!}"/>
				        </#if>
				        </td>
				        </#if>
				     </#list>
				     <#if oprateLinks??>
				     <#if (oprateLinks?size>0) >
				     <td align="center">
				  	 <#list oprateLinks as oprateLink>
				  	 	<@s.if test="${oprateLink.disableConf!}">
				  	 	<a disabled=disabled href="#">${oprateLink.title!}</a>
				  	 	</@s.if> 
				  	 	<@s.else>
				  	 	<a href="javascript:${oprateLink.methodname!}(<#list oprateLink.param?split(",") as olk>'<@s.property value="${olk!}"/>',</#list>'dual');">${oprateLink.title!}</a>
				  	 	</@s.else>
				  	 </#list>
				  	 </td>
				  	 </#if>
				  	 </#if>
				  </tr>    
		    </@s.iterator>
       </@s.if>
       </#if>
</#macro>

<#macro richeditor id name width height>
 <@dtt.richeditor id=id! name=name! height=width! width=height! />
</#macro>