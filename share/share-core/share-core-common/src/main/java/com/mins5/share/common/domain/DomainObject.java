package com.mins5.share.common.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author zhoutian
 * @since 2014-2-28
 */
public class DomainObject implements Serializable {

	private static final long serialVersionUID = 379508630360141673L;

	public String toString()
	{
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	public boolean equals(Object o)
	{
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
