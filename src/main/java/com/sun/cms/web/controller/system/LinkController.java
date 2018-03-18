package com.sun.cms.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.model.PageDto;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.link.HyperLink;
import com.sun.cms.web.service.link.HyperLinkService;

@Controller
@RequestMapping("/system/link")
@AuthClass
public class LinkController extends BaseController<HyperLink> {
	
	@Autowired
	HyperLinkService linkService;
	
	
	@RequestMapping("/list")
	@AuthMethod(role="admin")
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("link/list");
		int pageNum = SystemContext.getPageOffset();
		int pageSize = SystemContext.getPageSize();
		PageDto<HyperLink> pageDto = linkService.getPageList(new HyperLink(), pageNum, pageSize);
		view.addObject("links", pageDto);
		return view;
	}
	
	@RequestMapping("/addPage")
	@AuthMethod(role="admin")
	public ModelAndView addPage(){
		ModelAndView view = new ModelAndView("link/add");
		return view;
	}
	
	@RequestMapping("/add")
	@AuthMethod(role="admin")
	public ModelAndView add(HyperLink link,HttpServletRequest request){
		String type = link.getType();
		if (type.contains("选择其他")) {
			type = type.split(",")[1];
			link.setType(type);
		}
		boolean result = linkService.addLink(link);
		if (result) {
			return list();
		}else {
			ModelAndView view = new ModelAndView("link/update");
			view.addObject("link", link);
			return view;
		}	
	}
	
	@RequestMapping("/updatePage/{id}")
	@AuthMethod(role="admin")
	public ModelAndView updatePage(@PathVariable int id){
		ModelAndView view = new ModelAndView("link/update");
		HyperLink link = new HyperLink();
		link.setId(id);
		link = linkService.getOne(link);
		view.addObject("link", link);
		return view;
	}
	
	@RequestMapping("/update")
	@AuthMethod(role="admin")
	public ModelAndView update(HyperLink link,HttpServletRequest request){
		boolean result = linkService.update(link);
		if (result) {
			return list();
		}else {
			ModelAndView view = new ModelAndView("link/update");
			view.addObject("link", link);
			return view;
		}	
	}
	
	@RequestMapping("/delete/{id}")
	@AuthMethod(role="admin")
	public ModelAndView delete(@PathVariable int id,HttpServletRequest request){
		HyperLink link = new HyperLink();
		link.setId(id);
		boolean result = linkService.delete(link);
		return list();	
	}
}
