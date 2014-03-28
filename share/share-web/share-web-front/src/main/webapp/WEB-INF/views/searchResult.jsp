<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${context}/css/zoomer.css" media="screen" />
<!--[if IE 6]>
<style type="text/css" media="screen">
	ul.introduce li img.hover {
	margin-top:15px;
	background:url(${context}/images/thumb_bg.gif) no-repeat center center;
	border: none;
}
ul.introduce li .title{color:#fff;width:185px;height:50px;margin:0;font-weight:900;background:url(${context}/images/title.gif) no-repeat center center;padding:17px 0 0 0;text-align:center;}
	</style>
<![endif]-->
</head>
<body>
<div class="main">
  <%--top --%> 
  <%@ include file="/WEB-INF/views/frame/listTop.jsp" %>
  <div class="cline"></div>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
         <%--内容加载于异步获取 --%>
		 <div id="Pagination" class="mypage">
      	 </div>
      	 <c:if test="${not empty recommendArticlesList }">
	        <div class="article" style="height:300px;">
	          <h2><span>精彩推荐</span></h2>
	          <div class="cline"></div>
	          <div class="photoBox">
	          <ul class="introduce">
	          	<c:forEach var="recommendArticles" items="${recommendArticlesList }">
	          		  <li><a href="${ recommendArticles.articleUrl}" target="_blank"><img src="${context}/images/robots.jpg" alt="${recommendArticles.articleTitle}" /></a></li>
	          	</c:forEach>
				   <%--    <li><a href="#"><img src="${context}/images/monster.jpg" alt="Monsters!" /></a></li>
				      <li><a href="#"><img src="${context}/images/santa.jpg" alt="Santa down under" /></a></li>
				      <li><a href="#"><img src="${context}/images/thumb6.jpg" alt="Sponguebob!" /></a></li>
				      <li><a href="#"><img src="${context}/images/thumb7.jpg" alt="Star Wars" /></a></li>
				      <li><a href="#"><img src="${context}/images/dino.png" alt="Dinosaur time" /></a></li>
				      <li><a href="#"><img src="${context}/images/orange.jpg" alt="Orange car" /></a></li>
				      <li><a href="#"><img src="${context}/images/alien.jpg" alt="Aliens!" /></a></li>
				      <li><a href="#"><img src="${context}/images/supe.jpg" alt="It's Superman!" /></a></li>
				      <li><a href="#"><img src="${context}/images/garfield.jpg" alt="Where's my lasagne?" /></a></li> --%>
	    		</ul>
	    		</div>
	        </div>
        </c:if>
      </div>
      <div class="sidebar">
      <div class="rightbox">
          <h2>Wise Words</h2>
          <div class="cline"></div>
          <p>   <img src="${context}/images/test_1.gif" alt="image" width="20" height="18" /> <em>We can let circumstances rule us, or we can take charge and rule our lives from within </em>.<img src="${context}/images/test_2.gif" alt="image" width="20" height="18" /></p>
          <p style="float:right;"><strong>Earl Nightingale</strong></p>
          </div>
       <c:if test="${not empty randomReadList}">   
       <div class="rightbox">
          <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>	
          <h2><span>猜你喜欢</span></h2>
          <div class="cline"></div>
          <ul class="ex_menu">
           <c:forEach var="randomArticle" items="${randomReadList }">
	  			 <li>
	  			 	<a href="${randomArticle.articleUrl}" target="_blank" title="${randomArticle.articleTitle}">${randomArticle.articleTitle}</a><br />
					      来源：${randomArticle.articleFrom}作者：${randomArticle.articleAuthor}             
				</li>         
		   </c:forEach>
          </ul>
        </div>
        </c:if>
        <!-- 百度广告位置1 -->
        <div class="rightbox" style="background-image:url(${context}/images/baidu.jpg);height:250px;">
        </div>
        <c:if test="${not empty hotLabelList}">
		<div class="rightbox">
		  	 <h2><span>热门标签</span></h2>
          	 <div class="cline"></div>
	          	<div class="taglist">
	          		<c:forEach var="label" items="${hotLabelList }">
            	 		<a href="#" target="_blank" title="${label.labelName}">${label.labelName}</a>
                    </c:forEach>
				</div>
	    </div>
	      </c:if>
	     <!-- 百度广告位置2 -->
        <div class="rightbox" style="background-image:url(${context}/images/baidu2.jpg);height:250px;">
        </div>     
      </div>
      <div class="cline"></div>
    </div>
  </div>
  <%--footer --%> 
  <%@ include file="/WEB-INF/views/frame/footer.jsp" %>
</div>
<script type="text/javascript" src="${context}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${context}/js/common.js"></script>
<script type="text/javascript" src="${context}/js/zoomer.js"></script>
<script type="text/javascript" src="${context}/js/jquery.pagination.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	var searchkey = '${searchKey}';
	$('.keywords').val(searchkey);
	
 	$('ul.introduce li').Zoomer({speedView:200,speedRemove:400,altAnim:true,speedTitle:400,debug:false});
 	
 	$("#Pagination").pagination('${totalArticleCount}', {
			 num_edge_entries: 2,//两侧显示的首尾分页的条目数
			 num_display_entries: 10,//连续分页主体部分显示的分页条目数
		 callback: pageSelectCallBack,//回调函数
		 items_per_page:10,//每页显示的条目数
	});
 	
    function pageSelectCallBack(page_index, jq){
    	var c = page_index+1;
    	var p = 10;
    	var k = $('.keywords').val();
    	var timestamp = Date.parse(new Date());
    	$.ajax({
 	       type: "GET",
 	       contentType : 'application/json;charset=UTF-8',
 	       url: '/s/key.mins',
 	       data: {t:timestamp,c:c,p:p,k:k},
 	       dataType: "json",
 	       success:function(result) {
 	    	  if(result == "error"){
 	    		   $(".mypage").before("<div class=\"articlelist\"><span>对不起，没有查询出数据！<span></div>");
		    	   $(".mypage").hide();
		       }else{
		    	   var content = "";
		    	   $.each(result,function(index,item){
		    		   content +="<div class=\"articlelist\">";
		    		   content +="<h2>"+item.articleTitle+"</h2>";
		    		   content +="<p><span>时间:"+item.createTime+"</span>&nbsp;&nbsp;<span>来源:"+item.articleFrom+"</span>&nbsp;&nbsp;<span>作者:"+item.articleAuthor+"</span>&nbsp;&nbsp;浏览（18）</p>";
		    		   content +="<div>";
		    		   content +="<img src=\"/images/img_1.jpg\" width=\"200\" height=\"100\" />";
		    		   content +="</div>";
		    		   content +=item.articleContent;
		    		   content +=" <p class=\"links\"><a href=\"#\" target=\"_blank\">标签/a>，<a href=\"#\" target=\"_blank\">标签/a></p>";
		    		    content +="</div>";
		    	   });
		    	   $(".mypage").before(content);
		       } 
 	    	   
 	       },
 	       error:function(data){
 	    	  $(".mypage").before("<div class=\"articlelist\"><span>对不起，没有查询出数据！<span></div>");
 	    	  $(".mypage").hide();
 	    	  
 	       }
 	});
        
        return false;
    }
 	
});
</script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
