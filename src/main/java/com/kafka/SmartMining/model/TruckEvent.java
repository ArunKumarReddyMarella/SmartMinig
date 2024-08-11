package com.kafka.SmartMining.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "TruckEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", location='" + location + '\'' +
                ", driverId='" + driverId + '\'' +
                ", truckData=" + truckData +
                '}';
    }
}
