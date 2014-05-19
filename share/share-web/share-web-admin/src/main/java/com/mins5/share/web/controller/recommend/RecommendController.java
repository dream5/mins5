package com.mins5.share.web.controller.recommend;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.business.article.domain.ArticleRecommend;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.business.article.service.ArticleRecommendService;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.util.JsonUtils;

/**
 * <p>
 * 推荐控制器
 * </p>
 * 
 * @author zhanglin 2014年5月2日 下午7:21:59
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/recommend")
public class RecommendController {

	@Autowired
	private ArticleRecommendService recommendService;

	/**
	 * 增加推荐
	 * 
	 * @param repsResponse
	 * @param position 推荐位
	 * @param order 顺序
	 * @param articleId 文章ID
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addRecommend")
	public void addRecommend(HttpServletResponse response, String position, String order, String articleId) {
		// 1.验证文章在posion上是否已存在，如果有返回，没有，下一步
		HashMap param = new HashMap();
		param.put("articleId", articleId);
		param.put("position", position);
		ReturnData<ArticleRecommend> returnData = recommendService.checkRecommend(param);
		if (StringUtils.isEmpty(returnData.getResultData())) {
			JsonUtils.write("在推荐位上已存在此文章！", response);
			return;
		}
		// 2.验证position的order上是否已有，如果有覆盖，没有新增
		param = new HashMap();
		param.put("recommendSort", order);
		param.put("position", position);
		ReturnData<ArticleRecommend> returnDataTwo = recommendService.checkRecommend(param);
		try {
			if (StringUtils.isEmpty(returnDataTwo.getResultData())) {
				// 新增
				ArticleRecommend recomend = new ArticleRecommend();
				recomend.setArticleId(Long.parseLong(articleId));
				recomend.setRecommendPosition(RECOMMEND_POSITION.valueOf(Integer.parseInt(position)));
				recomend.setRecommendSort(Long.parseLong(order));
				recommendService.saveArticleRecommend(recomend);
				JsonUtils.write("推荐成功！", response);
			} else {
				// 修改
				ArticleRecommend recomend = new ArticleRecommend();
				recomend.setRecommendId(returnDataTwo.getResultData().getRecommendId());
				recomend.setArticleId(Long.parseLong(articleId));
				recomend.setRecommendPosition(RECOMMEND_POSITION.valueOf(Integer.parseInt(position)));
				recomend.setRecommendSort(Long.parseLong(order));
				recommendService.updateArticleRecommend(recomend);
				JsonUtils.write("推荐成功！", response);
			}
		} catch (NumberFormatException e) {
			JsonUtils.write("推荐失败！", response);
		}

	}

}
