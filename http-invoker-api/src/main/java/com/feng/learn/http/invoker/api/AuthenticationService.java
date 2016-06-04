package com.feng.learn.http.invoker.api;

import com.feng.learn.http.invoker.api.model.Key;

public interface AuthenticationService {
    /**
     * 向服务器请求Key
     * 
     * @return
     */
    Key getKey();
}
