package com.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

public class CriptoUtils {

    private static PublicKey publicKey;
    // private static PrivateKey privateKey;

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

    public static String getPublicKey() {

        getPrivateKey();
        ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
        ECPoint point = ecPublicKey.getW();
        String sx = adjustTo64(point.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(point.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;
        
        return "bcPub: " + bcPub;
    }

    public static String getPrivateKey() {

        String strPrivateKey = "";
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecSpec);
            KeyPair pair = keyPairGenerator.genKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            publicKey = pair.getPublic();

            ECPrivateKey ecPrivateKey = (ECPrivateKey) privateKey;
            strPrivateKey =  adjustTo64(ecPrivateKey.getS().toString(16).toUpperCase());

        } catch (GeneralSecurityException e) {
            
            e.printStackTrace();
        }

        return "s[" + strPrivateKey.length() +"]: " +  strPrivateKey;
    }



    private static String adjustTo64(String s) {
        switch (s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
}
