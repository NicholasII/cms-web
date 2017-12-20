package com.sun.cms.web.controller.channel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.cms.model.PageDto;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.channel.Channel;
import com.sun.cms.web.dto.channel.ChannelTree;
import com.sun.cms.web.service.channel.ChannelService;
import com.sun.cms.web.utils.Constant;

@Controller
@RequestMapping("/channel")
public class ChannelContoller extends BaseController<Channel>{
	
	@Autowired
	ChannelService channelService;
	/**
	 * 栏目管理总页面：包括栏木树和当前选中栏目的子栏目列表
	 * @param p_id
	 * @param p_name
	 * @return
	 */
	@RequestMapping("/page")
	public ModelAndView page(@RequestParam(value="pid",defaultValue="null",required=true)String p_id,
			@RequestParam(value="pname",defaultValue="null",required=true)String p_name) {
		ModelAndView modelAndView = new ModelAndView("/channel/channel");
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
		Channel c = new Channel();
		if ("null".equals(p_id)) {
			modelAndView.addObject("pid", Constant.ROOT_ID);
			modelAndView.addObject("pname", Constant.ROOT_NAME);
			c.setPid(Constant.ROOT_ID);
					
		}else {
			modelAndView.addObject("pid", Integer.valueOf(p_id));	
			modelAndView.addObject("pname", p_name);
			c.setPid(Integer.valueOf(p_id));
		}
		int currpage = SystemContext.getPageOffset();
		int pagesize = SystemContext.getPageSize();
		PageDto<Channel> channel= channelService.getPageList(c, currpage, pagesize);
		if (channel!=null) {
			modelAndView.addObject("channels", channel);
			modelAndView.addObject("total", channel.getTotal());
		}			
		return modelAndView;
	}
	
	
	/**
	 * 添加栏目页面
	 * @param pid
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/addPage/{pid}")
	public ModelAndView addPage(@PathVariable int pid,HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView("/channel/add");
		String pname = request.getParameter("pname");
		pname = new String(pname.getBytes("iso-8859-1"),"utf-8");
		modelAndView.addObject("pname", pname);
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 添加栏目
	 * @param channel
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add(Channel channel){
		ModelAndView modelAndView = new ModelAndView("/channel/add");
		if (channel.getId()!=null) {
			Integer pid = channel.getPid();
			boolean result = channelService.createNewChannel(channel);
			modelAndView.addObject(Constant.SUCCESS, result);	
			modelAndView.addObject("pid", pid);
			Channel dto = new Channel();
			dto.setId(pid);
			List<Channel> temp = channelService.getAllList(dto);
			if (temp!=null&&temp.size()>0) {
				modelAndView.addObject("pname", temp.get(0).getName());
			}
		}
		return modelAndView;
	}
	/**
	 * 删除子栏目
	 * @param id 子栏目id
	 * @param pid 父栏目id
	 * @return
	 */
	@RequestMapping("/delete/{id}/{pid}")
	private ModelAndView delete(@PathVariable int id,@PathVariable int pid){
		Channel c = new Channel();
		c.setPid(id);
		List<Channel> subChannel = channelService.getAllList(c);
		if (subChannel!=null && subChannel.size()>0) {
			ModelAndView modelAndView = new ModelAndView("/channel/error");
			modelAndView.addObject(Constant.RESULT_REASION, "删除栏目包含子栏目");
			return modelAndView;
		}else {
			Channel channel = new Channel();
			channel.setId(id);
			boolean result = channelService.delete(channel);
			if (result) {
				Channel channel2 = new Channel();
				channel2.setId(pid);
				List<Channel> channels = channelService.getAllList(channel2);
				if (channels.size()>0) {
					return page(String.valueOf(pid), channels.get(0).getName());
				}else {
					return page("null", null);
				}
				
			}else {
				ModelAndView modelAndView = new ModelAndView("/channel/error");
				modelAndView.addObject(Constant.RESULT_REASION, "删除栏目包含子栏目");
				return modelAndView;
			}
		}
	}
	/**
	 * 更新子栏目页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/update/{id}")
	public ModelAndView updateChannel(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView("/channel/update");
		Channel channel = new Channel();
		channel.setId(id);
		channel = channelService.getAllList(channel).get(0);
		Integer pid = channel.getPid();
		String target = JSON.toJSONString(channel);
		modelAndView.addObject("channel", target);
		Channel pChannel = new Channel();
		pChannel.setId(pid);
		pChannel = channelService.getAllList(pChannel).get(0);
		modelAndView.addObject("pid", pChannel.getId());
		modelAndView.addObject("pname",pChannel.getName());
		return modelAndView;
	}
	/**
	 * 更新栏目
	 * @param channel
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView update(Channel channel){
		ModelAndView modelAndView = new ModelAndView("/channel/update");
		if (channel.getId()!=null) {
			Integer pid = channel.getPid();
			boolean result = channelService.update(channel);
			modelAndView.addObject(Constant.SUCCESS, result);	
			modelAndView.addObject("pid", pid);
			Channel dto = new Channel();
			dto.setId(pid);
			List<Channel> temp = channelService.getAllList(dto);
			if (temp!=null&&temp.size()>0) {
				modelAndView.addObject("pname", temp.get(0).getName());
				return page(String.valueOf(pid), temp.get(0).getName());
			}
		}
		return modelAndView;
	}
}
