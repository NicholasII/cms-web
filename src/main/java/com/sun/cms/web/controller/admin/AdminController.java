package com.sun.cms.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/navi")
	public ModelAndView navi(){
		return new ModelAndView("/admin/navi");
	}
}
