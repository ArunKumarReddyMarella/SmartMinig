package com.kafka.SmartMining.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TruckEvent {
    private String eventId;                  // Unique identifier for the event
    private String eventType;                // Type of event (e.g., "Truck Data Update")
    private String location;                 // Location of the truck at the time of the event
    private String driverId;                 // Identifier for the driver
    private TruckData truckData;             // The truck data object


    public TruckEvent(TruckData truckData) {
        this.eventId = UUID.randomUUID().toString();
        this.eventType = "Truck Data Update"; // Default event type
        this.location = "Unknown";            // Default location (to be updated with actual data)
        this.driverId = "Unknown";            // Default driver ID (to be updated with actual data)
        this.truckData = truckData;
    }
}
