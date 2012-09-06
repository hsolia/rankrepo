package com.vidyo.util;

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
import javax.servlet.http.HttpSession;

import com.vidyo.beans.User;
import com.vidyo.common.VidyoConstants;

/**
 * Servlet Filter implementation class VdyoSecurityFilter
 */
@WebFilter({ "/nofilter/*"})
public class VdyoSecurityFilter implements Filter {

    /**
     * Default constructor. 
     */
    public VdyoSecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession(); 
		if(httpRequest.getRequestURI().toLowerCase().contains("/user/")){
			if(session != null && authorize(session, VidyoConstants.USER_ROLE_USER) ){
				chain.doFilter(request, response);
			}else{
				httpRequest.getSession(true).setAttribute("nextpage", httpRequest.getRequestURI()+"?"+httpRequest.getQueryString());
				((HttpServletResponse)response).sendRedirect(VidyoConstants.PORTAL_URL+"/pages/login.jsf"); 
				return;
			}
			
		}else if(httpRequest.getRequestURI().toLowerCase().contains("/admin/")){
			if(session != null && authorize(session, VidyoConstants.USER_ROLE_ADMIN) ){
				chain.doFilter(request, response);

			}else{
				httpRequest.getSession(true).setAttribute("nextpage", httpRequest.getRequestURI()+"?"+httpRequest.getQueryString());
				((HttpServletResponse)response).sendRedirect(VidyoConstants.PORTAL_URL+"/pages/ad-login.jsf"); 
				return;
			}

		}
		

	}

	public boolean authorize(HttpSession session, String requiredRole){
		
		User user = (User)session.getAttribute("user");
		
		if(user == null){
			return false;
		}
		
		String currentRole = user.getUserrole();
		if(currentRole.equalsIgnoreCase(requiredRole)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
