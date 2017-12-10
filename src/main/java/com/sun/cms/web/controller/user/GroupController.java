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
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.GroupDto;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.UserGroupDto;
import com.sun.cms.web.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController extends BaseController<GroupDto>{
	
	@Autowired
	GroupService groupService;
	
	@RequestMapping(value="/page")
	public ModelAndView page(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView =  new ModelAndView("group/list");
		GroupDto dto = new GroupDto();
		int currpage = SystemContext.getPageOffset();
		int pagesize = SystemContext.getPageSize();
		PageDto<GroupDto> groups = groupService.getPageList(dto, currpage, pagesize);
		modelAndView.addObject("group", groups);
		if (groups!=null) {
			modelAndView.addObject("total", groups.getTotal());
		}
		return modelAndView;
	}
	@RequestMapping("/numbers")
	public ModelAndView members(@RequestParam(value="groupid" , required=true)String groupId){
		ModelAndView modelAndView = new ModelAndView("group/member");
		GroupDto dto = new GroupDto();
		dto.setGroupId(groupId);
		GroupDto group = groupService.select(dto);
		modelAndView.addObject("group", group);
		UserGroupDto userRoleDto = new UserGroupDto();
		userRoleDto.setGroupid(groupId);
		List<UserDto> members = groupService.members(userRoleDto);
		modelAndView.addObject("member", members);
		return modelAndView;

	}
}
