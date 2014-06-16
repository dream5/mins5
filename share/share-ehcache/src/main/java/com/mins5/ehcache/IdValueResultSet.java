package com.mins5.ehcache;

import com.mins5.share.common.domain.DomainObject;

public class IdValueResultSet extends DomainObject {
	private String id;
	private String value;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}