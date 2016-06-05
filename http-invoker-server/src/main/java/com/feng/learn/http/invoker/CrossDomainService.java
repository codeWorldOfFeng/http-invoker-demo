package com.feng.learn.http.invoker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;
import org.springframework.web.util.NestedServletException;

import com.feng.learn.http.invoker.api.security.SecurityService;

public abstract class CrossDomainService extends HttpInvokerServiceExporter {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SecurityService securityService;

	private void initCrossDomainService() {
		this.setService(this);
		this.setServiceInterface(this.getClass().getInterfaces()[0]);
	}

	public CrossDomainService() {
		initCrossDomainService();
	}
	
	protected RemoteInvocation readRemoteInvocation(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		String signature = req.getHeader(SecurityService.SIGNATURE);
		if (null == signature) return null;
		
		byte[] data = new byte[req.getContentLength()];
		req.getInputStream().read(data);
		
		if (!securityService.verifyByServer(data, signature)) { //验证签名
			return null;
		}
		
		byte[] decryptedData = securityService.decryptByServer(data); //解密数据
		if (null == decryptedData) {
			return null;
		}
		ObjectInputStream ois = createObjectInputStream(new ByteArrayInputStream(decryptedData));
		try {
			return doReadRemoteInvocation(ois);
		}
		finally {
			ois.close();
		}
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RemoteInvocation invocation = readRemoteInvocation(request, response);
			if (null == invocation){
				dealUnverifyRequest(request, response);
				return;
			}
			RemoteInvocationResult result = invokeAndCreateResult(invocation, getProxy());
			writeRemoteInvocationResult(request, response, result);
		}
		catch (ClassNotFoundException ex) {
			throw new NestedServletException("Class not found during deserialization", ex);
		} catch (Exception e) {
			dealUnverifyRequest(request, response);
		}
	}

	/**
	 * 处理为认证成功的请求
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	protected void dealUnverifyRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		logger.error("未认证的请求,请求方IP:{}", req.getRemoteAddr());
		resp.setStatus(402); // 为认证的请求
		resp.flushBuffer();
	}

	
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	

}
