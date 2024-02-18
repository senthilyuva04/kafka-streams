package com.example.kafka.kafkaclients.kafka;

import lombok.Data;

@Data
public class Invoice {
private String invoiceId;
private Long value;
private String name;
}
