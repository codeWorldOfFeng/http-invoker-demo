package com.feng.learn.servlet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/sc")
public class SpringController {
	
	@RequestMapping(value="/get")
	public void get(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/html;charset=utf-8");
		try {
			resp.getWriter().println("Hello spring Mvc. 你好。。");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
