package com.mins5.pageUI.pub.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerLayout {

	private void addMacros(Template template) {
		try {
			Configuration cfg = new Configuration();
			ClassTemplateLoader ctl = new ClassTemplateLoader(
					FreeMarkerLayout.class, "/uiTemplet");
			TemplateLoader[] loaders = { ctl };
			MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
			cfg.setTemplateLoader(mtl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doExecute() {
		try {
			Configuration cfg = new Configuration();
			ClassTemplateLoader ctl = new ClassTemplateLoader(
					FreeMarkerLayout.class, "/uiTemplet");
			TemplateLoader[] loaders = { ctl };
			MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
			cfg.setTemplateLoader(mtl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}