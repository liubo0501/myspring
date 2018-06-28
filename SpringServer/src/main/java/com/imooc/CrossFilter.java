package com.imooc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;


public class CrossFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest req = (HttpServletRequest)arg0;
		String origin = req.getHeader("Origin");
		if(!StringUtils.isEmpty(origin)){
			response.addHeader("Access-Control-Allow-Origin", origin);
		}
		response.addHeader("Access-Control-Allow-Methods", "GET");
		
		arg2.doFilter(arg0, arg1);
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
