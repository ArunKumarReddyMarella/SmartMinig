package com.kafka.SmartMining.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.SmartMining.model.TruckEvent;
import com.kafka.SmartMining.service.TruckDataService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.topic}")
    private String topic;

    @Autowired
    private ObjectMapper objectMapper;

    private final TruckDataService truckDataService;

    public KafkaConsumerService(TruckDataService truckDataService) {
        this.truckDataService = truckDataService;
    }

//    @KafkaListener(topics = "truck-data", groupId = "truck-group1")
//    public void listen(String message) {
//        System.out.println("Received message: " + message);
//    }
    @KafkaListener(topics = "truck-data", groupId = "truck-group1")
    public void listen(@Payload ConsumerRecord<String,String> consumerRecord){
        executeEvent(consumerRecord);
    }

    private void executeEvent(ConsumerRecord<String, String> consumerRecord) {
        try{
            if(Objects.isNull(consumerRecord.value())){
                System.out.println("There is no body for the message");
            }
            else{
                TruckEvent truckEvent = objectMapper.convertValue(consumerRecord.value(), TruckEvent.class);
                System.out.println(truckEvent.toString());
            }
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }
}

