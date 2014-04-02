package com.mins5.share.util;

import java.util.List;


/**
 * EasyUI树形结构模型
 * @author zhoutian
 * @since 2014年4月1日
 */
public class EasyUITreeModule {

	private Long id;
	
	private String text;
	
	private String state = "closed";
	
	private Boolean checked = false;
	
	private List<Object> attributes;
	
	private List<Object> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}
	
}
