<#include "/templates/head.ftl"><#--模板head部分  -->
<#--引用宏 -->
<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 -->
<head>
<script language="javaScript">

	function liu(){
	
		var nodes=getCheckBoxTreeNodes();
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].isParent==false){
				alert(nodes[i].name);
			}
		}
	}
		
	function test(event, treeId, treeNode) {
		alert(treeNode.id + ", " + treeNode.name);
	}
	
	
</script>
</head>
<body>

<input type="button" onClick="liu();" value="确认"/>

<@dtt.checkboxtree name="主目录" classname="com.cattsoft.rdms.tree.service.Hello" clickmethodname="test"/>

</body>


</html>

