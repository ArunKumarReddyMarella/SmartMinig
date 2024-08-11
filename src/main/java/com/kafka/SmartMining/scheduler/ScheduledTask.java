package com.kafka.SmartMining.scheduler;

import com.kafka.SmartMining.kafka.KafkaProducerService;
import com.kafka.SmartMining.model.TruckData;
import com.kafka.SmartMining.model.TruckEvent;
import com.kafka.SmartMining.processor.TruckDataProcessor;
import com.kafka.SmartMining.reader.RandomWeatherDataReader;
import com.kafka.SmartMining.service.TruckDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    private RandomWeatherDataReader randomWeatherDataReader;
    private TruckDataService truckDataService;
    private KafkaProducerService kafkaProducerService;

    public ScheduledTask(RandomWeatherDataReader randomWeatherDataReader, TruckDataService truckDataService, KafkaProducerService kafkaProducerService){
        this.randomWeatherDataReader=randomWeatherDataReader;
        this.truckDataService=truckDataService;
        this.kafkaProducerService=kafkaProducerService;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 5000) // Run every 5000 milliseconds (5 seconds)
    public void runTask() {
        try {
            TruckData truckData = randomWeatherDataReader.read();
            truckData.setIsFailure(TruckDataProcessor.isLikelyToFail(truckData));
//            truckDataService.saveTruck(truckData);
            TruckEvent truckEvent = new TruckEvent(truckData);
            kafkaProducerService.sendMessage("truck-data", truckEvent.toString());
            logger.info("Truck Event: {}",truckEvent.toString());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
