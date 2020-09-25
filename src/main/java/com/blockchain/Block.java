package com.blockchain;

import java.io.UnsupportedEncodingException;

public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private String timeStamp;
    private int nonce;

    public Block() {
    }

    public Block(final String previousHash, final String data) {
        this.previousHash = previousHash;
        this.data = data;
        this.hash = generateHash();
    }

    public String getHash() {

        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(final String previousHash) {
        this.previousHash = previousHash;
    }

    public String getData() {
        return data;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    private String generateHash() {
        nonce = -1;
        String minedHash;
        do {
            nonce += 1;
            byte[] blockHashBytes = null;
            blockHashBytes = CriptoUtils.hexStringToByteArray(data + previousHash + nonce);
            minedHash = CriptoUtils.toSHA256(blockHashBytes).toString();
        } while (!minedHash.substring(0, 4).equals("0000"));

        return minedHash;
    }


    
}
