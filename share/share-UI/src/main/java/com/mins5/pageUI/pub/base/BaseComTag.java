/*     */package com.mins5.pageUI.pub.base;

/*     */
/*     */import java.io.IOException;

/*     */
import javax.servlet.http.HttpServletRequest;
/*     */
import javax.servlet.jsp.JspException;
/*     */
import javax.servlet.jsp.JspTagException;
/*     */
import javax.servlet.jsp.JspWriter;
/*     */
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */public class BaseComTag extends TagSupport
/*     */{
	/*     */private static final long serialVersionUID = 3174234039143531070L;
	/*     */private JspWriter writer;
	/*     */protected String name;
	/*     */protected String id;

	/*     */
	/*     */public String getId()
	/*     */{
		/* 34 */return this.id;
		/*     */}

	/*     */
	/*     */public void setId(String id) {
		/* 38 */this.id = id;
		/*     */}

	/*     */
	/*     */protected Object getBean(String beanName)
	/*     */{
		/* 49 */WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.pageContext
						.getServletContext());
		/*     */
		/* 51 */return wac.getBean(beanName);
		/*     */}

	/*     */
	/*     */protected void write(String s)
	/*     */throws JspTagException
	/*     */{
		/*     */try
		/*     */{
			/* 63 */this.writer.write(s);
			/*     */} catch (IOException e) {
			/* 65 */throw new JspTagException("Writer Exception: "
					+ e.getMessage());
			/*     */}
		/*     */}

	/*     */
	/*     */protected void beginWrite() {
		/* 70 */this.writer = this.pageContext.getOut();
		/*     */}

	/*     */
	/*     */protected void writeln(String s)
	/*     */throws JspTagException
	/*     */{
		/* 80 */write(s + "\r\n");
		/*     */}

	/*     */
	/*     */protected String getContextPath()
	/*     */{
		/* 89 */return ((HttpServletRequest) this.pageContext.getRequest())
				.getContextPath();
		/*     */}

	/*     */
	/*     */protected Object getTagValue() throws JspException
	/*     */{
		/* 94 */HttpServletRequest request = (HttpServletRequest) this.pageContext
				.getRequest();
		/*     */try {
			/* 96 */return parseTagName(this.name, request);
			/*     */} catch (Exception e) {
			/* 98 */throw new JspException(e);
			/*     */}
		/*     */}

	/*     */
	/*     */public static Object parseTagName(String name,
			HttpServletRequest request)
	/*     */throws Exception
	/*     */{
		/* 113 */String key = name;
		/* 114 */String expression = name;
		/* 115 */if (name.contains(".")) {
			/* 116 */key = name.substring(0, name.indexOf("."));
			/* 117 */expression = name.substring(name.indexOf(".") + 1);
			/*     */}
		/* 119 */Object obj = null;
		/* 120 */if (request.getAttribute(key) != null) {
			/* 121 */if (key.equals(expression))
				/* 122 */obj = request.getAttribute(key);
			/*     */else {
				/* 124 */// obj = Ognl.getValue(expression,
							// request.getAttribute(key));
				/*     */}
			/*     */}
		/* 127 */return obj;
		/*     */}

	/*     */
	/*     */public JspWriter getWriter()
	/*     */{
		/* 132 */return this.writer;
		/*     */}

	/*     */
	/*     */public void setWriter(JspWriter writer) {
		/* 136 */this.writer = writer;
		/*     */}

	/*     */
	/*     */public String getName() {
		/* 140 */return this.name;
		/*     */}

	/*     */
	/*     */public void setName(String name) {
		/* 144 */this.name = name;
		/*     */}
	/*     */
}

/*
 * Location:
 * E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPUI-1.2.0.jar
 * Qualified Name: com.cattsoft.dapui.pub.base.BaseComTag JD-Core Version: 0.6.2
 */