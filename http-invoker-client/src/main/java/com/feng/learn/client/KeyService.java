package com.feng.learn.client;

import com.feng.learn.http.invoker.api.model.Key;

public interface KeyService {
    /**
     * 向服务器请求秘钥
     * 
     * @param ipOfServiceProvider
     */
    public Key loadKeyFromServer(String ipOfServiceProvider);
}
