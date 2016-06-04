package com.feng.learn.http.invoker.api.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public final class RSAKeys {
    public static final String PRIVATE_KEY;
    public static final String PUBLIC_KEY;

    static {
	Properties keys = new Properties();
	File keyProperties = null;
	try {
	    String fileUrl = RSAKeys.class.getResource("/RsaKeys.properties").getFile();
	    keyProperties = new File(fileUrl);
	    keys.load(new FileInputStream(keyProperties));
	} catch (Exception e) {
	    throw new RuntimeException("RsaKeys.properties not Exists..)");
	}
	String publicKey = keys.getProperty("publicKey", "");
	String privateKey = keys.getProperty("privateKey", "");
	if ("".equals(publicKey.trim()) || "".equals(privateKey.trim())) {
	    throw new RuntimeException("RSAKeys not initialized..");
	}
	PUBLIC_KEY = publicKey;
	PRIVATE_KEY = privateKey;
    }

    public static void main(String[] args) {
	Properties keys = new Properties();
	String fileUrl = RSAKeys.class.getResource("/RsaKeys.properties").getFile();
	File keyProperties = new File(fileUrl);
	Map<String, Object> keyMap = RSACoder.initKey();
	String publicKey = RSACoder.getPublicKey(keyMap);
	String privateKey = RSACoder.getPrivateKey(keyMap);
	keys.setProperty("publicKey", publicKey);
	keys.setProperty("privateKey", privateKey);
	try {
	    keys.store(new FileOutputStream(keyProperties), new Date().toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 获取RSA公钥
     * 
     * @return RSA公钥
     */
    public static String getPublicKey() {
	return PUBLIC_KEY;
    }

    /**
     * 获取RSA私钥
     * 
     * @return RSA私钥
     */
    public static String getPriateKey() {
	return PRIVATE_KEY;
    }
}
