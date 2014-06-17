<%@page import="com.cplatform.ad.common.util.Configger"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link href="${basepath}/css/guide.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	set_height();

	var pageIdV='${pageId}';
	if(pageIdV=="index0"){
		//--我的媒体
		if(getTagContent(1)!="")
		{
			var $mediaManage0=$("#a_mediaManage0").html();
			$mediaManage0=$mediaManage0+"<span id=\"guide1\" class=\"guide\" style=\"left:-150px;top:8px;width:200px;\"><span class=\"guide-right\" ><\/span><span class=\"guide-txt\" >"+getTagContent(1)+" <em onclick=\"javascript:closeTagSpan(1);\">知道了<\/em><\/span></span>";
			$("#a_mediaManage0").html($mediaManage0);
		}
	    //--我的推广
	    if(getTagContent(2)!="")
	    {
	    	var $promotersContent0=$("#a_promotersContent0").html();
	    	$promotersContent0=$promotersContent0+"<span id=\"guide2\" class=\"guide\" style=\"left:30px;top:8px;width:220px;\"><span class=\"guide-top\" ><\/span><span class=\"guide-txt\" >"+getTagContent(2)+" <em onclick=\"javascript:closeTagSpan(2);\">知道了<\/em><\/span></span>";
			$("#a_promotersContent0").html($promotersContent0);
	    }
	 	//--个人设置
	 	if(getTagContent(3)!="")
	 	{
			var $toEdit=$("#toEdit").html();
			$toEdit=$toEdit+"<span id=\"guide3\" class=\"guide\" style=\"left:-75px;top:25px;\"><span class=\"guide-top\" ><\/span><span class=\"guide-txt\" >"+getTagContent(3)+" <em onclick=\"javascript:closeTagSpan(3);\">知道了</em><\/span></span>";
			$("#toEdit").html($toEdit);
	 	}
 	}else if(pageIdV=="promotersContent0"&&pageType=="promotersContent"){

	 	//--我的推广-- 添加推广业务
	 	if(getTagContent(12)!="")
	 	{
			var $addBusi=$("#addBusi").html();
			$addBusi=$addBusi+"<span id=\"guide12\" class=\"guide\" style=\"left:-135px;top:5px;\"><span class=\"guide-right\" ><\/span><span class=\"guide-txt\" >"+getTagContent(12)+" <em onclick=\"javascript:closeTagSpan(12);\">知道了</em><\/span></span>";
			$("#addBusi").html($addBusi);
	 	}
	 	//--我的推广-- 数据分析
	 	if(getTagContent(16)!="")
	 	{
			var $data=$("#data").html();
			$data=$data+"<span id=\"guide16\" class=\"guide\" style=\"left:95px;top:-35px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+getTagContent(16)+" <em onclick=\"javascript:closeTagSpan(16);\">知道了</em><\/span></span>";
			$("#data").html($data);
	 	}
	 	//--我的推广-- 积分统计
	 	if(getTagContent(17)!="")
	 	{
			var $jifen=$("#jifen").html();
			$jifen=$jifen+"<span id=\"guide17\" class=\"guide\" style=\"left:95px;top:-85px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+getTagContent(17)+" <em onclick=\"javascript:closeTagSpan(17);\">知道了</em><\/span></span>";
			$("#jifen").html($jifen);
	 	}
	 	//--我的推广-- 业务到期
	 	if(getTagContent(15)!="")
	 	{
			var $terminCount=$("#terminCount").html();
			$terminCount=$terminCount+"<span id=\"guide15\" class=\"guide\" style=\"left:420px;top:50px;width:210px;\"><span class=\"guide-top\" ><\/span><span class=\"guide-txt\" >"+getTagContent(15)+" <em onclick=\"javascript:closeTagSpan(15);\">知道了</em><\/span></span>";
			$("#terminCount").html($terminCount);
	 	}
 	}else if(pageIdV=="finance0"){
 		//--账户信息
	 	if(getTagContent(18)!="")
	 	{
		 	if(${status<0})
		 	{
				var $toEdit=$("#financeManage").html();
				$toEdit=$toEdit+"<span id=\"guide18\" class=\"guide\" style=\"left:93px;top:-35px;z-index:999;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+getTagContent(18)+" <em onclick=\"javascript:closeTagSpan(18);\">知道了</em><\/span></span>";
				$("#financeManage").html($toEdit);
		 	}
	 	}
 	}else if(pageIdV=="mediaManage0"&&pageType=="mediaManage"){
		var  tagobj=getTagObj("4");
	     //如果未创建过媒体则显示
	     var guideMediaCount='${guideMediaCount}';
	     if(tagobj&&guideMediaCount<=0){
		     var $a_mediaManage_client=$("#a_mediaManage_client").html();
		     $a_mediaManage_client=$a_mediaManage_client+"<span id=\"guide4\"  class=\"guide\" style=\"left:-135px;top:8px;\"><span class=\"guide-right\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(4);\">知道了<\/em><\/span></span>";
		     $("#a_mediaManage_client").html($a_mediaManage_client);
	     }else{
	     	var quidePassMedia='${quidePassMedia}';
	     	if(quidePassMedia>0){
	     		tagobj=getTagObj("7");
				var guideChaCount='${guideChaCount}';
				//如果未点知道了并且没有频道数据时显示频道引导,点击知道了或有数据时不显示
				if(tagobj&&guideChaCount<=0){
		    		 var $a_channel=$("#a_channel").html();
				     $a_channel=$a_channel+"<span id=\"guide7\" class=\"guide\" style=\"left:-155px;top:8px;\"><span class=\"guide-right\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(7);\">知道了<\/em><\/span></span>";
				     $("#a_channel").html($a_channel);
		    	 }else{
		    	 	//存在频道数据
		    	 	//如果未点知道了并且没有位置数据时显示位置引导,点击知道了或有数据时不显示
		    	 	 tagobj=getTagObj("8");
			   	 	 var guidePosCount='${guidePosCount}';
				     if(tagobj&&guidePosCount<=0){
					     var $a_location=$("#a_location").html();
					     $a_location=$a_location+"<span id=\"guide8\" class=\"guide\" style=\"left:20px;top:8px;\"><span class=\"guide-top\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(8);\">知道了<\/em><\/span></span>";
					     $("#a_location").html($a_location);
				     }else{
				     	//未点击"知道了"
				     	//审核通过的媒体下面存在位置，并且位置没有业务申请时显示投放管理引导
				     	tagobj=getTagObj("6");
				     	var guideConCount='${guideConCount}';
					     if(tagobj&&guideConCount<=0){
						     var $a_mediaContent=$("#a_mediaContent").html();
						     $a_mediaContent=$a_mediaContent+"<span id=\"guide6\" class=\"guide\" style=\"left:93px;top:6px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(6);\">知道了<\/em><\/span></span>";
						     $("#a_mediaContent").html($a_mediaContent);
					     }
				     }
		    	 }
	     	}

	     }

	     //如果未点知道了并且存在积分收入则显示，直到点击知道了不显示
	     //没有积分收入也不显示
	     tagobj=getTagObj("11");
	     var guideInterCount='${guideInterCount}';
	     if(tagobj&&guideInterCount>0){
		     var $a_integral=$("#a_integral").html();
		     $a_integral=$a_integral+"<span id=\"guide11\" class=\"guide\" style=\"left:93px;top:6px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(11);\">知道了<\/em><\/span></span>";
		     $("#a_integral").html($a_integral);
	     }
	     //数据分析
	     tagobj=getTagObj("10");
	     var guideGenericeCount='${guideGenericeCount}';
	     if(tagobj&&guideGenericeCount>0){
		     var $a_mediaDataManage=$("#a_mediaDataManage").html();
		     $a_mediaDataManage=$a_mediaDataManage+"<span id=\"guide10\" class=\"guide\" style=\"left:93px;top:6px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(10);\">知道了<\/em><\/span></span>";
		     $("#a_mediaDataManage").html($a_mediaDataManage);
	     }
	}else if(pageIdV=="mediaManage0"&&pageType=="mediaContent"){
		var  tagobj=getTagObj("9");
	     //未点击知道了
	     //如果存在审核通过的wap类型的媒体，并且存在位置时显示
	     var guideMediaConCount='${guideMediaConCount}';
	     if(tagobj&&guideMediaConCount>0){
		     var $a_mediaContent=$("#a_mediaContent").html();
		     $a_mediaContent=$a_mediaContent+"<span id=\"guide9\" class=\"guide\" style=\"left:-135px;top:8px;\"><span class=\"guide-right\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(9);\">知道了<\/em><\/span></span>";
		     $("#a_mediaContent").html($a_mediaContent);
	     }
	}
    /**添加新媒体 频道信息提示*/
    var  tagobj=getTagObj("5");
    if(tagobj){
	     var $a_mediaManage_client_channel=$("#a_mediaManage_client_channel").html();
	     $a_mediaManage_client_channel=$a_mediaManage_client_channel+"<span id=\"guide5\" class=\"guide\" style=\"left:13px;top:-5px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(5);\">知道了<\/em><\/span></span>";
	     $("#a_mediaManage_client_channel").html($a_mediaManage_client_channel);
	     $("#chaaAction").click(function(){closeTagSpan(5);}) ;
    }
    /**添加新业务 定向设置信息提示*/
    tagobj=getTagObj("13");
    if(tagobj){
	     var $setDirectAction_span=$("#setDirectAction_span").html();
	     $setDirectAction_span=$setDirectAction_span+"<span id=\"guide13\" class=\"guide\" style=\"left:10px;top:-6px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(13);\">知道了<\/em><\/span></span>";
	     $("#setDirectAction_span").html($setDirectAction_span);
	     $("#setDirectAction").click(function(){closeTagSpan(13);}) ;
    }
    /**添加新业务 预算设置信息提示*/
    tagobj=getTagObj("14");
    if(tagobj){
	     var $budgetAction_span=$("#budgetAction_span").html();
	     $budgetAction_span=$budgetAction_span+"<span id=\"guide14\" class=\"guide\" style=\"left:10px;top:-6px;\"><span class=\"guide-left\" ><\/span><span class=\"guide-txt\" >"+tagobj.tagContent+"<em onclick=\"javascript:closeTagSpan(14);\">知道了<\/em><\/span></span>";
	     $("#budgetAction_span").html($budgetAction_span);
	     $("#budgetAction").click(function(){closeTagSpan(14);}) ;
    }
}) ;
/**获取提示语*/
function getTagObj(id){
	var showMsgList=eval(${showMsgList});
	for(var i=0;i<showMsgList.length;i++){
		if(id==showMsgList[i].id){
			return showMsgList[i];
		}
	}
	return null;
}
/**获取提示语内容*/
function getTagContent(id)
{
	var showMsgList=eval(${showMsgList});
	for(var i=0;i<showMsgList.length;i++)
	{
		if(id==showMsgList[i].id)
		{
			return showMsgList[i].tagContent;
		}
	}
	return "";
}
/**关闭提示语内容*/
function closeTagSpan(id)
{
	$("#guide"+id).hide(150);
	/**需要删除数据ID调用接口*/
	$.post("${basepath}/deleteTag",{'id':id,'time':new Date().getTime()},function(result){
		return true;
	  });
}
</script>
		<div class="footer">
			<div class="hd">
				<div class="copyright fl">© 2011 go.10086.cn</div>
				<div class="link fr">
					<a href="${basepath }/index/aboutus">关于平台</a> |
					<a href="${basepath }/index/service">服务条款</a> |
					<a href="${basepath}/index/document">帮助中心</a> |
					<a href="<%=Configger.getString("union_url")%>" target="_blank">冲浪联盟</a>
				</div>
			</div>
		</div>
	</body>
</html>