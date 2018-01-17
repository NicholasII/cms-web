package com.sun.cms.web.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.common.utils.Captcha;
import com.sun.cms.common.utils.JSONUtil;
import com.sun.cms.web.dto.GroupDto;
import com.sun.cms.web.dto.RoleDto;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.service.user.UserService;
import com.sun.cms.web.utils.Constant;
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/navi")
	public ModelAndView navi(){
		return new ModelAndView("/admin/navi");
	}
	
	@RequestMapping("/checkcode")
	public void checkcode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Captcha captcha = Captcha.getInstance();
		String checkcode = captcha.generateCheckCode();
		captcha.set(150, 30, 4);
		session.setAttribute(Constant.CHECKCODE,checkcode);
		BufferedImage bi =captcha.generateCheckImage(checkcode);
		OutputStream os = response.getOutputStream();
		ImageIO.write(bi, "jpg", os);
	}
	@RequestMapping("/user/updateSelf")
	public ModelAndView updateSelf(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("/user/update");
		UserDto userInfo = (UserDto)session.getAttribute("userInfo");
		if (userInfo!=null) {
			UserDto userDto = new UserDto();
			userDto.setUserId(userInfo.getUserId());
			UserDto user =  userService.getOne(userDto);	
			List<GroupDto> groups = userService.belongGroups(userDto);
			List<RoleDto> roles = userService.havingRoles(userDto);
			modelAndView.addObject("user", JSONUtil.ObjectToJSONObject(user));
			modelAndView.addObject("role", JSONUtil.ListToJSONArray(roles));
			modelAndView.addObject("group", JSONUtil.ListToJSONArray(groups));
		}	
		return modelAndView;
	}
	
}
