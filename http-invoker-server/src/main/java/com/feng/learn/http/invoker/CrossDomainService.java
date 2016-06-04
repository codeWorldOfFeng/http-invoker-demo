package com.feng.learn.http.invoker;

import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

public abstract class CrossDomainService extends HttpInvokerServiceExporter {
    private void initCrossDomainService() {
	this.setService(this);
	this.setServiceInterface(this.getClass().getInterfaces()[0]);
    }

    public CrossDomainService() {
	initCrossDomainService();
    }
}
