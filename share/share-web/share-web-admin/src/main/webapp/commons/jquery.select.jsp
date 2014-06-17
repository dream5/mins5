<%@ page contentType="text/html;charset=UTF-8"%>
<style>
<!--
/*style for iSimulateSelect*/
.i_selectbox {
	height: 30px;
	position: relative;
	font-size: 12px
}

.i_selectbox .i_currentselected {
	width: 200px;
	height: 28px;
	background: #fff;
	border: 1px solid #ccc;
	/*-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;*/
	text-indent: 5px;
	line-height: 28px;
	cursor: pointer;
	overflow: hidden;
}

.i_selectbox .i_currentselected .texts {
	width: 93px;
	float: left;
	overflow: hidden;
}
.i_selectbox .i_currentselected .texts0 {
	width: 93px;
	float: left;
	color:#ccc;
	overflow: hidden;
}
.i_selectbox .i_currentselected .icon {
	display: block;
	width: 18px;
	background:blue;
	height: 18px;
	background:url(${basepath}/images/form.png) 0 0 no-repeat;
	margin:5px 2px 0 0;
	float: right;
}

.i_selectbox .mouse-on {
	border: 1px solid #2b8dd9;
}

.i_selectbox .mouse-on .icon {
	background:url(${basepath}/images/form.png) 0 -18px no-repeat;;
}

.i_selectbox .i_selectoption {
	overflow: hidden;
	overflow-y: auto;
	position: absolute;
	left: 0px;
	top: 30px;
	background-color: #fff;
	/*background:rgba(255,255,255,.9);-webkit-box-shadow:-2px 3px 5px rgba(0,0,0,.3);-moz-box-shadow:-2px 3px 5px rgba(0,0,0,.3);box-shadow:-2px 3px 5px rgba(0,0,0,.3);border-radius:5px;border-right:1px solid #eee*/
	border: 1px solid #2b8dd9;
}

.i_selectbox .i_selectoption dt {
	height: 24px;
	background-color: #eee;
	text-indent: 5px;
	font-style: italic;
	color: #555;
	line-height: 24px;
}

.i_selectbox .i_selectoption dd {
	height: 30px;
	cursor: pointer;
	text-indent: 10px;
	line-height: 30px;
	background: #fff;
}

.i_selectbox .i_selectoption dd:hover {
	background: #E9F3FB;
	color: #333
}
.i_selectbox .i_selectoption dd.selected {
	background-color: #2b8dd9;
	color: #fff
}
.selec_txt{
	width:90px;
	overflow:hidden;
}
-->
</style>
<script type="text/javascript">
/*******************************
 * @author Mr.Think
 * @author blog http://mrthink.net/
 * @2012.02.28
 * @可自由转载及使用,但请注明版权归属
 *******************************/
