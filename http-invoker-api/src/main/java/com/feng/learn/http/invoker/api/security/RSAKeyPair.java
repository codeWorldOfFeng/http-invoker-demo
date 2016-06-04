package com.feng.learn.http.invoker.api.security;

public class RSAKeyPair {

    private byte[] privateKey;
    private byte[] publicKey;

    public RSAKeyPair() {
    }

    public RSAKeyPair(byte[] privateKey, byte[] publicKey) {
	this.privateKey = privateKey;
	this.publicKey = publicKey;
    }

    public byte[] getPrivateKey() {
	return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
	this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
	this.publicKey = publicKey;
    }
}
