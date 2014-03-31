/**
 * 
 */
package com.mins5.share.business.article.service;

import java.util.Date;
import java.util.List;

import com.mins5.share.business.article.domain.ArticleLabel;
import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.ReturnPageData;
import com.mins5.share.common.service.VOID;

/**
 * @author zhoutian
 * @since 2014年3月13日
 */
public interface ArticleLabelService {

	/**
	 * 新增
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleLabel> saveArticleLabel(ArticleLabel articleLabel);

	/**
	 * 根据ID删除
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param articleLabelId
	 * @return
	 */
	ReturnData<VOID> deleteArticleLabelById(Long labelId);

	/**
	 * 更新
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param articleLabel
	 * @return
	 */
	ReturnData<ArticleLabel> updateArticleLabel(ArticleLabel articleLabel);

	/**
	 * 根据ID查询
	 * 
	 * @author zhoutian
	 * @since 2014年3月13日
	 * @param labelId
	 * @return
	 */
	ReturnData<ArticleLabel> findArticleLabelById(Long labelId);

	/**
	 * 查询一定数目的标签
	 * 
	 * @param amount
	 *            数量
	 * @return
	 */
	ReturnData<List<ArticleLabel>> findArticleLabelByNum(int amount);

	/**
	 * 分页查询文章标签列表
	 * @author zhoutian
	 * @since 2014年3月22日
	 * @param labelName 标签名称
	 * @param status 标签状态
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPage 当前页
	 * @param onePageSize 每行行数
	 * @return
	 */
	ReturnPageData<List<ArticleLabel>> findArticleLabelByLabelNameAndStatusAndCreateTime(
			String labelName, String status, Date beginTime, Date endTime,
			int currentPage, int onePageSize);
	
	/**
	 * 删除文章标签
	 * <p/>关联删除文章标签关联表数据
	 * @author zhoutian
	 * @since 2014年3月31日
	 * @param articleLabelId
	 * @return
	 */
	ReturnData<VOID> deleteArticleLabelAndArticleLabelRelByLabelId(Long articleLabelId);
}
