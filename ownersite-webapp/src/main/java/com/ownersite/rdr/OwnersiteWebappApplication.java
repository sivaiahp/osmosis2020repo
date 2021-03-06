package com.ownersite.rdr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories("com.ownersite.rdr.repository")
public class OwnersiteWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(OwnersiteWebappApplication.class, args);
	}
}
