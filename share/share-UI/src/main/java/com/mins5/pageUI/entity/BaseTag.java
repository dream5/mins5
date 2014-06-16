package com.mins5.pageUI.entity;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class BaseTag extends TagSupport {
	private static final long serialVersionUID = 3174234039143531070L;
	private JspWriter writer;
	protected String name;
	protected String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	protected Object getBean(String beanName) {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.pageContext
						.getServletContext());

		return wac.getBean(beanName);
	}

	protected void write(String s) throws JspTagException {
		try {
			this.writer.write(s);
		} catch (IOException e) {
			throw new JspTagException("Writer Exception: " + e.getMessage());
		}
	}

	protected void beginWrite() {
		this.writer = this.pageContext.getOut();
	}

	protected void writeln(String s) throws JspTagException {
		write(s + "\r\n");
	}

	protected String getContextPath() {
		return ((HttpServletRequest) this.pageContext.getRequest())
				.getContextPath();
	}

	protected Object getTagValue() throws JspException {
		HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		try {
			return parseTagName(this.name, request);
		} catch (Exception e) {
			throw new JspException(e);
		}
	}

	public int doStartTag() throws JspException {
		try {
			beginWrite();
			doTag(getTagValue());
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new JspException(ex);
		}
		return 1;
	}

	public static Object parseTagName(String name, HttpServletRequest request)
			throws Exception {
		String key = name;
		String expression = name;
		if (name.contains(".")) {
			key = name.substring(0, name.indexOf("."));
			expression = name.substring(name.indexOf(".") + 1);
		}
		Object obj = null;
		if (request.getAttribute(key) != null) {
			if (key.equals(expression))
				obj = request.getAttribute(key);
			else {
				// obj = Ognl.getValue(expression, request.getAttribute(key));
			}
		}
		return obj;
	}

	protected abstract void doTag(Object paramObject) throws Exception;

	public JspWriter getWriter() {
		return this.writer;
	}

	public void setWriter(JspWriter writer) {
		this.writer = writer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
