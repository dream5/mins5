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
         <c:if test="${not empty articlesList}">
      	  <c:forEach var="article" items="${articlesList }">
      	 	<div class="articlelist">
         		 <h2>
         		 	<c:choose>
						<c:when test="${fn:length(article.articleTitle) > 20}">
							<c:out value="${fn:substring(article.articleTitle, 0,20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${article.articleTitle}" />
						</c:otherwise>
					</c:choose>
         		</h2>
         		 <div class="cline"></div>
          		<p>
	          		<span>时间:${article.createTime}</span>&nbsp;&nbsp;<span>来源:${article.articleFrom}</span>
	          		&nbsp;&nbsp;<span>作者:${article.articleAuthor}</span>&nbsp;&nbsp;浏览（18）
          		</p>
          		<c:if test="${not empty article.articleTitle}">
          			 <img src="${article.articleUrl}" width="613" height="193" alt="${article.articleTitle}" />
          		</c:if>
         		<div class="cline"></div>
		         <c:choose>
					<c:when test="${fn:length(article.articleContent) > 200}">
						${fn:substring(article.articleContent, 0,200)}...
					</c:when>
					<c:otherwise>
						${article.articleContent}"
					</c:otherwise>
				</c:choose>
	          <p class="links"><a href="${contex}/${article.kindPinYin}/${article.articleId}.html" target="_blank">继续阅读</a></p>
			  <!-- baidu share begin-->
				  <div class="bdsharebuttonbox" style="float:right;">
					<a href="#" class="bds_more" data-cmd="more"></a>
					<a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
					<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a>
					<a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
					<a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a>
					<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a>
				 </div>
			 <!-- baidu share end-->
	        </div>
        	</c:forEach>
      	 </c:if>
      	 <c:if test="${ empty articlesList}">
      	 	<div class="articlelist"><span>对不起，没有查询出数据！</span></div>
      	 </c:if>
      
       <%--分页标签开始 --%>
       <pageUtil:pageHandler currPage="${currentPage}" link="/s?" var="pageTag" pageSize="${pageSize}" totalResults="${totalArticleCount}"></pageUtil:pageHandler>
       ${pageTag}
       <%--分页标签结束 --%>
      	 <c:if test="${not empty recommendArticlesList }">
	        <div class="article" style="height:300px;">
	          <h2><span>精彩推荐</span></h2>
	          <div class="cline"></div>
	          <div class="photoBox">
	          <ul class="introduce">
	          	   <c:forEach var="recommendArticles" items="${recommendArticlesList }">
			     <c:choose>
					<c:when test="${fn:length(recommendArticles.articleTitle) > 10}">
						<c:set var="recommendtitle" value="${fn:substring(recommendArticles.articleTitle, 0,10)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="recommendtitle" value="${recommendArticles.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
	          		  <li><a href="${context}/${recommendArticles.kindPinYin}/${recommendArticles.articleId}.html" target="_blank">
	          		  <img src="${context}/images/robots.jpg" alt="${recommendtitle}" />
	          		  </a>
	          		  </li>
	          	</c:forEach>
	    		</ul>
	    		</div>
	        </div>
        </c:if>
      </div>
      <div class="sidebar">
       <c:if test="${not empty randomReadList}">   
       <div class="rightbox">
          <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>	
          <h2><span>猜你喜欢</span></h2>
          <div class="cline"></div>
          <ul class="ex_menu">
          <c:forEach var="randomArticle" items="${randomReadList }">
             <c:choose>
					<c:when test="${fn:length(randomArticle.articleTitle) > 30}">
						<c:set var="randomtitle" value="${fn:substring(randomArticle.articleTitle, 0,30)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="randomtitle" value="${randomArticle.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
            	 <li><a href="${context}/${randomArticle.kindPinYin}/${randomArticle.articleId}.html" target="_blank" title="${randomtitle}">${randomtitle}</a></li>
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
<script type="text/javascript">
$(document).ready(function(){
	
	var searchkey = '${searchKey}';
	$('.keywords').val(searchkey);
 	$('ul.introduce li').Zoomer({speedView:200,speedRemove:400,altAnim:true,speedTitle:400,debug:false});
 	
 	$(window).toTop({
		showHeight : 100,//设置滚动高度时显示
		speed : 500 //返回顶部的速度以毫秒为单位
	});
});
</script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
