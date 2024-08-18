package com.kafka.SmartMining.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.SmartMining.kafka.KafkaProducerService;
import com.kafka.SmartMining.model.TruckData;
import com.kafka.SmartMining.model.TruckEvent;
import com.kafka.SmartMining.reader.RandomWeatherDataReader;
import com.kafka.SmartMining.utils.TruckDataProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final ObjectMapper objectMapper;

    private final RandomWeatherDataReader randomWeatherDataReader;
    private final KafkaProducerService kafkaProducerService;

    public ScheduledTask(RandomWeatherDataReader randomWeatherDataReader, ObjectMapper objectMapper, KafkaProducerService kafkaProducerService){
        this.randomWeatherDataReader=randomWeatherDataReader;
        this.objectMapper = objectMapper;
        this.kafkaProducerService=kafkaProducerService;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 1000) // Run every 5000 milliseconds (5 seconds)
    public void runTask() {
        try {
            TruckData truckData = randomWeatherDataReader.read();
            assert truckData != null;
            truckData.setIsFailure(TruckDataProcessor.isLikelyToFail(truckData));
            TruckEvent truckEvent = new TruckEvent(truckData);
            String truckEventData = objectMapper.writeValueAsString(truckEvent);
            kafkaProducerService.sendMessage(topic, truckEventData);
            logger.info("produced Truck Event: {}", truckEventData);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
