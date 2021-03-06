<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:if test="${not empty article }">
	<title>${article.articleTitle}_【5分钟，互联网最新资讯】</title>
</c:if>
<c:if test="${empty article }">
 <title>【5分钟，互联网最新资讯】</title>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/zoomer.css" rel="stylesheet" type="text/css"  media="screen" />
<link href="${context}/favicon.ico" mce_href="${context}/favicon.ico" rel="bookmark" type="image/x-icon" /> 
<link href="${context}/favicon.ico" mce_href="${context}/favicon.ico" rel="icon" type="image/x-icon" /> 
<link href="${context}/favicon.ico" mce_href="${context}/favicon.ico" rel="shortcut icon" type="image/x-icon" /> 
<!--[if IE 6]>
	<style type="text/css" media="screen">
	ul.thumb li img.hover {
	margin-top:15px;
	background:url(images/thumb_bg.gif) no-repeat center center;
	border: none;
}
ul.thumb li .title{color:#fff;width:185px;height:50px;margin:0;font-weight:900;background:url(images/title.gif) no-repeat center center;padding:17px 0 0 0;text-align:center;}
	</style>
<![endif]-->
</head>
<body>
<div class="main">
  <%--top --%> 
  <%@ include file="/WEB-INF/views/frame/top.jsp" %>
  <div class="cline"></div>
   <!--面包屑-->
  <div class="page-position">
	当前位置：<a target="_parent" href="${context}">5分钟</a>&nbsp;&gt;&nbsp;<a target="_parent" href="${context}/${articleKind.kindPinyin}/">${articleKind.kindName}</a>&nbsp;&gt;&nbsp;${article.articleTitle}
  </div>
  <!--面包屑  -->
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      <c:if test="${not empty article }">
       <div class="article">
          <h2>${article.articleTitle}</h2>
          <div class="cline"></div>
          <p>
	          <span>时间:&nbsp;<fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日" /></span>
	          <span style="margin-left:25px;">来源:&nbsp;${article.articleFrom}</span>
	          <span style="margin-left:25px;">作者:&nbsp;${article.articleAuthor}</span>&nbsp;&nbsp;
          	</p>
          <c:if test="${not empty article.articleUrl}">
         	 <img src="${article.articleUrl}" width="613" height="193" alt="${article.articleTitle}" />
          </c:if>
          <div class="cline"></div>
         	${article.articleContent}
         <!--  <p class="links"><a href="#" target="_blank">标签</a>,<a href="#" target="_blank">标签</a></p> -->
          <c:if test="${not empty preArticle and not empty nextArticle}">
	          <div class="nextContent">
		          <p style="font-size:12px;">
		          	   <b>上一篇:</b><a title="${preArticle.articleTitle}" href="${context}/${preArticle.kindPinYin}/${preArticle.articleId}.html" target="_blank">
		          	    <c:choose>
							<c:when test="${fn:length(preArticle.articleTitle) > 20}">
								${fn:substring(preArticle.articleTitle, 0,20)}...
							</c:when>
							<c:otherwise>
								${preArticle.articleTitle}
							</c:otherwise>
						</c:choose>
		          	   </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					   <b>下一篇:</b><a title="${nextArticle.articleTitle}" href="${context}/${nextArticle.kindPinYin}/${nextArticle.articleId}.html" target="_blank">
					  	 <c:choose>
							<c:when test="${fn:length(nextArticle.articleTitle) > 20}">
								${fn:substring(nextArticle.articleTitle, 0,20)}...
							</c:when>
							<c:otherwise>
								${nextArticle.articleTitle}
							</c:otherwise>
						</c:choose>
					   </a>
				  </p>
			  </div>
		  </c:if>
        </div>
         <!-- 多说评论框 start -->
         <div class="article">
			<div class="ds-thread" data-thread-key="${article.articleId}" data-title="${article.articleTitle}" data-url="${context}/${article.kindPinYin}/${article.articleId}.html">
			</div>
		 </div>	
		 <!-- 多说评论框 end -->
       </c:if>
       <c:if test="${ empty article }">
     	  <div class="article"><span>对不起，没有查询出数据！</span></div>
       </c:if>
        
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
				      <c:if test="${not empty recommendArticles.small}">
					      <li>
						      <a href="${context}/${recommendArticles.kindPinYin}/${recommendArticles.articleId}.html" target="_blank">
			          			  <img src="${context}/upload/${recommendArticles.small}" alt="${recommendtitle}" />
			          		  </a>
		          		  </li>
				      </c:if>
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
	  			 <li>
	  			 	<a href="${context}/${randomArticle.kindPinYin}/${randomArticle.articleId}.html" target="_blank" title="${randomArticle.articleTitle}">
	  				 	 <c:choose>
							<c:when test="${fn:length(randomArticle.articleTitle) > 16}">
								${fn:substring(randomArticle.articleTitle, 0,16)}...
							</c:when>
							<c:otherwise>
								${randomArticle.articleTitle}
							</c:otherwise>
						</c:choose>
	  			 	</a>
					<%--  <span>作者：${randomArticle.articleAuthor}</span> <span style="float:right">来源：${randomArticle.articleFrom}</span>   --%>            
				</li>         
		   </c:forEach>
          </ul>
        </div>
        </c:if>
        <!-- 百度广告位置1
        <div class="rightbox" style="background-image:url(${context}/images/baidu.jpg);height:250px;">
        </div>
         -->
         <c:if test="${not empty  randomReadList}">
      <div class="rightbox">
      		 <h2><span>精彩图文</span></h2>
      		  <c:forEach var="imgArticle" items="${randomReadList }" begin="3" end="5">
      		   <c:choose>
					<c:when test="${fn:length(imgArticle.articleTitle) > 20}">
						<c:set var="imgtitle" value="${fn:substring(imgArticle.articleTitle, 0,20)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="imgtitle" value="${imgArticle.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
	      	<div class="special_text_img">
				<div class="text_img_left">
					<div class="text_img_leftImg">
						<a target="_blank" title="${imgArticle.articleTitle}" href="${context}/${imgArticle.kindPinYin}/${imgArticle.articleId}.html">
							<img src="${imgArticle.articleUrl}" width="72px" height="72px"/>
						</a>
					</div>
				</div>
				<div class="text_img_right">
					<h4>
						<a target="_blank" title="${imgArticle.articleTitle}" href="${context}/${imgArticle.kindPinYin}/${imgArticle.articleId}.html">${imgtitle}</a>
					</h4>
					<p>
						  <c:choose>
							<c:when test="${fn:length(imgArticle.articleContent) > 20}">
								${fn:substring(imgArticle.articleContent, 0,20)}...
							</c:when>
							<c:otherwise>
								${imgArticle.articleContent}
							</c:otherwise>
						</c:choose>
					
					</p>
				</div>
			</div>
			</c:forEach>
      </div>
      </c:if>
         <c:if test="${not empty hotLabelList}">
        <div class="rightbox">
		  	 <h2><span>热门标签</span></h2>
          	 <div class="cline"></div>
	          	<div class="taglist">
					<c:forEach var="label" items="${hotLabelList }">
            	 		<a href="${context}/label/${label.labelId}.html" target="_blank" title="${label.labelName}">${label.labelName}</a>
                    </c:forEach>
				</div>
	          </div>
	          </c:if>
	       <!-- 百度广告位置2 
        <div class="rightbox" style="background-image:url(${context}/images/baidu2.jpg);height:250px;">
        </div>
      	  -->
      </div>
      <div class="cline"></div>
    </div>
  </div>
  <%--footer --%> 
  <%@ include file="/WEB-INF/views/frame/footer.jsp" %>
</div>
<script type="text/javascript" src="${context}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${context}/js/jquery.LoadImage.js"></script>
<script type="text/javascript" src="${context}/js/common.js"></script>
<script type="text/javascript" src="${context}/js/zoomer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 	$('ul.introduce li').Zoomer({speedView:200,speedRemove:400,altAnim:true,speedTitle:400,debug:false});
 	$(".main img").lazyload({
		placeholder : "${context}/images/loading.gif",
		effect      : "fadeIn"
	});
});
</script>
	<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
	<script type="text/javascript">
	var duoshuoQuery = {short_name:"min5"};
		(function() {
			var ds = document.createElement('script');
			ds.type = 'text/javascript';ds.async = true;
			ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
			ds.charset = 'UTF-8';
			(document.getElementsByTagName('head')[0] 
			 || document.getElementsByTagName('body')[0]).appendChild(ds);
		})();
		</script>
	<!-- 多说公共JS代码 end -->
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>