/**
 * 
 */
package com.mins5.share.web.controller.login;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping(value="/login")
public class LoginController {

	@RequestMapping(value="/test")
	public String test() {
		
		return "test";
	}
}
