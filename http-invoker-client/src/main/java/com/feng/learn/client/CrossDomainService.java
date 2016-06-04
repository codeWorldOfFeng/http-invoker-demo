package com.feng.learn.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerRequestExecutor;
import org.springframework.stereotype.Service;

@Service
public class CrossDomainService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpInvokerRequestExecutor requestExecutor;

    @Autowired
    private Map<String, Object> serviceProxyBeans = new HashMap<String, Object>();

    @Autowired
    private Map<Class<?>, String> services;

    /**
     * 
     * @param interfaceClazz
     *            服务接口
     * @param ipOfServiceProvider
     *            远程服务主机的ip
     * @return 实现服务接口的远程代理对象
     */
    public <T> T getService(Class<T> interfaceClazz, String ipOfServiceProvider) {
	String serviceUrl = null;
	if (!services.containsKey(interfaceClazz)) {
	    logger.error("CrossDomainService 中未配置{}接口的服务地址", interfaceClazz);
	    throw new IllegalArgumentException("未找到接口 <" + interfaceClazz + "> 的远程服务地址。");
	} else {
	    serviceUrl = createServiceAbsoluteUrl(ipOfServiceProvider, services.get(interfaceClazz));
	}

	return getService(serviceUrl, interfaceClazz);
    }

    /**
     * 由服务的相对地址和服务主机的ip合成服务的绝对地址（默认采用 http 协议）
     * 
     * @param ip
     * @param serviceRelativeUrl
     * @return
     */
    private String createServiceAbsoluteUrl(String ip, String serviceRelativeUrl) {
	return "http://" + ip + serviceRelativeUrl;
    }

    /**
     * 
     * @param serviceUrl
     *            服务url的绝对地址（http://172.16.81.116:8080/jetty/cudService/
     *            personService）
     * @param interfaceClazz
     *            服务接口
     * @return 实现服务接口的远程代理对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getService(String serviceUrl, Class<T> interfaceClazz) {

	if (serviceProxyBeans.containsKey(serviceUrl)) {
	    // 先取缓存
	    return (T) serviceProxyBeans.get(serviceUrl);
	}
	HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();

	bean.setHttpInvokerRequestExecutor(requestExecutor); //

	bean.setServiceUrl(serviceUrl);
	bean.setServiceInterface(interfaceClazz);
	bean.afterPropertiesSet();

	// 缓存远程代理类
	Object proxyBean = bean.getObject();
	serviceProxyBeans.put(serviceUrl, proxyBean);

	return (T) proxyBean;
    }

    public HttpInvokerRequestExecutor getRequestExecutor() {
	return requestExecutor;
    }

    public void setRequestExecutor(HttpInvokerRequestExecutor requestExecutor) {
	this.requestExecutor = requestExecutor;
    }

    public Map<String, Object> getServiceProxyBeans() {
	return serviceProxyBeans;
    }

    public void setServiceProxyBeans(Map<String, Object> serviceProxyBeans) {
	this.serviceProxyBeans = serviceProxyBeans;
    }

    public Map<Class<?>, String> getServices() {
	return services;
    }

    public void setServices(Map<Class<?>, String> services) {
	this.services = services;
    }
}
