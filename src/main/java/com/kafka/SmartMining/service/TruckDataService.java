package com.kafka.SmartMining.service;

import com.kafka.SmartMining.model.TruckData;

import java.util.List;

public interface TruckDataService {
    List<TruckData> getAllTrucks();

    TruckData getTruckById(String trkId);

    void saveTruck(TruckData truckData);

    void updateTruck(TruckData truckData);

    void deleteTruck(String trkId);


}
