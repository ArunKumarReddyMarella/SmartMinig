package com.kafka.SmartMining.service.impl;

import com.kafka.SmartMining.model.TruckData;
import com.kafka.SmartMining.repository.TruckDataRepository;
import com.kafka.SmartMining.service.TruckDataService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TruckDataServiceImpl implements TruckDataService {
    private final TruckDataRepository truckDataRepository;

    public TruckDataServiceImpl(TruckDataRepository truckDataRepository) {
        this.truckDataRepository = truckDataRepository;
    }

    @Override
    public List<TruckData> getAllTrucks() {
        return new ArrayList<>(truckDataRepository.findAll()); // Return a copy to avoid modifications
    }

    @Override
    public TruckData getTruckById(String trkId) {
        Optional<TruckData> optionalTruckData = truckDataRepository.findById(trkId);
        return optionalTruckData.orElse(null);
    }

    @Override
    public void saveTruck(TruckData truckData) {
        truckDataRepository.save(truckData);
    }

    @Override
    public void updateTruck(TruckData truckData) {
        TruckData existingTruck = getTruckById(truckData.getTrkId());
        if (existingTruck != null) {
            // Update existing truck properties
            existingTruck.setAvgSpeed(truckData.getAvgSpeed());
            // ... update other properties
        }
    }

    @Override
    public void deleteTruck(String trkId) {
        truckDataRepository.deleteById(trkId);
    }
}
