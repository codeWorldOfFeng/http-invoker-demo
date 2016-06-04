package com.feng.learn.client;

import com.feng.learn.http.invoker.api.model.Key;

public class SecurityKeyHolder {

    private static Key key;

    public static Key getKey() {
	return key;
    }

    public static void setKey(Key key) {
	SecurityKeyHolder.key = key;
    }

}
