package com.sun.cms.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.model.PageDto;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.topic.AttachmentDto;
import com.sun.cms.web.service.syetem.SystemCleanService;

@Controller
@RequestMapping("/system/clean")
@AuthClass
public class SystemCleanController {
	
	@Autowired
	SystemCleanService systemCleanService;
	
	@RequestMapping("/page")
	@AuthMethod(role="admin")
	public ModelAndView page(){
		ModelAndView modelAndView = new ModelAndView("clean/clean");
		int attachSize = systemCleanService.getNoUseAttachSize();
		int indexpicSize = systemCleanService.getNoUseIndexPicSize();
		modelAndView.addObject("attach", attachSize);
		modelAndView.addObject("indexpics", indexpicSize);
		return modelAndView;
	}
	
	@RequestMapping("/attachPage")
	@AuthMethod(role="admin")
	public ModelAndView attachPage(){
		ModelAndView modelAndView = new ModelAndView("clean/cleanAtts");
		int pageSize = SystemContext.getPageSize();
		int pageNum = SystemContext.getPageOffset();
		PageDto<AttachmentDto> attach = systemCleanService.getNoUseAttachments(pageNum, pageSize);
		modelAndView.addObject("attachs", attach);
		return modelAndView;
	}
	
	@RequestMapping("/indexPage")
	@AuthMethod(role="admin")
	public ModelAndView indexPage(){
		ModelAndView modelAndView = new ModelAndView("clean/cleanPics");
		List<String> indexPics = systemCleanService.getNoUseIndexPics();
		modelAndView.addObject("indexPics", indexPics);
		return modelAndView;
	}
	
	
	@RequestMapping("/cleanAttach")
	@AuthMethod(role="admin")
	public ModelAndView cleanAttach(){
		ModelAndView modelAndView = new ModelAndView("clean/clean");
		systemCleanService.cleanAttach();
		int attachSize = systemCleanService.getNoUseAttachSize();
		int indexpicSize = systemCleanService.getNoUseIndexPicSize();
		modelAndView.addObject("attach", attachSize);
		modelAndView.addObject("indexpics", indexpicSize);
		return modelAndView;
	}
	
	@RequestMapping("/cleanIndexPic")
	@AuthMethod(role="admin")
	public ModelAndView cleanIndexPic(){
		ModelAndView modelAndView = new ModelAndView("clean/clean");
		systemCleanService.cleanIndexPic();
		int attachSize = systemCleanService.getNoUseAttachSize();
		int indexpicSize = systemCleanService.getNoUseIndexPicSize();
		modelAndView.addObject("attach", attachSize);
		modelAndView.addObject("indexpics", indexpicSize);
		return modelAndView;
	}
}
