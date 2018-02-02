package com.sun.cms.web.controller.login;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.sun.cms.web.dto.RoleDto;
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
		
		if (code!=null) {
			if (code.equals(checkcode)) {
				UserDto user = new UserDto();
				user.setUserId(userid);
				UserDto userInfo = userService.getOne(user);
				if (userInfo!=null) {
					if (userInfo.getPassword().equals(SecurityUtil.getMD5(password))) {
						request.getSession().setAttribute("userInfo", userInfo);
						List<RoleDto> roles = userService.havingRoles(userInfo);
						request.getSession().setAttribute("role", roles);
						boolean isadmin = isAdmin(roles);
						request.getSession().setAttribute("isAdmin", isadmin);
						ModelAndView modelAndView = new ModelAndView("admin/main");
						modelAndView.addObject("user", userInfo.getUserName());
						if (!isadmin) {
							Set<String> actions = getAllAction(roles, session);
							session.setAttribute("allAction", actions);
						}
						return modelAndView;
					}else {
						ModelAndView modelAndView = new ModelAndView("admin/login");
						modelAndView.addObject("error", "输入密码不正确!");
						return modelAndView;
					}			
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
		}else {
			ModelAndView modelAndView = new ModelAndView("admin/login");
			return modelAndView;
		}
		
		
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session){
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	private boolean isAdmin(List<RoleDto> roles){
		if (roles!=null) {
			for (RoleDto roleDto : roles) {
				if (roleDto.getRoleId().equals("admin")) {
					return true;
				}
			}
			return false;
		}else {
			return false;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Set<String> getAllAction(List<RoleDto> roles,HttpSession session){
		Map<String, Set<String>> auths = (Map<String, Set<String>>) session.getServletContext().getAttribute("allAuths");
		Set<String> actions = new HashSet<>();
		if (auths!=null) {
			for (RoleDto role : roles) {
				String roleName = role.getRoleId();
				if (auths.containsKey(roleName)) {
					Set<String> action = auths.get(roleName);
					if (action!=null) {
						actions.addAll(action);
					}
				}	
			}
			actions.addAll(auths.get("base"));
		}	
		return actions;
	}

}
