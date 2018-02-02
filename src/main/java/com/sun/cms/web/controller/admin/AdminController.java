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
import com.sun.cms.common.utils.SecurityUtil;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.dto.GroupDto;
import com.sun.cms.web.dto.RoleDto;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.service.user.UserService;
import com.sun.cms.web.utils.Constant;
@Controller
@RequestMapping("/admin")
@AuthClass
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@AuthMethod
	@RequestMapping("/navi")
	public ModelAndView navi(){
		return new ModelAndView("/admin/navi");
	}
	
	@RequestMapping("/checkcode")
	@AuthMethod
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
	
	@AuthMethod
	@RequestMapping("/user/updateSelf")
	public ModelAndView updateSelf(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("/admin/update");
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
	
	@AuthMethod
	@RequestMapping("/user/showSelf")
	public ModelAndView showSelf(HttpServletRequest request,HttpSession session){
		ModelAndView modelAndView = new ModelAndView("/admin/user");
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

	@AuthMethod
	@RequestMapping("/user/updatePwd/page")
	public ModelAndView updatePwdPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/admin/password");
		return modelAndView;
	}
	
	@AuthMethod
	@RequestMapping("/user/updatePwd")
	public ModelAndView updatePwd(HttpServletRequest request,HttpSession session){
		ModelAndView map = new ModelAndView("/admin/password");
		String pwd = request.getParameter("origin_password");
		String repwd = request.getParameter("password");
		UserDto userinfo = (UserDto)session.getAttribute("userInfo");
		if (pwd!=null&&repwd!=null) {
			pwd = SecurityUtil.getMD5(pwd);
			if (userinfo!=null) {
				System.out.println(pwd);
				System.out.println(userinfo.getPassword());
				if (pwd.equals(userinfo.getPassword())) {
					repwd = SecurityUtil.getMD5(repwd);
					UserDto userDto = new UserDto();
					userDto.setUserId(userinfo.getUserId());
					userDto.setPassword(repwd);
					boolean result = userService.update(userDto);
					if (result) {
						map.addObject("response", "密码重置成功!");
					}else {
						map.addObject("response", "密码重置失败!");
					}
				}else{
					map.addObject("response", "输入密码不正确!");
				}
			}
		}else {
			map.addObject("response", "密码不能为空");
		}
		
		return map;
	}
	
}
