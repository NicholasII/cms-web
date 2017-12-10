package com.sun.cms.web.controller.user;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.model.PageDto;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.service.UserService;
import com.sun.cms.web.utils.Constant;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<UserDto>{
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/page")
	public ModelAndView page(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView =  new ModelAndView("user/list");
		UserDto dto = new UserDto();
		int currpage = SystemContext.getPageOffset();
		int pagesize = SystemContext.getPageSize();
		PageDto<UserDto> users = userService.getPageList(dto, currpage, pagesize);
		modelAndView.addObject("users", users);
		if (users!=null) {
			modelAndView.addObject("total", users.getTotal());
		}
		return modelAndView;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public ModelMap list(HttpServletRequest request){
		ModelMap map = new ModelMap();
		UserDto dto = new UserDto();
		int currpage = SystemContext.getPageOffset();
		int pagesize = SystemContext.getPageSize();
		PageDto<UserDto> users = userService.getPageList(dto, currpage, pagesize);
		map.addAttribute("users", users);
		return map;
	}
	
	@RequestMapping(value="/addpage")
	public ModelAndView addpage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView =  new ModelAndView("user/add");
		return modelAndView;
	}
	
	@RequestMapping(value="/add")
	public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView =  new ModelAndView("user/add");
		String userid = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String mobile = request.getParameter("mobile");
		String status = request.getParameter("status");
		String[] roles = request.getParameterValues("role");
		String[] groups = request.getParameterValues("group");
		UserDto dto = new UserDto();
		dto.setUserId(userid);
		dto.setUserName(name);
		dto.setPassword(password);
		dto.setMobile(mobile);
		dto.setTel(tel);
		dto.setCreateTime(new Date());
		if ("启用".equals(status)) {
			dto.setStatus(1);
		}else if ("停用".equals(status)) {
			dto.setStatus(0);
		}
		boolean result = userService.createUser(dto, roles, groups);
		modelAndView.addObject(Constant.SUCCESS, result);
		return modelAndView;
	}
}
