package com.kafka.SmartMining.repository;

import com.kafka.SmartMining.model.TruckData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckDataRepository extends JpaRepository<TruckData,String> {
}
