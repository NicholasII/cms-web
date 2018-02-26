package com.sun.cms.web.controller.topic;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.cms.common.utils.UUIDUtil;
import com.sun.cms.model.PageDto;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.channel.ChannelSimpleTree;
import com.sun.cms.web.dto.topic.AttachmentDto;
import com.sun.cms.web.dto.topic.TopicDto;
import com.sun.cms.web.service.channel.UserChannelService;
import com.sun.cms.web.service.topic.AttachService;
import com.sun.cms.web.service.topic.KeywordService;
import com.sun.cms.web.service.topic.TopicService;
import com.sun.cms.web.utils.CommonUtils;
@Controller
@RequestMapping("/topic")
@AuthClass("login")
public class TopicController extends BaseController<TopicDto>{
	@Autowired
	TopicService topicService;
	@Autowired
	UserChannelService userChannelService;
	@Autowired
	AttachService attachService;
	@Autowired
	KeywordService keywordService;
	//图片附件的类型
	private final static List<String> imgTypes = Arrays.asList("jpg","jpeg","gif","png");
	
	
	@RequestMapping("/addPage")
	@AuthMethod(role="articlepublisher")
	public ModelAndView addPage(HttpSession session){
		ModelAndView modelAndView = new ModelAndView("topic/add");
		UserDto user = (UserDto)session.getAttribute("userInfo");
		String userid = user.getUserId();
		JSONArray checkedArray = getChannelTree(userid);
		if (checkedArray!=null) {
			modelAndView.addObject("tree", checkedArray);	
		}
		return modelAndView;
	}
	
	private JSONArray getChannelTree(String userid){
		List<ChannelSimpleTree> channels = userChannelService.havingChannels(userid);
		if (channels!=null) {
			return JSON.parseArray(JSON.toJSONString(channels));
		}
		return null;
	}
	
	
	@RequestMapping("/list")
	@AuthMethod(role="{articlechecker,articlepublisher}")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("topic/list");
		String title = request.getParameter("title");
		String keyward = request.getParameter("keyword");
		String isPublish = request.getParameter("ispublish");
		String channelid = request.getParameter("channel");
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
		if (CommonUtils.isEmpty(channelid)) {
			topic.setChannelid(Integer.valueOf(channelid));
		}
		Integer pageNum = SystemContext.getPageOffset();
		Integer pageSize = SystemContext.getPageSize();
		PageDto<TopicDto> pageDto = topicService.getPageList(topic, pageNum, pageSize);
		
