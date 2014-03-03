/**
 * 
 */
package com.mins5.share.web.controller.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mins5
 * @since 2014-3-3
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/a")
public class A {

	@RequestMapping(value="/a1")
	public String a1() {
		System.out.println("A.a1()");
		return "demo/a1";
	}
}
