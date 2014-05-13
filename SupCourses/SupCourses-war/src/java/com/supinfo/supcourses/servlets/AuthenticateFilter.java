package com.supinfo.supcourses.servlets;

import com.supinfo.supcourses.entities.User;
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

@WebFilter(filterName="AuthenticateFilter")
public class AuthenticateFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		User user = (User)request.getSession().getAttribute("user");
		if (user!=null)
		{
			chain.doFilter(req, res);
			return;
		}
		else
		{		
                        response.sendRedirect("../index");
			return;
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException
	{

	}
	
	public void destroy()
	{
		
	}
}
