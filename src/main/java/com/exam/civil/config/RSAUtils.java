package com.exam.civil.config;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAUtils {
    public static final String KEY_ALGORITHM = "RSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";

    private static final String PRIVATE_KEY = "RSAPrivateKey";

    // 1024 bits 的 RSA 密钥对，最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;

    // 1024 bits 的 RSA 密钥对，最大解密密文大小
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static PublicKey publicKey = null;
    private static PrivateKey privateKey = null;

    // 生成密钥对
    public static void initKey(int keysize) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 设置密钥对的 bit 数，越大越安全
        keyPairGen.initialize(keysize);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 获取公钥
        publicKey =  keyPair.getPublic();
        // 获取私钥
        privateKey =  keyPair.getPrivate();
    }

    public static String getPublicKeyStr() {
        return new String(Base64.getEncoder().encode(publicKey.getEncoded()));
    }
    public static String getPrivateKeyStr() {
        return new String(Base64.getEncoder().encode(privateKey.getEncoded()));
    }

    public static String decrypt(String secretText) {
        try {
            // 生成私钥
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 密文解码
            byte[] secretTextDecoded = Base64.getDecoder().decode(secretText.getBytes("UTF-8"));
            byte[] tempBytes = cipher.doFinal(secretTextDecoded);
            return new String(tempBytes);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + secretText + "]时遇到异常", e);
        }
    }

}
