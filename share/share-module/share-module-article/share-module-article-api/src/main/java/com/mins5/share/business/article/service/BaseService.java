package com.mins5.share.business.article.service;

import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

/**
 * service层基类接口
 * 
 * @param <E>
 * @author zhanglin
 * @since 2014年5月27日
 */
public interface BaseService<E> {

	ReturnData<E> save(E e);

	ReturnData<VOID> deleteById(E e);

	ReturnData<E> update(E e);

	ReturnData<E> findById(long id, E e);

}
