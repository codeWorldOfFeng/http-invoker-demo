package com.feng.learn.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;
import org.springframework.remoting.support.RemoteInvocationResult;

import com.feng.learn.http.invoker.api.security.SecurityService;

public class CustomHttpInvokerRequestExecutor extends HttpComponentsHttpInvokerRequestExecutor {

    @Autowired
    private SecurityService securityService;

	@Override
	protected RemoteInvocationResult doExecuteRequest(HttpInvokerClientConfiguration config, ByteArrayOutputStream baos)
			throws IOException, ClassNotFoundException {

		HttpPost postMethod = createHttpPost(config);
		setRequestBody(config, postMethod, baos);
		try {
			encryptDataByRsaBeforeSend(postMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			HttpResponse response = executeHttpPost(config, getHttpClient(), postMethod);
			validateResponse(config, response);
			InputStream responseBody = getResponseBody(config, response);
			return readRemoteInvocationResult(responseBody, config.getCodebaseUrl());
		}
		finally {
			postMethod.releaseConnection();
		}
	}
	/**
	 * 发送请求前加密发送数据和对数据进行数字签名
	 * @param postMethod
	 * @throws Exception 
	 */
	protected void encryptDataByRsaBeforeSend(HttpPost postMethod) throws Exception {
		HttpEntity entity = postMethod.getEntity();
		byte[] data = new byte[ (int) entity.getContentLength()];
		entity.getContent().read(data);
		byte[] encryptData = securityService.encryptByClient(data);
		String signature = securityService.signByClient(encryptData);
		entity = new ByteArrayEntity(encryptData);
		postMethod.setEntity(entity);
		//postMethod.setHeader(HTTP_HEADER_CONTENT_LENGTH, ((Long) entity.getContentLength()).toString());
		postMethod.addHeader("signature", signature);
	}
	public SecurityService getSecurityService() {
		return securityService;
	}
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
}
