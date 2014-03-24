<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>详情页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${context}css/zoomer.css" rel="stylesheet" type="text/css"  media="screen" />
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
  <%@ include file="/WEB-INF/views/frame/listTop.jsp" %>
  <div class="cline"></div>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      <c:if test="${not empty article }">
       <div class="article">
          <h2>${article.articleTitle}</h2>
          <div class="cline"></div>
          <p><span>时间:${article.createTime}</span>&nbsp;&nbsp;<span>来源:${article.articleFrom}</span>&nbsp;&nbsp;<span>作者:${article.articleAuthor}</span></a></p>
          <img src="images/img_1.jpg" width="613" height="193" alt="image" />
          <div class="cline"></div>
         	${article.articleContent}
          <p class="links"><a href="#" target="_blank">标签</a>,<a href="#" target="_blank">标签</a></p>
          <div class="nextContent">
	          <p><b>上一篇:</b><a href="#">2013年全国移动支付业务...</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   <b>下一篇:</b> <a href="#">苹果新专利：智能健康监测...</a>
			  </p>
		  </div>
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
       </c:if>
        <!-- <div class="article">
          <h2><span>3</span> 评论（暂不接入）</h2><div class="cline"></div>
          <div class="comment">
            <a href="#"><img src="images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>
            <p><a href="#">admin</a> Says:<br />April 20th, 2009 at 2:17 pm</p>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum.</p>
          </div>
          <div class="comment">
            <a href="#"><img src="images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>
            <p><a href="#">Owner</a> Says:<br />April 20th, 2009 at 3:21 pm</p>
            <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo.</p>
          </div>
          <div class="comment">
          </div>
        </div> -->
        <c:if test="${not empty recommendArticlesList }">
        <div class="article" style="height:300px;">
          <h2><span>精彩推荐</span></h2>
          <div class="cline"></div>
          <div class="photoBox">
          <ul class="introduce">
			    <c:forEach var="recommendArticles" items="${recommendArticlesList }">
	          		  <li><a href="${ recommendArticles.articleUrl}" target="_blank"><img src="${context}/images/robots.jpg" alt="${recommendArticles.articleTitle}" /></a></li>
	          	</c:forEach>
    		</ul>
    		</div>
        </div>
        </c:if>
      </div>
      <div class="sidebar">
      <div class="rightbox">
          <h2>Wise Words</h2>
          <div class="cline"></div>
          <p>   <img src="images/test_1.gif" alt="image" width="20" height="18" /> <em>We can let circumstances rule us, or we can take charge and rule our lives from within </em>.<img src="images/test_2.gif" alt="image" width="20" height="18" /></p>
          <p style="float:right;"><strong>Earl Nightingale</strong></p>
          </div>
  		<c:if test="${not empty randomReadList}">           
       <div class="rightbox">
          <img class="paperclip" alt="paperclip" src="images/paperclip.png"/>	
          <h2><span>猜你喜欢</span></h2>
          <div class="cline"></div>
          <ul class="ex_menu">
             <c:forEach var="randomArticle" items="${randomReadList }">
	  			 <li>
	  			 	<a href="${randomArticle.articleUrl}" target="_blank" title="${randomArticle.articleTitle}">${randomArticle.articleTitle}</a><br />
					      来源：${randomArticle.articleFrom}   作者：${randomArticle.articleAuthor}             
				</li>         
		   </c:forEach>
          </ul>
        </div>
        </c:if>
        <!-- 百度广告位置1 -->
        <div class="rightbox" style="background-image:url(images/baidu.jpg);height:250px;">
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
        <div class="rightbox" style="background-image:url(images/baidu2.jpg);height:250px;">
        </div>     
      </div>
      <div class="cline"></div>
    </div>
  </div>
  <div class="fbg">
    <div class="footer">
    	<span class="fs">版权所有 © 2014 5M网 </span><span class="fs"><a href="javascript:;">免责声明</a></span><span class="fs"> 站长邮箱:zl007_ml@126.com </span><span class="fs">粤ICP备xxxxxx号 </span>  <span class="fs">站长统计 </span>
    </div>
  </div>
</div>
<script type="text/javascript" src="${context}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${context}/js/zoomer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
 	$('ul.introduce li').Zoomer({speedView:200,speedRemove:400,altAnim:true,speedTitle:400,debug:false});
});
</script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>