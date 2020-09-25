package com.blockchain;


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
            minedHash = CriptoUtils.toSHA256(data + previousHash + nonce).toString();
        } while (!minedHash.substring(0, 4).equals("0000"));

        return minedHash;
    }


    
}
