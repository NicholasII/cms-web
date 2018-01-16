package com.sun.cms.web.controller.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.common.utils.SecurityUtil;
import com.sun.cms.model.BaseDto;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.service.user.UserService;
import com.sun.cms.web.utils.Constant;
@Controller
public class LoginController extends BaseController<BaseDto>{
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public ModelAndView page(){
		return new ModelAndView("admin/login");
	}
	@RequestMapping("/main")
	public ModelAndView login(HttpServletRequest request,
			@RequestParam(value="name",required=true)String userid,
			@RequestParam(value="password",required=true)String password,
			@RequestParam(value="checkcode",required=true)String checkcode,
			HttpSession session){
		String code = (String) session.getAttribute(Constant.CHECKCODE);
		
		if (code.equals(checkcode)) {
			UserDto user = new UserDto();
			user.setUserId(userid);
			user.setPassword(SecurityUtil.getMD5(password));
			List<UserDto> dtos = userService.getAllList(user);
			if (dtos!=null && dtos.size()>0) {
				
				UserDto userInfo = dtos.get(0);
				request.getSession().setAttribute("userInfo", userInfo);
				ModelAndView modelAndView = new ModelAndView("admin/main");
				modelAndView.addObject("user", userInfo.getUserName());
				return modelAndView;
			}else {
				ModelAndView modelAndView = new ModelAndView("admin/login");
				modelAndView.addObject("error", "请注册用户'"+userid+"'!");
				return modelAndView;
			}
		}else{
			ModelAndView modelAndView = new ModelAndView("admin/login");
			modelAndView.addObject("error", "输入正确的验证码!");
			return modelAndView;
		}
		
	}

}
