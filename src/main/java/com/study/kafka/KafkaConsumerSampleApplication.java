package com.study.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaConsumerSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerSampleApplication.class, args);
	}

}
