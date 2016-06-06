package com.feng.learn.http.invoker.api.security;

public class SecurityService {
	
	public static final String SIGNATURE = "signature";
	
	private String publicKey;
	private String privateKey;
	
	public byte[] encryptByClient(byte[] data) throws Exception {
		return RSACoder.encryptByPrivateKey(data, privateKey);
	}

	public String signByClient(byte[] data) throws Exception {
		return RSACoder.sign(data, privateKey);
	}

	public byte[] decryptByServer(byte[] data) throws Exception {
		return RSACoder.decryptByPublicKey(data, publicKey);
	}

	public boolean verifyByServer(byte[] data, String signature) throws Exception {
		if (null == signature) return false;
		return RSACoder.verify(data, publicKey, signature);
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
