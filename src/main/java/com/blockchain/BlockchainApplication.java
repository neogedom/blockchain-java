package com.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainApplication.class, args);
		// Block genesisBlock = new Block("000000000000000000000000000000000000000000000000000000000000", "Pool of transactions");
		
		// System.out.println("Hash: " + genesisBlock.getHash());
		// System.out.println("Nonce: " + genesisBlock.getNonce());

		System.out.println(CriptoUtils.getPublicKey()); 
		//System.out.println(CriptoUtils.getPublicKey("MHcCAQEEIEwyVtplEEwsZoNphLdK6TDJhuwLiVrv+IHmDkBNlQVuoAoGCCqGSM49AwEHoUQDQgAEe6LgV5zwM0hvuhX4hrpnJYofzDrFZZtdcLElZHgbxtpEjpZpgeNq8D1QACIQtwZXtAYdGf1PJkH58V4OE7ka8w=="));
	}

}
