package com.feng.learn.client;

import java.io.IOException;

import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;
import org.springframework.util.StringUtils;

public class CustomHttpInvokerRequestExecutor extends HttpComponentsHttpInvokerRequestExecutor {

    @Autowired
    private KeyService keyService;

    // 重写createHttpPost方法添加authenticationKey
    @Override
    protected HttpPost createHttpPost(HttpInvokerClientConfiguration config) throws IOException {
	HttpPost httpPost = new HttpPost(config.getServiceUrl());
	LocaleContext locale = LocaleContextHolder.getLocaleContext();
	if (locale != null) {
	    httpPost.addHeader(HTTP_HEADER_ACCEPT_LANGUAGE, StringUtils.toLanguageTag(locale.getLocale()));
	}
	if (isAcceptGzipEncoding()) {
	    httpPost.addHeader(HTTP_HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
	}
	if (SecurityKeyHolder.getKey() == null) {
	    keyService.loadKeyFromServer(ipOfServiceProvider);
	}
	return httpPost;
    }

}
