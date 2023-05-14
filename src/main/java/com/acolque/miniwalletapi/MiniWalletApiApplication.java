package com.acolque.miniwalletapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiniWalletApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniWalletApiApplication.class, args);
	}

}