;(function($){
	var widthV=113;
	if(selectWidth){
		widthV=selectWidth;
	}
	var textWidth=widthV-20;
    /*
     * 统一select样式并实现样式高度自定义的jQuery插件@Mr.Think(http://mrthink.net/)
     */
    $.fn.iSimulateSelect=function(iSet){
        iSet=$.extend({
            selectBoxCls:'i_selectbox', //string类型,外围class名
            curSCls:'i_currentselected',//string类型,默认显示class名
            optionCls:'i_selectoption',//string类型,下拉列表class名
            selectedCls:'selected',//string类型,当前选中class名
            width:widthV,//number类型，模拟select的宽度
            //height:180,//number类型，模拟select的最大高度
			height:220,
            zindex:20,//层级顺序
			width2:190
        },iSet||{});
       // this.hide();
        return this.each(function(){
           var self=this; 
           var thisCurVal,thisSelect,cIndex=0;
           //计算模拟select宽度
           if(iSet.width==0){
              iSet.width=$(self).width();
           }
           var html='';
           //如果值为空则设置为灰色显示
           if($(self).val()==""){
           		 html='<div id="'+$(self).attr("id")+'Div"  class="'+iSet.selectBoxCls+'" style="z-index:'+iSet.zindex+'">'+
	            '<div class="'+iSet.curSCls+'" style="width:'+iSet.width+'px">'+
	           		'<span class="texts0" style="width:'+textWidth+'px">'+$(self).find('option:selected').text()+'</span>'+
	           		'<span class="icon"></span></div>'+
	           		'<dl class="'+iSet.optionCls+'" style="display:none;width:'+iSet.width+'px">';
           }else{
           		 html='<div id="'+$(self).attr("id")+'Div" class="'+iSet.selectBoxCls+'" style="z-index:'+iSet.zindex+'">'+
	            '<div class="'+iSet.curSCls+'" style="width:'+iSet.width+'px">'+
	           		'<span class="texts" style="width:'+textWidth+'px">'+$(self).find('option:selected').text()+'</span>'+
	           		'<span class="icon"></span></div>'+
	           		'<dl class="'+iSet.optionCls+'" style="display:none;width:'+iSet.width+'px">';
           }

          //用dd替代option
             $(this).find('option').each(function(){
                 if($(this).is(':selected')){
                    html+='<dd style="overflow:hidden;"class="'+iSet.selectedCls+'">'+$(this).text()+'</dd>'; 
                 }else{
                    html+='<dd style="overflow:hidden;">'+$(this).text()+'</dd>';
                 }
             });
           
           //将模拟dl插入到select后面
           $(self).after(html);
           //当前模拟select外围box元素    
           thisBox=$(self).next('.'+iSet.selectBoxCls);
           //当前模拟select初始值元素    
           thisCurVal=thisBox.find('.'+iSet.curSCls);
           //当前模拟select列表
           thisSelect=thisBox.find('.'+iSet.optionCls);


			 thisCurVal.mouseover(function(){
				$(this).addClass('mouse-on');
			 }).mouseout(function(){
				$(this).removeClass('mouse-on');
			 });
           /*
           若同页面还有其他原生select,请前往https://github.com/brandonaaron/bgiframe下载bgiframe，同时在此处调用thisSelect.bgiframe()
            */ 
           //thisSelect.bgiframe();
           
           //弹出模拟下拉列表
         thisCurVal.toggle( 
				function () { 
					if(thisSelect.is(":hidden")){
						$('.'+iSet.optionCls).hide();
		               $('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex); 
		               $(self).next('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex+1);
		               thisSelect.show();
					}else{
						thisSelect.hide();
                  		$('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex); 
					}
					  
		        }, 
				function () { 
					if(thisSelect.is(":hidden")){
						$('.'+iSet.optionCls).hide();
		               $('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex); 
		               $(self).next('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex+1);
		               thisSelect.show();
					}else{
						thisSelect.hide();
                  		$('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex); 
					}
				} 
		   ); 
           thisCurVal.click(function(e){
              //媒体关联频道
              	$('.'+iSet.optionCls).hide();
	            $('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex); 
	            $(self).next('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex+1);
	            thisSelect.show();	 
           });
           //若模拟select高度超出限定高度，则自动overflow-y:auto
           if(thisSelect.height()>iSet.height){
               thisSelect.height(iSet.height);
           }
           //模拟列表点击事件-赋值-改变y坐标位置-...
           thisSelect.find('dd').click(function(){
           	  // alert("123");
           	 
              $(this).addClass(iSet.selectedCls).siblings().removeClass(iSet.selectedCls);
               cIndex=thisSelect.find('dd').index(this);
               //thisCurVal.text($(this).text());
               if($(self).find('option').eq(cIndex).attr("value")==""){
               	   thisCurVal.html('<span class="texts0" style="width:'+textWidth+'px">'+$(this).text()+'</span><span class="icon"></span>');
               }else{
               	   thisCurVal.html('<span class="texts" style="width:'+textWidth+'px">'+$(this).text()+'</span><span class="icon"></span>');
               }
               $(self).find('option').eq(cIndex).attr('selected','selected');
               $('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex);
               //媒体关联频道
               if($(self).attr("nextAss")=="channel_id"||$(self).attr("nextAss")=="channellist"){
               		getChannel($(self).attr("nextAssValue"));
               }else if($(self).attr("nextAss")=="mediaContent_channel"){
               		getSub('unbusichannel','mediaId',$(self).find('option').eq(cIndex).attr("value"),'channelId','1')
               }else if($(self).attr("nextAss")=="mediaContent_postionId"){
               		getSub('unbusipostion','channelId',$(self).find('option').eq(cIndex).attr("value"),'postionId','2');
               }
               
               
               
               
               thisSelect.hide(); 
           });
           //非模拟列表处点击隐藏模拟列表
		   //$(document)点击事件不兼容部分移动设备
           $('html,body').click(function(e){
              if(e.target.className.indexOf(iSet.curSCls)==-1){
                  thisSelect.hide();
                  $('.'+iSet.selectBoxCls).css('zIndex',iSet.zindex); 
              } 
           });
           //取消模块列表处取消默认事件
           thisSelect.click(function(e){
              e.stopPropagation();
           });
        });
    }
})(jQuery);
$(function(){
	//插件调用
	$('.iselect').iSimulateSelect();
	$("div[name=initSelDiv]").each(function(i){
		$(this).hide();
	})
})
</script>
</div>