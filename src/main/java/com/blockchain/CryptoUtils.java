package com.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import com.google.bitcoin.core.Base58;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

// Class containing methods to manage cripto activities
public class CryptoUtils {
    private static MessageDigest digest = null;


    public static byte[] toSHA256(Object obj) {
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(((String) obj).getBytes("UTF-8"));
           
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        return bytes;
    }

    public static byte[] toSHA256(byte [] obj) {
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(obj);
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        return bytes;
    }

    public static String bufferedHASH256(Object obj) {
        MessageDigest digest = null;
        byte[] bytes = null;
        StringBuffer buffer = null;

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


    public static byte[] toRIPEMD160(byte [] obj) {
        byte[] bytes = null;
        try {
            Security.addProvider(new BouncyCastleProvider()); // Add BouncyCastle as Provider
            digest = MessageDigest.getInstance("RipeMD160", "BC");
            bytes = digest.digest(obj);
            bytes = addZeroesToBytes(bytes);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            
            e.printStackTrace();
        }
        return bytes;
    }

    public static String toBase58 (byte[] bytes) {
        return Base58.encode(bytes);
    }


    private static byte[] addZeroesToBytes(byte [] b1) {
        byte [] b2 = new byte[b1.length + 1];
        b2[0] = 0;
        for (int i = 0; i < b1.length; i++) {
            b2[i+1] = b1[i];
        }
        return b2;
    }

    public static byte[] hexStringToByteArray(String s) {
       
        System.out.println("Aqui: "+ s);
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;

    }


}
