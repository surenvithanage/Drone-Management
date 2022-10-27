package com.server.musalasoft.drone_management.scheduler;

import com.server.musalasoft.drone_management.entity.Drone;
import com.server.musalasoft.drone_management.repository.DroneRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@EnableAsync
@Component
public class DroneScheduler {

    private final DroneRepository droneRepository;

    public DroneScheduler(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Scheduled(fixedRate = 10000)
    public void logBatteryLevel() throws InterruptedException {

        List<Drone> droneList = droneRepository.findAll();

        for (Drone drone : droneList) {
            log.info("--------------------------------- Drone Battery Check -------------------------------------------------");
            log.info("SerialNumber:  " + drone.getSerialNumber() + "  Battery Level: " + drone.getBatteryCapacity().toString());
            log.info("--------------------------------- Drone Battery Check -------------------------------------------------");
        }
        Thread.sleep(10000);
    }

}
