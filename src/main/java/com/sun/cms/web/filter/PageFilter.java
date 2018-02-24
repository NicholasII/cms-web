package com.sun.cms.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import com.sun.cms.web.dto.SystemContext;

@WebFilter(filterName="PageFilter", urlPatterns="/*",initParams={@WebInitParam(name="pageSize",value="10")})
public class PageFilter implements Filter {

	private Integer pageSize;
    /**
     * Default constructor. 
     */
    public PageFilter() {
        // TODO Auto-generated constructor stub
    	
    }


	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Integer currPage = 1;
		
	    try {
			currPage = Integer.valueOf(request.getParameter("pager.offset"))/pageSize+1;
		} catch (NumberFormatException e) {}
		try {
			SystemContext.setPageSize(pageSize);
			SystemContext.setPageOffset(currPage);
			SystemContext.setRealPath(((HttpServletRequest)request).getSession().getServletContext().getRealPath("/"));
			chain.doFilter(request, response);
		} finally {
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
			SystemContext.removeRealPath();
		}
				
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		 try {
			pageSize = Integer.valueOf(fConfig.getInitParameter("pageSize"));
		} catch (Exception e) {
			pageSize = 15;
		}
	}

}
