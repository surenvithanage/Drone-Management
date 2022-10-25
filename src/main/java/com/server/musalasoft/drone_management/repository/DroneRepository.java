package com.server.musalasoft.drone_management.repository;

import com.server.musalasoft.drone_management.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    Drone findBySerialNumber(String serialNumber);
    List<Drone> findByBatteryCapacityGreaterThan(Integer percentage);
}
