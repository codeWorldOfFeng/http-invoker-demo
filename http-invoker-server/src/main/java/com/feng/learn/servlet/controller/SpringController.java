package com.feng.learn.servlet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/sc")
public class SpringController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(HttpServletRequest req, HttpServletResponse resp) {
	System.out.println(req.getContextPath()); // /jetty

	System.out.println(req.getRequestURI()); // /jetty/sc/get
	System.out.println(req.getRequestURL()); // http://localhost:8080/jetty/sc/get

	// System.out.println(req.getServletContext());
	System.out.println(req.getServletPath());
	System.out.println(req.getServerName());
	System.out.println(req.getServerPort());

	System.out.println(req.getLocalAddr());
	System.out.println(req.getLocalName());
	System.out.println(req.getLocalPort());

	System.out.println(req.getRemoteAddr());
	System.out.println(req.getRemotePort());
	System.out.println(req.getRemoteHost());
	resp.setContentType("text/html;charset=utf-8");
	try {
	    resp.getWriter().println("Hello spring Mvc. 你好。。(GET)");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void post(HttpServletRequest req, HttpServletResponse resp) {
	resp.setContentType("text/html;charset=utf-8");
	try {
	    resp.getWriter().println("Hello spring Mvc. 你好。。(POST)");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
