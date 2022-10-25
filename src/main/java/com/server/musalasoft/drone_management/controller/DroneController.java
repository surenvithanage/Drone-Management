package com.server.musalasoft.drone_management.controller;

import com.server.musalasoft.drone_management.bean.response.ResponseBean;
import com.server.musalasoft.drone_management.dto.DroneRequestDto;
import com.server.musalasoft.drone_management.service.DroneService;
import com.server.musalasoft.drone_management.utility.function.Common;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/drone")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DroneController {

    private final Common common;

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<ResponseBean> registerDrone(@Valid @RequestBody DroneRequestDto requestDto) {
        ResponseBean responseBean = droneService.registerDrone(requestDto);
        return common.getResponseEntityBean(responseBean);
    }

    @GetMapping(value = "/medications/{serialNumber}")
    public ResponseEntity<ResponseBean> loadDroneWithMedications(@PathVariable String serialNumber) {
        ResponseBean responseBean = droneService.loadDroneWithMedications(serialNumber);
        return common.getResponseEntityBean(responseBean);
    }

    @GetMapping(value = "/check/medications/{serialNumber}")
    public ResponseEntity<ResponseBean> checkLoadedMedicationItems(@PathVariable String serialNumber) {
        ResponseBean responseBean = droneService.checkLoadedMedicationItems(serialNumber);
        return common.getResponseEntityBean(responseBean);
    }

    @GetMapping(value = "/find/available")
    public ResponseEntity<ResponseBean> checkDroneAvailability() {
        ResponseBean responseBean = droneService.checkDroneAvailability();
        return common.getResponseEntityBean(responseBean);
    }

    @GetMapping(value = "/battery_level/{serialNumber}")
    public ResponseEntity<ResponseBean> obtainDroneBatteryLevel(@PathVariable String serialNumber) {
        ResponseBean responseBean = droneService.obtainDroneBatteryLevel(serialNumber);
        return common.getResponseEntityBean(responseBean);
    }
}
