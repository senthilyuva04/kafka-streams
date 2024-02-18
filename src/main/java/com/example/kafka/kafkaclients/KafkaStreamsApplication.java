package com.example.kafka.kafkaclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.kafka.kafkaclients.kafka.Producer;

@SpringBootApplication
public class KafkaStreamsApplication implements CommandLineRunner{
@Autowired
private Producer producer;  
	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsApplication.class, args);
	}
	@Override
    public void run(String... args) throws Exception {
;		producer.sendMessage("This is a test Message");
    }

}
