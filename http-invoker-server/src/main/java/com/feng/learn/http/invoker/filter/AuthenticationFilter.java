package com.feng.learn.http.invoker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse resp = (HttpServletResponse) response;

	if (checkRequest(req, resp)) {
	    chain.doFilter(request, response);
	} else {
	    logger.debug("authenticationFilter.doFilter() fail");
	}

    }

    private boolean checkRequest(HttpServletRequest req, HttpServletResponse response) {
	return true;
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

}
