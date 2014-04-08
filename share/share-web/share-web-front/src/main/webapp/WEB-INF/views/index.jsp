<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>心灵鸡汤</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${context}/css/zoomer.css" rel="stylesheet" type="text/css"  media="screen" />
<style type="text/css">
.hotRecommend{width:930px;margin:0px auto;}
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/* ui-banner */
.ui-banner{display:block;position:relative;width:930px;margin:5px auto;text-align:center;}
.ui-banner.ui-banner-invalid{display:none;}
.ui-banner,.ui-banner .ui-banner-slides,.ui-banner .ui-banner-slogans,.ui-banner .ui-banner-arrow{height:233px;}
.ui-banner .ui-banner-slides,.ui-banner .ui-banner-slogans,.ui-banner .ui-banner-arrow{position:absolute;top:0;}
.ui-banner ul{list-style-type:none;margin:0;padding:0;overflow:hidden;}
.ui-banner .ui-banner-slides{width:654px;height:233px;left:1px;}
.ui-banner .ui-banner-slides li{display:none;position:absolute;}
.ui-banner .ui-banner-slides li img{width:654px;height:233px;border:none;}
.ui-banner .ui-banner-slides li.ui-banner-slides-current,.ui-banner .ui-banner-slides li.ui-banner-slides-prev,.ui-banner .ui-banner-slides li.ui-banner-slides-next{display:block;}
.ui-banner .ui-banner-slides li.ui-banner-slides-current{left:0;}
.ui-banner .ui-banner-slides li.ui-banner-slides-prev{left:-654px;}
.ui-banner .ui-banner-slides li.ui-banner-slides-next{left:654px;}
.ui-banner .ui-banner-slogans{background:#009AC9;height:213px;overflow:hidden;padding:10px 30px 10px 15px;width:228px;right:0;}
.ui-banner .ui-banner-slogans li{cursor:pointer;color:#8DC4EC;text-align:left;font-weight:bold;font-size:12px;line-height:14px;padding:10px 0 10px 10px;margin-left:5px;border-bottom:1px solid #79B4DF;list-style:none;list-style-type:none;}
.ui-banner .ui-banner-slogans li.ui-banner-slogans-current{color:#FFF;}
.ui-banner .ui-banner-slogans li.ui-banner-slogans-prev{border-bottom:none;}
.ui-banner .ui-banner-arrow{display:block;width:45px;outline:none;}
.ui-banner .ui-banner-arrow.ui-banner-arrow-prev{left:-14px;top:100px;background:transparent url("images/hero-slider-arrow-left.png") no-repeat 0 0;}
.ui-banner .ui-banner-arrow.ui-banner-arrow-next{left:630px;top:100px;background:transparent url("images/hero-slider-arrow-right.png") no-repeat 0 0;}
.ui-banner .ui-banner-arrow.ui-banner-arrow-next img{left:-15px;}
.ui-banner .ui-banner-overlay{bottom:0;height:10px;position:absolute;right:0;width:173px;}
</style>
</head>
<body>
<div class="login">
</div>
<div class="main">	
  <%--top --%> 
  <%@ include file="/WEB-INF/views/frame/top.jsp" %>
  <div class="cline"></div>
  <div class="hotRecommend">
	  <div id="banners" class="ui-banner">
	<ul class="ui-banner-slides">
		<li><a href="#"><img src="images/HeartHealthOmega.jpg" alt="NEW! Get Heart Smart With Essential Omega III" title="NEW! Get Heart Smart With Essential Omega III"/></a></li>
		<li><a href="#"><img src="images/CellLabs.jpg" alt="NEW! Support Your Skin on a Cellular Level" title="NEW! Support Your Skin on a Cellular Level"/></a></li>
		<li><a href="#"><img src="images/RoyalSpa.jpg" alt="NEW! Indulge in Luxury With Royal Spa" title="NEW! Indulge in Luxury With Royal Spa"/></a></li>
		<li><a href="#"><img src="images/Cashback.jpg" alt="NEW! Get paid to shop with Cashback" title="NEW! Get paid to shop with Cashback"/></a></li>
		<li><a href="#"><img src="images/StealthShield.jpg" alt="NEW! Radiation Protection at its Best" title="NEW! Radiation Protection at its Best"/></a></li>
		<li><a href="#"><img src="images/IsotonixEducate.jpg" alt="Unleash the Power of Isotonix Today" title="Unleash the Power of Isotonix Today"/></a></li>
		<li><a href="#"><img src="images/MotivesSimple.jpg" alt="Motives is Changing the Face of UK Beauty" title="Motives is Changing the Face of UK Beauty"/></a></li>
		<li><a href="#"><img src="images/UltimateAloeKwStb.jpg" alt="Get a Taste of Spring" title="Get a Taste of Spring"/></a></li>
		<li><a href="#"><img src="images/OPC3AllBetter.jpg" alt="Try Isotonix OPC-3 Today for Better Health" title="Try Isotonix OPC-3 Today for Better Health"/></a></li>
		<li><a href="#"><img src="images/RoyalSpaRoyalHair.jpg" alt="Feel like a princess with Royal Spa" title="Feel like a princess with Royal Spa"/></a></li>
		<li><a href="#"><img src="images/JubileeRoyalPartyAcai.jpg" alt="Stay energised with Isotonix for the Queen's Jubilee" title="Stay energised with Isotonix for the Queen's Jubilee"/></a></li>
		<li><a href="#"><img src="images/WorldStores.gif" alt="WorldStores - Top Brands Delivered Next Day" title="WorldStores - Top Brands Delivered Next Day"/></a></li>
	</ul><!--ui-banner-slides end-->
	<ul class="ui-banner-slogans">
		<li>新！让心智能必要的欧米茄三</li>
		<li>新！支持你的皮肤细胞水平上</li>
		<li>新！御温泉尽情享受豪华</li>
		<li>新！得到报酬购物现金回赠</li>
		<li>新！在其最好的辐射防护</li>
		<li>今天释放等渗电力</li>
		<li>动机的改变，面对英国美容</li>
		<li>得到了春天的味道</li>
		<li>尝试等渗OPC-3今天更健康</li>
		<li>与皇家温泉公主的感觉</li>
		<li>保持精力充沛与等渗女王的银禧</li>
		<li>WorldStores - 顶级品牌提供翌日</li>
	</ul><!--ui-banner-slogans end-->
</div>
  </div>
  
  
  <%--content --%>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      	<%--内容加载于异步获取 --%>
       <div id="Pagination" class="mypage">
       </div>
      </div>
      <div class="sidebar">
      <c:if test="${not empty  recommendArticlesList}">
        <div class="rightbox">
          <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>
          <h2><span>热门推荐</span></h2>
          <div class="cline"></div>
          <ul class="sb_menu">
            <c:forEach var="recommendArticle" items="${recommendArticlesList }">
            	 <li><a href="#" title="${recommendArticle.articleTitle}">${recommendArticle.articleTitle}</a></li>
            </c:forEach>
          </ul>
        </div>
        </c:if>
        <c:if test="${not empty randomReadList}">
        <div class="rightbox">
           <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>	
          <h2><span>随机阅读</span></h2>
          <div class="cline"></div>
          <ul class="ex_menu">
            <c:forEach var="randomArticle" items="${randomReadList }">
            	 <li><a href="#" title="${randomArticle.articleTitle}">${randomArticle.articleTitle}</a></li>
            </c:forEach>
          </ul>
        </div>
        </c:if>
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
      </div>
      <div class="cline"></div>
    </div>
  </div>
  	<%--footer --%> 
     <%@ include file="/WEB-INF/views/frame/footer.jsp" %>
</div>
<script type="text/javascript" src="${context}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${context}/js/common.js"></script>
<script type="text/javascript" src="${context}/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${context}/js/jquery-ui-1.8.6.core.widget.js"></script>
<script type="text/javascript" src="${context}/js/jqueryui.bannerize.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	$('#banners').bannerize({
		shuffle: 1,
		interval: "5"
	});
	
 	//$('ul.introduce li').Zoomer({speedView:200,speedRemove:400,altAnim:true,speedTitle:400,debug:false});
});
</script>
        <script type="text/javascript">

   
            function pageSelectCallBack(page_index, jq){
            	var cp = page_index+1;
            	var ps = 7;
            	var timestamp = Date.parse(new Date());
            	$.ajax({
         	       type: "GET",
         	       contentType : 'application/json;charset=UTF-8',
         	       url: '${context}/index/getArticlesByPageNo.mins',
         	       data: {t:timestamp,cp:cp,ps:ps},
         	       dataType: "json",
         	       success:function(result) {
         	    	  if(result == "error"){
       		    	   $(".mainbar").html("<div class=\"article\"><span>对不起，没有查询出数据！<span></div>");
       		    	   $(".mypage").hide();
       		       }else{
       		    	   var content = "";
       		    	   $.each(result,function(index,item){
       		    		   content +="<div class=\"article\">";
       		    		   content +="<h2>"+item.articleTitle+"</h2>";
       		    		   content +="<div class=\"cline\"></div>";
       		    		   content +="<p><span>时间:"+item.createTime+"</span>&nbsp;&nbsp;<span>来源:"+item.articleFrom+"</span>&nbsp;&nbsp;<span>作者:"+item.articleAuthor+"</span>&nbsp;&nbsp;浏览（18）</p>";
       		    		   content +="<img src=\"images/img_1.jpg\" width=\"613\" height=\"193\" alt=\""+item.articleTitle+"\" />";
       		    		   content +="<div class=\"cline\"></div>";
       		    		   content +=item.articleContent;
       		    		   content +=" <p class=\"links\"><a href=\"#\" target=\"_blank\">继续阅读</a></p>";
       		    		   content +="<div class=\"cline\"></div>";
       		    		   content +=baiduShare();
       		    			content +="</div>";
       		    	   });
       		    	   $(".mypage").before(content);
       		       } 
         	    	   
         	       },
         	       error:function(data){
         	    	  $(".mainbar").html("<div class=\"article\"><span>对不起，没有查询出数据！<span></div>");
         	    	  $(".mypage").hide();
         	    	  
         	       }
         	});
                
                return false;
            }

            

            // When document has loaded, initialize pagination and form
            $(document).ready(function(){
            	
            	
              
                $("#Pagination").pagination('${totalArticleCount}', {
  							 num_edge_entries: 2,//两侧显示的首尾分页的条目数
   							 num_display_entries: 10,//连续分页主体部分显示的分页条目数
    						 callback: pageSelectCallBack,//回调函数
    						 items_per_page:15,//每页显示的条目数
					});

            });
            
            
            function baiduShare(url,content){
            	var baidu="<div class=\"bdsharebuttonbox\" style=\"float:right;\">";
            	    baidu +="<a href=\""+url+"\" class=\"bds_more\" data-cmd=\"more\"></a>";
            	    baidu +="<a title=\"分享到QQ空间\" href=\""+url+"\" class=\"bds_qzone\" data-cmd=\"qzone\"></a>";
            	    baidu +="<a title=\"分享到新浪微博\" href=\""+url+"\" class=\"bds_tsina\" data-cmd=\"tsina\"></a>";
            	    baidu +="<a title=\"分享到腾讯微博\" href=\""+url+"\" class=\"bds_tqq\" data-cmd=\"tqq\"></a>";
            	    baidu +="<a title=\"分享到人人网\" href=\""+url+"\" class=\"bds_renren\" data-cmd=\"renren\"></a>";
            	    baidu +="<a title=\"分享到微信\" href=\""+url+"\" class=\"bds_weixin\" data-cmd=\"weixin\"></a>";
            	    baidu +="</div>";
            	  return baidu;  
 				
            }

        </script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
