<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 --> 

<#--普通下拉树的 宏定义  name,classname,clickMethodName都为必需的参数-->
<#macro normalSelectTreeMacro name classname clickmethodname>
	<div id="treeDiv" class="dtree" sytle="width:100%;overflow-y:scroll" 
		onscroll="treeDiv.scrollTop = this.scrollTop;treeDiv.scrollLeft = this.scrollLeft;">
		<@dtt.tree name="${name}" classname="${classname}"
	 		clickmethodname="${clickmethodname}"/>
	</div>
</#macro>