package com.mins5.share.business.article.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mins5.share.business.article.dao.ArticleRecommendDao;
import com.mins5.share.business.article.domain.Article;
import com.mins5.share.business.article.domain.ArticleRecommend;
import com.mins5.share.business.article.enums.RECOMMEND_POSITION;
import com.mins5.share.business.article.service.ArticleRecommendService;
import com.mins5.share.common.service.ReturnCode;
import com.mins5.share.common.service.ReturnData;

/**
 * 
 * @author zhanglin
 * @since 20140317
 */
@Service
public class ArticleRecommendServiceImpl implements ArticleRecommendService {

	private static final Log log = LogFactory.getLog(ArticleRecommendServiceImpl.class);

	@Autowired
	private ArticleRecommendDao recommendDao;

	@Override
	public ReturnData<List<Article>> findRecommendByPositionAndCount(RECOMMEND_POSITION position, int num) {
		ReturnData<List<Article>> returnData = new ReturnData<List<Article>>();
		try {
			List<Article> Articles = recommendDao.findRecommendByPositionAndCount(position, num);
			returnData.setResultData(Articles);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	/**
	 * 检验推荐位
	 * 
	 * @param param
	 * @return
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 */
	public ReturnData<ArticleRecommend> checkRecommend(Map param) {
		ReturnData<ArticleRecommend> returnData = new ReturnData<ArticleRecommend>();
		try {
			ArticleRecommend articleRecommend = recommendDao.checkRecommend(param);
			returnData.setResultData(articleRecommend);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	@Override
	public ReturnData<ArticleRecommend> saveArticleRecommend(ArticleRecommend recommend) {
		ReturnData<ArticleRecommend> returnData = new ReturnData<ArticleRecommend>();
		try {
			if (!checkSaveArticleRecommend(recommend)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			int count = recommendDao.save(recommend);
			if (count != 1) {
				returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
				return returnData;
			}
			returnData.setResultData(recommend);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkSaveArticleRecommend(ArticleRecommend recommend) {
		if (recommend == null) {
			return false;
		}
		if (recommend.getRecommendPosition() == null) {
			return false;
		}
		if (recommend.getRecommendSort() == null) {
			return false;
		}
		if (recommend.getArticleId() == null) {
			return false;
		}
		return true;
	}

	/**
	 * 更新
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 * @param articleKind
	 * @return
	 */
	@Override
	public ReturnData<ArticleRecommend> updateArticleRecommend(ArticleRecommend recomend) {
		ReturnData<ArticleRecommend> returnData = new ReturnData<ArticleRecommend>();
		try {
			if (!checkUpdateArticleRecommend(recomend)) {
				returnData.setReturnCode(ReturnCode.PARAM_ERROR.getCode());
				return returnData;
			}
			recommendDao.update(recomend);
			returnData.setResultData(recomend);
			returnData.setReturnCode(ReturnCode.SUCCESS.getCode());
		} catch (Exception e) {
			log.error("service exception", e);
			returnData.setReturnCode(ReturnCode.EXCEPTION.getCode());
		}
		return returnData;
	}

	private boolean checkUpdateArticleRecommend(ArticleRecommend recomend) {
		if (recomend == null) {
			return false;
		}
		if (recomend.getRecommendId() == null) {
			return false;
		}
		return true;
	}

}
