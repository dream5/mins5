package com.mins5.share.business.article.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mins5.share.business.article.domain.ArticleKind;
import com.mins5.share.common.dao.CrudDao;

/**
 * <p>文字种类</p>
 * @author zhanglin
 * @since 20140312
 */
@Service
public interface ArticleKindDao extends CrudDao<Long, ArticleKind>{
	
	List<ArticleKind> findAllArticleKind();

}
