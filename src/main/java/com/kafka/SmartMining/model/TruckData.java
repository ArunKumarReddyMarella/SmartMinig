package com.kafka.SmartMining.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@ToString
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

}

