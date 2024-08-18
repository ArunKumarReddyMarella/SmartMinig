package com.kafka.SmartMining.utils;

import com.kafka.SmartMining.model.TruckData;
import org.springframework.stereotype.Component;

@Component
public class TruckDataProcessor {

    private TruckDataProcessor(){}

    public static boolean isLikelyToFail(TruckData truckData) {
        boolean isSnowOrHeavyRain = truckData.getEnvironmentalCondition().equals("Snow") ||
                truckData.getEnvironmentalCondition().equals("HeavyRain");
        boolean isEnginePerformancePoor = truckData.getEnginePerformance() < 100;
        boolean isTyrePressureHigh = truckData.getTyrePressure() > 50;

        return isSnowOrHeavyRain || isEnginePerformancePoor || isTyrePressureHigh;
    }
}
