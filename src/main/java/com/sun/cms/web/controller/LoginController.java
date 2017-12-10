package com.sun.cms.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.model.BaseDto;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.service.UserService;
@Controller
public class LoginController extends BaseController<BaseDto>{
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public ModelAndView page(){
		return new ModelAndView("admin/login");
	}
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,
			@RequestParam(value="name",required=true)String userid,
			@RequestParam(value="password",required=true)String password){
		UserDto user = new UserDto();
		user.setUserId(userid);
		user.setPassword(password);
		List<UserDto> dtos = userService.getAllList(user);
		if (dtos!=null && dtos.size()>0) {
			UserDto userInfo = dtos.get(0);
			request.getSession().setAttribute("userInfo", userInfo);
			ModelAndView modelAndView = new ModelAndView("admin/main");
			modelAndView.addObject("user", userid);
			modelAndView.addObject("date", new Date());
			return modelAndView;
		}else {
			ModelAndView modelAndView = new ModelAndView("admin/login");
			return modelAndView;
		}
	}

}
