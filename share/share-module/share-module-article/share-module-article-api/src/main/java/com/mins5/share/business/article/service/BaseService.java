package com.mins5.share.business.article.service;

import com.mins5.share.common.service.ReturnData;
import com.mins5.share.common.service.VOID;

public interface BaseService<E> {

	ReturnData<E> save(E e);

	ReturnData<VOID> deleteById(long id);

	ReturnData<E> update(E e);

	ReturnData<E> findById(long id);

}
