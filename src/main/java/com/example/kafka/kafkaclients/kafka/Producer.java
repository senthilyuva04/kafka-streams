package com.example.kafka.kafkaclients.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
@Autowired
private KafkaTemplate<String,List<Invoice>> kafkaTemplate;

/**
 * Sends a message using the given message to the "test-topic" in Kafka.
 *
 * @param  message  the message to be sent
 * @return         void
 */
public void sendMessage(String message) {
    Invoice invoice = new Invoice();
    invoice.setInvoiceId(UUID.randomUUID().toString());
    invoice.setName(message);
    invoice.setValue(12345L);
    List<Invoice> invoices = new ArrayList<>();
    invoices.add(invoice);
    kafkaTemplate.send("test-topic", invoices);
}
}
