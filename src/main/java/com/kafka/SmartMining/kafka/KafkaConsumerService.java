package com.kafka.SmartMining.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.SmartMining.model.TruckEvent;
import com.kafka.SmartMining.service.TruckDataService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper;

    private final TruckDataService truckDataService;

    public KafkaConsumerService(ObjectMapper objectMapper, TruckDataService truckDataService) {
        this.objectMapper = objectMapper;
        this.truckDataService = truckDataService;
    }

    @KafkaListener(topics = "truck-data", groupId = "truck-group1")
    public void listen(@Payload ConsumerRecord<String,String> consumerRecord){
        executeEvent(consumerRecord);
    }

    private void executeEvent(ConsumerRecord<String, String> consumerRecord) {
        logger.info("consumer record:{}",consumerRecord);
        TruckEvent truckEvent = null;
        try {
            truckEvent = objectMapper.readValue(consumerRecord.value(), TruckEvent.class);
            truckDataService.saveTruck(truckEvent.getTruckData());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logger.info("consumed truckEvent:{}",truckEvent);
    }
}

