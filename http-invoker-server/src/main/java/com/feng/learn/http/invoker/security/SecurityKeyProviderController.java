package com.feng.learn.http.invoker.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/securityKey")
public class SecurityKeyProviderController {

    @RequestMapping(value = "/getKey", method = RequestMethod.POST)
    public void getSecurityKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
	byte[] publicKey = SecurityKeyContainer.getKey().getPublicKey();
	response.setContentType("application/octet-stream");
	response.setContentLength(publicKey.length);
	response.getOutputStream().write(publicKey);
	response.flushBuffer();
    }
}
