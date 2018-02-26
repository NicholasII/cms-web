package com.sun.cms.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.dto.system.BaseInfo;
import com.sun.cms.web.utils.BaseinfoUtil;
@Controller
@RequestMapping("/system/baseinfo")
@AuthClass
public class BaseinfoController {
	
	@RequestMapping("/show")
	@AuthMethod
	public ModelAndView show(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("system/show");
		BaseInfo baseInfo = (BaseInfo) session.getServletContext().getAttribute("baseinfo");
		modelAndView.addObject("baseinfo", JSON.toJSONString(baseInfo));
		return modelAndView;
	}
	
	@RequestMapping("/updatePage")
	@AuthMethod
	public ModelAndView updatePage(HttpSession session){
		ModelAndView modelAndView = new ModelAndView("system/update");
		BaseInfo baseInfo = (BaseInfo) session.getServletContext().getAttribute("baseinfo");
		modelAndView.addObject("baseinfo", JSON.toJSONString(baseInfo));
		return modelAndView;
	}
	
	
	@RequestMapping("/update")
	@AuthMethod
	public ModelAndView update(BaseInfo baseInfo,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("system/show");
		BaseInfo info = BaseinfoUtil.getInstance().write(baseInfo);
		session.getServletContext().setAttribute("baseinfo", info);
		modelAndView.addObject("baseinfo", JSON.toJSONString(info));
		return modelAndView;
	}
}
