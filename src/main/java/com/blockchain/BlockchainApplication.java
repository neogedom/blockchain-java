package com.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainApplication.class, args);
		// Mining block
		// Block genesisBlock = new Block("000000000000000000000000000000000000000000000000000000000000", "Pool of transactions");
		
		// System.out.println("Hash: " + genesisBlock.getHash());
		// System.out.println("Nonce: " + genesisBlock.getNonce());


		// Creating public Key
		System.out.println("Wallet address: " + new Wallet().getAddress()); 

			}

}
