package com.blockchain;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;

public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private String timeStamp;
    private Integer nonce;

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
            try {
                blockHashBytes = (data + previousHash + nonce).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            minedHash = Hex.encodeHexString(CriptoUtils.toSHA256(blockHashBytes));
        } while (!minedHash.substring(0, 4).equals("0000"));

        return minedHash;
    }


    
}
