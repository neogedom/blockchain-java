package com.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class CriptoUtils {

    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static String toHash256(Object obj) {
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

    public PrivateKey getPrivateKey() {

        if (privateKey == null) {
            getPublicKey();
        }
        return privateKey;
    }

    public static PublicKey getPublicKey() {

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256, random);
            KeyPair pair = keyPairGenerator.genKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return publicKey;
    }

    public static PublicKey getPublicKey(String strPrivateKey) {

            try {
                byte[] signature = Base64.getDecoder().decode(strPrivateKey.getBytes("UTF-8"));
                EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(signature);                 KeyFactory keyFactory = KeyFactory.getInstance("EC");
                privateKey = keyFactory.generatePrivate(privateKeySpec);
                publicKey = keyFactory.generatePublic(privateKeySpec);
            } catch (InvalidKeySpecException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        
        return publicKey;
    }
}
