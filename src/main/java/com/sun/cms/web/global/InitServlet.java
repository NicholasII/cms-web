package com.sun.cms.web.global;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.cms.web.auth.AuthUtil;
import com.sun.cms.web.utils.BaseinfoUtil;

public class InitServlet extends HttpServlet {

	/**
	 * @author dongqun
	 * 2018年1月31日下午2:14:35
	 */
	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	private static final String pname = "com.sun.cms.web.controller";
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		Map<String, Set<String>> auth = AuthUtil.initAuth(pname);
		this.getServletContext().setAttribute("allAuths", auth);
		this.getServletContext().setAttribute("baseinfo", BaseinfoUtil.getInstance().read());
		System.out.println("-----------------------"+"系统初始化成功："+auth+"-----------------------");
	}
	
	public static WebApplicationContext getWC(){
		return wc;
	}
	
	

}
