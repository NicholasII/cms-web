package com.sun.cms.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.cms.model.PageDto;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.GroupDto;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.UserGroupDto;
import com.sun.cms.web.dto.channel.ChannelSimpleTree;
import com.sun.cms.web.dto.channel.ChannelTree;
import com.sun.cms.web.dto.channel.GroupChannelDto;
import com.sun.cms.web.service.GroupService;
import com.sun.cms.web.service.channel.ChannelService;
import com.sun.cms.web.service.channel.GroupChannelService;
import com.sun.cms.web.utils.Constant;

@Controller
@RequestMapping("/group")
public class GroupController extends BaseController<GroupDto>{
	
	@Autowired
	GroupService groupService;
	@Autowired
	GroupChannelService groupChannelService;
	
	@Autowired
	ChannelService channelService;
	/**
	 * 分组页面-全部分组列表
	 * dongqun
	 * 2017年12月26日上午10:38:41
	 * @param request
	 * @param response
	 * @return
	 */
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
	/**
	 * 某个分组成员页面--成员列表
	 * dongqun
	 * 2017年12月26日上午10:38:29
	 * @param groupId
	 * @return
	 */
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
	/**
	 * 添加栏目页面
	 * @author dongqun
	 * 2017年12月26日下午3:42:41
	 * @param request
	 * @return
	 */
	@RequestMapping("/addchannel/page")
	public ModelAndView addChannlePage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/group/addChannel");
		List<ChannelTree> trees = new ArrayList<>();
		ChannelTree tree = new ChannelTree();
		tree.setId(Constant.ROOT_ID);
		tree.setName(Constant.ROOT_NAME);
		tree.setOpen(true);
		tree.setPid(null);
		trees.add(tree);
		trees = channelService.getChildren(trees);
		String str_tree =  JSON.toJSONString(trees);
		modelAndView.addObject("tree",str_tree);
		
		String pid = request.getParameter("pid");
		List<ChannelSimpleTree> checkedtree = groupChannelService.havingChannels(pid);
		modelAndView.addObject("checkedtree", checkedtree);
		return modelAndView;
	}
	/**
	 * 向分组添加栏目
	 * @author dongqun
	 * 2017年12月26日下午1:39:01
	 * @param request
	 * @return
	 */
	@RequestMapping("/bindChannel")
	@ResponseBody
	public ModelMap bindChannel(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String channels = request.getParameter("data");
		List<GroupChannelDto> list = JSON.parseArray(channels,GroupChannelDto.class);
		boolean result = false;
		if (list!=null) {
			result = groupChannelService.insert(list);
		}
		map.addAttribute(Constant.SUCCESS, result);
		return map;
	}
	/**
	 * 删除栏目
	 * @author dongqun
	 * 2017年12月26日下午3:00:29
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteChannel")
	@ResponseBody
	public ModelMap deleteChannel(HttpServletRequest request){
		ModelMap map = new ModelMap();
		String channels = request.getParameter("data");
		List<GroupChannelDto> list = JSON.parseArray(channels,GroupChannelDto.class);
		boolean result = false;
		if (list!=null) {
			result = groupChannelService.delete(list);
		}
		map.addAttribute(Constant.SUCCESS, result);
		return map;
	}
	/**
	 * 获取属于分组的栏木树
	 * @author dongqun
	 * 2017年12月26日下午3:10:03
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTree")
	public ModelAndView getTree(HttpServletRequest request){
		ModelAndView map = new ModelAndView("/group/channelTree");
		String pid = request.getParameter("pid");
		List<ChannelSimpleTree> tree = groupChannelService.havingChannels(pid);
		map.addObject("tree", tree);
		return map;
	}
	
	
	
}
