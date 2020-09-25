package com.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class CriptoUtils {
    private static MessageDigest digest = null;
    private static byte[] bytes = null;
    private static StringBuffer buffer = null;

    public static String toSHA256(Object obj) {

        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(obj.toString().getBytes("UTF-8"));

            buffer = new StringBuffer();
            for (byte b : bytes) {
                buffer.append(String.format("%02x", b));
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        return buffer.toString();
    }

    public static String toRIPEMD160(Object obj) {
        try {
            digest = MessageDigest.getInstance("RipeMD160", "BC");
            bytes = digest.digest(obj.toString().getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | NoSuchProviderException | UnsupportedEncodingException e) {
            
            e.printStackTrace();
        }
        
        return buffer.toString();
    }

}
