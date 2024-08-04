package com.kafka.SmartMining.reader;

import com.kafka.SmartMining.model.TruckData;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class RandomWeatherDataReader implements ItemReader<TruckData> {

    private final Random random = new Random();

    private static final List<String> weatherConditions = Arrays.asList("ModerateRain", "Snow", "HeavyRain");

    @Override
    public TruckData read() throws Exception {
        TruckData truckData = new TruckData();
        truckData.setAvgSpeed(50);
        truckData.setEnginePerformance(random.nextInt(150 - 50 + 1) + 50);//50-150
        truckData.setTyrePressure(random.nextInt(75 - 25 + 1) + 25);//25-75
        truckData.setEnvironmentalCondition(weatherConditions.get(random.nextInt(weatherConditions.size())));
        return truckData;
    }
}

