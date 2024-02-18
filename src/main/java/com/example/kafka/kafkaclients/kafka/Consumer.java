package com.example.kafka.kafkaclients.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class Consumer {
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;
    @Autowired
private Producer producer; 

    @KafkaListener(topics = "test-topic", groupId = "test", batch = "true")
    @CircuitBreaker(name = "test", fallbackMethod = "fallbackMethod")
    public void consume(List<Invoice> invoice) {
        if(circuitBreakerRegistry.circuitBreaker("test").getState().equals("CLOSED")){
            System.out.println("Consumed message: " + invoice);
            System.out.println("Circuit breaker state: " + circuitBreakerRegistry.circuitBreaker("test").getState());
            circuitBreakerRegistry.circuitBreaker("test").transitionToOpenState();
            System.out.println("Circuit breaker state: " + circuitBreakerRegistry.circuitBreaker("test").getState());
            producer.sendMessage("Test Circuit Breaker Open");
            throw new RuntimeException("Error Occurred in SFTP");
        }
    }

    public void fallbackMethod(Throwable throwable, List<Invoice> invoice) {
        System.out.println("Fallback method called");
    }
}