		modelAndView.addObject("topic", pageDto);
		modelAndView.addObject("total", pageDto.getTotal());
		return modelAndView;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AuthMethod(role="articlepublisher")
	public ModelAndView add(HttpServletRequest request,HttpSession session){
		boolean result = false;
		ModelAndView modelAndView;
		try {
			String title = request.getParameter("title");
			String[] keyward = request.getParameterValues("keyward");
			String channelname = request.getParameter("channelname");
			String channelid = request.getParameter("channelid");
			String isPublish = request.getParameter("status");
			String recommend = request.getParameter("recommend");
			String createdate = request.getParameter("createdate");
			String content = request.getParameter("content");
			String summary = request.getParameter("summary");
			String[] attachs = request.getParameterValues("attachments");
			String channelpicid = request.getParameter("channelpicid");
			
			String topicId = UUIDUtil.generateUUID();
			TopicDto topic = new TopicDto();
			if (CommonUtils.isEmpty(title)) {
				topic.setTitle(title);
			}
			if (CommonUtils.isEmpty(isPublish)) {
				topic.setStatus(Integer.valueOf(isPublish));
			}
			if (CommonUtils.isEmpty(channelid)) {
				topic.setChannelid(Integer.valueOf(channelid));
			}
			if (CommonUtils.isEmpty(channelname)) {
				topic.setChannelname(channelname);
			}
			if (keyward!=null && keyward.length>0) {
				StringBuffer keyword = new StringBuffer();
				for (int i = 0; i < keyward.length; i++) {
					keyword.append(keyward[i]);
					if (i!=keyward.length-1) {
						keyword.append("|");
					}
				}
				topic.setKeyward(keyword.toString());
			}
			if (CommonUtils.isEmpty(content)) {
				topic.setContent(content);
			}
			if (CommonUtils.isEmpty(createdate)) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				Date date = format.parse(createdate);
				topic.setCreatedate(date);
			}
			topic.setId(topicId);
			topic.setPublishdate(new Date());
			UserDto userDto = (UserDto)session.getAttribute("userInfo");
			if (userDto!=null) {
				topic.setPublisher(userDto.getUserName());
				topic.setPublisherid(userDto.getUserId());
			}

			if (CommonUtils.isEmpty(recommend)) {
				topic.setRecommend(Integer.valueOf(recommend));
			}
			if (CommonUtils.isEmpty(summary)) {
				topic.setSummary(summary);
			}
			if (CommonUtils.isEmpty(channelpicid)) {
				topic.setChannelpicid(channelpicid);
			}
			result = topicService.insert(topic);
			if (attachs!=null && attachs.length>0) {
				attachService.bindTopic(attachs, topicId);
			}	
			if (keyward!=null && keyward.length>0) {
				keywordService.addKeyword(keyward);
			}
			if (result) {
				modelAndView = new ModelAndView("redirect:/topic/list");
			}else {
				modelAndView = new ModelAndView("topic/add");
			}
		} catch (Exception e) {
			modelAndView = new ModelAndView("topic/add");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping("/updatePublish/{topicid}/{isPublish}")
	@AuthMethod(role="articlepublisher")
	public ModelAndView updatePublish(@PathVariable String topicid,@PathVariable int isPublish,HttpServletRequest request,HttpSession session){
		boolean result = false;
		ModelAndView modelAndView;
		try {
			TopicDto topic = new TopicDto();
			topic.setId(topicid);
			if (isPublish==1) {
				topic.setStatus(1);
			}else if (isPublish==0) {
				topic.setStatus(0);
			}
			result = topicService.update(topic);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView = new ModelAndView("redirect:/topic/list");
		return modelAndView;
	}
	
	
	@RequestMapping("/update/{topicid}")
	@AuthMethod(role="articlepublisher")
	public ModelAndView update(@PathVariable String topicid,HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("topic/update");
		TopicDto temp = new TopicDto();
		temp.setId(topicid);
		TopicDto topic = topicService.getOne(temp);		
		modelAndView.addObject("topic", JSON.toJSONString(topic));
		UserDto user = (UserDto)session.getAttribute("userInfo");
		String userid = user.getUserId();
		JSONArray checkedArray = getChannelTree(userid);
		if (checkedArray!=null) {
			modelAndView.addObject("tree", checkedArray);	
		}
		List<AttachmentDto> attachments = attachService.getAttachs(topicid);
		modelAndView.addObject("attachment", JSON.parseArray(JSON.toJSONString(attachments)));	
		return modelAndView;
	}
	
	
	@RequestMapping("/updateContent")
	@AuthMethod(role="articlepublisher")
	public ModelAndView updateContent(HttpServletRequest request,HttpSession session){
		boolean result = false;
		ModelAndView modelAndView = new ModelAndView("topic/list");
		try {
			String topicId = request.getParameter("id");
			String title = request.getParameter("title");
			String[] keyward = request.getParameterValues("keyward");
			String channelname = request.getParameter("channelname");
			String channelid = request.getParameter("channelid");
			String isPublish = request.getParameter("status");
			String recommend = request.getParameter("recommend");
			String createdate = request.getParameter("createdate");
			String content = request.getParameter("content");
			String summary = request.getParameter("summary");
			String[] attachs = request.getParameterValues("attachments");
			String channelpicid = request.getParameter("channelpicid");
			
			TopicDto topic = new TopicDto();
			topic.setId(topicId);
			if (CommonUtils.isEmpty(title)) {
				topic.setTitle(title);
			}
			if (CommonUtils.isEmpty(isPublish)) {
				topic.setStatus(Integer.valueOf(isPublish));
			}
			if (CommonUtils.isEmpty(channelid)) {
				topic.setChannelid(Integer.valueOf(channelid));
			}
			if (CommonUtils.isEmpty(channelname)) {
				topic.setChannelname(channelname);
			}
			if (keyward!=null && keyward.length>0) {
				StringBuffer keyword = new StringBuffer();
				for (int i = 0; i < keyward.length; i++) {
					keyword.append(keyward[i]);
					if (i!=keyward.length-1) {
						keyword.append("|");
					}
				}
				topic.setKeyward(keyword.toString());
			}
			if (CommonUtils.isEmpty(content)) {
				topic.setContent(content);
			}
			if (CommonUtils.isEmpty(createdate)) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				Date date = format.parse(createdate);
				topic.setCreatedate(date);
			}						

			if (CommonUtils.isEmpty(recommend)) {
				topic.setRecommend(Integer.valueOf(recommend));
			}
			if (CommonUtils.isEmpty(summary)) {
				topic.setSummary(summary);
			}
			if (CommonUtils.isEmpty(channelpicid)) {
				topic.setChannelpicid(channelpicid);
			}
			result = topicService.update(topic);

			if (result) {
				modelAndView.addObject("state", "success");
			}else {
				modelAndView.addObject("state", "fail");
			}
		} catch (Exception e) {
			modelAndView.addObject("state", "fail");
			e.printStackTrace();
		}
		TopicDto topic = new TopicDto();
		Integer pageSize = SystemContext.getPageSize();
		PageDto<TopicDto> pageDto = topicService.getPageList(topic, 1, pageSize);	
		modelAndView.addObject("topic", pageDto);
		modelAndView.addObject("total", pageDto.getTotal());

		return modelAndView;
	}
	
	@AuthMethod(role="articlepublisher")
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap uploadFile(HttpServletRequest request, HttpServletResponse response){
		ModelMap map = new ModelMap();
		try {
			MultipartHttpServletRequest mutiRequest = (MultipartHttpServletRequest) request;
			MultipartFile attach = mutiRequest.getFile("attach");
			String ext  = FilenameUtils.getExtension(attach.getOriginalFilename());
			AttachmentDto attachment = new AttachmentDto();
			attachment.setId(UUIDUtil.generateUUID());
			attachment.setIsattach(1);
			if (imgTypes.contains(ext)) {
				attachment.setIsimg(1);
			}else {
				attachment.setIsimg(0);
			}
			attachment.setIsindexpic(0);
			attachment.setNewname(String.valueOf(new Date().getTime())+"."+ext);
			attachment.setOldname(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			attachment.setSize(attach.getSize());
			attachment.setSuffix(ext);
			attachment.setType(attach.getContentType());
			attachService.add(attachment, attach.getInputStream());
			map.addAttribute("status", true);
			map.addAttribute("attach", attachment);
		} catch (Exception e) {
			map.addAttribute("status", false);
			e.printStackTrace();
		}
		
		return map;
	}
	
	@RequestMapping("/deleteTopic/{topicid}")
	public ModelAndView deleteTopic(@PathVariable String topicid){
		ModelAndView modelAndView = new ModelAndView("topic/list");
		TopicDto topicDto = new TopicDto();
		topicDto.setId(topicid);
		boolean result = topicService.delete(topicDto);
		if (result) {
			modelAndView.addObject("success", true);
		}else {
			modelAndView.addObject("success", false);
		}
		TopicDto topic = new TopicDto();
		Integer pageSize = SystemContext.getPageSize();
		PageDto<TopicDto> pageDto = topicService.getPageList(topic, 1, pageSize);	
		modelAndView.addObject("topic", pageDto);
		modelAndView.addObject("total", pageDto.getTotal());
		return modelAndView;
	}
}
