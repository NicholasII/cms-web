package com.sun.cms.web.controller.topic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.cms.model.PageDto;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.channel.ChannelSimpleTree;
import com.sun.cms.web.dto.topic.TopicDto;
import com.sun.cms.web.service.channel.UserChannelService;
import com.sun.cms.web.service.topic.TopicService;
import com.sun.cms.web.utils.CommonUtils;
import com.sun.cms.web.utils.Constant;
@Controller
@RequestMapping("/topic")
@AuthClass("login")
public class TopicController extends BaseController<TopicDto>{
	@Autowired
	TopicService topicService;
	@Autowired
	UserChannelService userChannelService;
	
	@RequestMapping("/page")
	@AuthMethod(role="{articlechecker,articlepublisher}")
	public ModelAndView listPage(){
		ModelAndView modelAndView = new ModelAndView("topic/list");
		return modelAndView;
	}
	
	@RequestMapping("/addPage")
	@AuthMethod(role="articlepublisher")
	public ModelAndView addPage(HttpSession session){
		ModelAndView modelAndView = new ModelAndView("topic/add");
		UserDto user = (UserDto)session.getAttribute("userInfo");
		String userid = user.getUserId();
		List<ChannelSimpleTree> channels = userChannelService.havingChannels(userid);
		if (channels!=null) {
			JSONArray checkedArray = JSON.parseArray(JSON.toJSONString(channels));
			modelAndView.addObject("tree", checkedArray);	
		}
		return modelAndView;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	@AuthMethod(role="{articlechecker,articlepublisher}")
	public ModelMap list(HttpServletRequest request){
		String title = request.getParameter("title");
		String keyward = request.getParameter("keyward");
		String isPublish = request.getParameter("ispublish");
		TopicDto topic = new TopicDto();
		if (CommonUtils.isEmpty(title)) {
			topic.setTitle(title);
		}
		if (CommonUtils.isEmpty(keyward)) {
			topic.setKeyward(keyward);
		}
		if (CommonUtils.isEmpty(isPublish)) {
			topic.setStatus(Integer.valueOf(isPublish));
		}
		Integer pageNum = SystemContext.getPageOffset();
		Integer pageSize = SystemContext.getPageSize();
		PageDto<TopicDto> pageDto = topicService.getPageList(topic, pageNum, pageSize);
		ModelMap map = new ModelMap();
		map.addAttribute(Constant.ROWS, pageDto);
		return map;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	@AuthMethod(role="articlepublisher")
	public ModelMap add(HttpServletRequest request){
		String title = request.getParameter("title");
		String keyward = request.getParameter("keyward");
		String isPublish = request.getParameter("ispublish");
		TopicDto topic = new TopicDto();
		if (CommonUtils.isEmpty(title)) {
			topic.setTitle(title);
		}
		if (CommonUtils.isEmpty(keyward)) {
			topic.setKeyward(keyward);
		}
		if (CommonUtils.isEmpty(isPublish)) {
			topic.setStatus(Integer.valueOf(isPublish));
		}
		Integer pageNum = SystemContext.getPageOffset();
		Integer pageSize = SystemContext.getPageSize();
		PageDto<TopicDto> pageDto = topicService.getPageList(topic, pageNum, pageSize);
		ModelMap map = new ModelMap();
		map.addAttribute(Constant.ROWS, pageDto);
		return map;
	}
	@AuthMethod(role="articlepublisher")
	@RequestMapping("/upload")
	@ResponseBody
	public ModelMap uploadFile(MultipartFile file,HttpServletRequest request){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest mutiRequest = (MultipartHttpServletRequest) request;
		MultipartFile file2 = mutiRequest.getFile("attach");
		System.out.println(file2.getOriginalFilename());
		ModelMap map = new ModelMap();
		map.addAttribute("fileOriginalName", file2.getOriginalFilename());
		map.addAttribute("fileSize", file2.getSize());
		map.addAttribute("fileType", file2.getContentType());
		map.addAttribute("fileName", file2.getName());
		return map;
	}
	
}
