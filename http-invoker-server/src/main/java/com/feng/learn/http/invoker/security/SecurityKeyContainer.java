package com.feng.learn.http.invoker.security;

import com.feng.learn.http.invoker.api.security.RSACoder;
import com.feng.learn.http.invoker.api.security.RSAKeyPair;

public class SecurityKeyContainer {

    private static class KeyHolder {
	private static RSAKeyPair rsaKey = RSACoder.initKeyPair();

	public static RSAKeyPair getKey() {
	    return KeyHolder.rsaKey;
	}
    }

    public static RSAKeyPair getKey() {
	return KeyHolder.getKey();
    }

}
