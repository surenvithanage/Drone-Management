package com.server.musalasoft.drone_management.service;

import com.server.musalasoft.drone_management.bean.response.ResponseBean;
import com.server.musalasoft.drone_management.dto.DroneRequestDto;

public interface DroneService {
    ResponseBean registerDrone(DroneRequestDto droneRequestDto);

    ResponseBean loadDroneWithMedications(String serialNumber);

    ResponseBean checkLoadedMedicationItems(String serialNumber);

    ResponseBean checkDroneAvailability();

    ResponseBean obtainDroneBatteryLevel(String serialNumber);
}
