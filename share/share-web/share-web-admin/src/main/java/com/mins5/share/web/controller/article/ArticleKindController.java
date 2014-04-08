/**
 * 
 */
package com.mins5.share.web.controller.article;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.business.article.service.ArticleService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.util.EasyUITreeModule;
import com.mins5.share.util.JsonUtils;
import com.mins5.share.util.StringUtils;

/**
 * @author zhoutian
 * @since 2014-3-9
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/article")
public class ArticleKindController {
	
	@Autowired
	private ArticleService articleService;

	/**
	 * 文章类别列表
	 * @since 2014-3-9
	 * @return
	 */
	@RequestMapping(value = "/articleKindList")
	public String articleKindList() {
		return "article/article_kind_list";
	}
	
	/**
	 * ajax查询文章类别
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param response
	 */
	@RequestMapping(value = "/searchArticleKind")
	public void searchArticleKind(HttpServletResponse response) {
		
		Long parentId = 0L;
		List<Map<String, Object>> articleKindTreeGrid = this.getArticleKindTreeGrid(parentId); 
		JsonUtils.write(articleKindTreeGrid, response);
	}
	
	/**
	 * 递归查询文章列表树
	 * @author zhoutian
	 * @since 2014年4月8日
	 * @param articleKindList
	 * @return
	 */
	private List<Map<String, Object>> getArticleKindTreeGrid(Long parentId) {
		ReturnData<List<ArticleKind>> returnData = articleService.findArticleKindByParentId(parentId);
		List<ArticleKind> articleKindList = returnData.getResultData();
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if(articleKindList != null) {
			for(ArticleKind articleKind : articleKindList) {
				Map<String, Object> treeMap = new HashMap<String, Object>();
				treeMap.put("state", "open");
				treeMap.put("adminId", articleKind.getAdminId());
				treeMap.put("articleKindId", articleKind.getArticleKindId());
				treeMap.put("createTime", articleKind.getCreateTime());
				treeMap.put("kindName", articleKind.getKindName());
				treeMap.put("kindPinyin", articleKind.getKindPinyin());
				treeMap.put("parentKindId", articleKind.getParentKindId());
				treeMap.put("status", articleKind.getStatus());
				treeMap.put("updateTime", articleKind.getUpdateTime());
				List<Map<String, Object>> childTreeList = this.getArticleKindTreeGrid(articleKind.getArticleKindId());
				if(!CollectionUtils.isEmpty(childTreeList)) {
					treeMap.put("children", childTreeList);
					for(Map<String, Object> childTreeMap : childTreeList) {
						this.getArticleKindTreeGrid((Long)childTreeMap.get("articleKindId"));
					}
				}
				treeList.add(treeMap);
			}
		}
		return treeList;
	}
	
	/**
	 * 跳转到添加文章类别页面
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @return
	 */
	@RequestMapping(value = "/toArticleKindAdd")
	public String toArticleKindAdd() {
		return "article/article_kind_add";
	}
	
	/**
	 * ajax请求添加文章类别
	 * @author zhoutian
	 * @since 2014年3月17日
	 * @param response
	 * @param article
	 */
	@RequestMapping(value = "/articleKindAdd")
	public void articleKindAdd(HttpServletResponse response, ArticleKind articleKind) {
		Date currentDate = new Date();
		articleKind.setKindPinyin(StringUtils.trimBlank(articleKind.getKindPinyin()).toLowerCase());
		articleKind.setAdminId(0L);
		articleKind.setCreateTime(currentDate);
		articleKind.setUpdateTime(currentDate);
		if(articleKind.getParentKindId() == null) {
			articleKind.setParentKindId(0L);
		}
		ReturnData<ArticleKind> returnData = articleService.saveArticleKind(articleKind);
		String tip = "添加成功！";
		if(returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "添加失败！";
		}
		JsonUtils.write(tip, response);
	}
	
	/**
	 * ajax根据父ID查询文章类别
	 * @author zhoutian
	 * @since 2014年4月1日
	 * @param response
	 * @param parentId
	 */
	@RequestMapping(value = "/getArticleKind")
	public void getArticleKind(HttpServletResponse response) {
		Long parentId = 0L;
		List<EasyUITreeModule> treeList = this.getArticleKindTreeByParentId(parentId);
		JsonUtils.write(treeList, response);
	}
	
	/**
	 * 递归查询文章列表树
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param parentId
	 * @return
	 */
	private List<EasyUITreeModule> getArticleKindTreeByParentId(Long parentId) {
		ReturnData<List<ArticleKind>> returnData = articleService.findArticleKindByParentId(parentId);
		List<ArticleKind> articleKindList = returnData.getResultData();
		List<EasyUITreeModule> treeList = new ArrayList<EasyUITreeModule>();
		if(articleKindList != null) {
			for(ArticleKind articleKind : articleKindList) {
				EasyUITreeModule tree = new EasyUITreeModule();
				tree.setId(articleKind.getArticleKindId());
				tree.setText(articleKind.getKindName());
				List<EasyUITreeModule> childTree = this.getArticleKindTreeByParentId(articleKind.getArticleKindId());
				if(CollectionUtils.isEmpty(childTree)) {
					tree.setState("open");
				} else {
					tree.setChildren(childTree);
					for(EasyUITreeModule easyUITreeModule : childTree) {
						this.getArticleKindTreeByParentId(easyUITreeModule.getId());
					}
				}
				treeList.add(tree);
			}
		}
		return treeList;
	}
	
	/**
	 * 跳转到类别编辑页面
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param articleKindId
	 * @return
	 */
	@RequestMapping(value = "/toArticleKindEdit")
	public ModelAndView toArticleKindEdit(Long articleKindId) {
		ModelAndView mv = new ModelAndView();
		ReturnData<ArticleKind> returnData = articleService.findArticleKindById(articleKindId);
		ArticleKind articleKind = returnData.getResultData();
		mv.addObject("articleKind", articleKind);
		mv.setViewName("article/article_kind_edit");
		return mv;
	}
	
	/**
	 * 修改文章标签
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param response
	 * @param articleKindId
	 * @param kindName
	 * @param status
	 */
	@RequestMapping(value = "/articleKindEdit")
	public void  articleLabelEdit(HttpServletResponse response, Long articleKindId, String kindName, String kindPinyin, String status, Long parentKindId) {
		ArticleKind articleKind = new ArticleKind();
		articleKind.setArticleKindId(articleKindId);
		articleKind.setKindName(StringUtils.trimBlank(kindName));
		articleKind.setKindPinyin(StringUtils.trimBlank(kindPinyin).toLowerCase());
		articleKind.setStatus(status);
		articleKind.setUpdateTime(new Date());
		if(parentKindId == null) {
			articleKind.setParentKindId(0L);
		} else {
			articleKind.setParentKindId(parentKindId);
		}
		ReturnData<ArticleKind> returnData = articleService.updateArticleKind(articleKind);
		String tip = "修改成功！";
		if (returnData.getReturnCode() != ReturnCode.SUCCESS.getCode()) {
			tip = "修改失败！";
		}
		JsonUtils.write(tip, response);
	}
	
	/**
	 * 删除文章类别
	 * @author zhoutian
	 * @since 2014年4月7日
	 * @param response
	 * @param articleKindId
	 */
	@RequestMapping(value = "/articleKindDelete")
	public void articleKindDelete(HttpServletResponse response, Long articleKindId) {
		String tip = "删除成功！";
		try {
			articleService.deleteArticleKindAndArticleKindRelByArticleKindId(articleKindId);
		} catch(Exception e) {
			tip = "删除失败！";
		}
		JsonUtils.write(tip, response);
	}
}
