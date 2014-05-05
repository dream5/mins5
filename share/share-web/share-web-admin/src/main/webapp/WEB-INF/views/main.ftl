<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<title>Mins5 后台管理系统</title>
</head>
<body class="easyui-layout">
	<div region="north" border="true" class="cs-north">
		<div class="cs-north-bg">
		<div class="cs-north-logo">Mins5 后台管理系统</div>
		<ul class="ui-skin-nav">				
			<li class="li-skinitem" title="gray"><span class="gray" rel="gray"></span></li>
			<li class="li-skinitem" title="default"><span class="default" rel="default"></span></li>
			<li class="li-skinitem" title="bootstrap"><span class="bootstrap" rel="bootstrap"></span></li>
			<li class="li-skinitem" title="black"><span class="black" rel="black"></span></li>
			<li class="li-skinitem" title="metro"><span class="metro" rel="metro"></span></li>
		</ul>	
		</div>
	</div>
	<div region="west" border="true" split="true" title="Navigation" class="cs-west">
		<div class="easyui-accordion" fit="true" border="false">
				<div title="综合管理" data-options="iconCls:'sys-article'">
					<ul class="subMenuCls">
						<li>
							<div>
								<a href="javascript:void(0);" src="${path}/article/articleList.mins" class="cs-navi-tab">
								<span class="leftIcons sys-article-edit"><em>文章管理</em></span>
								</a>
							</div>
						</li>
						<li>
							<div>
								<a href="javascript:void(0);" src="${path}/article/articleKindList.mins" class="cs-navi-tab">
								<span class="leftIcons sys-kind-edit"><em>类别管理</em></span>
								</a>
							</div>
						</li>
						<li>
							<div>
								<a href="javascript:void(0);" src="${path}/article/articleLabelList.mins" class="cs-navi-tab">
								<span class="leftIcons sys-label-edit"><em>标签管理</em></span>
								</a>
							</div>
						</li>
					</ul>
				</div>
				<div title="图片管理" data-options="iconCls:'sys-images'"> 
					<ul class="subMenuCls">
						<li>
							<div>
								<a href="javascript:void(0);" src="${path}/upload/addImagesInit.mins" class="cs-navi-tab">
								<span class="leftIcons sys-images-edit"><em>图片管理</em></span>
								</a>
							</div>
						</li>
					</ul>
				</div>
				<div title="统计管理" data-options="iconCls:'sys-statistics'"> 
					<ul class="subMenuCls">
						<li>
							<div>
								<a href="javascript:void(0);" src="${path}/upload/addImagesInit.mins" class="cs-navi-tab">
								<span class="leftIcons sys-statistics-edit"><em>统计管理</em></span>
								</a>
							</div>
						</li>
					</ul>
				</div>
				<div title="base">
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/easyloader/basic.html" class="cs-navi-tab">easyloader</a></p>
					
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/draggable/basic.html" class="cs-navi-tab">draggable-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/draggable/constain.html" class="cs-navi-tab">draggable-constain</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/draggable/snap.html" class="cs-navi-tab">draggable-snap</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/droppable/basic.html" class="cs-navi-tab">droppable-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/droppable/accept.html" class="cs-navi-tab">droppable-accept</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/droppable/sort.html" class="cs-navi-tab">droppable-sort</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/resizable/basic.html" class="cs-navi-tab">resizable</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/pagination/basic.html" class="cs-navi-tab">pagination-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/pagination/custombuttons.html" class="cs-navi-tab">pagination-custombuttons</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/pagination/simple.html" class="cs-navi-tab">pagination-simple</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/searchbox/basic.html" class="cs-navi-tab">searchbox-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/searchbox/category.html" class="cs-navi-tab">searchbox-category</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/progressbar/basic.html" class="cs-navi-tab">progressbar</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/basic.html" class="cs-navi-tab">tooltip-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/ajax.html" class="cs-navi-tab">tooltip-ajax</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/customcontent.html" class="cs-navi-tab">tooltip-customcontent</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/customstyle.html" class="cs-navi-tab">tooltip-customstyle</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/position.html" class="cs-navi-tab">tooltip-position</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/toolbar.html" class="cs-navi-tab">tooltip-toolbar</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tooltip/tooltipdialog.html" class="cs-navi-tab">tooltip-tooltipdialog</a></p>	
				</div>
				<div title="Layout">
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/accordion/basic.html" class="cs-navi-tab">accordion-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/accordion/actions.html" class="cs-navi-tab">accordion-actions</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/accordion/tools.html" class="cs-navi-tab">accordion-tools</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/accordion/ajax.html" class="cs-navi-tab">accordion-ajax</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/layout/basic.html" class="cs-navi-tab">layout-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/layout/complex.html" class="cs-navi-tab">layout-complex</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/layout/full.html" class="cs-navi-tab">layout-full</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/layout/addremove.html" class="cs-navi-tab">layout-addremove</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/layout/nestedlayout.html" class="cs-navi-tab">layout-nestedlayout</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/layout/nocollapsible.html" class="cs-navi-tab">layout-nocollapsible</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/panel/basic.html" class="cs-navi-tab">panel-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/panel/customtools.html" class="cs-navi-tab">panel-customtools</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/panel/loadcontent.html" class="cs-navi-tab">panel-loadcontent</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/panel/nestedpanel.html" class="cs-navi-tab">panel-nestedpanel</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/panel/paneltools.html" class="cs-navi-tab">panel-paneltools</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/basic.html" class="cs-navi-tab">tabs-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/autoheight.html" class="cs-navi-tab">tabs-autoheight</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/hover.html" class="cs-navi-tab">tabs-hover</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/nestedtabs.html" class="cs-navi-tab">tabs-nestedtabs</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/striptools.html" class="cs-navi-tab">tabs-striptools</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/tabposition.html" class="cs-navi-tab">tabs-tabposition</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tabs/tabstools.html" class="cs-navi-tab">tabs-tabstools</a></p>
				</div>
				<div title="Menu and Button">
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/menu/basic.html" class="cs-navi-tab">menu-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/menu/customitem.html" class="cs-navi-tab">menu-customitem</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/menu/events.html" class="cs-navi-tab">menu-events</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/menubutton/basic.html" class="cs-navi-tab">menubutton-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/menubutton/actions.html" class="cs-navi-tab">menubutton-actions</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/linkbutton/basic.html" class="cs-navi-tab">linkbutton-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/linkbutton/iconalign.html" class="cs-navi-tab">linkbutton-iconalign</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/linkbutton/plain.html" class="cs-navi-tab">linkbutton-plain</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/splitbutton/basic.html" class="cs-navi-tab">splitbutton-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/splitbutton/actions.html" class="cs-navi-tab">splitbutton-actions</a></p>
				</div>
				<div title="Form">
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/form/basic.html" class="cs-navi-tab">form-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/form/load.html" class="cs-navi-tab">form-load</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/validatebox/basic.html" class="cs-navi-tab">validatebox</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combo/basic.html" class="cs-navi-tab">combo</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/basic.html" class="cs-navi-tab">combobox-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/actions.html" class="cs-navi-tab">combobox-actions</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/customformat.html" class="cs-navi-tab">combobox-customformat</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/dynamicdata.html" class="cs-navi-tab">combobox-dynamicdata</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/multiple.html" class="cs-navi-tab">combobox-multiple</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/remotedata.html" class="cs-navi-tab">combobox-remotedata</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combobox/remotejsonp.html" class="cs-navi-tab">combobox-remotejsonp</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combotree/basic.html" class="cs-navi-tab">combotree-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combotree/actions.html" class="cs-navi-tab">combotree-actions</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combotree/initvalue.html" class="cs-navi-tab">combotree-initvalue</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combotree/multiple.html" class="cs-navi-tab">combotree-multiple</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combogrid/basic.html" class="cs-navi-tab">combogrid-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combogrid/actions.html" class="cs-navi-tab">combogrid-actions</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combogrid/initvalue.html" class="cs-navi-tab">combogrid-initvalue</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/combogrid/multiple.html" class="cs-navi-tab">combogrid-multiple</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/numberbox/basic.html" class="cs-navi-tab">numberbox-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/numberbox/format.html" class="cs-navi-tab">numberbox-format</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/numberbox/range.html" class="cs-navi-tab">numberbox-range</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datebox/basic.html" class="cs-navi-tab">datebox-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datebox/dateformat.html" class="cs-navi-tab">datebox-dateformat</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datebox/events.html" class="cs-navi-tab">datebox-events</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datebox/validate.html" class="cs-navi-tab">datebox-validate</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datetimebox/basic.html" class="cs-navi-tab">datetimebox-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datetimebox/initvalue.html" class="cs-navi-tab">datetimebox-initvalue</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datetimebox/showseconds.html" class="cs-navi-tab">datetimebox-showseconds</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/calendar/basic.html" class="cs-navi-tab">calendar-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/calendar/firstday.html" class="cs-navi-tab">calendar-firstday</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/timespinner/basic.html" class="cs-navi-tab">timespinner-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/timespinner/actions.html" class="cs-navi-tab">timespinner-actions</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/timespinner/range.html" class="cs-navi-tab">timespinner-range</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/numberspinner/basic.html" class="cs-navi-tab">numberspinner-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/numberspinner/increment.html" class="cs-navi-tab">numberspinner-increment</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/numberspinner/range.html" class="cs-navi-tab">numberspinner-range</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/slider/basic.html" class="cs-navi-tab">slider-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/slider/formattip.html" class="cs-navi-tab">slider-formattip</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/slider/rule.html" class="cs-navi-tab">slider-rule</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/slider/vertical.html" class="cs-navi-tab">slider-vertical</a></p>
				</div>
				<div title="Window">
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/window/basic.html" class="cs-navi-tab">window-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/window/customtools.html" class="cs-navi-tab">window-customtools</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/window/inlinewindow.html" class="cs-navi-tab">window-inlinewindow</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/window/modalwindow.html" class="cs-navi-tab">window-modalwindow</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/window/windowlayout.html" class="cs-navi-tab">window-windowlayout</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/dialog/basic.html" class="cs-navi-tab">dialog-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/dialog/complextoolbar.html" class="cs-navi-tab">dialog-complextoolbar</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/dialog/toolbarbuttons.html" class="cs-navi-tab">dialog-toolbarbuttons</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/messager/basic.html" class="cs-navi-tab">messager-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/messager/alert.html" class="cs-navi-tab">messager-alert</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/messager/interactive.html" class="cs-navi-tab">messager-interactive</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/messager/position.html" class="cs-navi-tab">messager-position</a></p>
				</div>
				<div title="DataGrid and Tree">
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/basic.html" class="cs-navi-tab">datagrid-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/cellstyle.html" class="cs-navi-tab">datagrid-cellstyle</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/checkbox.html" class="cs-navi-tab">datagrid-checkbox</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/clientpagination.html" class="cs-navi-tab">datagrid-clientpagination</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/columngroup.html" class="cs-navi-tab">datagrid-columngroup</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/complextoolbar.html" class="cs-navi-tab">datagrid-complextoolbar</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/contextmenu.html" class="cs-navi-tab">datagrid-contextmenu</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/custompager.html" class="cs-navi-tab">datagrid-custompager</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/footer.html" class="cs-navi-tab">datagrid-footer</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/formatcolumns.html" class="cs-navi-tab">datagrid-formatcolumns</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/frozencolumns.html" class="cs-navi-tab">datagrid-frozencolumns</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/frozenrows.html" class="cs-navi-tab">datagrid-frozenrows</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/mergecells.html" class="cs-navi-tab">datagrid-mergecells</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/rowborder.html" class="cs-navi-tab">datagrid-rowborder</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/rowediting.html" class="cs-navi-tab">datagrid-rowediting</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/rowstyle.html" class="cs-navi-tab">datagrid-rowstyle</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/selection.html" class="cs-navi-tab">datagrid-selection</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/simpletoolbar.html" class="cs-navi-tab">datagrid-simpletoolbar</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/datagrid/transform.html" class="cs-navi-tab">datagrid-transform</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/propertygrid/basic.html" class="cs-navi-tab">propertygrid-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/propertygrid/customcolumns.html" class="cs-navi-tab">propertygrid-customcolumns</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/propertygrid/groupformat.html" class="cs-navi-tab">propertygrid-groupformat</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/basic.html" class="cs-navi-tab">tree-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/animation.html" class="cs-navi-tab">tree-animation</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/actions.html" class="cs-navi-tab">tree-actions</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/checkbox.html" class="cs-navi-tab">tree-checkbox</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/contextmenu.html" class="cs-navi-tab">tree-contextmenu</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/dnd.html" class="cs-navi-tab">tree-dnd</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/editable.html" class="cs-navi-tab">tree-editable</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/tree/icons.html" class="cs-navi-tab">tree-icons</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/treegrid/basic.html" class="cs-navi-tab">treegrid-basic</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/treegrid/editable.html" class="cs-navi-tab">treegrid-editable</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/treegrid/actions.html" class="cs-navi-tab">treegrid-actions</a></p>

					<a href="javascript:void(0);" src="${path}/js/easyui/demo/treegrid/contextmenu.html" class="cs-navi-tab">treegrid-contextmenu</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/treegrid/footer.html" class="cs-navi-tab">treegrid-footer</a></p>
					<a href="javascript:void(0);" src="${path}/js/easyui/demo/treegrid/reports.html" class="cs-navi-tab">treegrid-reports</a></p>
				</div>
		</div>
	</div>
	
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="Home">
				<div class="cs-home-remark">
					<h1>Mins5 后台管理系统</h1><br/>
					心有多大，舞台就有多大！ <br/>
				</div>
				</div>
        </div>
	</div>

	<div region="south" border="false" id="south"><center>Made in Mins5</center></div>
	
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
	</div>
	<script>
	$(function() {
	tabCloseEven();

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});

	var skins = $('.li-skinitem span').click(function() {
		if ($(this).hasClass('cs-skin-on')){
			return;
		}
		skins.removeClass('cs-skin-on');
		$(this).addClass('cs-skin-on');
		var skin = $(this).attr('rel');
		$('#swicth-style').attr('href', themes[skin]);
		var $iframe = $('iframe'); 
		if ($iframe.length> 0) { 
			for ( var i = 0; i< $iframe.length; i++) { 
				var ifr = $iframe[i]; 
				$(ifr).contents().find('#swicth-style').attr('href', href); 
			} 
		}
		setCookie('cs-skin', skin);
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	});
});
	</script>
</body>
</html>