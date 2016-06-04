package com.feng.learn.client;

import com.feng.learn.http.invoker.api.model.Key;

public class RsaPrivateKeyService implements KeyService {

    private String serviceRelativeURL;

    @Override
    public Key loadKeyFromServer(String ipOfServiceProvider) {
	Key keyFromServer = null;
	return keyFromServer;
    }

    private String getSerivceURL(String ipOfServiceProvider, String serviceRelativeURL) {
	return "http://" + ipOfServiceProvider + serviceRelativeURL;
    }

    public String getServiceRelativeURL() {
	return serviceRelativeURL;
    }

    public void setServiceRelativeURL(String serviceRelativeURL) {
	this.serviceRelativeURL = serviceRelativeURL;
    }

}
