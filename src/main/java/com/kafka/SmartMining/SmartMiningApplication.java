package com.kafka.SmartMining;

import com.kafka.SmartMining.model.TruckData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SmartMiningApplication {

	public static List<TruckData> readCSV(String filePath) throws IOException {
		List<TruckData> truckDataList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				TruckData truckData = new TruckData();
				truckData.setTrkId(values[0]);
				truckData.setAvgSpeed(Integer.parseInt(values[1]));
				truckData.setEnginePerformance(Integer.parseInt(values[2]));
				truckData.setTyrePressure(Integer.parseInt(values[3]));
				truckData.setEnvironmentalCondition(values[4]);
				truckDataList.add(truckData);
			}
		}
		return truckDataList;
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartMiningApplication.class, args);

	}

}
