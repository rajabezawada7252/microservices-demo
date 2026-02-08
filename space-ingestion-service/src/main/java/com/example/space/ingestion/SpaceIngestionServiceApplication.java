package com.example.space.ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class SpaceIngestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceIngestionServiceApplication.class, args);
	}

}
