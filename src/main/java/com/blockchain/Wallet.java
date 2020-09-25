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

    private ECPrivateKey ecPrivateKey;
    private ECPublicKey ecPublicKey;
    private String address;

    public Wallet() {
        generateKeys();
        this.address = generateAddress();

    }

    public String getPrivateKey() {
        return adjustTo64(ecPrivateKey.getS().toString(16).toUpperCase());
    }

    public byte[] getPublicKey() {
        ECPoint point = ecPublicKey.getW();

        String sx = adjustTo64(point.getAffineX().toString(16)).toUpperCase();
        String sy = adjustTo64(point.getAffineY().toString(16)).toUpperCase();
        
        return  CryptoUtils.hexStringToByteArray("04" + sx + sy);
    }

    public String getAddress() {
        return address;
    }

    private ECPrivateKey generateKeys() {

        ECPrivateKey ecPrivateKey = null;
        try {
            //Generating Private Key
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecSpec);
            KeyPair pair = keyPairGenerator.genKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            ecPrivateKey = (ECPrivateKey) privateKey;

            // Keeping Public Key
            ecPublicKey = (ECPublicKey) publicKey;
            

        } catch (GeneralSecurityException e) {     
            e.printStackTrace();
        }
        return ecPrivateKey;
    }

    private String generateAddress() {
        
        byte [] bytes = CryptoUtils.toSHA256(getPublicKey()); //Perform SHA-256 in publicKey
        byte [] bytesAfterRIPE160 = CryptoUtils.toRIPEMD160(bytes); // Perform RIPEMD-160 in SHA-256 result
        byte [] bytesAfterSHA256Twice = CryptoUtils.toSHA256(CryptoUtils.toSHA256(bytesAfterRIPE160)); // Perform SHA-256 twice in RIPE-160 result

        bytes = new byte[25]; // reusing bytes variable to create a checksum

        // First 4 bytes of the result of the second hashing is used as the address checksum 
        for (int i = 0 ; i < bytesAfterRIPE160.length ; i++) {
            bytes[i] = bytesAfterRIPE160[i];
        }
        for (int i = 0 ; i < 4 ; i++) {
            bytes[21 + i] = bytesAfterSHA256Twice[i];
        }
       

        //Encoding Address using Base58
        address = CryptoUtils.toBase58(bytes);
        
        return address;
    }

    // Support method to standarize key in 64 char
    private String adjustTo64(String s) {
        switch (s.length()) {
            case 62: return "00" + s;
            case 63: return "0" + s;
            case 64: return s;
            default:
                throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
    
    
}
