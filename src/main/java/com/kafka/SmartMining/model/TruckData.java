package com.kafka.SmartMining.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@Entity
public class TruckData {
    @Id
    @Column
    private String trkId;
    @Column
    private int avgSpeed;
    @Column
    private int enginePerformance;
    @Column
    private int tyrePressure;
    @Column(length = 50)
    private String environmentalCondition;
    @Column
    private Boolean isFailure;

    public TruckData() {
        this.trkId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "TruckData{" +
                "trkId='" + trkId + '\'' +
                ", avgSpeed=" + avgSpeed +
                ", enginePerformance=" + enginePerformance +
                ", tyrePressure=" + tyrePressure +
                ", environmentalCondition='" + environmentalCondition + '\'' +
                '}';
    }
}

