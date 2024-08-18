package com.kafka.SmartMining.controller;

import com.kafka.SmartMining.model.TruckData;
import com.kafka.SmartMining.service.TruckDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class TruckDataController {

    private final TruckDataService truckDataService;

    public TruckDataController(TruckDataService truckDataService) {
        this.truckDataService = truckDataService;
    }

    @GetMapping("/getTruckData")
    public ResponseEntity<List<TruckData>> getTruckEventData(){
        return ResponseEntity.ok(truckDataService.getAllTrucks());
    }
}
