package com.sun.cms.web.filter;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.exception.CMSException;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userInfo");
		HandlerMethod hm  = (HandlerMethod)handler;
		if (userDto==null) {
			response.sendRedirect(request.getContextPath()+"/");
		}else{
			//如果是管理员，不加限制
			//其他用户：如果没有权限统一返回一个无权限的页面
			boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
			if (!isAdmin) {
				Set<String> actions = (Set<String>) request.getSession().getAttribute("allAction");
				String methodname = hm.getBean().getClass().getName()+"."+hm.getMethod().getName();
				if (!actions.contains(methodname)) {
					throw new CMSException("没有权限访问该功能，请联系管理员分配权限！");
				}
			}	
		}
		return true;
	}

	
	
}
