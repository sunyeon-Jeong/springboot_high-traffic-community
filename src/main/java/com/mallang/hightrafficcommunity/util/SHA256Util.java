package com.mallang.hightrafficcommunity.util;

import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log4j2
public class SHA256Util {

    // key
    public static final String ENCRYPTION_TYPE = "SHA-256";

    // 암호화
    public static String encryptSHA256(String password) {

        String SHA256 = null;

        MessageDigest messageDigest;

        try {

            messageDigest = MessageDigest.getInstance(ENCRYPTION_TYPE);
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA256 = stringBuilder.toString();

        } catch (NoSuchAlgorithmException exception) {

            throw new RuntimeException("암호화 에러!", exception);

        }

        return SHA256;

    }

}