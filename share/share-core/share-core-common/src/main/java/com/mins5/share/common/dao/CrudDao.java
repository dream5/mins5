package com.mins5.share.common.dao;

import com.mins5.share.common.domain.DomainObject;

/**
 * @author mins5
 * @since 2014-2-28
 * @param <ID>
 * @param <E>
 */
public interface CrudDao<ID, E extends DomainObject> {

	int save(E e);

	int deleteById(ID id);
	
	int update(E e);
	
	E findById(ID id);
}
