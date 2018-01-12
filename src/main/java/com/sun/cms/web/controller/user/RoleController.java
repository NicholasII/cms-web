package com.sun.cms.web.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.model.PageDto;
import com.sun.cms.web.dto.RoleDto;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.UserRoleDto;
import com.sun.cms.web.service.user.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/page")
	public ModelAndView page(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView =  new ModelAndView("role/list");
		RoleDto dto = new RoleDto();
		int currpage = SystemContext.getPageOffset();
		int pagesize = SystemContext.getPageSize();
		PageDto<RoleDto> roles = roleService.getPageList(dto, currpage, pagesize);
		modelAndView.addObject("roles", roles);
		if (roles!=null) {
			modelAndView.addObject("total", roles.getTotal());
		}
		return modelAndView;
	}
	@RequestMapping("/numbers")
	public ModelAndView members(@RequestParam(value="roleid" , required=true)String roleid){
		ModelAndView modelAndView = new ModelAndView("role/member");
		RoleDto dto = new RoleDto();
		dto.setRoleId(roleid);
		RoleDto role = roleService.select(dto);
		modelAndView.addObject("role", role);
		UserRoleDto userRoleDto = new UserRoleDto();
		userRoleDto.setRoleId(roleid);
		List<UserDto> members = roleService.members(userRoleDto);
		modelAndView.addObject("member", members);
		return modelAndView;

	}
}
