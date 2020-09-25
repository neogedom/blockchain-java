package com.blockchain;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

public class Wallet {
    private String address;
    private  PublicKey publicKey;
    private  PrivateKey privateKey;

    public String getPublicKey() {

        getPrivateKey();
        ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
        ECPoint point = ecPublicKey.getW();
        String sx = adjustTo64(point.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(point.getAffineY().toString(16)).toUpperCase();
        String bcPub = "04" + sx + sy;
        
        return bcPub;
    }

    public String getPrivateKey() {

        String strPrivateKey = "";
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecSpec);
            KeyPair pair = keyPairGenerator.genKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();

            ECPrivateKey ecPrivateKey = (ECPrivateKey) privateKey;
            strPrivateKey =  adjustTo64(ecPrivateKey.getS().toString(16).toUpperCase());

        } catch (GeneralSecurityException e) {
            
            e.printStackTrace();
        }
        return "s[" + strPrivateKey.length() +"]: " +  strPrivateKey;
    }



    private String adjustTo64(String s) {
        switch (s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
    
    public String generateAddress(String message) {
        CriptoUtils.toSHA256(getPublicKey());
        return address;
    }
}
