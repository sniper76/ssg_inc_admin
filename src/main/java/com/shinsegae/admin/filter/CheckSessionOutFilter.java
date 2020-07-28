package com.shinsegae.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinsegae.admin.common.constants.SysConstants;
import com.shinsegae.admin.entity.SessionUser;

@WebFilter
public class CheckSessionOutFilter implements Filter {
    protected FilterConfig filterConfig = null;

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hsrq = (HttpServletRequest) request;
        HttpServletResponse hsrp = (HttpServletResponse) response;
        String clientType = hsrq.getParameter("clientType");
        if ("WORD".equals(clientType)) {
            chain.doFilter(request, response);
        } else {
            SessionUser person = null;
            String reqPage = hsrq.getServletPath();
            if (!reqPage.trim().equals("/login.do") && !reqPage.trim().equals("/logout.do") && !excludeUrl((HttpServletRequest) request)
            		
                    ) {
                person = (SessionUser) hsrq.getSession().getAttribute(SysConstants.SESSION_USER_KEY);
                if (person == null) {
                    hsrp.sendRedirect("/login.do");
                    return;
                }
            }
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    private boolean excludeUrl(HttpServletRequest request) {
    	
    	String uri = request.getRequestURI().toString().trim();
    	
    	if(uri.startsWith("/fonts/") ||uri.startsWith("/ckeditor5/") || uri.startsWith("/css/")
                       ||uri.startsWith("/images/") || uri.startsWith("/js/") || uri.startsWith("/scss/") || uri.contains("Ajax")) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
