package com.sun.cms.web.controller.channel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.channel.Channel;
import com.sun.cms.web.dto.channel.ChannelTree;
import com.sun.cms.web.service.channel.ChannelService;
import com.sun.cms.web.utils.Constant;

@Controller
@RequestMapping("/channel")
public class ChannelContoller extends BaseController<Channel>{
	
	@Autowired
	ChannelService channelService;
	
	@RequestMapping("/page")
	public ModelAndView page(@RequestParam(value="pid",defaultValue="null",required=true)String p_id) {
		ModelAndView modelAndView = new ModelAndView("/channel/channel");
		List<ChannelTree> trees = new ArrayList<>();
		ChannelTree tree = new ChannelTree();
		tree.setId(Constant.ROOT_ID);
		tree.setName(Constant.ROOT_NAME);
		tree.setOpen(true);
		tree.setPid(null);
		trees.add(tree);
		trees = channelService.getChildren(trees);
		String channels =  JSON.toJSONString(trees);
		modelAndView.addObject("tree",channels);
		if ("null".equals(p_id)) {
			modelAndView.addObject("pid", Constant.ROOT_ID);
			Channel channel = new Channel();
			channel.setP_id(Constant.ROOT_ID);
			List<Channel> channellist = channelService.getAllList(channel);
			modelAndView.addObject("channel", channellist);
		}else {
			modelAndView.addObject("pid", Integer.valueOf(p_id));
			Channel channel = new Channel();
			channel.setP_id(Integer.valueOf(p_id));
			List<Channel> channellist = channelService.getAllList(channel);
			modelAndView.addObject("channel", channellist);
		}		
		return modelAndView;
	}
	
	
	
	@RequestMapping("/addPage/{pid}")
	public ModelAndView addPage(@PathVariable int pid){
		ModelAndView modelAndView = new ModelAndView("/channel/add");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	@RequestMapping("/add")
	@ResponseBody
	public ModelMap add(Channel channel){
		ModelMap map = new ModelMap();
		if (channel.getId()!=null) {
			boolean result = channelService.insert(channel);
			map.addAttribute(Constant.SUCCESS, result);
		}else {
			map.addAttribute(Constant.SUCCESS, false);
		}
		return map;
	}
}
